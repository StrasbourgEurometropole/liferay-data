<#assign fileEntryHelper = serviceLocator.findService("eu.strasbourg.utils.api.FileEntryHelperService") />
<div class="images">
  <h2 class="title"><@liferay_ui["message"] key="eu.last-image" /></h2>
  <div class="diapo">
    <ul class="slider_home">

      <#if images?has_content && images.getSiblings()?has_content>
        <#list images.getSiblings() as image>
          <#assign file = fileEntryHelper.getFileEntryByRelativeURL(image.getData()) />
          <#assign fileTitle = fileEntryHelper.getStructureFieldValue(file.getFileEntryId(), "Titre", locale)!file.getTitle(locale) />
          <li class="${file.getFileEntryId()} ${fileTitle}">
              <#if file.description?has_content && file.description?starts_with("http")>
                <a href="${file.getDescription()}">
              </#if>
              <#if image.getAttribute('alt')?has_content>
                <img src="${image.getData()}" alt="© ${image.getAttribute('alt')} " />
              <#else>
                <img src="${image.getData()}" />
              </#if>
              <#if file.description?has_content && file.description?starts_with("http")>
                </a>
              </#if>
            <div class="headband">
              <span>
                ${fileTitle}
              </span>
              <#if image.getAttribute('alt')?has_content>
                <span class="copyright">
                    Copyright : ${image.getAttribute('alt')}
                </span>
              </#if>
            </div>
          </li>
        </#list>
      </#if>

    </ul>
    <a href="/web/ond/medias/visuels" class="btn-more" title="<@liferay_ui["message"] key="eu.see-all-galleries" />"><@liferay_ui["message"] key="eu.see-all-galleries" /></a>
  </div>
  <div class="clearfix"></div>
</div>