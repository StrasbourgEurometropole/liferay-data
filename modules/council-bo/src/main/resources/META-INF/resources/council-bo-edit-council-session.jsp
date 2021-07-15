<%@ include file="/council-bo-init.jsp"%>

<%@page import="eu.strasbourg.service.council.model.CouncilSession"%>

<%-- URL : definit le lien menant vers la page de listage de l'entite --%>
<liferay-portlet:renderURL varImpl="councilSessionsURL">
	<portlet:param name="tab" value="councilSessions" />
</liferay-portlet:renderURL>

<%-- URL : definit le lien menant vers la suppression de l'entite --%>
<liferay-portlet:actionURL name="deleteCouncilSession" var="deleteCouncilSessionURL">
	<portlet:param name="cmd" value="deleteCouncilSession" />
	<portlet:param name="tab" value="councilSessions" />
	<portlet:param name="councilSessionId"
	    value="${not empty dc.councilSession ? dc.councilSession.councilSessionId : ''}" />
</liferay-portlet:actionURL>

<%-- URL : definit le lien menant vers la sauvegarde de l'entite --%>
<liferay-portlet:actionURL name="saveCouncilSession" varImpl="saveCouncilSessionURL">
	<portlet:param name="cmd" value="saveCouncilSession" />
	<portlet:param name="tab" value="councilSessions" />
</liferay-portlet:actionURL>

<%-- URL : definit le lien menant vers la gestion des procurations --%>
<liferay-portlet:renderURL varImpl="manageProcurationsURL">
    <portlet:param name="cmd" value="manageProcurations" />
    <portlet:param name="councilSessionId" value="${dc.councilSession.councilSessionId}" />
    <portlet:param name="returnURL" value="${councilSessionsURL}" />
    <portlet:param name="mvcPath" value="/council-bo-manage-procurations.jsp" />
</liferay-portlet:renderURL>

