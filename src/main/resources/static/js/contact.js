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

        var fromjava = $.post(
            "/contact", $('#lastname')
        );

        fromjava.done(function( data ) {
            $("#contactFormGroup").text(data);
        });
        //alert(fromjava);

    });


