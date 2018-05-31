<%@ include file="/search-asset-init.jsp"%>

<div class="pro-group">
	<div class="pro-header">
		<h4>
			<liferay-ui:message key="eu.projet-status" />
		</h4>
		<span class="pro-remove"><liferay-ui:message
				key="eu.projet-erase" /></span>
	</div>
	<fieldset>
		<legend aria-hidden="true" class="hide">Choix par nom de projet</legend>
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

<div class="pro-group">
	<input type="submit" name="rechercher"/>
</div>

<aui:input type="hidden" name="vocabulariesCount" value="3" />

<script>
	$(document).ready(function() {
		$('.move-to-grand-parent').each(function() {
		  $(this).prependTo($(this).parent().parent());
		});
	});
</script>
