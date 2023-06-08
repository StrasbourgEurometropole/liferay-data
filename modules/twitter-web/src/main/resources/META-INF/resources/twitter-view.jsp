<%@ include file="/twitter-init.jsp" %>

<c:choose>
	<c:when test="${empty twitterAccount or empty tweetCount or tweetCount lt 1}">
		<liferay-ui:message key="no-config" />
	</c:when>
	<c:otherwise>
		<section id="twitter" style="background-image:url(/o/sae-theme/images/background/bg_twitter.jpg);">
			<div class="container">
				<div class="content-center"><h3><liferay-ui:message key="tw.follow-us" /></h3></div>
				<div class="flex">
					<c:forEach items="${requestScope.tweets}" var="tweet">
						<div class="item">
							<c:if test="${tweet.retweet}">
								<div class="retweet">
									<a href="https://twitter.com/${tweet.screenName}">${tweet.userName} <liferay-ui:message key="tw.retweeted" /></a>
								</div>									
							</c:if>
							<div class="pseudo">
								<i><img src="/o/sae-theme/images/pictos/twitter-hover.png" alt="" class="on"><img class="off" src="/o/sae-theme/images/pictos/twitter.png" alt=""></i>
								<p style="word-wrap: break-word;">
									<c:if test="${tweet.retweet}">
										<span style="word-wrap: break-word;"><a href="https://twitter.com/${tweet.retweetScreenName}">${tweet.retweetUserName}</a></span><a href="https://twitter.com/${tweet.retweetScreenName}">@${tweet.retweetScreenName}</a>
									</c:if>
									<c:if test="${not tweet.retweet}">
										<span style="word-wrap: break-word;"><a href="https://twitter.com/${tweet.screenName}">${tweet.userName}</a></span><a href="https://twitter.com/${tweet.screenName}">@${tweet.screenName}</a>
									</c:if>
									 - <a href="${tweet.url}">${tweet.displayDate} <liferay-ui:message key="tw.${tweet.displayDateUnit}" /></a>
								</p> 
							</div>
							<div class="post">
								<p>
									${tweet.textWithLinks}
								</p>
							</div>
						</div>
					</c:forEach>
				</div>
				<div class="social-network">
				    <span><liferay-ui:message key="tw.partage" /></span>
                    <ul>
                        <li><a class="btn-fb" href="https://www.facebook.com/strasetudiants" target="_blank"></a></li>
                        <li><a class="btn-twitter" href="//twitter.com/StrasEtudiants" target="_blank"></a></li>
                        <li><a class="btn-insta" href="//www.instagram.com/strasetudiants" target="_blank"></a></li>
						<li><a class="btn-linkdin" href="//www.linkedin.com/showcase/strasbourg-aime-ses-%C3%A9tudiants/" target="_blank"></a></li>

					</ul>
                </div>
			</div>
		</section>
		<script src="//twemoji.maxcdn.com/twemoji.min.js"></script>
		<script>
			var tweetSection = document.querySelector('#twitter');
			twemoji.parse(tweetSection, {
				size: "16x16"
			});
		</script>	
	</c:otherwise>
</c:choose>