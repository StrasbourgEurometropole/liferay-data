<%@ include file="./user-display-init.jsp"%>


<h1>
	<liferay-ui:message key="title-message" />
</h1>
<ul class="notification-list">


	<li class="notification-list__item notification-list__item--read">


		<div class="notification-item">
			<div class="notification-item__lead">
				<c:out value="Test" />
			</div>
		</div>

		<div class="notification-list__toggle">
			<div class="flexbox">
				<div class="notification-list__toggle-trigger">
					<input type="checkbox" id="testId"> <label for="testId"></label>
				</div>
				<div class="notification-list__state notification-list__state--new">Non
					lue</div>
				<div class="notification-list__state notification-list__state--read">Lue</div>
			</div>
		</div>
	</li>
</ul>

<aui:script>

</aui:script>