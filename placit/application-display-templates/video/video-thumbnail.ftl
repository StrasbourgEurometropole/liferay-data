<!-- VIGNETTE VIDEO (Recherche d'asset) -->

<!-- Recuperation de la localisation de l'utilisateur -->
<#setting locale = locale />

<!-- Recuperation de l'URL de "base" du site -->
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<!-- Recuperation de la source URL de la vidéo -->
<#assign videoURL = entry.getSource(locale) />
<!-- Recuperation du site cible de la vidéo -->
<#assign site = entry.getSiteVideo(videoURL) />
<!-- Recuperation de l'id de la vidéo -->
<#assign videoId = entry.getVideoId(site, videoURL) />
<!-- Recuperation du nombre de vue de la vidéo -->
<#assign nbViews = entry.getNbViews(site,videoId) />

<#assign fromSearch = renderRequest.getAttribute("fromSearchPortlet")!false >
<#assign plId = 0 />
<#if fromSearch>
  <#assign plId = renderRequest.getAttribute("classNameLayoutId")[entry.getModelClassName()] />
</#if>

<@liferay_portlet.renderURL plid=plId var="detailURL" portletName="eu_strasbourg_portlet_entity_detail_EntityDetailPortlet" windowState="normal">
  <@liferay_portlet.param name="classPK" value="${entry.videoId}" />
  <@liferay_portlet.param name="returnURL" value="${currentURL}" />
</@liferay_portlet.renderURL>

<@liferay_portlet.actionURL var="detailURLFilter">
  <@liferay_portlet.param name="userTargetClassId" value="${entry.assetEntry.classNameId}" />
  <@liferay_portlet.param name="userTargetClassPK" value="${entry.assetEntry.classPK}" />
  <@liferay_portlet.param name="userTargetTitle" value="${entry.getTitle(locale)}" />
  <@liferay_portlet.param name="detailURL" value="${detailURL}" />
  <@liferay_portlet.param name="searchLogId" value="${renderRequest.getAttribute('searchLogId')!0}" />
</@liferay_portlet.actionURL>

<div class="col-md-4 col-sm-6 col-xs-12">
    <div class="pro-card-video" data-linkall="a">
        <div class="pro-header">
            <figure class="fit-cover" role="group">
                <img alt="${entry.getTitle(locale)}" width="280" height="175" src="${entry.imageURL}">
            </figure>
            <span class="icon-ico-lecteur"></span>
        </div>
        <div class="pro-meta-avis">
            <div class="pro-avis">
                <a href="#pro-avis-like-pro" class="pro-like"
                    data-typeid="3" 
                    data-isdislike="false"
                    data-title='${entry.getTitle(locale)}' 
                    data-entityid="${entry.videoId}"
                    data-entitygroupid="${entry.groupId}">
                    ${entry.nbLikes}
                </a>
                <a href="#pro-avis-dislike-pro" class="pro-dislike"
                    data-typeid="3" 
                    data-isdislike="true"
                    data-title='${entry.getTitle(locale)}'
                    data-entityid="${entry.videoId}"
                    data-entitygroupid="${entry.groupId}">
                    ${entry.nbDislikes}
                </a>
            </div>
            <#if nbViews != "" >
                <span class="pro-view">
                    ${nbViews?number} vues
                </span>
            </#if>
        </div>
        <a href="${detailURLFilter}" title="Vers la page ${entry.getTitle(locale)}" class="pro-link-all"><h3>${entry.getTitle(locale)}</h3></a>
    </div>
</div>