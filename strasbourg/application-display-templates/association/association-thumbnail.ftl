<!-- Vignette association -->

<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
  <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
  <#assign homeURL = "/" />
</#if>

<#assign plId = renderRequest.getAttribute("classNameLayoutId")[entry.getModelClassName()] />

<@liferay_portlet.renderURL plid=plId var="detailURL" portletName="eu_strasbourg_portlet_entity_detail_EntityDetailPortlet" windowState="normal">
    <@liferay_portlet.param name="classPK" value="${entry.assetEntry.classPK}" />
    <@liferay_portlet.param name="returnURL" value="${currentURL}" />
</@liferay_portlet.renderURL>

<@liferay_portlet.actionURL var="detailURLFilter">
    <@liferay_portlet.param name="userTargetClassId" value="${entry.assetEntry.classNameId}" />
    <@liferay_portlet.param name="userTargetClassPK" value="${entry.assetEntry.classPK}" />
    <@liferay_portlet.param name="userTargetTitle" value="${entry.getName(locale)}" />
    <@liferay_portlet.param name="detailURL" value="${detailURL}" />
    <@liferay_portlet.param name="searchLogId" value="${renderRequest.getAttribute('searchLogId')!0}" />
</@liferay_portlet.actionURL>


<div class="wi-search-result wi-search-generic wi-search-association">
    <div class="seu-result-left">
        <div class="seu-result-icon"></div>
    </div>
    <div class="seu-result-right">
        <div>
            <a class="seu-result-content" href="${detailURLFilter}">
                <h2 class="seu-result-title">${entry.getName(locale)}</h2>
                <div class="seu-result-catcher">
                    ${entry.getPresentation(locale)?replace("<[^>]*>", "", "r")[0..*100]}...
                </div>
            </a>
            <div class="contact">
                <#if entry.phone?has_content>
                    <p>
                        <@liferay_ui.message key="phone" /> : ${entry.phone}
                    </p>
                </#if>
                <#if entry.getSiteURL(locale)?has_content>
                    <p>
                        <a href="${entry.getSiteURL(locale)}" class="seu-external" title="<@liferay_ui.message key="eu.website" /> (<@liferay_ui.message key="eu.new-window" />)" target="_blank"><@liferay_ui.message key="eu.website" /></a>
                    </p>
                </#if>
                <#if entry.mail?has_content>
                    <p>
                        <a href="mailto:${entry.mail}" class="seu-external" title="${entry.mail}"><@liferay_ui.message key="to-contact" /></a>
                    </p>
                </#if>
                <#if entry.getFacebookURL(locale)?has_content>
                    <p>
                        <a href="${entry.getFacebookURL(locale)}" class="seu-external" title="<@liferay_ui.message key="facebook" /> (<@liferay_ui.message key="eu.new-window" />)" target="_blank"><@liferay_ui.message key="facebook" /></a>
                    </p>
                </#if>
            </div>
        </div>
        <div class="seu-result-infos">
            <div class="seu-result-infos-top">
            </div>
            <div class="seu-result-infos-bottom"> 
            </div>
        </div>
    </div>

</div>

<style>
.contact{
    display: flex;
    flex-direction: rox;
    flex-wrap: wrap;
}
.contact p{
    width: 50%;
}
p a{
    text-decoration: underline;
}
</style>
