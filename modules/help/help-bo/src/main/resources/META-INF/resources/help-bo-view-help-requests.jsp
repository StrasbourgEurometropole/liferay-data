<%@ include file="/help-bo-init.jsp"%>

<%-- URL : definit le lien avec les parametres de recherche des entites--%>
<liferay-portlet:renderURL varImpl="helpRequestsURL">
    <portlet:param name="tab" value="helpRequests" />
    <portlet:param name="orderByCol" value="${dc.orderByCol}" />
    <portlet:param name="filterCategoriesIds" value="${dc.filterCategoriesIds}" />
    <portlet:param name="keywords" value="${dc.keywords}" />
    <portlet:param name="delta" value="${dc.searchContainer.delta}" />
</liferay-portlet:renderURL>

<%-- Composant : barre de filtres et de gestion des entites --%>
<liferay-frontend:management-bar includeCheckBox="true" searchContainerId="helpRequestsSearchContainer">

    <%-- Composant : partie filtres et selection --%>
    <liferay-frontend:management-bar-filters>
        <c:if test="${fn:length(dc.vocabularies) > 0}">
            <li><a>Filtrer par :</a></li>
        </c:if>
        <c:forEach var="vocabulary" items="${dc.vocabularies}">
            <liferay-frontend:management-bar-filter
                    managementBarFilterItems="${dc.getManagementBarFilterItems(vocabulary)}"
                    value="${dc.getVocabularyFilterLabel(vocabulary)}" />
        </c:forEach>

        <liferay-frontend:management-bar-sort orderByCol="${dc.orderByCol}"
                                              orderByType="${dc.orderByType}"
                                              orderColumns='<%= new String[] {"create-date", "modified-date" } %>'
                                              portletURL="${helpRequestsURL}" />
    </liferay-frontend:management-bar-filters>

    <%-- Composant : partie gestion (affichee apres une selection) --%>
    <liferay-frontend:management-bar-action-buttons>
        <c:if test="${dc.hasPermission('DELETE_HELP_REQUEST') and empty themeDisplay.scopeGroup.getStagingGroup()}">
            <liferay-frontend:management-bar-button
                    href='<%="javascript:" + renderResponse.getNamespace() + "deleteSelection();"%>'
                    icon="trash" label="delete" />
        </c:if>
    </liferay-frontend:management-bar-action-buttons>

</liferay-frontend:management-bar>

