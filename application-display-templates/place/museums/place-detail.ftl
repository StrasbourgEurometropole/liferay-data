<!-- Détail lieu -->
<#setting locale = locale />
<#setting date_format="d MMMM yyyy">

<#if entry?has_content>
    <div class="place-detail">

        <div class="place-header">
            <h1 class="place-title">${entry.getAlias(locale)}</h1>

            <div class="place-address">
                <#if entry.addressStreet?has_content>
                    ${entry.addressStreet} -
                </#if>
                <#if entry.addressComplement?has_content>
                    ${entry.addressComplement} -
                </#if>
                <#if entry.addressDistribution?has_content>
                    ${entry.addressDistribution} -
                </#if>
                ${entry.addressZipCode} ${entry.getCity(locale)}
            </div>

            <#if entry.phone?has_content>
                <div class="place-phone">
                    <@liferay_ui.message key="phone" /> ${entry.phone}
                </div>
            </#if>

            <#if entry.mail?has_content || (entry.getFacebookLabel(locale)?has_content && entry.getFacebookURL(locale)?has_content) || (entry.getSiteLabel(locale)?has_content && entry.getSiteURL(locale)?has_content)>
                <div class="place-links">
                    <#if entry.mail?has_content>
                        <a href="#contact-form-section"><@liferay_ui.message key="contact" /></a> 
                    </#if>
                    <#if entry.getFacebookLabel(locale)?has_content && entry.getFacebookURL(locale)?has_content >
                        <a href="${entry.getFacebookURL(locale)}" title="${entry.getFacebookLabel(locale)} (<@liferay_ui.message key="eu.new-window" />)" target="_blank">
                            ${entry.getFacebookLabel(locale)}
                        </a> 
                    </#if>
                    <#if entry.getSiteLabel(locale)?has_content && entry.getSiteURL(locale)?has_content>
                        <a href="${entry.getSiteURL(locale)}" title="${entry.getSiteLabel(locale)} (<@liferay_ui.message key="eu.new-window" />)" target="_blank">
                            ${entry.getSiteLabel(locale)}
                        </a>  
                    </#if>
                </div>
            </#if>
        </div>

        <div class="place-info">
            <div class="place-60">
                <div class="google-map" data-zoom="13">
                    <div class="marker" data-lat="48.585259" data-lng="7.764706" data-icon="img/design/gmap-markers.png">
                    </div>
                </div>

                <#if entry.getAccess(locale)?has_content >
                    <div class="place-info-section">
                        <h4>
                            <@liferay_ui.message key="access" />
                        </h4>
                        ${entry.getAccess(locale)}
                    </div>
                </#if>

                <#if entry.getCharacteristics(locale)?has_content >
                    <div class="place-info-section">
                        <h4>
                            <@liferay_ui.message key="eu.confort-and-equipment" />
                        </h4>
                        ${entry.getCharacteristics(locale)}
                    </div>
                </#if>

                <#if entry.getServiceAndActivities(locale)?has_content >
                    <div class="place-info-section">
                        <h4>
                            <@liferay_ui.message key="eu.services-and-activities" />
                        </h4>
                        ${entry.getServiceAndActivities(locale)}
                    </div>
                </#if>

                <#if entry.hasAnyAccessForDisabled() || entry.getAccessForDisabled(locale)?has_content >
                    <div class="place-info-section">
                        <h4>
                            <@liferay_ui.message key="eu.access-for-disabled" />
                        </h4>
                      
                        <#if entry.hasAnyAccessForDisabled() >
                            <div class="access-for-disabled-icons">
                                <#if entry.accessForWheelchair>
                                    <img src="/o/placeweb/images/access-for-wheelchair.png"
                                        title="<@liferay_ui.message key='eu.access-for-wheelchair' />"
                                        alt="<@liferay_ui.message key='access-for-wheelchair' />">
                                </#if>
                                <#if entry.accessForBlind>
                                    <img src="/o/placeweb/images/access-for-blind.png"
                                        title="<@liferay_ui.message key='eu.access-for-blind' />"
                                        alt="<@liferay_ui.message key='access-for-blind' />">
                                </#if>
                                <#if entry.accessForDeaf>
                                    <img src="/o/placeweb/images/access-for-deaf.png"
                                        title="<@liferay_ui.message key='eu.access-for-deaf' />"
                                        alt="<@liferay_ui.message key='access-for-deaf' />">
                                </#if>
                                <#if entry.accessForElder>
                                    <img src="/o/placeweb/images/access-for-elder.png"
                                        title="<@liferay_ui.message key='eu.access-for-elder' />"
                                        alt="<@liferay_ui.message key='access-for-elder' />">
                                </#if>
                                <#if entry.accessForDeficient>
                                    <img src="/o/placeweb/images/access-for-deficient.png"
                                        title="<@liferay_ui.message key='eu.access-for-deficient' />"
                                        alt="<@liferay_ui.message key='access-for-deficient' />">
                                </#if>
                            </div>
                        </#if>
                        ${entry.getAccessForDisabled(locale)}
                    </div>
                </#if>

                <!-- Widget Bloc Contenus associés -->
                <#assign contenus = entry.getRandomContents() />
                <#if contenus?has_content>
                    <div class="items-carousel places-carousel">
                        <h4 class="items-carousel-title">
                            <@liferay_ui.message key="associated-content" />   
                        </h4>
                        <div class="owl-carousel">
                            <#list contenus as contenu>
                                <div class="item"> 
                                    <div class="item-image">
                                        <#assign class = contenu.getClassNameId() />  
                                        <#if class == 20015>  
                                            <#assign image = contenu.getAssetRenderer().getAssetObject() />
                                            <#assign imageURL = entry.getImageURL(image.getFileEntryId()) />
                                            <#assign imageTitle = entry.getImageCopyright(image.getFileEntryId(), locale) />
                                            <a href="${imageURL}" target="_blank">
                                                <img src="${imageURL}" >
                                            </a>
                                            <div class="item-title">
                                                <h4><a href="${imageURL}" target="_blank">${imageTitle}</a></h4>
                                            </div>
                                        <#else>  
                                            <#assign video = contenu.getAssetRenderer().getAssetObject() /> 
                                            <a href="/web${layout.group.friendlyURL}/detail-video/-/entity/id/${video.videoId}" target="_blank">
                                                <img src="${video.imageURL}" >
                                            </a>
                                            <div class="item-title">
                                                <h4><a href="/web${layout.group.friendlyURL}/detail-video/-/entity/id/${video.videoId}" target="_blank">${video.getTitle(locale)}</a></h4>
                                            </div>
                                        </#if>  
                                    </div>
                                </div>   
                            </#list>
                        </div>
                    </div>
                </#if>

                <!-- Widget Bloc Agenda -->
                <#if entry.displayEvents>
                    <#assign events = entry.getEvents() />
                    <#if events?has_content>
                        <div class="agenda-collections-carousel">
                            <h4 class="agenda-carousel-title">
                                <@liferay_ui.message key="eu.agenda-and-exposition" />
                            </h4>
                            <div class="owl-carousel">
                                <#list events as event>
                                    <div class="item"> 
                                        <div class="item-image">
                                            <a href="/web${layout.group.friendlyURL}/evenement-des-musees-de-strasbourg/-/entity/id/${event.eventId}" target="_blank">
                                                <img src="${event.externalImageURL}" >
                                            </a>
                                        </div>
                                        <div class="item-info">
                                            <div class="item-date">
                                                <date><@liferay_ui.message key="from" /> ${event.firstStartDate?date} <@liferay_ui.message key="to" /> ${event.lastEndDate?date}</date>
                                            </div>
                                            <div class="item-title">
                                                <h4><a href="/web${layout.group.friendlyURL}/evenement-des-musees-de-strasbourg/-/entity/id/${event.eventId}">${event.getTitle(locale)}</a></h4>
                                                <h5><a href="/web${layout.group.friendlyURL}/evenement-des-musees-de-strasbourg/-/entity/id/${event.eventId}">${event.getSubtitle(locale)}</a></h5>
                                            </div>
                                            <div class="item-content">
                                                ${event.getDescription(locale)}
                                            </div>
                                        </div>
                                    </div>
                                </#list>
                            </div>
                        </div>
                    </#if>
                </#if>


                <#if entry.mail?has_content>
                    <div class="place-info-section" id="contact-form-section">
                        <@liferay_portlet.actionURL var="contactURL" name="contact">
                            <@liferay_portlet.param name="classPK" value="${entry.getPlaceId()}" />
                            <@liferay_portlet.param name="to" value="${entry.mail}" />
                            <@liferay_portlet.param name="subject" value="Formulaire de contact - Lieu - ${entry.getAlias(locale)}" />
                        </@liferay_portlet.actionURL>
                        <h4>
                            <@liferay_ui.message key="contact" />
                        </h4>
                        <@liferay_ui.success key="mail-success" message="eu.mail-success" />
                        <@liferay_ui.error key="all-fields-required" message="eu.all-fields-required" />
                        <@liferay_ui.error key="invalid-mail" message="eu.invalid-mail" />
                        <@liferay_ui.error key="recaptcha-error" message="eu.recaptcha-error" />
                        <p class="error-message all-fields-required-message" style="display:none;">
                            <@liferay_ui.message key="eu.all-fields-required" />
                        </p>
                        <p class="error-message invalid-mail-message" style="display:none;">
                            <@liferay_ui.message key="eu.invalid-mail" />
                        </p>
                        <p class="error-message recaptcha-error-message" style="display:none;">
                            <@liferay_ui.message key="eu.recaptcha-error" />
                        </p>

                        <#if renderRequest.getAttribute("mailSent")?has_content && renderRequest.getAttribute("mailSent")>
                            <p class="mail-success">
                                <@liferay_ui.message key="eu.mail-success" />
                            </p>
                        </#if>
                        <form action="${contactURL}" name="contactForm" method="post" class="contact-form">
                            <div>
                                <label for="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_firstName">
                                    <@liferay_ui.message key="firstname" /> *
                                </label>
                                <input type="text" class="first-name"
                                name="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_firstName"
                                value="${renderRequest.getAttribute("firstName")!""}">  
                            </div>
                            <div>
                                <label for="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_lastName">
                                    <@liferay_ui.message key="lastname" /> *
                                </label>
                                <input type="text" class="last-name"
                                name="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_lastName"
                                value="${renderRequest.getAttribute("lastName")!""}">  
                            </div>
                            <div>
                                <label for="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_email">
                                    <@liferay_ui.message key="email" /> *
                                </label>
                                <input type="text" class="email"
                                name="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_email"
                                value="${renderRequest.getAttribute("email")!""}">  
                            </div>
                            <div>
                                <label for="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_message">
                                    <@liferay_ui.message key="message" /> *
                                </label>
                                <textarea name="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_message" class="message">${renderRequest.getAttribute("message")!""}</textarea>  
                            </div>
                            <div class="g-recaptcha" data-sitekey="${propsUtil.get('eu.strasbourg.recaptcha.public')}"></div>
                            <div>
                                <label for="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_notificationEmail">
                                    <input type="checkbox" class="notification-email" 
                                    name="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_notificationEmail" checked>
                                    &nbsp;<@liferay_ui.message key="eu.do-you-want-a-notification" />
                                </label>
                            </div>
                            <p>
                                <input type="submit" value="<@liferay_ui.message key="send" />">
                            </p>
                            <p>
                                * :  <@liferay_ui.message key="eu.required-field" />
                            </p>
                            <p class="privacy-policy">
                                <label><@liferay_ui.message key="eu.privacy-policy" /></label>
                                <#assign VOID = freeMarkerPortletPreferences.setValue("portletSetupPortletDecoratorId", "barebone") />
                                <@liferay_portlet["runtime"]
                                defaultPreferences="${freeMarkerPortletPreferences}"
                                portletProviderAction=portletProviderAction.VIEW
                                portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
                                settingsScope="group"
                                instanceId="entityDetail" />
                                ${freeMarkerPortletPreferences.reset()}
                            </p>
                        </form>
                    </div>
                </#if>
            </div>
            <div class="place-40">
                <div class="image-with-copyright-on-hover">
                    <img src="${entry.getImageURL()}" class="lightbox">
                    <#if entry.getImageCopyright(locale)?has_content>
                        <div class="image-copyright">
                            ${entry.getImageCopyright(locale)}
                        </div>
                    </#if>
                </div>
                <div class="place-info-section">
                    <h4>
                        <@liferay_ui.message key="eu.times" />
                        <#assign types = entry.getTypes() />
                        <#if types?has_content>
                            <#list types as type>
                                ${type.getCategoryId()}
                                 <a href="tous-les-horaires/-/entity/id/${type.getCategoryId()}" target="_blank"><@liferay_ui.message key="eu.all-times" /></a>
                            </#list>
                        </#if>
                    </h4>
                    <div class="place-schedule">
                        <ul>
                            <#assign horaires = entry.getHoraire(.now) />
                            <#list horaires?keys as jour>
                                <li class="schedule">
                                    <div class="schedule-day">
                                        <@liferay_ui.message key="jour-semaine${jour}" />
                                    </div>
                                    <div class="schedule-time">
                                        <#assign liste = horaires[jour] />
                                        <#list liste as placeSchedule >
                                            <#if placeSchedule.isException() || placeSchedule.isPublicHoliday() >
                                                <span style="color:#B22222;">                              
                                            </#if>
                                            <#if placeSchedule.isClosed() >
                                                <@liferay_ui.message key="closed" />
                                            <#else>
                                                <#if placeSchedule.isAlwaysOpen() >
                                                    <@liferay_ui.message key="always-open" />    
                                                <#else>
                                                    <#if placeSchedule?counter == 3 || placeSchedule?counter == 5 >
                                                        <br>
                                                    </#if> 
                                                    <@liferay_ui.message key="eu.from" /> ${placeSchedule.getStartTime().toString()} 
                                                    <@liferay_ui.message key="eu.to" /> ${placeSchedule.getEndTime().toString()}
                                                </#if> 
                                            </#if> 
                                            <#if placeSchedule.isException() || placeSchedule.isPublicHoliday() >
                                                *</span>                              
                                            </#if>
                                        </#list>
                                    </div>
                                </li>
                            </#list>
                        </ul>
                    </div>

                    <#if entry.getExceptionalSchedule(locale)?has_content >
                        <strong><@liferay_ui.message key="eu.exceptional-schedule" /></strong>
                        ${entry.getExceptionalSchedule(locale)}
                    </#if>
                </div>

                <#if entry.getPrice()?has_content >
                    <div class="place-info-section">
                        <h4>
                            <@liferay_ui.message key="eu.prices" />
                        </h4>
                        <p>
                            ${entry.getPrice().getPrice(locale)}
                        </p>
                    </div>
                </#if>
                <#if entry.getDocumentURLs()?has_content >
                    <div class="place-info-section">
                        <h4>
                            <@liferay_ui.message key="read-too" />
                        </h4>
                        <ul>
                            <#assign documents = entry.getDocuments() />
                            <#list documents?keys as title>
                                <li class="document"><a href="${documents[title]}" >${title}</a></li>
                            </#list>
                        </ul>

                    </div>
                </#if>
            </div>
        </div>

    </div>
<#else>
    <@liferay_ui.message key="select-place" />
</#if>