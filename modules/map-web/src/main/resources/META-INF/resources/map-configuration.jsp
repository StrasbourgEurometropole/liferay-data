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
                    <aui:input type="radio" name="mode" value="normal" label="no-mode" checked="${(not widgetMod and not defaultConfig and not districtMod) or (empty widgetMod and empty defaultConfig and empty districtMod)}"/>
                    <aui:input type="radio" name="mode" value="district" label="district-mode" checked="${districtMod}"/>
                    <aui:input type="radio" name="mode" value="widget" label="widget-mode" checked="${widgetMod}"/>
                    <aui:input type="radio" name="mode" value="aroundme" label="aroundme-mode" checked="${defaultConfig}"/>
                </div>

                <!-- Type de contenu carto autre que widget -->
                <aui:fieldset collapsed="true" collapsible="true"
                        label="type-contenu" cssClass="noWidgetMode" >
                    <!-- Lieux -->
                    <aui:input type="checkbox" name="typeContenu" id="placeContentType" value="eu.strasbourg.service.place.model.Place" label="eu.places"
                        checked="${fn:contains(typesContenu, 'eu.strasbourg.service.place.model.Place') || !hasConfig}" ></aui:input>

                    <!-- Arrêts -->
                    <aui:input type="checkbox" name="typeContenu" id="arretContentType" value="eu.strasbourg.service.gtfs.model.Arret" label="eu.arrets"
                        checked="${fn:contains(typesContenu, 'eu.strasbourg.service.gtfs.model.Arret') || !hasConfig}" ></aui:input>

                    <!-- Evenements -->
                    <aui:input type="checkbox" name="typeContenu" id="eventContentType" value="eu.strasbourg.service.agenda.model.Event" label="eu.events"
                        checked="${fn:contains(typesContenu, 'eu.strasbourg.service.agenda.model.Event') || !hasConfig}" cssClass="typeEvent"></aui:input>

                    <!-- Texte explicatif événement -->
                    <div class="eventExplanation">
                        <aui:input name="eventExplanationMap" value="${eventExplanation}" localized="true" type="editor" label="event-explanation-text" />
                    </div>
                </aui:fieldset>

                <!-- Affichage -->
                <aui:fieldset collapsed="true" collapsible="true" label="display-label">
                    <!-- Choix du fond de plan -->
                    <div>
                        <aui:select class="group" label="select-background" name="backgroundId">
                            <aui:option value="monstrasbourg" label="default"
                                selected="${'monstrasbourg' eq backgroundId}" />
                            <aui:option value="summer" label="summer"
                                selected="${'summer' eq backgroundId}" />
                        </aui:select>
                    </div>

                    <!-- Choix de l'affichage de la zone de configuration -->
                    <div class="showConfig noWidgetMode">
                        <aui:input type="checkbox" name="showConfig" value="${showConfig || !hasConfig}" label="show-config" />
                    </div>

                    <!-- Choix de l'affichage du lien de suppression des filtres -->
                    <div class="showDeleteFilter noWidgetMode">
                        <aui:input type="checkbox" name="showDeleteFilter" value="${showDeleteFilter}" label="show-delete-filter" />
                    </div>

                    <!-- Choix de l'affichage des pictos dans la configuration -->
                    <div class="showPictos noWidgetMode">
                        <aui:input type="checkbox" name="showPictos" value="${showPictos || !hasConfig}" label="show-pictos" />
                    </div>

                    <!-- Choix de l'affichage de la liste -->
                    <div class="noWidgetMode">
                        <aui:input type="checkbox" name="showList" value="${showList}" label="show-list" />
                    </div>

                    <!-- Détourage d'un quartier ou d'une commune -->
                    <div class="normalMode clippingTerritory">
                        <div >
                            <aui:input type="checkbox" name="clippingTerritory" value="${clippingTerritory}" label="clipping-territory" />
                        </div>

                        <!-- Choix de la zone à détourer -->
                        <div class="clippingTerritoryChecked">
                            <label><liferay-ui:message key="choise-territory" /></label>
                            <select class="toCustomSelect" id="clippingCategoryId" name="<portlet:namespace />clippingCategoryId">
                                <aui:option value=""></aui:option>
                                <c:forEach items="${territories}" var="territory">
                                    <aui:option value="${territory[0]}"
                                        label="${territory[1]}"
                                        selected="${territory[0] == clippingCategoryId}" />
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <!-- Choix du site pour la cible des lien -->
                    <aui:select name="groupId" label="detail-target-site" cssClass="group">
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
                        <label><liferay-ui:message key="zoom" /></label>
                        <p>
                            <liferay-ui:message key="zoom-help" />
                        </p>
                        <aui:input type="text" name="zoom" value="${zoom}" label="" cssClass="zoom" />
                    </div>

                    <!-- Cadrage de la carte -->
                    <div>
                        <label><liferay-ui:message key="cadrage"/></label>
                        <p>
                            <liferay-ui:message key="cadrage-help" />
                        </p>
                        <div class="cadrage">
                            <aui:input type="text" name="cadrageX" value="${cadrageX}" label="X"/>
                        </div>
                        <div class="cadrage">
                            <aui:input type="text" name="cadrageY" value="${cadrageY}" label="Y"/>
                        </div>
                    </div>
                </aui:fieldset>

                <!-- Carto normale -->
                <!-- Préfiltres -->
                <aui:fieldset collapsed="true" collapsible="true"
                        label="prefilters" cssClass="normalMode">
                    <div>
                        <label><liferay-ui:message key="prefilter-label" /></label>
                        <p>
                            <liferay-ui:message key="prefilter-help" />
                        </p>
                    </div>

                    <div>
                        <label><liferay-ui:message key="categories" /></label>
                        <liferay-ui:asset-categories-selector
                                hiddenInput="prefilterCategoriesIds"
                                curCategoryIds="${prefilterCategoriesIds}" />
                    </div>

                    <div>
                        <label><liferay-ui:message key="tags" /></label>
                        <liferay-ui:asset-tags-selector
                            hiddenInput="prefilterTags"
                            curTags="${prefilterTags}" />
                    </div>

                    <!-- Filtre sur le quartier de l'utilisateur -->
                    <div class="districtUser">
                        <aui:input type="checkbox" name="districtUser" value="${districtUser}" label="district-user" />
                    </div>
                </aui:fieldset>

                <!-- Filtres -->
                <aui:fieldset collapsed="true" collapsible="true"
                        label="filters" cssClass="normalMode">
                    <div>
                        <label><liferay-ui:message key="filter-label" /></label>
                        <p>
                            <liferay-ui:message key="filter-help" />
                        </p>
                    </div>

                    <!-- Choix d'affichage (checkbox ou liste) -->
                    <div class="filter-type">
                        <label><liferay-ui:message key="filter-type" /></label>
                        <aui:input type="radio" name="filterType" value="checkbox" checked="${filterType == 'checkbox' || !hasConfig}" label="checkbox" />
                        <aui:input type="radio" name="filterType" value="list" checked="${filterType == 'list'}" label="list" />
                    </div>

                    <div class="checkboxChosen">
                        <label class="default-filters-label"><liferay-ui:message key="default-filters-with-config-label" /></label>
                        <liferay-ui:asset-categories-selector
                            hiddenInput="categoriesDefaultsIds"
                            curCategoryIds="${categoriesDefaultsIds}" />
                        <div class="margin filters">
                            <label><liferay-ui:message key="filters-label"/></label>
                            <liferay-ui:asset-categories-selector
                                hiddenInput="categoriesIds"
                                curCategoryIds="${categoriesIds}" />
                        </div>
                    </div>
                    <div class="listChosen">
                        <label><liferay-ui:message key="filters-categ-label" /></label>
                        <p>
                            <liferay-ui:message key="filters-categ-help" />
                        </p>
                        <liferay-ui:asset-categories-selector
                            hiddenInput="parentsCategoriesIds"
                            curCategoryIds="${parentsCategoriesIds}" />
                        <div class="margin">
                            <label><liferay-ui:message key="filters-vocabularies-label"/></label>
                            <p>
                                <liferay-ui:message key="filters-vocabularies-help" />
                            </p>
                            <select name="<portlet:namespace />vocabulariesIds" id="<portlet:namespace />vocabulariesIds" label="" multiple
                                placeholder="<liferay-ui:message key="select-vocabularies" />">
                                <c:forEach items="${vocabularies}" var="vocabulary">
                                    <option value="${vocabulary[0]}"
                                        <c:if test="${fn:contains(vocabulariesIds, vocabulary[0])}">
                                            selected
                                        </c:if>
                                    >
                                        ${vocabulary[1]} (${vocabulary[2]})
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <!-- Filtre par date -->
                    <div class="dateField">
                        <aui:input type="checkbox" name="dateField" value="${dateField}" label="date-field" inlineField="true" />

                        <div class="defaultDateRange">
                            <aui:input type="number" name="defaultDateRange" min="-1000" max="+1000" cssClass="date-range" value="${defaultDateRange}" label="default-date-range" inlineField="true"/>
                        </div>
                    </div>
                </aui:fieldset>

                <!-- MonStrabourg -->
                <aui:fieldset collapsed="true" collapsible="true"
                        label="mon-strasbourg" cssClass="monStrasbourgMode">
                    <!-- Mode widget -->
                    <!-- Texte d'intro mode widget -->
                    <div class="widgetMode">
                        <aui:input type="text" name="widgetIntro" value="${widgetIntro}" label="widget-intro" />
                    </div>

                    <!-- Label bouton mode widget -->
                    <div class="widgetMode">
                        <aui:input type="text" name="widgetLink" value="${widgetLink}" label="widget-link" />
                    </div>

                    <!-- Mode autour de moi -->
                    <div class="aroundMeMode margin-less">
                        <!-- Centres d'intérêts -->
                        <label><liferay-ui:message key="interest-choice"/></label>
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

                    <!-- Affichage des favoris par défaut" -->
                    <div class="aroundMeMode">
                        <aui:input type="checkbox" name="showFavorites" value="${showFavorites || !hasConfig}" label="show-favorites" />
                    </div>
                </aui:fieldset>

                <!-- Info trafic -->
                <aui:fieldset collapsed="true" collapsible="true"
                        label="traffic" cssClass="noWidgetMode infoTraffic">
                    <!-- Affichage de l'info trafic -->
                    <div>
                        <aui:input type="checkbox" name="showTraffic" value="${showTraffic}" label="show-traffic" />
                    </div>

                    <div class="infoTrafficChecked">
                        <!-- Mode normal -->
                        <div class="normalMode">
                            <!-- Choix de la catégorie qui affichera l'info trafic -->
                            <label><liferay-ui:message key="category-link" /></label>
                            <div id="categorySelectorLabel"></div>
                            <div id="categorySelector"></div>
                            <aui:input type="hidden" name="linkCategoryId" />
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
                
                <script>
                    var refreshConfigDisplay = function() {
                        var mode = $('.modeSelection input[type=radio]:checked').val();
                        if (mode === 'widget') {
                            $('.monStrasbourgMode').show();
                            $('.widgetMode').show();
                            $('.aroundMeMode').hide();
                            $('.normalMode').hide();
                            $('.noWidgetMode').hide();
                            $(".group").closest(".form-group").addClass("margin-less");
                            $('.clippingTerritory').hide();
                        } else if (mode == 'aroundme') {
                            $('.monStrasbourgMode').show();
                            $('.widgetMode').hide();
                            $('.aroundMeMode').show();
                            $('.normalMode').hide();
                            $('.noWidgetMode').show();
                            $(".group").closest(".form-group").removeClass("margin-less");
                            $('.clippingTerritory').hide();
                        } else {
                            $('.monStrasbourgMode').hide();
                            $('.widgetMode').hide();
                            $('.aroundMeMode').hide();
                            $('.normalMode').show();
                            $('.noWidgetMode').show();
                            $(".group").closest(".form-group").removeClass("margin-less");
                            if(mode == 'district'){
                                $('.districtUser').show();
                                $('.infoTraffic').hide();
                                $('.clippingTerritory').hide();
                            }else{
                                $('.districtUser').hide();
                                $('.infoTraffic').show();
                                $('.clippingTerritory').show();
                            }
                        }
                    }

                    var refreshTypeEvent = function() {
                       if ($('.typeEvent').is(":checked")) {
                           $('.eventExplanation').show();
                           $('.dateField').show();

                       } else {
                           $('.eventExplanation').hide();
                           $('.dateField').hide();
                       }
                    }

                    var refreshConfigShowConfig = function() {
                       if ($('.showConfig input[type=checkbox]').is(":checked")) {
                           $('.showDeleteFilter').show();
                           $('.showPictos').show();
                           $('.filter-type').show();
                           refreshTypeFilterChoice();
                           $('.default-filters-label').html(Liferay.Language.get("default-filters-with-config-label"));
                           $('.filters').show();
                           $('.dateField').show();
                       } else {
                           $('.showDeleteFilter').hide();
                           $('.showPictos').hide();
                           $('.filter-type').hide();
                           $('.default-filters-label').html(Liferay.Language.get("default-filters-without-config-label"));
                           $('.checkboxChosen').show();
                           $('.filters').hide();
                           $('.listChosen').hide();
                           $('.dateField').hide();

                       }
                    }

                    var refreshConfigClipping = function() {
                       if ($('.clippingTerritory input[type=checkbox]').is(":checked")) {
                           $('.clippingTerritoryChecked').show();
                       } else {
                           $('.clippingTerritoryChecked').hide();
                       }
                    }

                    var refreshTypeFilterChoice = function() {
                       if ($('.filter-type input[type=radio][value="checkbox"]').is(":checked")) {
                           $('.checkboxChosen').show();
                           $('.listChosen').hide();
                       } else {
                           $('.checkboxChosen').hide();
                           $('.listChosen').show();
                       }
                    }

                    var refreshFilterDate = function() {
                        if ($('.dateField input[type=checkbox]').is(":checked")) {
                            $('.defaultDateRange').show();
                        } else {
                            $('.defaultDateRange').hide();
                        }
                    }

                    var refreshConfigTrafficDisplay = function() {
                       if ($('.infoTraffic input[type=checkbox]').is(":checked")) {
                           $('.infoTrafficChecked').show();
                       } else {
                           $('.infoTrafficChecked').hide();
                       }
                    }

                    $('.modeSelection input[type=radio]').on('change', function() {
                        refreshConfigDisplay();
                    })

                    $('.typeEvent').on('change', function() {
                       refreshTypeEvent();
                    })

                    $('.showConfig input[type=checkbox]').on('change', function() {
                        refreshConfigShowConfig();
                    })

                    $('.clippingTerritory input[type=checkbox]').on('change', function() {
                        refreshConfigClipping();
                    })

                    $('.filter-type input[type=radio]').on('change', function() {
                        refreshTypeFilterChoice();
                    })

                    $('.dateField input[type=checkbox]').on('change', function() {
                        refreshFilterDate();
                    })

                    $('.infoTraffic input[type=checkbox]').on('change', function() {
                        refreshConfigTrafficDisplay();
                    })

                    $(function() {
                        refreshConfigDisplay();
                        refreshConfigShowConfig();
                        refreshConfigClipping();
                        refreshTypeFilterChoice();
                        refreshFilterDate();
                        refreshTypeEvent();
                        refreshConfigTrafficDisplay();
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
			vocabularyIds: "${vocabulariesStr}"
		}
	).render();
</aui:script>

<link rel="stylesheet" href="/o/mapweb/css/config-map.css" />