<#setting locale = locale />
<div class="details">
<h2 class="subtitle">${entry.getTitle(locale)}</h2>
<!-- video --> 
<div class="video-detail-player">
     ${entry.getPlayer(locale)}
  </div>
  <div class="video-informations"> 
  <div class="clearer">&nbsp;</div>
  <p class="gray">
        <@liferay_ui.message key='eu.published' />
        <time>
            ${entry.getPublicationDate()?string("dd/MM/yyyy")} 
        </time>
        <#list entry.getCategories() as category>
        - ${category.getTitle(locale)}
        </#list>
        <#if entry.getCopyright(locale)?has_content>
        - &copy; ${entry.getCopyright(locale)}
        </#if>
      </p>
	<#if entry.getDescription(locale)?has_content>
      <div class="description ck-editor-content">
         ${entry.getDescription(locale)}
      </div>
    </#if>
	<#if entry.getTranscriptionDownloadURL()?has_content>
      <div class="video-transcription">
        <a href="${entry.getTranscriptionDownloadURL()}" download><@liferay_ui["message"] key="eu.video.transcription" /></a>
      </div>
    </#if>
</div>
<div class="button-gray button-gray-bottom-details"> <div class="left">&nbsp;</div> <div class="middle"><a href="/medias/videos">Retour vers les dernières vidéos</a></div> <div class="right">&nbsp;</div> </div>