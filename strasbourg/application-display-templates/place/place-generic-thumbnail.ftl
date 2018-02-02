<!-- Vignette lieu -->

<#setting locale = locale />

<#assign plId = renderRequest.getAttribute("classNameLayoutId")[entry.getModelClassName()] />
<#assign serviceContext = staticUtil["com.liferay.portal.kernel.service.ServiceContextThreadLocal"].getServiceContext()>
<#assign themeDisplay = serviceContext.getThemeDisplay() />
<#assign strasbourgURL = themeDisplay.getPortalURL() + "/web/" + themeDisplay.getSiteGroupName() + "/"  />

<@liferay_portlet.renderURL plid=plId var="detailURL" portletName="eu_strasbourg_portlet_entity_detail_EntityDetailPortlet" windowState="normal">
    <@liferay_portlet.param name="sigId" value="${entry.getSIGid()}" />
    <@liferay_portlet.param name="returnURL" value="${currentURL}" />
</@liferay_portlet.renderURL>

<@liferay_portlet.actionURL var="detailURLFilter">
    <@liferay_portlet.param name="userTargetClassId" value="${entry.assetEntry.classNameId}" />
    <@liferay_portlet.param name="userTargetClassPK" value="${entry.assetEntry.classPK}" />
    <@liferay_portlet.param name="userTargetTitle" value="${entry.getAlias(locale)}" />
    <@liferay_portlet.param name="detailURL" value="${detailURL}" />
    <@liferay_portlet.param name="searchLogId" value="${renderRequest.getAttribute('searchLogId')!0}" />
</@liferay_portlet.actionURL>


<div class="wi-search-result wi-place-thumbnail">
    <div class="seu-result-left">
        <div class="seu-result-icon"></div>
    </div>
    <div class="seu-result-right">
        <a class="seu-result-content" href="${detailURL}">
            <h2 class="seu-result-title">${entry.getAlias(locale)}</h2>
            <div class="seu-result-catcher">${entry.getTypeLabel(locale)}</div>
        </a>
        <div class="seu-result-infos">
            <div class="seu-result-infos-top">
                ${entry.getCity(locale)}
            </div>
            <div class="seu-result-infos-bottom">
                <a href="#" class="seu-add-favorites" 
                data-type="1" 
                data-title="${entry.getAlias(locale)}" 
                data-url="${strasbourgURL}lieu/-/entity/id/${entry.placeId}"
                data-id="${entry.placeId}">
                    <span><@liferay_ui.message key='eu.add-to-favorite' /></span>
                </a>
            </div>
        </div>
    </div>

</div>
