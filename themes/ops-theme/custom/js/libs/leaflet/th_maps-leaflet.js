
/**
 *
 * Version 1.2.0
 * 09/10/2017
 *
 *

 PAS BESOIN DE METTRE LE SCRIPT MAPS, IL SE MET AUTOMATIQUEMENT QUAND LE SCRIPT DETECTE UNE CARTE

 USAGE :

 // initialisation, possible de redefinir tout via option
 th_maps.init({ options });

 Exemple :

 th_maps.init({
    defaultOptions: {
        center: {lat: 0, lng:1},
        draggable:true,
    }
 });

 // ajout d'event
 th_maps.onLoad(function(){
    mon code ici a executer quand maps est chargé avant l'initialisation des cartes
 });

 th_maps.onLoadAfter(function(){
    mon code ici a executer quand maps est chargé après l'initialisation des cartes
 });

 // retourne l'objet map par rapport a l'element du dom
 th_maps.getMap(el)

 // Ajout d'un theme snazzy maps ou autre
 th_maps.addThemes(nom,tableauDuStyle);

 // Ajout d'une icone de marker
 th_maps.addMarkerIcon(nom,objet icone);

 // créer un marker
 th_maps.createMarker(carte,position,icon,groupName);

 // créer une infowindow
 th_maps.createInfoWindow(marker,html,largeur);

 // cache un group de marker
 th_maps.hideGroupMarker(groupName);

 // affiche un group de marker
 th_maps.showGroupMarker(groupName,carte);


 <div class="ops-maps" data-kml="url" data-custom="nom_du_callback" data-callback="nom_du_callback" data-lat="0.0" data-lng="0.0" data-theme="nom" data-marker="" data-markericon="default"></div>

 data-custom : execute une fonction pour l'initialisation de la carte, surpasse la methode défini
 data-callback : execute une fonction (la carte, les options, le marker) après l'initialisation de la carte

 data-marker : ajoute un marker au centre de la carte
 data-markericon: défini le stype des marker
 data-index est ajouté a la carte une fois initialisé


 */

