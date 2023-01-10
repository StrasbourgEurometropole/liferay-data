<#setting locale = locale />
<#assign fromSearch = renderRequest.getAttribute("fromSearchPortlet")!false >
<#assign plId = 0 />
<#if fromSearch>
  <#assign plId = renderRequest.getAttribute("classNameLayoutId")[entry.getModelClassName()] />
</#if>

<@liferay_portlet.renderURL plid=plId var="detailURL" portletName="eu_strasbourg_portlet_entity_detail_EntityDetailPortlet" windowState="normal">
  <@liferay_portlet.param name="classPK" value="${entry.getEditionId()}" />
  <@liferay_portlet.param name="title" value="${entry.getTitle(locale)}" />
  <@liferay_portlet.param name="returnURL" value="${currentURL}" />
</@liferay_portlet.renderURL>

<@liferay_portlet.actionURL var="detailURLFilter">
  <@liferay_portlet.param name="userTargetClassId" value="${entry.assetEntry.classNameId}" />
  <@liferay_portlet.param name="userTargetClassPK" value="${entry.assetEntry.classPK}" />
  <@liferay_portlet.param name="userTargetTitle" value="${entry.getTitle(locale)}" />
  <@liferay_portlet.param name="detailURL" value="${detailURL}" />
  <@liferay_portlet.param name="searchLogId" value="${renderRequest.getAttribute('searchLogId')!0}" />
</@liferay_portlet.actionURL>
    
<a href="${detailURLFilter}" aria-label="${entry.getTitle(locale)?html}" title="${entry.getTitle(locale)?html}" class="thumbnail-general">
    <div class="title">
        ${entry.getTitle(locale)}
        <div class="type"><@liferay_ui["message"] key="eu.museum.type.edition"/></div>
    </div>
    <div class="content">${entry.getDescription(locale)?replace("<[^>]*>", "", "r")[0..*250]}${(entry.getDescription(locale)?length > 250)?then("...","")}</div>
    <span class="basic-link"><@liferay_ui["message"] key="eu.museum.read-next"/></span>
</a>