<%@ include file="/search-asset-init.jsp"%>

<c:set var="groupID" value="${themeDisplay.scopeGroupId}" />

<!-- Recherche par mots-clefs -->
<div class="pro-group">
    <div class="pro-header">
        <h4><liferay-ui:message key="keywords" /></h4>
        <span class="pro-remove"><liferay-ui:message key="eu.erase" /></span>
    </div>
    <fieldset class="pro-text">
        <legend aria-hidden="true" class="hide">Effectuer une recherche</legend>
        <div>
            <input type="text" 
            	name="<portlet:namespace />keywords"
            	id="name"
            	placeholder="<liferay-ui:message key="please-enter-keyword" />"
            	value="${dc.keywords}">
            <label for="name" class="hide"><liferay-ui:message key="keywords" /></label>
        </div>
    </fieldset>
</div>

<!-- Recherche par dates -->
<c:if test="${dc.dateField}">
	<div class="pro-group">
	    <div class="pro-header">
	        <h4><liferay-ui:message key="eu.dates" /></h4>
	        <span class="pro-remove"><liferay-ui:message key="eu.erase" /></span>
	    </div>
	    <fieldset>
	        <legend aria-hidden="true" class="hide">Choix par date</legend>
	        <div>
	            <div class="pro-facette-date">
	                <label for="startDate" class="hide"><liferay-ui:message key="eu.dates" /></label>
	                <span class="pro-wrapper-date">
	                	<input name="from" data-type="date" type="text" id="date-start"  class="frm_date"
			            	readonly="readonly" value="${dc.fromMonthValue}/${dc.fromDay}/${dc.fromYear}">
			            <input type="hidden" name="<portlet:namespace />fromDay" data-name="fromDay" value="${dc.fromDay}" />
			            <input type="hidden" name="<portlet:namespace />fromMonth" data-name="fromMonth" value="${dc.fromMonthIndex}" />
			            <input type="hidden" name="<portlet:namespace />fromYear" data-name="fromYear" value="${dc.fromYear}" />
	                </span>
	                <label for="endDate"><liferay-ui:message key="eu.au" /></label>
	                <span class="pro-wrapper-date"> 
	                	<input name="to" data-type="date" type="text" id="date-end" class="frm_date"
			            	readonly="readonly" value="${dc.toMonthValue}/${dc.toDay}/${dc.toYear}">
			            <input type="hidden" name="<portlet:namespace />toDay" data-name="toDay" value="${dc.toDay}" />
			            <input type="hidden" name="<portlet:namespace />toMonth" data-name="toMonth" value="${dc.toMonthIndex}" />
			            <input type="hidden" name="<portlet:namespace />toYear" data-name="toYear" value="${dc.toYear}" />
	                </span>
	            </div>
	        </div>
	    </fieldset>
	</div>
</c:if>

<!-- Recherche par états -->
<div class="pro-group">
    <div class="pro-header">
        <h4><liferay-ui:message key="eu.state" /></h4>
        <span class="pro-remove"><liferay-ui:message key="eu.erase" /></span>
    </div>
    <fieldset class="pro-checkbox">
        <legend aria-hidden="true" class="hide">Choix par denomination de l'etat</legend>
        
        <c:set var="statusVocabulary" value="${vocabularyAccessor.getParticipationStatus(groupID)}" />
		<c:forEach
			items="${dc.getDropdownRootCategories(statusVocabulary)}"
			var="category"
			varStatus="catStatus">
			<aui:input type="checkbox" name="vocabulary_0"
				value="${category.categoryId}"
				checked="${fn:contains(dc.filterCategoriesIdsString, category.categoryId)}"
				id="vocabulary_0_${catStatus.index}"
				label="${category.getTitle(locale)}"
				cssClass="move-to-grand-parent" />
		</c:forEach>
    </fieldset>
</div>

<!-- Recherche par quartiers -->
<div class="pro-group">
    <div class="pro-header">
        <h4><liferay-ui:message key="eu.districts" /></h4>
        <span class="pro-remove"><liferay-ui:message key="eu.erase" /></span>
    </div>
    <fieldset class="pro-checkbox">
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

<!-- Recherche par thématiques -->
<div class="pro-group">
    <div class="pro-header">
        <h4><liferay-ui:message key="eu.thematics" /></h4>
        <span class="pro-remove"><liferay-ui:message key="eu.erase" /></span>
    </div>
    <fieldset class="pro-checkbox">
        <legend aria-hidden="true" class="hide">Choix par nom de la thematique</legend>
        		
		<c:set var="thematicVocabulary" value="${vocabularyAccessor.getThematics(groupID)}" />
		<c:forEach
			items="${dc.getDropdownRootCategories(thematicVocabulary)}"
			var="category"
			varStatus="catStatus">
			<aui:input type="checkbox" name="vocabulary_2"
				value="${category.categoryId}"
				checked="${fn:contains(dc.filterCategoriesIdsString, category.categoryId)}"
				id="vocabulary_2_${catStatus.index}"
				label="${category.getTitle(locale)}"
				cssClass="move-to-grand-parent" />
		</c:forEach>
		
    </fieldset>
</div>

<!-- Recherche par types -->
<div class="pro-group">
    <div class="pro-header">
        <h4><liferay-ui:message key="eu.participation.types" /></h4>
        <span class="pro-remove"><liferay-ui:message key="eu.erase" /></span>
    </div>
    <fieldset class="pro-checkbox">
        <legend aria-hidden="true" class="hide">Choix par type de participation</legend>
		
		<c:set var="participationTypesVocabulary" value="${vocabularyAccessor.getParticipationTypes(groupID)}" />
		<c:forEach
			items="${dc.getDropdownRootCategories(participationTypesVocabulary)}"
			var="category"
			varStatus="catStatus">
			<aui:input type="checkbox" name="vocabulary_3"
				value="${category.categoryId}"
				checked="${fn:contains(dc.filterCategoriesIdsString, category.categoryId)}"
				id="vocabulary_3_${catStatus.index}"
				label="${category.getTitle(locale)}"
				cssClass="move-to-grand-parent" />
		</c:forEach>
		
    </fieldset>
</div>

<div class="pro-group">
    <input type="submit" name="rechercher" value="Rechercher" />
</div>

<aui:input type="hidden" name="vocabulariesCount" value="4" />

<script>
	$(document).ready(function() {
		$('.move-to-grand-parent').each(function() {
		  $(this).prependTo($(this).parent().parent());
		});
	});
</script>