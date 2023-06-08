<#if !Date.getSiblings()?has_content>
    Aucun mail/courrier reçu.
</#if>
<#if Date.getSiblings()?has_content>
    <#assign nbResponses = 0>
    <#assign nbResponsesVille = 0>
    <#list Date.getSiblings() as cur_Date>
        <#assign Date_Data = getterUtil.getString(cur_Date.getData())>
        
        <#if validator.isNotNull(Date_Data)>
            <#assign Date_DateObj = dateUtil.parseDate("yyyy-MM-dd", Date_Data, locale)>
        
            <div class="pro-item-proposition" data-contribution="1">
                <span class="pro-published">
                    <time datetime="${dateUtil.getDate(Date_DateObj, "yyyy-MM-dd", locale)}">
                        Le ${dateUtil.getDate(Date_DateObj, "dd MMM yyyy", locale)}
                    </time>
                </span>
                <#if cur_Date.TitreQuestion.getSiblings()?has_content>
                    <#assign nbResponses = nbResponses + cur_Date.TitreQuestion.getSiblings()?size>
                    <#list cur_Date.TitreQuestion.getSiblings() as question>
                        <span class="pro-title-question">${question.getData()}</span>
                        <#if question.Question?has_content>
                            <p>${question.Question.getData()}</p>
                        </#if>
                        <div class="pro-item-response">
                            <div class="pro-bloc-texte">
                                <p>${question.ReponseUsager.getData()}</p>
                            </div>
                            <#if question.ReponseVille.getData()?has_content>
                                <#assign nbResponsesVille = nbResponsesVille + 1>
                                <div class="pro-footer-response">
                                    <#assign UserLocalService = serviceLocator.findService("com.liferay.portal.kernel.service.UserLocalService") />
                                    <#assign userId =.vars['reserved-article-author-id'].data />
                                    <#assign user = UserLocalService.getUser(userId?number) />
                                    
                                    <div class="pro-author">
                                        <figure role="group">
                                            <img src="${user.getPortraitURL(themeDisplay)}" width="40" height="40" alt="Image participation">
                                        </figure>
                                        <div class="pro-meta-txt">
                                            <p>Réponse publiée par :</p>
                                            <p><strong>${user.getFullName()}</strong></p>
                                        </div>
                                    </div>
                                    <div class="pro-txt">
                                        <p>${question.ReponseVille.getData()}</p>
                                    </div>
                                </div>
                            </#if>
                        </div>
                    </#list>
                </#if>
            </div>
        </#if>
    </#list> 
    <input type="hidden" id="nbReponsesCourrier_${randomNamespace}" value="${nbResponses}" />
    <input type="hidden" id="nbReponsesVilleCourrier_${randomNamespace}" value="${nbResponsesVille}" />
</#if>