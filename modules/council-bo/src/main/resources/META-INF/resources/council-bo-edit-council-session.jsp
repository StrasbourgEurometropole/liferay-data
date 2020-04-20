<%@ include file="/council-bo-init.jsp"%>

<%@page import="eu.strasbourg.service.council.model.CouncilSession"%>

<%-- URL : definit le lien menant vers la page de listage de l'entite --%>
<liferay-portlet:renderURL varImpl="councilSessionssURL">
	<portlet:param name="tab" value="councilSessions" />
</liferay-portlet:renderURL>

<%-- URL : definit le lien menant vers la suppression de l'entite --%>
<liferay-portlet:actionURL name="deleteCouncilSession" var="deleteCouncilSessionURL">
	<portlet:param name="cmd" value="deleteCouncilSession" />
	<portlet:param name="tab" value="councilSessions" />
	<portlet:param name="concilSessionId"
	    value="${not empty dc.councilSession ? dc.councilSession.councilSessionId : ''}" />
</liferay-portlet:actionURL>

<%-- URL : definit le lien menant vers la sauvegarde de l'entite --%>
<liferay-portlet:actionURL name="saveCouncilSession" varImpl="saveCouncilSessionURL">
	<portlet:param name="cmd" value="saveCouncilSession" />
	<portlet:param name="tab" value="councilSessions" />
</liferay-portlet:actionURL>

<%-- Composant : Body --%>
<div class="container-fluid-1280 main-content-body">

	<%-- Composant : definit la liste des messages d'erreur  (voir methode "validate" dans le saveAction de l'entite) --%>
	<liferay-ui:error key="title-error" message="title-error" />
	<liferay-ui:error key="text-error" message="description-error" />

	<%-- Composant : formulaire de saisie de l'entite --%>
	<aui:form action="${saveCouncilSessionURL}" method="post" name="fm" onSubmit="submitForm(event);">

		<%-- Propriete : definit l'entite de reference pour le formulaire--%>
		<aui:model-context bean="${dc.councilSession}" model="<%=CouncilSession.class %>" />
		<aui:fieldset-group markupView="lexicon">

			<%-- Champ : (cache) PK de l'entite --%>
			<aui:input name="councilSessionId" type="hidden" />

			<%-- Groupe de champs : Generalites --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="general">

			    <%-- Champ : Titre --%>
                <aui:input name="title" required="true" />

                <%-- Champ : Type --%>
                <div><label><liferay-ui:message key="type" /></label></div>
                <label><input type="radio" value="internalImage" name="imageType"
                    <c:if test="${!dc.councilSession.isEurometropolitan}">checked</c:if>>
                        <liferay-ui:message key="type-municipal" />
                </label><br>
                <label><input type="radio" value="externalImage" name="imageType"
                    <c:if test="${dc.councilSession.isEurometropolitan}">checked</c:if>>
                        <liferay-ui:message key="type-eurometropolitan" />
                </label><br><br>

			    <%-- Champ : Duree --%>
                <aui:input name="date" required="true" />

				<%-- Champ : President --%>
                <aui:input type="text" name="officialLeader" required="true" />

			</aui:fieldset>

			<%-- Groupe de champs : Procuration --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="procurations">

				<div class="procuration-label"><label><liferay-ui:message key="enter-a-procuration" /></label></div>



			</aui:fieldset>

			<%-- Groupe de champs : Categorisations --%>
			<aui:fieldset collapsed="<%=true%>" collapsible="<%=true%>" label="categorization">

				<%-- Champ : Selection des categories (gere par le portail dans l'onglet "Categories" du BO) --%>
				<aui:input name="categories" type="assetCategories" wrapperCssClass="categories-selectors" />

				<%-- Hack pour ajouter une validation sur les vocabulaires obligatoires --%>
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

			</aui:fieldset>

		</aui:fieldset-group>

		<%-- Composant : Menu de gestion de l'entite --%>
		<aui:button-row>

			<aui:input type="hidden" name="workflowAction" value="" />

			<%-- Test : Verification des droits d'edition et de sauvegarde --%>
			<c:if test="${(dc.hasPermission('ADD_COUNCIL_SESSION') and empty dc.councilSession or dc.hasPermission('EDIT_COUNCIL_SESSION') and not empty dc.councilSession) and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<c:if test="${dc.workflowEnabled}">
					<aui:button cssClass="btn-lg" type="submit" value="save" />
				</c:if>
				<c:if test="${not dc.workflowEnabled}">
					<aui:button cssClass="btn-lg" type="submit" name="publish" value="eu.publish" />
					<aui:button cssClass="btn-lg btn-default" type="submit" name="save-as-draft" value="save-as-draft" />
				</c:if>
			</c:if>

			<%-- Test : Verification des droits de supression --%>
			<c:if test="${not empty dc.councilSession && dc.hasPermission('DELETE_COUNCIL_SESSION') and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<aui:button cssClass="btn-lg" onClick='<%=renderResponse.getNamespace() + "deleteEntity();"%>' type="cancel" value="delete" />
			</c:if>

			<%-- Composant : bouton de retour a la liste des entites --%>
			<aui:button cssClass="btn-lg" href="${param.returnURL}" type="cancel" />

		</aui:button-row>

	</aui:form>

</div>

<liferay-util:html-top>
	<script>
		var editCouncilSession = true;
	</script>
</liferay-util:html-top>

<liferay-util:html-bottom>
	<aui:script>
		define._amd = define.amd;
		define.amd = false;
	</aui:script>
	<script	src="/o/councilbo/js/vendors/jquery.autocomplete.js"></script>
	<script>
		define.amd = define._amd;
	</script>
	<script src="/o/councilbo/js/council-bo-edit-council-session.js" type="text/javascript"></script>
</liferay-util:html-bottom>

<%-- Script : permet l'affichage des alertes de validation d'action --%>
<aui:script>
	function <portlet:namespace />deleteEntity() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-this-entry" />')) {
			window.location = '${deleteCouncilSessionURL}';
		}
	}
</aui:script>