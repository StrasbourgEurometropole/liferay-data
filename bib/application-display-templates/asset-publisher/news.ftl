<!-- Toutes les actualités -->
<#setting locale = locale />
<#assign assetPublisherTemplateHelperService = serviceLocator.findService("eu.strasbourg.utils.api.AssetPublisherTemplateHelperService")/>

<section id="medias" class="section align-center">
  <div class="container">
    <span class="icon section-icon icon-multimedia-08"></span>
    <#assign PortletHelper = serviceLocator.findService("eu.strasbourg.utils.api.PortletHelperService") />
    <h3>${PortletHelper.getPortletTitle('medias', renderRequest)}</h3>
    <br>
    <br>
    <#if entries?has_content>
        <#list entries as curEntry>
            <#assign docXml = saxReaderUtil.read(curEntry.getAssetRenderer().getArticle().getContentByLocale(locale)) />
            <#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()") />
            <#assign subtitle = docXml.valueOf("//dynamic-element[@name='subtitle']/dynamic-content/text()") />
            <#assign catcher = docXml.valueOf("//dynamic-element[@name='catcher']/dynamic-content/text()") />
            <#assign image = docXml.valueOf("//dynamic-element[@name='image']/dynamic-content/text()") />
            <#assign videoURL = docXml.valueOf("//dynamic-element[@name='videoURL']/dynamic-content/text()") />
            <#assign currentURL = assetPublisherHelper.getAssetViewURL(renderRequest, renderResponse, curEntry) />
            <#assign viewURL = curEntry.getAssetRenderer().getURLViewInContext(renderRequest, renderResponse, currentURL) />
            <#if image?has_content>
                <#assign imageURL = assetPublisherTemplateHelperService.getDocumentUrl(image) />
            </#if>
            <div class="col-sm-4">
              <div class="speaker">
                <div class="photo-wrapper"> 
                    <#if videoURL?has_content>
                        <iframe width="100%" height="205" src="${videoURL}" frameborder="0" allowfullscreen></iframe>    
                    <#else>
                      <#if imageURL?has_content>
                          <img src="${imageURL}" width="250" class="img-responsive">
                      </#if>
                    </#if>
                </div>
                <h3 class="name">${title}</h3>
                <p class="text-alt"><small>${subtitle}</small></p>
                <p class="about">
                    ${catcher}
                  <br><a href="${viewURL}"><@liferay_ui.message key="read-more" /></a>
                </p>
              </div>
            </div>
        </#list>
    </#if>
  </div>
</section>