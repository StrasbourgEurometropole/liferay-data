<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
  <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
  <#assign homeURL = "/" />
</#if>

<#assign uriHelper = serviceLocator.findService("eu.strasbourg.utils.api.UriHelperService")/>

<!-- Webmag - List d'event -->
<section class="hp-agenda" data-scroll-animation>
    <h2 class="waved-title waved-title--center waved-title--t-white waved-title--w-transparent hp-agenda__title">Agenda</h2>
    <div class="slider-agenda rte-item" data-scroll-animation>
        <div class="slider-agenda-main">
            <div class="slider-agenda-main__loader">
                <div class="loader loader-cube-flipping">
                    <div class="cube-wrapper">
                        <div class="cube-folding">
                            <span class="leaf1"></span>
                            <span class="leaf2"></span>
                            <span class="leaf3"></span>
                            <span class="leaf4"></span>
                        </div>
                    </div>
                </div>
            </div> 
    
            <!-- Slider -->
            <ul class="slider-agenda-main__slider unstyled">
                <#list entries as curEntry>
                    <#assign entry = curEntry.getAssetRenderer().getEvent() />
                    <li class="slider-agenda-main__slider-item">
                        <div class="slider-agenda-main__slide" style="background-image: url(${entry.imageURL});">
                            <div class="slider-agenda-main__slide-text flexbox">
                                <a class="left unstyled" href="${homeURL}evenement/-/entity/id/${entry.eventId}/${entry.getNormalizedTitle(locale)}">
                                    <div class="slider-agenda-main__slide-date-prefix">
                                        <#if entry.firstStartDate?date == entry.lastEndDate?date>
                                            Le
                                        <#else>
                                            Du ${entry.firstStartDate?date?string.short?replace('/', '.')} au 
                                        </#if>
                                    </div>
                                    <div class="slider-agenda-main__slide-date">${entry.lastEndDate?date?string.short?replace('/', '.')}</div>
                                    <div class="slider-agenda-main__slide-title" data-dot="2">${entry.getTitle(locale)}</div>
                                    <div class="slider-agenda-main__slide-category" data-dot="2">${entry.getTypeLabel(locale)}</div>
                                </a>
                                <div class="right">
                                    <a href="${homeURL}lieu/-/entity/id/${entry.placeId}/${uriHelper.normalizeToFriendlyUrl(entry.getPlaceAlias(locale))}" class="slider-agenda-main__slide-tag unstyled"> ${entry.getPlaceAlias(locale)} - ${entry.getPlaceCity(locale)}</a>
                                    <a href="#" class="slider-agenda-main__slide-favorite unstyled add-favorites" 
                                        data-type="2" 
                                        data-title="${entry.getTitle(locale)}" 
                                        data-url="${themeDisplay.getPortalURL()}${homeURL}evenement/-/entity/id/${entry.eventId}/${entry.getNormalizedTitle(locale)}" 
                                        data-id="${entry.eventId}">
                                        <span>Ajouter à mes favoris</span>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </li>
                </#list>
            </ul>
    
            <!-- Slider Arrows -->
            <div class="slider-agenda-main__nav">
                <div class="slider-agenda-main__pager slider-agenda-main__pager--current"></div>
                <div class="slider-agenda-main__pager slider-agenda-main__pager--total slider-agenda-main__pager--total--slideNpop"></div>
                <button class="slider-agenda-main__arrow slider-agenda-main__arrow--prev">
                    <div class="flexbox">
                        <div class="slider-agenda-main__arrow-icon"></div>
                        <div class="slider-agenda-main__arrow-pagination">
                            <div class="slider-agenda-main__pager slider-agenda-main__pager--prev"></div>
                            <div class="slider-agenda-main__pager slider-agenda-main__pager--total"></div>
                        </div>
                    </div>
                </button>
                <button class="slider-agenda-main__arrow slider-agenda-main__arrow--next">
                    <div class="flexbox">
                        <div class="slider-agenda-main__arrow-icon"></div>
                        <div class="slider-agenda-main__arrow-pagination">
                            <div class="slider-agenda-main__pager slider-agenda-main__pager--next"></div>
                            <div class="slider-agenda-main__pager slider-agenda-main__pager--total"></div>
                        </div>
                    </div>
                </button>
            </div>  
    
            <!-- Slider Dots -->
            <div class="slider-agenda-main__dots">
                <button class="slider-agenda-main__dot" data-slider-index=""></button>
            </div>    
            
            <!-- Slider Play / Pause -->
            <button class="slider-agenda-main__playpause play"></button> 
    
            <!-- Slider Text -->
            <div class="slider-agenda-main__text">
                <h2 class="slider-agenda-main__title unstyled" data-dot="2"></h2>
                <div class="slider-agenda-main__description" data-dot="3"></div>
                <a href="#" class="slider-agenda-main__link unstyled"></a>
            </div>
        </div>
    </div>
    <div class="hp-agenda__more">
        <a href="${homeURL}agenda" class="a-btn-main core h-inverted icon-right">
            <span class="flexbox"><i class="mag mag-arrow-right"></i>
                <span class="btn-text">Tous les évènements</span>
            </span>
        </a>
    </div>
</section>