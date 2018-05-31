// Afficher une date

$('.frm_date').each(function(){
    var picker = new Pikaday({ field: this,format: 'DD/MM/YY', });
});