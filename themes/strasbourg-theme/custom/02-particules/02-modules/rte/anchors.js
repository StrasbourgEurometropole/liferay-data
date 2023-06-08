(function ($) {

    // Get Hash if one
    if (window.location.hash) {
        var id = window.location.hash.substring(1);
    }

    // Au chargement, si hash, on récup son offset - un eventuel header fixed
    $(window).on( "load",function () {
        if (window.location.hash) {
            var element = $('#' + id);
            if (element.length == 0) {
                element = $('[name=' + id + ']');
            }
            if (element.length !== 0) {
                $('body, html').animate({
                    scrollTop: element.offset().top - 110
                });
            }
        }
    });

    // Pour chaque liens, si # dans l'href => on scroll à l'élément s'il est présent sur la page, sinon on va suit le liens normalement
    $('a:not(.webform-progressbar-page)').on('click', function (e) {
        if($(this).hasClass('seu-add-favorites') || $(this).hasClass('add-favorites') || $(this).hasClass('item-misc')) {
            return;
        }
        var href = $(this).attr('href');
        var id;
        if (href && href.indexOf('#') != -1) {
            e.preventDefault();
            var pos = href.search('#') + 1;
            id = href.slice(pos);
            var element = $('#' + id);
            if (id.length > 0 && element.length == 0) {
                element = $('[name=' + id + ']');
            }
            if (element.length) {
                // cl("Scroll to Element in page");
                $('body, html').animate({
                    scrollTop: element.offset().top - 110
                }, 800);
            } else {
                window.location.replace(href);
            }
        }
    });

}(jQuery));