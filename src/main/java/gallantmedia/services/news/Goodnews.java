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
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
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
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import gallantmedia.services.news.Article;


public class Goodnews
{
    private JsonElement generalNews;

    protected Map<List<String>, String> filters;

    private String newsPath = "/opt/news/";

    private List<String> relFilter = Arrays.asList(
            "www.businessinsider.com",
            "markets.businessinsider.com",
            "www.independent.co.uk",
            "www.usatoday.com",
            "www.bbc.co.uk",
            "www.bbc.com",
            "timesofindia.indiatimes.com",
            "edition.cnn.com",
            "www.cnn.com",
            "www.foxnews.com",
            "www.cnet.com",
            "www.theguardian.com",
            "www.chicagotribune.com",
            "news.sky.com",
            "www.rt.com",
            "www.telegraph.co.uk",
            "www.bloomberg.com"
    );

    private List<String> topFilter = Arrays.asList(
            "www.bloomberg.com",
            "www.theguardian.com",
            "www.foxnews.com",
            "www.bbc.com",
            "edition.cnn.com",
            "www.cnn.com",
            "www.theobserver.com",
            "www.newsobserver.com"
    );

    private List<String> usFilter = Arrays.asList(
           "Alexandria",
           "Trump",
           "Obama",
           "Pelosi",
           "Bernie",
           "Politics",
           "Business",
           "US",
           "temperatures",
           "Venezuela",
           "Hurricane",
           "AOC",
           "Brexit",
           "Kamala",
           "Roger Stone",
           "book",
           "New York",
           "L.A.",
           "Soho",
           "Los Angeles",
           "Chicago",
           "deal",
           "United States",
           "Cardi"
    );

    private List<String> bignewsFilter = Arrays.asList(
            "Alexandria",
            "Ocasio-Cortez",
            "Trump",
            "Bernie Sanders",
            "Roger Stone"
    );

    private List<String> bigtimeFilter = Arrays.asList(
            "Alexandria",
            "Ocasio-Cortez",
            "Ariana",
            "Cardi",
            "Lennon",
            "Jagger",
            "Regina King",
            "Taylor Swift"
    );

    private List<String> techFilter = Arrays.asList(
            "Amazon",
            "Bezos",
            "Elon Musk",
            "Google",
            "Apple",
            "Linux",
            "NASA",
            "Asteroid",
            "Galaxy",
            "Samsung",
            "Sony",
            "Spotify",
            "hacker",
            "Microsoft",
            "IBM",
            "artificial",
            "Huawei",
            "Facebook",
            "Tim Cook",
            "AI",
            "Laptop",
            "Mobile phone",
            "machine learning",
            "processing",
            "Cryptocurrency",
            "tech",
            "computer",
            "web",
            "encrypt",
            "technology",
            "cyber",
            "Alibaba",
            "yahoo",
            "Bill Gates"
    );

    private List<String> bizFilter = Arrays.asList(
            "stocks",
            "ipo",
            "nasdaq",
            "dow jones",
            "industry",
            "finance",
            "business",
            "companies",
            "merger",
            "acquisition",
            "kpi",
            "employee"
    );

    private List<String> fashionFilter = Arrays.asList(
            "Karl Lagerfeld",
            "Kate Spade",
            "Kate Moss",
            "Kate Valentine",
            "Prado",
            "Styles",
            "Clothing",
            "Calvin Klein",
            "Kenneth Cole",
            "Nike",
            "Versace",
            "Gianni",
            "Style",
            "Fashion"
    );

    private List<String> sportsFilter = Arrays.asList(
            "Football",
            "Baseball",
            "Basketball",
            "Colin Kaepernick",
            "Arod",
            "derek jeter",
            "NFL",
            "NBA",
            "NHL",
            "MLB",
            "Yankees",
            "Mets",
            "Packers",
            "Green Bay"
    );


    private List<String> entFilter = Arrays.asList(
            "Mariah Carey",
            "Ariana Grande",
            "Taylor Swift",
            "Cardi",
            "guitarist",
            "concert",
            "record label",
            "Tonight Show",
            "kpop",
            "pop music",
            "pianist",
            "Lady Gaga",
            "Yuja Wang",
            "lead singer",
            "Rita Ora",
            "Njomza",
            "Lennon",
            "Kurt Cobain",
            "Frances Bean Cobain",
            "Van Halen",
            "Star Wars",
            "Star Trek",
            "entertainment",
            "music",
            "album",
            "artist",
            "Soul",
            "headphones",
            "song"
    );

