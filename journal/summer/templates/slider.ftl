<!-- Slider image -->
<#setting locale = locale />
<div class="summer-slider">
  <h2>${title.getData()}</h2>
  <div class="carousel-container">
    <div class="owl-carousel">
      <#if image.getSiblings()?has_content>
        <#list image.getSiblings() as cur_image>
          <#if cur_image.getData()?? && cur_image.getData() != "">
            <div>
              <#assign fileEntryHelper = serviceLocator.findService("eu.strasbourg.utils.api.FileEntryHelperService") />
              <#assign file = fileEntryHelper.getFileEntryByRelativeURL(cur_image.getData()) />
              <#assign copyright = fileEntryHelper.getImageCopyright(file.getFileEntryId(), locale) />
              <#assign legend = fileEntryHelper.getImageLegend(file.getFileEntryId(), locale) />
              <img alt="${cur_image.getAttribute("alt")}" src="${cur_image.getData()}" class="lightbox" data-title="${legend}" />
              <div class="caption">
                <div class="legend">${legend}</div>
                <div class="copyright">&copy; ${copyright}</div>
              </div>
            </div>
          </#if>
        </#list>
      </#if>
    </div>
    <div class="prev"></div>
    <div class="next"></div>
  </div>
</div>

<style>
.summer-slider .carousel-container {
  position: relative;
}
.summer-slider .owl-carousel {
  margin-top: 20px;
}
.summer-slider .caption {
  position: absolute;
  bottom: 0;
  background: rgba(0, 0, 0, 0.8);
  width: 100%;
  font-size: 16px;
  padding: 10px 15px;
  color: white;
}

.summer-slider .caption .copyright {
  font-size: 12px;
  margin-top: 5px;
}

.summer-slider .owl-dots {
    text-align: center;
    margin-top: 10px;
}

.summer-slider .owl-dot {
    background: white;
    width: 15px;
    height: 15px;
    border-radius: 50%;
    display: inline-block;
    margin: 5px;
    border: 1px solid black;
}

.summer-slider .owl-dot.active {
    background: black;
}

.summer-slider .next, .summer-slider .prev {
  width: 40px;
  height: 40px;
  background: white;
  position: absolute;
  top: calc(50% - 48px);
  z-index: 20;
  cursor: pointer;
}

.summer-slider .prev:before, .summer-slider .next:before {
  display: block;
  content: "";
  width: 0;
  height: 0;
  margin: 5px;
  margin-left: 7px;
  border-style: solid;
  border-width: 15px 26.0px 15px 0;
  border-color: transparent #383838 transparent transparent;
}

.summer-slider .next:before {
  border-width: 15px 0 15px 26.0px;
  border-color: transparent transparent transparent #383838;
  margin-right: 7px;
}

.summer-slider img.lightbox {
  cursor: zoom-in;
}

.summer-slider .next {
  right: 0;
}

.mfp-image-holder .mfp-content, .mfp-img {
  padding: 0 !important;
  background-color: transparent;
}

.mfp-bottom-bar {
    margin-top: -40px;
    width: 100%;
    background: rgba(0, 0, 0, 0.8);
    bottom: 0;
    font-size: 15px;
    padding: 10px 15px;
}

</style>