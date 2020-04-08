<%@ include file="/form-send-bo-init.jsp"%>

<%-- URL : definit le lien avec les parametres de recherche des entites--%>
<liferay-portlet:renderURL varImpl="formsURL">
	<portlet:param name="tab" value="forms" />
	<portlet:param name="orderByCol" value="${dc.orderByCol}" />
	<portlet:param name="orderByType" value="${dc.orderByType}" />
	<portlet:param name="keywords" value="${dc.keywords}" />
	<portlet:param name="delta" value="${dc.searchContainer.delta}" />
</liferay-portlet:renderURL>

<!-- Declaration de l'URL de recherche dans le listing de l'entite courrante -->
<liferay-portlet:renderURL varImpl="searchURL">
	<portlet:param name="cmd" value="search" />
	<portlet:param name="tab" value="${param.tab}" />
	<portlet:param name="orderByCol" value="${dc.orderByCol}" />
	<portlet:param name="orderByType" value="${dc.orderByType}" />
	<portlet:param name="keywords" value="${dc.keywords}" />
	<portlet:param name="delta" value="${dc.searchContainer.delta}" />
</liferay-portlet:renderURL>

<!-- Barre de navigation -->
<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">
	<!-- Liste des onglet -->
	<aui:nav cssClass="navbar-nav">
		<aui:nav-item href="${formsURL}" label="forms"
			selected="${tab eq 'forms'}" />
	</aui:nav>
</aui:nav-bar>

<%-- Composant : barre de filtres et de gestion des entite --%>
<liferay-frontend:management-bar includeCheckBox="false" searchContainerId="formsSearchContainer">
		<%-- Composant : partie filtres et selection --%>
		<liferay-frontend:management-bar-filters>
			<liferay-frontend:management-bar-sort
			    orderByCol="${dc.orderByCol}"
				orderByType="${dc.orderByType}"
				orderColumns='<%= new String[] {"modified-date"} %>'
                portletURL="${formsURL}" />
		</liferay-frontend:management-bar-filters>
</liferay-frontend:management-bar>

<%-- Composant : tableau de visualisation des entites --%>
<div class="container-fluid-1280 main-content-body">
	<aui:form method="post" name="fm">
		<liferay-ui:search-container id="formsSearchContainer"
			searchContainer="${dc.searchContainer}">

			<liferay-ui:search-container-results results="${dc.forms}" />

			<liferay-ui:search-container-row
				className="com.liferay.dynamic.data.mapping.model.DDMFormInstance" modelVar="ddmFormInstance"
				keyProperty="formInstanceId" rowIdProperty="formInstanceId">

				<%-- URL : definit le lien vers la page d'edition de l'entite selectionnee --%>
				<liferay-portlet:renderURL varImpl="viewFormSendsURL">
	                <portlet:param name="tab" value="viewFormSends" />
					<portlet:param name="formInstanceId" value="${ddmFormInstance.formInstanceId}" />
					<portlet:param name="returnURL" value="${formsURL}" />
					<portlet:param name="mvcPath" value="/form-send-bo-view-form-send.jsp" />
				</liferay-portlet:renderURL>

				<%-- Colonne : Name --%>
				<liferay-ui:search-container-column-text cssClass="content-column"
					href="${viewFormSendsURL}" name="name" truncate="true" orderable="true"
					value="${ddmFormInstance.getName(locale)}" />

				<%-- Colonne : Id --%>
				<liferay-ui:search-container-column-text cssClass="content-column"
					href="${viewFormSendsURL}" name="id" truncate="true" orderable="true"
					value="${ddmFormInstance.formInstanceId}" />

				<%-- Colonne : Date de modification --%>
				<fmt:formatDate value="${ddmFormInstance.modifiedDate}"
					var="formattedModifiedDate" type="date" pattern="dd/MM/yyyy HH:mm" />
				<liferay-ui:search-container-column-text cssClass="content-column"
					name="modified-date" truncate="true" orderable="true"
					value="${formattedModifiedDate}" />
			</liferay-ui:search-container-row>

			<%-- Iterateur --%>
			<liferay-ui:search-iterator paginate="true" displayStyle="list"
				markupView="lexicon" searchContainer="${dc.searchContainer}" />
		</liferay-ui:search-container>
	</aui:form>
</div>

<style>
    .lfr-checkbox-column{
        display:none;
    }
</style>