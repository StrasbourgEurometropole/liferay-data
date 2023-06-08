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
    
                    <!-- Widget Bloc Agenda -->
                    <#assign placeEvents = EventLocalService.getCurrentAndFuturePublishedEventsFromPlace(entry.getSIGid()) />
                    <#if entry.displayEvents>
                        <#assign events = placeEvents />
                        <#if events?has_content>
                            <div id="place-events" class="place-info-section">
                                <h2>
                                    <@liferay_ui.message key="eu.agenda-and-exposition" />
                                </h2>
                                
                                <div class="slider">
                                    <div class="swiper">
                                        <div class="swiper-wrapper">
                                    	    <#list events as event>
                                                <#assign detailURL = homeURL + "evenement/-/entity/id/" + event.eventId + "/" + event.getNormalizedTitle(locale) />
                                                <div class="swiper-slide">
                                                    <a href="${detailURL}" aria-label="${event.getTitle(locale)?html}" title="${event.getTitle(locale)?html}" class="event-thumbnail" style="background-image: url(${event.getImageURL()})">
                                                        <#if event.getActivityTypeLabel(locale)?has_content>
                                                            <div class="visit">
                                                                <span>${event.getActivityTypeLabel(locale)}</span>
                                                            </div>
                                                        </#if>
                                                        <div class="info">
                                                            <div class="title">
                                                                <span>${event.getTitle(locale)}</span>
                                                            </div>
                                                            <div class="dates">
                                                                <span>${event.getEventScheduleDisplay(locale)}</span>
                                                            </div>
                                                            <div class="museums">
                                                                <span>${event.getMuseumsLabel(locale)}</span>
                                                            </div>
                                                        </div>
                                                    </a>
                                                </div>
                                        	</#list>
                                        </div>
                                        
                                        <div class="swipper-buttons">
                                            <div class="swiper-button-prev"></div>
                                            <div class="swiper-button-next"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </#if>
                    </#if>
                </div>
                <div class="place-40">
    
                    <#if entry.getImageURL()?has_content>
                        <div class="image-with-copyright-on-hover">
                            <img src="${entry.getImageURL()}" alt="${entry.getAlias(locale)?html}" title="${entry.getAlias(locale)?html}">
                            <#if entry.getImageCopyright(locale)?has_content>
                                <div class="copyright"><span>C</span><span>${entry.getImageCopyright(locale)}</span></div>
                            </#if>
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
    	                                                    *                              
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
                        </div>
    
    	                <div class="place-info-section2">
    	                    <#if scheduleExceptions?has_content >  
    	                        <strong  style="color:#B22222;">
    	                        	*<@liferay_ui.message key="eu.exceptional-closings-openings" />
    	                        </strong>
    	                        ${scheduleExceptions}
    	                    </#if>
    	                    
    	                    <#if entry.getExceptionalSchedule(locale)?has_content >
    	                        <h2><@liferay_ui.message key="eu.exceptional-schedule" /></h2>
    	                        <div>
    	                        	${entry.getExceptionalSchedule(locale)}
    	                        </div>
    	                    </#if>
    	                </div>
                    </#if>
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
                        <a href="${entry.getFacebookURL(locale)}" target="_blank" aria-label="${entry.getFacebookLabel(locale)?html} (<@liferay_ui.message key="eu.new-window" />)" title="${entry.getFacebookLabel(locale)?html} (<@liferay_ui.message key="eu.new-window" />)" class="facebook" ></a>
                    </#if>
                    <#if entry.getInstagramURL(locale)?has_content>
                        <a href="${entry.getInstagramURL(locale)}" target="_blank" aria-label="${entry.getInstagramLabel(locale)?html} (<@liferay_ui.message key="eu.new-window" />)" title="${entry.getInstagramLabel(locale)?html} (<@liferay_ui.message key="eu.new-window" />)" class="instagram" ></a>
                    </#if>
                </div>
            </#if>
        </#if>
    </div>
</section>