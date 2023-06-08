<#setting locale = locale />
<#assign serviceContext = staticUtil["com.liferay.portal.kernel.service.ServiceContextThreadLocal"].getServiceContext() />
<#assign themeDisplay = serviceContext.getThemeDisplay() />
<#assign currentUrl = themeDisplay.getPortalURL() + themeDisplay.getURLCurrent() />

<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

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
        <div class="pro-content pro-bloc-texte col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1 col-sm-12">
            ${content.getData()}
        </div>
    </div>
</article>
<style>
.search-asset-portlet, .page-header, .pro-header-small {
    display: none !important;
}
.portlet-body > div > div.h2 {
    display: none;
}
</style>
<script type="text/javascript">
    $(document).ready(function() {
        $('.comment-portlet').css("cssText", "display: block !important;");
    });
</script>