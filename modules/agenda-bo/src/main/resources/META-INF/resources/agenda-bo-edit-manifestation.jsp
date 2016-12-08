<%@ include file="/agenda-bo-init.jsp"%>
<%@page import="eu.strasbourg.service.agenda.model.Manifestation"%>

<liferay-portlet:renderURL varImpl="manifestationsURL">
	<portlet:param name="tab" value="manifestations" />
</liferay-portlet:renderURL>

<liferay-portlet:actionURL name="deleteManifestation" var="deleteManifestationURL">
	<portlet:param name="cmd" value="deleteManifestation" />
	<portlet:param name="tab" value="manifestations" />
	<portlet:param name="manifestationId"
		value="${not empty dc.manifestation ? dc.manifestation.manifestationId : ''}" />
</liferay-portlet:actionURL>

<liferay-portlet:actionURL name="saveManifestation" varImpl="saveManifestationURL">
	<portlet:param name="cmd" value="saveManifestation" />
	<portlet:param name="tab" value="manifestations" />
</liferay-portlet:actionURL>


<div class="container-fluid-1280 main-content-body">
	<aui:form action="${saveManifestationURL}" method="post" name="fm">
		<aui:translation-manager availableLocales="${dc.availableLocales}"
			changeableDefaultLanguage="false" defaultLanguageId="${locale}"
			id="translationManager" />

		<aui:model-context bean="${dc.manifestation}"
			model="<%=Manifestation.class %>" />
		<aui:fieldset-group markupView="lexicon">
			<aui:input name="manifestationId" type="hidden" />

			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>"
				label="general">

				<aui:input name="title">
					<aui:validator name="required"
						errorMessage="this-field-is-required" />
				</aui:input>

				<strasbourg-picker:image label="image" name="imageId"
					required="true" value="${dc.manifestation.imageId}" />

				<aui:input name="description" label="required-description" />
				<!-- Hack pour ajouter une validation sur la description -->
				<div class="has-error">
					<aui:input type="hidden" name="descriptionValidatorInputHelper" value="placeholder">
						<aui:validator name="custom" errorMessage="requested-description-error">
							function (val, fieldNode, ruleValue) {
								var validate = $('#_eu_strasbourg_portlet_agenda_AgendaBOPortlet_description_fr_FR').val().length > 0;
								if (!validate) {
									$("#_eu_strasbourg_portlet_agenda_AgendaBOPortlet_descriptionContainer").get(0).scrollIntoView();
								}
								return validate;
							}
						</aui:validator>
					</aui:input>
				</div>
								
				<aui:input name="startDate" />
				
				<aui:input name="endDate" />
				
				<strasbourg-picker:entity label="events" name="eventsIds"
					value="${dc.manifestation.eventsIds}"
					type="eu.strasbourg.service.agenda.model.Event"
					multiple="true" />				

			</aui:fieldset>

			<aui:fieldset collapsed="<%=true%>" collapsible="<%=true%>"
				label="categorization">
				
				<aui:input name="categories" type="assetCategories" wrapperCssClass="categories-selectors" />
				
				<!-- Hack pour ajouter une validation sur les vocabulaires obligatoires -->
				<div class="has-error">
					<aui:input type="hidden" name="assetCategoriesValidatorInputHelper" value="placeholder">
						<aui:validator name="custom" errorMessage="requested-vocabularies-error">
							function (val, fieldNode, ruleValue) {
								var validated = true;
								var fields = document.querySelectorAll('.categories-selectors > .field-content');
								for (var i = 0; i < fields.length; i++) {
									fieldContent = fields[i];
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
					className="<%= Manifestation.class.getName() %>"
					classPK="${dc.manifestation.manifestationId}" />
			</aui:fieldset>


			<aui:fieldset collapsed="<%=true%>" collapsible="<%=true%>"
				label="publication">
				<aui:input name="displayDate" />
			</aui:fieldset>

		</aui:fieldset-group>
		
		<aui:button-row>
			<c:if test="${(dc.hasPermission('ADD_EVENT_GALLERY') and empty dc.manifestation or dc.hasPermission('EDIT_EVENT_GALLERY') and not empty dc.manifestation) and empty themeDisplay.scopeGroup.getStagingGroup()}">
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
			<c:if test="${not empty dc.manifestation and dc.hasPermission('DELETE_EVENT_GALLERY') and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<aui:button cssClass="btn-lg" href="${deleteManifestationURL}"
					type="cancel" value="delete" />
			</c:if>
			<aui:button cssClass="btn-lg" href="${param.returnURL}" type="cancel" />
		</aui:button-row>
	</aui:form>
</div>