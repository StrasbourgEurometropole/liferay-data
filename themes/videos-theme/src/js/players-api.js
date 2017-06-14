(function(exports) {

	exports.dailymotion = {
		player: undefined,
		url: 'http://www.dailymotion.com',
		ended: false,
		play: function () {
			this.player.contentWindow.postMessage('play', this.url);
		},
		pause: function () {
			this.player.contentWindow.postMessage('pause', this.url);
		},
		mute: function () {
			var self = this;
			self.player.contentWindow.postMessage('volume=0', self.url);
			self.player.contentWindow.postMessage('muted=1', self.url);
		},
		unmute: function () {
			this.player.contentWindow.postMessage('muted=0', this.url);
			this.player.contentWindow.postMessage('volume=0.5', this.url);
		},
		rewind: function () {
			this.player.contentWindow.postMessage('seek=0', this.url);
		},
		onPlay: function (callback) {
			var self = this;
			var onPlay = function (event) {
				if (event.data === 'event=play' && event.origin === self.url) {
					callback();
					window.removeEventListener('message', onPlay);
				}
			}
			window.addEventListener('message', onPlay, false);
		},
		onPause: function (callback) {
			var self = this;
			window.addEventListener('message', function (event) {
				if (event.data === 'event=pause' && event.origin === self.url) {
					callback();
				}
			}, false);
		},
		onEnded: function (callback) {
			var self = this;
			window.addEventListener('message', function (event) {
				if (event.data === 'event=ended' && event.origin === self.url) {
					if (!self.ended) {
						self.ended = true;
						callback();
					}
				}
			}, false);
		},
		onReady: function (callback) {
			var self = this;
			this.player = $('iframe[src^="//www.dailymotion.com"]')[0];
			var onReady = function (event) {
				if ((event.data === 'event=apiready' || event.data === 'event=canplay') && event.origin === self.url) {
					self.ended = false;
					callback();
				}
				if (event.data.indexOf('volumechange') > -1) {
					window.removeEventListener('message', onReady);
				}
			}
			window.addEventListener('message', onReady, false);	
		}
	};


	var firstLoad = true;
	exports.youtube = {
		player: undefined,
		play: function() {
			this.player.playVideo();
		},
		pause: function() {
			this.player.pauseVideo();
		},
		mute: function() {
			this.player.setVolume(0);
		},
		unmute: function () {
			this.player.setVolume(50)
		},
		rewind: function () {
			this.player.seekTo(1, true);
		},
		onReady: function (callback) {
			var self = this;
			if (firstLoad) {
				window.onYouTubeIframeAPIReady = function () {
					self.player = new YT.Player('youtubePlayer', {});
					self.player.addEventListener('onReady', callback);
					firstLoad = false;
				}
			} else {
				self.player = new YT.Player('youtubePlayer', {});
				this.player.addEventListener('onReady', callback);
			}
		},
		onPause: function (callback) {
			this.player.addEventListener('onStateChange', function (event) {
				if (event.data == YT.PlayerState.PAUSED) {
					callback ();
				}
			});
		},
		onEnded: function (callback) {
			this.player.addEventListener('onStateChange', function (event) {
				if (event.data == YT.PlayerState.ENDED) {
					callback();
				}
			});
		}
	};

	exports.vimeo = {
		player: undefined,
		url: 'https://player.vimeo.com',
		play: function () {
			var data = JSON.stringify({
				method: 'play'
			});
			this.player.contentWindow.postMessage(data, this.url);
		},
		pause: function () {
			var data = JSON.stringify({
				method: 'pause'
			});
			this.player.contentWindow.postMessage(data, this.url);
		},
		mute: function () {
			var data = JSON.stringify({
				method: 'setVolume',
				value: 0
			});
			this.player.contentWindow.postMessage(data, this.url);
		},
		unmute: function () {
			var data = JSON.stringify({
				method: 'setVolume',
				value: 0.5
			});
			this.player.contentWindow.postMessage(data, this.url);
		},
		rewind: function () {
			var data = JSON.stringify({
				method: 'seekTo',
				value: 0
			});
			this.player.contentWindow.postMessage(data, this.url);
		},
		onPlay: function (callback) {
			var self = this;
			window.addEventListener('message', function (event) {
				if (event.origin === self.url) {
					var data = JSON.parse(event.data);
					if (data.event === 'play') {
						callback();
					}
				}
			}, false);
		},
		onPause: function (callback) {
			var self = this;
			window.addEventListener('message', function (event) {
				if (event.origin === self.url) {
					var data = JSON.parse(event.data);
					if (data.event === 'pause') {
						callback();
					}
				}
			}, false);
		},
		onEnded: function (callback) {
			var self = this;
			window.addEventListener('message', function (event) {
				if (event.origin === self.url) {
					var data = JSON.parse(event.data);
					if (data.event === 'finish') {
						callback();
					}
				}
			}, false);
		},
		onReady: function (callback) {
			var self = this;
			this.player = $('iframe[src^="' + this .url + '"]')[0];
			var post = function (action, value) {
				var data = { method: action };
				if (value) {
					data.value = value;
				}
				var message = JSON.stringify(data);
				self.player.contentWindow.postMessage(data, self.url);
			}
			var onReady = function (event) {
				if (event.origin === self.url) {
					var data = JSON.parse(event.data);
					if (data.event == 'ready') {
						post('addEventListener', 'play');
						post('addEventListener', 'pause');
						post('addEventListener', 'finish');
						callback();
						window.removeEventListener('message', onReady, false);
					}
				}
			}
			window.addEventListener('message', onReady, false);
		}
	}

})(this['playersApi'] = {});