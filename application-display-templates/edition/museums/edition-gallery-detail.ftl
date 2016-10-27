<!-- Détail galerie d'éditions (ADT) - ${entry.getTitle(locale)} -->
<div class="entity-detail edition-gallery-detail">
  <div class="entity-images">
    <div class="entity-images-main">
      <img src="${entry.getImageURL()}" alt="" class="lightbox">
      <#if entry.getImageCopyright(locale)?has_content>
        <div class="entity-images-main-copyright">
            ${entry.getImageCopyright(locale)}
        </div>
      </#if>
    </div>
  </div>
  <div class="entity-info">
    <div class="entity-title">
      <h1>${entry.getTitle(locale)}</h1>
    </div>
    <div class="gallery-edition-count">
      ${entry.getPublishedEditions()?size} <@liferay_ui["message"] key="eu.edition.editions" />
    </div>
    <div class="entity-description">
      ${entry.getDescription(locale)}
    </div>
  </div>
</div>

<!-- Editions de la galerie -->
<#if entry.getPublishedEditions()?has_content>
  <h3 class="entity-detail-children-title"><@liferay_ui["message"] key="eu.edition.gallery-editions" /></h3>
  <div class="entity-detail-children">
    <#list entry.getPublishedEditions() as edition>
      <#assign targetFriendlyURL = renderRequest.getAttribute("targetFriendlyURL")!"" />
      <div class="entity-detail-child">
        <div class="entity-detail-child-image">
          <a href="${targetFriendlyURL}/-/entity/id/${edition.getEditionId()}">
            <img src="${edition.getImageURL()}">
          </a>
        </div>
        <div class="entity-detail-child-info">
          <div class="entity-detail-child-title">
            <a href="${targetFriendlyURL}/-/entity/id/${edition.getEditionId()}">
              <h4>${edition.getTitle(locale)}</h4>
            </a>
          </div>
        </div>
      </div>
    </#list>
  </div>
</#if>
