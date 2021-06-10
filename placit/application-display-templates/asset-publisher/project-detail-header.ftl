<!-- DETAIL D'UN PROJET - ENTETE -->

<#setting locale = locale />
<#assign currEntry = entry.getAssetRenderer().getProject() />

<#assign timelines = currEntry.getProjectTimelines()>

<#if timelines?first??>
  <#assign firstTimeLine = currEntry.getProjectTimelines()?first>
  <#assign lastTimeLine = currEntry.getProjectTimelines()?last>
</#if>

<header>

	<!--  -->
    <figure class="fit-cover">
	    <img src="${currEntry.imageURL}" loading="lazy" alt="" width="1600" height="600" style="opacity:${currEntry.opacityImage}">
    </figure>
    <div class="container caption">
        <div class="pro-bloc-display"><span class="pro-surtitre">${currEntry.getProjectStatus(locale)}</span></div>
        <h1>${currEntry.title}</h1>
        <div class="pro-bloc-display"><span class="pro-soustitre"><@liferay_ui.message key="placit.information.territoire" /> <strong>${currEntry.getDistrictLabel(locale)}</strong></span></div>
        <div class="pro-bloc-display-desc"><p>${currEntry.description}</p></div>
    </div>


    <#if timelines?size gt 0>
	    <!-- Start slider timeline wrapper -->
	    <div class="container pro-slider-timeline">

	        <!-- Navigation - Input range / S'il y a par exemple 5 éléments alors inscrire la value est égale à 3. -->
	        <div class="pro-navigation">
	            <div class="pro-extreme-date">
	                <span>Début</span>
	                <span class="pro-datetime">${firstTimeLine.getDate()?string[firstTimeLine.getFreeMarkerFormatDate()]}</span>
	            </div>
	            <div class="pro-slidecontainer">
	                <input type="range" min="1" max="${timelines?size}" value="${timelines?size - 2}" class="slider" id="myRange">
	            </div>
	            <div class="pro-extreme-date">
	                <span>Fin</span>
	                <span class="pro-datetime">${lastTimeLine.getDate()?string[lastTimeLine.getFreeMarkerFormatDate()]}</span>
	            </div>
	            <span>Navigation</span>
	        </div>

	        <div class="owl-carousel owl-timeline">

	        	<#list currEntry.getProjectTimelines() as timeline>
		            <div class="pro-item">
		                <div class="pro-small-jalon">
			                <#switch timeline.getFreeMarkerFormatDate()>
			                    <#case "dd/MM/yyyy">
			                    	<span class="pro-day-month">${timeline.getDate()?string["dd MMMM"]}</span>
	                    			<span class="pro-year">${timeline.getDate()?string["yyyy"]}</span>
			                      	<#break>
			                    <#case "MM/yyyy">
									<span class="pro-day-month">${timeline.getDate()?string["MMMM"]}</span>
	                    			<span class="pro-year">${timeline.getDate()?string["yyyy"]}</span>
			                      	<#break>
			                    <#case "yyyy">
			                    	<span class="pro-day-month"></span>
	                    			<span class="pro-year">${timeline.getDate()?string["yyyy"]}</span>
			                      	<#break>
			                </#switch>
		                </div>
		                <a href="${timeline.getLink()}" class="pro-jalon-hover">
		                    <div class="pro-wrapper-date">
		                        <div>
		                            <#switch timeline.getFreeMarkerFormatDate()>
					                    <#case "dd/MM/yyyy">
					                    	<span class="pro-day-month">${timeline.getDate()?string["dd MMMM"]}</span>
			                    			<span class="pro-year">${timeline.getDate()?string["yyyy"]}</span>
					                      	<#break>
					                    <#case "MM/yyyy">
											<span class="pro-day-month">${timeline.getDate()?string["MMMM"]}</span>
			                    			<span class="pro-year">${timeline.getDate()?string["yyyy"]}</span>
					                      	<#break>
					                    <#case "yyyy">
					                    	<span class="pro-day-month"></span>
			                    			<span class="pro-year">${timeline.getDate()?string["yyyy"]}</span>
					                      	<#break>			                      
					                </#switch>
		                        </div>
		                    </div>
		                    <div class="pro-txt-jalon">
		                        <p>${timeline.getTitle()}</p>
		                    </div>
		                </a>
		            </div>
		        </#list>

	        </div>
	    </div>
	</#if>

</header>

<style>
.pro-page-detail.pro-page-detail-projet section.portlet{
margin : 0px;
}
</style>