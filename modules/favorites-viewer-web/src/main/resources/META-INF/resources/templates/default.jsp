<%@ include file="/favorites-viewer-init.jsp" %>

<c:set var="locale" value="${themeDisplay.getLocale() }" />

<section id="favoris">

	<portlet:resourceURL id="hidePortlet" var="portletURL">
		<portlet:param name="portletName" value="last-favorites" />
	</portlet:resourceURL>
	
<%--     <button class="delete-wi" value="${portletURL}"></button>      --%>
    <h2>${dc.getPortletTitle('my-last-favorites')}</h2>
    <div class="favoris-grid">
		
		<c:if test="${empty dc.lastFavorites}">
			<p style="font-size: 15px;">
				${dc.getNoFavoriteText()}
			</p>
		</c:if>
		<c:forEach items="${dc.lastFavorites}" var="favorite">					
			<div class="favoris-teaser">
				<a href="${favorite.url}" class="favoris-teaser__link">
					<div class="favoris-teaser__type"><liferay-ui:message key="eu.${fn:toLowerCase(favorite.typeName) }" /></div>
					<div >
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
										<c:if test="${schedule.isClosed()}">
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
								<c:if test="${favorite.event.firstStartDate == favorite.event.lastEndDate }">
									<fmt:formatDate value="${favorite.event.firstStartDate}"
										var="formattedModifiedDate" type="date" pattern="dd.MM" />
									Le ${formattedModifiedDate}
								</c:if>
								<c:if test="${favorite.event.firstStartDate != favorite.event.lastEndDate }">
									${favorite.event.getEventScheduleDisplay(locale)}
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
								<c:if test="${favorite.manifestation.startDate == favorite.manifestation.endDate }">
									<fmt:formatDate value="${favorite.manifestation.startDate}"
										var="formattedModifiedDate" type="date" pattern="dd.MM" />
									Le ${formattedModifiedDate}
								</c:if>
								<c:if test="${favorite.manifestation.startDate != favorite.manifestation.endDate }">
									${favorite.manifestation.getManifestationScheduleDisplay(locale) }
								</c:if>
							</c:if>
						</div>
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
		                                    ${occupationState.occupation} <c:if test="${occupationState.occupation != '-'}">min</c:if>
							    	</c:if>
								</div>
								<div class="favoris-teaser__crowding-label"><liferay-ui:message key="${occupationState.label}" /></div>
							</div>
						</c:if>
					</div>
				</a> 
				<liferay-portlet:actionURL name="deleteFavorite" var="deleteFavoriteURL">
					<portlet:param name="favoriteId" value="${favorite.favoriteId}" />
				</liferay-portlet:actionURL>
				<a href="${deleteFavoriteURL }" class="favoris-teaser__trash" data-favconfirm="delete"><liferay-ui:message key="delete-favorite" /></a>
			</div>
		</c:forEach>

	</div>
    <a href="${showAllURL}" class="btn-square--bordered--core"><span class="flexbox"><span class="btn-text"><liferay-ui:message key="all-favorites" /></span><span class="btn-arrow"></span></span></a>
</section>