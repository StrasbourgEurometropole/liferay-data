<%@ include file="/campaign-init.jsp"%>

<liferay-portlet:renderURL var="campaignEventsURL">
</liferay-portlet:renderURL>

<liferay-portlet:actionURL varImpl="searchURL">
	<liferay-portlet:param name="statusId" value="${dc.statusId}" />
	<liferay-portlet:param name="themeId" value="${dc.themeId}" />
</liferay-portlet:actionURL>


<liferay-portlet:renderURL varImpl="updateStatusURL">
	<liferay-portlet:param name="mvcPath"
		value="/campaign-update-status.jsp" />
</liferay-portlet:renderURL>
<liferay-portlet:actionURL varImpl="deleteSelectionURL" name="deleteCampaignEvent">
</liferay-portlet:actionURL>

<liferay-portlet:renderURL varImpl="addCampaignEventURL">
	<portlet:param name="cmd" value="editCampaignEvent" />
	<portlet:param name="mvcPath" value="/campaign-edit.jsp" />
	<portlet:param name="returnURL" value="${campaignEventsURL}" />
</liferay-portlet:renderURL>

<div class="big-button">
	<aui:button href="${addCampaignEventURL}" type="button"
		value="add-campaign-event" primary="true" />
</div>

<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">
	<aui:nav-bar-search>
		<aui:form action="${searchURL}" name="searchFm">
			<liferay-ui:input-search markupView="lexicon" />
		</aui:form>
	</aui:nav-bar-search>
</aui:nav-bar>

<liferay-frontend:management-bar includeCheckBox="true"
	searchContainerId="campaignEventsSearchContainer">
	
	<liferay-frontend:management-bar-filters>
		<liferay-frontend:management-bar-navigation
			label="${dc.statusId ge 0 ? dc.statuses.get(dc.statusId) : 'filter-by-status'}">
			<liferay-portlet:renderURL var="anyStatusURL">
				<liferay-portlet:param name="statusId" value="" />
				<liferay-portlet:param name="themeId" value="${dc.themeId}" />
				<liferay-portlet:param name="keywords" value="${dc.keywords}" />
			</liferay-portlet:renderURL>
			<liferay-frontend:management-bar-filter-item
				label="n'importe lequel"
				url="${anyStatusURL}" />
			<c:forEach var="status" items="${dc.statuses}">
				<liferay-portlet:renderURL var="statusFilterURL">
					<liferay-portlet:param name="statusId" value="${status.key}" />
					<liferay-portlet:param name="themeId" value="${dc.themeId}" />
					<liferay-portlet:param name="keywords" value="${dc.keywords}" />
				</liferay-portlet:renderURL>
				<c:set var="isActive"
					value="${dc.statusId eq status.key}" />
				<liferay-frontend:management-bar-filter-item
					label="${status.value}" url="${statusFilterURL}" active="${isActive}" />
			</c:forEach>
		</liferay-frontend:management-bar-navigation>
		<liferay-frontend:management-bar-navigation
			label="${dc.themeId gt 0 ? dc.themeLabel : 'filter-by-theme'}">
			<liferay-portlet:renderURL var="anyThemeURL">
				<liferay-portlet:param name="statusId" value="${dc.statusId}" />
				<liferay-portlet:param name="themeId" value="" />
				<liferay-portlet:param name="keywords" value="${dc.keywords}" />
			</liferay-portlet:renderURL>
			<liferay-frontend:management-bar-filter-item
				label="n'importe lequel"
				url="${anyThemeURL}" />
			<c:forEach var="theme" items="${dc.themes}">
				<liferay-portlet:renderURL var="themeFilterURL">
					<liferay-portlet:param name="statusId" value="${dc.statusId}" />
					<liferay-portlet:param name="themeId" value="${theme.categoryId}" />
					<liferay-portlet:param name="keywords" value="${dc.keywords}" />
				</liferay-portlet:renderURL>
				<c:set var="isActive"
					value="${dc.themeId eq theme.categoryId}" />
				<liferay-frontend:management-bar-filter-item
					label="${theme.getTitle(locale)}" url="${themeFilterURL}" active="${isActive}" />
			</c:forEach>
		</liferay-frontend:management-bar-navigation>
	</liferay-frontend:management-bar-filters>

	<c:if test="${dc.isUserAManager()}">
		<liferay-frontend:management-bar-action-buttons>
			<liferay-frontend:management-bar-button
				href='<%="javascript:" + renderResponse.getNamespace() + "updateSelectionStatus(1);"%>'
				icon="check" label="submit-to-validation" />
			<liferay-frontend:management-bar-button
				href='<%="javascript:" + renderResponse.getNamespace() + "updateSelectionStatus(0);"%>'
				icon="thumbs-up" label="approve" />
			<liferay-frontend:management-bar-button
				href='<%="javascript:" + renderResponse.getNamespace() + "updateSelectionStatus(4);"%>'
				icon="thumbs-down" label="deny" />
			<liferay-frontend:management-bar-button
				href='<%="javascript:" + renderResponse.getNamespace() + "updateSelectionStatus(8);"%>'
				icon="times" label="request-deletion" />
			<liferay-frontend:management-bar-button
				href='<%="javascript:" + renderResponse.getNamespace() + "deleteSelection();"%>'
				icon="trash" label="delete" />
			<liferay-frontend:management-bar-button
				href='<%="javascript:" + renderResponse.getNamespace() + "updateSelectionStatus(-1);"%>'
				icon="undo" label="deny-deletion" />
		</liferay-frontend:management-bar-action-buttons>
	</c:if>
