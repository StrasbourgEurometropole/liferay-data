(function($) {
    $(document).ready(function() {
        if ($('#aroundme').length) {
            var ame = {},
            $ame = $('#aroundme');
            ame.$ame = $('#aroundme'),
            ame.$trigger_side = ame.$ame.find('.side__trigger'),
            ame.$panel_side = ame.$ame.find('#aroundme__side'),
            ame.$trigger_top = ame.$ame.find('.top__trigger'),
            ame.$panel_top = ame.$ame.find('#aroundme__top'),
            ame.$filters = ame.$ame.find("#aroundme__top input[type='checkbox']"),
            ame.$filters_categories = ame.$ame.find("#aroundme__top .categories input[type='checkbox']"),
            ame.$filters_interests = ame.$ame.find("#aroundme__top .interests input[type='checkbox']"),
            ame.$showFavoritesFilter = ame.$ame.find('[name=' + window.aroundMePortletNamespace + 'showFavorites]');

            ame.close_panel_top = function() {
                $ame.removeClass('top-opened');
            },
            ame.open_panel_top = function() {
                $ame.addClass('top-opened');
            },
            ame.close_panel_side = function() {
                $ame.removeClass('side-opened');
            },
            ame.open_panel_side = function() {
                $ame.addClass('side-opened');
            };

            ame.$ui_fullscreen = $('.aroundme__ui--fullscreen'),
            ame.$ui_zoomin = $('.aroundme__ui--zoomin'),
            ame.$ui_zoomout = $('.aroundme__ui--zoomout'),
            ame.$ui_locate = $('.aroundme__ui--locate'),
            ame.$ui_home = $('.aroundme__ui--home'),

            // Ouverture/fermeture Panel
            ame.$trigger_side.on('click', function() {
                if ($ame.hasClass('side-opened')) {
                    ame.close_panel_side();
                } else {
                    ame.open_panel_side();
                }
            });

            ame.$trigger_top.on('click', function() {
                if ($ame.hasClass('top-opened')) {
                    ame.close_panel_top();
                } else {
                    ame.open_panel_top();
                }
            });

            function managePanelRwd(environment) {
                if (environment != 'desktop') { // Si tablette/mobile on ferme le panel de base
                    ame.close_panel_side();
                    ame.close_panel_top();
                } else {
                    ame.open_panel_side();
                    ame.open_panel_top();
                }
            }
            managePanelRwd(environment);
            $(document).on('environment:changed', function(data) {
                managePanelRwd(data.newEnvironment);
            });

            // Carte

            //Création de la carte au centre de strasbourg
            var mymap = L.map('mapid', {
                // crs: L.CRS.EPSG4326, //Commenté car casse l'affichage de la carte
                center: [48.573, 7.752],
                maxBounds: [[48.42, 7.52], [48.72, 7.94]],
                zoom: 13,
                minZoom: 11,
                zoomControl: false,
                attributionControl: false
            });

            // Fonction qui update la liste des markers affichés sur la carte
            var updateList = function() {
                listControl._updateList();
            }

            // Couche gui gère le clustering des points
            var markers = L.markerClusterGroup({
                showCoverageOnHover: false,
                chunkProgress: updateList
            });

            // Ajout de la couche couleur 'gct_fond_de_carte_couleur' à la carte
            var wmsLayer = L.tileLayer.wms('https://adict.strasbourg.eu/mapproxy/service?', {
                layers: 'monstrasbourg'
            }).addTo(mymap);

            // Contrôle correspondant à la liste des markers
            var listControl = new L.Control.ListMarkers({ layer: markers, itemIcon: null, minZoom: 17 });
            mymap.addControl(listControl);

            // Copie de ce contrôle dans la div prévue à cet effet
            $('.filtres--poi').append($('.list-markers .filtres__list'));

            // Création de la popup pour chaque POI
            var poi_infos_to_display = ["visual", "name", "like", "address", "opened", "schedules", "amount", "url", "type"];
            var popupMarkup =
                '<div class="aroundme__infowindow infowindow">' +
                '     <button class="infowindow__close"></button>' +
                '     <div class="infowindow__content">' +
                '         <div class="infowindow__visual"></div>'+
                '         <div class="infowindow__top">' +
                '             <div class="infowindow__title-block"><div class="infowindow__name"></div><div class="infowindow__like"><a class="" href="/like"></a></div></div>' +
                '             <div class="infowindow__address"></div>' +
                '         </div>' +
                '         <div class="infowindow__middle">' +
                '             <div class="infowindow__left">' +
                '                 <div class="infowindow__opened"></div>' +
                '                 <div class="infowindow__schedules"></div>' +
                '             </div>' +
                '             <div class="infowindow__right">' +
                '                 <div class="infowindow__amount"></div>' +
                '             </div>' +
                '         </div>' +
                '         <div class="infowindow__bottom">' +
                '                 <div class="infowindow__type"></div>' +
                '         </div>' +
                '    </div>' +
                '    <div class="infowindow__url"></div>' +
                '</div>';
            var onEachFeature = function(feature, layer) {
                var popupElement = $.parseHTML(popupMarkup);
                if (feature.properties) {
                    var hasOpened = false;
                    var hasAmount = false;
                    poi_infos_to_display.forEach(function(info_to_display) { // Pour chaque infos qu'on est censé avoir dans le poi
                        $(popupElement).find('.infowindow__' + info_to_display).html(''); // On reset le champ dans l'infowindow
                        if (info_to_display in feature.properties && feature.properties[info_to_display] !== '') { // Si cette info est bien renseignée
                            var formated_info = '';
                            if (info_to_display == 'amount') {
                            	var frequentation = '<div class="infowindow__opened">' + Liferay.Language.get(feature.properties[info_to_display]["title"]) + '</div>';
                                frequentation += '<div class="infowindow__frequentation ' + feature.properties[info_to_display]["color"] + '">' + feature.properties[info_to_display]["frequentation"] + '</div>';
                                frequentation += '<div class="crowded-label">' + Liferay.Language.get(feature.properties[info_to_display]["label"]);
                                if (feature.properties[info_to_display]["label"] == "available-spots"){
                                	frequentation += feature.properties[info_to_display]["frequentation"];
                                }
                                frequentation += '</div>';
                                formated_info = frequentation;
                                hasOpened = true;
                                hasAmount = true;
                            } else if (info_to_display == "url") {
                                var newTabAttribute = '';
                                if (window.newTab) {
                                    newTabAttribute = 'target="_blank"';
                                }
                                formated_info = '<a href="' + feature.properties[info_to_display] + '" ' + newTabAttribute + '><span class="flexbox"><span class="btn-text">En savoir plus</span><span class="btn-arrow"></span></span></a>';
                            } else if (info_to_display == "like") {
                                var state = feature.properties[info_to_display]["liked"] ? "liked" : "";
                                formated_info = '<a class="' + state + '" href=' + feature.properties[info_to_display]["href"] + '></a>';
                            } else if (info_to_display =="visual" && !feature.properties.amount) {//on n'affiche pas l'image si c'est un lieu avec des horaires
                                formated_info = '<div class="infowindow__visualImage" style="background-image: url(' + feature.properties[info_to_display] + ');"></div>';
                            } else if(info_to_display =="type") {
                    			var addedFavorite = false;
                    			if (window.userFavorites) {
                    				var i;
                    				for (i = 0; i < window.userFavorites.length; i++) {
                    					if(window.userFavorites[i].typeId == feature.properties[info_to_display] && window.userFavorites[i].entityId == feature.properties["id"]){
                    						addedFavorite = true;
                    						break;
                    					}
                    				} 
                    			}
                    		
                    			var lienFavori = '<a href="#" class="add-favorites';
                    			if(addedFavorite){
                    				lienFavori += ' liked';
                    			}
                    			lienFavori += '" style="display: flex; margin-bottom: 0px;" '
                    				+ 'data-type="' + feature.properties[info_to_display] + '"' 
                    		        + 'data-title="' + feature.properties["name"] + '"' 
                    		        + 'data-url="' + feature.properties["url"] + '"' 
                    		        + 'data-id="' + feature.properties["id"]+ '">';
                    			if(addedFavorite){
                    				lienFavori += '<span>' + Liferay.Language.get("eu.remove-from-favorite") + '</span>';
                    			}else{
                    				lienFavori += '<span>' + Liferay.Language.get("eu.add-to-favorite") + '</span>';
                    			}
                    			lienFavori += '</a>';
                    			formated_info = lienFavori;
                            } else if (info_to_display == "opened"){
                                formated_info = feature.properties[info_to_display];
                                hasOpened = true;
                            } else if (info_to_display !="visual") {
                                formated_info = feature.properties[info_to_display];
                            }
                            $(popupElement).find('.infowindow__' + info_to_display).html(formated_info); // On rempli le champ dans l'infowindow
                        }
                    });
                    if(!hasOpened){
                    	$(popupElement).find('.infowindow__middle').remove(); // On cache le champ dans l'infowindow
                    }else if(!hasAmount){
                    	$(popupElement).find('.infowindow__right').remove(); // On cache le champ dans l'infowindow
                    }
                    layer.bindPopup($(popupElement).html(), {closeButton: false});
                    layer.on('popupopen', function(e) {
                        var addFavoriteElement = $('.add-favorites', e.target._popup._contentNode);
                        var isFavorite = false;
                        var id = addFavoriteElement.data('id');
                        var type = addFavoriteElement.data('type');
                        var i;
                        for (i = 0; i < window.userFavorites.length; i++) {
                            if(window.userFavorites[i].typeId == type && window.userFavorites[i].entityId == id){
                                isFavorite = true;
                                break;
                            }
                        }
                        if (isFavorite) {
                            addFavoriteElement.addClass('liked');
                        } else {
                            addFavoriteElement.removeClass('liked');
                        }
                    });
                    // Titre dans la liste des markers
                    layer.options['title'] = feature.properties.name;
                }
            }


            // Création de la popup pour chaque Alerte
            var popupAlerteMarkup =
                '<div class="aroundme__infowindow infowindow">' +
                '     <button class="infowindow__close"></button>' +
                '     <div class="infowindow__content">' +
                '         <div class="infowindow__visual"></div>'+
                '         <div class="infowindow__top" style="clear: both; padding-top:15px; padding-bottom: 30px;">' +
                '             <div class="infowindow__address"></div>' +
                '         </div>' +
                '    </div>' +
                '</div>';
            var popupAlerteElement = $.parseHTML(popupAlerteMarkup);
            var onEachFeatureAlerte = function(feature, layer) {
                if (feature.properties) {
                    var icone = feature.properties.type;
                    icone = icone.replace(new RegExp(' '.replace(/[|\\{}()[\]^$+*?.]/g, '\\$&'),'g'), '_').toLowerCase();
           	    	formated_info = '<img src="/o/mapweb/images/' + icone + '.png" align="left" style="margin-right: 25px;"><div class="infowindow__name">' + feature.properties.type + '</div>';
       	    		//formated_info = '<img src="' + feature.properties.url + '" align="left" style="margin-right: 25px;"><div class="infowindow__name">' + feature.properties.type + '</div>';
           	    	// On rempli les champs dans l'infowindow
                    $(popupAlerteElement).find('.infowindow__visual').html(formated_info);  
                    $(popupAlerteElement).find('.infowindow__address').html(feature.properties.texte); 
                    layer.bindPopup($(popupAlerteElement).html(), {closeButton: false});
                    layer.on('popupopen', function(e) {
                        $('.leaflet-popup-tip', $(e.target._popup._contentNode).parent().parent()).css('background', 'white');
                    });
                    // Titre dans la liste des markers
                    layer.options['title'] = feature.properties.lieu;
                }
            }

            // Supprime les doublons parmis les markers
            var removeDuplicates = function(markers) {
                var layers = markers.getLayers();
                var i = 0;
                while (i < layers.length) {
                    var j = i;
                    while (j < layers.length) {
                        if (i != j && layers[j].feature.properties.sigId
                            && layers[i].feature.properties.sigId == layers[j].feature.properties.sigId) {
                            markers.removeLayer(layers[j]);
                        };
                        j++;
                    }
                    i++;
                }
            }
            
            // Retourne l'objet marker pour un POI donné
            var pointToLayer = function(feature, latlng) {
                if (feature.properties.icon) {
                    if (feature.properties.amount) {
                        var divIcon = new L.divIcon({
                            html:  '<img width="35" height="49" src="' + feature.properties.icon + '"><div class="aroundme__marker-amount ' 
                                + feature.properties.amount.color + '">' 
                                + feature.properties.amount.frequentation + '</div>',
                            iconSize: [35,49],
                            iconAnchor: [17, 49],
                            popupAnchor: [1, -49]
                        });
                        return L.marker(latlng, { icon: divIcon })
                    } else {
                        var markerIcon = new L.Icon({
                            iconUrl: feature.properties.icon,
                            iconSize: [35,49],
                            iconAnchor: [17, 49],
                            popupAnchor: [1, -50]
                        });
                        return L.marker(latlng, { icon: markerIcon })
                    }
                } else {
                	if(feature.properties.type){
                		switch (feature.properties.type) {
						case 2:
	                        var markerIcon = new L.Icon({
	                            iconUrl: '/o/mapweb/images/event.png',
	                            iconSize: [35,49],
	                            iconAnchor: [17, 49],
	                            popupAnchor: [1, -49]
	                        });
	                        return L.marker(latlng, { icon: markerIcon })
							break;
						default:
	                        var markerIcon = new L.Icon({
	                            iconUrl: '/o/mapweb/images/default.png',
	                            iconSize: [35,49],
	                            iconAnchor: [17, 49],
	                            popupAnchor: [1, -49]
	                        });
	                        return L.marker(latlng, { icon: markerIcon })
							break;
						}                		
                	}
                }
            }
            
            var pointAlertToLayer = function(feature, latlng) {
            	var icone = feature.properties.type;
                var markerIcon = new L.Icon({
                    iconUrl: '/o/mapweb/images/' + icone.replace(new RegExp(' '.replace(/[|\\{}()[\]^$+*?.]/g, '\\$&'),'g'), '_').toLowerCase() + '.png',
                    iconSize: [43,'auto'],
                    iconAnchor: [17, 49],
                    popupAnchor: [1, -49]
                });
                return L.marker(latlng, { icon: markerIcon })
            }

            // Retient le nombre de requêtes en cours pour l'icône de chargement
            var requestsInProgress = 0;

            // Ajoute à la liste des markers ceux des favoris
            var addFavoriteMarkers = function(markers) {
                requestsInProgress++;
                showLoadingIcon();
                Liferay.Service(
                    '/strasbourg.strasbourg/get-favorites-pois', {
                        groupId: window.groupId,
                        typeContenu: window.typesContenu
                    },
                    function(data) {
                        try {
                            // Convertion des données geoJSON en marker
                            var favoritesData = L.geoJson(data, {
                                pointToLayer: pointToLayer,
                                onEachFeature: onEachFeature
                            });
                            markers.addLayers(favoritesData);
                        } catch (e) {}
                        removeDuplicates(markers);
                        requestsInProgress--;
                        maybeHideLoadingIcon();
                    }
                );
            }

            // Ajoute à la liste des markers ceux des centres d'intérêt
            var addInterestsMarkers = function(markers, interests, categories, prefilters) {
                requestsInProgress++;
                showLoadingIcon();
                Liferay.Service(
                    '/strasbourg.strasbourg/get-pois', {
                        interests: interests,
                        categories: categories,
                        prefilters: prefilters,
                        groupId: window.groupId,
                        typeContenu: window.typesContenu
                    },
                    function(data) {
                        // Convertion des données geoJSON en marker
                        try {
                            var poisData = L.geoJson(data, {
                                pointToLayer: pointToLayer,
                                onEachFeature: onEachFeature
                            });
                            markers.addLayers(poisData);
                            removeDuplicates(markers);
                        } catch(e) {}
                        requestsInProgress--;
                        maybeHideLoadingIcon();
                    }
                );
            }

            // Ajoute le traffic à la carte
            var addTraffic = function(markers) {
                requestsInProgress++;
                showLoadingIcon();
                Liferay.Service(
                    '/strasbourg.strasbourg/get-traffic', {
                    },
                    function(data) {
                        try {
                            // Convertion des données JSON en liner
                            var trafficData = L.geoJson(data, {
                                style: function (feature) {
                                    var color = feature.properties.color.replace('0x', '#');
                                    return {color: color};
                                }
                            });
                            markers.addLayers(trafficData);
                        } catch (e) {}
                        requestsInProgress--;
                        maybeHideLoadingIcon();
                    }
                );
            }

            // Ajoute les alertes à la carte
            var addAlerts = function(markers) {
                requestsInProgress++;
                showLoadingIcon();
                Liferay.Service(
                    '/strasbourg.strasbourg/get-alerts', {
                    },
                    function(data) {
                        try {
                            // Convertion des données geoJSON en marker
                            var alertesData = L.geoJson(data, {
                                pointToLayer: pointAlertToLayer,
                                onEachFeature: onEachFeatureAlerte
                            });
                            markers.addLayers(alertesData);
                        } catch (e) {}
                        requestsInProgress--;
                        maybeHideLoadingIcon();
                    }
                );
            }

            // Affichage des POIs
            var showPois = function() {
                // Clean de la carte
                mymap.removeLayer(markers);
                markers.clearLayers();

                // Récupération des catégories à afficher
                var categories = "";
                if (window.isWidgetMode) {
                	categories =  window.categoriesCheckedIds;
                } else {
                    var i;
                    for (i = 0; i < ame.$filters_categories.length; i++) {
                        var filter = $(ame.$filters_categories[i]);
                        if (filter.attr('name').indexOf("showFavorites") == -1 && filter.is(':checked')) {
                            if (categories.length > 0) {
                            	categories = categories + ",";
                            }
                            categories = categories + filter.attr('value');
                        }
                    }
                }

                // Préfiltres
            	var prefilters = window.prefilterCategoriesIds;

                // Récupération des centres d'intérêts à afficher
                var interests = "";
                if (window.isWidgetMode) {
                    interests =  window.interestsCheckedIds;
                } else {
                    var i;
                    for (i = 0; i < ame.$filters_interests.length; i++) {
                        var filter = $(ame.$filters_interests[i]);
                        if (filter.attr('name').indexOf("showFavorites") == -1 && filter.is(':checked')) {
                            if (interests.length > 0) {
                                interests = interests + ",";
                            }
                            interests = interests + filter.attr('value');
                        }
                    }
                }

                // Récupération des données concernant les centres d'intérêt
                if (interests.length > 0 || categories.length > 0) {
                    addInterestsMarkers(markers, interests, categories, prefilters);
                }

                // Récupération des données concernant les favoris
                if ((window.isWidgetMode && window.showFavoritesByDefault) 
                    || (ame.$showFavoritesFilter.length && ame.$showFavoritesFilter.is(':checked'))) {
                    addFavoriteMarkers(markers);
                }

                // Récupération des données concernant le trafic et les alertes
                // uniquement si choisi en configuration
                if (window.showTraffic) {
                	if(window.mode == "normal" ){
                        for (i = 0; i < ame.$filters_categories.length; i++) {
                            var filter = $(ame.$filters_categories[i]);
                            // et si la catégorie choisie est cochée en mode normal ou mon quartier
                            if (filter.attr('value') == window.linkCategoryId && filter.is(':checked')) {
                        		addTraffic(markers);
                        		addAlerts(markers);
                        		break;
                            }
                        }
                	}
                	if(window.mode == "aroundme" ){
                        for (i = 0; i < ame.$filters_interests.length; i++) {
                            var filter = $(ame.$filters_interests[i]);
                            // et si le centre d'intérêt choisi est coché en mode autour de moi
                            if (filter.attr('value') == window.linkInterestId && filter.is(':checked')) {
                        		addTraffic(markers);
                        		addAlerts(markers);
                        		break;
                            }
                        }
                	}
                }
                if (window.mode == "widget" && interests.length > 0) {
                    var interestIds = interests.split(',');
                    for (var i = 0; i < interestIds.length; i++) {
                        if (interestIds[i] === window.linkInterestId) {
                            addTraffic(markers);
                            addAlerts(markers);
                            break;
                        }
                    }
                }

                // Ajout à la map
                mymap.addLayer(markers);
            }


            /**
             *  Interface 
             */
            ame.$ui_fullscreen.on('click', function() {
                $('body').toggleClass('aroundme--fullscreen');
                $('html').toggleClass('no-scroll');
                mymap.invalidateSize()
                if (environment == 'desktop') {
                    ame.open_panel_side();
                } else {
                    ame.close_panel_side();
                }
            });
            ame.$ui_zoomin.on('click', function() {
                mymap.zoomIn();
            });
            ame.$ui_zoomout.on('click', function() {
                mymap.zoomOut();
            });
            ame.$ui_locate.on('click', function() {
                moveToUserPosition();
            });
            ame.$ui_home.on('click', function() {
                moveToUserAddress();
            });
            ame.$filters.on('change', function() {
                saveUserConfig();
            });

            $('#mapid').on('click', '.infowindow__close', function() {
                mymap.closePopup();
            });

            function saveUserConfig() {
                AUI().use('aui-io-request', function(A) {
                    A.io.request(window.interestPointUrl, {
                        method: 'post',
                        form: {
                            id: window.aroundMePortletNamespace + 'addItemForm'
                        },
                    });
                });
                showPois();
            }

            function showLoadingIcon() {
                $('.aroundme__ui--loading').show();
            }

            function maybeHideLoadingIcon() {
                if (requestsInProgress == 0) {
                    $('.aroundme__ui--loading').hide();
                }
            }

            function moveToUserAddress() {
            	if(window.userAddress == "  "){
    		        var agree = function() {
    		        	window.location = window.publikProfileURL;
    		        }
            		createPopinMap(Liferay.Language.get('center-to-address'), agree);
            	}else{
                    Liferay.Service('/strasbourg.strasbourg/get-coordinate-for-address', {
                        address: window.userAddress
                    }, function(data) {
                        var markerIcon = new L.Icon({
                            iconUrl: '/o/mapweb/images/home.png',
                            iconSize: [35,49],
                            iconAnchor: [17, 49]
                        });
                        var homeMarker = L.marker([data[1], data[0]], { icon: markerIcon }).addTo(mymap);
                        mymap = mymap.setView([data[1], data[0]], 18);
                    });
            	}
            }
            
            function destroyPopinMap(){
                $('#mapConfirm').remove().off('clickmapConfirm');
                $('.mseu').off('click.mapconfirm').removeClass('overlayed');
            }
            
            function createPopinMap(message, agree){
                var template = '<div id="mapConfirm"> \
                    <div class="mapMessage">##mapMessage##</div> \
                    <div class="mapActions"> \
                        <button class="btn-square--bordered--core deny"><span class="flexbox"><span class="btn-text">Annuler</span><span class="btn-arrow"></span></span></button> \
                        <button class="btn-square--filled--second confirm"><span class="flexbox"><span class="btn-text">Modifier mes informations</span><span class="btn-arrow"></span></span></button> \
                    </div> \
                </div>';

                template = template.replace('##mapMessage##', message);
                $('body').append(template);
                $('.mseu').addClass('overlayed');


                $('#mapConfirm .deny').on('click.mapConfirm', function(e){
                    destroyPopinMap();
                });
                $('#mapConfirm .confirm').on('click.mapConfirm', function(){
                    destroyPopinMap();
                    if(agree !== undefined){
                        agree();
                    }
                });
            }

            function moveToUserPosition() {
                navigator.geolocation.getCurrentPosition(function(position) {
                    var markerIcon = new L.Icon({
                        iconUrl: '/o/mapweb/images/gps.png',
                        iconSize: [35,35],
                        iconAnchor: [12, 12]
                    });
                    var homeMarker = L.marker([position.coords.latitude, position.coords.longitude], { icon: markerIcon }).addTo(mymap);
                    mymap.setView([position.coords.latitude, position.coords.longitude], 18);
                });
            }

            // Scroll sur la liste
            var list = document.getElementsByClassName('filtres--poi')[0];
            list.addEventListener('mouseover', function() {
                mymap.dragging.disable();
                mymap.scrollWheelZoom.disable();
            });
            list.addEventListener('mouseout', function() {
                mymap.dragging.enable();
                mymap.scrollWheelZoom.enable();
            });

            // Affichage des POIs
            showPois();
            if (window.isWidgetMode) {
                ame.close_panel_side();
            }
        }
    });
}(jQuery));