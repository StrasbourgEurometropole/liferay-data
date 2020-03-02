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

    <aui:fieldset collapsed="true" collapsible="true" label="list-establishment">
        <liferay-ui:message key="gmb-establishment" /><br><br>
        <table class="table table-autofit table-heading-nowrap table-list">
            <thead>
                <tr>
                    <th class="lfr-result-column content-column table-cell-content" >
                        <span class="truncate-text"><liferay-ui:message key="name-place" /></span>
                    </th>
                    <th class="lfr-create-date-column content-column table-cell-content" >
                        <span class="truncate-text"><liferay-ui:message key="gmb-location-id" /></span>
                    </th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${dc.locationIds}" var="location">
                    <tr >
                        <td class="content-column table-cell-content lfr-result-column">
                            <span class="truncate-text">${location.key}</span>
                        </td>
                        <td class="content-column table-cell-content lfr-create-date-column">
                            <span class="truncate-text">${location.value}</span>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </aui:fieldset>
</div>