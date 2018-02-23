<%@ include file="./map-init.jsp"%>

<link rel="stylesheet" href="/o/mapweb/css/leaflet.css" />
<link rel="stylesheet" href="/o/mapweb/css/MarkerCluster.css" />
<link rel="stylesheet" href="/o/mapweb/css/MarkerCluster.Default.css" />

<liferay-util:html-bottom>
	<script>
		define._amd = define.amd;
		define.amd = false;
	</script>
	<script src="/o/mapweb/js/leaflet.js"></script>
	<script src="/o/mapweb/js/leaflet.markercluster-src.js"></script>
	<script src="/o/mapweb/js/map.js"></script>
	<script>
		define.amd = define._amd;
	</script>
</liferay-util:html-bottom>

<c:if test="${!widgetMod}">
	<div id="point-of-interest" style="witdh: 600px">

		<portlet:resourceURL id="toggleInterestPoint" var="interestPointURL">
			<portlet:param name="interestsCount" value="${fn:length(interests)}" />
		</portlet:resourceURL>

		<aui:form method="POST" action="#" name="addItemForm" id="addItemForm">
			<c:forEach var="interest" items="${interests}" varStatus="intStatus">

				<aui:input type="checkbox" name="interestPointId_${intStatus.index}"
					inlineField="true"
					checked="${fn:contains(interestsCheckedIds, interest.interestId) || !hasConfig}"
					onChange="callServeResource();"
					label="${interest.getTitle(locale)}" value="${interest.interestId}">
				</aui:input>
			</c:forEach>

			<aui:input type="checkbox" name="showFavorites" inlineField="true"
				checked="${showFavorites}" onChange="callServeResource();"
				label="show-favorites" value="${showFavorites}">
			</aui:input>
		</aui:form>
		
		<portlet:actionURL name="resetUserConfiguration" var="resetUserConfiguration">
			<portlet:param name="mvcPath" value="/map-view.jsp"></portlet:param>
		</portlet:actionURL>
		
		<aui:button-row>
			<aui:button href="${resetUserConfiguration}" value="reinit-map"></aui:button>
			<input type="button" value="Votre position" onclick="position()">
			<input type="button" value="Votre adresse" onclick="address('${address}')">
		</aui:button-row>
	</div>
</c:if>
		
		
	</aui:form>
	
</div>
<div id="mapid"
	style="width: 600px; height: 400px; display: inline-block;"></div>
</div>

<aui:script>
	function callServeResource() {
		AUI().use('aui-io-request', function(A) {
			A.io.request('${interestPointURL}', {
				method : 'post',
				form: { id: '<portlet:namespace />addItemForm' },
			});
		});
	}

	
</aui:script>