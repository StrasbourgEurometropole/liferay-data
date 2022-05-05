<!-- Vignette offre -->
<#setting locale = locale />

<#assign plId = renderRequest.getAttribute("classNameLayoutId")[entry.getModelClassName()] />
<@liferay_portlet.renderURL plid=plId var="detailURL" portletName="eu_strasbourg_portlet_entity_detail_EntityDetailPortlet" windowState="normal">
    <@liferay_portlet.param name="classPK" value="${entry.getOfferId()}" />
    <@liferay_portlet.param name="returnURL" value="${currentURL}" />
</@liferay_portlet.renderURL>


<div class="wi-search-result wi-search-offer">
    <div class="seu-result-right">
        <a class="seu-result-content" href="${detailURL}">
            <h2 class="seu-result-title">${entry.getPost()}</h2>
            <div class="seu-result-catcher"><#if entry.direction ??>${entry.direction.getTitle(locale)}</#if>
                <#if entry.service??>
                  / ${entry.service.getTitle(locale)}
                </#if>
            </div>
            <#assign gradeRanges = entry.gradeRanges />
            <#if gradeRanges??>
                <div class="seu-result-grade">
                    <#list gradeRanges as gradeRange>
                        ${gradeRange[2].getTitle(locale)} 
                        <#if gradeRange[3]??>
                            <@liferay_ui.message key="eu.to" /> ${gradeRange[3].getTitle(locale)}<#sep>, </#sep>
                        </#if>
                    </#list>
                </div>
            </#if>
        </a>
        <#if .now < entry.publicationEndDate?datetime>
            <div class="seu-result-infos">
                <div class="seu-result-infos-top">
                    <@liferay_ui.message key="eu.offer-limit-date" />
                </div>
                <div class="seu-result-infos-bottom">
                    ${entry.getLimitDate()?datetime?string("dd/MM/yyyy")}
                </div>
            </div>
        </#if>
    </div>

</div>