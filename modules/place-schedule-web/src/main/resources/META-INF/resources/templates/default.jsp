<%@ include file="/place-schedule-init.jsp" %>
<jsp:useBean id="now" class="java.util.Date" />

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

<div class="container-fluid-1280 main-content-body">
	<c:choose>
		<c:when test="${noconfig}">
			<liferay-ui:message key="no-config" />
		</c:when>
		<c:when test="${empty places}">
			<liferay-ui:message key="no-places-x" arguments="${category.getTitle(locale)}" />
		</c:when>
		<c:otherwise>				
			<div class="row row-spacing">
				<form action="${changeDataURL}" method="post" name="fm">
				
					<div class="col-md-3" >
						<fmt:formatDate value="${jourChoisi}" pattern="yyyy-MM-dd" type="date" var="formattedDate"/>
						<aui:input type="date" name="date" value="${formattedDate}" inlineField="true" inlineLabel="left" label="date" style="margin-left: 10px;" 
						dayParam="day" dayValue="${selectedDay}" monthParam="month" monthValue="${selectedMonth}"
								yearParam="year" yearValue="${selectedYear}" />
					</div>
					
					<div class="col-md-6" >
						<aui:select class="form-control" name="placeId" label="select-place"  inlineField="true" inlineLabel="left" style="margin-left: 10px;" >
					        <aui:option value="" >
			        			${category.getTitle(locale).toUpperCase()}
			        		</aui:option>
							<c:forEach var="place" items="${places}">
			       				<c:if test="${place.placeId == placeId}">
							        <aui:option value="${place.placeId}" selected="true" >
					        			 - ${place.getAlias(locale)}
					        		</aui:option>
						        </c:if>	
			       				<c:if test="${place.placeId != placeId}">
							        <aui:option value="${place.placeId}" >
					        			 - ${place.getAlias(locale)}
					        		</aui:option>
						        </c:if>	
					        </c:forEach>
					    </aui:select>
					</div>
					
					<div class="col-md-2" >
						<aui:button cssClass="btn-lg" type="button" value="filter-places" onClick="document.forms['fm'].submit(); return false;"/>
					</div>
					
					<div class="col-md-1" ></div>
					
				</form>
			</div>
			
			
			<aui:fieldset>${textSchedule}</aui:fieldset>
			
			<h3><liferay-ui:message key="title-schedule" /> ${category.getTitle(locale)}</h3>
			
			<aui:button-row>
				<div class="left" >
					<aui:button cssClass="btn-lg" type="button" href="${previousURL}" value="previous" />
				</div>
				
				<div class="right" >
					<aui:button cssClass="btn-lg" type="button" href="${nextURL}" value="next" />
				</div>
			</aui:button-row>
				
			<aui:fieldset>
	       		<c:if test="${!empty selectedPlaces}">
					<table>
					    <tr>
					        <th class="place" >
					        	${category.getTitle(locale)}
					        </th>
							<c:choose>
								<c:when test="${piscine}">
							        <th class="occupation" >
							        	<liferay-ui:message key="attendance" />
							        </th>
								</c:when>
								<c:when test="${parking}">
							        <th class="occupation" >
							        	<liferay-ui:message key="occupation" />
							        </th>
								</c:when>
								<c:when test="${mairie}">
							        <th class="occupation" >
							        	<liferay-ui:message key="estimated-time" />
							        </th>
								</c:when>
							</c:choose>
							<fmt:formatDate value="${now}" type="date" var="formattedDateJour" dateStyle="SHORT"/>
							<c:forEach var="jour" items="${semaine}" varStatus="status" >
								<c:if test="${jour[1] != formattedDateJour}">
					        		<th class="jours screen" >
								</c:if>
								<c:if test="${jour[1] == formattedDateJour}">
					        		<th class="jours" >
									<c:set var="displayDate" value="${status.count}" />
								</c:if>
					        	${jour[0]}</th>
							</c:forEach>
					    </tr>
						<c:set var="color" value="1" />
						<c:forEach var="place" items="${selectedPlaces}">
							<tr class="color${place.SIGid}">
								<td >
								    <strong  class="title">${fn:toUpperCase(place.getAlias(locale))}</strong><br/>
								    ${place.getCity(locale)}<br />
									<liferay-portlet:renderURL var="detailURL" portletName="eu_strasbourg_portlet_entity_detail_EntityDetailPortlet" windowState="normal" plid="${plId}" >
									  <liferay-portlet:param name="classPK" value="${place.placeId}" />
									  <liferay-portlet:param name="returnURL" value="${currentURL}" />
									</liferay-portlet:renderURL>
									<strong><a href="${detailURL}" class="linkMuseum"><liferay-ui:message key="link-detail" /></a></strong>
								</td>
								<c:if test="${piscine}">
										<c:set var="occupationState" value="${place.getRealTime('1')}" />
										<td rowspan="${place.getSubPlaces().size() + 1}" class="${occupationState.getCssClass()}" >
											<liferay-ui:message key="${occupationState.getLabel()}" />
											<c:if test="${not empty occupationState.getOccupationLabel() and occupationState.getOccupationLabel() != '-'}">
												<br/>${occupationState.getOccupationLabel()}
												<c:if test="${occupationState.getOccupationLabel() <= 1}">
													<liferay-ui:message key="person" />
												</c:if>
												<c:if test="${occupationState.getOccupationLabel() > 1}">
													<liferay-ui:message key="persons" />
												</c:if>
											</c:if>
								    	</td>
							    </c:if>
								<c:if test="${parking}">
										<c:set var="occupationState" value="${place.getRealTime('2')}" />
										<td rowspan="${place.getSubPlaces().size() + 1}" class="${occupationState.getCssClass()}" >
											<c:if test="${empty occupationState.getAvailable()}">
												<liferay-ui:message key="${occupationState.getLabel()}" />
											</c:if>
											<c:if test="${not empty occupationState.getAvailable()}">
												${occupationState.getAvailable()} 
												<liferay-ui:message key="places_available" /><br/>
												<liferay-ui:message key="capacity" /> ${occupationState.getCapacity()} 
											</c:if>
								    	</td>
							    </c:if>
								<c:if test="${mairie}">
								
										<c:set var="occupationState" value="${place.getRealTime('3')}" />
										<td rowspan="${place.getSubPlaces().size() + 1}" class="${occupationState.getCssClass()}" >
											<liferay-ui:message key="${occupationState.getLabel()}" />
											<c:if test="${not empty occupationState.getOccupationLabel() and occupationState.getOccupationLabel() != '-'}">
												<br/>${occupationState.getOccupationLabel()}
											</c:if>
								    	</td>
							    </c:if>
							    <c:set var="hasURL" value="0" />
								<c:forEach var="period" items="${place.periods}" varStatus="status" >
									<c:if test="${!empty period.linkURL && !empty period.linkLabel}">  
										<td colspan="7">
									    	<a href="${period.getLinkURL(locale)}" target="_blank"> ${period.getLinkLabel(locale)}</a>
									    </td>   
										<c:set var="hasURL" value="1" />
										<c:set var="status.index" value="${items.size}" />
									</c:if>
								</c:forEach> 

								<c:if test="${hasURL == 0}">
									<c:forEach var="horaires" items="${place.getHoraire(jourChoisi, locale)}" varStatus="status" >
										<c:if test="${displayDate != status.count}">
							        		<td class="screen" >
										</c:if>
										<c:if test="${displayDate == status.count}">
							        		<td >
										</c:if>
											<c:forEach var="placeSchedule" items="${horaires.value}" varStatus="status">
												<c:if test="${placeSchedule.isException() || placeSchedule.isPublicHoliday()}">
													<span style="color:#B22222;">                              
												</c:if>
												<c:choose>
													<c:when test="${placeSchedule.isClosed()}">
														<liferay-ui:message key="eu.closed" />
													</c:when>
													<c:when test="${placeSchedule.isAlwaysOpen()}">
												        <liferay-ui:message key="always-open" />
													</c:when>
													<c:otherwise>
														<c:forEach items="${placeSchedule.openingTimes}" var="openingTime" varStatus="timeLoopStatus">
															${openingTime.first} - ${openingTime.second}
															<c:if test="${not timeLoopStatus.last}"><br></c:if>
														</c:forEach> 
													</c:otherwise>
												</c:choose>
												<c:if test="${placeSchedule.isException() || placeSchedule.isPublicHoliday()}">
													*</span>                             
												</c:if>
											</c:forEach>
										</td>
									</c:forEach>
								</c:if>
							</tr>  
							<c:forEach var="subPlace" items="${place.getSubPlaces()}">
								<tr class="color${color}">
									 <td class="subPlace">
									    <strong>${fn:toUpperCase(subPlace.getName(locale))}</strong>
									</td>
								    <c:set var="hasURL" value="0" />
									<c:forEach var="period" items="${subPlace.periods}"  varStatus="status">
										<c:if test="${!empty period.linkURL && !empty period.linkLabel}">  
											<td colspan="7">
										    	<a href="${period.getLinkURL(locale)}" target="_blank"> ${period.getLinkLabel(locale)}</a>
										    </td>   
											<c:set var="hasURL" value="1" />                    
										</c:if>
									</c:forEach> 

									<c:if test="${hasURL == 0}">
										<c:forEach var="horaires" items="${subPlace.getHoraire(jourChoisi, locale)}" varStatus="status" >
											<c:if test="${displayDate != status.count}">
								        		<td class="screen" >
											</c:if>
											<c:if test="${displayDate == status.count}">
								        		<td >
											</c:if>
												<c:forEach var="subPlaceSchedule" items="${horaires.value}" varStatus="status">
													<c:if test="${subPlaceSchedule.isException() || subPlaceSchedule.isPublicHoliday()}">
														<span style="color:#B22222;">                              
													</c:if>
													<c:choose>
														<c:when test="${subPlaceSchedule.isClosed()}">
															<liferay-ui:message key="eu.closed" />
														</c:when>
														<c:when test="${subPlaceSchedule.isAlwaysOpen()}">
													        <liferay-ui:message key="always-open" />
														</c:when>
														<c:otherwise>
															<c:forEach items="${subPlaceSchedule.openingTimes}" var="openingTime" varStatus="timeLoopStatus">
																${openingTime.first} - ${openingTime.second}
																<c:if test="${not timeLoopStatus.last}"><br></c:if>
															</c:forEach>  
														</c:otherwise>
													</c:choose>   
													<c:if test="${subPlaceSchedule.isException() || subPlaceSchedule.isPublicHoliday()}">
														*</span>                             
													</c:if>
												</c:forEach>
											</td>
										</c:forEach>
									</c:if>
								</tr>
							</c:forEach>
						</c:forEach>
					</table>
		       	</c:if>
			</aui:fieldset>
			
			<aui:button-row>
				<div class="left" >
					<aui:button cssClass="btn-lg" type="button" href="${previousURL}" value="previous" />
				</div>
				
				<div class="right" >
					<aui:button cssClass="btn-lg" type="button" href="${nextURL}" value="next" />
				</div>
			</aui:button-row>
			
       		<c:if test="${!empty exceptions}">
       			<div class="calendar-schedule-exceptions">
	                <strong  style="color:#B22222;">
	                	*<liferay-ui:message key="eu.exceptional-closings-openings" />
	                </strong>
	                <c:set var="nbExceptions" value="0" />
					<c:forEach var="exception" items="${exceptions}">
	               			<c:set var="nbExceptions" value="${nbExceptions + 1}" />
							<c:if test="${nbExceptions <= 4}">
	                			<p>
							</c:if>
							<c:if test="${nbExceptions > 4}">
	                			<p class="more-schedules">
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
							</p>
					</c:forEach>
					<a href="#" class="btn-more-schedules" title="Voir toutes les exceptions" style="display: ${(nbExceptions <= 4) ? 'none' : 'block'};"><span class="btn-icon icon icon-plus"></span></a>
					<a href="#horaires" class="btn-less-schedules" title="Masquer les exceptions" style="display: none;"><span class="btn-icon icon icon-minus"></span></a>
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
<liferay-util:html-top>
	<link href="/o/placescheduleweb/css/style.css" rel="stylesheet" type="text/css">
