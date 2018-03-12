<%@ include file="../favorites-viewer-init.jsp"%>

<c:set var="locale" value="${themeDisplay.getLocale() }" />

<h1> ${dc.getPortletTitle('my-favorites')}</h1>
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
					<c:forEach items="${dc.favoritesType}" var="favoriteType">
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
		        
	<!-- Nombre de résultats et items par page -->
	<div class="search-infos">
	    <div class="search-infos__amount"> 
	    	<c:choose>
	    		<c:when test="${dc.searchContainer.total == 0}">
	    			<liferay-ui:message key="no-favorites" />
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
		
	<!-- Liste des résultats -->
	<aui:form method="post" name="fm">
		<!-- Résultats -->
		<liferay-ui:search-container id="entriesSearchContainer"
					searchContainer="${dc.searchContainer}">
			<ul class="favoris-list">
				<c:forEach items="${dc.paginatedResults}" var="favorite">
					<li class="favoris-teaser"><a href="${favorite.url}"
						class="favoris-teaser__link">
							<div class="favoris-teaser__type">  <liferay-ui:message key="eu.${fn:toLowerCase(favorite.typeName) }" /></div>
							<div class="favoris-teaser__content">
							
								<c:if test="${favorite.typeId != 7 && favorite.typeId != 9 }">
									<div class="favoris-teaser__tag">					
										<c:if test="${favorite.typeId == 1 }">
											${favorite.place.getTypeLabel(locale) }
										</c:if>
										<c:if test="${favorite.typeId == 2 }">
											${favorite.event.getThemeLabel(locale) }
										</c:if>
										<c:if test="${favorite.typeId == 4 }">
											${favorite.edition.getTypesLabels(locale) }
										</c:if>
										<c:if test="${favorite.typeId == 6 }">
											${favorite.newsLabel}
										</c:if>
										<c:if test="${favorite.typeId == 10 }">
											${favorite.activity.getTypesLabel(locale) }
										</c:if>
										<c:if test="${favorite.typeId == 11 }">
											${favorite.course.getTypesLabels(locale) }
										</c:if>
										<c:if test="${favorite.typeId == 12 }">
											${favorite.manifestation.getTypeLabel(locale) }
										</c:if>
										<c:if test="${favorite.typeId == 13 }">
											${favorite.gallery.getTypesLabels(locale) }
										</c:if>
									</div>
								</c:if>
								
								<h3 class="favoris-teaser__title" data-dot="3"
									style="word-wrap: break-word;">${favorite.title}</h3>
												
								<div class="favoris-teaser__description">
									<c:if test="${favorite.typeId == 2 }">
										${favorite.event.getPlaceAlias(locale)} - ${favorite.event.getPlaceCity(locale)}
									</c:if>
								</div>
								
								<div class="favoris-teaser__date">
									<c:if test="${favorite.typeId == 1 }">
										<c:if test="${not empty favorite.place.periods}">
											<c:forEach items="${favorite.place.getPlaceSchedule(dc.todayCalendar, locale)}" var="schedule" varStatus="loopStatus">
												<c:choose>
													<c:when test="${schedule.isClosed()}">
														<liferay-ui:message key="eu.closed" />
													</c:when>
													<c:when test="${schedule.isAlwaysOpen()}">
														<liferay-ui:message key="always-open" />
													</c:when>
													<c:otherwise>
														<liferay-ui:message key="today-schedule" />
														<c:forEach items="${schedule.openingTimes}" var="openingTime" varStatus="timeLoopStatus">
															<div>${openingTime.first} - ${openingTime.second}</div>
															<c:if test="${not empty schedule.comments[timeLoopStatus.index]}">
																<div style="text-transform: none; font-weight: 400; margin-top:-10px;">(${schedule.comments[timeLoopStatus.index]})</div>
															</c:if>
														</c:forEach>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</c:if>
									</c:if>
									<c:if test="${favorite.typeId == 2 }">
										${favorite.event.getEventScheduleDisplay(locale)}
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
										${favorite.manifestation.getManifestationScheduleDisplay(locale) }
									</c:if>
								</div>
								
								
								
								<c:if test="${favorite.typeId == 1 && favorite.place.isEnabled()}">	
									<div class="favoris-teaser__crowding">
										<c:set var="occupationState" value="${favorite.place.getRealTime('1')}" />
										<div class="favoris-teaser__crowding-amount favoris-teaser__crowding-amount--${occupationState.cssClass}">${occupationState.occupation}</div>
										<div class="favoris-teaser__crowding-label"><liferay-ui:message key="${occupationState.label}" /></div>
									</div>
								</c:if>
							</div>
					</a> 
				
					<liferay-portlet:actionURL name="deleteFavorite" var="deleteFavoriteURL">
						<portlet:param name="favoriteId" value="${favorite.favoriteId}" />
					</liferay-portlet:actionURL>
					<a href="${deleteFavoriteURL }" class="favoris-teaser__trash" data-favconfirm="delete"><liferay-ui:message key="delete-favorite" /></a></li>
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
