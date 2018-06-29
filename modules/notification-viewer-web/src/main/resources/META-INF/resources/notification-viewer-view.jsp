<%@ include file="./notification-viewer-init.jsp"%>

<c:set var="notifCount" value="${dc.resultUnreadCount}" />
<div id="nav-notifications">
	<button id="trigger-notifications">
		<div class="notif-flexbox">
			<div class="notif-picto">
				<!-- #dev Si notifications.count != 0 -->
				<c:if test="${notifCount > 0}">
					<div class="notif-amount">
						<c:out value="${notifCount}" />
					</div>
				</c:if>
				<!-- #dev Si notifications.count != 0 -->
			</div>
			<div class="notification-text">Notifications</div>
		</div>
	</button>
	<div class="notif-list">
		<c:set var="nbNotifDisplay" value="0" />
		<c:forEach var="notif" items="${dc.results}">
			<portlet:actionURL name="showNotification" var="notificationURL">
				<portlet:param name="mvcPath" value="/notification-viewer-view.jsp"></portlet:param>
				<portlet:param name="notificationId" value="${notif.notificationId}"></portlet:param>
			</portlet:actionURL>
			<c:choose>
				<c:when test="${notif.isRead()}">
					<a href="${notificationURL}" class="notif-item" id="notif_${notif.notificationId}" style="display: none;">
				</c:when>
				<c:otherwise>
					<a href="${notificationURL} " class="notif-item new" <c:if test='${nbNotifDisplay > 2}'>style="display: none;"</c:if> id="notif_${notif.notificationId}">
					<c:set var="nbNotifDisplay" value="${nbNotifDisplay + 1}" />
				</c:otherwise>
			</c:choose>
				<div class="notif-date">
					<fmt:formatDate type="date" value="${notif.date}" pattern="dd.MM"/>
				</div>
				<div class="notif-text" data-dot="2">
					<p style="word-break: break-word"><c:out value="${notif.title}" /></p>
				</div>
			</a>
		</c:forEach>
		<div class="notif-item" id="no-notif-item" <c:if test="${notifCount > 0}"> style="display: none;" </c:if> >
			<div class="notif-text no-notif" data-dot="2"><liferay-ui:message key="no-notification-unread" /></div>
		</div>
		<div class="notif-last">
			<a href="${showAllURL}" class="btn-square--filled--core"><span
				class="flexbox"><span class="btn-text"><liferay-ui:message key="see-all" /></span><span
					class="btn-arrow"></span></span></a>
		</div>
	</div>
</div>
