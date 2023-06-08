<!-- Vignette élu -->
<#setting locale = locale />
<#assign fromSearch = renderRequest.getAttribute("fromSearchPortlet")!false >
<#assign plId = 0 />
<#if fromSearch>
  <#assign plId = renderRequest.getAttribute("classNameLayoutId")[entry.getModelClassName()] />
</#if>

<@liferay_portlet.renderURL plid=plId var="detailURL" portletName="eu_strasbourg_portlet_entity_detail_EntityDetailPortlet" windowState="normal">
  <@liferay_portlet.param name="classPK" value="${entry.getOfficialId()}" />
  <@liferay_portlet.param name="returnURL" value="${currentURL}" />
</@liferay_portlet.renderURL>

<@liferay_portlet.actionURL var="detailURLFilter">
  <@liferay_portlet.param name="userTargetClassId" value="${entry.assetEntry.classNameId}" />
  <@liferay_portlet.param name="userTargetClassPK" value="${entry.assetEntry.classPK}" />
  <@liferay_portlet.param name="userTargetTitle" value="${entry.getLastName()} ${entry.getFirstName()}" />
  <@liferay_portlet.param name="detailURL" value="${detailURL}" />
  <@liferay_portlet.param name="searchLogId" value="${renderRequest.getAttribute('searchLogId')!0}" />
</@liferay_portlet.actionURL>

<div class="apercu-fond" style="display: inline-block;"> 
  <div class="colonne-photo"> 
    <a href="${detailURLFilter}"><img src="${entry.imageURL}" /></a>
  </div>
  <div class="colonne-details">
    <span class="colonne-nom">${entry.lastName} ${entry.firstName}</span> 
    <p>${entry.getName(entry.fonctionCity,locale)}</p> 
  </div>
  <div class="link-read-more">
    <a href="${detailURLFilter}"><@liferay_ui.message key="eu.read-more" /></a>
  </div>
  <div class="clearer"></div>
</div>