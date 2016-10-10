
base.plugin("mobielegidsen.Main", ["base.core.Class", "constants.mobielegidsen.core", function (Class, MgConstants)
{
    var Main = this;

    //-----FUNCTIONS-----
    this.initMain = function()
    {
        // header read-more
        $('.read-more-btn').click(function ()
        {
            $('.read-more-hidden').slideToggle();
            $('.read-more-btn').hide();
            $('.three-dots').hide();
        });

        // header read-less
        $('.read-less-btn').click(function ()
        {
            $('.read-more-hidden').slideToggle();
            $('.read-more-btn').show();
            $('.three-dots').show();
        });

        // more info read-more
        $('.more-info-btn').click(function ()
        {
            $(this).find('i').toggleClass('fa-chevron-down');
            $(this).find('i').toggleClass('fa-chevron-up');
            $(this).next('div').slideToggle();
        });

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
            var currentItem = $('.food-wrapper .item:nth-child(' + currentId + ')');
            $('.surroundings-item-name').html(currentItem.attr('data-name'));
        });

        // show-route-btn
        $('.show-route-btn').click(function ()
        {
            $('.route-cell').removeClass('default');
            $('.main-cell').addClass('hide');

            $('.map-cell').removeClass('active-cell');
            $('.route-cell').addClass('active-cell');
            $('.extra-info-cell').removeClass('active-cell');

            $('.route-cell').scrollTop(0);
        });

        // show-map-btn
        $('.show-map-btn').click(function ()
        {
            $('.route-cell').removeClass('default');
            $('.main-cell').addClass('hide');

            $('.map-cell').addClass('active-cell');
            $('.route-cell').removeClass('active-cell');
            $('.extra-info-cell').removeClass('active-cell');

            $('.map-cell').scrollTop(0);
        });

        // show-info-btn
        $('.route-cell table tr').click(function ()
        {
            $('.route-cell').removeClass('default');
            $('.main-cell').addClass('hide');

            $('.map-cell').removeClass('active-cell');
            $('.route-cell').removeClass('active-cell');
            $('.extra-info-cell').removeClass('active-cell');

            $('.extra-info-cell-' + $(this).attr('data-extra-info-id')).addClass('active-cell');
            $('.extra-info-cell-' + $(this).attr('data-extra-info-id')).scrollTop(0);
        });

        // show-main-btn
        $('.show-main-btn').click(function ()
        {
            $('.route-cell').addClass('default');
            $('.main-cell').removeClass('hide');

            $('.map-cell').removeClass('active-cell');
            $('.route-cell').removeClass('active-cell');
            $('.extra-info-cell').removeClass('active-cell');

            $('.main-cell').scrollTop(0);
        });

        // next lodging
        $('.next-btn').click(function ()
        {
            var wrapperClass = Main.getActiveSurroundingsWrapperClass();
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
            var wrapperClass = Main.getActiveSurroundingsWrapperClass();
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
            var currentItem = $('.' + wrapperClass + ' .item:nth-child(' + currentId + ')');
            $('.surroundings-item-name').html(currentItem.attr('data-name'));
        }
    };

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

    this.popupCenter = function(url, title, w, h)
    {
        // Fixes dual-screen position                         Most browsers      Firefox
        var dualScreenLeft = window.screenLeft != undefined ? window.screenLeft : screen.left;
        var dualScreenTop = window.screenTop != undefined ? window.screenTop : screen.top;

        var width = window.innerWidth ? window.innerWidth : document.documentElement.clientWidth ? document.documentElement.clientWidth : screen.width;
        var height = window.innerHeight ? window.innerHeight : document.documentElement.clientHeight ? document.documentElement.clientHeight : screen.height;

        var left = ((width / 2) - (w / 2)) + dualScreenLeft;
        var top = ((height / 2) - (h / 2)) + dualScreenTop;
        var newWindow = window.open(url, title, 'scrollbars=yes, width=' + w + ', height=' + h + ', top=' + top + ', left=' + left);

        // Puts focus on the newWindow
        if (window.focus) {
            newWindow.focus();
        }
    };


    //-----MAIN CODE-----
    //this.initMain();
    
}]);
