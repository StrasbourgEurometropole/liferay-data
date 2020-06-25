var namespace = '_eu_strasbourg_portlet_council_CouncilBOPortlet_';

/** Lors du changement des types de conseils **/
function changeIsActive(){
    hasType = false;
    $('input[data-type]').each(function(){
        if($(this).is(':checked')){
            hasType = true;
        }
    });
    if(hasType){
        $('input[name=' + namespace + 'isActive]').prop('checked',true);
    }else{
        $('input[name=' + namespace + 'isActive]').prop('checked',false);
    }
}