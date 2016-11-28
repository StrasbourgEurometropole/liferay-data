<%@ include file="/agenda-bo-init.jsp"%>
<%@page import="eu.strasbourg.service.agenda.model.Event"%>

<liferay-util:html-bottom>
	<script>
		define._amd = define.amd;
		define.amd = false;
	</script>
	<script src="//cdn.jsdelivr.net/momentjs/latest/moment.min.js"
		type="text/javascript"></script>
	<script
		src="//cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.js"
		type="text/javascript"></script>
	<script>
		define.amd = define._amd;
	</script>
	<script
		src="/o/agendabo/js/agenda-bo-main.js"
		type="text/javascript"></script>
</liferay-util:html-bottom>

<liferay-portlet:renderURL varImpl="eventsURL">
	<portlet:param name="tab" value="events" />
</liferay-portlet:renderURL>

<liferay-portlet:actionURL name="deleteEvent" var="deleteEventURL">
	<portlet:param name="cmd" value="deleteEvent" />
	<portlet:param name="tab" value="events" />
	<portlet:param name="eventId"
		value="${not empty dc.event ? dc.event.eventId : ''}" />
</liferay-portlet:actionURL>

<liferay-portlet:actionURL name="saveEvent" varImpl="saveEventURL">
	<portlet:param name="cmd" value="saveEvent" />
</liferay-portlet:actionURL>

<div class="container-fluid-1280 main-content-body">
	<aui:form action="${saveEventURL}" method="post" name="fm" onSubmit="validatePeriods(event);">
		<aui:translation-manager availableLocales="${dc.availableLocales}"
			changeableDefaultLanguage="false" defaultLanguageId="${locale}"
			id="translationManager" />

		<aui:model-context bean="${dc.event}" model="<%=Event.class %>" />
		<aui:fieldset-group markupView="lexicon">
			<aui:input name="eventId" type="hidden" />

			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>"
				label="general">

				<aui:input name="title">
					<aui:validator name="required"
						errorMessage="this-field-is-required" />
				</aui:input>

				<aui:input name="subtitle" />

				<strasbourg-picker:image label="image" name="imageId"
					required="true" value="${dc.event.imageId}" />

				<aui:input name="description" />

			</aui:fieldset>

			<aui:fieldset collapsed="<%=true%>" collapsible="<%=true%>"
				label="categorization">

				<strasbourg-picker:entity label="manifestations"
					name="manifestationsIds" value="${dc.event.manifestationsIds}"
					type="eu.strasbourg.service.agenda.model.Manifestation"
					multiple="true" />

				<aui:input name="categories" type="assetCategories"
					wrapperCssClass="categories-selectors" />

				<!-- Hack pour ajouter une validation sur les vocabulaires obligatoires -->
				<div class="has-error">
					<aui:input type="hidden" name="assetCategoriesValidatorInputHelper"
						value="placeholder">
						<aui:validator name="custom"
							errorMessage="requested-vocabularies-error">
							function (val, fieldNode, ruleValue) {
								var validated = true;
								for (var fieldContent of document.querySelectorAll('.categories-selectors > .field-content')) {
								    if ($(fieldContent).find('.icon-asterisk').length > 0
								    	&& $(fieldContent).find('input[type="hidden"]')[0].value.length == 0) {
								    	validated = false;
								    	break;
								    }
								}
								return validated;
							}
						</aui:validator>
					</aui:input>
				</div>

				<aui:input name="tags" type="assetTags" />

				<liferay-ui:input-asset-links
					className="<%= Event.class.getName() %>"
					classPK="${dc.event.eventId}" />
			</aui:fieldset>

			<aui:fieldset collapsed="<%=true%>" collapsible="<%=true%>"
				label="schedule">
				
				<div class="event-periods-title">
					<p class="text-muted"><liferay-ui:message key="event-period-creation" /></p>
				</div>
				
				<div class="add-dates-section">
					<span class="date-range" id="periodGenerator"><liferay-ui:message key="select-period-dates" /></span>
				</div>
				
				<div class="change-times-section">
					<div class="event-periods-title">
						<p class="text-muted"><liferay-ui:message key="update-current-language-times" /></p>
					</div>
					<div class="time-detail-generator-wrapper">
						<aui:input type="text" name="timeDetailGenerator" label="event-times" inlineField="true" />
					</div>
					<aui:button id="changeTimes" name="changeTimes" value="update-times" />
				</div>
				<div class="event-periods-title">
					<p class="text-muted"><liferay-ui:message key="event-periods" /></p>
				</div>
				<div id="date-fields">
					<div class="lfr-form-row lfr-form-row-inline">
						<div class="row-fields">
							<liferay-util:include page="/includes/period-row.jsp" servletContext="<%=application %>">
								<liferay-util:param name="index" value="0" />
							</liferay-util:include>
						</div>
					</div>
						
					<c:forEach items="${dc.event.eventPeriods}" var="period" varStatus="status">
						<div class="lfr-form-row lfr-form-row-inline">
							<div class="row-fields">
								<fmt:formatDate value="${period.startDate}" pattern="dd/MM/YYYY" type="date" var="formattedStartDate"/>
								<fmt:formatDate value="${period.endDate}" pattern="dd/MM/YYYY" type="date" var="formattedEndDate"/>
								<liferay-util:include page="/includes/period-row.jsp" servletContext="<%=application %>">
									<liferay-util:param name="index" value="${status.count}" />
									<liferay-util:param name="startDate" value="${formattedStartDate}" />
									<liferay-util:param name="endDate" value="${formattedEndDate}" />
									<liferay-util:param name="timeDetail" value="${period.timeDetail}" />
								</liferay-util:include>
							</div>
						</div>
					</c:forEach>
					<aui:input type="hidden" name="periodIndexes" value="${dc.defaultPeriodIndexes}" />
				</div>
			</aui:fieldset>
			
			<aui:fieldset collapsed="<%=true%>" collapsible="<%=true%>"
				label="publication">
				<aui:input name="displayDate" />
			</aui:fieldset>
		</aui:fieldset-group>

		<aui:button-row>
			<c:if
				test="${(dc.hasPermission('ADD_EVENT') and empty dc.event or dc.hasPermission('EDIT_EVENT') and not empty dc.event) and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<aui:input type="hidden" name="workflowAction" value="" />
				<c:if test="${dc.workflowEnabled}">
					<aui:button cssClass="btn-lg" type="submit" value="save" />
				</c:if>
				<c:if test="${not dc.workflowEnabled}">
					<aui:button cssClass="btn-lg" type="submit" name="publish"
						value="publish" />
					<aui:button cssClass="btn-lg btn-default" type="submit"
						name="save-as-draft" value="save-as-draft" />
				</c:if>
			</c:if>
			<c:if
				test="${not empty dc.event and dc.hasPermission('DELETE_EVENT') and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<aui:button cssClass="btn-lg" href="${deleteEventURL}" type="cancel"
					value="delete" />
			</c:if>
			<aui:button cssClass="btn-lg" href="${param.returnURL}" type="cancel" />
		</aui:button-row>

	</aui:form>
	<liferay-portlet:actionURL name="getPeriodRow" var="periodRowURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
		<liferay-portlet:param name="mvcPath" value="/includes/period-row.jsp" />
	</liferay-portlet:actionURL>
	<liferay-util:html-top>
		<script>
			var getPeriodRowJSPURL = '${periodRowURL}';
		</script>
	</liferay-util:html-top>
	
</div>
