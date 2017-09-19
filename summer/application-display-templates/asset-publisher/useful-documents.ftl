<!-- Documents utiles -->
<#setting locale = locale />
<div class="portlet-link-viewer">
  <div class="portlet-body" style="padding-bottom: 0">
    <span class="title-read-further title-with-picto-span">
      <@liferay_ui.message key="eu.useful-documents" />
    </span>
  <#if entries?has_content>
    <#assign FileEntryHelper = serviceLocator.findService("eu.strasbourg.utils.api.FileEntryHelperService") />
    <ul class="link-list" style="padding: 10px 5px 5px 5px;">
      <#list entries as curEntry>
          <#assign file = curEntry.getAssetRenderer().getAssetObject() />
          <li>
            <a href="${FileEntryHelper.getFileEntryURL(file.getFileEntryId())}" download >
              <div class="file-name">
                ${file.getTitle()}
              </div>
              <div class="file-type-and-size">
                ${file.getExtension()?upper_case} — ${FileEntryHelper.getReadableFileEntrySize(file.getFileEntryId(), locale)}
              </div>
            </a>
         </li>
       </#list>
    </ul>
  </#if>
</div>
