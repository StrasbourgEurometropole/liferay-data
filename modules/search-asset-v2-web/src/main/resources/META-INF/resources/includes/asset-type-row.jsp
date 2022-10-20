<%@ include file="/search-asset-init.jsp"%>

<!-- Type d'asset -->
<aui:fieldset-group markupView="lexicon">

    <aui:fieldset collapsed="false" collapsible="true" label="${not empty assetTypeData ? assetTypeData.className : 'some-content'}" id="assetType${param.index}">

        <aui:input name="numAssetType${param.index}" value="${param.index}" type="hidden" />

        <aui:select id="classname_${param.index}" name="classname_${param.index}" label="" onChange="updateBloc(${param.index})" inlineField="true" >
            <aui:option value="some-content" >
                <liferay-ui:message key="select-asset-type"/>
            </aui:option>
            <c:forEach var="assetClassName" varStatus="assetStatus"
                       items="${assetTypeNames}">
                <aui:option value="${assetClassName}" selected="${not empty assetTypeData and assetTypeData.className == assetClassName  ? 'true' : 'false'}">
                    <liferay-ui:message key="${assetClassName}"/>
                </aui:option>
            </c:forEach>
        </aui:select>
        <c:if test="${param.index gt 0}">
            <aui:button cssClass="form-group btn-icon icon icon-trash icon-2x" type="button" onClick="deleteAssetType(${param.index});"/>
        </c:if>

        <!-- Portée -->
        <aui:fieldset collapsed="false" collapsible="true" label="scope" id="scope${param.index}">
            <liferay-ui:message key="scope-explanations" />
            <aui:input name="scopeSelectedIds${param.index}" value="${not empty assetTypeData  ? assetTypeData.scopeGroupIDsJSON : ''}" type="hidden" />
            <select class="form-control" name="<portlet:namespace />scopeIds_${param.index}"
                    id="<portlet:namespace />scopeIds_${param.index}" multiple onChange="updatePrefiltersStructureVocabulariesAndGroupBy(${param.index})">
                <option placeholder><liferay-ui:message key="select-scopes" /></option>
            </select>
        </aui:fieldset>

        <!-- Structure -->
        <aui:fieldset collapsed="false" collapsible="true" label="structure" id="structure${param.index}">
            <aui:input name="structureSelectedId${param.index}" value="${not empty assetTypeData  ? assetTypeData.structureID : ''}" type="hidden" />
            <aui:select id="structure_${param.index}" name="structure_${param.index}" label="" inlineField="true"></aui:select>
        </aui:fieldset>

        <!-- Template et URL -->
        <aui:fieldset collapsed="false" collapsible="true" label="template-and-url" id="template-and-url${param.index}">
            <aui:input name="templateSelectedId${param.index}" value="${not empty assetTypeData  ? assetTypeData.templateKey : ''}" type="hidden" />
            <aui:select id="templateKey_${param.index}" name="templateKey_${param.index}" label="" inlineField="true"></aui:select>
            <aui:input id="friendlyUrl_${param.index}" name="friendlyUrl_${param.index}" label="" type="text" placeholder="detail-friendly-url" inlineField="true" value="${not empty assetTypeData ? assetTypeData.friendlyURL : ''}"/>
        </aui:fieldset>

        <!-- Préfiltre -->
        <aui:fieldset collapsed="false" collapsible="true" label="prefilter" id="prefilter${param.index}">

            <div id="prefilters-content${param.index}">
                <aui:input name="nbPrefiltre${param.index}" type="hidden" value="${empty assetTypeData ? '0' : assetTypeData.assetPrefilterDataList.size()}" />
                <aui:input name="prefilterIndexes${param.index}" type="hidden" />

                <c:forEach items="${assetTypeData.assetPrefilterDataList}" var="prefilterData" varStatus="status">
                    <c:set var="prefilterData" value="${prefilterData}" scope="request"/>
                    <liferay-util:include page="/includes/prefilter-row.jsp" servletContext="<%=application %>">
                            <liferay-util:param name="assetTypeIndex" value="${param.index}" />
                            <liferay-util:param name="ruleIndex" value="${status.index}" />
                    </liferay-util:include>
                </c:forEach>
            </div>

            <aui:button cssClass="btn-icon icon icon-plus icon-2x" type="button" onClick="addPrefilter(${param.index});"/>
        </aui:fieldset>
    </aui:fieldset>
</aui:fieldset-group>
