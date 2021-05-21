<!-- Détail lieu -->
<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
   <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
   <#assign homeURL = "/" />
</#if>
<#setting date_format="d MMMM yyyy">
<#assign EventLocalService = serviceLocator.findService("eu.strasbourg.service.agenda.service.EventLocalService")/>

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

            <#if entry.mail?has_content || (entry.getFacebookLabel(locale)?has_content && entry.getFacebookURL(locale)?has_content) || (entry.getInstagramLabel(locale)?has_content && entry.getInstagramURL(locale)?has_content)>
                <div class="place-links">
                    <#if entry.mail?has_content>
                        <a href="#contact-form-section"><@liferay_ui.message key="contact" /></a> 
                    </#if>
                    <#if entry.getFacebookLabel(locale)?has_content && entry.getFacebookURL(locale)?has_content >
                        <a href="${entry.getFacebookURL(locale)}" title="${entry.getFacebookLabel(locale)} (<@liferay_ui.message key="eu.new-window" />)" target="_blank">
                            ${entry.getFacebookLabel(locale)}
                        </a> 
                    </#if>
                    <#if entry.getInstagramLabel(locale)?has_content && entry.getInstagramURL(locale)?has_content >
                        <a href="${entry.getInstagramURL(locale)}" title="${entry.getInstagramLabel(locale)} (<@liferay_ui.message key="eu.new-window" />)" target="_blank">
                            ${entry.getInstagramLabel(locale)}
                        </a> 
                    </#if>
                </div>
            </#if>
        </div>

        <div class="place-info">
            <div class="place-60">
                <!-- <div class="google-map" data-zoom="13">
                    <div class="marker" data-lat="${entry.mercatorY}" data-lng="${entry.mercatorX}" data-icon="img/design/gmap-markers.png">
                    </div>
                </div> -->

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
                                            <img src="${imageURL}" >
                                            <div class="item-title">
                                                <h4>${imageTitle}</h4>
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
                <#assign placeEvents = EventLocalService.getCurrentAndFuturePublishedEventsFromPlace(entry.getSIGid()) />
                <#if entry.displayEvents>
                    <#assign events = placeEvents />
                    <#if events?has_content>
                        <div class="agenda-collections-carousel">
                            <h4 class="agenda-carousel-title">
                                <@liferay_ui.message key="eu.agenda-and-exposition" />
                            </h4>
                            <div class="owl-carousel">
                                <#list events as event>
                                    <div class="item"> 
                                        <div class="item-image">
                                            <a href="/web${layout.group.friendlyURL}/evenement-des-musees-de-strasbourg/-/entity/id/${event.eventId}">
                                                <img src="${event.imageURL}" >
                                            </a>
                                        </div>
                                        <div class="item-info">
                                            <div class="item-date">
                                                <date>${event.getEventScheduleDisplay(locale)}</date>
                                            </div>
                                            <div class="item-title">
                                                <h4><a href="/web${layout.group.friendlyURL}/evenement-des-musees-de-strasbourg/-/entity/id/${event.eventId}">${event.getTitle(locale)}</a></h4>
                                                <h5 style="margin-bottom: 25px"><a href="/web${layout.group.friendlyURL}/evenement-des-musees-de-strasbourg/-/entity/id/${event.eventId}">${event.getSubtitle(locale)}</a></h5>
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
                        </form>
                        <p class="privacy-policy">
                            <label><@liferay_ui.message key="eu.privacy-policy" /></label>
                            <@liferay_portlet["runtime"]
                            defaultPreferences="${freeMarkerPortletPreferences}"
                            portletProviderAction=portletProviderAction.VIEW
                            portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
                            settingsScope="group"
                            instanceId="entityDetail" />
                        </p>
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
                <#if entry.periods?has_content>
	                <div class="place-info-section">
	                    <h4>
	                        <@liferay_ui.message key="eu.times" />
	                        <#assign types = entry.getTypes() />
	                        <#if types?has_content>
	                        	<#assign categoriesIds = "" />
	                            <#list types as type>
	                                <#if type?counter == 1 >
	                        			<#assign categoriesIds = type.getCategoryId() />
	                                <#else> 
	                        			<#assign categoriesIds = categoriesIds + "," + type.getCategoryId() />
	                                </#if> 
	                            </#list>
	                            <a href="${homeURL}tous-les-horaires"><@liferay_ui.message key="eu.all-times" /></a>
	                        </#if>
	                    </h4>
	                    <#assign hasURL = 0 />
	                    <#assign periods = entry.periods />
	                    <#list periods as period>
	                        <#if period.linkURL?has_content && period.linkLabel?has_content >
	                            <div class="place-schedule">
	                                <a href="${period.getLinkURL(locale)}" target="_blank"> ${period.getLinkLabel(locale)}</a>
	                            </div>    
	                            <#assign hasURL = 1 />
	                            <#break />                      
	                        </#if>    
	                    </#list>  
	                    <#if hasURL == 0>  
	                    	<#assign idException = 0 /> 
	                    	<#assign scheduleExceptions = "" />   
	                        <div class="place-schedule">
	                            <ul>
	                                <#assign horaires = entry.getHoraire(.now, locale) />
	                                <#list horaires?keys as jour>
	                                    <li class="schedule">
	                                        <div class="schedule-day">
	                                            ${jour?capitalize}
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
                                                            <#list placeSchedule.openingTimes as openingTime>
                                                                <#if openingTime?counter gt 1 >
                                                                <br>
                                                                </#if> 
                                                                <@liferay_ui.message key="eu.from" /> ${openingTime.first} 
                                                                <@liferay_ui.message key="eu.to" /> ${openingTime.second}
                                                            </#list>
	                                                    </#if> 
	                                                </#if> 
	                                                <#if placeSchedule.isException() || placeSchedule.isPublicHoliday() >
	                                                    *</span>                              
	                                                </#if>
													<!-- stock les descriptions pour les ouvertures et fermetures exceptionnelle  -->
	                                                <#if placeSchedule.isException() || placeSchedule.isPublicHoliday() >
		                                                <#if idException != placeSchedule.idSchedule >
	                    									<#assign idException = placeSchedule.idSchedule />  
	                    									<#assign scheduleExceptions >
	                    										${scheduleExceptions}
										                        <p>
										                        	<strong>
											                            ${placeSchedule.period}
										                            </strong>
										                            <#if placeSchedule.isClosed() >
										                                <@liferay_ui.message key="closed" />
										                            <#else> 
                                                                        <#list placeSchedule.openingTimes as openingTime>
                                                                            ${openingTime.first} - ${openingTime.second}
                                                                        </#list>
										                            </#if> 
											                    	 - ${placeSchedule.getDescription()}
										                        </p>
	                    									</#assign>          
		                                                </#if>                            
	                                                </#if>
	                                            </#list>
	                                        </div>
	                                    </li>
	                                </#list>
	                            </ul>
	                        </div>                        
	                    </#if>

	                    <#if scheduleExceptions?has_content >  
	                        <strong  style="color:#B22222;">
	                        	*<@liferay_ui.message key="eu.exceptional-closings-openings" />
	                        </strong>
	                        ${scheduleExceptions}
	                    </#if>

	                    <#if entry.getExceptionalSchedule(locale)?has_content >
	                        <strong><@liferay_ui.message key="eu.exceptional-schedule" /></strong>
	                        <p>
	                        	${entry.getExceptionalSchedule(locale)}
	                        </p>
	                    </#if>
	                </div>
                </#if>

                <#if entry.getPrice()?has_content >
                    <div class="place-info-section">
                        <h4>
                            <@liferay_ui.message key="eu.priceDescription" />
                        </h4>
                        <p>
                            ${entry.getPrice().getPriceDescription(locale)}
                        </p>
                    </div>
                </#if>
                <#if entry.getDocumentURLs()?has_content >
                    <div class="place-info-section">
                        <h4>
                            <@liferay_ui.message key="eu.useful-documents" />
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