<%@ include file="/place-bo-init.jsp"%>

<liferay-portlet:renderURL var="saveRefreshTokenURL">
	<portlet:param name="tab" value="token" />
    <portlet:param name="cmd" value="saveRefreshToken" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="updateAccesTokenURL">
	<portlet:param name="tab" value="token" />
    <portlet:param name="cmd" value="updateAccesToken" />
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

			<aui:form action="${updateAccesTokenURL}" method="post" name="fmAcces" >
				<aui:fieldset collapsed="true" collapsible="true" label="access-token">
				    <c:if test="${not empty dc.lastAccessToken}">
                        ${dc.lastAccessToken.extraInfo}<br>
                        <fmt:formatDate value="${dc.lastAccessToken.createDate}"
                            var="formattedDate" type="date" pattern="dd/MM/yyyy" />
                        <strong><liferay-ui:message key="creation-date" /></strong> ${formattedDate}
                    </c:if>
				    <c:if test="${empty dc.lastAccessToken}">
				        <liferay-ui:message key="no-access-token" />
                    </c:if>
				</aui:fieldset>
				<aui:button-row>
					<aui:button cssClass="btn-lg" type="submit" value="update" />
				</aui:button-row>
			</aui:form>
		</c:when>
		<c:otherwise>
			<label><liferay-ui:message key="no-rules" /></label>
		</c:otherwise>
	</c:choose>
</div>