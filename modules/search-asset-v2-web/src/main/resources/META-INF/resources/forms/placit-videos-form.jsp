<%@ include file="/search-asset-init.jsp"%>

<portlet:resourceURL id="entrySelectionVideo" var="videosSelectionURL">
</portlet:resourceURL>

<c:set var="groupID" value="${themeDisplay.scopeGroupId}" />

<!-- Recherche par mots-clefs -->
<div class="pro-group">
    <div class="pro-header">
        <h4><liferay-ui:message key="eu.placite.keywords" /></h4>
        <span class="pro-remove dynamic"><liferay-ui:message key="eu.erase" /></span>
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
	        <span class="pro-remove dynamic"><liferay-ui:message key="eu.erase" /></span>
	    </div>
	    <fieldset>
	        <legend aria-hidden="true" class="hide">Choix par date</legend>
	        <div>
	            <div class="pro-facette-date">
	                <label for="startDate" class="hide"><liferay-ui:message key="eu.dates" /></label>
	                <span class="pro-wrapper-date">
	                	<input name="from" data-type="date" type="text" id="date-start"  class="frm_date dynamic"
			            	readonly="readonly" value="${dc.fromDay}/${dc.fromMonthValue}/${dc.fromYear}">
			            <input type="hidden" name="<portlet:namespace />fromDay" data-name="fromDay" value="${dc.fromDay}" />
			            <input type="hidden" name="<portlet:namespace />fromMonth" data-name="fromMonth" value="${dc.fromMonthIndex}" />
			            <input type="hidden" name="<portlet:namespace />fromYear" data-name="fromYear" value="${dc.fromYear}" />
	                </span>
	                <label for="endDate"><liferay-ui:message key="eu.au" /></label>
	                <span class="pro-wrapper-date">
	                	<input name="to" data-type="date" type="text" id="date-end" class="frm_date dynamic"
			            	readonly="readonly" value="${dc.toDay}/${dc.toMonthValue}/${dc.toYear}">
			            <input type="hidden" name="<portlet:namespace />toDay" data-name="toDay" value="${dc.toDay}" />
			            <input type="hidden" name="<portlet:namespace />toMonth" data-name="toMonth" value="${dc.toMonthIndex}" />
			            <input type="hidden" name="<portlet:namespace />toYear" data-name="toYear" value="${dc.toYear}" />
	                </span>
	            </div>
	        </div>
	    </fieldset>
	</div>
</c:if>

<!-- Recherche par projet -->
<div class="pro-group">
    <div class="pro-header">
        <h4><liferay-ui:message key="eu.projects" /></h4>
        <span class="pro-remove dynamic"><liferay-ui:message key="eu.erase" /></span>
    </div>
    <fieldset>
        <legend aria-hidden="true" class="hide">Choix par nom de projet</legend>
        <div>
            <select class="" id="projet"
				name="<portlet:namespace />vocabulary_0">
				<option><liferay-ui:message key="eu.project-choose" /></option>
				<c:set var="groupID" value="${themeDisplay.scopeGroupId}" />
				<c:set var="projectStatusVocabulary"
					value="${vocabularyAccessor.getProjects(groupID)}" />
				<c:forEach
					items="${dc.getDropdownRootCategories(projectStatusVocabulary)}"
					var="category">
					<c:set var="category" value="${category}" scope="request" />
					<c:set var="dc" value="${dc}" scope="request" />
					<c:set var="level" value="1" scope="request" />
					<jsp:include page="/forms/category-option.jsp" />
				</c:forEach>
			</select>
        </div>
    </fieldset>
</div>

<!-- Recherche par quartiers -->
<div class="pro-group">
    <div class="pro-header">
        <h4><liferay-ui:message key="eu.districts" /></h4>
        <span class="pro-remove dynamic"><liferay-ui:message key="eu.erase" /></span>
    </div>
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

<!-- Recherche par thÃ©matiques -->
<div class="pro-group">
    <div class="pro-header">
        <h4><liferay-ui:message key="eu.thematics" /></h4>
        <span class="pro-remove dynamic"><liferay-ui:message key="eu.erase" /></span>
    </div>
    <fieldset id="thematics_fieldset" class="pro-checkbox">
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

        var videosSelectionURL = '${videosSelectionURL}';
	</script>
</liferay-util:html-top>

<liferay-util:html-bottom>
	<script src="/o/searchassetv2web/js/placit-video.js"></script>

</liferay-util:html-bottom>