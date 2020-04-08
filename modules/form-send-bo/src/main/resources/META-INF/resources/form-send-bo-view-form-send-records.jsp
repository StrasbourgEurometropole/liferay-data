<%@ include file="/form-send-bo-init.jsp"%>

<%-- URL : definit le lien de retour --%>
<liferay-portlet:renderURL varImpl="formsURL">
	<portlet:param name="tab" value="forms" />
</liferay-portlet:renderURL>

<%-- URL : definit le lien avec les parametres de recherche des entites--%>
<liferay-portlet:renderURL varImpl="viewFormSendsURL">
	<portlet:param name="tab" value="viewFormSends" />
	<portlet:param name="orderByCol" value="${dc.orderByCol}" />
	<portlet:param name="orderByType" value="${dc.orderByType}" />
	<portlet:param name="keywords" value="${dc.keywords}" />
    <portlet:param name="formInstanceId" value="${formInstanceId}" />
	<portlet:param name="delta" value="${dc.searchContainer.delta}" />
    <portlet:param name="returnURL" value="${formsURL}" />
</liferay-portlet:renderURL>

<%-- Composant : barre de filtres et de gestion des entite --%>
<liferay-frontend:management-bar includeCheckBox="false" searchContainerId="formSendsSearchContainer">
		<%-- Composant : partie filtres et selection --%>
		<liferay-frontend:management-bar-filters>
			<liferay-frontend:management-bar-sort
			    orderByCol="${dc.orderByCol}"
				orderByType="${dc.orderByType}"
				orderColumns='<%= new String[] {"modified-date"} %>'
				portletURL="${viewFormSendsURL}" />
		</liferay-frontend:management-bar-filters>
</liferay-frontend:management-bar>

<%-- Composant : tableau de visualisation des entites --%>
<div class="container-fluid-1280 main-content-body">
	<aui:form method="post" name="fm">
		<liferay-ui:search-container id="formSendsSearchContainer"
			searchContainer="${dc.searchContainer}">
			<liferay-ui:search-container-results results="${dc.formSends}" />

			<liferay-ui:search-container-row
				className="com.liferay.dynamic.data.mapping.model.DDMFormInstanceRecord" modelVar="ddmFormInstanceRecord"
				keyProperty="recordId" rowIdProperty="recordId">

				<%-- URL : definit le lien vers la page d'edition de l'entite selectionnee --%>
				<liferay-portlet:renderURL varImpl="editFormSendURL">
					<portlet:param name="cmd" value="editFormSend" />
                    <portlet:param name="formInstanceId" value="${formInstanceId}" />
					<portlet:param name="recordId" value="${ddmFormInstanceRecord.formInstanceRecordId}" />
					<portlet:param name="returnURL" value="${viewFormSendsURL}" />
					<portlet:param name="mvcPath" value="/form-send-bo-edit-form-send.jsp" />
				</liferay-portlet:renderURL>

				<%-- Colonne : Date de création --%>
				<fmt:formatDate value="${ddmFormInstanceRecord.createDate}"
					var="formattedCreateDate" type="date" pattern="dd/MM/yyyy HH:mm" />
				<liferay-ui:search-container-column-text cssClass="content-column"
					href="${editFormSendURL}" name="create-date" truncate="true" orderable="true"
					value="${formattedCreateDate}" />

                <c:forEach items="${dc.getRecordFields(ddmFormInstanceRecord.storageId, locale)}" var="recordField">
                    <liferay-ui:search-container-column-text cssClass="content-column"
                        href="${editFormSendURL}" name="${recordField[0]}" truncate="true" orderable="true"
                        value="${recordField[1]}" />
                </c:forEach>
			</liferay-ui:search-container-row>

			<%-- Iterateur --%>
			<liferay-ui:search-iterator paginate="false" displayStyle="list"
				markupView="lexicon" searchContainer="${dc.searchContainer}" />
		</liferay-ui:search-container>
	</aui:form>
</div>

<style>
    .lfr-checkbox-column{
        display:none;
    }
</style>