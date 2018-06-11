<%@ include file="/project-bo-init.jsp"%>
<%@page import="eu.strasbourg.service.project.model.Project"%>

<%-- URL : definit le lien menant vers la page de listage de l'entite --%>
<liferay-portlet:renderURL varImpl="projectsURL">
	<portlet:param name="tab" value="projects" />
</liferay-portlet:renderURL>

<%-- URL : definit le lien menant vers la suppression de l'entite --%>
<liferay-portlet:actionURL name="deleteProject" var="deleteProjectURL">
	<portlet:param name="cmd" value="deleteProject" />
	<portlet:param name="tab" value="projects" />
	<portlet:param name="projectId" value="${not empty dc.project ? dc.project.projectId : ''}" />
</liferay-portlet:actionURL>

<%-- URL : definit le lien menant vers la sauvegarde de l'entite --%>
<liferay-portlet:actionURL name="saveProject" varImpl="saveProjectURL">
	<portlet:param name="cmd" value="saveProject" />
</liferay-portlet:actionURL>

<%-- Composant : Body --%>
<div class="container-fluid-1280 main-content-body">

	<%-- Composant : definit la liste des messages d'erreur 
	(voir mÃÂ©thode "validate" dans le saveAction de l'entitÃÂ©) --%>
	<liferay-ui:error key="title-error" message="title-error" />
	<liferay-ui:error key="description-error" message="description-error" />
	<liferay-ui:error key="image-error" message="image-error" />

	<%-- Composant : formulaire de saisie de l'entite --%>
	<aui:form action="${saveProjectURL}" method="post" name="fm">

		<%-- Propriete : definit l'entite de reference pour le formulaire--%>
		<aui:model-context bean="${dc.project}" model="<%=Project.class %>" />
		<aui:fieldset-group markupView="lexicon">
		
			<%-- Champ : (cache) PK de l'entite --%>
			<aui:input name="projectId" type="hidden" />

			<%-- Groupe de champs : Generalites --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="general">

				<%-- Champ : Titre --%>
				<aui:input name="title" required="true" />
				
				<%-- Champ : Description --%>
				<aui:input name="description" required="true" />
				
				<%-- Selecteur : Image interne ou externe ? --%>	
				<label><input type="radio" value="internalImage" name="imageType" 
					<c:if test="${(not empty dc.project.imageId and dc.project.imageId gt 0) or empty dc.project.externalImageURL }">checked</c:if>> Image interne</label><br>
				<label><input type="radio" value="externalImage" name="imageType"
					<c:if test="${(empty dc.project.imageId or dc.project.imageId eq 0) and not empty dc.project.externalImageURL }">checked</c:if>> Image externe</label><br><br>
				
				<%-- Champ : Image interne --%>
				<div class="internalImage" <c:if test="${(empty dc.project.imageId or dc.project.imageId eq 0) and not empty dc.project.externalImageURL }">style="display: none;"</c:if>>
					<strasbourg-picker:image label="image" name="imageId" required="true" value="${dc.project.imageId}" global="true"/>
				</div>
				
				<%-- Groupe de champs : Image externe --%>
				<div class="externalImage" <c:if test="${(not empty dc.project.imageId and dc.project.imageId gt 0) or empty dc.project.externalImageURL }">style="display: none;"</c:if>>
					
					<%-- Champ : URL de l'image externe --%>
					<aui:input name="externalImageURL" helpMessage="help-image-size" >
						<aui:validator name="required" errorMessage="this-field-is-required" />
					</aui:input>
					
					<%-- Champ : Copyright de l'image externe --%>
					<aui:input name="externalImageCopyright" >
						<aui:validator name="required" errorMessage="this-field-is-required" />
					</aui:input>
					
				</div>
				
				<%-- Champ : URL detail du projet --%>
				<aui:input name="detailURL" required="false" />
				
			</aui:fieldset>
			
			<%-- Groupe de champs : En bref --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="in-short">
				
				<%-- Champ : Budget --%>
				<aui:input name="budget" required="false" />
				
				<%-- Champ : Label --%>
				<aui:input name="label" required="false" />
				
				<%-- Champ : Duree --%>
				<aui:input name="duration" required="false" />
				
				<%-- Champ : Liste des partenaires --%>
				<aui:input name="partners" required="false" />
				
			</aui:fieldset>
			
			<%-- Groupe de champs : Contact --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="contact">
				
				<%-- Champ : Nom --%>
				<aui:input name="contactName" required="false" />
				
				<%-- Champ : Ligne 1 --%>
				<aui:input name="contactLine1" required="false" />
				
				<%-- Champ : Ligne 2 --%>
				<aui:input name="contactLine2" required="false" />
				
				<%-- Champ : Numero de telephone --%>
				<aui:input name="contactPhoneNumber" required="false" />
				
			</aui:fieldset>
			
			<%-- Groupe de champs : Categorisations --%>
			<aui:fieldset collapsed="<%=true%>" collapsible="<%=true%>" label="categorization">
				
				<%-- Champ : Selection des categories (gere par le portail dans l'onglet "Categories" du BO) --%>
				<aui:input name="categories" type="assetCategories" wrapperCssClass="categories-selectors" />
				
				<!-- Hack pour ajouter une validation sur les vocabulaires obligatoires -->
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
				
				<%-- Champ : Selection des etiquettes (gere par le portail dans l'onglet "Etiquettes" du BO) --%>
				<aui:input name="tags" type="assetTags" />

			</aui:fieldset>
			
			<%-- Groupe de champs : Timeline --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="timeline">
			
				<div class="timeline-label"><label><liferay-ui:message key="enter-a-timeline" /></label></div>
				
				<%-- Composant : Definit l'utilisation d'un selecteur multiple --%>
				<div id="date-fields">
				
					<c:if test="${empty dc.project.projectTimelines}">
						<div class="lfr-form-row lfr-form-row-inline">
							<div class="row-fields">
								<liferay-util:include page="/includes/timeline-row.jsp" servletContext="<%=application %>">
									<liferay-util:param name="index" value="0" />
								</liferay-util:include>
							</div>
						</div>
					</c:if>
					
					<c:forEach items="${dc.project.projectTimelines}" var="projectTimeline" varStatus="status">
						<div class="lfr-form-row lfr-form-row-inline">
							<div class="row-fields">
								<fmt:formatDate value="${projectTimeline.date}" pattern="yyyy-MM-dd" type="date" var="formattedDate"/>
								<liferay-util:include page="/includes/timeline-row.jsp" servletContext="<%=application %>">
									<liferay-util:param name="index" value="${status.count}" />
									<liferay-util:param name="startDay" value="${projectTimeline.startDay}" />
									<liferay-util:param name="date" value="${formattedDate}" />
									<liferay-util:param name="title" value="${projectTimeline.title}" />
									<liferay-util:param name="link" value="${projectTimeline.link}" />
								</liferay-util:include>
							</div>
						</div>
					</c:forEach>
					
					<%-- Variable : Definit les variables de gestion et de retour du selecteur 
					(voir "autofields" dans le .js de l'edit de l'entitÃÂ©)  --%>
					<c:if test="${empty dc.project.projectTimelines}">
							<aui:input type="hidden" name="projectTimelineIndexes" value="0" />
						</c:if>
						<c:if test="${not empty dc.project.projectTimelines}">
							<aui:input type="hidden" name="projectTimelineIndexes" value="0" />
					</c:if>		
					
				</div>
			
			</aui:fieldset>

		</aui:fieldset-group>

		<%-- Composant : Menu de gestion de l'entite --%>
		<aui:button-row>
			
			<aui:input type="hidden" name="workflowAction" value="" />
			
			<%-- Test : Verification des droits d'edition et de sauvegarde --%>
			<c:if test="${(dc.hasPermission('ADD_PROJECT') and empty dc.project or dc.hasPermission('EDIT_PROJECT') and not empty dc.project) and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<c:if test="${dc.workflowEnabled}">
					<aui:button cssClass="btn-lg" type="submit" value="save" />
				</c:if>
				<c:if test="${not dc.workflowEnabled}">
					<aui:button cssClass="btn-lg" type="submit" name="publish" value="eu.publish" />
					<aui:button cssClass="btn-lg btn-default" type="submit" name="save-as-draft" value="save-as-draft" />
				</c:if>
			</c:if>
			
			<%-- Test : Verification des droits de supression --%>
			<c:if test="${not empty dc.project && dc.hasPermission('DELETE_PROJECT') and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<aui:button cssClass="btn-lg" onClick='<%=renderResponse.getNamespace() + "deleteEntity();"%>' type="cancel" value="delete" />
			</c:if>
			
			<%-- Composant : bouton de retour a la liste des entites --%>
			<aui:button cssClass="btn-lg" href="${param.returnURL}" type="cancel" />
			
		</aui:button-row>

	</aui:form>
	
	<%-- Variable : definit la phase de l'entite (utile pour l'action Jquery) --%>
	<liferay-util:html-top>
		<script>
			var editProject = true;
		</script>
	</liferay-util:html-top>
	
</div>

<liferay-util:html-top>
	<script>
		var getProjectTimelineRowJSPURL = '${timelineRowURL}';
	</script>
</liferay-util:html-top>

<liferay-util:html-bottom>
	<script src="/o/projectbo/js/project-bo-edit-project.js" type="text/javascript"></script>
</liferay-util:html-bottom>

<%-- Script : permet l'affichage des alertes de validation d'action --%>
<aui:script>
	function <portlet:namespace />deleteEntity() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-this-entry" />')) {
			window.location = '${deleteProjectURL}';
		}
	}
</aui:script>