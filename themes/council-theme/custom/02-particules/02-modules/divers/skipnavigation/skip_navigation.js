

(function ($) {
    $(document).ready( function() {
        $( '#skipnavigation ul li a' ).focus( function() {
            $( '#skipnavigation' ).removeClass( 'sr-only' );
        }).blur( function() {
            $( '#skipnavigation' ).addClass( 'sr-only' );
        });
    });
}(jQuery));
