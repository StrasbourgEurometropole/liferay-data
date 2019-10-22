<!-- Détail événement -->
<#setting locale = locale />

<!-- image -->
<#assign imageUrl = ""/>
<#if !imageUrl?has_content>
    <#assign imageUrl = themeDisplay.getPortalURL() + entry.imageURL />
</#if>

<#-- Liste des infos a partager -->
<#assign openGraph = {
"og:title":"${entry.getEventScheduleDisplay(locale)} - ${entry.getTitle(locale)?html}",
"og:description":'${entry.getDescription(locale)?replace("<[^>]*>", "", "r")?html}', 
"og:image":"${imageUrl}"
} />
<#-- partage de la configuration open graph dans la request -->
${request.setAttribute("LIFERAY_SHARED_OPENGRAPH", openGraph)}


<div class="container mns-agenda-detail mns-fck">
    <div class="row">
        <div class="col-sm-3 hidden-xs">
            <figure>
                <img src="${entry.getImageURL()}" alt="${entry.getTitle(locale)}" class="mns-img-agenda-top" />
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
        <div class="col-sm-9 mns-content-agenda-detail" >
            <div class="col-xs-12">
                <span class="mns-event-date">${entry.getEventScheduleDisplay(locale)}</span>
                <h1>${entry.getTitle(locale)}</h1>
                <div class="mns-indic">
                    <span class="icon-ico-map-marker"></span>
                    <span>${entry.getPlaceAlias(locale)}</span>
                    <span class="icon-ico-type"></span>
                    <span>${entry.getTypeLabel(locale)}</span>
                </div>
                <span class="mns-line"></span>
                <p><strong>${entry.getSubtitle(locale)}</strong></p>
                <p>${entry.getDescription(locale)}</p>
            </div>

            <!--Calcul pour permettre de prendre pile la place qu'il faut aux 4 colonnes d'infos s'il y en a des manquantes-->
            <#assign i=1/>
            <#if entry.free == 1 || entry.getPrice(locale)?has_content>
                <#assign i=i+1>
            </#if>
            <#if entry.hasAnyAccessForDisabled() || entry.getAccessForDisabled(locale)?has_content >
                <#assign i=i+1>
            </#if>
            <#if entry.getAccess(locale)?has_content>
                <#assign i=i+1>
            </#if>

            <#if i ==4>
                <#assign column=6>
            <#else>
                <#assign column= 12/i />
            </#if>

            <div class="mns-info-more col-sm-${column}">
                <span class="mns-title-detail-actu"><@liferay_ui.message key="eu.dates-and-times" /></span>
                <#list entry.eventPeriods as period>
                    <p>
                    ${period.getDisplay(locale)}<#if period.getTimeDetail(locale)?has_content> : ${period.getTimeDetail(locale)}</#if>
                    </p>
                </#list>
            </div>
            <#if entry.free == 1 || entry.getPrice(locale)?has_content>
                <div class="mns-info-more col-sm-${column}">                 
                    <span class="mns-title-detail-actu">Tarifs</span>
                    <#if entry.free == 1>
                        <div class="free-event">
                            <@liferay_ui.message key="eu.free-event" />
                        </div>
                    </#if>
                    <p>${entry.getPrice(locale)}</p>
                </div>
            </#if>
            <#if entry.hasAnyAccessForDisabled() || entry.getAccessForDisabled(locale)?has_content >
                <div class="mns-info-more col-sm-${column}">
                    <span class="mns-title-detail-actu" style="padding-bottom: 10px;">Services aux Handicapés</span>
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
                    <p>
                        ${entry.getAccessForDisabled(locale)}
                    </p>
                </div>
            </#if>
            <#if entry.getAccess(locale)?has_content>
                <div class="mns-info-more col-sm-${column}">
                    <span class="mns-title-detail-actu">Transport</span>
                    <p>${entry.getAccess(locale)}</p>
                </div>
            </#if>

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