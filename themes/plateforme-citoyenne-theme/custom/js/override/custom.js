/*
* Gestion des likes/dislikes
*/
$(function() {
    $(document).on("click", '.pro-like, .pro-dislike', function(e) {

        e.preventDefault();
        e.stopPropagation();

        var element = $(this);
        var title = $(this).data("title");
        var isdislike = $(this).data("isdislike");
        var typeid = $(this).data("typeid");
        var entityid = $(this).data("entityid");
        var entitygroupid = $(this).data("entitygroupid") ? $(this).data("entitygroupid") : 0;

        Liferay.Service(
            '/like.like/add-like-link',
            {
                title: title,
                isDislike: isdislike,
                typeId: typeid,
                entityId: entityid,
                entityGroupId: entitygroupid
            },
            function(obj) {
                // En cas de succès, on modifie le bouton
                if (obj.hasOwnProperty('success')) {
                    switch(obj['success']) {
                        case "like added":
                            element.toggleClass('active');
                            element.find('strong').text(+parseInt(element.text()) + 1);
                            break;
                        case "dislike added":
                            element.toggleClass('active');
                            element.find('strong').text(+parseInt(element.text()) + 1);
                            break;
                        case "like mind changed added":
                            element.toggleClass('active');
                            element.find('strong').text(+parseInt(element.text()) + 1);
                            element.siblings().first().find('strong').text(+parseInt(element.siblings().first().text()) - 1);
                            break;
                        case "dislike mind changed added":
                            element.toggleClass('active');
                            element.find('strong').text(+parseInt(element.text()) + 1);
                            element.siblings().first().find('strong').text(+parseInt(element.siblings().first().text()) - 1);
                            break;
                        case "like deleted":
                            element.toggleClass('active');
                            element.find('strong').text(+parseInt(element.text()) - 1);
                            break;
                        case "dislike deleted":
                            element.toggleClass('active');
                            element.find('strong').text(+parseInt(element.text()) - 1);
                            break;
                    }

                }
                // Sinon on affiche un message d'erreur
                else if (obj.hasOwnProperty('error')) {
                    if (obj['error'] == 'notConnected') {
                        // Si l'utilisateur n'est pas connecté
                        e.preventDefault();
                        $("#myModal").modal();
                    } else {
                        // Autre erreur
                        alert('Une erreur est survenue.');
                    }
                }
            }
        );

    });
});
