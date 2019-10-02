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

<liferay-portlet:actionURL name="exportAgendaExport" varImpl="exportAgendaExportURL">
	<portlet:param name="cmd" value="exportAgendaExport" />
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


           <aui:fieldset collapsed="true" collapsible="true"
                label="eu.dates-and-times">

                <div class="event-periods-title">
                    <p class="control-label"><liferay-ui:message key="event-period-creation" /></p>
                </div>

                <div class="add-dates-section">
                    <aui:button id="periodGenerator" cssClass="date-range" name="periodGenerator" value="select-period-dates" />
                </div>

                <div class="change-times-section">
                    <div class="event-periods-title">
                        <p class="control-label"><liferay-ui:message key="update-current-language-times" /></p>
                    </div>
                    <div class="time-detail-generator-wrapper">
                        <aui:input type="text" name="timeDetailGenerator" label="event-times" inlineField="true" helpMessage="event-times-help"/>
                    </div>
                    <aui:button id="changeTimes" name="changeTimes" value="update-times" />
                </div>

                <div class="event-periods-title">
                    <p class="control-label"><liferay-ui:message key="event-periods" /></p>
                </div>
                <div id="date-fields">
                    <div class="lfr-form-row lfr-form-row-inline">
                        <div class="row-fields">
                            <liferay-util:include page="/includes/period-row.jsp" servletContext="<%=application %>">
                                <liferay-util:param name="index" value="0" />
                            </liferay-util:include>
                        </div>
                    </div>

                    <c:forEach items="${dc.agendaExport.agendaExportPeriods}" var="period" varStatus="status">
                        <div class="lfr-form-row lfr-form-row-inline">
                            <div class="row-fields">
                                <fmt:formatDate value="${period.startDate}" pattern="dd/MM/YYYY" type="date" var="formattedStartDate"/>
                                <fmt:formatDate value="${period.endDate}" pattern="dd/MM/YYYY" type="date" var="formattedEndDate"/>
                                <liferay-util:include page="/includes/period-row.jsp" servletContext="<%=application %>">
                                    <liferay-util:param name="index" value="${status.count}" />
                                    <liferay-util:param name="startDate" value="${formattedStartDate}" />
                                    <liferay-util:param name="endDate" value="${formattedEndDate}" />
                                </liferay-util:include>
                            </div>
                        </div>
                    </c:forEach>
                    <aui:input type="hidden" name="periodIndexes" value="${dc.defaultPeriodIndexes}" />
                </div>

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
			<aui:button cssClass="btn-lg" href="${exportAgendaExportURL}" value="eu.export"/>
			<c:if test="${not empty agendaExport and dc.hasPermission('DELETE_AGENDA_EXPORT') and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<aui:button cssClass="btn-lg" onClick='<%=renderResponse.getNamespace() + "deleteEntity();"%>' type="cancel"
					value="delete" />
			</c:if>
			<aui:button cssClass="btn-lg" href="${param.returnURL}" type="cancel" />
		</aui:button-row>
	</aui:form>
</div>

<liferay-util:html-top>
	<script>
        var getPeriodRowJSPURL = '${periodRowURL}';
        var getSlotRowJSPURL = '${slotRowURL}';
        var getAttendanceRowJSPURL = '${attendanceRowURL}';
        var getScheduleExceptionRowJSPURL = '${scheduleExceptionRowURL}';
	</script>
</liferay-util:html-top>
<liferay-util:html-bottom>
    <!-- Include Choices CSS -->
	<link rel="stylesheet" href="/o/agendaexportbo/css/vendors/choices.min.css">
	<link rel="stylesheet" href="/o/agendaexportbo/css/vendors/daterangepicker.css">
	<!-- Include Choices JavaScript -->
	<script>
		define._amd = define.amd;
		define.amd = false;
	</script>
	<script src="/o/agendaexportbo/js/vendors/moment.min.js"
		type="text/javascript"></script>
	<script
		src="/o/agendaexportbo/js/vendors/daterangepicker.js"
		type="text/javascript"></script>
	<script
		src="/o/agendaexportbo/js/vendors/jquery.autocomplete.js"
		type="text/javascript"></script>
	<script>
		define.amd = define._amd;
	</script>
	<script
		src="/o/agendaexportbo/js/agenda-export-bo-edit-event.js"
		type="text/javascript"></script>
</liferay-util:html-bottom>

<aui:script>
	function <portlet:namespace />deleteEntity() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-this-entry" />')) {
			window.location = '${deleteAgendaExportURL}';
		}
	}
</aui:script>