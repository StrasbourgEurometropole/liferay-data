<%@ include file="/artwork-bo-init.jsp"%>
<%@page import="eu.strasbourg.service.artwork.model.Artwork"%>

<liferay-portlet:renderURL varImpl="artworksURL">
	<portlet:param name="tab" value="artworks" />
</liferay-portlet:renderURL>

<liferay-portlet:actionURL name="deleteArtwork" var="deleteArtworkURL">
	<portlet:param name="cmd" value="deleteArtwork" />
	<portlet:param name="tab" value="artworks" />
	<portlet:param name="artworkId"
		value="${not empty dc.artwork ? dc.artwork.artworkId : ''}" />
</liferay-portlet:actionURL>

<liferay-portlet:actionURL name="saveArtwork" varImpl="saveArtworkURL">
	<portlet:param name="cmd" value="saveArtwork" />
</liferay-portlet:actionURL>

<div class="container-fluid-1280 main-content-body">
	<aui:form action="${saveArtworkURL}" method="post" name="fm">
		<aui:translation-manager availableLocales="${dc.availableLocales}"
			changeableDefaultLanguage="false" defaultLanguageId="${locale}"
			id="translationManager" />

		<aui:model-context bean="${dc.artwork}" model="<%=Artwork.class %>" />
		<aui:fieldset-group markupView="lexicon">
			<aui:input name="artworkId" type="hidden" />

			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>"
				label="general">

				<aui:input name="title">
					<aui:validator name="required"
						errorMessage="this-field-is-required" />
				</aui:input>
				
				<strasbourg-picker:image label="image" name="imageId" required="true" value="${dc.artwork.imageId}" multiple="false"/>
				
				<strasbourg-picker:image label="additional-images" name="imagesIds" value="${dc.artwork.imagesIds}" multiple="true"/>
				
				<aui:input name="description" />

				<label><liferay-ui:message key="technical-information" /></label>
				<liferay-ui:input-editor
					contents="${dc.artwork.technicalInformationMap[locale]}"
					editorName="tinymce" name="technicalInformationEditor"
					placeholder="" showSource="<%=true %>" />
				<aui:input type="hidden" name="technicalInformation" />

				<aui:input name="noticeLink" />
				<aui:input name="artistName" />
				<aui:input name="creationYear" />
				<aui:input name="origin" />
			</aui:fieldset>

			<aui:fieldset collapsed="<%=true%>" collapsible="<%=true%>"
				label="artwork-movement">
				<aui:input name="exhibitionName" />
				<aui:input name="exhibitionPlace" />
				<aui:input name="loanPeriod" />
				<aui:input name="linkName" />
				<aui:input name="link" />
			</aui:fieldset>

			<aui:fieldset collapsed="<%=true%>" collapsible="<%=true%>"
				label="categorization">
				
				<strasbourg-picker:entity label="collections" name="collectionsIds"
					value="${dc.artwork.artworkCollectionsIds}"
					type="eu.strasbourg.service.artwork.model.ArtworkCollection"
					multiple="true" />
					
				<aui:input name="categories" type="assetCategories" />

				<aui:input name="tags" type="assetTags" />

				<liferay-ui:input-asset-links
					className="<%= Artwork.class.getName() %>"
					classPK="${dc.artwork.artworkId}" />
			</aui:fieldset>

		</aui:fieldset-group>

		<aui:button-row>
			<aui:input type="hidden" name="forceStatus" value="" />
			<aui:button cssClass="btn-lg" type="submit" value="save" />
			<c:if test="${not empty dc.artwork and dc.artwork.status}">
				<aui:button cssClass="btn-lg" type="submit" name="unpublish"
					value="unpublish" />
			</c:if>
			<c:if test="${not empty dc.artwork and not dc.artwork.status}">
				<aui:button cssClass="btn-lg" type="submit" name="publish"
					value="publish" />
			</c:if>
			<c:if test="${not empty dc.artwork}">
				<aui:button cssClass="btn-lg" href="${deleteArtworkURL}"
					type="cancel" value="delete" />
			</c:if>
			<aui:button cssClass="btn-lg" href="${param.returnURL}" type="cancel" />
		</aui:button-row>

	</aui:form>
</div>
