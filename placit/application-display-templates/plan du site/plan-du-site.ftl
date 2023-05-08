<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostnames?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}" />
<#else>
    <#assign homeURL = "" />
</#if>

<div class="col-lg-11 col-lg-offset-3">
    <section class="container pro-bloc-text-hp plan">
        <h1>Plan du site</h1>
        
        <#if entries?has_content>
            <ul>
                <#list entries as curPage>
                    <#if !curPage.hidden>
                        <li><a href="${homeURL}${curPage.friendlyURL}">${curPage.getName(locale)}</a>
                        <#if curPage.hasChildren()>
                            <ul>
                                <#list curPage.allChildren as childPage>
                                    <#if !childPage.hidden>
                                        <li><a href="${homeURL}${childPage.friendlyURL}">${childPage.getName(locale)}</a></li>
                                    </#if>
                                </#list>
                            </ul>
                        </#if>
                        </li>
                    </#if>
                </#list>
            </ul>
        </#if>

    </section>
</div>

<style>
a:hover{
    color: #ffed00;
}

ul{
    font-size: 1.2em;
}

ul ul{
    font-size: 1em;
    margin-left: 25px;
}
</style>