<%@ include file="/edition-bo-init.jsp"%>
<%@page import="eu.strasbourg.service.edition.model.Edition"%>

<liferay-portlet:renderURL varImpl="editionsURL">
	<portlet:param name="tab" value="editions" />
</liferay-portlet:renderURL>

<liferay-portlet:actionURL name="deleteEdition" var="deleteEditionURL">
	<portlet:param name="cmd" value="deleteEdition" />
	<portlet:param name="tab" value="editions" />
	<portlet:param name="editionId"
		value="${not empty dc.edition ? dc.edition.editionId : ''}" />
</liferay-portlet:actionURL>

<liferay-portlet:actionURL name="saveEdition" varImpl="saveEditionURL">
	<portlet:param name="cmd" value="saveEdition" />
</liferay-portlet:actionURL>

<div class="container-fluid-1280 main-content-body">
	<aui:form action="${saveEditionURL}" method="post" name="fm">
		<aui:translation-manager availableLocales="${dc.availableLocales}"
			changeableDefaultLanguage="false" defaultLanguageId="${locale}"
			id="translationManager" />

		<aui:model-context bean="${dc.edition}" model="<%=Edition.class %>" />
		<aui:fieldset-group markupView="lexicon">
			<aui:input name="editionId" type="hidden" />

			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>"
				label="general">

				<aui:input name="title">
					<aui:validator name="required"
						errorMessage="this-field-is-required" />
				</aui:input>

				<aui:input name="subtitle" />

				<strasbourg-picker:image label="image" name="imageId"
					required="true" value="${dc.edition.imageId}" />
				
				<aui:input name="description" />

				<aui:input name="author" />

				<aui:input name="editor" label="eu.co-editor" />


			</aui:fieldset>
			<aui:fieldset collapsed="<%=true%>" collapsible="<%=true%>"
				label="file">
				<aui:input name="URL" />
				
				
				<strasbourg-picker:file label="file" name="fileId"
					required="false" value="${dc.edition.fileId}" localized="true" multiple="false" />
			</aui:fieldset>
			<aui:fieldset collapsed="<%=true%>" collapsible="<%=true%>"
				label="metadata">
				<aui:input name="distribution" />
				<aui:input name="ISBN" />
				<aui:input name="price" />
				<aui:input name="availableForExchange" type="toggle-switch" value="${not empty dc.edition ? dc.edition.availableForExchange : false}" />
				<aui:input name="inStock" type="toggle-switch" value="${not empty dc.edition ? dc.edition.inStock : false}" />
				<label>Date de diffusion</label><br>
				<aui:input type="number" label="month" name="diffusionDateMonth" inlineField="true" min="1" max="12" value="${dc.edition.diffusionDateMonth}" />
				<aui:input type="number" label="year" name="diffusionDateYear" inlineField="true" min="1900" max="2100" value="${dc.edition.diffusionDateYear}">
					<aui:validator name="required" errorMessage="this-field-is-required" />
				</aui:input>
				<aui:input name="pageNumber" />
				<aui:input name="pictureNumber" />
			</aui:fieldset>

			<aui:fieldset collapsed="<%=true%>" collapsible="<%=true%>"
				label="categorization">
				
				<strasbourg-picker:entity label="galleries" name="galleriesIds"
					value="${dc.edition.editionGalleriesIds}"
					type="eu.strasbourg.service.edition.model.EditionGallery"
					multiple="true" />
					
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
					className="<%= Edition.class.getName() %>"
					classPK="${dc.edition.editionId}" />
			</aui:fieldset>


			<aui:fieldset collapsed="<%=true%>" collapsible="<%=true%>"
				label="publication">
				<aui:input name="publicationDate" />
			</aui:fieldset>
		</aui:fieldset-group>

		<aui:button-row>
			<c:if test="${(dc.hasPermission('ADD_EDITION') and empty dc.edition or dc.hasPermission('EDIT_EDITION') and not empty dc.edition) and empty themeDisplay.scopeGroup.getStagingGroup()}">
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
			<c:if test="${not empty dc.edition and dc.hasPermission('DELETE_EDITION') and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<aui:button cssClass="btn-lg" href="${deleteEditionURL}"
					type="cancel" value="delete" />
			</c:if>
			<aui:button cssClass="btn-lg" href="${param.returnURL}" type="cancel" />
		</aui:button-row>

	</aui:form>
</div>
