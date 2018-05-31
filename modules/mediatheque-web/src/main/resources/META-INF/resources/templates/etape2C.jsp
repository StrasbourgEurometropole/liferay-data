<%@ include file="/mediatheque-init.jsp" %>

<section id="mediatheque">
    <h2><liferay-ui:message key="account-mediatheque" /></h2>
		<!-- Etape 2 attente d'activation -->
		<p align="center">
			<liferay-ui:message key="" />
			<liferay-ui:message key="activate-text-x" arguments="${dc.transformEmail}" />
		</p>
</section>