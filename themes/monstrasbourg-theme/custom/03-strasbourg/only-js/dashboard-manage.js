(function ($) {
    $(document).ready(function(){
        if($('.mseu body.front').length){
            $('.delete-wi').on('click', function(){

                var $section = $(this).closest('section');
                var message = "Êtes vous sur de vouloir fermer le panneau ?";
                var serveURL = this.value;
                var agree = function(){
                    $section.addClass('deleted');

                    AUI().use('aui-io-request', function(A) {
                        A.io.request(serveURL, {
                            method : 'post'
                        });
                    });

                    setTimeout(function(){
                        $section.slideUp();
                    },100);
                };
                createPopin(message, agree);
            });
        }
    });
}(jQuery));