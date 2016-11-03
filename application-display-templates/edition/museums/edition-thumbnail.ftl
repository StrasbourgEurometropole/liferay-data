<#assign fromSearch = renderRequest.getAttribute("fromSearchPortlet")!false >
<#assign plId = 0 />
<#if fromSearch>
  <#assign plId = renderRequest.getAttribute("classNameLayoutId")[entry.getModelClassName()] />
</#if>

<@liferay_portlet.renderURL plid=plId var="detailURL" portletName="eu_strasbourg_portlet_entity_detail_EntityDetailPortlet" windowState="normal">
  <@liferay_portlet.param name="classPK" value="${entry.getEditionId()}" />
  <@liferay_portlet.param name="returnURL" value="${currentURL}" />
</@liferay_portlet.renderURL>

<!-- Edition : ${entry.getTitle(locale)} -->
<div class="entity-thumbnail edition-tumbnail">
  <div class="entity-thumbnail-image">
    <a href="${detailURL}">
      <img src="${entry.getImageURL()}" />
    </a>
  </div>
  <div class="entity-thumbnail-info">
    <#if entry.getEditionGalleries()?has_content>
      <div class="entity-thumbnail-parent-title">
        <#list entry.getEditionGalleries() as gallery>
          ${gallery.getTitle(locale)}&nbsp;
        </#list>
      </div>
    </#if>
    <div class="entity-thumbnail-title">
      <a href="${detailURL}">
        <h4>${entry.getTitle(locale)}</h4>
      </a>
    </div>
  </div>
</div>