$(document).ready(function(){
    if($('.seu-trigger-advanced-search').length){
            $('.seu-trigger-advanced-search').on('click', function(){
                $(this).toggleClass('opened');
                $('.seu-filter-advanced-search').slideToggle();
            });
    } 

    $(document).ready(function(){
        if($('input[type="date"]').length || $('input[data-type="date"]').length){
            $('input[type="date"], input[data-type="date"]').parent('.content').addClass('type-date');
        }
    });
});