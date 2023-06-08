<#setting locale = locale />

<section id="headerMuseum">
    <h1>${layout.getName(locale)}</h1>
    <div class="slider">
        <div class="swiper">
            <div class="swiper-wrapper">
                <#list image.getSiblings() as cur_image>
		            <#if (cur_image.getData())?? && cur_image.getData() != "">
                        <div class="swiper-slide">
			                <div class="info image-with-copyright-on-hover">
    			                <div class="image" style="background-image:url(${cur_image.getData()})" >
                                </div>
			                    <div class="copyright"><span>C</span><span>${cur_image.getAttribute("alt")}</span></div>
                            </div>
                        </div>
	                </#if>
                </#list>
            </div>
        </div>
    </div>
                    
    <div class="swipper-buttons">
        <div class="swiper-button-prev"></div>
        <div class="swiper-button-play"></div>
        <div class="swiper-button-pause"></div>
        <div class="swiper-button-next"></div>
    </div>
</section>