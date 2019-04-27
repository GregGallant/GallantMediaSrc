/*
 Plugin: Auto-motion Parallax for Gallantmedia (jquery 3.x compat)
 Version 1.0
 */
(function ($) {

    const $window = $(window);
    let windowHeight = $window.height();
    let freescroll = false;

    /* Handle window height if window resized */
    $window.resize(function() {
        windowHeight = $window.height();
    });

    $.fn.parallax = function(divArr) {

        let $this = $(this);
        let topDiv;
        let divmap = [];

        /* Call on each div attached to our parallax function */
        /*
         $this.each(function() {
         topDiv = $this.offset().top;  // Get top position of div
         });
         */

        /* Call on each div attached to our parallax function */
        $.each(divArr, function(index, value) {
            topPositionOfDiv = $(value).offset().top;
            heightOfDiv = $(value).height();
            bottomPositionOfDiv = topPositionOfDiv - heightOfDiv;
            mapAllDivs(topPositionOfDiv, bottomPositionOfDiv, heightOfDiv, index);
        });

        /**
         * Maps all divs within the divArr, defining each point for the scroll/parallax
         * @param toppo
         * @param bottom
         * @param heightOfDiv
         * @param index
         */
        function mapAllDivs(toppo, bottom, heightOfDiv, index)
        {
            let $divElement = $(this); // each div
            let BOUNDS_OFFSET = 25; // Number of pixels 'hitbox' from top of div where auto scroll kicks in

            let topHitBoxBounds = toppo - BOUNDS_OFFSET;
            let bottomHitBoxBounds = bottom + BOUNDS_OFFSET;

            // Build dimensional obj {'top', 'bottom', 'topBound', 'bottomBound'}
            // Maybe calculate any/all extra space between divs if any

            let divobj = {'top':toppo, 'bottom':bottom, 'topBound':topHitBoxBounds, 'bottomBound':bottomHitBoxBounds, 'height':heightOfDiv};
            divmap.push(divobj);
        }

        /* The update function, called by delegated handler binded to our scroll event */
        function update()
        {
            let lastindex;
            const speed = .1;
            let pixels_from_top = $window.scrollTop();

            $.each(divArr, function(index, value) {

                var divElement = $(divArr[index]); // each div

                var mcGuffin = divArr[index];

                /*
                 console.log('eletype: ' + typeof($divElement));
                 console.log('ele: ' + $divElement);
                 */
                var origDivTopVertPos = divElement.offset().top;
                var divHeight = divElement.height();

                /* If the top div position plus its height is less than the amount of pixels scrolled from top  (still showing)
                 *  or if the original top position of the div is greater than the number of scrolled pixels plus the window height (still showing)
                 */
                var divOffsetHeight = origDivTopVertPos + divHeight;
                var windowOffsetHeight = pixels_from_top + windowHeight;

                if (divOffsetHeight < pixels_from_top
                    || origDivTopVertPos > windowOffsetHeight)
                {
                    return;
                }


                //var lastDivID = divElement;
                var lastDivID = null;
                if(index > 0) {
                    lastDivID = divArr[index-1];
                }

                lastindex = index;

                var divID = divArr[index];
                //var divID = divElement.attr('id');
                var lastScrollTop = 0;

                var totalDivHeight;
                var portionOfSecondDivShowing;

                /* TODO: Remove this debugger eventually... */
                /*
                 console.log($divElement.attr('id'));
                 console.log("divHeight: "+divHeight);
                 console.log("divOffsetHeight: "+divOffsetHeight);
                 console.log("pixels_from_top: "+pixels_from_top);
                 console.log("origDivTopVertPos: "+origDivTopVertPos);
                 console.log("windowHeight: "+windowHeight);
                 console.log("windowOffsetHeight: "+windowOffsetHeight);
                 console.log("freescroll: "+freescroll);
                 console.log("DIV HEIGHT: "+$('#'+divID).height());
                 */

                /* Creating a one time only autoscroller.  It moves for the user, ONCE.  Anymore and it's terrible UI.  Other sites love to annoy their customer.  --ggallant */
                $(window).scroll(function(event){

                    /* Outdated stuff */
                    /*
                     console.log('squire1 - '+ lastDivID);
                     console.log('squire2 - '+ divID);
                     */
                    /* Modern stuff */
                    /*
                     console.log('hero3 - ' + divArr[index]);
                     console.log('hero4 - ' + divArr[index-1]);
                     */


                    /* Determine if last element different than current element  */
                    if (divID != lastDivID && lastDivID != null) {

                        var firstDivHeight = $(divArr[index]).height();
                        var secondDivHeight = $(divArr[index]).height();

                        var divNum = mcGuffin.substr(divArr[index].length - 1);
                        var lastDivNum = mcGuffin.substr(divArr[lastindex].length - 1);

                        if (index < lastindex) {
                            /*
                             console.log('hero3 - ' + divArr[index]);
                             console.log('hero4 - ' + divArr[index-1]);
                             */
                            var div1HeightShowing = firstDivHeight - pixels_from_top;
                            var div2Showing = windowHeight - div1HeightShowing;

                            /* If second div height showing is greater than first */
                            if (div2Showing > div1HeightShowing  &&
                                ( ( div1HeightShowing / div2Showing ) < .08))
                            {
                                freescroll = false;
                                return;
                            } else {
                                freescroll = true;
                            }

                        } else {
                            if (freescroll == false) {

                                var st = divElement.scrollTop();

                                console.log('divElement: ' + $(divArr[index]));

                                if (st > lastScrollTop)
                                {
                                    // down
                                    $("html,body").animate({ scrollTop: origDivTopVertPos }, "slow");
                                } else {
                                    // up
                                    freescroll = true;
                                }
                            }
                        }
                    }
                    lastScrollTop = st;
                    lastDivID = divID;
                    //console.log("---------------------------------------------------");
                });

                /* If the div is no longer showing, let's... */
                $this.css('backgroundPosition', '50%' +  " " + Math.round((topDiv - pixels_from_top) * speed) + "px" );
            });
        }

        /* Events binded to window */
        $window.on('scroll', null, null, update).resize(update); // scroll or resize event triggers our update method

        update(); // Call update on load (todo: this is calling twice onLoad.  Needs optimization).
    };
})(jQuery);