</liferay-util:html-top>

<style>
.place-schedule-portlet div[class^=col-] {
  padding: 0px 15px 0px 15px !important;
}

.place-schedule-portlet .left{
	float: left;
}

.place-schedule-portlet .right{
	float: right;
}

.place-schedule-portlet .center{
	vertical-align: middle;
	text-align: center;
}

.place-schedule-portlet table{
	width: 100%;
	border-spacing : 2px 5px;
	border-collapse: inherit;
	background-color: rgba(255, 255, 255, 0.15);

}

.place-schedule-portlet table tr{
	vertical-align: middle;
	background-color: #ddd;
	height: 100px;
}

.place-schedule-portlet table th{
	padding: 10px;
	text-align: center !important;
	font-size: 18px;
}

.place-schedule-portlet table td{
	text-align: center;
}

.place-schedule-portlet .place{
	text-align: left;
	padding: 10px;
	width: 13.5%;
}

.place-schedule-portlet .jours{
	width: 11%;
}

.place-schedule-portlet .occupation{
	width: 9.5%;
}

.white{
	background-color: white;
	color: black;	
}

.green{
	background-color: #97bf0c;
	color: white;
}

.orange{
	background-color: #e79615;
	color: white;
}

.red{
	background-color: #d40000;
	color: white;
}

