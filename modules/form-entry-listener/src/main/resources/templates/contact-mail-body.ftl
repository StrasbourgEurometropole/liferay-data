<html>
<head>
    <style>
        hr {
            background-color: #eee;
            border: 0 none;
            color: #eee;
            height: 1px;
            padding: 0px;
            margin: 0px;
        }
        .label {
            color: gray;
        }
    </style>
</head>
<body>
<img src="https://www.strasbourg.eu/documents/976405/1013671/bandeau-mail.jpg/dfdd2c06-7961-ae34-a810-6de4b66059d3">
<p>
    Bonjour,<br><br>
    Vous venez de soumettre des informations sur le formulaire "${formName}".<br>
    Vous trouverez ci-dessous la liste des informations saisies.
</p>
<br>
<#if content?has_content>
    ${content}
</#if>

<p>
    Date et heure de soumission du formulaire : ${date} ${time}<br>
    <br>
    Ces informations seront traitées dans les meilleurs délais.<br>
    Cordialement,<br>
    <br>
    La Ville et l'Eurométropole de Strasbourg<br>
    <a href="http://www.strasbourg.eu/" target="_blank">www.strasbourg.eu</a><br>
</p>
</body>
</html>

