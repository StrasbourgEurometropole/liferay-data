<#assign currProject = entry.getAssetRenderer().getProject()>
<#assign firstTimeLine = currProject.getProjectTimelines()?first>

<div class="pro-timeline">

<div class="pro-jalon pro-first">
    <div>
        <div class="pro-date">
            <div>
                <#if firstTimeLine == "">
                    <span>${entry.getPublishDate()?string["dd/MM"]}</span>
                    <span class="pro-year">${entry.getPublishDate()?string["yyyy"]}</span>
                    <#else>
                    <span>${firstTimeLine.getDate()?string["dd/MM"]}</span>
                    <span class="pro-year">${firstTimeLine.getDate()?string["yyyy"]}</span>
                </#if>
            </div>
        </div>
    </div>
</div>

<#list currProject.getProjectTimelines() as timeline>

<#if dateUtil.compareTo(.now, timeline.getDate()) gt 0>
    <#assign css = "pro-past"/>
<#else>
    <#assign css = ""/>
</#if>
<#if firstTimeLine != timeline>
   <a href="${timeline.link}" class="pro-jalon ${css}">
       <div style="padding-top: 700px;">
           <div class="pro-date">
               <div>
                   <span class="pro-day">Jour</span>
                   <span class="pro-day-more">J+${timeline.startDay}</span>
               </div>
           </div>
           <div class="pro-titre">
               <span>Le <time datetime="2017-02-14">${timeline.getDate()?date}</time></span>
               <h4>${timeline.title}</h4>
           </div>
       </div>
   </a>
</#if>
</#list>
</div>
<style>
.pro-page-detail.pro-page-detail-projet section .pro-timeline{
    position : relative;
}
</style>