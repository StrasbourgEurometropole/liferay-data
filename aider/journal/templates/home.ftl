<#setting locale = locale />

<#-- Récupération du contexte de navigation de l'utilisateur -->
<#assign serviceContext = staticUtil["com.liferay.portal.kernel.service.ServiceContextThreadLocal"].getServiceContext()>
<#assign httpServletRequest = serviceContext.getRequest()>
<#assign isUserloggedIn = httpServletRequest.getSession().getAttribute("publik_logged_in")!false />

<div class="container" style="display: flex; justify-content: center; flex-wrap: wrap;">

        <div class="item pro-bloc-card-home col-4" data-linkall="a">
            <div>
                <div class="pro-header-home">
                    <span class="pro-ico">
                        <i class="fas fa-hands-helping"></i>
                    </span>
                    <h4>${TexteProposition.getData()}</h4>
                </div>
                <div class="pro-footer-home "> 
                    <a href="${URLProposition.getData()}" class="pro-btn-yellow" >
                        ${BoutonProposition.getData()}
                    </a>
                </div>
            </div>
        </div>

        <div class="item pro-bloc-card-home col-4" data-linkall="a">
            <div>
                <div class="pro-header-home">
                    <span class="pro-ico">
                        <i class="fas fa-search"></i>
                    </span>
                    <h4>${TexteDemande.getData()}</h4>
                </div>
                <div class="pro-footer-home "> 
                    <a href="${URLDemande.getData()}" class="pro-btn-yellow" >
                        ${BoutonDemande.getData()}
                    </a>
                </div>
            </div>
        </div>

</div>