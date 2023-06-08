<!-- Rubrique -->
<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>
<#assign journalArticleLocalService = serviceLocator.findService("com.liferay.journal.service.JournalArticleLocalService")/>
<#assign articleRegleObjtp = journalArticleLocalService.getArticleByUrlTitle(themeDisplay.getScopeGroupId(), "regles-d-utilisation-objets-trouves") />
<#assign contentArticle = articleRegleObjtp.getContentByLocale(locale) />
 <#assign docXml = saxReaderUtil.read(contentArticle) />
<#assign message = docXml.valueOf("//dynamic-element[@name='content']/dynamic-content/text()") />

<main class="seu-container">
   

    <h1>${layout.getName(locale)}</h1>
    <div id="objtp-grid-carrefour" style="position: relative; height: 3478.3px;">
        <#if entries?has_content>
            <#list entries as currentPage>
                <#if currentPage.getName(locale) != 'Raccourcis' && !currentPage.hidden>
                    <#assign hasImage = currentPage.expandoBridge.getAttribute('image')?has_content />
                    <#if hasImage>
                        <#assign itemCssClass = 'objtp-has-picture' />
                    <#else>
                        <#assign itemCssClass = 'objtp-no-picture' />
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
                        <#assign itemCssClass += ' objtp-list' />
                    <#else>
                        <#assign itemCssClass += ' objtp-page' />
                    </#if>
                    <div class="objtp-grid-item ${itemCssClass}">
                        <a href="${homeURL}${currentPage.friendlyURL?remove_beginning('/')}" class="objtp-grid-item-visu">
                            <#if hasImage>
                                <#assign backgroundImage = currentPage.expandoBridge.getAttribute('image') />
                                <div class="objtp-grid-item-background" style="background-image: url(${backgroundImage});"></div>
                            <#else>
                                <div class="objtp-grid-item-background"></div>
                            </#if>
                            <div class="objtp-grid-item-title" data-dot="4" style="word-wrap: break-word;">${currentPage.getName(locale)}</div>
                        </a>
                        <#if hasVisibleChildren>
                            <ul class="objtp-grid-item-sublist unstyled">
                            <#list currentPage.children as currentSubPage>
                                <#if !currentSubPage.hidden>
                                    <li class="objtp-sublist-item">
                                        <a href="${homeURL}${currentSubPage.friendlyURL?remove_beginning('/')}" class="seu-btn-square seu-bordered seu-core"><span class="seu-btn-text">${currentSubPage.getName(locale)}</span><span class="objtp-btn-arrow"></span></a>
                                    </li>
                                </#if>
                            </#list>
                        </#if>
                    </div>
                </#if>
            </#list>
        </#if>
    </div>




    <div id="rubricPopup" style="display: none;"> 
        <div class="rubricMessage">${message}</div> 
        <div class="rubricActions"> 
            <button class="seu-btn-square--bordered--core deny"><span class="seu-flexbox"><span class="seu-btn-text">Annuler</span><span class="seu-btn-arrow"></span></span>
            </button> 
            <button class="seu-btn-square--filled--second confirm"><span class="seu-flexbox"><span class="seu-btn-text">Valider</span><span class="seu-btn-arrow"></span></span>
            </button> 
        </div> 
    </div>

</main>