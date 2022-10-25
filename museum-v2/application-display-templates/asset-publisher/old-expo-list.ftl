<#setting locale = locale />
<#setting datetime_format="iso">
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>
<#assign portletHelper = serviceLocator.findService("eu.strasbourg.utils.api.PortletHelperService") />
<#assign dlFolderLocalService = serviceLocator.findService("com.liferay.document.library.kernel.service.DLFolderLocalService") />
<#assign expositionFolder = dlFolderLocalService.getFolder(groupId , 0, "Expositions") />
<#assign dlFileEntryLocalService = serviceLocator.findService("com.liferay.document.library.kernel.service.DLFileEntryLocalService") />
<#assign fileEntryHelper = serviceLocator.findService("eu.strasbourg.utils.api.FileEntryHelperService") />

<section id="old-expo" class="margin-bottom margin-top">
    <div  class="content container">
        <h2>${portletHelper.getPortletTitle('eu.museum.old-expo', renderRequest)}</h2>
        <#if entries?has_content>
        	<#list entries as curEntry>
                <#assign folder = dlFolderLocalService.fetchFolder(curEntry.classPK) />
                <#if folder.parentFolderId == expositionFolder.folderId>
        	        <div class="folder">
                        <button class="btn minus" data-list="files-${curEntry?counter}"></button>
                        ${folder.name}
                    </div>
                    <#assign files = dlFileEntryLocalService.getFileEntries(groupId, folder.folderId) />
        	        <div class="files files-${curEntry?counter}">
                    	<#list files as file>
                            <#assign documentURL = fileEntryHelper.getFileEntryURL(file.fileEntryId) />
                	        <a href="${documentURL}" title="${file.title}" class="file">${file.title}</a>
                    	</#list>
                	</div>
                </#if>
        	</#list>
        </#if>
    </div>
</section>

<script>
    $('.btn').click(function(element){
        if($(this).attr("class") == "btn minus"){
            $(this).removeClass("minus");
            $(this).addClass("plus");
            $("." + $(this).attr("data-list")).css('display', "none");
        }else{
            $(this).removeClass("plus");
            $(this).addClass("minus");
            $("." + $(this).attr("data-list")).css('display', "flex");
        }
    });
</script>