</liferay-frontend:management-bar>

<div class="container-fluid-1280 main-content-body">
	<aui:form method="post" name="fm">
		<aui:input type="hidden" name="selectionIds" />
		<aui:input type="hidden" name="campaignEventId" />
		<aui:input type="hidden" name="newStatus" />
		<liferay-ui:search-container id="campaignEventsSearchContainer"
			searchContainer="${dc.searchContainer}">
			<liferay-ui:search-container-results results="${dc.campaignEvents}" />

			<liferay-ui:search-container-row
				className="eu.strasbourg.service.agenda.model.CampaignEvent"
				modelVar="campaignEvent" keyProperty="campaignEventId"
				rowIdProperty="campaignEventId">
				<liferay-portlet:renderURL varImpl="editCampaignEventURL">
					<portlet:param name="cmd" value="editCampaignEvent" />
					<portlet:param name="campaignEventId"
						value="${campaignEvent.campaignEventId}" />
					<portlet:param name="returnURL" value="${campaignEventsURL}" />
					<portlet:param name="mvcPath" value="/campaign-edit.jsp" />
				</liferay-portlet:renderURL>
				<liferay-portlet:actionURL varImpl="deleteURL"
					name="deleteCampaignEvent">
					<portlet:param name="campaignEventId"
						value="${campaignEvent.campaignEventId}" />
				</liferay-portlet:actionURL>

				<liferay-ui:search-container-column-text cssClass="content-column"
					href="${editCampaignEventURL}" name="title" truncate="true"
					orderable="true" value="${campaignEvent.titleCurrentValue}" />

				<liferay-ui:search-container-column-text
					cssClass="content-column table-cell-content" name="campaign">
					${campaignEvent.campaign.getTitle(locale)}
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					cssClass="content-column table-cell-content" name="theme">
					<c:forEach var="theme" items="${campaignEvent.themes}" varStatus="themeStatus">
						<c:if test="${themeStatus.index gt 0}">
						 - 
						</c:if>
						${theme.getTitle(locale)}
					</c:forEach>
					
				</liferay-ui:search-container-column-text>
				<liferay-ui:search-container-column-text
					cssClass="content-column table-cell-content" name="service">
					${campaignEvent.getServiceName(locale)}
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					cssClass="content-column table-cell-content" name="status">
					<span class="${campaignEvent.lastStatus.statusLabel}"><liferay-ui:message
							key="${campaignEvent.lastStatus.statusLabel}" /></span>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text>
					<c:set var="namespace" value="<%=renderResponse.getNamespace() %>" />
					<liferay-ui:icon-menu markupView="lexicon">
						<liferay-ui:icon icon="edit" message="edit"
							url="${editCampaignEventURL}" />
						<c:if test="${campaignEvent.status eq 2}">
							<liferay-ui:icon icon="check" message="submit-to-validation"
								url="javascript:${namespace}updateCampaignEventStatus(${campaignEvent.campaignEventId}, 1)" />
						</c:if>
						<c:if
							test="${campaignEvent.status eq 1 and campaignEvent.isUserManagerOfTheEvent(themeDisplay.userId)}">
							<liferay-ui:icon icon="thumbs-up" message="approve"
								url="javascript:${namespace}updateCampaignEventStatus(${campaignEvent.campaignEventId}, 0)" />
						</c:if>
						<c:if
							test="${campaignEvent.status eq 1 and campaignEvent.isUserManagerOfTheEvent(themeDisplay.userId)}">
							<liferay-ui:icon icon="thumbs-down" message="deny"
								url="javascript:${namespace}updateCampaignEventStatus(${campaignEvent.campaignEventId}, 4)" />
						</c:if>
						<c:if
							test="${campaignEvent.status eq 8 and campaignEvent.isUserManagerOfTheEvent(themeDisplay.userId)}">
							<liferay-ui:icon icon="trash" message="delete" url="${deleteURL}" />
							<liferay-ui:icon icon="undo" message="deny-deletion"
								url="javascript:${namespace}updateCampaignEventStatus(${campaignEvent.campaignEventId}, -1)" />
						</c:if>
						<liferay-ui:icon icon="times" message="request-deletion"
							url="javascript:${namespace}updateCampaignEventStatus(${campaignEvent.campaignEventId}, 8)" />
							
						<c:if test="${campaignEvent.status eq 2}">
							<liferay-portlet:actionURL name="duplicateCampaignEvent" var="duplicateCampaignEventURL">
								<liferay-portlet:param name="campaignEventId" value="${campaignEvent.campaignEventId}" />
							</liferay-portlet:actionURL>
							<liferay-ui:icon icon="copy" message="duplicate" url="${duplicateCampaignEventURL}" />
						</c:if>
					</liferay-ui:icon-menu>
				</liferay-ui:search-container-column-text>

			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator paginate="true" displayStyle="list"
				markupView="lexicon" searchContainer="${dc.searchContainer}" />
		</liferay-ui:search-container>
	</aui:form>

	<aui:button-row>
		<aui:button href="${addCampaignEventURL}" type="button"
			value="add-campaign-event" primary="true" />
	</aui:button-row>
