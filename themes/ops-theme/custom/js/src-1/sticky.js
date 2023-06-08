if($('.ops-wrapper-content-rh').length > 0) {


    var isInitStick = false;

    // Si nous sommes sur le détail SIT ou détail Rando
    if ($('.ops-wrapper-sticky').length > 0) {

        console.log('oko');

        var $elSticky = $('.ops-wrapper-content-rh .ops-wrapper-sticky');



        if(document.body.clientHeight-120 > $elSticky.outerHeight()){

            if (!isTabletPortraitOrSmalller()) {
                $elSticky.stick({top: 100});
                isInitStick = true;
            }

            $(window).on('resize', function () {
                console.log(isTabletPortraitOrSmalller());
                if (isTabletPortraitOrSmalller()) {
                    $elSticky.stick('stop');
                    $elSticky.attr('style', '');
                } else {
                    if (isInitStick) {
                        $elSticky.stick('update');
                    } else {
                        isInitStick = true;
                        setTimeout(function () {
                            $elSticky.stick({top: 100});
                        }, 500);
                    }
                }
            });

        }


    }

}