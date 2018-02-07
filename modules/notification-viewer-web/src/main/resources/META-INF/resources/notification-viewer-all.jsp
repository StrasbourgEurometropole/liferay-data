<%@ include file="./notification-viewer-init.jsp"%>

<div class="card-box">
	<h1>Mes notifications</h1>
	<ul class="notification-list">

		<c:forEach var="notif" items="${notifications}">
			<c:choose>
				<c:when test="${notif.isRead()}">
					<li class="notification-list__item">
				</c:when>
				<c:otherwise>
					<li class="notification-list__item notification-list__item--read">
				</c:otherwise>
			</c:choose>

			<div class="notification-item">
				<div class="notification-item__date">
					<fmt:formatDate type="date" value="${notif.date}" pattern = "dd.MM.yyyy"/>
				</div>
				<div class="notification-item__lead">
					<c:out value="${notif.title}" />
				</div>
			</div>

			<portlet:resourceURL id="toggleNotification" var="notificationURL">
				<portlet:param name="notificationId" value="${notif.notificationId}" />
			</portlet:resourceURL>

			<div class="notification-list__toggle">
				<div class="flexbox">
					<div class="notification-list__toggle-trigger">
						<input type="checkbox" id="${notif.notificationId}"
							onclick="callServeResource('${notificationURL}');"> <label
							for="${notif.notificationId}"></label>
					</div>
					<div class="notification-list__state notification-list__state--new">Non
						lue</div>
					<div
						class="notification-list__state notification-list__state--read">Lue</div>
				</div>
			</div>
			</li>
		</c:forEach>

	</ul>
</div>

<aui:script>
function callServeResource(notificationURL){
    AUI().use('aui-io-request', function(A){
        A.io.request(notificationURL, {
               method: 'post'
        });
     });
}
</aui:script>
