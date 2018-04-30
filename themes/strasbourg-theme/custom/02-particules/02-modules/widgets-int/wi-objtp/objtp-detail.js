$(window).load(function() {
    $('#objtp-detail-container').masonry({
        itemSelector: '.objtp-detail-item',
        gutter: '.gutter-sizer',
        percentPosition: true,
        transitionDuration: '0.6s'
    });
});
