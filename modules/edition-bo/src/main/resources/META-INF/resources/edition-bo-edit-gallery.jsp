<%@ include file="/edition-bo-init.jsp" %>
<%@page import="eu.strasbourg.service.edition.model.EditionGallery" %>

<liferay-portlet:renderURL varImpl="galleriesURL">
	<portlet:param name="tab" value="galleries" />
</liferay-portlet:renderURL>

<liferay-portlet:actionURL name="deleteGallery" var="deleteGalleryURL">
	<portlet:param name="cmd" value="deleteGallery" />
	<portlet:param name="tab" value="galleries" />
	<portlet:param name="galleryId" value="${not empty dc.editionGallery ? dc.editionGallery.galleryId : ''}" />
</liferay-portlet:actionURL>

<liferay-portlet:actionURL name="saveGallery" varImpl="saveGalleryURL">
	<portlet:param name="cmd" value="saveGallery" />
	<portlet:param name="tab" value="galleries" />
</liferay-portlet:actionURL>


<div class="container-fluid-1280 main-content-body">
	<aui:form action="${saveGalleryURL}" method="post" name="fm" >
		<aui:translation-manager
			availableLocales="${dc.availableLocales}"
			changeableDefaultLanguage="false"
			defaultLanguageId="${locale}"
			id="translationManager"
		/>
		
		<aui:model-context bean="${dc.editionGallery}" model="<%=EditionGallery.class %>" />
		<aui:fieldset-group markupView="lexicon">
			<aui:input name="galleryId" type="hidden" />
			
		  	<aui:fieldset collapsed="<%= false %>" collapsible="<%= true %>" label="general">
		  	
				<aui:input name="title">
					<aui:validator name="required" errorMessage="this-field-is-required" />
				</aui:input>
				
				<aui:input name="image">
					<aui:validator name="required"
						errorMessage="this-field-is-required" />
				</aui:input>				
		
				<label><liferay-ui:message key="Description" /></label>
				<liferay-ui:input-editor contents="${dc.editionGallery.descriptionMap[locale]}" 
					editorName="tinymce" name="descriptionEditor" placeholder="caption" showSource="<%=true %>" />
				<aui:input type="hidden" name="description" />

			</aui:fieldset>
			
		  	<aui:fieldset collapsed="<%= true %>" collapsible="<%= true %>" label="categorization">
				<aui:input name="categories" type="assetCategories" />

				<aui:input name="tags" type="assetTags" />
				
				 <liferay-ui:input-asset-links
				       className="<%= EditionGallery.class.getName() %>"
				       classPK="${dc.editionGallery.galleryId}"
				   />
			</aui:fieldset>
			
			
		  	<aui:fieldset collapsed="<%= true %>" collapsible="<%= true %>" label="publication">
		  		<aui:input name="publicationDate" />
		  	</aui:fieldset>

		</aui:fieldset-group>
		
		<aui:button-row>
			<aui:button cssClass="btn-lg" type="submit" />
			<c:if test="${not empty dc.editionGallery and dc.editionGallery.status}">
				<aui:input type="hidden" name="forceStatus" value="unpublish" />
				<aui:button cssClass="btn-lg" type="submit" value="unpublish" />
			</c:if>
			<c:if test="${not empty dc.editionGallery and not dc.editionGallery.status}">
				<aui:input type="hidden" name="forceStatus" value="publish" />
				<aui:button cssClass="btn-lg" type="submit" value="publish" />
			</c:if>
			<c:if test="${not empty dc.editionGallery}">
				<aui:button cssClass="btn-lg" href="${deleteGalleryURL}" type="cancel" value="delete" />
			</c:if>
			<aui:button cssClass="btn-lg" href="${param.returnURL}" type="cancel" />
		</aui:button-row>
		
	</aui:form>
</div>