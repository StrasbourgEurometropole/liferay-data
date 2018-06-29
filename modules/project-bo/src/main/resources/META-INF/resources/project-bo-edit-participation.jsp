<%@ include file="/project-bo-init.jsp"%>
<%@page import="eu.strasbourg.service.project.model.Participation"%>

<%-- URL : definit le lien menant vers la page de listage de l'entite --%>
<liferay-portlet:renderURL varImpl="participationsURL">
	<portlet:param name="tab" value="participations" />
</liferay-portlet:renderURL>

<%-- URL : definit le lien menant vers la suppression de l'entite --%>
<liferay-portlet:actionURL name="deleteParticipation" var="deleteParticipationURL">
	<portlet:param name="cmd" value="deleteParticipation" />
	<portlet:param name="tab" value="participations" />
	<portlet:param name="participationId" value="${not empty dc.participation ? dc.participation.participationId : ''}" />
</liferay-portlet:actionURL>

<%-- URL : definit le lien menant vers la sauvegarde de l'entite --%>
<liferay-portlet:actionURL name="saveParticipation" varImpl="saveParticipationURL">
	<portlet:param name="cmd" value="saveParticipation" />
	<portlet:param name="tab" value="participations" />
</liferay-portlet:actionURL>

<%-- Composant : formulaire de saisie de l'entite --%>
<div class="container-fluid-1280 main-content-body">
	<aui:form action="${saveParticipationURL}" method="post" name="fm">

		<%-- Propriete : definit l'entite de reference pour le formulaire--%>
		<aui:model-context bean="${dc.participation}" model="<%=Participation.class %>" />
		<aui:fieldset-group markupView="lexicon">
		
			<%-- Champ : (cache) PK de l'entite --%>
			<aui:input name="participationId" type="hidden" />

			<%-- Groupe de champs : Generalites --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="general">

				<%-- Champ : Titre --%>
				<aui:input name="title" required="true" />
				
				<%-- Champ : Auteur --%>
				<aui:input name="author" required="true" />
			
			</aui:fieldset>
			
			<%-- Groupe de champs : Contact --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="contact">
				
				<%-- Champ : Nom --%>
				<aui:input name="contactName" required="false" />
				
				<%-- Champ : Ligne 1 --%>
				<aui:input name="contactLine1" required="false" />
				
				<%-- Champ : Ligne 2 --%>
				<aui:input name="contactLine2" required="false" />
				
				<%-- Champ : Numero de telephone --%>
				<aui:input name="contactPhoneNumber" required="false" />
				
			</aui:fieldset>
			
			<%-- Groupe de champs : Medias --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="medias">
				
				<%-- Champ : Choix du media --%>
				<aui:input name="mediaChoice" label="media-preference" type="toggle-switch"
					value="${not empty dc.participation ? dc.participation.mediaChoice : true}" />
				
				<%-- Champ : URL de la video --%>
				<aui:input name="videoUrl" required="false" />
				
				<%-- Selecteur : Image interne ou externe ? --%>
				<label><input type="radio" value="internalImage" name="imageType" 
					<c:if test="${(not empty dc.participation.imageId and dc.participation.imageId gt 0) or empty dc.participation.externalImageURL }">checked</c:if>> Image interne</label><br>
				<label><input type="radio" value="externalImage" name="imageType"
					<c:if test="${(empty dc.participation.imageId or dc.participation.imageId eq 0) and not empty dc.participation.externalImageURL }">checked</c:if>> Image externe</label><br><br>
				
				<%-- Champ : Image interne --%>
				<div class="internalImage" <c:if test="${(empty dc.participation.imageId or dc.participation.imageId eq 0) and not empty dc.participation.externalImageURL }">style="display: none;"</c:if>>
					<strasbourg-picker:image label="image" name="imageId" required="false" value="${dc.participation.imageId}" global="true" />
				</div>
				
				<%-- Groupe de champs : Image externe --%>
				<div class="externalImage" <c:if test="${(not empty dc.participation.imageId and dc.participation.imageId gt 0) or empty dc.participation.externalImageURL }">style="display: none;"</c:if>>
					
					<%-- Champ : URL de l'image externe --%>
					<aui:input name="externalImageURL" required="false" />
					
					<%-- Champ : Copyright de l'image externe --%>
					<aui:input name="externalImageCopyright" required="false" />
					
				</div>
				
			</aui:fieldset>
			
			<%-- Groupe de champs : Description --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="description">
				
				<%-- Champ : Chapeau de la description --%>
				<aui:input name="descriptionChapeau" required="false" />
				
				<%-- Champ : Corps de la description --%>
				<aui:input name="descriptionBody" required="false" />
				
			</aui:fieldset>
			
			<%-- Groupe de champs : Lieux de consultation --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="consultation-places">
				
				<%-- Champ : Corps de la description des lieux de consultation --%>
				<aui:input name="consultationPlacesBody" required="false" />
				
				<%-- Champ : Lieux --%>
				<strasbourg-picker:entity type="eu.strasbourg.service.place.model.Place" label="eu.places" name="placesIds"
					required="false" multiple="true" value="${dc.participation.placesIds}" />
				
			</aui:fieldset>
			
			<%-- Groupe de champs : Documents --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="documents">
				
				<%-- Champ : Selection des documents --%>
				<strasbourg-picker:file label="eu.documents" name="filesIds"
					required="false" multiple="true" value="${dc.participation.filesIds}" /> 
				
			</aui:fieldset>
			
			<%-- Groupe de champs : Autres --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="others">
				
				<%-- Champ : Evenements --%>
				<strasbourg-picker:entity type="eu.strasbourg.service.agenda.model.Event" label="eu.events" name="eventsIds"
					required="false" multiple="true" value="${dc.participation.eventsIds}" />
				
				<%-- Champ : Date de publication --%>
				<aui:input name="publicationDate" required="true" />
				
				<%-- Champ : Date d'expiration --%>
				<aui:input name="expirationDate" required="true" />
				
			</aui:fieldset>
			
			<%-- Groupe de champs : Categorisations --%>
			<aui:fieldset collapsed="<%=true%>" collapsible="<%=true%>" label="categorization">
				
				<%-- Champ : Selection des categories (gere par le portail dans l'onglet "Categories" du BO) --%>
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
				
				<%-- Champ : Selection des etiquettes (gere par le portail dans l'onglet "Etiquettes" du BO) --%>
				<aui:input name="tags" type="assetTags" />

			</aui:fieldset>

		</aui:fieldset-group>

		<%-- Composant : Menu de gestion de l'entite --%>
		<aui:button-row>
			
			<aui:input type="hidden" name="workflowAction" value="" />
			
			<%-- Test : Verification des droits d'edition et de sauvegarde --%>
			<c:if test="${(dc.hasPermission('ADD_PARTICIPATION') and empty dc.participation or dc.hasPermission('EDIT_PARTICIPATION') and not empty dc.participation) and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<c:if test="${dc.workflowEnabled}">
					<aui:button cssClass="btn-lg" type="submit" value="save" />
				</c:if>
				<c:if test="${not dc.workflowEnabled}">
					<aui:button cssClass="btn-lg" type="submit" name="publish" value="eu.publish" />
					<aui:button cssClass="btn-lg btn-default" type="submit" name="save-as-draft" value="save-as-draft" />
				</c:if>
			</c:if>
			
			<%-- Test : Verification des droits de supression --%>
			<c:if test="${not empty dc.participation && dc.hasPermission('DELETE_PARTICIPATION') and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<aui:button cssClass="btn-lg" onClick='<%=renderResponse.getNamespace() + "deleteEntity();"%>' type="cancel" value="delete" />
			</c:if>
			
			<%-- Composant : bouton de retour a la liste des entites --%>
			<aui:button cssClass="btn-lg" href="${param.returnURL}" type="cancel" />
			
		</aui:button-row>

	</aui:form>
</div>

<liferay-util:html-bottom>
	<script src="/o/projectbo/js/project-bo-edit-participation.js" type="text/javascript"></script>
</liferay-util:html-bottom>

<%-- Script : permet l'affichage des alertes de validation d'action --%>
<aui:script>
	function <portlet:namespace />deleteEntity() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-this-entry" />')) {
			window.location = '${deleteParticipationURL}';
		}
	}
</aui:script>