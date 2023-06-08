<!-- BLOC IMAGE OU VIDEO A LA UNE -->

<#if selectMedia.getData() == "image" >

    <header class="pro-header-medium">
        <figure class="fit-cover" role="group">
            <img src="${image.getData()}" width="1600" height="500" alt="${image.getAttribute("alt")}"/>
        </figure>
        <!--        <div class="pro-no-photo"></div>-->
        <div class="container caption">
            <#if surtitle.getData() !="" >
                <div class="pro-bloc-display"><span class="pro-surtitre">${surtitle.getData()}</span></div>
            </#if>
            <h1>
                ${titleLine1.getData()}
                <#if titleLine2.getData() != "" >
                    <br>
                    ${titleLine2.getData()}
                </#if>
            </h1>
            <div class="pro-description">
                <p>${description.getData()}</p>
            </div>
        </div>
    </header>

<#else>

    <header class="pro-header-fullpage">
        <div class="pro-cover">
            <div class="pro-container-video">
                <video width="1600" height="900" autoplay="autoplay" loop="loop" muted="muted">
                    <source src="${video.getData()}" type="video/mp4">
                </video>
            </div>
            <div class="pro-container-img">
                <figure class="fit-cover">
                    <img src="${image.getData()}" width="1600" height="900" alt="${image.getAttribute("alt")}" />
                </figure>
            </div>
            <div class="container caption">
                <#if surtitle.getData() !="" >
                    <div class="pro-bloc-display"><span class="pro-surtitre">${surtitle.getData()}</span></div>
                </#if>
                <h1>
                    ${titleLine1.getData()}
                    <#if titleLine2.getData() != "" >
                        <br>
                        ${titleLine2.getData()}
                    </#if>
                </h1>
                <div class="pro-description">
                    <p>${description.getData()}</p>
                </div>
            </div>
        </div>
    </header>

</#if>