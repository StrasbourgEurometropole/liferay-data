<!-- Vignette manifestation -->
<#setting locale = locale />
<#assign fromSearch = renderRequest.getAttribute("fromSearchPortlet")!false >
<#assign plId = 0 />
<#if fromSearch>
  <#assign plId = renderRequest.getAttribute("classNameLayoutId")[entry.getModelClassName()] />
</#if>

<@liferay_portlet.renderURL plid=plId var="detailURL" portletName="eu_strasbourg_portlet_entity_detail_EntityDetailPortlet" windowState="normal">
  <@liferay_portlet.param name="classPK" value="${entry.getManifestationId()}" />
  <@liferay_portlet.param name="returnURL" value="${currentURL}" />
</@liferay_portlet.renderURL>

<@liferay_portlet.actionURL var="detailURLFilter">
  <@liferay_portlet.param name="userTargetClassId" value="${entry.assetEntry.classNameId}" />
  <@liferay_portlet.param name="userTargetClassPK" value="${entry.assetEntry.classPK}" />
  <@liferay_portlet.param name="userTargetTitle" value="${entry.getTitle(locale)}" />
  <@liferay_portlet.param name="detailURL" value="${detailURL}" />
  <@liferay_portlet.param name="searchLogId" value="${renderRequest.getAttribute('searchLogId')!0}" />
</@liferay_portlet.actionURL>

<div class="event portlet-event-item" headers="ishh_col-1">
  <div class="entry-image"> 
    <a href="${detailURLFilter}" title="${entry.getTitle(locale)}"">
      <img src="${entry.getImageURL()}" alt="">
    </a>
  </div>
  <div class="entry-header">
    <span class="category">${entry.getTypeLabel(locale)} - ${entry.getThemeLabel(locale)}</span>
    <h2>
      <a href="${detailURLFilter}" title="L’Elsässer Owe">
        ${entry.getTitle(locale)}
      </a>
    </h2>
    <span class="date">${entry.getManifestationScheduleDisplay(locale)}</span>
  </div>
  <footer class="entry-meta"> 
    <time></time>
    <a href="${detailURLFilter}" title="<@liferay_ui.message key="read-more" />" class="btn-more">
      <@liferay_ui.message key="read-more" />
    </a>
    <div class="clearfix"></div>
  </footer> 
</div>
