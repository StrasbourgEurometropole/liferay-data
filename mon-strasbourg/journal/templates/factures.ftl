<#assign serviceContext = staticUtil["com.liferay.portal.kernel.service.ServiceContextThreadLocal"].getServiceContext()>
<#assign httpServletRequest = serviceContext.getRequest()>

<script>
    window.tipiURL = '${tipiURL.getData()}';
    window.tipiCallbackURL = '${tipiCallbackURL.getData()}';
</script>

<section id="bills">
    <h2><@liferay.language key="pay-my-bills" /></h2>
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