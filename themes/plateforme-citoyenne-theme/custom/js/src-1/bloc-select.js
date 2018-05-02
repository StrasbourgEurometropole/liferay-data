// Slider Actu Quartiers - Changement de slider
$('.pro-wrapper-quartier').first().addClass('active');

$('#actu_quartiers').on('change', function () {
    $('.pro-wrapper-quartier').css('display', 'none');
    $('.pro-wrapper-quartier').removeClass('active');
    var str = this.value;
    var slider = "";
    $('.pro-wrapper-quartier').each(function () {
        slider = $(this).attr("id");

        if (slider === str) {
            $(this).fadeIn('300');
        }
    });
});


// Slider Actu Quartiers - Changement de slider
$('.owl-projet').first().addClass('active');

$('#quartiers').on('change', function () {
    $('.owl-projet').css('display', 'none');
    $('.owl-projet').removeClass('active');
    var str = this.value;
    var slider = "";
    $('.owl-projet').each(function () {
        slider = $(this).attr("id");

        if (slider === str) {
            $(this).fadeIn('300');
        }
    });
});