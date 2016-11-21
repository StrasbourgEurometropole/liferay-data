<div class="featured-artwork">
  <div class="featured-artwork-image">
    <a href="${url.getData()}">
      <img src="${image.getData()}" />
    </a>
    <#if image.getAttribute('alt')?has_content>
      <div class="image-copyright">
          ${image.getAttribute('alt')}
      </div>
    </#if>
  </div>
  <div class="featured-artwork-detail">
      <div class="featured-artwork-collections">
         ${italicTitle.getData()}
      </div>
    <div class="featured-artwork-title">
      <a href="${url.getData()}">
        <h4>${title.getData()}</h4>
      </a>
    </div>
    <div class="featured-artwork-text">
      <div class="featured-artwork-description">
        <p>${description.getData()}</p>
      </div>
      <div class="featured-artwork-link">
        <a href="${url.getData()}"> <@liferay_ui["message"] key="read-more" /> </a>
      </div>
    </div>
  </div>
</div>