<%-- Composant : tableau de visualisation des entites --%>
<div class="container-fluid-1280 main-content-body">
    <aui:form method="post" name="fm">
        <aui:input type="hidden" name="selectionIds" />
        <liferay-ui:search-container id="helpRequestsSearchContainer"
                                     searchContainer="${dc.searchContainer}">
            <liferay-ui:search-container-results results="${dc.helpRequests}" />

            <liferay-ui:search-container-row
                    className="eu.strasbourg.service.help.model.HelpRequest" modelVar="helpRequest"
                    keyProperty="helpRequestId" rowIdProperty="helpRequestId">

                <%-- URL : definit le lien vers la page d'edition de l'entite selectionne --%>
                <liferay-portlet:renderURL varImpl="editHelpProposalURL">
                    <portlet:param name="cmd" value="editHelpProposal" />
                    <portlet:param name="helpProposalId" value="${helpRequest.helpProposalId}" />
                    <portlet:param name="returnURL" value="${dc.currentURL}" />
                    <portlet:param name="mvcPath" value="/help-bo-edit-help-proposal.jsp" />
                </liferay-portlet:renderURL>

                <%-- URL : definit le lien vers la page d'edition de l'entite selectionne --%>
                <liferay-portlet:renderURL varImpl="editHelpRequestURL">
                    <portlet:param name="cmd" value="editHelpRequest" />
                    <portlet:param name="helpRequestId" value="${helpRequest.helpRequestId}" />
                    <portlet:param name="returnURL" value="${dc.currentURL}" />
                    <portlet:param name="mvcPath" value="/help-bo-edit-help-request.jsp" />
                </liferay-portlet:renderURL>

                <%-- URL : definit le lien vers l'action de passage en Conforme --%>
                <liferay-portlet:actionURL name="changeStatusHelpRequest" var="validHelpRequestURL">
                    <portlet:param name="cmd" value="changeStatusHelpRequest" />
                    <portlet:param name="tab" value="helpRequests" />
                    <portlet:param name="returnURL" value="${dc.currentURL}" />
                    <portlet:param name="requestModerationStatus" value="Conforme" />
                    <portlet:param name="helpRequestId" value="${helpRequest.helpRequestId}" />
                </liferay-portlet:actionURL>

                <%-- URL : definit le lien vers l'action de passage en Alerte --%>
                <liferay-portlet:actionURL name="changeStatusHelpRequest" var="alertHelpRequestURL">
                    <portlet:param name="cmd" value="changeStatusHelpRequest" />
                    <portlet:param name="tab" value="helpRequests" />
                    <portlet:param name="returnURL" value="${dc.currentURL}" />
                    <portlet:param name="requestModerationStatus" value="Alerte" />
                    <portlet:param name="helpRequestId" value="${helpRequest.helpRequestId}" />
                </liferay-portlet:actionURL>

                <%-- URL : definit le lien vers l'action de passage en Non-conforme --%>
                <liferay-portlet:actionURL name="changeStatusHelpRequest" var="notValidHelpRequestURL">
                    <portlet:param name="cmd" value="changeStatusHelpRequest" />
                    <portlet:param name="tab" value="helpRequests" />
                    <portlet:param name="returnURL" value="${dc.currentURL}" />
                    <portlet:param name="requestModerationStatus" value="Non-conforme" />
                    <portlet:param name="helpRequestId" value="${helpRequest.helpRequestId}" />
                </liferay-portlet:actionURL>

                <%-- Colonne : Date de creation --%>
                <fmt:formatDate value="${helpRequest.createDate}"
                                var="formattedCreateDate" type="date" pattern="dd/MM/yyyy HH:mm" />
                <liferay-ui:search-container-column-text cssClass="content-column"
                                                         href="${editHelpRequestURL}"
                                                         name="request-create-date" truncate="true" orderable="true"
                                                         value="${formattedCreateDate}" />

                <%-- Colonne : Titre de la proposition d'aide --%>
                <liferay-ui:search-container-column-text cssClass="content-column"
                                                         href="${editHelpRequestURL}" name="help-proposal-title" truncate="true" orderable="true">
                    <c:out value="${helpRequest.helpProposal.titleCurrentValue}" escapeXml='true'/>
                </liferay-ui:search-container-column-text>

                <%-- Colonne : Identifiant --%>
                <liferay-ui:search-container-column-text name="help-proposal-id"
                                                         href="${editHelpRequestURL}">
                    <c:out value="${helpRequest.helpProposalId}" escapeXml='true'/>
                </liferay-ui:search-container-column-text>

                <%-- Colonne : Demandeur d'aide --%>
                <liferay-ui:search-container-column-text name="request-author"
                                                         href="${editHelpRequestURL}">
                    <c:out value="${helpRequest.authorNameLabel}" escapeXml='true'/>
                </liferay-ui:search-container-column-text>

                <%-- Colonne : Statut modération de la demande --%>
                <liferay-ui:search-container-column-text name="statusHelpModeration"
                                                         href="${editHelpRequestURL}">
				    <span class="badge ${helpRequest.getModerationStatusClass()}">
                            ${helpRequest.getModerationStatusTitle(locale)}
                    </span>
                </liferay-ui:search-container-column-text>

                <%-- Colonne : Consentement a etre contacte--%>
                <liferay-ui:search-container-column-text name="request-contact-consent"
                                                         href="${editHelpRequestURL}">
                    <c:if test="${helpRequest.agreementSigned3}">
                        <liferay-ui:message key="yes"/>
                    </c:if>
                    <c:if test="${not helpRequest.agreementSigned3}">
                        <liferay-ui:message key="no"/>
                    </c:if>

                </liferay-ui:search-container-column-text>

                <%-- Colonne : Actions possibles --%>
                <liferay-ui:search-container-column-text>
                    <liferay-ui:icon-menu markupView="lexicon">
                        <c:if test="${dc.hasPermission('EDIT_HELP_REQUEST') and empty themeDisplay.scopeGroup.getStagingGroup()}">
                            <liferay-ui:icon message="view-help-request" url="${editHelpRequestURL}" />
                        </c:if>
                        <c:if test="${dc.hasPermission('EDIT_HELP') and empty themeDisplay.scopeGroup.getStagingGroup()}">
                            <liferay-ui:icon message="view-help-proposal" url="${editHelpProposalURL}" />
                        </c:if>

                        <c:if test="${dc.hasPermission('EDIT_HELP_REQUEST') and empty themeDisplay.scopeGroup.getStagingGroup()}">
                            <c:if test="${helpRequest.getModerationStatusTitle(locale) != 'Conforme'}">
                                <liferay-ui:icon-delete confirmation="set-valid-confirm" message="set-request-valid" url="${validHelpRequestURL}" />
                            </c:if>
                            <c:if test="${helpRequest.getModerationStatusTitle(locale) != 'Alerte'}">
                                <liferay-ui:icon-delete confirmation="set-alert-confirm" message="set-request-alert" url="${alertHelpRequestURL}" />
                            </c:if>
                            <c:if test="${helpRequest.getModerationStatusTitle(locale) != 'Non-conforme'}">
                                <liferay-ui:icon-delete confirmation="set-not-valid-confirm" message="set-request-not-valid" url="${notValidHelpRequestURL}" />
                            </c:if>
                        </c:if>
                        <%--
						<c:set value="${helpSeeker.publikUser.publikUserLiferayId}" var="publikId" />
						<c:if test="${dc.hasPermissionOIDC('EDIT_PUBLIKUSER') and empty themeDisplay.scopeGroup.getStagingGroup()}">
							<liferay-ui:icon message="view-user-profil" url="${dc.getPublikUserEditURL(publikId)}" />
						</c:if>
						--%>
                    </liferay-ui:icon-menu>
                </liferay-ui:search-container-column-text>

            </liferay-ui:search-container-row>

            <%-- Iterateur --%>
            <liferay-ui:search-iterator paginate="true" displayStyle="list"
                                        markupView="lexicon" searchContainer="${dc.searchContainer}" />
        </liferay-ui:search-container>
    </aui:form>
</div>