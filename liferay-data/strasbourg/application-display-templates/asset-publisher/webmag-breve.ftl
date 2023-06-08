<!-- Webmag - Brève -->
<#setting locale = locale />
<#assign assetPublisherTemplateHelperService = serviceLocator.findService("eu.strasbourg.utils.api.AssetPublisherTemplateHelperService")/>

<div class="smag-container smag-hp-breves__container" style="margin-top: 50px;">
    <section class="smag-hp-breves" data-scroll-animation>
        <h2 class="hp-diapo__title">Brèves</h2>
        <div class="slider-breves rte-item">
            <div class="slider-breves-main">
                <div class="slider-breves-main__loader">
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
                <ul class="slider-breves-main__slider unstyled">
                    <#list entries as curEntry>
                        <#if curEntry.getAssetRenderer()??>
                            <#assign docXml = saxReaderUtil.read(curEntry.getAssetRenderer().getArticle().getContentByLocale(locale)) />
                            <#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()")/>
                            <#assign chapo = docXml.valueOf("//dynamic-element[@name='chapo']/dynamic-content/text()") />
                            <#assign image = docXml.valueOf("//dynamic-element[@name='image']/dynamic-content/text()") />
                            <#assign currentURL = assetPublisherHelper.getAssetViewURL(renderRequest, renderResponse, curEntry) />
                            <#assign viewURL = curEntry.getAssetRenderer().getURLViewInContext(renderRequest, renderResponse, currentURL) />
                            <#assign id = curEntry.getAssetRenderer().getArticle().getArticleId() />
                            <#assign imageURL ="" />
                            <#if image?has_content>
                                <#assign imageURL = assetPublisherTemplateHelperService.getDocumentUrl(image) />
                            </#if>
                            <#if (curEntry?index % 3 == 0)> 
                                <li class="slider-breves-main__slider-item">
                            </#if>
                            <a href="${viewURL}" class="slider-breves-main__slide unstyled" >
                                <div class="slider-breves-main__slide-icon" style="background-image: url(${imageURL});"></div>
                                <div class="slider-breves-main__slide-text">
                                    <div class="slider-breves-main__slide-title" data-dot="2">${title}</div>
                                    <div class="slider-breves-main__slide-description" data-dot="2">${chapo}</div>
                                </div>
                            </a>
                            <#if (curEntry?index % 3 == 2)>
                                </li>
                            </#if>
                        </#if>
                    </#list>
                    <#if (entries?size % 3 != 0)> 
                        </li>
                    </#if>
                </ul>
    
                <!-- Slider Arrows -->
                <!--<div class="slider-breves-main__nav">
                    <div class="slider-breves-main__pager slider-breves-main__pager--current"></div>
                    <div class="slider-breves-main__pager slider-breves-main__pager--total slider-breves-main__pager--total--slideNpop"></div>
                    <button class="slider-breves-main__arrow slider-breves-main__arrow--prev">
                        <div class="flexbox">
                            <div class="slider-breves-main__arrow-icon"></div>
                            <div class="slider-breves-main__arrow-pagination">
                                <div class="slider-breves-main__pager slider-breves-main__pager--prev"></div>
                                <div class="slider-breves-main__pager slider-breves-main__pager--total"></div>
                            </div>
                        </div>
                    </button>
                    <button class="slider-breves-main__arrow slider-breves-main__arrow--next">
                        <div class="flexbox">
                            <div class="slider-breves-main__arrow-icon"></div>
                            <div class="slider-breves-main__arrow-pagination">
                                <div class="slider-breves-main__pager slider-breves-main__pager--next"></div>
                                <div class="slider-breves-main__pager slider-breves-main__pager--total"></div>
                            </div>
                        </div>
                    </button>
                </div> -->
    
                <!-- Slider Dots -->
                <div class="slider-breves-main__dots">
                    <button class="slider-breves-main__dot" data-slider-index=""></button>
                </div>    
                
                <!-- Slider Play / Pause -->
                <!-- <button class="slider-breves-main__playpause play"></button>  -->
        
                <!-- Slider Text -->
                <!-- <div class="slider-breves-main__text">
                    <h2 class="slider-breves-main__title unstyled" data-dot="2"></h2>
                    <div class="slider-breves-main__description" data-dot="3"></div>
                    <a href="#" class="slider-breves-main__link unstyled"></a>
                </div> -->
            </div>
        </div>
    </section>
</div>

<style>
    .smag-hp-breves{
        overflow-x: hidden;
    }

    .smag-hp-breves .owl-carousel .owl-stage-outer{
        overflow: visible;
    }

    .smag-hp-breves .owl-carousel .owl-stage {
        width: calc(300% + 150px) !important;
    }

    .smag-hp-breves .owl-carousel .owl-stage .owl-item.active{
        margin-right: 150px;
    }


    .smag-hp-breves .owl-carousel .owl-stage .owl-item.active .slider-breves-main__slider-item{
        padding-right: 0;
    }

    .slider-breves-main__slider-item{
        flex-wrap: wrap;
        padding-right: 150px;
    }

    .slider-breves-main__slide{
        width: 33.4%;
    }

    .slider-breves-main__slider-item .slider-breves-main__slide:nth-child(2){
        margin-bottom: 40px;
        margin-top: -130px;
        margin-left: 32%;
    }

    .slider-breves-main__slider-item .slider-breves-main__slide:nth-child(3){
        margin-left: auto;
        margin-top: -40px;
    }

    .slider-breves-main__dots{
        margin-top: 30px;
    }

    @media only screen and (max-width: 1279px){
        .slider-breves-main__slider-item .slider-breves-main__slide:nth-child(2) {
            margin-top: -60px;
        }
    }

    @media only screen and (max-width: 767px){
        .hp-diapo__title{
            bottom: 6%;
            left: 40px;
        }

        .slider-breves-main__slider-item{
            padding-right: 40px;
        }

        .slider-breves-main__slide{
            width: 100%;
        }

        .slider-breves-main__slider-item .slider-breves-main__slide:nth-child(2) {
            margin-bottom: 0; 
            margin-top: 20px; 
            margin-left:0;
        }

        .slider-breves-main__slider-item .slider-breves-main__slide:nth-child(3) {
            margin-top: 20px; 
        }

        .slider-breves-main__slide-text{
            width: inherit;
        }

    }
</style>