(function ($) {
    $('.dossier .btn-minus').on('click', function() {
        var num = $(this).parent().attr("name");
        $('#dossier' + num).addClass("hide");
        //$('#dossier' + num).hide();
        $(this).addClass("hide");
        $(this).parent().children(".btn-more").removeClass("hide");
    });
    $('.dossier .btn-more').on('click', function() {
        var num = $(this).parent().attr("name");
        $('#dossier' + num).removeClass("hide");
        //$('#dossier' + num).show();
        $(this).addClass("hide");
        $(this).parent().children(".btn-minus").removeClass("hide");
    });
 }(jQuery));
