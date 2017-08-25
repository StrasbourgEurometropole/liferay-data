$(document).ready(function(){
    if($('input[type="date"]').length || $('input[data-type="date"]').length){
        $('input[type="date"], input[data-type="date"]').datepicker($.datepicker.regional[ "fr" ]);
    }
});