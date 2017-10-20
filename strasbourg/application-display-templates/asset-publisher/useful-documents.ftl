<!-- Documents utiles -->
<#setting locale = locale />
<#assign FileEntryHelper = serviceLocator.findService("eu.strasbourg.utils.api.FileEntryHelperService") />

<div class="seu-wi seu-wi-crossreading">
    <div class="seu-container">
        <h2 class="seu-section-title">
            <span class="seu-title"><@liferay_ui.message key="eu.useful-documents" /></span>
        </h2>
        <div class="seu-wi-content">
            <#if entries?has_content>
                <#list entries as curEntry>
                    <#assign file = curEntry.getAssetRenderer().getAssetObject() />
                    <#if FileEntryHelper.getFileTitle??>
                        <#assign fileTitle = FileEntryHelper.getFileTitle(file.getFileEntryId(), locale) />
                    <#else>
                        <#assign fileTitle = file.getTitle() />
                    </#if>
                    <a  class="seu-btn-square seu-bordered seu-core" 
                        href="${FileEntryHelper.getFileEntryURL(file.getFileEntryId())}"target="_blank"
                        title="${fileTitle}" 
                        download>
                        <span class="seu-flexbox">
                            <span class="seu-btn-text">
                                ${fileTitle} (${file.getExtension()?upper_case} - ${FileEntryHelper.getReadableFileEntrySize(file.getFileEntryId(), locale)})
                            </span>
                            <span class="seu-btn-arrow"></span>
                        </span>
                    </a>
                </#list>
            </#if>
        </div>
    </div>
</div>
