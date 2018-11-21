<%@ include file="/dynamic-search-asset-init.jsp" %>

<portlet:resourceURL id="searchSubmit" var="searchSubmitURL">
</portlet:resourceURL>

<!-- Portlet de recherche dynamique -->
<div class="pro-wrapper-search-top">
    <div class="container pro-box-search">
    	
    	<!-- Icone de fermeture -->
        <div class="pro-wrapper-title-search">
            <span class="pro-rechercher">Rechercher</span>
            <span class="icon-ico-close"></span>
        </div>
        
        <!-- CHAMP : Saisie de la recherche -->
        <form>
            <label for="pro-search" style="display: none;">Rechercher...</label>
            <input type="text" name="dynamic-search-keywords" id="pro-search" placeholder="Effectuer une recherche...">
            <button id="dynamic-search-submit" type="submit" aria-label="Bouton de recherche"><span class="icon-ico-search"></span></button>
        </form>
        
        <!-- CHAMPS : Filtres et tris de la recherche -->
        <div class="pro-wrapper-top-listing">
        
        	<!-- CHAMP : Filtres des types d'entite -->
            <div class="pro-sort pro-dropdown">
                <a href="#" title="Voir toutes les types d'entite">Types</a>
                <fieldset class="pro-checkbox">
                    <legend aria-hidden="true" class="hide">Choix des types recherches</legend>
                    
                    <c:forEach var="className" varStatus="status" items="${classNames}">
	                    <div>
	                        <input type="checkbox" name="zone_vdl" id="dynamic_search_type_${status.index}" checked value="${className}">
	                        <label for="dynamic_search_type_${status.index}"><liferay-ui:message key="${className}" /></label>
	                    </div>
	                </c:forEach>
	                
                </fieldset>
            </div>
            
            <!-- CHAMP : Tri des entites -->
            <!-- <div class="pro-sort pro-dropdown">
                <a href="#">Tri</a>
                <ul>
                    <li><a href="#">A-Z</a></li>
                    <li><a href="#">Z-1</a></li>
                </ul>
            </div> -->
            
        </div>
        
        <!-- Liste des resultats -->
        <div class="pro-wrapper-search row" id="dynamic-search-asset-results" data-egalize="> *">
            
			

        </div>
    </div>
</div>

<liferay-util:html-top>
	<script>
		var homeURL = '${homeURL}';
		var porletNamespace = '<portlet:namespace/>';
		var dynamicSearch = ${dynamicSearch}
		
		var searchSubmitURL = '${searchSubmitURL}';
	</script>
</liferay-util:html-top>

<liferay-util:html-bottom>
	<script src="/o/dynamicsearchassetweb/js/placit.js"></script>
</liferay-util:html-bottom>