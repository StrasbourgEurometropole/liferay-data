<%@ include file="/resid-init.jsp" %>

<section id="resid">
    <h2>${title}</h2>
	<!-- Etape 0 : webService indispo -->
	<div class="error"><liferay-ui:message key="eu.webservice-indispo" /></div>
	
    <div class="form-group">
		<div class="content" align="center">
			<a href="${dc.residURL}" target="_blank" class="btn-square--bordered--core" title="<liferay-ui:message key="resid-site"/>(<liferay-ui:message key="eu.new-window"/>)">
				<span class="flexbox">
					<span class="btn-text">
						<liferay-ui:message key="resid-site"/>
					</span>
					<span class="btn-arrow"></span>
				</span>
			</a>
		</div>
	</div>
</section>


