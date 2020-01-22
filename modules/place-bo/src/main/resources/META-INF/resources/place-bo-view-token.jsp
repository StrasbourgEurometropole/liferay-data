<%@ include file="/place-bo-init.jsp"%>

<liferay-portlet:renderURL var="saveRefreshTokenURL">
	<portlet:param name="tab" value="token" />
    <portlet:param name="cmd" value="saveRefreshToken" />
</liferay-portlet:renderURL>

<div class="container-fluid-1280 main-content-body">
	<c:choose>
		<c:when test="${isAdmin}">
			<aui:form action="${saveRefreshTokenURL}" method="post" name="fmRefresh" >
				<aui:fieldset collapsed="true" collapsible="true" label="refresh-token">
					<aui:input type="text" name="refresh-token" label="" value="${dc.refeshToken}" />
				</aui:fieldset>
				<aui:button-row>
					<aui:button cssClass="btn-lg" type="submit" value="save" />
				</aui:button-row>
			</aui:form>
		</c:when>
		<c:otherwise>
			<label><liferay-ui:message key="no-rules" /></label>
		</c:otherwise>
	</c:choose>
</div>