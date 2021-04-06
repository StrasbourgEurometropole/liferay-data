<#assign fileEntryHelper = serviceLocator.findService("eu.strasbourg.utils.api.FileEntryHelperService") />
<#assign file = fileEntryHelper.getFileEntryByRelativeURL(illustration.getData()) />
<#assign editorialTitle = fileEntryHelper.getFileTitle(file.getFileEntryId(), locale) />
<#assign copyright = fileEntryHelper.getImageCopyright(file.getFileEntryId(), locale) />


<div class="article-detail">
  <#if illustration.getAttribute('alt')?has_content>
    <h1 class="article-title ${illustration.getAttribute('alt')}">
  <#else>
    <h1 class="article-title ">
  </#if>
  ${title.getData()}</h1>
  <div class="article-date-publication">
    <!-- Pour que le parseDate fonctionne, on lui ordonne d'utiliser une locale Fr (on récup la date en Fr) -->
    <#setting locale="fr_FR"/>
    <#assign displaydate = .vars['reserved-article-display-date'].data?date('EEE, dd MMM yyyy hh:mm:ss Z')/>
    <#assign modifieddate = .vars['reserved-article-modified-date'].data?date('EEE, dd MMM yyyy hh:mm:ss Z')/>
    <#setting locale=locale/>
    <@liferay_ui.message key='eu.published' />
    <time>
      <#if displaydate == modifieddate>
        ${displaydate?string("dd/MM/yyyy")}
      <#else>
        ${modifieddate?string("dd/MM/yyyy")} 
      </#if>
    </time>
  </div>
  <div class="article-image">
    <div class="imgcontainer">
      <#if editorialTitle?has_content && copyright?has_content>
        <a title="${editorialTitle} © ${copyright} " href="${illustration.getData()}" class="magnific-popup">
      <#elseif editorialTitle?has_content >
        <a href="${illustration.getData()}" title ="${editorialTitle}" class="magnific-popup">
      <#else>
        <a href="${illustration.getData()}" class="magnific-popup">
      </#if>
      <#if editorialTitle?has_content && copyright?has_content>
        <img src="${illustration.getData()}?imagePreview=1" loading="lazy" alt="© ${copyright} " title="${editorialTitle}" />
      <#elseif editorialTitle?has_content >
        <img src="${illustration.getData()}?imagePreview=1" loading="lazy"  title="${editorialTitle}" />
      <#else>
        <img src="${illustration.getData()}?imagePreview=1" loading="lazy"  />
      </#if>
      </a>
      <div class="copyright-caption">
        <#if copyright?has_content>
          &copy;&nbsp;${copyright} &nbsp;
        </#if>
      </div>
    </div>
  </div>
  <div class="article-catcher">${catcher.getData()}</div>
  <div class="article-clear"></div>
  <div class="article-texte">
    ${text.getData()}
    <div class="social">
        <!-- AddThis Button BEGIN -->
        <span>Partager cet article : </span>
        <div class="addthis_toolbox addthis_default_style addthis_16x16_style">
          <a class="addthis_button_preferred_1"></a>
          <a class="addthis_button_preferred_2"></a>
          <a class="addthis_button_preferred_3"></a>
          <a class="addthis_button_compact"></a>
        </div>
        <script type="text/javascript">var addthis_config = {"data_track_addressbar":true};</script>
        <script type="text/javascript">
          // Service AddThis
          tarteaucitron.user.addthisPubId = 'ra-51d143de770773c5';
          (tarteaucitron.job = tarteaucitron.job || []).push('addthis');
        </script>
        <div class="addthis_sharing_toolbox"></div>
        <!-- AddThis Button END -->
    </div>
    
  </div>
</div>
<div class="previous-link-div">
  <div class="previous-link" style="background-color:transparent; padding-bottom:20px;">
    <a href="/web/ond/actualites"><@liferay_ui.message key='eu.all-news' /></a>
  </div>
</div>