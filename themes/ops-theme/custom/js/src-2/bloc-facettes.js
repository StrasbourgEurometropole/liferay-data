// Récupère les valeurs dans la dropdown

var thFacettes = {

    states: {},

    init: function(){

        $('.ops-facette-checkbox').each(function(){
            var facetteFilterEl = this;
            var facetteName = $(this).find('input');

            // console.log(facetteName);

            setTimeout(function(){
                $('a.selected',facetteFilterEl).off('click').on('click',function(e){
                    e.preventDefault();
                    e.stopPropagation();
                    $(this).html('');
                    thFacettes.states[facetteName] = '';
                });
            },250);

            thFacettes.states[facetteName] = '';

            $('input',this).on('click', function (e) {
                var newValue =  $(this).val();

                $(this).parents('.ops-facette-checkbox').find('a.selected').html($(this).val());


                $('.ops-facette-checkbox').removeClass('ops-open');

                if(newValue != thFacettes.states[facetteName] && newValue != ''){
                    thFacettes.states[facetteName] = newValue;
                }else{
                    thFacettes.states[facetteName] = '';
                }
            });
        });

    }
};

thFacettes.init();