var th_maps = {

    api_key: 'AIzaSyDDqH0YjBZ3qa3QtSgh6MS4Nsb9YGqr2zQ',

    maps_class: '.ops-maps',

    themes: {},
    markersIcons: {},

    elements: [],
    callbacks: [],
    callbacksAfter: [],

    markers: [],
    markersGrouped: [],
    infoWindows: [],
    lastOpenedInfoWindow: null,

    defaultOptions: {
        center:{lat:48.613322, lng:7.718948},
        zoom:12,
        draggable:true,
        scrollwheel: false,

        panControl:true,
        zoomControl:true,
        mapTypeControl:true,
        scaleControl:true,
        streetViewControl:true,
        overviewMapControl:true,
        rotateControl:true,
    },

    maps: [],

    /**
     * Initialise le module
     * Peut surpasser tout le module depuis options
     *
     * @param options
     */
    init: function(options){

        // redéfini les options de th_maps voir même ses fonctions par rapport a options
        for (var prop in options) {
            if(typeof options[prop] == 'object'){
                for (var prop2 in options[prop]) {
                    if(typeof th_maps[prop] != 'object'){
                        th_maps[prop] = {};
                    }
                    th_maps[prop][prop2] = options[prop][prop2];
                }
            }else{
                th_maps[prop] = options[prop];
            }
        }

        // cherche les elements contenant une carte
        th_maps.parseItems();

        // lance le chargement de la libs de maps si nécessaire
        if(th_maps.elements.length > 0){
            th_maps.loadMaps();
        }
    },


    /**
     * Lance l'ajout du script maps
     */
    loadMaps: function(){

        th_maps.loaded();
    },

    /**
     * Execution des callback et init des cartes une fois la lib maps chargé
     */
    loaded: function(){

        // on lance d'abord les event custom au chargement
        for(var i= 0; i < th_maps.callbacks.length; i++)
        {
            if(typeof th_maps.callbacks[i] === 'function'){
                th_maps.callbacks[i]();
            }
        }

        // puis on charge les cartes seuleemnt
        for(var i= 0; i < th_maps.elements.length; i++)
        {
            if(th_maps.elements[i]){
                th_maps.initSingleMap(th_maps.elements[i]);
            }
        }

        // on lance les event custom after
        for(var i= 0; i < th_maps.callbacksAfter.length; i++)
        {
            if(typeof th_maps.callbacksAfter[i][i] === 'function'){
                th_maps.callbacksAfter();
            }
        }


    },

    /**
     * Ajout d'une fonction a executer au chargement de maps
     * @param callback
     */
    onLoad: function(callback){
        th_maps.callbacks.push(callback);
    },

    /**
     * Ajout d'une fonction a executer après le chargement des cartes
     * @param callback
     */
    onLoadAfter: function(callback){
        th_maps.callbacksAfter.push(callback);
    },


    /**
     * Ajout d'un style de carte nommé
     *
     * @param key
     * @param val
     */
    addThemes: function(key,val){
        th_maps.themes[key] = val;
    },

    /**
     * Ajout d'une icon de marker nommé
     *
     * @param key
     * @param val
     */
    addMarkerIcon: function(key,val){
        th_maps.markersIcons[key] = L.icon(val);
    },

    /**
     * Récupére tous les éléments devant contenir des cartes
     */
    parseItems: function(){
        var mapsEls = document.querySelectorAll(th_maps.maps_class);
        th_maps.elements = mapsEls;
    },

    /**
     * initialisation d'une carte par rapport a son element
     *
     * @param el
     * @returns {boolean}
     */
    initSingleMap: function(el){

        // execute le callback si custom
        if(el.getAttribute('data-custom')){
            eval(el.getAttribute('data-custom')+'(el);');

            return false;
        }


        // récupére les options
        var options = th_maps._clone(th_maps.defaultOptions);


        // attribue automatiquement le centre
        if (el.getAttribute('data-lat') && el.getAttribute('data-lng')) {
            options.center = {lat: el.getAttribute('data-lat').replace(',','.') * 1, lng: el.getAttribute('data-lng').replace(',','.') * 1};
        }

        if (el.getAttribute('data-zoom')) {
            options.zoom = parseInt(el.getAttribute('data-zoom'));
        }

        // permet de filtrer les options pour une carte
        if(el.getAttribute('data-filter-options')){
            eval('options = '+el.getAttribute('data-filter-options')+'(options);');
        }


        // variable index et map
        var mapIndex = th_maps.maps.length;
        var map = L.map(el).setView([options.center.lat, options.center.lng], options.zoom);
        map.attributionControl.setPrefix('');
        map.scrollWheelZoom.disable();

        // attribue automatiquement un theme
        if(el.getAttribute('data-theme') && typeof mapboxgl != 'undefined' && mapboxgl.supported()){
            console.log(map._layers);
            var gl = L.mapboxGL(th_maps.themes[el.getAttribute('data-theme')]).addTo(map);
        }else{
            L.tileLayer(th_maps.tileLayerUrl, th_maps.tileLayerOptions).addTo(map);
        }

        var marker = false;
        th_maps.maps[mapIndex] = map;

        // si data-marker présent alors on ajoute un marker par défaut au centre avec le style marker-icon si défini
        if (el.getAttribute('data-marker')) {
            var icon = null;

            if (el.getAttribute('data-markericon')) {
                icon = el.getAttribute('data-markericon');
            }

            marker = th_maps.createMarker(map,options.center,icon);
        }


        el.setAttribute('data-index',mapIndex);

         if(el.getAttribute('data-kml')){
            var kmlLayer = omnivore.kml(el.getAttribute('data-kml')).on("ready", function(e) {

                var highlightStyle = {
                    fillColor: "00FFFFFF",
                    weight: 1,
                    opacity: 1,
                    color: "#000000",
                    fillOpacity:0.0
                };
                if(el.getAttribute('data-kml-bgcolor')){ highlightStyle.fillColor = el.getAttribute('data-kml-bgcolor'); }
                if(el.getAttribute('data-kml-weight')){ highlightStyle.weight = el.getAttribute('data-kml-weight'); }
                if(el.getAttribute('data-kml-opacity')){ highlightStyle.opacity = el.getAttribute('data-kml-opacity'); }
                if(el.getAttribute('data-kml-color')){ highlightStyle.color = el.getAttribute('data-kml-color'); }
                if(el.getAttribute('data-kml-bgopacity')){ highlightStyle.fillOpacity = el.getAttribute('data-kml-bgopacity'); }

                this.setStyle(highlightStyle);
                map.fitBounds(e.target.getBounds());
             }).addTo(map);
         }

        if(el.getAttribute('data-gpx')){
            var gpxLayer = omnivore.gpx(el.getAttribute('data-gpx')).on("ready", function(e) {

                var highlightStyle = {
                    fillColor: "00FFFFFF",
                    weight: 1,
                    opacity: 1,
                    color: "#000000",
                    fillOpacity:0.0
                };
                if(el.getAttribute('data-gpx-bgcolor')){ highlightStyle.fillColor = el.getAttribute('data-gpx-bgcolor'); }
                if(el.getAttribute('data-gpx-weight')){ highlightStyle.weight = el.getAttribute('data-gpx-weight'); }
                if(el.getAttribute('data-gpx-opacity')){ highlightStyle.opacity = el.getAttribute('data-gpx-opacity'); }
                if(el.getAttribute('data-gpx-color')){ highlightStyle.color = el.getAttribute('data-gpx-color'); }
                if(el.getAttribute('data-gpx-bgopacity')){ highlightStyle.fillOpacity = el.getAttribute('data-gpx-bgopacity'); }

                this.setStyle(highlightStyle);
                map.fitBounds(e.target.getBounds());
            }).addTo(map);
        }


        if(el.getAttribute('data-callback')){
            eval(el.getAttribute('data-callback')+'(map,options,marker);');
        }

        return map;
    },


    /**
     * Récupére l'objet map depuis un element
     * @param el
     * @returns {*}
     */
    getMap: function(el){
        if(el.getAttribute('data-index') || el.getAttribute('data-index') == "0"){
            return th_maps.maps[el.getAttribute('data-index')];
        }
    },


    /**
     * Génére un marker et le retourne
     *
     * @param map
     * @param position
     * @param icon
     * @param groupBy
     * @returns {google.maps.Marker}
     */
    createMarker: function(map,position,icon,groupBy){

        var marker;

        if(icon){
            if(th_maps.markersIcons[icon]){
                marker = L.marker([position.lat, position.lng],{icon:th_maps.markersIcons[icon]});
            }else{
                marker = L.marker([position.lat, position.lng],{icon:icon});
            }
        }else{
            marker = L.marker([position.lat, position.lng]);
        }

        if(map){
            marker.addTo(map);
        }
        th_maps.markers.push(marker);

        if(groupBy){
            if(!th_maps.markersGrouped[groupBy]){
                th_maps.markersGrouped[groupBy] = [];
            }
            th_maps.markersGrouped[groupBy].push(marker);
        }

        return marker;
    },


    /**
     * Génére une infowindow et la retourne
     * Gestion du click pour l'ouvrir et de la fermeture automatique
     *
     * @param content
     * @param marker
     * @param width
     * @returns {google.maps.InfoWindow}
     */
    createInfoWindow: function(content,marker,width){

        if(!width){
            width = 320;
        }

        width--;

        var infoWindow = L.popup({className:'custom-popup-thuria',maxWidth:width,minWidth:width}).setContent(content);

        th_maps.infoWindows.push(infoWindow);

        if(marker){
            marker.bindPopup(infoWindow);

            marker.addEventListener('click',function () {
                infoWindow.openPopup();
            });
        }

        /*
        google.maps.event.addListener(infoWindow, 'domready', function() {

            // Reference to the DIV which receives the contents of the infowindow using jQuery
            var iwOuter = $('.gm-style-iw');

            iwOuter.addClass('gm-style-thuria');




            var iwBackground = iwOuter.prev();

            // Remove the background shadow DIV
            iwBackground.children(':nth-child(2)').css({'display' : 'none'});

            // Remove the white background DIV
            iwBackground.children(':nth-child(4)').css({'display' : 'none'});

            // Moves the infowindow 115px to the right.
            iwOuter.parent().parent().css({left: (width-250)+'px'});

            // Moves the shadow of the arrow 76px to the left margin
            iwBackground.children(':nth-child(1)').attr('style', function(i,s){ return s + 'display: none !important;'});

            // Moves the arrow 76px to the left margin
            iwBackground.children(':nth-child(3)').attr('style', function(i,s){ return s + 'display: none !important;'});

            // Changes the desired color for the tail outline.
            // The outline of the tail is composed of two descendants of div which contains the tail.
            // The .find('div').children() method refers to all the div which are direct descendants of the previous div.
            iwBackground.children(':nth-child(3)').find('div').children().css({'box-shadow': 'rgba(178, 178, 178, 0.6) 0px 1px 6px', 'z-index' : '1'});


            // Taking advantage of the already established reference to
            // div .gm-style-iw with iwOuter variable.
            // You must set a new variable iwCloseBtn.
            // Using the .next() method of JQuery you reference the following div to .gm-style-iw.
            // Is this div that groups the close button elements.
            var iwCloseBtn = iwOuter.next();
            iwOuter.parent().find('> img').css({top:9,right:30});

            // Apply the desired effect to the close button
            iwCloseBtn.css({
                opacity: '1', // by default the close button has an opacity of 0.7
                left: 'auto',
                top: '30px',
                right: '55px',
                padding: '6px'
            });


            // The API automatically applies 0.7 opacity to the button after the mouseout event.
            // This function reverses this event to the desired value.
            iwCloseBtn.mouseout(function(){
                $(this).css({opacity: '1'});
            });

        });
*/
        return infoWindow;
    },


    /**
     * Cache tout un groupe de marker
     *
     * @param groupName
     */
    hideGroupMarker: function(groupName){
        if(th_maps.markersGrouped[groupName]){
            for(var i= 0; i < th_maps.markersGrouped[groupName].length; i++)
            {
                if(th_maps.markersGrouped[groupName][i]){
                    th_maps.markersGrouped[groupName][i]._map.removeLayer(th_maps.markersGrouped[groupName][i]);
                }
            }
        }
    },

    /**
     * Affiche tout un groupe de marker
     *
     * @param groupName
     * @param map
     */
    showGroupMarker: function(groupName,map){
        if(th_maps.markersGrouped[groupName]){
            for(var i= 0; i < th_maps.markersGrouped[groupName].length; i++)
            {
                if(th_maps.markersGrouped[groupName][i]){
                    th_maps.markersGrouped[groupName][i].addTo(map);
                    console.log(th_maps.markersGrouped[groupName][i]);
                }
            }
        }
    },



    createMarkerTarget: function(group){

        th_maps.addMarkerIcon('target',{
            iconUrl: "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACgAAAAoCAYAAACM/rhtAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyRpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMC1jMDYxIDY0LjE0MDk0OSwgMjAxMC8xMi8wNy0xMDo1NzowMSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNS4xIE1hY2ludG9zaCIgeG1wTU06SW5zdGFuY2VJRD0ieG1wLmlpZDoyRTY5ODhFNUQ2OUMxMUU3ODExRUY2M0RDODQ0RjY0MSIgeG1wTU06RG9jdW1lbnRJRD0ieG1wLmRpZDoyRTY5ODhFNkQ2OUMxMUU3ODExRUY2M0RDODQ0RjY0MSI+IDx4bXBNTTpEZXJpdmVkRnJvbSBzdFJlZjppbnN0YW5jZUlEPSJ4bXAuaWlkOjJFNjk4OEUzRDY5QzExRTc4MTFFRjYzREM4NDRGNjQxIiBzdFJlZjpkb2N1bWVudElEPSJ4bXAuZGlkOjJFNjk4OEU0RDY5QzExRTc4MTFFRjYzREM4NDRGNjQxIi8+IDwvcmRmOkRlc2NyaXB0aW9uPiA8L3JkZjpSREY+IDwveDp4bXBtZXRhPiA8P3hwYWNrZXQgZW5kPSJyIj8+XqnaGgAAA8xJREFUeNrMmV1IFVEQx9e1sAevUYbkTQMps4eKUkqsoKBCCVEzIyj6Iiks6jF8yCB6qR6jb6QPxCCiLBMpqKcy0TDDDCqT0OoaUUbpQ2Ba/wP/C9sy5+7Z+xEN/FD2zJmZu3fn7MzcpE4rakkDC8FikAtmgVSujYKPoA88By/Aj2icTPKpnwTWgC38O9tw33vwAFwDD8FvY4c+7mAFOASKrNikHZwEt02UbQOdHHATNMUhOIs2mmgzJ9YAS0EbqLTiL5W0XRrtM7gXnAHJEXS+gy7QA96CL7w+A8wFi0ABmKrZn8mvej+44CfAPeB8hMB6wTnQDD543KksUAZqwAJhPZm+VOJcNEmS9XQs3bmv4Ai4BH76/EqngN3gKEgX1sf5QVojBaiOjacgQ5N9u8DrGJ+9PHBZk3CfwVIwqEuSU5rg7oHiOARn0UYxbbolgzGIWayyqVzY1AE2gZE4ZvAIbXYIa+XOzLYdD2qdoDwMtvLVFW8Zpe1hYa0unAPhAFeBZYLiYdBvJU766cMtKpbVzgC3a46Seh/OVKEwj6T62FdPX27ZFg4wANYJCmfBmIEDlfmn6eQl6eU1k2JijL7comIKqGNmBf557FpUpdF8MORhfCW4DoKa9RDYLNiX3iivWML9Zd9mPeeWLoPgsvnCD0bQCVIn28PWEH26ZYnNZ8Ytzwy+mlrNmSmdbbUGepLPXFtzB/oNEqLMRyKUGSSO5DNoazZ+8zAW5HNjKjMN9CWfqbb1n4uteUtM89gXMkgip3wy0Jd8jtp05pY5Bq+pZh8B3jV4XUo+QzZbQ7fkGzg9wfLIS5TOcQM9yWefCrBbWCgweKhVzVal+Qacj0KVs76LcFAXCNe7bfYTIaEprzD41I9YeKreZQD8IgO8VkQdk5Y2TfhwPeGK+grYIRQL+Ybv4/DZGHQYNy3RJvOQdvcrV8HO8DHTIGxUG6p91ndviJ/6sVrTTDU4exJVHD4RasJhXktUTagyV4Uw3XVdXVuuGinb0VEdEwyojY0+6zs/9WOjEJzFWMbdPUkLuCMoF4IbrBvjJQHaLBTWmhmL2NUd1JxtJeA+W8ZYJY+2SjRn5oFIs5lB9r4TmqGPmqXsAylRBJbCvW2anniCvge9hketHFNIks7zrZPOsgwCy6JuJ/ema/Rq3FMFr/lgLMMjFUSuwfAonKDa4ZHXALOUA53MBB0zQxxUtUQ7H1QbVVN1KwHBNdF2i1c96CXvwEawgQOkWKWdtippO6Ik+ZzyO4foaw26tbD8kyG6JVQ87p8hAo7hUFx+hvgjwAD9ktvlRfPtxQAAAABJRU5ErkJggg==",
            shadowUrl: null,
            iconSize:[40, 40]
        });
        return th_maps.createMarker(null,{lat:0,lng:0},'target',group);
    },




    searchCity: function(macarte,form,marker){

        if(!marker){
            marker = th_maps.createMarkerTarget();
        }
        var geocoder = new google.maps.Geocoder();

        $('input[type="text"]',form).on('keypress',function(e){
            if(e.keyCode == 13){
                e.preventDefault();
                th_maps.searchCitySearch(macarte,form,marker,geocoder);
            }
        });
        $('input[type="submit"],button',form).on('click',function(e){
            th_maps.searchCitySearch(macarte,form,marker,geocoder);
        });


        console.log(marker);
    },

    searchCitySearch: function(macarte,form,marker,geocoder){
        marker.setMap(null);

        if($('input[type="text"]',form).val()){
            console.log(geocoder);
            console.log($('input[type="text"]',form).val());
            geocoder.geocode({'address': $('input[type="text"]',form).val()}, function (results, status) {
                if (status == google.maps.GeocoderStatus.OK) {
                    th_maps.searchReplaceCenterMaps(macarte,results[0].geometry.location,marker);
                }
            });
        }
    },

    searchAutoComplete: function(macarte,form,marker,options){
        if(!options){
            options = {};
        }

        options.types = ['geocode'];

        var el = $('input[type="text"]',form)[0];
        var autocomplete = new google.maps.places.Autocomplete(el,{types: ['geocode']});
        autocomplete.addListener('place_changed', function(){
            var place = autocomplete.getPlace();
            if(place.geometry && place.geometry.location){
                th_maps.searchReplaceCenterMaps(macarte,place.geometry.location,marker);
            }
        });

    },

    searchReplaceCenterMaps: function(macarte,coords,marker){
        macarte.setCenter(coords);
        marker.setPosition(coords);
        marker.setMap(macarte);
    },

    geoLocPositionFound: function(macarte,pos,marker){
        var crd = pos.coords;
        if(!marker){
            marker = th_maps.createMarkerTarget();
        }
        th_maps.searchReplaceCenterMaps(macarte,{lat:crd.latitude,lng:crd.longitude},marker);
    },

    geoLocError: function(error) {
        console.log(error);
        alert('Impossible de vous localiser. Le service de localisation est peut-être désactivé.');
    },

    geoLoc: function(macarte,el,marker) {
        if (navigator.geolocation) {
            $(el).on('click',function(){
                navigator.geolocation.getCurrentPosition(function(pos){th_maps.geoLocPositionFound(macarte,pos,marker);}, th_maps.geoLocError);
            });
        } else {
            $(el).remove();
            error('Not supported');
        }
    },











    // fonction qui ajoute des script js a la page
    _appendScript: function(params,callback){
        var script = document.createElement("script");
        script.type = "text/javascript";
        script.setAttribute('async','');
        script.setAttribute('defer','');
        if(callback)script.onload=callback;

        if(typeof params == 'object'){
            document.getElementsByTagName("head")[0].appendChild(script);
            $.each(params,function(key,val){
                script.setAttribute(key,params[key]);
            });
        }else{
            document.getElementsByTagName("head")[0].appendChild(script);
            script.src = url;
        }

    },
    _appendStyle: function(params,callback){
        var style = document.createElement("link");
        style.setAttribute('rel',"stylesheet");
        if(callback)script.onload=callback;

        $.each(params,function(key,val){
            style.setAttribute(key,params[key]);
        });

        document.getElementsByTagName("head")[0].appendChild(style);
    },

    // fonction de clone d'objet js
    _clone: function(obj) {
        if (obj === null || typeof(obj) !== 'object' || 'isActiveClone' in obj)
            return obj;

        if (obj instanceof Date)
            var temp = new obj.constructor(); //or new Date(obj);
        else
            var temp = obj.constructor();

        for (var key in obj) {
            if (Object.prototype.hasOwnProperty.call(obj, key)) {
                obj['isActiveClone'] = null;
                temp[key] = th_maps._clone(obj[key]);
                delete obj['isActiveClone'];
            }
        }

        return temp;
    }
};

