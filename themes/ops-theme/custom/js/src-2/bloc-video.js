// Bloc video
$('.ops-wrapper-video').each(function () {
    var mask = $('.mask-video', this);
    var vidContainer = $('.embed-container', this);

    var idvideo = vidContainer.data('video_id');
    var plateforme = vidContainer.data('video_plateforme');

    if (typeof plateforme != 'undefined') {
        var html = CitronVideoHtml(idvideo, plateforme, 1, 1);


        if (mask.length > 0) {
            $(mask).click(function (e) {
                e.preventDefault();
                vidContainer.append(html);

                elem = vidContainer.find('.' + plateforme + '_player');
                elem = elem[0];

                if (tarteaucitron.serviceAllowed(plateforme) == true) {
                    elem.innerHTML = tarteaucitron.services[plateforme].jsOnElement(elem);
                } else {
                    tarteaucitron.services[plateforme].fallback();
                }

                setTimeout(function () {
                    mask.addClass('hide');

                    setTimeout(function () {
                        mask.remove();
                    }, 500);
                }, 200);
            });
        } else {
            vidContainer.append(html);

            elem = vidContainer.find('.' + plateforme + '_player');
            elem = elem[0];

            if (tarteaucitron.serviceAllowed(plateforme) == true) {
                elem.innerHTML = tarteaucitron.services[plateforme].jsOnElement(elem);
            } else {
                tarteaucitron.services[plateforme].fallback();
            }
        }
    }
});