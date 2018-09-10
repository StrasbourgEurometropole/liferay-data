<%@ include file="/search-asset-init.jsp"%>

<portlet:resourceURL id="entrySelectionNews" var="newsSelectionURL">
</portlet:resourceURL>

<c:set var="groupID" value="${themeDisplay.scopeGroupId}" />

<!-- Recherche par dates -->
<c:if test="${dc.dateField}">
	<div class="pro-facette-date">
	    <label for="startDate"><liferay-ui:message key="eu.dates" /></label>
        <span class="pro-wrapper-date">
            <input name="from" data-type="date" type="text" id="date-start"  class="frm_date dynamic"
                readonly="readonly" value="${dc.fromMonthValue}/${dc.fromDay}/${dc.fromYear}">
            <input type="hidden" name="<portlet:namespace />fromDay" data-name="fromDay" value="${dc.fromDay}" />
            <input type="hidden" name="<portlet:namespace />fromMonth" data-name="fromMonth" value="${dc.fromMonthIndex}" />
            <input type="hidden" name="<portlet:namespace />fromYear" data-name="fromYear" value="${dc.fromYear}" />
        </span>
        <label for="endDate"><liferay-ui:message key="eu.au" /></label>
        <span class="pro-wrapper-date">
            <input name="to" data-type="date" type="text" id="date-end" class="frm_date dynamic"
                readonly="readonly" value="${dc.toMonthValue}/${dc.toDay}/${dc.toYear}">
            <input type="hidden" name="<portlet:namespace />toDay" data-name="toDay" value="${dc.toDay}" />
            <input type="hidden" name="<portlet:namespace />toMonth" data-name="toMonth" value="${dc.toMonthIndex}" />
            <input type="hidden" name="<portlet:namespace />toYear" data-name="toYear" value="${dc.toYear}" />
        </span>
	</div>
</c:if>

<!-- Recherche par thématiques -->
<div class="pro-sort pro-dropdown">
    <a href="#" title="Voir toutes les thématiques"><liferay-ui:message key="eu.thematics" /></a>
    <fieldset id="thematics_fieldset" class="pro-checkbox">
        <legend aria-hidden="true" class="hide">Choix par thématique</legend>

		<c:set var="thematicVocabulary" value="${vocabularyAccessor.getThematics(groupID)}" />
		<c:forEach
			items="${dc.getDropdownRootCategories(thematicVocabulary)}"
			var="category"
			varStatus="catStatus">
			<aui:input type="checkbox" name="vocabulary_0"
				value="${category.categoryId}"
				checked="${fn:contains(dc.filterCategoriesIdsString, category.categoryId)}"
				id="vocabulary_2_${catStatus.index}"
				label="${category.getTitle(locale)}"
				cssClass="move-to-grand-parent" />
		</c:forEach>

    </fieldset>
</div>

<!-- Recherche par quartiers -->
<div class="pro-sort pro-dropdown">
    <a href="#" title="Voir tous les quartiers"><liferay-ui:message key="eu.districts" /></a>
    <fieldset id="districts_fieldset" class="pro-checkbox">
        <legend aria-hidden="true" class="hide">Choix par nom de quartier</legend>
        
        <c:set var="districtVocabulary" value="${vocabularyAccessor.getTerritories()}" />
		<c:forEach
			items="${dc.getDropdownRootCategories(districtVocabulary)}"
			var="category"
			varStatus="catStatus">
			<aui:input type="checkbox" name="vocabulary_1"
				value="${category.categoryId}"
				checked="${fn:contains(dc.filterCategoriesIdsString, category.categoryId)}"
				id="vocabulary_1_${catStatus.index}"
				label="${category.getTitle(locale)}"
				cssClass="move-to-grand-parent" />
		</c:forEach>
        
    </fieldset>
</div>

<script>
	$(document).ready(function() {
		$('.move-to-grand-parent').each(function() {
		  $(this).prependTo($(this).parent().parent());
		});
	});
</script>

<liferay-util:html-top>
	<script>
		var porletNamespace = '<portlet:namespace/>';

        var newsSelectionURL = '${newsSelectionURL}';
	</script>
</liferay-util:html-top>

<liferay-util:html-bottom>
	<script src="/o/searchassetweb/js/placit-news.js"></script>

</liferay-util:html-bottom>