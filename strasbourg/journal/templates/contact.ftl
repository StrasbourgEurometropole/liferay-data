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
                <p>${address.getData()}</p>
                <p>Tél. ${phone.getData()}</p>
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
url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg'  fill='%232da192' viewBox='0 0 266.9 393.3'%3E%3Cpath d='M133.5 0C59.9 0 0 59.9 0 133.5 0 163 17.4 208 54.8 275c25.5 45.7 50.9 84.3 52 85.9l18.4 27.8c1.8 2.8 5 4.5 8.3 4.5 3.4 0 6.5-1.7 8.3-4.5l18.4-27.8c1.1-1.6 26.3-39.9 52-85.9 37.4-67 54.8-112 54.8-141.5C266.9 59.9 207.1 0 133.5 0L133.5 0zM194.7 265.3c-25.3 45.4-50.2 83.1-51.2 84.7l-10 15.2 -10-15.2c-1.1-1.6-26.1-39.6-51.2-84.7C37.6 203.1 20 158.8 20 133.5 20 70.9 70.9 20 133.5 20c62.6 0 113.5 50.9 113.5 113.5C246.9 158.8 229.4 203.1 194.7 265.3L194.7 265.3zM194.7 265.3'/%3E%3Cpath d='M133.5 58.6c-40.8 0-74 33.2-74 74 0 40.8 33.2 74 74 74 40.8 0 74-33.2 74-74C207.5 91.8 174.3 58.6 133.5 58.6L133.5 58.6zM133.5 186.6c-29.8 0-54-24.2-54-54 0-29.8 24.2-54 54-54 29.8 0 54 24.2 54 54C187.5 162.4 163.2 186.6 133.5 186.6L133.5 186.6zM133.5 186.6'/%3E%3C/svg%3E")