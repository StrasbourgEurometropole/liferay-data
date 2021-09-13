<%@ include file="/csmap-bo-agenda-init.jsp" %>

<%-- URL : definit le lien avec les parametres de recherche des entites--%>
<liferay-portlet:renderURL varImpl="agendaThematiqueURL">
	<portlet:param name="tab" value="agendaThematique" />
</liferay-portlet:renderURL>
<%-- URL : definit le lien vers la page d'edition d'une entite --%>
<liferay-portlet:renderURL varImpl="addAgendaThematiqueURL">
	<portlet:param name="cmd" value="editAgendaThematique" />
    <portlet:param name="returnURL" value="${agendaThematiqueURL}" />
    <portlet:param name="mvcPath" value="/csmap-bo-agenda-edit-thematique.jsp" />
</liferay-portlet:renderURL>

<%-- Composant : tableau de visualisation des entites --%>
<div class="container-fluid-1280 main-content-body">
	<aui:form method="post" name="fm">
		<aui:input type="hidden" name="selectionIds" />
		<liferay-ui:search-container id="agendasThematiqueSearchContainer"
			searchContainer="${dc.searchContainer}">

			<liferay-ui:search-container-results results="${dc.agendas}" />

			<liferay-ui:search-container-row
				className="eu.strasbourg.service.csmap.model.Agenda" modelVar="agenda"
				keyProperty="agendaId" rowIdProperty="agendaId">

                <%-- URL : definit le lien vers la page d'edition de l'entite selectionnee --%>
                <liferay-portlet:renderURL varImpl="editAgendaThematiqueURL">
                    <portlet:param name="cmd" value="editAgendaThematique" />
                    <portlet:param name="agendaId" value="${agenda.agendaId}" />
                    <portlet:param name="returnURL" value="${agendaThematiqueURL}" />
                    <portlet:param name="mvcPath" value="/csmap-bo-agenda-edit-thematique.jsp" />
                </liferay-portlet:renderURL>
                <%-- URL : definit le lien vers l'action de supprimer l'entite selectionnee --%>
                <liferay-portlet:actionURL name="deleteAgendaThematique" var="deleteAgendaThematiqueURL">
                    <portlet:param name="cmd" value="deleteAgendaThematique" />
                    <portlet:param name="tab" value="agendaThematique" />
                    <portlet:param name="agendaId" value="${agenda.agendaId}" />
                </liferay-portlet:actionURL>
                <%-- URL : definit le lien vers l'action d'activer l'entite selectionnee --%>
                <liferay-portlet:actionURL name="ActivateAgendaThematique" var="ActivateAgendaThematiqueURL">
                    <portlet:param name="cmd" value="ActivateAgendaThematique" />
                    <portlet:param name="tab" value="agendaThematique" />
                    <portlet:param name="agendaId" value="${agenda.agendaId}" />
                </liferay-portlet:actionURL>
                <%-- URL : definit le lien vers l'action de désactiver l'entite selectionnee --%>
                <liferay-portlet:actionURL name="deactivateAgendaThematique" var="deactivateAgendaThematiqueURL">
                    <portlet:param name="cmd" value="deactivateAgendaThematique" />
                    <portlet:param name="tab" value="agendaThematique" />
                    <portlet:param name="agendaId" value="${agenda.agendaId}" />
                </liferay-portlet:actionURL>

				<%-- Colonne : Titre --%>
				<liferay-ui:search-container-column-text cssClass="content-column" href="${editAgendaThematiqueURL}"
					name="title" truncate="true" orderable="true" value="${agenda.getTitle()}" />

				<%-- Colonne : IsActive --%>
				<liferay-ui:search-container-column-text cssClass="content-column"
					name="isActive" truncate="true" orderable="true"
					value="${agenda.getIsActive()}" />


				<liferay-ui:search-container-column-text>
					<liferay-ui:icon-menu markupView="lexicon">
						<c:if test="${dc.hasPermission('EDIT_AGENDA')}">
							<liferay-ui:icon message="edit" url="${editAgendaThematiqueURL}" />
						</c:if>

						<c:if test="${dc.hasPermission('DELETE_AGENDA')}">
							<liferay-ui:icon message="delete" url="${deleteAgendaThematiqueURL}" />
						</c:if>

						<c:if test="${dc.hasPermission('EDIT_AGENDA')}">
                            <c:if test="${agenda.getIsActive()}">
                                <liferay-ui:icon message="deactivate" url="${deactivateAgendaThematiqueURL}" />
                            </c:if>
                            <c:if test="${!agenda.getIsActive()}">
							    <liferay-ui:icon message="activate" url="javascript:areYouSure('${ActivateAgendaThematiqueURL}')"  />
                            </c:if>
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

<liferay-frontend:add-menu>
    <liferay-frontend:add-menu-item title="Ajouter un agenda"
        url="${addAgendaThematiqueURL}" />
</liferay-frontend:add-menu>

<aui:script>
	function areYouSure(url) {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-activate-this-agenda" />')) {
			var form = AUI.$(document.<portlet:namespace />fm);
			submitForm(form, url);
		}
	}
</aui:script>