<%@ include file="./map-init.jsp"%>

<c:if test="${!districtUser || district != null}">
	<c:if test="${!districtUser}">
	    <c:if test="${hierarchy == 'h1'}">
            <h1 class="aroundme--h1">${title}</h1>
        </c:if>
	    <c:if test="${hierarchy == 'h2'}">
            <h2 class="aroundme--h2">${title}</h2>
        </c:if>
	    <c:if test="${hierarchy == 'h3'}">
            <h3 class="aroundme--h3">${title}</h3>
        </c:if>
	</c:if>
	<c:if test="${districtUser}">
	    <h2 class="aroundme--title">
	    	<liferay-ui:message key="my-district" /><br />
	    	${district.getTitle(locale)}
	    </h2>
	</c:if>
	<section id="wi-aroundme" class="no-widget">
		<div id="aroundme">
			<c:if test="${showConfig && !showFiltersReminder}">
		    	<div id="aroundme__top">
			</c:if>
			<c:if test="${!showConfig || showFiltersReminder}">
		    	<div id="aroundme__top" class="hidden">
			</c:if>
		        <button class="top__trigger top__trigger--pull opened"></button>
		        <div class="top__overflow">
		            <aui:form method="POST" action="#" name="addItemForm" id="addItemForm" cssClass="filtres filtres--category seu-view-filters">
						<c:if test="${typesContenu.contains('eu.strasbourg.service.agenda.model.Event')}">
	    					<div class="info-text">${eventExplanationText}</div>
						</c:if>
		                <div class="filtres__list" id="poin">
	                        <button type="button" class="top__trigger top__trigger--close mobile-only"></button>
		                    <c:set var="checkboxNamesCategories" value="" />
                            <c:if test="${dateField}">
                                <div class="seu-filter-line">
                                    <div class="widget filtres__title">
                                        <div class="title">
                                            <label class="option" for="date-start"><liferay-ui:message key="eu.event.from-date" /></label>
                                        </div>
                                        <div class="content type-date">
                                            <input name="from" data-type="date" type="text" id="date-start" placeholder="JJ/MM/AAAA"
                                                value="${fromDate}">
                                            <input type="hidden" name="<portlet:namespace />fromDay" data-name="fromDay" value="${fromDay}" />
                                            <input type="hidden" name="<portlet:namespace />fromMonth" data-name="fromMonth" value="${fromMonth -1}" />
                                            <input type="hidden" name="<portlet:namespace />fromYear" data-name="fromYear" value="${fromYear}" />
                                        </div>
                                    </div>
                                    <div class="widget filtres__title">
                                        <div class="title">
                                            <label class="option" for="date-end"><liferay-ui:message key="eu.event.to" /></label>
                                        </div>
                                        <div class="content type-date">
                                            <input name="to" data-type="date" type="text" id="date-end" placeholder="JJ/MM/AAAA"
                                                value="${toDate}">
                                            <input type="hidden" name="<portlet:namespace />toDay" data-name="toDay" value="${toDay}" />
                                            <input type="hidden" name="<portlet:namespace />toMonth" data-name="toMonth" value="${toMonth -1}" />
                                            <input type="hidden" name="<portlet:namespace />toYear" data-name="toYear" value="${toYear}" />
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${!displayCheckbox}">
                                <div class="filtres__list filtres__title" style="width: 100%; margin-top: 20px;">
                            </c:if>
		                    <c:forEach var="categoriesVocabulary" items="${categoriesVocabularies}" varStatus="categoriesVocabularyLoopStatus">
                                <c:if test="${displayCheckbox}">
                                    <h2 class="filtres__title" style="flex-basis: 100%; margin-top: 20px;"><liferay-ui:message key="filter-by" arguments="${categoriesVocabulary.key[1]}" /></h2>
                                    <div class="filtres__list" style="width: 100%">
                                        <c:forEach var="category" items="${categoriesVocabulary.value}" varStatus="intStatus">
                                            <c:set var="checkboxNamesCategories" value="${checkboxNamesCategories},categoryPointId_${categoriesVocabularyLoopStatus.index}_${intStatus.index}" />
                                            <div class="filtres__item form-group grid-item categories">
                                                <input
                                                    name="<portlet:namespace />categoryPointId_${categoriesVocabularyLoopStatus.index}_${intStatus.index}"
                                                    type="checkbox"
                                                    value="${category.categoryId}"
                                                    data-vocabulary = "${categoriesVocabulary.key[0]}"
                                                    id="<portlet:namespace />categoryPointId_${categoriesVocabularyLoopStatus.index}_${intStatus.index}"
                                                    <c:if test="${fn:contains(categoriesCheckedIds, category.categoryId) || !hasConfig}">
                                                        checked
                                                    </c:if>
                                                >
                                                <label for="<portlet:namespace />categoryPointId_${categoriesVocabularyLoopStatus.index}_${intStatus.index}" class="option">
                                                    ${category.getTitle(locale)}

                                                    <c:set var="prefilters" value="${fn:replace(prefilterCategoriesIds,'\"','')}" />
                                                    <%-- <c:set var="fromDate" value="${fromDay}/${fromMonth}/${fromYear}" />
                                                    <c:set var="toDate" value="${toDay}/${toMonth}/${toYear}" />
                                                    (${dc.getPoisCategoryCount(category.categoryId, prefilters, prefilterTags, groupId, typesContenu, dateField, fromDate, toDate, locale, globalGroupId)}) --%>

                                                    <c:if test="${showPictos && !category.getDescription(locale).equals(\"\")}">
                                                        <img src="${category.getDescription(locale)}">
                                                    </c:if>
                                                </label>
                                            </div>
                                        </c:forEach>
		                            </div>
                                </c:if>
                                <c:if test="${!displayCheckbox}">
                                    <div class="filtres__item form-group grid-item categories">
                                        <div class="title">
                                            <label class="option" for="vocabulary_${categoriesVocabularyLoopStatus.index}">${categoriesVocabulary.key[1]}</label>
                                        </div>
                                        <div class="content">
                                            <select class="categories" id="vocabulary_${categoriesVocabularyLoopStatus.index}" multiple="multiple" name="<portlet:namespace />vocabulary_${categoriesVocabularyLoopStatus.index}">
                                                <c:forEach items="${categoriesVocabulary.value}" var="categ">
                                                    <c:set var="showParents" value="1" scope="request"/>
                                                    <c:if test="${categoriesVocabulary.value.size() == 1}">
                                                        <c:set var="showParents" value="0" scope="request"/>
                                                    </c:if>
                                                    <c:set var="category" value="${categ}" scope="request"/>
                                                    <c:set var="level" value="0" scope="request" />
                                                    <jsp:include page="/include/category-option.jsp"/>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </c:if>
		                    </c:forEach>
                            <c:if test="${!displayCheckbox}">
                                </div>
                            </c:if>
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
											<%-- (${dc.getFavoritesPoisCount(groupId, typesContenu)}) --%>
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
		                                	<%-- (${dc.getPoisInterestCount(interest.interestId, groupId, typesContenu, locale, globalGroupId)}) --%>
		                                </label>
		                            </div>
		                        </c:forEach>
		                    </c:forEach>

                            <c:if test="${showDeleteFilter}">
                                <div class="deleteFilters">
                                    <a id="deleteFilters" href=""><liferay-ui:message key="delete-filters" /></a>
                                </div>
                            </c:if>

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

			            <c:if test="${mode != 'normal'}">
                            <div class="filtres__actions">
                                <portlet:actionURL name="resetUserConfiguration"
                                    var="resetUserConfiguration">
                                    <portlet:param name="mvcPath" value="/map-view.jsp"></portlet:param>
                                    <portlet:param name="configId" value="${configId}" />
                                </portlet:actionURL>
                                <a href="${resetUserConfiguration}" class="filtres__btn filtres__btn--reset">
                                    <span class="flexbox">
                                        <span class="btn-arrow"></span>
                                        <span class="btn-text"><liferay-ui:message key="reset-my-filters" /></span>
                                    </span>
                                </a>
                                <portlet:resourceURL id="toggleInterestPoint" var="interestPointURL">
                                    <portlet:param name="checkboxNamesCategories" value="${checkboxNamesCategories}" />
                                    <portlet:param name="checkboxNamesInterests" value="${checkboxNamesInterests}" />
                                    <portlet:param name="configId" value="${configId}" />
                                </portlet:resourceURL>
                                <a href="" class="filtres__btn filtres__btn--save">
                                    <span class="flexbox">
                                        <span class="btn-arrow"></span>
                                        <span class="btn-text"><liferay-ui:message key="save-my-settings" /></span>
                                    </span>
                                </a>
                            </div>
                        </c:if>
		            </aui:form>
		        </div>
		    </div>
		    <div id="aroundme__center">
		        <div id="mapid" class="aroundme__map">
		            <div class="aroundme__ui__group" style="z-index: 401">
		                <button class="aroundme__ui aroundme__ui--fullscreen" aria-label="<liferay-ui:message key='eu.aroundme.fullscreen' />"></button>
		                <div class="aroundme__ui aroundme__ui--loading" style="display: none;"><div class="lds-rolling"><div class=""></div></div></div>
		                <button class="aroundme__ui aroundme__ui--zoomin" aria-label="<liferay-ui:message key='eu.aroundme.zoomin' />"></button>
		                <button class="aroundme__ui aroundme__ui--zoomout" aria-label="<liferay-ui:message key='eu.aroundme.zoomout' />"></button>
		                <button class="aroundme__ui aroundme__ui--locate" aria-label="<liferay-ui:message key='eu.aroundme.locate' />"></button>
		                <button class="aroundme__ui aroundme__ui--home" data-mapconfirm="center" aria-label="<liferay-ui:message key='eu.aroundme.home' />"></button>
		            </div>
					
					<c:if test="${showList}">
						<div id="aroundme__side" style="z-index: 406" class="${showList} opened">
					</c:if>
					<c:if test="${!showList}">
						<div id="aroundme__side" style="z-index: 406" class="${showList} opened hidden">
					</c:if>
		                <button class="side__trigger side__trigger--pull opened" aria-label="<liferay-ui:message key='eu.aroundme.open-list' />"></button>
                        <c:if test="${showFiltersReminder}">
                            <div id="filters__reminder">
                            </div>
                        </c:if>
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

