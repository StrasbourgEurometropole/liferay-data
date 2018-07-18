<%@ include file="/mediatheque-init.jsp" %>

<section id="mediatheque">
    <h2>${title}</h2>
	<!-- Etape 0 - erreur technique -->
	<div class="error">
		<c:if test="${not empty error}">
			${error}
		</c:if>
	
		<c:if test="${empty error}">
			${dc.errorText}
		</c:if>
	</div>
	
	<div align="center">
		<a href="${dc.mediathequeURL}" class="btn-square--bordered--core" target="_blank" title="<liferay-ui:message key="site-mediatheques" /> (<liferay-ui:message key="eu.new-window" />)">
			<span class="flexbox">
				<span class="btn-text"><liferay-ui:message key="site-mediatheques" /></span>
				<span class="btn-arrow"></span>
			</span>
		</a>
	</div>
</section>