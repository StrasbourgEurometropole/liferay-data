<#assign serviceContext = staticUtil["com.liferay.portal.kernel.service.ServiceContextThreadLocal"].getServiceContext()>
<#assign httpServletRequest = serviceContext.getRequest()>

<script>
    window.tipiURL = '${tipiURL.getData()}';
    window.tipiCallbackURL = '${tipiCallbackURL.getData()}';
</script>

<section id="bills">
    <#assign PortletHelper = serviceLocator.findService("eu.strasbourg.utils.api.PortletHelperService") />
	<#assign isFolded = false />
	<div class="buttons">
		<#-- Vérifie si ce widget peut être plié dans la config de la personnalisation -->
		<#if PortletHelper.showRetractableButtonOnDashboard(themeDisplay, themeDisplay.portletDisplay.id)>
			<#-- Récupère le fait de plier ou déplier ce widget dans la config de la personnalisation -->
			<#assign isFolded = PortletHelper.isPortletFoldedOnDashboard(themeDisplay, themeDisplay.portletDisplay.id) />
			<button class="${isFolded?then('retractable-folded-wi','retractable-unfolded-wi')}" data-portlet-id="${themeDisplay.portletDisplay.id}"></button>
		</#if>
		<#-- Vérifie si ce widget peut être masqué dans la config de la personnalisation -->
		<#if PortletHelper.showDeleteButtonOnDashboard(themeDisplay, themeDisplay.portletDisplay.id)>
			<button class="delete-wi" data-portlet-id="${themeDisplay.portletDisplay.id}"></button>
		</#if>
	</div>
    <h2>${title.getData()}</h2>
	<div class="detail" ${isFolded?then('style="display: none;"','')} >
        <form id="formFactures" action="#bills" class="generic-form toValidate">
            <p>${text.getData()}</p> 
            <p class="errors"></p>      
            <div class="webform-layout-box">
                <div class="form-group list">
                    <div class="form-label"><label for="bill_type"><@liferay.language key="bill-type" /><strong class="required">*</strong></label></div>
                    <div class="form-field">
                        <select id="type_facture" class="toCustomSelect silencedSelect" required="" aria-required="true" name="bill_type">
                            <option value=""></option>
                            <option value="childhood"><@liferay.language key="childhood" /></option>
                            <option value="schoolRestaurant"><@liferay.language key="school-restaurant" /></option>
                            <option value="afterSchool"><@liferay.language key="after-school" /></option>
                            <option value="water"><@liferay.language key="water" /></option>
                            <option value="reom"><@liferay.language key="reom" /></option>
                            <option value="rs"><@liferay.language key="rs" /></option>
                        </select>
                    </div>
                </div>
            </div>

            <div class="webform-layout-box">
                <div class="form-group">
                    <div class="form-label"><label for="bill_annee"><@liferay.language key="year" /><strong class="required">*</strong></label></div>
                    <div class="form-field">
                        <input type="number" id="year" required="" aria-required="true" placeholder='<@liferay.language key="year" />...' name="bill_annee">
                    </div>
                </div>

                <div class="form-group">
                    <div class="form-label"><label for="bill_id"><@liferay.language key="bill-number" /><strong class="required">*</strong></label></div>
                    <div class="form-field">
                        <input type="number" id="ref" required="" aria-required="true" placeholder='<@liferay.language key="your-bill-numer" />...' name="bill_id">
                    </div>
                </div>
            </div>
            <div class="webform-layout-box">
                <div class="form-group">
                    <div class="form-label"><label for="bill_mail"><@liferay.language key="bill-email" /><strong class="required">*</strong></label></div>
                    <div class="form-field">
                        <input type="email" id="email" required="" aria-required="true"  placeholder='<@liferay.language key="your-email" />...' name="bill_mail"
                        <#if httpServletRequest.getSession().getAttribute('publik_email')?has_content>
                            value="${httpServletRequest.getSession().getAttribute('publik_email')}" 
                        </#if>
                        >
                    </div>
                </div>

                <div class="form-group">
                    <div class="form-label"><label for="bill_amount"><@liferay.language key="amount" /><strong class="required">*</strong></label></div>
                    <div class="form-field">
                        <input type="number" placeholder="<@liferay.language key='bill-amount-paie' />" min="0" required="" aria-required="true"  id="amount" name="bill_amount" />
                    </div>
                </div>
            </div>
            <button type="submit" class="btn-square--bordered--core"><span class="flexbox"><span class="btn-text"><@liferay.language key="pay" /></span><span class="btn-arrow"></span></span></button>
        </form>
    </div>
</section>

<style>
    #bills .errors {
        color: red;
    }

    .customSelectContain{    
        border: solid rgb(221, 221, 221) 2px;
    }

    @media only screen and (min-width: 1280px){
        .form-group.list{
            width: 450px;
        }
    }

    input[type=number]::-webkit-inner-spin-button, 
    input[type=number]::-webkit-outer-spin-button { 
        -webkit-appearance: none;
    }
    input[type="number"] {
        -moz-appearance: textfield;
    }
</style>