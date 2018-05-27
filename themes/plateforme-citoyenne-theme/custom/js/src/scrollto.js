$('a[href^="#pro-link"]').bind('click.smoothscroll',function (e) {
    e.preventDefault();
    var target = this.hash,
        $target = $(target);

    $('html, body').stop().animate( {
        'scrollTop': $target.offset().top
    }, 600, 'swing', function () {
        window.location.hash = target;
    } );
} );
