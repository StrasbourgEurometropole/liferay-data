<%@ include file="/social-init.jsp" %>

<!-- Zone Social Wall -->
<div class="mns-social-wall mns-p50">
    <div class="container">
        <div class="row">
            <div class="col-xs-12 mns-top-social-wall">
                <ul>
                    <li><a href="https://www.facebook.com/Marche.Noel.Strasbourg/" target="_blank"><span class="icon-facebook-with-circle"></span></a></li>
                    <li><a href="https://www.instagram.com/" target="_blank"><span class="icon-instagram-with-circle"></span></a></li>
                    <li><a href="https://twitter.com/strasbourg?lang=fr" target="_blank"><span class="icon-twitter-with-circle"></span></a></li>
                </ul>
                <h2><span>#Noel</span>Strasbourg</h2>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et doloremagna aliqua. Ut enim ad minim</p>
            </div>
            <div class="mns-list-social-wall mns-p50" data-egalize=".mns-bloc-social-wall > a">
            
            	<c:forEach var="post" items="${posts}" varStatus="loopStatus">
	                <div class="col-md-3 col-sm-6 col-xs-12">
	                    <div class="mns-bloc-social-wall">
	                        <a href="${post.url}" target="_blank">
	                            <figure class="include-zoom">
	                                <img src="${post.imageURL}" alt="Image réseaux sociaux" width="270" height="270" class="mns-feed" />
	                            </figure>
	                            <div>
	                                <span><img src="/o/noel-theme/images/strasbourg-feed.svg" alt="strasbourg logo social wall" width="58" height="58" /></span>
	                                <span class="account">@${post.username}</span>
	                                <p>${post.content}</p>
	                            </div>
	                        </a>
	                    </div>
	                </div>
                </c:forEach>

                <div class="col-xs-12 text-center">
                    <a href="https://www.instagram.com/" class="large-link" target="_blank">Retrouvez-nous sur instagram</a>
                </div>
            </div>
        </div>
    </div>
</div>