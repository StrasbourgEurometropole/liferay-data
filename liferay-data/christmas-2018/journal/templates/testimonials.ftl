<#setting locale = locale />
<!-- Zone Testimonials -->
<div class="mns-testimonial small-container">
    <img src="/o/christmas-2018-theme/images/cote-left.png" alt="image" class="img-cote-left" width="232" height="161" />
    <div>
        <div class="col-xs-12">
            <div class="owl-carousel owl-theme owl-testi col-xs-12">
                <#list picture.getSiblings() as cur_picture>
                 <div class="item">
                    <blockquote class="mns-bloc-testi row">
                        <figure class="col-sm-3 col-xs-12">
                            <img src="${cur_picture.getData()}" alt="Image Testimonial" width="190" height="190" />
                        </figure>
                        <div class="col-sm-9 col-xs-12 text-content">
                            <h4 class="title">${cur_picture.getChildren()[0].getData()}</h4>
                            <p>${cur_picture.getChildren()[1].getData()}</p>
                            <p class="info-testi">${cur_picture.getChildren()[2].getData()}</p>
                            <#if cur_picture.getChildren()[3].data?has_content && cur_picture.getChildren()[4].data?has_content>
                                <a href="${cur_picture.getChildren()[4].getData()}" class="link" target="_blank">${cur_picture.getChildren()[3].getData()}</a>
                            </#if>
                        </div>
                    </blockquote>
                </div>
                </#list>
            </div>
        </div>
    </div>
    <img src="/o/christmas-2018-theme/images/cote-left.png" alt="image" class="img-cote-right" width="130" height="190" />
</div>