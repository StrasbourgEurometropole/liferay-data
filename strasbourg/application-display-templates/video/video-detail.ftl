<!-- Détail Vidéo -->
<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
   <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
   <#assign homeURL = "/" />
</#if>

<div class="seu-container" style="margin-bottom: 50px"> 
    <a href="#" class="add-favorites"
        data-type="3" 
        data-title="${entry.getTitle(locale)}" 
        data-url="${themeDisplay.getPortalURL()}${homeURL}video/-/entity/id/${entry.videoId}" 
        data-id="${entry.videoId}">
        <span><@liferay_ui.message key="eu.add-to-favorite" /></span>
    </a>
	<h1>${entry.getTitle(locale)}</h1> 
    <div class="meta"> 
        <div class="themes"> 
            <span> 
                ${entry.getThemesLabel(locale)}
            </span> 
        </div> 
    </div> 
	<div class="video-player-wrapper"> 
		<div class="video-player">
			${entry.getPlayer(locale)}
		</div>
	</div> 
    <div class="infos"> 
        <div class="description"> 
            ${entry.getDescription(locale)} 
        </div> 
        <#if entry.getCopyright(locale) != "">
            <div class="source"> 
                <strong><@liferay.language key="credit" /></strong>&nbsp;${entry.getCopyright(locale)}
            </div> 
        </#if>
    </div>
</div>
