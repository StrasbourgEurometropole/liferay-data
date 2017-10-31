<!-- Liste de documents - bleus -->
<#setting locale = locale />
<#assign FileEntryHelper = serviceLocator.findService("eu.strasbourg.utils.api.FileEntryHelperService") />
<#assign PortletHelper = serviceLocator.findService("eu.strasbourg.utils.api.PortletHelperService") />
<div class="seu-container">
    <div class="rte">
        <h2>
            ${PortletHelper.getPortletTitle('eu.useful-documents', renderRequest)}
        </h2>
    </div>
    <#list entries as curEntry>
        <#assign file = curEntry.getAssetRenderer().getAssetObject() />
        <#if FileEntryHelper.getFileTitle??>
            <#assign fileTitle = FileEntryHelper.getFileTitle(file.getFileEntryId(), locale) />
        <#else>
            <#assign fileTitle = file.getTitle() />
        </#if>
        <a href="${FileEntryHelper.getFileEntryURL(file.getFileEntryId())}" class="seu-btn-square seu-bordered seu-core" target="_blank">
            <span class="seu-btn-text"> 
                ${fileTitle} (${file.getExtension()?upper_case} - ${FileEntryHelper.getReadableFileEntrySize(file.getFileEntryId(), locale)})
            </span>
            <span class="seu-btn-arrow"></span>
        </a>
    </#list>
</div>

