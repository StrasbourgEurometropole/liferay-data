jQuery(function () {
	if (typeof videos != 'undefined' && window.innerWidth <= 768 || jQuery('html').hasClass('mobile')) {
		// Owl carousel
		jQuery('.owl-carousel').owlCarousel({
			items: 1,
			dots: true,
			loop: true
		});
	}
	if (typeof videos != 'undefined' && window.innerWidth > 768 && !jQuery('html').hasClass('mobile') && !jQuery('html').hasClass('tablet')) {
		var currentVideoIndex = 0;
		var playButton = jQuery('.video-play-button');
		var titleElement = jQuery('.video-title span a');
		var cover = jQuery('.cover');
		var nextVideoButton = jQuery('#next-video');
		var previousVideoButton = jQuery('#previous-video');
		var bulletContainer = jQuery('.video-carousel-controls ul');
		var bullets;
		var api;
		var i = 0;

		if (videos.length > 1) {
			for (var i = 0; i < videos.length; i++) {
				bulletContainer.append('<li class="btn"></li>');
			}
			bullets = jQuery('.video-carousel-controls ul li');
		}
		
		nextVideoButton.on('click', function (event) {
			event.preventDefault();
			if (currentVideoIndex + 1 === videos.length) {
				playVideo(videos, 0);
			} else {
				playVideo(videos, currentVideoIndex + 1);
			}
		});
		previousVideoButton.on('click', function (event) {
			event.preventDefault();
			if (currentVideoIndex === 0) {
				playVideo(videos, videos.length - 1);
			} else {
				playVideo(videos, currentVideoIndex - 1);
			}
		});
		if (bullets) {
			bullets.on('click', function (event) {
				event.preventDefault();
				playVideo(videos, jQuery(this).index());
			});
		}

		if (videos.length >= 1) {
			playVideo(videos, 0);
		}
	}
	
	function playVideo(videos, videoIndex) {
		currentVideoIndex = videoIndex;
		var video = videos[videoIndex];

		updateMetaData(video, videoIndex);

		updatePlayer('#featured-video-container', video);

		if (api) {
			api.onReady(function () {
				if (!!api.onPlay) {
					api.onPlay(function () {
						api.mute();
					});
				}
				api.play();
				api.mute();
				api.onEnded(function () {
					if (currentVideoIndex + 1 === videos.length) {
						currentVideoIndex = -1;
					}
					if (currentVideoIndex < videos.length) {
						jQuery('#featured-video-container').html('');
						playVideo(videos, currentVideoIndex + 1);
					}
				});
				api.onPause(function () {
					cover.fadeIn();
					playButton.one('click', function (event) {
						event.preventDefault();
						cover.fadeOut();
						api.play();
					});
					titleElement.one('click', function (event) {
						event.preventDefault();
						cover.fadeOut();
						api.play();
					});
				});
				playButton.one('click', resetAndPlay);
				titleElement.one('click', resetAndPlay);
			});
		}
	}
	
	function resetAndPlay(event) {
		event.preventDefault();
		cover.fadeOut();
		api.unmute();
		api.rewind();
		api.play();
	}

	function updatePlayer (containerSelector, video) {
		var html;
		if (video.source.indexOf('dailymotion.') > -1) {
			html = video.player;
			api = playersApi.dailymotion;
		} else if (video.source.indexOf('youtube.') > -1) {
			html = '<script src="https://www.youtube.com/iframe_api"></script>';
			html += video.player;
			api = playersApi.youtube;
		} else if (video.source.indexOf('vimeo.') > -1) {
			html = video.player;
			api = playersApi.vimeo;
		}
		jQuery(containerSelector).html(html);
	}

	function updateMetaData (video, videoIndex) {
		titleElement.fadeOut(500, function () {
			var shortTitle = video.title;
			if (video.title.length > 73) {
				shortTitle = jQuery.trim(video.title).substring(0, 73).split(" ").slice(0, -1).join(" ") + "...";
			}
			titleElement.text(shortTitle);
			titleElement.fadeIn(500);
		});

		var channelsElement = jQuery('.video-meta .channels');
		var providerElement = jQuery('.video-meta .provider');
		var dateElement = jQuery('.video-meta .date date');
		dateElement.fadeOut(500, function () {
			if (video.publicationDate) {
				dateElement.text(video.publicationDate);
				dateElement.fadeIn(500);
			}
		})
		channelsElement.fadeOut(500, function () {
			if (video.channels) {
				channelsElement.text(video.channels);
				channelsElement.fadeIn(500);
			}
		});
		providerElement.fadeOut(500, function () {
			if (video.provider && video.provider.length > 0) {
				providerElement.text(video.provider);
				providerElement.fadeIn(500);
			}
		});

		if (bullets) {
			bullets.removeClass('active');
			jQuery(bullets[videoIndex]).addClass('active');
		}
	}

});

