(function($){
    $(document).ready(function(){

        $('.masonry-demarche').masonry({
            itemSelector: '.demarche-topic',
            gutter: 20,
            percentPosition: true,
            transitionDuration: '0.6s'
        });

    });
}(jQuery));