base.plugin("mobielegidsen.Slider", ["base.core.Class", "constants.mobielegidsen.core", "mobielegidsen.Video", function (Class, MgConstants, Video)
{
    var Slider = this;
    var owlCarousel = null;

    //-----CONSTANTS-----
    
    //-----VARIABLES-----
    var allVideoSlides = $('.'+MgConstants.SLIDER_CLASS+' .'+MgConstants.VIDEO_SLIDE_CLASS);

    //-----PUBLIC FUNCTIONS-----
    this.setAutoSlide = function (boolVal)
    {
        if (owlCarousel) {
            if (boolVal) {
                owlCarousel.play();
            }
            else {
                owlCarousel.stop();
            }
        }
    };

    //-----PRIVATE METHODS-----
    var onSliderStartDragging = function ()
    {
        //old
        //resetAllVideoSlides();
        
        Video.resetAllVideos();
        //don't really know why this works without re-enabling them, but it does
        Video.enableAllControls(false);
    };
    var onSliderStopDragging = function ()
    {
        Video.enableAllControls(true);
    };

    // allVideoSlides.find('.btn.play').click(function ()
    // {
    //     var playButton = $(this);
    //     var videoPlayer = playButton.closest('.video').find('video');
    //     var sliderControls = playButton.closest('.slider').find('.owl-controls');
    //
    //     var controlsWrapper = playButton.closest('.controls');
    //     var pauseButton = controlsWrapper.find('.pause');
    //     var loader = controlsWrapper.find('.loader');
    //
    //     playButton.fadeOut(function ()
    //     {
    //         //avoids flickering of the loader icon
    //         setTimeout(function ()
    //         {
    //             if (videoPlayer.length > 0 && !videoPlayer.get(0).paused && !videoPlayer.get(0).canplay && videoPlayer.get(0).currentTime == 0) {
    //                 loader.show();
    //             }
    //         }, 250);
    //     });
    //     pauseButton.fadeIn();
    //
    //     sliderControls.fadeOut();
    //     videoPlayer.attr("controls", "true");
    //
    //     if (videoPlayer.length > 0) {
    //         videoPlayer.get(0).play();
    //     }
    // });
    // allVideoSlides.find('.btn.pause').click(function ()
    // {
    //     var pauseButton = $(this);
    //     var playButton = pauseButton.closest('.controls').find('.play');
    //     var loader = playButton.closest('.controls').find('.loader');
    //     playButton.fadeIn();
    //     pauseButton.fadeOut();
    //     loader.hide();
    //
    //     var videoPlayer = pauseButton.closest('.video').find('video');
    //     if (videoPlayer.length > 0) {
    //         videoPlayer.get(0).pause();
    //     }
    // });
    // var resetVideoSlide = function (slide)
    // {
    //     var playButton = slide.find('.btn.play').show();
    //     var pauseButton = slide.find('.btn.pause').hide();
    //     var loader = slide.find('.loader').hide();
    //     var progressBar = slide.find('.progress-bar .progress');
    //     progressBar.css("width", "0%");
    //
    //     var videoPlayer = slide.find('video');
    //     if (videoPlayer.length > 0) {
    //         var player = videoPlayer.get(0);
    //         player.pause();
    //         player.currentTime = 0;
    //         player.load();
    //
    //         player.addEventListener('timeupdate', function ()
    //         {
    //             var percentage = Math.floor((100 / player.duration) * player.currentTime);
    //             progressBar.css("width", percentage + "%");
    //             if (player.currentTime > 0) {
    //                 loader.hide();
    //             }
    //         });
    //     }
    // };
    // var resetAllVideoSlides = function ()
    // {
    //     allVideoSlides.each(function ()
    //     {
    //         resetVideoSlide($(this));
    //     });
    // };
    
    //-----BOOTSTRAP-----
    //do it once
    //resetAllVideoSlides();

    Slider.initSliders = function()
    {
        var slider = $(".slider");

        // init owl-carousel
        slider.owlCarousel({
            singleItem: true,
            autoPlay: 5000,
            //if this is activated, the caroussel will re-start after stopping on blur
            stopOnHover: false,
            startDragging: onSliderStartDragging,
            afterMove: onSliderStopDragging
        });

        //get carousel instance data and store it in variable owl
        owlCarousel = slider.data('owlCarousel');

        //we'll start out stopped (so we can activate it with onSliderStartDragging())
        if (owlCarousel) {
            owlCarousel.stop();
        }
    };
    //call it once
    Slider.initSliders();

}]);
