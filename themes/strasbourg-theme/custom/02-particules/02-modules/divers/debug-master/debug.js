(function ($) {

        var debugOpen = [68,69,66,85,71,79,80,69,78];
        var debugClose = [68,69,66,85,71,67,76,79,83,69];
        var open = 0;
        var close = 0;
        $debug_window = $('#debug-master');

        $(document).ready(function(){
            if(localStorage.getItem('debugOpened')){
                $debug_window.show();
            }

            $debug_window.find('.close').on('click', function(){
                $debug_window.hide();
            });

            $(document).keydown(function (e) {
                // Open
                if (e.keyCode === debugOpen[open++]) {
                    if (open === debugOpen.length) {
                        $debug_window.show();
                        localStorage.setItem('debugOpened', true);
                        open = 0;
                        return false;
                    }
                }
                else {
                    open = 0;
                }
                // Close
                if (e.keyCode === debugClose[close++]) {
                    if (close === debugClose.length) {
                        $debug_window.hide();
                        localStorage.removeItem('debugOpened');
                        close = 0;
                        return false;
                    }
                }
                else {
                    close = 0;
                }
            });
        });


}(jQuery));