.grey{
	background-color: #616161;
	color: white;
}

.black{
	background-color: black;
	color: white;
}
    

.place-schedule-portlet .linkMuseum{
	text-decoration: underline !important;
}

.place-schedule-portlet .subPlace{
	text-align: left;
	padding-left: 30px;
}


.place-schedule-portlet .color946_CUL_97 td{
	border-bottom: 10px #41C797 solid;
}

.place-schedule-portlet .color945_CUL_96 td{
	border-bottom: 10px #A30000 solid;
}

.place-schedule-portlet .color948_CUL_99 td{
	border-bottom: 10px #4C28D4 solid;
}

.place-schedule-portlet .color949_CUL_100 td{
	border-bottom: 10px #C79D49 solid;
}

.place-schedule-portlet .color942_CUL_93 td{
	border-bottom: 10px #3F2575 solid;
}

.place-schedule-portlet .color950_CUL_101 td{
	border-bottom: 10px #790C3A solid;
}

.place-schedule-portlet .color944_CUL_95 td{
	border-bottom: 10px #17B7CB solid;
}

.place-schedule-portlet .color947_CUL_98 td{
	border-bottom: 10px #628BA7 solid;
}

.place-schedule-portlet .color951_CUL_102 td{
	border-bottom: 10px #D42889 solid;
}

