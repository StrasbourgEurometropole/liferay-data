<#setting locale = locale />
<#assign serviceContext = staticUtil["com.liferay.portal.kernel.service.ServiceContextThreadLocal"].getServiceContext() />
<#assign themeDisplay = serviceContext.getThemeDisplay() />
<#assign currentUrl = themeDisplay.getPortalURL() + themeDisplay.getURLCurrent() />

<#--  récupération de l'id du webcontent -->
<#assign journalArticleId = .vars['reserved-article-id'].data>
<#assign journalArticleResourceLocalServiceUtil = staticUtil["com.liferay.journal.service.JournalArticleResourceLocalServiceUtil"]>
<#assign assetCategoryLocalServiceUtil = staticUtil["com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil"]>


<#assign articleResourcePK = journalArticleResourceLocalServiceUtil.getArticleResourcePrimKey(groupId, journalArticleId)/>
<#assign categoryList=assetCategoryLocalServiceUtil.getCategories("com.liferay.journal.model.JournalArticle",articleResourcePK) >


<@liferay_util["html-top"]>
    <meta property="og:title" content="${title.getData()?html}" />
    <meta property="og:url" content="${currentUrl}" />
</@>

<article class="container pro-actu">
    <div class="col-md-10 col-md-offset-1 col-sm-12">
        <header>
            <span class="pro-time"><@liferay_ui.message key="eu.published-on" /> ${.vars['reserved-article-display-date'].getData()?date('EEE, dd MMM yyyy hh:mm:ss Z')?string("dd/MM/yyyy")} 
           - <@liferay_ui.message key="eu.modified-on" /> ${.vars['reserved-article-modified-date'].getData()?date('EEE, dd MMM yyyy hh:mm:ss Z')?string("dd/MM/yyyy")}
            </span>
            <h1>${title.getData()}</h1>
            <div class="pro-meta">
                <#list categoryList as categoryList>
                   <span> ${categoryList.getName()} <span>
                </#list>
            </div>
            <figure role="group">
                <img src='${image.getData()}' alt="Image agenda" width="1160" height="593" class="fit-cover"/>
                <figcaption>Crédit de la photographie</figcaption>
            </figure>
        </header>
        
        <div id="breadcrumb">
            <span>
                <span><a href="index.html">Accueil</a>
                    <a href="listing-actu.html">Toutes les actualités</a>
                    <span class="breadcrumb_last">Titre de l'actualité</span>
                </span>
            </span>
        </div>
        <div class="pro-content pro-bloc-texte col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1 col-sm-12">
            ${content.getData()}
        </div>
    </div>
</article>
<style>
.search-asset-portlet, .page-header {
    display: none !important;
}
.portlet-body > div > div.h2 {
    display: none;
}

</style>