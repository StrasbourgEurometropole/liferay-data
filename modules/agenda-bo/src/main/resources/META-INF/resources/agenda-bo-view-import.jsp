<%@ include file="/agenda-bo-init.jsp"%>

<liferay-portlet:renderURL varImpl="importURL">
	<portlet:param name="tab" value="import" />
</liferay-portlet:renderURL>

<liferay-portlet:actionURL name="startImport" var="startImportURL">
	<portlet:param name="tab" value="import" />
</liferay-portlet:actionURL>

<div class="container-fluid-1280 main-content-body">
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