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
					
				<portlet:resourceURL id="like" var="likeURL">
					<portlet:param name="commentId" value="${comment.commentId}"></portlet:param>
				</portlet:resourceURL>

				<portlet:resourceURL id="signaler" var="signalerURL">
					<portlet:param name="commentId" value="${comment.commentId}"></portlet:param>
				</portlet:resourceURL>

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
									<a href="#pro-avis-like-pro" class="pro-like"
										title="Mettre j'aime &agrave; cette vid&eacute;o" onclick="callServeResource('${likeURL}','like');">${comment.like}</a> <a
										href="#pro-avis-dislike-pro" class="pro-dislike active" onclick="callServeResource('${likeURL}','dislike');"
										title="Mettre je n'aime pas &agrave; cette vid&eacute;o">${comment.dislike}</a>
									<div>
                                        <a href="#Repondre" class="pro-reponse" title="R&eacute;pondre au commentaire">R&eacute;pondre</a>
                                        <a href="#Signaler" title="Signaler le commentaire" onclick="callServeResource('${signalerURL}','signaler');">Signaler</a>
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
					<form id="form-comments" method="post" action="${postComment}"
						class="pro-user-connected">

						<div class="pro-textearea">
							<label for="message"><liferay-ui:message
									key="comment-your-comment" /></label>
							<textarea id="message" name="<portlet:namespace />message"
								<c:choose>
								  <c:when test="${!isUserloggedIn}">
								   	placeholder="<liferay-ui:message key='comment-please-connect'/>"
								  </c:when>
								  <c:when test="${!hasUserSigned}">
								    placeholder="<liferay-ui:message key='comment-please-sign'/>"
								  </c:when>
								  <c:otherwise>
								    placeholder="<liferay-ui:message key='comment-write-your-comment-here'/>"
								  </c:otherwise>
								</c:choose>></textarea>
						</div>
						<input type="submit" class="pro-btn-yellow" value="Envoyer" />
					</form>
				</div>
			</div>
		</div>
	</div>
</section>
<aui:script>
	$("#form-comments").submit(function(e){
	    if(!${isUserloggedIn}){
	    	e.preventDefault();
	    	$("#myModal").modal();
    	}
	    else if(!${hasUserSigned}){
	    	e.preventDefault();
	    	$("#myModal").modal();
	    }
    	else if($("#message").val().length == 0){
    		e.preventDefault();
    		alert('<liferay-ui:message key="comment-empty" />');
    	}
	});
	
	$("#message").click(function(e){
	    if(!${isUserloggedIn}){
	    	e.preventDefault();
	    	$("#myModal").modal();
    	}
	    else if(!${hasUserSigned}){
	    	e.preventDefault();
	    	$("#myModal").modal();
	    }
	});
	
	function callServeResource(portletURL, likedislike) {
			AUI().use('aui-io-request', function(A) {
			A.io.request(portletURL, {
				method : 'post',
				data : {
					<portlet:namespace/>action : likedislike
				}
			});
		});
	}
			
		
</aui:script>