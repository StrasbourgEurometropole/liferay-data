<#if headerImage?has_content>
	<img src="${headerImage}">
</#if>

<p>Bonjour,<br><br>

De nouvelles annonces sont disponibles pour l'alerte ${subject}.
<br>
Vous trouverez ci-dessous la liste des informations saisies.</p>
<#list content as entry>
    <#if entry?has_content>
    <br><br>
    <div class="offer-content-mail">
        <div class="wi-search-result wi-search-offer">
            <div class="seu-result-right">
                <a class="seu-result-content">
                    <h2 class="seu-result-title">
                        <#if entry.getPost()?length &lt; 190>
                            ${entry.getPost(locale)}
                        <#else>
                            ${entry.getPost(locale)?substring(0,189)} ...
                        </#if>
                    </h2>
                    <div class="seu-result-catcher">${entry.offerDirection.getTitle(locale)}
                        <#if entry.offerService??>
                        / ${entry.offerService.getTitle(locale)}
                        </#if>
                    </div>
                    <#if entry.offerGrade??>
                        <div class="seu-result-category">${entry.offerGrade.getTitle(locale)}</div>
                    </#if>
                </a>
                <div class="seu-result-infos">
                    <div class="seu-result-infos-top">
                        Date limite de dépôt des candidatures
                    </div>
                    <div class="seu-result-infos-bottom">
                        ${entry.getLimitDate()?datetime?string("dd-MM-yyyy")}
                    </div>
                </div>
            </div>
        </div>
        <br><br>
        <button type="button" class="seu-btn-square seu-filled seu-core">
            <span class="seu-flexbox">
                <span class="seu-btn-text">
                    <a href="http://www.strasbourg.eu/offre/-/entity/id/${entry.getOfferId()}">Candidater</a>
                </span>
                <span class="seu-btn-arrow"></span>
            </span>
        </button>
    </div>
    </#if>
</#list>
<br><br><br><br>
<#if footerText?has_content>
	<p>${footerText}</p>
</#if>

