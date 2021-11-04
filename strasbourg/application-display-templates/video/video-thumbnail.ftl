<!-- Vignette video -->
<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>
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

<li class="grid-item">
    <div class="item-visu">
        <!-- <button class="item-favoris"></button> -->
        <div class="item-background" style="background-image: url(${entry.imageURL});"></div>
    </div>
    <div class="item-right">
        <a href="${detailURLFilter}" class="item-content">
            <h3 class="item-title" data-dot="2">${entry.getTitle(locale)}</h3>
            <div class="item-description" data-dot="1">${entry.getDescription(locale)?replace("<[^>]*>", "", "r")[0..*100]}...</div>
            <div class="item-categories" data-dot="1">${entry.getThemesLabel(locale)}</div>
        </a>
        <div class="item-infos">
            <div class="date">
                <span class="text">${entry.getPublicationDate()?string("dd/MM/yyyy")}</span>
            </div>
            <!--
            <a href="" class="item-misc">
                <span>Ajouter au calendrier</span>
            </a>
            -->
            <a href="#" class="item-misc"
            data-type="3" 
            data-title="${entry.getTitle(locale)}" 
            data-url="${themeDisplay.getPortalURL()}${homeURL}video/-/entity/id/${entry.videoId}" 
            data-id="${entry.videoId}">
                <span><@liferay_ui.message key='eu.add-to-favorite' /></span>
            </a>
        </div>
    </div>
</li>