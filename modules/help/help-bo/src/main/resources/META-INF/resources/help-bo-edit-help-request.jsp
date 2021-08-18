<%@ include file="/help-bo-init.jsp"%>
<%@page import="eu.strasbourg.service.help.model.HelpRequest"%>

<%-- URL : definit le lien menant vers la sauvegarde de l'entite --%>
<liferay-portlet:actionURL name="saveHelpRequest" varImpl="saveHelpRequestURL">
	<portlet:param name="cmd" value="saveHelpRequest" />
	<portlet:param name="tab" value="helpSeekers" />
	<portlet:param name="redirectURL" value="${returnURL}" />
</liferay-portlet:actionURL>

<%-- Composant : Body --%>
<div class="container-fluid-1280 main-content-body">

	<%-- Composant : definit la liste des messages d'erreur
	(voir methode "validate" dans le saveAction de l'entite) --%>

	<%-- Composant : formulaire de saisie de l'entite --%>
	<aui:form action="${saveHelpRequestURL}" method="post" name="fm" onSubmit="submitForm(event);">

		<%-- Propriete : definit l'entite de reference pour le formulaire--%>
		<aui:model-context bean="${dc.helpRequest}" model="<%=HelpRequest.class %>" />
		<aui:fieldset-group markupView="lexicon">

			<%-- Champ : (cache) PK de l'entite --%>
			<aui:input name="helpRequestId" type="hidden" />

			<%-- Champ : (cache) enriegistrement conform ou alerte --%>
			<aui:input name="newStatus" type="hidden" value="" />

			<%-- Groupe de champs : Generalites --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="general">

			    <%-- Champ : Date de soumission --%>
                <aui:input name="createDate" disabled="true" />

                <%-- Champ : Message du demandeur --%>
                <aui:input name="message" disabled="true" />

			</aui:fieldset>

			<%-- Groupe de champs : Citoyen --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="seeker-informations">

                <%-- Champ : Nom --%>
                <aui:input name="authorName" type="text" label="author" disabled="true" value="${not empty dc.helpRequest ? dc.helpRequest.getAuthorLabel() : '' }" />

                <%-- Champ : Adresse mail --%>
                <aui:input name="authorEmail" type="text" label="email" disabled="true" value="${not empty dc.helpRequest ? dc.helpRequest.getAuthorEmail() : '' }" />

                <%-- Champ : Téléphone --%>
                <aui:input name="phoneNumber" required="true" disabled="true" />

                <%-- Champ : Justificatif du demandeur --%>
                <strasbourg-picker:image label="Photo du justificatif" name="seeker-proof" required="false" value="${dc.helpRequest.studentCardImageId}" global="false" />

			</aui:fieldset>

			<%-- Groupe de champs : Consentements --%>
            <aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="agreements">

                <%-- Champ : Consentement 1 --%>
                <aui:input name="agreementSigned1" disabled="true" required="false"/>

                <%-- Champ : Consentement 2 --%>
                <aui:input name="agreementSigned2" disabled="true" required="false"/>

                <%-- Champ : Consentement 3 --%>
                <aui:input name="agreementSigned3" disabled="true" required="false"/>

            </aui:fieldset>

			<%-- Groupe de champs : Autres --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="moderation">

				<%-- Champ : Dernière modification faite par --%>
				<aui:input name="statusByUserName" label="last-moderation-user-name" disabled="true" required="false" />

				<%-- Champ : Dernière modification faite le --%>
				<aui:input name="statusDate" label="last-moderation-date" disabled="true" required="false" helpMessage="help-last-moderation-date" />

				<%-- Champ : Commentaire --%>
				<aui:input name="comment" required="false" />

			</aui:fieldset>

		</aui:fieldset-group>

		<%-- Composant : Menu de gestion de l'entite --%>
		<aui:button-row>

			<aui:input type="hidden" name="workflowAction" value="" />

			<%-- Test : Verification des droits d'edition et de sauvegarde --%>
			<c:if test="${(dc.hasPermission('ADD_HELP_REQUEST') and empty dc.helpRequest or dc.hasPermission('EDIT_HELP_REQUEST') and not empty dc.helpRequest) and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<c:if test="${dc.workflowEnabled}">
					<aui:button cssClass="btn-lg" type="submit" value="save" />
				</c:if>
				<c:if test="${not dc.workflowEnabled}">
					<aui:button cssClass="btn-lg" type="submit" name="publish" value="save" />
					<aui:button cssClass="btn-lg btn-valid" type="submit" name="save-is-valid" value="save-is-valid" />
					<aui:button cssClass="btn-lg btn-alert" type="submit" name="save-is-alert" value="save-is-alert" />
					<aui:button cssClass="btn-lg btn-not-valid" type="submit" name="save-is-not-valid" value="save-is-not-valid" />
				</c:if>
			</c:if>

			<%-- Composant : bouton de retour a la liste des entites --%>
			<aui:button cssClass="btn-lg" href="${param.returnURL}" type="cancel" />

		</aui:button-row>

	</aui:form>

</div>

<liferay-util:html-top>
	<script>
		var editHelp = true;
	</script>
</liferay-util:html-top>

<liferay-util:html-bottom>
	<script src="/o/helpbo/js/help-bo-edit-help-request.js" type="text/javascript"></script>
</liferay-util:html-bottom>

<aui:script>
	$("#<portlet:namespace />save-is-valid").click(function(event){
	$("#<portlet:namespace />newStatus").val('Conforme');
	});

	$("#<portlet:namespace />save-is-alert").click(function(event){
	$("#<portlet:namespace />newStatus").val('Alerte');
	});

	$("#<portlet:namespace />save-is-not-valid").click(function(event){
	$("#<portlet:namespace />newStatus").val('Non-conforme');
	});
</aui:script>