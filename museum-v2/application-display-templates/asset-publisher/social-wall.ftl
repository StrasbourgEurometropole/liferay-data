<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>
<#assign portletHelper = serviceLocator.findService("eu.strasbourg.utils.api.PortletHelperService") />

<section id="social-wall" class="margin-bottom">
    <div  class="content container">
        <#if entries?has_content>
            <#assign place = entries[0].getAssetRenderer().getPlace() />
            <#if place.getFacebookURL(locale)?has_content || place.getInstagramURL(locale)?has_content>
                <h2>${portletHelper.getPortletTitle('eu.museum.social-wall', renderRequest)}</h2>
                <div class="list">
                    <#if place.getFacebookURL(locale)?has_content>
                        <a href="${place.getFacebookURL(locale)}" target="_blank" aria-label="${place.getFacebookLabel(locale)?html} (<@liferay_ui.message key="eu.new-window" />)" title="${place.getFacebookLabel(locale)?html} (<@liferay_ui.message key="eu.new-window" />)" class="facebook" ></a>
                    </#if>
                    <#if place.getInstagramURL(locale)?has_content>
                        <a href="${place.getInstagramURL(locale)}" target="_blank" aria-label="${place.getInstagramLabel(locale)?html} (<@liferay_ui.message key="eu.new-window" />)" title="${place.getInstagramLabel(locale)?html} (<@liferay_ui.message key="eu.new-window" />)" class="instagram" ></a>
                    </#if>
                </div>
            </#if>
        </#if>
    </div>
</section>