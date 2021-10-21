<%@ include file="/notif-bo-init.jsp"%>

<%@page import="eu.strasbourg.service.notif.model.Notification"%>

<%-- URL : definit le lien menant vers la sauvegarde de l'entite --%>
<liferay-portlet:actionURL name="saveNotification" varImpl="saveNotificationURL">
	<portlet:param name="cmd" value="saveNotification" />
	<portlet:param name="tab" value="notifications" />
</liferay-portlet:actionURL>

<%-- Composant : Body --%>
<div class="container-fluid-1280 main-content-body ejob-bo">

	<%-- Composant : definit la liste des messages d'erreur  (voir methode "validate" dans le saveAction de l'entite) --%>
	<liferay-ui:error key="service-error" message="eu.strasbourg.notification.service-error" />
	<liferay-ui:error key="nature-error" message="eu.strasbourg.notification.nature-error" />
	<liferay-ui:error key="broadcast-date-error" message="eu.strasbourg.notification.broadcast-error" />
	<liferay-ui:error key="title-error" message="eu.strasbourg.notification.title-error" />
	<liferay-ui:error key="start-date-error" message="eu.strasbourg.notification.start-date-error" />
	<liferay-ui:error key="end-date-error" message="eu.strasbourg.notification.end-date-error" />
	<liferay-ui:error key="content-error" message="eu.strasbourg.notification.content-error" />
	<liferay-ui:error key="broadcast-type-error" message="eu.strasbourg.notification.broadcast-type-error" />
    <liferay-ui:error key="district-error" message="eu.strasbourg.notification.district-error" />
    <liferay-ui:error key="broadcast-channels-error" message="eu.strasbourg.notification.broadcast-channels-error" />


	<%-- Composant : formulaire de saisie de l'entite --%>
	<aui:form action="${saveNotificationURL}" method="post" name="fm" onSubmit="submitForm(event);">

		<%-- Propriete : definit l'entite de reference pour le formulaire--%>
		<aui:model-context bean="${dc.notification}" model="<%=Notification.class %>" />
		<aui:fieldset-group markupView="lexicon">

			<%-- Champ : (cache) PK de l'entite --%>
			<aui:input name="notificationId" type="hidden" />
			<aui:input name="new" type="hidden" />

			<%-- Groupe de champs : Generale --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="eu.strasbourg.notif.general">

                <%-- Champ : Service   --%>
                <aui:select cssClass="toCustomSelect" id="service" name="service" label="eu.strasbourg.notif.service" required="true" disabled="${dc.isOnlyView()}" >
                    <aui:option style="display: none" selected="${empty dc.notification}"><liferay-ui:message key="eu.strasbourg.notif.choose-service" /></aui:option>
                    <c:forEach items="${dc.services}" var="service">
                        <aui:option value="${service.serviceId}" selected="${dc.notification.serviceId == service.serviceId || fn:length(dc.services) == 1}" >${service.name}</aui:option>
                    </c:forEach>
                </aui:select>
                <div id="service-error" style="display: none">
                    <liferay-ui:message key="this-field-is-required" />
                </div>

                <%-- Champ : Alerte --%>
                <aui:input type="checkbox" value="1" name="notificationType" label="eu.strasbourg.notif.alerte"
                    checked="${not empty dc.notification and dc.notification.isAlert == 1}" disabled="${dc.isOnlyView()}" />

                <%-- Champ : Nature   --%>
                <aui:select cssClass="toCustomSelect" id="nature" name="nature" label="eu.strasbourg.notif.nature" required="true" disabled="${dc.isOnlyView()}" >
                    <aui:option style="display: none" selected="${empty dc.notification}"><liferay-ui:message key="eu.strasbourg.notif.choose-nature" /></aui:option>
                    <c:forEach items="${dc.natures}" var="nature">
                        <aui:option value="${nature.natureId}" data-service-id="${nature.serviceId}" selected="${dc.notification.natureId == nature.natureId}" >${nature.name}</aui:option>
                    </c:forEach>
                </aui:select>
                <div id="nature-error" style="display: none">
                    <liferay-ui:message key="this-field-is-required" />
                </div>

                <%-- Champ : Date de diffusion --%>
                <aui:input cssClass="hasTime" name="broadcastDate" label="eu.strasbourg.notif.broadcast-date" required="true" disabled="${dc.isOnlyView()}" />
                <!-- Hack pour ajouter une validation -->
                <div class="has-error form-group">
                    <aui:input type="hidden" name="broadcastDateValidatorInputHelper" value="placeholder" >
                        <aui:validator name="custom" errorMessage="this-field-is-required">
                            function (val, fieldNode, ruleValue) {
                                var validate = document.getElementById('_eu_strasbourg_portlet_notif_NotifBOPortlet_broadcastDate').value.length > 0;
                                if (!validate) {
                                    document.getElementById("_eu_strasbourg_portlet_notif_NotifBOPortlet_broadcastDate").scrollIntoView();
                                    event.preventDefault();
                                }
                                return validate;
                            }
                        </aui:validator>
                    </aui:input>
                </div>

            </aui:fieldset>

			<%-- Groupe de champs : Notification --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="eu.strasbourg.notif.notification">

                <%-- Champ : Titre --%>
                <aui:input name="title" label="eu.strasbourg.notif.title" localized="true" required="true" type="text" disabled="${dc.isOnlyView()}" />

                <%-- Champ : Sous-Titre --%>
                <aui:input name="subtitle" label="eu.strasbourg.notif.subtitle" required="false" localized="true" type="textarea" disabled="${dc.isOnlyView()}" />

                <%-- Champ : Date de début --%>
                <aui:input name="startDate" label="eu.strasbourg.notif.notification.start-date" required="true" disabled="${dc.isOnlyView()}" />
                <!-- Hack pour ajouter une validation -->
                <div class="has-error form-group">
                    <aui:input type="hidden" name="startDateValidatorInputHelper" value="placeholder">
                        <aui:validator name="custom" errorMessage="please-enter-a-valid-date">
                            function (val, fieldNode, ruleValue) {
                                var validate = document.getElementById('_eu_strasbourg_portlet_notif_NotifBOPortlet_startDate').value.match(/^(\d{1,2})\/(\d{1,2})\/(\d{4})$/);
                                if (!validate) {
                                    document.getElementById("_eu_strasbourg_portlet_notif_NotifBOPortlet_startDate").scrollIntoView();
                                    event.preventDefault();
                                }
                                return validate;
                            }
                        </aui:validator>
                    </aui:input>
                </div>

                <%-- Champ : Date de fin --%>
                <aui:input name="endDate" label="eu.strasbourg.notif.notification.end-date" required="true" disabled="${dc.isOnlyView()}" />
                <!-- Hack pour ajouter une validation -->
                <div class="has-error form-group">
                    <aui:input type="hidden" name="endDateValidatorInputHelper" value="placeholder">
                        <aui:validator name="custom" errorMessage="this-field-is-required">
                            function (val, fieldNode, ruleValue) {
                                var validate = document.getElementById('_eu_strasbourg_portlet_notif_NotifBOPortlet_endDate').value.length > 0;
                                if (!validate) {
                                    document.getElementById("_eu_strasbourg_portlet_notif_NotifBOPortlet_endDate").scrollIntoView();
                                    event.preventDefault();
                                }
                                return validate;
                            }
                        </aui:validator>
                    </aui:input>
                </div>

                <%-- Champ : Message --%>
                <aui:select cssClass="toCustomSelect" id="message" name="message" label="eu.strasbourg.notif.message" required="false" disabled="${dc.isOnlyView()}" >
                    <aui:option style="display: none" selected="${empty dc.notification}"><liferay-ui:message key="eu.strasbourg.notif.choose-message" /></aui:option>
                    <aui:option value="0" ></aui:option>
                    <c:forEach items="${dc.messages}" var="message">
                        <aui:option value="${message.messageId}" data-service-id="${message.serviceId}" data-content-fr="${fn:escapeXml(message.getContent('fr_FR'))}" data-content-de="${fn:escapeXml(message.getContent('de_DE'))}" data-content-en="${fn:escapeXml(message.getContent('en_US'))}" selected="${dc.notification.messageId == message.messageId}" >${message.getContent(locale)}</aui:option>
                    </c:forEach>
                </aui:select>

                <%-- Champ : Contenu --%>
                <aui:input name="content" label="eu.strasbourg.notif.content" required="true" localized="true" type="textarea" disabled="${dc.isOnlyView()}" />

                <%-- Champ : Label de l'URL --%>
                <aui:input name="labelUrl" label="eu.strasbourg.notif.label-url" localized="true" required="false" type="text" disabled="${dc.isOnlyView()}" />

                <%-- Champ : URL --%>
                <aui:input name="url" label="eu.strasbourg.notif.url" localized="true" required="false" type="text" disabled="${dc.isOnlyView()}" />

            </aui:fieldset>

			<%-- Groupe de champs : Diffusion --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="eu.strasbourg.notif.diffusion">

                <%-- Champ : Type de diffusion --%>
                <aui:select cssClass="toCustomSelect" id="broadcast-type" name="broadcast-type" label="eu.strasbourg.notif.broadcast-type" required="true" disabled="${dc.isOnlyView()}">
                    <aui:option style="display: none" selected="${empty dc.notification}"><liferay-ui:message key="eu.strasbourg.notif.choose-broadcast-type" /></aui:option>
                    <c:forEach items="${dc.broadcastTypes}" var="broadcastType">
                        <aui:option value="${broadcastType.id}" selected="${dc.notification.typeBroadcast == broadcastType.id}" >${broadcastType.label}</aui:option>
                    </c:forEach>
                </aui:select>
                <div id="broadcastType-error" style="display: none">
                    <liferay-ui:message key="this-field-is-required" />
                </div>

                <aui:select cssClass="toCustomSelect" id="district" name="district" label="eu.strasbourg.notif.district" required="true" disabled="${dc.isOnlyView()}">
                    <aui:option style="display: none" selected="${empty dc.notification}"><liferay-ui:message key="eu.strasbourg.notif.choose-district" /></aui:option>
                    <c:forEach var="district" items="${dc.districts}">
                        <aui:option value="${district.categoryId}" selected="${dc.notification.district == district.categoryId}">${district.name}</aui:option>
                    </c:forEach>
                </aui:select>

                <%-- Champ : Canaux de diffusion --%>
                <aui:select multiple="true" cssClass="choices-element" id="broadcast-channels" name="broadcast-channels" label="eu.strasbourg.notif.broadcast-channels" required="true" disabled="${dc.isOnlyView()}">
                    <c:forEach items="${dc.broadcastChannels}" var="broadcastChannel">
                        <aui:option value="${broadcastChannel.id}" selected="${fn:contains(dc.notification.broadcastChannels, broadcastChannel.id)}" >${broadcastChannel.label}</aui:option>
                    </c:forEach>
                </aui:select>
                <div id="broadcastChannels-error" style="display: none">
                    <liferay-ui:message key="this-field-is-required" />
                </div>

            </aui:fieldset>

            <c:if test="${not empty dc.notification && not dc.notification.new}">
                <%-- Groupe de champs : Statut d'envoi --%>
                <aui:fieldset collapsed="<%=true%>" collapsible="<%=true%>" label="eu.strasbourg.notif.statut">
                    <table class="broadcast-channels" border="1" cellspacing="0">
                        <tr>
                            <th><strong><liferay-ui:message key="eu.strasbourg.notif.channel" /></strong></th>
                            <th><strong><liferay-ui:message key="eu.strasbourg.notif.send-status" /></strong></th>
                        </tr>
                        <c:forEach items="${dc.broadcastChannels}" var="broadcastChannel">
                            <tr>
                                <td>${broadcastChannel.label}</td>
                                <td>${dc.getStatusByField(broadcastChannel.statusField)}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </aui:fieldset>
            </c:if>
		</aui:fieldset-group>

		<%-- Composant : Menu de gestion de l'entite --%>
		<aui:button-row>

			<aui:input type="hidden" name="workflowAction" value="" />
			<%-- Test : Verification des droits d'edition et de sauvegarde --%>
			<c:if test="${(dc.hasPermission('ADD_NOTIFICATION') and empty dc.notification or dc.hasPermission('EDIT_NOTIFICATION') and not empty dc.notification) and empty themeDisplay.scopeGroup.getStagingGroup()}">
                <c:if test="${not dc.isOnlyView()}">
                    <c:if test="${dc.workflowEnabled}">
                        <aui:button cssClass="btn-lg saveButton" type="submit" value="save" />
                    </c:if>
                    <c:if test="${not dc.workflowEnabled}">
                        <aui:button cssClass="btn-lg saveButton" type="submit" name="publish" value="save" />
                    </c:if>
			    </c:if>
			</c:if>

			<%-- Test : Verification des droits de supression --%>
            <%-- URL : definit le lien menant vers la suppression de l'entite --%>
            <liferay-portlet:actionURL name="deleteNotification" var="deleteNotificationURL">
                <portlet:param name="cmd" value="deleteNotification" />
                <portlet:param name="tab" value="notifications" />
                <portlet:param name="notificationId"
                    value="${not empty dc.notification ? dc.notification.notificationId : ''}" />
            </liferay-portlet:actionURL>
			<c:if test="${not empty dc.notification && dc.hasPermission('DELETE_NOTIFICATION') and empty themeDisplay.scopeGroup.getStagingGroup()}">
                <c:if test="${!dc.notification.new and dc.canUpdateOrDeleteNotification()}">
                    <aui:button cssClass="btn-lg" onClick='<%=renderResponse.getNamespace() + "deleteEntity();"%>' type="cancel" value="delete" />
                </c:if>
			</c:if>

			<%-- Composant : bouton de retour a la liste des entites --%>
			<aui:button cssClass="btn-lg" href="${param.returnURL}" type="cancel" />

		</aui:button-row>

	</aui:form>

</div>

<liferay-util:body-top>
    <aui:script>
        var isContribOnly = ${dc.isContribOnly()};
        var isOnlyView = ${dc.isOnlyView()};
    </aui:script>
</liferay-util:body-top>

<liferay-util:html-bottom>
    <!-- Include Choices CSS -->
	<link rel="stylesheet" href="/o/notifbo/css/vendors/choices.min.css">
    <!-- Include Choices JavaScript -->
    <script src="/o/notifbo/js/vendors/choices.min.js"
            type="text/javascript"></script>
	<script src="/o/notifbo/js/notif-bo-edit-notification.js" type="text/javascript"> </script>
</liferay-util:html-bottom>

<%-- Script : permet l'affichage des alertes de validation d'action --%>
<aui:script>
	function <portlet:namespace />deleteEntity() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-this-entry" />')) {
			window.location = '${deleteNotificationURL}';
		}
	}
</aui:script>