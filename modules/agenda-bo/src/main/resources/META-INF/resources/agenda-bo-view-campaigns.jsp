<%@ include file="/agenda-bo-init.jsp"%>

<liferay-portlet:renderURL varImpl="campaignsURL">
	<portlet:param name="tab" value="campaigns" />
	<portlet:param name="orderByCol" value="${dc.orderByCol}" />
	<portlet:param name="orderByType" value="${dc.orderByType}" />
	<portlet:param name="filterCategoriesIds"
		value="${dc.filterCategoriesIds}" />
	<portlet:param name="keywords" value="${dc.keywords}" />
	<portlet:param name="delta" value="${dc.searchContainer.delta}" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL varImpl="addCampaignURL">
	<portlet:param name="cmd" value="editCampaign" />
	<portlet:param name="mvcPath" value="/agenda-bo-edit-campaign.jsp" />
	<portlet:param name="returnURL" value="${campaignsURL}" />
</liferay-portlet:renderURL>

<liferay-frontend:management-bar includeCheckBox="true"
	searchContainerId="campaignsSearchContainer">

		<liferay-frontend:management-bar-filters>
			<c:if test="${fn:length(dc.globalVocabularies) > 0}">
				<li><a>Filtrer par :</a></li>
			</c:if>
			<c:forEach var="vocabulary" items="${dc.globalVocabularies}">
				<liferay-frontend:management-bar-filter 
					managementBarFilterItems="${dc.getManagementBarFilterItems(vocabulary)}" 
					value="${dc.getVocabularyFilterLabel(vocabulary)}" />
			</c:forEach>

			<liferay-frontend:management-bar-sort orderByCol="${dc.orderByCol}"
				orderByType="${dc.orderByType}"
				orderColumns='<%= new String[] {"title", "modified-date", "status"} %>'
				portletURL="${campaignsURL}" />
		</liferay-frontend:management-bar-filters>

		<liferay-frontend:management-bar-action-buttons>
			<c:if test="${not dc.workflowEnabled}">
				<c:if
					test="${dc.hasPermission('EDIT_CAMPAIGN') and empty themeDisplay.scopeGroup.getStagingGroup()}">
					<liferay-frontend:management-bar-button
						href='<%="javascript:" + renderResponse.getNamespace() + "publishSelection();"%>'
						icon="check" label="publish" />
					<liferay-frontend:management-bar-button
						href='<%="javascript:" + renderResponse.getNamespace() + "unpublishSelection();"%>'
						icon="times" label="unpublish" />
				</c:if>
			</c:if>
			<c:if
				test="${dc.hasPermission('DELETE_CAMPAIGN') and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<liferay-frontend:management-bar-button
					href='<%="javascript:" + renderResponse.getNamespace() + "deleteSelection();"%>'
					icon="trash" label="delete" />
			</c:if>
		</liferay-frontend:management-bar-action-buttons>
</liferay-frontend:management-bar>

<div class="container-fluid-1280 main-content-body">
	<aui:form method="post" name="fm">
		<aui:input type="hidden" name="selectionIds" />
		<liferay-ui:search-container id="campaignsSearchContainer"
			searchContainer="${dc.searchContainer}">
			<liferay-ui:search-container-results results="${dc.campaigns}" />

			<liferay-ui:search-container-row
				className="eu.strasbourg.service.agenda.model.Campaign"
				modelVar="campaign" keyProperty="campaignId"
				rowIdProperty="campaignId">
				<liferay-portlet:renderURL varImpl="editCampaignURL">
					<portlet:param name="cmd" value="editCampaign" />
					<portlet:param name="campaignId" value="${campaign.campaignId}" />
					<portlet:param name="returnURL" value="${campaignsURL}" />
					<portlet:param name="mvcPath" value="/agenda-bo-edit-campaign.jsp" />
				</liferay-portlet:renderURL>

				<liferay-ui:search-container-column-text cssClass="content-column"
					href="${editCampaignURL}" name="title" truncate="true"
					orderable="true" value="${campaign.titleCurrentValue}" />

				<liferay-ui:search-container-column-text cssClass="content-column"
					name="themes" truncate="true">
					<c:forEach var="theme" items="${campaign.themes}" varStatus="themeStatus">
						<c:if test="${themeStatus.index > 0}"> - </c:if>
						${theme.getTitle(locale)}
					</c:forEach>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text name="export">
					<c:choose>
						<c:when test="${campaign.exportEnabled}">
							<liferay-ui:message key="yes" />
						</c:when>
						<c:otherwise>
							<liferay-ui:message key="no" />
						</c:otherwise>
					</c:choose>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text name="status">
					<aui:workflow-status markupView="lexicon" showIcon="false"
						showLabel="false" status="${campaign.status}" />
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text>
					<liferay-ui:icon-menu markupView="lexicon">
						<c:if
							test="${dc.hasPermission('EDIT_CAMPAIGN') and empty themeDisplay.scopeGroup.getStagingGroup()}">
							<liferay-ui:icon message="edit" url="${editCampaignURL}" />
						</c:if>

						<liferay-portlet:actionURL name="deleteCampaign"
							var="deleteCampaignURL">
							<portlet:param name="cmd" value="deleteCampaign" />
							<portlet:param name="tab" value="campaigns" />
							<portlet:param name="campaignId" value="${campaign.campaignId}" />
						</liferay-portlet:actionURL>
						<c:if
							test="${dc.hasPermission('DELETE_CAMPAIGN') and empty themeDisplay.scopeGroup.getStagingGroup()}">
							<liferay-ui:icon message="delete" url="${deleteCampaignURL}" />
						</c:if>
						
						<liferay-portlet:resourceURL id="exportJson"
							var="exportJsonURL">
							<portlet:param name="campaignId" value="${campaign.campaignId}" />
						</liferay-portlet:resourceURL>
						<liferay-ui:icon message="export-json" url="${exportJsonURL}" />
						
						<liferay-portlet:resourceURL id="exportDocx"
							var="exportDocxURL">
							<portlet:param name="campaignId" value="${campaign.campaignId}" />
						</liferay-portlet:resourceURL>
						<liferay-ui:icon message="export-docx" url="${exportDocxURL}" />
					</liferay-ui:icon-menu>
				</liferay-ui:search-container-column-text>

			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator paginate="true" displayStyle="list"
				markupView="lexicon" searchContainer="${dc.searchContainer}" />
		</liferay-ui:search-container>
	</aui:form>
