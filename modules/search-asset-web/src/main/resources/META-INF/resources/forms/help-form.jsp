<%@ include file="/search-asset-init.jsp"%>

<portlet:resourceURL id="entrySelectionHelpProposal" var="helpsSelectionURL">
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

<!-- Recherche par type d'aide' -->
<div class="pro-group">
    <div class="pro-header">
        <h4><liferay-ui:message key="eu.help.types" /></h4>
        <span class="pro-remove dynamic"><liferay-ui:message key="eu.erase" /></span>
    </div>
    <fieldset id="types_fieldset" class="pro-checkbox">
        <legend aria-hidden="true" class="hide">Choix par type d'aide</legend>

		<c:set var="typeVocabulary" value="${vocabularyAccessor.getHelpProposalType(groupID)}" />
		<c:forEach
			items="${dc.getDropdownRootCategories(typeVocabulary)}"
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

<!-- Recherche par localisation -->
<div class="pro-group">
    <div class="pro-header">
        <h4><liferay-ui:message key="eu.search.asset.web.platit.participation.territories" /></h4>
        <span class="pro-remove dynamic"><liferay-ui:message key="eu.erase" /></span>
    </div>
    <fieldset id="localisations_fieldset" class="pro-checkbox">
        <legend aria-hidden="true" class="hide">Choix par localisation</legend>
        
        <c:set var="localisationVocabulary" value="${vocabularyAccessor.getTerritories()}" />
		<c:forEach
			items="${dc.getDropdownRootCategories(localisationVocabulary)}"
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

<!-- Recherche par etats -->
<div class="pro-group">
    <div class="pro-header">
        <h4><liferay-ui:message key="eu.help.state" /></h4>
        <span class="pro-remove dynamic"><liferay-ui:message key="eu.erase" /></span>
    </div>
    <fieldset id="help_activity_status_fieldset" class="pro-checkbox">
        <legend aria-hidden="true" class="hide">Choix par denomination de l'etat</legend>

        <c:set var="statusVocabulary" value="${vocabularyAccessor.getHelpProposalActivityStatus(groupID)}" />
		<c:forEach
			items="${dc.getDropdownRootCategories(statusVocabulary)}"
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
        var helpsSelectionURL = '${helpsSelectionURL}';
	</script>
</liferay-util:html-top>

<liferay-util:html-bottom>
	<script src="/o/searchassetweb/js/help.js"></script>
</liferay-util:html-bottom>