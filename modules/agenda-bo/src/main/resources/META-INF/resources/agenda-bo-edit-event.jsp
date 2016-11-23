<%@ include file="/agenda-bo-init.jsp"%>
<%@page import="eu.strasbourg.service.agenda.model.Event"%>

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
	<aui:form action="${saveEventURL}" method="post" name="fm">
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
				
				<strasbourg-picker:entity label="manifestations" name="manifestationsIds"
					value="${dc.event.manifestationsIds}"
					type="eu.strasbourg.service.agenda.model.Manifestation"
					multiple="true" />
					
				<aui:input name="categories" type="assetCategories" wrapperCssClass="categories-selectors" />
				
				<!-- Hack pour ajouter une validation sur les vocabulaires obligatoires -->
				<div class="has-error">
					<aui:input type="hidden" name="assetCategoriesValidatorInputHelper" value="placeholder">
						<aui:validator name="custom" errorMessage="requested-vocabularies-error">
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
				label="publication">
				<aui:input name="displayDate" />
			</aui:fieldset>
		</aui:fieldset-group>

		<aui:button-row>
			<c:if test="${(dc.hasPermission('ADD_EVENT') and empty dc.event or dc.hasPermission('EDIT_EVENT') and not empty dc.event) and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<aui:input type="hidden" name="workflowAction" value="" />
				<c:if test="${dc.workflowEnabled}">
					<aui:button cssClass="btn-lg" type="submit" value="save" />
				</c:if>
				<c:if test="${not dc.workflowEnabled}">
					<aui:button cssClass="btn-lg" type="submit" name="publish"
							value="publish" />
					<aui:button cssClass="btn-lg btn-default" type="submit" name="save-as-draft"
							value="save-as-draft" />
				</c:if>
			</c:if>
			<c:if test="${not empty dc.event and dc.hasPermission('DELETE_EVENT') and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<aui:button cssClass="btn-lg" href="${deleteEventURL}"
					type="cancel" value="delete" />
			</c:if>
			<aui:button cssClass="btn-lg" href="${param.returnURL}" type="cancel" />
		</aui:button-row>

	</aui:form>
</div>
