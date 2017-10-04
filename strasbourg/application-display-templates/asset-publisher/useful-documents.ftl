<!-- Documents utiles -->
<#setting locale = locale />
<#assign FileEntryHelper = serviceLocator.findService("eu.strasbourg.utils.api.FileEntryHelperService") />

<div class="seu-container">
    <#if entries?has_content>
    <#list entries as curEntry>
    <#assign file = curEntry.getAssetRenderer().getAssetObject() />
    <div class="seu-wi seu-media seu-wi-download">
        <div class="seu-media-container">
            <div class="seu-media-left">
                <div class="seu-media-picto">&nbsp;</div>
            </div>
            <div class="seu-media-right">
                <div class="seu-media-text">
                    <#if FileEntryHelper.getFileeTitle??>
                        <#assign fileTitle = FileEntryHelper.getFileTitle(file.getFileEntryId(), locale) />
                    <#else>
                        <#assign fileTitle = file.getTitle() />
                    </#if>
                    <div class="seu-media-title">${fileTitle}</div>
                    <p>${file.getExtension()?upper_case} - ${FileEntryHelper.getReadableFileEntrySize(file.getFileEntryId(), locale)}</p>
                </div>
                <a class="seu-media-download seu-btn-square seu-filled seu-second" href="${FileEntryHelper.getFileEntryURL(file.getFileEntryId())}" target="_blank" title="palais.jpg (nouvelle fenêtre)" download>
                    <div class="seu-btn-text-editable"><span class="seu-flexbox"><span class="seu-btn-text"><@liferay_ui.message key="download" /></span> <span class="seu-btn-arrow">&nbsp;</span> </span></div>
                </a>
            </div>
        </div>
    </div>
    </#list>
    </#if>
</div>