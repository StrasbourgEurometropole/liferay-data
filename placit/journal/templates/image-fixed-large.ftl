<!-- BLOC IMAGE FIXE LARGE -->

<div class="container">
    <div class="pro-bloc pro-bloc-texte pro-bloc-image">
        <figure role="group">
            <img src="${image.getData()}" width="922" height="566" alt="${image.getAttribute("alt")}"/>
         	<figcaption>${image.children[0].data} - ${legend.getData()}</figcaption>
        </figure>
    </div>
</div>