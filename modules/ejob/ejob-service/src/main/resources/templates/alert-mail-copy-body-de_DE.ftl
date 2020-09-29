<#if headerImage?has_content>
	<img width="1450" src="${headerImage}">
</#if>

<p class="header">Bonjour,<br><br>

De nouvelles annonces sont disponibles pour l'alerte ${subject}.
<br>
Vous trouverez ci-dessous la liste des informations saisies.</p>

<#list content as entry>
    <#if entry?has_content>
        <table border="0" cellpadding="0" cellspacing="0" width="1450">
            <tbody>
                <tr class="offer">
                    <td class="detail" width="1280">
                        <table border="0" cellpadding="0" cellspacing="0" width="1280">
                            <tr>
                                <td class="gauche" width="1040">
                                    <h2 class="titre">
                                        <font style="margin-bottom: 25px; line-height: 1.2; text-transform: uppercase; color: #505050;" >
                                            <#if entry.getPost(locale)?length &lt; 190>
                                                ${entry.getPost(locale)}
                                            <#else>
                                                ${entry.getPost(locale)?substring(0,189)} ...
                                            </#if>
                                        </font>
                                    </h2>
                                    <div class="direction">
                                        <font style="margin-bottom: 10px; color: #505050;" >
                                            ${entry.direction.getTitle(locale)}
                                            <#if entry.service??>
                                                / ${entry.service.getTitle(locale)}
                                            </#if>
                                        </font>
                                    </div>
                                    <#assign gradeRanges = entry.gradeRanges />
                                    <#if gradeRanges??>
                                        <div class="grades">
                                            <font style="line-height: 1.2; color: #5c5c5c;" >
                                                <#list gradeRanges as gradeRange>
                                                    ${gradeRange[2].getTitle(locale)} à ${gradeRange[3].getTitle(locale)}<#sep>, </#sep>
                                                </#list>
                                            </font>
                                        </div>
                                    </#if>
                                </td>
                                <td>
                                    <table bgcolor="#f6f6f6" border="0" cellpadding="0" cellspacing="0" width="1" style="min-height: 100px;">
                                        <tbody>
                                            <tr>
                                                <td width="1">
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </td>
                                <td class="droite" width="220">
                                    <table border="0" cellpadding="0" cellspacing="0" width="220">
                                        <tbody>
                                            <tr>
                                                <td class="droite-top" style="padding-bottom: 10px;">
                                                    <table border="0" cellpadding="0" cellspacing="0" width="100%" >
                                                        <tbody>
                                                            <tr>
                                                                <td width="30">
                                                                    <img width="20" style="padding-right: 10px;" src="${theme}/o/strasbourg-theme/images/calendrier.png">
                                                                </td>
                                                                <td style="vertical-align: middle;">
                                                                    <font style="color: #31455d;" >
                                                                        <center>Date limite de dépôt des candidatures</center>
                                                                    </font>
                                                                </td>
                                                            </tr>
                                                        </tbody>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                <table bgcolor="#f6f6f6" border="0" cellpadding="0" cellspacing="0" width="220" style="height: 1px;">
                                                    <tbody>
                                                        <tr>
                                                            <td width="80%">
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="droite-bottom" style="padding-top: 10px;">
                                                    <font style="color: #31455d;" >
                                                        <center>${entry.getLimitDate()?datetime?string("dd-MM-yyyy")}</center>
                                                    </font>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td class="bouton" width="170">
				        <table bgcolor="#31455d" border="0" cellpadding="0" cellspacing="18" height="100%" style="padding: 0 5px" width="170">
                            <tbody>
                                <tr>
                                    <td align="center" height="100%" width="85">
                                        <a href="http://www.strasbourg.eu/offre/-/entity/id/${entry.getOfferId()}">
                                            <font style="text-transform: uppercase; color: #ffffff;" >Candidater</font>
                                        </a>
                                    </td>
                                    <td align="center" height="100%" width="21">
                                        <img width="20" src="${theme}/o/strasbourg-theme/images/fleche.png">
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </td>
                </tr>
            </tbody>
        </table>
        <br><br>
    </#if>
</#list>
<#if footerText?has_content>
	<p class="footer">${footerText}</p>
</#if>


<style>
    .header{
        margin-bottom: 50px;
    }

    .detail {
        padding-right: 5px;
    }

    .detail .gauche {
        vertical-align: middle;
        padding: 10px 40px 10px 10px;
    }

    .detail .gauche .titre {
        font: 400 14px MontSerrat, arial;
    }

    .detail .gauche .direction {
        font: 400 12px OpenSans,arial;
    }

    .detail .gauche .grades {
        font: 600 12px OpenSans,arial;
    }

    .detail .droite {
        vertical-align: middle;
        padding: 10px;
    }

    .detail .droite-top {
        font: 500 10px MontSerrat, arial;
        padding-bottom: 10px;
    }

    .detail .droite-bottom {
        font: 700 10px MontSerrat, arial;
        margin-top: 15px;
		padding-top: 10px;
    }

    .bouton{
        vertical-align: middle;
    }

    table a{
        font: 400 10px MontSerrat, arial;
        transition: all,.3s;
        margin-right: 20px;
        width: 100%;
        color: #ffffff;
    }

    .footer{
        margin-top: 60px;
    }

</style>