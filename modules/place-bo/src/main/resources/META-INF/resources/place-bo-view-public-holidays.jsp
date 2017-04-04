<%@ include file="/place-bo-init.jsp"%>
<%@page import="eu.strasbourg.service.place.model.PublicHoliday"%>

<liferay-portlet:renderURL varImpl="publicHolidaysURL">
	<portlet:param name="tab" value="publicHolidays" />
	<portlet:param name="delta" value="${dc.searchContainer.delta}" />
</liferay-portlet:renderURL>
<liferay-portlet:renderURL varImpl="addPublicHolidayURL">
	<portlet:param name="cmd" value="editPublicHoliday" />
	<portlet:param name="mvcPath" value="/place-bo-edit-public-holiday.jsp" />
	<portlet:param name="returnURL" value="${publicHolidaysURL}" />
</liferay-portlet:renderURL>

<liferay-frontend:management-bar includeCheckBox="true"
	searchContainerId="publicHolidaysURLSearchContainer">

		<liferay-frontend:management-bar-action-buttons>
			<c:if test="${empty themeDisplay.scopeGroup.getStagingGroup()}">
			<liferay-frontend:management-bar-button
				href='<%="javascript:" + renderResponse.getNamespace() + "deleteSelection();"%>'
				icon="trash" label="delete" />
			</c:if>
		</liferay-frontend:management-bar-action-buttons>
</liferay-frontend:management-bar>

<div class="container-fluid-1280 main-content-body">
	<aui:form method="post" name="fm">
		<aui:input type="hidden" name="selectionIds" />
		<liferay-ui:search-container id="publicHolidaysSearchContainer"
			searchContainer="${dc.searchContainer}">
			<liferay-ui:search-container-results results="${dc.publicHolidays}" /> 

			<liferay-ui:search-container-row
				className="eu.strasbourg.service.place.model.PublicHoliday" modelVar="publicHoliday"
				keyProperty="publicHolidayId" rowIdProperty="publicHolidayId">
				<liferay-portlet:renderURL varImpl="editPublicHolidayURL">
					<portlet:param name="cmd" value="editPublicHoliday" />
					<portlet:param name="publicHolidayId" value="${publicHoliday.publicHolidayId}" />
					<portlet:param name="returnURL" value="${publicHolidaysURL}" />
					<portlet:param name="mvcPath" value="/place-bo-edit-public-holiday.jsp" />
				</liferay-portlet:renderURL>

				<liferay-ui:search-container-column-text cssClass="content-column"
					href="${editPublicHolidayURL}" name="name-public-holiday" truncate="true" orderable="true"
					value="${publicHoliday.nameCurrentValue}" />
				
				<fmt:formatDate value="${publicHoliday.date}"
					var="formattedDate" type="date" pattern="dd/MM/yyyy" />
				<liferay-ui:search-container-column-text cssClass="content-column"
					name="date-public-holiday" truncate="true" orderable="true"
					value="${formattedDate}" />
				
				<liferay-ui:search-container-column-text cssClass="content-column"
					name="recurrent" truncate="true" orderable="true"
					value="${publicHoliday.recurrent? 'Oui' : 'Non'}" />


				<liferay-ui:search-container-column-text>
					<liferay-ui:icon-menu markupView="lexicon">
						<c:if test="${empty themeDisplay.scopeGroup.getStagingGroup()}">
							<liferay-ui:icon message="edit" url="${editPublicHolidayURL}" />
						</c:if>

						<liferay-portlet:actionURL name="deletePublicHoliday" var="deletePublicHolidayURL">
							<portlet:param name="cmd" value="deletePublicHoliday" />
							<portlet:param name="tab" value="publicHolidays" />
							<portlet:param name="publicHolidayId" value="${publicHoliday.publicHolidayId}" />
						</liferay-portlet:actionURL>
						<c:if test="${empty themeDisplay.scopeGroup.getStagingGroup()}">
							<liferay-ui:icon message="delete" url="${deletePublicHolidayURL}" />
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
		<liferay-frontend:add-menu-item title="Ajouter un jour férié" url="${addPublicHolidayURL}" />
	 </c:if>
</liferay-frontend:add-menu>

<liferay-portlet:actionURL name="selectionAction"
	var="deleteSelectionURL">
	<portlet:param name="cmd" value="delete" />
	<portlet:param name="tab" value="publicHolidays" />
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
</aui:script>