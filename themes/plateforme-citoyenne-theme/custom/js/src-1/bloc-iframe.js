// défini la hauteur a la hauteur de la fenêtre - la hauteur du header - 20px de marge
$('.bloc-iframe iframe').height(
    document.body.clientHeight - $('header#pro-header').height() - 20
);