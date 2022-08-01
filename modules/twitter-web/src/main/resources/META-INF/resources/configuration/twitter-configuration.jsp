<%@ include file="/twitter-init.jsp"%>

<liferay-portlet:actionURL portletConfiguration="true"
	var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="true"
	var="configurationRenderURL" />

<aui:form action="${configurationActionURL}" method="post" name="fm">
    <aui:input name="cmd" type="hidden"
        value="update" />

    <aui:input name="redirect" type="hidden"
        value="${configurationRenderURL}" />

    <div class="portlet-configuration-body-content">
        <div class="container-fluid-1280">
            <aui:fieldset-group markupView="lexicon">

                <aui:fieldset id="twitter" label="twitter">
                    <aui:input type="text" name="twitterAccount" value="${twitterAccount}" label="tw.twitter-account" />
                    <aui:input type="number" name="tweetCount" value="${tweetCount}" label="tw.tweet-count" />
                </aui:fieldset>

            </aui:fieldset-group>
        </div>
    </div>

    <aui:button-row>
        <aui:button type="submit"></aui:button>
    </aui:button-row>
</aui:form>