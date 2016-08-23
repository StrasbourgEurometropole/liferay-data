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
				
				<aui:input name="image">
					<aui:validator name="required"
						errorMessage="this-field-is-required" />
				</aui:input>

				<label><liferay-ui:message key="Description" /></label>
				<liferay-ui:input-editor
					contents="${dc.collection.descriptionMap[locale]}"
					editorName="tinymce" name="descriptionEditor" placeholder="caption"
					showSource="<%=true %>" />
				<aui:input type="hidden" name="description" />

				<aui:input name="contributors" />
			</aui:fieldset>

			<aui:fieldset collapsed="<%=true%>" collapsible="<%=true%>"
				label="categorization">
				<aui:input name="categories" type="assetCategories" />

				<aui:input name="tags" type="assetTags" />
				 
				 <liferay-ui:input-asset-links
				       className="<%= ArtworkCollection.class.getName() %>"
				       classPK="${dc.collection.collectionId}"
				   />
			</aui:fieldset>
		</aui:fieldset-group>

		<aui:button-row>
			<aui:input type="hidden" name="forceStatus" value="" />
			<aui:button cssClass="btn-lg" type="submit" value="save"/>
			<c:if test="${not empty dc.collection and dc.collection.status}">
				<aui:button cssClass="btn-lg" type="submit" name="unpublish" value="unpublish" />
			</c:if>
			<c:if test="${not empty dc.collection and not dc.collection.status}">
				<aui:button cssClass="btn-lg" type="submit" name="publish" value="publish" />
			</c:if>
			<c:if test="${not empty dc.collection}">
				<aui:button cssClass="btn-lg" href="${deleteCollectionURL}"
					type="cancel" value="delete" />
			</c:if>
			<aui:button cssClass="btn-lg" href="${param.returnURL}" type="cancel" />
		</aui:button-row>

	</aui:form>
</div>
