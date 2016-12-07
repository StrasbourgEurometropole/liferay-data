<%@ include file="/search-asset-init.jsp" %>

<liferay-portlet:actionURL varImpl="searchActionURL" />

<div class="search-asset-portlet-page">
	<div class="search-asset-form">
		<aui:form action="${searchActionURL}" method="post" name="fm" id="search-asset-form">
			<liferay-util:include page="/forms/museum-form.jsp" servletContext="<%=application %>" />
		</aui:form>
	</div>
	<div class="search-asset-search-container">
		<liferay-util:include page="/form-headers/museum-form-header.jsp" servletContext="<%=application %>" />
		<aui:form method="post" name="fm">
			<aui:input type="hidden" name="selectionIds" />
			<liferay-ui:search-container id="entriesSearchContainer"
				searchContainer="${dc.searchContainer}">
				<div class="search-asset-results">
					<liferay-ui:search-container-results results="${dc.entries}" />
			
					<liferay-ui:search-container-row
						className="com.liferay.asset.kernel.model.AssetEntry"
						modelVar="entry" keyProperty="entryId" rowIdProperty="entryId">
							<%
								Map<String, Object> contextObjects = new HashMap<String, Object>();
								contextObjects.put("entry", entry.getAssetRenderer().getAssetObject());
								List<Object> entries = new ArrayList<Object>();
							%>
							<div>
								<liferay-ddm:template-renderer
								    className="${entry.className}"
								    contextObjects="<%=contextObjects%>"
								    displayStyle="${dc.templatesMap[entry.className]}"
								    displayStyleGroupId="${themeDisplay.scopeGroupId}"
								    entries="<%=entries%>"
								>
									<liferay-ui:asset-display
										assetEntry="${entry}"
										assetRenderer="${entry.assetRenderer}"
										assetRendererFactory="${entry.assetRendererFactory}"
										template="abstract"
									/>
								</liferay-ddm:template-renderer>
							</div>
					</liferay-ui:search-container-row>
				</div>
				<liferay-ui:search-paginator searchContainer="${dc.searchContainer}" />
			</liferay-ui:search-container>
		</aui:form>
	</div>
</div>