<!-- Contact -->
<#setting locale = locale />
<#assign portletHelper = serviceLocator.findService("eu.strasbourg.utils.api.PortletHelperService") />
<div class="seu-wi seu-wi-contact">
    <div class="seu-container">
        <h2 class="seu-section-title">
            <span class="seu-title">${portletHelper.getPortletTitle('contact', renderRequest)}</span>
        </h2>
        <div class="seu-wi-content">
            <div class="seu-wi-text">
                <div class="seu-wi-title">${name.getData()}</div>
                <p>${address.getData()}</p>
                <p>${phone.getData()}</p>
            </div>
            <a href="${detailLink.getData()}" class="seu-btn-square seu-bordered seu-core" title="Contacter">
                <span class="seu-flexbox">
                    <span class="seu-btn-text"><@liferay_ui.message key="to-contact" /></span>
                    <span class="seu-btn-arrow"></span>
                </span>
            </a>
        </div>
    </div>
</div>
