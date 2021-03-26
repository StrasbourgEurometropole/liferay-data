/*

Changements effectués :
 - ligne 1047 : remplacement de "social_source" par "smag_social_source"

*/

(function($){
    // $(function(){
    //     if($('input[type="date"]').length || $('input[data-type="date"]').length){
    //         $.datepicker.setDefaults( $.datepicker.regional[ "fr" ] );
    //         $('input[type="date"], input[data-type="date"]').datepicker({
    //             onSelect: function(dateText, inst){
    //                 if($(this).parents('form.toValidate').length){ // Trig la validation jquery si besoin
    //                     $(this).valid(); 
    //                 }
    //             }
    //         });
    //     }
    // });
}(jQuery));


(function ($) {
    $('.toCustomSelect:not([multiple]), .form-select:not([multiple])').customSelect();
    $(document).on('ready ajax:reload', function(){
        $('.toCustomSelect:not([multiple]), .form-select:not([multiple])').customSelect();
        // Trigg validation jquery si besoin
        $('.toValidate .toCustomSelect:not([multiple]), .toValidate .form-select:not([multiple])').on('change', function(){
            $(this).valid();
        });
        $('button[type="reset"]').on('click', function(){
            $(this).closest('form').find('select:not([multiple])').val('').trigger('change');
        });
    });
}(jQuery));
(function ($) {
	$(document).ready(function(){
        
        $('select[multiple="multiple"]').each(function(index, select){
            var $select = $(select);
            var placeholder = $select.find('option[disabled]').text();
            var is_required = $select[0].hasAttribute('required');
            $select.selectize({
                placeholder: placeholder,
                plugins: ['remove_button'],
                delimiter: ',',
                persist: false,
                // create: function(input) {   // Permet d'autoriser la création de tag par l'user
                //     return {
                //         value: input,
                //         text: input
                //     }
                // }
                create: false
            });
            

            // Trigg jqueryValidate si besoin
            if($select.parents('form.toValidate').length){
                // $select.rules('add', {
                //     minlength: 1
                // });
                $select.on('change',function(e){
                    $select.valid();
                });
            }

            $('button[type="reset"]').on('click', function(){
                $(this).closest('form').find('select[multiple]').val([]).trigger('change').find('option').removeAttr('selected');
            });
        });    

    }); 
}(jQuery));

