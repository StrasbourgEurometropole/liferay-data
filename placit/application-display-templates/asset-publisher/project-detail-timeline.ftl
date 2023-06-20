<!-- DETAIL D'UN PROJET - TIMELINE -->

<#assign currProject = entry.getAssetRenderer().getProject()>

<#assign timelines = currProject.getProjectTimelines()>

<#if timelines?first??>
  <#assign firstTimeLine = currProject.getProjectTimelines()?first>
</#if>

<div class="pro-timeline-content">

  <div class="pro-jalon pro-first">
      <div>
          <div class="pro-date">
                  <#if !firstTimeLine??>
                    <span>${entry.getPublishDate()?string["dd/MM"]}</span>
                    <span class="pro-year">${entry.getPublishDate()?string["yyyy"]}</span>
                  <#else>
                    <span>${firstTimeLine.getDate()?string["dd/MM"]}</span>
                    <span class="pro-year">${firstTimeLine.getDate()?string[firstTimeLine.getFreeMarkerFormatDate()]}</span>
                  </#if>
          </div>
      </div>
  </div>

  <#if firstTimeLine??>
    <#list currProject.getProjectTimelines() as timeline>

      <#if dateUtil.compareTo(.now, timeline.getDate()) gt 0>
          <#assign css = "pro-past"/>
      <#else>
          <#assign css = ""/>
      </#if>

      <#if firstTimeLine != timeline>
         <a <#if timeline.link?has_content> href="${timeline.link}" </#if> class="pro-jalon ${css}">
             <div>
                 <div class="pro-date">
                  <span class="pro-day">Jour</span>
                  <span class="pro-day-more">+${timeline.startDay}</span>
                 </div>
                 <div class="pro-titre">
                     <span>Le <time datetime="2017-02-14">${timeline.getDate()?string[timeline.getFreeMarkerFormatDate()]}</time></span>
                     <h4>${timeline.title}</h4>
                 </div>
             </div>
         </a>
      </#if>

    </#list>
   </#if> 


</div>
<style>
.pro-page-detail.pro-page-detail-projet section .pro-timeline{
    position : relative;
}
</style>