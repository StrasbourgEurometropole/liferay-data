/* DANS LES LISTING DE FACETTE DANS LES BARRES LATERALES, AU CLICK SUR EFFACER, ON DESELECTIONNE LES CHECKBOX ENFANTS ET LA VALEUR DE LA DATE DANS INPUT TEXT */
$('.mns-remove').on('click',function(){
    $(this).parents('.form-group').find('input:checked').prop('checked',false);

    $(this).parents('.form-group').find('input:text').val('');

    $(this).parents('.form-group').find('select').prop('selectedIndex', 0).selectric('refresh');
});