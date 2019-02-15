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
import java.io.FileNotFoundException;
import java.io.File;
import java.text.SimpleDateFormat;
import java.text.ParseException;

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
            newsResults += "...Parse error detected...";
            return false; // assume old news
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


    public String getLatestNews() {

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