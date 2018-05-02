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

    if($('.owl-cards .item').length > 0){
        var _self = $(this);

        var options = {
            loop: false,
            margin: 30,
            dots: true,
            nav: true,
            items: 4,
            // autoHeight:true,
            autoWidth: true,
            navText: ["", ""]
        };

        var data = _self.data();

        $.each(data, function (key, data) {

            if (key.match(/(items|loop|margin|center|autoWidth|autoheight|nav|dots|autoplay|autoplayspeed)/gi)) {
                options[key] = data;
            }

        });
        _self.on('initialized.owl.carousel', function(event){
            // Do something
            $('.owl-stage',_self).attr('data-anim','top-stack');
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

    _self.on('initialized.owl.carousel', function(event){
        // Do something
        $('.owl-stage',_self).attr('data-anim','top-stack');
    });

    _self.owlCarousel(options);

});