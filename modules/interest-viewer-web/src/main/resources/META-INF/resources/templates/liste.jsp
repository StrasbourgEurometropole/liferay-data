<%@ include file="/interest-viewer-init.jsp" %>
<c:if test="${empty dc.getVirtualHostName()}">
    <c:set var="homeURL" value="/web/strasbourg.eu/"/>
</c:if>
<c:if test="${not empty dc.getVirtualHostName()}">
    <c:set var="homeURL" value="${dc.getVirtualHostName()}/"/>
</c:if>

<c:if test="${not dc.hasInterest()}">
	<div class="seu-wi seu-wi-agenda"> 
		<div class="seu-container"> 
			<h2 class="seu-section-title"> 
				<span class="seu-title"><liferay-ui:message key="actu-agenda" /></span> 
			</h2> 
			<p class="no-interests">${dc.getNoInterestText()}</p>
		</div>
	</div>
</c:if>
<c:if test="${not empty dc.actusAndWebmags}">
	<div class="seu-container">
		<div class="seu-wi seu-wi-agenda seu-type--actu"> 
			<div class="seu-container"> 
				<h2 class="seu-section-title"> 
					<span class="seu-title"><liferay-ui:message key="news" /></span> 
				</h2> 
				<div class="seu-wi-content"> 
					<div class="seu-wi-grid unstyled seu-visible">
	        			<c:forEach var="curEntry" items="${dc.actusAndWebmags}">
			                <c:set var="article" value="${curEntry.getAssetRenderer().getArticle()}"/>
							<c:set var="currentURL" value="${assetPublisherHelper.getAssetViewURL(renderRequest, renderResponse, curEntry)}"/>
							<c:set var="viewURL" value="${curEntry.getAssetRenderer().getURLViewInContext(renderRequest, renderResponse, currentURL)}"/> 
							<div class="seu-wi-item seu-actu seu-has-picture"> 
								<a href="${viewURL}" class="seu-link" title="${dc.getJournalArticleTitle(article,locale)}"> 
									<div class="seu-text"> 
										<div class="seu-title dotme is-truncated" data-dot="3" style="word-wrap: break-word;">${dc.getJournalArticleTitle(article,locale)}</div> 
										<div class="seu-lead dotme is-truncated" data-dot="3" style="word-wrap: break-word;">${dc.getJournalArticleCatcher(article,locale)}</div> 
									</div> 
									<div> 
										<div class="seu-picture" style="background-image: url(${dc.getJournalArticleImage(article,locale)})"></div> 
									</div> 
								</a> 
							</div> 
						</c:forEach> 
					</div> 
				</div>  
				<div class="seu-btn-line"> 
					<a href="${dc.configuration.allNewsURL()}" class="seu-btn-square seu-bordered seu-core">
						<span class="seu-flexbox"> 
							<span class="seu-btn-text"><liferay-ui:message key="eu.all-news" /></span>
							<span class="seu-btn-arrow"></span>
						</span> 
					</a>
				</div>
			</div> 
		</div>
	</div>
</c:if>

<c:if test="${not empty dc.events}">
	<div class="seu-wi seu-wi-agenda"> 
		<div class="seu-container"> 
			<h2 class="seu-section-title"> 
				<span class="seu-title"><liferay-ui:message key="agenda" /></span> 
			</h2> 
			<div class="seu-wi-content"> 
				<div class="seu-wi-grid"> 
        			<c:forEach var="curEntry" items="${dc.events}" varStatus="loopStatus">
						<c:set var="event" value="${curEntry.getAssetRenderer().getEvent()}"/>
						<div class="seu-wi-item seu-has-ville"> 
							<a href="${homeURL}evenement/-/entity/id/${event.eventId}" class="seu-link" title="${event.getTitle(locale)}"> 
                            	<c:if test="${event.getFirstStartDate() != null}">
									<div class="seu-date"> 
										<div class="seu-date-sup"> 
	                                        <c:if test="${event.getFirstStartDate().equals(event.getLastEndDate())}">
	                                        	<fmt:formatDate value="${event.getFirstStartDate()}" pattern="dd.MM" type="date" var="firstStartDate" />
	                                            <span class="seu-date-prefix"><liferay-ui:message key="eu.event.the" /></span>
	                                        </c:if>
	                                        <c:if test="${!event.getFirstStartDate().equals(event.getLastEndDate())}">
	                                        	<fmt:formatDate value="${event.getFirstStartDate()}" pattern="dd.MM" type="date" var="firstStartDate" />
												<fmt:formatDate value="${event.getLastEndDate()}" pattern="dd.MM" type="date" var="lastEndDate" />
	                                            <span class="seu-date-prefix"><liferay-ui:message key="eu.event.from-the" /></span>
	                                        </c:if>
											<span class="seu-date-start"></span> 
											<span class="seu-date-suffix"></span> 
										</div> 
										<div class="seu-date-end">${firstStartDate}</div> 
									</div> 
	                            </c:if>
								<div class="seu-title dotme" data-dot="3" style="word-wrap: break-word;">${event.getTitle(locale)}</div> 
								<div class="seu-ville">${event.getPlaceAlias(locale)} - ${event.getPlaceCity(locale)}</div> 
								<div class="seu-lead dotme is-truncated" data-dot="3" style="word-wrap: break-word;">${event.getDescription(locale)}</div> 
							</a> 
						</div> 
					</c:forEach>
				</div>
			</div> 
			<div class="seu-btn-line"> 
				<a href="${dc.configuration.allEventsURL()}" class="seu-btn-square seu-bordered seu-core">
					<span class="seu-flexbox"> 
						<span class="seu-btn-text"><liferay-ui:message key="eu.all-events" /></span>
						<span class="seu-btn-arrow"></span>
					</span> 
				</a>
			</div>
		</div> 
	</div>
</c:if>

<style>
	#portlet_eu_strasbourg_portlet_interest_viewer_InterestViewerWebPortlet .no-interests{
		margin-top: 40px;
		margin-bottom: 40px;
	}
</style>