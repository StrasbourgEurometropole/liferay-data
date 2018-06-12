// Afficher une date

$('.frm_date').each(function(){
    var picker = new Pikaday({ 
    	field: this,
    	toString(date, format) {
	        const day = date.getDate() < 10 ? '0' + date.getDate() : date.getDate() ;
	        const month = (date.getMonth() + 1) < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1;
	        const year = date.getFullYear();
	        return `${day}/${month}/${year}`;
	    },
	    onSelect: function(date) {
            var name = picker._o.field.name;
            if ($('input[data-name="' + name + 'Day"').length) {
                var dayInput = $('input[data-name="' + name + 'Day"');
                var monthInput = $('input[data-name="' + name + 'Month"');
                var yearInput = $('input[data-name="' + name + 'Year"');
                dayInput[0].value = date.getDate();
                monthInput[0].value = date.getMonth();
                yearInput[0].value = date.getFullYear();
            }
	    }
    });
});