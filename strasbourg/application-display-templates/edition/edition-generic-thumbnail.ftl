<!-- Vignette édition -->

<#setting locale = locale />
<#assign plId = renderRequest.getAttribute("classNameLayoutId")[entry.getModelClassName()] />

<@liferay_portlet.renderURL plid=plId var="detailURL" portletName="eu_strasbourg_portlet_entity_detail_EntityDetailPortlet" windowState="normal">
    <@liferay_portlet.param name="classPK" value="${entry.assetEntry.classPK}" />
    <@liferay_portlet.param name="returnURL" value="${currentURL}" />
</@liferay_portlet.renderURL>

<@liferay_portlet.actionURL var="detailURLFilter">
    <@liferay_portlet.param name="userTargetClassId" value="${entry.assetEntry.classNameId}" />
    <@liferay_portlet.param name="userTargetClassPK" value="${entry.assetEntry.classPK}" />
    <@liferay_portlet.param name="userTargetTitle" value="${entry.getTitle(locale)}" />
    <@liferay_portlet.param name="detailURL" value="${detailURL}" />
    <@liferay_portlet.param name="searchLogId" value="${renderRequest.getAttribute('searchLogId')!0}" />
</@liferay_portlet.actionURL>


<div class="wi-search-result wi-search-generic wi-search-edition">
    <div class="seu-result-left">
        <div class="seu-result-icon"></div>
    </div>
    <div class="seu-result-right">
        <a class="seu-result-content" href="${detailURLFilter}">
            <h2 class="seu-result-title">${entry.getTitle(locale)}</h2>
            <div class="seu-result-catcher">${entry.getDescription(locale)?replace("<[^>]*>", "", "r")[0..*100]}...</div>
            <div class="seu-result-category">
                <#list entry.types as type>
                    ${type.getTitle(locale)}<#sep>, </#sep>
                </#list>
            </div>
        </a>
        <div class="seu-result-infos">
            <div class="seu-result-infos-top">
                <#if entry.getDiffusionDateMonth()?has_content>
                    ${entry.getDiffusionDateMonth()} /
                </#if> ${entry.getDiffusionDateYear()}
            </div>
            <div class="seu-result-infos-bottom"> 
            </div>
        </div>
    </div>

</div>
