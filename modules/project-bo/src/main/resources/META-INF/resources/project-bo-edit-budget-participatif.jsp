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

				<%-- Champ : Budget --%>
				<aui:input name="budget" required="false" />

				<%-- Champ : Motif --%>
				<aui:input name="motif" required="false" />

				<%-- Champ : Auteur --%>
				<aui:input name="userName" required="true" />

			</aui:fieldset>

			<%-- Groupe de champs : Citoyen --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="citizen">

				<%-- Champ : Nom --%>
				<aui:input name="citoyenLastname" required="true" />

				<%-- Champ : Prenom --%>
				<aui:input name="citoyenFirstname" required="true" />

				<%-- Champ : Adresse --%>
				<aui:input name="citoyenAdresse" required="false" />

				<%-- Champ : Code postal --%>
				<aui:input name="citoyenPostalCode" required="false" />

				<%-- Champ : Ville --%>
				<aui:input name="citoyenCity" required="false" />

				<%-- Champ : Adresse mail --%>
				<aui:input name="citoyenEmail" required="true" />

				<%-- Champ : téléphone --%>
				<aui:input name="citoyenPhone" required="false" />

				<%-- Champ : mobile --%>
				<aui:input name="citoyenMobile" required="true" />

				<%-- Champ : copyright --%>
				<aui:input name="citoyenEmail" required="true" />

			</aui:fieldset>

            <%-- Groupe de champs : video/image --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="label-video">

				<%-- Champ : Choix du media --%>
				<aui:input name="mediaChoice" label="media-preference" type="toggle-switch"
					value="${not empty dc.budgetParticipatif ? dc.budgetParticipatif.mediaChoice : true}" />

				<%-- Champ : URL de la video --%>
				<aui:input name="videoUrl" required="false" />

			    <%-- Selecteur : Image interne ou externe ? --%>
                <label><input type="radio" value="internalImage" name="imageType"
                    <c:if test="${(not empty dc.budgetParticipatif.imageId and dc.budgetParticipatif.imageId gt 0) or empty dc.budgetParticipatif.externalImageURL }">checked</c:if>> Image interne</label><br>
                <label><input type="radio" value="externalImage" name="imageType"
                    <c:if test="${(empty dc.budgetParticipatif.imageId or dc.budgetParticipatif.imageId eq 0) and not empty dc.budgetParticipatif.externalImageURL }">checked</c:if>> Image externe</label><br><br>

                <%-- Champ : Image interne --%>
                <div class="internalImage" <c:if test="${(empty dc.budgetParticipatif.imageId or dc.budgetParticipatif.imageId eq 0) and not empty dc.budgetParticipatif.externalImageURL }">style="display: none;"</c:if>>
                    <strasbourg-picker:image label="image" name="imageId" required="false" value="${dc.budgetParticipatif.imageId}" global="false"/>
                </div>

                <%-- Groupe de champs : Image externe --%>
                <div class="externalImage" <c:if test="${(not empty dc.budgetParticipatif.imageId and dc.budgetParticipatif.imageId gt 0) or empty dc.budgetParticipatif.externalImageURL }">style="display: none;"</c:if>>

                    <%-- Champ : URL de l'image externe --%>
                    <aui:input name="externalImageURL" helpMessage="help-image-size"/>

                    <%-- Champ : Copyright de l'image externe --%>
                    <aui:input name="externalImageCopyright"/>

                </div>

			</aui:fieldset>

            <%-- Groupe de champs : Lieux --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="label-place">

				<aui:input name="consultationPlacesText" required="false" />

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

                </div>
			</aui:fieldset>

            <%-- Groupe de champs : vocabulaire --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="label-vocabulary">

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

            <%-- Groupe de champs : Coup de coeur --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="crush">

				<%-- Champ : est un coup de coeur --%>
			    <aui:input name="isCrush" label="isCrush" type="toggle-switch"
			        value="${not empty dc.budgetParticipatif ? dc.budgetParticipatif.isCrush : false}" />

				<%-- Champ : Corps de la description --%>
				<aui:input name="crushComment" required="false" />

			</aui:fieldset>

            <%-- Groupe de champs : Phases --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="Phases">

			</aui:fieldset>

		</aui:fieldset-group>

		<%-- Composant : Menu de gestion de l'entite --%>
		<aui:button-row>
			
			<aui:input type="hidden" name="workflowAction" value="" />
			
			<%-- Test : Verification des droits d'edition et de sauvegarde --%>
			<c:if test="${(dc.hasPermission('ADD_BUDGET') and empty dc.budgetParticipatif or dc.hasPermission('EDIT_BUDGET') and not empty dc.budgetParticipatif) and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<c:if test="${dc.workflowEnabled}">
					<aui:button cssClass="btn-lg" type="submit" value="save" />
				</c:if>
				<c:if test="${not dc.workflowEnabled}">
					<aui:button cssClass="btn-lg" type="submit" name="publish" value="eu.publish" />
					<aui:button cssClass="btn-lg btn-default" type="submit" name="save-as-draft" value="save-as-draft" />
				</c:if>
			</c:if>
			
			<%-- Test : Verification des droits de supression --%>
			<c:if test="${not empty dc.budgetParticipatif && dc.hasPermission('DELETE_BUDGET') and empty themeDisplay.scopeGroup.getStagingGroup()}">
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
	<script src="/o/projectbo/js/project-bo-edit-budgetParticipatif.js" type="text/javascript"></script>
</liferay-util:html-bottom>

<%-- Script : permet l'affichage des alertes de validation d'action --%>
<aui:script>
	function <portlet:namespace />deleteEntity() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-this-entry" />')) {
			window.location = '${deleteBudgetParticipatifURL}';
		}
	}
</aui:script>