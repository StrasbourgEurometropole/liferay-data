<%@ include file="/project-bo-init.jsp"%>
<%@page import="eu.strasbourg.service.project.model.BudgetParticipatif"%>

<%-- URL : definit le lien menant vers la page de listage de l'entite --%>
<liferay-portlet:renderURL varImpl="budgetParticipatifURL">
	<portlet:param name="tab" value="budgets-participatifs" />
</liferay-portlet:renderURL>

<%-- URL : definit le lien menant vers la suppression de l'entite --%>
<liferay-portlet:actionURL name="deleteBudgetParticipatif" var="deleteBudgetParticipatifURL">
	<portlet:param name="cmd" value="deleteBudgetParticipatif" />
	<portlet:param name="tab" value="budgets-participatifs" />
	<portlet:param name="budgetParticipatifId" value="${not empty dc.budgetParticipatif ? dc.budgetParticipatif.budgetParticipatifId : ''}" />
</liferay-portlet:actionURL>

<%-- URL : definit le lien menant vers la sauvegarde de l'entite --%>
<liferay-portlet:actionURL name="saveBudgetParticipatif" varImpl="saveBudgetParticipatifURL">
	<portlet:param name="cmd" value="saveBudgetParticipatif" />
	<portlet:param name="tab" value="budgets-participatifs" />
</liferay-portlet:actionURL>

