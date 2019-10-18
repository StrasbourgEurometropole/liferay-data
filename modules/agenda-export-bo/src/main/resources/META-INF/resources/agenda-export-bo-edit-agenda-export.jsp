<%@ include file="/agenda-export-bo-init.jsp"%>
<%@page import="eu.strasbourg.service.agenda.model.AgendaExport"%>
<c:set var="agendaExport" value="${dc.agendaExport}" />
<c:set var="toExport" value="${dc.toExport}" />

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

<liferay-portlet:resourceURL var="exportAgendaExportURL" id="exportAgendaExport">
</liferay-portlet:resourceURL>

<%--
<liferay-portlet:resourceURL var="exportXlsxURL" id="exportXlsx">
</liferay-portlet:resourceURL>
<form method="POST" action="${exportXlsxURL}">
   <aui:input type="hidden" name="eventIds" value="${dc.allEventIds}" />
   <aui:button-row>
       <aui:button cssClass="btn-lg" type="submit"
           value="export-xlsx" />
   </aui:button-row>
</form>
--%>

<div class="container-fluid-1280 main-content-body">
    <aui:form action="${(toExport eq true) ? exportAgendaExportURL : saveAgendaExportURL}" method="POST" name="fm">
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

           <aui:fieldset collapsed="true" collapsible="true" label="eu.period">
                <div id="date-fields">
                    <c:forEach items="${dc.getOrCreateAgendaExportPeriods()}" var="period" varStatus="status">
                        <div class="lfr-form-row lfr-form-row-inline">
                            <div class="row-fields">
                                <fmt:formatDate value="${period.startDate}" pattern="dd/MM/YYYY" type="date" var="formattedStartDate"/>
                                <fmt:formatDate value="${period.endDate}" pattern="dd/MM/YYYY" type="date" var="formattedEndDate"/>
                                <liferay-util:include page="/includes/period-row.jsp" servletContext="<%=application %>">
                                    <liferay-util:param name="index" value="0" />
                                    <liferay-util:param name="startDate" value="${formattedStartDate}" />
                                    <liferay-util:param name="endDate" value="${formattedEndDate}" />
                                </liferay-util:include>
                            </div>
                        </div>
                    </c:forEach>
                </div>
           </aui:fieldset>

           <aui:fieldset collapsed="true" collapsible="true" label="Categories">
                <c:forEach var="vocabulary" items="${dc.eventVocabularies}" varStatus="status">
                    <label><span>${vocabulary.getTitle(locale)}</span></label>
                    <select
                        id="vocabulary${status.index}"
                        class="choices-element"
                        name="<portlet:namespace />vocabulary_${status.index}_select"
                        placeholder="<liferay-ui:message key="select-multiple" />"
                    multiple>
                        <c:forEach var="category" items="${vocabulary.categories}">
                            <option
                                value="${category.categoryId}"
                                <c:if test="${fn:contains(dc.getSavedCategoriesByVocabulary(vocabulary.vocabularyId), Long.toString(category.categoryId) )}">
                                    selected="selected"
                                </c:if>
                            >
                                ${category.getTitle(locale)}
                            </option>
                        </c:forEach>
                    </select>
                    <aui:input
                        type="hidden"
                        name="vocabulary_${status.index}_id"
                        value="${vocabulary.vocabularyId}"
                    />
                </c:forEach>
                 <aui:input type="hidden" name="vocabulary_number" value="${dc.eventVocabularies.size()}"/>
           </aui:fieldset>

           <aui:fieldset collapsed="true" collapsible="true" label="Tags">
               <aui:input name="tags" type="assetTags" />
           </aui:fieldset>

           <aui:fieldset collapsed="true" collapsible="true" label="eu.language">
                <aui:select name="language" label="detail-target-site">
                    <c:forEach var="language" items="${dc.languageList}">
                        <aui:option
                            value="${language}"
                            selected="${language eq dc.agendaExport.language ? true : false}"
                        >
                            ${language}
                        </aui:option>
                    </c:forEach>
                </aui:select>
           </aui:fieldset>

           <aui:fieldset collapsible="true" label="eu.export-format">
               <aui:select name="exportFormat" label="export-target-format">
                   <c:forEach var="format" items="${dc.formatExportList}">
                       <aui:option
                           value="${format}"
                           selected="${format eq dc.agendaExport.exportFormat ? true : false}"
                       >
                           ${format}
                       </aui:option>
                   </c:forEach>
               </aui:select>
          </aui:fieldset>
		</aui:fieldset-group>
		
		<aui:button-row>
            <c:if test="${toExport ne true}">
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
            </c:if>
            <c:if test="${toExport eq true}">
                <%--<aui:button id="export-btn" cssClass="btn-lg" type="button" value="eu.export"/>--%>
                <aui:button id="export-btn" cssClass="btn-lg" type="submit" value="eu.export"/>
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
    <script src="/o/agendaexportbo/js/vendors/choices.min.js"
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