<%@ include file="/favorites-viewer-init.jsp" %>

<c:set var="locale" value="${themeDisplay.getLocale() }" />

<section id="favoris">
    <%-- Récupère le fait de plier ou déplier ce widget dans la config de la personnalisation --%>
    <c:set value="${dc.isFolded()}" var="isFolded" />
    <div class="buttons">
        <%-- Vérifie si ce widget peut être plié dans la config de la personnalisation --%>
        <c:if test="${dc.showRetractableButton()}">
            <button class="${isFolded?'retractable-folded-wi':'retractable-unfolded-wi'}" data-portlet-id="${themeDisplay.portletDisplay.id}"></button>
        </c:if>
        <%-- Vérifie si ce widget peut être masqué dans la config de la personnalisation --%>
        <c:if test="${dc.showDeleteButton()}">
            <button class="delete-wi" data-portlet-id="${themeDisplay.portletDisplay.id}"></button>
        </c:if>
    </div>

    <h2>${dc.getPortletTitle('my-favorites')}</h2>
    <div class="detail" ${dc.isFolded()?'style="display: none;"':''}>
        <div class="favoris-grid">

            <c:if test="${empty dc.myFavorites}">
                <p>
                    ${dc.getNoFavoriteText()}
                </p>
            </c:if>
            <c:forEach items="${dc.myFavorites}" var="favorite" varStatus="loopStatus">
                <div class="favoris-teaser type-${favorite.typeId}">
                    <a href="${favorite.url}" class="favoris-teaser__link">
                        <div class="favoris-teaser__type"><liferay-ui:message key="eu.${fn:toLowerCase(favorite.typeName) }" /></div>
                        <div >
                            <h3 class="favoris-teaser__title" data-dot="3"
                                style="word-wrap: break-word;">${favorite.title}</h3>

                            <div class="favoris-teaser__date">
                                <c:if test="${favorite.typeId == 1 }">
                                    <c:if test="${not empty favorite.place.periods}">
                                        <c:forEach items="${favorite.place.getPlaceSchedule(dc.todayCalendar, locale)}" var="schedule" varStatus="loopStatus">
                                            <c:if test="${!favorite.place.isOpenNow()}">
                                                <div>
                                                    <liferay-ui:message key="eu.closed" />
                                                </div>
                                            </c:if>
                                            <c:if test="${schedule.isAlwaysOpen()}">
                                                <div>
                                                    <liferay-ui:message key="always-open" />
                                                </div>
                                            </c:if>
                                            <c:if test="${not empty schedule.openingTimes}">
                                                <div>
                                                    <liferay-ui:message key="today-schedule" />
                                                    <c:forEach items="${schedule.openingTimes}" var="openingTime" varStatus="timeLoopStatus">
                                                        <div>${openingTime.first} - ${openingTime.second}</div>
                                                        <c:if test="${not empty schedule.comments[timeLoopStatus.index]}">
                                                            <div style="text-transform: none; font-weight: 400; margin-top:-10px;">(${schedule.comments[timeLoopStatus.index]})</div>
                                                        </c:if>
                                                    </c:forEach>
                                                </div>
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
                                <c:if test="${favorite.typeId == 14 }">
                                    <div>
                                        <liferay-ui:message key="eu.arret.next-bus-stop" />
                                    </div>
                                </c:if>
                            </div>

                            <div class="favoris-teaser__description">
                                <c:if test="${favorite.typeId == 2 }">
                                    <div>${favorite.event.getPlaceAlias(locale)} - ${favorite.event.getPlaceCity(locale)}</div>
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
                                                ${occupationState.occupation}
                                        </c:if>
                                    </div>
                                    <div class="favoris-teaser__crowding-label"><liferay-ui:message key="${occupationState.label}" /></div>
                                </div>
                            </c:if>

                            <c:if test="${favorite.typeId == 14}">
                                <c:set var="arretRealTime" value="${favorite.arret.arretRealTime}" />
                                <c:if test="${arretRealTime.size() == 0}" >
                                    <div class="tram-destination"><p class="tram-destination-name" style="height: auto"><liferay-ui:message key="eu.no-visit-found" /></p></div>
                                </c:if>
                                <c:if test="${arretRealTime.size() != 0}" >
                                    <a class="jspArrow${loopStatus.index} jspArrowUp jspDisabled"></a>
                                    <div class="scroll-pane${loopStatus.index}">
                                        <c:set var="ligneColors" value="${dc.ligneColors}" />
                                        <c:forEach items="${arretRealTime.subList(0,(arretRealTime.size() > 12)?12:arretRealTime.size())}" var="realTime">
                                            <c:set var="colors" value='${ligneColors.get(realTime.get("MonitoredVehicleJourney").get("PublishedLineName"))}' />
                                            <c:set var="backgroundColor" value='${not empty colors[0] ? colors[0] : "000000"}' />
                                            <c:set var="textColor" value='${not empty colors[1] ? colors[1] : "FFFFFF"}' />
                                            <div class="row tram-destination">
                                                <p class="tram-destination-letter">
                                                    <span class="transport-letters-icon" style="background:#${backgroundColor}; color:#${textColor};">
                                                        ${realTime.get("MonitoredVehicleJourney").get("PublishedLineName")}
                                                    </span>
                                                </p>
                                                <div class="tram-destination-name">
                                                    <p data-dot="2">
                                                        ${realTime.get("MonitoredVehicleJourney").get("DestinationName")}
                                                    </p>
                                                </div>
                                                <p class="tram-destination-schedule">
                                                    <strong>
                                                        <fmt:parseDate value='${realTime.get("MonitoredVehicleJourney").get("MonitoredCall").get("ExpectedDepartureTime")}'
                                                            var="nextStopDate" type="both" pattern="yyyy-MM-dd'T'HH:mm:ssX" />
                                                        <fmt:formatDate value='${nextStopDate}' var="formattedHour" type="date" pattern="HH:mm" />
                                                            ${formattedHour}
                                                    </strong>
                                                </p>
                                            </div>
                                        </c:forEach>
                                    </div>
                                    <a class="jspArrow${loopStatus.index} jspArrowDown ${(arretRealTime.size() <= 4)?'jspDisabled':''}"></a>
                                </c:if>
                            </c:if>
                        </div>
                    </a>
                </div>
            </c:forEach>
            <c:if test="${empty dc.favoritesSelected && not empty dc.myFavorites}">
                <p>
                    ${dc.getNoFavoriteSelectedText()}
                </p>
            </c:if>

        </div>
        <a href="${showAllURL}" class="btn-square--filled--core"><span class="flexbox"><span class="btn-text"><liferay-ui:message key="all-favorites" /></span><span class="btn-arrow"></span></span></a>
    </div>
</section>