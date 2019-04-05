if (typeof isTouchDevice == 'undefined'){
    function isTouchDevice() {
        return 'ontouchstart' in document.documentElement;
    }
}


function thAnimParse(el){
    if (navigator.userAgent.match(/trident/gi) || navigator.appName == 'Microsoft Internet Explorer'){
        return false;
    }
    if(!el){
        el = document;
    }
    if(!isTouchDevice()){

        $('[data-anim]',el).each(function(){
            $(this).attr('data-anim-hide',true);
            new Waypoint({
                element: this,
                handler: function(direction) {
                    if(direction == 'up'){
                        $(this.element).attr('data-anim-hide',true);
                    }else{
                        $(this.element).attr('data-anim-hide',false);
                    }
                },
                offset: '75%'
            });
        });
    }
    return false;
}

thAnimParse();