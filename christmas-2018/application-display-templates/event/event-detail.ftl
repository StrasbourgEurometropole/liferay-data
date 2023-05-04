<!-- Détail événement -->
<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostnames?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<#assign imageUrl = ""/>
<!-- image -->
<#if entry.imageURL?has_content>
    <#if !entry.imageURL?contains('http')>
        <#assign imageUrl = themeDisplay.getPortalURL() />
    </#if>
    <#assign imageUrl = imageUrl + entry.imageURL?replace('@', "")?replace('cdn_hostroot_path', "") />
</#if>

<#-- Liste des infos a partager -->
<#assign openGraph = {
"og:title":"${entry.getEventScheduleDisplay(locale)} - ${entry.getTitle(locale)?html}",
"og:description":'${entry.getDescription(locale)?replace("<[^>]*>", "", "r")?html}', 
"og:image":"${imageUrl}"
} />
<#-- partage de la configuration open graph dans la request -->
${request.setAttribute("LIFERAY_SHARED_OPENGRAPH", openGraph)}

<div class="small-container mns-wrapper-agenda-detail mns-fck">
    <div class="row">
        <div class="mns-aside-info col-sm-4">
            <figure class="fit-cover">
                <img src="${entry.getImageURL()}" alt="${entry.getTitle(locale)}" width="300" height="380" class="mns-img-agenda-top" />
                <figcaption>© ${entry.getImageCopyright(locale)}</figcaption>
            </figure>
            <div class="mns-info-pratiques mns-info-test">
                <div class="mns-sec-info">
                    <h3 class="mns-title-info"><@liferay_ui.message key="eu.infos-and-contact" /></h3>
                    <p>${entry.getPlaceAlias(locale)} 
                        <br> 
                        <#if entry.getPlaceAddress(locale)?has_content>
                            ${entry.getPlaceAddress(locale)} - 
                        </#if>
                        ${entry.placeZipCode} ${entry.getPlaceCity(locale)}
                    </p>
                    <#if entry.getWebsiteName(locale)?has_content && entry.getWebsiteURL(locale)?has_content>
                        <a href="${entry.getWebsiteURL(locale)}">${entry.getWebsiteName(locale)}</a>
                    </#if>
                    <#if entry.email?has_content>
                        <a href="#contactForm" class="show-contact"><@liferay_ui.message key="contact" /></a>
                    </#if>
                    <#if entry.phone?has_content>
                        <a href="tel:${entry.phone}">${entry.phone}</a>
                    </#if>
                </div>
            </div>
        </div>
        <div class="col-sm-8 mns-content-agenda-detail">
            <a href="#" class="add-favorites"
                data-type="2" 
                data-title="${entry.getTitle(locale)}" 
                data-url="${themeDisplay.getPortalURL()}${homeURL}event/-/entity/id/${entry.eventId}/${entry.getNormalizedTitle(locale)}" 
                data-id="${entry.eventId}">
                <span><@liferay_ui.message key="eu.add-to-favorite" /></span>
            </a>
            <div class="col-xs-12" style="float: none;">
                <span class="mns-event-date">${entry.getEventScheduleDisplay(locale)}</span>
                <h1>${entry.getTitle(locale)}</h1>
                <div class="mns-indic">
                    <div>
                        <span class="icon-ico-map-marker"></span>
                        <span>${entry.getPlaceAlias(locale)}</span>
                    </div>
                    <div>
                        <span class="icon-ico-type"></span>
                        <span>${entry.getTypeLabel(locale)}</span>
                    </div>
                </div>
                <span class="mns-line"></span>
                <p><strong>${entry.getSubtitle(locale)}</strong></p>
                <p>${entry.getDescription(locale)}</p>
            </div>
            <div class="mns-wrapper-info-more">
                <div class="mns-info-more col-sm-6">
                    <span class="mns-title-detail-actu"><@liferay_ui.message key="eu.dates-and-times" /></span>
                    <#list entry.eventPeriods as period>
                        <p>
                        ${period.getDisplay(locale)}<#if period.getTimeDetail(locale)?has_content> : ${period.getTimeDetail(locale)}</#if>
                        </p>
                    </#list>
                </div>
                <div class="mns-info-more col-sm-6">
                    <#if entry.free == 1 || entry.getPrice(locale)?has_content>
                        <span class="mns-title-detail-actu"><@liferay_ui.message key="eu.prices" /></span>
                        <#if entry.free == 1>
                            <div class="free-event">
                                <@liferay_ui.message key="eu.free-event" />
                            </div>
                        </#if>
                        <p>${entry.getPrice(locale)}</p>
                    </#if>
                </div>
                <#if entry.bookingURL?has_content || entry.getBookingDescription(locale)?has_content>
                    <div class="mns-info-more col-xs-12">
                        <span class="mns-title-detail-actu col-sm-6"><@liferay_ui.message key="eu.booking" /></span>
                        <#if entry.getBookingDescription(locale)?has_content>
                            <p>${entry.getBookingDescription(locale)}</p>
                        </#if>
                        <#if entry.bookingURL?has_content>
                            <div class="booking">
                                <a href="${entry.bookingURL}" target="_blank"><@liferay_ui.message key="eu.book" /> </a>
                            </div>
                        </#if>
                    </div>
                </#if>
                <div class="mns-info-more col-sm-6">
                    <#if entry.hasAnyAccessForDisabled() || entry.getAccessForDisabled(locale)?has_content >
                        <span class="mns-title-detail-actu"><@liferay_ui.message key="eu.services-for-disabled" /></span>
                        <#if entry.accessForBlind>
                            <span class="icon-ico-1"></span>
                        </#if>
                        <#if entry.accessForDeficient>
                            <span class="icon-ico-2"></span>
                        </#if>
                        <#if entry.accessForDeaf>
                            <span class="icon-ico-3"></span>
                        </#if>
                        <#if entry.accessForWheelchair>
                            <span class="icon-ico-4"></span>
                        </#if>
                        <#if entry.accessForElder>
                            <span class="icon-ico-5"></span>
                        </#if>
                        <p>
                            ${entry.getAccessForDisabled(locale)}
                        </p>
                    </#if>
                </div>
                <div class="mns-info-more col-sm-6">
                    <#if entry.getAccess(locale)?has_content>
                        <span class="mns-title-detail-actu"><@liferay_ui.message key="access" /></span>
                        <p>${entry.getAccess(locale)}</p>
                    </#if>
                </div>

                <!-- Contact -->
                <#if entry.email?has_content>
                    <div class="col-xs-12">
                        <div class="mns-wi--collapsing <#if renderRequest.getAttribute("fromContactForm")?has_content && renderRequest.getAttribute("fromContactForm")>mns-first-opened</#if>">
                            <button class="mns-toggle-collapse email-collapse">
                                <h2 class="contact"><span><@liferay_ui.message key="contact" /></span></h2>
                            </button>
                            <div class="mns-collapsing-box white-box">
                                <div class="rte">
                                    <@liferay_portlet.actionURL var="contactURL" name="contact">
                                        <@liferay_portlet.param name="classPK" value="${entry.getEventId()}" />
                                        <@liferay_portlet.param name="to" value="${entry.email}" />
                                        <@liferay_portlet.param name="title" value="${entry.getTitle(locale)}" />
                                        <@liferay_portlet.param name="type" value="Event" />
                                    </@liferay_portlet.actionURL>

                                    <form id="contactForm" action="${contactURL}#contactForm" name="contactForm" method="post" class="mns-wi mns-wi-contact-form">
                                        <@liferay_ui.error key="all-fields-required" message="eu.all-fields-required" targetNode="#contactForm" />
                                        <@liferay_ui.error key="invalid-mail" message="eu.invalid-mail" targetNode="#contactForm" />
                                        <@liferay_ui.error key="recaptcha-error" message="eu.recaptcha-error" targetNode="#contactForm" />

                                        <#if renderRequest.getAttribute("mailSent")?has_content && renderRequest.getAttribute("mailSent")>
                                            <div class="mail-success">
                                                <@liferay_ui.message key="eu.mail-success" />
                                            </div>
                                        </#if>
                                        <div class="form-group">
                                            <label for="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_lastName"><@liferay_ui.message key="contact.lastname" /></label>
                                            <input class="form-control" name="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_lastName" aria-required="true" id="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_lastName" type="text" placeholder="<@liferay_ui.message key="contact.your-lastname" />" value="${renderRequest.getAttribute("lastName")!""}">
                                        </div>
                                        <div class="form-group">
                                            <label for="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_firstName"><@liferay_ui.message key="contact.firstname" /></label>
                                            <input class="form-control" name="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_firstName" aria-required="true" id="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_firstName" type="text" placeholder="<@liferay_ui.message key="contact.your-firstname" />" value="${renderRequest.getAttribute("firstName")!""}">
                                        </div>
                                        <div class="form-group">
                                            <label for="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_email"><@liferay_ui.message key="contact.mail" /></label>
                                            <input class="form-control" name="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_email" aria-required="true" id="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_email" type="text" placeholder="<@liferay_ui.message key="contact.your-mail" />" value="${renderRequest.getAttribute("email")!""}">
                                        </div>
                                        <div class="form-group mns-textarea">
                                            <label for="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_message"><@liferay_ui.message key="contact.request" /></label>
                                            <textarea class="form-control" name="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_message" id="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_message" placeholder="<@liferay_ui.message key="contact.your-message" />" rows="5">${renderRequest.getAttribute("message")!""}</textarea>
                                        </div>
                                        <div class="checkbox">
                                            <label for="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_notificationEmail">
                                                <input type="checkbox" name="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_notificationEmail" id="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_notificationEmail" value="true" checked>
                                                <span class="cr"><i class="cr-icon glyphicon glyphicon-ok"></i></span>
                                                <@liferay_ui.message key="eu.do-you-want-a-notification" />
                                            </label>
                                        </div>
                                        <div class="recaptcha" style="margin-top: 20px;">
                                            <div class="g-recaptcha" data-sitekey="${propsUtil.get('eu.strasbourg.recaptcha.public')}"></div>
                                        </div>
                                        <div class="mns-submit">
                                            <span></span>
                                            <input type="submit" id="submit" value="<@liferay_ui.message key="contact.send" />">
                                        </div>
                                    </form>
                                    <p class="mns-outro-form">
                                        <@liferay_ui.message key="contact.default-privacy" />
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </#if>
            </div>
        </div>
    </div>
</div>

<script>
(function ($) {
    $(document).ready(function(){
        if($('.mns-toggle-collapse').length){
            $('.mns-toggle-collapse').on('click', function(){
                $(this).toggleClass('mns-opened')
                .parent('.mns-wi--collapsing').toggleClass('mns-opened')
                .find('.mns-collapsing-box').slideToggle();
            });
            $('.mns-wi--collapsing.mns-first-opened .mns-toggle-collapse').click();
            $('.show-contact').on('click', function(){
                if($('.mns-wi--collapsing .mns-toggle-collapse.email-collapse:not(.mns-opened)').length > 0){
                    $('.mns-wi--collapsing .mns-toggle-collapse.email-collapse').click();
                }
            });
        }
    });
 }(jQuery));
</script>
