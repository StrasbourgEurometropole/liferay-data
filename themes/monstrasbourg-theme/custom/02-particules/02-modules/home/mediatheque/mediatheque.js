(function ($) {
    $('.portlet-column-content').on('click','#borrowingSeeMore', function() {
        for (i = 3; i < $("#borrowingCount").val(); i++) {
            $('#borrowing_' + i).show();
            $('#borrowing_' + i).removeClass("hidden");
        }
        $('#borrowingSeeMore').hide();
        $('#borrowingSeeLess').removeClass("hidden");
        $('#borrowingSeeLess').show();
    });
    $('.portlet-column-content').on('click','#reservationSeeMore', function() {
        for (i = 3; i < $("#reservationCount").val(); i++) {
            $('#reservation_' + i).show();
            $('#reservation_' + i).removeClass("hidden");
        }
        $('#reservationSeeMore').hide();
        $('#reservationSeeLess').removeClass("hidden");
        $('#reservationSeeLess').show();
    });
    $('.portlet-column-content').on('click','#borrowingSeeLess', function() {
        for (i = 3; i < $("#borrowingCount").val(); i++) {
            $('#borrowing_' + i).hide();
        }
        $('#borrowingSeeMore').show();
        $('#borrowingSeeLess').hide();
    });
    $('.portlet-column-content').on('click','#reservationSeeLess', function() {
        for (i = 3; i < $("#reservationCount").val(); i++) {
            $('#reservation_' + i).hide();
        }
        $('#reservationSeeMore').show();
        $('#reservationSeeLess').hide();
    });
 }(jQuery));
