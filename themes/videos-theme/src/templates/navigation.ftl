<nav class="sort-pages modify-pages" id="navigation">
	<div class="menu desktop-only">
		<div class="container">
			<ul>
				<#list nav_items as nav_item>
					<#if nav_item.getName() != "Accueil">
						<#if nav_item.isSelected()>
							<li class="selected">
						<#else>
							<li>
						</#if>
								<a href="${nav_item.getURL()}" title="${nav_item.getName()}" ${nav_item.getTarget()}><span>${nav_item.getName()}</span></a>
							</li>
					</#if>
				</#list>
				<li class="search" role="button">
					<img src="${images_folder}/ui/search.png" title="Search" alt="Search" />
					<div class="search-box">
						<form class="search-form">
							<input type="text" name="search-input" class="search-input" id="search-input" placeholder="Rechercher" />
							<button class="arrow-box" id="search" type="submit" role="button">OK</button>
						</form>
					</div>
				</li>
			</ul>
		</div>
	</div>
</nav>
<script>
	var searchPageUrl = '/web${themeDisplay.getLayout().getGroup().getFriendlyURL()}/recherche/-/search/';
</script>