<%-- Composant : Body --%>
<div class="container-fluid-1280 main-content-body council-bo">

	<%-- Composant : definit la liste des messages d'erreur  (voir methode "validate" dans le saveAction de l'entite) --%>
	<liferay-ui:error key="title-error" message="title-error" />
	<liferay-ui:error key="title-already-used-error" message="title-already-used-error" />
	<liferay-ui:error key="date-error" message="date-error" />
	<liferay-ui:error key="title-already-exist-error" message="title-already-exist-error" />
	<liferay-ui:error key="official-leader-not-found-error" message="official-leader-not-found-error" />
	<liferay-ui:error key="official-leader-type-error" message="official-leader-type-error" />
    <liferay-ui:error key="official-voter-type-error" message="official-voter-type-error" />
    <liferay-ui:error key="official-voters-limit-error" message="official-voters-limit-error" />

	<%-- Composant : definit la liste des messages d'erreur  (voir methode "doProcessAction" dans le deleteAction de l'entite) --%>
	<liferay-ui:error key="council-has-delib-error" message="council-has-delib-error" />

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
                <aui:input name="title" required="true" size="75" />

                <%-- Champ : Type --%>
                <aui:select cssClass="toCustomSelect" id="council-type" name="council-type" label="council-type">
                    <c:forEach items="${dc.authorizedTypes}" var="type">
                        <aui:option value="${type.typeId}" selected="${dc.councilSession.typeId == type.typeId}">${type.title}</aui:option>
                    </c:forEach>
                </aui:select>

			    <%-- Champ : Date --%>
                <aui:input name="date" required="true" />

				<%-- Champ : President --%>
				<c:set var="officialLeaderFullName" value="${not empty officialLeaderId ? dc.officialLeaderFullName : ''}" />
				<div class="official-autocomplete-input-wrapper" id="official-autocomplete-input-wrapper">
                    <aui:input cssClass="autocomplete-shown" label="official-leader" type="text"
                        name="officialLeaderFullName" value="${officialLeaderFullName}" required="true" />
                    <aui:input cssClass="autocomplete-hidden" type="hidden" name="officialLeaderId" required="true" />
                </div>

			</aui:fieldset>

			<%-- Groupe de champs : Procuration --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="absents-and-procurations">

                <div id="procurations-table">
                    <table border="1">

                        <tr>
                            <th class="th-hidden"/>
                            <th class="th-hidden"/>
                            <th class="th-hidden"/>
                            <th class="th-hidden"/>
                            <th colspan="2">
                                <strong><liferay-ui:message key="start" /></strong>
                            </th>
                            <th colspan="2">
                                <strong><liferay-ui:message key="end" /></strong>
                            </th>
                        </tr>

                        <tr>
                            <th>
                                <strong><liferay-ui:message key="official" /></strong>
                            </th>
                            <th>
                                <strong><liferay-ui:message key="procuration-mode" /></strong>
                            </th>
                            <th>
                                <strong><liferay-ui:message key="presential" /></strong>
                            </th>
                            <th>
                                <strong><liferay-ui:message key="official-receiver" /></strong>
                            </th>
                            <th>
                                <strong><liferay-ui:message key="hour" /></strong>
                            </th>
                            <th>
                                <strong><liferay-ui:message key="from-point" /></strong>
                            </th>
                            <th>
                                <strong><liferay-ui:message key="hour" /></strong>
                            </th>
                            <th>
                                <strong><liferay-ui:message key="to-point" /></strong>
                            </th>
                        </tr>

                        <c:set var="procurationsHistoric" value="${dc.getProcurationsHistoric()}" />
                        <c:forEach var="procuration" items="${procurationsHistoric}">
                            <c:set var="official" value="${dc.getOfficial(procuration.officialUnavailableId)}" />
                            <c:set var="officialVotersIdValue" value="${procuration.officialVotersId}" />
                            <c:set var="officialVotersFullName" value="${procuration.officialVotersFullName}" />
                            <c:set var="disabledInput" value="true" />

                            <tr data-council-types="${official.councilTypesIds}">
                                <td class="text-left">
                                    ${official.fullName}
                                </td>
                                <td>
                                <c:choose>
                                    <c:when test="${procuration.procurationMode eq 0}">
                                        -
                                    </c:when>
                                    <c:otherwise>
                                        ${procuration.procurationMode eq 4?procuration.otherProcurationMode:dc.getProcurationMode(procuration.procurationMode)}
                                    </c:otherwise>
                                </c:choose>
                                </td>
                                <td>
                                    ${empty dc.getProcurationPresential(procuration.presential)?"-":dc.getProcurationPresential(procuration.presential)}
                                </td>
                                <td class="text-left">
                                    ${empty officialVotersFullName?"Aucun":officialVotersFullName}
                                </td>
                                <td>
                                    <fmt:formatDate value="${procuration.startHour}" pattern="HH:mm:ss" />
                                </td>
                                <td>
                                    ${procuration.startDelib}${procuration.isAfterVote?" - Intervenu apres le vote":""}
                                </td>
                                <td>
                                    <fmt:formatDate value="${procuration.endHour}" pattern="HH:mm:ss" />
                                </td>
                                <td>
                                    ${procuration.endDelib eq -1?"":procuration.endDelib}
                                </td>
                            </tr>
                        </c:forEach>

                    </table>
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
                    <aui:button cssClass="btn-lg" type="submit" name="publish" value="save" />
                </c:if>
			</c:if>

			<%-- Test : Verification des droits de supression --%>
			<c:if test="${not empty dc.councilSession && dc.hasPermission('DELETE_COUNCIL_SESSION') and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<aui:button cssClass="btn-lg" onClick='<%=renderResponse.getNamespace() + "deleteEntity();"%>' type="cancel" value="delete" />
			</c:if>

			<%-- Composant : bouton de retour a la liste des entites --%>
			<aui:button cssClass="btn-lg" href="${param.returnURL}" type="cancel" />

			<%-- Composant : bouton de retour a la liste des entites --%>
            <aui:button cssClass="btn-lg" href="${manageProcurationsURL}" type="cancel" value="Gestion des procurations" />

            <!-- RESOURCE ACTION : Export de historique des procurations -->
            <liferay-portlet:resourceURL id="exportProcurationsHistoric" var="exportProcurationsHistoricURL"
                    copyCurrentRenderParameters="false">
                <portlet:param name="councilSessionId"
	                value="${not empty dc.councilSession ? dc.councilSession.councilSessionId : ''}" />
            </liferay-portlet:resourceURL>
            <aui:button cssClass="btn-lg" href="${exportProcurationsHistoricURL}" type="cancel" value="export-procurations" />

		</aui:button-row>

	</aui:form>

</div>

<liferay-util:html-top>
	<script>
		var editCouncilSession = true;
		var currentGroupId = ${dc.groupId}
	</script>
</liferay-util:html-top>

<liferay-util:html-bottom>
	<script	src="/o/agendabo/js/vendors/jquery.autocomplete.js"></script>
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