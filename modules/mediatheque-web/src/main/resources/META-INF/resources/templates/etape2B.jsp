<%@ include file="/mediatheque-init.jsp" %>

<section id="mediatheque">
    <h2><liferay-ui:message key="account-mediatheque" /></h2>
		<!-- Etape 2 Pas d'email renseignÃÂ© -->
		<p align="center">
			<liferay-ui:message key="email-text" />
		</p>
	
		<div align="center">
			<a href="${dc.mediathequeURL}" class="btn-square--bordered--core" target="_blank" title="<liferay-ui:message key="site-mediatheques" /> (<liferay-ui:message key="eu.new-window" />)">
				<span class="flexbox">
					<span class="btn-text"><liferay-ui:message key="site-mediatheques" /></span>
					<span class="btn-arrow"></span>
				</span>
			</a>
		</div>
</section>