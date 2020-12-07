<%@ include file="/search-asset-init.jsp"%>
<div id="prefilter${param.assetTypeIndex}_${param.ruleIndex}">

    <aui:input name="numPrefiltre${param.assetTypeIndex}_${param.ruleIndex}" value="${param.ruleIndex}" type="hidden" />
    <aui:select id="includeOrExclude_${param.assetTypeIndex}_${param.ruleIndex}" name="includeOrExclude_${param.assetTypeIndex}_${param.ruleIndex}" inlineField="true" label="">
        <aui:option value="true" selected="${empty prefilterData or prefilterData.isIncludeOrExclude()  ? 'true' : 'false'}">
            <liferay-ui:message key="eu.search.asset.web.configuration.contains"/>
        </aui:option>
        <aui:option value="false" selected="${not empty prefilterData and not prefilterData.isIncludeOrExclude()  ? 'true' : 'false'}">
            <liferay-ui:message key="eu.search.asset.web.configuration.notContains"/>
        </aui:option>
    </aui:select>
    <aui:select id="allOrAny_${param.assetTypeIndex}_${param.ruleIndex}" name="allOrAny_${param.assetTypeIndex}_${param.ruleIndex}" inlineField="true" label="">
        <aui:option value="all" selected="${empty prefilterData or prefilterData.operator.equals('all') ? 'true' : 'false'}">
            <liferay-ui:message key="eu.search.asset.web.configuration.all"/>
        </aui:option>
        <aui:option value="any" selected="${not empty prefilterData and not prefilterData.operator.equals('all') ? 'true' : 'false'}">
            <liferay-ui:message key="eu.search.asset.web.configuration.any"/>
        </aui:option>
    </aui:select>
    <span class="form-group form-group-inline">parmi</span>
    <aui:select id="categoriesOrTags_${param.assetTypeIndex}_${param.ruleIndex}" name="categoriesOrTags_${param.assetTypeIndex}_${param.ruleIndex}" inlineField="true" label="" onChange="updatePrefilter(this)">
        <aui:option value="categories"  selected="${not empty prefilterData and not prefilterData.type.equals('tags') ? 'true' : 'false'}">
            <liferay-ui:message key="eu.search.asset.web.configuration.categories"/>
        </aui:option>
        <aui:option value="tags" selected="${empty prefilterData or prefilterData.type.equals('tags') ? 'true' : 'false'}">
            <liferay-ui:message key="eu.search.asset.web.configuration.tags"/>
        </aui:option>
    </aui:select>
    <aui:button cssClass="form-group btn-icon icon icon-trash icon-2x" type="button" onClick="deletePrefilter(${param.assetTypeIndex},${param.ruleIndex});"/>
    <aui:input name="prefilterSelectedIds${param.assetTypeIndex}_${param.ruleIndex}" value="${not empty prefilterData  ? prefilterData.categoryOrTagIdsJSON : ''}" type="hidden" />
    <select class="form-control" name="<portlet:namespace />prefilterIds_${param.assetTypeIndex}_${param.ruleIndex}"
        id="<portlet:namespace />prefilterIds_${param.assetTypeIndex}_${param.ruleIndex}"
        multiple>
        <option placeholder><liferay-ui:message key="select-prefilters" /></option>
    </select>
</div>
<div style='margin: 20px 0;'></div>