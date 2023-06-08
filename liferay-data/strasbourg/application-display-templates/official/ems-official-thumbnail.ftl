<#setting locale = locale />
<#assign plId = renderRequest.getAttribute("classNameLayoutId")[entry.getModelClassName()] />

<@liferay_portlet.renderURL plid=plId var="detailURL" portletName="eu_strasbourg_portlet_entity_detail_EntityDetailPortlet" windowState="normal">
  <@liferay_portlet.param name="classPK" value="${entry.officialId}" />
  <@liferay_portlet.param name="returnURL" value="${currentURL}" />
</@liferay_portlet.renderURL>

<@liferay_portlet.actionURL var="detailURLFilter">
  <@liferay_portlet.param name="userTargetClassId" value="${entry.assetEntry.classNameId}" />
  <@liferay_portlet.param name="userTargetClassPK" value="${entry.assetEntry.classPK}" />
  <@liferay_portlet.param name="userTargetTitle" value="${entry.firstName} ${entry.lastName}" />
  <@liferay_portlet.param name="detailURL" value="${detailURL}" />
  <@liferay_portlet.param name="searchLogId" value="${renderRequest.getAttribute('searchLogId')!0}" />
</@liferay_portlet.actionURL>

<div class="official-thumbnail seu-mag">
    <a href="${detailURLFilter}" class="seu-link" title="${entry.firstName} ${entry.lastName}">
        <div class="seu-picture" style="background-image: url(${entry.imageURL})"></div>
        <div class="seu-text">
            <div class="seu-title dotme" data-dot="3" style="word-wrap: break-word;">${entry.firstName} ${entry.lastName}</div>
            <div class="seu-lead dotme" data-dot="3" style="word-wrap: break-word;">${entry.getName(entry.fonctionEurometropole,locale)}</div>
        </div>
    </a>
</div>