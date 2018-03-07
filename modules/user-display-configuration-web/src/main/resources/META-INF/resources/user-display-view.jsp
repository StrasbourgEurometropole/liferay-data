<%@ include file="./user-display-init.jsp"%>

<h1>
	<liferay-ui:message key="title-message" />
</h1>

<aui:form method="POST" action="#" name="portletForm" id="portletForm">
	<ul class="notification-list">

		<c:forEach var="portlet" items="${portlets}" varStatus="intPortlet">

			<c:choose>
				<c:when test="${fn:contains(hiddenPortlets, portlet)}">
					<li class="notification-list__item notification-list__item--read">
				</c:when>
				<c:otherwise>
					<li class="notification-list__item">
				</c:otherwise>
			</c:choose>

			<div class="notification-item">
				<div class="notification-item__date">
					<liferay-ui:message key="${portlet}" />
				</div>
				<div class="notification-item__lead">
					<liferay-ui:message key="${portlet}-description" />
				</div>
			</div>
			
			<portlet:resourceURL id="togglePortlet" var="portletURL">
				<portlet:param name="portletName" value="${portlet}" />
			</portlet:resourceURL>
			
			<div class="notification-list__toggle">
				<div class="flexbox">
					<div class="notification-list__toggle-trigger">
						<input type="checkbox" name="portletName_${intPortlet.index}"
							value="${portlet}"
							id="portletId_${intPortlet.index}" onclick="callServeResource('${portletURL}');"/>
						<label for="portletId_${intPortlet.index}"></label>
					</div>
					<div
						class="notification-list__state notification-list__state--read">
						<liferay-ui:message key="not-displayed" />
					</div>
					<div class="notification-list__state notification-list__state--new">
						<liferay-ui:message key="displayed" />
					</div>
				</div>
			</div>
			</li>
		</c:forEach>
	</ul>
</aui:form>

<aui:script>
	function callServeResource(portletURL) {
		AUI().use('aui-io-request', function(A) {
			A.io.request(portletURL, {
				method : 'post'
			});
		});
	}
</aui:script>