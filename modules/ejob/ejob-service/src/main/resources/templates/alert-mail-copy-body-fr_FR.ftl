<#if headerImage?has_content>
    <img src="${headerImage}  style="min-width: 500px; max-width:800px;">
</#if>

<table border="0" cellpadding="10" cellspacing="0" style="min-width: 500px; max-width:800px;">
    <tbody>
        <tr>
            <td colspan=2 style="min-width: 500px; max-width:800px;">
                Bonjour,<br><br>
                De nouvelles annonces sont disponibles pour votre alerte ${subject}.<br><br>
            </td>
        </tr>
        <#list content as entry>
            <#if entry?has_content>
                <tr>
                    <td colspan=2 style="min-width: 500px; max-width:800px;">
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
                                        ${gradeRange[2].getTitle(locale)}
                                        <#if gradeRange[3]??>
                                            à ${gradeRange[3].getTitle(locale)}<#sep>, </#sep>
                                        </#if>
                                    </#list>
                                </font>
                            </div>
                        </#if>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right; text-align: -webkit-right; min-width: 230px; max-width:400px;">
                        <br>
                        <table border="0" cellpadding="0" cellspacing="0">
                            <tbody>
                                <tr>
                                    <td style="padding-bottom: 10px;">
                                        <table border="0" cellpadding="0" cellspacing="0">
                                            <tbody>
                                                <tr>
                                                    <td width="30">
                                                        <img width="20" style="padding-right: 10px;" src="${theme}/o/strasbourg-theme/images/calendrier.png">
                                                    </td>
                                                    <td style="vertical-align: middle;" class="date-label">
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
                                    <td class="date-value">
                                        <font style="color: #31455d;" >
                                            <center>${entry.getLimitDate()?datetime?string("dd-MM-yyyy")}</center>
                                        </font>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </td>
                    <td style="vertical-align: middle; text-align: left; text-align: -webkit-left; min-width: 170px; max-width:400px;">
                        <table bgcolor="#31455d" border="0" cellpadding="0" cellspacing="18" height="100%" style="padding: 0 5px" width="170">
                            <tbody>
                                <tr>
                                    <td align="center" height="100%" width="85">
                                        <a href="${urlOffer}${entry.getOfferId()}" style="text-decoration: none; text-decoration-line: none;" >
                                            <font style="text-transform: uppercase; color: #ffffff; text-decoration: none; text-decoration-line: none;" >Candidater</font>
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
            <tr>
                <td colspan=2 style="min-width: 500px; max-width:800px;">
                    <br><br><br>
                </td>
            </tr>
            </#if>
        </#list>
        <#if footerText?has_content>
            <tr>
                <td colspan=2 style="min-width: 500px; max-width:800px;">
                    <br><br>
                    ${footerText}
                </td>
            </tr>
        </#if>
    </tbody>
</table>


<style>
    .titre {
        font: 400 14px MontSerrat, arial;
    }

    .direction {
        font: 400 12px OpenSans,arial;
    }

    .grades {
        font: 600 12px OpenSans,arial;
    }

    .date-label {
        font: 500 10px MontSerrat, arial;
    }

    .date-value {
        font: 700 10px MontSerrat, arial;
    }

    table a{
        font: 400 10px MontSerrat, arial;
    }
</style>