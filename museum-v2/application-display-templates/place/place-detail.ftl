<!-- Détail lieu -->
<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
   <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
   <#assign homeURL = "/" />
</#if>
<#setting date_format="d MMMM yyyy">
<#assign EventLocalService = serviceLocator.findService("eu.strasbourg.service.agenda.service.EventLocalService")/>

<section id="place-detail" class="margin-bottom">
    <div  class="content container">
        <#if entry?has_content>
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
    
                <!--
                <#if entry.mail?has_content>
                    <div class="place-links">
                        <#if entry.mail?has_content>
                            <a href="#contact-form-section"><@liferay_ui.message key="contact" /></a> 
                        </#if>
                    </div>
                </#if>
                -->
            </div>
    
            <div class="place-info">
                <div class="place-60">
       
                    <#if entry.getPrice()?has_content >
                        <div class="place-info-section">
                            <h2>
                                <@liferay_ui.message key="eu.priceDescription" />
                            </h2>
                            <div>
                                ${entry.getPrice().getPriceDescription(locale)}
                            </div>
                        </div>
                    </#if>

                    <#if entry.getAccess(locale)?has_content >
                        <div class="place-info-section">
                            <h2>
                                <@liferay_ui.message key="access" />
                            </h2>
                            <div>
                                ${entry.getAccess(locale)}
                            </div>
                        </div>
                    </#if>
    
                    <!--
                    <#if entry.getCharacteristics(locale)?has_content >
                        <div class="place-info-section">
                            <h2>
                                <@liferay_ui.message key="eu.confort-and-equipment" />
                            </h2>
                            ${entry.getCharacteristics(locale)}
                        </div>
                    </#if>
                    -->
    
                    <#if entry.getServiceAndActivities(locale)?has_content >
                        <div class="place-info-section">
                            <h2>
                                <@liferay_ui.message key="eu.services-and-activities" />
                            </h2>
                            <div>
                                ${entry.getServiceAndActivities(locale)}
                            </div>
                        </div>
                    </#if>
    
                    <#if entry.hasAnyAccessForDisabled() || entry.getAccessForDisabled(locale)?has_content >
                        <div class="place-info-section">
                            <h2>
                                <@liferay_ui.message key="eu.access-for-disabled" />
                            </h2>
                          
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
                    <!--
                    <#assign contenus = entry.getRandomContents() />
                    <#if contenus?has_content>
                        <div class="items-carousel places-carousel">
                            <h2 class="items-carousel-title">
                                <@liferay_ui.message key="associated-content" />   
                            </h2>
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
                                                    <h3>${imageTitle}</h3>
                                                </div>
                                            <#else>  
                                                <#assign video = contenu.getAssetRenderer().getAssetObject() /> 
                                                <a href="/web${layout.group.friendlyURL}/detail-video/-/entity/id/${video.videoId}" target="_blank">
                                                    <img src="${video.imageURL}" >
                                                </a>
                                                <div class="item-title">
                                                    <h3><a href="/web${layout.group.friendlyURL}/detail-video/-/entity/id/${video.videoId}" target="_blank">${video.getTitle(locale)}</a></h3>
                                                </div>
                                            </#if>  
                                        </div>
                                    </div>   
                                </#list>
                            </div>
                        </div>
                    </#if>
                    -->
    
                    <!-- Widget Bloc Agenda -->
                    <!--
                    <#assign placeEvents = EventLocalService.getCurrentAndFuturePublishedEventsFromPlace(entry.getSIGid()) />
                    <#if entry.displayEvents>
                        <#assign events = placeEvents />
                        <#if events?has_content>
                            <div class="agenda-collections-carousel">
                                <h2 class="agenda-carousel-title">
                                    <@liferay_ui.message key="eu.agenda-and-exposition" />
                                </h2>
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
                                                    <h3><a href="/web${layout.group.friendlyURL}/evenement-des-musees-de-strasbourg/-/entity/id/${event.eventId}">${event.getTitle(locale)}</a></h3>
                                                    <h4 style="margin-bottom: 25px"><a href="/web${layout.group.friendlyURL}/evenement-des-musees-de-strasbourg/-/entity/id/${event.eventId}">${event.getSubtitle(locale)}</a></h4>
                                                </div>
                                            </div>
                                        </div>
                                    </#list>
                                </div>
                            </div>
                        </#if>
                    </#if>
                    -->
    
                    <!--
                    <#if entry.mail?has_content>
                        <div class="place-info-section" id="contact-form-section">
                            <@liferay_portlet.actionURL var="contactURL" name="contact">
                                <@liferay_portlet.param name="classPK" value="${entry.getPlaceId()}" />
                                <@liferay_portlet.param name="to" value="${entry.mail}" />
                                <@liferay_portlet.param name="subject" value="Formulaire de contact - Lieu - ${entry.getAlias(locale)}" />
                            </@liferay_portlet.actionURL>
                            <h2>
                                <@liferay_ui.message key="contact" />
                            </h2>
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
                    -->
                </div>
                <div class="place-40">
    
                    <#if entry.getImageURL()?has_content>
                        <div class="image-with-copyright-on-hover">
                            <img src="${entry.getImageURL()}" alt="${entry.getAlias(locale)}" title="${entry.getAlias(locale)}">
                            <!--
                            <#if entry.getImageCopyright(locale)?has_content>
                                <div class="image-copyright">
                                    ${entry.getImageCopyright(locale)}
                                </div>
                            </#if>
                            -->
                        </div>
                    </#if>

                    <#if entry.periods?has_content>
    	                <div class="place-info-section">
    	                    <h2>
    	                        <@liferay_ui.message key="eu.times" />
    	                    </h2>
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
                            <#assign types = entry.getTypes() />
                            <#if types?has_content>
                                <a href="${homeURL}tous-les-horaires" class="button2" aria-label="<@liferay_ui.message key="eu.all-times" />" title="<@liferay_ui.message key="eu.all-times" />"><@liferay_ui.message key="eu.all-times" /></a>
                            </#if>
    
                            <!--
    	                    <#if scheduleExceptions?has_content >  
    	                        <strong  style="color:#B22222;">
    	                        	*<@liferay_ui.message key="eu.exceptional-closings-openings" />
    	                        </strong>
    	                        ${scheduleExceptions}
    	                    </#if>
                            -->
                        </div>
    
    	                <div class="place-info-section2">
    	                    <#if entry.getExceptionalSchedule(locale)?has_content >
    	                        <h2><@liferay_ui.message key="eu.exceptional-schedule" /></h2>
    	                        <div>
    	                        	${entry.getExceptionalSchedule(locale)}
    	                        </div>
    	                    </#if>
    	                </div>
                    </#if>

                    <!--
                    <#if entry.getDocumentURLs()?has_content >
                        <div class="place-info-section">
                            <h2>
                                <@liferay_ui.message key="eu.useful-documents" />
                            </h2>
                            <ul>
                                <#assign documents = entry.getDocuments() />
                                <#list documents?keys as title>
                                    <li class="document"><a href="${documents[title]}" >${title}</a></li>
                                </#list>
                            </ul>
    
                        </div>
                    </#if>
                    -->
                </div>
            </div>

        <#else>
            <@liferay_ui.message key="select-place" />
        </#if>
    </div>
</section>
<section id="social-wall" class="margin-bottom">
    <div  class="content container">
        <#if entry?has_content>
            <#if entry.getFacebookURL(locale)?has_content || entry.getInstagramURL(locale)?has_content>
                <h2><@liferay_ui.message key='eu.museum.social-wall' /></h2>
                <div class="list">
                    <#if entry.getFacebookURL(locale)?has_content>
                        <a href="${entry.getFacebookURL(locale)}" aria-label="${entry.getFacebookLabel(locale)}" title="${entry.getFacebookLabel(locale)}" class="facebook" />
                    </#if>
                    <#if entry.getInstagramURL(locale)?has_content>
                        <a href="${entry.getInstagramURL(locale)}" aria-label="${entry.getInstagramLabel(locale)}" title="${entry.getInstagramLabel(locale)}" class="instagram" />
                    </#if>
                </div>
            </#if>
        </#if>
    </div>
</section>