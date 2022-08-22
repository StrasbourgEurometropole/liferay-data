<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<#assign uriHelper = serviceLocator.findService("eu.strasbourg.utils.api.UriHelperService")/>

<#assign imageUrl = ""/>
<!-- vignette -->
<#if entry.imageURL?has_content>
    <#if !entry.imageURL?contains('http')>
        <#assign imageUrl = themeDisplay.getPortalURL() />
    </#if>
    <#assign imageUrl= imageUrl + entry.imageURL?replace('@', "")?replace('cdn_hostroot_path', "") />
</#if>

<#-- Liste des infos a partager -->
<#assign openGraph = {
"og:title":"${entry.getEventScheduleDisplay(locale)} - ${entry.getTitle(locale)?html}",
"og:description":'${entry.getDescription(locale)?replace("<[^>]*>", "", "r")?html}', 
"og:image":"${imageUrl}"
} />
<#-- partage de la configuration open graph dans la request -->
${request.setAttribute("LIFERAY_SHARED_OPENGRAPH", openGraph)}

<!-- Détail événement -->
<div class="seu-container">
    <a href="#" class="add-favorites"
        data-type="2" 
        data-title="${entry.getTitle(locale)}" 
        data-url="${themeDisplay.getPortalURL()}${homeURL}evenement/-/entity/id/${entry.eventId}/${entry.getNormalizedTitle(locale)}" 
        data-id="${entry.eventId}">
        <span><@liferay_ui.message key="eu.add-to-favorite" /></span>
    </a>
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
                <#if entry.firstStartDate?has_content && entry.lastEndDate?has_content>
                    <#if entry.firstStartDate?date == entry.lastEndDate?date>
                        <div class="item-date"><@liferay_ui.message key="eu.event.the" /> <strong>${entry.firstStartDate?date?string.short?replace('/', '.')}</strong></div>
                    <#else>
                        <div class="item-date"><@liferay_ui.message key="eu.event.from-date" /> <strong>${entry.firstStartDate?date?string.short?replace('/', '.')}</strong> <@liferay_ui.message key="eu.event.to" /> <strong>${entry.lastEndDate?date?string.short?replace('/', '.')}</strong></div>
                    </#if>
                </#if>
                <div class="item-dates rte">
                    <h3 class="item-title"><@liferay_ui.message key="eu.next-dates" /></h3>
                    <ul class="seu-dates-list">
                        <#list entry.currentAndFuturePeriods as period>
                            <li>
                                ${period.getDisplay(locale)}<#if period.getTimeDetail(locale)?has_content> : ${period.getTimeDetail(locale)}</#if>
                            </li>
                        </#list>
                    </ul>
                    <#if (entry.currentAndFuturePeriods?size > 5)>
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
                            <a href="${homeURL}lieu/-/entity/id/${entry.placeId}/${uriHelper.normalizeToFriendlyUrl(entry.getPlaceAlias(locale))}">
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
                    <#if entry.registration>
                        <div class="seu-wi-text">
                            <div class="seu-wi-title"><@liferay_ui.message key="eu.registration" /></div>
                            <div class="rte">
                                <div class="registration-date"><@liferay_ui.message key="eu.registration.from" /> ${entry.registrationStartDate?date?string.long?replace('.', '')}</div>
                            </div>
                            <div class="rte">
                                <div class="registration-date"><@liferay_ui.message key="eu.registration.to" /> ${entry.registrationEndDate?date?string.long?replace('.', '')}</div>
                            </div>
                        </div>
                    </#if>
                    <#if entry.bookingURL?has_content || entry.getBookingDescription(locale)?has_content>
                        <div class="seu-wi-text">
                            <div class="seu-wi-title"><@liferay_ui.message key="eu.ticket-office" /></div>
                            <div class="rte">
                                <#if entry.getBookingDescription(locale)?has_content>
                                    <p>${entry.getBookingDescription(locale)}</p>
                                </#if>
                                <#if entry.bookingURL?has_content>
                                    <a href="${entry.bookingURL}" target="_blank"><@liferay_ui.message key="eu.book" /> </a>
                                </#if>
                            </div>
                        </div>
                    </#if>
                    <#if entry.getWebsiteName(locale)?has_content && entry.getWebsiteURL(locale)?has_content >
                        <div class="seu-wi-text">
                            <div class="seu-wi-title"><@liferay_ui.message key="eu.see-also" /></div>
                            <div class="rte">
                                <a href="${entry.getWebsiteURL(locale)}" target="_blank">${entry.getWebsiteName(locale)}</a>
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
                                    <div class="seu-picto seu-picto-wheelchair" title="<@liferay_ui.message key='eu.access-for-wheelchair' />"></div>
                                </#if>

                                <#if entry.accessForDeaf>
                                    <div class="seu-picto seu-picto-deaf" title="<@liferay_ui.message key='eu.access-for-deaf' />"></div>
                                </#if>
                                
                                <#if entry.accessForBlind>
                                    <div class="seu-picto seu-picto-blind" title="<@liferay_ui.message key='eu.access-for-blind' />"></div>
                                </#if>

                                <#if entry.accessForDeficient>
                                    <div class="seu-picto seu-picto-deficient" title="<@liferay_ui.message key='eu.access-for-blind' />"></div>
                                </#if>
                                
                                <#if entry.accessForElder>
                                    <div class="seu-picto seu-picto-elder" title="<@liferay_ui.message key='eu.access-for-elder' />"></div>
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


    <#if entry.email?has_content>
        <div class="rte row">
            <h2><@liferay_ui.message key="contact" /></h2>

            <@liferay_portlet.actionURL var="contactURL" name="contact">
                <@liferay_portlet.param name="classPK" value="${entry.getEventId()}" />
                <@liferay_portlet.param name="to" value="${entry.email}" />
                <@liferay_portlet.param name="title" value="${entry.getTitle(locale)}" />
                <@liferay_portlet.param name="type" value="Event" />
            </@liferay_portlet.actionURL>
            
            <form id="contactForm" action="${contactURL}#contactForm" name="contactForm" method="post" class="seu-wi seu-wi-contact-form col-md-8">

                <@liferay_ui.error key="all-fields-required" message="eu.all-fields-required" targetNode="#contactForm" />
                <@liferay_ui.error key="invalid-mail" message="eu.invalid-mail" targetNode="#contactForm" />
                <@liferay_ui.error key="recaptcha-error" message="eu.recaptcha-error" targetNode="#contactForm" />

                <#if renderRequest.getAttribute("mailSent")?has_content && renderRequest.getAttribute("mailSent")>
                    <p class="mail-success">
                       <@liferay_ui.message key="eu.mail-success" />
                    </p>
                </#if>
                <div class="inline-group">
                    <div class="widget widget-required">
                        <div class="title">
                            <label for="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_lastName"><@liferay_ui.message key="contact.lastname" /></label>
                        </div>
                        <div class="content">
                            <input name="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_lastName" aria-required="true" id="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_lastName" type="text" placeholder="<@liferay_ui.message key="contact.your-lastname" />" value="${renderRequest.getAttribute("lastName")!""}">
                        </div>
                    </div>
                    <div class="widget widget-required">
                        <div class="title">
                            <label for="prenom"><@liferay_ui.message key="contact.firstname" /></label>
                        </div>
                        <div class="content">
                            <input name="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_firstName" aria-required="true" id="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_firstName" type="text" placeholder="<@liferay_ui.message key="contact.your-firstname" />" value="${renderRequest.getAttribute("firstName")!""}">
                        </div>
                    </div>
                </div>
                <div class="widget widget-required">
                    <div class="title">
                        <label for="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_email"><@liferay_ui.message key="contact.mail" /></label>
                    </div>
                    <div class="content">
                        <input name="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_email" aria-required="true" id="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_email" type="text" placeholder="<@liferay_ui.message key="contact.your-mail" />" value="${renderRequest.getAttribute("email")!""}">
                    </div>
                </div>
                <div class="widget widget-required">
                    <div class="title">
                        <label for="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_message"><@liferay_ui.message key="contact.request" /></label>
                    </div>
                    <div class="content">
                        <textarea name="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_message" id="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_message" placeholder="<@liferay_ui.message key="contact.your-message" />" rows="5">${renderRequest.getAttribute("message")!""}</textarea>
                    </div>
                </div>
                <div>
                    <label for="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_notificationEmail">

                    <input type="checkbox" class="notification-email"
                        name="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_notificationEmail" checked>&nbsp;<@liferay_ui.message key="eu.do-you-want-a-notification" />
                    </label>
                </div>
                <div style="margin: 20px 0;" class="g-recaptcha" data-sitekey="${propsUtil.get('eu.strasbourg.recaptcha.public')}"></div>
                <div style="padding-top: 20px; padding-bottom: 20px;">
                    <@liferay_ui.message key="contact.default-privacy" />
                </div>
                <div class="buttons submit">
                    <div class="SubmitWidget widget submit-button">
                        <div class="content"><button name="submit" value="<@liferay_ui.message key="send" />"><@liferay_ui.message key="send" /></button></div>
                    </div>
                </div>
            </form>
        </div>
    </#if>  
</div>

<style>
    .lfr-alert-container {
        position: static;
        padding: 40px;
        margin-bottom: 25px;
        background: #EF5350;
    }
    
    .lfr-alert-container .lfr-alert-wrapper {
        margin-bottom: 0;
        padding: 0;
        height: auto !important;
    }
    
    .lfr-alert-container .lfr-alert-wrapper + .lfr-alert-wrapper {
        margin-top: 15px;
    }
    
    .lfr-alert-container .alert-danger {
        background: none;
        border: none;
        margin: 0;
        padding: 0;
        color: white;
        line-height: 25px
    }
    
    .lfr-alert-container .alert-danger button,
    .lfr-alert-container .alert-danger .lead {
        display: none;
    }
    
    .mail-success {
        background: #4CAF50;
        color: white;
        padding: 40px;
    }
    .previous-button {
        margin-top: 40px;
    }
    .previous-button button {
        margin-top: 40px;
        font-weight: normal;
    }
</style>

