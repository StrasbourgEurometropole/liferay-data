//Création de la carte au centre de strasbourg
var mymap = L.map('mapid', {
	//crs: L.CRS.EPSG4326, //Commenté car casse l'affichage de la carte
	center: [48.573, 7.752],
	zoom: 13
});	

//Couche gui gère le clustering des points
var markers = L.markerClusterGroup({
	showCoverageOnHover: false
	});

//Ajout de la couche couleur 'gct_fond_de_carte_couleur' à la carte
var wmsLayer = L.tileLayer.wms('http://adict.strasbourg.eu/mapproxy/service?', {
	layers: 'gct_fond_de_carte_couleur'
}).addTo(mymap);

showPois();


function onEachFeature(feature, layer) {
    // does this feature have a property named name
    if (feature.properties) {
    	//popup du marker
    	var popup = "<img src='" + feature.properties.visual + "' width='100%' /><br>"
		+ feature.properties.name + "<br>"
		+ feature.properties.address + "<br>"
		+ "<a href='' >" + feature.properties.sigId + "</a><br>"
		+ "<input type='button' value='favoris' name='favoris'/><br>";
		if(feature.properties.isClosed != undefined){
			if(feature.properties.isClosed){ 
				popup += "Fermé"; 
			}else{ 
				popup += "Ouvert"; 
			}
			popup += "<br>";
		}
		if(feature.properties.placeSchedules != undefined){
			popup += feature.properties.placeSchedules + "<br>";
		}
		if(feature.properties.icon != ""){
			popup += feature.properties.icon + "<br>"; 
		}
        layer.bindPopup(popup);
        //Titre dans la liste des markers
        layer.options['title'] = feature.properties.name;
    }
}


function showPois(){
	var interests = "";
	var favoritesData;
	mymap.removeLayer(markers);
	markers.clearLayers();

	$("#point-of-interest input[type='checkbox']:checked").each(
		function() {
			if(!$(this).attr('name').includes("showFavorites")){
				if(interests.length > 0){
					interests = interests + ",";
				}
				interests = interests + $(this).attr('value');
			}else{
				Liferay.Service(
					'/strasbourg.strasbourg/get-favorites-pois',
					function(data) {
						//Convertion des données geoJSON en marker
						favoritesData = L.geoJson(data, {							
						  	onEachFeature: onEachFeature
						});
						markers.addLayers(favoritesData);
					}
				);
			}
		}
	);
	
	//Récupère les données au format GeoJSON
	if(interests.length >0){
		Liferay.Service(
			'/strasbourg.strasbourg/get-pois',
			{
				interests: interests
			},
			function(data) {
				//Convertion des données geoJSON en marker
				var poisData = L.geoJson(data, {
					pointToLayer: function(feature, latlng) {
						if(feature.properties.icon){
			                var markerIcon = new L.Icon({
			                    iconUrl: feature.properties.icon
			                });
			                return L.marker(latlng, {icon: markerIcon})
						}
						else{
							return L.marker(latlng);
						}
		                
		                
		            },
					onEachFeature: onEachFeature
				});
				if(favoritesData != undefined){
					poisData.eachLayer(function(layer){
						favoritesData.eachLayer(function(layer2){
						    if(layer.feature.properties.sigId == layer2.feature.properties.sigId){
						    	poisData.removeLayer(layer._leaflet_id);
						    };
						});
					});
				}
				markers.addLayers(poisData);
			}
		);
	}
	mymap.addControl(new L.Control.ListMarkers({layer: markers, itemIcon: null}));
	mymap.addLayer(markers);

	//Copie la liste des markers dans la div prévue à cet effet
	$('#map-markers').append($('ul.list-markers-ul'));
}