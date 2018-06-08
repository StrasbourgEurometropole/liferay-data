<#assign currProject = entry.getAssetRenderer().getProject()>

<div class="pro-timeline">

<div class="pro-jalon pro-first">
    <div>
        <div class="pro-date">
            <div>
                <span>${entry.getPublishDate()?string["dd/MM"]}</span>
                <span class="pro-year">${entry.getPublishDate()?string["yyyy"]}</span>
            </div>
        </div>
    </div>
</div>

<#list currProject.getProjectTimelines() as tl>

<#if dateUtil.compareTo(.now, tl.getDate()) gt 0>
    <#assign css = "pro-past"/>
<#else>
    <#assign css = ""/>
</#if>
   <a href="${tl.link}" class="pro-jalon ${css}">
        <div style="padding-top: 700px;">
            <div class="pro-date">
                <div>
                    <span class="pro-day">Jour</span>
                    <span class="pro-day-more">J+${tl.startDay}</span>
                </div>
            </div>
            <div class="pro-titre">
                <span>Le <time datetime="2017-02-14">${tl.getDate()?date}</time></span>
                <h4>${tl.title}</h4>
            </div>
        </div>
    </a>
</#list>
</div>
<style>
.pro-page-detail.pro-page-detail-projet section .pro-timeline{
    position : relative;
}
</style>