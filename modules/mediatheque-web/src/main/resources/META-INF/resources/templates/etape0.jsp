<%@ include file="/mediatheque-init.jsp" %>

<section id="mediatheque">
	<c:if test="${dc.showDeleteButton()}">
		<button class="delete-wi" data-portlet-id="${themeDisplay.portletDisplay.id}"></button>
	</c:if>
    <h2><liferay-ui:message key="account-mediatheque" /></h2>
		<!-- Etape 0 - erreur technique -->
		<p>
			${dc.errorText}
		</p>
</section>