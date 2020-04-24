package eu.strasbourg.portlet.council.resource;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import eu.strasbourg.service.council.service.*;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_POPUP_WEB,
                "mvc.command.name=giveBudgetSupport"
        },
        service = MVCResourceCommand.class
)
public class SubmitVotesResourceCommand  implements MVCResourceCommand {

        private final Log log = LogFactoryUtil.getLog(this.getClass().getName());

        /** Services **/
        private CouncilSessionLocalService councilSessionLocalService;
        private DeliberationLocalService deliberationLocalService;
        private OfficialLocalService officialLocalService;
        private ProcurationLocalService procurationLocalService;
        private VoteLocalService voteLocalService;

        @Override
        public boolean serveResource(ResourceRequest request, ResourceResponse response) throws PortletException {
                boolean result = true;

                // TODO : Business

                return result;
        }

        /**
         * Recuperation du publik ID avec la session
         */
        private String getPublikID(PortletRequest request) {
                LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(request);
                HttpServletRequest originalRequest = liferayPortletRequest.getHttpServletRequest();
                return SessionParamUtil.getString(originalRequest, "publik_internal_id");
        }

        @Reference(unbind = "-")
        protected void setCouncilSessionLocalService(CouncilSessionLocalService councilSessionLocalService) {
                this.councilSessionLocalService = councilSessionLocalService;
        }

        @Reference(unbind = "-")
        protected void setDeliberationLocalService(DeliberationLocalService deliberationLocalService) {
                this.deliberationLocalService = deliberationLocalService;
        }

        @Reference(unbind = "-")
        protected void setOfficialLocalService(OfficialLocalService officialLocalService) {
                this.officialLocalService = officialLocalService;
        }

        @Reference(unbind = "-")
        protected void setProcurationLocalService(ProcurationLocalService procurationLocalService) {
                this.procurationLocalService = procurationLocalService;
        }

        @Reference(unbind = "-")
        protected void setVoteLocalService(VoteLocalService voteLocalService) {
                this.voteLocalService = voteLocalService;
        }

}
