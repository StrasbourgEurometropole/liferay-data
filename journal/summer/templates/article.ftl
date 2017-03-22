<div class="article-detail entry-content">
  <div class="article-title">
    <h1>
      <span class="article-title-content">
        ${title.getData()}
      </span>
    </h1> 
  </div>
  <div class="article-image">
    <div class="imgcontainer">
      <#if image.getData()?has_content>
         <img class="f-left" src="${image.getData()}" alt="${image.getData()}" class="captioned"  />
         
          <#assign fileEntryHelper = serviceLocator.findService("eu.strasbourg.utils.api.FileEntryHelperService") />
          <#assign file = fileEntryHelper.getFileEntryByRelativeURL(image.getData()) />
          <#assign copyright = fileEntryHelper.getImageCopyright(file.getFileEntryId(), locale) />
          <#if copyright?has_content>
            <div class="copyright-caption">
              &copy; ${copyright}
            </div>
          </#if>
       </#if>
    </div>
  </div>
  <div class="article-clear"></div>
  <div class="article-catcher">
    ${catcher.getData()}
  </div>
  <div class="article-texte">
    ${text.getData()}
  </div>
  <div class="clearer">&nbsp;</div>
                 
</div>