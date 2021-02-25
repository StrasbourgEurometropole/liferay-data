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
    <#if (subtitle.siblings?size > 0)>
        <div class="row">
            <div class="col-lg-5 col-md-6 col-sm-6 col-xs-12 order-2">
                <span>${subtitle.siblings[0].data}</span>
                <h2>${subtitle.siblings[0].children[0].data}</h2>
                <strong>${subtitle.siblings[0].children[1].data}</strong>
                <p>${subtitle.siblings[0].children[2].data}</p>
                <a href="${subtitle.siblings[0].children[3].data}" class="link"><@liferay_ui.message key="eu.discover" /></a>
            </div>
            <div class="col-lg-7 col-md-6 col-sm-6 col-xs-12 pull-right order-1">
                <img src="/o/christmas-2018-theme/images/deco-elipse-or.png" alt="deco elipse" width="232" height="232" class="deco-elipse-top-1" />
                <a href="#">
                    <span class="mns-badge badge-left">1</span>
                    <figure class="mns-wrapper-img mns-wrapper-img-top1">
                        <img src="${subtitle.siblings[0].children[4].getData()}" alt="${subtitle.siblings[0].children[0].getData()}" width="670" height="750" class="fit-cover" />
                    </figure>
                </a>
            </div>
        </div>
    </#if>
    <#if (subtitle.siblings?size > 1)>
        <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                <a href="${subtitle.siblings[1].children[3].data}">
                    <span class="mns-badge badge-right">2</span>
                    <figure class="mns-wrapper-img mns-wrapper-img-top2">
                        <img src="${subtitle.siblings[1].children[4].getData()}" alt="${subtitle.siblings[1].children[0].getData()}" width="570" height="570" class="fit-cover" />
                    </figure>
                </a>
            </div>
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 pull-right">
                <span>${subtitle.siblings[1].data}</span>
                <h2>${subtitle.siblings[1].children[0].data}</h2>
                <strong>${subtitle.siblings[1].children[1].data}</strong>
                <p>${subtitle.siblings[1].children[2].data}</p>
                <a href="${subtitle.siblings[1].children[3].data}" class="link"><@liferay_ui.message key="eu.discover" /></a>
            </div>
        </div>
    </#if>
    <#if (subtitle.siblings?size > 2)>
        <img src="/o/christmas-2018-theme/images/parallax-deco-triangle.svg" alt="deco parallax" class="hidden-xs mns-para para-triangle" data-stellar-offset-parent="true" data-stellar-vertical-offset="-150" data-stellar-ratio="1.25" />
        <div class="row">
            <div class="col-lg-5 col-md-6 col-sm-6 col-xs-12 order-2">
                <span>${subtitle.siblings[2].data}</span>
                <h2>${subtitle.siblings[2].children[0].data}</h2>
                <strong>${subtitle.siblings[2].children[1].data}</strong>
                <p>${subtitle.siblings[2].children[2].data}</p>
                <a href="${subtitle.siblings[2].children[3].data}" class="link"><@liferay_ui.message key="eu.discover" /></a>
            </div>
            <div class="col-lg-7 col-md-6 col-sm-6 col-xs-12 pull-right order-1">
                <a href="${subtitle.siblings[2].children[3].data}">
                    <span class="mns-badge badge-left">3</span>
                    <figure class="mns-wrapper-img mns-wrapper-img-top1">
                        <img src="${subtitle.siblings[2].children[4].getData()}" alt="${subtitle.siblings[2].children[0].getData()}" class="fit-cover"/>
                    </figure>
                </a>
            </div>
        </div>
    </#if>
    <#if (subtitle.siblings?size > 3)>
        <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                <a href="${subtitle.siblings[3].children[3].data}">
                    <span class="mns-badge badge-right">4</span>
                    <figure class="mns-wrapper-img mns-wrapper-img-top2">
                        <img src="${subtitle.siblings[3].children[4].getData()}" alt="${subtitle.siblings[3].children[0].getData()}" class="fit-cover" />
                    </figure>
                </a>
            </div>
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 pull-right">
                <span>${subtitle.siblings[3].data}</span>
                <h2>${subtitle.siblings[3].children[0].data}</h2>
                <strong>${subtitle.siblings[3].children[1].data}</strong>
                <p>${subtitle.siblings[3].children[2].data}</p>
                <a href="${subtitle.siblings[3].children[3].data}" class="link"><@liferay_ui.message key="eu.discover" /></a>
            </div>
        </div>
    </#if>
    <#if (subtitle.siblings?size > 4)>
        <div class="row">
            <div class="col-lg-5 col-md-6 col-sm-6 col-xs-12 order-2">
                <span>${subtitle.siblings[4].data}</span>
                <h2>${subtitle.siblings[4].children[0].data}</h2>
                <strong>${subtitle.siblings[4].children[1].data}</strong>
                <p>${subtitle.siblings[4].children[2].data}</p>
                <a href="${subtitle.siblings[4].children[3].data}" class="link"><@liferay_ui.message key="eu.discover" /></a>
            </div>
            <div class="col-lg-7 col-md-6 col-sm-6 col-xs-12 pull-right order-1">
                <a href="${subtitle.siblings[4].children[3].data}">
                    <span class="mns-badge badge-left">5</span>
                    <figure class="mns-wrapper-img mns-wrapper-img-top1">
                        <img src="${subtitle.siblings[4].children[4].getData()}" alt="${subtitle.siblings[4].children[0].getData()}" class="fit-cover" />
                    </figure>
                </a>
            </div>
        </div>
    </#if>
    <#if (subtitle.siblings?size > 5)>
        <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                <a href="${subtitle.siblings[5].children[4].data}">
                    <span class="mns-badge badge-right mns-medium">6</span>
                    <figure class="mns-wrapper-img mns-wrapper-img-top2">
                        <img src="${subtitle.siblings[5].children[4].getData()}" alt="${subtitle.siblings[5].children[0].getData()}" class="fit-cover" />
                    </figure>
                </a>
            </div>
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 pull-right">
                <span>${subtitle.siblings[5].data}</span>
                <h2>${subtitle.siblings[5].children[0].data}</h2>
                <strong>${subtitle.siblings[5].children[1].data}</strong>
                <p>${subtitle.siblings[5].children[2].data}</p>
                <a href="${subtitle.siblings[5].children[3].data}" class="link"><@liferay_ui.message key="eu.discover" /></a>
            </div>
        </div>
    </#if>
    <#if (subtitle.siblings?size > 6)>
        <img src="/o/christmas-2018-theme/images/parallax-deco-beige.png" alt="deco parallax" class="hidden-xs mns-para para-elipse" data-stellar-offset-parent="true" data-stellar-vertical-offset="-150" data-stellar-ratio="1.25" width="396" height="399" />
        <div class="row">
            <div class="col-lg-offset-1 col-lg-5 col-md-6 col-sm-6 col-xs-12 order-2">
                <span>${subtitle.siblings[6].data}</span>
                <h2>${subtitle.siblings[6].children[0].data}</h2>
                <strong>${subtitle.siblings[6].children[1].data}</strong>
                <p>${subtitle.siblings[6].children[2].data}</p>
                <a href="${subtitle.siblings[6].children[3].data}" class="link"><@liferay_ui.message key="eu.discover" /></a>
            </div>
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 pull-right order-1">
                <a href="${subtitle.siblings[6].children[3].data}">
                    <span class="mns-badge badge-left-top">7</span>
                    <figure class="mns-wrapper-img mns-wrapper-img-top2">
                        <img src="${subtitle.siblings[6].children[4].getData()}" alt="${subtitle.siblings[6].children[0].getData()}" class="fit-cover" />
                    </figure>
                </a>
            </div>
        </div>
    </#if>
    <#if (subtitle.siblings?size > 7)>
        <div class="row">
            <div class="col-lg-5 col-md-6 col-sm-6 col-xs-12">
                <img src="/o/christmas-2018-theme/images/deco-elipse-grey.png" alt="deco elipse" width="96" height="96" class="deco-elipse-top-8" />
                <a href="${subtitle.siblings[7].children[3].data}">
                    <span class="mns-badge badge-right-top-small mns-medium">8</span>
                    <figure class="mns-wrapper-img mns-wrapper-img-top8">
                        <img src="${subtitle.siblings[7].children[4].getData()}" alt="${subtitle.siblings[7].children[0].getData()}" class="fit-cover" />
                    </figure>
                </a>
            </div>
            <div class="col-lg-7 col-md-6 col-sm-6 col-xs-12 pull-right">
                <span>${subtitle.siblings[7].data}</span>
                <h2>${subtitle.siblings[7].children[0].data}</h2>
                <strong>${subtitle.siblings[7].children[1].data}</strong>
                <p>${subtitle.siblings[7].children[2].data}</p>
                <a href="${subtitle.siblings[7].children[3].data}" class="link"><@liferay_ui.message key="eu.discover" /></a>
            </div>
        </div>
    </#if>
    <#if (subtitle.siblings?size > 8)>
        <div class="row">
            <div class="col-lg-6 col-sm-offset-1 col-md-6 col-md-offset-0 col-sm-6 col-sm-offset-0 col-xs-12 text-right order-2">
                <span>${subtitle.siblings[8].data}</span>
                <h2>${subtitle.siblings[8].children[0].data}</h2>
                <strong>${subtitle.siblings[8].children[1].data}</strong>
                <p>${subtitle.siblings[8].children[2].data}</p>
                <a href="${subtitle.siblings[8].children[3].data}" class="link"><@liferay_ui.message key="eu.discover" /></a>
            </div>
            <div class="col-lg-5 col-md-6 col-sm-6 col-xs-12 pull-right order-1">
                <a href="${subtitle.siblings[8].children[3].data}">
                    <span class="mns-badge badge-left-top-small">9</span>
                    <figure class="mns-wrapper-img mns-wrapper-img-top8">
                        <img src="${subtitle.siblings[8].children[4].getData()}" alt="${subtitle.siblings[8].children[0].getData()}" class="fit-cover"/>
                    </figure>
                </a>
            </div>
        </div>
    </#if>
    <#if (subtitle.siblings?size > 9)>
        <img src="/o/christmas-2018-theme/images/deco-stars-grey.png" alt="deco parallax" class="hidden-xs mns-para para-stars" data-stellar-offset-parent="true" data-stellar-vertical-offset="-150" data-stellar-ratio="1.25" width="396" height="399" />
        <div class="row">
            <div class="col-lg-5 col-md-6 col-sm-6 col-xs-12">
                <img src="/o/christmas-2018-theme/images/deco-elipse-beige.png" alt="deco elipse" width="96" height="96" class="deco-elipse-top-10" />
                <a href="${subtitle.siblings[9].children[3].data}">
                    <span class="mns-badge badge-right-top-small mns-medium">10</span>
                    <figure class="mns-wrapper-img mns-wrapper-img-top8">
                        <img src="${subtitle.siblings[9].children[4].getData()}" alt="${subtitle.siblings[9].children[0].getData()}" class="fit-cover" />
                    </figure>
                </a>
            </div>
            <div class="col-lg-7 col-md-6 col-sm-6 col-xs-12">
                <span>${subtitle.siblings[9].data}</span>
                <h2>${subtitle.siblings[9].children[0].data}</h2>
                <strong>${subtitle.siblings[9].children[1].data}</strong>
                <p>${subtitle.siblings[9].children[2].data}</p>
                <a href="${subtitle.siblings[9].children[3].data}" class="link"><@liferay_ui.message key="eu.discover" /></a>
            </div>
        </div>
    </#if>
</div>
