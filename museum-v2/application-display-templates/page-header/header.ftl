<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<section id="header" class="margin-top margin-bottom">
    <h1>${layout.getTitle(locale)}</h1>
    <#if page.expandoBridge.getAttribute('introduction')?has_content>
        <#assign introductionAttribute = page.expandoBridge.getAttribute('introduction') />
        <#list introductionAttribute?keys as key> 
            <#if key == locale>
                <#assign introduction = introductionAttribute?values[key_index] />
            </#if>
        </#list>
    </#if>
    <#if introduction?has_content>
        <div class="chapo">${introduction}</div>
    </#if>
</section>