/*
 Plugin: Auto-motion Parallax for PrivCo Mobile (jquery 3.x compat)
 Version 1.0
 */

    var $window = $(window);
    var windowHeight = $window.height();

    //$.fn.submitContact = function() {
    $('#submitButton').on('click', function(event) {
        event.preventDefault();
        console.log("Ajax post call goes here...");

        var firstname = $('#firstname').val();
        var lastname = $('#lastname').val();
        var email = $('#email').val();
        var details = $('#details').val();
        var company = $('#company').val();

        var fromjava = $.post(
            "/contact", { firstname: firstname,
                            lastname: lastname,
                            email: email,
                            details: details,
                            company: company
                        }
        );

        fromjava.done(function( data ) {
            $("#contactFormGroup").html(data);
        });
        //alert(fromjava);

    });


