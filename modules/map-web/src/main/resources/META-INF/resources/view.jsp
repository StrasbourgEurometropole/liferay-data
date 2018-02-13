<%@ include file="/init.jsp" %>

<link rel="stylesheet" href="https://unpkg.com/leaflet@1.3.1/dist/leaflet.css" />

<liferay-util:html-bottom>
	<script>
		define._amd = define.amd;
		define.amd = false;
	</script>
	<script	src="/o/mapweb/js/leaflet-src.js"></script>
	
	<script src="/o/mapweb/js/map.js"></script>
	<script>
		define.amd = define._amd;
	</script>
</liferay-util:html-bottom>
	
<div id="mapid" style="width: 600px; height: 400px;"></div>