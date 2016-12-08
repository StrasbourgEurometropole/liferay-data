<%@ include file="/artwork-bo-init.jsp"%>
<%@page import="eu.strasbourg.service.artwork.model.ArtworkCollection"%>

<liferay-portlet:renderURL varImpl="collectionsURL">
	<portlet:param name="tab" value="collections" />
</liferay-portlet:renderURL>

<liferay-portlet:actionURL name="deleteCollection" var="deleteCollectionURL">
	<portlet:param name="cmd" value="deleteCollection" />
	<portlet:param name="tab" value="collections" />
	<portlet:param name="collectionId"
		value="${not empty dc.collection ? dc.collection.collectionId : ''}" />
</liferay-portlet:actionURL>

<liferay-portlet:actionURL name="saveCollection" varImpl="saveCollectionURL">
	<portlet:param name="cmd" value="saveCollection" />
	<portlet:param name="tab" value="collections" />
</liferay-portlet:actionURL>

<div class="container-fluid-1280 main-content-body">
	<aui:form action="${saveCollectionURL}" method="post" name="fm">
		<aui:translation-manager availableLocales="${dc.availableLocales}"
			changeableDefaultLanguage="false" defaultLanguageId="${locale}"
			id="translationManager" />

		<aui:model-context bean="${dc.collection}" model="<%=ArtworkCollection.class %>" />
		<aui:fieldset-group markupView="lexicon">
			<aui:input name="collectionId" type="hidden" />

			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>"
				label="general">

				<aui:input name="title">
					<aui:validator name="required"
						errorMessage="this-field-is-required" />
				</aui:input>
				
				<strasbourg-picker:image label="image" name="imageId" required="true" value="${dc.collection.imageId}"/>

				<aui:input name="description" />

				<aui:input name="contributors" />
				
				<strasbourg-picker:entity label="artworks" name="artworksIds"
					value="${dc.collection.artworksIds}"
					type="eu.strasbourg.service.artwork.model.Artwork"
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
				       className="<%= ArtworkCollection.class.getName() %>"
				       classPK="${dc.collection.collectionId}"
				   />
			</aui:fieldset>
		</aui:fieldset-group>

		<aui:button-row>
			<c:if test="${(dc.hasPermission('ADD_ARTWORK_COLLECTION') and empty dc.collection or dc.hasPermission('EDIT_ARTWORK_COLLECTION') and not empty dc.collection) and empty themeDisplay.scopeGroup.getStagingGroup()}">
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
			<c:if test="${not empty dc.collection and dc.hasPermission('DELETE_ARTWORK_COLLECTION') and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<aui:button cssClass="btn-lg" href="${deleteCollectionURL}"
					type="cancel" value="delete" />
			</c:if>
			<aui:button cssClass="btn-lg" href="${param.returnURL}" type="cancel" />
		</aui:button-row>

	</aui:form>
</div>
