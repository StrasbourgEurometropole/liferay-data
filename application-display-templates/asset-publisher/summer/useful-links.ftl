<!-- Liens utiles -->
<#setting locale = locale />
<div class="portlet-link-viewer">
    <div class="portlet-body" style="padding-bottom: 0">
        <span class="title-read-further title-with-picto-span">
            <@liferay_ui.message key="eu.read-also" />
        </span>
        <#if entries?has_content>
            <ul class="link-list" style="padding: 10px 5px 5px 5px;">
            <#list entries as curEntry>
                <li>
                    <#assign link = curEntry.getAssetRenderer().getLink() />
                    <a href="${link.getURL(locale)}" title="${link.getHoverText(locale)} (<@liferay_ui.message key="eu.new-window" />)" target="_blank" >
                      ${link.getTitle(locale)}
                    </a>
                </li>
            </#list>
            </ul>
        </#if>
    </div>
</div>
