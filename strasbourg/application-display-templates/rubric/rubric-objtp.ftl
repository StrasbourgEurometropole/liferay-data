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
    <h1><@liferay_ui.message key="objtp-gallery-found-objects" /></h1>
    <div id="objtp-grid-carrefour" style="position: relative">
        <#if entries?has_content>
            <#list entries as currentPage>
                <#if currentPage.getName(locale) != 'Raccourcis' && !currentPage.hidden>
                    <#assign itemCssClass = 'objtp-no-picture' />
                    <#assign itemCssClass += ' objtp-page' />
                    <div class="objtp-grid-item ${itemCssClass}">
                        <a href="${homeURL}${currentPage.friendlyURL?remove_beginning('/')}" class="objtp-grid-item-visu">
                                <div class="objtp-grid-item-background"></div>
                            <div class="objtp-grid-item-title" data-dot="4" style="word-wrap: break-word;">${currentPage.getName(locale)}</div>
                        </a>
                    </div>
                </#if>
            </#list>
        </#if>
    </div>

    <div id="favConfirm" style="display: none;"> 
        <div class="favMessage">${message}</div> 
        <div class="favActions"> 
            <button class="seu-btn-square--bordered--core deny"><span class="seu-flexbox"><span class="seu-btn-text">Annuler</span><span class="seu-btn-arrow"></span></span>
            </button> 
            <button class="seu-btn-square--filled--second confirm"><span class="seu-flexbox"><span class="seu-btn-text">Valider</span><span class="seu-btn-arrow"></span></span>
            </button> 
        </div> 
    </div>

</main>