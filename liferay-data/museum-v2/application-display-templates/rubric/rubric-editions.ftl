<!-- Rubrique -->
<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}" />
<#else>
    <#assign homeURL = "" />
</#if>

<section id="rubric" class="editions margin-bottom">
    <div  class="content container">
        <#if entries?has_content>
            <div id="listRubrics" class="list">
                <div class="gutter-sizer"></div>
                <#list entries as currentPage>
                    <#if !currentPage.hidden>
                        <div class="rubric-thumbnail">
                            <a href="${homeURL}${currentPage.friendlyURL}" aria-label="${currentPage.getName(locale)?html}" title="${currentPage.getName(locale)?html}">
                                <div style="position: relative">
                                    <#assign hasImage = currentPage.expandoBridge.getAttribute('image')?has_content />
                                    <#if hasImage>
                                        <#assign backgroundImage = currentPage.expandoBridge.getAttribute('image') />
                                        <img aria-label="${currentPage.getName(locale)?html}" alt="${currentPage.getName(locale)?html}" title="${currentPage.getName(locale)?html}" src="${backgroundImage}">
                                        <div class="info">
                                    <#else>
                                        <div class="info noImage">
                                    </#if>
                                        <div class="title">
                                            <span>${currentPage.getName(locale)}</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
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
                                <ul class="">
                                    <#list currentPage.children as currentSubPage>
                                        <#if !currentSubPage.hidden>
                                            <li class="">
                                                <a href="${homeURL}${currentSubPage.friendlyURL}" class="">
                                                    ${currentSubPage.getName(locale)}
                                                </a>
                                            </li>
                                        </#if>
                                    </#list>
                                </ul>
                            </#if>
                        </div>
                    </#if>
                </#list>
            </div>
        </#if>
    </div>
</section>