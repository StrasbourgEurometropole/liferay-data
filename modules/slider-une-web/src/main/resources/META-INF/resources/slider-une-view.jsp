<%@ include file="/slider-une-init.jsp" %>
<c:set var="virtualStrasbourgHostName" value="${dc.getVirtualStrasbourgHostName()}"/>
<c:if test="${empty virtualStrasbourgHostName}">
	<c:set var="homeStrasbourgURL" value="/web/strasbourg.eu/"/>
</c:if>
<c:if test="${not empty virtualStrasbourgHostName}">
	<c:set var="homeStrasbourgURL" value="https://${virtualStrasbourgHostName}/"/>
</c:if>

<script type="text/javascript">
    <c:set var="editionCount" value="0" />
    <c:set var="newsCount" value="0" />
    <c:set var="eventCount" value="0" />
    var une_source = [
    <c:if test="${not empty dc.assetEntries}">
        <c:forEach var="curEntry" items="${dc.assetEntries}" varStatus="loopStatus">
            <c:if test="${curEntry.getClassName().equals('com.liferay.journal.model.JournalArticle')}">
                <c:set var="article" value="${curEntry.getAssetRenderer().getArticle()}"/>
                <c:set var="title" value="${dc.DeleteTag(dc.getJournalArticleTitle(article,locale))}"/>
                <c:set var="chapo" value="${dc.DeleteTag(dc.getJournalArticleCatcher(article,locale))}"/>
                <c:set var="image" value="${dc.getJournalArticleImage(article,locale)}"/>
                <c:set var="id" value="${article.getArticleId()}"/>
                <c:set var="groupId" value="${article.getGroupId()}"/>
                <c:set var="currentURL" value="${assetPublisherHelper.getAssetViewURL(renderRequest, renderResponse, curEntry)}"/>
                <c:set var="viewURL" value="${curEntry.getAssetRenderer().getURLViewInContext(renderRequest, renderResponse, currentURL)}"/>
                {
                    <c:if test="${dc.isMag(curEntry.getTagNames())}">
                        <c:set var="editionCount" value="${editionCount + 1}" />
                        category: 'mag',
                    </c:if>
                    <c:if test="${!dc.isMag(curEntry.getTagNames())}">
                        <c:set var="newsCount" value="${newsCount + 1}" />
                        category: 'actu',
                    </c:if>
                    title: '${dc.getJSONEncodedString(title)}',
                    lead: '${dc.getJSONEncodedString(chapo)}',
                    picture: '${image}',
                    link: '${viewURL}',
                    id: '${id}',
                    groupId: '${groupId}',
                    <c:if test="${dc.hasFocus(curEntry.getTagNames())}">
                      ,is_Big: true
                    </c:if>
                }
            </c:if>
            <c:if test="${curEntry.getClassName().equals('eu.strasbourg.service.agenda.model.Event')}">
                <c:set var="event" value="${curEntry.getAssetRenderer().getEvent()}"/>
                <c:set var="eventCount" value="${eventCount + 1}" />
                {
                    category: 'agenda',
                    title: '${dc.getJSONEncodedString(dc.DeleteTag(event.getTitle(locale)))}',
                    type: '${dc.getJSONEncodedString(dc.DeleteTag(event.getTypeLabel(locale)))}',
                    link: '${homeStrasbourgURL}evenement/-/entity/id/${event.eventId}',
                    ville: '${event.getCity(locale)} <c:if test="${not empty event.getCity(locale)}">-</c:if> ${dc.getJSONEncodedString(event.getPlaceAlias(locale))}',
                    id: '${event.eventId}',
                    <c:if test="${event.firstStartDate.equals(event.lastEndDate)}">
                        <fmt:formatDate value="${event.getFirstStartDate()}" pattern="dd.MM" type="date" var="firstStartDate" />
                        date_start: '',
                        date_end: '${firstStartDate}',
                        date_prefix: '<liferay-ui:message key="eu.event.the" />',
                        date_suffix: ''
                    </c:if>
                    <c:if test="${!event.firstStartDate.equals(event.lastEndDate)}">
                        <fmt:formatDate value="${event.getFirstStartDate()}" pattern="dd.MM" type="date" var="firstStartDate" />
                        <fmt:formatDate value="${event.getLastEndDate()}" pattern="dd.MM" type="date" var="lastEndDate" />
                        date_start: '${firstStartDate}',
                        date_end: '${lastEndDate}',
                        date_prefix: '<liferay-ui:message key="eu.event.from-date" />',
                        date_suffix: '<liferay-ui:message key="eu.event.to" />',
                    </c:if>
                    <c:if test="${dc.hasFocus(curEntry.getTagNames())}">
                      ,is_Big: true
                    </c:if>
                }
            </c:if>
            <c:if test="${!loopStatus.last}">,</c:if>
        </c:forEach>
    </c:if>
    ];
