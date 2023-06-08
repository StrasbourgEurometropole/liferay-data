// Slider Basic
// ==========================================================================
$('.slick-basic-slider').each(function(){
    $(this).slick({
        infinite: true,
        dots: true,
        arrows: true,
        appendArrows: $('.ops-slick-nav'),
        appendDots: $('.ops-slick-nav'),
        autoplay: true,
        autoplaySpeed: 2000
    });
});


// Slider Cards
// ==========================================================================
$('.slick-cards-slider').each(function () {
    $(this).slick({
        dots: false,
        infinite: false,
        speed: 300,
        slidesToShow: 1,
        slidesToScroll: 1,
        centerMode: false,
        variableWidth: true,
        responsive: [
            {
                breakpoint: 600,
                settings: {
                    variableWidth: false
                }
            }
        ]
    });
});


// Slider Distribution - Détail CONCERT
// ==========================================================================
$('.slick-distribution').each(function () {
    $(this).slick({
        dots: false,
        arrows: true,
        slidesToShow: 1,
        slidestoScroll: 1,
        slidesPerRow: 3,
        rows: 2,
        responsive: [
            {
                breakpoint: 1024,
                settings: {
                    slidesToShow: 1,
                    slidesToScroll: 1,
                    slidesPerRow: 2
                }
            },
            {
                breakpoint: 600,
                settings: {
                    slidesToShow: 1,
                    slidesToScroll: 1,
                    slidesPerRow: 1
                }
            }
        ]
    });
});



// Slider Actualités - Détail CONCERT
// ==========================================================================
$('.slick-actus').each(function(){
    $(this).slick({
        infinite: false,
        dots: false,
        arrows: true,
        autoplay: true,
        autoplaySpeed: 2000
    });
});