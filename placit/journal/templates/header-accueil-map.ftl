<!-- Un simple template sans structure, le lien vers la map est en dur -->

<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<header class="pro-header-homepage">

    <figure role="group">
        <img src='/o/plateforme-citoyenne-theme/images/medias/bg-homepage-portail.jpg' alt="Arrière plan header de la page d'accueil" width="1600" height="320" class="fit-cover"/>
    </figure>

    <a href="${homeURL}carte" class="pro-btn-icon" title="Lien vers la page de la carte interactive"><span class="icon-ico-map"></span>La carte interactive</a>
    
</header>