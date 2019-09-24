<%@ include file="/agenda-export-bo-init.jsp"%>
<%@page import="eu.strasbourg.service.agenda.model.AgendaExport"%>
<c:set var="agendaExport" value="${dc.agendaExport}" />

<liferay-portlet:renderURL varImpl="agendaExportsURL">
	<portlet:param name="tab" value="agendaExports" />
</liferay-portlet:renderURL>

<liferay-portlet:actionURL name="deleteAgendaExport" var="deleteAgendaExportURL">
	<portlet:param name="cmd" value="deleteAgendaExport" />
	<portlet:param name="tab" value="agendaExports" />
	<portlet:param name="agendaExportId"
		value="${not empty agendaExport ? agendaExport.agendaExportId : ''}" />
</liferay-portlet:actionURL>

<liferay-portlet:actionURL name="saveAgendaExport" varImpl="saveAgendaExportURL">
	<portlet:param name="cmd" value="saveAgendaExport" />
	<portlet:param name="tab" value="agendaExports" />
	<portlet:param name="agendaExportId"
		value="${not empty agendaExport ? agendaExport.agendaExportId : ''}" />
</liferay-portlet:actionURL>


<div class="container-fluid-1280 main-content-body">
	<aui:form action="${saveAgendaExportURL}" method="post" name="fm">
		<aui:translation-manager availableLocales="${dc.availableLocales}"
			changeableDefaultLanguage="false" defaultLanguageId="${locale}"
			id="translationManager" />

		<aui:model-context bean="${agendaExport}"
			model="<%=AgendaExport.class %>" />
		<aui:fieldset-group markupView="lexicon">
			<aui:input name="agendaExportId" type="hidden" />

			<aui:fieldset collapsed="false" collapsible="true"
				label="general">

				<aui:input name="title">
					<aui:validator name="required"
						errorMessage="this-field-is-required" />
				</aui:input>

			</aui:fieldset>
		</aui:fieldset-group>
		
		<aui:button-row>
			<c:if test="${(dc.hasPermission('ADD_AGENDA_EXPORT') and empty agendaExport or dc.hasPermission('EDIT_AGENDA_EXPORT') and not empty agendaExport) and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<aui:input type="hidden" name="workflowAction" value="" />
				<c:if test="${dc.workflowEnabled}">
					<aui:button cssClass="btn-lg" type="submit" value="save" />
				</c:if>
				<c:if test="${not dc.workflowEnabled}">
					<aui:button cssClass="btn-lg" type="submit" name="publish"
							value="eu.publish" />
					<aui:button cssClass="btn-lg btn-default" type="submit" name="save-as-draft"
							value="save-as-draft" />
				</c:if>
			</c:if>
			<c:if test="${not empty agendaExport and dc.hasPermission('DELETE_AGENDA_EXPORT') and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<aui:button cssClass="btn-lg" onClick='<%=renderResponse.getNamespace() + "deleteEntity();"%>' type="cancel"
					value="delete" />
			</c:if>
			<aui:button cssClass="btn-lg" href="${param.returnURL}" type="cancel" />
		</aui:button-row>
	</aui:form>
</div>

<aui:script>
	function <portlet:namespace />deleteEntity() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-this-entry" />')) {
			window.location = '${deleteAgendaExportURL}';
		}
	}
</aui:script>