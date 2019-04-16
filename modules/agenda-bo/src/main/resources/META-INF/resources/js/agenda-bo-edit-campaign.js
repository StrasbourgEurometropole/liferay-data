// Champs select themes
new Choices('#themesIds', {
	removeItemButton: true
});

//  on vérifie que les date sont correcte à la validation du formulaire
$(":submit").on('click', function(e) {
    setPeriodValidators(e);
});

function setPeriodValidators(event) {
	var namespace = "_eu_strasbourg_portlet_agenda_AgendaBOPortlet_";
	var namespaceAUI = "#" + namespace;
    var allValidated = true;
    var startDate = $(namespaceAUI + "startDate").val();
    if(startDate == ""){
        $('.start-date-error').show();
        allValidated = false;
    }else{
        $('.start-date-error').hide();
    }
    var endDate = $(namespaceAUI + "endDate").val();
    if(endDate == ""){
        $('.end-date-error').show();
        allValidated = false;
    }else{
        $('.end-date-error').hide();
    }

    if(allValidated){
        // on vérifie que la date de début soit <= à la date de fin
        if(startDate > endDate){
            $('.incorrect-date-error').show();
            allValidated = false;
        }else{
            $('.incorrect-date-error').hide();
        }
    }

    if (!allValidated) {
        event.preventDefault();
    }
}

// Champ select users
Liferay.Service('/user/get-group-users', {
	groupId : Liferay.ThemeDisplay.getScopeGroupId()
}, function(users) {
	var choices = [];
	for (var i = 0; i < users.length; i++) {
		choices.push({
			label: users[i].firstName + " " + users[i].lastName + " (" + users[i].screenName + " - " + users[i].emailAddress + ")",
			value: users[i].userId,
			selected: managersIds.indexOf(users[i].userId) > -1
		});
	}
	new Choices('#managersIds', {
		removeItemButton: true,
		choices: choices
	});
});