<!-- diaporama photo -->

<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>
<#assign serviceContext = staticUtil["com.liferay.portal.kernel.service.ServiceContextThreadLocal"].getServiceContext() />
<#assign themeDisplay = serviceContext.getThemeDisplay() />
<#assign currentUrl = themeDisplay.getPortalURL() + themeDisplay.getURLCurrent() />

<@liferay_util["html-top"]>
    <meta property="og:title" content="${title.getData()?html}" />
    <meta property="og:description" content="${content.getData()?replace("<[^>]*>", "", "r")?html}" />
    <meta property="og:url" content="${currentUrl}" />
    <meta property="og:image" content="${themeDisplay.getPortalURL()}${image.getData()}" />
</@>
    <div>

        <h1>${title.getData()}</h1>
        <#if photos.getSiblings()?has_content>
            <div id="smag-banner">
                <section>
                    <div class="slider-une rte-item">
                        <div class="slider-une-main">
                            <div class="slider-une-main__loader">
                                <div class="loader"></div>
                            </div>
                
                            <!-- Slider -->
                            <ul class="slider-une-main__slider unstyled">
                                <#list photos.getSiblings() as photo>                
                                    <li class="slider-une-main__slider-item">
                                        <div class="slider-une-main__slide" data-title="${photo.getChildren()[0].getData()}" style="background-image: url(${photo.getData()});">
                                            <div class="slider-une-main__slide-image">
                                                <div class="slider-une-main__slide-background" style="background-image: url(${photo.getData()});"></div>
                                            </div> 
                                        </div>
                                    </li>
                                </#list>    
                            </ul>
                
                            <!-- Slider Arrows -->
                            <div class="slider-une-main__nav">
                                <div class="slider-une-main__pager slider-une-main__pager--current"></div>
                                <div class="slider-une-main__pager slider-une-main__pager--total slider-une-main__pager--total--slideNpop"></div>
                                <button class="slider-une-main__arrow slider-une-main__arrow--prev">
                                    <div class="flexbox">
                                        <div class="slider-une-main__arrow-icon"></div>
                                        <div class="slider-une-main__arrow-pagination">
                                            <div class="slider-une-main__pager slider-une-main__pager--prev"></div>
                                            <div class="slider-une-main__pager slider-une-main__pager--total"></div>
                                        </div>
                                    </div>
                                </button>
                                <button class="slider-une-main__arrow slider-une-main__arrow--next">
                                    <div class="flexbox">
                                        <div class="slider-une-main__arrow-icon"></div>
                                        <div class="slider-une-main__arrow-pagination">
                                            <div class="slider-une-main__pager slider-une-main__pager--next"></div>
                                            <div class="slider-une-main__pager slider-une-main__pager--total"></div>
                                        </div>
                                    </div>
                                </button>
                            </div>  
                
                            <!-- Slider Dots -->
                            <div class="slider-une-main__dots">
                                <button class="slider-une-main__dot" data-slider-index=""></button>
                            </div>    
                            
                            <!-- Slider Play / Pause -->
                            <button class="slider-une-main__playpause play"></button> 
                
                            <!-- Slider Text -->
                            <h2 class="slider-une-main__title unstyled"></h2>
                        </div>
                    </div>
                </section>
            </div>
        </#if>
        <p class="seu-container">${content.getData()}</p>
    </div>

    <style>
        .slider-une-main__slide {
            background-size: contain;
            background-color: #31455d;
        }

        .slider-une-main__slide:before{
            content: none;
        }

        .slider-une-main__title{
            font-size: 2rem;
            position: absolute;
            top: 95%;
            left: 50%;
            z-index: 2;
            color: #23527c;
            -webkit-transform: translate(-50%, -50%);
            transform: translate(-50%, -50%);
            -webkit-transition: all 0.25s;
            transition: all 0.25s;
            width: 100%;
            height: 70px;
            padding: 10px;
            background-color: rgba(255, 255, 255, 0.65);
        }

        .slider-une-thumbnail__slider.owl-carousel{
            margin-top: 170px;
        }

        .slider-une-thumbnail__nav{
            top: -150%;
            width: calc(100% - 20px);
            left: 10px;
        }

        .slider-une-thumbnail__nav .slider-une-thumbnail__arrow--prev{
            margin-right: auto;
        }
    </style>
    

<@liferay_util["html-bottom"]>
    <script type="text/javascript" src="/o/strasbourg-theme/js/conf.js"></script>
    <script type="text/javascript" src="/o/strasbourg-theme/js/libraries.js"></script>
    <script type="text/javascript" src="/o/strasbourg-theme/js/slider.js"></script>
</@>