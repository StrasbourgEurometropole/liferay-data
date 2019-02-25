var ThuriaStickyScrollableEL = document;
// Thuria Sticky
(function ( $ ) {

    var instances = [];

    var defaultSettings = {
        top: 0,
        bottom: 0,
        scrollWindow: ThuriaStickyScrollableEL,
    };

    var generateInstance = function(el){

        instances.push({
            settings:{},
            parent:$(el).parent(),
            el:el,
            $el:$(el),
            offset:$(el).offset(),
            state:0,
            isStart:false
        });

        $(el).attr('data-instance',instances.length-1);
        return instances.length-1;
    };

    var getInstance = function(el){
        var instanceId = el.getAttribute('data-instance');
        if(instanceId || instanceId === 0 || instanceId === "0"){
            return instanceId;
        }else{
            return generateInstance(el);
        }
    }

    var updateParent = function(i){
        instances[i].parent.$el = instances[i].$el.parent();
        instances[i].parent.offset = instances[i].parent.$el.offset();
        instances[i].parent.width = instances[i].parent.$el.width();
        instances[i].parent.height = instances[i].parent.$el.outerHeight();
    };

    var handleScrollEvent = function(e){
        var st = $(ThuriaStickyScrollableEL).scrollTop();
        $.each(instances,function(i,instance){
            if(st > instance.presCalcTop){
                if(st < instance.parent.offset.top+instance.parent.height-instance.$el.outerHeight()-instance.settings.top-instance.settings.bottom){
                    stick(instance);
                }else{
                    stickBottom(instance);
                }
            }else{
                unstick(instance);
            }
        });

    };

    var stick = function(instance){
        if(instance.state != 1){
            instance.state = 1;

            instance.$el
                .addClass('stick')
                .removeClass('stick-bottom')
                .css({top:instance.settings.top,width:instance.parent.width,bottom:instance.settings.bottom,position:'fixed'});
        }
    };

    var stickBottom = function(instance){
        if(instance.state != 2){
            instance.state = 2;

            instance.$el
                .addClass('stick-bottom')
                .css({top:'auto',bottom:instance.settings.bottom,width:instance.parent.width,position:'absolute'});

        }
    };

    var unstick = function(instance){
        if(instance.state != 0){
            instance.state = 0;
            if(instance.$el){
                instance.$el
                    .removeClass('stick-bottom stick')
                    .css({top:0,bottom:'auto',width:instance.parent.width,position:'absolute'});

            }
        }
    };


    var optionalMethod = {
        update: function(el,options){

            var i = getInstance(el);

            instances[i].settings = $.extend(instances[i].settings,options);

            updateParent(i);

            instances[i].presCalcTop = instances[i].offset.top-instances[i].settings.top;
            //instances[i].presCalcParentBottom = instances[i].parent.offset.top+instances[i].parent.height-document.body.clientHeight;
            //instances[i].presCalcParentBottom = instances[i].parent.offset.top+instances[i].parent.height-instances[i].$el.outerHeight()-instances[i].settings.top+-instances[i].settings.bottom;

            handleScrollEvent();
        },
        stop: function(el){

            var i = getInstance(el);

            unstick(i);
            instances[i].isStart = false;
        }
    };


    $.fn.stick = function( options, params ) {

        $(this).each(function(){

            if(typeof options == 'string'){
                optionalMethod[options](this,params);
            }else{
                if(options != 'stop'){

                    var i = getInstance(this);
                    instances[i].isStart = true;
                    instances[i].settings = $.extend(false,defaultSettings,options);


                    updateParent(i);

                    instances[i].presCalcTop = instances[i].offset.top-instances[i].settings.top;

                    setTimeout(function(){
                        updateParent(i);
                    },500);

                }
            }
        });


        return this;
    };


    $.stickListenScroll = function(newEl) {
        if(newEl && newEl != ThuriaStickyScrollableEL){
            $(ThuriaStickyScrollableEL).off('scroll',handleScrollEvent);
            ThuriaStickyScrollableEL = newEl;
            $(ThuriaStickyScrollableEL).on('scroll',handleScrollEvent);
            setTimeout(handleScrollEvent,100);
        }
    }

    $(ThuriaStickyScrollableEL).on('scroll',handleScrollEvent);
    setTimeout(handleScrollEvent,100);

}( jQuery ));