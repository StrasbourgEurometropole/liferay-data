<%@ include file="/social-init.jsp" %>

<!-- Zone Social Wall -->
<div class="mns-social-wall mns-p50">
    <div class="container">
        <div class="row">
            <div class="col-xs-12 mns-top-social-wall">
            	<liferay-portlet:runtime
		            portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
		            instanceId="social-wall-christmas-top" />
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
	            	<liferay-portlet:runtime
			            portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
			            instanceId="social-wall-christmas-bottom" />
                </div>
            </div>
        </div>
    </div>
</div>