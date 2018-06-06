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

<div class="seu-container">

	<c:choose>
		<c:when test="${noconfig}">
			<liferay-ui:message key="no-config" />
		</c:when>
		<c:when test="${empty places}">
			<liferay-ui:message key="no-places-x" arguments="${category.getTitle(locale)}" />
		</c:when>
		<c:otherwise>	
		
			<h1><liferay-ui:message key="title-schedule" /> ${category.getTitle(locale)}</h1>

			<!-- Formulaire de recherche -->
			<aui:form action="${changeDataURL}" method="post" name="fm" id="search-asset-form" cssClass="seu-view-filters">
				<div class="seu-filter-line">
				    <div class="widget" style="width: calc( 50% - 15px);">
				        <div class="title">
				           <label for="place"><liferay-ui:message key="eu.place" /></label>
				        </div>
				        <div class="content">
				            <select class="toCustomSelect" id="place" name="<portlet:namespace />placeId">
				            	<aui:option value=""></aui:option>
				                <c:forEach var="place" items="${places}">
				                    <c:choose>
				                        <c:when test="${place.placeId == placeId}">
				                            <aui:option value="${place.placeId}" selected="true" >
				                                ${place.getAlias(locale)}
				                            </aui:option>
				                        </c:when>
				                        <c:otherwise>
				                            <aui:option value="${place.placeId}" >
				                                ${place.getAlias(locale)}
				                            </aui:option>
				                        </c:otherwise>
				                    </c:choose>
				                </c:forEach>
				            </select>
				        </div>
				    </div>
				    
				    <div class="widget">
				        <div class="title">
				            <label for="date-start"><liferay-ui:message key="eu.date" /></label>
				        </div>
				        <div class="content">
				            <input name="date" data-type="date" type="text" id="date" placeholder="JJ/MM/AAAA" 
				              value="${selectedDay}/${selectedMonth + 1}/${selectedYear}">
				            <input type="hidden" name="<portlet:namespace />day" data-name="dateDay" value="${selectedDay}" />
				            <input type="hidden" name="<portlet:namespace />month" data-name="dateMonth" value="${selectedMonth}" />
				            <input type="hidden" name="<portlet:namespace />year" data-name="dateYear" value="${selectedYear}" />
				        </div>
				    </div>
				</div>
			    
			    <div class="seu-btn-line">
			        <button type="submit" class="seu-btn-square seu-filled seu-core">
			            <span class="seu-flexbox">
			                <span class="seu-btn-text"><liferay-ui:message key="search" /></span>
			                <span class="seu-btn-arrow"></span>
			            </span>
			        </button>
			    </div>
			</aui:form>			

			
			<aui:button-row>
			        <a href="${previousURL}" class="seu-btn-square seu-filled seu-core pull-left seu-prev">
			            <span class="seu-flexbox">
			                <span class="seu-btn-arrow"></span>
			                <span class="seu-btn-text"><liferay-ui:message key="previous" /></span>
			            </span>
			        </a>
			        <a href="${nextURL}" class="seu-btn-square seu-filled seu-core pull-right">
			            <span class="seu-flexbox">
			                <span class="seu-btn-text"><liferay-ui:message key="next" /></span>
			                <span class="seu-btn-arrow"></span>
			            </span>
			        </a>
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
							<c:choose>
								<c:when test="${piscine}">
							        <th class="occupation" >
							        	<div><liferay-ui:message key="live-attendance" /></div>
							        </th>
								</c:when>
								<c:when test="${parking}">
							        <th class="occupation" >
							        	<div><liferay-ui:message key="occupation" /></div>
							        </th>
								</c:when>
								<c:when test="${mairie}">
							        <th class="occupation" >
							        	<div><liferay-ui:message key="estimated-time" /></div>
							        </th>
								</c:when>
							</c:choose>
							<fmt:formatDate value="${now}" type="date" var="shortNow" dateStyle="SHORT"/>
							<c:forEach var="date" items="${weekDates}" varStatus="loopStatus" >
								<fmt:formatDate value="${date}" type="date" var="shortDate" dateStyle="SHORT" />
								<fmt:formatDate value="${date}" type="date" var="dayOfWeek" pattern="EEEE" />
								<fmt:formatDate value="${date}" type="date" var="dateAndMonth" pattern="d MMMM" />
								<c:set var="isToday" value="${shortNow eq shortDate}" />
								<c:choose>
									<c:when test="${loopStatus.index eq 0}">
										<th class="first-day">
									</c:when>
									<c:otherwise>
										<th class="not-first-day">
									</c:otherwise>
								</c:choose>
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
								<c:if test="${piscine}">
										<c:set var="occupationState" value="${place.getRealTime('1')}" />
										<td rowspan="${place.getSubPlaces().size() + 2}" class="occupation-state" >
											<div class="crowded-amount ${occupationState.cssClass}">
			                                    ${occupationState.occupation}
			                                </div>
			                                <div class="crowded-label">
			                                	<liferay-ui:message key="${occupationState.label}" />
			                                </div>
								    	</td>
							    </c:if>
								<c:if test="${parking}">
										<c:set var="occupationState" value="${place.getRealTime('2')}" />
										<td rowspan="${place.getSubPlaces().size() + 1}" class="occupation-state" >
											<div class="crowded-amount ${occupationState.cssClass}">
			                                    ${occupationState.available}
			                                </div>
			                                <div class="crowded-label">
			                                	<liferay-ui:message key="${occupationState.label}" />
			                                </div>
								    	</td>
							    </c:if>
								<c:if test="${mairie}">
										<c:set var="occupationState" value="${place.getRealTime('3')}" />
										<td rowspan="${place.getSubPlaces().size() + 2}" class="occupation-state" >
											<div class="crowded-amount ${occupationState.cssClass}" style="font-size: 1.5rem">
			                                    ${occupationState.occupation} <c:if test="${occupationState.occupation != '-'}">min</c:if>
			                                </div>
			                                <div class="crowded-label">
			                                	<liferay-ui:message key="${occupationState.label}" />
			                                </div>
								    	</td>
							    </c:if>
								<c:forEach var="horaires" items="${place.getPlaceSchedule(jourChoisi, 5, locale)}" varStatus="status" >
									<c:choose>
										<c:when test="${status.index eq 0}">
											<td class="first-day">
										</c:when>
										<c:otherwise>
											<td class="not-first-day">
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
															<div style="font-weight: 400;margin-top:-10px;margin-bottom: 5px;font-size: 0.9em; ${isException ? 'color: #F44336' : '' }">(${placeSchedule.comments[loopStatus.index]})</div>
														</c:if>
													</c:forEach> 
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</td>
								</c:forEach>
							</tr>  
							<c:forEach var="subPlace" items="${place.getSubPlaces()}">
								<tr class="${placeStatus.index % 2 eq 0 ? 'bg-white' : 'bg-grey'}">
									 <td class="subplace-name">
									    ${subPlace.getName(locale)}
									</td> 

									<c:forEach var="horaires" items="${subPlace.getSubPlaceSchedule(jourChoisi, 5, locale)}" varStatus="status" >
										<c:choose>
											<c:when test="${status.index eq 0}">
												<td class="first-day">
											</c:when>
											<c:otherwise>
												<td class="not-first-day">
											</c:otherwise>
										</c:choose>
											<c:forEach var="subPlaceSchedule" items="${horaires.value}" varStatus="status">
												<c:set var="isException" value="${subPlaceSchedule.isException() || subPlaceSchedule.isPublicHoliday()}" />
												<c:if test="${isException}">
													<c:set var="hasException" value="${true}" />
												</c:if>
												<c:choose>
													<c:when test="${subPlaceSchedule.isClosed()}">
														<div class="opening-time ${isException ? 'exception' : '' }">
															<liferay-ui:message key="eu.closed" />
														</div>
													</c:when>
													<c:when test="${subPlaceSchedule.isAlwaysOpen()}">
														<div class="opening-time ${isException ? 'exception' : '' }">
												        	<liferay-ui:message key="always-open" />
												        </div>
													</c:when>
													<c:otherwise>
														<c:forEach items="${subPlaceSchedule.openingTimes}" var="openingTime" varStatus="loopStatus">
															<div class="opening-time ${isException ? 'exception' : '' }">
																${openingTime.first} - ${openingTime.second}	
															</div>
															<c:if test="${not empty subPlaceSchedule.comments[loopStatus.index]}">
																	<div style="font-weight: 400;margin-top:-10px;margin-bottom: 5px;font-size: 0.9em;${isException ? 'color: #F44336' : '' }">(${subPlaceSchedule.comments[loopStatus.index]})</div>
																</c:if>
														</c:forEach>  
													</c:otherwise>
												</c:choose>   
											</c:forEach>
										</td>
									</c:forEach>
								</tr>
							</c:forEach>
							<tr class="${placeStatus.index % 2 eq 0 ? 'bg-white' : 'bg-grey'} see-more-row">
								<td style="padding-top: 0">
									<a href="${homeURL}lieu/-/entity/sig/${place.getSIGid()}"
										class="seu-btn-square seu-bordered seu-core" 
										title="${place.getAlias(locale)}"> 
											<span class="seu-flexbox"> 
												<span class="seu-btn-text">Voir plus</span> 
												<span class="seu-btn-arrow"></span> 
											</span>
									</a>
								</td>
								<td class="first-day ${occupationState.cssClass}">
									<div class="mobile-occupation-state">
										<div class="mobile-crowded-amount">
		                                    ${occupationState.occupation}
		                                </div>
		                                <div class="mobile-crowded-label">
		                                	<liferay-ui:message key="${occupationState.label}" />
										</div>
									</div>
								</td>
								<td class="not-first-day"></td>
								<td class="not-first-day"></td>
								<td class="not-first-day"></td>
								<td class="not-first-day"></td>
							</tr>
						</c:forEach>
						
						<tr>
					        <th class="place" >
					        	<div>
					        		${category.getTitle(locale)}
					        	</div>
					        </th>
							<c:choose>
								<c:when test="${piscine}">
							        <th class="occupation" >
							        	<div><liferay-ui:message key="live-attendance" /></div>
							        </th>
								</c:when>
								<c:when test="${parking}">
							        <th class="occupation" >
							        	<div><liferay-ui:message key="occupation" /></div>
							        </th>
								</c:when>
								<c:when test="${mairie}">
							        <th class="occupation" >
							        	<div><liferay-ui:message key="estimated-time" /></div>
							        </th>
								</c:when>
							</c:choose>
							<c:forEach var="date" items="${weekDates}" varStatus="loopStatus" >							
								<fmt:formatDate value="${date}" type="date" var="shortDate" dateStyle="SHORT" />
								<fmt:formatDate value="${date}" type="date" var="dayOfWeek" pattern="EEEE" />
								<fmt:formatDate value="${date}" type="date" var="dateAndMonth" pattern="d MMMM" />
								<c:set var="isToday" value="${shortNow eq shortDate}" />
								<c:choose>
									<c:when test="${loopStatus.index eq 0}">
										<th class="first-day">
									</c:when>
									<c:otherwise>
										<th class="not-first-day">
									</c:otherwise>
								</c:choose>
									<c:if test="${isToday}">
										<div class=""><liferay-ui:message key="today" /></div>
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
		        <a href="${previousURL}" class="seu-btn-square seu-filled seu-core pull-left seu-prev">
		            <span class="seu-flexbox">
		                <span class="seu-btn-arrow"></span>
		                <span class="seu-btn-text"><liferay-ui:message key="previous" /></span>
		            </span>
		        </a>
		        <a href="${nextURL}" class="seu-btn-square seu-filled seu-core pull-right">
		            <span class="seu-flexbox">
		                <span class="seu-btn-text"><liferay-ui:message key="next" /></span>
		                <span class="seu-btn-arrow"></span>
		            </span>
		        </a>
			</aui:button-row>
			
			<!-- Message pour exceptions -->
			<c:if test="${hasException}">
				<div style="color: #F44336; font-weight: bold; font-size: 1.6rem;">
					<liferay-ui:message key="eu.place.look-at-exceptionnal-schedule" />
				</div>
			</c:if>
       		<c:if test="${!empty exceptions}">
       			<div class="calendar-schedule-exceptions rte">
	                <h3 id="exceptions"><liferay-ui:message key="eu.exceptional-closings-openings" /></h3>
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
					<a href=""
						class="btn-more-schedules seu-btn-square seu-bordered seu-core" 
						title="${place.getAlias(locale)}" style="display: ${(nbExceptions <= 4) ? 'none' : 'inline-block'};"> 
							<span class="seu-flexbox"> 
								<span class="seu-btn-text">Voir plus</span> 
								<span class="seu-btn-arrow" style="transform: rotateZ(90deg);"></span> 
							</span>
					</a>
					<a href=""
						class="btn-less-schedules seu-btn-square seu-bordered seu-core" 
						title="${place.getAlias(locale)}" style="display: none;"> 
							<span class="seu-flexbox"> 
								<span class="seu-btn-text">Voir moins</span> 
								<span class="seu-btn-arrow" style="transform: rotateZ(-90deg);"></span> 
							</span>
					</a>
				</div>
				<script>
					/*<![CDATA[*/
						jQuery(".btn-more-schedules").on("click",function(a){
							a.preventDefault();
							jQuery(".more-schedules").show();
							jQuery(".btn-more-schedules").hide();
							jQuery(".btn-less-schedules").show()
						});
					
						jQuery(".btn-less-schedules").on("click",function(a){
							jQuery(document).scrollTop($("#exceptions").offset().top);  
							jQuery(".more-schedules").hide();
							jQuery(".btn-more-schedules").show();
							jQuery(".btn-less-schedules").hide()
						});
					/*]]>*/
				</script>
       		</c:if>
			
		</c:otherwise>
	</c:choose>	
</div>
