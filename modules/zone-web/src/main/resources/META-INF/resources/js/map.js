(function($) {
    $(document).ready(function() {
        if ($('#aroundme').length) {
            var ame = {},
            $ame = $('#aroundme');
            ame.$ame = $('#aroundme'),
            ame.$ui_fullscreen = $('.aroundme__ui--fullscreen'),
            ame.$ui_zoomin = $('.aroundme__ui--zoomin'),
            ame.$ui_zoomout = $('.aroundme__ui--zoomout'),
            ame.$ui_locate = $('.aroundme__ui--locate'),
            ame.$ui_home = $('.aroundme__ui--home');

            // Carte

            //Création de la carte au centre de strasbourg
            var mymap = L.map('mapid', {
                // crs: L.CRS.EPSG4326, //Commenté car casse l'affichage de la carte
                center: [48.585, 7.754],
                maxBounds: [[48.565, 7.69], [48.605, 7.817]],
                zoom: 14,
                minZoom: 14,
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

            // Retient le nombre de requêtes en cours pour l'icône de chargement
            var requestsInProgress = 0;

            // Affichage de la zone
            var showZone = function() {                
                // Récupération des coordonnées de la zone
                if (window.json) {
                    requestsInProgress++;
                    showLoadingIcon();
                    // Convertion des données geoJSON en polygon
                    var coordinates = L.geoJson(window.json, {
                        style: "color: '#ff0000'"
                    }).addTo(mymap);
                    requestsInProgress--;
                    maybeHideLoadingIcon();
                }
            }


            /**
             *  Interface 
             */
            ame.$ui_fullscreen.on('click', function() {
                $('body').toggleClass('aroundme--fullscreen');
                $('html').toggleClass('no-scroll');
                mymap.invalidateSize()
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

            $('#mapid').on('click', '.infowindow__close', function() {
                mymap.closePopup();
            });

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

            // Affichage de la zone
            showZone();
        }
    });
}(jQuery));