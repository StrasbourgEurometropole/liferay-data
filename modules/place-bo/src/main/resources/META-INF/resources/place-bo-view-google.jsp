<%@ include file="/place-bo-init.jsp"%>

<%-- URL : definit le lien avec les parametres de recherche des entites--%>
<liferay-portlet:renderURL varImpl="googleURL">
	<portlet:param name="tab" value="google" />
	<portlet:param name="orderByCol" value="${dc.orderByCol}" />
	<portlet:param name="orderByType" value="${dc.orderByType}" />
	<portlet:param name="filterCategoriesIds" value="${dc.filterCategoriesIds}" />
	<portlet:param name="keywords" value="${dc.keywords}" />
	<portlet:param name="delta" value="${dc.searchContainer.delta}" />
</liferay-portlet:renderURL>
<liferay-ui:error key="synchronise-forbidden" message="synchronise-forbidden />

<%-- Composant : tableau de visualisation des entites --%>
<div class="container-fluid-1280 main-content-body">
    <h1><liferay-ui:message key="gmb-synchro-title" /></h1>
    <br>
	<aui:form method="post" name="fm">
		<aui:input type="hidden" name="selectionIds" />
		<liferay-ui:search-container id="importHistoricsSearchContainer"
			searchContainer="${dc.searchContainer}">

			<liferay-ui:search-container-results results="${dc.googleMyBusinessHistorics}" />

			<liferay-ui:search-container-row
				className="eu.strasbourg.service.place.model.GoogleMyBusinessHistoric" modelVar="googleMyBusinessHistoric"
				keyProperty="googleMyBusinessHistoricId" rowIdProperty="googleMyBusinessHistoricId">

				<%-- URL : definit le lien vers la page d'edition de l'entite selectionnee --%>
				<liferay-portlet:renderURL varImpl="editGoogleURL">
					<portlet:param name="cmd" value="editGoogle" />
					<portlet:param name="googleMyBusinessHistoricId" value="${googleMyBusinessHistoric.googleMyBusinessHistoricId}" />
					<portlet:param name="returnURL" value="${googleURL}" />
					<portlet:param name="mvcPath" value="/place-bo-edit-google.jsp" />
				</liferay-portlet:renderURL>

				<%-- Colonne : Resultat --%>
				<liferay-ui:search-container-column-text cssClass="content-column" href="${editGoogleURL}"
					name="result" truncate="true" orderable="true" value="${googleMyBusinessHistoric.getResultLabel()}" />

				<%-- Colonne : Date de creation --%>
				<fmt:formatDate value="${googleMyBusinessHistoric.createDate}"
					var="formattedCreateDate" type="date" pattern="dd/MM/yyyy HH:mm" />
				<liferay-ui:search-container-column-text cssClass="content-column"
					name="create-date" truncate="true" orderable="true"
					value="${formattedCreateDate}" />

				<%-- Colonne : Createur --%>
				<liferay-ui:search-container-column-text name="user">
					${googleMyBusinessHistoric.statusByUserName}
				</liferay-ui:search-container-column-text>

			</liferay-ui:search-container-row>

			<%-- Iterateur --%>
			<liferay-ui:search-iterator paginate="true" displayStyle="list"
				markupView="lexicon" searchContainer="${dc.searchContainer}" />

		</liferay-ui:search-container>
	</aui:form>

    <liferay-portlet:actionURL name="startSynchronized" var="startSynchronizedURL">
        <portlet:param name="tab" value="google" />
    </liferay-portlet:actionURL>
	<form action="${startSynchronizedURL}" method="POST">
   		<aui:button-row>
   			<c:if test="${dc.canSynchronise() && isAdmin}">
   			    <aui:button cssClass="btn-lg" type="submit" value="synchronize" />
   			</c:if>
   			<c:if test="${not dc.canSynchronise() || not isAdmin}">
   			    <aui:button cssClass="btn-lg" type="submit" value="synchronize"  disabled="true" />
   			    <div class="tooltip-inner"><liferay-ui:message key="synchronise-forbidden" /></div>
   			</c:if>
   		</aui:button-row>
   	</form>

</div>