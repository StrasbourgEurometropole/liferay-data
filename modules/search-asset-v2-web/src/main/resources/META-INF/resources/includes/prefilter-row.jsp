<%@ include file="/search-asset-init.jsp"%>
<div>
    <aui:select name="includeOrExclude" inlineField="true" label="">
        <aui:option value="contains" selected="true">
            Contient
        </aui:option>
        <aui:option value="notContains" selected="false">
            Ne contient pas
        </aui:option>
    </aui:select>
    <aui:select name="allOrAny" inlineField="true" label="">
        <aui:option value="all" selected="true">
            Tous
        </aui:option>
        <aui:option value="any" selected="false">
            N'importe lequel
        </aui:option>
    </aui:select>
    <liferay-ui:message key="parmi"/>
    <aui:select name="categoriesOrTags" inlineField="true" label="">
        <aui:option value="categories" selected="true">
            Categories
        </aui:option>
        <aui:option value="tags" selected="false">
            Etiquettes
        </aui:option>
    </aui:select>
</div>
<div>
    <liferay-ui:asset-tags-selector
            hiddenInput="prefilterTagsNames"
            curTags="${prefilterTagsNames}" />
</div>