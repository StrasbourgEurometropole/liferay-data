<#setting locale = locale />
<div id="seu-quicklinks">
    <div class="seu-container">
        <h2 class="seu-section-title">
          <span class="seu-title">${title.getData()}</span>
      </h2>
      <div class="seu-quicklinks-list">
          <#if linkLabel.getSiblings()?has_content>
              <#list linkLabel.getSiblings() as cur_linkLabel>
                  <a href="${cur_linkLabel.getChildren()[0].getData()}" class="seu-quicklink" title="${cur_linkLabel.getData()}">
                      <span class="seu-picto">
                          <img src="${cur_linkLabel.getChildren()[1].getData()}" alt="${cur_linkLabel.getData()}">
                      </span>
                      <div class="seu-title">${cur_linkLabel.getData()}</div>
                  </a>
              </#list>
            </#if>
      </div>
      <div class="seu-btn-line">
            <a href="${allServicesLink.getFriendlyUrl()}" class="seu-btn-square seu-bordered seu-white" title="Tous les services">
                <span class="seu-flexbox">
                    <span class="seu-btn-text">${allServicesLinkLabel.getData()}</span>
                    <span class="seu-btn-arrow"></span>
                </span>
            </a>
      </div>
    </div>
</div>