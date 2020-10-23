<%@ include file="/search-asset-init.jsp"%>

<portlet:resourceURL id="entrySelectionProject" var="projectsSelectionURL">
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

<!-- Recherche par statut -->
<div class="pro-group">
	<div class="pro-header">
		<h4>
			<liferay-ui:message key="eu.projet-status" />
		</h4>
		<span class="pro-remove dynamic"><liferay-ui:message
				key="eu.projet-erase" /></span>
	</div>
	<fieldset>
		<legend aria-hidden="true" class="hide">Choix par statut</legend>
		<div>
			<select class="" id="statut-projet"
				name="<portlet:namespace />vocabulary_0">
				<option><liferay-ui:message key="eu.projet-choose-status" /></option>
				<c:set var="groupID" value="${themeDisplay.scopeGroupId}" />
				<c:set var="projectStatusVocabulary"
					value="${vocabularyAccessor.getProjectStatus(groupID)}" />
				<c:forEach
					items="${dc.getDropdownRootCategories(projectStatusVocabulary)}"
					var="category">
					<c:set var="category" value="${category}" scope="request" />
					<c:set var="dc" value="${dc}" scope="request" />
					<c:set var="level" value="0" scope="request" />
					<jsp:include page="/forms/category-option.jsp" />
				</c:forEach>
			</select>
		</div>
	</fieldset>
</div>
<br />

<!-- Recherche par quartier -->
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

<!-- Recherche par thÃ©matique -->
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

        var projectsSelectionURL = '${projectsSelectionURL}';
	</script>
</liferay-util:html-top>

<liferay-util:html-bottom>
	<script src="/o/searchassetv2web/js/placit-project.js"></script>

</liferay-util:html-bottom>
