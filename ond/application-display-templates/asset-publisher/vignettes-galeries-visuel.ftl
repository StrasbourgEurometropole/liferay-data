<!-- Vignette Visuel -->
<#setting locale = locale />

<h2 class="subtitle visuel"><@liferay_ui.message key='eu.featured-galleries' /></h2>
<#if entries?has_content>
    <div class="content agenda galeries visuel">
    	<div class="lfr-search-container ">
    		<div id="cusImages" class="aui-widget aui-component aui-searchcontainer">
    			<div class="aui-searchcontainer-content" id="cusImagesSearchContainer">
    				<div summary class="list-evt">
                        <#list entries as curEntry>
                            <#assign docXml = saxReaderUtil.read(curEntry.getAssetRenderer().getArticle().getContentByLocale(locale)) />
                            <#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()") />
                            <#assign mainImage = docXml.valueOf("//dynamic-element[@name='mainImage']/dynamic-content/text()") />
                            <#assign assetPublisherTemplateHelperService = serviceLocator.findService("eu.strasbourg.utils.api.AssetPublisherTemplateHelperService")/>
							<#assign imageURL ="" />
							<#if mainImage?has_content>
								<#assign imageURL = assetPublisherTemplateHelperService.getDocumentUrl(mainImage) />
							</#if>
							<#assign content = docXml.valueOf("//dynamic-element[@name='content']/dynamic-content/text()") />
                            <#assign images = docXml.valueOf("//dynamic-element[@name='images']/dynamic-content/text()") />
                            <#assign publishDate = curEntry.getPublishDate() />
                            <#assign currentURL = assetPublisherHelper.getAssetViewURL(renderRequest, renderResponse, curEntry) />
                            <#assign viewURL = curEntry.getAssetRenderer().getURLViewInContext(renderRequest, renderResponse, currentURL) />
        					<div class="event portlet-visuel-item">
        						<div class="entry-image">
        							<a href="${viewURL}" title="${title}">
        								<img src="${imageURL}" alt="${title}">
        							</a>
        						</div>
        						<div class="entry-header">
        							<h2>
        								<a href="${viewURL}">${title}</a>
        							</h2>
        						</div>
        						<footer class="entry-meta">
        							<time>${publishDate?date}</time>
        							<a href="${viewURL}" title="<@liferay_ui.message key='eu.see-gallery' />" class="btn-more"><@liferay_ui.message key='eu.see-gallery' /></a>
        							<div class="clearfix"></div>
        						</footer>
        					</div>
                        </#list>
    				</div>
    			</div>
    		</div>
    	</div>
    </div>
</#if>