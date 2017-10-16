$(document).ready(function(){
    if($('input[type="date"]').length || $('input[data-type="date"]').length){
        $('input[type="date"], input[data-type="date"]').datepicker(
            $.extend({
                onClose: function(date, instance) {
                    var name = instance.input[0].name;
                    if ($('input[data-name="' + name + 'Day"').length) {
                        var dayInput = $('input[data-name="' + name + 'Day"');
                        var monthInput = $('input[data-name="' + name + 'Month"');
                        var yearInput = $('input[data-name="' + name + 'Year"');
                        dayInput[0].value = instance.selectedDay;
                        monthInput[0].value = instance.selectedMonth;
                        yearInput[0].value = instance.selectedYear;
                    }
                }
            }, $.datepicker.regional[ "fr" ]) 
        );
    }
});