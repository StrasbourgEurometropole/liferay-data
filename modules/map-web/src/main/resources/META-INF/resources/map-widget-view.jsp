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
    <script src="/o/mapweb/js/leaflet-list-markers.src.js"></script>
    <script src="/o/mapweb/js/map.js"></script>
    <script>
        define.amd = define._amd;
    </script>
</liferay-util:html-bottom>

<section id="wi-aroundme">
    <%-- Récupère le fait de plier ou déplier ce widget dans la config de la personnalisation --%>
    <c:set value="${dc.isFolded()}" var="isFolded" />
    <div class="buttons">
        <%-- Vérifie si ce widget peut être plié dans la config de la personnalisation --%>
        <c:if test="${dc.showRetractableButton()}">
            <button class="${isFolded?'retractable-folded-wi':'retractable-unfolded-wi'}" data-portlet-id="${themeDisplay.portletDisplay.id}"style="top: 0;"></button>
        </c:if>
        <%-- Vérifie si ce widget peut être masqué dans la config de la personnalisation --%>
        <c:if test="${dc.showDeleteButton()}">
            <button class="delete-wi" data-portlet-id="${themeDisplay.portletDisplay.id}"style="top: 0;"></button>
        </c:if>
    </div>

    <h2>${title}</h2>
    <div class="detail" ${dc.isFolded()?'style="display: none;"':''}>
        <div class="meta" style="position: relative;">
            <p class="subtitle">${widgetIntro}</p>
            <div class="btn-line">
                <a href="${widgetLink}" class="btn-square--bordered--core"><span class="flexbox"><span class="btn-text">Modifier ma carte</span><span class="btn-arrow"></span></span></a>
            </div>
        </div>
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

                    <div id="aroundme__side" style="z-index: 406" >
                        <button class="side__trigger side__trigger--pull"></button>
                        <div class="side__overflow">
                            <form class="liste filtres--poi">
                                <h2 class="filtres__title">
                                    <div class="icon mobile-only"></div>
                                    Liste
                                    <div class="filler"></div>
                                    <button type="button" class="side__trigger side__trigger--close mobile-only"></button>
                                </h2>
                            </form>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</section>
