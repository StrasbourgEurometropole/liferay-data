<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<#assign plId = renderRequest.getAttribute("classNameLayoutId")[entry.getModelClassName()] />
<#assign uriHelperService = serviceLocator.findService("eu.strasbourg.utils.api.UriHelperService")/>

<@liferay_portlet.renderURL plid=plId var="detailURL" portletName="eu_strasbourg_portlet_entity_detail_EntityDetailPortlet" windowState="normal">
  <@liferay_portlet.param name="classPK" value="${entry.getEventId()}" />
  <@liferay_portlet.param name="returnURL" value="${currentURL}" />
</@liferay_portlet.renderURL>

<@liferay_portlet.actionURL var="detailURLFilter">
  <@liferay_portlet.param name="userTargetClassId" value="${entry.assetEntry.classNameId}" />
  <@liferay_portlet.param name="userTargetClassPK" value="${entry.assetEntry.classPK}" />
  <@liferay_portlet.param name="userTargetTitle" value="${entry.getTitle(locale)}" />
  <@liferay_portlet.param name="detailURL" value="${detailURL}" />
  <@liferay_portlet.param name="searchLogId" value="${renderRequest.getAttribute('searchLogId')!0}" />
</@liferay_portlet.actionURL>

<!-- Vignette événement -->
<#if isFeatured>
    <#assign cssClass = 'seu-coup-de-coeur' />
<#else>
    <#assign cssClass = '' />
</#if>
<li class="grid-item ${cssClass}">
    <div class="item-visu">
        <!-- <button class="item-favoris"></button> -->
        <#if entry.imageURL?contains("http")>     
            <div class="item-background" data-background-src="${entry.imageURL}"></div>
        <#else>
            <div class="item-background" data-background-src="${uriHelperService.appendUriImagePreview(entry.imageURL)}"></div>
        </#if>
    </div>
    <div class="item-right">
        <a href="${detailURLFilter}" class="item-content">
            <#if isFeatured>
                <div class="item-coup-de-coeur">Coup de coeur de strasbourg.eu</div>
            </#if>
            <#if entry.firstStartDate?has_content && entry.lastEndDate?has_content>
                <#if entry.firstStartDate?date == entry.lastEndDate?date>
                    <div class="item-date"><@liferay_ui.message key="eu.event.the" /> <strong>${entry.firstStartDate?date?string.short?replace('/', '.')}</strong></div>
                <#else>
                    <div class="item-date"><@liferay_ui.message key="eu.event.from-date" /> <strong>${entry.firstStartDate?date?string.short?replace('/', '.')}</strong> <@liferay_ui.message key="eu.event.to" /> <strong>${entry.lastEndDate?date?string.short?replace('/', '.')}</strong></div>
                </#if>
            </#if>
            <h3 class="item-title" data-dot="2">${entry.getTitle(locale)}</h3>
            <div class="item-categories" data-dot="1">${entry.getTypeLabel(locale)}</div>
        </a>
        <div class="item-infos">
            <div class="item-geoloc">
                <span class="text">${entry.getPlaceAlias(locale)} - ${entry.getPlaceCity(locale)}</span>
            </div>
            <!--
            <a href="" class="item-misc">
                <span>Ajouter au calendrier</span>
            </a>
            -->
            <a href="#" class="item-misc"
            data-type="2" 
            data-title="${entry.getTitle(locale)}" 
            data-url="${themeDisplay.getPortalURL()}${homeURL}evenement/-/entity/id/${entry.eventId}" 
            data-id="${entry.eventId}">
                <span><@liferay_ui.message key='eu.add-to-favorite' /></span>
            </a>
        </div>
    </div>
</li>
