(function($) {

	$('.search-form').submit(function (e) {
        e.preventDefault();
        var keywords = $('#search-input', this).val();
        if (keywords.length > 0) {
            window.location = 'recherche?' 
                + 'p_p_id=eu_strasbourg_portlet_search_asset_SearchAssetPortlet'
                + '&p_p_lifecycle=1'
                + '&_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_orderByCol=score'
                + '&_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_orderByType=desc'
                + '&_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_keywords='
                + keywords;
        }
	});

})(jQuery);