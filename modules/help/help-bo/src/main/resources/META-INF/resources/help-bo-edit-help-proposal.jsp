<%@ include file="/help-bo-init.jsp"%>
<%@page import="eu.strasbourg.service.help.model.HelpProposal"%>

<%-- URL : definit le lien menant vers la page de listage de l'entite --%>
<liferay-portlet:renderURL varImpl="helpProposalsURL">
	<portlet:param name="tab" value="helpProposals" />
</liferay-portlet:renderURL>

<%-- URL : definit le lien menant vers la suppression de l'entite --%>
<liferay-portlet:actionURL name="deleteHelpProposal" var="deleteHelpProposalURL">
	<portlet:param name="cmd" value="deleteHelpProposal" />
	<portlet:param name="tab" value="helpProposals" />
	<portlet:param name="helpProposalId" value="${not empty dc.helpProposal ? dc.helpProposal.helpProposalId : ''}" />
</liferay-portlet:actionURL>

<%-- URL : definit le lien menant vers la sauvegarde de l'entite --%>
<liferay-portlet:actionURL name="saveHelpProposal" varImpl="saveHelpProposalURL">
	<portlet:param name="cmd" value="saveHelpProposal" />
	<portlet:param name="tab" value="helpProposals" />
</liferay-portlet:actionURL>

<%-- Composant : Body --%>
<div class="container-fluid-1280 main-content-body">

	<%-- Composant : definit la liste des messages d'erreur 
	(voir methode "validate" dans le saveAction de l'entite) --%>
	<liferay-ui:error key="title-error" message="title-error" />
	<liferay-ui:error key="description-error" message="description-error" />
	<liferay-ui:error key="address-error" message="address-error" />
	<liferay-ui:error key="city-error" message="city-error" />
	<liferay-ui:error key="postal-code-error" message="postal-code-error" />
	<liferay-ui:error key="phone-number-error" message="phone-number-error" />
	<liferay-ui:error key="in-the-name-of-error" message="in-the-name-of-error" />

	<%-- Composant : formulaire de saisie de l'entite --%>
	<aui:form action="${saveHelpProposalURL}" method="post" name="fm" onSubmit="submitForm(event);">

		<%-- Propriete : definit l'entite de reference pour le formulaire--%>
		<aui:model-context bean="${dc.helpProposal}" model="<%=HelpProposal.class %>" />
		<aui:fieldset-group markupView="lexicon">
		
			<%-- Champ : (cache) PK de l'entite --%>
			<aui:input name="helpProposalId" type="hidden" />

			<%-- Champ : (cache) enriegistrement lue ou non lue --%>
			<aui:input name="read" type="hidden" value="0" />

			<%-- Groupe de champs : Generalites --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="general">

				<%-- Champ : Titre --%>
				<aui:input name="title" required="true" />

				<%-- Champ : Date de création --%>
				<aui:input name="createDate" disabled="true" />

				<%-- Champ : Date de modification --%>
				<aui:input name="modifiedByUserDate" disabled="true" helpMessage="help-update-date-by-user" />
				
				<%-- Champ : Détail de l'aide --%>
				<aui:input name="description" required="true"/>

				<%-- Champ : Image interne --%>
				<div class="internalImage">
					<strasbourg-picker:image label="help.photo" name="imageId" required="false" value="${dc.helpProposal.imageId}" global="false" />
				</div>
				
			</aui:fieldset>
			
			<%-- Groupe de champs : Citoyen --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="depositary">
				
				<%-- Champ : Nom --%>
				<aui:input name="authorName" type="text" label="author" disabled="true" value="${not empty dc.helpProposal ? dc.helpProposal.getAuthorNameLabel() : '' }" />
				
				<%-- Champ : Adresse mail --%>
				<aui:input name="authorEmail" type="text" label="email" disabled="true" value="${not empty dc.helpProposal ? dc.helpProposal.getAuthorEmail() : '' }" />
				
				<%-- Champ : Adresse --%>
				<aui:input name="address" required="true" />

				<%-- Champ : Ville --%>
				<aui:input name="city" required="true" />

				<%-- Champ : Code postal --%>
				<aui:input name="postalCode" required="true" />

				<%-- Champ : Téléphone --%>
				<aui:input name="phoneNumber" required="true" />

				<%-- Champ : Au nom de --%>
				<aui:input name="inTheNameOf" required="true" />

				<%-- Champ : Langues parlées --%>
				<aui:input name="spokenLanguages" required="false"/>
			
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

			<%-- Groupe de champs : Autres --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="moderation">

				<%-- Champ : Commentaire --%>
				<aui:input name="comment" required="false" />

			</aui:fieldset>

		</aui:fieldset-group>

		<%-- Composant : Menu de gestion de l'entite --%>
		<aui:button-row>
			
			<aui:input type="hidden" name="workflowAction" value="" />
			
			<%-- Test : Verification des droits d'edition et de sauvegarde --%>
			<c:if test="${(dc.hasPermission('ADD_HELP') and empty dc.helpProposal or dc.hasPermission('EDIT_HELP') and not empty dc.helpProposal) and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<c:if test="${dc.workflowEnabled}">
					<aui:button cssClass="btn-lg" type="submit" value="save" />
				</c:if>
				<c:if test="${not dc.workflowEnabled}">
					<aui:button cssClass="btn-lg" type="submit" name="save" value="save" />
					<aui:button cssClass="btn-lg btn-default" type="submit" name="save-and-read" value="save-and-read" />
				</c:if>
			</c:if>
			
			<%-- Test : Verification des droits de supression --%>
			<c:if test="${not empty dc.helpProposal && dc.hasPermission('DELETE_HELP') and empty themeDisplay.scopeGroup.getStagingGroup()}">
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
	<script src="/o/helpbo/js/help-bo-edit-help-proposal.js" type="text/javascript"></script>
</liferay-util:html-bottom>

<%-- Script : permet l'affichage des alertes de validation d'action --%>
<aui:script>
	function <portlet:namespace />deleteEntity() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-this-entry" />')) {
			window.location = '${deleteHelpProposalURL}';
		}
	}


    $("#<portlet:namespace />save-and-read").click(function(event){
        $("#<portlet:namespace />read").val('1');
    });
</aui:script>