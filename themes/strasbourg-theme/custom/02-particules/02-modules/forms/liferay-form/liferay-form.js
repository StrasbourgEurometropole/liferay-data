var validateForms = document.getElementsByClassName("ddm-form-success-page");
if(validateForms.length > 0 ){
    $('html,body').animate({scrollTop: validateForms[0].offsetParent.offsetTop + 100}, 'slow');
}