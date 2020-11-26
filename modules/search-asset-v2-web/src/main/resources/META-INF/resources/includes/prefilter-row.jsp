<%@ include file="/search-asset-init.jsp"%>
<div id="prefilter${param.assetTypeIndex}_${param.ruleIndex}">

    <aui:input name="numPrefiltre${param.assetTypeIndex}_${param.ruleIndex}" value="${param.index}" type="hidden" />
    <aui:select id="includeOrExclude_${param.assetTypeIndex}_${param.ruleIndex}" name="includeOrExclude_${param.assetTypeIndex}_${param.ruleIndex}" inlineField="true" label="">
        <aui:option value="contains" selected="true">
            <liferay-ui:message key="eu.search.asset.web.configuration.contains"/>
        </aui:option>
        <aui:option value="notContains" selected="false">
            <liferay-ui:message key="eu.search.asset.web.configuration.notContains"/>
        </aui:option>
    </aui:select>
    <aui:select id="allOrAny_${param.assetTypeIndex}_${param.ruleIndex}" name="allOrAny_${param.assetTypeIndex}_${param.ruleIndex}" inlineField="true" label="">
        <aui:option value="all" selected="true">
            <liferay-ui:message key="eu.search.asset.web.configuration.all"/>
        </aui:option>
        <aui:option value="any" selected="false">
            <liferay-ui:message key="eu.search.asset.web.configuration.any"/>
        </aui:option>
    </aui:select>
    <span class="form-group form-group-inline">parmi</span>
    <aui:select id="categoriesOrTags_${param.assetTypeIndex}_${param.ruleIndex}" name="categoriesOrTags_${param.assetTypeIndex}_${param.ruleIndex}" inlineField="true" label="" onChange="updatePrefilterChoices(this)">
        <aui:option value="categories" selected="false">
            <liferay-ui:message key="eu.search.asset.web.configuration.categories"/>
        </aui:option>
        <aui:option value="tags" selected="true">
            <liferay-ui:message key="eu.search.asset.web.configuration.tags"/>
        </aui:option>
    </aui:select>
    <select class="form-control" name="<portlet:namespace />prefilterChoices_${param.assetTypeIndex}_${param.ruleIndex}"
        id="<portlet:namespace />prefilterChoices_${param.assetTypeIndex}_${param.ruleIndex}"
        multiple>
        <option placeholder><liferay-ui:message key="select-prefilters" /></option>
    </select>
</div>