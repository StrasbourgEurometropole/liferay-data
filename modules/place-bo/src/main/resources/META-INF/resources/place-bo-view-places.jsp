<%@ include file="/place-bo-init.jsp"%>

<liferay-portlet:renderURL varImpl="placesURL">
	<portlet:param name="tab" value="places" />
	<portlet:param name="orderByCol" value="${dc.orderByCol}" />
	<portlet:param name="orderByType" value="${dc.orderByType}" />
	<portlet:param name="filterCategoriesIds"
		value="${dc.filterCategoriesIds}" />
	<portlet:param name="keywords" value="${dc.keywords}" />
	<portlet:param name="delta" value="${dc.searchContainer.delta}" />
</liferay-portlet:renderURL>

<liferay-frontend:management-bar includeCheckBox="true"
	searchContainerId="placesSearchContainer">
	<c:if test="${empty dc.keywords}">
		<liferay-frontend:management-bar-filters>
			<c:if test="${fn:length(dc.vocabularies) > 0}">
				<li><a>Filtrer par :</a></li>
			</c:if>
			<c:if test="${not dc.hasPermission('CONTRIBUTE') or themeDisplay.permissionChecker.isOmniadmin()}">
				<c:forEach var="vocabulary" items="${dc.vocabularies}">
					<liferay-frontend:management-bar-filter 
						managementBarFilterItems="${dc.getManagementBarFilterItems(vocabulary)}" 
						value="${dc.getVocabularyFilterLabel(vocabulary)}" />
				</c:forEach>
			</c:if>

			<liferay-frontend:management-bar-sort orderByCol="${dc.orderByCol}"
				orderByType="${dc.orderByType}"
				orderColumns='<%= new String[] {"alias", "modified-date"} %>'
				portletURL="${placesURL}" />
		</liferay-frontend:management-bar-filters>

		<liferay-frontend:management-bar-action-buttons>
			<c:if test="${not dc.workflowEnabled}">
				<c:if test="${dc.hasPermission('EDIT_PLACE') and empty themeDisplay.scopeGroup.getStagingGroup()}">
					<liferay-frontend:management-bar-button
						href='<%="javascript:" + renderResponse.getNamespace() + "publishSelection();"%>'
						icon="check" label="publish" />
					<liferay-frontend:management-bar-button
						href='<%="javascript:" + renderResponse.getNamespace() + "unpublishSelection();"%>'
						icon="times" label="unpublish" />
				</c:if>
			</c:if>
			<c:if test="${dc.hasPermission('DELETE_PLACE') and empty themeDisplay.scopeGroup.getStagingGroup()}">
			<liferay-frontend:management-bar-button
				href='<%="javascript:" + renderResponse.getNamespace() + "deleteSelection();"%>'
				icon="trash" label="delete" />
			</c:if>
		</liferay-frontend:management-bar-action-buttons>
	</c:if>
</liferay-frontend:management-bar>

