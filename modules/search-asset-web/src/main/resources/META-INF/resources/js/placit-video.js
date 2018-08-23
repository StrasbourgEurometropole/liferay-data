// Initialisation des variables de références
var videos = null;

var entityType = {
	DISTRICT : 'vocabulary_1',
	THEMATIC : 'vocabulary_2',
}

var sortField = "title";
var sortType = "asc";

/**
 * Renvoi la liste des IDs des entités demandées
 * @param entityName Nom de l'entité à sonder
 * @return liste des IDs sous forme d'une chaine de caractères séparée par des ","
 */
function getSelectedMarkerElements(entityName) {
	var results = "";
	
	$("input[id*='" + entityName + "_']:checked").each(function() {
		results =  results.concat(this.value, ',');
	});
	
	return results;
}

/**
 * Renvoi la liste des vidéos demandées
 * @return
 */
function getSelectedVideos() {
	var selectedKeyWords = $('#name')[0].value;
	var selectedStartDay = $('input[data-name="fromDay"]')[0].value;
	var selectedStartMonth = $('input[data-name="fromMonth"]')[0].value;
	var selectedStartYear = $('input[data-name="fromYear"]')[0].value;
	var selectedEndDay = $('input[data-name="toDay"]')[0].value;
	var selectedEndMonth = $('input[data-name="toMonth"]')[0].value;
	var selectedEndYear = $('input[data-name="toYear"]')[0].value;
	var selectedProject = $('#projet')[0].value;
	var selectedDistricts = getSelectedMarkerElements(entityType.DISTRICT);
	var selectedThematics = getSelectedMarkerElements(entityType.THEMATIC);

	AUI().use('aui-io-request', function(A) {
		A.io.request(videosSelectionURL, {
			method : 'post',
			dataType: 'json',
			data : {
				_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_selectedKeyWords : selectedKeyWords,
				_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_selectedStartDay : selectedStartDay,
				_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_selectedStartMonth : selectedStartMonth,
				_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_selectedStartYear : selectedStartYear,
				_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_selectedEndDay : selectedEndDay,
				_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_selectedEndMonth : selectedEndMonth,
				_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_selectedEndYear : selectedEndYear,
				_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_selectedProject : selectedProject,
				_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_selectedDistricts : selectedDistricts,
				_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_selectedThematics : selectedThematics,
				_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_sortFieldAndType : sortField + ',' + sortType
			},
			on: {
                success: function(e) {
                	var data = this.get('responseData');
                	data.entries.each(function(video) {
                        var vignette =
                            '<div class="col-md-4 col-sm-6 col-xs-12">' +
                                '<div class="pro-card-video" data-linkall=".pro-link-all">' +
                                    '<div class="pro-header">' +
                                        '<figure class="fit-cover" role="group">' +
                                            '<img alt="' + video.title + '" width="280" height="175" src="' + video.imageURL + '">' +
                                        '</figure>' +
                                        '<span class="icon-ico-lecteur"></span>' +
                                    '</div>' +
                                    '<div class="pro-meta-avis">' +
                                        '<div class="pro-avis">' +
                                            '<a href="#pro-avis-like-pro" class="pro-like"' +
                                                'data-typeid="3" ' +
                                                'data-isdislike="false"' +
                                                'data-title=\'' + video.title + '\'' +
                                                'data-entityid="' + video.videoId + '"' +
                                                'data-entitygroupid="' + video.groupId + '">' +
                                                video.nbLikes +
                                            '</a>' +
                                            '<a href="#pro-avis-dislike-pro" class="pro-dislike"' +
                                                'data-typeid="3" ' +
                                                'data-isdislike="true"' +
                                                'data-title=\'' + video.title + '\'' +
                                                'data-entityid="' + video.videoId + '"' +
                                                'data-entitygroupid="' + video.groupId + '">' +
                                                video.nbDislikes +
                                            '</a>' +
                                        '</div>';
                                        if (video.nbViews != ""){
                                            vignette +=
                                            '<span class="pro-view">' +
                                                video.nbViews + ' vues' +
                                            '</span>';
                                        }
                                    vignette +=
                                    '</div>' +
                                    '<a href="' + homeURL + '" title="Vers la page ' + video.title + '" class="pro-link-all"><h3>' + video.title + '</h3></a>' +
                                '</div>' +
                            '</div>';
                    });
			 	}
			 }
		});
	});
}

// Lors d'un effacement de selection
$('.pro-remove').on('click',function(){
	getSelectedVideos();
});

// Lors d'une recherche par mots clés
$('#name').change(function() {
	getSelectedVideos();
});

// Lors d'une selection de projet
$('#projet').change(function() {
	getSelectedVideos();
});

// Lors d'une selection d'un quartier
$("fieldset[id='districts_fieldset'] input").change(function() {
	getSelectedVideos();
});

// Lors d'une selection d'une thématique
$("fieldset[id='thematics_fieldset'] input").change(function() {
	getSelectedVideos();
});

// Permet le tri des vidéos
function sortVideo(type) {
    sortType = type;
    // change l'affichage du tri
    if(type == "asc"){
        $('#sortType').text = "Tri A-Z";
    }else{
        $('#sortType').text = "Tri Z-A";
    }
    getSelectedVideos();
}
