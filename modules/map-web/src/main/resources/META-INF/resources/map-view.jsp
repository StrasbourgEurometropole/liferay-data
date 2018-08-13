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

<c:if test="${!districtUser || district != null}">
	<c:if test="${!districtUser}">
	    <h1><liferay-ui:message key="auround-me" /></h1>
	</c:if>
	<c:if test="${districtUser}">
	    <h2 class="aroundme--title">
	    	<liferay-ui:message key="my-district" /><br />
	    	${district.getTitle(locale)}
	    </h2>
	</c:if>
	<section id="wi-aroundme">
		<div id="aroundme">
			<c:if test="${showConfig}">
		    	<div id="aroundme__top">
			</c:if>
			<c:if test="${!showConfig}">
		    	<div id="aroundme__top" class="hidden">
			</c:if>
		        <button class="top__trigger top__trigger--pull opened"></button>
		        <div class="top__overflow">
		            <aui:form method="POST" action="#" name="addItemForm" id="addItemForm" cssClass="filtres filtres--category">
						<c:if test="${typesContenu.contains('eu.strasbourg.service.agenda.model.Event')}">
	    					<p>${eventExplanationText}</p>
						</c:if>
		                <div class="filtres__list" id="poin">
	                        <button type="button" class="top__trigger top__trigger--close mobile-only"></button>
		                    <c:set var="checkboxNamesCategories" value="" />
		                    <c:forEach var="vocabularyGroups" items="${vocabularyGroups}" varStatus="groupVocabularyLoopStatus">
		                        <h2 class="filtres__title" style="flex-basis: 100%; margin-top: 20px;">${vocabularyGroups.key}</h2>
		                        <div class="filtres__list">
			                        <c:forEach var="category" items="${vocabularyGroups.value}" varStatus="intStatus">
			                            <c:set var="checkboxNamesCategories" value="${checkboxNamesCategories},categoryPointId_${groupVocabularyLoopStatus.index}_${intStatus.index}" />
			                            <div class="filtres__item form-group grid-item categories">
			                                <input
			                                    name="<portlet:namespace />categoryPointId_${groupVocabularyLoopStatus.index}_${intStatus.index}"
			                                    type="checkbox"
			                                    value="${category.categoryId}"
			                                    id="<portlet:namespace />categoryPointId_${groupVocabularyLoopStatus.index}_${intStatus.index}"
			                                    <c:if test="${fn:contains(categoriesCheckedIds, category.categoryId) || !hasConfig}">
			                                        checked
			                                    </c:if>
			                                >
			                                <label for="<portlet:namespace />categoryPointId_${groupVocabularyLoopStatus.index}_${intStatus.index}" class="option">
			                                	${category.getTitle(locale)}
		
												<c:set var="prefilters" value="${fn:replace(prefilterCategoriesIds,'\"','')}" />
			                                	(${dc.getPoisCategoryCount(category.categoryId, prefilters, groupId, typesContenu)})
			                                </label>
			                            </div>
			                        </c:forEach>
		                        </div>
		                    </c:forEach>
		                    <c:set var="checkboxNamesInterests" value="" />
		                    <c:forEach var="interestGroup" items="${interestGroups}" varStatus="groupLoopStatus">
		                        <h2 class="filtres__title" style="flex-basis: 100%; margin-top: 20px;">${interestGroup.category.getTitle(locale)}</h2>
								<c:if test="${groupLoopStatus.index eq 0 and defaultConfig}">
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
										<label for="<portlet:namespace />showFavorites" class="option">
											Mes favoris
											(${dc.getFavoritesPoisCount(groupId, typesContenu)})
										</label>
									</div>
								</c:if>
		                        <c:forEach var="interest" items="${interestGroup.interests}" varStatus="intStatus">
		                            <c:set var="checkboxNamesInterests" value="${checkboxNamesInterests},interestPointId_${groupLoopStatus.index}_${intStatus.index}" />
		                            <div class="filtres__item form-group grid-item interests">
		                                <input
		                                    name="<portlet:namespace />interestPointId_${groupLoopStatus.index}_${intStatus.index}"
		                                    type="checkbox"
		                                    value="${interest.interestId}"
		                                    id="<portlet:namespace />interestPointId_${groupLoopStatus.index}_${intStatus.index}"
		                                    <c:if test="${fn:contains(interestsCheckedIds, interest.interestId) || !hasConfig}">
		                                        checked
		                                    </c:if>
		                                >
		                                <label for="<portlet:namespace />interestPointId_${groupLoopStatus.index}_${intStatus.index}" class="option">
		                                	${interest.getTitle(locale)}
		                                	(${dc.getPoisInterestCount(interest.interestId, groupId, typesContenu)})
		                                </label>
		                            </div>
		                        </c:forEach>
		                    </c:forEach>
		                    <input
		                        id="<portlet:namespace />checkboxNamesCategories"
		                        name="<portlet:namespace />checkboxNamesCategories"
		                        type="hidden"
		                        value="${checkboxNamesCategories}">
		                    <input
		                        id="<portlet:namespace />checkboxNamesInterests"
		                        name="<portlet:namespace />checkboxNamesInterests"
		                        type="hidden"
		                        value="${checkboxNamesInterests}">
		                </div>
		
		                <div class="filtres__actions">
		
		                    <portlet:actionURL name="resetUserConfiguration"
		                        var="resetUserConfiguration">
		                        <portlet:param name="mvcPath" value="/map-view.jsp"></portlet:param>
		                    </portlet:actionURL>
		                    <a href="${resetUserConfiguration}" class="filtres__btn filtres__btn--reset">
		                        <span class="flexbox">
		                            <span class="btn-arrow"></span>
		                            <span class="btn-text">R&eacute;initialiser mes filtres</span>
		                        </span>
		                    </a>
						    <portlet:resourceURL id="toggleInterestPoint" var="interestPointURL">
						        <portlet:param name="checkboxNamesCategories" value="${checkboxNamesCategories}" />
						        <portlet:param name="checkboxNamesInterests" value="${checkboxNamesInterests}" />
						    </portlet:resourceURL>
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
		                <button class="aroundme__ui aroundme__ui--home" data-mapconfirm="center"></button>
		            </div>
					
					<c:if test="${showList}">
						<div id="aroundme__side" style="z-index: 406" class="${showList} opened">
					</c:if>
					<c:if test="${!showList}">
						<div id="aroundme__side" style="z-index: 406" class="${showList} opened hidden">
					</c:if>
		                <button class="side__trigger side__trigger--pull opened"></button>
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
	</section>
</c:if>


<liferay-util:body-top>
    <aui:script>
        window.interestPointUrl = '${interestPointURL}';
    </aui:script>
</liferay-util:body-top>
