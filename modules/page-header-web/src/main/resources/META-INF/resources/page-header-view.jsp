<%@ include file="/page-header-init.jsp" %>

<div class="page-header <c:if test="${displayImage}">with-image</c:if>
<c:if test="${alternativeTheme}">alt-theme</c:if>">
	<c:if test="${displayImage}">
		<div class="page-header-image">
			<img src="${layoutImage}" />
		</div>
	</c:if>
	<c:if test="${fn:length(imageCredit) > 0}">
		<div class="page-header-image-credit">
			${imageCredit}
		</div>
	</c:if>
	<div class="page-header-info">
		<div class="page-header-title-and-share">
			<div class="page-header-title">
				<h1>${layoutName}</h1>
			</div>
			<c:if test="${displayShareButtons}">
				<div class="page-header-share">
					<span><liferay-ui:message key="share" /></span>
					<div class="page-header-share-buttons">
						<div class="facebook"></div>
						<div class="twitter"></div>
					</div>
				</div>
			</c:if>
		</div>
		<div class="page-header-description">
			${layoutDescription}
		</div>
	</div>
</div>