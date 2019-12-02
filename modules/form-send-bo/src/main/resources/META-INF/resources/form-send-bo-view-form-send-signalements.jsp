<%@ include file="/form-send-bo-init.jsp"%>

<%-- URL : definit le lien de retour --%>
<liferay-portlet:renderURL varImpl="formsURL">
	<portlet:param name="tab" value="forms" />
</liferay-portlet:renderURL>

<%-- URL : definit le lien avec les parametres de recherche des entites--%>
<liferay-portlet:renderURL varImpl="viewReportingsURL">
	<portlet:param name="tab" value="viewReportings" />
	<portlet:param name="orderByCol" value="${dc.orderByCol}" />
	<portlet:param name="orderByType" value="${dc.orderByType}" />
	<portlet:param name="keywords" value="${dc.keywords}" />
    <portlet:param name="recordSetId" value="${recordSetId}" />
	<portlet:param name="delta" value="${dc.searchContainer.delta}" />
    <portlet:param name="returnURL" value="${formsURL}" />
</liferay-portlet:renderURL>

<%-- Composant : barre de filtres et de gestion des entite --%>
<liferay-frontend:management-bar includeCheckBox="false" searchContainerId="signalementsSearchContainer">

		<%-- Composant : partie filtres et selection --%>
		<liferay-frontend:management-bar-filters>
			<liferay-frontend:management-bar-sort orderByCol="${dc.orderByCol}"
				orderByType="${dc.orderByType}"
				orderColumns='<%= new String[] {"modified-date"} %>'
				portletURL="${viewReportingsURL}" />
		</liferay-frontend:management-bar-filters>
</liferay-frontend:management-bar>

<%-- Composant : tableau de visualisation des entites --%>
<div class="container-fluid-1280 main-content-body">
	<aui:form method="post" name="fm">
		<liferay-ui:search-container id="signalementsSearchContainer"
			searchContainer="${dc.searchContainer}">
			<liferay-ui:search-container-results results="${dc.signalements}" />

			<liferay-ui:search-container-row
				className="eu.strasbourg.service.formSendRecordField.model.FormSendRecordFieldSignalement" modelVar="signalement"
				keyProperty="signalementId" rowIdProperty="signalementId">

				<%-- Colonne : debut de la réponse--%>
				<liferay-ui:search-container-column-text cssClass="content-column"
					name="response" truncate="true" value="${dc.getResponse(signalement.formSendRecordFieldId)}" />

				<%-- Colonne : Date de signalement --%>
				<fmt:formatDate value="${signalement.createDate}"
					var="formattedCreatedDate" type="date" pattern="dd/MM/yyyy HH:mm" />
				<liferay-ui:search-container-column-text cssClass="content-column"
					name="reporting-date" truncate="true" orderable="true"
					value="${formattedCreatedDate}" />

				<%-- Colonne : Type de signalement--%>
				<liferay-ui:search-container-column-text cssClass="content-column"
                    name="report-type" truncate="true" orderable="true"
                    value="${signalement.getCategorieName()}" />

                <%-- URL : Definit le lien vers la page d'edition du commentaire liee au signalement selectionnee --%>
                <c:choose>
                    <c:when test="${signalement.status eq 0}">
                        <liferay-portlet:renderURL varImpl="ShowResponseURL">
	                        <portlet:param name="tab" value="viewReportings" />
                            <portlet:param name="cmd" value="showResponse" />
                            <portlet:param name="formSendRecordFieldId" value="${signalement.formSendRecordFieldId}" />
                            <portlet:param name="recordSetId" value="${recordSetId}" />
                            <portlet:param name="returnURL" value="${formsURL}" />
                        </liferay-portlet:renderURL>

                        <%-- Colonne : approuver la réponse --%>
                        <liferay-ui:search-container-column-text cssClass="content-column" >
                            <aui:button href="${ShowResponseURL}" value="show-response"/>
                        </liferay-ui:search-container-column-text>
                    </c:when>
                    <c:when test="${signalement.status eq 4}">
                        <liferay-portlet:renderURL varImpl="HideResponseURL">
	                        <portlet:param name="tab" value="viewReportings" />
                            <portlet:param name="cmd" value="hideResponse" />
                            <portlet:param name="formSendRecordFieldId" value="${signalement.formSendRecordFieldId}" />
                            <portlet:param name="recordSetId" value="${recordSetId}" />
                            <portlet:param name="returnURL" value="${formsURL}" />
                        </liferay-portlet:renderURL>

                        <%-- Colonne : cacher la réponse --%>
                        <liferay-ui:search-container-column-text cssClass="content-column" >
                            <aui:button href="${HideResponseURL}" value="hide-response"/>
                        </liferay-ui:search-container-column-text>
                    </c:when>
                </c:choose>

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