<style>
    .offer-content-mail{
        width: 1450px;
    }

    .wi-search-result {
         width: 45%;
         padding-right: 5px;
         display: flex;
         position: relative;
         float: left;
    }
     .wi-search-result + .wi-search-result {
         margin-top: 40px;
    }
     .wi-search-result + .wi-search-result:before {
         content: '';
         position: absolute;
         top: 0;
         left: 50%;
         width: calc(100% + 100px);
         transform: translateX(-50%);
         height: 1px;
         background-color: #f6f6f6;
    }
     .wi-search-result .seu-result-left {
         margin-right: 20px;
    }
     .wi-search-result .seu-result-thumbnail {
         width: 240px;
         flex-shrink: 0;
         margin-right: 30px;
         justify-content: center;
         flex-direction: column;
         display: flex;
    }
     .wi-search-result .seu-result-thumbnail .thumbnail-background {
         width: 100%;
         padding-bottom: 64%;
         background-size: cover;
         background-position: center;
         background-repeat: no-repeat;
         position: relative;
    }
     .wi-search-result .seu-result-thumbnail .mag {
         position: absolute;
         top: 50%;
         left: 50%;
         transform: translate(-50%, -50%);
         font-family: "MontSerrat", arial;
         font-size: 16px;
         font-weight: 700;
         color: #fff;
         text-transform: uppercase;
    }
     .wi-search-result .seu-result-icon {
         background-size: 25px;
         background-color: #f6f6f6;
         background-position: center;
         background-repeat: no-repeat;
         display: inline-block;
         width: 70px;
         height: 70px;
         border-radius: 50%;
    }
     .wi-search-result .seu-result-right {
         display: flex;
         flex-grow: 1;
         margin-top: 5px;
    }
     .wi-search-result .seu-result-right .seu-result-content {
         display: block;
         padding-right: 35px;
         text-decoration: none;
         flex-grow: 1;
    }
     .wi-search-result .seu-result-right .seu-result-content:hover .seu-result-title, .wi-search-result .seu-result-right .seu-result-content:focus .seu-result-title {
         text-decoration: underline;
    }
     .wi-search-result .seu-result-right .seu-result-content .seu-result-title {
         font: 400 14px "MontSerrat", arial;
         text-transform: uppercase;
         color: #505050;
         margin-bottom: 25px;
         line-height: 1.2;
         position: relative;
    }

     .wi-search-result .seu-result-right .seu-result-content .seu-result-catcher {
                color: #505050;
                font: 400 12px OpenSans,arial;
                margin-bottom: 10px;
    }
     .wi-search-result .seu-result-right .seu-result-content .seu-result-category {
         font: 600 12px OpenSans,arial;
         color: #5c5c5c;
         display: flex;
         align-items: center;
         line-height: 1.2;
    }
     .wi-search-result .seu-result-right .seu-result-infos {
         position: relative;
         padding: 10px;
    }
     .wi-search-result .seu-result-right .seu-result-infos:before {
         content: '';
         position: absolute;
         top: 0;
         left: 0;
         height: 80%;
         width: 1px;
         background-color: #f6f6f6;
    }
     .wi-search-result .seu-result-right .seu-result-infos-top {
         position: relative;
         min-height: 50%;
         padding: 0;
         font-family: "MontSerrat", arial;
         font-size: 10px;
         text-transform: none;
         font-weight: 400;
    }
     .wi-search-result .seu-result-right .seu-result-infos-top:after {
         content: '';
         display: block;
         position: absolute;
         bottom: 0;
         left: 50%;
         transform: translate(-50%, 0%);
         height: 1px;
         width: 80%;
         background-color: #f6f6f6;
    }
     .wi-search-result .seu-result-right .seu-result-infos-top + .seu-result-infos-bottom {
         margin-top: 15px;
         position: relative;
    }
     .wi-search-result .seu-result-right .seu-result-infos-bottom {
         font-family: "MontSerrat", arial;
         font-size: 10px;
         color: #31455d;
         text-transform: uppercase;
         font-weight: 700;
         position: relative;
         min-height: 50%;
         padding: 0;
    }
     .wi-search-result .seu-result-right .seu-result-infos-top {
         display: flex;
         height: 50%;
         align-items: center;
    }
     .wi-search-result .seu-result-right .seu-result-infos-top:before {
         content: '';
         display: inline-block;
         width: 20px;
         height: 20px;
         background-size: 8px;
         background-position: center;
         background-repeat: no-repeat;
    }


    .wi-search-result.wi-search-offer {
        margin: auto;
    }

    .seu-result-right .seu-result-infos-top {
        color: #31455d;
        display: flex;
        justify-content: center;
        min-height: 50%;
        padding: 0;
        font-family: MontSerrat,arial;
        font-size: 10px;
        text-transform: none;
        font-weight: 500;
        font-size: 8px;
    }

    .wi-search-result.wi-search-offer .seu-result-right .seu-result-infos-top::before {
        content: '';
        background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' fill='%2331455d' viewBox='0 0 25.6 25.6'%3E%3Cpath d='M23.5 2.4H20V0.8C20 0.4 19.6 0 19.2 0c-0.4 0-0.8 0.4-0.8 0.8v1.6h-4.8V0.8c0-0.4-0.4-0.8-0.8-0.8C12.4 0 12 0.4 12 0.8v1.6H7.2V0.8C7.2 0.4 6.8 0 6.4 0 6 0 5.6 0.4 5.6 0.8v1.6H2.1C1 2.4 0 3.4 0 4.5v18.9c0 1.2 1 2.1 2.1 2.1h21.3c1.2 0 2.1-1 2.1-2.1V4.5C25.6 3.4 24.6 2.4 23.5 2.4L23.5 2.4zM24 23.5C24 23.8 23.8 24 23.5 24H2.1c-0.3 0-0.5-0.2-0.5-0.5V4.5C1.6 4.2 1.8 4 2.1 4h3.5v1.6c0 0.4 0.4 0.8 0.8 0.8 0.4 0 0.8-0.4 0.8-0.8V4H12v1.6c0 0.4 0.4 0.8 0.8 0.8 0.4 0 0.8-0.4 0.8-0.8V4h4.8v1.6c0 0.4 0.4 0.8 0.8 0.8C19.6 6.4 20 6 20 5.6V4h3.5C23.8 4 24 4.2 24 4.5V23.5zM24 23.5' class='a'/%3E%3Crect x='5.6' y='9.6' width='3.2' height='2.4' class='a'/%3E%3Crect x='5.6' y='13.6' width='3.2' height='2.4' class='a'/%3E%3Crect x='5.6' y='17.6' width='3.2' height='2.4' class='a'/%3E%3Crect x='11.2' y='17.6' width='3.2' height='2.4' class='a'/%3E%3Crect x='11.2' y='13.6' width='3.2' height='2.4' class='a'/%3E%3Crect x='11.2' y='9.6' width='3.2' height='2.4' class='a'/%3E%3Crect x='16.8' y='17.6' width='3.2' height='2.4' class='a'/%3E%3Crect x='16.8' y='13.6' width='3.2' height='2.4' class='a'/%3E%3Crect x='16.8' y='9.6' width='3.2' height='2.4' class='a'/%3E%3C/svg%3E");
        margin-right: 12px;
        margin-top: -3px;
        background-position: center;
        background-repeat: no-repeat;
        display: inline-block;
        height: 20px;
        width: 20px;
    }

    .seu-result-right .seu-result-infos-bottom {
        display: flex;
        justify-content: center;
        font-family: MontSerrat,arial;
        font-size: 10px;
        color: #31455d;
        text-transform: uppercase;
        font-weight: 500;
        min-height: 50%;
        padding: 0;
    }

    .seu-btn-square.seu-filled.seu-core {
        border-color: #31455d;
        background-color: #31455d;
        padding: 15px 20px;
        text-decoration: none;
        position: relative;
        display: block;
        margin: auto;
        transition: all,.5s;
        overflow: hidden;
    }

    .seu-btn-square.seu-filled.seu-core .seu-flexbox, .seu-btn-square.seu-filled.seu-second .seu-flexbox {
        display: -ms-flexbox;
        display: flex;
        -ms-flex-align: center;
        align-items: center;
        -ms-flex-pack: center;
        justify-content: center;
    }

    .seu-btn-square.seu-filled.seu-core .seu-btn-text, .seu-btn-square.seu-filled.seu-second .seu-btn-text {
        font-family: MontSerrat,arial;
        font-size: 10px;
        text-transform: uppercase;
        font-weight: 400;
        transition: all,.3s;
        margin-right: 20px;
        width: 100%;
    }

    .seu-btn-square.seu-filled.seu-core .seu-btn-text a, .seu-btn-square.seu-filled.seu-second .seu-btn-text a{
        color: white;
    }

    .seu-btn-square.seu-filled.seu-core .seu-btn-arrow {
        background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' fill='%23FFFFFF' width='31' height='22'%3E%3Cpath d='m21.2 0.3a1.1 1.1 0 1 0-1.6 1.6l8 8 -26.5 0a1.1 1.1 0 0 0-1.1 1.2 1.1 1.1 0 0 0 1.1 1.1l26.6 0 -8 8a1.1 1.1 0 0 0 0 1.6 1.1 1.1 0 0 0 1.6 0l10-10a1.1 1.1 0 0 0 0-1.6l-10.1-9.9z'/%3E%3C/svg%3E");
        content: '';
        display: inline-block;
        width: 20px;
        height: 20px;
        background-size: 10px;
        background-position: center;
        background-repeat: no-repeat;
    }

</style>
