<%@ include file="/ejob-bo-init.jsp"%>

<%@page import="eu.strasbourg.service.ejob.model.Offer"%>

<%-- URL : definit le lien menant vers la page de listage de l'entite --%>
<liferay-portlet:renderURL varImpl="offersURL">
	<portlet:param name="tab" value="offers" />
</liferay-portlet:renderURL>

<%-- URL : definit le lien menant vers la suppression de l'entite --%>
<liferay-portlet:actionURL name="deleteOffer" var="deleteOfferURL">
	<portlet:param name="cmd" value="deleteOffer" />
	<portlet:param name="tab" value="offers" />
	<portlet:param name="offerId"
	    value="${not empty dc.offer ? dc.offer.offerId : ''}" />
</liferay-portlet:actionURL>

<%-- URL : definit le lien menant vers la sauvegarde de l'entite --%>
<liferay-portlet:actionURL name="saveOffer" varImpl="saveOfferURL">
	<portlet:param name="cmd" value="saveOffer" />
	<portlet:param name="tab" value="offers" />
</liferay-portlet:actionURL>

<%-- Composant : Body --%>
<div class="container-fluid-1280 main-content-body council-bo">

	<%-- Composant : definit la liste des messages d'erreur  (voir methode "validate" dans le saveAction de l'entite) --%>
	<liferay-ui:error key="title-error" message="title-error" />
	<liferay-ui:error key="title-already-used-error" message="title-already-used-error" />
	<liferay-ui:error key="date-error" message="date-error" />
	<liferay-ui:error key="date-already-used-error" message="date-already-used-error" />

	<%-- Composant : definit la liste des messages d'erreur  (voir methode "doProcessAction" dans le deleteAction de l'entite) --%>
	<liferay-ui:error key="council-has-delib-error" message="council-has-delib-error" />

	<%-- Composant : formulaire de saisie de l'entite --%>
	<aui:form action="${saveOfferURL}" method="post" name="fm" onSubmit="submitForm(event);">

		<%-- Propriete : definit l'entite de reference pour le formulaire--%>
		<aui:model-context bean="${dc.offer}" model="<%=Offer.class %>" />
		<aui:fieldset-group markupView="lexicon">

			<%-- Champ : (cache) PK de l'entite --%>
			<aui:input name="offer-id" type="hidden" />

			<%-- Groupe de champs : Generalites --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="general">

                <%-- Champ : Numero de publication --%>
                    <aui:input name="publicationId" required="false" />

                <%-- Champ : Numero de poste --%>
                    <aui:input name="postNumber" required="false" />

                <%-- Champ : Nom de la personne remplacée ou création de poste --%>
                     <aui:input name="jobCreationDescription" required="false" />

                <%-- Champ : Date de départ  --%>
                     <aui:input name="startDate" required="false" />

                <%-- Champ : Motif  --%>
                     <aui:input name="motif" required="false" />

                <%-- Champ : Si contrat permanent  --%>
                     <aui:input name="permanentDescription" required="false" />

                <%-- Champ : Durée du contrat  --%>
                     <aui:input name="duration" required="false" />

                <%-- Champ : Intitulé du poste  --%>
                     <aui:input name="post" required="true" />

                <%-- Champ : Temps complet ou non   --%>
                     <aui:input name="isFullTime" required="false"/>

                <%-- Champ : Si temps complet   --%>
                     <aui:input name="fullTimeDescription" required="false" disabled="${isFullTime}" />

                <%-- Champ : Introduction --%>
                     <aui:input name="introduction" required="true" />

                <%-- Champ : Activités --%>
                     <aui:input name="activities" required="true" />

                <%-- Champ : Profil --%>
                     <aui:input name="profil" required="true" />

                <%-- Champ : Condition d'exercice --%>
                     <aui:input name="conditions" required="true" />

                <%-- Champ : Avantages liés au poste --%>
                     <aui:input name="avantages" required="true" />

                <%-- Champ : Date limite de candidatures --%>
                     <aui:input name="limitDate" required="true" />

                <%-- Champ : Nom du RE + contact téléphonique  --%>
                     <aui:input name="contact" required="false" />

                <%-- Champ : Nom du RRH + contact téléphonique --%>
                     <aui:input name="emails" required="true" />

                <%-- Champ : Champ partage Linkedin  --%>
                     <aui:input name="shareLinkedin" required="true" />

                <%-- Champ : Export vers TOTEM --%>
                     <aui:input name="exportTotem" required="false" />

                <%-- Champ : Date de publication d’une offre ( programmation) --%>
                     <aui:input name="publicationDate" required="false" />

			</aui:fieldset>

			<%-- Groupe de champs : Procuration --%>
            <aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="categorisation">
                <%-- Champ : Type de recrutement  --%>
                    <%-- Liste déroulante (Permanent – Non permanent- Stage – Vacataire – Saisonnier  + Apprentissage) attention si permanent voir champ en dessous affichage conditionnel --%>

                <%-- Champ : Direction  --%>
                     <%-- Liste déroulante  gérée par des catégories créees en BO --%>

                <%-- Champ : Service  --%>
                     <%-- Liste déroulante  gérée par des catégories créees en BO --%>

                <%-- Champ : Catégories   --%>
                     <%-- (case a cocher ) multiple possible. Max 2 (Liste : Catégorie A, Catégorie B, Catégorie C) --%>

                <%-- Champ : Filières   --%>
                     <%-- ( cache à cocher plusieurs possible) Voir liste fournie--%>

                <%-- Champ : Choix du grade   --%>
                     <%-- Comment ca fonctionne pour lui ? Moyen de récupérer les possibilités depuis un site ?
                          Comme une requete avec en parametres les choix de l'utilisateur ? --%>

                <%-- Champ : Niveau d'étude   --%>
                     <%-- Liste à choix à définir --%>

                <%-- Champ : Famille de métiers --%>
                     <%-- Liste déroulante  gérée par des catégories créees en BO --%>

			</aui:fieldset>



		</aui:fieldset-group>

		<%-- Composant : Menu de gestion de l'entite --%>
		<aui:button-row>

			<aui:input type="hidden" name="workflowAction" value="" />

			<%-- Test : Verification des droits d'edition et de sauvegarde --%>
			<c:if test="${(dc.hasPermission('ADD_OFFER') and empty dc.offer or dc.hasPermission('EDIT_OFFER') and not empty dc.offer) and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<c:if test="${dc.workflowEnabled}">
					<aui:button cssClass="btn-lg" type="submit" value="save" />
				</c:if>
                <c:if test="${not dc.workflowEnabled}">
                    <aui:button cssClass="btn-lg" type="submit" name="publish" value="save" />
                </c:if>
			</c:if>

			<%-- Test : Verification des droits de supression --%>
			<c:if test="${not empty dc.offer && dc.hasPermission('DELETE_OFFER') and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<aui:button cssClass="btn-lg" onClick='<%=renderResponse.getNamespace() + "deleteEntity();"%>' type="cancel" value="delete" />
			</c:if>

			<%-- Composant : bouton de retour a la liste des entites --%>
			<aui:button cssClass="btn-lg" href="${param.returnURL}" type="cancel" />

		</aui:button-row>

	</aui:form>

</div>

<liferay-util:html-top>
	<script>
		var editOffer = true;
		var currentGroupId = ${dc.groupId}
	</script>
</liferay-util:html-top>

<liferay-util:html-bottom>
	<script src="/o/councilbo/js/council-bo-edit-council-session.js" type="text/javascript"></script>
</liferay-util:html-bottom>

<%-- Script : permet l'affichage des alertes de validation d'action --%>
<aui:script>
	function <portlet:namespace />deleteEntity() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-this-entry" />')) {
			window.location = '${deleteOfferURL}';
		}
	}
</aui:script>