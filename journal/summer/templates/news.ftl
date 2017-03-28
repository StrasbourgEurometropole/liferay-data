<#setting locale = locale />
<#setting date_format = "d MMMM yyyy">
<div class="article-detail actu_detail entry-content">
  <div class="article-title">
        <h1>
          <span class="article-title-content">
            ${title.getData()}
          </span>
        </h1>         
      </div>
    <span class="date_detail_actu">
      ${.vars['reserved-article-display-date'].data?date('EEE, dd MMM yyyy hh:mm:ss Z')} 
    </span>
  <br><br>

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
        ${chapo.getData()}
    </div>
  <div class="article-texte">
    ${content.getData()}
  </div>
<br>
<div class="previous-link" style="background-color:transparent; padding-bottom:20px;"><p><a href="javascript:history.back()"><@liferay_ui.message key='eu.news.all-news' /></a></p></div>
