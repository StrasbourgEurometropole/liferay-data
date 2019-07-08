<%@ include file="./map-init.jsp"%>

<liferay-portlet:actionURL portletConfiguration="true"
	var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="true"
	var="configurationRenderURL" />
<aui:form action="${configurationActionURL}" method="post" name="fm">

    <aui:input name="cmd" type="hidden" value="update" />

    <div class="portlet-configuration-body-content">
        <div class="container-fluid-1280">

            <aui:fieldset-group markupView="lexicon">

                <!-- Mode -->
                <div class="modeSelection" style="margin-left: 20px;">
                    <aui:input type="radio" name="mode" value="normal" label="no-mode" checked="${not widgetMod and not defaultConfig or empty widgetMod and empty defaultConfig}"/>
                    <aui:input type="radio" name="mode" value="widget" label="widget-mode" checked="${widgetMod}"/>
                    <aui:input type="radio" name="mode" value="aroundme" label="aroundme-mode" checked="${defaultConfig}"/>
                </div>

                <!-- Type de contenu -->
                <aui:fieldset collapsed="true" collapsible="true"
                        label="type-contenu" cssClass="noWidgetMode" >

                    <!-- Lieux -->
                    <aui:input type="checkbox" name="typeContenu" id="placeContentType" value="eu.strasbourg.service.place.model.Place" label="eu.places"
                        checked="${fn:contains(typesContenu, 'eu.strasbourg.service.place.model.Place') || !hasConfig}" ></aui:input>

                    <!-- Evenements -->
                    <aui:input type="checkbox" name="typeContenu" id="eventContentType" value="eu.strasbourg.service.agenda.model.Event" label="eu.events"
                        checked="${fn:contains(typesContenu, 'eu.strasbourg.service.agenda.model.Event') || !hasConfig}" cssClass="typeEvent"></aui:input>

                    <!-- Carto normale et page autour de moi -->
                    <div class="eventExplanation">
                        <aui:input name="eventExplanationMap" value="${eventExplanation}" localized="true" type="editor" label="event-explanation-text" />
                    </div>

                </aui:fieldset>

                <!-- Affichage -->
                <aui:fieldset collapsed="true" collapsible="true" label="display-label">

                    <div class="noWidgetMode">
                        <!-- Choix de l'affichage de la zone de configuration -->
                        <div>
                            <aui:input type="checkbox" name="showConfig" value="${showConfig || !hasConfig}" label="show-config" />
                        </div>

                        <!-- Choix de l'affichage de la liste -->
                        <div>
                            <aui:input type="checkbox" name="showList" value="${showList || !hasConfig}" label="show-list" />
                        </div>
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

                    <!-- Niveau de zoom de la carte -->
                    <div>
                        <aui:input type="text" name="zoom" value="${zoom}" label="zoom" />
                    </div>

                    <!-- Cadrage de la carte -->
                    <div>
                        <label><liferay-ui:message key="cadrage"/></label>
                        <aui:input type="text" name="cadrageX" value="${cadrageX}" label="X" />
                        <aui:input type="text" name="cadrageY" value="${cadrageY}" label="Y" />
                    </div>

                    <!-- Choix de l'affichage des pictos dans la configuration -->
                    <div>
                        <aui:input type="checkbox" name="showPictos" value="${showPictos || hasConfig}" label="show-pictos" />
                    </div>
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
                                                   checked="${!fn:contains(interestsIds, interest.interestId) || !hasConfig}" ></aui:input>
                                    </td>
                                    <td style="padding-top: 10px">
                                        <aui:input type="radio" name="interestStatus${interest.interestId}" value="unchecked" label=""
                                                   checked="${fn:contains(interestsIds, interest.interestId)}" ></aui:input>
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
                </aui:fieldset>

                <!-- Info trafic -->
                <aui:fieldset collapsed="true" collapsible="true"
                        label="traffic" cssClass="noWidgetMode infoTraffic">

                    <p>
                        <!-- Affichage de l'info trafic -->
                        <div>
                            <aui:input type="checkbox" name="showTraffic" value="${showTraffic || !hasConfig}" label="show-traffic" />
                        </div>

                    </p>

                    <div class="infoTrafficChecked">
                        <!-- Mode widget -->
                        <div class="normalMode">

                            <!-- Choix de la catégorie qui affichera l'info trafic -->
                            <label><liferay-ui:message key="category-link" /></label>
                            <p>
                                <div id="categorySelectorLabel"></div>
                                <div id="categorySelector"></div>
                                <aui:input type="hidden" name="linkCategoryId" />
                            </p>

                        </div>

                        <!-- Mode autour de moi -->
                        <div class="aroundMeMode">

                            <!-- Choix du CI qui affichera l'info trafic -->
                            <label><liferay-ui:message key="interest-link" /></label>

                            <select class="toCustomSelect" id="linkInterestId" name="<portlet:namespace />linkInterestId">
                                <aui:option value=""></aui:option>
                                <c:forEach var="interest" items="${interests}" varStatus="intStatus">
                                    <c:choose>
                                        <c:when test="${interest.interestId == linkInterestId}">
                                            <aui:option value="${interest.interestId}" selected="true" >
                                                ${interest.getTitle(locale)}
                                            </aui:option>
                                        </c:when>
                                        <c:otherwise>
                                            <aui:option value="${interest.interestId}" >
                                                ${interest.getTitle(locale)}
                                            </aui:option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>

                        </div>
                    </div>

                </aui:fieldset>
                
                <!-- Transports -->
                <aui:fieldset collapsed="true" collapsible="true"
                        label="transports" cssClass="noWidgetMode transports">

                    <p>
                        <!-- Affichage de l'info trafic -->
                        <div>
                            <aui:input type="checkbox" name="showTransports" value="${showTransports || !hasConfig}" label="show-transports" />
                        </div>

                    </p>

                    <div class="transportsChecked">
                        <!-- Mode widget -->
                        <div class="normalMode">
							
                            <!-- Choix de la categorie qui affichera les transports -->
                            <label><liferay-ui:message key="transports-category-link" /></label>
                            <p>
                                <div id="transportsLinkCategorySelectorLabel"></div>
                                <div id="transportsLinkCategorySelector"></div>
                                <aui:input type="hidden" name="transportsLinkCategoryId" />
                            </p>

                        </div>

                        <!-- Mode autour de moi -->
                        <div class="aroundMeMode">

                            <!-- Choix du CI qui affichera l'info trafic -->
                            <label><liferay-ui:message key="transports-interest-link" /></label>

                            <select class="toCustomSelect" id="transportsLinkInterestId" name="<portlet:namespace />transportsLinkInterestId">
                                <aui:option value=""></aui:option>
                                <c:forEach var="interest" items="${interests}" varStatus="intStatus">
                                    <c:choose>
                                        <c:when test="${interest.interestId == transportsLinkInterestId}">
                                            <aui:option value="${interest.interestId}" selected="true" >
                                                ${interest.getTitle(locale)}
                                            </aui:option>
                                        </c:when>
                                        <c:otherwise>
                                            <aui:option value="${interest.interestId}" >
                                                ${interest.getTitle(locale)}
                                            </aui:option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>

                        </div>
                    </div>
                    
                </aui:fieldset>
                
                <script>
                    var refreshConfigDisplay = function() {
                       var mode = $('.modeSelection input[type=radio]:checked').val();
                       if (mode === 'widget') {
                           $('.monStrasbourgMode').show();
                           $('.widgetMode').show();
                           $('.aroundMeMode').hide();
                           $('.normalMode').hide();
                           $('.noWidgetMode').hide();
                       } else if (mode == 'aroundme') {
                           $('.monStrasbourgMode').show();
                           $('.widgetMode').hide();
                           $('.aroundMeMode').show();
                           $('.normalMode').hide();
                           $('.noWidgetMode').show();
                       } else {
                           $('.monStrasbourgMode').hide();
                           $('.widgetMode').hide();
                           $('.aroundMeMode').hide();
                           $('.normalMode').show();
                           $('.noWidgetMode').show();
                       }
                       if ($('.typeEvent').is(":checked")) {
                           $('.eventExplanation').show();
                       } else {
                           $('.eventExplanation').hide();
                       }
                    }

                    var refreshConfigTrafficDisplay = function() {
                       if ($('.infoTraffic input[type=checkbox]').is(":checked")) {
                           $('.infoTrafficChecked').show();
                       } else {
                           $('.infoTrafficChecked').hide();
                       }
                    }
                    var refreshConfigTransportsDisplay = function() {
                        if ($('.transports input[type=checkbox]').is(":checked")) {
                            $('.transportsChecked').show();
                        } else {
                            $('.transportsChecked').hide();
                        }
                     }
                    $('.modeSelection input[type=radio]').on('change', function() {
                        refreshConfigDisplay();
                    })
                    $('.typeEvent').on('change', function() {
                       if ($(this).is(":checked")) {
                           $('.eventExplanation').show();
                       } else {
                           $('.eventExplanation').hide();
                       }
                    })
                    $('.infoTraffic input[type=checkbox]').on('change', function() {
                        refreshConfigTrafficDisplay();
                    })
                    $('.transports input[type=checkbox]').on('change', function() {
                        refreshConfigTransportsDisplay();
                    })
                    $(function() {
                        refreshConfigDisplay();
                        refreshConfigTrafficDisplay();
                        refreshConfigTransportsDisplay();
                    })
                </script>

            </aui:fieldset-group>
        </div>
    </div>

    <aui:button-row>
        <aui:button type="submit"></aui:button>
    </aui:button-row>
</aui:form>

<aui:script use="liferay-asset-categories-selector">
	new Liferay.AssetCategoriesSelector(
		{
			curEntryIds: "${linkCategoryId}",
			curEntries: "${categoryTitle}",
			hiddenInput: "#<portlet:namespace />linkCategoryId",
			contentBox: "#categorySelector",
			label: "<liferay-ui:message key='category' />",
			labelNode: "#categorySelectorLabel",
			singleSelect: true,
			vocabularyGroupIds: ${themeDisplay.companyGroupId},
			vocabularyIds: "${vocabularies}"
		}
	).render();
	
	new Liferay.AssetCategoriesSelector(
		{
			curEntryIds: "${transportsLinkCategoryId}",
			curEntries: "${transportsLinkCategoryTitle}",
			hiddenInput: "#<portlet:namespace />transportsLinkCategoryId",
			contentBox: "#transportsLinkCategorySelector",
			label: "<liferay-ui:message key='category' />",
			labelNode: "#transportsLinkCategorySelectorLabel",
			singleSelect: true,
			vocabularyGroupIds: ${themeDisplay.companyGroupId},
			vocabularyIds: "${vocabularies}"
		}
	).render();
</aui:script>