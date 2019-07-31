<%@ include file="/search-association-init.jsp" %>


<liferay-portlet:actionURL varImpl="searchActionURL" />

<main class="seu-container">
	<div class="seu-view-agenda">
		<!-- Formulaire -->
		<aui:form action="${searchActionURL}" method="get" name="fm" id="search-asset-form" cssClass="seu-view-filters">
		 	<liferay-portlet:renderURLParams varImpl="searchActionURL" />
	        <liferay-ui:error key="practice-error" message="practice-error" />
	        <p class="error" style="display: none; color:#ff1818;"><liferay-ui:message key="practice-error" /></p>

            <div class="seu-filter-line">
                <div class="widget">
                    <div class="title content">
                        <aui:select cssClass="toCustomSelect domains" id="domain" name="domain" label="domain">
                            <aui:option value="" disabled="disabled" />
                            <c:forEach items="${dc.getSortedCategories(dc.domainVocabulary)}" var="category">
                                <c:set var="category" value="${category}" scope="request" />
                                <c:set var="level" value="0" scope="request" />
                                <jsp:include page="/includes/category-option.jsp" />
                            </c:forEach>
                        </aui:select>
                    </div>
                </div>
                <div class="widget speciality" <c:if test="${empty param.domain}">style="display: none;" </c:if>>
                    <div class="title content">
                        <aui:select cssClass="toCustomSelect specialities" id="speciality" name="speciality" label="speciality">
                            <aui:option value="" disabled="disabled" />
                            <c:if test="${param.domain != null}">
                                <c:forEach items="${dc.getSortedCategories(dc.domainVocabulary, param.domain)}" var="category">
                                    <c:set var="category" value="${category}" scope="request" />
                                    <c:set var="level" value="0" scope="request" />
                                    <jsp:include page="/includes/category-option.jsp" />
                                </c:forEach>
                            </c:if>
                        </aui:select>
                    </div>
                </div>
                <div class="widget subSpeciality" <c:if test="${empty param.speciality}">style="display: none;" </c:if>>
                    <div class="title content">
                        <aui:select cssClass="toCustomSelect subSpecialities" id="subSpeciality" name="subSpeciality" label="sub-speciality">
                            <aui:option value="" disabled="disabled" />
                            <c:if test="${param.speciality != null}">
                                <c:forEach items="${dc.getSortedCategories(dc.domainVocabulary, param.speciality)}" var="category">
                                    <c:set var="category" value="${category}" scope="request" />
                                    <c:set var="level" value="0" scope="request" />
                                    <jsp:include page="/includes/category-option.jsp" />
                                </c:forEach>
                            </c:if>
                        </aui:select>
                    </div>
                </div>
                <div class="widget subSubSpeciality" <c:if test="${empty param.subSpeciality}">style="display: none;" </c:if>>
                    <div class="title content">
                        <aui:select cssClass="toCustomSelect subSubSpecialities" id="subSubSpeciality" name="subSubSpeciality" label="sub-speciality">
                            <aui:option value="" disabled="disabled" />
                            <c:if test="${param.subSpeciality != null}">
                                <c:forEach items="${dc.getSortedCategories(dc.domainVocabulary, param.subSpeciality)}" var="category">
                                    <c:set var="category" value="${category}" scope="request" />
                                    <c:set var="level" value="0" scope="request" />
                                    <jsp:include page="/includes/category-option.jsp" />
                                </c:forEach>
                            </c:if>
                        </aui:select>
                    </div>
                </div>
            </div>


            <div class="seu-filter-line">
                <c:forEach items="${dc.vocabularies}" var="vocabulary"
                    varStatus="vocStatus">
	                <div class="widget">
	                    <div class="title content">
	                        <aui:select cssClass="toCustomSelect" id="vocabulary_${vocStatus.index}" name="vocabulary_${vocStatus.index}" label="${not empty vocabulary.getDescription(locale) ? vocabulary.getDescription(locale) : vocabulary.getTitle(locale)}">
	                            <aui:option value="" disabled="disabled" />
                                <c:forEach items="${dc.getSortedCategories(vocabulary)}" var="category">
									<c:set var="category" value="${category}" scope="request" />
									<c:set var="level" value="0" scope="request" />
									<jsp:include page="/includes/category-option.jsp" />
                                </c:forEach>
	                        </aui:select>
	                    </div>
	                </div>
                </c:forEach>
            </div>
            <aui:input type="hidden" name="vocabulariesCount" value="${fn:length(dc.vocabularies) + 1}" />
            <div class="seu-btn-line">
                <liferay-portlet:renderURL var="formURL" />
                <button type="button" onclick="window.location.href = '${formURL}'" class="seu-btn-square seu-bordered seu-core">
                    <span class="seu-flexbox">
                        <span class="seu-btn-text" style="margin-right: 0">
                            <liferay-ui:message key="cancel" />
                        </span>
                    </span>
                </button>
                <button type="submit" class="seu-btn-square seu-filled seu-core">
                    <span class="seu-flexbox">
                        <span class="seu-btn-text">
                            <liferay-ui:message key="search" />
                        </span>
                        <span class="seu-btn-arrow"></span>
                    </span>
                </button>
            </div>
		</aui:form>

		<!-- Nombre de résultats et items par page -->
        <div class="seu-view-results">
            <div class="seu-result-count">${dc.searchContainer.total}
            	<c:choose>
            		<c:when test="${dc.searchContainer.total gt 1}">
            			<liferay-ui:message key="results" />
            		</c:when>
            		<c:otherwise>
            			<liferay-ui:message key="result" />
            		</c:otherwise>
            	</c:choose>
            </div>
            <div class="seu-filler"></div>
            <div class="seu-result-filter">
                <span><liferay-ui:message key="results-per-page" /></span>
                <select name="filter" id="" class="toCustomSelect" onchange="this.options[this.selectedIndex].value && (window.location = this.options[this.selectedIndex].value);">
                    <c:forEach var="delta" items="${[5, 10, 20, 50, 100]}">
                    	<c:set var="selected" value="${delta eq dc.delta ? 'selected' : ''}" />
                    	<option value="${dc.getURLForDelta(delta)}" ${selected} >${delta}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

		<aui:form method="post" name="fm">
			<!-- Résultats -->
			<liferay-ui:search-container id="entriesSearchContainer"
						searchContainer="${dc.searchContainer}">
				<ul id="seu-grid--list01">

		        	<liferay-ui:search-container-results results="${dc.entries}" />
		        	<liferay-ui:search-container-row
						className="com.liferay.asset.kernel.model.AssetEntry"
						modelVar="entry" keyProperty="entryId" rowIdProperty="entryId">
							<c:set var="className" value="${entry.className}" />
                            <liferay-ddm:template-renderer
                                className="${className}"
                                contextObjects="${dc.getTemplateContextObjects(entry)}"
                                displayStyle="${dc.displayStyle}"
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
					</liferay-ui:search-container-row>
				</ul>

				<!-- Pagination -->
				<c:if test="${dc.pager.lastPage > 1}">
		            <ul class="seu-pagination unstyled">
		            	<!-- Page précédente -->
		                <li class="seu-pagin-prev disabled seu-pagin-item">
							<c:if test="${not dc.pager.onFirstPage}">
			                    <a class="seu-btn-square seu-bordered seu-core" data-action="prev" title="<liferay-ui:message key="previous" />"
									href="${dc.getURLForPage(dc.pager.currentPage - 1)}">
			                        <span class="seu-flexbox">
			                            <span class="seu-btn-text"><liferay-ui:message key="previous" /></span>
			                            <span class="seu-btn-arrow"></span>
			                        </span>
			                    </a>
		               		</c:if>
		                </li>
		                <c:forEach var="page" items="${dc.pager.pages}">
		                	<c:choose>
		                		<c:when test="${page.isALink() and not (page.index eq dc.pager.currentPage)}">
		                			<!-- Lien vers page -->
			                		<li class="seu-pagin-item">
					                    <a data-page="${page.index}" href="${dc.getURLForPage(page.index)}">
					                        <span class="seu-flexbox">
					                            <span class="seu-btn-text">${page.label}</span>
					                        </span>
					                    </a>
					                </li>
		                		</c:when>
		                		<c:when test="${page.isALink() and (page.index eq dc.pager.currentPage)}">
		                			<!-- Page en cours -->
			                		<li class="seu-pagin-item seu-is-active">
				                        <span class="seu-flexbox">
				                            <span class="seu-btn-text">${page.label}</span>
				                        </span>
				                    </li>
				                </c:when>
		                		<c:otherwise>
				                	<!-- Texte -->
			                		<li class="seu-pagin-item">
				                        <span class="seu-flexbox">
				                            <span class="seu-btn-text">${page.label}</span>
				                        </span>
				                    </li>
		                		</c:otherwise>
		                	</c:choose>
		                </c:forEach>

		                <!-- Page suivante -->
		                <li class="seu-pagin-next seu-pagin-item">
							<c:if test="${not dc.pager.onLastPage}">
			                    <a class="seu-btn-square seu-bordered seu-core" title="<liferay-ui:message key="next" />"
			                    	data-action="next" href="${dc.getURLForPage(dc.pager.currentPage + 1)}">
			                        <span class="seu-flexbox">
			                            <span class="seu-btn-text"><liferay-ui:message key="next" /></span>
			                            <span class="seu-btn-arrow"></span>
			                        </span>
			                    </a>
		              	 	</c:if>
		                </li>
		            </ul>
		    	</c:if>

			</liferay-ui:search-container>
		</aui:form>
	</div>
</main>


<liferay-util:body-top>
    <aui:script>
		var homeURL = '${homeURL}';
		var delta = ${dc.delta};
    </aui:script>
</liferay-util:body-top>

<liferay-util:html-bottom>
	<script>
		define._amd = define.amd;
		define.amd = false;
	</script>
	<script	src="/o/associationsearchweb/js/search-association-main.js"></script>
	<script>
		define.amd = define._amd;
	</script>
</liferay-util:html-bottom>