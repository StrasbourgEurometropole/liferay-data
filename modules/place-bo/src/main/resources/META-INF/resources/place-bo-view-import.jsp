<%@ include file="/place-bo-init.jsp"%>

<liferay-portlet:renderURL varImpl="importURL">
	<portlet:param name="tab" value="import" />
</liferay-portlet:renderURL>

<liferay-portlet:actionURL name="startImportPlaces" var="startImportPlacesURL">
	<portlet:param name="tab" value="import" />
</liferay-portlet:actionURL>

<liferay-portlet:actionURL name="startImportCategories" var="startImportCategoriesURL">
	<portlet:param name="tab" value="import" />
</liferay-portlet:actionURL>

<liferay-portlet:actionURL name="startImportTerritories" var="startImportTerritoriesURL">
	<portlet:param name="tab" value="import" />
</liferay-portlet:actionURL>

<div class="container-fluid-1280 main-content-body">
	<c:choose>
		<c:when test="${isAdmin}">
			<aui:form action="${startImportPlacesURL}" method="post" name="fmPlaces" enctype="multipart/form-data" >
				<aui:fieldset collapsed="true" collapsible="true"
					label="import-places">
					<aui:input type="file" name="places-file">
						<aui:validator name="acceptFiles">'csv'</aui:validator>
					</aui:input>
				</aui:fieldset>
				<aui:button-row>
					<aui:button cssClass="btn-lg" type="submit"
						value="import" />
				</aui:button-row>
			</aui:form>
			
			<aui:form action="${startImportCategoriesURL}" method="post" name="fmCategories" enctype="multipart/form-data" >
				<aui:fieldset collapsed="true" collapsible="true"
					label="import-categories">
					<aui:input type="file" name="categories-file">
						<aui:validator name="acceptFiles">'csv'</aui:validator>
					</aui:input>
				</aui:fieldset>
				<aui:button-row>
					<aui:button cssClass="btn-lg" type="submit"
						value="import" />
				</aui:button-row>
			</aui:form>
			
			<aui:form action="${startImportTerritoriesURL}" method="post" name="fmTerritories" enctype="multipart/form-data" >
				<aui:fieldset collapsed="true" collapsible="true"
					label="import-territories">
					<aui:input type="file" name="territories-file">
						<aui:validator name="acceptFiles">'csv'</aui:validator>
					</aui:input>
				</aui:fieldset>
				<aui:button-row>
					<aui:button cssClass="btn-lg" type="submit"
						value="import" />
				</aui:button-row>
			</aui:form>
		</c:when>
		<c:otherwise>
			<label>Vous n'avez pas les droits pour effectuer l'import.</label>
		</c:otherwise>
	</c:choose>
</div>