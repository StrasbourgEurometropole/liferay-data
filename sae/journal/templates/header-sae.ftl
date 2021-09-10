<#if backgroundvideo.data?has_content>
  <video class="parameter-video" autoplay muted loop>
      <source src="${backgroundvideo.getData()}" type="video/mp4">
  </video>
</#if>


<style>
.page-header {
  background-image: url(${backgroundpicture.getData()}) !important;
}
</style>