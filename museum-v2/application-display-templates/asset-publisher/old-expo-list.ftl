<#setting locale = locale />
<#setting datetime_format="iso">
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>
<#assign portletHelper = serviceLocator.findService("eu.strasbourg.utils.api.PortletHelperService") />
<#assign dlFolderLocalService = serviceLocator.findService("com.liferay.document.library.kernel.service.DLFolderLocalService") />
<#assign dlFileEntryLocalService = serviceLocator.findService("com.liferay.document.library.kernel.service.DLFileEntryLocalService") />
<#assign fileEntryHelper = serviceLocator.findService("eu.strasbourg.utils.api.FileEntryHelperService") />

<section id="old-expo" class="margin-bottom margin-top">
    <div  class="content container">
        <h2>${portletHelper.getPortletTitle('eu.museum.old-expo', renderRequest)}</h2>
        <#if entries?has_content>
        	<#list entries as curEntry>
                <#assign folder = dlFolderLocalService.fetchFolder(curEntry.classPK) />
    	        <#assign subFolders = dlFolderLocalService.getFolders(groupId, folder.folderId) />
                	<#list subFolders?sort_by("name")?reverse as subFolder>
            	        <#assign files = dlFileEntryLocalService.getFileEntries(groupId, subFolder.folderId) />
            	        <#if files?has_content>
                            <div class="folder">
                                <button class="btn plus" data-list="files-${subFolder?counter}" aria-label="<@liferay_ui.message key="show" />"></button>
                                ${subFolder.name}
                            </div>
                            <div class="files files-${subFolder?counter}">
                                <#list files as file>
                                    <#assign documentURL = fileEntryHelper.getFileEntryURL(file.fileEntryId) />
                                    <a href="${documentURL}" target="_blank" aria-label="${file.title?html} (<@liferay_ui.message key="eu.new-window" />)" title="${file.title?html} (<@liferay_ui.message key="eu.new-window" />)" class="file">${file.title}</a>
                                </#list>
                            </div>
                        </#if>
                	</#list>
        	</#list>
        </#if>
    </div>
</section>

<script>
    $('.btn').click(function(element){
        if($(this).attr("class") == "btn minus"){
            $(this).removeClass("minus");
            $(this).addClass("plus");
            $(this).attr("aria-label", Liferay.Language.get("show"));
            $("." + $(this).attr("data-list")).css('display', "none");
        }else{
            $(this).removeClass("plus");
            $(this).addClass("minus");
            $(this).attr("aria-label", Liferay.Language.get("hide"));
            $("." + $(this).attr("data-list")).css('display', "flex");
        }
    });
</script>