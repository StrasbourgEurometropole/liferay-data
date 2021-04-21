<!-- Tablette Portrait + Mobile -->
<div class="th-top-overlay-menu">
    <div class="back back-level-1">
        <span class="title-menu-niv-1"><liferay-ui:message key="eu.strasbourg.dynamic-search-strasbourg-search" /></span>
        <span class="back-txt"><liferay-ui:message key="eu.strasbourg.dynamic-search-strasbourg-back" /></span>
    </div>
    <button data-overlay-close="th-overlay-nav" class="th-cta-menu"></button>
</div>

<div class="th-menu-niveau-2 th-search-form">
    <span class="title-search-form th-hide-tablet-p"><liferay-ui:message key="eu.strasbourg.dynamic-search-strasbourg-search" /></span>
    <form action="/" method="get" class="th-form-search">
        <input type="text" name="th-search" id="th-search" placeholder="..." class="th-input-search" />
        <button type="submit" name="th-form-submit" id="th-form-submit" class="th-form-submit"></button>

        <ul class="th-dropdown-results">
            <li><a href="#"><span>Bar</span>s & restaurants</a></li>
            <li><a href="#">Jeanne <span>Bar</span>seghian</a></li>
            <li><a href="#"><span>Bar</span>rage Vauban</a></li>
            <li><a href="#">Sainte-<span>bar</span>be</a></li>
        </ul>
    </form>
    <ul class="th-predef-tags">
        <li><a href="#"><span>Covid-19 :</span> infos sur les services publics</a></li>
        <li><a href="#">Déplacements et stationnement</a></li>
        <li><a href="#">Piscines</a></li>
        <li><a href="#">Collecte des déchets</a></li>
        <li><a href="#"><span>Covid-19 :</span> services aux professionnels</a></li>
        <li><a href="#">Démarches</a></li>
    </ul>
</div>

<div class="th-search-results">
    <p class="th-nb-results th-hide-tablet-p"><liferay-ui:message key="eu.strasbourg.dynamic-search-strasbourg-result-search" /> <span class="th-result">192</span></p>
    <p class="th-nb-results th-v-tablet-p"><liferay-ui:message key="eu.strasbourg.dynamic-search-strasbourg-result" /> <span class="th-result">192</span></p>
    <div class="th-all-results">
        <a href="#" class="th-item-result">
            <div class="th-metas-left">
                <span class="th-picto th-picto-lieu"></span>
                <span class="th-title">Le Code Bar</span>
                <p>Bars et restaurants</p>
                <span class="th-infos th-localisation">Strasbourg</span>
            </div>
            <span class="th-favoris">Ajouter à mes favoris</span>
        </a>
    </div>
</div>