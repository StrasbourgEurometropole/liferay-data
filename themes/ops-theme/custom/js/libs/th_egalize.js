/*
        EGALISE EN HAUTEUR DES BLOCKS

        Mettre data-egalize en attr sur le parent
        Mettre la cible en tant que valeur
        Exemple (egalize tous les li):
        <ul data-egalize="li">
 */
function egalizeAll(){
    $('[data-egalize]').each(function(){

        var _self = this;
        var target = $(_self).data('egalize');

        egalizeBlock(_self,target);

        $('img',_self).on('load',function(){
            egalizeBlock(_self,target);
        });
    });
}
function egalizeBlock(elem,target){

    var maxHeight = 0;

    $(target, elem).each(function(){
        $(this).css('min-height','auto');
        if(maxHeight < $(this).height()){
            maxHeight = $(this).height();
        }
    });

    $(target, elem).css('min-height',(maxHeight+1)+'px');
}

/*
    Previen de certains bug
 */
egalizeAll();
setTimeout(egalizeAll,250);
setTimeout(egalizeAll,750);
setTimeout(egalizeAll,1500);

$(window).on('resize',egalizeAll);