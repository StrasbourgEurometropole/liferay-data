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
	    parse(dateString, format) {
	        const parts = dateString.split('/');
	        const day = parseInt(parts[0], 10);
	        const month = parseInt(parts[1] - 1, 10);
	        const year = parseInt(parts[1], 10);
	        return new Date(year, month, day);
	    }
    });
});