<div class="container-fluid-1280 main-content-body">
	<aui:form method="post" name="fm">
		<aui:input type="hidden" name="selectionIds" />
		<liferay-ui:search-container id="placesSearchContainer"
			searchContainer="${dc.searchContainer}">
			<liferay-ui:search-container-results results="${dc.places}" />

			<liferay-ui:search-container-row
				className="eu.strasbourg.service.place.model.Place" modelVar="place"
				keyProperty="placeId" rowIdProperty="placeId">
				<liferay-portlet:renderURL varImpl="editPlaceURL">
					<portlet:param name="cmd" value="editPlace" />
					<portlet:param name="placeId" value="${place.placeId}" />
					<portlet:param name="returnURL" value="${placesURL}" />
					<portlet:param name="mvcPath" value="/place-bo-edit-place.jsp" />
				</liferay-portlet:renderURL>

				<liferay-ui:search-container-column-text cssClass="content-column"
					href="${editPlaceURL}" name="alias" truncate="true" orderable="true"
					value="${place.aliasCurrentValue}" />
				
				<c:set var="typePlace" value="" />	
				<c:forEach var="category" items="${place.getTypes()}">
					<c:if test="${not empty typePlace}">
						<c:set var="typePlace" value="${typePlace}, ${category.getName()}" />
					</c:if>
					<c:if test="${empty typePlace}">
						<c:set var="typePlace" value="${category.getName()}" />
					</c:if>	
				</c:forEach>	
				<liferay-ui:search-container-column-text cssClass="content-column"
					name="type-place" truncate="true" orderable="true"
					value="${typePlace}" />
					
				<c:set var="territory" value="" />	
				<c:forEach var="category" items="${place.getTerritories()}">
					<c:if test="${not empty territory}">
						<c:set var="territory" value="${territory}, ${category.getName()}" />
					</c:if>	
					<c:if test="${empty territory}">
						<c:set var="territory" value="${category.getName()}" />
					</c:if>
				</c:forEach>
				<liferay-ui:search-container-column-text cssClass="content-column"
					name="territory" truncate="true" orderable="true"
					value="${territory}" />

				<liferay-ui:search-container-column-text name="status">
					<aui:workflow-status markupView="lexicon" showIcon="false"
						showLabel="false" status="${place.status}" />
				</liferay-ui:search-container-column-text>


				<liferay-ui:search-container-column-text>
					<liferay-ui:icon-menu markupView="lexicon">
						<c:if test="${dc.hasPermission('EDIT_PLACE') and empty themeDisplay.scopeGroup.getStagingGroup()}">
							<liferay-ui:icon message="edit" url="${editPlaceURL}" />
						</c:if>

						<liferay-portlet:actionURL name="deletePlace" var="deletePlaceURL">
							<portlet:param name="cmd" value="deletePlace" />
							<portlet:param name="tab" value="places" />
							<portlet:param name="placeId" value="${place.placeId}" />
						</liferay-portlet:actionURL>
						<c:if test="${dc.hasPermission('DELETE_PLACE') and empty themeDisplay.scopeGroup.getStagingGroup()}">
							<liferay-ui:icon message="delete" url="${deletePlaceURL}" />
						</c:if>
					</liferay-ui:icon-menu>
				</liferay-ui:search-container-column-text>

			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator paginate="true" displayStyle="list"
				markupView="lexicon" searchContainer="${dc.searchContainer}" />
		</liferay-ui:search-container>
	</aui:form>
</div>


<liferay-portlet:actionURL name="selectionAction"
	var="deleteSelectionURL">
	<portlet:param name="cmd" value="delete" />
	<portlet:param name="tab" value="places" />
</liferay-portlet:actionURL>
<liferay-portlet:actionURL name="selectionAction"
	var="publishSelectionURL">
	<portlet:param name="cmd" value="publish" />
	<portlet:param name="tab" value="places" />
</liferay-portlet:actionURL>
<liferay-portlet:actionURL name="selectionAction"
	var="unpublishSelectionURL">
	<portlet:param name="cmd" value="unpublish" />
	<portlet:param name="tab" value="places" />
</liferay-portlet:actionURL>
<aui:script>
	function <portlet:namespace />deleteSelection() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-selected-places" />')) {
			var form = AUI.$(document.<portlet:namespace />fm);
			var selectionIdsInput = document
					.getElementsByName('<portlet:namespace />selectionIds')[0];
			selectionIdsInput.value = Liferay.Util.listCheckedExcept(form,
					'<portlet:namespace />allRowIds');

			submitForm(form, '${deleteSelectionURL}');
		}
	}
	function <portlet:namespace />publishSelection() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-publish-selected-places" />')) {
			var form = AUI.$(document.<portlet:namespace />fm);
			var selectionIdsInput = document
					.getElementsByName('<portlet:namespace />selectionIds')[0];
			selectionIdsInput.value = Liferay.Util.listCheckedExcept(form,
					'<portlet:namespace />allRowIds');

			submitForm(form, '${publishSelectionURL}');
		}
	}
	function <portlet:namespace />unpublishSelection() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-unpublish-selected-places" />')) {
			var form = AUI.$(document.<portlet:namespace />fm);
			var selectionIdsInput = document
					.getElementsByName('<portlet:namespace />selectionIds')[0];
			selectionIdsInput.value = Liferay.Util.listCheckedExcept(form,
					'<portlet:namespace />allRowIds');

			submitForm(form, '${unpublishSelectionURL}');
		}
	}
</aui:script>