</div>

<c:if
	test="${dc.hasPermission('ADD_CAMPAIGN') and empty themeDisplay.scopeGroup.getStagingGroup()}">
	<liferay-frontend:add-menu>
		<liferay-frontend:add-menu-item title="Ajouter une campagne"
			url="${addCampaignURL}" />
	</liferay-frontend:add-menu>
</c:if>


<liferay-portlet:actionURL name="selectionAction"
	var="deleteSelectionURL">
	<portlet:param name="cmd" value="delete" />
	<portlet:param name="tab" value="campaigns" />
	<portlet:param name="orderByCol" value="${dc.orderByCol}" />
	<portlet:param name="orderByType" value="${dc.orderByType}" />
	<portlet:param name="filterCategoriesIds"
		value="${dc.filterCategoriesIds}" />
	<portlet:param name="keywords" value="${dc.keywords}" />
	<portlet:param name="delta" value="${dc.searchContainer.delta}" />
</liferay-portlet:actionURL>
<liferay-portlet:actionURL name="selectionAction"
	var="publishSelectionURL">
	<portlet:param name="cmd" value="publish" />
	<portlet:param name="tab" value="campaigns" />
	<portlet:param name="orderByCol" value="${dc.orderByCol}" />
	<portlet:param name="orderByType" value="${dc.orderByType}" />
	<portlet:param name="filterCategoriesIds"
		value="${dc.filterCategoriesIds}" />
	<portlet:param name="keywords" value="${dc.keywords}" />
	<portlet:param name="delta" value="${dc.searchContainer.delta}" />
</liferay-portlet:actionURL>
<liferay-portlet:actionURL name="selectionAction"
	var="unpublishSelectionURL">
	<portlet:param name="cmd" value="unpublish" />
	<portlet:param name="tab" value="campaigns" />
	<portlet:param name="orderByCol" value="${dc.orderByCol}" />
	<portlet:param name="orderByType" value="${dc.orderByType}" />
	<portlet:param name="filterCategoriesIds"
		value="${dc.filterCategoriesIds}" />
	<portlet:param name="keywords" value="${dc.keywords}" />
	<portlet:param name="delta" value="${dc.searchContainer.delta}" />
</liferay-portlet:actionURL>

<aui:script>
	function <portlet:namespace />deleteSelection() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-selected-entries" />')) {
			var form = AUI.$(document.<portlet:namespace />fm);
			var selectionIdsInput = document
					.getElementsByName('<portlet:namespace />selectionIds')[0];
			selectionIdsInput.value = Liferay.Util.listCheckedExcept(form,
					'<portlet:namespace />allRowIds');

			submitForm(form, '${deleteSelectionURL}');
		}
	}
	function <portlet:namespace />publishSelection() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-publish-selected-entries" />')) {
			var form = AUI.$(document.<portlet:namespace />fm);
			var selectionIdsInput = document
					.getElementsByName('<portlet:namespace />selectionIds')[0];
			selectionIdsInput.value = Liferay.Util.listCheckedExcept(form,
					'<portlet:namespace />allRowIds');

			submitForm(form, '${publishSelectionURL}');
		}
	}
	function <portlet:namespace />unpublishSelection() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-unpublish-selected-entries" />')) {
			var form = AUI.$(document.<portlet:namespace />fm);
			var selectionIdsInput = document
					.getElementsByName('<portlet:namespace />selectionIds')[0];
			selectionIdsInput.value = Liferay.Util.listCheckedExcept(form,
					'<portlet:namespace />allRowIds');

			submitForm(form, '${unpublishSelectionURL}');
		}
	}
</aui:script>