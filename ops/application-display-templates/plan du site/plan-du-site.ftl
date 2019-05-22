<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}" />
<#else>
    <#assign homeURL = "" />
</#if>

<div class="ops-content-wrapper ops-bloc ops-bloc-small ops-bloc-texte ops-bloc-wysiwyg">
        
    <#if entries?has_content>
        <ul>
            <#list entries as curPage>
                <#if !curPage.hidden>
                    <li><a href="${homeURL}${curPage.friendlyURL}"><strong>${curPage.getName(locale)}</strong></a>
                    <#if curPage.hasChildren()>
                        <#list curPage.allChildren as childPage>
                            <#if !childPage.hidden>
                                <p class="sitemap-alinea"><a href="${homeURL}${childPage.friendlyURL}">${childPage.getName(locale)}</a></p>
                            </#if>
                        </#list>
                    </#if>
                    </li>
                </#if>
            </#list>
        </ul>
    </#if>

</div>

<style>
    .sitemap-alinea{
        text-indent: 30px;
        margin-bottom: 0px !important;
    }
</style>