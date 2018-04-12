<%@ include file="../objtp-init.jsp" %>


<main>
	<h1><liferay-ui:message key="gallery-found-objects" /></h1>
	<div id="objtp-grid-carrefour" style="position: relative;">
		<c:forEach items="${dc.objectCategories}" var="objectCategorie">	
		  	<div class="objtp-grid-item objtp-page">
			    <a href="" class="objtp-grid-item-visu">
			        <div class="objtp-grid-item-background"></div>
			        <div class="objtp-grid-item-title" data-dot="4" style="word-wrap: break-word;">
			        	${objectCategorie.getName()}
			        </div>
			    </a>
			</div>
		</c:forEach>
	</div>
</main>