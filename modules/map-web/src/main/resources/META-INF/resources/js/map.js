//Création de la carte au centre de strasbourg
var mymap = L.map('mapid', {
	// crs: L.CRS.EPSG4326, //Commenté car casse l'affichage de la carte
	center: [48.573, 7.752],
	zoom: 13
});	

// Couche gui gère le clustering des points
var markers = L.markerClusterGroup({
	showCoverageOnHover: false
	});

// Ajout de la couche couleur 'gct_fond_de_carte_couleur' à la carte
var wmsLayer = L.tileLayer.wms('http://adict.strasbourg.eu/mapproxy/service?', {
	layers: 'gct_fond_de_carte_couleur'
}).addTo(mymap);

showPois();


function onEachFeature(feature, layer) {
    if (feature.properties) {
    	var popup = "";
    	// popup du marker
    	//popup += "<img src='" + feature.properties.visual + "' width='100%' /><br>";
		popup += feature.properties.name + "<br>";
		popup += feature.properties.address + "<br>";
		//popup += "<input type='button' value='favoris' name='favoris'/><br>";
		if(feature.properties.schedule != undefined){
			if(feature.properties.schedule.isClosed != undefined){
				if(feature.properties.schedule.isClosed){ 
					popup += Liferay.Language.get('eu.closed') + "<br>";
					var openingDate = feature.properties.schedule.openingDate
					if(openingDate != undefined){
						popup += "Ouvrira " + feature.properties.schedule.openingDate + "<br>";
						if(feature.properties.schedule.alwaysOpen){
							popup += "24h/24<br>";
						}else{
							var openingTime = feature.properties.schedule.openingTime
							if(openingTime != undefined){
								// on n'affiche que le prochain horaire
								// d'ouverture
								var schedule = "";
								for (var time in openingTime[0]){
									if(schedule == "" ){
										schedule += openingTime[0][time] + " - ";
									}else{
										schedule += openingTime[0][time];
									}
								}
								popup += schedule + "<br>";
							}
						}
					}
				}else{ 
					popup += Liferay.Language.get('open-period') + "<br>";
					if(feature.properties.schedule.alwaysOpen){
						popup += "24h/24<br>";
					}else{ 
						var openingTimes = feature.properties.schedule.openingTimes
						if(openingTimes != undefined){
							for (var opening in openingTimes){
								// on affiche tous les horaires du jour.
								var times = openingTimes[opening];
								var schedule = "";
								for (var time in times){
									if(schedule == ""){
										schedule += times[time] + " - ";
									}else{
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
		if(feature.properties.url != undefined){
			popup += "<a href='" + feature.properties.url + "' ";
			if(newTab){
				popup += "target='_blank' ";
			}
			popup += ">" + Liferay.Language.get('learn-more') +  "</a>";
		}
        layer.bindPopup(popup);
        // Titre dans la liste des markers
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
				if(interests.lenght > 0){
					interests = interests + ",";
				}
				interests = interests + $(this).attr('value');
			}else{
				Liferay.Service(
					'/strasbourg.strasbourg/get-favorites-pois',
					{
						groupId: groupId
					},
					function(data) {
						// Convertion des données geoJSON en marker
						favoritesData = L.geoJson(data, {
						  	onEachFeature: onEachFeature
						});
						markers.addLayers(favoritesData);
					}
				);
			}
		}
	);
	
	// Récupère les données au format GeoJSON
	if(interests.length >0){
		Liferay.Service(
			'/strasbourg.strasbourg/get-pois',
			{
				interests: interests, groupId: groupId
			},
			function(data) {
				// Convertion des données geoJSON en marker
				var poisData = L.geoJson(data, {
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
	mymap.addLayer(markers);

}