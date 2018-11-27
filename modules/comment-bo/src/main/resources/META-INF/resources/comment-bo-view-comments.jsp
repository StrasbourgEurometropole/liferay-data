<%@ include file="/comment-bo-init.jsp"%>

<%-- URL : definit le lien avec les parametres de recherche des entites--%>
<liferay-portlet:renderURL varImpl="commentsURL">
	<portlet:param name="tab" value="comments" />
	<portlet:param name="orderByCol" value="${dc.orderByCol}" />
	<portlet:param name="orderByType" value="${dc.orderByType}" />
	<portlet:param name="filterCategoriesIds" value="${dc.filterCategoriesIds}" />
	<portlet:param name="keywords" value="${dc.keywords}" />
	<portlet:param name="delta" value="${dc.searchContainer.delta}" />
</liferay-portlet:renderURL>

<%-- URL : definit le lien vers la page d'ajout/edition d'une entite --%>
<liferay-portlet:renderURL varImpl="addCommentsURL">
	<portlet:param name="cmd" value="editComments" />
	<portlet:param name="mvcPath" value="/comment-bo-edit-comment.jsp" />
	<portlet:param name="returnURL" value="${commentsURL}" />
</liferay-portlet:renderURL>

<%-- Composant : barre de filtres et de gestion des entite --%>
<liferay-frontend:management-bar includeCheckBox="true" searchContainerId="commentsSearchContainer">

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
				orderColumns='<%= new String[] {"userName", "modified-date","status","reportings","entityType","entityName"} %>'
				portletURL="${commentsURL}" />
		</liferay-frontend:management-bar-filters>

		<%-- Composant : partie gestion (affichee apres une selection) --%>
		<liferay-frontend:management-bar-action-buttons>
			<c:if test="${not dc.workflowEnabled}">
				<c:if test="${dc.hasPermission('EDIT_COMMENT') and empty themeDisplay.scopeGroup.getStagingGroup()}">
					<liferay-frontend:management-bar-button
						href='<%="javascript:" + renderResponse.getNamespace() + "publishSelection();"%>'
						icon="check" label="publish" />
					<liferay-frontend:management-bar-button
						href='<%="javascript:" + renderResponse.getNamespace() + "unpublishSelection();"%>'
						icon="times" label="unpublish" />
				</c:if>
			</c:if>
			<c:if test="${dc.hasPermission('DELETE_COMMENT') and empty themeDisplay.scopeGroup.getStagingGroup()}">
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
		<liferay-ui:search-container id="commentsSearchContainer"
			searchContainer="${dc.searchContainer}">
			<liferay-ui:search-container-results results="${dc.comments}" />

			<liferay-ui:search-container-row
				className="eu.strasbourg.service.comment.model.Comment" modelVar="comment"
				keyProperty="commentId" rowIdProperty="commentId">
				
				<%-- URL : definit le lien vers la page d'edition de l'entite selectionnee --%>
				<liferay-portlet:renderURL varImpl="editCommentURL">
					<portlet:param name="cmd" value="editComment" />
					<portlet:param name="commentId" value="${comment.commentId}" />
					<portlet:param name="returnURL" value="${commentsURL}" />
					<portlet:param name="mvcPath" value="/comment-bo-edit-comment.jsp" />
				</liferay-portlet:renderURL>

				<%-- Colonne : userName --%>
				<liferay-ui:search-container-column-text cssClass="content-column"
					href="${editCommentURL}" name="userName" truncate="true" orderable="true"
					value="${comment.userName}" />

				<liferay-ui:search-container-column-text cssClass="content-column"
														 href="${editCommentURL}" name="comment" truncate="true" orderable="true"
														 value="${comment.comment}" />
				
				<%-- Colonne : Date de modification --%>
				<fmt:formatDate value="${comment.modifiedDate}"
					var="formattedModifiedDate" type="date" pattern="dd/MM/yyyy HH:mm" />
				<liferay-ui:search-container-column-text cssClass="content-column"
					name="modified-date" truncate="true" orderable="true"
					value="${formattedModifiedDate}" />

				<%-- Colonne : Statut --%>
				<liferay-ui:search-container-column-text name="status" orderable="true">
					<aui:workflow-status markupView="lexicon" showIcon="false"
						showLabel="false" status="${comment.status}" />
				</liferay-ui:search-container-column-text>

				<%-- Colonne : le nombre de signalement--%>
				<liferay-ui:search-container-column-text cssClass="content-column"
                    name="reportings" truncate="true" orderable="true" align="center"
                    value="${comment.getCountSignalements()}" />

				<%-- Colonne : Type de l'entite --%>
				<liferay-ui:search-container-column-text cssClass="content-column"
                    name="entityType" truncate="true" orderable="true"
                    value="${comment.getTypeAssetEntry()}" />

				<%-- Colonne : nom de l'entite--%>
				<liferay-ui:search-container-column-text cssClass="content-column"
                    name="entityName" truncate="true" orderable="true"
                    value="${comment.getAssetEntryTitle()}" />

				<%-- Colonne : lien vers la page--%>
				<liferay-ui:search-container-column-text cssClass="content-column" name="linkTitle" >
				    <aui:button href="${comment.urlProjectCommentaire}" value="link"/>
				</liferay-ui:search-container-column-text>
			</liferay-ui:search-container-row>

			<%-- Iterateur --%>
			<liferay-ui:search-iterator paginate="true" displayStyle="list"
				markupView="lexicon" searchContainer="${dc.searchContainer}" />
		</liferay-ui:search-container>
	</aui:form>

	<liferay-portlet:resourceURL var="exportCommentsXlsxURL" id="exportCommentsXlsx">
	</liferay-portlet:resourceURL>
	<form method="POST" action="${exportCommentsXlsxURL}">
		<aui:input type="hidden" name="commentIds" value="${dc.allCommentIds}" />
		<aui:button-row>
			<aui:button cssClass="btn-lg" type="submit"
				value="export-comments-xlsx" />
		</aui:button-row>
	</form>
