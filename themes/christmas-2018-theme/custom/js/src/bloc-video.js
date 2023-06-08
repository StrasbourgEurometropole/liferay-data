// Bloc video
$('.mns-bloc-video').each(function() {

    var mask = $('.mns-mask-video',this);
    var vidContainer = $('.mns-embed-container', this);

    var urlVideo = vidContainer.data("urlvideo");
    if(urlVideo.match(/\?/)){
        urlVideo+='&autoplay=1';
    }else{
        urlVideo+='?autoplay=1';
    }
    var iframe  ='<iframe src="' + urlVideo + '" width="1280px"  height="auto"></iframe>';


    $(mask).click(function (e) {
        e.preventDefault();
        vidContainer.append(iframe);

        setTimeout(function(){
            mask.addClass('hide');

            setTimeout(function(){
                mask.remove();
            }, 500);
        }, 200);
    });
});