<!-- Vignette édition -->
<#setting locale = locale />
<#assign fromSearch = renderRequest.getAttribute("fromSearchPortlet")!false >
<#assign plId = 0 />
<#if fromSearch>
  <#assign plId = renderRequest.getAttribute("classNameLayoutId")[entry.getModelClassName()] />
</#if>

<@liferay_portlet.renderURL plid=plId var="detailURL" portletName="eu_strasbourg_portlet_entity_detail_EntityDetailPortlet" windowState="normal">
  <@liferay_portlet.param name="classPK" value="${entry.getEditionId()}" />
  <@liferay_portlet.param name="returnURL" value="${currentURL}" />
</@liferay_portlet.renderURL>

<@liferay_portlet.actionURL var="detailURLFilter">
  <@liferay_portlet.param name="userTargetClassId" value="${entry.assetEntry.classNameId}" />
  <@liferay_portlet.param name="userTargetClassPK" value="${entry.assetEntry.classPK}" />
  <@liferay_portlet.param name="userTargetTitle" value="${entry.getTitle(locale)}" />
  <@liferay_portlet.param name="detailURL" value="${detailURL}" />
  <@liferay_portlet.param name="searchLogId" value="${renderRequest.getAttribute('searchLogId')!0}" />
</@liferay_portlet.actionURL>

<!-- Edition : ${entry.getTitle(locale)} -->
<div class="entity-thumbnail edition-tumbnail">
  <div class="entity-thumbnail-image">
    <a href="${detailURLFilter}">
      <img src="${entry.getImageURL()}" />
    </a>
  </div>
  <div class="entity-thumbnail-info">
    <#if entry.getPublishedEditionGalleries()?has_content>
      <div class="entity-thumbnail-parent-title">
        <#list entry.getPublishedEditionGalleries() as gallery>
          ${gallery.getTitle(locale)}&nbsp;
        </#list>
      </div>
    </#if>
    <div class="entity-thumbnail-title">
      <a href="${detailURLFilter}">
        <h4>${entry.getTitle(locale)}</h4>
      </a>
    </div>
  </div>
</div>
