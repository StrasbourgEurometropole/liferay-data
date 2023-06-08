<!-- Vignette fichier -->
<#setting locale = locale />
<#assign FileEntryHelper = serviceLocator.findService("eu.strasbourg.utils.api.FileEntryHelperService") />
<div class="entity-thumbnail news-tumbnail">
  <div class="entity-thumbnail-image">
    <a href="${FileEntryHelper.getFileEntryURL(entry.getFileEntryId())}" download>
      <img src="">
    </a>
  </div>
  <div class="entity-thumbnail-info">
    <div class="entity-thumbnail-title">
      <a href="${FileEntryHelper.getFileEntryURL(entry.getFileEntryId())}" download>
        <h4>${entry.getTitle()} </h4><div class="file-type-and-size">${entry.getExtension()?upper_case} — ${FileEntryHelper.getReadableFileEntrySize(entry.getFileEntryId(), locale)}</div>
      </a>
    </div>
  </div>
</div>
