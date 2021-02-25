<!-- Galerie -->
<#setting locale = locale />
<section class="section align-center">
  <div class="container">
    <span class="icon section-icon icon-multimedia-08"></span>
    <h3>${title.getData()}</h3>
    <p class="text-alt">${subtitle.getData()}</p>
    <br/>
    <img src="${mainImage.getData()}">
    <p>
      ${description.getData()}
    </p>
    <div class="gallery masonry">
      <#if image.getSiblings()?has_content>
        <#list image.getSiblings() as cur_image>
          <span class="masonry-item">
            <a href="#" class="gallery-thumb-link" data-modal-link="gallery-${cur_image?index}">
              <img src="${cur_image.getData()}" alt="">
            </a>
            <div class="modal-window" data-modal="gallery-${cur_image?index}">
              <div class="modal-box medium animated" data-animation="zoomIn" data-duration="700">
                <span class="close-btn icon icon-office-52"></span>
                <h5 class="heading-alt">${cur_image.getChildren()[0].getData()}</h5>
                <br/>
                <img src="${cur_image.getData()}" class="full-width-img" alt="${cur_image.getChildren()[0].getData()}">
              </div>
            </div>
          </span>
        </#list>
      </#if>
    </div>
  </div>
</section>