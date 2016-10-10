base.plugin("mobielegidsen.imports.Surroundings", ["base.core.Class", function (Class)
{
    var Surroundings = this;

    this.getActiveSurroundingsWrapperClass = function()
    {
        var wrapperClass = null;
        if ($('.show-food-btn').hasClass('active')) {
            wrapperClass = 'food-wrapper';
        }
        if ($('.show-lodging-btn').hasClass('active')) {
            wrapperClass = 'lodging-wrapper';
        }

        return wrapperClass;
    };

    this.initSliders = function()
    {
        var slider = $(".slider");

        // init owl-carousel
        slider.owlCarousel({
            singleItem: true,
            //milliseconds between auto-slide or false to disable
            autoPlay: false,
            //if this is activated, the caroussel will re-start after stopping on blur
            stopOnHover: false,
            //startDragging: onSliderStartDragging,
            //afterMove: onSliderStopDragging
        });

        //get carousel instance data and store it in variable owl
        owlCarousel = slider.data('owlCarousel');

        //we'll start out stopped (so we can activate it with onSliderStartDragging())
        if (owlCarousel) {
            owlCarousel.stop();
        }
    };

    // show-lodging-btn
    $('.show-lodging-btn').click(function ()
    {
        $('.show-food-btn').removeClass('active');
        $('.show-lodging-btn').addClass('active');

        $('.lodging-wrapper').addClass('active');
        $('.food-wrapper').removeClass('active');

        var currentId = parseInt($('.lodging-wrapper .item.active').attr('data-id'));
        var currentItem = $('.lodging-wrapper .item:nth-child(' + currentId + ')');
        $('.surroundings-item-name').html(currentItem.attr('data-name'));
    });

    // show-food-btn
    $('.show-food-btn').click(function ()
    {
        $('.show-food-btn').addClass('active');
        $('.show-lodging-btn').removeClass('active');

        $('.lodging-wrapper').removeClass('active');
        $('.food-wrapper').addClass('active');

        var currentId = parseInt($('.food-wrapper .item.active').attr('data-id'));
        if (currentId) {
            var currentItem = $('.food-wrapper .item:nth-child(' + currentId + ')');
            $('.surroundings-item-name').html(currentItem.attr('data-name'));
        }
    });

    // next lodging
    $('.next-btn').click(function ()
    {
        var wrapperClass = Surroundings.getActiveSurroundingsWrapperClass();
        if (wrapperClass) {
            var currentId = parseInt($('.' + wrapperClass + ' .item.active').attr('data-id'));
            var nextId = currentId + 1;
            var total = $('.' + wrapperClass + ' .item').length;

            if (nextId > total) {
                nextId = 1;
            }

            var nextItem = $('.' + wrapperClass + ' .item:nth-child(' + nextId + ')');
            $('.surroundings-item-name').html(nextItem.attr('data-name'));

            $('.' + wrapperClass + ' .item').removeClass('active');
            nextItem.addClass('active');
        }
    });

    // prev lodging
    $('.prev-btn').click(function ()
    {
        var wrapperClass = Surroundings.getActiveSurroundingsWrapperClass();
        if (wrapperClass) {
            var currentId = parseInt($('.' + wrapperClass + ' .item.active').attr('data-id'));
            var nextId = currentId - 1;
            var total = $('.' + wrapperClass + ' .item').length;

            if (nextId <= 0) {
                nextId = total;
            }

            var nextItem = $('.' + wrapperClass + ' .item:nth-child(' + nextId + ')');
            $('.surroundings-item-name').html(nextItem.attr('data-name'));

            $('.' + wrapperClass + ' .item').removeClass('active');
            nextItem.addClass('active');
        }
    });

    //init surroundings
    var wrapperClass = null;
    if ($('.show-food-btn').hasClass('active')) {
        wrapperClass = 'food-wrapper';
    }
    if ($('.show-lodging-btn').hasClass('active')) {
        wrapperClass = 'lodging-wrapper';
    }
    if (wrapperClass) {
        var currentId = parseInt($('.' + wrapperClass + ' .item.active').attr('data-id'));
        if (currentId) {
            var currentItem = $('.' + wrapperClass + ' .item:nth-child(' + currentId + ')');
            $('.surroundings-item-name').html(currentItem.attr('data-name'));
        }
    }
    this.initSliders();

}]);