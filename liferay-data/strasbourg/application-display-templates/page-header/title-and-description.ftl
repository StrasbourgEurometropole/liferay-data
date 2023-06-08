<!-- Header titre et description -->
<#setting locale = locale />
<#assign portalHelper = serviceLocator.findService("eu.strasbourg.utils.api.PortalHelperService") />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<main class="seu-container">
	<a href="#" class="add-favorites"
        data-type="9" 
        data-title="${layout.getName(locale)}" 
        data-url="${portalHelper.getPortalURL(themeDisplay)}${homeURL}${layout.friendlyURL?remove_beginning('/')}" 
        data-id="0">
        <span><@liferay_ui.message key="eu.add-to-favorite" /></span>
	</a>
    <h1>${layout.getName(locale)}</h1>
    <div class="hat">
        <p>${layout.getDescription(locale)}</p>
    </div>
</main>
