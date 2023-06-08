<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<section id="header" class="editions container margin-bottom">
    <div class="page-header-image">
        <img src="${layout.expandoBridge.getAttribute('image')}" alt="${layout.getTitle(locale)?html}" title="${layout.getTitle(locale)?html}" />
    </div>
    <div class="info">
        <h1>${layout.getTitle(locale)}</h1>
        <#if page.getDescription(locale)?has_content>
            <div class="chapo">${page.getDescription(locale)}</div>
        </#if>
    </div>
</section>