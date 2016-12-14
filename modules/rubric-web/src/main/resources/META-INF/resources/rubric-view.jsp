<%@ include file="/rubric-init.jsp" %>

<div class="rubric-pages">
	<liferay-ddm:template-renderer
	    className="<%= Layout.class.getName() %>"
	    contextObjects="${contextObjects}"
	    displayStyle="${displayStyle}"
	    displayStyleGroupId="${displayStyleGroupId}"
	    entries="${childrenLayouts}"
	>
		<c:forEach items="${childrenLayouts}" var="layout">
			<c:if test="${not layout.hidden}">
				<div class="rubric-page">
					<div class="rubric-page-image">
						<a href="${themeDisplay.pathFriendlyURLPublic}${layout.group.friendlyURL}${layout.friendlyURL}">
							<img src="${layout.expandoBridge.attributes["image"]}" />
						</a>
					</div>
					<div class="rubric-page-name">
						<a href="${themeDisplay.pathFriendlyURLPublic}${layout.group.friendlyURL}${layout.friendlyURL}">
							${layout.nameMap[locale]}
						</a>
					</div>
					<c:choose>
						<c:when test="${layoutHelper.hasVisibleChildren(layout)}">
							<ul class="rubric-page-links" role="nav">
								<c:forEach items="${layout.children}" var="subLayout">
									<c:if test="${not subLayout.hidden}">
										<li>
											<a href="${themeDisplay.pathFriendlyURLPublic}${layout.group.friendlyURL}${subLayout.friendlyURL}">
												${subLayout.nameMap[locale]}
											</a>
										</li>
									</c:if>
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
			</c:if>
		</c:forEach>
	</liferay-ddm:template-renderer>
</div>