<%@ include file="/zone-init.jsp" %>

<div class="seu-container seu-view-places" style="margin-bottom: 50px;">
	<div>${dc.text}</div>
	<liferay-portlet:actionURL var="searchURL" name="searchSector">
	</liferay-portlet:actionURL>
	<aui:form name="fm" action="${searchURL}" cssClass="seu-view-filters">
		<div class="seu-filter-line" style="align-items: flex-end;">
			<!-- Adresse -->
			<div class="widget widget-big" style="width: 100%;">
				<div class="title">
					<label for="query"><liferay-ui:message key="address" /></label>
				</div>
				<div class="content">
					<input type="text" id="query" name="query"
						placeholder="<liferay-ui:message key="address" />" value="${not empty streetName ? streetName : myQuery}">
				</div>
			</div>
			<!-- Validation -->
			<div class="widget">
				<button type="submit" class="seu-btn-square seu-filled seu-core">
					<span class="seu-flexbox">
						<span class="seu-btn-text"><liferay-ui:message key="search" /></span>
						<span class="seu-btn-arrow"></span>
					</span>
				</button>
			</div>
		</div>
	</aui:form>
	
	<!-- Erreurs -->
	<div class="seu-error-messages">
	   	<liferay-ui:error key="address-required" message="address-required" targetNode=".seu-error-messages" />
	   	<liferay-ui:error key="no-result" message="eu.no-result" targetNode=".seu-error-messages" />
	   	<liferay-ui:error key="too-many-results" message="eu.too-many-results" targetNode=".seu-error-messages" />
	</div>
	
	<!-- Résultat -->
	<c:if test="${not empty json}">
		<link rel="stylesheet" href="/o/mapweb/css/leaflet.css" />
		<link rel="stylesheet" href="/o/mapweb/css/MarkerCluster.css" />
		<link rel="stylesheet" href="/o/mapweb/css/MarkerCluster.Default.css" />

		<liferay-util:body-top>
		    <aui:script>
		       window.userAddress = '${fn:escapeXml(address)}';
		       window.publikProfileURL = '${strasbourgPropsUtil.getPublikProfileURL()}';
		       window.json = ${json};
		   </aui:script>
		</liferay-util:body-top>
		
		<div align="center">
			<liferay-ui:message key="result-x" arguments="${number}" />
		</div>
		
		<liferay-util:html-bottom>
		    <script src="/o/mapweb/js/leaflet.js"></script>
		    <script src="/o/mapweb/js/leaflet.markercluster-src.js"></script>
		    <script src="/o/mapweb/js/leaflet-list-markers.src.js"></script>
		    <script src="/o/zoneweb/js/map.js"></script>
		</liferay-util:html-bottom>

	    <div id="aroundme">
	        <div id="aroundme__center">
	            <div id="mapid" class="aroundme__map">
	                <div class="aroundme__ui__group" style="z-index: 401">
	                    <button class="aroundme__ui aroundme__ui--fullscreen"></button>
	                    <div class="aroundme__ui aroundme__ui--loading" style="display: none;"><div class="lds-rolling"><div class=""></div></div></div>
	                    <button class="aroundme__ui aroundme__ui--zoomin"></button>
	                    <button class="aroundme__ui aroundme__ui--zoomout"></button>
	                    <button class="aroundme__ui aroundme__ui--locate"></button>
	                    <button class="aroundme__ui aroundme__ui--home"></button>
	                </div>
	
	            </div>
	        </div>
	    </div>
	
	</c:if>
	
</div>

<liferay-util:html-bottom>
	<aui:script>
		define._amd = define.amd;
		define.amd = false;
	</aui:script>
	<script	src="/o/agendabo/js/vendors/jquery.autocomplete.js"></script>
	<script>
		define.amd = define._amd;
		window.forcedCity = 'Strasbourg';
	</script>
	<script
		src="/o/sectorized/js/sectorized.js"
		type="text/javascript"></script>
		
	<style>
		#aroundme {
		    z-index: 10;
		    overflow: hidden;
		}
    		
		.alert-success {
			display: none;
		}
	</style>
</liferay-util:html-bottom>