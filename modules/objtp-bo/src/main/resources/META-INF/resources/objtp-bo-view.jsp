<%@ include file="/objtp-bo-init.jsp" %>

<liferay-portlet:renderURL varImpl="importURL">
</liferay-portlet:renderURL>

<liferay-portlet:actionURL name="startImportObjtp" var="startImportObjtpURL">
</liferay-portlet:actionURL>

<aui:form action="${startImportObjtpURL}" method="post" name="fmTerritories" enctype="multipart/form-data" >
	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit"
			value="Import" />
	</aui:button-row>
</aui:form>