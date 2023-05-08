<!-- Fil d'ariane -->
<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostnames?has_content || themeDisplay.scopeGroup.isStagingGroup()>
  <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
  <#assign homeURL = "/" />
</#if>
<div class="container">
    <div id="breadcrumb" class="pro-bloc">
        <span>
            <span><a href="${homeURL}"><@liferay_ui.message key="home" /></a>
                <#if entries?has_content>
                    <#list entries as curEntry>
                        <#if !curEntry.baseModel?has_content || curEntry.baseModel.friendlyURL != '/accueil'>
                                <#if curEntry?is_last>
                                    <span class="breadcrumb_last">${curEntry.getTitle()}</span>
                                <#else>
                                    ${curEntry.getTitle()}
                                </#if>
                            </li>
                        </#if>
                    </#list>
                </#if>
            </span>
        </span>
    </div>
</div>