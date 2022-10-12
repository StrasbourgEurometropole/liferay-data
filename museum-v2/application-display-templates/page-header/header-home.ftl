<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<#assign fileEntryHelper = serviceLocator.findService("eu.strasbourg.utils.api.FileEntryHelperService") />
<#assign imageURL = fileEntryHelper.getRandomFileURLByGroupIdAndFolderName(groupId, "Header images") />

<section id="header" class="home margin-bottom">
    <h1>
        <#if page.expandoBridge.getAttribute('introduction')?has_content>
            <#assign introductionAttribute = page.expandoBridge.getAttribute('introduction') />
            <#list introductionAttribute?keys as key> 
                <#if key == locale>
                    <#assign introduction = introductionAttribute?values[key_index] />
                </#if>
            </#list>
        </#if>
        <#if introduction?has_content>
            ${introduction}
        </#if>
    </h1>
    <div class="menu">
        <@liferay_portlet["runtime"]
        portletProviderAction=portletProviderAction.VIEW
        instanceId="menu"
        portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet" />
    </div>
</section>

<style>
    #global #header.home{
        background-image: url(${imageURL});
    }
</style>