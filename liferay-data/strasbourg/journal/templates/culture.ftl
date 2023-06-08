<!-- Boutique culture -->

<div class="seu-container">
    <div class="seu-wi seu-wi-boutique">
        <div class="seu-container">
            <h2 class="seu-section-title">
                <span class="seu-title">${title.getData()}</span>
            </h2>
            <div class="seu-wi-content">
                <div class="seu-wi-text">
                <div class="seu-wi-title">${subtitle.getData()}</div>
                <p>${textContent.getData()}</p>
                </div>
            </div>
            <div class="seu-btn-line">
                <a href="${linkUrl1.getData()}" class="seu-btn-square seu-bordered seu-white" title="${linkLabel1.getData()} (<@liferay_ui.message key="eu.new-window" />)" target="_blank">
                    <span class="seu-flexbox">
                        <span class="seu-btn-text">${linkLabel1.getData()}</span>
                        <span class="seu-btn-arrow"></span>
                    </span>
                </a>
                <a href="${linkUrl2.getData()}" class="seu-btn-square seu-bordered seu-white" title="${linkLabel2.getData()}">
                    <span class="seu-flexbox">
                        <span class="seu-btn-text">${linkLabel2.getData()}</span>
                        <span class="seu-btn-arrow"></span>
                    </span>
                </a>
            </div>
        </div>
    </div>
</div>