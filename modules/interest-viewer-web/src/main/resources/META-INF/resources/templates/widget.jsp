<%@ include file="/interest-viewer-init.jsp" %>
<c:if test="${empty dc.getVirtualHostName()}">
    <c:set var="homeURL" value="/web/strasbourg.eu/"/>
</c:if>
<c:if test="${not empty dc.getVirtualHostName()}">
    <c:set var="homeURL" value="https://${dc.getVirtualHostName()}/"/>
</c:if>
         
<c:if test="${not dc.hasInterest()}">
    <section id="actu-agenda-no-interest">
        <h2><liferay-ui:message key="actu-agenda" /></h2>
        <p class="no-interests">${dc.getNoInterestText()}</p>
    </section>
</c:if>
<c:if test="${dc.hasInterest() and not empty dc.entries}">
    <script type="text/javascript">
        <c:set var="newsCount" value="0"/>
        <c:set var="editionCount" value="0"/>
        <c:set var="eventCount" value="0"/>
        var mega_source = [
        	<c:forEach var="curEntry" items="${dc.entries}" varStatus="loopStatus">
        		<c:if test="${curEntry.getClassName().equals('com.liferay.journal.model.JournalArticle')}">
	                <c:set var="article" value="${curEntry.getAssetRenderer().getArticle()}"/>
	          		<c:set var="title" value="${dc.getJournalArticleTitle(article,locale)}"/>
					<c:set var="chapo" value="${dc.getJournalArticleCatcher(article,locale)}"/>
					<c:set var="image" value="${dc.getJournalArticleImage(article,locale)}"/>
					<c:set var="currentURL" value="${assetPublisherHelper.getAssetViewURL(renderRequest, renderResponse, curEntry)}"/>
					<c:set var="viewURL" value="${curEntry.getAssetRenderer().getURLViewInContext(renderRequest, renderResponse, currentURL)}"/>
	              {
	            	<c:if test="${dc.isMag(curEntry.getTagNames())}">
	                  <c:set var="editionCount" value="${editionCount + 1}"/>
	                  category: 'mag',
	  	            </c:if>
		          	<c:if test="${!dc.isMag(curEntry.getTagNames())}">
	                  <c:set var="newsCount" value="${newsCount + 1}"/>
	                  category: 'actu',
	  	            </c:if>
	                title: '${dc.getJSONEncodedString(title)}',
	                lead: '${dc.getJSONEncodedString(chapo)}',
	                picture: '${image}',
	                link: '${viewURL}'
		          	<c:if test="${dc.isFocus(curEntry.getTagNames())}">
	                  ,is_Big: true
		  	        </c:if>
	              }
	            </c:if>
        		<c:if test="${!curEntry.getClassName().equals('com.liferay.journal.model.JournalArticle')}">
	                <c:set var="eventCount" value="${eventCount + 1}"/>
					<c:set var="event" value="${curEntry.getAssetRenderer().getEvent()}"/>
	              {
	                category: 'agenda',
	                title: '${dc.getJSONEncodedString(event.getTitle(locale))}',
	                lead: '${dc.getJSONEncodedString(event.getDescription(locale))}',
	                link: '${homeURL}evenement/-/entity/id/${event.eventId}',
	                ville: '${event.getCity(locale)} <c:if test="${not empty event.getCity(locale)}">-</c:if> ${dc.getJSONEncodedString(event.getPlaceAlias(locale))}',
	                <c:if test="${event.getFirstStartDate().equals(event.getLastEndDate())}">
						<fmt:formatDate value="${event.getFirstStartDate()}" pattern="dd.MM" type="date" var="firstStartDate" />
						date_start: '',
						date_end: '${firstStartDate}',
						date_prefix: '<liferay-ui:message key="eu.event.the" />',
						date_suffix: ''
	                </c:if>
	        		<c:if test="${!event.getFirstStartDate().equals(event.getLastEndDate())}">
						<fmt:formatDate value="${event.getFirstStartDate()}" pattern="dd.MM" type="date" var="firstStartDate" />
						<fmt:formatDate value="${event.getLastEndDate()}" pattern="dd.MM" type="date" var="lastEndDate" />
						date_start: '${firstStartDate}',
						date_end: '${lastEndDate}',
						date_prefix: '<liferay-ui:message key="eu.event.from-date" />',
						date_suffix: '<liferay-ui:message key="eu.event.to" />'
	                </c:if>
		          	<c:if test="${dc.isFocus(curEntry.getTagNames())}">
	                  ,is_Big: true
		  	        </c:if>
	              }
	            </c:if>
        		<c:if test="${!loopStatus.last}">,
 	        	</c:if>
			</c:forEach>
        ];
    </script>
      
    <section id="actu-agenda">
        <!--<button class="delete-wi"></button>-->
        <h2><liferay-ui:message key="actu-agenda" /></h2>    
        <div id="slider--mega">
            <div class="top-line"> 
                <div class="filters">
                    <button class="actu-filter actif btn-round--grey" data-category="tous">
                        <span class="flexbox">
                            <span class="btn-text"><liferay-ui:message key="all" /></span>
                        </span>
                    </button>
                    <c:if test="${newsCount > 0}">
                        <button class="actu-filter btn-round--grey" data-category="actu">
                            <span class="flexbox">
                                <span class="btn-text"><liferay-ui:message key="actus" /></span>
                            </span>
                        </button>
                    </c:if>
                    <c:if test="${editionCount > 0}">
                        <button class="actu-filter btn-round--grey" data-category="mag">
                            <span class="flexbox">
                                <span class="btn-text"><liferay-ui:message key="mag" /></span>
                            </span>
                        </button>
                    </c:if>
                    <c:if test="${eventCount > 0}">
                        <button class="actu-filter btn-round--grey" data-category="agenda">
                            <span class="flexbox">
                                <span class="btn-text"><liferay-ui:message key="agenda" /></span>
                            </span>
                        </button>
                    </c:if>
                </div>
            </div>
            <div class="slider-mega-container">
                <div class="slider-overflow">
                    <div class="slider">
                    </div>
                </div>
                <div class="owl-nav">
                    <button class="owl-prev">
                        <span class="picto"></span>
                    </button>
                    <button class="owl-next">
                        <span class="picto"></span>
                    </button>
                </div>
            </div>
            <div id="mega-templates">
                <!--Template Event-->
                <div class="item event">
                    <a href="__link__" class="link" title="__title__">
                        <div class="date">
                            <div class="date-sup">
                                <span class="date-prefix">__date_prefix__</span>
                                <span class="date-start">__date_start__</span>
                                <span class="date-suffix">__date_suffix__</span>
                            </div>
                            <div class="date-end">__date_end__</div>
                        </div>
                        <div class="ville">__ville__</div>
                        <div class="title dotme" data-dot="3">__title__</div>
                        <div class="lead dotme" data-dot="7">__lead__</div>
                    </a>
                </div>

                <!-- Template actu-->
                <div class="item actu">
                    <a href="__link__" class="link" title="__title__">
                        <div class="text">
                            <div class="title dotme" data-dot="3">__title__</div>
                            <div class="lead dotme" data-dot="3">__lead__</div>
                        </div>
                        <div class="picture" style="background-image: url(__picture__)">
                        </div> 
                    </a>
                </div>

                <!--Template Mag-->
                <div class="item mag">
                    <a href="__link__" class="link" title="__title__">
                        <div class="picture" style="background-image: url(__picture__)">
                        </div>
                        <div class="mag-logo">
                            <div class="mag-text">Mag'</div>
                            <div class="mag-picto"></div>
                        </div>
                        <div class="text">
                            <div class="title dotme" data-dot="3">__title__</div>
                            <div class="lead dotme" data-dot="3">__lead__</div>
                        </div>
                    </a>
                </div>
            </div>
        </div>
    </section>
</c:if>