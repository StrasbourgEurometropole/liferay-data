<%@ include file="/comments-init.jsp"%>

<portlet:actionURL var="postComment" name="postComment">
	<portlet:param name="mvcPath" value="/comments-view.jsp"></portlet:param>
	<portlet:param name="entryID" value="${entryID}"></portlet:param>
</portlet:actionURL>

<section id="pro-link-commentaire"
	class="container pro-bloc-commentaires">
	<h2>
		<liferay-ui:message key="javax.portlet.title" />
	</h2>
	<div>
		<div class="col-md-8">
			<c:forEach var="comment" items="${comments}">
				<portlet:actionURL name="hideComment" var="hideComment">
						<portlet:param name="mvcPath" value="/comments-view.jsp"></portlet:param>
						<portlet:param name="commentId" value="${comment.commentId}"></portlet:param>
					</portlet:actionURL>
				<div class="pro-item">
					<div class="pro-txt">
						<span class="pro-name">${comment.getPublikUserName()}</span> <span
							class="pro-comment-time"><liferay-ui:message
								key="comment-published" /> <time
								datetime="${comment.createDate}">
								<fmt:formatDate type="date" value="${comment.createDate}"
									pattern="dd MMM yyyy" />
							</time></span>
						<div class="pro-comment">
							<p>${comment.comment}</p>
							<c:if test="${isAdmin}">
								<div class="pro-interactions">
									<div><a href="${hideComment}" title="Masquer le commentaire">Masquer</a>
									</div>
								</div>
							</c:if>
						</div>
					</div>
				</div>

			</c:forEach>
		</div>
		<div class="col-md-4">
			<div class="pro-reagir">
				<div>
					<form method="post" action="${postComment}"
						class="pro-user-connected">

						<div class="pro-textearea">
							<label for="message"><liferay-ui:message
									key="comment-your-comment" /></label>
							<textarea id="message" name="<portlet:namespace />message"
								placeholder="<liferay-ui:message key='comment-write-your-comment-here'/>"></textarea>
						</div>
						<input type="submit" class="pro-btn-yellow" value="Envoyer" />
					</form>
				</div>
			</div>
		</div>
	</div>
</section>