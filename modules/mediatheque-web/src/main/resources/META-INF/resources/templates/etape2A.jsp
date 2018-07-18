<%@ include file="/mediatheque-init.jsp" %>

<section id="mediatheque">
	<c:if test="${dc.showDeleteButton()}">
		<button class="delete-wi" data-portlet-id="${themeDisplay.portletDisplay.id}"></button>
	</c:if>
    <h2><liferay-ui:message key="account-mediatheque" /></h2>
		<!-- Etape 2 carte dÃ©jÃ  associÃ©e Ã  un compte -->
		<p align="center">
			<liferay-ui:message key="associate-text" /><br>
			<a href="${dc.contactURL}" target="_blank" title="<liferay-ui:message key="contact-mediatheque" /> (<liferay-ui:message key="eu.new-window" />)">
				<liferay-ui:message key="contact-mediatheque" />
			</a>
		</p>
</section>