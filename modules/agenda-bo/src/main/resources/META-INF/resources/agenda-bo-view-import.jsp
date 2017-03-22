<%@ include file="/agenda-bo-init.jsp"%>

<liferay-portlet:renderURL varImpl="importURL">
	<portlet:param name="tab" value="import" />
</liferay-portlet:renderURL>

<liferay-portlet:actionURL name="startImport" var="startImportURL">
	<portlet:param name="tab" value="import" />
</liferay-portlet:actionURL>

<div class="container-fluid-1280 main-content-body">	
	<aui:form method="post" name="fm">
		<h2>10 derniers imports</h2>
		<liferay-ui:search-container id="campaignsSearchContainer">
			<liferay-ui:search-container-results results="${reports}" />

			<liferay-ui:search-container-row
				className="eu.strasbourg.service.agenda.model.ImportReport"
				modelVar="report" keyProperty="reportId"
				rowIdProperty="reportId">
				
				<liferay-ui:search-container-column-text name="provider">
					${report.provider}
				</liferay-ui:search-container-column-text>
				
				<fmt:formatDate value="${report.startDate}"
					var="startDate" type="date" pattern="dd/MM/yyyy HH:mm" />
				<liferay-ui:search-container-column-text cssClass="content-column"
					name="start-date" truncate="true" orderable="true"
					value="${startDate}" />
				
				<liferay-ui:search-container-column-text name="status" cssClass="content-column table-cell-content">
					<c:choose>
						<c:when test="${report.status eq 0}">
							<span class="text-danger"><liferay-ui:message key="failure" /></span>
						</c:when>
						<c:when test="${report.status eq 1}">
							<span class="text-success"><liferay-ui:message key="success" /></span>
						</c:when>
						<c:when test="${report.status eq 2}">
							<span class="text-warning"><liferay-ui:message key="success-with-errors" /></span>
						</c:when>
					</c:choose>
				</liferay-ui:search-container-column-text>
				<liferay-ui:search-container-column-text name="additions" cssClass="content-column table-cell-content">
					${report.newEventsCount} <liferay-ui:message key="events" /><br>
					${report.newManifestationsCount} <liferay-ui:message key="manifestations" />
				</liferay-ui:search-container-column-text>
				<liferay-ui:search-container-column-text name="modifications" cssClass="content-column table-cell-content">
					${report.modifiedEventsCount} <liferay-ui:message key="events" /><br>
					${report.modifiedManifestationsCount} <liferay-ui:message key="manifestations" />
				</liferay-ui:search-container-column-text>
				<liferay-ui:search-container-column-text name="errors" cssClass="content-column table-cell-content">
					${report.errorEventsCount} <liferay-ui:message key="events" /><br>
					${report.errorManifestationsCount} <liferay-ui:message key="manifestations" />
				</liferay-ui:search-container-column-text>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator paginate="true" displayStyle="list"
				markupView="lexicon" searchContainer="${dc.searchContainer}" />
		</liferay-ui:search-container>
	</aui:form>
	<c:choose>
		<c:when test="${isAdmin}">
			<label>Source des fichiers : ${empty importPath ? 'configuration manquante' : importPath}</label>
			<aui:button-row>
				<aui:button cssClass="btn-lg" href="${startImportURL}" type="submit"
					value="import" />
			</aui:button-row>
		</c:when>
		<c:otherwise>
			<label>Vous n'avez pas les droits pour effectuer l'import.</label>
		</c:otherwise>
	</c:choose>
</div>