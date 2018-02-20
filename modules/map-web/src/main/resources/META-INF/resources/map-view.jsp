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
<div id="point-of-interest" style="witdh: 600px">

	<c:forEach var="interest" items="${interests}" varStatus="intStatus">
		<aui:input type="checkbox" name="interestId_${intStatus.index}" inlineField="true"
			checked="${fn:contains(interestsCheckedIds, interest.interestId)}"
			label="${interest.getTitle(locale)}" value="${interest.interestId}">
		</aui:input>
	</c:forEach>

</div>
<div id="mapid"
	style="width: 600px; height: 400px; display: inline-block;"></div>
</div>