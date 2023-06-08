<%@ include file="./user-display-init.jsp"%>

<h1>
	<liferay-ui:message key="title-message" />
</h1>
<p class="chapo-customize"><liferay-ui:message key="chapo-message" /></p>

<aui:form method="POST" action="#" name="portletForm" id="portletForm">
	<ul class="customize-list">

		<c:forEach var="portletId" items="${dc.portletIds}" varStatus="loopStatus">

			<c:set var="displayStatus" value="${dc.getPortletDisplayStatus(portletId)}" />
			<c:if test="${displayStatus ne 'on_hidden'}">
				<c:set var="displayDescription" value="${dc.getPortletDisplayDescription(portletId)}" />
				<c:set var="displayTitle" value="${dc.getPortletDisplayTitle(portletId)}" />
				<c:set var="hiddenByUser" value="${dc.isPortletHiddenByUser(portletId)}" />
				<c:choose>
					<c:when test="${not hiddenByUser}">
						<li class="customize-list__item">
					</c:when>
					<c:otherwise>
						<li class="customize-list__item customize-list__item--read">
					</c:otherwise>
				</c:choose>

				<div class="customize-item">
					<div class="customize-item__date">
						${displayTitle}
					</div>
					<div class="customize-item__lead">
						${displayDescription}
					</div>
				</div>

				<portlet:resourceURL id="showPortlet" var="showPortletURL">
					<portlet:param name="portletId" value="${portletId}" />
				</portlet:resourceURL>
				<portlet:resourceURL id="hidePortlet" var="hidePortletURL">
					<portlet:param name="portletId" value="${portletId}" />
				</portlet:resourceURL>

				<div class="customize-list__toggle">
					<div class="flexbox">
						<div class="customize-list__toggle-trigger"
							<c:if test="${displayStatus eq 'on_disabled'}">
								style="opacity: 0.5"
							</c:if>
						>
							<input type="checkbox" name="${portletId}Toggle" data-show-url="${showPortletURL}"
								   data-hide-url="${hidePortletURL}" value="${portletId}"
									<c:if test="${displayStatus eq 'on_disabled'}">disabled</c:if>
								   id="portletId_${loopStatus.index}" />
							<label for="portletId_${loopStatus.index}"></label>
						</div>
						<div
								class="customize-list__state customize-list__state--read">
							<liferay-ui:message key="not-displayed" />
						</div>
						<div class="customize-list__state customize-list__state--new">
							<liferay-ui:message key="displayed" />
						</div>
					</div>
				</div>
				</li>
			</c:if>



		</c:forEach>
	</ul>
</aui:form>
