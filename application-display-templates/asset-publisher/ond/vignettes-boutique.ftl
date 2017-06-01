<div class="detail-commande">
    <#list entries as curEntry>
        <#assign docXml = saxReaderUtil.read(curEntry.getAssetRenderer().getArticle().getContentByLocale(locale)) />
        <#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()") />
        <#assign illustration = docXml.valueOf("//dynamic-element[@name='illustration']/dynamic-content/text()") />
        <#assign price = docXml.valueOf("//dynamic-element[@name='price']/dynamic-content/text()") />
        <#assign currentURL = assetPublisherHelper.getAssetViewURL(renderRequest, renderResponse, curEntry) />
        <#assign viewURL = curEntry.getAssetRenderer().getURLViewInContext(renderRequest, renderResponse, currentURL)/>
        <#assign article = curEntry.getAssetRenderer().getArticle() />
        <#assign viewURL = "accueil?p_p_id=com_liferay_journal_web_portlet_JournalPortlet&p_p_state=pop_up&_com_liferay_journal_web_portlet_JournalPortlet_mvcPath=%2Fpreview_article_content.jsp&_com_liferay_journal_web_portlet_JournalPortlet_groupId="+article.groupId+"&_com_liferay_journal_web_portlet_JournalPortlet_articleId="+article.articleId+"&_com_liferay_journal_web_portlet_JournalPortlet_version="+article.version+"&p_p_auth=w6zUDug6" />
        
		<article class="produit">
			<div class="entry-header">
				<a class="boutique-popup-link" href="${viewURL}"><img src="${illustration}" title="${title}" alt="${title}"></a>
				<h3> <a class="boutique-popup-link" href="${viewURL}">${title}</a></h3>
			</div>
			<footer class="entry-meta">
				<span class="prix">${price}</span>
				<a class="btn-more boutique-popup-link" href="${viewURL}" title="En savoir plus"> En savoir plus</a>
				<div class="clearfix"></div>
			</footer>
		</article> 
    </#list>

	<div class="clearfix"></div>
</div>