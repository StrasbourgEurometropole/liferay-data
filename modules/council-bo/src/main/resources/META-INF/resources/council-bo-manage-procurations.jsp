<%@ include file="/council-bo-init.jsp"%>

<%@page import="eu.strasbourg.service.council.model.Procuration"%>

<%-- URL : definit le lien menant vers la page de listage de l'entite --%>
<liferay-portlet:renderURL varImpl="councilSessionsURL">
	<portlet:param name="tab" value="councilSessions" />
</liferay-portlet:renderURL>

<%-- URL : definit le lien menant vers la sauvegarde ou la suprpession de l'entite --%>
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

            <aui:input cssClass="officalIdHidden" id="officalIdHidden" type="hidden"
                name="officalIdHidden"
                value="${officialIdValue}" />

            <aui:input cssClass="officalIdHidden" id="councilIdHidden" type="hidden"
                name="councilIdHidden"
                value="${dc.councilSession.councilSessionId}" />

            <aui:input cssClass="actionHidden" id="actionHidden" type="hidden"
                name="actionHidden"
                value="${actionValue}" />

            <aui:input cssClass="officalIdHidden" id="editHidden" type="hidden"
                name="editHidden"
                value="<%=false%>" />

            <aui:input cssClass="procurationIdHidden" id="procurationIdHidden" type="hidden"
                name="procurationIdHidden"
                value="${procurationId}" />

                <h3>${dc.getCouncilSession().title}</h3>

                <div id="refresh" name="refresh">
                     <button type="button" name="reloadButton" id="reloadButton" class="reloadButton" title ="refresh tableau" style="display: inline-block;">
                        <liferay-ui:icon
                            icon="reload"
                            markupView="lexicon"
                        />
                     </button>
                    <p id="refreshTimer" name="refreshTimer" style="display: inline-block;"></p>
                    <p id="refreshTimerValue" name="refreshTimerValue" style="display : none;">5000</p>
                </div>
                <div id="procurations-table">
                    <table border="1">

                        <tr>
                            <th class="largeColumn">
                                <strong><liferay-ui:message key="official" /></strong>
                            </th>
                            <th class="reduceColumn">
                                <strong><liferay-ui:message key="is-absent" /></strong>
                            </th>
                            <th class="largeColumn">
                                <strong><liferay-ui:message key="procuration-mode"/></strong>
                            </th>
                            <th class="largeColumn">
                                <strong><liferay-ui:message key="is-presential" /></strong>
                            </th>
                            <th class="largeColumn">
                                <strong><liferay-ui:message key="official-receiver" /></strong>
                            </th>
                            <th class="reduceColumn">
                                <strong><liferay-ui:message key="action" /></strong>
                            </th>
                        </tr>

                        <c:set var="allActiveOfficials" value="${dc.getAllActiveOfficials()}" />
                        <c:forEach var="official" items="${allActiveOfficials}">

                            <c:set var="procuration" value="${dc.findAssociatedProcuration(official.officialId)}" />

                            <c:choose>
                                <c:when test="${procuration != null}">
                                    <c:set var="hasStartHour" value = "${not empty procuration.startHour ? 'true' : 'false'}"/>
                                    <c:set var="hasEndHour" value = "${not empty procuration.endHour ? 'true' : 'false'}"/>
                                    <c:set var="isAbsentValue" value= "${hasStartHour && !hasEndHour}" />
                                    <c:set var="officialVotersIdValue" value="${procuration.officialVotersId}" />
                                    <c:set var="officialVotersFullName" value="${procuration.officialVotersFullName}" />
                                    <c:set var="otherProcurationMode" value="${procuration.otherProcurationMode}" />
                                    <c:set var="disabledInput" value="false" />
                                    <c:set var="procurationId" value="${procuration.procurationId}" />
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
                                    <div id="checkAbsent" name="${official.officialId}-checkAbsent" style="display: none">
                                        <liferay-ui:icon
                                            icon="check-circle"
                                            markupView="lexicon"
                                        />
                                    </div>
                                    <input id="inputAbsent" class="inputAbsent" name="${official.officialId}-inputAbsent" type="hidden" value ="${isAbsentValue}"/>
                                </td>
                                <td id="procurationMode">
                                    <div class="selectMode" id="selectMode" name="${official.officialId}-selectMode">
                                        <aui:select cssClass="modeSelect" id="modeSelect" name="${official.officialId}-modeSelect" disabled="true">
                                            <aui:option style="display: none" selected="${empty procuration}"></aui:option>
                                            <c:forEach items="${dc.getAllProcurationMode()}" var="procurationMode">
                                                <aui:option value="${procurationMode.getId()}" selected="${dc.verifId(procuration.procurationMode, procurationMode.getId())}">${procurationMode.name}</aui:option>
                                            </c:forEach>
                                        </aui:select>
                                    </div>
                                    <div class="inputMode">
                                        <aui:input type="text" name="${official.officialId}-autre" disabled="true" value="${otherProcurationMode}"/>
                                    </div>
                                </td>
                                <td>
                                    <div class="selectMode"  >
                                        <aui:select cssClass="presentialSelect" id="presentialSelect" name="${official.officialId}-presentialSelect" disabled="true">
                                            <aui:option style="display: none" selected="${empty procuration}"></aui:option>
                                                <c:forEach items="${dc.getAllProcurationPresential()}" var="presential">
                                                    <aui:option value="${presential.getId()}" selected="${dc.verifId(procuration.presential, presential.getId())}">${presential.getName()}</aui:option>
                                                </c:forEach>
                                        </aui:select>
                                    </div>
                                </td>
                                <td>
                                    <div class="official-autocomplete-input-wrapper" id="official-autocomplete-input-wrapper-${official.officialId}">
                                        <aui:input cssClass="autocomplete-shown" label="" type="text"
                                            title="official-receiver" name="${official.officialId}-officialVoters"
                                            value="${officialVotersFullName}" disabled="true"/>

                                        <aui:input cssClass="hiddenBeneficiary" id ="hiddenBeneficiary" type="hidden"
                                            name="${official.officialId}-officialVotersId"
                                            value="${officialVotersIdValue}" />
                                    </div>
                                </td>
                                <td>
                                    <div style="text-align: center;">
                                        <button type="button" name="${official.officialId}-editButton" class="editButton" title ="Editer la ligne">
                                           <liferay-ui:icon
                                               icon="pencil"
                                               markupView="lexicon"
                                           />
                                        </button>
                                        <button id="saveButton" class="saveButton" name="${official.officialId}-saveButton" title ="Enregistrer la procuration"
                                            data-official-id="${official.officialId}" action="save" >
                                           <liferay-ui:icon
                                               icon="check"
                                               markupView="lexicon"
                                           />
                                        </button>
                                        <button type="button" name="${official.officialId}-resetButton" class="resetButton" title ="Vider la ligne">
                                           <liferay-ui:icon
                                               icon="undo"
                                               markupView="lexicon"
                                           />
                                        </button>
                                        <button id="closeButton" class="closeButton" name="${official.officialId}-closeButton" title ="Fermer la procuration"
                                            procuration-id="${procurationId}">
                                            <liferay-ui:icon
                                                    icon="trash"
                                                    markupView="lexicon"
                                                />
                                        </button>
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