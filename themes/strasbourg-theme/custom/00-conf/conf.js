/**
 * Initialisation de variables de configuration transverses au site
 */

var breakpoint_small, breakpoint_large, jqueryValidateConf, owl_options_int;
var canScrollMagic = true;

(function ($) {
    breakpoint_small = 767;
    breakpoint_large = 1279;
    jqueryValidateConf = {
        highlight: function(element, errorClass, validClass){
            $(element).parents('.form-entry').addClass(errorClass).removeClass(validClass); 
        },
        unhighlight: function(element, errorClass, validClass){
            $(element).parents('.form-entry').addClass(validClass).removeClass(errorClass); 
        },
        showErrors: function(errorMap, errorList) {
            $(".form-errors-summary").html("Your form contains "
            + this.numberOfInvalids()
            + " errors, see details below.");
            this.defaultShowErrors();
        }
    }
}(jQuery));
