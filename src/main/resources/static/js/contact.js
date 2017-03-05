/*
 Plugin: Auto-motion Parallax for PrivCo Mobile (jquery 3.x compat)
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
            $('div#email-group').addClass('has-warning');
        } else {
            $('#email-group').removeClass("has-warning");
            $('#email-group').attr("class", "col-sm-12 form-group has-success");
            $('div#email_issue').html("");
        }

    });

    // Description
    $('#description').on('focusout', function(event) {

        let description = $('#description').val();

        if (description.length > 4) {
            $('div#description-group').attr("class", "col-sm-12 form-group formgroup has-success");
            $('div#description_issue').html("");
        }  else {
            $('div#description-group').addClass('has-warning');
        }

    });

    // firstname
    $('#firstname').on('focusout', function(event) {

        let firstname = $('#firstname').val();

        if (firstname.length > 4) {
            $('div#firstname-group').attr("class", "col-sm-6 form-group inlabel has-success");
            $('div#firstname_issue').html("");
        }  else {
            $('div#firstname-group').addClass('has-warning');
        }

    });

    // lastname
    $('#lastname').on('focusout', function(event) {

        let lastname = $('#lastname').val();

        if (lastname.length > 4) {
            $('div#lastname-group').attr("class", "col-sm-6 form-group inlabel has-success");
            $('div#lastname_issue').html("");
        }  else {
            $('div#lastname-group').addClass('has-warning');
        }

    });

        //$.fn.submitContact = function() {
    $('#submitButton').on('click', function(event) {
        event.preventDefault();
        console.log("Ajax post call goes here...");

        let firstname = $('#firstname').val();
        let lastname = $('#lastname').val();
        let email = $('#email').val();
        let description = $('#description').val();
        let company = $('#company').val();

        let contact_form_data = {
            firstname: firstname,
            lastname: lastname,
            email: email,
            description: description,
            company: company
        };

        let fromjava = $.post(
            "/contact", contact_form_data
        );

        fromjava.done(function( data ) {

            let cd = jQuery.parseJSON(data);

            $.fn.validationHandler(cd);

            // success
            cd.forEach(function(cda) {
                if (cda.field) {
                    // ERROR HANDLER
                    $('#'+cda.field+"-group").addClass('has-error');
                    $('#' + cda.field + '_issue').html(cda.defaultMessage);
                    //$("#contactFormGroup").append(cda.field + ":" + cda.defaultMessage + "<br/>");
                } else {
                    // SUCCESS
                    $("#contactFormGroup").html("<h1>Thank you for your interest and your time!  </h1>").show();
                    $("#contactFormGroup").append("We will contact you in 1-5 days. GallantOne.com will do our best to help bring your ideas to life.");
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

})(jQuery);
