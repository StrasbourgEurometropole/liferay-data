(function($) {
    $(document).ready(function() {
        if ($('#aroundme').length) {
            var ame = {},
            $ame = $('#aroundme');
            ame.$ame = $('#aroundme');

            ame.$ui_zoomin = $('.aroundme__ui--zoomin'),
            ame.$ui_zoomout = $('.aroundme__ui--zoomout');

            // Carte

            //Création de la carte au centre de strasbourg
            var mymap = L.map('mapid', {
                // crs: L.CRS.EPSG4326, //Commenté car casse l'affichage de la carte
                center: [window.y, window.x],
                maxBounds: [[48.42, 7.52], [48.72, 7.94]],
                zoom: 16,
                minZoom: 11,
                zoomControl: false,
                attributionControl: false
            });

            // Couche gui gère le clustering des points
            var markerIcon = new L.Icon({
                iconUrl: '/o/strasbourg-theme/images/default.png',
                iconSize: [35,49],
                iconAnchor: [17, 49],
                popupAnchor: [1, -49]
            });

            var popup = "<div class='infowindow__url'><span class='seu-flexbox'><span class='seu-btn-text'>" + window.title + "</span></span></div>";
            var marker = L.marker([window.y, window.x], { icon: markerIcon }).addTo(mymap).bindPopup(popup);
            marker.on('mouseover', function (e) {
                this.openPopup();
            });
            marker.on('mouseout', function (e) {
                this.closePopup();
            });

            // Ajout de la couche couleur 'gct_fond_de_carte_couleur' à la carte
            var wmsLayer = L.tileLayer.wms('https://adict.strasbourg.eu/mapproxy/service?', {
                layers: 'monstrasbourg'
            }).addTo(mymap);


            /**
             *  Interface 
             */
            ame.$ui_zoomin.on('click', function() {
                mymap.zoomIn();
            });
            ame.$ui_zoomout.on('click', function() {
                mymap.zoomOut();
            });
        }
    });
}(jQuery));