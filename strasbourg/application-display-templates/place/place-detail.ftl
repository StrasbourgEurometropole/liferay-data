<!-- Détail lieu -->

<#setting locale = locale />

<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<#assign fileEntryHelper = serviceLocator.findService("eu.strasbourg.utils.api.FileEntryHelperService") />

<div class="seu-page-lieu">
    <main class="seu-container">
        <h1>${entry.getAlias(locale)}</h1>
        
        <div class="seu-flexbox">

            <div class="seu-container-left">

                <!-- Horaires -->
                <#if entry.periods?has_content>
                    <div class="seu-wi--collapsing <#if !renderRequest.getAttribute("fromContactForm")?has_content>seu-first-opened</#if>">
                        <button class="seu-toggle-collapse">
                            <h2 class="schedule"><span><@liferay_ui.message key="eu.times" /></span></h2>
                        </button>
                        <div class="seu-collapsing-box">
                            <div class="seu-wi seu-wi-schedules">
                                <div class="tab-list">
                                </div>
                                <div class="tab-content">
                                    <h3 class="hidden"><@liferay_ui.message key="eu.place.next-days" /></h3>
                                    <div class="tab-title">${entry.getAlias(locale)}</div>
                                    <ul class="schedule-list">
                                        <#assign daySchedulesMap = entry.getFollowingWeekSchedules(.now, locale) />
                                        <#assign hasException = false />
                                        <#list daySchedulesMap?keys as day>
                                            <li>
                                                <span>${day}</span>
                                                <span>
                                                    <#list daySchedulesMap[day] as schedule>
                                                        <#if schedule.isException()>
                                                            <#assign hasException = true />
                                                            <span class="exception">
                                                        </#if>
                                                        <#if schedule.isClosed()>
                                                            <@liferay_ui.message key="eu.closed" />
                                                        <#elseif schedule.isAlwaysOpen()>
                                                            <@liferay_ui.message key="always-open" />
                                                        <#else>
                                                            ${schedule.startTime?string} - ${schedule.endTime}
                                                        </#if>
                                                        <#if schedule.isException()>
                                                            </span>
                                                        </#if>
                                                        <#sep><br></#sep>
                                                    </#list>
                                                </span>
                                            </li>
                                        </#list>
                                    </ul>
                                    <#list entry.publishedSubPlaces as subPlace>
                                        <div class="tab-title">${subPlace.getName(locale)}</div>
                                        <ul class="schedule-list">
                                            <#assign daySchedulesMap = subPlace.getFollowingWeekSchedules(.now, locale) />
                                            <#list daySchedulesMap?keys as day>
                                                <li>
                                                    <span>${day}</span>
                                                    <span>
                                                        <#list daySchedulesMap[day] as schedule>
                                                            <#if schedule.isException()>
                                                                <#assign hasException = true />
                                                                <span class="exception">
                                                            </#if>
                                                           <#if schedule.isClosed()>
                                                                <@liferay_ui.message key="eu.closed" />
                                                            <#elseif schedule.isAlwaysOpen()>
                                                                <@liferay_ui.message key="always-open" />
                                                            <#else>${schedule.startTime?string} - ${schedule.endTime}</#if><#sep><br></#sep>
                                                            <#if schedule.isException()>
                                                                </span>
                                                            </#if>
                                                        </#list>
                                                    </span>
                                                </li>
                                            </#list>
                                        </ul>
                                    </#list>
                                    <#if hasException>
                                        <span style="color: #F44336; font-weight: bold; font-size: 1.6rem;"><@liferay_ui.message key="eu.place.look-at-exceptionnal-schedule" /></span>
                                    </#if>
                                </div>
                                <#if entry.defaultPeriod?has_content>
                                    <div class="tab-content">
                                        <h3 class="hidden"><@liferay_ui.message key="eu.place.normal-schedule" /></h3>
                                        <div class="tab-title">${entry.getAlias(locale)}</div>
                                        <ul class="schedule-list">
                                            <#assign weekSchedules = entry.defaultPeriod.getWeekSchedule() />
                                            <#assign day = 0 />
                                            <#list weekSchedules as daySchedules>
                                                <li>
                                                    <span><@liferay_ui.message key="jour-semaine${day}" /></span>
                                                    <span>
                                                        <#list daySchedules as schedule>
                                                           <#if schedule.isClosed()>
                                                                <@liferay_ui.message key="eu.closed" />
                                                            <#elseif schedule.isAlwaysOpen()>
                                                                <@liferay_ui.message key="always-open" />
                                                            <#else>${schedule.startTime?string} - ${schedule.endTime}</#if><#sep><br></#sep>
                                                        </#list>
                                                    </span>
                                                </li>
                                                <#assign day = day + 1 />
                                            </#list>
                                        </ul>
                                    </div>
                                </#if>
                                <#list entry.nonDefaultPeriods as period>
                                    <div class="tab-content">
                                        <h3 class="hidden">${period.getName(locale)} <div>${period.getDisplay(locale)}</div></h3>
                                        <div class="tab-title">${entry.getAlias(locale)}</div>
                                        <ul class="schedule-list">
                                            <#assign weekSchedules = period.getWeekSchedule() />
                                            <#assign day = 0 />
                                            <#list weekSchedules as daySchedules>
                                                <li>
                                                    <span><@liferay_ui.message key="jour-semaine${day}" /></span>
                                                    <span>
                                                        <#list daySchedules as schedule>
                                                           <#if schedule.isClosed()>
                                                                <@liferay_ui.message key="eu.closed" />
                                                            <#elseif schedule.isAlwaysOpen()>
                                                                <@liferay_ui.message key="always-open" />
                                                            <#else>${schedule.startTime?string} - ${schedule.endTime}</#if><#sep><br></#sep>
                                                        </#list>
                                                    </span>
                                                </li>
                                                <#assign day = day + 1 />
                                            </#list>
                                        </ul>
                                    </div>
                                </#list>
                            </div>
                            <div class="rte">
                                <!-- TODO : lien vers le tableau
                                <p><a href="#" class="seu-btn-square--filled--second"><span class="seu-btn-text">Voir tous les horaires de la piscine</span></a></p>
                            -->

                                <#assign exceptions = entry.getPlaceScheduleExceptionFreeMarker(.now, true, locale) />
                                <#if exceptions?has_content>
                                    <#assign totalExceptionsCount = 0 />
                                    <h3><@liferay_ui.message key="eu.exceptional-closings-openings" /></h3>
                                    <ul class="seu-dates-list">
                                        <#list exceptions as exception>
                                            <#assign totalExceptionsCount++ />
                                            <li>
                                                <strong>${exception.getPeriodDisplay(locale)}</strong> : 
                                                <#if exception.isClosed()>
                                                    <@liferay_ui.message key="eu.closed" />
                                                <#else>
                                                    ${exception.startTime} - ${exception.endTime}
                                                </#if>
                                                - ${exception.description}
                                            </li>
                                        </#list>
                                        <#list entry.publishedSubPlaces as subPlace>
                                            <#assign exceptions = subPlace.getSubPlaceScheduleExceptionFreeMarker(.now, true, locale) />
                                            <#if exceptions?has_content>
                                                <#list exceptions as exception>
                                                    <#assign totalExceptionsCount++ />
                                                    <li>
                                                        <strong>${subPlace.getName(locale)} - ${exception.getPeriodDisplay(locale)}</strong> : 
                                                        <#if exception.isClosed()>
                                                            <@liferay_ui.message key="eu.closed" />
                                                        <#else>
                                                            ${exception.startTime} - ${exception.endTime}
                                                        </#if>
                                                        - ${exception.description}
                                                    </li>
                                                </#list>
                                            </#if>
                                        </#list>
                                    </ul>

                                    <#if (totalExceptionsCount > 5)>
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
                                </#if>
                                
                                <#if entry.getExceptionalSchedule(locale)?has_content>
                                    <h3><@liferay_ui.message key="eu.exceptional-schedule" /></h3>
                                    ${entry.getExceptionalSchedule(locale)}
                                </#if>
                            </div>
                        </div>
                    </div>
                </#if>
        

                <!-- Présentation -->
                <#if entry.getPresentation(locale)?has_content>
                    <div class="seu-wi--collapsing <#if !entry.periods?has_content && !renderRequest.getAttribute("fromContactForm")?has_content>seu-first-opened</#if>">
                        <button class="seu-toggle-collapse">
                            <h2 class="description"><span><@liferay_ui.message key="eu.presentation" /></span></h2>
                        </button>
                        <div class="seu-collapsing-box">
                            <div class="rte">
                                ${entry.getPresentation(locale)}
                            </div>
                        </div>
                    </div>
                </#if>

                <!-- Informations complémentaires -->
                <#if entry.getAdditionalInformation(locale)?has_content>
                    <div class="seu-wi--collapsing">
                        <button class="seu-toggle-collapse">
                            <h2 class="more"><span><@liferay_ui.message key="eu.place.additional-information" /></span></h2>
                        </button>
                        <div class="seu-collapsing-box">
                            <div class="rte">
                                ${entry.getAdditionalInformation(locale)}
                            </div>
                        </div>
                    </div>
                </#if>

                <!-- Médias -->
                <#if entry.documentURLs?has_content || entry.videos?has_content>
                    <div class="seu-wi--collapsing">
                        <button class="seu-toggle-collapse">
                            <h2 class="media"><span><@liferay_ui.message key="eu.place.medias" /></span></h2>
                        </button>
                        <div class="seu-collapsing-box">
                            <#list entry.documentURLs as fileURL>
                                <#assign file = fileEntryHelper.getFileEntryByRelativeURL(fileURL) />
                                <#assign title = fileEntryHelper.getFileTitle(file.getFileEntryId(), locale) />
                                <#assign size = fileEntryHelper.getReadableFileEntrySize(file.getFileEntryId(), locale) />
                                <div class="seu-wi seu-media seu-wi-download">  
                                    <div class="seu-media-container">  
                                        <div class="seu-media-left"><div class="seu-media-picto"></div></div>  
                                        <div class="seu-media-right">  
                                            <div class="seu-media-text">  
                                                <div class="seu-media-title">${title}</div>  
                                                <p>${file.getExtension()?upper_case} - ${size}</p>  
                                            </div>  
                                            <a href="{url}" target="_blank" class="seu-media-download seu-btn-square seu-filled seu-second" title="${title} (<@liferay_ui.message key="eu.new-window" />)">  
                                                <div class="seu-btn-text-editable">
                                                    <span class="seu-flexbox">  
                                                        <span class="seu-btn-text"><@liferay_ui.message key="download" /></span>  
                                                        <span class="seu-btn-arrow">&nbsp;</span>  
                                                    </span>
                                                </div>  
                                            </a>  
                                        </div>  
                                    </div>  
                                </div>  
                            </#list>
                            <#list entry.videos as video>
                                <div class="seu-wi seu-media seu-wi-embed">
                                    <div class="seu-media-container"> 
                                        <div class="seu-media-left">
                                            <div class="seu-media-picto"></div>
                                        </div>  
                                        <div class="seu-media-right"> 
                                            <div class="seu-media-text"> 
                                                <div class="seu-media-title">${video.getTitle(locale)}</div> 
                                                <p class="seu-media-description">${video.getDescription(locale)}</p> 
                                            </div> 
                                        </div> 
                                        <div class="seu-media-bottom"> 
                                            <div class="seu-media-ratio"> 
                                                ${video.getPlayer(locale)}
                                            </div> 
                                        </div> 
                                    </div> 
                                </div>
                            </#list>
                        </div>
                    </div>
                </#if>

                <!-- Accès -->
                <#if entry.getAccess(locale)?has_content>
                    <div class="seu-wi--collapsing">
                        <button class="seu-toggle-collapse">
                            <h2 class="geoloc"><span><@liferay_ui.message key="access" /></span></h2>
                        </button>
                        <div class="seu-collapsing-box">
                            <div class="rte">
                                ${entry.getAccess(locale)}
                            </div>
                        </div>
                    </div>
                </#if>

                <!-- Accès pour handicapés -->
                <#if entry.hasAnyAccessForDisabled() || entry.getAccessForDisabled(locale)?has_content>
                    <div class="seu-wi--collapsing">
                        <button class="seu-toggle-collapse">
                            <h2 class="handicap"><span><@liferay_ui.message key="eu.access-for-disabled" /></span></h2>
                        </button>
                        <div class="seu-collapsing-box">
                            <#if entry.hasAnyAccessForDisabled()>
                                <div class="seu-svg-list">
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
                                        <div class="seu-picto seu-picto-mental" title="<@liferay_ui.message key='eu.access-for-deficient' />"></div>
                                    </#if>
                                    
                                    <#if entry.accessForElder>
                                        <div class="seu-picto seu-picto-pmr" title="<@liferay_ui.message key='eu.access-for-elder' />"></div>
                                    </#if>
                                </div>
                            </#if>
                            <#if entry.getAccessForDisabled(locale)?has_content>
                                <div class="rte">
                                    ${entry.getAccessForDisabled(locale)}
                                </div>
                            </#if>
                        </div>
                    </div>
                </#if>

                <!-- Accès -->
                <#if entry.getServiceAndActivities(locale)?has_content>
                    <div class="seu-wi--collapsing">
                        <button class="seu-toggle-collapse">
                            <h2 class="services"><span><@liferay_ui.message key="eu.services-and-activities" /></span></h2>
                        </button>
                        <div class="seu-collapsing-box">
                            <div class="rte">
                                ${entry.getServiceAndActivities(locale)}
                            </div>
                        </div>
                    </div>
                </#if>

                <!-- TODO : Agenda -->

                <!-- Caractéristiques -->
                <#if entry.getCharacteristics(locale)?has_content>
                    <div class="seu-wi--collapsing">
                        <button class="seu-toggle-collapse">
                            <h2 class="caracteristiques"><span><@liferay_ui.message key="eu.features" /></span></h2>
                        </button>
                        <div class="seu-collapsing-box">
                            <div class="rte">
                                ${entry.getCharacteristics(locale)}
                            </div>
                        </div>
                    </div>
                </#if>

                <!-- Contact -->
                <#if entry.mail?has_content>
                    <div class="seu-wi--collapsing <#if renderRequest.getAttribute("fromContactForm")?has_content && renderRequest.getAttribute("fromContactForm")>seu-first-opened</#if>">
                        <button class="seu-toggle-collapse">
                            <h2 class="contact"><span><@liferay_ui.message key="contact" /></span></h2>
                        </button>
                        <div class="seu-collapsing-box white-box">
                            <div class="rte">
                                <@liferay_portlet.actionURL var="contactURL" name="contact">
                                    <@liferay_portlet.param name="classPK" value="${entry.getPlaceId()}" />
                                    <@liferay_portlet.param name="to" value="${entry.mail}" />
                                    <@liferay_portlet.param name="subject" value="Formulaire de contact - Lieu - ${entry.getAlias(locale)}" />
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
                                            name="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_notificationEmail" id="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_notificationEmail" checked>&nbsp;<@liferay_ui.message key="eu.do-you-want-a-notification" />
                                        </label>
                                    </div>
                                    <div style="margin: 20px 0;" class="g-recaptcha" data-sitekey="${propsUtil.get('eu.strasbourg.recaptcha.public')}"></div>
                                    <div style="padding-top: 20px">
                                        <@liferay_ui.message key="contact.default-privacy" />
                                    </div>
                                    <div class="buttons submit">
                                        <div class="SubmitWidget widget submit-button">
                                            <div class="content"><button name="submit" value="<@liferay_ui.message key="send" />"><@liferay_ui.message key="send" /></button></div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </#if>
            </div>


            <div class="seu-container-right">
                <#if entry.imagesURLs?has_content>
                    <div class="seu-wi-slideNpop">
                        <div class="slide">
                            <ul class="slider">
                                <#list entry.imagesURLs as imageURL>
                                    <#assign image = fileEntryHelper.getFileEntryByRelativeURL(imageURL) />
                                    <#assign title = fileEntryHelper.getFileTitle(image.getFileEntryId(), locale) />
                                    <#assign legend = fileEntryHelper.getImageLegend(image.getFileEntryId(), locale) />
                                    <#assign copyright = fileEntryHelper.getImageCopyright(image.getFileEntryId(), locale) />
                                    <li style="background-image: url(${imageURL});"  data-title="${title}" data-description="${legend} <#if copyright?has_content>&copy; ${copyright}</#if>">
                                        <img src="${imageURL}" alt="${title}">
                                    </li>
                                </#list>
                            </ul>
                            <div class="owl-nav">
                                <button class="seu-owl-prev disabled">
                                    <span class="seu-picto"></span>
                                </button>
                                <button class="seu-owl-next">
                                    <span class="seu-picto"></span>
                                </button>
                            </div>
                        </div>
                        <div class="pop">
                            <div class="pop-box">
                                <div class="pop-head">
                                    <div class="pop-title dotme">Test de titre</div>
                                    <button class="pop-close"></button>
                                </div>
                                <div class="owl-nav">
                                    <button class="seu-owl-prev disabled">
                                        <span class="seu-picto"></span>
                                    </button>
                                    <button class="seu-owl-next">
                                        <span class="seu-picto"></span>
                                    </button>
                                </div>
                                <div class="fill"></div>
                                <div class="pop-foot">
                                    <div class="pop-description dotme">Test description</div>
                                    <div class="pop-pager">
                                        <div class="pop-current"></div>
                                        <div class="pop-total"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="pop-area">
                                
                            </div>
                        </div>
                    </div>
                </#if>
                <div class="seu-location-infos">
                    <#if entry.isEnabled()>
                        <#assign occupationState = entry.getRealTime() />
                        <div class="seu-crowded-flexbox">
                            <div class="flex-left">
                                <#assign isSwimmingPool = entry.isSwimmingPool() />
                                <#if isSwimmingPool>
                                    <h3><@liferay_ui.message key="live-frequentation" /></h3>
                                <#else>
                                    <h3><@liferay_ui.message key="live-occupation" /></h3>
                                </#if>
                                <div class="crowded-date"><span class="wroded-day-month">${.now?date?string.long}</span><span> - </span><span class="crowded-time">${.now?time?string.short}</span></div>
                            </div>
                            <div class="flex-right">
                                <!-- green orange red black -->
                                <div class="crowded-amount ${occupationState.cssClass}">
                                    <#if isSwimmingPool>
                                        ${occupationState.occupation}
                                    <#else>
                                        ${occupationState.available}
                                    </#if>
                                </div>
                            </div>
                        </div>
                        <div class="crowded-caption">
                            <#if isSwimmingPool>
                                <@liferay_ui.message key="${occupationState.label}" />
                            <#else>
                                <@liferay_ui.message key="eu.place.available-spots" /> ${occupationState.available}
                            </#if>
                        </div>
                        <div class="crowded-fyi">    
                            <#if isSwimmingPool>
                                <@liferay_ui.message key="live-occupation-explanation" />
                            <#else>
                                <@liferay_ui.message key="eu.place.total-capacity" /> ${occupationState.capacity}
                            </#if>
                        </div>
                    </#if>

                    <h3><@liferay_ui.message key="eu.place.address-details" /></h3>
                    <div class="rte">
                        <p>
                            <#if entry.addressStreet?has_content>
                                ${entry.addressStreet} <br>
                            </#if>
                            <#if entry.addressComplement?has_content>
                                ${entry.addressComplement} <br>
                            </#if>
                            <#if entry.addressDistribution?has_content>
                                ${entry.addressDistribution} <br>
                            </#if>
                            ${entry.addressZipCode} ${entry.getCity(locale)}
                        </p>
                        <#if entry.phone?has_content>
                            <p>
                                <@liferay_ui.message key="phone" /> : ${entry.phone}
                            </p>
                        </#if>
                        <#if entry.getSiteLabel(locale)?has_content && entry.getSiteURL(locale)?has_content>
                            <p>
                                <a href="${entry.getSiteURL(locale)}" class="seu-external" title="${entry.getSiteLabel(locale)} (<@liferay_ui.message key="eu.new-window" />)" target="_blank">${entry.getSiteLabel(locale)}</a>
                            </p>
                        </#if>
                    </div>
                </div>
            </div>
        </div>  
    </main>
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
</style>