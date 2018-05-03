$('[data-frmval]').each(function(){
    this.setAttribute('placeholder',this.getAttribute('data-frmval'));
    if(this.value === this.getAttribute('data-frmval')){
        this.value = '';
    }
});

$('form select').selectric();


// Ajoute une class à la balise form pour annuler le curseur qui clignotte
$('form input').focusin(function () {
    $(this).parents('form:first').addClass('input-is-focus');
});

$('form input').focusout(function () {
    $(this).parents('form:first').removeClass('input-is-focus');
});


$('.frm_radio > label').append('<span></span>');
$('.frm_checkbox > label').append('<span></span>');



// Add a hidden label around the selectric input for accessibility.
var countSelectricInput = 0;
$(".selectric-input").each(function(){
    countSelectricInput++;
    $(this)
        .attr('id','selectric-input-id-'+countSelectricInput).attr('tabindex','1')
        .parent()
        .append('<label for="selectric-input-id-'+countSelectricInput+'" class="hide" aria-hidden="true">Hidden Label</label>');
});



// Quand on click sur l'îcone de recherche dans le menu, on ouvre la search bar
$('a[href$="rechercher"]').on('click',function(){
    $('#header').toggleClass('pro-wrapper-search-open');
    $('.pro-wrapper-search form input').focus();
    $('#pro-shadow-bg').addClass('pro-display-block');
});