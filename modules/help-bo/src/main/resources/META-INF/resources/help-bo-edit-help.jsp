<%@ include file="/help-bo-init.jsp"%>
<%@page import="eu.strasbourg.service.project.model.Initiative"%>

<%-- URL : definit le lien menant vers la page de listage de l'entite --%>
<liferay-portlet:renderURL varImpl="helpsURL">
	<portlet:param name="tab" value="helps" />
</liferay-portlet:renderURL>

<%-- URL : definit le lien menant vers la suppression de l'entite --%>
<liferay-portlet:actionURL name="deleteHelp" var="deleteHelpURL">
	<portlet:param name="cmd" value="deleteHelp" />
	<portlet:param name="tab" value="helps" />
	<portlet:param name="helpId" value="${not empty dc.help ? dc.help.helpId : ''}" />
</liferay-portlet:actionURL>

<%-- URL : definit le lien menant vers la sauvegarde de l'entite --%>
<liferay-portlet:actionURL name="saveHelp" varImpl="saveHelpURL">
	<portlet:param name="cmd" value="saveHelp" />
	<portlet:param name="tab" value="helps" />
</liferay-portlet:actionURL>

<%-- Composant : Body --%>
<div class="container-fluid-1280 main-content-body">

	<%-- Composant : definit la liste des messages d'erreur 
	(voir methode "validate" dans le saveAction de l'entite) --%>
	<liferay-ui:error key="title-error" message="title-error" />
	<liferay-ui:error key="description-error" message="description-error" />
	<liferay-ui:error key="image-error" message="image-error" />

	<%-- Composant : formulaire de saisie de l'entite --%>
	<aui:form action="${saveHelpURL}" method="post" name="fm" onSubmit="submitForm(event);">

		<%-- Propriete : definit l'entite de reference pour le formulaire--%>
		<aui:model-context bean="${dc.help}" model="<%=Initiative.class %>" />
		<aui:fieldset-group markupView="lexicon">
		
			<%-- Champ : (cache) PK de l'entite --%>
			<aui:input name="helpId" type="hidden" />

			<%-- Groupe de champs : Generalites --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="general">

				<%-- Champ : Titre --%>
				<aui:input name="title" required="true" />
				
				<%-- Champ : Corps de la description --%>
				<aui:input name="description" required="true"/>
				
			</aui:fieldset>
			
			<%-- Groupe de champs : Citoyen --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="depositary">
				
				<%-- Champ : Nom --%>
				<aui:input name="authorName" type="text" label="author" disabled="true" value="${not empty dc.help ? dc.help.getAuthorNameLabel() : '' }" />
				
				<%-- Champ : Adresse mail --%>
				<aui:input name="authorEmail" type="text" label="email" disabled="true" value="${not empty dc.help ? dc.help.getAuthorEmail() : '' }" />
				
				<%-- Champ : Au nom de --%>
				<aui:input name="inTheNameOf" required="false" />
			
			</aui:fieldset>
											
			<%-- Groupe de champs : Medias --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="medias">
				
				<%-- Champ : Choix du media --%>
				<aui:input name="mediaChoice" label="media-preference" type="toggle-switch"
					value="${not empty dc.help ? dc.help.mediaChoice : true}" />
				
				<%-- Selecteur : Image interne ou externe ? --%>
				<label><input type="radio" value="internalImage" name="imageType" 
					<c:if test="${(not empty dc.help.imageId and dc.help.imageId gt 0) or empty dc.help.externalImageURL }">checked</c:if>> Image interne</label><br>
				<label><input type="radio" value="externalImage" name="imageType"
					<c:if test="${(empty dc.help.imageId or dc.help.imageId eq 0) and not empty dc.help.externalImageURL }">checked</c:if>> Image externe</label><br><br>
				
				<%-- Champ : Image interne --%>
				<div class="internalImage" <c:if test="${(empty dc.help.imageId or dc.help.imageId eq 0) and not empty dc.help.externalImageURL }">style="display: none;"</c:if>>
					<strasbourg-picker:image label="image" name="imageId" required="false" value="${dc.help.imageId}" global="false" />
				</div>
				
				<%-- Groupe de champs : Image externe --%>
				<div class="externalImage" <c:if test="${(not empty dc.help.imageId and dc.help.imageId gt 0) or empty dc.help.externalImageURL }">style="display: none;"</c:if>>
					
					<%-- Champ : URL de l'image externe --%>
					<aui:input name="externalImageURL" required="false" />
					
					<%-- Champ : Copyright de l'image externe --%>
					<aui:input name="externalImageCopyright" required="false" />
					
				</div>
				
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
		                                event.preventDefault();
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
			<c:if test="${(dc.hasPermission('ADD_HELP') and empty dc.help or dc.hasPermission('EDIT_HELP') and not empty dc.help) and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<c:if test="${dc.workflowEnabled}">
					<aui:button cssClass="btn-lg" type="submit" value="save" />
				</c:if>
				<c:if test="${not dc.workflowEnabled}">
					<aui:button cssClass="btn-lg" type="submit" name="publish" value="eu.publish" />
					<aui:button cssClass="btn-lg btn-default" type="submit" name="save-as-draft" value="save-as-draft" />
				</c:if>
			</c:if>
			
			<%-- Test : Verification des droits de supression --%>
			<c:if test="${not empty dc.help && dc.hasPermission('DELETE_HELP') and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<aui:button cssClass="btn-lg" onClick='<%=renderResponse.getNamespace() + "deleteEntity();"%>' type="cancel" value="delete" />
			</c:if>
			
			<%-- Composant : bouton de retour a la liste des entites --%>
			<aui:button cssClass="btn-lg" href="${param.returnURL}" type="cancel" />
			
		</aui:button-row>

	</aui:form>
	
</div>

<liferay-util:html-top>
	<script>
		var editHelp = true;
	</script>
</liferay-util:html-top>

<liferay-util:html-bottom>
	<script src="/o/helpbo/js/help-bo-edit-help.js" type="text/javascript"></script>
</liferay-util:html-bottom>

<%-- Script : permet l'affichage des alertes de validation d'action --%>
<aui:script>
	function <portlet:namespace />deleteEntity() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-this-entry" />')) {
			window.location = '${deleteHelpURL}';
		}
	}
</aui:script>