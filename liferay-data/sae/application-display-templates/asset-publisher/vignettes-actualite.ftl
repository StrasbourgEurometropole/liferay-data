<div class="portlet-body">
	<h1 class="cus-news-header-title"><@liferay_ui["message"] key="eu.news" /></h1> 
	<#list entries as curEntry>
        <#assign docXml = saxReaderUtil.read(curEntry.getAssetRenderer().getArticle().getContentByLocale(locale)) />
        <#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()") />
        <#assign catcher = docXml.valueOf("//dynamic-element[@name='catcher']/dynamic-content/text()") />
        <#assign illustration = docXml.valueOf("//dynamic-element[@name='illustration']/dynamic-content/text()") />
		<#assign assetPublisherTemplateHelperService = serviceLocator.findService("eu.strasbourg.utils.api.AssetPublisherTemplateHelperService")/>
		<#assign imageURL ="" />
		<#if illustration?has_content>
			<#assign imageURL = assetPublisherTemplateHelperService.getDocumentUrl(illustration) />
		</#if>
                    
        <#assign publishDate = curEntry.getPublishDate() />
        <#assign currentURL = assetPublisherHelper.getAssetViewURL(renderRequest, renderResponse, curEntry) />
        <#assign viewURL = curEntry.getAssetRenderer().getURLViewInContext(renderRequest, renderResponse, currentURL) />
		<div class="cus-news"> 
			<div class="cus-news-illustration"> 
				<a href="${viewURL}" title="${title}"><img src="${imageURL}" id="illustration"></a> 
			</div> 
			<div class="cus-news-content"> 
				<span class="cus-news-theme"> </span> 
				<div class="cus-news-publication-date"> - ${publishDate?date}</div>
				<div class="cus-news-title"> 
					<a href="${viewURL}" title="${title}">${title}</a>
				</div>
				<div class="cus-news-catcher"> 
					<p>${catcher}</p>
				</div>
				<a href="${viewURL}" title="${title}" class="image-link" id="_101_INSTANCE_W1hB_ contentURL" name="_101_INSTANCE_W1hB_contentURL"> 
					<@liferay_ui.message key='learn-more' />
				</a>
			</div> 
			<div class="clearer"></div> 
			<div class="asset-metadata"> </div> 
		</div> 
	</#list>
</div>