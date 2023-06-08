<!-- Liste des galeries -->
<#setting locale = locale />
<#assign assetPublisherTemplateHelperService = serviceLocator.findService("eu.strasbourg.utils.api.AssetPublisherTemplateHelperService")/>
        
<section id="gallery" class="section align-center">
  <div class="container">
    <span class="icon section-icon icon-multimedia-08"></span>
    <#assign PortletHelper = serviceLocator.findService("eu.strasbourg.utils.api.PortletHelperService") />
    <h3>${PortletHelper.getPortletTitle('Albums Photos', renderRequest)}</h3>
    <br>    
    <div class="gallery masonry">
      <#if entries?has_content>
        <#list entries as curEntry>
          <#assign docXml = saxReaderUtil.read(curEntry.getAssetRenderer().getArticle().getContentByLocale(locale)) />
          <#assign mainImage = docXml.valueOf("//dynamic-element[@name='mainImage']/dynamic-content/text()") />
          <#assign currentURL = assetPublisherHelper.getAssetViewURL(renderRequest, renderResponse, curEntry) />
          <#assign viewURL = curEntry.getAssetRenderer().getURLViewInContext(renderRequest, renderResponse, currentURL) />
          <#assign imageURL ="" />
          <#if mainImage?has_content>
            <#assign imageURL = assetPublisherTemplateHelperService.getDocumentUrl(mainImage) />
          </#if>
          <span class="masonry-item">
            <a href="${viewURL}" class="gallery-thumb-link">
              <img src="${imageURL}" alt="">
            </a>
          </span>
        </#list>
      </#if>
    </div>
  </div>
</section>