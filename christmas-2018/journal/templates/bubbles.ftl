<#setting locale = locale />
<!-- Zone Entrées -->
<div class="mns-wrapper-home-entry">
    <div class="container mns-home-entry mns-p50">
        <img src="/o/christmas-2018-theme/images/deco-big-elipse.png" width="284" height="284" class="deco-bg" alt="image decoration" />
        <div class="col-xl-6 col-lg-8 col-md-12 bubbles-title">
            <h1 class="title">${title1.data}</h1>
            <h2 class="city"><span>${title2.data}</span></h2>
        </div>
        <div>
            <div class="mns-bloc-entry">
                <img src="/o/christmas-2018-theme/images/deco-elipse-or.png" width="232" height="232" class="deco" alt="image decoration"/>
                <img src="/o/christmas-2018-theme/images/deco-stars-grey.png" width="400" height="400" class="deco-stars" alt="image decoration"/>
                <#list bubbleTitle.getSiblings() as cur_bubble>
                    <div class="mns-elipse-${cur_bubble?counter}">
                        <a href="${cur_bubble.children[1].data}">
                            <figure>
                                <img src="/o/christmas-2018-theme/images/home-elipse-${cur_bubble?counter}.jpeg" width="400" height="400" alt="${cur_bubble.data}" class="mns-image" />
                            </figure>
                            <div class="caption">
                                <div>
                                    <h3>${cur_bubble.data}</h3>
                                    <p>${cur_bubble.children[0].data}</p>
                                    <span><@liferay_ui.message key="eu.discover" /></span>
                                </div>
                            </div>
                        </a>
                    </div>
                </#list>
            </div>
        </div>
    </div>
</div>
