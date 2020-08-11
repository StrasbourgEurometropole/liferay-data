<!-- Vignette lieu -->

<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<#assign plId = renderRequest.getAttribute("classNameLayoutId")[entry.getModelClassName()] />

<@liferay_portlet.renderURL plid=plId var="detailURL" portletName="eu_strasbourg_portlet_entity_detail_EntityDetailPortlet" windowState="normal">
    <@liferay_portlet.param name="classPK" value="${entry.getOfferId()}" />
    <@liferay_portlet.param name="returnURL" value="${currentURL}" />
</@liferay_portlet.renderURL>


<div class="wi-search-result wi-offer-thumbnail">
    <div class="seu-result-right">
        <a class="seu-result-content" href="${detailURL}">
            <h2 class="seu-result-title">${entry.getPost()}</h2>
            <div class="seu-result-catcher">${entry.offerDirection.getName()} / ${entry.offerService.getName()}</div>
            <div class="seu-result-catcher">${entry.offerGrade.getName()}</div>
        </a>
        <div class="seu-result-infos">
            <div class="seu-result-offer-infos-top">
                <@liferay_ui.message key="eu.offer-limit-date" />
            </div>
            <div class="seu-result-offer-infos-bottom">
                ${entry.getLimitDate()?date}
            </div>
        </div>
    </div>

</div>
