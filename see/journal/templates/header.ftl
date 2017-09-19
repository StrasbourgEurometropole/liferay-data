<!-- Header -->
<#setting locale = locale />
<section id="hero" class="hero-section bg1 bg-cover window-height light-text">
  <ul class="socials-nav">
    <li class="socials-nav-item"><a href="${twitterLink.getData()}"><span class="fa fa-twitter"></span></a></li>
    <li class="socials-nav-item"><a href="${facebookLink.getData()}"><span class="fa fa-facebook"></span></a></li>
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
        background-image: url(${backgroundImage.getData()});
    }
</style>