</div>

<aui:script>
	function <portlet:namespace />deleteSelection() {
		var form = AUI.$(document.<portlet:namespace />fm);
		var selectionIdsInput = document
				.getElementsByName('<portlet:namespace />selectionIds')[0];
		selectionIdsInput.value = Liferay.Util.listCheckedExcept(form,
				'<portlet:namespace />allRowIds');

		submitForm(form, '${deleteSelectionURL}');
	}
	function <portlet:namespace />updateSelectionStatus(newStatus) {
		var form = AUI.$(document.<portlet:namespace />fm);
		var selectionIdsInput = document
				.getElementsByName('<portlet:namespace />selectionIds')[0];
		var newStatusInput = document
				.getElementsByName('<portlet:namespace />newStatus')[0];

		selectionIdsInput.value = Liferay.Util.listCheckedExcept(form,
				'<portlet:namespace />allRowIds');
		newStatusInput.value = newStatus;

		submitForm(form, '${updateStatusURL}');
	}
	function <portlet:namespace />updateCampaignEventStatus(campaignEventId,
			newStatus) {
		var form = AUI.$(document.<portlet:namespace />fm);
		var selectionIdsInput = document
				.getElementsByName('<portlet:namespace />selectionIds')[0];
		var newStatusInput = document
				.getElementsByName('<portlet:namespace />newStatus')[0];
		var campaignEventIdInput = document
				.getElementsByName('<portlet:namespace />campaignEventId')[0];

		selectionIdsInput.value = Liferay.Util.listCheckedExcept(form,
				'<portlet:namespace />allRowIds');
		newStatusInput.value = newStatus;
		campaignEventIdInput.value = campaignEventId;

		submitForm(form, '${updateStatusURL}');
	}
</aui:script>