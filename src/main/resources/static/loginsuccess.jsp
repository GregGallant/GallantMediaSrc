<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Welcome Back...</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="/js/popper.min.js"></script>
    <script type="text/javascript" src="/bootstrap/js/bootstrap.min.js"></script>
    <link type="text/css" rel="stylesheet" href="/bootstrap/css/bootstrap.css" />
    <link type="text/css" rel="stylesheet" href="/css/gm.css" />
    <link rel="icon" HREF="icons/favicon.ico" size="16x16">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
</head>


<body>
<header id="fixed_header">
    <div class="row no-gutter superhead">
        <div class="col-lg-2 col-md-4 col-sm-4 col-xs-4 logohead"><img class="img-fluid"  src="/images/gone_logo.png" border="0"/></div>
        <div class="col-lg-9 col-md-7 col-sm-7 col-xs-7 menuhead">
            <ul class="nav">
                <li><a href="/">Home</a></li>
                <li><a target="github" href="http://www.github.com/GregGallant">GitHub</a></li>
                <li><a target="linkedin" href="http://www.linkedin.com/in/greggallant">Linked-In</a></li>
                <li><a target="software" href="http://qa.gallantone.com">Software</a></li>
            </ul>
        </div>
        <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 burgerhead dropdown">
            <button class="btn dropdown-toggle" type="button" id="burgertime" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img class="img-fluid theburger" src="/images/goodburger50.png" border="0"/></button>
            <div class="dropdown-menu">
                <ul id="burgermenu" aria-labelledby="burgertime">
                    <li class="dropdown-item" onclick="location.href='/'"><a href="#">Home</a></li>
                    <li class="dropdown-item" onclick="window.open('https://www.github.com/GregGallant','github')"><a target="github" href="http://www.github.com/GregGallant">GitHub</a></li>
                    <li class="dropdown-item" onclick="window.open('https://www.linkedin.com/in/greggallant', 'linkedin')"><a target="linkedin" href="http://www.linkedin.com/in/greggallant">Linked-In</a></li>
                    <li class="dropdown-item" onclick="window.open('http://qa.gallantone.com', 'software')"><a href="#">Software</a></li>
                </ul>
            </div>
        </div>
    </div>
</header>
<div class="container-fluid">
    <div class="row no-gutter">
        <div class="col-lg-12 col-md-12 col-sm-12 hed">
            <br/>
            <h1>Login Successful</h1>
            <h3>Welcome back, sir.</h3>
        </div>
    </div>
    <div class="row no-gutter centered">
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 allnight">
            <div class="textHeader">
                <h1>Reliable Engineering &amp; Design</h1>
                <h4>For those who appreciate such things...</h4>
            </div>
        </div>
    </div>
    <div class="row no-gutter centered verbox">

        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 gbox">
            <i class="fas fa-cloud-upload-alt ficons"></i>
            <h4>Cloud Server Development</h4>
            <p> From EC2 to DigitalOcean, we enjoy building better servers with increased uptime so your devs can get some sleep. </p>
        </div>
        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 gbox">
            <i class="fas fa-mobile-alt ficons"></i>
            <h4>Adaptive Programming</h4>
            <p>Hardware limitations should never get in the way of design consistency.</p>
        </div>
        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 gbox">
            <i class="fas fa-database ficons"></i>
            <h4>Organize Your Code</h4>
            <p>Refactor your code and organize your data with minimal headaches and zero breakage.</p>
        </div>
    </div>
    <div id="contactFormGroup" class="row no-gutter contactimg">
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"> <h2 id="contactHeader">Get a quote for your project</h2></div>
        <div  class="col-lg-5 col-md-5 col-sm-12 col-xs-12">

            <form id="contactForm">

            <!-- Email -->
            <div id="email-group">
                <label class="control-label" for="email" id="email_label"></label>
                <input type="email" value="" name="email" id="email" class="form-control formy" placeholder="Email" >
                <div class="validation" id="email_issue"></div>
            </div>

            <!-- First Name -->
            <div id="firstname-group">
                <label class="control-label" for="firstname"></label>
                <input type="fname" class="form-control formy" id="firstname" name="firstname" type="text" value="" placeholder="First Name" />
                <div class="validation" id="firstname_issue"></div>
            </div>

            <!-- Last Name -->
            <div id="lastname-group">
                <label class="control-label" for="lastname"></label>
                <input type="lname" class="form-control formy" id="lastname" name="lastname" type="text" value="" placeholder="Last Name" />
                <div class="validation" id="lastname_issue"></div>
            </div>

            <!-- Phone -->
            <div id="phone-group">
                <label for="phone" class="control-label"></label>
                <input type="phone" value="" name="phone" id="phone" class="form-control formy" type="text" placeholder="Phone Number (optional)" >
                <div class="validation" id="phone_issue"></div>
            </div>

                <!--
                <div class="form_error">
                    @if (!is_null($errors->first('captcha')))
                    Invalid captcha
                    @endif
                </div>
                <input type="hidden" name="_token" value="{!! csrf_token() !!}">
                {!! app('captcha')->display(); !!}
                -->

        </div>
        <div class="col-lg-7 col-md-7 col-sm-12 col-xs-12">
            <!-- Website Group -->
            <div id="website-group">
                <label for="website" class="control-label"></label>
                <input class="form-control formwide" id="website" name="website" type="text" value="" placeholder="Website URL or Domain Lookup" />
            </div>

            <!-- Company Group -->
            <div id="company-group">
                <label for="company" class="control-label"></label>
                <input class="form-control formwide" id="company" name="company" type="text" value="" placeholder="Company Name" />
            </div>

            <!-- Description -->
                <label class="control-label" for="description"></label>
                <textarea class="form-control textformy" cols="140" rows="8" id="description" name="description"  placeholder="Describe your Project..."></textarea>
        </div>
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <div class="contactSubmitRow">
                <Button id="submitButton" class="contactSubmit btn btn-large" type="submit" value="Submit Request"> Submit Project Request </Button>
            </div>
            </form>
        </div>
    </div>
    <div class="row no-gutter centered blahdiv">



    </div>
</div>
<footer>
    <div class="row no-gutter">
        <div class="col-lg-12 col-md-12 col-sm-12 footer">&nbsp;
            <div class="foot_place_1">
                &nbsp;<img src="/images/gmedia_logo_w.png" border="0" />
                <div>
                    <ul class="mini_footy">
                        <li><a target="github" href="http://www.github.com/GregGallant">GitHub</a></li>
                        <li><a target="linkedin" href="http://www.linkedin.com/in/greggallant">Linked-In</a></li>
                        <li><a target="software" href="http://qa.gallantone.com">Software</a></li>
                    </ul>
                </div>
            </div>
            <div class="foot_addr">
                <div class="fhead">Programming, Design & Photography</div>
                <div class="fname">Greg Gallant </div>
                <div class="femail">ggallant2017@gmail.com </div>
                <div class="femail">Powered by: <a style="font-family:Arial,Helvetica,sans-serif; color:#ffff4f;" href="https://spring.io">Spring Boot 2.1.2</a> </div>
            </div>
        </div>
    </div>
</footer>
<!-- Main -->
<script type="text/javascript" src="/js/contact.js"></script>
</body>
</html>
