function th_expand(el) {
    $('.to-expand', el).each(function () {
        var $link = $('a[href="#expand"]', this).first();
        if ($link.length == 0) {
            $link = $('a[href="#expand"]', $(this).parent()).first();
        }
        var $wrapper = $(this);

        var wrapperHeight = 0;
        $wrapper.attr('style', 'height:auto!important;max-height:2000px!important;');
        wrapperHeight = $wrapper.height();
        $wrapper.removeAttr('style').css('height', wrapperHeight + 'px');

        $(window).on('resize', function () {
            $wrapper.attr('style', 'height:auto!important;');
            wrapperHeight = $wrapper.height();
            $wrapper.removeAttr('style').css('height', wrapperHeight + 'px');
        });

        $link.on('click', function (e) {
            e.preventDefault();
            $(this).addClass('fadeout');
            $wrapper.addClass('expand');
        });
    });
}

th_expand(document);