<%@ include file="/place-schedule-init.jsp" %>
<jsp:useBean id="now" class="java.util.Date" />
<fmt:setLocale value="${locale}" />
<c:choose>
	<c:when test="${empty themeDisplay.scopeGroup.publicLayoutSet.virtualHostname or themeDisplay.scopeGroup.isStagingGroup()}">
		<c:set var="homeURL" value="/web${layout.group.friendlyURL}/" />
	</c:when>
	<c:otherwise>
		<c:set var="homeURL" value="/" />
	</c:otherwise>
</c:choose>

<liferay-portlet:renderURL var="previousURL">
	<portlet:param name="categoryId" value="${category.categoryId}" />
	<portlet:param name="date" value="${previous}" />
	<portlet:param name="placeId" value="${placeId}" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="nextURL" >
	<portlet:param name="categoryId" value="${category.categoryId}" />
	<portlet:param name="date" value="${next}" />
	<portlet:param name="placeId" value="${placeId}" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="changeDataURL">
	<portlet:param name="categoryId" value="${category.categoryId}" />
</liferay-portlet:renderURL>

<main class="place-schedule-portlet-page container">
    <div class="place-schedule-form">
        <c:choose>
            <c:when test="${noconfig}">
                <liferay-ui:message key="no-config" />
            </c:when>
            <c:when test="${empty places}">
                <liferay-ui:message key="no-places-x" arguments="${category.getTitle(locale)}" />
            </c:when>
            <c:otherwise>
                <!-- Formulaire de recherche -->
                <aui:form action="${changeDataURL}" method="post" name="fm" id="place-schedule-form">
                    <div class="place-schedule-fields">
                        <div class="place-selection">
                            <legend><liferay-ui:message key="eu.place" /></legend>
                            <div class="place-selection-control">
                                <select id="place2" name="<portlet:namespace />placeId">
                                    <option value="" ></option>
                                    <option value="-1">&nbsp;</option>
                                    <c:forEach var="place" items="${places}">
                                        <aui:option value="${place.placeId}"
                                            label="${place.getAlias(locale)}"
                                            selected="${place.placeId == placeId}" />
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="date-selection">
                            <legend><liferay-ui:message key="eu.date" /></legend>
                            <div class="date-selection-control">
                                <input class="date" name="date" data-type="date" type="text" id="date" placeholder="JJ/MM/AAAA"
                                    value="${selectedDay}/${selectedMonth + 1}/${selectedYear}" aria-label="<liferay-ui:message key="eu.date" />">
                                <input type="hidden" name="<portlet:namespace />day" data-name="dateDay" value="${selectedDay}" />
                                <input type="hidden" name="<portlet:namespace />month" data-name="dateMonth" value="${selectedMonth}" />
                                <input type="hidden" name="<portlet:namespace />year" data-name="dateYear" value="${selectedYear}" />
                            </div>
                        </div>
                    </div>

                    <a id="submit" class="button2" aria-label="<liferay-ui:message key="search" />" title="<liferay-ui:message key="search" />"><liferay-ui:message key="search" /></a>
                </aui:form>


                <aui:button-row>
                    <a href="${previousURL}" id="previous" class="button1" aria-label="<liferay-ui:message key="previous" />" title="<liferay-ui:message key="previous" />"><liferay-ui:message key="previous" /></a>
                    <a href="${nextURL}" id="next" class="button1" aria-label="<liferay-ui:message key="next" />" title="<liferay-ui:message key="next" />"><liferay-ui:message key="next" /></a>
                </aui:button-row>

                <aui:fieldset>
                    <c:if test="${!empty selectedPlaces}">
                        <table>
                            <tr>
                                <th class="place" >
                                    <div>
                                        ${category.getTitle(locale)}
                                    </div>
                                </th>
                                <th class="occupation" >
                                    <div><liferay-ui:message key="live-attendance" /></div>
                                </th>
                                <fmt:formatDate value="${now}" type="date" var="shortNow" dateStyle="SHORT"/>
                                <c:forEach var="date" items="${weekDates}" varStatus="loopStatus" >
                                    <fmt:formatDate value="${date}" type="date" var="shortDate" dateStyle="SHORT" />
                                    <fmt:formatDate value="${date}" type="date" var="dayOfWeek" pattern="EEEE" />
                                    <c:choose>
                                        <c:when test="${locale eq 'en_EN'}">
                                            <fmt:formatDate value="${date}" type="date" var="dateAndMonth" pattern="d MMMM" />
                                        </c:when>
                                        <c:when test="${locale eq 'de_DE'}">
                                            <fmt:formatDate value="${date}" type="date" var="dateAndMonth" pattern="d. MMMM" />
                                        </c:when>
                                        <c:otherwise>
                                            <fmt:formatDate value="${date}" type="date" var="dateAndMonth" pattern="d MMMM" />
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${loopStatus.index eq 0}">
                                            <th class="first-day">
                                        </c:when>
                                        <c:otherwise>
                                            <th class="not-first-day">
                                        </c:otherwise>
                                    </c:choose>
                                        <c:set var="isToday" value="${shortNow eq shortDate}" />
                                        <c:if test="${isToday}">
                                            <div class="today-label"><liferay-ui:message key="today" /></div>
                                        </c:if>
                                        <div class="day-of-week">${dayOfWeek}</div>
                                        <div class="date-and-month">${dateAndMonth}</div>
                                    </th>
                                </c:forEach>
                            </tr>
                            <c:set var="hasException" value="${false}" />
                            <c:forEach var="place" items="${selectedPlaces}" varStatus="placeStatus">
                                <tr class="${placeStatus.index % 2 eq 0 ? 'bg-white' : 'bg-grey'}">
                                    <td class="place-name">
                                        ${place.getAlias(locale)}
                                    </td>
                                    <c:set var="occupationState" value="${place.getRealTime('1')}" />
                                    <td rowspan="2" class="occupation-state" >
                                        <div class="crowded-amount ${occupationState.cssClass}">
                                            ${occupationState.occupationLabel}
                                        </div>
                                        <div class="crowded-label">
                                            <liferay-ui:message key="${occupationState.label}" />
                                        </div>
                                    </td>
                                    <c:if test="${place.hasURLSchedule}">
                                        <c:set var="occupationState" value="${place.getRealTime('3')}" />
                                        <td rowspan="2">
                                            <div>
                                                <a href="${place.getScheduleLinkURL(locale)}" target="_blank" title="${place.getScheduleLinkName(locale)} (<liferay-ui:message key="eu.new-window" />)">
                                                    <span class="seu-btn-text">${place.getScheduleLinkName(locale)}</span>
                                                </a>
                                            </div>
                                        </td>
                                    </c:if>
                                    <c:if test="${!place.hasURLSchedule}">
                                        <c:forEach var="horaires" items="${place.getPlaceSchedule(jourChoisi, 7, locale)}" varStatus="status" >
                                            <c:choose>
                                                <c:when test="${status.index eq 0}">
                                                    <td class="first-day" rowspan="2">
                                                </c:when>
                                                <c:otherwise>
                                                    <td class="not-first-day" rowspan="2">
                                                </c:otherwise>
                                            </c:choose>
                                                <c:forEach var="placeSchedule" items="${horaires.value}" varStatus="status">
                                                    <c:set var="isException" value="${placeSchedule.isException() || placeSchedule.isPublicHoliday()}" />
                                                    <c:if test="${isException}">
                                                        <c:set var="hasException" value="${true}" />
                                                    </c:if>
                                                    <c:choose>
                                                        <c:when test="${placeSchedule.isClosed()}">
                                                            <div class="opening-time ${isException ? 'exception' : '' }">
                                                                <liferay-ui:message key="eu.closed" />
                                                            </div>
                                                        </c:when>
                                                        <c:when test="${placeSchedule.isAlwaysOpen()}">
                                                            <div class="opening-time ${isException ? 'exception' : '' }">
                                                                <liferay-ui:message key="always-open" />
                                                            </div>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:forEach items="${placeSchedule.openingTimes}" var="openingTime" varStatus="loopStatus">
                                                                <div class="opening-time ${isException ? 'exception' : '' }">
                                                                ${openingTime.first} - ${openingTime.second}
                                                                </div>
                                                                <c:if test="${not empty placeSchedule.comments[loopStatus.index]}">
                                                                    <div>(${placeSchedule.comments[loopStatus.index]})</div>
                                                                </c:if>
                                                            </c:forEach>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </td>
                                        </c:forEach>
                                    </c:if>
                                </tr>
                                <tr class="${placeStatus.index % 2 eq 0 ? 'bg-white' : 'bg-grey'} see-more-row">
                                    <td style="padding-top: 0">
                                        <a href="${homeURL}lieu/-/entity/sig/${place.getSIGid()}/${place.getNormalizedAlias(locale)}"
                                            class="button2" title="<liferay-ui:message key="eu.see-more" />" aria-label="<liferay-ui:message key="eu.see-more" />">
                                            <liferay-ui:message key="eu.see-more" />
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>

                            <tr>
                                <th class="place" >
                                    <div>
                                        ${category.getTitle(locale)}
                                    </div>
                                </th>
                                <th class="occupation" >
                                    <div><liferay-ui:message key="live-attendance" /></div>
                                </th>
                                <c:forEach var="date" items="${weekDates}" varStatus="loopStatus" >
                                    <fmt:formatDate value="${date}" type="date" var="shortDate" dateStyle="SHORT" />
                                    <fmt:formatDate value="${date}" type="date" var="dayOfWeek" pattern="EEEE" />
                                    <c:choose>
                                        <c:when test="${locale eq 'en_EN'}">
                                            <fmt:formatDate value="${date}" type="date" var="dateAndMonth" pattern="d MMMM" />
                                        </c:when>
                                        <c:when test="${locale eq 'de_DE'}">
                                            <fmt:formatDate value="${date}" type="date" var="dateAndMonth" pattern="d. MMMM" />
                                        </c:when>
                                        <c:otherwise>
                                            <fmt:formatDate value="${date}" type="date" var="dateAndMonth" pattern="d MMMM" />
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${loopStatus.index eq 0}">
                                            <th class="first-day">
                                        </c:when>
                                        <c:otherwise>
                                            <th class="not-first-day">
                                        </c:otherwise>
                                    </c:choose>
                                        <c:set var="isToday" value="${shortNow eq shortDate}" />
                                        <c:if test="${isToday}">
                                            <div class="today-label"><liferay-ui:message key="today" /></div>
                                        </c:if>
                                        <div class="day-of-week">${dayOfWeek}</div>
                                        <div class="date-and-month">${dateAndMonth}</div>
                                    </th>
                                </c:forEach>
                            </tr>
                        </table>
                    </c:if>
                </aui:fieldset>

                <aui:button-row>
                    <a href="${previousURL}" id="previous2" class="button1" aria-label="<liferay-ui:message key="previous" />" title="<liferay-ui:message key="previous" />"><liferay-ui:message key="previous" /></a>
                    <a href="${nextURL}" id="next2" class="button1" aria-label="<liferay-ui:message key="next" />" title="<liferay-ui:message key="next" />"><liferay-ui:message key="next" /></a>
                </aui:button-row>

                <!-- Message pour exceptions -->
                <c:if test="${hasException}">
                    <div class="exceptions">
                        <liferay-ui:message key="eu.place.look-at-exceptionnal-schedule" />
                    </div>
                </c:if>
                <c:if test="${!empty exceptions}">
                    <div class="calendar-schedule-exceptions">
                        <span id="exceptions" class="title"><liferay-ui:message key="eu.exceptional-closings-openings" /></span>
                        <c:set var="nbExceptions" value="0" />
                        <ul>
                            <c:forEach var="exception" items="${exceptions}">
                                <c:set var="nbExceptions" value="${nbExceptions + 1}" />
                                <c:if test="${nbExceptions <= 4}">
                                    <li>
                                </c:if>
                                <c:if test="${nbExceptions > 4}">
                                    <li class="more-schedules" style="display: none;">
                                </c:if>
                                    <strong>
                                        ${exception.key[0]}
                                        <c:if test="${fn:length(exception.key) > 1}">
                                            - ${exception.key[1]}
                                        </c:if>
                                         -
                                        ${exception.value.period} :
                                    </strong>
                                    <c:if test="${exception.value.isClosed()}">
                                        <liferay-ui:message key="eu.closed" />
                                    </c:if>
                                    <c:if test="${!exception.value.isClosed()}">
                                        <c:forEach var="openingTime" items="${exception.value.openingTimes}">
                                            ${openingTime.first} - ${openingTime.second}
                                        </c:forEach>
                                    </c:if>
                                    - ${exception.value.getDescription()}
                                </li>
                            </c:forEach>
                        </ul>
                        <a href="" class="button3 btn-more-schedules" title="<liferay-ui:message key="eu.see-more" />"  aria-label="<liferay-ui:message key="eu.see-more" />"
                            style="display: ${(nbExceptions <= 4) ? 'none' : 'inline-block'};">
                            <liferay-ui:message key="eu.see-more" />
                        </a>
                        <a href="" class="button3 btn-less-schedules" title="<liferay-ui:message key="eu.see-less" />" aria-label="<liferay-ui:message key="eu.see-less" />"
                            style="display: none;">
                            <liferay-ui:message key="eu.see-less" />
                        </a>
                    </div>
                    <script>
                        /*<![CDATA[*/
                            jQuery(".btn-more-schedules").on("click",function(a){
                                a.preventDefault();
                                jQuery(".more-schedules").show();
                                jQuery(".btn-more-schedules").hide();
                                jQuery(".btn-less-schedules").show();
                            });

                            jQuery(".btn-less-schedules").on("click",function(a){
                                a.preventDefault();
                                jQuery(document).scrollTop($("#exceptions").offset().top);
                                jQuery(".more-schedules").hide();
                                jQuery(".btn-more-schedules").show();
                                jQuery(".btn-less-schedules").hide();
                            });

                            jQuery("#submit").on("click",function(a){
                                $('.place-schedule-form form').submit();
                            });
                        /*]]>*/
                    </script>
                </c:if>

            </c:otherwise>
        </c:choose>
    </div>
</main>


<liferay-util:html-bottom>
    <link href="/o/placescheduleweb/css/default.css" rel="stylesheet"></script>
</liferay-util:html-bottom>