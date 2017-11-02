<!-- Liste de documents - bleus -->
<#setting locale = locale />
<#assign fileEntryHelper = serviceLocator.findService("eu.strasbourg.utils.api.fileEntryHelperService") />
<#assign portletHelper = serviceLocator.findService("eu.strasbourg.utils.api.portletHelperService") />
<div class="seu-container">
    <div class="rte">
        <h2>
            ${portletHelper.getPortletTitle('eu.useful-documents', renderRequest)}
        </h2>
    </div>
    <#list entries as curEntry>
        <#assign file = curEntry.getAssetRenderer().getAssetObject() />
        <#if fileEntryHelper.getFileTitle??>
            <#assign fileTitle = fileEntryHelper.getFileTitle(file.getFileEntryId(), locale) />
        <#else>
            <#assign fileTitle = file.getTitle() />
        </#if>
        <a href="${fileEntryHelper.getFileEntryURL(file.getFileEntryId())}" class="seu-btn-square seu-bordered seu-core" target="_blank">
            <span class="seu-btn-text"> 
                ${fileTitle} (${file.getExtension()?upper_case} - ${fileEntryHelper.getReadableFileEntrySize(file.getFileEntryId(), locale)})
            </span>
            <span class="seu-btn-arrow"></span>
        </a>
    </#list>
</div>

