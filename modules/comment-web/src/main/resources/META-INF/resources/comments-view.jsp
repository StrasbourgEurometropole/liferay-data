<%@ include file="/comments-init.jsp"%>
<%@ include file="/report-modal.jsp"%>

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

				<portlet:actionURL name="reportComment" var="reportComment">
					<portlet:param name="mvcPath" value="/comments-view.jsp"></portlet:param>
					<portlet:param name="commentId" value="${comment.commentId}"></portlet:param>
				</portlet:actionURL>

				<div id="${comment.commentId}" class="pro-item">
					<div class="pro-txt">
						<span class="pro-name">${comment.getPublikUserName()}</span> <span
							class="pro-comment-time"><liferay-ui:message
								key="comment-published" /> <time
								datetime="${comment.createDate}">
								<fmt:formatDate type="date" value="${comment.createDate}"
									pattern="dd MMM yyyy" />
							</time></span>
						<div class="pro-comment">
							<p id="comment-${comment.commentId}">${comment.comment}</p>
							<div class="pro-interactions">
								<a href="#pro-avis-like-pro" class="pro-like"
									data-typeid="16"
	                                data-isdislike="false"
	                                data-title="Comment of ${comment.getPublikUserName()}"
	                                data-entityid="${comment.commentId}"
	                                data-entitygroupid="${comment.groupId}"
									title="Aimer ce commentaire">
									${comment.nbLikes}
								</a>
                                   <a href="#pro-avis-dislike-pro" class="pro-dislike"
                                   	data-typeid="16"
	                                data-isdislike="true"
	                                data-title="Comment of ${comment.getPublikUserName()}"
	                                data-entityid="${comment.commentId}"
	                                data-entitygroupid="${comment.groupId}"
                                   	title="Ne pas aimer ce commentaire">
                                   	${comment.nbDislikes}
                                   </a>
								<div>
									<a href="#Repondre" class="pro-reponse"
										data-commentid="${comment.commentId}"
										data-username="${comment.getPublikUserName()}"
										title="Repondre au commentaire">
										<liferay-ui:message key='comment-answer'/>
									</a>
									<c:if test="${comment.publikId == userPublikId}">
										<a href="#Modifier"
											data-commentid="${comment.commentId}"
											title="Repondre au commentaire">
											<liferay-ui:message key='comment-edit'/>
										</a>
									</c:if>
										<c:if test="${isAdmin}">
										<a href="${hideComment}" title="Masquer le commentaire">
											<liferay-ui:message key='comment-hide'/>
										</a>
									</c:if>
								</div>
								<div>
                                    <a href="#report" title="Signaler le commentaire"
                                        data-commentid="${comment.commentId}"
                                        >Signaler</a>
                                    <c:if test="${userPublikId eq comment.publikId}">
                                    	<a href="#Supprimer" title="Supprimer mon commentaire" onclick="deleteMessage('${comment.commentId}');">Supprimer</a>
                                    </c:if>
                                </div>

							</div>
						</div>
						
						<div class="pro-comment-response" style="padding-left: 50px">
							<c:forEach var="commentAnswer" items="${comment.getApprovedChildComments()}">
								<div>
									<p style="margin-bottom: 7px">
										<strong>${commentAnswer.getPublikUserName()}</strong>
										<liferay-ui:message key="comment-answered" /> 
										<time datetime="${commentAnswer.createDate}">
										<fmt:formatDate type="date" 
											value="${commentAnswer.createDate}"
											pattern="dd MMM yyyy" />
									</p>
									<p>${commentAnswer.comment}</p>
								</div>
							</c:forEach>
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
							<label for="message"><liferay-ui:message key="comment-your-comment" /></label>
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
								</c:choose>
							></textarea>
						</div>
						<input type="hidden" id="parentCommentId" name="<portlet:namespace />parentCommentId"/>
						<input type="hidden" id="editCommentId" name="<portlet:namespace />editCommentId"/>
						<input type="submit" class="pro-btn-yellow" value="Envoyer" />
					</form>
				</div>
			</div>
		</div>
	</div>
</section>

<aui:script>
    $("a[href='#report']").click(function(e){
        var commentId=$(this).data('commentid');
        $("input[id='commentId']").val(commentId);
        e.preventDefault();
        $("#signalementModal").modal();
    });
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
	
	$("[href='#Repondre']").click(function(e){
		var OPName = $(this).data('username');
		var parentId = $(this).data('commentid');
		
		$("input[id='parentCommentId']").val(parentId);
		$("input[id='editCommentId']").val(0);
		$(".pro-reagir .pro-textearea>textarea").text("");
 		$(".pro-reagir .pro-textearea>label").text('<liferay-ui:message key="comment-parent-answer" /> ' + OPName + ' :');
		$(".pro-reagir .pro-user-connected>.pro-btn-yellow").val('<liferay-ui:message key="comment-answer"/>');
		
		$(document).scrollTop($("#pro-link-commentaire").offset().top);
	});
	
	$("[href='#Modifier']").click(function(e){
		var commentId = $(this).data('commentid');
		var baseMsg = $("p[id=comment-" + commentId + "]").text();

		$("input[id='editCommentId']").val(commentId);
		$("input[id='parentCommentId']").val(0);
		$(".pro-reagir .pro-textearea>textarea").text(baseMsg);
		$(".pro-reagir .pro-textearea>label").text('<liferay-ui:message key="comment-edit-comment" />');
		$(".pro-reagir .pro-user-connected>.pro-btn-yellow").val('<liferay-ui:message key="comment-edit"/>');

		$(document).scrollTop($("#pro-link-commentaire").offset().top);
	});

	function deleteMessage(commentId){
		if (confirm('êtes-vous sûr de vouloir supprimer votre message ?')) {
			//
		}
	}
</aui:script>