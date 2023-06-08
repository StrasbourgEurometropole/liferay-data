<!-- Liens utiles (externes) -->
<#setting locale = locale />
<#assign portletHelper = serviceLocator.findService("eu.strasbourg.utils.api.PortletHelperService") />
<div class="seu-wi seu-wi-crossreading">
    <div class="seu-container">
        <h2 class="seu-section-title">
            <span class="seu-title">${portletHelper.getPortletTitle('eu.useful-links', renderRequest)}</span>
        </h2>
        <div class="seu-wi-content">
            <#if entries?has_content>
                <#list entries as curEntry>
                    <#assign link = curEntry.getAssetRenderer().getLink() />
                    <a href="${link.getURL(locale)}" 
                    target="_blank" 
                    class="seu-btn-square seu-bordered seu-core"  
                    title="${link.getHoverText(locale)} (<@liferay_ui.message key="eu.new-window" />)" >
                        <span class="seu-flexbox">
                            <span class="seu-btn-text">${link.getTitle(locale)}</span>
                            <span class="seu-btn-arrow"></span>
                        </span>
                    </a>
                </#list>
            </#if>
        </div>
    </div>
</div>