<!-- Vignette événement -->
<#setting locale = locale />
<#assign fromSearch = renderRequest.getAttribute("fromSearchPortlet")!false >
<#assign plId = 0 />
<#if fromSearch>
  <#assign plId = renderRequest.getAttribute("classNameLayoutId")[entry.getModelClassName()] />
</#if>

<@liferay_portlet.renderURL plid=plId var="detailURL" portletName="eu_strasbourg_portlet_entity_detail_EntityDetailPortlet" windowState="normal">
  <@liferay_portlet.param name="classPK" value="${entry.getEventId()}" />
  <@liferay_portlet.param name="title" value="${entry.getNormalizedTitle(locale)}" />
  <@liferay_portlet.param name="returnURL" value="${currentURL}" />
</@liferay_portlet.renderURL>

<@liferay_portlet.actionURL var="detailURLFilter">
  <@liferay_portlet.param name="userTargetClassId" value="${entry.assetEntry.classNameId}" />
  <@liferay_portlet.param name="userTargetClassPK" value="${entry.assetEntry.classPK}" />
  <@liferay_portlet.param name="userTargetTitle" value="${entry.getTitle(locale)}" />
  <@liferay_portlet.param name="detailURL" value="${detailURL}" />
  <@liferay_portlet.param name="searchLogId" value="${renderRequest.getAttribute('searchLogId')!0}" />
</@liferay_portlet.actionURL>

<a href="${detailURLFilter}" aria-label="${entry.getTitle(locale)?html}" title="${entry.getTitle(locale)?html}" class="event-thumbnail" style="background-image: url(${entry.getImageURL()})">
    <#if entry.getActivityTypeLabel(locale)?has_content>
        <div class="visit">
            <span>${entry.getActivityTypeLabel(locale)}</span>
        </div>
    </#if>
    <div class="info">
        <div class="title">
            <span>${entry.getTitle(locale)}</span>
        </div>
        <div class="dates">
            <span>${entry.getEventScheduleDisplay(locale)}</span>
        </div>
        <div class="museums">
            <span>${entry.getMuseumsLabel(locale)}</span>
        </div>
    </div>
</a>