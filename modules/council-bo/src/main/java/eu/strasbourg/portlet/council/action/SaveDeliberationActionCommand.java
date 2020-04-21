package eu.strasbourg.portlet.council.action;

import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.*;
import eu.strasbourg.service.council.model.Deliberation;
import eu.strasbourg.service.council.service.DeliberationLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import java.util.Locale;
import java.util.Map;

@Component(
        immediate = true,
        property = { "javax.portlet.name=" + StrasbourgPortletKeys.COUNCIL_BO,
                "mvc.command.name=saveDeliberation" },
        service = MVCActionCommand.class)
public class SaveDeliberationActionCommand extends BaseMVCActionCommand {

    private DeliberationLocalService deliberationLocalService;

    @Reference(unbind = "-")
    protected void setDeliberationLocalService(
            DeliberationLocalService deliberationLocalService) {
        this.deliberationLocalService = deliberationLocalService;
    }

    @Override
    protected void doProcessAction(ActionRequest request,
                                   ActionResponse response) throws Exception {
        ServiceContext sc = ServiceContextFactory.getInstance(request);
        ThemeDisplay themeDisplay = (ThemeDisplay) request
                .getAttribute(WebKeys.THEME_DISPLAY);
        String portletName = (String) request.getAttribute(WebKeys.PORTLET_ID);

        long deliberationId = ParamUtil.getLong(request, "deliberationId");
        Deliberation deliberation;
        if (deliberationId == 0) {
            deliberation = deliberationLocalService.createDeliberation(sc);
        } else {
            deliberation = deliberationLocalService.getDeliberation(deliberationId);
        }

        // Validation
        boolean isValid = validate(request);
        if (!isValid) {
            // Si pas valide : on reste sur la page d'édition
            PortalUtil.copyRequestParameters(request, response);

            PortletURL returnURL = PortletURLFactoryUtil.create(request,
                    portletName, themeDisplay.getPlid(),
                    PortletRequest.RENDER_PHASE);
            returnURL.setParameter("tab", request.getParameter("tab"));

            response.setRenderParameter("returnURL", returnURL.toString());
            response.setRenderParameter("mvcPath",
                    "/council-bo-edit-deliberation.jsp");
            return;
        }

        // ordre
        int order = ParamUtil.getInteger(request, "order");
        deliberation.setOrder(order);

        // Titre
        String title = ParamUtil.getString(request, "title");
        deliberation.setTitle(title);

        // Conseil
        long councilSessionId = ParamUtil.getLong(request, "councilSessionId");
        deliberation.setCouncilSessionId(councilSessionId);

        // Statut
        String stage = ParamUtil.getString(request, "stage");
        deliberation.setStage(stage);

        // Update de l'entité
        deliberationLocalService.updateDeliberation(deliberation, sc);

        // Post / Redirect / Get si tout est bon
        PortletURL renderURL = PortletURLFactoryUtil.create(request,
                portletName, themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);
        renderURL.setParameter("tab", request.getParameter("tab"));
        response.sendRedirect(renderURL.toString());
    }

    /**
     * Validation des champs obligatoires
     */
    private boolean validate(ActionRequest request) {
        boolean isValid = true;

        if (Validator.isNull(ParamUtil.getString(request, "order"))) {
            SessionErrors.add(request, "order-error");
            isValid = false;
        }

        if (Validator.isNull(ParamUtil.getString(request, "title"))) {
            SessionErrors.add(request, "title-error");
            isValid = false;
        }

        if (Validator.isNull(ParamUtil.getString(request, "councilSessionId"))) {
            SessionErrors.add(request, "council-session-error");
            isValid = false;
        }

        return isValid;
    }

}
