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
            var poi_infos_to_display = ["name", "like", "address", "opened", "schedules", "amount", "url"];
            var popupMarkup =
                '<div class="aroundme__infowindow infowindow">' +
                '     <button class="infowindow__close"></button>' +
                '     <div class="infowindow__content">' +
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
                            } else {
                                formated_info = feature.properties[info_to_display];
                            }
                            $(popupElement).find('.infowindow__' + info_to_display).html(formated_info); // On rempli le champ dans l'infowindow
                        }
                    });

/*
                    var popup = "";
                    // popup du marker
                    //popup += "<img src='" + feature.properties.visual + "' width='100%' /><br>";
                    popup += feature.properties.name + "<br>";
                    popup += feature.properties.address + "<br>";
                    //popup += "<input type='button' value='favoris' name='favoris'/><br>";
                    if (feature.properties.schedule != undefined) {
                        if (feature.properties.schedule.isClosed != undefined) {
                            if (feature.properties.schedule.isClosed) {
                                popup += Liferay.Language.get('eu.closed') + "<br>";
                                var openingDate = feature.properties.schedule.openingDate
                                if (openingDate != undefined) {
                                    popup += "Ouvrira " + feature.properties.schedule.openingDate + "<br>";
                                    if (feature.properties.schedule.alwaysOpen) {
                                        popup += "24h/24<br>";
                                    } else {
                                        var openingTime = feature.properties.schedule.openingTime
                                        if (openingTime != undefined) {
                                            // on n'affiche que le prochain horaire
                                            // d'ouverture
                                            var schedule = "";
                                            for (var time in openingTime[0]) {
                                                if (schedule == "") {
                                                    schedule += openingTime[0][time] + " - ";
                                                } else {
                                                    schedule += openingTime[0][time];
                                                }
                                            }
                                            popup += schedule + "<br>";
                                        }
                                    }
                                }
                            } else {
                                popup += Liferay.Language.get('open-period') + "<br>";
                                if (feature.properties.schedule.alwaysOpen) {
                                    popup += "24h/24<br>";
                                } else {
                                    var openingTimes = feature.properties.schedule.openingTimes
                                    if (openingTimes != undefined) {
                                        for (var opening in openingTimes) {
                                            // on affiche tous les horaires du jour.
                                            var times = openingTimes[opening];
                                            var schedule = "";
                                            for (var time in times) {
                                                if (schedule == "") {
                                                    schedule += times[time] + " - ";
                                                } else {
                                                    schedule += times[time];
                                                }
                                            }
                                            popup += schedule + "<br>";
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (feature.properties.url != undefined) {
                        popup += "<a href='" + feature.properties.url + "' ";
                        if (newTab) {
                            popup += "target='_blank' ";
                        }
                        popup += ">" + Liferay.Language.get('learn-more') + "</a>";
                    }
                    */
                    layer.bindPopup($(popupElement).html(), {closeButton: false});
                    // Titre dans la liste des markers
                    layer.options['title'] = feature.properties.name;
                }
            }

            // Supprime les doublons parmis les markerss
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
                    var markerIcon = new L.Icon({
                        iconUrl: feature.properties.icon,
                        iconSize: [34,42],
                        iconAnchor: [17, 42],
                        popupAnchor: [0, -42]
                    });
                    return L.marker(latlng, { icon: markerIcon })
                } else {
                    return L.marker(latlng);
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
                        groupId: groupId
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
            var addInterestsMarkers = function(markers, interests) {
                requestsInProgress++;
                showLoadingIcon();
                Liferay.Service(
                    '/strasbourg.strasbourg/get-pois', {
                        interests: interests,
                        groupId: groupId
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

                // Récupération des centres d'intérêts à afficher
                var interests = "";
                if (window.isWidgetMode) {
                    interests =  window.interestsCheckedIds;
                } else {
                    var i;
                    for (i = 0; i < ame.$filters.length; i++) {
                        var filter = $(ame.$filters[i]);
                        if (!filter.attr('name').includes("showFavorites") && filter.is(':checked')) {
                            if (interests.length > 0) {
                                interests = interests + ",";
                            }
                            interests = interests + filter.attr('value');
                        }
                    }
                }

                // Récupèration des données concernant les centres d'intérêt
                if (interests.length > 0) {
                    addInterestsMarkers(markers, interests);
                }

                // Récupération des données concernant les favoris
                if (window.isWidgetMode && window.showFavoritesByDefault 
                    || ame.$showFavoritesFilter.length && ame.$showFavoritesFilter.is(':checked')) {
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
            ame.$ui_locate.on('click', moveToUserPosition);
            ame.$ui_home.on('click', moveToUserAddress);
            ame.$filters.on('change', saveUserConfig);

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
                Liferay.Service('/strasbourg.strasbourg/get-coordinate-for-address', {
                    address: window.userAddress
                }, function(data) {
                    mymap = mymap.setView([data[1], data[0]], 18);
                });
            }

            function moveToUserPosition() {
                navigator.geolocation.getCurrentPosition(function(position) {
                    mymap.setView([position.coords.latitude, position.coords.longitude], 18);
                });
            }

            // Affichage des POIs
            showPois();
        }
    });
}(jQuery));