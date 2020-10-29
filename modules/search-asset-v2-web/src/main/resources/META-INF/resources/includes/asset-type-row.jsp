<%@ include file="/search-asset-init.jsp"%>

<aui:fieldset collapsed="false" collapsible="true" label="some-content">
    <aui:select name="contentTypeSelect" label="">
        <aui:option value="event" selected="true">
            Evenement
        </aui:option>
    </aui:select>
    <aui:fieldset collapsed="false" collapsible="true" label="template-and-url">
        <aui:select name="templateSelect" inlineField="true">
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
