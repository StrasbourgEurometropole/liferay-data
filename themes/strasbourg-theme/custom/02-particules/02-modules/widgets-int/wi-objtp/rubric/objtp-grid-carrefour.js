$(window).on( "load",function() {
    $('#objtp-grid-carrefour').masonry({
        itemSelector: '.objtp-grid-item',
        gutter: 20,
        percentPosition: true,
        transitionDuration: '0.6s'
    });
});
