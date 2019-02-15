<!-- Vignette édition -->

<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
  <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
  <#assign homeURL = "/" />
</#if>

<#assign plId = renderRequest.getAttribute("classNameLayoutId")[entry.getModelClassName()] />

<@liferay_portlet.renderURL plid=plId var="detailURL" portletName="eu_strasbourg_portlet_entity_detail_EntityDetailPortlet" windowState="normal">
    <@liferay_portlet.param name="classPK" value="${entry.editionId}" />
    <@liferay_portlet.param name="returnURL" value="${currentURL}" />
</@liferay_portlet.renderURL>

<@liferay_portlet.actionURL var="detailURLFilter">
    <@liferay_portlet.param name="userTargetClassId" value="${entry.assetEntry.classNameId}" />
    <@liferay_portlet.param name="userTargetClassPK" value="${entry.assetEntry.classPK}" />
    <@liferay_portlet.param name="userTargetTitle" value="${entry.getTitle(locale)}" />
    <@liferay_portlet.param name="detailURL" value="${detailURL}" />
    <@liferay_portlet.param name="searchLogId" value="${renderRequest.getAttribute('searchLogId')!0}" />
</@liferay_portlet.actionURL>

<div class="wi-search-result wi-edition-thumbnail">
    <div class="seu-result-left seu-result-thumbnail">
        <a href="${detailURLFilter}" title="${entry.getTitle(locale)}">
            <div class="thumbnail-background" style="background-image: url(${entry.imageURL});"></div>
        </a>
    </div>
    <div class="seu-result-right">
        <a class="seu-result-content" href="${detailURLFilter}" title="${entry.getTitle(locale)}">
            <h2 class="seu-result-title">${entry.getTitle(locale)}</h2>
            <div class="seu-result-catcher">
                ${entry.getDescription(locale)?replace("<[^>]*>", "", "r")[0..*100]}...
            </div>
            <#if entry.getTypes()?has_content>
                <div class="seu-result-category">
                    <#list entry.getTypes() as type>
                        ${type.getTitle(locale)}
                        <#sep>, </#sep>
                    </#list>
                </div>
            </#if>
        </a>
        <div class="seu-result-infos">
            <div class="seu-result-infos-top">
                <#if entry.getDiffusionDateMonth()?has_content>
                    ${entry.getDiffusionDateMonth()} /
                </#if> ${entry.getDiffusionDateYear()}
            </div>
            <div class="seu-result-infos-bottom"> 
                 <a href="#" class="seu-add-favorites"
                    data-type="4" 
                    data-title="${entry.getTitle(locale)}" 
                    data-url="${themeDisplay.getPortalURL()}${homeURL}edition/-/entity/id/${entry.editionId}" 
                    data-id="${entry.editionId}">
                    <span><@liferay_ui.message key='eu.add-to-favorite' /></span>
                </a>
            </div>
        </div>
    </div>

</div>
