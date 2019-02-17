<!doctype html>
<html>
<head>
    <title>Gallantmedia Source</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link type="text/css" rel="stylesheet" href="./css/gallantone.css" />
    <link type="text/css" rel="stylesheet" href="./css/bootstrap.css" />

    <script type="text/javascript" src="./js/jquery-3.1.1.min.js"></script>

</head>
<body>
<div><img src="./images/gg_sunset.png" id="center_image" /></div>
<header id="fixed_header">
            <span id="fixed_logo">
                <img width="175px" height="24px" src="./images/gallantone_logo_175w.png" />
            </span>
    <span id="fixed_menu">
                <a href="contact/#homescreen">Home</a>
                    &nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;
                <a href="#requestaproject">Request a Project</a>
                    &nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;
                <a target="linkedin" href="http://www.linkedin.com/in/greggallant">Linked-In</a>
            </span>
</header>
<a name="homescreen" />

<div id="ggscreen1" class="container-fluid">
    <div class="row">
        <div class="col-lg-12 col-md-12">
            <div class="br"></div>
            <!-- Hed -->
            <div id="textHeader">
                <h1>AWESOME ENGINEERING &amp; DESIGN</h1>
                <h3>KEEPING THE DIGITAL WORLD INNOVATIVE AND VISUALLY REMARKABLE</h3>
            </div>
            <div class="br"></div>
            <!-- Body -->
            <div id="textContent">
                <strong>From programming to devops</strong> -- Greg Gallant specializes in creating full-stack development environments to mirror production using vagrant and ansible provisioning, AWS and Digital Cloud stack building, Apache2, Nginx, multiple language and framework support including Laravel (and the Lumen microframework powering this very site), Zend Framework 2 (the previous framework powering this site), Codeigniter, Yii2, Prado as well as Java Spring 3 MVC and Python Django.   Experience with frontend and responsive technologies include vanilla javascript, jQuery, CSS and Sass, Bootstrap and Zurb Foundation.  The fundamentals of building a successful and profitable business is not just about building a responsive website able to handle high amounts of traffic, but bringing small technology companies to a medium and large plateau by creating consistent and productive development environments for technology teams.
            </div>
            <div class="br"></div>
            <div class="br"></div>
        </div>
    </div>
</div>

<div id="ggscreen2" class="container-fluid">
    <div class="row">
        <div class="col-lg-12 col-md-12">
    <div class="br_cc"></div>
    <div id="cc_image_holder">
        &nbsp;
        <div class="cc_text">
            <div class="skills_header">
                Full-stack technologies and frameworks
            </div>
            <div class="skills_left">
                <div class="skills">Laravel Framework & Lumen Microframework</div>
                <div class="skills">Wordpress Module programming and Integration</div>
                <div class="skills">Zend Framework</div>
                <div class="skills">Bootstrap & Foundation Responsive development</div>
            </div>
            <div class="skills_right">
                <div class="skills">Android Mobile Development</div>
                <div class="skills">API Development</div>
                <div class="skills">Akamai Luna Control Center Caching</div>
                <div class="skills">MySQL / PostgreSQL database normalization</div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-6 col-md-6 col-sm-12 text-center">
            <img src="./images/jira_logo.png" />
        </div>
            <div class="col-lg-6 col-md-6 col-sm-12 text-center">
                <img src="./images/github_logo.png" />
            </div>
        </div>
    </div>
    <div id="jirapara">
        <p>
            Agile Planning &amp; Jira Sprints as well as Github controlled code reviews ensures secure &amp; controlled development.
        </p>
    </div>
    &nbsp;
    </div></div>
</div>
<a name="requestaproject"/>
<div id="ggscreen3" class="container-fluid">
    <div class="row">
        <div class="col-lg-12 col-md-12">
    <div id="contactFormGroup">
        <div class="br2nd"></div>
        <div class="br2nd"></div>
            <!-- Contact Form -->
            <h1>Request a Project</h1>
            <h3>
                <div>
                    Have a project or an idea?
                </div>
                <div>
                    Send a brief description of the services you or your business needs.
                </div>
            </h3>
        <form id="contactForm">
            <div class="formgroup">
                <div class="label">Email:</div>
                <div class="cform"><input class="formy" size="50px" id="email" name="email" type="text" value="{!! $old_email !!}" /></div>
                <div class="validation" id="email_issue"></div>
            </div>

            <div class="formgroup">
                <div class="inlabel">
                    <div class="form_error">{!! $errors->first('firstname');  !!}</div>
                    <div class="label">First Name:</div>
                    <div class="cform">
                        <input class="formy" size="25px" id="firstname" name="firstname" type="text" value="{!! $old_firstname !!}" />
                    </div>
                    <div class="validation" id="firstname_issue"></div>
                </div>
                <div class="inlabel">
                    <div class="form_error">{!! $errors->first('lastname');  !!}</div>
                    <div class="label">Last Name:</div>
                    <div class="cform"><input class="formy" size="30px" id="lastname" name="lastname" type="text" value="{!! $old_lastname !!}" /></div>
                    <div class="validation" id="lastname_issue"></div>
                </div>
            </div>

            <div class="formgroup">
                <div class="label">Website URL or Company Name:</div>
                <div class="cform"><input class="formy" size="50px" id="company" name="company" type="text" value="{!! $old_company !!}" /></div>
                <div class="validation" id="company_issue"></div>
            </div>

            <div class="formgroup">
                <div class="label">Describe Your Project:</div>
                <div class="cform">
                    <textarea class="textformy" id="details" name="details" cols="60" rows="6">{!! $old_details !!}</textarea>
                </div>
                <div class="validation" id="details_issue"></div>
            </div>
            <div class="formgroup">
                <div class="form_error">
                    @if (!is_null($errors->first('captcha')))
                    Invalid captcha
                    @endif
                </div>
                <input type="hidden" name="_token" value="{!! csrf_token() !!}">
                {!! app('captcha')->display(); !!}
            </div>
            <div class="formsubmit">
                <button id="submitButton" class="formbutton">Ajax Submit</button>
            </div>
        </form>
    </div>
        </div>
    </div>
</div>
<!-- Main -->
<script type="text/javascript" src="./js/contact.js"></script>
</body>
</html>
