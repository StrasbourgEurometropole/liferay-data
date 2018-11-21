$('.pro-page-carte .pro-group').each(function(){

    $('.pro-item > input',this).on('click',function(){
       $('.pro-switch',this).find('input').prop('checked',true);
    });


    $('.pro-remove-chk',this).on('click',function(){
        $(this).parent().find('input:checked').prop('checked',false);
    });
});