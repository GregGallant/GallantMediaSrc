<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org" xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head th:include="fragments/header" :: headerFragment></head>
<div class="container-fluid">
    <div class="row no-gutter">
        <div class="col-lg-12 col-md-12 col-sm-12 hed">
            <br/>
            <br/>
            <br/>
            <div style="background-color:#ffffff; color:#111111;">
                <div th:each="contact : ${contactList}" style="border:1px solid #333333; padding:10px;">
                    <div th:text="${contact.firstname}"></div>
                    <div th:text="${contact.lastname}"></div>
                    <div th:text="${contact.website}"></div>
                </div>
            </div>
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
</div>
<footer th:include="fragments/footer" :: footerFragment></footer>
</body>
</html>
