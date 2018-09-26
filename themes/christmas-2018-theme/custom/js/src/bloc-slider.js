// Change comportement OwlCarousel
function opacifySlider(){
    $('.owl-opacify').on('translated.owl.carousel',function(){
        var $el = $(this);
        opacifyOffSlide($el);
        $el.removeClass('translate');

    }).on('translate.owl.carousel drag.owl.carousel',function(){
        $(this).addClass('translate');
    }).on('initialized.owl.carousel',function(){
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

    $('.owl-item',$el).each(function(){
        $slide = $(this);
        var o = $slide.offset();
        var w = $slide.width();

        if(o.left < left){
            slides.push(this);
        }
        if(o.left + w > right){
            slides.push(this);
        }
    }).removeClass('opacify');

    $(slides).addClass('opacify');
}
// End Change comportement OwlCarousel


// Carsousel section Agenda
$('#owl-agenda').owlCarousel({
    loop:false,
    margin:40,
    dots: false,
    nav:true,
    items: 3,
    autoWidth: true,
    navText: ["<span class='icon-chevron-thin-left'></span>","<span class='icon-chevron-thin-right'></span>"]
})

// Carsousel section Testimonial
$('.owl-testi').each(function () {

    if($('.owl-testi .item').length > 0){
        var _self = $(this);

        var options = {
            loop:true,
            margin:0,
            dots: false,
            nav:true,
            items: 1,
            navText: ["<span class='icon-chevron-thin-left'></span>","<span class='icon-chevron-thin-right'></span>"]
        };
        _self.owlCarousel(options);
    }
});

// Carsousel section agenda header
$('#owl-full').owlCarousel({
    loop:true,
    dots: false,
    nav:true,
    items: 1,
    navText: ["<span class='icon-chevron-thin-left'></span>","<span class='icon-chevron-thin-right'></span>"]
});

// Carsousel Section Slider Instagram
$('.owl-instagram').each(function () {

    if($('.owl-instagram .item').length > 0){
        var _self = $(this);

        var options = {
            responsive:{
                0:{
                    items:1,
                    margin: 20
                },
                768:{
                    items:2,
                    margin: 20
                },
                1024:{
                    items: 3,
                    margin: 60
                }
            },
            loop: true,
            dots: false,
            nav:true,
            autoplay: false,
            autoplayTimeout: 4000,
            navText: ["<span class='icon-chevron-thin-left'></span>","<span class='icon-chevron-thin-right'></span>"]
        };
        _self.owlCarousel(options);
    }
});