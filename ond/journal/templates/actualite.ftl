<div class="article-detail">
  <#if illustration.getAttribute('alt')?has_content>
    <h1 class="article-title ${illustration.getAttribute('alt')}">
  <#else>
    <h1 class="article-title ">
  </#if>
  ${title.getData()}</h1>
  <div class="article-date-publication">
    <#assign displaydate = .vars['reserved-article-display-date'].data?date('EEE, dd MMM yyyy hh:mm:ss Z')/>
    <#assign modifieddate = .vars['reserved-article-modified-date'].data?date('EEE, dd MMM yyyy hh:mm:ss Z')/> 
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
      <#if illustration.getAttribute('title')?has_content && illustration.getAttribute('alt')?has_content>
        <a title="${illustration.getAttribute('title')} © ${illustration.getAttribute('alt')} " href="${illustration.getData()}" class="magnific-popup">
      <#else>
        <a href="${illustration.getData()}" class="magnific-popup">
      </#if>
      <#if illustration.getAttribute('title')?has_content && illustration.getAttribute('alt')?has_content>
        <img src="${illustration.getData()}" alt="© ${illustration.getAttribute('alt')} " title="${illustration.getAttribute('title')}" />
      <#else>
        <img src="${illustration.getData()}" />
      </#if>
      </a>
      <div class="copyright-caption">
        <#if illustration.getAttribute('alt')?has_content>
          &copy;&nbsp;${illustration.getAttribute('alt')} &nbsp;
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
        <script type="text/javascript" src="//s7.addthis.com/js/300/addthis_widget.js#pubid=ra-51d143de770773c5"></script>
        <!-- AddThis Button END -->
    </div>
  </div>
</div>
<div class="previous-link-div">
  <div class="previous-link" style="background-color:transparent; padding-bottom:20px;">
    <a href="/web/ond/actualites"><@liferay_ui.message key='eu.all-news' /></a>
  </div>
</div>