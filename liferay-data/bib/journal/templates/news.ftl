<!-- Détail news -->
<#setting locale = locale />
<section class="section align-center">
  <div class="container">
    <h3>
      ${title.getData()}
    </h3>
    <p class="alt-text">
      ${subtitle.getData()}
    </p>
    <#if videoURL?has_content && videoURL.getData()?has_content>
      <div class="video-wrapper">
        <iframe width="100%" height="205" src="${videoURL.getData()}" frameborder="0" allowfullscreen></iframe>
      </div>
    </#if>
    <div class="align-left">
      ${text.getData()}
    </div>
  </div>

</section>