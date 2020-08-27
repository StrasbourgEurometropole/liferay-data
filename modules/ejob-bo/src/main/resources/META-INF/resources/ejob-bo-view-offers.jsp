<%@ include file="/ejob-bo-init.jsp"%>

<liferay-portlet:renderURL varImpl="offersURL">
	<portlet:param name="tab" value="offers" />
	<portlet:param name="orderByCol" value="${dc.orderByCol}" />
	<portlet:param name="orderByType" value="${dc.orderByType}" />
	<portlet:param name="filterCategoriesIds" value="${dc.filterCategoriesIds}" />
	<portlet:param name="keywords" value="${dc.keywords}" />
	<portlet:param name="delta" value="${dc.searchContainer.delta}" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL varImpl="addOffersURL">
	<portlet:param name="cmd" value="editOffers" />
	<portlet:param name="mvcPath" value="/ejob-bo-edit-offer.jsp" />
	<portlet:param name="returnURL" value="${offersURL}" />
</liferay-portlet:renderURL>


<liferay-frontend:management-bar includeCheckBox="true" searchContainerId="offersSearchContainer">
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
                orderColumns='<%= new String[] {"title", "date"} %>'
                portletURL="${offersURL}" />
    </liferay-frontend:management-bar-filters>
</liferay-frontend:management-bar>


<div class="container-fluid-1280 main-content-body">

	<%-- Composant : definit la liste des messages d'erreur  (voir methode "doProcessAction" dans le deleteAction de l'entite) --%>
	<liferay-ui:error key="offers-error" message="offers-error" />

	<aui:form method="post" name="fm">
		<aui:input type="hidden" name="selectionIds" />
		<liferay-ui:search-container id="offersSearchContainer" searchContainer="${dc.searchContainer}">
			<liferay-ui:search-container-results results="${dc.offers}" />

			<liferay-ui:search-container-row
				className="eu.strasbourg.service.ejob.model.Offer"
				modelVar="offer" keyProperty="offerId" rowIdProperty="offerId">

				<liferay-portlet:renderURL varImpl="editOfferURL">
					<portlet:param name="cmd" value="editOffer" />
					<portlet:param name="offerId" value="${offer.offerId}" />
					<portlet:param name="returnURL" value="${offersURL}" />
					<portlet:param name="mvcPath" value="/ejob-bo-edit-offer.jsp" />
				</liferay-portlet:renderURL>

                <!-- Colonne : Publication -->
				<liferay-ui:search-container-column-text cssClass="content-column"
					href="${editOfferURL}" name="publication-id" truncate="true"
					orderable="true" value="${offer.publicationId}" />

                <!-- Colonne : type d'export' -->
               <c:set var="isExported" value=""/>
                <c:choose>
                   <c:when test="${offer.isExported==0}">
                       <c:set var="isExported"><liferay-ui:message key='ejob-not-concerned' /></c:set>
                   </c:when>
                   <c:when test="${offer.isExported==1}">
                       <c:set var="isExported"><liferay-ui:message key='ejob-not-exported' /></c:set>
                   </c:when>
                   <c:otherwise>
                       <c:set var="isExported"><liferay-ui:message key='ejob-exported' /></c:set>
                   </c:otherwise>
                </c:choose>
				<liferay-ui:search-container-column-text cssClass="content-column"
					name="ejob-export-totem" truncate="true"
					orderable="true" value="${isExported}" />

                <!-- Colonne : intitulé du post -->
				<liferay-ui:search-container-column-text cssClass="content-column"
					name="post" truncate="true"
					orderable="true" value="${offer.post}" />

                <!-- Colonne : date de début de publication -->
				<fmt:formatDate value="${offer.publicationStartDate}"
					var="formattedPublicationStartDate" type="date" pattern="dd/MM/yyyy" />
				<liferay-ui:search-container-column-text cssClass="content-column"
					name="publication-date" truncate="true"
					orderable="true" value="${formattedPublicationStartDate}" />

                <!-- Colonne : date de modification -->
				<fmt:formatDate value="${offer.modifiedDate}"
					var="formattedModifiedDate" type="date" pattern="dd/MM/yyyy" />
				<liferay-ui:search-container-column-text cssClass="content-column"
					name="modified-date" truncate="true"
					orderable="true" value="${formattedModifiedDate}" />

                <!-- Colonne : statut -->
				<liferay-ui:search-container-column-text name="status">
					<aui:workflow-status markupView="lexicon" showIcon="false"
						showLabel="false" status="${offer.status}" />
				</liferay-ui:search-container-column-text>

                <!-- ACTIONS -->
				<liferay-ui:search-container-column-text>
					<liferay-ui:icon-menu markupView="lexicon">

						<!-- ACTION : Édition -->
						<c:if test="${dc.hasPermission('EDIT_OFFER') and empty themeDisplay.scopeGroup.getStagingGroup()}">
							<liferay-ui:icon message="edit" url="${editOfferURL}" />
						</c:if>

                        <!-- ACTION : Supprimer -->
						<liferay-portlet:actionURL name="deleteOffer" var="deleteOfferURL">
							<portlet:param name="cmd" value="deleteOffer" />
							<portlet:param name="tab" value="offers" />
							<portlet:param name="offerId" value="${offer.offerId}" />
						</liferay-portlet:actionURL>
						<c:if test="${dc.hasPermission('DELETE_OFFER') and empty themeDisplay.scopeGroup.getStagingGroup()}">
							<liferay-ui:icon message="delete" url="${deleteOfferURL}" />
						</c:if>

					</liferay-ui:icon-menu>
				</liferay-ui:search-container-column-text>

			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator paginate="true" displayStyle="list"
				markupView="lexicon" searchContainer="${dc.searchContainer}" />
		</liferay-ui:search-container>
	</aui:form>
</div>

<c:if test="${dc.hasPermission('ADD_OFFER') and empty themeDisplay.scopeGroup.getStagingGroup()}">
	<liferay-frontend:add-menu>
		<liferay-frontend:add-menu-item title="Ajouter une offre" url="${addOffersURL}" />
	</liferay-frontend:add-menu>
</c:if>