<!-- Documents utiles -->
<#setting locale = locale />
<div class="useful-documents">
    <h3 class="useful-documents-title">
        <@liferay_ui.message key="eu.useful-documents" />
    </h3>
    <#if entries?has_content>
        <#assign FileEntryHelper = serviceLocator.findService("eu.strasbourg.utils.api.FileEntryHelperService") />
        <ul>
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
