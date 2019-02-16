package gallantmedia.services.news;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonElement;
import com.webhoseio.sdk.WebhoseIOClient;
import java.net.URISyntaxException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.File;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

public class Goodnews
{
    private JsonElement generalNews;

    protected Map<String, String> filters;

    private String newsPath = "/opt/news/";


    /**
     * Builds the news filters, add more as fit
     */
    private void buildNewsFilters()
    {
        filters = new HashMap<>();
        filters.put("United States", "US");
        filters.put("Technology", "tech");
    }

    /**
     * If news is old, return false
     * @param newsFilter String the news type (tech, general etc.)
     * @return boolean if false, renders new news
     */
    private boolean checkLatestNews(String newsFilter)
    {
        Logger logger = LoggerFactory.getLogger(Goodnews.class);

        int oldNewsTime = 21600000;

        Date newsfiledate;
        String filename = newsPath + newsFilter + ".news";
        File file = new File(filename);
        logger.info("==-=gg=-> File name to render: " + filename);
        long fdate = file.lastModified();
        String sdate = Long.toString(fdate); // string date

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String ffdate = sdf.format(fdate);


        try {
            newsfiledate = sdf.parse(ffdate);
            logger.info("newsfiledate: " + newsfiledate);
        } catch(ParseException pe) {
            logger.info("Parse error detected for date:" +ffdate+" with "+ pe.getMessage());
            return true; // Parse error, don't waste news
        }

        // Calculate date differences between now and pre-renderd news if news even exists
        Date today = new Date();

        long datediff = today.getTime() - newsfiledate.getTime();
        //logger.info("dareDiff: " + datediff);

        if (!file.exists()) {
            logger.info("... News files not found ... ");
            return false;
        } else if (file.exists() && datediff > oldNewsTime) {
            logger.info("... Date difference between : "+today.getTime()+" and " + newsfiledate.getTime() +" == Date difference calculated: "+ datediff +" ... ");
            return false;
        }

        // news is fine, use pre-rendered news
        return true;
    }

    /**
     * Renders latest news from webhose.io if return false
     * In future will use whatever api we include
     * @return String A string that can be logged
     */
    public boolean renderLatestNews()
    {
        Logger logger = LoggerFactory.getLogger(Goodnews.class);

        buildNewsFilters();

        // Create a WebhoseIOClient instance
        WebhoseIOClient webhoseClient = WebhoseIOClient.getInstance("a3fedef2-8667-4555-a4f1-d31a98f1c5e6");
        Map<String, String> queries = new HashMap<>();

        Iterator it = filters.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry filter = (Map.Entry)it.next();

            // If news is old this returns false and gets new news
            if (!checkLatestNews(filter.getValue().toString())) {

                String filterString = "performance_score:>0 (title:\""+ filter.getKey().toString() +"\" OR title:"+filter.getValue().toString()+")";
                queries.put("q", filterString);
                queries.put("sort", "crawled");

                try {
                    generalNews = webhoseClient.query("filterWebContent", queries);
                    // Outputs and builds to the Filename of the filter variable or whatever (general.news tech.news)
                    writeNewsTemplate(generalNews, filter.getValue().toString());
                } catch(URISyntaxException iuri) {
                    logger.error("URI Exception on renderLatestNews() with " + iuri.getMessage());
                    return false;
                } catch(IOException iori) {
                    logger.error("IO Exception on renderLatestNews() with " + iori.getMessage());
                    return false;
                }

                it.remove();

                JsonElement totalNews = generalNews.getAsJsonObject().get("totalResults");     // Print posts count
                logger.info(" New " + filter.getKey().toString() + " news has been rendered -- count: " + totalNews.toString() );
            } else {
                logger.info(" Using pre-rendered " + filter.getKey().toString() + " news -- ");
            }
        }