</div>

<%-- URL : definit le lien vers l'action de suppression --%>
<liferay-portlet:actionURL name="selectionAction" var="deleteSelectionURL">
	<portlet:param name="cmd" value="delete" />
	<portlet:param name="tab" value="comments" />
	<portlet:param name="orderByCol" value="${dc.orderByCol}" />
	<portlet:param name="orderByType" value="${dc.orderByType}" />
	<portlet:param name="filterCategoriesIds" value="${dc.filterCategoriesIds}" />
	<portlet:param name="keywords" value="${dc.keywords}" />
	<portlet:param name="delta" value="${dc.searchContainer.delta}" />
</liferay-portlet:actionURL>

<%-- URL : definit le lien vers l'action de publication --%>
<liferay-portlet:actionURL name="selectionAction" var="publishSelectionURL">
	<portlet:param name="cmd" value="publish" />
	<portlet:param name="tab" value="comments" />
	<portlet:param name="orderByCol" value="${dc.orderByCol}" />
	<portlet:param name="orderByType" value="${dc.orderByType}" />
	<portlet:param name="filterCategoriesIds" value="${dc.filterCategoriesIds}" />
	<portlet:param name="keywords" value="${dc.keywords}" />
	<portlet:param name="delta" value="${dc.searchContainer.delta}" />
</liferay-portlet:actionURL>

<%-- URL : definit le lien vers l'action de depublication --%>
<liferay-portlet:actionURL name="selectionAction" var="unpublishSelectionURL">
	<portlet:param name="cmd" value="unpublish" />
	<portlet:param name="tab" value="comments" />
	<portlet:param name="orderByType" value="${dc.orderByType}" />
	<portlet:param name="filterCategoriesIds" value="${dc.filterCategoriesIds}" />
	<portlet:param name="keywords" value="${dc.keywords}" />
	<portlet:param name="delta" value="${dc.searchContainer.delta}" />
</liferay-portlet:actionURL>

<%-- Script : permet l'affichage des alertes de validation d'action --%>
<aui:script>
	function <portlet:namespace />deleteSelection() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-selected-entries" />')) {
			var form = AUI.$(document.<portlet:namespace />fm);
			var selectionIdsInput = document
					.getElementsByName('<portlet:namespace />selectionIds')[0];
			selectionIdsInput.value = Liferay.Util.listCheckedExcept(form,
					'<portlet:namespace />allRowIds');

			submitForm(form, '${deleteSelectionURL}');
		}
	}
	function <portlet:namespace />publishSelection() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-publish-selected-entries" />')) {
			var form = AUI.$(document.<portlet:namespace />fm);
			var selectionIdsInput = document
					.getElementsByName('<portlet:namespace />selectionIds')[0];
			selectionIdsInput.value = Liferay.Util.listCheckedExcept(form,
					'<portlet:namespace />allRowIds');

			submitForm(form, '${publishSelectionURL}');
		}
	}
	function <portlet:namespace />unpublishSelection() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-unpublish-selected-entries" />')) {
			var form = AUI.$(document.<portlet:namespace />fm);
			var selectionIdsInput = document
					.getElementsByName('<portlet:namespace />selectionIds')[0];
			selectionIdsInput.value = Liferay.Util.listCheckedExcept(form,
					'<portlet:namespace />allRowIds');

			submitForm(form, '${unpublishSelectionURL}');
		}
	}

	function <portlet:namespace/>formatComment(commentaire){
		return (comment.length()<20)?comment: comment.substring(0, 15) + "...";
	}
</aui:script>
