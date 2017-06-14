<!-- Vignette video -->
<#setting locale = locale />
<#setting date_format="d/MM/yyyy">
<#assign fromSearch = renderRequest.getAttribute("fromSearchPortlet")!false >
<#assign plId = 0 />
<#if fromSearch>
  <#assign plId = renderRequest.getAttribute("classNameLayoutId")[entry.getModelClassName()] />
</#if>

<@liferay_portlet.renderURL plid=plId var="detailURL" portletName="eu_strasbourg_portlet_entity_detail_EntityDetailPortlet" windowState="normal">
  <@liferay_portlet.param name="classPK" value="${entry.getVideoId()}" />
  <@liferay_portlet.param name="returnURL" value="${currentURL}" />
</@liferay_portlet.renderURL>

<@liferay_portlet.actionURL var="detailURLFilter">
  <@liferay_portlet.param name="userTargetClassId" value="${entry.assetEntry.classNameId}" />
  <@liferay_portlet.param name="userTargetClassPK" value="${entry.assetEntry.classPK}" />
  <@liferay_portlet.param name="userTargetTitle" value="${entry.getTitle(locale)}" />
  <@liferay_portlet.param name="detailURL" value="${detailURL}" />
  <@liferay_portlet.param name="searchLogId" value="${renderRequest.getAttribute('searchLogId')!0}" />
</@liferay_portlet.actionURL>

<a href="${detailURLFilter}" >
	<img src="${entry.getImageURL()}" alt="${entry.getTitle(locale)}">
</a> 
<div class="meta"> 
	<div class="title">
		<a href="${detailURLFilter}">${entry.getTitle(locale)}</a>
	</div> 
	<div class="provider">${entry.getProvidersLabel(locale)}</div> 
	<div class="date">${entry.getPublicationDate()?string("dd/MM/yyyy")}</div> 
</div>