<%@ include file="/oidc-bo-init.jsp"%>

<%-- URL : definit le lien avec les parametres de recherche des entites--%>
<liferay-portlet:renderURL varImpl="anonymisationHistoricsURL">
	<portlet:param name="tab" value="anonymisationHistorics" />
	<portlet:param name="orderByCol" value="${dc.orderByCol}" />
	<portlet:param name="orderByType" value="${dc.orderByType}" />
	<portlet:param name="filterCategoriesIds" value="${dc.filterCategoriesIds}" />
	<portlet:param name="keywords" value="${dc.keywords}" />
	<portlet:param name="delta" value="${dc.searchContainer.delta}" />
</liferay-portlet:renderURL>
<liferay-ui:error key="anonymisation-forbidden" message="anonymisation-forbidden" />

<%-- Composant : tableau de visualisation des entites --%>
<div class="container-fluid-1280 main-content-body">
	<aui:form method="post" name="fm">
		<aui:input type="hidden" name="selectionIds" />
		<liferay-ui:search-container id="importHistoricsSearchContainer"
			searchContainer="${dc.searchContainer}">

			<liferay-ui:search-container-results results="${dc.anonymisationHistorics}" />

			<liferay-ui:search-container-row
				className="eu.strasbourg.service.oidc.model.AnonymisationHistoric" modelVar="anonymisationHistoric"
				keyProperty="anonymisationHistoricId" rowIdProperty="anonymisationHistoricId">

				<%-- URL : definit le lien vers la page d'edition de l'entite selectionnee --%>
				<liferay-portlet:renderURL varImpl="editAnonymisationHistoricURL">
					<portlet:param name="cmd" value="editAnonymisationHistoric" />
					<portlet:param name="anonymisationHistoricId" value="${anonymisationHistoric.anonymisationHistoricId}" />
					<portlet:param name="returnURL" value="${anonymisationHistoricsURL}" />
					<portlet:param name="mvcPath" value="/oidc-bo-edit-anonymisation-historic.jsp" />
				</liferay-portlet:renderURL>

				<%-- Colonne : Resultat --%>
				<liferay-ui:search-container-column-text cssClass="content-column" href="${editAnonymisationHistoricURL}"
					name="result" truncate="true" orderable="true" value="${anonymisationHistoric.getResultLabel()}" />

				<%-- Colonne : Date de creation --%>
				<fmt:formatDate value="${anonymisationHistoric.createDate}"
					var="formattedCreateDate" type="date" pattern="dd/MM/yyyy HH:mm" />
				<liferay-ui:search-container-column-text cssClass="content-column"
					name="create-date" truncate="true" orderable="true"
					value="${formattedCreateDate}" />

				<%-- Colonne : Createur --%>
				<liferay-ui:search-container-column-text name="user"
				    cssClass="content-column" truncate="true" >
					${anonymisationHistoric.statusByUserName}
				</liferay-ui:search-container-column-text>

			</liferay-ui:search-container-row>

			<%-- Iterateur --%>
			<liferay-ui:search-iterator paginate="true" displayStyle="list"
				markupView="lexicon" searchContainer="${dc.searchContainer}" />

		</liferay-ui:search-container>
	</aui:form>

	<liferay-portlet:actionURL var="anonymisationURL" name="anonymisation">
        <portlet:param name="tab" value="anonymisationHistorics" />
    </liferay-portlet:actionURL>

	<form method="POST" action="${anonymisationURL}">
   		<aui:button-row>
            <c:if test="${cd.canAnonymise() && isAdmin}">
                <aui:button cssClass="btn-lg" type="submit" value="anonymisation" />
            </c:if>
            <c:if test="${not cd.canAnonymise() || not isAdmin}">
                <aui:button cssClass="btn-lg" type="submit" value="anonymisation"  disabled="true" />
   			    <div class="tooltip-inner"><liferay-ui:message key="anonymisation-forbidden" /></div>
            </c:if>
   		</aui:button-row>
   	</form>

</div>