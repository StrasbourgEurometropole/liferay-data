<%@ include file="/search-asset-init.jsp"%>

<c:set var="groupID" value="${themeDisplay.scopeGroupId}" />

<div class="pro-group">
    <div class="pro-header">
        <h4><liferay-ui:message key="eu.Dates" /></h4>
        <span class="pro-remove"><liferay-ui:message key="eu.erase" /></span>
    </div>
    <fieldset>
        <legend aria-hidden="true" class="hide">Choix par date</legend>
        <div>
            <div class="pro-facette-date">
                <label for="startDate" class="hide"><liferay-ui:message key="eu.dates" /></label>
                <span class="pro-wrapper-date"><input type="text" id="startDate" name="start_date" class="frm_date" readonly="readonly"></span>
                <label for="endDate"><liferay-ui:message key="eu.erase" /></label>
                <span class="pro-wrapper-date"> <input type="text" id="endDate" name="end_date" class="frm_date" readonly="readonly"></span>
            </div>
        </div>
    </fieldset>
</div>

<div class="pro-group">
    <div class="pro-header">
        <h4><liferay-ui:message key="eu.state" /></h4>
        <span class="pro-remove"><liferay-ui:message key="eu.erase" /></span>
    </div>
    <fieldset class="pro-checkbox">
        <legend aria-hidden="true" class="hide">Choix par denomination de l'etat</legend>
        <div>
            <input type="checkbox" name="zone_vdl" id="type_v_1_1" value="1">
            <label for="type_v_1_1"><liferay-ui:message key="eu.state-soon-arrived" /></label>
        </div>
        <div>
            <input type="checkbox" name="zone_vdl" id="type_v_1_2" value="1">
            <label for="type_v_1_2"><liferay-ui:message key="eu.state-new" /></label>
        </div>
        <div>
            <input type="checkbox" name="zone_vdl" id="type_v_1_3" value="1">
            <label for="type_v_1_3"><liferay-ui:message key="eu.state-in-progress" /></label>
        </div>
        <div>
            <input type="checkbox" name="zone_vdl" id="type_v_1_4" value="1">
            <label for="type_v_1_4"><liferay-ui:message key="eu.state-soon-finished" /></label>
        </div>
        <div>
            <input type="checkbox" name="zone_vdl" id="type_v_1_5" value="1">
            <label for="type_v_1_5"><liferay-ui:message key="eu.state-finished" /></label>
        </div>
    </fieldset>
</div>

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

<aui:input type="hidden" name="vocabulariesCount" value="2" />


<script>
	$(document).ready(function() {
		$('.move-to-grand-parent').each(function() {
		  $(this).prependTo($(this).parent().parent());
		});
	});
</script>