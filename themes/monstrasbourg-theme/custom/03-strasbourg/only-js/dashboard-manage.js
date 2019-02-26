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
        }
    });
}(jQuery));