(function($){
    $(function(){
        $(document).on('.a-fancy-file .fancyFileTrigger', 'click', function() {
            $(this).closest('.a-fancy-file').find('input[type="file"]').trigger('click');
        })
        $(document).on('.a-fancy-file input', 'change', function() {
            if ($(this)[0].files.length ){
                var value = $(this)[0].files[0].name;
            }else{
                value = "Pas de fichier selectionné";
            }
            $(this).closest('.a-fancy-file').find('.selected-file span').html(value);
        })
        $(document).on('.a-fancy-file .arrow', 'click', function() {
            $(this).closest('.a-fancy-file').find('input[type="file"]').val('').trigger('change');
        })
        $(document).on('.selected-file .arrow', 'click', function() {
            $(this).closest('.a-fancy-file').find('button[type="submit"]').trigger('click');
        });

        $( document ).ajaxComplete(function() {
           if ($('.selected-file').length > 0) {
               $('.a-fancy-file').each(function() {
                   if ($(this).find('.selected-file-link').length > 0) {
                       $(this).find('.fancyFileTrigger').hide();
                       $(this).find('.flexbox .selected-file').hide();

                       $(this).find('.selected-file-link, button[type="submit"]').wrapAll('<div class="wrapper-file-open"></div>');
                       $(this).find('.wrapper-file-open').insertAfter($(this).find('.flexbox .selected-file'));
                   } else {
                       $(this).find('.wrapper-file-open').insertAfter($(this).find('.description'));
                       $(this).find('.fancyFileTrigger', '.flexbox .selected-file').show();
                   }
               })
           }
        });


        // Trigg validation jquery si besoin
        $(document).on('.toValidate .a-fancy-file input', 'change', function() {
            $(this).valid();
        })
    });
}(jQuery));
(function ($) {

    $('.a-range').on('input', function(e){
        var min = e.target.min,
            max = e.target.max,
            val = e.target.value;
        $(e.target).css({
            'backgroundSize': (val - min) * 100 / (max - min) + '% 100%'
        });
        $('.a-range-value').html(val);
    }).trigger('input');

}(jQuery));
(function ($) {

    // /!\ 1 seul par page
    var result = $("#password-strength");
    function checkStrength(password){
        //initial strength
        var strength = 0
        
        if (password.length == 0) {
            result.removeClass()
            return ''
        }
        //if the password length is less than 7, return message.
        if (password.length < 9) {
            result.removeClass()
            result.addClass('normal')
            return 'Normal'
        }
    
        //length is ok, lets continue.
    
        //if length is 8 characters or more, increase strength value
        if (password.length > 9) strength += 1
    
        //if password contains both lower and uppercase characters, increase strength value
        if (password.match(/([a-z].*[A-Z])|([A-Z].*[a-z])/))  strength += 1
    
        //if it has one special character, increase strength value
        if (password.match(/([!,%,&,@,#,$,^,*,?,_,~])/))  strength += 1
    
        //if it has two special characters, increase strength value
        if (password.match(/(.*[!,%,&,@,#,$,^,*,?,_,~].*[!,",%,&,@,#,$,^,*,?,_,~])/)) strength += 1
    
        //now we have calculated strength value, we can return messages
    
        //if value is less than 2
        if (strength < 2) {
            result.removeClass()
            result.addClass('medium')
            return 'Medium'
        } else if (strength == 2 ) {
            result.removeClass()
            result.addClass('strong')
            return 'Strong'
        } else {
            result.removeClass()
            result.addClass('vstrong')
            return 'Very Strong'
        }
    }

    jQuery(document).ready(function($) {    
        // Gestion PAssword Strength
        if ($('.toPasswordStrength').length) {
        
            $('.toPasswordStrength').keyup(function(){
                checkStrength($('.toPasswordStrength').val());
            });  
        }
    });
}(jQuery));

(function($){
    $(function(){
        $('input[type="radio"]').closest('.form__group').addClass('form__group--radio');
        $('input[type="checkbox"]').closest('.form__group').addClass('form__group--checkbox');
    })
}(jQuery));

(function ($) {
	$(function() {
		
		$('.toValidate').each(function(){
			$(this).validate({
				lang: 'fr',
				errorElement: 'span',
				errorPlacement : function(error, element){
					error.appendTo( element.closest('.form__group:not(.form__type-datelist) .form__field') );
				},
				ignore: ':hidden:not([class~=selectized]),:hidden > .selectized, .selectize-control .selectize-input input'
			});
		});

	}); 
}(jQuery));

(function ($) {
    /*
        audio.currentTime = X;

    */

    $(function(){        
        function sectostr(s){return(s-(s%=60))/60+(9<s?':':':0')+s;}
        function playAudio(audio){
            audio.$control.addClass('playing');
            audio.player.play();
            audio.majTime = setInterval(function(){
                var current_time = Math.floor(audio.player.currentTime);
                updateTime(audio, current_time);
            },50);
        }
        function stopAudio(audio){
            audio.$control.removeClass('playing');
            audio.player.pause();
            clearInterval(audio.majTime);
        }
        function updateTime(audio, time){
            var ratio = (time / audio.player.duration) * 100;
            audio.$bar.width(ratio + '%');
            audio.$current_time.html(sectostr(time));
        }
    
        function resetAudio(audio){
            stopAudio(audio);
            audio.$bar.width(0);
            audio.$current_time.html('0:00');
        }
    
        var $player = $('.a-audio-player');
        if($player.length){
            $player.each(function(index, element){
                var player = $(element).find('audio')[0];
                var audio = {
                    player: player,
                    $control: $(element).find('.player-control'),
                    $bar: $(element).find('.player-progress-bar .player-running-bar'),
                    $current_time: $(element).find('.player-time-current'),
                    $total_time: $(element).find('.player-time-total'),
                    $range : $(element).find('input[type="range"]'),
                    majTime : function(){}
                };
    
                audio.$total_time.html('0:00');
                if (audio.player.readyState > 3) {
                    audio.$total_time.html(sectostr(Math.floor(audio.player.duration)));
                }else{
                    audio.player.addEventListener('canplay', function(){       
                        audio.$total_time.html(sectostr(Math.floor(audio.player.duration)));
                    });
                }
    
                audio.player.addEventListener('ended', function(){
                    stopAudio(audio);
                    resetAudio(audio);
                });
    
                audio.$range.on('click', function(){
                    var target_time = Math.floor(($(this)[0].value * audio.player.duration) / 100);
                    audio.player.currentTime = target_time;
                    updateTime(audio, target_time);
                });
                
                audio.$control.on('click', function(){
                    if($(this).hasClass('playing')){
                        stopAudio(audio);
                    }else{
                        playAudio(audio);
                    }
                });
    
            });
        }
    });
}(jQuery));

(function ($) {
    if ($('.m-mediabox__transcription-button').length){
        $('.m-mediabox__transcription-button').on('click', function(){
            $(this).toggleClass('opened');
            $(this).parents('.m-mediabox').find('.m-mediabox__transcription').slideToggle();
        });
    }
}(jQuery));

/*! Tablesaw - v3.0.0-beta.4 - 2016-10-12
* https://github.com/filamentgroup/tablesaw
* Copyright (c) 2016 Filament Group; Licensed MIT */
;(function( win ) {
	
	var $;
	if( 'shoestring' in win ) {
		$ = win.shoestring;
	} else if( 'jQuery' in win ) {
		$ = win.jQuery;
	} else {
		throw new Error( "tablesaw: DOM library not found." );
	}

	// DOM-ready auto-init of plugins.
	// Many plugins bind to an "enhance" event to init themselves on dom ready, or when new markup is inserted into the DOM
	$( function(){  // Pas de RWD
		$('.drupal .a-edito table:not(.unstyled)') // Drupal only RWD
		.addClass('tablesaw tablesaw-stack table-content')
		.attr('data-tablesaw-mode', 'stack')
		.parents('body').trigger('enhance.tablesaw');
	});

})( typeof window !== "undefined" ? window : this );
(function ($) { 
    $(document).ready(function(){   
        // Menu à récupérer en json (ici en dur pour l'exemple)     
        var menu = [
            {
                "name": "Ma collectivité",
                "link": "#1",
                "has_submenu": true,
                "submenu": [
                    {
                        "name": "Page 1.1",
                        "link": "#1.1",
                        "has_submenu": true,
                        "submenu": [
                            {
                                "name": "Page 1.1.1",
                                "link": "#1.1.2",
                                "has_submenu": false												
                            }
                        ]
                    },
                    {
                        "name": "Page 1.2", 
                        "link": "#1.2",
                        "has_submenu": false
                    },
                    {
                        "name": "Page 1.3",
                        "link": "#1.3",
                        "has_submenu": true,
                        "submenu": [
                            {
                                "name": "Page 1.3.1",
                                "link": "#1.3.1",
                                "has_submenu": true,
                                "submenu": [
                                    {
                                        "name": "Page 1.3.1.1",
                                        "link": "#1.3.1.1",
                                        "has_submenu": false												
                                    }
                                ]											
                            },
                            {
                                "name": "Page 1.3.2",
                                "link": "#1.3.2",
                                "has_submenu": true,
                                "submenu": [
                                    {
                                        "name": "Page 1.3.2.1",
                                        "link": "#1.3.2.1",
                                        "has_submenu": false												
                                    }
                                ]											
                            }
                        ]
                    },
                    {
                        "name": "Page 1.4",
                        "link": "#1.4",
                        "has_submenu": false
                    }
                ]
            },
            {
                "name": "Mon pôle & Ma commune associée",
                "link": "#3",
                "has_submenu": false
            },
            {
                "name": "Mes services & démarches",
                "link": "#4",
                "has_submenu": false
            },
            {
                "name": "Mes communautés",
                "link": "#5",
                "has_submenu": false
            }
        ];

        blm = {
            $module: $('#menu--bottomless'),
            tpl_li: '<li><a href="##link##" class="menu-item">##name##</a>##submenu##</li>',
            tpl_back: '<li><button class="menu-arrow menu-back"><span class="flexbox"><div></div><span class="menu-item">##parent_name##</span></span></button></li>',
            tpl_sub: '<ul>##submenu##</ul>',
            generated: ''
        }; 

        /**
         * @name getSubmenu
         * @description Retourne le sous menu complet (string)
         * @param {Object} submenu 
         * @param {String} parent_name 
         * @return {string} Sous menu
         */
        function getSubmenu(submenu, parent_name){ // Initialise un sous menu
            submenu_tpl = "";
            submenu_tpl = '<button class="menu-arrow"><span class="flexbox"><div></div></span></button><div class="sub-overflow-container bottomless-overflow"><ul class="submenu">'; // Lvl 2
            li_back = blm.tpl_back;
            li_back = li_back.replace('##parent_name##', parent_name);
            submenu_tpl += li_back;
            submenu.forEach(function(subentry) {
                submenu_tpl += buildSubentriesRecursively(subentry);
            }, this);
            submenu_tpl += '</ul></div>';
            return submenu_tpl;
        }

        /**
         * @name buildSubentriesRecursively
         * @description Construit une entrée du menu, si présence d'un sous menu, on refait appel à getSubmenu()
         * @param {Object} subentry 
         * @return {String} Une entrée de menu
         */
        function buildSubentriesRecursively(subentry){ // Construit une sous entrée
            var subentry_tpl = blm.tpl_li;
            subentry_tpl = subentry_tpl.replace('##name##', subentry.name).replace('##link##', subentry.link);
            if(subentry.has_submenu){
                subentry_tpl = subentry_tpl.replace('##submenu##', getSubmenu(subentry.submenu, subentry.name));
            }else{
                subentry_tpl = subentry_tpl.replace('##submenu##', '');
            }
            return subentry_tpl;
        }

        // 1. INIT

        blm.$module.addClass('overflow-container bottomless-overflow focus');
        blm.generated = '<ul class="unstyled menu"><li><div class="menu-item">Menu</div></li>';

        menu.forEach(function(entry) { // Pour chaque entrée de niveau 1
            var entry_tpl = blm.tpl_li;
            entry_tpl = entry_tpl.replace('##name##', entry.name).replace('##link##', entry.link);
            if(entry.has_submenu){
                entry_tpl = entry_tpl.replace('##submenu##', getSubmenu(entry.submenu, entry.name));
            }else{
                entry_tpl = entry_tpl.replace('##submenu##', '');
            }
            blm.generated += entry_tpl;
        }, this);
        blm.generated += '</ul>';
        blm.$module.append(blm.generated);


        // 2. ECOUTEURS
        
        // Ecouteur revenir au niveau du dessus
        blm.$module.find('.menu-back').on('click', function(){
            $(this).closest('.sub-overflow-container.opened').removeClass('opened focus');
            $(this).closest('.parent-active').addClass('focus').removeClass('parent-active');
        });

        // Ecouteur aller au niveau du dessous
        blm.$module.find('.menu-arrow').on('click', function(){
            $(this).parent().closest('.bottomless-overflow').addClass('parent-active').scrollTop(0).removeClass('focus');
            $(this).next('.sub-overflow-container').addClass('opened focus');
        });

    });
}(jQuery));
(function($) {
    $(window).on('load', function() {
        if ($('.hp-agenda').length) {
            var offsetTopInCSS = 50;
            var trigger = $('.hp-agenda').offset().top - offsetTopInCSS;
            var offset = trigger - (windowHeight * 0.8);
            var duration = windowHeight * 1.5;

            var banner_title_controller = new ScrollMagic.Controller();
            var banner_title_scene = new ScrollMagic.Scene({
                    offset: offset,
                    duration: duration
                })
                .setTween($('.hp-agenda'), {
                    y: -50
                })
                .addTo(banner_title_controller);
        }
    })
}(jQuery));
(function ($) {
  $(function () {
	if ($('.slider-agenda').length) {

		$('.slider-agenda').each(function (index, element) {

			// Initialisation des variables globales et éléments de dom
			var s = {};
			s.$root = $(this);
			s.name = 'slider-agenda';
			s.main = {};
			s.main.name = s.name + '-main';
			s.main.$root = s.$root.find('.'+s.main.name);
			s.main.$slider = s.main.$root.find('.'+s.main.name + '__slider');
			s.main.$slider_item = s.main.$slider.find('.'+s.main.name + '__slider-item');
			// s.hasSpeClass = s.$root.hasClass('spe');    @extend du slider pour un autre slider spé

			if (s.main.$slider_item.length > 1) { // S'il ny a qu'une slide on fait rien

				/* Slider main : configuration
				============================= */


					// Ajout d'un data-index sur tous les <li>
					s.main.$slider_item.each(function (index, element) {
						$(this).attr('data-index', index);
					});

					s.main.can_animate = true;  // Ne pas toucher
					s.main = $.extend(s.main, {
						// Configuration owl, voir doc en ligne https://owlcarousel2.github.io/OwlCarousel2/docs/api-options.html
						conf: {
							animateOut: false, // customOut,  false ou classe de animate.css
							animateIn: false, // customIn, false ou classe de animate.css
							items: 1,
							nav: false,
							loop: true,
							smartSpeed: 500,
							mouseDrag: false,
							center: true
						},

						/*
							@extend du slider pour un autre slider spé, exemple de condition dans la configuration des sliders
						conf: function(){
							var conf = {
								animateOut: 'customOut', // customOut,  false ou classe de animate.css
								animateIn: 'customIn', // customIn, false ou classe de animate.css
								items: 1,
								nav: false,
								loop: true,
								smartSpeed: 500,
								mouseDrag: false
							}
							if(s.hasSpeClass){
								return $.extend(conf, {
									"items": 3
								})
							}else{
								return conf;
							}
						}
						*/

						// Arrows
						arrow: {
							has_arrow: true,
							$prev: s.main.$root.find('.'+s.main.name + '__arrow--prev'),
							$next: s.main.$root.find('.'+s.main.name + '__arrow--next')
						},

						// Pagers
						pager: {
							has_pager: false,
							$current: s.main.$root.find('.'+s.main.name + '__pager--current'),
							$total: s.main.$root.find('.'+s.main.name + '__pager--total'),
							$prev: s.main.$root.find('.'+s.main.name + '__pager--prev'),
							$next: s.main.$root.find('.'+s.main.name + '__pager--next')
						},

						// Dots
						dot: {
							has_dot: false,
							$dots: s.main.$root.find('.'+s.main.name + '__dots'),
							$dot: s.main.$root.find('.'+s.main.name + '__dot').remove()
						},

						// Autoplay /!\ need loop à true
						autoplay: {
							has_autoplay: false,
							$playpause: s.main.$root.find('.'+s.main.name + '__playpause'),
							start_autoplay: false,
							interval: '', // Ne pas modifier
							timeout: 2000
						},

						// Texte dynamique des slides
						text: {
							has_text: false,
							$root: s.main.$root.find('.'+s.main.name + '__text'),
							infos: [{
								data: "data-title",
								$selector: s.main.$root.find('.'+s.main.name + '__title'),
								type: "text"
							}, {
								data: "data-description",
								$selector: s.main.$root.find('.'+s.main.name + '__description'),
								type: "text"
							}, {
								data: "data-link",
								$selector: s.main.$root.find('.'+s.main.name + '__link'),
								type: "href"
								}, {
								data: "data-link-text",
								$selector: s.main.$root.find('.'+s.main.name + '__link'),
								type: "text"
							}
						]
						},

						// Animation
						animation: {
							css_custom: false,
							timeout: 500  // Temps de l'animation CSS, need en JS pour setTimeout sur la maj du texte
						}
					});

				//


				/* SlideNpop: configuration
				============================= */
					s.slideNpop = {
						has_slideNpop: false,
						can_animate: true,  // Ne pas toucher
						slide_slider_type: 'grid', // same, new, grid
						name: s.name + '-slideNpop',
						$slider_pop_close: $('<button class="'+s.main.name+'__close"></button>'),
						conf: { // Utile qu'avec le type "new"
							items: 1,
							margin: 10,
							nav: false,
							smartSpeed: 500,
							mouseDrag: true,
							center: true,
							loop: true,
							slideBy: 2
						}
					};


					// Event mode grid
					s.$root.on('slideNpop:grid:open', function (e, datas) {
						// Quand le slider slideNpop s'ouvre après un clic sur un item de la grid
					});

				// 

				/* Thumbnail : configuration
				============================= */
					s.thumbnail = {
						has_thumbnail: false,
						name: s.name + '-thumbnail',
						can_animate: true, // Ne pas toucher
						conf : {
							items: 3,
							margin: 20,
							nav: false,
							smartSpeed: 500,
							mouseDrag: true,
							center: true,
							loop: true,
							slideBy: 2
						}
					};

				// 

				/* Events 
				============================= */
				
					s.$root.on('sliderfull:domready', function (e) {
						// Les DOM des sliders sont prêt, c'est le bon endroit pour attacher des events
					});

				//

				/* Initialisation
				============================= */
				
					masterSliderFull(s);
				//
			}
		});
	}
  });
}(jQuery));


(function ($) {
  $(function () {
	if ($('.slider-breves').length) {

		$('.slider-breves').each(function (index, element) {

			// Initialisation des variables globales et éléments de dom
			var s = {};
			s.$root = $(this);
			s.name = 'slider-breves';
			s.main = {};
			s.main.name = s.name + '-main';
			s.main.$root = s.$root.find('.'+s.main.name);
			s.main.$slider = s.main.$root.find('.'+s.main.name + '__slider');
			s.main.$slider_item = s.main.$slider.find('.'+s.main.name + '__slider-item');
			// s.hasSpeClass = s.$root.hasClass('spe');    @extend du slider pour un autre slider spé

			if (s.main.$slider_item.length > 1) { // S'il ny a qu'une slide on fait rien

				/* Slider main : configuration
				============================= */


					// Ajout d'un data-index sur tous les <li>
					s.main.$slider_item.each(function (index, element) {
						$(this).attr('data-index', index);
					});

					s.main.can_animate = true;  // Ne pas toucher
					s.main = $.extend(s.main, {
						// Configuration owl, voir doc en ligne https://owlcarousel2.github.io/OwlCarousel2/docs/api-options.html
						conf: {
							animateOut: false, // customOut,  false ou classe de animate.css
							animateIn: false, // customIn, false ou classe de animate.css
							items: 1,
							nav: false,
							loop: false,
							smartSpeed: 500,
							mouseDrag: false
						},

						/*
							@extend du slider pour un autre slider spé, exemple de condition dans la configuration des sliders
						conf: function(){
							var conf = {
								animateOut: 'customOut', // customOut,  false ou classe de animate.css
								animateIn: 'customIn', // customIn, false ou classe de animate.css
								items: 1,
								nav: false,
								loop: true,
								smartSpeed: 500,
								mouseDrag: false
							}
							if(s.hasSpeClass){
								return $.extend(conf, {
									"items": 3
								})
							}else{
								return conf;
							}
						}
						*/

						// Arrows
						arrow: {
							has_arrow: false,
							$prev: s.main.$root.find('.'+s.main.name + '__arrow--prev'),
							$next: s.main.$root.find('.'+s.main.name + '__arrow--next')
						},

						// Pagers
						pager: {
							has_pager: false,
							$current: s.main.$root.find('.'+s.main.name + '__pager--current'),
							$total: s.main.$root.find('.'+s.main.name + '__pager--total'),
							$prev: s.main.$root.find('.'+s.main.name + '__pager--prev'),
							$next: s.main.$root.find('.'+s.main.name + '__pager--next')
						},

						// Dots
						dot: {
							has_dot: true,
							$dots: s.main.$root.find('.'+s.main.name + '__dots'),
							$dot: s.main.$root.find('.'+s.main.name + '__dot').remove()
						},

						// Autoplay /!\ need loop à true
						autoplay: {
							has_autoplay: false,
							$playpause: s.main.$root.find('.'+s.main.name + '__playpause'),
							start_autoplay: false,
							interval: '', // Ne pas modifier
							timeout: 2000
						},

						// Texte dynamique des slides
						text: {
							has_text: false,
							$root: s.main.$root.find('.'+s.main.name + '__text'),
							infos: [{
								data: "data-title",
								$selector: s.main.$root.find('.'+s.main.name + '__title'),
								type: "text"
							}, {
								data: "data-description",
								$selector: s.main.$root.find('.'+s.main.name + '__description'),
								type: "text"
							}, {
								data: "data-link",
								$selector: s.main.$root.find('.'+s.main.name + '__link'),
								type: "href"
								}, {
								data: "data-link-text",
								$selector: s.main.$root.find('.'+s.main.name + '__link'),
								type: "text"
							}
						]
						},

						// Animation
						animation: {
							css_custom: false,
							timeout: 500  // Temps de l'animation CSS, need en JS pour setTimeout sur la maj du texte
						}
					});

				//


				/* SlideNpop: configuration
				============================= */
					s.slideNpop = {
						has_slideNpop: false,
						can_animate: true,  // Ne pas toucher
						slide_slider_type: 'grid', // same, new, grid
						name: s.name + '-slideNpop',
						$slider_pop_close: $('<button class="'+s.main.name+'__close"></button>'),
						conf: { // Utile qu'avec le type "new"
							items: 1,
							margin: 10,
							nav: false,
							smartSpeed: 500,
							mouseDrag: true,
							center: true,
							loop: true,
							slideBy: 2
						}
					};


					// Event mode grid
					s.$root.on('slideNpop:grid:open', function (e, datas) {
						// Quand le slider slideNpop s'ouvre après un clic sur un item de la grid
					});

				// 

				/* Thumbnail : configuration
				============================= */
					s.thumbnail = {
						has_thumbnail: false,
						name: s.name + '-thumbnail',
						can_animate: true, // Ne pas toucher
						conf : {
							items: 3,
							margin: 20,
							nav: false,
							smartSpeed: 500,
							mouseDrag: true,
							center: true,
							loop: true,
							slideBy: 2
						}
					};

				// 

				/* Events 
				============================= */
				
					s.$root.on('sliderfull:domready', function (e) {
						// Les DOM des sliders sont prêt, c'est le bon endroit pour attacher des events
					});

				//

				/* Initialisation
				============================= */
				
					masterSliderFull(s);
				//
			}
		});
	}
  });
}(jQuery));

(function ($) {
    // $('.hp-diapo').css({
    //     position: "relative",
    //     top: "50px"
    // })
    // $(function(){
    //     var offsetTopInCSS = 70;
    //     var trigger = $('.hp-diapo').offset().top;
    //     var offset = trigger - (windowHeight * 0.8);
    //     var duration = windowHeight;
    //     var offset_x = - windowWidth / 2;

    //     var banner_title_controller = new ScrollMagic.Controller();
    //     var banner_title_scene = new ScrollMagic.Scene({
    //         offset: offset, 
    //         duration: duration
    //     })
    //     .setTween($('.hp-diapo'),2, {
    //         top: "0px"
    //     })
    //     .addTo(banner_title_controller);
    // })
}(jQuery));
// (function ($) {
//     var $element = $('.hp-kiosque');
//     $element.css({
//         position: "relative",
//         top: "-30px"
//     })
//     $(function(){
//         var offsetTopInCSS = 60;
//         var trigger = $element.offset().top + 150;
//         var offset = trigger - (windowHeight * 0.8);
//         var duration = windowHeight /1.5;

//         var banner_title_controller = new ScrollMagic.Controller();
//         var banner_title_scene = new ScrollMagic.Scene({
//             offset: trigger, 
//             duration: windowHeight
//         })
//         .setTween($element,2, {
//             y: 30
//         })
//         .addTo(banner_title_controller);
//     })
// }(jQuery));
// (function ($) {
//     var $element = $('.hp-rencontres__card').eq(1);
//     $element.css({
//         position: "relative",
//         top: "50px"
//     })
//     $(function(){
//         var offsetTopInCSS = 60;
//         var trigger = $element.offset().top - 300;
//         var offset = trigger - (windowHeight * 0.8);
//         var duration = windowHeight /1.5;

//         var banner_title_controller = new ScrollMagic.Controller();
//         var banner_title_scene = new ScrollMagic.Scene({
//             offset: trigger, 
//             duration: windowHeight / 2
//         })
//         .setTween($element,2, {
//             y: -100
//         })
//         .addTo(banner_title_controller);
//     })

//     var $element2 = $('.hp-rencontres__card').eq(0);
//     $element2.css({
//         position: "relative",
//         top: "50px"
//     })
//     $(function(){
//         var offsetTopInCSS = 60;
//         var trigger = $element2.offset().top + 150;
//         var offset = trigger - (windowHeight * 0.8);
//         var duration = windowHeight /1.5;

//         var banner_title_controller = new ScrollMagic.Controller();
//         var banner_title_scene = new ScrollMagic.Scene({
//             offset: trigger, 
//             duration: windowHeight
//         })
//         .setTween($element2,2, {
//             top: "0px"
//         })
//         .addTo(banner_title_controller);
//     })
// }(jQuery));
(function ($) {
    /**
     * MegaSlider
     * 
     * @description - Affiche un slider comportant plusieurs éléments par pages, triable par catégorie. Le slider est construit automatiquement à partir d'un flux de donnée en JS au format simple:
     *  sources = [
     *  {
     *      "category": "macatégorie",
     *      "info en tout genre": "moninfo",
     *      etc...
     *  },
     * etc...
     * ]
     * 
     * A besoin pour fonctionner:
     *      - un objet de configuration (ici une et social)
     *      - un flux de donnée à afficher, stocké dans un objet JS (exemple ici une_source et social_source stockés dans index.html)
     *      - L'architecture html du slider
     *      - Un lot de template pour chaque catégorie d'item présent dans le slider, contenant des placeholders ##infosàremplacer## pour chaque entrée du flux de donnée (ici stockés dans index.html)
     *
     * @Lancement:
     *      - lancer d'abord getSources(monObjetSource, monObjetDeConfiguration); (à ne faire qu'une fois)
     *      - appeler megaSlider(monObjetDeConfiguration, maCategorie);
     *      - Ajouter les évènements au click sur les filtres de catégorie (optionnel)
     */


    /**
     * Configuration object 
     */
    var social = {
        can_animate: true,
        $slider: $('.smag-slider-social-container .smag-slider'),
        $prev: $('.smag-slider-social-container .smag-owl-prev'),
        $next: $('.smag-slider-social-container .smag-owl-next'),
        $pages: $('.smag-slider-social-container .smag-page'),
        pages_class: 'smag-page',
        hasPicture_class: 'smag-has-picture',
        hasVille_class: 'smag-has-ville',
        is_Big_Class: 'smag-big',
        $items: $('.smag-slider-social-container .smag-item'),
        $filters: $('.smag-social-filter'),
        $template_twitter: $('#smag-social-templates .smag-item'),
        $template_facebook: $('#smag-social-templates .smag-item'),
        $template_instagram: $('#smag-social-templates .smag-item'),
        $template_dailymotion: $('#smag-social-templates .smag-item'),
        $loader: $('#smag-social-loader')
    };


    $(document).ready(function(){
        if($('#smag-socials').length){
            // Init slider Social
            smag_getSources(smag_social_source, social);
            smag_megaSlider(social, 'tous');


            // Gestion des filtres slider Social
            social.$filters.on('click', function(e){
                if(social.can_animate){
                    social.can_animate = false;
                    var $filter = $(this),
                        category = $filter.attr('data-category');
                    social.$filters.removeClass('smag-actif');
                    $filter.addClass('smag-actif');
                    social.$slider.addClass('animate-out');
                    setTimeout(function(){
                        smag_megaSlider(social, category);
                        social.$slider.removeClass('animate-out');
                        social.can_animate = true;
                    }, 800); 
                }
            }); 

            // Reload des slider au resize pour le RWD
            $(document).on('environment:changed', function(){
                smag_megaSlider(social, 'tous');
            });
        }
    });
    $('.smag-socials .smag-slider').addClass('animate-out');
    var active = false;
    $(window).scroll(function(){
        if(!active){
            if($('.smag-socials').attr('data-animation-state') == "active"){
                $('.smag-socials .smag-slider').removeClass('animate-out');
                active = true;
            }
        }
    })
}(jQuery));
var test = '';
(function ($) {
  $(function () {
	if ($('.slider-une').length) {

		$('.slider-une').each(function (index, element) {

			// Initialisation des variables globales et éléments de dom
			var s = {};
			s.$root = $(this);
			s.name = 'slider-une';
			s.main = {};
			s.main.name = s.name + '-main';
			s.main.$root = s.$root.find('.'+s.main.name);
			s.main.$slider = s.main.$root.find('.'+s.main.name + '__slider');
			s.main.$slider_item = s.main.$slider.find('.'+s.main.name + '__slider-item');
			// s.hasSpeClass = s.$root.hasClass('spe');    @extend du slider pour un autre slider spé

			if (s.main.$slider_item.length > 1) { // S'il ny a qu'une slide on fait rien

				/* Slider main : configuration
				============================= */


					// Ajout d'un data-index sur tous les <li>
					s.main.$slider_item.each(function (index, element) {
						$(this).attr('data-index', index);
					});

					s.main.can_animate = true;  // Ne pas toucher
					s.main = $.extend(s.main, {
						// Configuration owl, voir doc en ligne https://owlcarousel2.github.io/OwlCarousel2/docs/api-options.html
						conf: {
							animateOut: 'customOut', // customOut,  false ou classe de animate.css
							animateIn: 'customIn', // customIn, false ou classe de animate.css
							items: 1,
							nav: false,
							loop: false,
							smartSpeed: 250,
							mouseDrag: false
						},

						/*
							@extend du slider pour un autre slider spé, exemple de condition dans la configuration des sliders
						conf: function(){
							var conf = {
								animateOut: 'customOut', // customOut,  false ou classe de animate.css
								animateIn: 'customIn', // customIn, false ou classe de animate.css
								items: 1,
								nav: false,
								loop: true,
								smartSpeed: 500,
								mouseDrag: false
							}
							if(s.hasSpeClass){
								return $.extend(conf, {
									"items": 3
								})
							}else{
								return conf;
							}
						}
						*/

						// Arrows
						arrow: {
							has_arrow: false,
							$prev: s.main.$root.find('.'+s.main.name + '__arrow--prev'),
							$next: s.main.$root.find('.'+s.main.name + '__arrow--next')
						},

						// Pagers
						pager: {
							has_pager: false,
							$current: s.main.$root.find('.'+s.main.name + '__pager--current'),
							$total: s.main.$root.find('.'+s.main.name + '__pager--total'),
							$prev: s.main.$root.find('.'+s.main.name + '__pager--prev'),
							$next: s.main.$root.find('.'+s.main.name + '__pager--next')
						},

						// Dots
						dot: {
							has_dot: false,
							$dots: s.main.$root.find('.'+s.main.name + '__dots'),
							$dot: s.main.$root.find('.'+s.main.name + '__dot').remove()
						},

						// Autoplay /!\ need loop à true
						autoplay: {
							has_autoplay: false,
							$playpause: s.main.$root.find('.'+s.main.name + '__playpause'),
							start_autoplay: false,
							interval: '', // Ne pas modifier
							timeout: 2000
						},

						// Texte dynamique des slides
						text: {
							has_text: true,
							$root: s.main.$root.find('.'+s.main.name + '__text'),
							infos: [{
								data: "data-title",
								$selector: s.main.$root.find('.'+s.main.name + '__title'),
								type: "text"
							}, {
								data: "data-description",
								$selector: s.main.$root.find('.'+s.main.name + '__description'),
								type: "text"
							}, {
								data: "data-link",
								$selector: s.main.$root.find('.'+s.main.name + '__link'),
								type: "href"
							}
						]
						},

						// Animation
						animation: {
							css_custom: true,
							timeout: 125  // Temps de l'animation CSS, need en JS pour setTimeout sur la maj du texte
						}
					});

				//


				/* SlideNpop: configuration
				============================= */
					s.slideNpop = {
						has_slideNpop: false,
						can_animate: true,  // Ne pas toucher
						slide_slider_type: 'grid', // same, new, grid
						name: s.name + '-slideNpop',
						$slider_pop_close: $('<button class="'+s.main.name+'__close"></button>'),
						conf: { // Utile qu'avec le type "new"
							items: 1,
							margin: 10,
							nav: false,
							smartSpeed: 500,
							mouseDrag: true,
							center: true,
							loop: true,
							slideBy: 2
						}
					};


					// Event mode grid
					s.$root.on('slideNpop:grid:open', function (e, datas) {
						// Quand le slider slideNpop s'ouvre après un clic sur un item de la grid
					});

				// 

				/* Thumbnail : configuration
				============================= */
					s.thumbnail = {
						has_thumbnail: true,
						name: s.name + '-thumbnail',
						can_animate: true, // Ne pas toucher
						conf : {
							items: 1,
							margin: 20,
							nav: false,
							smartSpeed: 250,
							mouseDrag: true,
							center: false, 
							loop: false,
							slideBy: 1,
							responsive: {
								0: {
									items: 1
								},
								768: {
									items: 2
								},
								1280: {
									items: 4,
									center: true, //Laisser a true sur ce slider sinon ça foire grave
									fixThumbnail: true // Fix pour le slider à la une dont les thumbnails ne sont pas correctement placés quand il y a plus de 5 items
								}
							}
						}
					};

				// 

				/* Events 
				============================= */
				
					s.$root.on('sliderfull:domready', function (e) {
						
    
						$('.slider-une-thumbnail').on('touchmove', function(e){
							e.preventDefault();
						});
						// Les DOM des sliders sont prêt, c'est le bon endroit pour attacher des events
						s.$root.find('.slider-une-main__slide').html(''); // Supprime le contenu des slide main qui servent uniquement à être dupliquée dans le slider thumbnail
						// s.thumbnail.$slider.on('translate.owl.carousel', function(){
						// 	console.log('transité le slider thumb');
						// 	$(this).find('.owl-item.active');
						// });
						$(this).find('.owl-item.active').removeClass('current').eq(0).addClass('current');
						dot();
						// s.thumbnail.$slider.on('translated.owl.carousel', function(e){
						// 	var index = getRealCurrentIndex(e);
						// 	setClassToCurrentSlide($(this), index);						
						// });
						// function setClassToCurrentSlide($slider, index){
						// 	$slider.find('.owl-item').removeClass('current').find('[data-index="'+index+'"]').addClass('current');
						// 	dot();
						// }
					});

				//

				/* Initialisation
				============================= */
				
					masterSliderFull(s);
				//
			}
		});
	}
  });
}(jQuery));
(function ($) {
    // $('.hp-video').css({
    //     position: "relative",
    //     top: "-30px"
    // })
    // $(function(){
    //     var offsetTopInCSS = 70;
    //     var trigger = $('.hp-diapo').offset().top;
    //     var offset = trigger - (windowHeight * 0.8);
    //     var duration = windowHeight * 0.8;
    //     var offset_x = - windowWidth / 2;

    //     var banner_title_controller = new ScrollMagic.Controller();
    //     var banner_title_scene = new ScrollMagic.Scene({
    //         offset: offset, 
    //         duration: duration
    //     })
    //     .setTween($('.hp-video'),2, {
    //         y: 30
    //     })
    //     .addTo(banner_title_controller);


    // })
    // Changement de classe du bouton en RWd
    var $btn = $('.hp-video__link');
    $(document).on('environment:changed ready', function(){
        if(environment == 'mobile'){
            $btn.removeClass('transparent-core-dark').addClass('transparent');
        }else{
            $btn.removeClass('transparent').addClass('transparent-core-dark');
        }
    })
}(jQuery));
// Js gérant les animations au scroll
(function ($) {
    $(function(){
        /* ***********************
            GRID DELAYED:
            - extrait VSCODE SCSS: griddelayed 
                    +
            $('grid').attr('data-scroll-animation', 'grid-delayed');
            $('item').each(function(index, element){
                $(this).addClass('delayed-element delayed-element-'+index);
            })

        */
        
        // Récupère tous les éléments possédant le data-scroll-animation, puis leur attribut data-animation-state=active quand ils arrivent au ratio de l'écran
        var triggerHook = 0.6;
        $('[data-scroll-animation]').each(function(index, element){
            if($(element).offset().top < ((window.innerHeight * triggerHook) + $(document).scrollTop()) ){
                $(element).attr('data-animation-state', 'active');
            }else{
                element.controller = new ScrollMagic.Controller();
    
                new ScrollMagic.Scene({
                    triggerElement: element,
                    triggerHook: triggerHook
                })
                .on('enter', function(e){
                    if(e.scrollDirection =="FORWARD" && $(element).attr('data-animation-state') != 'active'){
                        $(element).attr('data-animation-state', 'active');
                    }
                })
                .addTo(element.controller);
            }
        });

        // Exemple utilisation de Tween
        // Requiert : animation.gsap.js & TweenMax.min.js
        
        // var banner_title_controller = new ScrollMagic.Controller();
        // var banner_title_scene = new ScrollMagic.Scene({
        //     offset: 0, 
        //     duration: $('.hp-video').offset().top
        // })
        // .setTween($('.hp-video'), {
        //     top: "0px"
        // })
        // .addTo(banner_title_controller);
        
    });
}(jQuery));


(function ($) {
  $(function () {
	if ($('.slider-full').length) {

		$('.slider-full').each(function (index, element) {

			// Initialisation des variables globales et éléments de dom
			var s = {};
			s.$root = $(this);
			s.name = 'slider-full';
			s.main = {};
			s.main.name = s.name + '-main';
			s.main.$root = s.$root.find('.'+s.main.name);
			s.main.$slider = s.main.$root.find('.'+s.main.name + '__slider');
			s.main.$slider_item = s.main.$slider.find('.'+s.main.name + '__slider-item');
			// s.hasSpeClass = s.$root.hasClass('spe');    @extend du slider pour un autre slider spé

			if (s.main.$slider_item.length > 1) { // S'il ny a qu'une slide on fait rien

				/* Slider main : configuration
				============================= */


					// Ajout d'un data-index sur tous les <li>
					s.main.$slider_item.each(function (index, element) {
						$(this).attr('data-index', index);
					});

					s.main.can_animate = true;  // Ne pas toucher
					s.main = $.extend(s.main, {
						// Configuration owl, voir doc en ligne https://owlcarousel2.github.io/OwlCarousel2/docs/api-options.html
						conf: {
							animateOut: 'customOut', // customOut,  false ou classe de animate.css
							animateIn: 'customIn', // customIn, false ou classe de animate.css
							items: 1,
							nav: false,
							loop: false,
							smartSpeed: 500,
							mouseDrag: false
						},

						/*
							@extend du slider pour un autre slider spé, exemple de condition dans la configuration des sliders
						conf: function(){
							var conf = {
								animateOut: 'customOut', // customOut,  false ou classe de animate.css
								animateIn: 'customIn', // customIn, false ou classe de animate.css
								items: 1,
								nav: false,
								loop: true,
								smartSpeed: 500,
								mouseDrag: false
							}
							if(s.hasSpeClass){
								return $.extend(conf, {
									"items": 3
								})
							}else{
								return conf;
							}
						}
						*/

						// Arrows
						arrow: {
							has_arrow: true,
							$prev: s.main.$root.find('.'+s.main.name + '__arrow--prev'),
							$next: s.main.$root.find('.'+s.main.name + '__arrow--next')
						},

						// Pagers
						pager: {
							has_pager: true,
							$current: s.main.$root.find('.'+s.main.name + '__pager--current'),
							$total: s.main.$root.find('.'+s.main.name + '__pager--total'),
							$prev: s.main.$root.find('.'+s.main.name + '__pager--prev'),
							$next: s.main.$root.find('.'+s.main.name + '__pager--next')
						},

						// Dots
						dot: {
							has_dot: true,
							$dots: s.main.$root.find('.'+s.main.name + '__dots'),
							$dot: s.main.$root.find('.'+s.main.name + '__dot').remove()
						},

						// Autoplay /!\ need loop à true
						autoplay: {
							has_autoplay: true,
							$playpause: s.main.$root.find('.'+s.main.name + '__playpause'),
							start_autoplay: false,
							interval: '', // Ne pas modifier
							timeout: 2000
						},

						// Texte dynamique des slides
						text: {
							has_text: true,
							$root: s.main.$root.find('.'+s.main.name + '__text'),
							infos: [{
								data: "data-title",
								$selector: s.main.$root.find('.'+s.main.name + '__title'),
								type: "text"
							}, {
								data: "data-description",
								$selector: s.main.$root.find('.'+s.main.name + '__description'),
								type: "text"
							}, {
								data: "data-link",
								$selector: s.main.$root.find('.'+s.main.name + '__link'),
								type: "href"
								}, {
								data: "data-link-text",
								$selector: s.main.$root.find('.'+s.main.name + '__link'),
								type: "text"
							}
						]
						},

						// Animation
						animation: {
							css_custom: true,
							timeout: 500  // Temps de l'animation CSS, need en JS pour setTimeout sur la maj du texte
						}
					});

				//


				/* SlideNpop: configuration
				============================= */
					s.slideNpop = {
						has_slideNpop: false,
						can_animate: true,  // Ne pas toucher
						slide_slider_type: 'grid', // same, new, grid
						name: s.name + '-slideNpop',
						$slider_pop_close: $('<button class="'+s.main.name+'__close"></button>'),
						conf: { // Utile qu'avec le type "new"
							items: 1,
							margin: 10,
							nav: false,
							smartSpeed: 500,
							mouseDrag: true,
							center: true,
							loop: true,
							slideBy: 2
						}
					};


					// Event mode grid
					s.$root.on('slideNpop:grid:open', function (e, datas) {
						// Quand le slider slideNpop s'ouvre après un clic sur un item de la grid
					});

				// 

				/* Thumbnail : configuration
				============================= */
					s.thumbnail = {
						has_thumbnail: true,
						name: s.name + '-thumbnail',
						can_animate: true, // Ne pas toucher
						conf : {
							items: 3,
							margin: 20,
							nav: false,
							smartSpeed: 500,
							mouseDrag: true,
							center: true,
							loop: true,
							slideBy: 2
						}
					};

				// 

				/* Events 
				============================= */
				
					s.$root.on('sliderfull:domready', function (e) {
						// Les DOM des sliders sont prêt, c'est le bon endroit pour attacher des events
					});

				//

				/* Initialisation
				============================= */
				
					masterSliderFull(s);
				//
			}
		});
	}
  });
}(jQuery));


(function ($) {

}(jQuery));

(function($) {

	var debugOpen = [68, 69, 66, 85, 71, 79, 80, 69, 78];
	var debugClose = [68, 69, 66, 85, 71, 67, 76, 79, 83, 69];
	var open = 0;
	var close = 0;
	$debug_window = $('.debug-master');

	$debug_window.find('.debug-master__pin').on("click", function() {
		pinDebug();
	});

	var pinDebug = function() {
		$debug_window.toggleClass("debug-pinned");

		if ($debug_window.hasClass("debug-pinned")) {
			localStorage.setItem('debugPinned', true);
		} else {
			localStorage.removeItem('debugPinned');
		}
	};

	$(function() {

		if (localStorage.getItem('debugOpened')) {
			$debug_window.show();
		}

		if (localStorage.getItem('debugPinned')) {
			$debug_window.addClass("debug-pinned");
		}

		$(document).on('keydown', function(e) {
			// Open
			if (e.keyCode === debugOpen[open++]) {
				if (open === debugOpen.length) {
					$debug_window.show();
					localStorage.setItem('debugOpened', true);
					open = 0;
					return false;
				}
			} else {
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
			} else {
				close = 0;
			}
		});
	});


}(jQuery));
(function ($) { 
	$(window).on('load', function(){
		$('.no-js').removeClass('no-js');
	}) //End onLoad
    
}(jQuery));
// JS gérant le comportement lors de la progression du chargement du site
(function ($) {
    var site_progress = 0;
    var $loader = $('#loader');
    $('body').waitForImages({
        finished: function() {
            // 
        },
        each: function(loaded, count, success) { // Incrémente une variable site_progress en fonction du nombre d'image/vidéo du site chargée
            if(site_progress < 100){ // Comportement à chaque actualisation de la variable
                site_progress += 80 / count;
                $(document).trigger('siteload:progress', site_progress)
            }
        },
        waitForAll: true
    });
    $(window).on('load', function(){ // Comportement une fois le site complètement chargé
        $(document).trigger('siteload:loaded');
    });

    $(document)
    .on('siteload:start', function(){
        $loader.css('display', 'flex');
        $loader.find('.loaded').html('0%');
    })
    .on('siteload:progress', function(e, site_progress){
        $loader.find('.loaded').html(Math.floor(site_progress) + '%');
    })
    .on('siteload:loaded', function(e){
        site_progress = 100;
        $loader.find('.loaded').html('100%');
        setTimeout(function(){
            $loader.fadeOut();
        }, 0);
    });

    $(document).trigger('siteload:start');
}(jQuery));

var notifManager;

(function ($) { 
    function manageNotifTimeout($notif, timeout){
        $notif.timeout = setTimeout(function(){
            $notif.fadeOut(function(){
                $notif.remove();
            });
        }, timeout);
    }

    /**
     * @name notifManager
     * @description Permet d'afficher facilement des notifications sous forme de push à l'écran
     * @param {string} state état du message, class ajoutée sur la notif (prévoir au moins error/succes)
     * @param {string} message String pouvant contenir de l'html représentant le message de la notif
     * @param {int} timeout (ms) Optionnel, temps max avant fadeout de la notif
     */
    notifManager = function(state, message, timeout){

        if($('#notif-manager').length === 0){
            $('body').append('<div id="notif-manager"><div>');
        }

        var notif_tpl = '<div class="notif a-edito ##state##"><div class="nm-container"><div class="nm-picto"></div><div class="nm-content rte">##content##</div><div class="nm-close"></div></div></div>';
        var notif = notif_tpl.replace('##state##', state).replace('##content##', $.parseHTML(message)[0].data);
        var $notif = $(notif);
        if(timeout !== undefined){
            manageNotifTimeout($notif, timeout);
        }
        $notif.prependTo('#notif-manager').fadeIn();
    };

    $(function(){
        $('body').on('click', '#notif-manager .nm-close', function(){
            $(this).closest('.notif').fadeOut();
        });
    });

}(jQuery)); 


(function ($) {
    $( function() {
        $( '#skipnavigation ul li a' ).on('focus',  function() {
            $( '#skipnavigation' ).removeClass( 'sr-only' );
        }).on('blur', function() {
            $( '#skipnavigation' ).addClass( 'sr-only' );
        });
    });
}(jQuery));

(function ($) {
    $(function(){

// géré dans le CMS alert_banner.js

    });
 }(jQuery));
(function ($) {
    function manageStickyheader(){
		if($('.smag-front').length){
			if($(window).scrollTop() > 0){
				$('.smag-header').addClass('scrolled');
			}else{
				$('.smag-header').removeClass('scrolled');
				$('.smag-scrolled-search-engine').slideUp();
			}
			if($(window).scrollTop() > ($('#smag-banner').height())){
				$('.smag-header').addClass('scrolled-hp');
			}else{
				$('.smag-header').removeClass('scrolled-hp');
				$('.smag-scrolled-search-engine').slideUp();
			}
		}else{
			$('.smag-header').addClass('scrolled-hp');
			if($(window).scrollTop() > 0){
                $('.smag-header').addClass('scrolled');
            }else{
                $('.smag-header').removeClass('scrolled');
            }
		}
	}
	/*function manageMenuRwd(){
		if(environment != 'desktop'){
			$('#smag-main-menu').appendTo('.smag-menu-rwd-content');
			$('.smag-nav-contact').appendTo('.smag-menu-rwd-content');
		}else{
			$('#smag-main-menu').appendTo('.smag-nav-bottom');
			$('.smag-nav-contact').insertAfter('.smag-nav-account');
		}
	}*/
    function openSubmenu($trigger){
        var text = $trigger.text();
        var $submenu = $trigger.next('.smag-menu-subitem');
        $submenu.find('.smag-container').prepend('<div class="smag-rwd-back"><span class="smag-picto"></span><span class="smag-text">'+text+'</span></div>');
        $submenu.addClass('opened');
        $submenu.on('click', '.smag-title', function(){
            var link = $(this).next('.smag-links');
            if(link.is(':visible')){
                link.slideUp();
            }else{
                $submenu.find('.smag-links').stop().slideUp();
                $(this).next('.smag-links').stop().slideDown();
            }
        });
    }

    function closeSubmenu($trigger){
        var $submenu = $trigger.parents('.smag-menu-subitem.opened');        
        $submenu.off('click');
        $submenu.removeClass('opened');
        setTimeout(function() {
            $trigger.remove();
        }, 500);
    }

    function toggleNoScroll(scroll){
        if(scroll){
            $('html').removeClass('smag-no-scroll');
        }else{
            $('html').addClass('smag-no-scroll');
        }
    }

    $(document).ready(function(){
        $('[name=trigger-account-menu]').on('click', function(){
            $(this).next('#account-menu').toggle();
        })
        
        .parents('.smag-nav-account').on('mouseenter', function(){
            $(this).find('#account-menu').show();
        })
        .on('mouseleave', function(){
            $(this).find('#account-menu').hide();
        })


    
        $('#smag-menu-trigger').on('click', function(){
            if( $(this).is(':checked') ){
                toggleNoScroll(false);
            }else{
                toggleNoScroll(true);
            }
        });
        $('.smag-menu-rwd-overlay').on('click', function(e){
            if(!$(e.target).hasClass('.smag-menu-panel') && !$(e.target).parents('.smag-menu-panel').length){
                $('#smag-menu-trigger').click();
            }
        });

        $('.smag-menu-panel')
        .on('click', '.smag-menu-item', function(e){
            e.preventDefault();
            openSubmenu($(this));
        })
        .on('click', '.smag-rwd-back', function(e){
            e.preventDefault();
            closeSubmenu($(this));
        });


        // Moteur de recherche dépliant
		$('#smag-search-trigger').on('click', function(){
			$('.smag-scrolled-search-engine').stop();
			$('.smag-scrolled-search-engine').slideToggle();
		});
		$('.smag-search-close').on('click', function(){
			$('.smag-scrolled-search-engine').stop();
			$('.smag-scrolled-search-engine').slideUp();
		});
		$('#smag-menu-trigger').on('click', function(){
			$('.smag-header').toggleClass('smag-mmenu');
		})
		// Menu Lang
		$('.smag-lang-list').on('click', function(){
			$(this).toggleClass('smag-open');
		});
		// Big Menu
		function attachBigMenu(){
			if(environment != 'desktop'){
				$('.smag-menu-item-container').off('mouseenter mouseeleave');
			}else{	
				$('.smag-menu-item-container').on('mouseenter', function(){
					$('.smag-menu-subitem').stop().slideUp(300);
					$(this).find('.smag-menu-subitem').stop().slideDown(300);
				}).on('mouseleave', function(){
					$('.smag-menu-subitem').stop().slideUp(300);
					$(this).find('.smag-menu-subitem').stop().slideUp(300);
				});
			}
		}
        attachBigMenu();
        
        $(document).on('environment:changed', function(){
            //manageMenuRwd();
            attachBigMenu();
        });

    });
    $(document).ready(function(){
		manageStickyheader();
		//manageMenuRwd();        
    })

    $(window).on('load', function(){
		manageStickyheader();
		//manageMenuRwd();
		$(window).scroll(function(){
			manageStickyheader();
		});
	}) //End onLoad
    
    

}(jQuery)); 
(function ($) {
	if ($('.class_group_home, body.front').length) {
		$(function() {
				
		});
	};
}(jQuery));


(function ($) {
	$(function() {
		// Changement de breakpoint			
			$(document).on('environment:changed', function(e, env){
				// env.old , env.new
			});


	}); // End onReady  

	$(window).on('load', function(){
		$('.no-js').removeClass('no-js');
	}) //End onLoad
}(jQuery));



