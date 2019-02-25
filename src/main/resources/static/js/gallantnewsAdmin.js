/*
  Plugin: Beautiful news
  Version 1.0
  Author: Greg Gallant
*/

let g_env = "api.gallantone.com";
//let g_env = "staging.gallantone.com:8080";

let newsJson;
let load_news_type;
let isFrontPageNewsFade = 0;
console.log("testing 1 2 3 ");
if (document.getElementById("ggscreen1")) {
    newsJson = "http://"+g_env+"/newslinks";
    isFrontPageNewsFade = 1;
} else if (document.getElementById("newsUS")) {
    newsJson = "http://"+g_env+"/newsusjson";
} else {
    newsJson = "http://"+g_env+"/newslinks";
}

// Admin override for now
newsJson = "http://"+g_env+"/newsusjson";

// Menu State
let news_us_is_on = 1;
let news_tech_is_on = 0;
let news_business_is_on = 0;
let news_ent_is_on = 0;


$("li#news_us").on("click", function() {
    // Check if category already loaded
    if (news_us_is_on != 0) { }
    // Clear json
    // Load new json
    $("li#news_us").addClass("hover");
    $("li#news_tech").removeClass("hover");
    $("li#news_business").removeClass("hover");
    $("li#news_entertainment").removeClass("hover");
    load_news_type = "http://"+g_env+"/newsusjson";
    loadNewNews(load_news_type);
});


$("li#news_tech").on("click", function() {
    // Check if category already loaded

    // Clear json
    // Load new json
    $("li#news_us").removeClass("hover");
    $("li#news_tech").addClass("hover");
    $("li#news_business").removeClass("hover");
    $("li#news_entertainment").removeClass("hover");
    load_news_type = "http://"+g_env+"/newsjson";
    loadNewNews(load_news_type);

});


$("li#news_business").on("click", function() {
    // Check if category already loaded

    // Clear json
    // Load new json
    $("li#news_business").addClass("hover");
    $("li#news_tech").removeClass("hover");
    $("li#news_us").removeClass("hover");
    $("li#news_entertainment").removeClass("hover");
    load_news_type = "http://"+g_env+"/newsbusinessjson";
    loadNewNews(load_news_type);

});


$("li#news_entertainment").on("click", function() {
    // Check if category already loaded

    // Load new json
    $("li#news_us").removeClass("hover");
    $("li#news_entertainment").addClass("hover");
    $("li#news_tech").removeClass("hover");
    $("li#news_business").removeClass("hover");
    load_news_type = "http://"+g_env+"/newsentertainmentjson";
    loadNewNews(load_news_type);
});



// TODO: Check if actually json


/* Get news type */
let newsService = $.get( newsJson );
$("li#news_us").addClass("hover");

newsService.done(function( data )
{
    let newsdata = data;

    if(isFrontPageNewsFade == 1) {
        $('#newsfade').append(newsdata);
        return;
    }


    let parsedNews = $.parseJSON(newsdata);

    createNewsHeadline(parsedNews);

});


/**
 * Loads a new set of json for the news
 * @param newsJson
 */
function loadNewNews( newsJson )
{

    let newsLoading = '<div id="news-loading"> <img class="img-fluid" align="center" src="/images/newsloading.gif"/> <br/>Loading... </div>';
    $("#newsUS").html(newsLoading);
    //$("#news-loading").show();

    /* Get news type */
    let newsService = $.get( newsJson );

    newsService.done(function( data )
    {
        let newsdata = data;

        let parsedNews = $.parseJSON(newsdata);

        createNewsHeadline(parsedNews);

    });
}


function createNewsHeadline(articleBlob)
{
    let artblob = "";
    articleBlob.forEach(function(article)
    {

        if (article.newstitle != null && article.newstitle != "" && article.newstext.length > 1) {

            let art_image = "<img align=\"left\" class=\"newsImage\" src=\""+article.newsimage+"\" onerror=\"imgError(this)\" />";

            artblob += "<div class='newsArticleRect'>";
            artblob += "<h3 class='news'>" + article.newstitle + "</h3>";
            artblob += "<div class='newsyText'>" + art_image + "<span>" + article.newstext +"</span></div>";
            artblob += "</div>";
        }
        //$("#newsUS").append(article.newstext);
    });

    $("#newsUS").append(artblob);
    $("#news-loading").hide();
}

function imgError(image) {
    image.onerror = "";
    image.src = "/images/noimage.gif";
    return true;
}

function createNewsSubHeadline() {

}

function createNewsBlock() {

}

