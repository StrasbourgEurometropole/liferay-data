<header class="ops-orchestre">
    <figure class="fit-cover ops-o30">
        <img src="${image.getData()}" width="1600" height="450" alt="Image - Couverture page de recrutement"/>
    </figure>
    <div class="ops-content-wrapper ops-caption ops-aligncenter">
        <h1>${title.getData()}</h1>
    </div>

    <div class="ops-content-wrapper ops-nav-bottom-orchestre">
        <nav class="ops-scrollto">
            <ul>
            </ul>
        </nav>
    </div>

</header>

<script>

$(document).ready(function () {
    $('.ops-separation').each(function () {
        $('.ops-scrollto>ul').append('<li><a href="#' + $(this).attr('id') + '">' + $(this).find('h2').html() + '</a></li>');
    });
});

</script>