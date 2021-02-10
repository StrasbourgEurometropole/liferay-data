function th_linkAll(el){
    if(!el){
        el = document;
    }
    $('[data-linkall]',el).each(function(){

        var target = $(this).data('linkall');

        $(this).on('click',function(e){
            if(!e.target.tagName.match(/a/i)){
                e.preventDefault();
                var href = $(e.currentTarget).find(target).attr('href');
                if(href){
                    document.location.href = href;
                }
            }
        });

    });
}

th_linkAll();