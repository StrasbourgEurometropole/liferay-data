<%@ include file="/init.jsp" %>

<link rel="stylesheet" href="/o/mapweb/css/leaflet.css" />
<link rel="stylesheet" href="/o/mapweb/css/MarkerCluster.css" />
<link rel="stylesheet" href="/o/mapweb/css/MarkerCluster.Default.css" />
<link rel="stylesheet" href="/o/mapweb/css/leaflet-list-markers.min.css" />

<liferay-util:html-bottom>
	<script>
		define._amd = define.amd;
		define.amd = false;
	</script>
	<script	src="/o/mapweb/js/leaflet.js"></script>
	<script	src="/o/mapweb/js/leaflet.markercluster.js"></script>
	<script	src="/o/mapweb/js/leaflet-list-markers.min.js"></script>
	<script src="/o/mapweb/js/map.js"></script>
	<script>
		define.amd = define._amd;
	</script>
</liferay-util:html-bottom>
	
<div id="mapid" style="width: 600px; height: 400px;"></div>