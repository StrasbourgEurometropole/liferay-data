// Bloc video
$('.pro-bloc-video').each(function() {

	var mask = $('.mask-video',this);
	var vidContainer = $('.embed-container', this);

	var urlVideo = vidContainer.data("urlvideo");

	if(urlVideo){
		if(urlVideo.match(/\?/)){
			urlVideo+='&autoplay=1';
		}else{
			urlVideo+='?autoplay=1';
		}
		var iframe  ='<iframe src="' + urlVideo + '" width="1280px"  height="auto"></iframe>';


		$('.pro-btn-video, .btn-ytbe',this).click(function (e) {
			e.preventDefault();
			vidContainer.append(iframe);

			setTimeout(function(){
				mask.addClass('hide');

				setTimeout(function(){
					mask.remove();
				}, 500);
			}, 200);
		});
	}

});