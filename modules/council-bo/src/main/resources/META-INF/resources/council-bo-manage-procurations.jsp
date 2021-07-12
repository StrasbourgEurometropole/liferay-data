<%@ include file="/council-bo-init.jsp"%>

<%@page import="eu.strasbourg.service.council.model.Procuration"%>

<%-- URL : definit le lien menant vers la page de listage de l'entite --%>
<liferay-portlet:renderURL varImpl="councilSessionsURL">
	<portlet:param name="tab" value="councilSessions" />
</liferay-portlet:renderURL>

<%-- TODO --%>
<%-- URL : definit le lien menant vers la fermeture de la procuration --%>
<liferay-portlet:actionURL name="closeProcuration" var="closeProcurationURL">
	<portlet:param name="cmd" value="deleteCouncilSession" />
	<portlet:param name="procurationId"
	    value="${not empty dc.councilSession ? dc.councilSession.councilSessionId : ''}" />
</liferay-portlet:actionURL>

<%-- URL : definit le lien menant vers la sauvegarde de l'entite --%>
<liferay-portlet:actionURL name="saveProcuration" varImpl="saveProcurationURL">
	<portlet:param name="cmd" value="saveProcuration" />
</liferay-portlet:actionURL>

<%-- Composant : Body --%>
<div class="container-fluid-1280 main-content-body council-bo">


	<%-- TODO--%>
	<%-- Composant : definit la liste des messages d'erreur  (voir methode "validate" dans le saveAction de l'entite) --%>
	<%-- Composant : definit la liste des messages d'erreur  (voir methode "doProcessAction" dans le deleteAction de l'entite) --%>




	<%-- Composant : formulaire de saisie de l'entite --%>
	<aui:form action="${saveProcurationURL}" method="post" name="fm" onSubmit="submitForm(event);">

		<%-- Propriete : definit l'entite de reference pour le formulaire--%>
		<aui:model-context bean="${dc.councilSession}" model="<%=Procuration.class %>" />
		<aui:fieldset-group markupView="lexicon">

			<%-- Champ : (cache) PK de l'entite --%>
			<aui:input name="councilSessionId" type="hidden" />

			<%-- Groupe de champs : Procuration --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="absents-and-procurations">

            <aui:input cssClass="officalId-hidden" type="hidden"
                name="${official.officialId}"
                value="${officialIdValue}" />

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
                                <strong><liferay-ui:message key="procuration-mode"/></strong>
                            </th>
                            <th>
                                <strong><liferay-ui:message key="is-presential" /></strong>
                            </th>
                            <th>
                                <strong><liferay-ui:message key="official-receiver" /></strong>
                            </th>
                        </tr>

                        <c:set var="allActiveOfficials" value="${dc.getAllActiveOfficials()}" />
                        <c:forEach var="official" items="${allActiveOfficials}">

                            <c:set var="procuration" value="${dc.findAssociatedProcuration(official.officialId)}" />

                            <button id="officalIdButton" class="officalIdButton" data-official-id="${official.officialId}" />

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

                            <tr data-council-types="${official.councilTypesIds}">
                                <td class="text-left" >
                                    ${official.fullName}
                                </td>

                                <td>
                                    <aui:input cssClass="checkAbsent" name="${official.officialId}-isAbsent" label="" type="checkbox"
                                        title="is-absent" checked="${isAbsentValue}" value="isAbsent" />
                                </td>
                                <td id="procurationMode">
                                    <div class="selectMode">
                                        <aui:select cssClass="modeSelect" id="procurationModeChoice" name="choice">
                                            <aui:option style="display: none" selected="${empty procuration}"></aui:option>
                                            <c:forEach items="${dc.getAllProcurationMode()}" var="procurationMode">
                                                <aui:option value="${procurationMode.getName()}" selected="${dc.verifId(procuration.procurationMode, procurationMode.getId())}">${procurationMode.name}</aui:option>
                                            </c:forEach>
                                        </aui:select>
                                    </div>
                                    <div class="inputMode">
                                        <aui:input type="text" name="autre" />
                                    </div>
                                </td>
                                <td>
                                    <div class="selectMode">
                                        <aui:select cssClass="presentialSelect" id="presentialChoice" name="choice">
                                            <aui:option style="display: none" selected="${empty procuration}"></aui:option>
                                                <c:forEach items="${dc.getAllProcurationPresential()}" var="presential">
                                                    <aui:option value="${presential.getName()}" selected="${dc.verifId(procuration.presential, presential.getId())}">${presential.getName()}</aui:option>
                                                </c:forEach>
                                        </aui:select>
                                    </div>
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
		var currentGroupId = ${dc.groupId}
	</script>
</liferay-util:html-top>
<liferay-util:html-bottom>
	<script	src="/o/agendabo/js/vendors/jquery.autocomplete.js"></script>
	<script src="/o/councilbo/js/council-bo-manage-procurations.js" type="text/javascript"></script>
</liferay-util:html-bottom>

<%-- Script : permet l'affichage des alertes de validation d'action --%>
<aui:script>
	function <portlet:namespace />deleteEntity() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-this-entry" />')) {
			window.location = '${deleteCouncilSessionURL}';
		}
	}
</aui:script>