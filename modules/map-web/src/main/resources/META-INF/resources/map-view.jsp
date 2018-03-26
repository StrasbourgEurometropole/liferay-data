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


<h1>Autour de moi</h1>
<div id="aroundme">
    <portlet:resourceURL id="toggleInterestPoint" var="interestPointURL">
        <portlet:param name="interestsCount" value="${interestsCount}" />
    </portlet:resourceURL>
    <div id="aroundme__top">
        <button class="top__trigger top__trigger--pull opened"></button>
        <div class="top__overflow">
            <aui:form method="POST" action="#" name="addItemForm" id="addItemForm" cssClass="filtres filtres--category">
                <div class="filtres__list" id="poin">
                    <c:set var="checkboxNames" value="showFavorites" />
                    <c:forEach var="interestGroup" items="${interestGroups}" varStatus="groupLoopStatus">
                        <c:if test="${groupLoopStatus.index eq 0}">
                            <button type="button" class="top__trigger top__trigger--close mobile-only"></button>
                        </c:if>
                        <h2 class="filtres__title" style="flex-basis: 100%; margin-top: 20px;">${interestGroup.category.getTitle(locale)}</h2>
                        <c:if test="${groupLoopStatus.index eq 0}">
                            <div class="filtres__item form-group grid-item filtres__item--favorite">
                                <input
                                        name="<portlet:namespace />showFavorites"
                                        type="checkbox"
                                        value="true"
                                        id="<portlet:namespace />showFavorites"
                                <c:if test="${showFavorites}">
                                        checked
                                </c:if>
                                >
                                <label for="<portlet:namespace />showFavorites" class="option">Mes favoris</label>
                            </div>
                        </c:if>
                        <c:forEach var="interest" items="${interestGroup.interests}" varStatus="intStatus">
                            <c:set var="checkboxNames" value="${checkboxNames},interestPointId_${intStatus.index}" />
                            <div class="filtres__item form-group grid-item">
                                <input
                                    name="<portlet:namespace />interestPointId_${intStatus.index}"
                                    type="checkbox"
                                    value="${interest.interestId}"
                                    id="<portlet:namespace />interestPointId_${intStatus.index}"
                                    <c:if test="${fn:contains(interestsCheckedIds, interest.interestId) || !hasConfig}">
                                        checked
                                    </c:if>
                                >
                                <label for="<portlet:namespace />interestPointId_${intStatus.index}" class="option">${interest.getTitle(locale)}</label>
                            </div>
                        </c:forEach>
                    </c:forEach>
                    <input
                        id="<portlet:namespace />checkboxNames"
                        name="<portlet:namespace />checkboxNames"
                        type="hidden"
                        value="${checkboxNames}">
                </div>

                <div class="filtres__actions">

                    <portlet:actionURL name="resetUserConfiguration"
                        var="resetUserConfiguration">
                        <portlet:param name="mvcPath" value="/map-view.jsp"></portlet:param>
                    </portlet:actionURL>
                    <a href="${resetUserConfiguration}" class="filtres__btn filtres__btn--reset">
                        <span class="flexbox">
                            <span class="btn-arrow"></span>
                            <span class="btn-text">Effacer mes filtres</span>
                        </span>
                    </a>
                    <a href="" class="filtres__btn filtres__btn--save">
                        <span class="flexbox">
                            <span class="btn-arrow"></span>
                            <span class="btn-text">Enregistrer mes pr&eacute;f&eacute;rences</span>
                        </span>
                    </a>
                </div>
            </aui:form>
        </div>
    </div>
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

            <div id="aroundme__side" style="z-index: 406" class="opened">
                <button class="side__trigger side__trigger--pull opened"></button>
                <div class="side__overflow">
                    <form class="filtres filtres--poi">
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


<liferay-util:body-top>
    <aui:script>
        window.userAddress = '${fn:escapeXml(address)}';
        window.groupId = ${groupId};
        window.newTab = ${newTab};
        window.interestsCheckedIds = "${fn:replace(interestsCheckedIds, '"', '')}";
        window.showFavoritesByDefault = ${showFavorites};
        window.isWidgetMode = ${widgetMod};
        window.interestPointUrl = '${interestPointURL}';
        window.aroundMePortletNamespace = '<portlet:namespace />';    
    </aui:script>
</liferay-util:body-top>
