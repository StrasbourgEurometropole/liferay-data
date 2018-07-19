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
<<<<<<< HEAD
				
=======
					
				<portlet:resourceURL id="like" var="likeURL">
					<portlet:param name="commentId" value="${comment.commentId}"></portlet:param>
				</portlet:resourceURL>

				<portlet:resourceURL id="signaler" var="signalerURL">
					<portlet:param name="commentId" value="${comment.commentId}"></portlet:param>
				</portlet:resourceURL>

>>>>>>> 275f1d6a9e647c62843a36553e9957c0bfd6b477
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
<<<<<<< HEAD
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
											title="Répondre au commentaire">
											<liferay-ui:message key='comment-answer'/>
										</a>
										<a href="${hideComment}" title="Masquer le commentaire">
											<liferay-ui:message key='comment-hide'/>
										</a>
									</div>
=======
										title="Mettre j'aime &agrave; cette vid&eacute;o" onclick="callServeResource('${likeURL}','like');">${comment.like}</a> <a
										href="#pro-avis-dislike-pro" class="pro-dislike active" onclick="callServeResource('${likeURL}','dislike');"
										title="Mettre je n'aime pas &agrave; cette vid&eacute;o">${comment.dislike}</a>
									<div>
                                        <a href="#Repondre" class="pro-reponse" title="R&eacute;pondre au commentaire">R&eacute;pondre</a>
                                        <a href="#Signaler" title="Signaler le commentaire" onclick="callServeResource('${signalerURL}','signaler');">Signaler</a>
                                    </div>
>>>>>>> 275f1d6a9e647c62843a36553e9957c0bfd6b477
								</div>
								 
							</c:if>
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
								</c:choose>>
							</textarea>
						</div>
						<input type="hidden" id="parentCommentId" name="<portlet:namespace />parentCommentId"/>
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
	
	$("[href='#Repondre']").click(function(e){
		var OPName=$(this).data('username');
		var parentId=$(this).data('commentid');
		
		$("input[id='parentCommentId']").val(parentId);
 		$(".pro-reagir .pro-textearea>label").text('<liferay-ui:message key="comment-parent-answer" /> ' + OPName + ' :');
		$(".pro-reagir .pro-user-connected>.pro-btn-yellow").val('<liferay-ui:message key="comment-answer"/>');
		
		$(document).scrollTop($("#pro-link-commentaire").offset().top); 
	});
</aui:script>