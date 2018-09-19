<%@ include file="/comments-init.jsp"%>
<%@ include file="/modal/report-modal.jsp"%>
<%@ include file="/modal/delete-modal.jsp"%>

<portlet:actionURL var="postComment" name="postComment">
	<portlet:param name="mvcPath" value="/comments-view.jsp"></portlet:param>
	<portlet:param name="redirectURL" value="${redirectURL}"></portlet:param>
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
					<portlet:param name="redirectURL" value="${redirectURL}"></portlet:param>
					<portlet:param name="commentId" value="${comment.commentId}"></portlet:param>
				</portlet:actionURL>
				
				<div id="${comment.commentId}" class="pro-item">
					<div class="pro-txt">
						<span class="pro-name">${comment.getPublikUserName()}</span>
						<span class="pro-comment-time">
							<liferay-ui:message key='comment-published' />
							<time datetime="${comment.createDate}">
								<fmt:formatDate type="date" value="${comment.createDate}" pattern="dd MMM yyyy" />
							</time>
						</span>
						<c:if test="${comment.userQuality != null and comment.userQuality != ''}">
							<span class="pro-fonction">
								<liferay-ui:message key='comment-in-quality-of' /> ${comment.userQuality}
							</span>
						</c:if>
						<div class="pro-comment">
							<p id="comment-${comment.commentId}">${comment.comment}</p>
							<div class="pro-interactions">
								<c:choose>
									<c:when test="${!isUserBanned && hasUserSigned}">
										<a href="#pro-avis-like-pro" class="pro-like"
											data-typeid="16" 
			                                data-isdislike="false"
			                                data-title="Comment of ${comment.getPublikUserName()}"
			                                data-entityid="${comment.commentId}"
			                                data-entitygroupid="${comment.groupId}"
											title="Approuver ce commentaire">
											${comment.nbLikes}
										</a>
		                                   <a href="#pro-avis-dislike-pro" class="pro-dislike" 
		                                   	data-typeid="16" 
			                                data-isdislike="true"
			                                data-title="Comment of ${comment.getPublikUserName()}" 
			                                data-entityid="${comment.commentId}"
			                                data-entitygroupid="${comment.groupId}"
		                                   	title="Desapprouver ce commentaire">
		                                   	${comment.nbDislikes}
		                                </a>
										<div class="pro-action-link">
											<a href="#Repondre" class="pro-reponse"
												data-commentid="${comment.commentId}"
												data-username="${comment.getPublikUserName()}"
												title="Repondre au commentaire">
												<liferay-ui:message key='comment-answer'/>
											</a>
											<a href="#report" 
												title="Signaler le commentaire" 
												data-commentid="${comment.commentId}">
												<liferay-ui:message key='comment-report'/>
											</a>
											<c:if test="${isAdmin}">
												<a href="${hideComment}" title="Masquer le commentaire">
													<liferay-ui:message key='comment-hide'/>
												</a>
											</c:if>
										</div>
										<c:if test="${userPublikId eq comment.publikId}">
											<div class="pro-action-comm">
		                                        <a href="#Modifier"
													data-commentid="${comment.commentId}"
													title="Modifier mon commentaire">
		                                        	<span class="icon-ico-modifier"></span>
		                                        </a>
		                                        <a href="#Supprimer" 
		                                        	title="Supprimer mon commentaire" 
		                                        	data-commentid="${comment.commentId}">
		                                        	<span class="icon-ico-remove"></span>
		                                        </a>
		                                    </div>
		                                </c:if> 
		                                   
		                            </c:when>
		                            <c:otherwise>
		                            	<a class="pro-like" data-target="#myModal">${comment.nbLikes}</a>
		                                <a class="pro-dislike" data-target="#myModal">${comment.nbDislikes}</a>
		                       		</c:otherwise>
	                        	</c:choose>
							</div>							
						</div>
						<c:if test="${comment.modifiedByUserDate != null}">
							<p class="pro-label-edition">
								<liferay-ui:message key='comment-edited-on' />
								<fmt:formatDate type="date" value="${comment.modifiedByUserDate}" pattern="dd MMMM yyyy" />
								<liferay-ui:message key='comment-edited-at' />
								<fmt:formatDate type="date" value="${comment.modifiedByUserDate}" pattern="HH:mm:ss" />
							</p>
						</c:if>
						
						<!-- Réponse du commentaire -->
						<div class="pro-comment-response" style="padding-left: 50px">
							<c:forEach var="commentAnswer" items="${comment.getApprovedChildComments()}">
							
								<portlet:actionURL name="hideComment" var="hideComment">
									<portlet:param name="mvcPath" value="/comments-view.jsp"></portlet:param>
									<portlet:param name="redirectURL" value="${redirectURL}"></portlet:param>
									<portlet:param name="commentId" value="${commentAnswer.commentId}"></portlet:param>
								</portlet:actionURL>
							
								<div id="${commentAnswer.commentId}" style="margin-bottom: 20px">
									<p style="margin-bottom: 7px">
										<strong>${commentAnswer.getPublikUserName()}</strong>
										<liferay-ui:message key="comment-answered" /> 
										<time datetime="${commentAnswer.createDate}">
										<fmt:formatDate type="date" 
											value="${commentAnswer.createDate}"
											pattern="dd MMM yyyy" />
									</p>
									<p id="comment-${commentAnswer.commentId}">${commentAnswer.comment}</p>
									<c:if test="${!isUserBanned && hasUserSigned}">
										<div class="pro-interactions">
	                                        <div class="pro-action-link">
	                                            <a href="#report" title="Signaler le commentaire" data-commentid="${commentAnswer.commentId}">
	                                           		<liferay-ui:message key='comment-report'/>
	                                            </a>
	                                            <c:if test="${isAdmin}">
	                                            	<a href="${hideComment}" title="Masquer le commentaire">
	                                            		<liferay-ui:message key='comment-hide'/>
	                                            	</a>
	                                            </c:if> 
	                                        </div>
	                                        <c:if test="${userPublikId eq commentAnswer.publikId}">
		                                        <div class="pro-action-comm">
		                                            <a href="#Modifier" data-commentid="${commentAnswer.commentId}">
		                                            	<span class="icon-ico-modifier"></span>
		                                            </a>
		                                            <a href="#Supprimer" data-commentid="${commentAnswer.commentId}">
		                                            	<span class="icon-ico-remove"></span>
		                                            </a>
		                                        </div>
		                                	</c:if>
	                                    </div>
	                                </c:if>
	                                <c:if test="${commentAnswer.modifiedByUserDate != null}">
										<p class="pro-label-edition">
											<liferay-ui:message key='comment-edited-on' />
											<fmt:formatDate type="date" value="${commentAnswer.modifiedByUserDate}" pattern="dd MMMM yyyy" />
											<liferay-ui:message key='comment-edited-at' />
											<fmt:formatDate type="date" value="${commentAnswer.modifiedByUserDate}" pattern="HH:mm:ss" />
										</p>
									</c:if>
								</div>
								
							</c:forEach>
						</div>
						
					</div>
				</div>

			</c:forEach>
		</div>
		
		<c:choose>
			<c:when test="${!isUserBanned && isAssetCommentable}">
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
									<label for="inQualityOf"><liferay-ui:message key="comment-your-quality" /></label>
									<input type="text" id="inQualityOf"
										name="<portlet:namespace />inQualityOf"
										placeholder="<liferay-ui:message key='comment-write-your-quality-here'/>"
									/>
								</div>
								<input type="hidden" id="parentCommentId" name="<portlet:namespace />parentCommentId"/>
								<input type="hidden" id="editCommentId" name="<portlet:namespace />editCommentId"/>

								<input type="submit" class="pro-btn-yellow" value="Envoyer" />
							</form>
						</div>
					</div>
				</div>
			</c:when>
		    <c:otherwise>
		    	<div class="col-md-4">
					<div class="pro-reagir">
						<div>
							<form>
								<div class="pro-textearea">
									<label><liferay-ui:message key='comment-uncommentable'/></label>
								</div>
							</form>
						</div>
					</div>
				</div>
		    </c:otherwise>
		</c:choose>
	</div>
