<%@ include file="/search-asset-init.jsp"%>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %>
<%@page import="com.liferay.portal.kernel.security.permission.ResourceActionsUtil"%>
<%@page import="com.liferay.asset.kernel.model.AssetRendererFactory"%>

<liferay-portlet:actionURL portletConfiguration="true" varImpl="configurationActionURL" />

<liferay-ui:error key="wrong-friendly-url" message="eu.search.asset.web.configuration.wrong-friendly-url" />

<aui:form action="${configurationActionURL}" method="post" name="fmConfig">

    <aui:input name="cmd" type="hidden" value="update" />

    <div class="portlet-configuration-body-content">
        <div class="container-fluid-1280">

            <aui:fieldset-group markupView="lexicon">

                <!-- GROUPE : Type d'asset -->
                <aui:fieldset collapsed="false" collapsible="true" label="asset-type">

                    <liferay-ui:message key="eu.search.asset.web.configuration.asset-types-explanations" />

                    <div id="asset-types-content">
                        <c:set var="assetTypeList" value="${dc.configurationData.assetTypeDataList}"/>
		                <c:set var="assetTypeNames" value="${dc.availableAssetTypeNames}" scope="request"/>

                        <aui:input name="nbAssetType" type="hidden" value="${assetTypeList.isEmpty() ? '1' : assetTypeList.size()}" />
                        <aui:input name="assetTypeIndexes" type="hidden" />

                        <c:if test="${empty assetTypeList}">
                            <liferay-util:include page="/includes/asset-type-row.jsp" servletContext="<%=application %>">
                                    <liferay-util:param name="index" value="0" />
                            </liferay-util:include>
                        </c:if>

                        <c:forEach items="${assetTypeList}" var="assetTypeData" varStatus="status">
                            <c:set var="assetTypeData" value="${assetTypeData}" scope="request"/>
                            <liferay-util:include page="/includes/asset-type-row.jsp" servletContext="<%=application %>">
                                    <liferay-util:param name="index" value="${status.index}" />
                            </liferay-util:include>
                        </c:forEach>
                    </div>

                    <aui:button cssClass="btn-icon icon icon-plus icon-2x" type="button" onClick="addAssetType();"/>

                </aui:fieldset>

                <!-- GROUPE : Criteres de recherche -->
                <aui:fieldset collapsed="true" collapsible="true" label="eu.search.asset.web.configuration.search-criterias">

                    <liferay-ui:message key="eu.search.asset.web.configuration.vocabularies-explanations" />

                    <div  id="vocabularies-content">
                        <c:set var="vocabularyControlTypesMap" value="${dc.configurationData.vocabulariesControlTypesMap}"/>
                        <aui:input type="hidden" name="nbVocabularies" value="${vocabularyControlTypesMap.isEmpty() ? '0' : vocabularyControlTypesMap.size()}" />
                        <aui:input name="vocabularyIndexes" type="hidden" />

                        <c:forEach items="${vocabularyControlTypesMap}" var="vocabularyControlType" varStatus="status">
                            <c:set var="vocabularyControlType" value="${vocabularyControlType}" scope="request"/>
                            <liferay-util:include page="/includes/vocabulary-row.jsp" servletContext="<%=application %>">
                                    <liferay-util:param name="index" value="${status.index}" />
                            </liferay-util:include>
                        </c:forEach>
                    </div>

                    <aui:button cssClass="btn-icon icon icon-plus icon-2x" type="button" onClick="addVocabulary();"/>

                    <!-- CHAMP : Affichage du filtre par date -->
                    <div>
                        <liferay-ui:message key="eu.search.asset.web.configuration.date-explanations" />
                        <aui:input type="checkbox" name="displayDateField" value="${dc.configurationData.isDisplayDateField()}" label="eu.search.asset.web.configuration.date-field" inlineField="true" />
                    </div>

                    <!-- CHAMP : Affichage ddes boutons de dates -->
                    <div>
                        <liferay-ui:message key="eu.search.asset.web.configuration.dates-buttons-explanations" />
                        <aui:input type="checkbox" name="displayDatesButtons" value="${dc.configurationData.isDisplayDatesButtons()}" label="eu.search.asset.web.configuration.show-dates-buttons-field" inlineField="true" />
                    </div>

                    <!-- CHAMP : Affichage du selecteur de tri -->
                    <div>
                        <liferay-ui:message key="eu.search.asset.web.configuration.date-sorting-explanations" />
                        <aui:input type="checkbox" name="displaySorting" value="${dc.configurationData.isDisplaySorting()}" label="eu.search.asset.web.configuration.date-sorting-field" inlineField="true" />
                    </div>

                </aui:fieldset>

                <!-- GROUPE : Boosts -->
                <aui:fieldset collapsed="true" collapsible="true" label="eu.search.asset.web.configuration.boosts">

                    <liferay-ui:message key="eu.search.asset.web.configuration.boost.explanations" />
                    <p>
                        <label><liferay-ui:message key="tags" /></label>
                        <liferay-ui:asset-tags-selector hiddenInput="boostTagsNames" curTags="${dc.configurationData.boostTagsNames}" />
                    </p>

                </aui:fieldset>

                <!-- GROUPE : Filtres -->
                <aui:fieldset collapsed="true" collapsible="true" label="eu.search.asset.web.configuration.filters">

                    <!-- CHAMP : Colonne sur laquelle filtrer par défaut -->
                    <aui:select name="filterField" label="eu.search.asset.web.configuration.filter-field">
                        <aui:option label="eu.search.asset.web.configuration.filter.modification-date" value="modified_sortable" selected="${dc.configurationData.filterField eq 'modified_sortable'}"/>
                        <aui:option label="eu.search.asset.web.configuration.filter.publication-date" value="publishDate_sortable" selected="${dc.configurationData.filterField eq 'publishDate_sortable'}"/>
                        <aui:option label="eu.search.asset.web.configuration.filter.entity-date" value="dates_Number_sortable" selected="${dc.configurationData.filterField eq 'dates_Number_sortable'}"/>
                        <aui:option label="eu.search.asset.web.configuration.filter.due-date" value="endDate_Number_sortable" selected="${dc.configurationData.filterField eq 'endDate_Number_sortable'}"/>
                    </aui:select>

                    <!-- CHAMP : Filtre par date par defaut -->
                    <aui:input type="number" name="defaultFilterDateRange" min="-1000" max="+1000" cssClass="date-range" value="${dc.configurationData.defaultFilterDateRange}" label="eu.search.asset.web.configuration.default-date-range" inlineField="true"/>

                </aui:fieldset>

                <!-- GROUPE : Tris -->
                <aui:fieldset collapsed="true" collapsible="true" label="eu.search.asset.web.configuration.sorting">

                    <!-- CHAMP : Tri aléatoire -->
                    <div>
                        <aui:input type="checkbox" name="randomSort" value="${dc.configurationData.isRandomSort()}" label="eu.search.asset.web.configuration.random-sort" />
                    </div>

                    <aui:row id="ordering">
                    	<aui:col width="<%= 50 %>">

                            <!-- CHAMP : Tri colonne 1 -->
                    		<aui:select label="order-by" name="firstSortingField" value="" wrapperCssClass="field-inline w80">
                    			<aui:option label="title" value="localized_title_fr_FR_sortable" selected="${dc.configurationData.firstSortingField eq 'localized_title_fr_FR_sortable'}"/>
                    			<aui:option label="create-date" value="createDate" selected="${dc.configurationData.firstSortingField eq 'createDate'}" />
                    			<aui:option label="modified-date" value="modified_sortable" selected="${dc.configurationData.firstSortingField eq 'modified_sortable'}" />
                    			<aui:option label="publish-date" value="publishDate_sortable" selected="${dc.configurationData.firstSortingField eq 'publishDate_sortable'}" />
                    			<aui:option label="expiration-date" value="expirationDate" selected="${dc.configurationData.firstSortingField eq 'expirationDate'}" />
                                <aui:option label="eu.search.asset.web.configuration.filter.entity-date" value="dates_Number_sortable" selected="${dc.configurationData.firstSortingField eq 'dates_Number_sortable'}"/>
                                <aui:option label="eu.search.asset.web.configuration.sort.start-date" value="startDate_Number_sortable" selected="${dc.configurationData.firstSortingField eq 'startDate_Number_sortable'}"/>
                                <aui:option label="eu.search.asset.web.configuration.sort.end-date" value="endDate_Number_sortable" selected="${dc.configurationData.firstSortingField eq 'endDate_Number_sortable'}"/>
                                <aui:option label="eu.search.asset.web.configuration.sort.city-order-property" value="order_city_Number_sortable" selected="${dc.configurationData.firstSortingField eq 'order_city_Number_sortable'}" />
                                <aui:option label="eu.search.asset.web.configuration.sort.ems-order-property" value="order_ems_Number_sortable" selected="${dc.configurationData.firstSortingField eq 'order_ems_Number_sortable'}"/>
                                <aui:option label="eu.search.asset.web.configuration.sort.order-priority" value="priority_sortable" selected="${dc.configurationData.firstSortingField eq 'priority_sortable'}"/>
                    		</aui:select>

                            <!-- CHAMP : Tri type 1 -->
                    		<aui:field-wrapper cssClass="field-label-inline order-by-type-container">
                    			<liferay-ui:icon
                    				cssClass='icon order-arrow-up-active ${dc.configurationData.firstSortingType == "ASC" ? "hide" : ""}'
                    				icon="order-arrow"
                    				linkCssClass="btn btn-outline-borderless btn-outline-secondary"
                    				markupView="lexicon"
                    				message="descending"
                    				url="javascript:;"
                    			/>

                    			<liferay-ui:icon
                    				cssClass='icon order-arrow-down-active ${dc.configurationData.firstSortingType == "ASC" ? "" : "hide"}'
                    				icon="order-arrow"
                    				linkCssClass="btn btn-outline-borderless btn-outline-secondary"
                    				markupView="lexicon"
                    				message="ascending"
                    				url="javascript:;"
                    			/>

                    			<aui:input cssClass="order-by-type-field" name="firstSortingType" type="hidden" value="${dc.configurationData.firstSortingType}" />
                    		</aui:field-wrapper>
                    	</aui:col>

                    	<aui:col width="<%= 50 %>">

                            <!-- CHAMP : Tri colonne 2 -->
                    		<aui:select label="and-then-by" name="secondSortingField" wrapperCssClass="field-inline w80">
                    			<aui:option label="title" value="localized_title_fr_FR_sortable" selected="${dc.configurationData.secondSortingField eq 'localized_title_fr_FR_sortable'}"/>
                    			<aui:option label="create-date" value="createDate" selected="${dc.configurationData.secondSortingField eq 'createDate'}" />
                    			<aui:option label="modified-date" value="modified_sortable" selected="${dc.configurationData.secondSortingField eq 'modified_sortable'}" />
                    			<aui:option label="publish-date" value="publishDate_sortable" selected="${dc.configurationData.secondSortingField eq 'publishDate_sortable'}" />
                    			<aui:option label="expiration-date" value="expirationDate" selected="${dc.configurationData.secondSortingField eq 'expirationDate'}" />
                                <aui:option label="eu.search.asset.web.configuration.filter.entity-date" value="dates_Number_sortable" selected="${dc.configurationData.secondSortingField eq 'dates_Number_sortable'}"/>
                                <aui:option label="eu.search.asset.web.configuration.sort.start-date" value="startDate_Number_sortable" selected="${dc.configurationData.firstSortingField eq 'startDate_Number_sortable'}"/>
                                <aui:option label="eu.search.asset.web.configuration.sort.end-date" value="endDate_Number_sortable" selected="${dc.configurationData.firstSortingField eq 'endDate_Number_sortable'}"/>
                                <aui:option label="eu.search.asset.web.configuration.sort.city-order-property" value="order_city_Number_sortable" selected="${dc.configurationData.secondSortingField eq 'order_city_Number_sortable'}" />
                                <aui:option label="eu.search.asset.web.configuration.sort.ems-order-property" value="order_ems_Number_sortable" selected="${dc.configurationData.secondSortingField eq 'order_ems_Number_sortable'}"/>
                                <aui:option label="eu.search.asset.web.configuration.sort.order-priority" value="priority_sortable" selected="${dc.configurationData.secondSortingField eq 'priority_sortable'}"/>
                    		</aui:select>

                            <!-- CHAMP : Tri type 2 -->
                    		<aui:field-wrapper cssClass="field-label-inline order-by-type-container">
                    			<liferay-ui:icon
                    				cssClass='icon order-arrow-up-active ${dc.configurationData.secondSortingType == "ASC" ? "hide" : ""}'
                    				icon="order-arrow"
                    				linkCssClass="btn btn-outline-borderless btn-outline-secondary"
                    				markupView="lexicon"
                    				message="descending"
                    				url="javascript:;"
                    			/>

                    			<liferay-ui:icon
                    				cssClass='icon order-arrow-down-active ${dc.configurationData.secondSortingType == "ASC" ? "" : "hide"}'
                    				icon="order-arrow"
                    				linkCssClass="btn btn-outline-borderless btn-outline-secondary"
                    				markupView="lexicon"
                    				message="ascending"
                    				url="javascript:;"
                    			/>

                    			<aui:input cssClass="order-by-type-field" name="secondSortingType" type="hidden" value="${dc.configurationData.secondSortingType}" />
                    		</aui:field-wrapper>
                    	</aui:col>
                    </aui:row>

                    <aui:script use="aui-base">
                    	A.one('#<portlet:namespace />ordering').delegate(
                    	    'click',
                    		function(event) {
                    			var currentTarget = event.currentTarget;

                    			var orderByTypeContainer = currentTarget.ancestor(
                    				'.order-by-type-container'
                    			);

                    			orderByTypeContainer.all('.icon').toggleClass('hide');

                    			var orderByTypeField = orderByTypeContainer.one('.order-by-type-field');

                    			var newVal = orderByTypeField.val() === 'ASC' ? 'DESC' : 'ASC';

                    			orderByTypeField.val(newVal);
                    		},
                    		'.icon'
                    	);
                    </aui:script>

                </aui:fieldset>

                <!-- GROUPE : Groupement -->
                <%-- <aui:fieldset collapsed="true" collapsible="true" label="eu.search.asset.web.configuration.group">

                    <!-- CHAMP : Champ sur lequel effectuer le groupement -->
                    <aui:input name="groupBySelectedId" value="${dc.configurationData.groupBy}" type="hidden" />
                    <select class="form-control" name="<portlet:namespace />groupBy" id="<portlet:namespace />groupBy"></select>

                </aui:fieldset>
                --%>

                <!-- GROUPE : Affichage -->
                <aui:fieldset collapsed="true" collapsible="true" label="eu.search.asset.web.configuration.display">

                    <!-- CHAMP : Ne pas afficher de resultats avant que l'utilisateur n'ait fait de recherche -->
                    <aui:input type="checkbox" name="hideResultsBeforeSearch" value="${dc.configurationData.isHideResultsBeforeSearch()}" label="eu.search.asset.web.configuration.hide.results.before.search" />

                    <!-- CHAMP : Nombre de resultats par page -->
                    <aui:input type="number" name="delta" label="eu.search.asset.web.configuration.delta" value="${dc.configurationData.delta}" />

                    <!-- CHAMP : Formulaire de recherche -->
                    <aui:select name="searchForm" label="eu.search.asset.web.configuration.search-form">
                        <aui:option value="museum" selected="${dc.configurationData.searchForm eq 'museum'}">
                            <liferay-ui:message key="eu.search.asset.web.configuration.template.museum" />
                        </aui:option>
                        <aui:option value="museum-agenda" selected="${dc.configurationData.searchForm eq 'museum-agenda'}">
                            <liferay-ui:message key="eu.search.asset.web.configuration.template.museum-agenda" />
                        </aui:option>
                        <aui:option value="official" selected="${dc.configurationData.searchForm eq 'official'}">
                            <liferay-ui:message key="eu.search.asset.web.configuration.template.official" />
                        </aui:option>
                        <aui:option value="videos" selected="${dc.configurationData.searchForm eq 'videos'}">
                            <liferay-ui:message key="eu.search.asset.web.configuration.template.videos" />
                        </aui:option>
                        <aui:option value="summer-christmas-general" selected="${dc.configurationData.searchForm eq 'summer-christmas-general'}">
                            <liferay-ui:message key="eu.search.asset.web.configuration.template.summer-christmas-general" />
                        </aui:option>
                        <aui:option value="summer-christmas-agenda" selected="${dc.configurationData.searchForm eq 'summer-christmas-agenda'}">
                            <liferay-ui:message key="eu.search.asset.web.configuration.template.summer-christmas-agenda" />
                        </aui:option>
                        <aui:option value="christmas-experience" selected="${dc.configurationData.searchForm eq 'christmas-experience'}">
                            <liferay-ui:message key="eu.search.asset.web.configuration.template.christmas-experience" />
                        </aui:option>
                        <aui:option value="bib-sae-agenda" selected="${dc.configurationData.searchForm eq 'bib-sae-agenda'}">
                            <liferay-ui:message key="eu.search.asset.web.configuration.template.bib-sae-agenda" />
                        </aui:option>
                        <aui:option value="strasbourg-agenda" selected="${dc.configurationData.searchForm eq 'strasbourg-agenda'}">
                            <liferay-ui:message key="eu.search.asset.web.configuration.template.strasbourg-agenda" />
                        </aui:option>
                        <aui:option value="strasbourg-generic" selected="${dc.configurationData.searchForm eq 'strasbourg-generic'}">
                            <liferay-ui:message key="eu.search.asset.web.configuration.template.strasbourg-generic" />
                        </aui:option>
                        <aui:option value="strasbourg-offer" selected="${dc.configurationData.searchForm eq 'strasbourg-offer'}">
                            <liferay-ui:message key="eu.search.asset.web.configuration.template.strasbourg-offer" />
                        </aui:option>
                        <aui:option value="placit-projects" selected="${dc.configurationData.searchForm eq 'placit-projects'}">
                            <liferay-ui:message key="eu.search.asset.web.configuration.template.placit-projects" />
                        </aui:option>
                        <aui:option value="placit-participations" selected="${dc.configurationData.searchForm eq 'placit-participations'}">
                            <liferay-ui:message key="eu.search.asset.web.configuration.template.placit-participations" />
                        </aui:option>
                        <aui:option value="placit-agenda" selected="${dc.configurationData.searchForm eq 'placit-agenda'}">
                            <liferay-ui:message key="eu.search.asset.web.configuration.template.placit-agenda" />
                        </aui:option>
                        <aui:option value="placit-news" selected="${dc.configurationData.searchForm eq 'placit-news'}">
                            <liferay-ui:message key="eu.search.asset.web.configuration.template.placit-news" />
                        </aui:option>
                        <aui:option value="placit-videos" selected="${dc.configurationData.searchForm eq 'placit-videos'}">
                            <liferay-ui:message key="eu.search.asset.web.configuration.template.placit-videos" />
                        </aui:option>
                        <aui:option value="placit-petitions" selected="${dc.configurationData.searchForm eq 'placit-petitions'}">
                            <liferay-ui:message key="eu.search.asset.web.configuration.template.placit-petitions" />
                        </aui:option>
                        <aui:option value="placit-budgetParticipatif" selected="${dc.configurationData.searchForm eq 'placit-budgetParticipatif'}">
                            <liferay-ui:message key="eu.search.asset.web.configuration.template.placit-budgetParticipatif" />
                        </aui:option>
                        <aui:option value="placit-initiatives" selected="${dc.configurationData.searchForm eq 'placit-initiatives'}">
                            <liferay-ui:message key="eu.search.asset.web.configuration.template.placit-initiatives" />
                        </aui:option>
                        <aui:option value="placit-project-workshop" selected="${dc.configurationData.searchForm eq 'placit-project-workshop'}">
                            <liferay-ui:message key="eu.search.asset.web.configuration.template.placit-project-workshop" />
                        </aui:option>
                        <aui:option value="ops-agenda" selected="${dc.configurationData.searchForm eq 'ops-agenda'}">
                            <liferay-ui:message key="eu.search.asset.web.configuration.template.ops-agenda" />
                        </aui:option>
                        <aui:option value="ops-general" selected="${dc.configurationData.searchForm eq 'ops-general'}">
                            <liferay-ui:message key="eu.search.asset.web.configuration.template.ops-general" />
                        </aui:option>
                        <aui:option value="ops-news" selected="${dc.configurationData.searchForm eq 'ops-news'}">
                            <liferay-ui:message key="eu.search.asset.web.configuration.template.ops-news" />
                        </aui:option>
                        <aui:option value="help" selected="${dc.configurationData.searchForm eq 'help'}">
                            <liferay-ui:message key="eu.search.asset.web.configuration.template.aide" />
                        </aui:option>
                    </aui:select>

                </aui:fieldset>

                <!-- GROUPE : Export -->
                <aui:fieldset collapsed="true" collapsible="true" label="eu.search.asset.web.configuration.export">

                    <!-- CHAMP : Bouton exporter -->
                    <aui:input type="checkbox" name="displayExport" value="${dc.configurationData.isDisplayExport()}"
                        label="eu.search.asset.web.configuration.display-export" inlineField="true" />

                    <!-- CHAMP : Type d'export -->
                    <aui:select name="exportType" showEmptyOption="true" label="eu.search.asset.web.configuration.export-type">
                        <aui:option value="city" selected="${dc.configurationData.exportType eq 'city'}">
                            <liferay-ui:message key="eu.search.asset.web.configuration.type-export.city" />
                        </aui:option>
                        <aui:option value="ems" selected="${dc.configurationData.exportType eq 'ems'}">
                            <liferay-ui:message key="eu.search.asset.web.configuration.type-export.ems" />
                        </aui:option>
                    </aui:select>

                </aui:fieldset>

            </aui:fieldset-group>
        </div>
    </div>

    <aui:button-row>
        <aui:button type="submit"></aui:button>
    </aui:button-row>

</aui:form>

<liferay-util:html-top>
   	<script>
	        var assetTypeNames = '${dc.availableAssetTypeNamesString}';
	        var assetTemplates = ${dc.availableAssetTemplates};
         	var scopesJson = ${dc.availableScopes};
   	</script>
</liferay-util:html-top>


<liferay-util:html-bottom>
    <script src="/o/searchassetv2web/js/vendors/choices.min.js"></script>
    <script src="/o/searchassetv2web/js/bloc-asset-type-configuration.js"></script>
    <script src="/o/searchassetv2web/js/bloc-prefilter-configuration.js"></script>
    <script src="/o/searchassetv2web/js/bloc-critere-recherche-configuration.js"></script>
    <script src="/o/searchassetv2web/js/searchassetv2web-config.js"></script>
    <link href="/o/searchassetv2web/css/search-asset-configuration.css" rel="stylesheet"></script>
    <link href="/o/searchassetv2web/css/vendors/choices.min.css" rel="stylesheet">
</liferay-util:html-bottom>