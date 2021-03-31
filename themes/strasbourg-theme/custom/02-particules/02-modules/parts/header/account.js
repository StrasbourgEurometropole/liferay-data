(function ($) {
    

    $(document).ready(function(){
        $('[name=trigger-account-menu]').each(function(){
            this.addEventListener("click", function (e) {
                $(this).next('#account-menu').toggle();
            })
        
            $(this).parents('.seu-nav-account').on('mouseenter', function(){
                $(this).find('#account-menu').show();
            })
            .on('mouseleave', function(){
                $(this).find('#account-menu').hide();
            })
        })
    });

}(jQuery)); 