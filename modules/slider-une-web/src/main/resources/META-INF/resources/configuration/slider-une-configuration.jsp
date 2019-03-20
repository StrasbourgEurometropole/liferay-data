<%@ include file="/slider-une-init.jsp"%>

<link rel="stylesheet" href="/o/sliderune/css/slider-une.css" />
<script src="/o/sliderune/js/Sortable.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@shopify/draggable@1.0.0-beta.8/lib/draggable.bundle.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@shopify/draggable@1.0.0-beta.8/lib/draggable.js"></script>

<liferay-portlet:actionURL portletConfiguration="true"
	var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="true"
	var="configurationRenderURL" />

<div class="container-fluid-1280 main-content-body">
	<aui:form action="${configurationActionURL}" method="post" name="fm">

		<aui:input name="cmd" type="hidden"
			value="update" />

		<aui:input name="redirect" type="hidden"
			value="${configurationRenderURL}" />

		<div id="vignettes" class="vignettes">

            <c:forEach begin="1" end="24" step="1" var="i">
                <div id="vignette_${i}" class="vignette" >
                    <liferay-util:include page="/configuration/slider-une-configuration-selectors.jsp" servletContext="<%=application %>">
                        <liferay-util:param name="index" value="${i}" />
                        <liferay-util:param name="classPK" value="${classPKs[i - 1]}" />
                    </liferay-util:include>
                </div>
            </c:forEach>

        </div>

        <div class="additionalPicker" aria-hidden="true" >
            <div id="vignette_25" class="vignette" >
                <liferay-util:include page="/configuration/slider-une-configuration-selectors.jsp" servletContext="<%=application %>">
                    <liferay-util:param name="index" value="25" />
                    <liferay-util:param name="classPK" value="" />
                </liferay-util:include>
            </div>
        </div>


		<aui:button-row>
			<aui:button type="submit"></aui:button>
		</aui:button-row>
	</aui:form>
</div>

<script>
    new Sortable(vignettes, {
        handle: '.vignette-move',
        animation: 150,

        // Change la valeur des ids et names des itemspickers
        onSort: function (evt) {
            reindex();
        }
    });

    $('.vignette-delete').on('click', function(event){
        var index = $(this).attr('data-index');
        $('#vignette_' + index).remove();
        $('.vignettes').append($('#vignette_25').clone());
        reindex();
    });

    function reindex(){
        $('.vignette').each(function(i, value) {
            var index = i + 1;
            $(value).attr('id', "vignette_" + index);
            $(value).find('.detail').attr('id', $(value).find('.detail').attr('id').split("classPK_")[0] + "classPK_" + index);
            $(value).find('.eventButton').attr('id', $(value).find('.eventButton').attr('id').split("classPK_")[0] + "classPK_" + index);
            $(value).find('.eventButton').attr('name', $(value).find('.eventButton').attr('name').split("classPK_")[0] + "classPK_" + index);
            $(value).find('.articleButton').attr('id', $(value).find('.articleButton').attr('id').split("classPK_")[0] + "classPK_" + index);
            $(value).find('.articleButton').attr('name', $(value).find('.articleButton').attr('name').split("classPK_")[0] + "classPK_" + index);
            $(value).find('.has-error > input').attr('id', $(value).find('.has-error > input').attr('id').split("classPK_")[0] + "classPK_" + index);
            $(value).find('.has-error > input').attr('name', $(value).find('.has-error > input').attr('name').split("classPK_")[0] + "classPK_" + index);
            $(value).find('.vignette-delete').attr('data-index', index);
        });
    };

</script>