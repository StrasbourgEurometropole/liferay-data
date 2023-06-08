<!-- BLOC HOMPEAGE - DISCOVER - 3 BLOCS -->
<section class="ops-content-wrapper ops-content-wrapper-large ops-bloc-discover-ops">
    <h2 class="ops-big-section-title">${title.getData()}<span>${subTitle.getData()}</span></h2>

    <div class="ops-wrapper">
        <#if linkURL1.getData()?has_content>
            <a href="${linkURL1.getData()}" class="ops-col-50" <#if openInaNewTab1.getData() == "true">target="_blank"</#if>>
            
            <#else>
            <div class="ops-col-25">
        </#if>
            <figure class="fit-cover">
                <img src="${image1.getData()}" width="760" height="455" alt="Image"/>
            </figure>
            <div class="ops-caption">
                <h3>${title1.getData()}</h3>
                <#if linkURL1.getData()?has_content>
                    <span class="ops-basic-link"><@liferay_ui.message key="eu.ops.discover" /></span>
                </#if>
            </div>
        <#if linkURL1.getData()?has_content>
            </a>
            <#else>
            </div>
        </#if>

        <#if linkURL2.getData()?has_content>
            <a href="${linkURL2.getData()}" class="ops-col-25" <#if openInaNewTab2.getData() == "true">target="_blank"</#if>>
            <#else>
            <div class="ops-col-25">
        </#if>
            <figure class="fit-cover">
                <img src="${image2.getData()}" width="380" height="455" alt="Image"/>
            </figure>
            <div class="ops-caption">
                <h3>${title2.getData()}</h3>
                <#if linkURL2.getData()?has_content>
                    <span class="ops-basic-link"><@liferay_ui.message key="eu.ops.discover" /></span>
                </#if>
            </div>
        <#if linkURL2.getData()?has_content>
            </a>
            <#else>
            </div>
        </#if>
        


         <#if linkURL3.getData()?has_content>
            <a href="${linkURL3.getData()}" class="ops-col-25" <#if openInaNewTab3.getData() == "true">target="_blank"</#if>>
        <#else>
            <div class="ops-col-25">
         </#if>
            <figure class="fit-cover">
                <img src="${image3.getData()}" width="380" height="455" alt="Image"/>
            </figure>
            <div class="ops-caption">
                <h3>${title3.getData()}</h3>
                <#if linkURL3.getData()?has_content>
                    <span class="ops-basic-link"><@liferay_ui.message key="eu.ops.discover" /></span>
                 </#if>
            </div>
        <#if linkURL3.getData()?has_content>
            </a>
        <#else>
            </div>
        </#if>
    </div>
</section>