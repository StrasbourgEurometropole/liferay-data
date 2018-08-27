<%@ include file="/validation-address-init.jsp" %>
<liferay-portlet:actionURL var="recordURL" name="record" />
<c:set var="error" value="${dc.hasError()}" />
<c:if test="${error}">
	<section id="validation-address">
		<!-- erreur technique -->
		<div class="error"><liferay-ui:message key="eu.webservice-indispo" /></div>
	</section>
</c:if>

<c:if test="${!error}">
	<c:set var="hasAddress" value="${dc.hasAddress()}" />
	<c:if test="${!hasAddress}">
		<section id="validation-address">
		    <div class="error"><center><liferay-ui:message key="no-address" /></center></div>
			<!-- affichage du formulaire de recherche d'adresse -->
			<liferay-util:include page="/includes/search-address.jsp" servletContext="<%=application %>" />
		</section>
	</c:if>

	<c:if test="${hasAddress}">
		<c:set var="streets" value="${dc.addressList}" />

		<c:if test="${streets != ''}">
            <c:if test="${streets.size() == 0}">
                <section id="validation-address">
                    <div class="error"><center><liferay-ui:message key="no-choice" /></center></div>
                    <!-- affichage du formulaire de recherche d'adresse -->
                    <liferay-util:include page="/includes/search-address.jsp" servletContext="<%=application %>" />
                </section>
            </c:if>
            <c:if test="${streets.size() > 0}">
			<c:set var="nbStreet" value="0" />
                <section id="validation-address">
                    <div class="error"><center><liferay-ui:message key="choice" /></center></div>
                    <!-- affichage du choix d'adresse -->
                    <div class="list-address">
                        <aui:form name="fm" action="${recordURL}">
                            <aui:input type="hidden" name="address" value="" />
                            <aui:input type="hidden" name="zipCode" value="" />
                            <aui:input type="hidden" name="city" value="" />
                            <aui:input type="hidden" name="lastName" value="${dc.lastName}" />
                            <c:forEach var="street" items="${streets}" varStatus="status">
                                <c:if test="${nbStreet lt 4 and not empty street.houseNumber}">
                                    <c:set var="nbStreet" value="${nbStreet + 1}" />
                                    <label>
                                        <input type="radio" id="address" name="address" value="${street.name},${street.zipCode},${street.city}"  />
                                        <span>
                                            ${street.name}<br>
                                            ${street.zipCode} ${street.city}
                                        </span>
                                    </label>
                                </c:if>
                            </c:forEach>
                            <label>
                                <input type="radio" id="address" name="address" value="other"  />
                                <span>
                                    <liferay-ui:message key="other" />
                                </span>
                            </label>
                            <!-- Validation -->
                            <div id="validation" class="widget" style="display: none; text-align: center;">
                                <button type="submit" class="btn-square filled core">
                                    <span class="flexbox">
                                        <span class="btn-text"><liferay-ui:message key="record-address" /></span>
                                        <span class="btn-arrow"></span>
                                    </span>
                                </button>
                            </div>
                        </aui:form>
                        <!-- affichage du formulaire de recherche d'adresse -->
                        <div id="other-address" style="display: none;">
                            <liferay-util:include page="/includes/search-address.jsp" servletContext="<%=application %>" />
                        </div>
                    </div>
                </section>
		    </c:if>
		</c:if>
	</c:if>
</c:if>

<liferay-util:html-bottom>
	<aui:script>
		define._amd = define.amd;
		define.amd = false;
	</aui:script>
	<script	src="/o/agendabo/js/vendors/jquery.autocomplete.js"></script>
	<script>
		define.amd = define._amd;
		window.forcedCity = '';
	</script>
	<script
		src="/o/validation-address/js/validation-address.js"
		type="text/javascript"></script>

	<style>
		.alert-success {
			display: none;
		}
	</style>
</liferay-util:html-bottom>
