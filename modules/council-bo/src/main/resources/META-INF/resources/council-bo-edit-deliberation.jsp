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
	<liferay-ui:error key="order-error" message="order-error" />
	<liferay-ui:error key="council-session-error" message="council-session-error" />

	<%-- Composant : formulaire de saisie de l'entite --%>
	<aui:form action="${saveDeliberationURL}" method="post" name="fm" onSubmit="submitForm(event);">

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
                <aui:input type="textarea" name="title" required="true">
                    <aui:validator name="maxLength">500</aui:validator>
                </aui:input>

			    <%-- Champ : Session --%>
                <aui:select name="councilSessionId" label="councilSession" required="true">
                    <c:forEach var="council" items="${dc.availableCouncilSessions}">
                        <aui:option value="${council.councilSessionId}"
                            label="${council.getTitle()}"
                            selected="${council.councilSessionId eq dc.deliberation.councilSessionId}" />
                    </c:forEach>
                </aui:select>

			    <%-- Champ : Statut --%>
                <aui:input name="stage" disabled="true" />

			</aui:fieldset>

            <c:if test="${not empty dc.getVoteBeans()}">
                <%-- Groupe de champs : Votes --%>
                <aui:fieldset collapsed="<%=true%>" collapsible="<%=true%>" label="votes">
                    <div class="wrapper-vote">
                        <div class="wrapper-column">
                            <div class="wrapper-row">
                                <label>${dc.getPOUR()}</label>
                                <span>${dc.getVoteCountForAResult(dc.getPOUR())}</span>
                            </div>
                            <div class="wrapper-row">
                                <label><liferay-ui:message key="officials-voting"/></label>
                                <span>${dc.deliberation.countOfficialsVoting}</span>
                            </div>
                        </div>
                        <div class="wrapper-column">
                            <div class="wrapper-row">
                                <label>${dc.getCONTRE()}</label>
                                <span>${dc.getVoteCountForAResult(dc.getCONTRE())}</span>
                            </div>
                            <div class="wrapper-row">
                                <label><liferay-ui:message key="officials-active"/></label>
                                <span>${dc.deliberation.countOfficialsActive}</span>
                            </div>
                        </div>
                        <div class="wrapper-column">
                            <div class="wrapper-row">
                                <label>${dc.getABSTENTION()}</label>
                                <span>${dc.getVoteCountForAResult(dc.getABSTENTION())}</span>
                            </div>
                            <div class="wrapper-row">
                                <label><liferay-ui:message key="quorum"/></label>
                                <span>${dc.quorum}</span>
                            </div>
                        </div>
                    </div>
                </aui:fieldset>

                <%-- Groupe de champs : Détai des Votes --%>
                <aui:fieldset collapsed="<%=true%>" collapsible="<%=true%>" label="detail-votes">
                    <div id="procurations-table">
                        <table border="1">
                            <tr>
                                <th>
                                    <strong><liferay-ui:message key="official" /></strong>
                                </th>
                                <th >
                                    <strong><liferay-ui:message key="vote" /></strong>
                                </th>
                                <th >
                                    <strong><liferay-ui:message key="vote-by-procuration-by" /></strong>
                                </th>
                            </tr>
                            <c:forEach var="vote" items="${dc.getVoteBeans()}">
                                <tr>
                                    <td class="text-left">
                                        ${vote.voter}
                                    </td>
                                    <td>
                                       <div class="${vote.cssClass}"> ${vote.result}</div>
                                    </td>
                                    <td class="text-left">
                                        ${vote.procurationVoter}
                                    </td>
                                </tr>
                            </c:forEach>

                        </table>
                    </div>
                </aui:fieldset>
            </c:if>

            <%-- Groupe de champs : Categorisations (cachée via la classe 'hidden') --%>
            <aui:fieldset collapsed="<%=true%>" cssClass="hidden" collapsible="<%=true%>" label="categorization">

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
                <aui:button cssClass="btn-lg" type="submit" value="save" />
			</c:if>

			<%-- Test : Verification des droits de supression --%>
			<c:if test="${not empty dc.deliberation and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<aui:button cssClass="btn-lg" onClick='<%=renderResponse.getNamespace() + "deleteEntity();"%>' value="delete" />
			</c:if>

			<c:if test="${not empty dc.deliberation and empty themeDisplay.scopeGroup.getStagingGroup()}">
                <aui:button cssClass="btn-lg" onClick='<%=renderResponse.getNamespace() + "resetEntity();"%>' value="reset" />
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

	function <portlet:namespace />resetEntity() {
        if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-reset-this-deliberation" />')) {
            window.location = '${resetDeliberationURL}';
        }
    }
</aui:script>