if ($('.ops-search-form').length > 0) {
    $formSearch = $('.ops-search-form');
    $openForm = $('.ops-header-right .link-search');
    $closeForm = $('.ops-search-form a');
    $inputForm = $('.ops-search-form input');

    $openForm.on('click', function (e) {
        e.preventDefault();
        $formSearch.addClass('ops-open');
        $inputForm.focus();
    });


    $closeForm.on('click', function (e) {
        e.preventDefault();
        $formSearch.removeClass('ops-open');
    })


}