</script>
<section id="seu-une">
    <div class="seu-une-top-background"></div>
    <div class="seu-une-background"  style="background-image: url(/o/strasbourg-theme/images/blurry.jpg);"></div>
    <div class="seu-container seu-top-line">
        <h2 class="seu-section-title">
            <span class="seu-title"><liferay-ui:message key="eu.slider-one" /></span>
        </h2>
        <c:if test="${not empty dc.assetEntries && dc.showTags()}">
            <div class="seu-filters">
                <button class="seu-actu-filter seu-actif" data-category="tous">
                    <span class="seu-flexbox">
                        <span class="seu-btn-text"><liferay-ui:message key="all" /></span>
                    </span>
                </button>
                <c:if test="${newsCount > 0}">
                    <button class="seu-actu-filter" data-category="actu">
                        <span class="seu-flexbox">
                            <span class="seu-btn-text"><liferay-ui:message key="actu" /></span>
                        </span>
                    </button>
                </c:if>
                <c:if test="${editionCount > 0}">
                    <button class="seu-actu-filter" data-category="mag">
                        <span class="seu-flexbox">
                            <span class="seu-btn-text"><liferay-ui:message key="mag" /></span>
                        </span>
                    </button>
                </c:if>
                <c:if test="${eventCount > 0}">
                    <button class="seu-actu-filter" data-category="agenda">
                        <span class="seu-flexbox">
                            <span class="seu-btn-text"><liferay-ui:message key="agenda" /></span>
                        </span>
                    </button>
                </c:if>
            </div>
        </c:if>
    </div>
    <c:if test="${not empty dc.assetEntries}">
        <div class="seu-slider-une-container">
            <div class="seu-slider-overflow">
                <div class="seu-slider"></div>
            </div>
            <div class="owl-nav">
                <button class="seu-owl-prev">
                    <span class="seu-picto"></span>
                </button>
                <button class="seu-owl-next">
                    <span class="seu-picto"></span>
                </button>
            </div>
        </div>
        <div id="seu-une-templates">
            <!--Template Event-->
            <div class="seu-item seu-event">
                <a href="__link__" class="seu-link" title="__title__">
                    <div class="seu-date">
                        <div class="seu-date-sup">
                            <span class="seu-date-prefix">__date_prefix__</span>
                            <span class="seu-date-start">__date_start__</span>
                            <span class="seu-date-suffix">__date_suffix__</span>
                        </div>
                        <div class="seu-date-end">__date_end__</div>
                    </div>
                    <div class="seu-title dotme" data-dot="3">__title__</div>
                    <div class="seu-ville">__ville__</div>
                    <div class="item-categories" data-dot="3">__type__</div>
                </a>
                <a href="#" class="seu-add-favorites"
                    data-type="2"
                    data-title="__title__"
                    data-url="__link__"
                    data-id="__id__" >
                    <span><liferay-ui:message key="eu.add-to-favorite" /></span>
                </a>
            </div>
            <!-- Template actu-->
            <div class="seu-item seu-actu">
                <a href="__link__" class="seu-link" title="__title__">
                    <div class="seu-text">
                        <div class="seu-title dotme" data-dot="3">__title__</div>
                        <div class="seu-lead dotme" data-dot="3">__lead__</div>
                    </div>
                    <div class="seu-picture" style="background-image: url(__picture__)">
                    </div>
                </a>
                <a href="#" class="seu-add-favorites"
                    data-type="6"
                    data-title="__title__"
                    data-url="__link__"
                    data-id="__id__"
                    data-group-id="__groupId__" >
                    <span><liferay-ui:message key="eu.add-to-favorite" /></span>
                </a>
            </div>
            <!--Template Mag-->
            <div class="seu-item seu-mag">
                <a href="__link__" class="seu-link" title="__title__">
                    <div class="seu-picture" style="background-image: url(__picture__)">
                    </div>
                    <div class="seu-mag-logo">
                        <div class="seu-mag-text"><liferay-ui:message key="mag" /></div>
                        <div class="seu-mag-picto"></div>
                    </div>
                    <div class="seu-text">
                        <div class="seu-title dotme" data-dot="3">__title__</div>
                        <div class="seu-lead dotme" data-dot="3">__lead__</div>
                    </div>
                </a>
                <a href="#" class="seu-add-favorites"
                    data-type="6"
                    data-title="__title__"
                    data-url="__link__"
                    data-id="__id__"
                    data-group-id="__groupId__" >
                    <span><liferay-ui:message key="eu.add-to-favorite" /></span>
                </a>
            </div>
        </div>
    </c:if >
    <div class="seu-btn-line">
        <a href="/actualite" class="seu-btn-square seu-filled seu-second" title="<liferay-ui:message key="eu.news-new" />">
            <span class="seu-flexbox">
                <span class="seu-btn-text"><liferay-ui:message key="eu.new" /></span>
                <span class="seu-btn-arrow"></span>
            </span>
        </a>
    </div>
</section>