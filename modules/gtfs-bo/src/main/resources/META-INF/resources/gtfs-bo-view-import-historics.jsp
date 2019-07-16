<%@ include file="/gtfs-bo-init.jsp"%>

<%-- URL : definit le lien avec les parametres de recherche des entites--%>
<liferay-portlet:renderURL varImpl="importHistoricsURL">
	<portlet:param name="tab" value="import-historics" />
	<portlet:param name="orderByCol" value="${dc.orderByCol}" />
	<portlet:param name="orderByType" value="${dc.orderByType}" />
	<portlet:param name="filterCategoriesIds" value="${dc.filterCategoriesIds}" />
	<portlet:param name="keywords" value="${dc.keywords}" />
	<portlet:param name="delta" value="${dc.searchContainer.delta}" />
</liferay-portlet:renderURL>

<%-- Composant : tableau de visualisation des entites --%>
<div class="container-fluid-1280 main-content-body">
	<aui:form method="post" name="fm">
		<aui:input type="hidden" name="selectionIds" />
		<liferay-ui:search-container id="importHistoricsSearchContainer"
			searchContainer="${dc.searchContainer}">
			
			<liferay-ui:search-container-results results="${dc.importHistorics}" />

			<liferay-ui:search-container-row
				className="eu.strasbourg.service.gtfs.model.ImportHistoric" modelVar="importHistoric"
				keyProperty="importHistoricId" rowIdProperty="importHistoricId">

				<%-- Colonne : Resultat --%>
				<liferay-ui:search-container-column-text cssClass="content-column"
					name="result" truncate="true" orderable="true" value="${importHistoric.getResultLabel()}" />
				
				<%-- Colonne : Date de creation --%>
				<fmt:formatDate value="${importHistoric.createDate}"
					var="formattedCreateDate" type="date" pattern="dd/MM/yyyy HH:mm" />
				<liferay-ui:search-container-column-text cssClass="content-column"
					name="create-date" truncate="true" orderable="true"
					value="${formattedCreateDate}" />
				
				<%-- Colonne : Createur --%>
				<liferay-ui:search-container-column-text name="user">
					${importHistoric.statusByUserName}
				</liferay-ui:search-container-column-text>

			</liferay-ui:search-container-row>

			<%-- Iterateur --%>
			<liferay-ui:search-iterator paginate="true" displayStyle="list"
				markupView="lexicon" searchContainer="${dc.searchContainer}" />
				
		</liferay-ui:search-container>
	</aui:form>
	
	<liferay-portlet:resourceURL var="importGTFSURL" id="importGTFS">
    </liferay-portlet:resourceURL>
	
	<form method="POST" action="${importGTFSURL}">
   		<aui:button-row>
   			<aui:button cssClass="btn-lg" type="submit" value="import-gtfs" />
   		</aui:button-row>
   	</form>
   	
</div>