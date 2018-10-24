<%@ include file="/project-bo-init.jsp"%>
<%@page import="eu.strasbourg.service.project.model.BudgetPhase"%>

<%-- URL : definit le lien menant vers la page de listage de l'entite --%>
<liferay-portlet:renderURL varImpl="budgetPhasesURL">
	<portlet:param name="tab" value="budget-phases" />
</liferay-portlet:renderURL>

<%-- URL : definit le lien menant vers la suppression de l'entite --%>
<liferay-portlet:actionURL name="deleteBudgetPhase" var="deleteBudgetPhaseURL">
	<portlet:param name="cmd" value="deleteBudgetPhase" />
	<portlet:param name="tab" value="budget-phases" />
	<portlet:param name="budgetPhaseId" value="${not empty dc.budgetPhase ? dc.budgetPhase.budgetPhaseId : ''}" />
</liferay-portlet:actionURL>

<%-- URL : definit le lien menant vers la sauvegarde de l'entite --%>
<liferay-portlet:actionURL name="saveBudgetPhase" varImpl="saveBudgetPhaseURL">
	<portlet:param name="cmd" value="saveBudgetPhase" />
	<portlet:param name="tab" value="budget-phases" />
</liferay-portlet:actionURL>

<%-- Composant : Body --%>
<div class="container-fluid-1280 main-content-body">
	
	<%-- Composant : definit la liste des messages d'erreur 
	(voir methode "validate" dans le saveAction de l'entite) --%>
	<liferay-ui:error key="title-error" message="title-error" />
	<liferay-ui:error key="description-error" message="description-error" />
	<liferay-ui:error key="dates-error" message="dates-error" />
	<liferay-ui:error key="is-active-error" message="is-active-error" />

	<%-- Composant : formulaire de saisie de l'entite --%>
	<aui:form action="${saveBudgetPhaseURL}" method="post" name="fm" onSubmit="submitForm(event);">

		<%-- Propriete : definit l'entite de reference pour le formulaire--%>
		<aui:model-context bean="${dc.budgetPhase}" model="<%=BudgetPhase.class %>" />
		
		<aui:fieldset-group markupView="lexicon">
		
			<%-- Champ : (cache) PK de l'entite --%>
			<aui:input name="budgetPhaseId" type="hidden" />
			
			<%-- Groupe de champs : Generalites --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="general">
			
				<%-- Champ : Nom --%>
				<aui:input name="name" required="true" />
				
				<%-- Champ : Description --%>
				<aui:input name="description" required="false" />
				
			</aui:fieldset>
			
			<%-- Groupe de champs : Gestion de la phase --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="management">
				
				<%-- Champ : Nombre de votes necessaires --%>
				<aui:input name="numberOfVote" required="true" />
				
				<%-- Champ : Phase en cours d'activite --%>
				<aui:input name="isActive" label="is-active" type="toggle-switch"
					value="${not empty dc.budgetPhase ? dc.budgetPhase.isActive : true}" />
				
			</aui:fieldset>
			
			<%-- Groupe de champs : Periode de depot --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="deposit-period">
				
				<%-- Champ : Budget --%>
				<aui:input name="beginDate" required="true" />
				
				<%-- Champ : Label --%>
				<aui:input name="endDate" required="true" />
				
			</aui:fieldset>
			
			<%-- Groupe de champs : Periode de vote --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="vote-period">
				
				<%-- Champ : Budget --%>
				<aui:input name="beginVoteDate" required="true" />
				
				<%-- Champ : Label --%>
				<aui:input name="endVoteDate" required="true" />
				
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
			<c:if test="${(dc.hasPermission('ADD_BUDGET_PHASE') and empty dc.budgetPhase or dc.hasPermission('EDIT_BUDGET_PHASE') and not empty dc.budgetPhase) and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<c:if test="${dc.workflowEnabled}">
					<aui:button cssClass="btn-lg" type="submit" value="save" />
				</c:if>
				<c:if test="${not dc.workflowEnabled}">
					<aui:button cssClass="btn-lg" type="submit" name="publish" value="eu.publish" />
					<aui:button cssClass="btn-lg btn-default" type="submit" name="save-as-draft" value="save-as-draft" />
				</c:if>
			</c:if>
			
			<%-- Test : Verification des droits de supression --%>
			<c:if test="${not empty dc.budgetPhase && dc.hasPermission('DELETE_BUDGET_PHASE') and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<aui:button cssClass="btn-lg" onClick='<%=renderResponse.getNamespace() + "deleteEntity();"%>' type="cancel" value="delete" />
			</c:if>

			<%-- Composant : bouton de retour a la liste des entites --%>
			<aui:button cssClass="btn-lg" href="${param.returnURL}" type="cancel" />
			
		</aui:button-row>

	</aui:form>
	
</div>

<liferay-util:html-top>
	<script>
		var editBudgetPhase = true;
	</script>
	<script src="/o/projectbo/js/project-bo-edit-budget-phase.js" type="text/javascript"></script>
</liferay-util:html-top>

<%-- Script : permet l'affichage des alertes de validation d'action --%>
<aui:script>
	function <portlet:namespace />deleteEntity() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-this-entry" />')) {
			window.location = '${deleteProjectURL}';
		}
	}
</aui:script>