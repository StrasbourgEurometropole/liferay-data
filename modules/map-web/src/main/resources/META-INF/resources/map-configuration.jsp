<%@ include file="./map-init.jsp"%>

<liferay-portlet:actionURL portletConfiguration="true"
	var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="true"
	var="configurationRenderURL" />

<div class="container-fluid-1280 main-content-body">
	<aui:form action="${configurationActionURL}" method="post" name="fm">

		<aui:input name="cmd" type="hidden" value="update" />


		<aui:select name="groupId" label="detail-target-site">
			<c:forEach var="site" items="${sites}">
				<aui:option value="${site.groupId}" selected="${site.groupId eq selectedGroupId}">${site.name}</aui:option>
			</c:forEach>
		</aui:select>


		<!-- Choix de l'ouverture d'un lien  -->
		<div>
			<liferay-ui:message key="redirection-link-choice" />
			<aui:input type="checkbox" name="openInNewTab"
				value="${openInNewTab}" label="new-tab" />
		</div>

		<aui:button-row>
			<aui:button type="submit"></aui:button>
		</aui:button-row>
	</aui:form>
</div>