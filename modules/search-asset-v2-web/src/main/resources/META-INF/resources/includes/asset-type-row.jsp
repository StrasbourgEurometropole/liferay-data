<%@ include file="/search-asset-init.jsp"%>

<!-- Type d'asset -->
<aui:fieldset collapsed="false" collapsible="true" label="some-content" id="assetType${param.index}">

    <aui:input name="numAssetType${param.index}" value="${param.index}" type="hidden" />

    <aui:select id="classname_${param.index}" name="classname_${param.index}" label="" onChange="updateTemplates(${param.index})">
        <aui:option value="${false}" selected="true">
            <liferay-ui:message key="select-asset-type"/>
        </aui:option>
        <c:forEach var="assetClassName" varStatus="assetStatus"
                   items="${assetTypeNames}">
            <aui:option value="${assetClassName}">
                <liferay-ui:message key="${assetClassName}"/>
            </aui:option>
        </c:forEach>
    </aui:select>

    <!-- Template et URL -->
    <aui:fieldset collapsed="false" collapsible="true" label="template-and-url">
        <aui:select id="templateKey_${param.index}" name="templateKey_${param.index}" label="" inlineField="true">
            <aui:option value="${false}" selected="true">
                <liferay-ui:message key="select-a-template"/>
            </aui:option>
        </aui:select>
        <aui:input id="friendlyUrl_${param.index}" name="friendlyUrl_${param.index}" label="" type="text" placeholder="detail-friendly-url" inlineField="true"/>
    </aui:fieldset>

    <!-- Portée -->
    <aui:fieldset collapsed="false" collapsible="true" label="scope">
        <liferay-ui:message key="scope-explanations" />
        <select class="form-control" name="<portlet:namespace />scopeIds_${param.index}"
                id="<portlet:namespace />scopeIds_${param.index}"
                placeholder="<liferay-ui:message key="select-scopes" />" multiple>
        </select>
    </aui:fieldset>

    <!-- Préfiltre -->
    <aui:fieldset collapsed="false" collapsible="true" label="prefilter">
        <div id="prefilter-rules_${param.index}"></div>
        <aui:button cssClass="btn-icon icon icon-plus icon-2x" type="button" onClick="addPrefilter(${param.index});"/>
    </aui:fieldset>
</aui:fieldset>
