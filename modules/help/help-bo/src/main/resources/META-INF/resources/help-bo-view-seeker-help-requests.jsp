<%@ include file="/help-bo-init.jsp"%>

<%-- Header --%>
<div class="navbar navbar-default collapse-basic-search" id="iqzh">
	<div class="container-fluid-1280">
		<div class="navbar-header visible-xs">
			<button class="collapsed navbar-toggle navbar-toggle-left navbar-toggle-page-name" data-target="#_eu_strasbourg_portlet_oidc_OIDCBOPortlet_navTag_1NavbarCollapse" data-toggle="collapse" id="_eu_strasbourg_portlet_oidc_OIDCBOPortlet_NavbarBtn" type="button">
				<span class="sr-only">Basculer la navigation</span>
				<span class="page-name">Utilisateurs Publik</span>
				<span class="caret"></span>
			</button>
		</div>
		<!-- Liste des onglet -->
		<div class="collapse navbar-collapse" id="_eu_strasbourg_portlet_oidc_OIDCBOPortlet_navTagNavbarCollapse">
			<ul aria-label="Gestion Utilisateurs Publik" class="lfr-nav nav navbar-nav" id="_eu_strasbourg_portlet_oidc_OIDCBOPortlet_navTag" role="menubar">
				<li class=" active " id="nraf_" role="presentation" style="margin: auto; position: inherit; font-weight: bold">
					<a class="" role="menuitem" title="Utilisateurs Publik">
					<span class="nav-item-label">
					<liferay-ui:message key="seeker-help-requests-list"/>
					<liferay-ui:message key=" : ${dc.helpSeeker.firstName} ${dc.helpSeeker.lastName}"></liferay-ui:message>
					</span>
					</a>
				</li>
			</ul>
		</div>
	</div>
</div>

<%-- Body --%>
<%-- Composant : tableau de visualisation des entites --%>
<div class="container-fluid-1280 main-content-body">
	<aui:form method="post" name="fm">
		<liferay-ui:search-container id="helpRequestsSearchContainer"
									 searchContainer="${dc.searchContainer}">
			<liferay-ui:search-container-results results="${dc.helpRequests}" />
			<liferay-ui:search-container-row className="eu.strasbourg.service.help.model.HelpRequest" modelVar="helpRequest"
											 keyProperty="helpRequestId" rowIdProperty="helpRequestId">
				<%-- Colonne : Auteur de la demande --%>
				<liferay-ui:search-container-column-text name="help-proposal-title">
					${helpRequest.helpProposal.getTitle(locale)}
				</liferay-ui:search-container-column-text>
				<%-- Colonne : Message de la demande --%>
				<liferay-ui:search-container-column-text name="helper-name">
					${helpRequest.helpProposal.authorNameLabel}
				</liferay-ui:search-container-column-text>
				<%-- Colonne : Statut activite de l'aide --%>
				<liferay-ui:search-container-column-text name="statusHelpActivity">
                    <span class="badge ${helpRequest.helpProposal.getActivityStatusClass()}">
							${helpRequest.helpProposal.getActivityStatusTitle(locale)}
					</span>
				</liferay-ui:search-container-column-text>

				<%-- Colonne : Statut moderation de l'aide --%>
				<liferay-ui:search-container-column-text name="statusHelpModeration">
				    <span class="badge ${helpRequest.helpProposal.getModerationStatusClass()}">
							${helpRequest.helpProposal.getModerationStatusTitle(locale)}
					</span>
				</liferay-ui:search-container-column-text>
				<%-- Colonne : Date de depot de la demande --%>
				<fmt:formatDate value="${helpRequest.createDate}"
								var="formattedCreateDate" type="date" pattern="dd/MM/yyyy HH:mm" />
				<liferay-ui:search-container-column-text cssClass="content-column"
														 name="request-create-date" truncate="true" orderable="true"
														 value="${formattedCreateDate}" />
				<%-- Colonne : Boutons --%>
				<liferay-ui:search-container-column-text>
					<liferay-ui:icon-menu markupView="lexicon">
						<%-- URL : definit le lien vers la page d'ajout/edition d'une entite --%>
						<liferay-portlet:renderURL varImpl="editHelpRequestURL">
							<portlet:param name="cmd" value="editHelpRequest" />
							<portlet:param name="helpRequestId" value="${helpRequest.helpRequestId}" />
							<portlet:param name="mvcPath" value="/help-bo-edit-help-request.jsp" />
							<portlet:param name="returnURL" value="${dc.currentUrl}" />
						</liferay-portlet:renderURL>
						<%-- Consulter la demande --%>
						<c:if test="${dc.hasPermission('EDIT_HELP_REQUEST') and empty themeDisplay.scopeGroup.getStagingGroup()}">
							<liferay-ui:icon message="view-help-request" url="${editHelpRequestURL}" />
						</c:if>
						<%-- URL : definit le lien vers la page d'edition de l'entite selectionne --%>
						<liferay-portlet:renderURL varImpl="editHelpProposalURL">
							<portlet:param name="cmd" value="editHelpProposal" />
							<portlet:param name="helpProposalId" value="${helpRequest.helpProposal.helpProposalId}" />
							<portlet:param name="returnURL" value="${dc.currentUrl}" />
							<portlet:param name="mvcPath" value="/help-bo-edit-help-proposal.jsp" />
						</liferay-portlet:renderURL>
						<%-- Consulter la proposition --%>
						<c:if test="${dc.hasPermission('EDIT_HELP') and empty themeDisplay.scopeGroup.getStagingGroup()}">
							<liferay-ui:icon message="view-help-proposal" url="${editHelpProposalURL}" />
						</c:if>
					</liferay-ui:icon-menu>
				</liferay-ui:search-container-column-text>
			</liferay-ui:search-container-row>
			<%-- Iterateur --%>
			<liferay-ui:search-iterator paginate="true" displayStyle="list"
										markupView="lexicon" searchContainer="${dc.searchContainer}" />
		</liferay-ui:search-container>
	</aui:form>
</div>