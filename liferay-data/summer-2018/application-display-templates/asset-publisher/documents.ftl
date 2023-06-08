<!-- Documents utiles -->
<#setting locale = locale />
<#assign fileEntryHelper = serviceLocator.findService("eu.strasbourg.utils.api.FileEntryHelperService") />
<#assign portletHelper = serviceLocator.findService("eu.strasbourg.utils.api.PortletHelperService") />

<#if entries?has_content>
    <div>
        <div>
            <h3>
                <span>
                    ${portletHelper.getPortletTitle('eu.useful-documents', renderRequest)}</span>
            </h3>
            <div>
                <#if entries?has_content>
                    <#list entries as curEntry>
                        <#assign file = curEntry.getAssetRenderer().getAssetObject() />
                        <#if fileEntryHelper.getFileTitle??>
                            <#assign fileTitle = fileEntryHelper.getFileTitle(file.getFileEntryId(), locale) />
                        <#else>
                            <#assign fileTitle = file.getTitle() />
                        </#if>
                        <a  class="link" style="margin-bottom: 10px;" 
                            href="${fileEntryHelper.getFileEntryURL(file.getFileEntryId())}"
                            target="_blank"
                            title="${fileTitle}" >
                            <span>
                                <span>
                                    ${fileTitle} (${file.getExtension()?upper_case} - ${fileEntryHelper.getReadableFileEntrySize(file.getFileEntryId(), locale)})
                                </span>
                            </span>
                        </a>
                    </#list>
                </#if>
            </div>
        </div>
    </div>
</#if>
<#if !entries?has_content && themeDisplay.isSignedIn()>
    <div>
        Documents utiles - Aucune entrée (message non-visible par les visiteurs)
    </div>
</#if>