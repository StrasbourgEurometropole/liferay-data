<%@ include file="/search-asset-init.jsp" %>

<liferay-portlet:actionURL varImpl="searchActionURL" />


<!-- Formulaire -->
<div class="container mns-filtres">
	<div>
		<aui:form action="${searchActionURL}" method="get" name="fm" id="search-asset-form" cssClass="seu-view-filters row">
		 	<liferay-portlet:renderURLParams varImpl="searchActionURL" />
			<liferay-util:include page="/forms/${dc.searchForm}-form.jsp" servletContext="<%=application %>" />
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

		        	<%-- S'il n'y a pas de résultat, on affiche les 3 blocs --%>
                    <c:if test="${dc.total == 0}">
                        <div class="col-md-4 col-sm-6 col-xs-12" style="margin: 0;">
                            <liferay-portlet:runtime
                            portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
                            instanceId="first-wc-display" />
                        </div>
                        <div class="col-md-4 col-sm-6 col-xs-12" style="margin: 0;">
                            <liferay-portlet:runtime
                            portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
                            instanceId="second-wc-display" />
                        </div>
                        <div class="col-md-4 col-sm-6 col-xs-12" style="margin: 0;">
                            <liferay-portlet:runtime
                            portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
                            instanceId="third-wc-display" />
                        </div>
                    </c:if>

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
		        	        <%-- S'il y a 1 ou 2 résultat(s), on affiche le 1er bloc au début --%>
							<c:if test="${(dc.total lt 3 and index eq 0)}">
								<div class="col-md-4 col-sm-6 col-xs-12" style="margin: 0;">
									<liferay-portlet:runtime
						            portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
						            instanceId="first-wc-display" />
						        </div>
							</c:if>

							<liferay-ddm:template-renderer
							    className="${className}"
							    contextObjects="${dc.getTemplateContextObjects(entry)}"
							    displayStyle="${dc.templatesMap[entry.className]}"
							    displayStyleGroupId="${themeDisplay.scopeGroupId}"
							    entries="${dc.templateEntries }"
							>
								<liferay-asset:asset-display
									assetEntry="${entry}"
									assetRenderer="${entry.assetRenderer}"
									assetRendererFactory="${entry.assetRendererFactory}"
									template="abstract"
								/>
								
							</liferay-ddm:template-renderer>

		        	        <%-- S'il y a au moins 1 résultat, on affiche les autres blocs --%>
							<c:if test="${(dc.total gt 2 and dc.total lt 6 and index eq 1) or (dc.total gt 5 and index eq 4) }">
								<div class="col-md-4 col-sm-6 col-xs-12" style="margin: 0;"> 
									<liferay-portlet:runtime
						            portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
						            instanceId="first-wc-display" />
						        </div>
							</c:if>
							<c:if test="${(dc.total eq 1 and index eq 0) or (dc.total gt 1 and dc.total lt 10 and index eq (dc.total - 2)) or (dc.total gt 9 and index eq 7) }">
								<div class="col-md-4 col-sm-6 col-xs-12" style="margin: 0;">
									<liferay-portlet:runtime
						            portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
						            instanceId="second-wc-display" />
						        </div>
							</c:if>
							<c:if test="${(dc.total lt 3 and index eq (dc.total - 1)) or (dc.total gt 2 and dc.total lt 10 and index eq (dc.total - 1)) or (dc.total gt 9 and index eq 8) }">
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