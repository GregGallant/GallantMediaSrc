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
            alert("Invalid email - turn on class etc.");
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

            let error = false;
            // if java validator returns error signal call error handler function
            // Other wise just show the return stuffa
            if (error) {
                $.fn.validationHandler(data);
            } else {
                // success
                $("#contactFormGroup").html(data);
            }
        });
        //alert(fromjava);

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
