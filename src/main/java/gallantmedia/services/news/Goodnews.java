package gallantmedia.services.news;

import com.google.gson.JsonElement;
import com.webhoseio.sdk.WebhoseIOClient;
import java.net.URISyntaxException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.File;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import org.json.JSONArray;
import org.json.JSONTokener;
import org.json.JSONObject;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.json.JSONException;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

public class Goodnews
{
    private JsonElement techNews;

    private JsonElement generalNews;

    protected Map<String, String> filters;

    protected String newsPath = "/opt/news/";

    protected String newsResults = "Gallantnews reults: ";

    /**
     * Builds the news filters, add more as fit
     */
    protected void buildNewsFilters()
    {
        filters = new HashMap<>();
        filters.put("United States", "US");
        filters.put("Technology", "tech");
    }

    /**
     * If news is old, return false
     * @param newsFilter
     * @return
     */
    private boolean checkLatestNews(String newsFilter)
    {

        long oldNewsTime = 10000;

        Date newsfiledate;
        File file = new File(newsPath + newsFilter);
        long fdate = file.lastModified();
        String sdate = Long.toString(fdate); // string date

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String ffdate = sdf.format(fdate);

        try {
            newsfiledate = sdf.parse(sdate);
        } catch(ParseException pe) {
            newsResults += "...Parse error detected " + pe.getMessage() + "...";
            return true; // assume old news
        }

        // Calculate date differences between now and pre-renderd news if news even exists
        Date today = new Date();
        long datediff = today.getTime() - newsfiledate.getTime();

        if (!file.exists()) {
            newsResults += "... News files not found ... ";
            return false;
        } else if (file.exists() && datediff > oldNewsTime) {
            newsResults += "<br/>... Date difference between : "+today.getTime()+" and " + newsfiledate.getTime() +" == Date difference calculated: "+ datediff +" <br/>... ";
            return false;
        }

        // news is fine, use pre-rendered news
        return true;
    }

    /**
     * Renders latest news from webhose.io
     * In future will use whatever api we include
     * @return
     */
    public String renderLatestNews() {

        JsonElement result;

        buildNewsFilters();

        // Create a WebhoseIOClient instance
        WebhoseIOClient webhoseClient = WebhoseIOClient.getInstance("a3fedef2-8667-4555-a4f1-d31a98f1c5e6");
        Map<String, String> queries = new HashMap<String, String>();

        Iterator it = filters.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry filter = (Map.Entry)it.next();

            // If news is old this returns false and gets new news
            if (!checkLatestNews(filter.getKey().toString())) {

                String filterString = "performance_score:>0 (title:\""+ filter.getKey().toString() +"\" OR title:"+filter.getValue().toString()+")";
                queries.put("q", filterString);
                queries.put("sort", "crawled");

                try {
                    generalNews = webhoseClient.query("filterWebContent", queries);
                    // Outputs and builds to the Filename of the filter variable or whatever (general.news tech.news)
                    writeNewsTemplate(generalNews, filter.getValue().toString());
                } catch(URISyntaxException uri) {
                    result = null;
                } catch(IOException uri) {
                    result = null;
                    return "";
                }

                it.remove();
                newsResults += " New " + filter.getKey().toString() + " news has been rendered -- ";
                JsonElement totalNews = generalNews.getAsJsonObject().get("totalResults");     // Print posts count
            } else {
                newsResults += " Using pre-rendered " + filter.getKey().toString() + " news -- ";
            }
        }

        return newsResults + "The news has been written...";

    }

    // Getting specific news values for now
    public String getLatestTechNews()
    {
        FileReader fread;
        Object newsObj = new Object();

        // TODO: This can be simplified I'm sure, two Jsonparsers?
        JSONParser jParse = new JSONParser();
        JsonParser jParser;

        JsonToken toke;

        String bigNewsString = "";

        String newsContent = "/opt/news/tech.news";
        ArrayList<String> newsitem = new ArrayList<>();

        try {
            fread = new FileReader(newsContent);
            newsObj = jParse.parse(fread);
        } catch(FileNotFoundException fnfe) {
            newsitem.add("No news files present:" + fnfe.toString());
        } catch(IOException ioe) {
            newsitem.add("No news files read:" + ioe.toString());
        } catch(org.json.simple.parser.ParseException pe) {
            newsitem.add("No news files json parsed:" + pe.toString());
        }

        // Parse through the String file of news
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String newsJsonString = gson.toJson(newsObj);

        JsonFactory jFactory = new JsonFactory();
        try {
            jParser = jFactory.createParser(newsJsonString);
        } catch(IOException ioe) {
            return "No news from input/output: "+ioe.getMessage();
        }

        while(!jParser.isClosed()) {

            try {
                toke = jParser.nextToken();

                if(JsonToken.FIELD_NAME.equals(toke)) {
                    String newsField = jParser.getCurrentName();
                    //bigNewsString += "<h6>" + newsField + "</h6>";
                    //bigNewsString += "<div style=\"border:1px inset #999999; padding:8px; \">";

                    if (newsField.equals("title")) {
                        toke = jParser.nextToken();
                        bigNewsString += "<h3>" + jParser.getValueAsString() + "</h3>";
                    }

                    if (newsField.equals("author")) {
                        toke = jParser.nextToken();
                        bigNewsString += "<div style=\"font-size:8pt;\"><em>"+jParser.getValueAsString()+"</em></div>";
                    }

                    if (newsField.equals("published")) {
                        toke = jParser.nextToken();
                        bigNewsString += "<div style=\"font-size:8pt;\"><em>"+jParser.getValueAsString()+"</em></div>";
                    }

                    if (newsField.equals("url")) {
                        toke = jParser.nextToken();
                        bigNewsString += "<div style=\"font-size:8pt;\"><em><a href=\""+jParser.getValueAsString()+"\" target=\"newssource\" >source</a></em></div>";
                    }

                    if (newsField.equals("text")) {
                        toke = jParser.nextToken();
                        bigNewsString += "<div>"+jParser.getValueAsString()+"</div>";
                    }

                    if (newsField.equals("main_image")) {
                        toke = jParser.nextToken();
                        bigNewsString += "<img width=\"350px\" height=\"225px\" align=\"left\" style=\"margin:5px;\" border=\"1\" src=\""+jParser.getValueAsString()+"\" >";
                    }

                    //bigNewsString += "</div>";
                }

            } catch(IOException ioe) {
                return "No news from token input/output: "+ioe.getMessage();
            }
        }

        return bigNewsString;
    }

    /**
     * Writes the news content to file
     * @param templateContent
     * @param templateName
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


    protected void createNewsTemplates(JsonElement newsCache)
    {

    }


}