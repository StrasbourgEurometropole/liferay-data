<#-- Detail actualite -->

<#-- La documentation explicative de la modification des préférences du portlet est disponible sur le drive : Document (Asset publisher (Éléments relatifs)) -->
<#assign sliderTemplate =  "ddmTemplate_1809516"/>
<#assign typeActualite =  "1807609"/>

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
                <span><a href="${homeURL}accueil">Accueil</a>
                    <a href="${homeURL}actualites">Toutes les actualités</a>
                    <span class="breadcrumb_last">${title.getData()}</span>
                </span>
            </span>
        </div>
        <div class="pro-content pro-bloc-texte col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1 col-sm-12">
            ${content.getData()}
        </div>
    </div>
</article>

	<#assign PortalUtil = staticUtil["com.liferay.portal.kernel.util.PortalUtil"] />
	<#assign classNameId = PortalUtil.getClassNameId("com.liferay.journal.model.JournalArticle") />
	<#assign AssetVocabularyLocalServiceUtil = staticUtil["com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil"] />	
	
	<#assign scopGlobal = themeDisplay.getCompanyGroupId() />
	<#assign scop = themeDisplay.getSiteGroupId() />
	<#assign i = 0 />
	<#assign thematique = AssetVocabularyLocalServiceUtil.fetchGroupVocabulary(scop, "Thématique") />
	<#assign quartier = AssetVocabularyLocalServiceUtil.fetchGroupVocabulary(scopGlobal, "Territoire") />

	<#assign preferencesMap = {"anyAssetType" : "${classNameId}", "classNameIds" : "${classNameId}", "displayStyle" : "ddmTemplate_1809516", "anyClassTypeJournalArticleAssetRendererFactory" : "1807609",
	"classTypeIdsJournalArticleAssetRendererFactory" : "${typeActualite}", "classTypeIds" : "${typeActualite}", "delta" : "4"}	/>	
	
	<#-- On suggere les actualite avec la meme thematique que l'entite affichee -->
	<#list categoryList as cat >
		<#if cat.getVocabularyId() == thematique.getVocabularyId()>
			<#assign preferencesMap = preferencesMap + {"queryName${i}" : "assetCategories", "queryContains${i}" : "true", "queryValues${i}" : "${cat.getCategoryId()}"} >
			<#assign i++ />
		</#if>	
	</#list>
	
	<#-- On suggere les actualite avec le meme territoire que l'entite affichee -->
	<#list categoryList as cat>
		<#if cat.getVocabularyId() == quartier.getVocabularyId()>
			<#assign preferencesMap = preferencesMap + {"queryName${i}" : "assetCategories", "queryContains${i}" : "true", "queryValues${i}" : "${cat.getCategoryId()}"} >
			<#assign i++ />
		</#if>	
	</#list>

	<@liferay_portlet["runtime"]
		defaultPreferences=freeMarkerPortletPreferences.getPreferences(preferencesMap)
		portletProviderAction=portletProviderAction.VIEW
		portletName="com_liferay_asset_publisher_web_portlet_AssetPublisherPortlet"
		instanceId="article${journalArticleId}"
		/>

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
	
	$(document).ready(function() {
		//Change le titre du slider des actualite
		$(".pro-intro h2").text("CELA POURRAIT VOUS INTERESSER");
		$(".pro-intro p").hide();
	});
</script>