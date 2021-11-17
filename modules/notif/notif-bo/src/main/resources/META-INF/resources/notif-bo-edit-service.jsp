<%@ include file="/notif-bo-init.jsp"%>

<%@page import="eu.strasbourg.service.notif.model.ServiceNotif"%>

<%-- URL : definit le lien menant vers la sauvegarde de l'entite --%>
<liferay-portlet:actionURL name="saveService" varImpl="saveServiceURL">
	<portlet:param name="cmd" value="saveService" />
	<portlet:param name="tab" value="services" />
</liferay-portlet:actionURL>

<%-- Composant : Body --%>
<div class="container-fluid-1280 main-content-body ejob-bo">

	<%-- Composant : definit la liste des messages d'erreur  (voir methode "validate" dans le saveAction de l'entite) --%>
	<liferay-ui:error key="name-error" message="eu.strasbourg.service.name-error" />
	<liferay-ui:error key="organization-error" message="eu.strasbourg.service.organization-error" />
	<liferay-ui:error key="nb-nature-error" message="eu.strasbourg.service.nb-nature-error" />
	<liferay-ui:error key="natures-error" message="eu.strasbourg.service.natures-error" />
    <liferay-ui:error key="nb-indexes-error" message="eu.strasbourg.service.nb-indexes-error" />
	<div class="error"></div>

	<%-- Composant : formulaire de saisie de l'entite --%>
	<aui:form action="${saveServiceURL}" method="post" name="fm" onSubmit="submitForm(event);">

		<%-- Propriete : definit l'entite de reference pour le formulaire--%>
		<aui:model-context bean="${dc.service}" model="<%=ServiceNotif.class %>" />
		<aui:fieldset-group markupView="lexicon">

			<%-- Champ : (cache) PK de l'entite --%>
			<aui:input name="serviceId" type="hidden" />

			<%-- Groupe de champs : Generale --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="eu.strasbourg.notif.general">

                <%-- Champ : Nom --%>
                <aui:input name="name" id="name" label="eu.strasbourg.notif.name" required="true"/>

                <%-- Champ : Organisation   --%>
                <aui:select cssClass="toCustomSelect" id="organization" name="organization" label="eu.strasbourg.notif.organization" required="true">
                    <c:forEach items="${dc.organizations}" var="organization">
                        <aui:option value="${organization.organizationId}" selected="${dc.service.organisationId == organization.organizationId}" >${organization.name}</aui:option>
                    </c:forEach>
                </aui:select>
                <div id="organization-error" style="display: none">
                    <liferay-ui:message key="this-field-is-required" />
                </div>

                <%-- Champ : Label abonnement de Strasapp --%>
                <aui:input name="csmapSubscriptionLabel" id="csmapSubscriptionLabel" label="eu.strasbourg.notif.csmapSubscriptionLabel" required="false"/>

                <%-- Champ : Abonnement Strasapp --%>
                <aui:input name="csmapSubscriptionMandatory" id="csmapSubscriptionMandatory" label="eu.strasbourg.notif.csmapSubscriptionMandatory" required="false"/>

                <%-- Champ : Pictogramme   --%>
				<strasbourg-picker:image label="eu.strasbourg.notif.service.picto" name="pictoId"
					required="false" value="${dc.service.pictoId}" global="true" />

            </aui:fieldset>

			<%-- Groupe de champs : Nature du service --%>
			<aui:fieldset collapsed="<%=true%>" collapsible="<%=true%>" label="eu.strasbourg.notif.natures">

                <div id="nature-fields">
                    <c:if test="${dc.service == null || empty dc.service.natures}">
                        <div class="lfr-form-row" id="nature0">
                            <div class="row-fields">
                                <liferay-util:include page="/includes/nature-row.jsp" servletContext="<%=application %>">
                                    <liferay-util:param name="index" value="0" />
                                </liferay-util:include>
                            </div>
                        </div>
                    </c:if>

                    <c:if test="${dc.service != null && not empty dc.service.natures}">
                        <c:forEach items="${dc.service.natures}" var="nature" varStatus="status">
                            <c:set var="nature" value="${nature}" scope="request"/>
                            <div class="lfr-form-row" id="nature{status.index}">
                                <div class="row-fields">
                                    <liferay-util:include page="/includes/nature-row.jsp" servletContext="<%=application %>">
                                        <liferay-util:param name="index" value="${status.index}" />
                                    </liferay-util:include>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                    <aui:input type="hidden" name="serviceNaturesIndexes" value="${dc.getDefaultNaturesIndexes()}" />
                 </div>

            </aui:fieldset>

			<%-- Groupe de champs : Messages prédéfinies --%>
			<aui:fieldset collapsed="<%=true%>" collapsible="<%=true%>" label="eu.strasbourg.notif.messages">

                <div id="message-fields">
                    <c:if test="${dc.service == null || empty dc.service.messages}">
                        <div class="lfr-form-row" id="message0">
                            <div class="row-fields">
                                <liferay-util:include page="/includes/message-row.jsp" servletContext="<%=application %>">
                                    <liferay-util:param name="index" value="0" />
                                </liferay-util:include>
                            </div>
                        </div>
                    </c:if>

                    <c:if test="${dc.service != null && not empty dc.service.messages}">
                        <c:forEach items="${dc.service.messages}" var="message" varStatus="status">
                            <c:set var="message" value="${message}" scope="request"/>
                            <div class="lfr-form-row" id="message{status.index}">
                                <div class="row-fields">
                                    <liferay-util:include page="/includes/message-row.jsp" servletContext="<%=application %>">
                                        <liferay-util:param name="index" value="${status.index}" />
                                    </liferay-util:include>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                    <aui:input type="hidden" name="serviceMessagesIndexes" value="${dc.getDefaultMessagesIndexes()}" />
                 </div>

            </aui:fieldset>
		</aui:fieldset-group>

		<%-- Composant : Menu de gestion de l'entite --%>
		<aui:button-row>

			<%-- Test : Verification des droits d'edition et de sauvegarde --%>
			<c:if test="${(dc.hasPermission('ADD_SERVICE') and empty dc.service or dc.hasPermission('EDIT_SERVICE') and not empty dc.service) and empty themeDisplay.scopeGroup.getStagingGroup()}">
                <aui:button cssClass="btn-lg saveButton" type="submit" value="save" />
			</c:if>


            <%-- URL : definit le lien menant vers la suppression de l'entite --%>
            <liferay-portlet:actionURL name="deleteService" var="deleteServiceURL">
                <portlet:param name="cmd" value="deleteService" />
                <portlet:param name="tab" value="services" />
                <portlet:param name="serviceId"
                    value="${not empty dc.service ? dc.service.serviceId : ''}" />
            </liferay-portlet:actionURL>
			<%-- Test : Verification des droits de supression --%>
			<c:if test="${not empty dc.service && dc.hasPermission('DELETE_SERVICE') and empty themeDisplay.scopeGroup.getStagingGroup()}">
                <aui:button cssClass="btn-lg" onClick='<%=renderResponse.getNamespace() + "deleteEntity();"%>' type="cancel" value="delete" />
			</c:if>

			<%-- Composant : bouton de retour a la liste des entites --%>
			<aui:button cssClass="btn-lg" href="${param.returnURL}" type="cancel" />

		</aui:button-row>

	</aui:form>

</div>

<liferay-portlet:actionURL name="getServiceNatureRow" varImpl="serviceNatureRowURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<liferay-portlet:param name="mvcPath" value="/includes/nature-row.jsp" />
</liferay-portlet:actionURL>

<liferay-portlet:actionURL name="getServiceMessageRow" varImpl="serviceMessageRowURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<liferay-portlet:param name="mvcPath" value="/includes/message-row.jsp" />
</liferay-portlet:actionURL>

<liferay-util:html-top>
	<script>
	    var getServiceNatureRowJSPURL = '${serviceNatureRowURL}';
	    var getServiceMessageRowJSPURL = '${serviceMessageRowURL}';
	</script>
</liferay-util:html-top>

<liferay-util:html-bottom>
	<script src="/o/notifbo/js/notif-bo-edit-service.js" type="text/javascript"></script>
</liferay-util:html-bottom>

<%-- Script : permet l'affichage des alertes de validation d'action --%>
<aui:script>
	function <portlet:namespace />deleteEntity() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-this-entry" />')) {
			window.location = '${deleteServiceURL}';
		}
	}
</aui:script>