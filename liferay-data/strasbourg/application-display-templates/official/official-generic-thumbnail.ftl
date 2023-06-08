<!-- Vignette élu -->

<#setting locale = locale />
<#assign plId = renderRequest.getAttribute("classNameLayoutId")[entry.getModelClassName()] />

<@liferay_portlet.renderURL plid=plId var="detailURL" portletName="eu_strasbourg_portlet_entity_detail_EntityDetailPortlet" windowState="normal">
    <@liferay_portlet.param name="classPK" value="${entry.assetEntry.classPK}" />
    <@liferay_portlet.param name="returnURL" value="${currentURL}" />
</@liferay_portlet.renderURL>

<@liferay_portlet.actionURL var="detailURLFilter">
    <@liferay_portlet.param name="userTargetClassId" value="${entry.assetEntry.classNameId}" />
    <@liferay_portlet.param name="userTargetClassPK" value="${entry.assetEntry.classPK}" />
    <@liferay_portlet.param name="userTargetTitle" value="${entry.firstName} ${entry.lastName}" />
    <@liferay_portlet.param name="detailURL" value="${detailURL}" />
    <@liferay_portlet.param name="searchLogId" value="${renderRequest.getAttribute('searchLogId')!0}" />
</@liferay_portlet.actionURL>


<div class="wi-search-result wi-search-generic wi-search-official">
    <div class="seu-result-left">
        <div class="seu-result-icon"></div>
    </div>
    <div class="seu-result-right">
        <a class="seu-result-content" href="${detailURLFilter}">
            <h2 class="seu-result-title">${entry.firstName} ${entry.lastName}</h2>
            <div class="seu-result-catcher">
                <#if entry.fonctionCity?has_content>
                    <div>${entry.getName(entry.fonctionCity, locale)}</div>
                </#if>
                <#if entry.fonctionEurometropole?has_content>
                    <div>${entry.getName(entry.fonctionEurometropole, locale)}</div>
                </#if>
            </div>
        </a>
        <div class="seu-result-infos">
            <div class="seu-result-infos-top">
                <#if entry.town?has_content>
                    ${entry.getTown().getTitle(locale)}
                </#if>
            </div>
            <div class="seu-result-infos-bottom"> 
            </div>
        </div>
    </div>
</div>
