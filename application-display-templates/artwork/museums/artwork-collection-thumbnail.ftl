<#setting locale = locale />
<#assign fromSearch = renderRequest.getAttribute("fromSearchPortlet")!false >
<#assign plId = 0 />
<#if fromSearch>
  <#assign plId = renderRequest.getAttribute("classNameLayoutId")[entry.getModelClassName()] />
</#if>

<@liferay_portlet.renderURL plid=plId var="detailURL" portletName="eu_strasbourg_portlet_entity_detail_EntityDetailPortlet" windowState="normal">
  <@liferay_portlet.param name="classPK" value="${entry.getCollectionId()}" />
  <@liferay_portlet.param name="returnURL" value="${currentURL}" />
</@liferay_portlet.renderURL>

<!-- Collection d'oeuvres : ${entry.getTitle(locale)} -->
<div class="entity-thumbnail artwork-collection-thumbnail ${entry.getSourceCSSClass()}">
  <div class="entity-thumbnail-image">
    <a href="${detailURL}">
      <img src="${entry.getImageURL()}" />
    </a>
  </div>
  <div class="entity-thumbnail-info">
    <#if entry.getSources()?has_content>
      <div class="entity-thumbnail-source">
          ${entry.getSources()[0].getTitle(locale)}
      </div>
    </#if>
    <div class="entity-thumbnail-title">
      <a href="${detailURL}">
        <h4>${entry.getTitle(locale)}</h4>
      </a>
    </div>
  </div>
</div>