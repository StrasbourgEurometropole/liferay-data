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

<#assign assetVocabularyHelper = serviceLocator.findService("eu.strasbourg.utils.api.AssetVocabularyHelperService") />

<li class="grid-item">
    <div class="wi-search-result" style="padding-top: 0; margin-right: 20px;">
        <div class="seu-result-icon"></div>
    </div>
    <div class="item-right">
        <div class="item-content">
            <a class="item-link" href="${detailURLFilter}" title="${entry.getName(locale)}">
                <h3 class="item-title" data-dot="1">${entry.getName(locale)}</h3>
                <div class="item-description" data-dot="3">
                    ${entry.getPresentation(locale)}
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
    </div>
</li>

<style>
    #seu-grid--list01 .grid-item .item-infos .item-geoloc:after{
        content:none;
    }
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