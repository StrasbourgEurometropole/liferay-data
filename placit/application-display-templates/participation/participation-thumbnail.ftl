<#setting locale = locale />
<#assign plId = renderRequest.getAttribute("classNameLayoutId")[entry.getModelClassName()] />

<@liferay_portlet.renderURL plid=plId var="detailURL" portletName="eu_strasbourg_portlet_entity_detail_EntityDetailPortlet" windowState="normal">
  <@liferay_portlet.param name="classPK" value="${entry.getParticipationId()}" />
  <@liferay_portlet.param name="returnURL" value="${currentURL}" />
</@liferay_portlet.renderURL>

<@liferay_portlet.actionURL var="detailURLFilter">
  <@liferay_portlet.param name="userTargetClassId" value="${entry.assetEntry.classNameId}" />
  <@liferay_portlet.param name="userTargetClassPK" value="${entry.assetEntry.classPK}" />
  <@liferay_portlet.param name="userTargetTitle" value="${entry.getTitle(locale)}" />
  <@liferay_portlet.param name="detailURL" value="${detailURL}" />
  <@liferay_portlet.param name="searchLogId" value="${renderRequest.getAttribute('searchLogId')!0}" />
</@liferay_portlet.actionURL>

<div class="item pro-bloc-card-participation pro-theme-information" data-linkall="a">
    <div>
        <div class="pro-header-participation">
            <figure role="group">
                <img src="assets/images/medias/comm-mathilde.jpg" width="40" height="40" alt="Arrière plan page standard"/>
            </figure>
            <p>Participation publiée par :</p>
            <p><strong>Ville de Strasbourg</strong></p>
            <div class="pro-number-comm">
                <span>37</span>
                <p>Commentaire(s)</p>
            </div>
        </div>
        <div class="pro-content-participation">
            <div class="pro-meta">
                <span>Quartier</span>
                <span>Thématique</span>
                <span>Type : Information</span>
                <span>Statut</span>
                <span>Nom du projet</span>
            </div>
            <a href="detail-participation.html" title="lien de la page"><h3>Titre de l’Évènement<br>Sur deux lignes</h3></a>
            <span class="pro-time">Publiée le <time datetime="2018-01-10">10/04/2018</time> / <span class="pro-duree">Fin dans 11 jours</span></span>
        </div>
        <div class="pro-footer-participation pro-participation-in-progress">
            <div class="pro-avis">
                <a href="#pro-avis-like-pro" class="pro-like">0</a>
                <a href="#pro-avis-dislike-pro" class="pro-dislike">0</a>
            </div>
            <a href="detail-participation.html#pro-link-commentaire" class="pro-form-style">Réagissez...</a>
        </div>
    </div>
</div>