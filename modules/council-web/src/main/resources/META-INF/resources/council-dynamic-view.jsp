<%@ include file="/council-init.jsp"%>

<div class="council-flex council-web">
	<div class="detail-delib seu-container">

        <%@ include file="/templates/message.jsp"%>
        <%@ include file="/templates/presentation-delib.jsp"%>
        <%@ include file="/templates/result.jsp"%>
        <%@ include file="/templates/vote-form.jsp"%>

	</div>
</div>

<!-- Définition des variables à partager avec le JS -->
<liferay-util:html-top>
	<script>
	    var namespace = "<portlet:namespace />";
		var groupId = ${dc.groupId};
		var useSkypeView = ${dc.configuration.useSkypeView()};
		var officialConnectedId = ${dc.officialId};
	</script>
</liferay-util:html-top>

<!-- Import des JS -->
<liferay-util:html-bottom>
    <script src="/o/councilweb/js/council-dynamic-view.js" type="text/javascript"></script>
</liferay-util:html-bottom>