(function ($) {
    

    $(document).ready(function(){
        $('#trigger-account-menu').on('click', function(){
            $(this).next('#account-menu').toggle();
        })
        
        .parents('.seu-nav-account').on('mouseenter', function(){
            $(this).find('#account-menu').show();
        })
        .on('mouseleave', function(){
            $(this).find('#account-menu').hide();
        })
    });

}(jQuery)); 