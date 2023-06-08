<!--Section Territoire-->
<#setting locale = locale />
<section id="seu-territory">
    <div class="seu-container">
        <h2 class="seu-section-title">
            <span class="seu-title">${title.getData()}</span>
        </h2>
        <div class="seu-links">
            <#if linkLabel.getSiblings()?has_content>
              <#list linkLabel.getSiblings() as cur_linkLabel>
                    <a href="${cur_linkLabel.getChildren()[0].getFriendlyUrl()}" class="seu-btn-square seu-bordered seu-grey" title="${cur_linkLabel.getData()}">
                        <span class="seu-flexbox">            
                            <span class="seu-btn-text">${cur_linkLabel.getData()}</span>
                            <span class="seu-btn-arrow"></span>
                        </span>
                    </a>
              </#list>
            </#if>
        </div>
        <div class="seu-territories">
            <div class="seu-territory seu-left" style="background-image: url(/o/strasbourg-theme/images/test/quartier.jpg);">
                <div class="seu-text">
                    <div class="seu-suptitle">${districtSublabel.getData()}</div>
                    <div class="seu-title">${districtLabel.getData()}</div>
                    <form action="post">
                        <label for="quartier" class="sr-only">Sélectionner votre quartier</label>
                        <select name="quartier" id="quartier" class="seu-toCustomSelect" onchange="this.options[this.selectedIndex].value && (window.location = this.options[this.selectedIndex].value);">
                            <option value="#seu-territory">Sélectionnez votre quartier</option>
                            <#if district.getSiblings()?has_content>
                              <#list district.getSiblings() as cur_district>
                                    <option value="${cur_district.getChildren()[0].getFriendlyUrl()}">${cur_district.getData()}</option>
                                
                              </#list>
                            </#if>
                        </select>
                    </form>
                </div>
                <div class="seu-map">
                    <img src="/o/strasbourg-theme/images/test/map_quartier.png" alt="">
                </div>
            </div>
            <div class="seu-territory seu-right" style="background-image: url(/o/strasbourg-theme/images/test/commune.jpg);">
                <div class="seu-text">
                    <div class="seu-suptitle">${citySublabel.getData()}</div>
                    <div class="seu-title">${cityLabel.getData()}</div>
                    <form action="post">
                        <label for="commune" class="sr-only">Sélectionner votre commune</label>
                        <select name="commune" id="commune" class="seu-toCustomSelect" onchange="this.options[this.selectedIndex].value && (window.location = this.options[this.selectedIndex].value);">
                            <option value="#seu-territory">Sélectionnez votre commune</option>
                            <#if city.getSiblings()?has_content>
                              <#list city.getSiblings() as cur_city>
                                    <option value="${cur_city.getChildren()[0].getFriendlyUrl()}">${cur_city.getData()}</option>
                                
                              </#list>
                            </#if>
                        </select>
                    </form>
                </div>
                <div class="seu-map">
                    <img src="/o/strasbourg-theme/images/test/map_commune.png" alt="">
                </div>
            </div>
        </div>
    </div>
</section>