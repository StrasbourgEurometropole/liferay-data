<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
  <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
  <#assign homeURL = "/" />
</#if>

<div id="events">
    <h3><@liferay_ui.message key="sae.events" /></h3>
    <!-- Slider main container -->
    <div class="swiper-event-container">

        <div class="swiper-wrapper">
            <!-- Slides -->
            <#assign nbEvents = 0 />
            <#list entries as curEntry>
                <#if nbEvents == 6>
                    <#break>
                </#if>
                <#assign nbEvents = nbEvents + 1 />
                <#assign entry = curEntry.getAssetRenderer().getEvent() />

                <div class="swiper-slide">
                    <a class="event-content" href="${homeURL}evenement/-/entity/id/${entry.eventId}/${entry.normalizedTitle}">
                        <img class="event-image" src="${entry.getImageURL()}" loading="lazy" />
                        <div class="event-date">
                            ${entry.getEventScheduleDisplay(locale)}
                        </div>
                        <div class="event-meta"> 
                            <div class="event-title"> 
                                <span>${entry.getTitle(locale)}</span>
                            </div>
                        </div>
                    </a>
                </div>                
            </#list>
        </div>

        <div class="swiper-button-prev swiper-event-button-prev"></div>
        <div class="swiper-button-next swiper-event-button-next"></div>

        <div class="swiper-event-pagination"></div>
    </div>
    <a class="btn-square" title="<@liferay_ui.message key="sae.all-events" />" href="/agenda">
        <span class="btn-text"><@liferay_ui.message key="sae.all-events" /></span> 
    </a>
</div>


<@liferay_util["html-bottom"]>
    <script type="text/javascript">
        const swiperEvent = new Swiper('.swiper-event-container', {
            // Optional parameters
            loop: true,
            slidesPerView: 1,
            spaceBetween: 65,
            // Responsive breakpoints
            breakpoints: {
                // when window width is >= 1200px
                1200: {
                    slidesPerView: 2
                }
            },

            // If we need pagination
            pagination: {
                el: '.swiper-event-pagination',
                clickable: true
            },

            // Navigation arrows
            navigation: {
                nextEl: '.swiper-event-button-next',
                prevEl: '.swiper-event-button-prev'
            }
        });
    </script>
</@>