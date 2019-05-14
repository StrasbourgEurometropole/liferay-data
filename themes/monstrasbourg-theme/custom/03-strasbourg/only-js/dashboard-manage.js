(function ($) {
    $(document).ready(function(){
        if($('.mseu body.front').length){
            // Enlever le widget
            $('.portlet-column-content').on('click','.delete-wi', function(e){
                var portletId = $(this).data('portletId');
                var $section = $(this).closest('section');
                var message = "Êtes vous sur de vouloir masquer le widget ?";
                var agree = function(){
                    // enleve le widget
                    $section.addClass('deleted');

                    // stock en base le fait d'enlever le widget
                    Liferay.Service(
                        '/strasbourg.strasbourg/hide-portlet',
                        {
                            portletId: portletId
                        }
                    );
                    setTimeout(function(){
                        $section.slideUp();
                    }, 100);
                };
		        var deny = function() {
		        };
                // demande confirmation pour enlever le widget
                createPopin(message, agree, deny);
                e.stopPropagation();
            });
            // Déplier le widget
            $('.portlet-column-content').on('click','.retractable-folded-wi', function(e){
                var portletId = $(this).data('portletId');
                var $section = $(this).closest('section');
                var $detail = $section.find('.detail');
                // déplie le widget
                $detail.show();
                // Si c'est le portlet autour de moi, on recharge la carte
                if($section.attr('id') == "wi-aroundme")
                    mymap.invalidateSize();
                // change le bouton
                $(this).removeClass("retractable-folded-wi").addClass("retractable-unfolded-wi");
                // stock en base le fait de déplier ce widget
                Liferay.Service(
                    '/strasbourg.strasbourg/unfold-portlet',
                    {
                        portletId: portletId
                    }
                );
                e.stopPropagation();
            });
            // Plier le widget
            $('.portlet-column-content').on('click','.retractable-unfolded-wi', function(e){
                var portletId = $(this).data('portletId');
                var $section = $(this).closest('section');
                var $detail = $section.find('.detail');
                // plie le widget
                $detail.hide();
                // change le bouton
                $(this).removeClass("retractable-unfolded-wi").addClass("retractable-folded-wi");
                // stock en base le fait de déplier ce widget
                Liferay.Service(
                    '/strasbourg.strasbourg/fold-portlet',
                    {
                        portletId: portletId
                    }
                );
                e.stopPropagation();
            });
        }
    });
}(jQuery));
