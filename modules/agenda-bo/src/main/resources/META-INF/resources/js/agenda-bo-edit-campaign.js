// Champs select themes
new Choices('#themesIds', {
	removeItemButton: true
});

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