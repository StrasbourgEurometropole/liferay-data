(function ($) {
    
    function callServeResource(URL) {
        AUI().use('aui-io-request', function(A) {
            A.io.request(URL, {
                method : 'post'
            });
        });
    }
    
    $(document).ready(function(){
        if($('.customize-list').length){
            $list = $('.customize-list'),
            $inputs = $list.find('input[type="checkbox"]');

            $inputs.on('change', function(){
                var $item = $(this).closest('.customize-list__item');
                if(!$item.hasClass('customize-list__item--read')){
                    $item.addClass('customize-list__item--read');
                    var hidePortletURL = $(this).data('hideUrl');
                    if (hidePortletURL) {
                        callServeResource(hidePortletURL);
                    }
                } else {
                    $item.removeClass('customize-list__item--read');
                    var showPortletURL = $(this).data('showUrl');
                    if (showPortletURL) {
                        callServeResource(showPortletURL);
                    }
                }
            });
        }
    });
}(jQuery));