</section>

<aui:script>
	// Gestion de l'affichage et du controle de l'action de signalement
    $("a[href='#report']").click(function(e){
        var commentId=$(this).data('commentid');
        $("input[id='commentId']").val(commentId);
        e.preventDefault();
        $("#modalSignaler").modal();
    });

	 $("a[href='#Supprimer']").click(function(e){
        var commentId=$(this).data('commentid');
        $("input[id='commentId']").val(commentId);
        e.preventDefault();
        $("#modalSupprimer").modal();
     });

	// Gestion de l'affichage et du controle de l'action de post du commentaire
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
	
	// Gestion du controle de la saisie du commentaire
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
	
	// Gestion de l'affichage de la reponse
	$("[href='#Repondre']").click(function(e){
		var OPName = $(this).data('username');
		var parentId = $(this).data('commentid');
		
		$("input[id='parentCommentId']").val(parentId);
		$("input[id='editCommentId']").val(0);
		$(".pro-reagir .pro-textearea>textarea").text("");
		$(".pro-reagir .pro-textearea>input").show();
		$("label[for='inQualityOf']").show();
 		$("label[for='message']").text('<liferay-ui:message key="comment-parent-answer" /> ' + OPName + ' :');
		$(".pro-reagir .pro-user-connected>.pro-btn-yellow").val('<liferay-ui:message key="comment-answer"/>');
		
		$(document).scrollTop($("#pro-link-commentaire").offset().top);
	});
	
	// Gestion de l'affichage de la modification
	$("[href='#Modifier']").click(function(e){
		var commentId = $(this).data('commentid');
		var baseMsg = $("p[id=comment-" + commentId + "]").text();

		$("input[id='editCommentId']").val(commentId);
		$("input[id='parentCommentId']").val(0);
		$(".pro-reagir .pro-textearea>textarea").text(baseMsg);
		$(".pro-reagir .pro-textearea>textarea").focus();
		$(".pro-reagir .pro-textearea>input").hide();
		$("label[for='inQualityOf']").hide();
		$("label[for='message']").text('<liferay-ui:message key="comment-edit-comment" />');
		$(".pro-reagir .pro-user-connected>.pro-btn-yellow").val('<liferay-ui:message key="comment-edit"/>');

		$(document).scrollTop($("#pro-link-commentaire").offset().top);
	});

</aui:script>