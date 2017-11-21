(function ($) {

    // Get Hash if one
    if (window.location.hash) {
        var id = window.location.hash.substring(1);
    }

    // Au chargement, si hash, on récup son offset - un eventuel header fixed
    $(window).load(function () {
        if (window.location.hash) {
            $('body, html').animate({
                scrollTop: $('#' + id).offset().top - 66
            });
        }
    });

    // Pour chaque liens, si # dans l'href => on scroll à l'élément s'il est présent sur la page, sinon on va suit le liens normalement
    $('a:not(.webform-progressbar-page)').on('click', function (e) {
        var href = $(this).attr('href');
        var id;
        if (href.indexOf('#') != -1) {
            e.preventDefault();
            var pos = href.search('#') + 1;
            id = href.slice(pos);

            if ($('#' + id).length) {
                // cl("Scroll to Element in page");
                $('body, html').animate({
                    scrollTop: $('#' + id).offset().top - 66
                }, 800);
            } else {
                window.location.replace(href);
            }
        }
    });

}(jQuery));