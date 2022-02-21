<#setting locale = locale />
<div  class="mns-list-social-wall">
    <div class="container">
        <div class="row">
            <div class="col-xs-12 mns-bottom-social-wall">
                <p>
                    <a class="large-link" target="_blank" href="https://www.instagram.com/strasbourgcapitaledenoel/">Retrouvez-nous sur instagram</a>
                </p>
            </div>
        </div>
    </div>
    <div class="owl-carousel owl-theme" id="owl-social-wall">
        <#list image.getSiblings() as content>
            <#if (content?index == 10)>
                <#break>
            </#if>
            <div class="item">
                <a href="${content.link.data}" title="${content.title.data}">
                    <figure class="fit-cover">
                        <img src="${content.getData()}" alt="${content.title.data}" width="600" height="600" class="fit-cover" />
                    </figure>
                </a>
            </div>
        </#list>
    </div>
</div>