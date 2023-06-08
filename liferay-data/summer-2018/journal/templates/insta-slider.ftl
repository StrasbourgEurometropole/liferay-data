<#setting locale = locale />
<div  class="mns-slider-insta">
    <div class="container">
        <div class="row">
            <div class="col-xs-12 mns-top-slider-insta">
                <p>
                    <a class="large-link" target="_blank" href="https://www.instagram.com/ete.strasbourg"><@liferay_ui.message key="eu.find-us-on-Instagram" /></a>
                </p>
            </div>
        </div>
    </div>
    <div class="owl-carousel owl-opacify owl-theme col-xs-12" id="owl-insta">
        <#list image.getSiblings() as content>
            <#if (content?index == 10)>
                <#break>
            </#if>
            <div class="item">
                <a href="${content.link.data}" title="${content.title.data}" target="blank">
                    <figure class="fit-cover">
                        <img src="${content.getData()}" alt="${content.title.data}" width="600" height="600" class="fit-cover" />
                    </figure>
                </a>
            </div>
        </#list>
    </div>
</div>