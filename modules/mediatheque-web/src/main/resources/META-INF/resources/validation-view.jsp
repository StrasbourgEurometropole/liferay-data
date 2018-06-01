<%@ include file="/mediatheque-init.jsp" %>
<c:if test="${empty virtualHostName}">
    <c:set var="homeURL" value="${themeDisplay.getURLHome()}/../mon-strasbourg"/>
</c:if>
<c:if test="${not empty virtualHostName}">
    <c:set var="homeURL" value="https://${virtualHostName}"/>
</c:if>

<section id="validation">

   <div align="center">
		<c:if test="${liaison == 'ok'}">
			<p>
				<c:if test="${isLoggedIn}">
					<liferay-ui:message key="liaison-ok" arguments="<liferay-ui:message key='btn-continue'/>" />
				</c:if>
				<c:if test="${!isLoggedIn}">
					<liferay-ui:message key="liaison-ok" arguments="<liferay-ui:message key='btn-connect'/>"/>
				</c:if>
			</p>
		</c:if>
   
		<c:if test="${liaison == 'already-done'}">
			<p>
				<c:if test="${isLoggedIn}">
					<liferay-ui:message key="liaison-existante" arguments="<liferay-ui:message key='btn-continue'/>"/>
				</c:if>
				<c:if test="${!isLoggedIn}">
					<liferay-ui:message key="liaison-existante" arguments="<liferay-ui:message key='btn-connect'/>"/>
				</c:if>
			</p>
		</c:if>
   
		<c:if test="${liaison == 'time-excedeed'}">
			<p>
				<liferay-ui:message key="delais-depasse" />
			</p>
		</c:if>
   
		<c:if test="${liaison == 'ko'}">
			<!-- Message d'erreur -->
			<p><liferay-ui:message key="error-text" /></p>
		</c:if>
			
		<c:if test="${!isLoggedIn || (liaison != 'ko' && liaison != 'time-excedeed')}">
			<br>
	        <div class="form-group">
				<div class="content" align="center">
					<c:if test="${isLoggedIn}">
						<a href="${homeURL}" class="btn-square--bordered--core">
					</c:if>
					<c:if test="${!isLoggedIn}">
						<a href="${LayoutHelper.getPublikLoginURL(homeURL)}" class="btn-square--bordered--core">
					</c:if>
					<span class="flexbox">
						<span class="btn-text">
							<c:if test="${isLoggedIn}">
								<liferay-ui:message key="btn-continue"/>
							</c:if>
							<c:if test="${!isLoggedIn}">
									<liferay-ui:message key="btn-connect"/>
								</c:if>
							</span>
							<span class="btn-arrow"></span>
						</span>
					</a>
				</div>
			</div>
		</c:if>
	</div>
	    
</section>