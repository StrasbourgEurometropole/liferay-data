<%@ include file="/project-bo-init.jsp"%>
<%@page import="eu.strasbourg.service.project.model.Petition"%>

<%-- URL : definit le lien menant vers la page de listage de l'entite --%>
<liferay-portlet:renderURL varImpl="petitionsURL">
	<portlet:param name="tab" value="petitions" />
</liferay-portlet:renderURL>

<%-- URL : definit le lien menant vers la suppression de l'entite --%>
<liferay-portlet:actionURL name="deletePetition" var="deletePetitionURL">
	<portlet:param name="cmd" value="deletePetition" />
	<portlet:param name="tab" value="petitions" />
	<portlet:param name="petitionId" value="${not empty dc.petition ? dc.petition.petitionId : ''}" />
</liferay-portlet:actionURL>

<%-- URL : definit le lien menant vers la sauvegarde de l'entite --%>
<liferay-portlet:actionURL name="savePetition" varImpl="savePetitionURL">
	<portlet:param name="cmd" value="savePetition" />
	<portlet:param name="tab" value="petitions" />
</liferay-portlet:actionURL>

<%-- Composant : Body --%>
<div class="container-fluid-1280 main-content-body">

	<%-- Composant : definit la liste des messages d'erreur 
	(voir methode "validate" dans le saveAction de l'entite) --%>
	<liferay-ui:error key="title-error" message="title-error" />
	<liferay-ui:error key="description-error" message="description-error" />
	<liferay-ui:error key="image-error" message="image-error" />
	<liferay-ui:error key="place-error" message="place-error" />

	<%-- Composant : formulaire de saisie de l'entite --%>
	<aui:form action="${savePetitionURL}" method="post" name="fm" onSubmit="submitForm(event);">

		<%-- Propriete : definit l'entite de reference pour le formulaire--%>
		<aui:model-context bean="${dc.petition}" model="<%=Petition.class %>" />
		<aui:fieldset-group markupView="lexicon">

			<%-- Champ : (cache) PK de l'entite --%>
			<aui:input name="petitionId" type="hidden" />

			<%-- Groupe de champs : Generalites --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="general">

				<%-- Champ : Titre --%>
				<aui:input name="title" required="true" />
				
				<%-- Champ : Auteur --%>
				<aui:input name="userName" required="true" />

			</aui:fieldset>

            <%-- Groupe de champs : video/image --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="label-video">

				<%-- Champ : Choix du media --%>
				<aui:input name="mediaChoice" label="media-preference" type="toggle-switch"
					value="${not empty dc.petition ? dc.petition.mediaChoice : true}" />

				<%-- Champ : URL de la video --%>
				<aui:input name="videoUrl" required="false" />

			    <%-- Selecteur : Image interne ou externe ? --%>
                <label><input type="radio" value="internalImage" name="imageType"
                    <c:if test="${(not empty dc.petition.imageId and dc.petition.imageId gt 0) or empty dc.petition.externalImageURL }">checked</c:if>> Image interne</label><br>
                <label><input type="radio" value="externalImage" name="imageType"
                    <c:if test="${(empty dc.petition.imageId or dc.petition.imageId eq 0) and not empty dc.petition.externalImageURL }">checked</c:if>> Image externe</label><br><br>

                <%-- Champ : Image interne --%>
                <div class="internalImage" <c:if test="${(empty dc.petition.imageId or dc.petition.imageId eq 0) and not empty dc.petition.externalImageURL }">style="display: none;"</c:if>>
                    <strasbourg-picker:image label="image" name="imageId" required="true" value="${dc.petition.imageId}" global="true"/>
                </div>

                <%-- Groupe de champs : Image externe --%>
                <div class="externalImage" <c:if test="${(not empty dc.petition.imageId and dc.petition.imageId gt 0) or empty dc.petition.externalImageURL }">style="display: none;"</c:if>>

                    <%-- Champ : URL de l'image externe --%>
                    <aui:input name="externalImageURL" helpMessage="help-image-size" >
                        <aui:validator name="required" errorMessage="this-field-is-required" />
                    </aui:input>

                    <%-- Champ : Copyright de l'image externe --%>
                    <aui:input name="externalImageCopyright" >
                        <aui:validator name="required" errorMessage="this-field-is-required" />
                    </aui:input>

                </div>

			</aui:fieldset>

            <%-- Groupe de champs : Description --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="label-describe">

				<%-- Champ : Corps de la description --%>
				<aui:input name="description" required="false" />

			</aui:fieldset>

            <%-- Groupe de champs : Lieux --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="label-place">

				<%-- Champ : Lieux --%>
				<div id="place-fields">
					<c:if test="${empty dc.petition.getPlacitPlaces()}">
						<div class="lfr-form-row lfr-form-row-inline main-content-card row-place">
							<h3><liferay-ui:message key="place" /></h3>
							<div class="row-fields">
								<liferay-util:include page="/includes/placit-place-row.jsp" servletContext="<%=application %>">
									<liferay-util:param name="index" value="0" />
								</liferay-util:include>
							</div>
						</div>
					</c:if>
					<c:forEach items="${dc.petition.getPlacitPlaces()}" var="placitPlace" varStatus="status">
						<c:set var="placitPlace" value="${placitPlace}" scope="request"/>
						<div class="lfr-form-row lfr-form-row-inline main-content-card row-place">
							<h3><liferay-ui:message key="place" /></h3>
							<div class="row-fields">
								<liferay-util:include page="/includes/placit-place-row.jsp" servletContext="<%=application %>">
									<liferay-util:param name="index" value="${status.index}" />
								</liferay-util:include>
							</div>
						</div>
					</c:forEach>

					<aui:input type="hidden" name="placeIndexes" value="${dc.defaultPlaceIndexes}" />
                </div>
			</aui:fieldset>

            <%-- Groupe de champs : Documents a telecharger --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="label-document">
			</aui:fieldset>

            <%-- Groupe de champs : vocabulaire --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="label-vocabulary">
			</aui:fieldset>

            <%-- Groupe de champs : Autre --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="label-other">
			</aui:fieldset>

		</aui:fieldset-group>

		<%-- Composant : Menu de gestion de l'entite --%>
		<aui:button-row>
			
			<aui:input type="hidden" name="workflowAction" value="" />
			
			<%-- Test : Verification des droits d'edition et de sauvegarde --%>
			<c:if test="${(dc.hasPermission('ADD_PETITION') and empty dc.petition or dc.hasPermission('EDIT_PETITION') and not empty dc.petition) and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<c:if test="${dc.workflowEnabled}">
					<aui:button cssClass="btn-lg" type="submit" value="save" />
				</c:if>
				<c:if test="${not dc.workflowEnabled}">
					<aui:button cssClass="btn-lg" type="submit" name="publish" value="eu.publish" />
					<aui:button cssClass="btn-lg btn-default" type="submit" name="save-as-draft" value="save-as-draft" />
				</c:if>
			</c:if>
			
			<%-- Test : Verification des droits de supression --%>
			<c:if test="${not empty dc.petition && dc.hasPermission('DELETE_PETITION') and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<aui:button cssClass="btn-lg" onClick='<%=renderResponse.getNamespace() + "deleteEntity();"%>' type="cancel" value="delete" />
			</c:if>
			
			<%-- Composant : bouton de retour a la liste des entites --%>
			<aui:button cssClass="btn-lg" href="${param.returnURL}" type="cancel" />
			
		</aui:button-row>

	</aui:form>
	
</div>

<liferay-portlet:actionURL name="getPetitionPlaceRow" var="placeRowURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<liferay-portlet:param name="mvcPath" value="/includes/placit-place-row.jsp" />
</liferay-portlet:actionURL>

<liferay-util:html-top>
	<script>
		var editPetition = true;
		var getPetitionPlaceRowURL = '${placeRowURL}';
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
	<script src="/o/projectbo/js/project-bo-edit-petition.js" type="text/javascript"></script>
</liferay-util:html-bottom>

<%-- Script : permet l'affichage des alertes de validation d'action --%>
<aui:script>
	function <portlet:namespace />deleteEntity() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-this-entry" />')) {
			window.location = '${deletePetitionURL}';
		}
	}
</aui:script>