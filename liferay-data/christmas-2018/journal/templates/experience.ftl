<#setting locale = locale />
<#assign serviceContext = staticUtil["com.liferay.portal.kernel.service.ServiceContextThreadLocal"].getServiceContext() />
<#assign currentUrl = themeDisplay.getPortalURL() + themeDisplay.getURLCurrent() />


<#assign journalArticleId = .vars['reserved-article-id'].data>
<#assign journalArticleResourceLocalServiceUtil = staticUtil["com.liferay.journal.service.JournalArticleResourceLocalServiceUtil"]>
<#assign assetCategoryLocalServiceUtil = staticUtil["com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil"]>


<#assign articleResourcePK = journalArticleResourceLocalServiceUtil.getArticleResourcePrimKey(groupId, journalArticleId)/>
<#assign categoryList=assetCategoryLocalServiceUtil.getCategories("com.liferay.journal.model.JournalArticle",articleResourcePK) >
<#assign request = serviceContext.getRequest()/>

<#assign imageUrl = ""/>
<!-- image -->
<#if image.getData()?has_content>
    <#assign imageUrl = themeDisplay.getPortalURL() + image.getData()?replace('@', "")?replace('cdn_hostroot_path', "") />
</#if>

<#-- Liste des infos a partager -->
<#assign openGraph = {
"og:title":"${title.data?html}",
"og:description":'${text.data?replace("<[^>]*>", "", "r")?html}', 
"og:image":"${imageUrl}"
} />
<#-- partage de la configuration open graph dans la request -->
${request.setAttribute("LIFERAY_SHARED_OPENGRAPH", openGraph)}

<div class="mns-page-experience">
    <header class="container mns-top-experience">
        <div class="small-container">
            <div>
                <h1>${title.data}</h1>
                <div class="mns-meta">
                    <div class="mns-wrap-tag">
                        <#list categoryList as category>
                            <span>${category.getName()}</span>
                        </#list>
                    </div>
                    <span class="mns-location">${lieu.data}</span>
                    <figure class="fit-cover">
                        <img src="${image.getData()}" width="60" height="60" alt="Nom de l'auteur de l'expérience">
                    </figure>
                </div>
                <a href="#" class="add-favorites"
                    data-type="7" 
                    data-title="${title.data}" 
                    data-url="${currentUrl}" 
                    data-group-id=${themeDisplay.scopeGroupId}
                    data-id="${.vars['reserved-article-id'].data}">
                    <span><@liferay_ui.message key="eu.add-to-favorite" /></span>
                </a>
            </div>
        </div>
    </header>
</div>

<!-- Détail expérience -->
<div class="mns-fck container experience">
    ${text.data}
</div>