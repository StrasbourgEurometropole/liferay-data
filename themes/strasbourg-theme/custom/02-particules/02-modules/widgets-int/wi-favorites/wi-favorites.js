$(function() {
    // Si l'utilisateur a un favoris à ajouté via son sessionStorage, on l'ajoute
    var favoriteToAdd = window.sessionStorage.getItem("favorite");
    if (favoriteToAdd && favoriteToAdd.length > 0) {
        favoriteToAdd = JSON.parse(favoriteToAdd),
        Liferay.Service(
            '/favorite.favorite/add-favorite-link',
            favoriteToAdd,
            function(obj) {
                if (obj.hasOwnProperty('success')) {
                    var favoriteButton = $('[data-type=' + favoriteToAdd.typeId + '][data-id=' + favoriteToAdd.entityId + ']');
                    if(favoriteButton.length > 0) {
                        favoriteButton.addClass('liked');
                        favoriteButton[0].children[0].textContent = Liferay.Language.get('eu.remove-from-favorite');
                    }
                    window.sessionStorage.setItem("favorite", "");
                }
            }
        );
        if (!window.userFavorites) {
            window.userFavorites = [];
        }
        window.userFavorites.push(favoriteToAdd);
    }

    // On parcourt les favoris utilisateurs et on modfie les boutons correspondant sur la page
    if (window.userFavorites) {
        var i;
        for (i = 0; i < window.userFavorites.length; i++) {
            var favoriteButton = $('[data-type=' + window.userFavorites[i].typeId + '][data-id=' + window.userFavorites[i].entityId + ']')
            if (favoriteButton.length > 0) {
                favoriteButton.addClass('liked');
                favoriteButton[0].children[0].textContent = Liferay.Language.get('eu.remove-from-favorite');
            }
        }
    }

/*
    var elements = $('.seu-add-favorites, .add-favorites, .item-misc');
    if (elements) {
        elements.each(function(i) {
            var favorite = $(this);
            if (window.userFavorites) {
                $.each(window.userFavorites, function(index, value) {
                    if (favorite.data("id") == this.entityId) {
                        favorite[0].classList.add('liked'); // TO SIMPLIFY
                        favorite[0].children[0].textContent = Liferay.Language.get('eu.remove-from-favorite');
                    }
                });
            }
        });
    }
*/
});

$(function() {
    // Lors du clic sur un bouton "ajouter aux favoris"
    $(document).on("click", '.seu-add-favorites, .add-favorites, .item-misc', function(e) {
        e.preventDefault();
        e.stopPropagation();

        var htmlA = $(this);
        var url = $(this).data("url");
        var id = $(this).data("id");
        var groupId = $(this).data("groupId") ? $(this).data("groupId") : 0;
        var type = $(this).data("type");
        var title = $(this).data("title");
        var isFavorite = true;

        // Si le favoris a déjà été ajouté par l'utilisateur
        if (htmlA[0].classList.contains('liked')) {
            // On appelle le WS pour supprimer le favoris
            Liferay.Service(
                '/favorite.favorite/delete-favorite-link', {
                    title: title,
                    url: url,
                    typeId: type,
                    entityId: id
                },
                function(obj) {
                    // En cas de succès, on modifie le bouton
                    if (obj.hasOwnProperty('success')) {
                        htmlA[0].classList.remove('liked');
                        htmlA[0].children[0].textContent = Liferay.Language.get('eu.add-to-favorite');
                    }
                    // Sinon on affiche un message d'erreur
                    else if (obj.hasOwnProperty('error')) {
                        if (obj['error'] == 'notConnected')
                            // Si l'utilisateur n'est pas connecté
                            window.createPopin('Veuillez vous connecter pour retirer un favori.', function() {
                                // Si l'utilisateur n'est pas connecté, on ajoute à son LocalStorage le favoris
                                // On l'ajoutera la prochaine fois qu'il arrive sur la page en étant connecté
                                window.sessionStorage.setItem("favorite", JSON.stringify(favoriteToAdd));
                                window.location = window.loginURL;
                            }, undefined, 'Se connecter', 'Annuler');
                        else {
                            // Autre erreur
                            console.log(obj['error']);
                            window.createPopin('Une erreur est survenue.');
                        }
                    }
                }
            );
            isFavorite = false;
        } else {
            // Sinon appel du WS pour ajouter un favoris
            var favoriteToAdd = {
                title: title,
                url: url,
                typeId: type,
                entityId: id,
                entityGroupId: groupId
            };
            Liferay.Service(
                '/favorite.favorite/add-favorite-link',
                favoriteToAdd,
                function(obj) {
                    if (obj.hasOwnProperty('success')) {
                        htmlA[0].classList.add('liked');
                        htmlA[0].children[0].textContent = Liferay.Language.get('eu.remove-from-favorite');
                    } else if (obj.hasOwnProperty('error')) {
                        if (obj['error'] == 'notConnected')
                        window.createPopin('Veuillez vous connecter pour ajouter un favori.', function() {
                            // Si l'utilisateur n'est pas connecté, on ajoute à son LocalStorage le favoris
                            // On l'ajoutera la prochaine fois qu'il arrive sur la page en étant connecté
                            window.sessionStorage.setItem("favorite", JSON.stringify(favoriteToAdd));
                            window.location = window.loginURL;
                        }, undefined, 'Se connecter', 'Annuler');
                        else {
                            console.log(obj['error']);
                            window.createPopin('Une erreur est survenue.');
                        }
                    }

                }
            );
        }

        // On met à jour window.userFavorites
        var userFavorites = [];
        var isNewFavorite = true;
        for (var i = 0; i < window.userFavorites.length; i++) {
            var favorite = window.userFavorites[i];
            if (favorite.entityId !== id) {
                userFavorites.push(favorite);
            } else {
                isNewFavorite = false;
            }
        }
        if (isNewFavorite) {
            var newFavorite = {
                entityId: id,
                typeId: type
            };
            userFavorites.push(newFavorite);
        }
        window.userFavorites = userFavorites;

        // On modfie les boutons correspondant sur la page
        var favoriteButton = $('[data-type=' + type + '][data-id=' + id + ']')
        if (isFavorite) {
            favoriteButton.addClass('liked');
            favoriteButton[0].children[0].textContent = Liferay.Language.get('eu.remove-from-favorite');
        }else{
            favoriteButton.removeClass('liked');
            favoriteButton[0].children[0].textContent = Liferay.Language.get('eu.add-to-favorite');
        }
    });
});