.place-schedule-portlet .color952_CUL_103 td{
	border-bottom: 10px #00A369 solid;
}

.place-schedule-portlet .color943_CUL_94 td{
	border-bottom: 10px #F3AC00 solid;
}

.place-schedule-portlet .color1704_CUL_167 td{
	border-bottom: 10px rgba(255, 255, 255, 0.15) solid;
}

.place-schedule-portlet .coloraud td{
	border-bottom: 10px #9528D4 solid;
}

.place-schedule-portlet .colorbib td{
	border-bottom: 10px #ED6B2A solid;
}

.place-schedule-portlet .noRealTime{
	background-color: white;
}

.place-schedule-portlet .closed{
	background-color: #616161;
}

.place-schedule-portlet .greenPeriod{
	background-color: #97bf0c;
}

.place-schedule-portlet .orangePeriod{
	background-color: #e79615;
}

.place-schedule-portlet .redPeriod{
	background-color: #d40000;
}

.place-schedule-portlet .blackPeriod{
	background-color: black;
}



.place-schedule-portlet .color946_CUL_97 .title{
	color: #41C797;
}

.place-schedule-portlet .color945_CUL_96 .title{
	color: #A30000;
}

.place-schedule-portlet .color948_CUL_99 .title{
	color: #4C28D4;
}

.place-schedule-portlet .color949_CUL_100 .title{
	color: #C79D49;
}

.place-schedule-portlet .color942_CUL_93 .title{
	color: #3F2575;
}

.place-schedule-portlet .color950_CUL_101 .title{
	color: #790C3A;
}

.place-schedule-portlet .color944_CUL_95 .title{
	color: #17B7CB;
}

.place-schedule-portlet .color947_CUL_98 .title{
	color: #628BA7;
}

.place-schedule-portlet .color951_CUL_102 .title{
	color: #D42889;
}

.place-schedule-portlet .color952_CUL_103 .title{
	color: #00A369;
}

.place-schedule-portlet .color943_CUL_94 .title{
	color: #F3AC00;
}

.place-schedule-portlet .color1704_CUL_167 .title{
	color: rgba(255, 255, 255, 0.15);
}

.place-schedule-portlet .coloraud .title{
	color: #9528D4;
}

.place-schedule-portlet .colorbib .title{
	color: #ED6B2A;
}

.screen{
	display: ;
}

.place-schedule-portlet .calendar-schedule-exceptions p{
	margin: 0px;
    padding: 0px;
    line-height: normal;
    width: 100%
}

.place-schedule-portlet .more-schedules{
	display: none;
}

.place-schedule-portlet .btns-schedule{
	width: 100%;
}

.place-schedule-portlet .btn-more-schedules{
    margin: 10px auto;
    padding: 5px 6px;
    width: 31px;
    height: 31px;
    background-color: white;
    background-repeat: no-repeat;
    background-position: center center;
    border: 2px solid #97bf0c;
    position: relative;
    box-sizing: border-box;
    color: #97bf0c;
    display: block ;
}

.place-schedule-portlet .btn-less-schedules{
    margin: 10px auto;
    padding: 5px 6px;
    width: 31px;
    height: 31px;
    background-color: white;
    background-repeat: no-repeat;
    background-position: center center;
    border: 2px solid #97bf0c;
    position: relative;
    box-sizing: border-box;
    color: #97bf0c;
    display: block;
}

/* Media queries
-------------------------------------------------*/
@media screen and (max-width: 800px) {

	.screen{
		display: none;
	}
}

</style>