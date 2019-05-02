(function ($) {
    $(document).ready(function(){
        if($('.mseu body.front').length){
            $('.portlet-column-content').on('click','.delete-wi', function(){
                var portletId = $(this).data('portletId');
                var $section = $(this).closest('section');
                var message = "Êtes vous sur de vouloir masquer le widget ?";
                var agree = function(){
                    $section.addClass('deleted');

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
                createPopin(message, agree);
            });
            $('.portlet-column-content').on('click','.retractable-folded-wi', function(){
                var portletId = $(this).data('portletId');
                var $section = $(this).closest('section');
                var unfold = function(){
                    $section.addClass('unfolded');

                    Liferay.Service(
                        '/strasbourg.strasbourg/unfold-portlet',
                        {
                            portletId: portletId
                        }
                    );
                    setTimeout(function(){
                        $section.slideUp();
                    }, 100);
                };
                if(unfold){
                    $(this).removeClass("retractable-folded-wi").addClass("retractable-unfolded-wi");
                }
            });
            $('.portlet-column-content').on('click','.retractable-unfolded-wi', function(){
                var portletId = $(this).data('portletId');
                var $section = $(this).closest('section');
                var fold = function(){
                    $section.addClass('folded');

                    Liferay.Service(
                        '/strasbourg.strasbourg/fold-portlet',
                        {
                            portletId: portletId
                        }
                    );
                    setTimeout(function(){
                        $section.slideUp();
                    }, 100);
                };
                if(fold){
                    $(this).removeClass("retractable-unfolded-wi").addClass("retractable-folded-wi");
                }
            });
        }
    });
}(jQuery));
