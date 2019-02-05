(function ($) {
    $('.portlet-column-content').on('click','.dossier .btn-minus', function() {
        var num = $(this).parent().attr("name");
        $('#dossier' + num).addClass("hide");
        //$('#dossier' + num).hide();
        $(this).addClass("hide");
        $(this).parent().children(".btn-more").removeClass("hide");
    });
    $('.portlet-column-content').on('click','.dossier .btn-more', function() {
        var num = $(this).parent().attr("name");
        $('#dossier' + num).removeClass("hide");
        //$('#dossier' + num).show();
        $(this).addClass("hide");
        $(this).parent().children(".btn-minus").removeClass("hide");
    });
 }(jQuery));
