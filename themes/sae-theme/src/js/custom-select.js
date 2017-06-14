$.fn.customSelect = function(opts) {
    if(opts == undefined){
        opts = {}
    }
    var el = this;
    var settings;
    var container;
    settings = $.extend({
        customArrow: false
    }, opts);
    // RÃ©cupÃ¨re le texte de l'option selectionnee
    function getOptionTextFromValue(select, value){
        var option = select.find('option[value="'+value+'"]');
        return option.text();
    }
    // On boucle sur les appels identiques (ne fait qu'un appel si selecteur unique)
    for (var i = 0; i <= el.length - 1; i++) {
        // Construction de la structure
        $(el[i]).wrap('<div class="customSelectContain" />');
        $(el[i]).addClass('silencedSelect');
        container = $(el[i]).parent('.customSelectContain');
        container.append('<div class="customSelect"><span class="customSelectInner"></span></div>');

        // Affichage de la flÃ¨che
        if (settings.customArrow) {
            container.find('.customSelect').append('<div class="customArrow"></div>');
        } else {
            container.find('.customSelect').addClass('arrowed');
        }

        // Ecouteur - Mise Ã  jour/Init de la valeur du select
        if ($(el[i]).find('option[selected="true"]').length) {
            container.find('.customSelectInner').html($(el[i]).find('option[selected="true"]').text());
        }else{
            container.find('.customSelectInner').html($(el[i]).find('option:eq(1)').text());
        }
        
        $(el[i]).on('change ', function(e) {
            var text = getOptionTextFromValue($(this), $(this).val())
            $(this).parent('.customSelectContain').find('.customSelectInner').html(text);
        }).on('focus', function(){
            $(this).parent('.customSelectContain').addClass('focused');
        }).on('blur',function(){
            $(this).parent('.customSelectContain').removeClass('focused');
        });
        
        $(el[i]).on('keyup keypress', function(e) {
            if((e.keyCode >= 48 && e.keyCode <= 90) || (e.keyCode <=40 && e.keyCode >= 37) ){
                var text = getOptionTextFromValue($(this), $(this).val())
                $(this).parent('.customSelectContain').find('.customSelectInner').html(text);
            }
        }).on('focus', function(){
            $(this).parent('.customSelectContain').addClass('focused');
        }).on('blur',function(){
            $(this).parent('.customSelectContain').removeClass('focused');
        });
    };
}