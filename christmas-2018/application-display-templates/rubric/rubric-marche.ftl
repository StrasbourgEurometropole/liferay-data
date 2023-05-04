<#setting locale = locale />

<!-- Rubrique -->
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostnames?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>
<div class="container">

    <#if entries?has_content>
        <#assign hasPage = false />
        <#list entries as currentPage>
            <#if !currentPage.isHidden()>
                <#if !hasPage>
                    <div class="row">
                        <div class="small-container mns-thematiques">
                            <h2><@liferay_ui.message key="eu.thematics" /></h2>
                            <div class="row mns-p-wrapper-list-actu" data-egalize=".mns-bloc-actu > a">
                        <#assign hasPage = true />
                </#if>
                <div class="col-sm-4 col-xs-12">
                    <div class="mns-bloc-actu">
                        <a href="${homeURL}${currentPage.friendlyURL?remove_beginning('/')}">
                            <figure class="mns-bloc-top-img">
                                <#if currentPage.expandoBridge.getAttribute('image')?has_content>
                                    <img src="${currentPage.expandoBridge.getAttribute('image')}" alt="${currentPage.getName(locale)}" width="290" height="195" />
                                </#if>
                            </figure>
                            <div class="mns-bloc-content-actu">
                                <h4>${currentPage.getName(locale)}</h4>
                                <p>
                                    <#if currentPage.expandoBridge.getAttribute('introduction')?has_content>
                                    <#assign introductionAttribute = currentPage.expandoBridge.getAttribute('introduction') />
                                        <#list introductionAttribute?keys as key> 
                                            <#if key == locale>
                                                <#assign introduction = introductionAttribute?values[key_index] />
                                            </#if>
                                        </#list>
                                    </#if>
                                    <#if introduction?has_content>
                                        ${introduction?replace("<[^>]*>", "", "r")[0..*100]}...
                                    </#if>
                                    <#assign introduction = '' />
                                </p>
                                <span class="basic-link"><@liferay_ui.message key="learn-more" /></span>
                            </div>
                        </a>
                    </div>
                </div>
            </#if>
        </#list>
        <#if hasPage>
                    </div>
                </div>
            </div>
        </#if>
    </#if>
</div>
