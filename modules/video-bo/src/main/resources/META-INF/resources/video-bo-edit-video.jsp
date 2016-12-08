<%@ include file="/video-bo-init.jsp"%>
<%@page import="eu.strasbourg.service.video.model.Video"%>

<liferay-portlet:renderURL varImpl="videosURL">
	<portlet:param name="tab" value="videos" />
</liferay-portlet:renderURL>

<liferay-portlet:actionURL name="deleteVideo" var="deleteVideoURL">
	<portlet:param name="cmd" value="deleteVideo" />
	<portlet:param name="tab" value="videos" />
	<portlet:param name="videoId"
		value="${not empty dc.video ? dc.video.videoId : ''}" />
</liferay-portlet:actionURL>

<liferay-portlet:actionURL name="saveVideo" varImpl="saveVideoURL">
	<portlet:param name="cmd" value="saveVideo" />
</liferay-portlet:actionURL>

<div class="container-fluid-1280 main-content-body">
	<aui:form action="${saveVideoURL}" method="post" name="fm">
		<aui:translation-manager availableLocales="${dc.availableLocales}"
			changeableDefaultLanguage="false" defaultLanguageId="${locale}"
			id="translationManager" />

		<aui:model-context bean="${dc.video}" model="<%=Video.class %>" />
		<aui:fieldset-group markupView="lexicon">
			<aui:input name="videoId" type="hidden" />

			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>"
				label="general">

				<aui:input name="title">
					<aui:validator name="required"
						errorMessage="this-field-is-required" />
				</aui:input>
				
				<aui:input name="source" />

				<strasbourg-picker:image label="image" name="imageId"
					required="true" value="${dc.video.imageId}" />
				
				<aui:input name="description" />
				
				<aui:input name="copyright" />
				
				<aui:input name="origin" />
				
				<strasbourg-picker:file label="transcription" name="transcriptionFileId"
					required="false" value="${dc.video.transcriptionFileId}" />
			</aui:fieldset>

			<aui:fieldset collapsed="<%=true%>" collapsible="<%=true%>"
				label="categorization">
				
				<strasbourg-picker:entity label="galleries" name="galleriesIds"
					value="${dc.video.videoGalleriesIds}"
					type="eu.strasbourg.service.video.model.VideoGallery"
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
					className="<%= Video.class.getName() %>"
					classPK="${dc.video.videoId}" />
			</aui:fieldset>

		</aui:fieldset-group>

		<aui:button-row>
			<c:if test="${(dc.hasPermission('ADD_VIDEO') and empty dc.video or dc.hasPermission('EDIT_VIDEO') and not empty dc.video) and empty themeDisplay.scopeGroup.getStagingGroup()}">
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
			<c:if test="${not empty dc.video and dc.hasPermission('DELETE_VIDEO') and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<aui:button cssClass="btn-lg" href="${deleteVideoURL}"
					type="cancel" value="delete" />
			</c:if>
			<aui:button cssClass="btn-lg" href="${param.returnURL}" type="cancel" />
		</aui:button-row>

	</aui:form>
</div>
