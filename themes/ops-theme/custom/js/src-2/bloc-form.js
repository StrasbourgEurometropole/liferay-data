$('form select').selectric();


// Ajoute une class à la balise form pour annuler le curseur qui clignotte
$('.ops-group-email input').focusin(function () {
    $(this).parents('form:first').addClass('ops-focused');
});


// Quand on enlève le focus, si rien n'est écrit dans le input on ferme le volet
$('.ops-group-email input').focusout(function () {
    if ($(this).val().length < 1) {
        $(this).parents('form:first').removeClass('ops-focused');
    }
});


// A chaque focus
$('.ops-group-email input').focus(function(){
    if($(this).val().length > 0){
        $(this).parent().parent().addClass('ops-nl-open');
    }
    if($('.ops-group-checkbox input').is(':checked')){
        $(this).parent().parent().addClass('ops-nl-open');
    }
    else{
        $(this).parent().parent().removeClass('ops-nl-open');
    }
});


// A chaque check d'un des input, je test s'il y en a au moins 1 en checked. Si 0 alors on ferme le volet.
$('.ops-group-checkbox input').change(function(){
   if($('.ops-group-checkbox input').is(':checked')){
       $(this).parents('form:first').addClass('ops-nl-open');
   }
   else{
       $(this).parents('form:first').removeClass('ops-nl-open');
   }
});