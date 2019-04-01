<%@ include file="/search-asset-init.jsp"%>

<!-- BLOC FACETTES DE TRI -->
	<div class="ops-facette-checkbox ops-dropdown">
		<a href="#" class="selected"></a> <a href="#">Mois</a>
		<ul>
			<li><label>Janvier<input type="checkbox" id="ops-test-janv" name="check" value="Janvier" /><span></span></label></li>
			<li><label>Février<input type="checkbox" id="ops-test-fev" name="check" value="Février" /><span></span></label></li>
			<li><label>Mars<input type="checkbox" id="ops-test-mars" name="check" value="Mars" /><span></span></label></li>
		</ul>
	</div>


	<div class="ops-facette-checkbox ops-dropdown">
		<a href="#" class="selected"></a> <a href="#">Abonnements</a>
		<ul>
			<li><label>Mois<input type="checkbox" id="ops-test-abo2" name="check01" value="Mois" /><span></span></label></li>
			<li><label>Annuel<input type="checkbox" id="ops-test-abo3" name="check01" value="Annuel" /><span></span></label></li>
			<li><label>Hivernal<input type="checkbox" id="ops-test-abo4" name="check01" value="Hivernal" /><span></span></label></li>
			<li><label>Estival<input type="checkbox" id="ops-test-abo5" name="check01" value="Estival" /><span></span></label></li>
		</ul>
	</div>


	<div class="ops-facette-checkbox ops-dropdown">
		<a href="#" class="selected"></a> <a href="#">Typologie</a>
		<ul>
			<c:set var="themeVocabulary" value="${vocabularyAccessor.eventThemes}" />
			<c:forEach items="${dc.getDropdownRootCategories(themeVocabulary)}" var="category">
			
				<li><label>${category.getTitle(locale)}<input type="checkbox" 
				id="${category.categoryId}" name="<portlet:namespace />vocabulary_0" value="${category.categoryId}" /><span></span></label></li>
				
				<c:if test="${fn:contains(dc.filterCategoriesIdsString, category.categoryId)}">
					<c:set var="themeId" value="${category.categoryId}" scope="page"/>
				</c:if>
				
			</c:forEach>
		</ul>
	</div> 
	
	<aui:input type="hidden" name="vocabulariesCount" value="1" />

	<!-- FACETTE RECHERCHE  -->
	<div class="ops-facette-search">
		<label for="ops-intern-search" aria-hidden="true">Rechercher</label> <input type="text" id="ops-intern-search" value="${dc.keywords}" name="<portlet:namespace />keywords" placeholder="<liferay-ui:message key="please-enter-keyword" />" /> <span class="icon-ico-search"></span>
	</div>


	<!-- SOUMETTRE LE FORMULAIRE -->
	<div class="ops-button-submit">
		<input type="submit" class="ops-btn" value="Envoyer" />
	</div>

	
<script>

$(document).ready(function () {
	
	<c:if test="${!empty themeId}">
		$('#${themeId}').click();
	</c:if>
    
});
	
</script>