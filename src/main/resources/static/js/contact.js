/*
 Plugin: Beautiful forms
 Version 1.0
 */
    const $window = $(window);
    let windowHeight = $window.height();

    // Email address Pre-Validation
    $('#email').on('focusout', function(event)
    {
        let email = $('#email').val();

        let okemail = $.fn.validateEmail(email);

        if (!okemail) {
            //$('div#email-group').addClass('has-warning'); TODO
        } else {
//            $('#email-group').removeClass("has-warning");
 //           $('#email-group').attr("class", "form-group has-success");
            $('div#email_issue').html("");
        }

    });

    // Description
    $('#description').on('focusout', function(event) {

        let description = $('#description').val();

        if (description.length > 4) {
            //$('div#description-group').attr("class", "form-group has-success");
            $('div#description_issue').html("");
            $('input#description').addClass('has-success');
            $.fn.closeValidation();
        }  else {
            //$('div#description-group').addClass('has-warning');
            $('input#description').addClass('has-warning');
        }

    });

    // firstname
    $('#firstname').on('focusout', function(event) {

        let firstname = $('#firstname').val();

        if (firstname.length > 4) {
            //$('div#firstname-group').attr("class", "form-group inlabel has-success");
            $('input#firstname').addClass('has-success');
            $('div#firstname_issue').html("");
            $.fn.closeValidation();
        }  else {
            $('input#firstname').addClass('has-warning');
        }

    });

    // lastname
    $('#lastname').on('focusout', function(event) {

        let lastname = $('#lastname').val();

        if (lastname.length > 4) {
            $('div#lastname-group').attr("class", "form-group inlabel has-success");
            $('div#lastname_issue').html("");
            $('input#lastname').addClass('has-success');
            $.fn.closeValidation();
        }  else {
            //$('div#lastname-group').addClass('has-warning');
            $('input#lastname').addClass('has-warning');
        }

    });


    // Company
    $('#company').on('focusout', function(event) {

        let company = $('#company').val();

        if (company.length > 4) {
            //$('div#company-group').attr("class", "form-group inlabel has-success");
            $('div#company_issue').html("");
            $('input#company').addClass('has-success');
            $.fn.closeValidation();
        }  else {
            //$('div#company-group').addClass('has-warning');
            $('input#company').addClass('has-warning');
        }

    });

    // Website
    $('#website').on('focusout', function(event) {

        let website = $('#website').val();

        if (website.length > 4) {
            //$('div#website-group').attr("class", "form-group inlabel has-success");
            $('div#website_issue').html("");
            $('input#website').addClass('has-success');
            $.fn.closeValidation();
        }  else {
            //$('div#website-group').addClass('has-warning');
            $('input#website').addClass('has-warning');
        }

    });

    // Phone
    $('#phone').on('focusout', function(event) {
        let phone = $('#phone').val();
        $('input#phone').addClass('has-success');
    });


    $('.validation').on('click', function(event) {
        $.fn.closeValidation();
    });

        //$.fn.submitContact = function() {
    $('#submitButton').on('click', function(event) {
        event.preventDefault();
        //console.log("Ajax post call goes here...");

        let firstname = $('#firstname').val();
        let lastname = $('#lastname').val();
        let email = $('#email').val();
        let description = $('#description').val();
        let phone = $('#phone').val();
        let company = $('#company').val();
        let website = $('#website').val();

        let contact_form_data = {
            firstname: firstname,
            lastname: lastname,
            email: email,
            description: description,
            company: company,
            phone: phone,
            website: website
        };

        let fromjava = $.post(
            "/contact", contact_form_data
        );

        fromjava.done(function( data ) {

            let cd = jQuery.parseJSON(data);

            $.fn.validationHandler(cd);
            let error_string = "<div class=\"cfhead\"><strong>Please fix the following:</strong></div><br/>";

            // success
            cd.forEach(function(cda) {

                error_string = error_string + "<div class=\"cfgroup\"> " +
                    "<div class=\"cffield\">On the " + cda.field + " field</div><div class=\"cfmesg\">" + cda.defaultMessage + "</div></div>";

                if (cda.field) {
                    // ERROR HANDLER - One window message style
                    $('input#'+cda.field).addClass('has-warning');

                    $('#email_issue').css('visibility', 'visible');
                    $('#email_issue').addClass('has-error');
                    $('#email_issue').addClass('fadeInOut');
                    $('#email_issue').html(error_string);
                    //$("#contactFormGroup").append(cda.field + ":" + cda.defaultMessage + "<br/>");
                } else {
                    // SUCCESS
                    $("#contactFormGroup").html("<h1>Thank you for your interest and your time!  </h1>").show();
                    $("#contactFormGroup").append("<h3>We will contact you in 1-5 days. GallantOne.com will do our best to help bring your ideas to life.</h3>");
                }
            });

        });
    });


(function ($) {

    /**
     * Handles return value of Java validator
     *
     * @param c
     */
    $.fn.validationHandler = function(errorResponse)
    {
        // Do stuff
        console.log(errorResponse);
    };


    $.fn.validateEmail = function(email)
    {
        let re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

        let valid = re.test(email);

        if (valid) {
            return true;
        }

        return false;
    }

    $.fn.closeValidation = function()
    {
        $('#email_issue').removeClass('has-error fadeInOut');
        //$('#email_issue').removeClass('fadeInOut');
        $('#email_issue').css('visibility', 'hidden');
    }

})(jQuery);
