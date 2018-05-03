$('.pro-reponse').click(function(e){
    e.preventDefault();
    $('.pro-reagir').find(':text').focus();
    $('.pro-reagir').find('textarea').focus();
});