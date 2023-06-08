var th_overlay = {

    selector_overlay: '.overlay',
    selector_overlay_shadow: '.shadow-overlay',

    callbackOpen: [],
    callbackClose: [],

    init: function(){
        th_overlay.parseOverlayShadow($('body'));
        th_overlay.parse($('body'));
    },

    addCallbackOpen: function(callback){
        th_overlay.callbackOpen.push(callback);
    },
    addCallbackClose: function(callback){
        th_overlay.callbackClose.push(callback);
    },

    parseOverlayShadow: function(){
        $(th_overlay.selector_overlay_shadow).on('click',function(e){
            e.preventDefault();
            $(th_overlay.selector_overlay_shadow).removeClass('open');
            $(th_overlay.selector_overlay+'.open').removeClass('open');
        });
    },

    parse: function(el){

        $(th_overlay.selector_overlay).each(function(){

            var overlayId = this.id;
            $('a[href="#'+overlayId+'"]:not(.parsed-overlay):not(.close),.leaflet-popup-content a[href="#'+overlayId+'"]:not(.close), [data-overlay-open="'+overlayId+'"]:not(.parsed-overlay)')
                .addClass('parsed-overlay')
                .on('click',function(e){
                    e.preventDefault();
                    var idOverlay = $(this).attr('href');
                    if(!idOverlay){
                        idOverlay = $(this).attr('data-overlay-open');
                    }else{
                        idOverlay = idOverlay.replace('#','');
                    }
                    th_overlay.open(idOverlay);
                });
            console.log('coucou');

            $('a[href="#'+overlayId+'"].close:not(.parsed-overlay), [data-overlay-close="'+overlayId+'"]:not(.parsed-overlay)')
                .addClass('parsed-overlay')
                .on('click',function(e){
                    var idOverlay = $(this).attr('href');
                    if(!idOverlay){
                        idOverlay = $(this).attr('data-overlay-close');
                    }else{
                        idOverlay = idOverlay.replace('#','');
                    }

                    if(!idOverlay){
                        idOverlay = null;
                    }
                    e.preventDefault();
                    th_overlay.close(idOverlay);
                });
        });
    },

    open: function(overlayId,openShadow,doCallback){
        if(openShadow !== false){
            openShadow = true;
        }
        if(doCallback !== false){
            doCallback = true;
        }

        $('#'+overlayId).addClass('open');

        if(openShadow == true){
            $(th_overlay.selector_overlay_shadow).addClass('open');
        }

        if(doCallback == true){
            $.each(th_overlay.callbackOpen,function(k,callback){
                callback(overlayId);
            });
        }
    },

    close: function(overlayId,closeShadow,doCallback){

        if(closeShadow !== false){
            closeShadow = true;
        }
        if(doCallback !== false){
            doCallback = true;
        }


        if(overlayId){
            $('#'+overlayId).removeClass('open');
            if($(th_overlay.selector_overlay+'.open').length == 0 && closeShadow){
                $(th_overlay.selector_overlay_shadow).removeClass('open');
            }
        }else{
            $(th_overlay.selector_overlay+'.open').removeClass('open');
            if(closeShadow){
                $(th_overlay.selector_overlay_shadow).removeClass('open');
            }
        }

        if(doCallback){
            $.each(th_overlay.callbackClose,function(k,callback){
                callback(overlayId);
            });
        }

    }

};

th_overlay.init();