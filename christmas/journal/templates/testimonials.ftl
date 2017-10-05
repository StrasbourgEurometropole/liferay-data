<#setting locale = locale />
<!-- Zone Testimonials -->
<div class="mns-testimonial small-container row">
    <img src="/o/noel-theme/images/cote-left.png" alt="image" class="img-cote-left" width="232" height="161" />
    <div>
        <div class="col-xs-12">
            <div class="owl-carousel owl-theme col-xs-12" id="owl-testi">
                <#list picture.getSiblings() as cur_picture>
                    <blockquote class="mns-bloc-testi row">
                        <figure class="col-sm-3 col-xs-12">
                            <img src="${cur_picture.getData()}" alt="Image Testimonial" width="190" height="190" />
                        </figure>
                        <div class="col-sm-9 col-xs-12 text-content">
                            <h4 class="title">${cur_picture.getChildren()[0].getData()}</h4>
                            <p>${cur_picture.getChildren()[1].getData()}</p>
                            <p class="info-testi">${cur_picture.getChildren()[2].getData()}</p>
                            <a href="${cur_picture.getChildren()[4].getData()}" class="link" target="_blank">${cur_picture.getChildren()[3].getData()}</a>
                        </div>
                    </blockquote>
                </#list>
            </div>
        </div>
    </div>
    <img src="/o/noel-theme/images/cote-left.png" alt="image" class="img-cote-right" width="130" height="190" />
</div>