<%-- Composant : Body --%>
<div class="container-fluid-1280 main-content-body">

	<%-- Composant : definit la liste des messages d'erreur 
	(voir methode "validate" dans le saveAction de l'entite) --%>
	<liferay-ui:error key="title-error" message="title-error" />
	<liferay-ui:error key="description-error" message="description-error" />
	<liferay-ui:error key="image-error" message="image-error" />
	<liferay-ui:error key="place-error" message="place-error" />
	<liferay-ui:error key="motif-error" message="motif-error" />

	<%-- Composant : formulaire de saisie de l'entite --%>
	<aui:form action="${saveBudgetParticipatifURL}" method="post" name="fm" onSubmit="submitForm(event);">

		<%-- Propriete : definit l'entite de reference pour le formulaire--%>
		<aui:model-context bean="${dc.budgetParticipatif}" model="<%=BudgetParticipatif.class %>" />
		<aui:fieldset-group markupView="lexicon">

			<%-- Champ : (cache) PK de l'entite --%>
			<aui:input name="budgetParticipatifId" type="hidden" />

			<%-- Groupe de champs : Generalites --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="general">

				<%-- Champ : Titre --%>
				<aui:input name="title" required="true" />

				<%-- Champ : Corps de la description --%>
				<aui:input name="description" required="true" />
				
				<%-- Champ : Resume --%>
				<aui:input name="summary" label="bp-summary" required="false" />

				<%-- Champ : Budget --%>
				<aui:input name="budget" required="false" />

				<%-- Champ : Motif --%>
				<aui:input name="motif" required="false" />

			</aui:fieldset>
			
			<c:choose>
			    <c:when test="${not empty citoyenLastname}">
					<%-- Groupe de champs : Citoyen --%>
					<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="citizen">
			
						<%-- Champ : Nom --%>
						<aui:input name="citoyenLastname" label="last-name" disabled="true" />
						
						<%-- Champ : Prenom --%>
						<aui:input name="citoyenFirstname" label="first-name" disabled="true" />
			
						<%-- Champ : Adresse --%>
						<aui:input name="citoyenAdresse" label="address" />
			
						<%-- Champ : Code postal --%>
						<aui:input name="citoyenPostalCode" label="postal-code" />
			
						<%-- Champ : Ville --%>
						<aui:input name="citoyenCity" label="city" />
			
						<%-- Champ : Adresse mail --%>
						<aui:input name="citoyenEmail" label="email" />
			
						<%-- Champ : telephone --%>
						<aui:input name="citoyenPhone" label="phone" />
			
						<%-- Champ : mobile --%>
						<aui:input name="citoyenMobile" label="mobile" />
			
					</aui:fieldset>
			    </c:when>
				<c:otherwise>
					<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="fusion">
						<%-- Champ : Au nom de --%>
						<aui:input name="inTheNameOf" label="in-the-name-of" disabled="false" />
						
						<p><liferay-ui:message key='project-parent-explanation' /></p>
			
						<strasbourg-picker:entity label="eu.budgetParent" name="budgetParentId"
							value="${dc.budgetParticipatif.parentId}"
							type="eu.strasbourg.service.project.model.BudgetParticipatif"
							multiple="false" />
					</aui:fieldset>
				</c:otherwise>
			</c:choose>
			
            <%-- Groupe de champs : video/image --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="label-video">

				<%-- Champ : URL de la video --%>
				<aui:input name="videoUrl" required="false" />
				
                <%-- Champ : Image interne --%>
                <div class="internalImage" >
                    <strasbourg-picker:image label="image" name="imageId" required="false" value="${dc.budgetParticipatif.imageId}" global="false"/>
                </div>
				
			</aui:fieldset>

            <%-- Groupe de champs : Lieux --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="label-place">

				<aui:input name="placeTextArea" label="place-text-area" required="false" />

				<%-- Champ : Lieux --%>
				<div id="place-fields">
					<c:if test="${empty dc.budgetParticipatif.getPlacitPlaces()}">
						<div class="lfr-form-row lfr-form-row-inline main-content-card row-place">
							<h3><liferay-ui:message key="place" /></h3>
							<div class="row-fields">
								<liferay-util:include page="/includes/placit-place-row.jsp" servletContext="<%=application %>">
									<liferay-util:param name="index" value="0" />
								</liferay-util:include>
							</div>
						</div>
					</c:if>
					<c:forEach items="${dc.budgetParticipatif.getPlacitPlaces()}" var="placitPlace" varStatus="status">
						<c:set var="placitPlace" value="${placitPlace}" scope="request"/>
						<div class="lfr-form-row lfr-form-row-inline main-content-card row-place">
							<h3><liferay-ui:message key="place" /></h3>
							<div class="row-fields">
								<liferay-util:include page="/includes/placit-place-row.jsp" servletContext="<%=application %>">
									<liferay-util:param name="index" value="${status.index}" />
								</liferay-util:include>
							</div>
						</div>
					</c:forEach>

					<aui:input type="hidden" name="placeIndexes" value="${dc.defaultPlaceIndexes}" />
                </div>
			</aui:fieldset>

            <%-- Groupe de champs : Coup de coeur --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="crush">

				<%-- Champ : est un coup de coeur --%>
			    <aui:input name="isCrush" label="is-crush" type="toggle-switch"
			        value="${not empty dc.budgetParticipatif ? dc.budgetParticipatif.isCrush : false}" />

				<%-- Champ : Corps de la description --%>
				<aui:input name="crushComment" required="false" />

			</aui:fieldset>

            <%-- Groupe de champs : Phases --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="phase-selection">
				
				<p><liferay-ui:message key='phase-explanation' /></p>
			
				<strasbourg-picker:entity label="eu.budgetPhase" name="budgetPhaseId"
					value="${not empty budgetPhaseId ? budgetPhaseId : dc.budgetParticipatif.budgetPhaseId}"
					type="eu.strasbourg.service.project.model.BudgetPhase"
					multiple="false" />	
			</aui:fieldset>
			
			<%-- Groupe de champs : vocabulaire --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="label-vocabulary">

				<%-- Champ : Selection des categories (gere par le portail dans l'onglet "Categories" du BO) --%>
				<c:choose>
				    <c:when test="${empty defaultAssetCategoryIds}">
						<aui:input name="categories" type="assetCategories" wrapperCssClass="categories-selectors" />
				    </c:when>
					<c:otherwise>
						<liferay-ui:asset-categories-selector
								className="<%= BudgetParticipatif.class.getName() %>"
								curCategoryIds="${defaultAssetCategoryIds}"
						/>
					</c:otherwise>
				</c:choose>
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
			<c:if test="${(dc.hasPermission('ADD_BUDGET_PARTICIPATIF') and empty dc.budgetParticipatif or dc.hasPermission('EDIT_BUDGET_PARTICIPATIF') and not empty dc.budgetParticipatif) and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<c:if test="${dc.workflowEnabled}">
					<aui:button cssClass="btn-lg" type="submit" value="save" />
				</c:if>
				<c:if test="${not dc.workflowEnabled}">
					<aui:button cssClass="btn-lg" type="submit" name="publish" value="eu.publish" />
					<aui:button cssClass="btn-lg btn-default" type="submit" name="save-as-draft" value="save-as-draft" />
				</c:if>
			</c:if>
			
			<%-- Test : Verification des droits de supression --%>
			<c:if test="${not empty dc.budgetParticipatif && dc.hasPermission('DELETE_BUDGET_PARTICIPATIF') and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<aui:button cssClass="btn-lg" onClick='<%=renderResponse.getNamespace() + "deleteEntity();"%>' type="cancel" value="delete" />
			</c:if>
			
			<%-- Composant : bouton de retour a la liste des entites --%>
			<aui:button cssClass="btn-lg" href="${param.returnURL}" type="cancel" />
			
		</aui:button-row>
	</aui:form>
	
</div>

<liferay-portlet:actionURL name="getBudgetParticipatifPlaceRow" var="placeRowURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<liferay-portlet:param name="mvcPath" value="/includes/placit-place-row.jsp" />
</liferay-portlet:actionURL>

<liferay-util:html-top>
	<script>
		var editBudgetParticipatif = true;
		var getBudgetParticipatifPlaceRowURL = '${placeRowURL}';
	</script>
</liferay-util:html-top>

<liferay-util:html-bottom>
	<aui:script>
		define._amd = define.amd;
		define.amd = false;
	</aui:script>
	<script	src="/o/agendabo/js/vendors/jquery.autocomplete.js"></script>
	<script>
		define.amd = define._amd;
	</script>
	<script src="/o/projectbo/js/project-bo-edit-budget-participatif.js" type="text/javascript"></script>
</liferay-util:html-bottom>

<%-- Script : permet l'affichage des alertes de validation d'action --%>
<aui:script>
	function <portlet:namespace />deleteEntity() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-this-entry" />')) {
			window.location = '${deleteBudgetParticipatifURL}';
		}
	}
</aui:script>