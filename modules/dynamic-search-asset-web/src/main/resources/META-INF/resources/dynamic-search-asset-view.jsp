<%@ include file="/dynamic-search-asset-init.jsp" %>

<!-- Recherche dynamique -->
<div class="pro-wrapper-search-top">
    <div class="container pro-box-search">
        <div class="pro-wrapper-title-search">
            <span class="pro-rechercher">Rechercher</span>
            <span class="icon-ico-close"></span>
        </div>
        <form method="GET" action="page-search.html">
            <label for="pro-search" style="display: none;">Rechercher...</label>
            <input type="text" name="s" id="pro-search" placeholder="Effectuer une recherche...">
            <button type="submit" aria-label="Bouton de recherche"><span class="icon-ico-search"></span></button>
        </form>
        <div class="pro-wrapper-top-listing">
            <div class="pro-sort pro-dropdown">
                <a href="#">Type</a>
                <ul>
                    <li><a href="#">Evènement</a></li>
                    <li><a href="#">Participation</a></li>
                    <li><a href="#">Idée</a></li>
                    <li><a href="#">Actualité</a></li>
                    <li><a href="#">Page standard</a></li>
                    <li><a href="#">Pétition</a></li>
                </ul>
            </div>
            <div class="pro-sort pro-dropdown">
                <a href="#">Tri</a>
                <ul>
                    <li><a href="#">A-Z</a></li>
                    <li><a href="#">Z-1</a></li>
                </ul>
            </div>
        </div>
        <div class="pro-wrapper-search row" data-egalize="> *">
            
			

        </div>
    </div>
</div>