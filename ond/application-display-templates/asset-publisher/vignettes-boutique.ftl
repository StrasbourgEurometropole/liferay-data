<div class="detail-commande">
    <#list entries as curEntry>
        <#assign docXml = saxReaderUtil.read(curEntry.getAssetRenderer().getArticle().getContentByLocale(locale)) />
        <#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()") />
        <#assign illustration = docXml.valueOf("//dynamic-element[@name='illustration']/dynamic-content/text()") />
		<#assign assetPublisherTemplateHelperService = serviceLocator.findService("eu.strasbourg.utils.api.AssetPublisherTemplateHelperService")/>
		<#assign imageURL ="" />
		<#if illustration?has_content>
			<#assign imageURL = assetPublisherTemplateHelperService.getDocumentUrl(illustration) />
		</#if>
        <#assign price = docXml.valueOf("//dynamic-element[@name='price']/dynamic-content/text()") />
        <#assign currentURL = assetPublisherHelper.getAssetViewURL(renderRequest, renderResponse, curEntry) />
        <#assign article = curEntry.getAssetRenderer().getArticle() />
     
        <@liferay_portlet.renderURL var="previewArticleContentURL" windowState="pop_up" portletName="com_liferay_journal_web_portlet_JournalPortlet">
		    <@portlet.param name="mvcPath" value="/preview_article_content.jsp" />
		    <@portlet.param name="groupId" value="${curEntry.getAssetRenderer().getArticle().getGroupId()}" />
		    <@portlet.param name="articleId" value="${curEntry.getAssetRenderer().getArticle().getArticleId()}" />
		    <@portlet.param name="version" value="${curEntry.getAssetRenderer().getArticle().getVersion()}" />
		    <@portlet.param name="ddmTemplateKey" value="${curEntry.getAssetRenderer().getArticle().getDDMTemplateKey()}" />
		</@liferay_portlet.renderURL>

        
		<article class="produit">
			<div class="entry-header">
				<a class="boutique-popup-link" href="${previewArticleContentURL}"><img src="${imageURL}" title="${title}" alt="${title}"></a>
				<h3> <a class="boutique-popup-link" href="${previewArticleContentURL}">${title}</a></h3>
			</div>
			<footer class="entry-meta">
				<span class="prix">${price}</span>
				<a class="btn-more boutique-popup-link" href="${previewArticleContentURL}" title="En savoir plus"> En savoir plus</a>
				<div class="clearfix"></div>
			</footer>
		</article> 
    </#list>

	<div class="clearfix"></div>
</div>