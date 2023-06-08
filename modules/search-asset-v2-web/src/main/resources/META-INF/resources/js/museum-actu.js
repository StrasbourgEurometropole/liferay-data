// Initialisation des variables de références
$(document).ready(function(){
    getSelectedEntries(0, delta);
});

// Lors d'une selection d'un vocabulaire
$(".vocabulary-selection-control input").change(function() {
	getSelectedEntries(0, delta);
});
$(".vocabulary-selection-control select").change(function() {
	getSelectedEntries(0, delta);
});

// Lors d'un changement de date
$(".date-selection .date").change(function(){
    getSelectedEntries(0, delta);
});

/**
 * Renvoi la liste des news demandées
 * @return
 */
function getSelectedEntries(start, end) {

    var selectedStartDay = "";
    var selectedStartMonth = "";
    var selectedStartYear = "";
    var selectedEndDay = "";
    var selectedEndMonth = "";
    var selectedEndYear = "";
	if($('.date-selection').length > 0){
	    var selectedStartDate = $('#' + porletNamespace + 'fromDate').val();
        var [selectedStartDay, selectedStartMonth, selectedStartYear] = selectedStartDate.split('/');
        selectedStartMonth -= 1;
	    var selectedEndDate = $('#' + porletNamespace + 'toDate').val();
        var [selectedEndDay, selectedEndMonth, selectedEndYear] = selectedEndDate.split('/');
        selectedEndMonth -= 1;
	}

	var selectedAllCategories = "";
    $(".vocabulary-selection-control").each(function() {
        // selects
        if($(this).find("select").length > 0){
	        var selectedCategories = "";
            var selection = $(this).find("select")[0].selectedOptions;
            for (let i = 0 ; i < selection.length; i++){
                if(selectedCategories.length > 0)
                    selectedCategories += ",";
                selectedCategories += selection[i].value;
            }
            if(selectedAllCategories.length > 0)
                selectedAllCategories += "--";
            selectedAllCategories += selectedCategories;
        }

        // inputs
        if($(this).find("input:checked").length > 0){
	        var checkedCategories = "";
            $(this).find("input:checked").each(function() {
                if(checkedCategories.length > 0)
                    checkedCategories += ",";
                checkedCategories += this.value;
            });
            if(selectedAllCategories.length > 0)
                selectedAllCategories += "--";
            selectedAllCategories += checkedCategories;
        }
    });

	AUI().use('aui-io-request', function(A) {
		A.io.request(selectionURL, {
			method : 'post',
			dataType: 'json',
			data : {
				_eu_strasbourg_portlet_search_asset_v2_SearchAssetPortlet_selectedStartDay : selectedStartDay,
				_eu_strasbourg_portlet_search_asset_v2_SearchAssetPortlet_selectedStartMonth : selectedStartMonth.toString(),
				_eu_strasbourg_portlet_search_asset_v2_SearchAssetPortlet_selectedStartYear : selectedStartYear,
				_eu_strasbourg_portlet_search_asset_v2_SearchAssetPortlet_selectedEndDay : selectedEndDay,
				_eu_strasbourg_portlet_search_asset_v2_SearchAssetPortlet_selectedEndMonth : selectedEndMonth.toString(),
				_eu_strasbourg_portlet_search_asset_v2_SearchAssetPortlet_selectedEndYear : selectedEndYear,
				_eu_strasbourg_portlet_search_asset_v2_SearchAssetPortlet_selectedAllCategories : selectedAllCategories,
				_eu_strasbourg_portlet_search_asset_v2_SearchAssetPortlet_start : start,
				_eu_strasbourg_portlet_search_asset_v2_SearchAssetPortlet_end : end,
			},
			on: {
                success: function(e) {
                	var data = this.get('responseData');
                    // affichage résultats
                    var listing = $('.search-asset-results');
                    result = getResult(data);
                    if(start == 0)
                        listing.html(result);
                    else
                        listing.append(result);

                    var paginate = $('.search-asset-paginate');
                    var nbThumbnails = $('.news-thumbnail').length;
                    var bouton = "";
                    if(data.total > nbThumbnails){
                        bouton =
                            '<div class="btn-more">' +
                                Liferay.Language.get("eu.museum.more-news") +
                                '<button class="btn" aria-label="' + Liferay.Language.get("eu.museum.more-news") + '" onClick="getSelectedEntries(' + nbThumbnails + ', ' + (nbThumbnails + delta) + '); return false;" ></button>' +
                            '</div>';
                    }
                    paginate.html(bouton);
			 	}
			}
		});
	});
}

/**
 * Retoune le résultat
 */
function getResult(data) {
    var thumbnails = "";
    if(data != null){
        $.each(data.entries,function(index, json) {
            if(json.class == "com.liferay.journal.model.JournalArticle"){
                thumbnails += createNews(json.json);
            }

        });
    }
    return thumbnails;
}

/**
* Création de la vignette actualité
 * @return
*/
function createNews(news){
    var vignette =
    '<a href="' + news.detailURL + '" aria-label="' + news.title + '" title="' + news.title + '" class="news-thumbnail" style="background-image: url(' + news.image + ')">' +
        '<div class="info">' +
            '<div class="date">' +
                '<date>' + Liferay.Language.get("eu.published-on") + ' ' + news.modifiedDate + '</date>' +
            '</div>' +
            '<div class="museums">';

    var nbMusees = news.jsonMuseumsTitle.length;
    if(nbMusees > 0) {
        vignette +=
                '<span>' +
                    news.jsonMuseumsTitle[0];
        if (nbMusees > 2)
            vignette += ' ' + Liferay.Language.get("eu.museum.and-x", [(nbMusees - 1)]);
        else if (nbMusees > 1)
                vignette += ' ' + Liferay.Language.get("eu.museum.and");
        vignette +=
                 '</span>' +
                 '<ul class="list-museums">';
        $.each(news.jsonMuseumsTitle,function(index, json) {
                vignette += '<li>' + json + '</li>';
        });
        vignette +=
                 '</ul>';
    }
    vignette +=
             '</div>' +
             '<p class="title">' +
                 '<span>' + news.title + '</span>' +
             '</p>' +
         '</div>' +
     '</a>';

    return vignette;
}