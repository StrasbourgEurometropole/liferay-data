<%@ include file="/rubric-init.jsp" %>

<div class="rubric-pages">
	<c:forEach items="${childrenLayouts}" var="layout">
		<div class="rubric-page">
			<div class="rubric-page-image">
				<a href="${themeDisplay.pathFriendlyURLPublic}${layout.group.friendlyURL}${subLayout.friendlyURL}">
					<img src="${layout.expandoBridge.attributes["image"]}" />
				</a>
			</div>
			<div class="rubric-page-name">
				<a href="${themeDisplay.pathFriendlyURLPublic}${layout.group.friendlyURL}${subLayout.friendlyURL}">
					${layout.nameMap[locale]}
				</a>
			</div>
			<c:choose>
				<c:when test="${fn:length(layout.children) > 0}">
					<ul class="rubric-page-links" role="nav">
						<c:forEach items="${layout.children}" var="subLayout">
							<li>
								<a href="${themeDisplay.pathFriendlyURLPublic}${layout.group.friendlyURL}${subLayout.friendlyURL}">
									${subLayout.nameMap[locale]}
								</a>
							</li>
						</c:forEach>
					</ul>
				</c:when>
				<c:otherwise>
					<div class="rubric-page-description">
						<p>
							${layout.descriptionMap[locale]}
						</p>
					</div>
					<div class="rubric-page-read-more">
						<a href="${themeDisplay.pathFriendlyURLPublic}${layout.group.friendlyURL}${layout.friendlyURL}">
							<liferay-ui:message key="read-more" />
						</a>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</c:forEach>
</div>