<liferay-util:html-bottom>
    <script>
        $(document).ready(function() {
            if($('input[data-type="date"]').length){
                $('input[data-type="date"]').datepicker(
                    $.extend({
                        onClose: function(date, instance) {
                            var name = instance.input[0].name;
                            if ($('input[data-name="' + name + 'Day"').length) {
                                var dayInput = $('input[data-name="' + name + 'Day"');
                                var monthInput = $('input[data-name="' + name + 'Month"');
                                var yearInput = $('input[data-name="' + name + 'Year"');
                                dayInput[0].value = instance.selectedDay;
                                monthInput[0].value = instance.selectedMonth;
                                yearInput[0].value = instance.selectedYear;
                            }
                        }
                    }, $.datepicker.regional[ "fr" ])
                );
            }
        });
    </script>
    <script src="/o/mapweb/js/jquery-ui.min.js"></script>
    <script src="/o/mapweb/js/jquery-ui-datepicker-fr.js"></script>
    <script src="/o/mapweb/js/select2.min.js"></script>
    <script>
        $(document).ready(function() {
            $('select[multiple="multiple"]').each(function(index, element){
                var placeholder = $(element).find('option[disabled]').text();
                $(element).select2({
                    placeholder: placeholder,
                    closeOnSelect: false
                });
            })
        });
    </script>
</liferay-util:html-bottom>
