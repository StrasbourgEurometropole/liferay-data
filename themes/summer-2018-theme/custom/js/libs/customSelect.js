(function ($) {
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

            // Affichage de la flèche
            if (settings.customArrow) {
                container.find('.customSelect').append('<div class="customArrow"></div>');
            } else {
                container.find('.customSelect').addClass('arrowed');
            }

            // Ecouteur - Mise à jour/Init de la valeur du select
            if($(el[i]).find('option[selected]').length){
                container.find('.customSelectInner').html($(el[i]).find('option[selected]').text());
            }else{
                container.find('.customSelectInner').html($(el[i]).find('option:eq(0)').text());
            }
            $(el[i]).on('change ', function(e) {
                var text = getOptionTextFromValue($(this), $(this).val())
                $(this).parent('.customSelectContain').find('.customSelectInner').html(text);
            }).on('click', function(){
                $(this).parent('.customSelectContain').toggleClass('opened');
            }).on('focus', function(){
                $(this).parent('.customSelectContain').addClass('focused');
            }).on('blur',function(){
                $(this).parent('.customSelectContain').removeClass('focused');
                $(this).parent('.customSelectContain').removeClass('opened');
            });
            
            $(el[i]).on('keyup keypress', function(e) {
                if((e.keyCode >= 48 && e.keyCode <= 90) || (e.keyCode <=40 && e.keyCode >= 37) ){
                    var text = getOptionTextFromValue($(this), $(this).val())
                    $(this).parent('.customSelectContain').find('.customSelectInner').html(text);
                }else if(e.keyCode == 32){
                    if(! $(this).parent('.customSelectContain').hasClass('opened')){
                        $(this).trigger('click');
                    }
                }
            }).on('focus', function(){
                $(this).parent('.customSelectContain').addClass('focused');
            }).on('blur',function(){
                $(this).parent('.customSelectContain').removeClass('focused');
                $(this).parent('.customSelectContain').removeClass('opened');
            });
        };
    }
}(jQuery));
