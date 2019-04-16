<%@ include file="/slider-une-init.jsp"%>
<strasbourg-picker:slider name="classPK_${param.index}" value="${param.classPK}" />

<div class="buttons">
    <span class="move vignette-move" aria-hidden="true"></span>
    <span class="vignette-delete" aria-hidden="true" data-index="${param.index}"></span>
</div>