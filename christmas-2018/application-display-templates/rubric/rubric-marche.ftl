<#setting locale = locale />

<!-- Rubrique -->
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>
<div class="container">

    <#if entries?has_content>
         <div class="row">
            <div class="small-container">
                <div class="row mns-p-wrapper-list-actu" data-egalize=".mns-bloc-actu > a">
                <#list entries as currentPage>
                    <div class="col-sm-4 col-xs-12">
                        <div class="mns-bloc-actu">
                            <a href="${homeURL}${currentPage.friendlyURL?remove_beginning('/')}">
                                <figure class="mns-bloc-top-img">
                                    <#if currentPage.expandoBridge.getAttribute('image')?has_content>
                                        <img src="${currentPage.expandoBridge.getAttribute('image')}" alt="${currentPage.getName(locale)}" width="450" height="300" />
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
                </#list>
            </div>
        </div>
    </#if>
</div>