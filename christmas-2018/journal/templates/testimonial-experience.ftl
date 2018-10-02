<#setting locale = locale />
<!-- Zone Testimonials -->
<div class="mns-testimonial mns-testimonial-experience small-container row">
    <img src="/o/christmas-2018-theme/images/cote-left.png" alt="image" class="img-cote-left" width="232" height="161" />
    <div>
        <div>
            <div class="owl-carousel owl-theme owl-testi col-xs-12">
                <#list picture.getSiblings() as cur_picture>
                <div class="item">
                    <div class="mns-txt">
                        <blockquote>
                            ${cur_picture.getChildren()[0].getData()}
                        </blockquote>
                        <span class="mns-name">${cur_picture.getChildren()[1].getData()}</span>
                    </div>
                    <div class="mns-img">
                        <figure class="fit-cover">
                            <img src="${cur_picture.getData()}" width="265" height="265" alt="Image associé au testimonial">
                        </figure>
                    </div>
                </div>
                </#list>
            </div>
        </div>
    </div>
    <img src="/o/christmas-2018-theme/images/cote-left.png" alt="image" class="img-cote-right" width="130" height="190" />
</div>