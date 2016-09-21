<div class="useful-links">
    <h3 class="useful-links-title">
        Liens utiles
    </h3>
    <#if entries?has_content>
        <ul>
        <#list entries as curEntry>
            <li>
                <#assign link = curEntry.getAssetRenderer().getLink() />
                <a href="${link.getURL(locale)}" title="${link.getHoverText(locale)}" >
                  ${link.getTitle(locale)}
                </a>
            </li>
        </#list>
        </ul>
    </#if>
</div>
