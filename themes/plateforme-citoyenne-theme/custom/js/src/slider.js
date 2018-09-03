/* --------------------------- */
/* --------------------------- */

/* SCRIPT POUR OWL CAROUSEL OPACIFY */

/* --------------------------- */
/* --------------------------- */

function opacifySlider() {
    $('.owl-opacify').on('translated.owl.carousel', function () {
        var $el = $(this);
        opacifyOffSlide($el);
        $el.removeClass('translate');

    }).on('translate.owl.carousel drag.owl.carousel', function () {
        $(this).addClass('translate');
    }).on('initialized.owl.carousel', function () {
        var $el = $(this);
        opacifyOffSlide($el);
    });
}
opacifySlider();

function opacifyOffSlide($el) {
    var elOffset = $el.offset();
    var left = elOffset.left;
    var width = $el.width();
    var right = left + width;

    var slides = [];

    $('.owl-item', $el).each(function () {
        $slide = $(this);
        var o = $slide.offset();
        var w = $slide.width();

        if (o.left < left) {
            slides.push(this);
        }
        if (o.left + w > right) {
            slides.push(this);
        }
    }).removeClass('opacify');

    $(slides).addClass('opacify');

}
// End Change comportement OwlCarousel


/**
 * prend en charge automatiquement les options suivantes en balise data :
 *
 * items,
 * loop,
 * margin, center, autowidth, autoheight,
 * nav, dots,
 * autoplay, autoplayTimeout, autoplayHoverPause, autoplaySpeed
 *
 */


$('.owl-cards').each(function () {

    if ($('.owl-cards .item').length > 0) {
        var _self = $(this);

        var options = {
            loop: false,
            margin: 30,
            dots: true,
            nav: true,
            items: 4,
            // autoHeight:true,
            autoWidth: true,
            navText: ["<span class='icon-ico-chevron-left'></span>", "<span class='icon-ico-chevron-right'></span>"]
        };

        var data = _self.data();

        $.each(data, function (key, data) {

            if (key.match(/(items|loop|margin|center|autoWidth|autoheight|nav|dots|autoplay|autoplayspeed)/gi)) {
                options[key] = data;
            }

        });
        _self.on('initialized.owl.carousel', function (event) {
            // Do something
            $('.owl-stage', _self).attr('data-anim', 'top-stack');
        });

        _self.owlCarousel(options);
    }

});


$('.owl-slider').each(function () {

    var _self = $(this);

    var options = {
        items: 1,
        loop: true,
        singleItem: true,
        margin: 0,
        nav: true,
        dots: true,
        navText: ["", ""],
        autoplay: false,
        autoplayTimeout: 4000,
    };

    var data = _self.data();

    $.each(data, function (key, data) {

        if (key.match(/(items|loop|margin|center|autoWidth|autoheight|nav|dots|autoplay|autoplayspeed)/gi)) {
            options[key] = data;
        }

    });

    _self.on('initialized.owl.carousel', function (event) {
        // Do something
        $('.owl-stage', _self).attr('data-anim', 'top-stack');
    });

    _self.owlCarousel(options);

});


$('.owl-timeline').each(function () {

    var _self = $(this);


    var options = {
            items: 5,
            loop: false,
            margin: 0,
            startPosition: 2,
            responsive: {
                0: {
                    items: 2
                },
                992: {
                    items: 3
                },
                1300: {
                    items: 5
                }
            },
            nav: true,
            dots: false,
            center: true,
            mouseDrag: false,
            touchDrag: false,
            navText: ["<span class='icon-ico-chevron-left'></span>", "<span class='icon-ico-chevron-right'></span>"],
            autoplay: false,
            autoplayTimeout: 4000
        }
        ;

    var data = _self.data();

    $.each(data, function (key, data) {

        if (key.match(/(items|loop|margin|center|autoWidth|autoheight|nav|dots|autoplay|autoplayspeed)/gi)) {
            options[key] = data;
        }

    });

    _self.on('initialized.owl.carousel', function (event) {
        // Do something
        $('.owl-stage', _self).attr('data-anim', 'top-stack');
    });

    _self.on('changed.owl.carousel', function (event) {
        rangerSliderValue = event.item.index + 1;
        if (rangerSliderValue < 1) {

            rangerSliderValue = 1;
        }
        $('#myRange').val(rangerSliderValue);
    });

    _self.owlCarousel(options);

    $('#myRange').on('change', function () {
        _self.trigger('to.owl.carousel', (parseInt($('#myRange').val()) - 1));
    });


});