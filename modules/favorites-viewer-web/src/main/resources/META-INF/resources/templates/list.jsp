<%@ include file="../favorites-viewer-init.jsp"%>

<c:set var="locale" value="${themeDisplay.getLocale() }" />

<h1> ${dc.getPortletTitle('my-favorites')}</h1>
<p class="chapo">${dc.getTexte()}</p>
<div class="generic-form">
	<div class="widget favoris__filters">
		<div class="title">
			<label for="sort"><liferay-ui:message key="types-favorites" /></label>
		</div>
		<div class="content">
			<div class="customSelectContain">
				<select name="sorter" id="sort"
					class="toCustomSelect silencedSelect" 
					onchange="this.options[this.selectedIndex].value && (window.location = this.options[this.selectedIndex].value);">
					<liferay-portlet:renderURL var="allFavoritesURL"></liferay-portlet:renderURL>
					<option value="${allFavoritesURL}" <c:if test="${empty param.favoriteTypeId}">selected</c:if>>
						<liferay-ui:message key="everything" />
					</option>
					<c:forEach items="${dc.favoritesTypeFromUserFavorites}" var="favoriteType">
						<liferay-portlet:renderURL var="favoriteFilterURL">
							<liferay-portlet:param name="favoriteTypeId" value="${favoriteType.id}" />
						</liferay-portlet:renderURL>
						<option value="${favoriteFilterURL}" <c:if test="${favoriteType.id eq param.favoriteTypeId}">selected</c:if>>
							<liferay-ui:message key="eu.${fn:toLowerCase(favoriteType.name) }" />
						</option>
					</c:forEach>
				</select>
			</div>
		</div>
	</div>
		        
	<!-- Nombre de rÃ©sultats et items par page -->
	<div class="search-infos">
	    <div class="search-infos__amount"> 
	    	<c:choose>
	    		<c:when test="${dc.searchContainer.total == 0}">
	    			${dc.getNoFavoriteText()}
	    		</c:when>
	    		<c:when test="${dc.searchContainer.total gt 1}">
	    			${dc.searchContainer.total} <liferay-ui:message key="favorites" />
	    		</c:when>
	    		<c:otherwise>
	    			${dc.searchContainer.total} <liferay-ui:message key="favorite" />
	    		</c:otherwise>
	    	</c:choose>
	   	</div>
	    <div class="filler"></div>
	    <div class="search-infos__filter">
	        <span><liferay-ui:message key="results-per-page" /></span>
	        <select name="filter" id="" class="toCustomSelect" onchange="this.options[this.selectedIndex].value && (window.location = this.options[this.selectedIndex].value);">
	            <c:forEach var="delta" items="${[5, 10, 20, 50, 100]}">
	            	<c:set var="selected" value="${delta eq dc.delta ? 'selected' : ''}" />
	            	<option value="${dc.getURLForDelta(delta)}" ${selected} >${delta}</option>
	            </c:forEach>
	        </select>
	    </div> 
	</div>
		
	<!-- Liste des rÃ©sultats -->
	<aui:form method="post" name="fm">
		<!-- RÃ©sultats -->
		<liferay-ui:search-container id="entriesSearchContainer"
					searchContainer="${dc.searchContainer}">
			<ul class="favoris-list">
				<c:forEach items="${dc.paginatedResults}" var="favorite">
					<li class="favoris-teaser type-${favorite.typeId}"><a href="${favorite.url}" class="favoris-teaser__link">
                        <div class="favoris-teaser__type">  <liferay-ui:message key="eu.${fn:toLowerCase(favorite.typeName) }" /></div>
                        <div >
								
                            <h3 class="favoris-teaser__title" data-dot="3"
                                style="word-wrap: break-word;">${favorite.title}</h3>

                            <div class="favoris-teaser__date">
                                <c:if test="${favorite.typeId == 1 }">
                                    <c:if test="${not empty favorite.place.periods}">
                                        <c:forEach items="${favorite.place.getPlaceSchedule(dc.todayCalendar, locale)}" var="schedule" varStatus="loopStatus">

                                            <c:if test="${!favorite.place.isOpenNow()}">
                                                <liferay-ui:message key="eu.closed" />
                                            </c:if>
                                            <c:if test="${schedule.isAlwaysOpen()}">
                                                <liferay-ui:message key="always-open" />
                                            </c:if>
                                            <c:if test="${not empty schedule.openingTimes}">
                                                <liferay-ui:message key="today-schedule" />
                                                <c:forEach items="${schedule.openingTimes}" var="openingTime" varStatus="timeLoopStatus">
                                                    <div>${openingTime.first} - ${openingTime.second}</div>
                                                    <c:if test="${not empty schedule.comments[timeLoopStatus.index]}">
                                                        <div style="text-transform: none; font-weight: 400; margin-top:-10px;">(${schedule.comments[timeLoopStatus.index]})</div>
                                                    </c:if>
                                                </c:forEach>
                                            </c:if>

                                        </c:forEach>
                                    </c:if>
                                </c:if>
                                <c:if test="${favorite.typeId == 2 }">
                                    <fmt:formatDate value="${favorite.event.firstStartDate}"
                                        var="formattedStartDate" type="date" pattern="dd.MM.yy" />
                                    <fmt:formatDate value="${favorite.event.lastEndDate}"
                                        var="formattedEndDate" type="date" pattern="dd.MM.yy" />
                                    <c:if test="${favorite.event.firstStartDate == favorite.event.lastEndDate }">
                                        Le ${formattedStartDate}
                                    </c:if>
                                    <c:if test="${favorite.event.firstStartDate != favorite.event.lastEndDate }">
                                        Du ${formattedStartDate} au ${formattedEndDate}
                                    </c:if>
                                </c:if>
                                <c:if test="${favorite.typeId == 4 }">
                                    <c:if test="${favorite.edition.getDiffusionDateMonth()<10}">0</c:if>${favorite.edition.getDiffusionDateMonth()}.${favorite.edition.getDiffusionDateYear()}
                                </c:if>
                                <c:if test="${favorite.typeId == 6 }">
                                    <fmt:formatDate value="${favorite.news.modifiedDate}"
                                        var="formattedModifiedDate" type="date" pattern="dd.MM.yy" />
                                    Le ${formattedModifiedDate}
                                </c:if>
                                <c:if test="${favorite.typeId == 12 }">
                                    <fmt:formatDate value="${favorite.manifestation.startDate}"
                                        var="formattedStartDate" type="date" pattern="dd.MM.yy" />
                                    <fmt:formatDate value="${favorite.manifestation.endDate}"
                                        var="formattedEndDate" type="date" pattern="dd.MM.yy" />
                                    <c:if test="${favorite.manifestation.startDate == favorite.manifestation.endDate }">
                                        Le ${formattedStartDate}
                                    </c:if>
                                    <c:if test="${favorite.manifestation.startDate != favorite.manifestation.endDate }">
                                        Du ${formattedStartDate} au ${formattedEndDate}
                                    </c:if>
                                </c:if>
                            </div>

                            <c:if test="${favorite.typeId == 2 }">
                                <div class="favoris-teaser__description">
                                    <div>${favorite.event.getPlaceAlias(locale)} - ${favorite.event.getPlaceCity(locale)}</div>
                                </div>
                            </c:if>

                            <c:if test="${favorite.typeId == 1 && favorite.place.isEnabled()}">
                                <div class="favoris-teaser__crowding">
                                    <c:set var="occupationState" value="${favorite.place.getRealTime()}" />
                                    <div class="favoris-teaser__crowding-amount favoris-teaser__crowding-amount--${occupationState.cssClass}">
                                        <c:if test="${favorite.place.isSwimmingPool()}">
                                                ${occupationState.occupation}
                                        </c:if>
                                        <c:if test="${favorite.place.isParking()}">
                                                ${occupationState.available}
                                        </c:if>
                                        <c:if test="${favorite.place.isMairie()}">
                                                ${occupationState.occupation} <c:if test="${occupationState.occupation != '-'}"></c:if>
                                        </c:if>
                                    </div>
                                    <div class="favoris-teaser__crowding-label"><liferay-ui:message key="${occupationState.label}" /></div>
                                </div>
                            </c:if>
                        </div>

                        <liferay-portlet:actionURL name="deleteFavorite" var="deleteFavoriteURL">
                            <portlet:param name="favoriteId" value="${favorite.favoriteId}" />
                            <portlet:param name="favoriteTypeId" value="${param.favoriteTypeId}" />
                        </liferay-portlet:actionURL>
                        <a href="${deleteFavoriteURL }" class="favoris-teaser__trash" data-favconfirm="delete"><liferay-ui:message key="delete-favorite" /></a>

                        <liferay-portlet:actionURL name="linkFavoriteToDashboard" var="linkFavoriteToDashboardURL">
                            <portlet:param name="favoriteId" value="${favorite.favoriteId}" />
                            <portlet:param name="favoriteTypeId" value="${param.favoriteTypeId}" />
                        </liferay-portlet:actionURL>
                        <a href="${linkFavoriteToDashboardURL}" class="favoris-teaser__${dc.isFavoriteOnDashboard(favorite.favoriteId)?'remove':'add'}" data-favtodashboard="add"><liferay-ui:message key="add-favorite" /></a>
					</li>
				</c:forEach>
			</ul>
						
			<!-- Pagination -->
			<c:if test="${dc.pager.lastPage > 1}">
	            <ul class="mseu-pagination unstyled">

	            	<!-- Page précédente -->
	                <li class="pagin-prev pagin-item">
						<c:if test="${not dc.pager.onFirstPage}">
		                    <a class="btn-square bordered core" data-action="prev" title="<liferay-ui:message key="go-to-previous-page" />"
								href="${dc.getURLForPage(dc.pager.currentPage - 1)}">
		                        <span class="flexbox">
		                            <span class="btn-text"><liferay-ui:message key="previous" /></span>
		                            <span class="btn-arrow"></span>
		                        </span>
		                    </a>
	               		</c:if>
	                </li>
	                <c:forEach var="page" items="${dc.pager.pages}">
	                	<c:choose>
	                		<c:when test="${page.isALink() and not (page.index eq dc.pager.currentPage)}">
	                			<!-- Lien vers page -->
		                		<li class="pagin-item">
				                    <a data-page="${page.index}" href="${dc.getURLForPage(page.index)}">
				                        <span class="flexbox">
				                            <span class="btn-text">${page.label}</span>
				                        </span>
				                    </a>
				                </li>
	                		</c:when>
	                		<c:when test="${page.isALink() and (page.index eq dc.pager.currentPage)}">
	                			<!-- Page en cours -->
		                		<li class="pagin-item is-active">
			                        <span class="flexbox">
			                            <span class="btn-text">${page.label}</span>
			                        </span>
			                    </li>
			                </c:when>
	                		<c:otherwise>
			                	<!-- Texte -->
		                		<li class="pagin-item">
			                        <span class="flexbox">
			                            <span class="btn-text">${page.label}</span>
			                        </span>
			                    </li>
	                		</c:otherwise>
	                	</c:choose>
	                </c:forEach>
	                
	                <!-- Page suivante -->
	                <li class="pagin-next pagin-item">
						<c:if test="${not dc.pager.onLastPage}">
		                    <a class="btn-square bordered core" title="<liferay-ui:message key="go-to-next-page" />" 
		                    	data-action="next" href="${dc.getURLForPage(dc.pager.currentPage + 1)}">
		                        <span class="flexbox">
		                            <span class="btn-text"><liferay-ui:message key="next" /></span>
		                            <span class="btn-arrow"></span>
		                        </span>
		                    </a>
	              	 	</c:if>
	                </li>
	            </ul>
	        </c:if>
		</liferay-ui:search-container>
	</aui:form>
</div>
