(function ($) {
    $('.family .btn-minus').on('click', function() {
        var num = $(this).parent().attr("name");
        $('#family' + num).addClass("hide");
        //$('#family' + num).hide();
        $(this).addClass("hide");
        $(this).parent().children(".btn-more").removeClass("hide");
    });
    $('.family .btn-more').on('click', function() {
        var num = $(this).parent().attr("name");
        $('#family' + num).removeClass("hide");
        //$('#family' + num).show();
        $(this).addClass("hide");
        $(this).parent().children(".btn-minus").removeClass("hide");
    });
 }(jQuery));
