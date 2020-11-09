<%@ include file="/search-asset-init.jsp"%>

<%@page import="com.liferay.portal.kernel.security.permission.ResourceActionsUtil"%>
<%@page import="com.liferay.asset.kernel.model.AssetRendererFactory"%>
<%@page import="com.liferay.portal.kernel.util.StringUtil"%>

<liferay-portlet:actionURL portletConfiguration="true" varImpl="configurationActionURL" />

<liferay-ui:error key="wrong-friendly-url" message="wrong-friendly-url" />

<aui:form action="${configurationActionURL}" method="post" name="fm" onSubmit="submitForm(event)">

    <aui:input name="cmd" type="hidden" value="update" />

    <div class="portlet-configuration-body-content">
        <div class="container-fluid-1280">

            <aui:fieldset-group markupView="lexicon">

                <!-- GROUPE : Type d'asset -->
                <aui:fieldset collapsed="false" collapsible="true"label="asset-type">

                    <liferay-ui:message key="asset-types-explanations" />
                    <div id="asset-types-content">
                        <div class="lfr-form-row">
                            <aui:fieldset id="contentType_1" name="contentType_1" collapsed="false" collapsible="true" label="some-content">
                                <aui:select id="contentTypeSelect_1" name="contentTypeSelect_1" label="" onChange="">
                                    <c:forEach var="assetClassName" varStatus="assetStatus"
                                               items="${dc.availableAssetTypeNames}">
                                        <aui:option value="${assetClassName}">
                                            <liferay-ui:message key="${assetClassName}"/>
                                        </aui:option>
                                    </c:forEach>
                                </aui:select>
                                <aui:fieldset id="contentTemplate_1" name="contentTemplate_1" collapsed="false" collapsible="true" label="template-and-url">
                                    <aui:select id="contentTemplateSelect_1" name="contentTemplateSelect_1" inlineField="true">
                                        <aui:option value="default" selected="true">
                                            Selectionnez une template
                                        </aui:option>
                                    </aui:select>
                                    <aui:input name="friendlyUrl" type="text" placeholder="detail-friendly-url" inlineField="true"/>
                                </aui:fieldset>
                                <aui:fieldset collapsed="false" collapsible="true" label="scope">
                                    <liferay-ui:message key="scope-explanations" />
                                    <label><liferay-ui:message key="global-scope" /></label>
                                    <liferay-ui:asset-tags-selector
                                            hiddenInput="prefilterTagsNames"
                                            curTags="${prefilterTagsNames}" />
                                </aui:fieldset>
                                <aui:fieldset collapsed="false" collapsible="true" label="prefilter">
                                    <div class="lfr-form-row lfr-form-row-inline">

                                    </div>
                                </aui:fieldset>
                            </aui:fieldset>
                        </div>
                    </div>

                </aui:fieldset>

                <!-- GROUPE : Criteres de recherche -->
                <aui:fieldset collapsed="true" collapsible="true"label="search-criterias">

                    <liferay-ui:message key="vocabularies-explanations" />
                    <aui:input type="hidden" name="vocabulariesCount" value="${fn:length(vocabularies)}" />

                    <!-- CHAMP : Vocabulaires proposés à l'utilisateur pour la recherche -->
                    <c:set var="i" value="${0}" />
                    <c:forEach var="vocabulary" items="${vocabularies}"  varStatus="vocStatus">

                        <c:set var="vocabularyIsChecked"
                            value="${fn:contains(vocabulariesIds, vocabulary.vocabularyId)}" />

                        <div class="vocabulary-configuration">
                            <c:set var="vocabularyLabel" value="${vocabulary.name}" />
                            <c:if test="${vocabulary.groupId eq themeDisplay.companyGroupId}">
                                <c:set var="vocabularyLabel" value="${vocabularyLabel.concat(' (global)')}" />
                            </c:if>
                            <aui:input type="checkbox" name="vocabularyId_${vocStatus.index}"
                                label="${vocabularyLabel}" value="${vocabulary.vocabularyId}"
                                checked="${fn:contains(vocabulariesIds, vocabulary.vocabularyId)}"
                                inlineField="true" />

                            <aui:select name="vocabularyControlType_${vocStatus.index}"
                                inlineField="true">
                                <aui:option value="list"
                                    selected="${vocabularyIsChecked ? vocabulariesControlTypes[i] eq 'list' : 'false'}">Liste d&eacute;roulante</aui:option>
                                <aui:option value="radio"
                                    selected="${vocabularyIsChecked ? vocabulariesControlTypes[i] eq 'radio' : 'false'}">Boutons radios</aui:option>
                                <aui:option value="check"
                                    selected="${vocabularyIsChecked ? vocabulariesControlTypes[i] eq 'check' : 'false'}">Checkboxes</aui:option>
                            </aui:select>
                        </div>

                        <c:if test="${vocabularyIsChecked}">
                            <c:set var="i" value="${i + 1}" />
                        </c:if>
                    </c:forEach>

                    <!-- CHAMP : Affichage du filtre par date -->
                    <div>
                        <liferay-ui:message key="date-explanations" />
                        <aui:input type="checkbox" name="dateField" value="${dateField}" label="date-field" inlineField="true" />
                    </div>

                    <!-- CHAMP : Affichage du selecteur de tri -->
                    <div>
                        <liferay-ui:message key="date-sorting-explanations" />
                        <aui:input type="checkbox" name="displayDateSorting" value="${displayDateSorting}" label="date-sorting-field" inlineField="true" />
                    </div>

                </aui:fieldset>

                <!-- GROUPE : Boosts -->
                <aui:fieldset collapsed="true" collapsible="true" label="eu.search.asset.web.configuration.boosts">

                    <liferay-ui:message key="eu.search.asset.web.configuration.boost.explanations" />
                    <p>
                        <label><liferay-ui:message key="tags" /></label>
                        <liferay-ui:asset-tags-selector hiddenInput="boostTagsNames" curTags="${boostTagsNames}" />
                    </p>

                </aui:fieldset>

                <!-- GROUPE : Filtres -->
                <aui:fieldset collapsed="true" collapsible="true" label="eu.search.asset.web.configuration.filters">

                    <!-- CHAMP : Colonne sur laqualle filtrer par défaut -->
                    <aui:select name="defaultSortField">
                        <aui:option value="modified_sortable" selected="${defaultSortField eq 'modified_sortable'}">
                            <liferay-ui:message key="modification-date" />
                        </aui:option>
                        <aui:option value="publishDate_sortable" selected="${defaultSortField eq 'publishDate_sortable'}">
                            <liferay-ui:message key="publication-date" />
                        </aui:option>
                        <aui:option value="dates_Number_sortable" selected="${defaultSortField eq 'dates_Number_sortable'}">
                            <liferay-ui:message key="entity-date" />
                        </aui:option>
                        <aui:option value="order_city_Number_sortable" selected="${defaultSortField eq 'order_city_Number_sortable'}">
                            <liferay-ui:message key="city-order-property" />
                        </aui:option>
                        <aui:option value="order_ems_Number_sortable" selected="${defaultSortField eq 'order_ems_Number_sortable'}">
                            <liferay-ui:message key="ems-order-property" />
                        </aui:option>
                        <aui:option value="priority_sortable" selected="${defaultSortField eq 'priority_sortable'}">
                            <liferay-ui:message key="order-priority" />
                        </aui:option>
                    </aui:select>

                    <!-- CHAMP : Filtre par date par defaut -->
                    <aui:input type="number" name="defaultDateRange" min="-1000" max="+1000" cssClass="date-range" value="${defaultDateRange}" label="default-date-range" inlineField="true"/>

                </aui:fieldset>

                <!-- GROUPE : Tris -->
                <aui:fieldset collapsed="true" collapsible="true" label="eu.search.asset.web.configuration.sorting">

                    <aui:row id="ordering">
                    	<aui:col width="<%= 50 %>">

                            <!-- CHAMP : Tri colonne 1 -->
                    		<aui:select label="order-by" name="orderByColumn1" value="" wrapperCssClass="field-inline w80">
                    			<aui:option label="title" value="title"/>
                    			<aui:option label="create-date" value="createDate" />
                    			<aui:option label="modified-date" value="modifiedDate" />
                    			<aui:option label="publish-date" value="publishDate" />
                    			<aui:option label="expiration-date" value="expirationDate" />
                    			<aui:option label="priority" value="priority" />
                    		</aui:select>

                            <!-- CHAMP : Tri type 1 -->
                    		<%
                    		String orderByType1 = "DESC";
                    		%>

                    		<aui:field-wrapper cssClass="field-label-inline order-by-type-container">
                    			<liferay-ui:icon
                    				cssClass='<%= StringUtil.equalsIgnoreCase(orderByType1, "DESC") ? "hide icon order-arrow-up-active" : "icon order-arrow-up-active" %>'
                    				icon="order-arrow"
                    				linkCssClass="btn btn-outline-borderless btn-outline-secondary"
                    				markupView="lexicon"
                    				message="descending"
                    				url="javascript:;"
                    			/>

                    			<liferay-ui:icon
                    				cssClass='<%= StringUtil.equalsIgnoreCase(orderByType1, "ASC") ? "hide icon order-arrow-down-active" : "icon order-arrow-down-active" %>'
                    				icon="order-arrow"
                    				linkCssClass="btn btn-outline-borderless btn-outline-secondary"
                    				markupView="lexicon"
                    				message="ascending"
                    				url="javascript:;"
                    			/>

                    			<aui:input cssClass="order-by-type-field" name="orderByType1" type="hidden" value="" />
                    		</aui:field-wrapper>
                    	</aui:col>

                    	<aui:col width="<%= 50 %>">

                            <!-- CHAMP : Tri colonne 2 -->
                    		<aui:select label="and-then-by" name="orderByColumn2" wrapperCssClass="field-inline w80">
                    			<aui:option label="title" value="title"/>
                                <aui:option label="create-date" value="createDate" />
                                <aui:option label="modified-date" value="modifiedDate" />
                                <aui:option label="publish-date" value="publishDate" />
                                <aui:option label="expiration-date" value="expirationDate" />
                                <aui:option label="priority" value="priority" />
                    		</aui:select>

                            <!-- CHAMP : Tri type 2 -->
                    		<%
                    		String orderByType2 = "DESC";
                    		%>

                    		<aui:field-wrapper cssClass="field-label-inline order-by-type-container">
                    			<liferay-ui:icon
                    				cssClass='<%= StringUtil.equalsIgnoreCase(orderByType2, "DESC") ? "hide icon order-arrow-up-active" : "icon order-arrow-up-active" %>'
                    				icon="order-arrow"
                    				linkCssClass="btn btn-outline-borderless btn-outline-secondary"
                    				markupView="lexicon"
                    				message="descending"
                    				url="javascript:;"
                    			/>

                    			<liferay-ui:icon
                    				cssClass='<%= StringUtil.equalsIgnoreCase(orderByType2, "ASC") ? "hide icon order-arrow-down-active" : "icon order-arrow-down-active" %>'
                    				icon="order-arrow"
                    				linkCssClass="btn btn-outline-borderless btn-outline-secondary"
                    				markupView="lexicon"
                    				message="ascending"
                    				url="javascript:;"
                    			/>

                    			<aui:input cssClass="order-by-type-field" name="orderByType2" type="hidden" value="" />
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
                <aui:fieldset collapsed="true" collapsible="true" label="eu.search.asset.web.configuration.group">

                    <!-- CHAMP : Champ sur lequel effectuer le groupement -->
                    <aui:select name="groupBy" label="eu.search.asset.web.configuration.group.by">
                        <aui:option value="none">
                            <liferay-ui:message key="eu.search.asset.web.configuration.none" />
                        </aui:option>
                        <aui:option value="contentType">
                            <liferay-ui:message key="eu.search.asset.web.configuration.content.type" />
                        </aui:option>
                    </aui:select>

                </aui:fieldset>

                <!-- GROUPE : Affichage -->
                <aui:fieldset collapsed="true" collapsible="true" label="eu.search.asset.web.configuration.display">

                    <!-- CHAMP : Ne pas afficher de resultats avant que l'utilisateur n'ait fait de recherche -->
                    <aui:input type="checkbox" name="hideResultsBeforeSearch" value="${hideResultsBeforeSearch}" label="eu.search.asset.web.configuration.hide.results.before.search" />

                    <!-- CHAMP : Nombre de resultats par page -->
                    <aui:input type="number" name="delta" label="eu.search.asset.web.configuration.delta" value="${delta}" />

                    <!-- CHAMP : Formulaire de recherche -->
                    <aui:select name="searchForm">
                        <aui:option value="museum" selected="${searchForm eq 'museum'}">
                            <liferay-ui:message key="museum" />
                        </aui:option>
                        <aui:option value="official" selected="${searchForm eq 'official'}">
                            <liferay-ui:message key="official" />
                        </aui:option>
                        <aui:option value="videos" selected="${searchForm eq 'videos'}">
                            <liferay-ui:message key="videos" />
                        </aui:option>
                        <aui:option value="summer-christmas-general" selected="${searchForm eq 'summer-christmas-general'}">
                            <liferay-ui:message key="summer-christmas-general" />
                        </aui:option>
                        <aui:option value="summer-christmas-agenda" selected="${searchForm eq 'summer-christmas-agenda'}">
                            <liferay-ui:message key="summer-christmas-agenda" />
                        </aui:option>
                        <aui:option value="christmas-experience" selected="${searchForm eq 'christmas-experience'}">
                            <liferay-ui:message key="christmas-experience" />
                        </aui:option>
                        <aui:option value="bib-sae-agenda" selected="${searchForm eq 'bib-sae-agenda'}">
                            <liferay-ui:message key="bib-sae-agenda" />
                        </aui:option>
                        <aui:option value="strasbourg-agenda" selected="${searchForm eq 'strasbourg-agenda'}">
                            <liferay-ui:message key="strasbourg-agenda" />
                        </aui:option>
                        <aui:option value="strasbourg-generic" selected="${searchForm eq 'strasbourg-generic'}">
                            <liferay-ui:message key="strasbourg-generic" />
                        </aui:option>
                        <aui:option value="strasbourg-offer" selected="${searchForm eq 'strasbourg-offer'}">
                            <liferay-ui:message key="strasbourg-offer" />
                        </aui:option>
                        <aui:option value="placit-projects" selected="${searchForm eq 'placit-projects'}">
                            <liferay-ui:message key="placit-projects" />
                        </aui:option>
                        <aui:option value="placit-participations" selected="${searchForm eq 'placit-participations'}">
                            <liferay-ui:message key="placit-participations" />
                        </aui:option>
                        <aui:option value="placit-agenda" selected="${searchForm eq 'placit-agenda'}">
                            <liferay-ui:message key="placit-agenda" />
                        </aui:option>
                        <aui:option value="placit-news" selected="${searchForm eq 'placit-news'}">
                            <liferay-ui:message key="placit-news" />
                        </aui:option>
                        <aui:option value="placit-videos" selected="${searchForm eq 'placit-videos'}">
                            <liferay-ui:message key="placit-videos" />
                        </aui:option>
                        <aui:option value="placit-petitions" selected="${searchForm eq 'placit-petitions'}">
                            <liferay-ui:message key="placit-petitions" />
                        </aui:option>
                        <aui:option value="placit-budgetParticipatif" selected="${searchForm eq 'placit-budgetParticipatif'}">
                            <liferay-ui:message key="placit-budgetParticipatif" />
                        </aui:option>
                        <aui:option value="placit-initiatives" selected="${searchForm eq 'placit-initiatives'}">
                            <liferay-ui:message key="placit-initiatives" />
                        </aui:option>
                        <aui:option value="ops-agenda" selected="${searchForm eq 'ops-agenda'}">
                            <liferay-ui:message key="ops-agenda" />
                        </aui:option>
                        <aui:option value="ops-general" selected="${searchForm eq 'ops-general'}">
                            <liferay-ui:message key="ops-general" />
                        </aui:option>
                        <aui:option value="ops-news" selected="${searchForm eq 'ops-news'}">
                            <liferay-ui:message key="ops-news" />
                        </aui:option>
                    </aui:select>

                </aui:fieldset>

                <!-- GROUPE : Export -->
                <aui:fieldset collapsed="true" collapsible="true" label="eu.search.asset.web.configuration.export">

                    <!-- CHAMP : Bouton exporter -->
                    <aui:input type="checkbox" name="displayExport" value="${displayExport}"
                        label="display-export" inlineField="true" />

                    <!-- CHAMP : Type d'export -->
                    <aui:select name="exportType" showEmptyOption="true">
                        <aui:option value="city" selected="${exportType eq 'city'}">
                            <liferay-ui:message key="city" />
                        </aui:option>
                        <aui:option value="ems" selected="${exportType eq 'ems'}">
                            <liferay-ui:message key="ems" />
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
        var getassetTypeRowJSPURL = '${assetTypeRowURL}';
        var getprefilterRowJSPURL = '${prefilterRowURL}';
    </script>
</liferay-util:html-top>

<liferay-util:html-bottom>
    <link href="/o/searchassetv2web/css/search-asset-configuration.css" rel="stylesheet"></script>
    <link href="/o/searchassetv2web/css/vendors/choices.min.css" rel="stylesheet">
    <script src="/o/searchassetv2web/js/searchassetv2web-config.js" type="text/javascript"></script>
    <script src="/o/searchassetv2web/js/vendors/choices.min.js" type="text/javascript"></script>
</liferay-util:html-bottom>

<aui:script use="liferay-auto-fields">
    new Liferay.AutoFields({
        contentBox: '#asset-types-content',
        fieldIndexes: '<portlet:namespace />fieldIndexes',
        on : {
            'clone' : updateFieldSets()
        }
    }).render();
</aui:script>