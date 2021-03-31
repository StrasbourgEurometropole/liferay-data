package eu.strasbourg.utils;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.Date;

public class ServiceContextHelper {

    /**
     * Créer un service context pointant sur Global avec comme action workflow par defaut "publication"
     *
     * @throws PortalException Erreur lors de la récupération de l'instance par défaut
     */
    public static ServiceContext generateGlobalServiceContext() throws PortalException {
        ServiceContext sc = new ServiceContext();

        Company defaultCompany = CompanyLocalServiceUtil.getCompanyByWebId("liferay.com");
        sc.setCompanyId(defaultCompany.getCompanyId());
        sc.setScopeGroupId(defaultCompany.getGroup().getGroupId());
        sc.setUserId(UserLocalServiceUtil.getDefaultUserId(sc.getCompanyId()));
        sc.setWorkflowAction(WorkflowConstants.ACTION_PUBLISH);
        sc.setModifiedDate(new Date());

        return sc;
    }

}
