<%@ include file="/oidc-bo-init.jsp"%>
<%@page import="eu.strasbourg.service.oidc.model.PublikUser"%>

<%-- URL : definit le lien menant vers la page de listage de l'entite --%>
<liferay-portlet:renderURL varImpl="publikUsersURL">
	<portlet:param name="tab" value="publikUsers" />
</liferay-portlet:renderURL>

<%-- URL : definit le lien menant vers la sauvegarde de l'entite --%>
<liferay-portlet:actionURL name="savePublikUser" varImpl="savePublikUserURL">
	<portlet:param name="cmd" value="savePublikUser" />
	<portlet:param name="tab" value="publikUsers" />
</liferay-portlet:actionURL>

<%-- Composant : Body --%>
<div class="container-fluid-1280 main-content-body">

	<%-- Composant : formulaire de saisie de l'entite --%>
	<aui:form action="${savePublikUserURL}" method="post" name="fm">

		<%-- Propriete : definit l'entite de reference pour le formulaire--%>
		<aui:model-context bean="${dc.publikUser}" model="<%=PublikUser.class %>" />
		<aui:fieldset-group markupView="lexicon">
		
			<%-- Champ : (cache) PK de l'entite --%>
			<aui:input name="publikUserLiferayId" type="hidden" />

			<%-- Groupe de champs : Generalites --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="general">

				<%-- Champ : Nom --%>
				<aui:input name="lastName" required="true" disabled="true" />
				
				<%-- Champ : Prenom --%>
				<aui:input name="firstName" required="true" disabled="true" />
				
				<%-- Champ : Email --%>
				<aui:input name="email" required="true" disabled="true" />
				
			</aui:fieldset>
			
			<%-- Groupe de champs : Plateforme Citoyenne --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="placit">
				
				<%-- Champ : Date de signature du pacte --%>
				<c:choose>
					<c:when test = "${dc.publikUser.pactSignature != null}">
						<aui:input name="pactSignature" nullable="true" placeholder="dd/mm/yyyy" required="false"  disabled="true"/>
					</c:when>
					<c:otherwise>
						<liferay-ui:message key="pact-unsigned" />
					</c:otherwise>
				</c:choose>
			</aui:fieldset>
			
			<%-- Groupe de champs : Contact --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="banishment">
				
				<%-- Champ : Date de fin bannissement --%>
				<aui:input name="banishDate" required="false" helpMessage="help-banish-date"/>

				<%-- Champ : Description / Motifs du bannissement --%>
				<aui:input name="banishDescription" required="false" />
				
			</aui:fieldset>
			
		</aui:fieldset-group>
		
		<%-- Composant : Menu de gestion de l'entite --%>
		<aui:button-row>
			
			<aui:input type="hidden" name="workflowAction" value="" />
			
			<%-- Test : Verification des droits d'edition et de sauvegarde --%>
			<c:if test="${(dc.hasPermission('EDIT_PUBLIKUSER') and not empty dc.publikUser) and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<aui:button cssClass="btn-lg" type="submit" value="save" />
			</c:if>

			<%-- Composant : bouton de retour a la liste des entites --%>
			<aui:button cssClass="btn-lg" href="${param.returnURL}" type="cancel" />
			
		</aui:button-row>

	</aui:form>
	
</div>

<liferay-util:html-bottom>
	<script src="/o/oidcbo/js/oidc-bo-edit-publikuser.js" type="text/javascript"></script>
</liferay-util:html-bottom>