    private List<String> artifactsFilter = Arrays.asList(
            "Force Dump",
            "Quiz",
            "(WATCH)",
            "2 of 41 3",
            "Opinion",
            "50 Cent",
            "Hurricane",
            "POLL:",
            "Advertisement >",
            "Car Imports",
            "Rupee",
            "Car Sales"
    );

    // Until sports, sports in meh...
    private List<String> mehFilter = Arrays.asList(
            "Basketball",
            "Football",
            "Baseball",
            "Netflix",
            "Shooting",
            "Pulwama",
            "Shooter",
            "50 cent",
            "migrants",
            "Pixar",
            "GT-R",
            "punisher",
            "conspiracy",
            "Opinion",
            "jessica jones",
            "thor",
            "Brexit",
            "Gun",
            "OpEd"
    );


    /**
     * Builds the news filters, add more as fit
     */
    private void buildNewsFilters()
    {
        filters = new HashMap<>();
        filters.put(usFilter, "US");
        filters.put(techFilter, "tech");
        //filters.put(bizFilter, "business");
        filters.put(entFilter, "entertainment");
        filters.put(entFilter, "fashion");
        //filters.put(entFilter, "sports");

    }

    /**
     * If news is old, return false
     * @param newsFilter String the news type (tech, general etc.)
     * @return boolean if false, renders new news
     */
    private boolean checkLatestNews(String newsFilter)
    {
        Logger logger = LoggerFactory.getLogger(Goodnews.class);

        int oldNewsTime = 10000000;

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
        logger.info("==> Render dateDiff: " + datediff);
        logger.info("==> Render oldNewsTime: " + oldNewsTime);
        logger.info("==> Render fileexists: " + file.exists());

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
            Map.Entry<List<String>, String> filter = (Map.Entry)it.next();

            // If news is old this returns false and gets new news
            if (!checkLatestNews(filter.getValue())) {
                String filterString = "";

                List<String> superFilter = filter.getKey();
                for (String ifilter : superFilter) {
                    filterString += "title: \""+ ifilter +"\" OR section_title: \""+ ifilter +"\" OR site_section: \""+ifilter+"\" ";
                }

                //String finalFilterString = "performance_score:>0 ( "+ filterString +")";
                String finalFilterString = "performance_score:>0 language:english ( "+ filterString +")";
                queries.put("q", finalFilterString);
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
        Gson gson = new GsonBuilder().create();
        String newsJsonString = gson.toJson(newsObj);

        logger.info("news lookup check: " +newsContent);
        logger.info("news json check: " +newsJsonString);


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

                    // Relevancy items
                    if (newsField.equals("section_title")) {
                        toke = jParser.nextToken();
                        String newsSectionTitle = jParser.getValueAsString();
                        newsArticle.put("section_title", newsSectionTitle);
                    }

                    if (newsField.equals("spam_score")) {
                        toke = jParser.nextToken();
                        String newsSpamScore = jParser.getValueAsString();
                        newsArticle.put("spam_score", newsSpamScore);
                    }

                    if (newsField.equals("site_full")) {
                        toke = jParser.nextToken();
                        String newsSiteFull = jParser.getValueAsString();
                        newsArticle.put("site_full", newsSiteFull);
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
        logger.info("getLatestNews::View Type: " + viewType);

        if (viewType == null) {
            viewType = "links";
        }

        // News check and possible render
        renderLatestNews();

        String bigNewsString = "";
        Map<String, Map<String,String>> newsOrg;

        newsOrg = buildNewsMap(newsType);

        bigNewsString = newsJsonView(newsOrg, newsType, viewType);

        return bigNewsString;
    }



    public List<Article> getLatestNewsAdmin(String newsType, String viewType)
    {
        Logger logger = LoggerFactory.getLogger(Goodnews.class);
        logger.info("getLatestNewsAdmin::View Type: " + viewType);

        if (viewType == null) {
            viewType = "links";
        }

        // News check and possible render
        renderLatestNews();

        String bigNewsString = "";
        Map<String, Map<String,String>> newsOrg;

        newsOrg = buildNewsMap(newsType);

        List<Article> bigNewsList = newsAdminView(newsOrg, newsType);

        return bigNewsList;
    }


    /**
     * Builds a full news web view
     *
     * @param artlist
     * @param it
     * @return
     */
    private String buildLinksView(List<Article> artlist, Iterator it)
    {
        String bigNews = "";

        Map.Entry<String, Map<String,String>> newsRow;
        Map<String, String> newsArt;

        while (it.hasNext()) {

            newsRow = (Map.Entry) it.next();
            String title = newsRow.getKey();
            newsArt = newsRow.getValue();

            if (newsArt.get("text").length() > 850) {
                bigNews += "<a target=\"newsSource\" href=\""+newsArt.get("url")+"\">"+newsArt.get("title")+"</a><br/>";
            }

            it.remove();
        }

        return bigNews;
    }


    /**
     * Builds a full news web view
     * @param artlist
     * @param it
     * @return
     */
    private String buildWebView(List<Article> artlist, Iterator it)
    {
        String bigNews = "";
        Map<String, String> newsArt;
        Map.Entry<String, Map<String,String>> newsRow;
        Map.Entry<String,String> artRow;

        while (it.hasNext()) {

            newsRow = (Map.Entry) it.next();
            String title = newsRow.getKey();
            newsArt = newsRow.getValue();

            if (newsArt.get("text").length() > 850) {

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
     * Builds a full news web view
     * @param newsOrg A map containing a String and another map which is the article data
     * @return String html view
     */
    private String newsJsonView(Map<String, Map<String,String>> newsOrg, String newsType, String viewType)
    {
        String jsonBigNews = "";
        Logger logger = LoggerFactory.getLogger(Goodnews.class);
        logger.info("8====D~  viewType check: " + viewType);
        List<Article> artlist = new ArrayList<>();

        Iterator it = newsOrg.entrySet().iterator();

        if (viewType.equals("links")) {
            // wants links
            jsonBigNews = buildLinksView(artlist, it);
        } else if(viewType.equals("json") || viewType.equals("admin")) {
            jsonBigNews = buildJsonView(artlist, newsType, it, false);
        } else if(viewType.equals("full")){
            jsonBigNews = buildWebView(artlist, it);
        } else if(viewType.equals("order")){
            jsonBigNews = buildJsonView(artlist, newsType, it, true);
        } else {
            jsonBigNews = buildLinksView(artlist, it);
        }

        return jsonBigNews;
    }

    /**
     * Builds a full news web view
     * @param newsOrg A map containing a String and another map which is the article data
     * @return String html view
     */
    private List<Article> newsAdminView(Map<String, Map<String,String>> newsOrg, String newsType)
    {
        List<Article> artlist = new ArrayList<>();

        Iterator it = newsOrg.entrySet().iterator();

        return buildAdminView(artlist, newsType, it, false);
    }

    /**
     * Main method to output news in json format
     *
     * @param artlist  The map of articles
     * @param newsType  String essentially the news category
     * @param it    Iterator the iterator
     * @param titlesOnly  boolean titles for less debugging etc.
     * @return
     */
    private String buildJsonView(List<Article> artlist, String newsType, Iterator it, boolean titlesOnly)
    {
        String jsonBigNews = "";

        Article art = new Article();
        Map<String, String> newsArt;
        Map.Entry<String, Map<String,String>> newsRow;
        Map.Entry<String,String> artRow;

        while (it.hasNext()) {

            newsRow = (Map.Entry) it.next();
            String title = newsRow.getKey();
            newsArt = newsRow.getValue();

            if (titlesOnly == false) {

                // Filters out weak articles
                if (newsArt.get("text").length() > 850) {
                    art.setNewstitle(newsArt.get("title"));
                    art.setNewsauthor(newsArt.get("author"));
                    art.setNewspublished(newsArt.get("published"));
                    art.setNewsurl(newsArt.get("url"));
                    art.setNewsimage(newsArt.get("hedImage"));
                    art.setNewstext(newsArt.get("text"));

                    int artScore = calcNewsOrderAlg(newsArt, newsType);
                    art.setNewsscore(artScore);
                }

            } else {

                // Filters out weak articles
                if (newsArt.get("text").length() > 850) {
                    art.setNewstitle(newsArt.get("title"));
                    art.setNewspublished(newsArt.get("published"));
                    art.setNewssitefull(newsArt.get("site_full"));
                    art.setNewssectiontitle(newsArt.get("section_title"));
                    art.setNewsspamscore(newsArt.get("spam_score"));

                    int artScore = calcNewsOrderAlg(newsArt, newsType);
                    art.setNewsscore(artScore);
                }
            }

            artlist.add(art);
            art = new Article();

            it.remove();
        }

        Collections.sort(artlist);
        Collections.reverse(artlist);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        jsonBigNews = gson.toJson(artlist);

        return jsonBigNews;
    }

    private List<Article> buildAdminView(List<Article> artlist, String newsType, Iterator it, boolean titlesOnly)
    {

        String jsonBigNews = "";

        Article art = new Article();
        Map<String, String> newsArt;
        Map.Entry<String, Map<String,String>> newsRow;
        Map.Entry<String,String> artRow;

        while (it.hasNext()) {

            newsRow = (Map.Entry) it.next();
            String title = newsRow.getKey();
            newsArt = newsRow.getValue();

            if (titlesOnly == true) {

                // Filters out weak articles
                if (newsArt.get("text").length() > 850) {
                    art.setNewstitle(newsArt.get("title"));
                    art.setNewsauthor(newsArt.get("author"));
                    art.setNewspublished(newsArt.get("published"));
                    art.setNewsurl(newsArt.get("url"));
                    art.setNewsimage(newsArt.get("hedImage"));
                    art.setNewstext(newsArt.get("text"));

                    int artScore = calcNewsOrderAlg(newsArt, newsType);
                    art.setNewsscore(artScore);
                }

            } else {

                // Filters out weak articles
                if (newsArt.get("text").length() > 850) {
                    art.setNewstitle(newsArt.get("title"));
                    art.setNewspublished(newsArt.get("published"));
                    art.setNewssitefull(newsArt.get("site_full"));
                    art.setNewssectiontitle(newsArt.get("section_title"));
                    art.setNewsspamscore(newsArt.get("spam_score"));

                    int artScore = calcNewsOrderAlg(newsArt, newsType);
                    art.setNewsscore(artScore);
                }
            }

            artlist.add(art);
            art = new Article();

            it.remove();
        }

        Collections.sort(artlist);
        Collections.reverse(artlist);

        return artlist;
    }

    // Analytics Views soon... //////////


    private int calcNewsOrderAlg(Map<String,String> newsArt, String newsType)
    {
        int newsScore = 0;

        if (relFilter.contains( newsArt.get("site_full") )) {
            newsScore = newsScore + 1;
        }

        if (topFilter.contains( newsArt.get("site_full") )) {
            newsScore = newsScore + 3;
        }

        if (entFilter.contains( newsArt.get("title") )) {
            newsScore = newsScore + 5;
        }

        if (usFilter.contains( newsArt.get("title") )) {
            if (!newsType.equals("entertainment") && !newsType.equals("fashion") && !newsType.equals("tech")) {
                newsScore = newsScore + 11;
            } else {
                newsScore = newsScore - 9;
            }
        }

        if (techFilter.contains( newsArt.get("title") ) && !newsType.equals("entertainment")) {
            if (!newsType.equals("tech")) {
                newsScore = newsScore + 3;
            } else {
                newsScore = newsScore + 9;
            }
        }

        if (bigtimeFilter.contains( newsArt.get("title") ) && newsType.equals("entertainment")) {
            newsScore = newsScore + 15;
        }

        if (bignewsFilter.contains( newsArt.get("title") ) && !newsType.equals("entertainment")) {
            newsScore = newsScore + 7;
        }

        if (mehFilter.contains( newsArt.get("title") )) {
            newsScore = newsScore - 14;
        }

        if (artifactsFilter.contains( newsArt.get("title") )) {
            newsScore = newsScore - 15;
        }

        // -5 for spam!
        if (Float.parseFloat(newsArt.get("spam_score")) > 0f ) {
            newsScore = newsScore - 9;
        }

        if (Float.parseFloat(newsArt.get("spam_score")) > .5f ) {
            newsScore = newsScore - 13;
        }

        return newsScore;
    }



    /// IO Streams ////////

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