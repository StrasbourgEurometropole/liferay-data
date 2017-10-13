<!-- Rubrique -->
<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>
<main class="seu-container">
    <h1>${layout.getName(locale)}</h1>
    <div class="hat">
        <p>${layout.getDescription(locale)}</p>
    </div>
    <div id="seu-grid-carrefour" style="position: relative; height: 3478.3px;">
        <#if entries?has_content>
            <#list entries as currentPage>
                <#if currentPage.getName(locale) != 'Raccourcis' && !currentPage.hidden>
                    <#assign hasImage = currentPage.expandoBridge.getAttribute('image')?has_content />
                    <#if hasImage>
                        <#assign itemCssClass = 'seu-has-picture' />
                    <#else>
                        <#assign itemCssClass = 'seu-no-picture' />
                    </#if>
                    <#assign hasVisibleChildren = false>
                    <#if currentPage.children?has_content>
                        <#list currentPage.children as currentSubPage>
                            <#if !currentSubPage.hidden>
                                <#assign hasVisibleChildren = true>
                                <#break>
                            </#if>
                        </#list>
                    </#if>
                    <#if hasVisibleChildren>
                        <#assign itemCssClass += ' seu-list' />
                    <#else>
                        <#assign itemCssClass += ' seu-page' />
                    </#if>
                    <div class="seu-grid-item ${itemCssClass}">
                        <a href="${homeURL}${currentPage.friendlyURL?remove_beginning('/')}" class="seu-grid-item-visu">
                            <#if hasImage>
                                <#assign backgroundImage = currentPage.expandoBridge.getAttribute('image') />
                                <div class="seu-grid-item-background" style="background-image: url(${backgroundImage});"></div>
                            <#else>
                                <div class="seu-grid-item-background"></div>
                            </#if>
                            <div class="seu-grid-item-title" data-dot="4" style="word-wrap: break-word;">${currentPage.getName(locale)}</div>
                        </a>
                        <#if hasVisibleChildren>
                            <ul class="seu-grid-item-sublist unstyled">
                            <#list currentPage.children as currentSubPage>
                                <#if !currentSubPage.hidden>
                                    <li class="seu-sublist-item">
                                        <a href="${homeURL}${currentSubPage.friendlyURL?remove_beginning('/')}" class="seu-btn-square seu-bordered seu-core"><span class="seu-btn-text">${currentSubPage.getName(locale)}</span><span class="seu-btn-arrow"></span></a>
                                    </li>
                                </#if>
                            </#list>
                        </#if>
                    </div>
                </#if>
            </#list>
        </#if>
    </div>
</main>
