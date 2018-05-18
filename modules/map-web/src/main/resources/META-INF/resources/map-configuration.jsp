<%@ include file="./map-init.jsp"%>

<liferay-portlet:actionURL portletConfiguration="true"
	var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="true"
	var="configurationRenderURL" />

<div class="container-fluid-1280 main-content-body">
	<aui:form action="${configurationActionURL}" method="post" name="fm">

		<aui:input name="cmd" type="hidden" value="update" />
		
		<aui:fieldset-group markupView="lexicon">

			<!-- Mode -->
			<div class="modeSelection" style="margin-left: 20px;">
				<aui:input type="radio" name="mode" value="normal" label="no-mode" checked="${not widgetMod and not defaultConfig or empty widgetMod and empty defaultConfig}"/>
				<aui:input type="radio" name="mode" value="widget" label="widget-mode" checked="${widgetMod}"/>
				<aui:input type="radio" name="mode" value="aroundme" label="aroundme-mode" checked="${defaultConfig}"/>
			</div>
			
			<!-- Type de contenu -->
			<aui:fieldset collapsed="true" collapsible="true"
					label="type-contenu">
				
				<!-- Lieux -->
				<aui:input type="checkbox" name="typeContenu" value="eu.strasbourg.service.place.model.Place" label="eu.places" 
					checked="${fn:contains(typesContenu, 'eu.strasbourg.service.place.model.Place') || !hasConfig}" ></aui:input>
				<!-- Evénements -->
				<aui:input type="checkbox" name="typeContenu" value="eu.strasbourg.service.agenda.model.Event" label="eu.events" 
					checked="${fn:contains(typesContenu, 'eu.strasbourg.service.agenda.model.Event') || !hasConfig}" ></aui:input>
			
			</aui:fieldset>
			
			<!-- Carto normale -->
			<aui:fieldset collapsed="true" collapsible="true"
					label="filters-and-prefilters" cssClass="normalMode">
					
				<!-- Préfiltres -->
				<div>
					<label><liferay-ui:message key="prefilter-label" /></label>
					<p>
						<liferay-ui:message key="prefilter-help" />
					</p>
					<p>
						<liferay-ui:asset-categories-selector
							hiddenInput="prefilterCategoriesIds"
							curCategoryIds="${prefilterCategoriesIds}" />
					</p><br>
				</div>
			
				<!-- Filtres -->
				<div>
					<label><liferay-ui:message key="filters-label"/></label>
					<p>
						<liferay-ui:message key="filters-help" />
					</p>
					<p>
						<liferay-ui:asset-categories-selector
							hiddenInput="categoriesIds"
							curCategoryIds="${categoriesIds}" />
					</p>
					<label><liferay-ui:message key="default-filters-label" /></label>
					<p>
						<liferay-ui:message key="default-filters-help" />
					</p>
					<p>
						<liferay-ui:asset-categories-selector
							hiddenInput="categoriesDefaultsIds"
							curCategoryIds="${categoriesDefaultsIds}" />
					</p><br>
				</div>

				<!-- Filtre sur le quartier de l'utilisateur -->
				<div>
					<aui:input type="checkbox" name="districtUser" value="${districtUser}" label="district-user" />
				</div>
			</aui:fieldset>
			
			<!-- Affichage -->
			<aui:fieldset collapsed="true" collapsible="true" label="display-label">

				<!-- Choix de l'affichage de la zone de configuration -->
				<div>
					<aui:input type="checkbox" name="showConfig" value="${showConfig || !hasConfig}" label="show-config" />
				</div>
				
				<!-- Choix de l'affichage de la liste -->
				<div>
					<aui:input type="checkbox" name="showList" value="${showList || !hasConfig}" label="show-list" />
				</div>
			
				<!-- Choix du site pour la cible des lien -->
				<aui:select name="groupId" label="detail-target-site">
					<c:forEach var="site" items="${sites}">
						<aui:option value="${site.groupId}" selected="${site.groupId eq selectedGroupId}">${site.name}</aui:option>
					</c:forEach>
				</aui:select>
				
				<!-- Choix de l'ouverture d'un lien dans un nouvel onglet ou pas  -->
				<div>
					<aui:input type="checkbox" name="openInNewTab" value="${openInNewTab}" label="new-tab" />
				</div>
				
			</aui:fieldset>
			
			<!-- MonStrabourg -->
			<aui:fieldset collapsed="true" collapsible="true"
					label="mon-strasbourg" cssClass="monStrasbourgMode">

				<!-- Mode widget -->
				<div class="widgetMode">

					<!-- Texte d'intro mode widget -->
					<div>
						<aui:input type="text" name="widgetIntro" value="${widgetIntro}" label="widget-intro" />
					</div>

					<!-- Label bouton mode widget -->
					<div>
						<aui:input type="text" name="widgetLink" value="${widgetLink}" label="widget-link" />
					</div>

				</div>

				<!-- Mode autour de moi -->
				<div class="aroundMeMode">

					<!-- Centres d'intérêts -->
					<label><liferay-ui:message key="interest-choice"/></label>
					<br>
					<br>
					<div>
						<table style="text-align: center; width: 100%;">
							<tr style="border-bottom: solid 3px;">
								<td width="100px"><liferay-ui:message key="interest-choice-disabled-help"/></td>
								<td width="100px"><liferay-ui:message key="interest-choice-help"/></td>
								<td width="100px"><liferay-ui:message key="interest-choice-default-help" /></td>
								<td></td>
							</tr>
							<c:forEach var="interest" items="${interests}" varStatus="intStatus">
								<c:if test="${intStatus.count % 2 == 0}">
									<tr>
								</c:if>
								<c:if test="${intStatus.count % 2 != 0}">
									<tr style="background-color: #f9f9f9">
								</c:if>
								<td style="padding-top: 10px">
									<aui:input type="radio" name="interestStatus${interest.interestId}" value="disabled" label=""
											   checked="${!fn:contains(interestsIds, interest.interestId) || !fn:contains(interestsDefaultsIds, interest.interestId) || !hasConfig}" ></aui:input>
								</td>
								<td style="padding-top: 10px">
									<aui:input type="radio" name="interestStatus${interest.interestId}" value="unchecked" label=""
											   checked="${fn:contains(interestsIds, interest.interestId)}" ></aui:input>
								</td>
								<td style="padding-top: 10px">
									<aui:input type="radio" name="interestStatus${interest.interestId}" value="checked" label=""
											   checked="${fn:contains(interestsDefaultsIds, interest.interestId)}" ></aui:input>
								</td>
								<td style="text-align: left">${interest.getTitle(locale)}</td>
								</tr>

							</c:forEach>
						</table>
					</div>

					<p>
						<!-- Affichage des favoris par défaut" -->
						<div>
							<aui:input type="checkbox" name="showFavorites" value="${showFavorites || !hasConfig}" label="show-favorites" />
						</div>

					</p>
				</div>

				<script>
					var refreshConfigDisplay = function() {
                        var mode = $('.modeSelection input[type=radio]:checked').val();
                        if (mode === 'widget') {
                            $('.monStrasbourgMode').show();
                            $('.widgetMode').show();
                            $('.aroundMeMode').hide();
                            $('.normalMode').hide();
                        } else if (mode == 'aroundme') {
                            $('.monStrasbourgMode').show();
                            $('.widgetMode').hide();
                            $('.aroundMeMode').show();
                            $('.normalMode').hide();
                        } else {
                            $('.monStrasbourgMode').hide();
                            $('.widgetMode').hide();
                            $('.aroundMeMode').hide();
                            $('.normalMode').show();
                        }
					}
					$('.modeSelection input[type=radio]').on('change', function() {
                        refreshConfigDisplay();
					})
					$(function() {
                        refreshConfigDisplay();
					})
				</script>
			</aui:fieldset>		

		</aui:fieldset-group>
		
		<aui:button-row>
			<aui:button type="submit"></aui:button>
		</aui:button-row>
	</aui:form>
</div>