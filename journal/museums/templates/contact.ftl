<h3 class="contact-title">
    <@liferay_ui["message"] key="contact" />
</h3>
<div class="contact-infos">
    <div class="contact-name">
        ${title.getData()}
    </div>
    <div class="contact-phone">
        <@liferay_ui["message"] key="phone" /> : <a href="tel:${phone.getData()}">${phone.getData()}</a>
    </div>
    <div class="contact-mail">
        <@liferay_ui["message"] key="email" /> : <a href="mailto:${email.getData()}">${email.getData()}</a>
    </div>
</div>
