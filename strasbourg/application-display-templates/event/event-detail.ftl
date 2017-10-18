<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<!-- Détail événement -->
<div class="seu-container">
    <h1>${entry.getTitle(locale)}</h1>
    <div class="hat">
        <p>${entry.getSubtitle(locale)}</p>
    </div>
    <#if entry.imageURL?has_content>
        <div class="seu-event-visu">
            <img src="${entry.imageURL}" alt="${entry.getTitle(locale)}">
        </div>
    </#if>
    <div class="seu-event-categories" data-dot="1">${entry.getTypeLabel(locale)}</div>
    <div class="event-highlight">
        <div class="item-right">
            <div class="item-content">
                <#if entry.firstStartDate?date == entry.lastEndDate?date>
                    <div class="item-date"><@liferay_ui.message key="eu.event.the" /> <strong>${entry.firstStartDate?date?string.short?replace('/', '.')}</strong></div>
                <#else>
                    <div class="item-date"><@liferay_ui.message key="eu.event.from-date" /> <strong>${entry.firstStartDate?date?string.short?replace('/', '.')}</strong> <@liferay_ui.message key="eu.event.to" /> <strong>${entry.lastEndDate?date?string.short?replace('/', '.')}</strong></div>
                </#if>
                <div class="item-dates rte">
                    <h3 class="item-title"><@liferay_ui.message key="eu.next-dates" /></h3>
                    <ul class="seu-dates-list">
                        <#list entry.eventPeriods as period>
                            <li>
                                ${period.getDisplay(locale)}<#if period.getTimeDetail(locale)?has_content> : ${period.getTimeDetail(locale)}</#if>
                            </li>
                        </#list>
                    </ul>
                    <#if (entry.eventPeriods?size > 5)>
                        <div class="seu-line-left">
                            <button class="seu-see-more seu-btn-square seu-bordered seu-core">
                                <span class="seu-flexbox">
                                    <span class="seu-btn-text seu-more"><@liferay_ui.message key="eu.see-more" /></span>
                                    <span class="seu-btn-text seu-less"><@liferay_ui.message key="eu.see-less" /></span>
                                    <span class="seu-btn-arrow"></span>
                                </span>
                            </button>
                        </div>
                    </#if>
                </div>
            </div>
            <div class="item-infos">
                <div class="item-geoloc">
                    <span class="text">
                        <#if (entry.placeId > 0)>
                            <a href="${homeURL}place/-/entity/id/${entry.placeId}">
                                <strong>${entry.getPlaceAlias(locale)}</strong> 
                            </a>
                        <#else>
                            <strong>${entry.getPlaceAlias(locale)}</strong> 
                        </#if>
                        <br>
                        <#if entry.getPlaceAddress(locale)?has_content>
                            ${entry.getPlaceAddress(locale)} <br> 
                          </#if>
                          ${entry.placeZipCode} ${entry.getPlaceCity(locale)}
                    </span>
                </div>
                <!--
                <a href="" class="item-misc">
                    <span><@liferay_ui.message key="eu.add-to-calendar" /></span>
                </a>
                -->
            </div>
        </div>
    </div>
    <div class="rte">
        <h2><@liferay_ui.message key="eu.presentation" /></h2>
        ${entry.getDescription(locale)}
        <#if entry.promoter?has_content>
            <p>
                <strong><@liferay_ui.message key="eu.organized-by" /> : ${entry.promoter}</strong>
            </p>
        </#if>
        <#if entry.promoter?has_content>
            <p>
                <strong><@liferay_ui.message key="eu.organized-by" /> : ${entry.promoter}</strong>
            </p>
        </#if>
        <#if entry.publishedManifestations?has_content>
            <p>
                <strong><@liferay_ui.message key="eu.this-event-is-part-of" /> 
                    <#list entry.getPublishedManifestations() as manifestation>
                        <#if (manifestation?index > 0)>
                            - 
                        </#if>
                        <a href="${homeURL}manifestation/-/entity/id/${manifestation.manifestationId}">${manifestation.getTitle(locale)}</a>
                    </#list>
                </strong>
            </p>
        </#if>
    </div>
    <div class="seu-wi seu-wi-infos">
        <div class="seu-container">
            <h2 class="seu-section-title">
                <span class="seu-title"><@liferay_ui.message key="eu.practical-information" /></span>
            </h2>
            <div class="seu-wi-content">
                <div class="seu-left">
                    <div class="seu-wi-text">
                        <#if entry.free == 1 || entry.getPrice(locale)?has_content>
                            <div class="seu-wi-title"><@liferay_ui.message key="eu.prices" /></div>
                            <div class="rte">
                                <#if entry.free == 1>
                                    <div class="free-event"><@liferay_ui.message key="eu.free-event" /></div>
                                </#if>
                                ${entry.getPrice(locale)}
                            </div>
                        </#if>
                    </div>
                    <#if entry.getWebsiteName(locale)?has_content && entry.getWebsiteURL(locale)?has_content >
                        <div class="seu-wi-text">
                            <div class="seu-wi-title"><@liferay_ui.message key="eu.see-also" /></div>
                            <div class="rte">
                                <a href="entry.getWebsiteURL(locale)">${entry.getWebsiteName(locale)}</a>
                            </div>
                        </div>
                    </#if>
                    <#if entry.phone?has_content>
                        <div class="seu-wi-text">
                            <div class="seu-wi-title"><@liferay_ui.message key="contact" /></div>
                            <div class="rte">
                                <p><@liferay_ui.message key="phone" /> : ${entry.phone}</p>
                            </div>
                        </div>
                    </#if>
                </div>
                <div class="seu-right">
                    <#if entry.getAccess(locale)?has_content >
                        <div class="seu-wi-text">
                            <div class="seu-wi-title">Accès</div>
                            <div class="rte">
                                ${entry.getAccess(locale)}
                            </div>
                        </div>
                    </#if>
                    <#if entry.hasAnyAccessForDisabled() || entry.getAccessForDisabled(locale)?has_content >
                        <div class="seu-wi-text">
                            <div class="seu-wi-title"><@liferay_ui.message key="eu.access-for-disabled" /></div>
                            <div class="seu-pictos">
                                <#if entry.accessForWheelchair>
                                    <div class="seu-picto seu-picto-wheelchair"></div>
                                </#if>
                                <#if entry.accessForBlind>
                                    <div class="seu-picto seu-picto-wheelchair"></div>
                                </#if>
                                <#if entry.accessForDeaf>
                                    <div class="seu-picto seu-picto-wheelchair"></div>
                                </#if>
                                <#if entry.accessForElder>
                                    <div class="seu-picto seu-picto-wheelchair"></div>
                                </#if>
                                <#if entry.accessForDeficient>
                                    <div class="seu-picto seu-picto-wheelchair"></div>
                                </#if>
                            </div>
                            <div class="rte">
                                ${entry.getAccessForDisabled(locale)}
                            </div>
                        </div>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</div>