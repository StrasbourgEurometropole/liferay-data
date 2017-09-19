<#setting locale = locale />
<section id="seu-profil">
    <h2 class="sr-only">Profils</h2>
    <div class="seu-pictures">
        <div class="seu-left seu-picture" style="background-image: url(/o/strasbourg-theme/images/test/profil-left.jpg);"></div>
        <div class="seu-right seu-picture" style="background-image: url(/o/strasbourg-theme/images/test/profil-right.jpg);"></div>
    </div>
    <div class="seu-container">
        <div class="seu-left seu-text">
            <div class="seu-suptitle">${sublabelAssociation.getData()}</div>
            <div class="seu-title">${associationLabel.getData()}</div>
            <a href="${associationLink.getFriendlyUrl()}" class="seu-btn-square seu-bordered seu-core" title="Association : ${associationLinkLabel.getData()}">
            <span class="seu-flexbox">
            <span class="seu-btn-text">${associationLinkLabel.getData()}</span>
            <span class="seu-btn-arrow"></span>
            </span>
            </a>
        </div>
        <div class="seu-right  seu-text">
            <div class="seu-suptitle">${sublabelPro.getData()}</div>
            <div class="seu-title">${proLabel.getData()}</div>
            <a href="${proLink.getFriendlyUrl()}" class="seu-btn-square seu-bordered seu-core" title="Professionnel : ${proLinkLabel.getData()}">
            <span class="seu-flexbox">
            <span class="seu-btn-text">${proLinkLabel.getData()}</span>
            <span class="seu-btn-arrow"></span>
            </span>
            </a>
        </div>
    </div>
</section>