        return true;
    }

    /**
     *
     * Heart of the service - Builds the news map, article by article
     * @param newsType String of news type to map
     * @return  map of news articles with full titles as key
     */
    private Map<String, Map<String,String>> buildNewsMap(String newsType)
    {
        Logger logger = LoggerFactory.getLogger(Goodnews.class);
        FileReader fread;
        Object newsObj;

        // The main news object //////
        Map<String, Map<String,String>> newsOrg = new HashMap<>();

        Map<String, Map<String,String>> noNews = new HashMap<>(); // No News is Good news... for exceptions

        // TODO: This can be simplified I'm sure, two Jsonparsers?
        JSONParser jParse = new JSONParser();
        JsonParser jParser;

        JsonToken toke;

        String newsContent = "/opt/news/"+newsType+".news";

        try {
            fread = new FileReader(newsContent);
            newsObj = jParse.parse(fread);
        } catch(FileNotFoundException fnfe) {
            logger.info("No news files present:" + fnfe.toString());
            return noNews;
        } catch(IOException ioe) {
            logger.info("No news files read:" + ioe.toString());
            return noNews;
        } catch(org.json.simple.parser.ParseException pe) {
            logger.info("No news files json parsed:" + pe.toString());
            return noNews;
        }

        // Parse through the String file of news
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String newsJsonString = gson.toJson(newsObj);

        JsonFactory jFactory = new JsonFactory();
        try {
            jParser = jFactory.createParser(newsJsonString);
        } catch(IOException ioe) {
            logger.info("No news from input/output: "+ioe.getMessage());
            return noNews;
        }

        Map<String, String> newsArticle = new HashMap<>();

        while(jParser != null && !jParser.isClosed()) {

            try {

                toke = jParser.nextToken();

                if(JsonToken.FIELD_NAME.equals(toke)) {
                    String newsField = jParser.getCurrentName();

                    if(newsField.equals("ord_in_thread")) {
                        if (newsArticle.get("title_full") != null) {
                            newsOrg.put(newsArticle.get("title_full"), newsArticle);
                        }
                        toke = jParser.nextToken();
                        newsArticle = new HashMap<>();
                    }

                    if (newsField.equals("title_full")) {
                        toke = jParser.nextToken();
                        newsArticle.put("title_full", jParser.getValueAsString());
                    }

                    if (newsField.equals("title")) {
                        toke = jParser.nextToken();
                        // Search for duplicates first
                        newsArticle.put("title", jParser.getValueAsString());
                    }

                    if (newsField.equals("author")) {
                        toke = jParser.nextToken();
                        newsArticle.put("author", jParser.getValueAsString());
                    }

                    if (newsField.equals("published")) {
                        toke = jParser.nextToken();
                        newsArticle.put("published", jParser.getValueAsString());
                    }

                    if (newsField.equals("url")) {
                        toke = jParser.nextToken();
                        String sourceUrl = "<a href=\""+jParser.getValueAsString()+"\" target=\"newssource\" >source</a>";
                        newsArticle.put("sourceUrl", sourceUrl);
                        newsArticle.put("url", jParser.getValueAsString());
                    }

                    if (newsField.equals("text")) {
                        toke = jParser.nextToken();
                        String newsText = jParser.getValueAsString();
                        newsArticle.put("text", newsText);
                    }

                    if (newsField.equals("main_image")) {
                        toke = jParser.nextToken();
                        String sizedImage = "<img width=\"350px\" height=\"225px\" align=\"left\" style=\"margin:5px;\" border=\"1\" src=\""+jParser.getValueAsString()+"\" >";
                        newsArticle.put("sizedImage", sizedImage);
                        newsArticle.put("hedImage", jParser.getValueAsString());
                    }
                }
            } catch(IOException ioe) {
                logger.error("No news from token input/output: "+ioe.getMessage());
                return noNews;
            }
        }

        return newsOrg;
    }


    /**
     * Gets the latest news depending on type, rendering view type
     * @param newsType String tech news or whatever news
     * @param viewType String links or full news view
     * @return String json block
     */
    public String getLatestNews(String newsType, String viewType)
    {
        Logger logger = LoggerFactory.getLogger(Goodnews.class);

        if (viewType == null) {
            viewType = "full";
        }

        // News check and possible render
        renderLatestNews();

        String bigNewsString = "";
        Map<String, Map<String,String>> newsOrg;

        newsOrg = buildNewsMap(newsType);

        if (viewType != "full") {
            // wants links
            bigNewsString = newsLinksView(newsOrg);
        } else {
            bigNewsString = newsWebView(newsOrg);
        }

        return bigNewsString;
    }


    /**
     * Builds a full news web view
     * @param newsOrg A map containing a String and another map which is the article data
     * @return String html view as links
     */
    private String newsLinksView(Map<String, Map<String,String>> newsOrg)
    {
        String bigNews = "";

        Map.Entry<String, Map<String,String>> newsRow;
        Map<String, String> newsArt;

        Iterator it = newsOrg.entrySet().iterator();
        while (it.hasNext()) {

            newsRow = (Map.Entry) it.next();
            String title = newsRow.getKey();
            newsArt = newsRow.getValue();

            if (newsArt.get("text").length() > 400) {

                bigNews += "<a target=\"newsSource\" href=\""+newsArt.get("url")+"\">"+newsArt.get("title")+"</a><br/>";

            }

            it.remove();
        }

        return bigNews;
    }


    /**
     * Builds a full news web view
     * @param newsOrg A map containing a String and another map which is the article data
     * @return String html view
     */
    private String newsWebView(Map<String, Map<String,String>> newsOrg)
    {
        String bigNews = "";
        Map<String, String> newsArt;
        Map.Entry<String, Map<String,String>> newsRow;
        Map.Entry<String,String> artRow;

        Iterator it = newsOrg.entrySet().iterator();
        while (it.hasNext()) {

            newsRow = (Map.Entry) it.next();
            String title = newsRow.getKey();
            newsArt = newsRow.getValue();

            if (newsArt.get("text").length() > 400) {

                //bigNews = "<a href=\""+newsArt.get("url")+"\">"+newsArt.get("title")+"</a><br/>";
                bigNews += "<div><h3>" + newsArt.get("title") + "</h3></div>";
                bigNews += "<div>";
                bigNews += "<div style=\"font-size:8pt;\"><em>" + newsArt.get("author") + " -- " + newsArt.get("published") + "</em></div>";
                bigNews += newsArt.get("sizedImage");
                bigNews += newsArt.get("text");
                bigNews += "</div>";
            }

            it.remove();
        }

        return bigNews;
    }


    /**
     * Writes the news content to file
     * @param templateContent JsonElement JsonTemplate to write to file from webhoseio
     * @param templateName String name of template (ex: tech.news)
     */
    private void writeNewsTemplate( JsonElement templateContent, String templateName )
    {
        FileOutputStream outputStream = null;
        String newsContent = templateContent.toString();

        try {
            outputStream = new FileOutputStream(newsPath + templateName +".news");
        } catch(FileNotFoundException fnf) {
            return;
        }

        byte[] strToBytes = newsContent.getBytes();
        try {
            outputStream.write(strToBytes);
            outputStream.close();
        } catch(IOException ioe) {
            return;
        }
    }


}