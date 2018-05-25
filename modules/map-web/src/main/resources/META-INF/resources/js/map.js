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
                minZoom: 13,
                zoom: 13,
                minZoom: 12,
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
            var wmsLayer = L.tileLayer.wms('http://adict.strasbourg.eu/mapproxy/service?', {
                layers: 'gct_fond_de_carte_couleur'
            }).addTo(mymap);

            // Contrôle correspondant à la liste des markers
            var listControl = new L.Control.ListMarkers({ layer: markers, itemIcon: null, minZoom: 17 });
            mymap.addControl(listControl);

            // Copie de ce contrôle dans la div prévue à cet effet
            $('.filtres--poi').append($('.list-markers .filtres__list'));

            // Création de la popup pour chaque POI
            var poi_infos_to_display = ["visual", "name", "like", "address", "opened", "schedules", "amount", "url"];
            var popupMarkup =
                '<div class="aroundme__infowindow infowindow">' +
                '     <button class="infowindow__close"></button>' +
                '     <div class="infowindow__content">' +
                '         <div class="infowindow__visual"></div>'+
                '         <div class="infowindow__top">' +
                '             <div class="infowindow__title-block"><div class="infowindow__name"></div><div class="infowindow__like"><a class="" href="/like"></a></div></div>' +
                '             <div class="infowindow__address"></div>' +
                '         </div>' +
                '         <div class="infowindow__bottom">' +
                '             <div class="infowindow__left">' +
                '                 <div class="infowindow__opened"></div>' +
                '                 <div class="infowindow__schedules"></div>' +
                '             </div>' +
                '             <div class="infowindow__right">' +
                '                 <div class="infowindow__amount"></div>' +
                '             </div>' +
                '         </div>' +
                '    </div>' +
                '    <div class="infowindow__url"></div>' +
                '</div>';
            var popupElement = $.parseHTML(popupMarkup);
            var onEachFeature = function(feature, layer) {
                if (feature.properties) {
                    poi_infos_to_display.forEach(function(info_to_display) { // Pour chaque infos qu'on est censé avoir dans le poi
                        $(popupElement).find('.infowindow__' + info_to_display).html(''); // On reset le champ dans l'infowindow
                        if (info_to_display in feature.properties && feature.properties[info_to_display] !== '') { // Si cette info est bien renseignée
                            var formated_info = '';
                            if (info_to_display == 'amount') {
                                formated_info = '<div class="infowindow__frequentation ' + feature.properties[info_to_display]["color"] + '">' + feature.properties[info_to_display]["frequentation"] + '</div>';
                            } else if (info_to_display == "url") {
                                var newTabAttribute = '';
                                if (window.newTab) {
                                    newTabAttribute = 'target="_blank"';
                                }
                                formated_info = '<a href="' + feature.properties[info_to_display] + '" ' + newTabAttribute + '><span class="flexbox"><span class="btn-text">En savoir plus</span><span class="btn-arrow"></span></span></a>';
                            } else if (info_to_display == "like") {
                                var state = feature.properties[info_to_display]["liked"] ? "liked" : "";
                                formated_info = '<a class="' + state + '" href=' + feature.properties[info_to_display]["href"] + '></a>';
                            } else if (info_to_display =="visual") {
                                formated_info = '<div class="infowindow__visualImage" style="background-image: url(' + feature.properties[info_to_display] + ');"></div>';
                            } else {
                                formated_info = feature.properties[info_to_display];
                            }
                            $(popupElement).find('.infowindow__' + info_to_display).html(formated_info); // On rempli le champ dans l'infowindow
                        }
                    });
                    layer.bindPopup($(popupElement).html(), {closeButton: false});
                    // Titre dans la liste des markers
                    layer.options['title'] = feature.properties.name;
                }
            }

            // Supprime les doublons parmis les markers
            var removeDuplicates = function(markers) {
                var layers = markers.getLayers();
                var i = 0;
                while (i < layers.length) {
                    var j = i;
                    while (j < layers.length) {
                        if (i != j && layers[i].feature.properties.sigId == layers[j].feature.properties.sigId) {
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
                        // Convertion des données geoJSON en marker
                        var favoritesData = L.geoJson(data, {
                            pointToLayer: pointToLayer,
                            onEachFeature: onEachFeature
                        });
                        markers.addLayers(favoritesData);
                        removeDuplicates(markers);
                        requestsInProgress--;
                        maybeHideLoadingIcon();
                    }
                );
            }

            // Ajoute à la liste des markers ceux des centres d'intérêt
            var addInterestsMarkers = function(markers, interests, categories) {
                requestsInProgress++;
                showLoadingIcon();
                Liferay.Service(
                    '/strasbourg.strasbourg/get-pois', {
                        interests: interests,
                        categories: categories,
                        groupId: window.groupId,
                        typeContenu: window.typesContenu
                    },
                    function(data) {
                        // Convertion des données geoJSON en marker
                        var poisData = L.geoJson(data, {
                            pointToLayer: pointToLayer,
                            onEachFeature: onEachFeature
                        });
                        markers.addLayers(poisData);
                        removeDuplicates(markers);
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
                // ajout des catégories cochées par défaut
                if(categories != '')
                	categories += ",";
            	categories +=  window.prefilterCategoriesIds;

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
                    addInterestsMarkers(markers, interests, categories);
                }

                // Récupération des données concernant les favoris
                if ((window.isWidgetMode && window.showFavoritesByDefault) 
                    || (ame.$showFavoritesFilter.length && ame.$showFavoritesFilter.is(':checked'))) {
                    addFavoriteMarkers(markers);
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
                        iconSize: [25,25],
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