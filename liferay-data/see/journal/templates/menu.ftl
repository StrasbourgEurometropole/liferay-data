<!-- Menu -->
<#setting locale = locale />
<header class="header header-black">
  <div class="header-wrapper">
    <div class="container">
      <div class="col-sm-2 col-xs-12 navigation-header">
        <a href="#" class="logo">
          <img src="${image.getData()}" alt="Semaine de l'Entrepreneur Européen" class="retina-hide">
          <img src="${retinaImage.getData()}" alt="Semaine de l'Entrepreneur Européen" class="retina-show">
        </a>
        <button class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navigation" aria-expanded="false" aria-controls="navigation">
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
      </div>
      <div class="col-sm-10 col-xs-12 navigation-container">
        <div id="navigation" class="navbar-collapse collapse">
          <ul class="navigation-list pull-left light-text">
            <#if label.getSiblings()?has_content>
              <#list label.getSiblings() as cur_label>
                  <li class="navigation-item">
                      <a href="#${cur_label.getChildren()[0].getData()}" class="navigation-link">${cur_label.getData()}</a>
                  </li>
              </#list>
            </#if>
          </ul>
          <#if file.getData()?has_content>
            <a href="${file.getData()}" class="pull-right buy-btn">${downloadLabel.getData()}</a>
          </#if>
        </div>
      </div>
    </div>
  </div>
</header>