$(window).on( "load",function() {
    $('#seu-grid-carrefour').masonry({
        itemSelector: '.seu-grid-item',
        gutter: 20,
        percentPosition: true,
        transitionDuration: '0.6s'
    });
});
