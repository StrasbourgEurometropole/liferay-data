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
                        <a href="${place.getFacebookURL(locale)}" aria-label="${place.getFacebookLabel(locale)}" title="${place.getFacebookLabel(locale)}" class="facebook" />
                    </#if>
                    <#if place.getInstagramURL(locale)?has_content>
                        <a href="${place.getInstagramURL(locale)}" aria-label="${place.getInstagramLabel(locale)}" title="${place.getInstagramLabel(locale)}" class="instagram" />
                    </#if>
                </div>
            </#if>
        </#if>
    </div>
</section>