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
				<aui:input name="subtitle">
				</aui:input>
				
				<aui:input name="image">
					<aui:validator name="required"
						errorMessage="this-field-is-required" />
				</aui:input>				

				<label><liferay-ui:message key="Description" /></label>
				<liferay-ui:input-editor
					contents="${dc.edition.descriptionMap[locale]}"
					editorName="tinymce" name="descriptionEditor" placeholder="caption"
					showSource="<%=true %>" />
				<aui:input type="hidden" name="description" />


				<aui:input name="author">
					<aui:validator name="required"
						errorMessage="this-field-is-required" />
				</aui:input>

				<aui:input name="editor">
					<aui:validator name="required"
						errorMessage="this-field-is-required" />
				</aui:input>

			</aui:fieldset>
			<aui:fieldset collapsed="<%=true%>" collapsible="<%=true%>"
				label="file">
				<aui:input name="URL" />
			</aui:fieldset>
			<aui:fieldset collapsed="<%=true%>" collapsible="<%=true%>"
				label="metadata">
				<aui:input name="distribution" />
				<aui:input name="ISBN" />
				<aui:input name="price" />
				<aui:input name="availableForExchange" />
				<aui:input name="inStock" />
				<aui:input name="diffusionDate"
					helpMessage="diffusion-date-help-message" />
				<aui:input name="pageNumber" />
				<aui:input name="pictureNumber" />
				<aui:select name="galleryId" showEmptyOption="true" label="gallery">
					<c:forEach var="gallery" items="${dc.galleries}">
				    	<aui:option value="${gallery.galleryId}" selected="${gallery.galleryId eq dc.edition.galleryId}">${gallery.titleMap[locale]}</aui:option>
				    </c:forEach>
				</aui:select>
			</aui:fieldset>

			<aui:fieldset collapsed="<%=true%>" collapsible="<%=true%>"
				label="categorization">
				<aui:input name="categories" type="assetCategories" />

				<aui:input name="tags" type="assetTags" />
				 
				 <liferay-ui:input-asset-links
				       className="<%= Edition.class.getName() %>"
				       classPK="${dc.edition.editionId}"
				   />
			</aui:fieldset>


			<aui:fieldset collapsed="<%=true%>" collapsible="<%=true%>"
				label="publication">
				<aui:input name="publicationDate" />
			</aui:fieldset>
		</aui:fieldset-group>

		<aui:button-row>
			<aui:input type="hidden" name="forceStatus" value="" />
			<aui:button cssClass="btn-lg" type="submit" value="save"/>
			<c:if test="${not empty dc.edition and dc.edition.status}">
				<aui:button cssClass="btn-lg" type="submit" name="unpublish" value="unpublish" />
			</c:if>
			<c:if test="${not empty dc.edition and not dc.edition.status}">
				<aui:button cssClass="btn-lg" type="submit" name="publish" value="publish" />
			</c:if>
			<c:if test="${not empty dc.edition}">
				<aui:button cssClass="btn-lg" href="${deleteEditionURL}"
					type="cancel" value="delete" />
			</c:if>
			<aui:button cssClass="btn-lg" href="${param.returnURL}" type="cancel" />
		</aui:button-row>

	</aui:form>
</div>
