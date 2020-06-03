package eu.strasbourg.portlet.council.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.council.model.Type;
import eu.strasbourg.service.council.service.TypeLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.COUNCIL_BO,
                "mvc.command.name=saveType"
        },
        service = MVCActionCommand.class
)
public class SaveTypeActionCommand implements MVCActionCommand {

    private final Log log = LogFactoryUtil.getLog(this.getClass().getName());

    private long typeId;
    private String title;

    @Override
    public boolean processAction(ActionRequest request, ActionResponse response) {
        try {
            // Récupération du contexte de la requêtes
            ServiceContext sc = ServiceContextFactory.getInstance(request);
            ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

            // Validation
            boolean isValid = this.validate(request);
            if (!isValid) {
                // Si pas valide : on reste sur la page d'édition
                PortalUtil.copyRequestParameters(request, response);

                String portletName = (String) request.getAttribute(WebKeys.PORTLET_ID);
                PortletURL returnURL = PortletURLFactoryUtil.create(request, portletName, themeDisplay.getPlid(),
                        PortletRequest.RENDER_PHASE);

                response.setRenderParameter("returnURL", returnURL.toString());
                response.setRenderParameter("cmd", "editType");
                response.setRenderParameter("mvcPath", "/council-bo-edit-type.jsp");
                return false;
            }

            // Si édition ou création d'une nouvelle entrée
            Type type;
            if (this.typeId == 0) {
                type = this.typeLocalService.createType(sc);
            } else {
                type = this.typeLocalService.getType(this.typeId);
            }

            // Champ : titre
            type.setTitle(this.title);

            // Mise à jour de l'entrée en base
            this.typeLocalService.updateType(type, sc);

        } catch (PortalException e) {
            log.error(e);
        }
        return true;
    }

    /**
     * Validation de la requête
     */
    private boolean validate(ActionRequest request) throws PortalException {
        boolean isValid = true;

        ServiceContext sc = ServiceContextFactory.getInstance(request);

        this.typeId = ParamUtil.getLong(request, "typeId");

        // Titre
        this.title = ParamUtil.getString(request, "title");
        if (Validator.isNull(title)) {
            SessionErrors.add(request, "title-error");
            isValid = false;
        }

        // Titre déjà utilisé ?
        if (this.typeLocalService.isTitleAlreadyUsed(this.title, this.typeId)) {
            SessionErrors.add(request, "title-type-already-used-error");
            isValid = false;
        }

        return isValid;
    }

    @Reference(unbind = "-")
    protected void setTypeLocalService(TypeLocalService typeLocalService) {
        this.typeLocalService = typeLocalService;
    }

    private TypeLocalService typeLocalService;

}
