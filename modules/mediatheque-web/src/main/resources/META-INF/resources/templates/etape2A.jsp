<%@ include file="/mediatheque-init.jsp" %>

<section id="mediatheque">
    <h2>${title}</h2>
		<!-- Etape 2 carte déjà associée à un compte -->
		<p align="center">
			<liferay-ui:message key="associate-text" /><br>
			<a href="${dc.contactURL}" target="_blank" title="<liferay-ui:message key="contact-mediatheque" /> (<liferay-ui:message key="eu.new-window" />)">
				<liferay-ui:message key="contact-mediatheque" />
			</a>
		</p>
</section>