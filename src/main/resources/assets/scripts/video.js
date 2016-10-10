/**
 * Created by bram on 5/16/16.
 */
base.plugin("mobielegidsen.Video", ["base.core.Class", "constants.mobielegidsen.core", function (Class, MgConstants)
{
    var Video = this;

    //-----PUBLIC METHODS-----
    this.resetAllVideos = function ()
    {
        _forAll(function (videoEl)
        {
            //constructor expects an ID or (native) element
            var player = videojs(videoEl[0]);

            if (!player.paused()) {
                player.pause();
            }
        });
    };
    this.destroyAllVideos = function ()
    {
        _forAll(function (videoEl)
        {
            //constructor expects an ID or (native) element
            var player = videojs(videoEl[0]);

            if (player) {
                player.dispose();
            }
        });
    };
    this.enableAllControls = function (enable)
    {
        _forAll(function (videoEl)
        {
            //constructor expects an ID or (native) element
            var player = videojs(videoEl[0]);
            player.controls(enable);
        });
    };

    //-----PRIVATE METHODS-----
    var _forAll = function (callback)
    {
        var allVideos = $('video');
        allVideos.each(function ()
        {
            var video = $(this);
            if (callback) {
                callback(video);
            }
        });
    };
    var _onTap = function (video)
    {
        if (video.paused() === true) {
            video.play();
        }
        else {
            video.pause();
        }
    };
    var _onPlay = function (video)
    {
        var wrappedSlider = _findSlider(video);
        if (wrappedSlider) {
            wrappedSlider.addClass('playing');
        }

        //make sure they're reset after a possible slide swipe (force hide)
        video.controls(true);
    };
    var _onPause = function (video)
    {
        var wrappedSlider = _findSlider(video);
        if (wrappedSlider) {
            wrappedSlider.removeClass('playing');
            //if we're wrapped by a slider, explicitly hide the controls (only the big play button visible)
            video.controls(false);
        }
    };
    var _onEnded = function (video)
    {
        var wrappedSlider = _findSlider(video);
        if (wrappedSlider) {
            wrappedSlider.removeClass('playing');
        }

        video.pause();
        video.currentTime(0);
        video.exitFullscreen();
        video.hasStarted(false);
        video.controls(true);
    };
    var _findSlider = function (video)
    {
        var wrappedSlider = $(video.el()).closest('.' + MgConstants.SLIDER_CLASS);
        return wrappedSlider.length ? wrappedSlider : null;
    };


    this.initVideos = function ()
    {
        var i = 0;
        _forAll(function (videoEl)
        {
            //quickly init and make the id's unique before launching
            var id = 'videojs_' + i;
            videoEl.attr('id', id);

            if (videojs) {
                var player = videojs(id,
                    //options
                    {
                        flash: {
                            swf: "/assets/webjars/video-js/5.8.8/video-js.swf"
                        }
                    },
                    function ready()
                    {
                        var video = this;

                        //console.log('Good to go!');

                        //by default, on mobile, the controls bar is shown if a user taps the screen,
                        //but we want to pause the video instead -> this handles that workaround
                        var safetyObject = {
                            IWillNotUseThisInPlugins: true
                        };
                        var tech = this.tech(safetyObject);
                        video.on(tech, 'tap', function ()
                        {
                            //this works, but not very handy in a slider (you want to see the controls there)
                            //_onTap(video);
                        });

                        video.on('play', function ()
                        {
                            _onPlay(video);
                        });
                        video.on('pause', function ()
                        {
                            _onPause(video);
                        });
                        video.on('ended', function ()
                        {
                            _onEnded(video);
                        });

                    });

                i++;
            }
        });
    };

    //-----BOOTSTRAP CODE-----
    this.initVideos();

}]);
