<!-- Détail contact -->
<#setting locale = locale />

<div class="seu-container rte">
    <h1>${name.data}</h1>
    <h2><@liferay_ui.message key="eu.presentation" /></h2>
    <#if presentation.data?has_content>
        <div>
            ${presentation.data}
        </div>
    </#if>
    <#if office.data?has_content || phone.data?has_content>
        <h3><@liferay_ui.message key="eu.place.public-reception" /></h3>
        <div>
            ${office.data}
        </div>
        <#if phone.data?has_content>
            <div>
                Tél. ${phone.data}
            </div>
        </#if>
    </#if>
    <#if address.data?has_content>
        <h3><@liferay_ui.message key="address" /></h3>
        <div>
            ${address.data}
        </div>
    </#if>
    <#if websiteURL.data?has_content && websiteName.data?has_content>
        <h3><@liferay_ui.message key="eu.website" /></h3>
        <div>
            <a href="${websiteURL.data}" title="${websiteName.data} <@liferay_ui.message key="eu.new-window" />" target="_blank" class="seu-external" />${websiteName.data}</a>
        </div>
    </#if>
    <#if mondayMorning.data?has_content ||
        mondayAfternoon.data?has_content ||
        
        tuesdayMorning.data?has_content ||
        tuesdayAfternoon.data?has_content ||
        
        wednesdayMorning.data?has_content ||
        wednesdayAfternoon.data?has_content ||
        
        thursdayMorning.data?has_content ||
        thursdayAfternoon.data?has_content ||
        
        fridayMorning.data?has_content ||
        fridayAfternoon.data?has_content ||

        
        saturdayMorning.data?has_content ||
        saturdayAfternoon.data?has_content ||

        
        sundayMorning.data?has_content ||
        sundayAfternoon.data?has_content
    >
        <h2><@liferay_ui.message key="eu.times" /></h2>
        <div class="seu-wi seu-wi-schedules" style="padding: 50px 30px; background-color: #f6f6f6 !important; margin-bottom: 50px;">
            <ul class="unstyled schedule-list">
                <#if mondayMorning.data?has_content || mondayAfternoon.data?has_content>
                    <li>
                        <span>
                            <@liferay_ui.message key="monday" />
                        </span>
                        <span>
                            <#if mondayMorning.data?has_content><div>${mondayMorning.data}</div></#if>
                            <#if mondayAfternoon.data?has_content><div>${mondayAfternoon.data}</div></#if>
                        </span>
                    </li>
                </#if>
                <#if tuesdayMorning.data?has_content || tuesdayAfternoon.data?has_content>
                    <li>
                        <span>
                            <@liferay_ui.message key="tuesday" />
                        </span>
                        <span>
                            <#if tuesdayMorning.data?has_content><div>${tuesdayMorning.data}</div></#if>
                            <#if tuesdayAfternoon.data?has_content><div>${tuesdayAfternoon.data}</div></#if>
                        </span>
                    </li>
                </#if>
                <#if wednesdayMorning.data?has_content || wednesdayAfternoon.data?has_content>
                    <li>
                        <span>
                            <@liferay_ui.message key="wednesday" />
                        </span>
                        <span>
                            <#if wednesdayMorning.data?has_content><div>${wednesdayMorning.data}</div></#if>
                            <#if wednesdayAfternoon.data?has_content><div>${wednesdayAfternoon.data}</div></#if>
                        </span>
                    </li>
                </#if>
                <#if thursdayMorning.data?has_content || thursdayAfternoon.data?has_content>
                    <li>
                        <span>
                            <@liferay_ui.message key="thursday" />
                        </span>
                        <span>
                            <#if thursdayMorning.data?has_content><div>${thursdayMorning.data}</div></#if>
                            <#if thursdayAfternoon.data?has_content><div>${thursdayAfternoon.data}</div></#if>
                        </span>
                    </li>
                </#if>
                <#if fridayMorning.data?has_content || fridayAfternoon.data?has_content>
                    <li>
                        <span>
                            <@liferay_ui.message key="friday" />
                        </span>
                        <span>
                            <#if fridayMorning.data?has_content><div>${fridayMorning.data}</div></#if>
                            <#if fridayAfternoon.data?has_content><div>${fridayAfternoon.data}</div></#if>
                        </span>
                    </li>
                </#if>
                <#if saturdayMorning.data?has_content || saturdayAfternoon.data?has_content>
                    <li>
                        <span>
                            <@liferay_ui.message key="saturday" />
                        </span>
                        <span>
                            <#if saturdayMorning.data?has_content><div>${saturdayMorning.data}</div></#if>
                            <#if saturdayAfternoon.data?has_content><div>${saturdayAfternoon.data}</div></#if>
                        </span>
                    </li>
                </#if>
                <#if sundayMorning.data?has_content || sundayAfternoon.data?has_content>
                    <li>
                        <span>
                            <@liferay_ui.message key="sunday" />
                        </span>
                        <span>
                            <#if sundayMorning.data?has_content><div>${sundayMorning.data}</div></#if>
                            <#if sundayAfternoon.data?has_content><div>${sundayAfternoon.data}</div></#if>
                        </span>
                    </li>
                </#if>
            </ul>
        </div>
    </#if>
    <#if exceptionnalSchedule.data?has_content>
        <h3><@liferay_ui.message key="eu.exceptional-schedule" /></h3>
        <div>
            ${exceptionnalSchedule.data}
        </div>
    </#if>
    <h2><@liferay_ui.message key="contact.contact-us" /></h2>
</div>
<style>
    .contact-form-portlet h1 {
        display: none !important;
    }
</style>