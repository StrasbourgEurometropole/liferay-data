<!-- Contact -->
<#setting locale = locale />
<div class="seu-wi seu-wi-contact">
    <div class="seu-container">
        <h2 class="seu-section-title">
            <span class="seu-title"><@liferay_ui.message key="contact" /></span>
        </h2>
        <div class="seu-wi-content">
            <div class="seu-wi-text">
                <div class="seu-wi-title">${name.getData()}</div>
                <p>Tél. ${phone.getData()}</p>
            </div>
            <a href="${detailLink.getData()}" class="seu-btn-square seu-bordered seu-core" title="Contacter">
                <span class="seu-flexbox">
                    <span class="seu-btn-text"><@liferay_ui.message key="contact.contact-us" /></span>
                    <span class="seu-btn-arrow"></span>
                </span>
            </a>
        </div>
    </div>
</div>
