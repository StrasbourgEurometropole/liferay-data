<%@ include file="/ejob-bo-init.jsp"%>

<%-- Champ : Catégories   --%>
    <aui:select cssClass="toCustomSelect" id="ejobCategory${param.index}" name="ejobCategory${param.index}" label="ejobCategory" required="true" onChange="changeHandlerEjobCategory(this)">
        <aui:option style="display: none" selected="${empty dc.offer}"><liferay-ui:message key="choose-category" /></aui:option>
        <c:forEach items="${dc.categories}" var="category">
            <aui:option data-categorie="${category.name}" value="${category.categoryId}" selected="${gradeRange[0].categoryId == category.categoryId}">${category.name}</aui:option>
        </c:forEach>
    </aui:select>
	<div class="offerCategoryError" style="display: none">
		<liferay-ui:message key="this-field-is-required" />
	</div>

<%-- Champ : Filières   --%>
    <aui:select cssClass="toCustomSelect" id="ejobFiliere${param.index}" name="ejobFiliere${param.index}" label="ejobFiliere" required="true" onChange="changeHandlerEjobFiliere(this)" wrapperCssClass="unvisible">
        <aui:option style="display: none" selected="${empty dc.offer}"><liferay-ui:message key="choose-filiere" /></aui:option>
        <c:forEach items="${dc.filieres}" var="filiereWithCategories">
            <c:set value="${filiereWithCategories.get(0)}" var="filiere" />
            <c:set value="${filiereWithCategories.get(1)}" var="categories" />
            <aui:option data-categories="${categories}" value="${filiere.categoryId}" selected="${gradeRange[1].categoryId == filiere.categoryId}">${filiere.name}</aui:option>
        </c:forEach>
    </aui:select>
	<div class="offerFiliereError" style="display: none">
		<liferay-ui:message key="this-field-is-required" />
	</div>

<%-- Champ : Choix du grade minimum  --%>
	<div class="offerGradeMinMaxError" style="display: none">
		<liferay-ui:message key="grades-should-have-same-filiere-category" />
	</div>
    <aui:select cssClass="toCustomSelect" id="ejobGradeMin${param.index}" name="ejobGradeMin${param.index}" label="ejobGradeMin" required="true" wrapperCssClass="unvisible">
        <aui:option style="display: none" selected="${empty dc.offer}"><liferay-ui:message key="choose-grade" /></aui:option>
        <c:forEach items="${dc.grades}" var="gradeWithCategories">
            <c:set value="${gradeWithCategories.get(0)}" var="grade" />
            <c:set value="${gradeWithCategories.get(1)}" var="categories" />
            <c:set value="${grade.parentCategory}" var="gradGroup" />
            <aui:option data-filiere-category-id="${grade.parentCategoryId}" data-filiere-id="${gradGroup.parentCategoryId}" data-categories="${categories}" value="${grade.categoryId}" selected="${gradeRange[2].categoryId == grade.categoryId}">
                ${grade.name} (${gradGroup.name})
            </aui:option>
        </c:forEach>
    </aui:select>
	<div class="offerGradeMinError" style="display: none">
		<liferay-ui:message key="this-field-is-required" />
	</div>

<%-- Champ : Choix du grade maximum  --%>
    <aui:select cssClass="toCustomSelect" id="ejobGradeMax${param.index}" name="ejobGradeMax${param.index}" label="ejobGradeMax" required="false" wrapperCssClass="unvisible">
        <aui:option selected="${empty dc.offer}"><liferay-ui:message key="choose-grade" /></aui:option>
        <c:forEach items="${dc.grades}" var="gradeWithCategories">
            <c:set value="${gradeWithCategories.get(0)}" var="grade" />
            <c:set value="${gradeWithCategories.get(1)}" var="categories" />
            <c:set value="${grade.parentCategory}" var="gradGroup" />
            <aui:option data-filiere-category-id="${grade.parentCategoryId}" data-filiere-id="${gradGroup.parentCategoryId}" data-categories="${categories}" value="${grade.categoryId}" selected="${gradeRange[3].categoryId == grade.categoryId}">
                ${grade.name} (${gradGroup.name})
            </aui:option>
        </c:forEach>
    </aui:select>
	<div class="offerGradeMaxError" style="display: none">
		<liferay-ui:message key="this-field-is-required" />
	</div>

<c:if test="${not empty fromAjaxGradeRange}">
	<aui:script>
		$('#grade-range-fields').trigger('gradeRangeCreated', ${param.index});
	</aui:script>
</c:if>