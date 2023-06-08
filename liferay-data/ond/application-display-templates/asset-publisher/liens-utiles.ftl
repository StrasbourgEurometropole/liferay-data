<!-- Liens utils -->
<#setting locale = locale />
<#if entries?has_content>
    <div class="title-with-picto-div doc-title"><@liferay_ui.message key="eu.useful-links" /></div>
    <div class="bloc-util-docs">
        <#list entries as curEntry>
            <#if curEntry?has_content>
                <#assign link = curEntry.getAssetRenderer().getLink() />
                <div class="doc">
                    <div class="desc">
                        <div class="name">
                            <a target="_blank" href="${link.getURL(locale)}" title="${link.getHoverText(locale)} (<@liferay_ui.message key='new-window' />)">${link.getHoverText(locale)}</a>
                        </div>
                    </div>
                    <div class="clearer">&nbsp;</div>
                </div>
            </#if>
        </#list>
    </div>
</#if>