<#setting locale = locale />
<!-- Zone Entrées -->
<div class="mns-wrapper-home-entry">
    <div class="container mns-home-entry mns-p50">
        <img src="/o/noel-theme/images/deco-big-elipse.png" width="284" height="284" class="deco-bg" alt="image decoration" />
        <div class="col-lg-6 col-md-8 col-xs-12">
            <h1 class="title">${title1.data}</h1>
            <h2 class="city"><span>${title2.data}</span></h2>
        </div>
        <div>
            <div class="mns-bloc-entry">
                <img src="/o/noel-theme/images/deco-elipse-or.png" width="232" height="232" class="deco" alt="image decoration"/>
                <img src="/o/noel-theme/images/deco-stars-grey.png" width="400" height="400" class="deco-stars" alt="image decoration"/>
                <#list bubbleTitle.getSiblings() as cur_bubble>
                    <div class="mns-elipse-${cur_bubble?counter}">
                        <a href="${cur_bubble.children[1].data}">
                            <figure>
                                <img src="/o/noel-theme/images/home-elipse-${cur_bubble?counter}.jpeg" width="400" height="400" alt="${cur_bubble.data}" class="mns-image" />
                            </figure>
                            <div class="caption">
                                <div>
                                    <h3>${cur_bubble.data}</h3>
                                    <p>${cur_bubble.children[0].data}</p>
                                    <span>Découvrir</span>
                                </div>
                            </div>
                        </a>
                    </div>
                </#list>
            </div>
        </div>
    </div>
</div>
