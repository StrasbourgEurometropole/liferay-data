<#setting locale = locale />

<!-- Liste des actes réglementaires et normatifs -->
<#assign portletHelper = serviceLocator.findService("eu.strasbourg.utils.api.PortletHelperService") />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
  <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
  <#assign homeURL = "/" />
</#if>
        
<#assign dLFileEntryLocalService = serviceLocator.findService("com.liferay.document.library.kernel.service.DLFileEntryLocalService")>
<#assign dateHelperService = serviceLocator.findService("eu.strasbourg.utils.api.DateHelperService") />
<#assign assetTagService = serviceLocator.findService("com.liferay.asset.kernel.service.AssetTagService") />

<main class="seu-container" style="margin-bottom: 50px">
    <div class="rte"> 
        <#list entries as curEntry>
            <#assign file = curEntry.getAssetRenderer().getAssetObject() />
            <#assign fileEntryHelper = serviceLocator.findService("eu.strasbourg.utils.api.FileEntryHelperService") />
            <#if file.description?has_content>
                <#assign description = file.description />
            <#else>
                <#assign description = file.getTitle()?keep_before_last(".") />
            </#if> 
            <#assign description = file.getTitle()?keep_before_last(".") />
            
            <div class="seu-wi seu-media seu-wi-download"> 
                <div class="seu-media-container"> 
                    <div class="seu-media-left"> 
                        <div class="seu-media-picto">&nbsp;</div> 
                    </div> 
                    <div class="seu-media-right"> 
                        <div class="seu-media-text"> 
                            <div class="seu-media-title">${description}</div> 
                            <p>
                                <#list curEntry.getTags() as tag >
        							<#if tag.getName() == "strasbourg"><@liferay.language key="eu.actes.strasbourg" /> -
        							<#elseif tag.getName() == "eurométropole"><@liferay.language key="eu.actes.eurometropole" /> -
        							</#if>
        						</#list>	
                                <#assign dlFileEntry = dLFileEntryLocalService.fetchDLFileEntry(file.getFileEntryId()) />
                                <#assign fileVersionId = dlFileEntry.getLatestFileVersion(true).getFileVersionId() />
                                <#assign ddmFormValuesMap = dlFileEntry.getDDMFormValuesMap(fileVersionId) />
                                <#list ddmFormValuesMap as ddmFormKeys,ddmFormValues>
                                    <#list ddmFormValues.getDDMFormFieldValues() as ddmFormFieldValue>
                                        <#if ddmFormFieldValue.getName() == "publicationDate">
                                            <span style="">Publié le </span>
                                            <#assign publicationDate = ddmFormFieldValue.getValue().getString(locale) />
                                            ${dateHelperService.displayShortDate(publicationDate?date.xs, locale)} -
                                        </#if>
                                    </#list>
                                </#list>
                                ${file.getExtension()?upper_case} - 
                                ${fileEntryHelper.getReadableFileEntrySize(file.getFileEntryId(), locale)}
                            </p> 
                        </div> 
                        <a class="seu-media-download seu-btn-square seu-filled seu-second" 
                            href="${fileEntryHelper.getFileEntryURL(file.getFileEntryId())}" target="_blank" 
                            title="${description} (nouvelle fenêtre)"> 
                            <div class="seu-btn-text-editable">
                                <span class="seu-flexbox">
                                    <span class="seu-btn-text">Télécharger</span> 
                                    <span class="seu-btn-arrow">&nbsp;</span> 
                                </span>
                            </div> 
                        </a>
                    </div> 
                </div> 
            </div> 
        </#list>
    </div>
</main>

<style>
    h3.asset-entries-group-label{
        display: none;
    }
</style>