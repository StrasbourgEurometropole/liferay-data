<%@ include file="/place-bo-init.jsp"%>
<%@page import="eu.strasbourg.service.place.model.Price"%>

<liferay-portlet:renderURL varImpl="pricesURL">
	<portlet:param name="tab" value="prices" />
	<portlet:param name="delta" value="${dc.searchContainer.delta}" />
</liferay-portlet:renderURL>
<liferay-portlet:renderURL varImpl="addPriceURL">
	<portlet:param name="cmd" value="editPrice" />
	<portlet:param name="mvcPath" value="/place-bo-edit-price.jsp" />
	<portlet:param name="returnURL" value="${pricesURL}" />
</liferay-portlet:renderURL>

<liferay-frontend:management-bar includeCheckBox="true"
	searchContainerId="pricesSearchContainer">

		<liferay-frontend:management-bar-action-buttons>
			<c:if test="${empty themeDisplay.scopeGroup.getStagingGroup()}">
				<liferay-frontend:management-bar-button
					href='<%="javascript:" + renderResponse.getNamespace() + "publishSelection();"%>'
					icon="check" label="publish" />
				<liferay-frontend:management-bar-button
					href='<%="javascript:" + renderResponse.getNamespace() + "unpublishSelection();"%>'
					icon="times" label="unpublish" />
				<liferay-frontend:management-bar-button
				href='<%="javascript:" + renderResponse.getNamespace() + "deleteSelection();"%>'
				icon="trash" label="delete" />
			</c:if>
			
		</liferay-frontend:management-bar-action-buttons>
</liferay-frontend:management-bar>

<div class="container-fluid-1280 main-content-body">
	<aui:form method="post" name="fm">
		<aui:input type="hidden" name="selectionIds" />
		<liferay-ui:search-container id="pricesSearchContainer"
			searchContainer="${dc.searchContainer}">
			<liferay-ui:search-container-results results="${dc.prices}" /> 

			<liferay-ui:search-container-row
				className="eu.strasbourg.service.place.model.Price" modelVar="price"
				keyProperty="priceId" rowIdProperty="priceId">
				<liferay-portlet:renderURL varImpl="editPriceURL">
					<portlet:param name="cmd" value="editPrice" />
					<portlet:param name="priceId" value="${price.priceId}" />
					<portlet:param name="returnURL" value="${pricesURL}" />
					<portlet:param name="mvcPath" value="/place-bo-edit-price.jsp" />
				</liferay-portlet:renderURL>

				<liferay-ui:search-container-column-text cssClass="content-column"
					href="${editPriceURL}" name="title" truncate="true" orderable="true"
					value="${price.titleCurrentValue}" />

				<liferay-ui:search-container-column-text name="status">
					<aui:workflow-status markupView="lexicon" showIcon="false"
						showLabel="false" status="${price.status}" />
				</liferay-ui:search-container-column-text>


				<liferay-ui:search-container-column-text>
					<liferay-ui:icon-menu markupView="lexicon">
						<c:if test="${empty themeDisplay.scopeGroup.getStagingGroup()}">
							<liferay-ui:icon message="edit" url="${editPriceURL}" />
						</c:if>

						<liferay-portlet:actionURL name="deletePrice" var="deletePriceURL">
							<portlet:param name="cmd" value="deletePrice" />
							<portlet:param name="tab" value="prices" />
							<portlet:param name="priceId" value="${price.priceId}" />
						</liferay-portlet:actionURL>
						<c:if test="${empty themeDisplay.scopeGroup.getStagingGroup()}">
							<liferay-ui:icon message="delete" url="${deletePriceURL}" />
						</c:if>
					</liferay-ui:icon-menu>
				</liferay-ui:search-container-column-text>

			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator paginate="true" displayStyle="list"
				markupView="lexicon" searchContainer="${dc.searchContainer}" />
		</liferay-ui:search-container>
	</aui:form>
</div>

<liferay-frontend:add-menu>

	<c:if test="${empty themeDisplay.scopeGroup.getStagingGroup()}"> 
		<liferay-frontend:add-menu-item title="Ajouter un tarif" url="${addPriceURL}" />
	 </c:if>
</liferay-frontend:add-menu>

<liferay-portlet:actionURL name="selectionAction"
	var="deleteSelectionURL">
	<portlet:param name="cmd" value="delete" />
	<portlet:param name="tab" value="prices" />
</liferay-portlet:actionURL>
<liferay-portlet:actionURL name="selectionAction"
	var="publishSelectionURL">
	<portlet:param name="cmd" value="publish" />
	<portlet:param name="tab" value="prices" />
</liferay-portlet:actionURL>
<liferay-portlet:actionURL name="selectionAction"
	var="unpublishSelectionURL">
	<portlet:param name="cmd" value="unpublish" />
	<portlet:param name="tab" value="prices" />
</liferay-portlet:actionURL>
<aui:script>
	function <portlet:namespace />deleteSelection() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-selected-prices" />')) {
			var form = AUI.$(document.<portlet:namespace />fm);
			var selectionIdsInput = document
					.getElementsByName('<portlet:namespace />selectionIds')[0];
			selectionIdsInput.value = Liferay.Util.listCheckedExcept(form,
					'<portlet:namespace />allRowIds');

			submitForm(form, '${deleteSelectionURL}');
		}
	}
	function <portlet:namespace />publishSelection() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-publish-selected-prices" />')) {
			var form = AUI.$(document.<portlet:namespace />fm);
			var selectionIdsInput = document
					.getElementsByName('<portlet:namespace />selectionIds')[0];
			selectionIdsInput.value = Liferay.Util.listCheckedExcept(form,
					'<portlet:namespace />allRowIds');

			submitForm(form, '${publishSelectionURL}');
		}
	}
	function <portlet:namespace />unpublishSelection() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-unpublish-selected-prices" />')) {
			var form = AUI.$(document.<portlet:namespace />fm);
			var selectionIdsInput = document
					.getElementsByName('<portlet:namespace />selectionIds')[0];
			selectionIdsInput.value = Liferay.Util.listCheckedExcept(form,
					'<portlet:namespace />allRowIds');

			submitForm(form, '${unpublishSelectionURL}');
		}
	}
</aui:script>