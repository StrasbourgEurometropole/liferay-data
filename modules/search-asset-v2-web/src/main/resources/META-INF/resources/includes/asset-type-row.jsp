<%@ include file="/search-asset-init.jsp"%>

<!-- Type d'asset -->
<aui:fieldset-group markupView="lexicon">
    <aui:fieldset collapsed="false" collapsible="true" label="some-content" id="assetType${param.index}">

        <aui:input name="numAssetType${param.index}" value="${param.index}" type="hidden" />

        <aui:select id="classname_${param.index}" name="classname_${param.index}" label="" onChange="reinitializeBloc(${param.index})">
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
        <aui:fieldset collapsed="false" collapsible="true" label="template-and-url" id="template-and-url${param.index}">
            <aui:select id="templateKey_${param.index}" name="templateKey_${param.index}" label="" inlineField="true">
                <aui:option value="${false}" selected="true">
                    <liferay-ui:message key="select-a-template"/>
                </aui:option>
            </aui:select>
            <aui:input id="friendlyUrl_${param.index}" name="friendlyUrl_${param.index}" label="" type="text" placeholder="detail-friendly-url" inlineField="true"/>
        </aui:fieldset>

        <!-- Portée -->
        <aui:fieldset collapsed="false" collapsible="true" label="scope" id="scope${param.index}">
            <liferay-ui:message key="scope-explanations" />
            <select class="form-control" name="<portlet:namespace />scopeIds_${param.index}"
                    id="<portlet:namespace />scopeIds_${param.index}"multiple onChange="reinitializePrefilter(${param.index})">
                <option placeholder><liferay-ui:message key="select-scopes" /></option>
            </select>
        </aui:fieldset>

        <!-- Préfiltre -->
        <aui:fieldset collapsed="false" collapsible="true" label="prefilter" id="prefilter${param.index}">

            <div id="prefilters-content${param.index}">
                <aui:input name="nbPrefiltre${param.index}" type="hidden" value="${empty assetTypeData ? '1' : assetTypeData.assetPrefilterDataList.isEmpty() ? '1' : assetTypeData.assetPrefilterDataList.size()}" />

                <c:if test="${empty assetTypeData || empty assetTypeData.assetPrefilterDataList}">
                    <c:set var="prefilterData" value=""/>
                    <liferay-util:include page="/includes/prefilter-row.jsp" servletContext="<%=application %>">
                            <liferay-util:param name="assetTypeIndex" value="${param.index}" />
                            <liferay-util:param name="ruleIndex" value="0" />
                    </liferay-util:include>
                </c:if>

                <c:forEach items="${assetTypeData.assetPrefilterDataList}" var="prefilterData" varStatus="status">
                    <c:set var="prefilterData" value="${prefilterData}"/>
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
