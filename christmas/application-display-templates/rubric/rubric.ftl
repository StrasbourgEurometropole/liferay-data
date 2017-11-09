<#setting locale = locale />

<!-- Rubrique -->
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>
<div class="container mns-triple-actu-bloc">
    <#if entries?has_content>
        <div class="row" data-egalize=".mns-bloc-actu > a">
            <#list entries as currentPage>
                <div class="col-sm-4">
                    <div class="mns-bloc-actu">
                        <a href="${homeURL}${currentPage.friendlyURL?remove_beginning('/')}">
                            <figure class="mns-bloc-top-img">
                                <#if currentPage.expandoBridge.getAttribute('image')?has_content>
                                    <img src="${currentPage.expandoBridge.getAttribute('image')}" alt="${currentPage.getName(locale)}" width="370" height="250" />
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
                                        ${introduction}
                                    </#if>
                                    <#assign introduction = '' />
                                </p>
                                <span class="link"><@liferay_ui.message key="read-more" /></span>
                            </div>
                        </a>
                    </div>
                </div>
            </#list>
        </div>
    </#if>
</div>