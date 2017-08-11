<!-- Header -->
<#setting locale = locale />
<section id="hero" class="hero-section bg1 bg-cover window-height light-text">
  <ul class="socials-nav">
    <#if twitterLink?has_content && twitterLink.getData()?has_content>
      <li class="socials-nav-item"><a href="${twitterLink.getData()}" target="_blank"><span class="fa fa-twitter"></span></a></li>
    </#if>
    <#if facebookLink?has_content && facebookLink.getData()?has_content>
      <li class="socials-nav-item"><a href="${facebookLink.getData()}" target="_blank"><span class="fa fa-facebook"></span></a></li>
    </#if>
    <#if instagramLink?has_content && instagramLink.getData()?has_content>
      <li class="socials-nav-item"><a href="${instagramLink.getData()}" target="_blank"><span class="fa fa-instagram"></span></a></li>
    </#if>
    <#if youtubeLink?has_content && youtubeLink.getData()?has_content>
      <li class="socials-nav-item"><a href="${youtubeLink.getData()}" target="_blank"><span class="fa fa-youtube"></span></a></li>
    </#if>
    <#if soundCloudLink?has_content && soundCloudLink.getData()?has_content>
      <li class="socials-nav-item"><a href="${soundCloudLink.getData()}" target="_blank"><span class="fa fa-soundcloud"></span></a></li>
    </#if>
    <#if linkedInLink?has_content && linkedInLink.getData()?has_content>
      <li class="socials-nav-item"><a href="${linkedInLink.getData()}" target="_blank"><span class="fa fa-linkedin"></span></a></li>
    </#if>
  </ul>
  <div class="heading-block centered-block align-center">
    <div class="container">
      <h5 class="heading-alt" style="margin-bottom: 8px;"><span class="fa fa-calendar-o base-clr-txt"></span>${date.getData()}<span class="fa fa-map-marker base-clr-txt" style="margin-left: 14px;"></span>${place.getData()}</h5>
      <h1 class="extra-heading">${title.getData()}</h1>
      <h6 class="thin base-font">${subtitle.getData()}</h6>
    </div>
  </div>
</section>
<style>
    .bg1 {
        background-image: url(${backgroundImage.getData()?split('?')[0]});
    }
</style>