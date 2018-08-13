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



$('.pro-wrapper-search-top .icon-ico-close').on('click',function(){
    $('#pro-header').removeClass('pro-wrapper-search-open');
    $('body').css('overflow','auto');
    $('#pro-shadow-bg').removeClass('pro-display-block');
});


// Quand on click sur l'îcone de recherche dans le menu, on ouvre la search bar
$('a[href$="rechercher"]').on('click',function(e){
    e.preventDefault();
    $('#pro-header').toggleClass('pro-wrapper-search-open');
    $('body').css('overflow','hidden');
    $('.pro-wrapper-search form input').focus();
    $('#pro-shadow-bg').addClass('pro-display-block');
});



function bs_input_file() {
    $(".input-file").before(
        function() {
            if ( ! $(this).prev().hasClass('input-ghost') ) {
                var element = $("<input type='file' class='input-ghost' style='visibility:hidden; height:0'>");
                element.attr("name",$(this).attr("name"));
                element.change(function(){
                    element.next(element).find('input').val((element.val()).split('\\').pop());
                });
                $(this).find("button.btn-choose").click(function(){
                    element.click();
                });
                $(this).find("button.btn-reset").click(function(){
                    element.val(null);
                    $(this).parents(".input-file").find('input').val('');
                });
                $(this).find('input').css("cursor","pointer");
                $(this).find('input').mousedown(function() {
                    $(this).parents('.input-file').prev().click();
                    return false;
                });
                return element;
            }
        }
    );
}
$(function() {
    bs_input_file();
});