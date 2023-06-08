<!-- Documents utiles -->
<#setting locale = locale />
<#assign fileEntryHelper = serviceLocator.findService("eu.strasbourg.utils.api.FileEntryHelperService") />
<#assign portletHelper = serviceLocator.findService("eu.strasbourg.utils.api.PortletHelperService") />

<#if entries?has_content>
    <div class="seu-wi seu-wi-crossreading">
        <div class="seu-container">
            <h2 class="seu-section-title">
                <span class="seu-title">
                    ${portletHelper.getPortletTitle('eu.useful-documents', renderRequest)}</span>
            </h2>
            <div class="seu-wi-content">
                <#if entries?has_content>
                    <#list entries as curEntry>
                        <#assign file = curEntry.getAssetRenderer().getAssetObject() />
                        <#if fileEntryHelper.getFileTitle??>
                            <#assign fileTitle = fileEntryHelper.getFileTitle(file.getFileEntryId(), locale) />
                        <#else>
                            <#assign fileTitle = file.getTitle() />
                        </#if>
                        <a  class="seu-btn-square seu-bordered seu-core" 
                            href="${fileEntryHelper.getFileEntryURL(file.getFileEntryId())}"
                            target="_blank"
                            title="${fileTitle}" 
                        >
                            <span class="seu-flexbox">
                                <span class="seu-btn-text">
                                    ${fileTitle} (${file.getExtension()?upper_case} - ${fileEntryHelper.getReadableFileEntrySize(file.getFileEntryId(), locale)})
                                </span>
                                <span class="seu-btn-arrow"></span>
                            </span>
                        </a>
                    </#list>
                </#if>
            </div>
        </div>
    </div>
</#if>
<#if !entries?has_content && themeDisplay.isSignedIn()>
    <div class="seu-container">
        Documents utiles - Aucune entrée (message non-visible par les visiteurs)
    </div>
</#if>