//////////////////////////////////////
// Victory!  The GoodBurger Menu    //
// Programmed by: Greg Gallant      //
//////////////////////////////////////

var liCol = $('div.the-toggler');

var subCol = $('div.subby');
// Collapsing Mobile Menu

liCol.on('show.bs.collapse', function () {
    $('.collapse.in').each(function(){
        $(this).collapse('hide');
    });
});

// Mobile Menu Links from li element
subCol.on('click', function(e) {

    e.preventDefault();

    var idVal = $(this).attr('id');

    var iRoute = idVal.substring(2);
/*
    if (idVal == 'm_careers' || idVal == 'd_careers') {
        window.location.href="https://angel.co/privco-1/jobs";
    } else {
        window.location.href="/home/"+iRoute;
    }
*/
});


$('a.drop-btn.menu-mobile.logo-wrap').mouseenter(function() {
    $('.accordion-group').show();
    $('.mobile-cust').show();
    goodBurger();
});


$('.nav-header').mouseleave(function() {
    $('.accordion-group').hide();
    $('.mobile-cust').hide();
});


// Hamburger to X 
//////////////////////////////////////////////////////////////////////////////////
var spanham = $('button#hamburger'); // the button to button
var cryblue = $('#cry_blue');
var crywhite = $('#cry_white');

cryblue.on('click', function(e) {
   goodBurger();
});

crywhite.on('click', function(e) {
   goodBurger();
});


/******************************/
/* Toggles the hamburger icon */
/******************************/
function goodBurger() {

    if ($('.glyphicon.glyphicon-remove').is(':visible')) {
        spanham.on('click', function(e) {
            $('.accordion-group').hide();
            $('.mobile-cust').hide();
            $('button#hamburger').removeClass("glyphicon-remove");
            $('button#hamburger').addClass("glyphicon-menu-hamburger");
        });

        // check state
        if ( $('.glyphicon.glyphicon-menu-hamburger').is(':visible')
            && $('.mobile-cust').is(':visible')
        ) {
            $('button#hamburger').removeClass("glyphicon-menu-hamburger");
            $('button#hamburger').addClass("glyphicon-remove");
        }
    } else {
        spanham.on('click', function(e) {
            $('.accordion-group').show();
            $('.mobile-cust').show();
            $('button#hamburger').removeClass("glyphicon-menu-hamburger");
            $('button#hamburger').addClass("glyphicon-remove");
        });

        // check state
        if ( $('.glyphicon.glyphicon-menu-hamburger').is(':visible')
            && $('.mobile-cust').is(':visible')
        ) {
            $('button#hamburger').removeClass("glyphicon-menu-hamburger");
            $('button#hamburger').addClass("glyphicon-remove");
        }
    }
}

