<%@ include file="/search-asset-init.jsp" %>

<liferay-portlet:actionURL varImpl="searchActionURL" />


<!-- Formulaire -->
<div class="container mns-filtres row">
	<div>
		<aui:form action="${searchActionURL}" method="get" name="fm" id="search-asset-form" cssClass="seu-view-filters">
		 	<liferay-portlet:renderURLParams varImpl="searchActionURL" />
			<liferay-util:include page="/forms/christmas-2018-experience-form.jsp" servletContext="<%=application %>" />
		</aui:form>
	</div>
</div>
<div class="small-container mns-m-filtres-search row">
    <div class="col-xs-12">
        <span class="label">Je viens...,Pendant...,Pour un(e)... ?</span>
        <span id="search-mobile-filtres" class="btn-filtre">Recherchez</span>
    </div>
</div>

<aui:form method="post" name="fm">
	<!-- RÃ©sultats -->
	<liferay-ui:search-container id="entriesSearchContainer"
				searchContainer="${dc.searchContainer}">
		<div class="mns-page-experience">
			<div class="container mns-listing-exp">
	            <div class="row" data-egalize=".mns-bloc-exp">
		        	<liferay-ui:search-container-results results="${dc.entries}" />
		        	<liferay-ui:search-container-row
						className="com.liferay.asset.kernel.model.AssetEntry"
						modelVar="entry" keyProperty="entryId" rowIdProperty="entryId" indexVar="index">
							<c:set var="className" value="${entry.className}" />
							<c:choose>
								<c:when test="${fn:contains(className, 'JournalArticle')}">
									<c:set var="className" value="com.liferay.asset.kernel.model.AssetEntry" />
								</c:when>
								<c:when test="${fn:contains(className, 'DLFileEntry')}">
									<c:set var="className" value="com.liferay.portal.kernel.repository.model.FileEntry" />
								</c:when>
							</c:choose>
							<liferay-ddm:template-renderer
							    className="${className}"
							    contextObjects="${dc.getTemplateContextObjects(entry)}"
							    displayStyle="${dc.templatesMap[entry.className]}"
							    displayStyleGroupId="${themeDisplay.scopeGroupId}"
							    entries="${dc.templateEntries }"
							>
								<liferay-ui:asset-display
									assetEntry="${entry}"
									assetRenderer="${entry.assetRenderer}"
									assetRendererFactory="${entry.assetRendererFactory}"
									template="abstract"
								/>
								
								
								
							</liferay-ddm:template-renderer>
							  
							<c:if test="${(dc.entriesCount gt 6 and index eq 4) or (dc.entriesCount gt 3 and dc.entriesCount lt 7 and index eq (dc.entriesCount - 4)) or (dc.entriesCount lt 4 and index eq 0) }">
								<div class="col-md-4 col-sm-6 col-xs-12" style="margin: 0;"> 
									<liferay-portlet:runtime
						            portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
						            instanceId="first-wc-display" />
						        </div>
							</c:if>
							<c:if test="${(dc.entriesCount gt 7 and index eq 7) or (dc.entriesCount lt 8 and index eq (dc.entriesCount - 1)) }">
								<div class="col-md-4 col-sm-6 col-xs-12" style="margin: 0;"> 
									<liferay-portlet:runtime
						            portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
						            instanceId="second-wc-display" />
						        </div>
							</c:if>
							<c:if test="${(dc.entriesCount gt 8 and index eq 8) or (dc.entriesCount lt 9 and index eq (dc.entriesCount - 1)) }">
								<div class="col-md-4 col-sm-6 col-xs-12" style="margin: 0;">
									<liferay-portlet:runtime
						            portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
						            instanceId="third-wc-display" />
						        </div>
							</c:if>
							
					</liferay-ui:search-container-row>
				</div>
				<liferay-ui:search-paginator searchContainer="${dc.searchContainer}" />
			</liferay-ui:search-container>
		</aui:form>
	</div>
</div>