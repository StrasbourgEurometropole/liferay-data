(function ($) {
    // Affichage MAp Event
    if($('.btn-event-map').length){
        $('.btn-event-map').on('click', function(){
            if ($('.event-gmap').hasClass('visible')){
                $('.event-gmap').slideUp('slow'); 
                $('.event-gmap').removeClass('visible');           
            } else{
                $('.event-gmap').slideDown('slow');
                $(".event-gmap").css('visibility','inherit');
                $(".event-gmap").css('position','relative');
                $('.event-gmap').addClass('visible');
                $('html, body').animate({
                    scrollTop: $('.event-gmap').offset().top
                });
            }
        });
    }
});