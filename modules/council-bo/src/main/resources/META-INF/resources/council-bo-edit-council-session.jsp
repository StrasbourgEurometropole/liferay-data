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

<%-- Composant : Body --%>
<div class="container-fluid-1280 main-content-body council-bo">

	<%-- Composant : definit la liste des messages d'erreur  (voir methode "validate" dans le saveAction de l'entite) --%>
	<liferay-ui:error key="title-error" message="title-error" />
	<liferay-ui:error key="title-already-used-error" message="title-already-used-error" />
	<liferay-ui:error key="date-error" message="date-error" />
	<liferay-ui:error key="date-already-used-error" message="date-already-used-error" />
	<liferay-ui:error key="title-already-exist-error" message="title-already-exist-error" />
	<liferay-ui:error key="official-leader-not-found-error" message="official-leader-not-found-error" />
	<liferay-ui:error key="official-leader-type-error" message="official-leader-type-error" />
	<liferay-ui:error key="official-voters-limit-error" message="official-voters-limit-error" />

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
                <div><label><liferay-ui:message key="type" /></label></div>
                <label class="text-normal">
                    <input type="radio" value="municipal" name="<portlet:namespace />type"
                        <c:if test="${!dc.councilSession.isEurometropolitan()}">checked</c:if>>
                    <liferay-ui:message key="type-municipal" />
                </label><br>
                <label class="text-normal">
                    <input type="radio" value="eurometropolitan" name="<portlet:namespace />type"
                        <c:if test="${dc.councilSession.isEurometropolitan()}">checked</c:if>>
                    <liferay-ui:message key="type-eurometropolitan" />
                </label><br><br>

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
                            <th>
                                <strong><liferay-ui:message key="official" /></strong>
                            </th>
                            <th>
                                <strong><liferay-ui:message key="is-absent" /></strong>
                            </th>
                            <th>
                                <strong><liferay-ui:message key="official-receiver" /></strong>
                            </th>
                        </tr>

                        <c:forEach var="official" items="${dc.getAllActiveOfficials()}">

                            <c:set var="procuration" value="${dc.findAssociatedProcuration(official.officialId)}" />
                            <c:choose>
                                <c:when test="${procuration != null}">
                                    <c:set var="isAbsentValue" value="${procuration.isAbsent ? 'true' : 'false'}" />
                                    <c:set var="officialVotersIdValue" value="${procuration.officialVotersId}" />
                                    <c:set var="officialVotersFullName" value="${procuration.officialVotersFullName}" />
                                    <c:set var="disabledInput" value="false" />
                                </c:when>
                                <c:otherwise>
                                    <c:set var="isAbsentValue" value="false" />
                                    <c:set var="officialVotersIdValue" value="0" />
                                    <c:set var="officialVotersFullName" value="" />
                                    <c:set var="disabledInput" value="true" />
                                </c:otherwise>
                            </c:choose>

                            <tr data-is-municipal="${official.isMunicipal}" data-is-eurometropolitan="${official.isEurometropolitan}">
                                <td class="text-left" >
                                    ${official.fullName}
                                </td>
                                <td>
                                    <aui:input name="${official.officialId}-isAbsent" label="" type="checkbox"
                                        title="is-absent" checked="${isAbsentValue}" value="isAbsent" />
                                </td>
                                <td>
                                    <div class="official-autocomplete-input-wrapper" id="official-autocomplete-input-wrapper-${official.officialId}">
                                        <aui:input cssClass="autocomplete-shown" label="" type="text"
                                            title="official-receiver" name="${official.officialId}-officialVoters"
                                            value="${officialVotersFullName}" disabled="${disabledInput}" />
                                        <aui:input cssClass="autocomplete-hidden" type="hidden"
                                            name="${official.officialId}-officialVotersId"
                                            value="${officialVotersIdValue}" />
                                    </div>
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
	<aui:script>
		define._amd = define.amd;
		define.amd = false;
	</aui:script>
	<script	src="/o/agendabo/js/vendors/jquery.autocomplete.js"></script>
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