<#assign currProject = entry.getAssetRenderer().getProject()>

<div class="pro-jalon pro-first">
    <div>
        <div class="pro-date">
            <div>
                <span>15/03</span>
                <span class="pro-year">2018</span>
            </div>
        </div>
    </div>
</div>

<#list currProject.getProjectTimelines() as tl>
       <div class="pro-jalon pro-past">
            <div style="padding-top: 700px;">
                <div class="pro-date">
                    <div>
                        <span class="pro-day">Jour</span>
                        <span class="pro-day-more">J+52</span>
                    </div>
                </div>
                <div class="pro-titre">
                    <span>Le <time datetime="2017-02-14">19/05/2018</time></span>
                    <h4>Titre du jalon projet sur deux lignes</h4>
                </div>
            </div>
        </div>
</#list>

<style>
.pro-page-detail.pro-page-detail-projet section .pro-timeline{
    position : initial;
}
</style>