<div class="contact">
    <span class="title">
        <@liferay_ui["message"] key="contact" />
    </span>
    <div class="infos">
        <div class="name">
            ${title.getData()}
        </div>
        <#if phone.getData()?has_content>
            <div class="phone">
                <@liferay_ui["message"] key="phone" /> : <a href="tel:${phone.getData()}">${phone.getData()}</a>
            </div>
        </#if>
        <#if email.getData()?has_content>
            <div class="mail">
                <@liferay_ui["message"] key="email" /> : <a href="mailto:${email.getData()}">${email.getData()}</a>
            </div>
        </#if>
    </div>
</div>