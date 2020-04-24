<%@ include file="../init.jsp"%>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="true" var="configurationRenderURL" />

<div class="container-fluid-1280 main-content-body">
	<aui:form action="${configurationActionURL}" method="post" name="fm">

		<aui:input name="cmd" type="hidden" value="update" />

		<aui:input name="redirect" type="hidden" value="${configurationRenderURL}" />

		<aui:fieldset>
			<!-- Mode d'affichage -->
			<aui:select name="useSkypeView">
				<aui:option value="true" selected="${useSkypeView eq 'true'}">
					<liferay-ui:message key="council.configuration.true" />
				</aui:option>
				<aui:option value="false" selected="${useSkypeView eq 'false'}">
					<liferay-ui:message key="council.configuration.false" />
				</aui:option>
			</aui:select>
		</aui:fieldset>

		<aui:button-row>
			<aui:button type="submit"></aui:button>
		</aui:button-row>

	</aui:form>
</div>