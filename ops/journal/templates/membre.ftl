<style>
    .portlet-body>div>div.h2 {
        display : none;

    }

    .has-control-menu #ops-wrapper main.ops-page-musicien, .ops-page-musicien {
        padding-top : 0px
    }

    .has-control-menu #ops-wrapper header.ops-header-musicien {
        padding-top : 57px
    }
</style>

<#assign cssClass = ""/>
<#if !bigImage.getData()?has_content>
    <#assign cssClass = "ops-figure-empty"/>
</#if>

<main class="ops-page-musicien">

    <header class="ops-header-musicien">
        <div class="ops-content-wrapper ${cssClass}">
            <#if bigImage.getData()?has_content>
                <div class="ops-figure-musicien" data-vheight="100 - 120">
                    <figure class="fit-cover">
                        <img src="${bigImage.getData()}" width="533" height="800" alt="${Name.getData()}" />
                    </figure>
                </div>
            </#if>

            <div class="ops-meta-musicien">
                <span class="ops-typologie">${post.getData()}</span>
                <h1>${Name.getData()}</h1>
            </div>
        </div>
    </header>

    <!-- BLOC - PRESENTATION DU MUSICIEN -->
    <#if description.getData()?has_content>
        <div class="ops-content-wrapper ops-bloc ops-bloc-presentation">
        
            <h2 class="ops-title-line"><span><@liferay_ui.message key="eu.ops.presentation" /></span></h2>

            <div class="ops-bloc-texte">
                <p class="ops-accroche">${description.getData()}</p>
                <#if linkURL.getData()?has_content && linkLabel.getData()?has_content>
                    <a href="${linkURL.getData()}" class="ops-btn" <#if openInaNewTab.getData() == "true">target="_blank"</#if> >${linkLabel.getData()}</a>
                </#if>
            </div>

        </div>
    </#if>

</main>