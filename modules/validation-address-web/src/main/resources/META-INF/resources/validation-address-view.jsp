<%@ include file="/validation-address-init.jsp" %>
<liferay-portlet:actionURL var="recordURL" name="record" />
   
<c:set var="address" value="${dc.address}" />
<c:if test="${empty address}">
	<section id="validation-address">
   		<h2><liferay-ui:message key="validation-address" /></h2>
		<!-- affichage du formulaire de recherche d'adresse -->
		<liferay-util:include page="/includes/search-address.jsp" servletContext="<%=application %>" />
	</section>
</c:if>
	
<c:if test="${not empty address and dc.isEMSZipCode()}">
	<c:set var="streets" value="${dc.addressList}" />
	<!-- recherche de l'adresse dans adict --> 
	<c:if test="${empty streets}">
		<section id="validation-address">
   			<h2><liferay-ui:message key="validation-address" /></h2>
			<!-- affichage du formulaire de recherche d'adresse -->
			<liferay-util:include page="/includes/search-address.jsp" servletContext="<%=application %>" />
		</section>
	</c:if>
	<c:if test="${not empty streets and streets.get(0).score <= 0.9 }">
		<section id="validation-address">
   			<h2><liferay-ui:message key="validation-address" /></h2>
			<!-- affichage du choix d'adresse --> 
			<p><liferay-ui:message key="choice-address-text" /></p>
			<c:forEach var="street" items="${streets}" varStatus="status">
				<c:if test="${status.count lt 4}">
					<aui:form name="fm" action="${recordURL}">
						<aui:input type="hidden" name="address" value="${street.name}" />
						<aui:input type="hidden" name="zipCode" value="${street.zipCode}" />
						<aui:input type="hidden" name="city" value="${street.city}" />
					    <div class="webform-layout-box">
					        <div class="form-group">
								<div class="title">
								</div>
								<div class="content">
									<p>
										${street.name}<br>
										${street.zipCode} ${street.city}
									</p>
								</div>
					        </div>
					        <div class="form-group">
								<div class="title">
								</div>
								<div class="content">
									<!-- Validation -->
									<div class="widget">
										<button type="submit" class="btn-square filled core">
											<span class="flexbox">
												<span class="btn-text"><liferay-ui:message key="record-address" /></span>
												<span class="btn-arrow"></span>
											</span>
										</button>
									</div>
								</div>
							</div>
						</div>
					</aui:form>
				</c:if>
			</c:forEach>
			
			<p><liferay-ui:message key="other-address-text" /></p>
			<liferay-util:include page="/includes/search-address.jsp" servletContext="<%=application %>" />
		</section>
	</c:if>
</c:if>