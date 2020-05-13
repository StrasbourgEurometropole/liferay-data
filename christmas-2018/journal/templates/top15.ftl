<#setting locale = locale />
<div class="mns-fck text-center small-container mns-p50 mns-intro-top10">
    <div class="row">
        <div class="col-xs-12">
            <p><strong>${boldHeader.data}</strong></p>
            <p>${header.data}</p>
        </div>
    </div>
</div>
<div class="container mns-top-10">
    <#list subtitle.siblings as sibling>
        <#if sibling?counter = 3 >
            <img src="/o/christmas-2018-theme/images/parallax-deco-triangle.svg" alt="deco parallax" class="hidden-xs mns-para para-triangle" data-stellar-offset-parent="true" data-stellar-vertical-offset="-150" data-stellar-ratio="1.25" />
        </#if>
        <#if sibling?counter = 7 >
            <img src="/o/christmas-2018-theme/images/parallax-deco-beige.png" alt="deco parallax" class="hidden-xs mns-para para-elipse" data-stellar-offset-parent="true" data-stellar-vertical-offset="-150" data-stellar-ratio="1.25" width="396" height="399" />
        </#if>
        <#if sibling?counter = 10 >
            <img src="/o/christmas-2018-theme/images/deco-stars-grey.png" alt="deco parallax" class="hidden-xs mns-para para-stars" data-stellar-offset-parent="true" data-stellar-vertical-offset="-150" data-stellar-ratio="1.25" width="396" height="399" />
        </#if>
        <#if sibling?counter % 2 != 0>
            <div class="row">
                <#if sibling?counter = 7 >
                    <div class="col-lg-offset-1 col-lg-5 col-md-6 col-sm-6 col-xs-12 order-2">
                        <span>${sibling.data}</span>
                        <h2>${sibling.children[0].data}</h2>
                        <strong>${sibling.children[1].data}</strong>
                        <p>${sibling.children[2].data}</p>
                        <a href="${sibling.children[3].data}" class="link"><@liferay_ui.message key="eu.discover" /></a>
                    </div>
                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 pull-right order-1">
                        <a href="${sibling.children[3].data}">
                            <span class="mns-badge badge-left-top">${sibling?counter}</span>
                            <figure class="mns-wrapper-img mns-wrapper-img-top2">
                                <img src="${sibling.children[4].getData()}" alt="${sibling.children[0].getData()}" class="fit-cover" />
                            </figure>
                        </a>
                    </div>
                <#elseif sibling?counter gte 9 >
                    <div class="col-lg-6 col-sm-offset-1 col-md-6 col-md-offset-0 col-sm-6 col-sm-offset-0 col-xs-12 text-right order-2">
                        <span>${sibling.data}</span>
                        <h2>${sibling.children[0].data}</h2>
                        <strong>${sibling.children[1].data}</strong>
                        <p>${sibling.children[2].data}</p>
                        <a href="${sibling.children[3].data}" class="link"><@liferay_ui.message key="eu.discover" /></a>
                    </div>
                    <div class="col-lg-5 col-md-6 col-sm-6 col-xs-12 pull-right order-1">
                        <a href="${sibling.children[3].data}">
                            <span class="mns-badge badge-left-top-small">${sibling?counter}</span>
                            <figure class="mns-wrapper-img mns-wrapper-img-top8">
                                <img src="${sibling.children[4].getData()}" alt="${sibling.children[0].getData()}" class="fit-cover" />
                            </figure>
                        </a>
                    </div>
                <#else >
                    <div class="col-lg-5 col-md-6 col-sm-6 col-xs-12 order-2">
                        <span>${sibling.data}</span>
                        <h2>${sibling.children[0].data}</h2>
                        <strong>${sibling.children[1].data}</strong>
                        <p>${sibling.children[2].data}</p>
                        <a href="${sibling.children[3].data}" class="link"><@liferay_ui.message key="eu.discover" /></a>
                    </div>
                    <div class="col-lg-7 col-md-6 col-sm-6 col-xs-12 pull-right order-1">
                        <#if sibling?counter = 1 >
                            <img src="/o/christmas-2018-theme/images/deco-elipse-or.png" alt="deco elipse" width="232" height="232" class="deco-elipse-top-1" />
                        </#if>
                        <a href="${sibling.children[3].data}">
                            <span class="mns-badge badge-left">${sibling?counter}</span>
                            <figure class="mns-wrapper-img mns-wrapper-img-top1">
                                <#if sibling?counter = 1 >
                                    <img src="${sibling.children[4].getData()}" alt="${sibling.children[0].getData()}" width="670" height="750" class="fit-cover" />
                                <#else >
                                    <img src="${sibling.children[4].getData()}" alt="${sibling.children[0].getData()}" class="fit-cover"/>
                                </#if>
                            </figure>
                        </a>
                    </div>
                </#if>
            </div>
        <#else>
            <div class="row">
                <#if sibling?counter lte 6 >
                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                        <a href="${sibling.children[3].data}">
                            <#if sibling?counter = 6 >
                                <span class="mns-badge badge-right mns-medium">${sibling?counter}</span>
                            <#else>
                                <span class="mns-badge badge-right">${sibling?counter}</span>
                            </#if>
                            <figure class="mns-wrapper-img mns-wrapper-img-top2">
                                <#if sibling?counter = 2 >
                                    <img src="${sibling.children[4].getData()}" alt="${sibling.children[0].getData()}" width="570" height="570" class="fit-cover" />
                                <#else>
                                    <img src="${sibling.children[4].getData()}" alt="${sibling.children[0].getData()}" class="fit-cover" />
                                </#if>
                            </figure>
                        </a>
                    </div>
                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 pull-right">
                <#else >
                    <div class="col-lg-5 col-md-6 col-sm-6 col-xs-12">
                        <#if sibling?counter = 8 >
                            <img src="/o/christmas-2018-theme/images/deco-elipse-grey.png" alt="deco elipse" width="96" height="96" class="deco-elipse-top-8" />
                        </#if>
                        <#if sibling?counter = 10 >
                            <img src="/o/christmas-2018-theme/images/deco-elipse-beige.png" alt="deco elipse" width="96" height="96" class="deco-elipse-top-10" />
                        </#if>
                        <a href="${sibling.children[3].data}">
                            <span class="mns-badge badge-right-top-small mns-medium">${sibling?counter}</span>
                            <figure class="mns-wrapper-img mns-wrapper-img-top8">
                                <img src="${sibling.children[4].getData()}" alt="${sibling.children[0].getData()}" class="fit-cover" />
                            </figure>
                        </a>
                    </div>
                    <#if sibling?counter = 8 >
                        <div class="col-lg-7 col-md-6 col-sm-6 col-xs-12 pull-right">
                    <#else >
                        <div class="col-lg-7 col-md-6 col-sm-6 col-xs-12">
                    </#if>
                </#if>
                    <span>${sibling.data}</span>
                    <h2>${sibling.children[0].data}</h2>
                    <strong>${sibling.children[1].data}</strong>
                    <p>${sibling.children[2].data}</p>
                    <a href="${sibling.children[3].data}" class="link"><@liferay_ui.message key="eu.discover" /></a>
                </div>
            </div>
        </#if>
    </#list>
</div>