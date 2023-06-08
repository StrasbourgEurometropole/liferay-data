var thVheight = {

    height: 0,

    init: function(){
        $(window).on('resize',thVheight.update);
        thVheight.update();
    },
    update: function(){
        if(thVheight.detectChange()){
            $('[data-vheight]').each(function(){
                var vheight = this.getAttribute('data-vheight');
                var vheightPercent;
                var vheightMoin = 0;
                if(vheight.match(/\ \-\ /)){
                    var splitted = vheight.split(' - ');
                    vheightPercent = parseInt(splitted[0]);
                    vheightMoin = parseInt(splitted[1]);
                }else{
                    vheightPercent = parseInt(vheight);
                }
                var newHeight = ((thVheight.height/100)*vheightPercent) - vheightMoin;

                $(this).height(newHeight);
            });
        }
    },
    detectChange: function(){

        var newH = document.body.clientHeight;

        if((newH - thVheight.height) > 150 || (thVheight.height - newH) > 150){

            thVheight.height = newH;

            return true;
        }

        return false;
    }

};
thVheight.init();