/*
 Plugin: Auto-motion Parallax for PrivCo Mobile (jquery 3.x compat)
 Version 1.0
 */

    const $window = $(window);
    let windowHeight = $window.height();


    // Email address
    $('#email').on('focusout', function(event)
    {
        let email = $('#email').val();

        let okemail = $.fn.validateEmail(email);

        if (!okemail) {
            $('#email-group').addClass('has-warning');
        } else {
            $('#email-group').removeClass('has-warning');
            $('#email-group').addClass('has-success');
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
                    $("#contactFormGroup").append(cda.field + ":" + cda.defaultMessage + "<br/>");
                } else {
                    // SUCCESS
                    $("#contactFormGroup").html("<h1>Okay...</h1>").show();
                    $("#contactFormGroup").append(cda).show();
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
