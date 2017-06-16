<!-- Documents utils -->
<#setting locale = locale />
<#if entries?has_content>
    <#assign FileEntryHelper = serviceLocator.findService("eu.strasbourg.utils.api.FileEntryHelperService") />

    <div class="title-with-picto-div doc-title"><@liferay_ui.message key='eu.useful-documents' /></div>
    <div class="bloc-util-docs">
        <#list entries as curEntry>
            <#if curEntry?has_content>
                <#assign file = curEntry.getAssetRenderer().getAssetObject() />
                <div class="doc">
                    <div class="desc">
                        <div class="name">
                            <a target="_blank" href="${FileEntryHelper.getFileEntryURL(file.getFileEntryId())}" title="${file.getTitle()} (<@liferay_ui.message key='new-window' />)">${file.getTitle()}</a>
                        </div>
                        <div class="size">${file.getExtension()?upper_case} — ${FileEntryHelper.getReadableFileEntrySize(file.getFileEntryId(), locale)}</div>
                    </div>
                    <div class="clearer">&nbsp;</div>
                </div>
            </#if>
        </#list>
    </div>
</#if>