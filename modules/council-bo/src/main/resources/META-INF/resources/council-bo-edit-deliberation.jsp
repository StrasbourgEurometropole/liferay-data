<%@ include file="/council-bo-init.jsp"%>

<%@page import="eu.strasbourg.service.council.model.Deliberation"%>

<%-- URL : definit le lien menant vers la page de listage de l'entite --%>
<liferay-portlet:renderURL varImpl="deliberationsURL">
	<portlet:param name="tab" value="deliberations" />
</liferay-portlet:renderURL>

<%-- URL : definit le lien menant vers la suppression de l'entite --%>
<liferay-portlet:actionURL name="deleteDeliberation" var="deleteDeliberationURL">
	<portlet:param name="cmd" value="deleteDeliberation" />
	<portlet:param name="tab" value="deliberations" />
	<portlet:param name="deliberationId"
	    value="${not empty dc.deliberation ? dc.deliberation.deliberationId : ''}" />
</liferay-portlet:actionURL>

<%-- URL : definit le lien menant vers la suppression de l'entite --%>
<liferay-portlet:actionURL name="resetDeliberation" var="resetDeliberationURL">
	<portlet:param name="cmd" value="resetDeliberation" />
	<portlet:param name="tab" value="deliberations" />
	<portlet:param name="deliberationId"
	    value="${not empty dc.deliberation ? dc.deliberation.deliberationId : ''}" />
</liferay-portlet:actionURL>

<%-- URL : definit le lien menant vers la sauvegarde de l'entite --%>
<liferay-portlet:actionURL name="saveDeliberation" varImpl="saveDeliberationURL">
	<portlet:param name="cmd" value="saveDeliberation" />
	<portlet:param name="tab" value="deliberations" />
</liferay-portlet:actionURL>


<%-- Composant : Body --%>
<div class="container-fluid-1280 main-content-body">

	<%-- Composant : definit la liste des messages d'erreur  (voir methode "validate" dans le saveAction de l'entite) --%>
	<liferay-ui:error key="title-error" message="title-error" />
	<liferay-ui:error key="text-error" message="description-error" />

	<%-- Composant : formulaire de saisie de l'entite --%>
	<aui:form action="${saveCouncilSessionURL}" method="post" name="fm" onSubmit="submitForm(event);">

		<%-- Propriete : definit l'entite de reference pour le formulaire--%>
		<aui:model-context bean="${dc.deliberation}" model="<%=Deliberation.class %>" />
		<aui:fieldset-group markupView="lexicon">

			<%-- Champ : (cache) PK de l'entite --%>
			<aui:input name="deliberationId" type="hidden" />

			<%-- Groupe de champs : Generalites --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="general">

			    <%-- Champ : Ordre --%>
                <aui:input name="order" required="true" />

			    <%-- Champ : Titre --%>
                <aui:input type="textarea" name="title" required="true" />

			    <%-- Champ : Session --%>
                <aui:select name="councilSessionId" label="councilSession" required="true">
                    <c:forEach var="council" items="${dc.availableCouncilSessions}">
                        <aui:option value="${council.councilSessionId}"
                            label="${council.getTitle()}"
                            selected="${council.councilSessionId eq dc.deliberation.councilSessionId}" />
                    </c:forEach>
                </aui:select>

			    <%-- Champ : Statut --%>
                 <aui:select name="stage" label="stage">
                    <c:forEach var="stage" items="${dc.stages}">
                        <aui:option value="${stage.name}"
                            label="${stage.name}"
                            selected="${stage.name eq dc.deliberation.stage}" />
                    </c:forEach>
                </aui:select>

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
			<c:if test="${(dc.hasPermission('ADD_DELIBERATION') and empty dc.deliberation or dc.hasPermission('EDIT_DELIBERATION') and not empty dc.deliberation) and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<c:if test="${dc.workflowEnabled}">
					<aui:button cssClass="btn-lg" type="submit" value="save" />
				</c:if>
				<c:if test="${not dc.workflowEnabled}">
					<aui:button cssClass="btn-lg" type="submit" name="publish" value="eu.publish" />
					<aui:button cssClass="btn-lg btn-default" type="submit" name="save-as-draft" value="save-as-draft" />
				</c:if>
			</c:if>

			<%-- Test : Verification des droits de supression --%>
			<c:if test="${not empty dc.deliberation && dc.hasPermission('DELETE_DELIBERATION') and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<aui:button cssClass="btn-lg" onClick='<%=renderResponse.getNamespace() + "deleteEntity();"%>' type="cancel" value="delete" />
			</c:if>

			<c:if test="${not empty dc.deliberation and empty themeDisplay.scopeGroup.getStagingGroup()}">
                <aui:button cssClass="btn-lg" onClick='<%=renderResponse.getNamespace() + "resetEntity();"%>' type="cancel" value="delete" />
            </c:if>

			<%-- Composant : bouton de retour a la liste des entites --%>
			<aui:button cssClass="btn-lg" href="${param.returnURL}" type="cancel" />

		</aui:button-row>

	</aui:form>

</div>

<aui:script>
	function <portlet:namespace />deleteEntity() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-this-entry" />')) {
			window.location = '${deleteDeliberationURL}';
		}
	}

	function <portlet:namespace />deleteEntity() {
        if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-reset-this-deliberation" />')) {
            window.location = '${resetDeliberationURL}';
        }
    }
</aui:script>