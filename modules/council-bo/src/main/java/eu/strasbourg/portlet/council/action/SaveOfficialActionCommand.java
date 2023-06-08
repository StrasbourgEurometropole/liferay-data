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
import eu.strasbourg.service.council.model.Official;
import eu.strasbourg.service.council.model.OfficialTypeCouncil;
import eu.strasbourg.service.council.model.Procuration;
import eu.strasbourg.service.council.model.Type;
import eu.strasbourg.service.council.service.OfficialLocalService;
import eu.strasbourg.service.council.service.OfficialTypeCouncilLocalService;
import eu.strasbourg.service.council.service.TypeLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import java.util.List;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.COUNCIL_BO,
                "mvc.command.name=saveOfficial"
        },
        service = MVCActionCommand.class
)
public class SaveOfficialActionCommand implements MVCActionCommand {

    /** Log **/
    private final Log log = LogFactoryUtil.getLog(this.getClass().getName());

    /** Paramètres de la requête **/
    public static final String PARAM_OFFICIAL_ID = "officialId";
    public static final String PARAM_LASTNAME = "lastname";
    public static final String PARAM_FIRSTNAME = "firstname";
    public static final String PARAM_EMAIL = "email";

    /** Codes d'erreur */
    public static final String ERROR_MISSING_LASTNAME = "error-missing-lastname";
    public static final String ERROR_MISSING_FIRSTNAME = "error-missing-firstname";
    public static final String ERROR_MISSING_EMAIL = "error-missing-email";

    /** Service **/
    private OfficialLocalService officialLocalService;
    private OfficialTypeCouncilLocalService officialTypeCouncilLocalService;
    private TypeLocalService typeLocalService;

    /** Variables tampons **/
    private long officialId;
    private String lastname;
    private String firstname;
    private String email;
    private boolean typeTitle;

    @Override
    public boolean processAction(ActionRequest request, ActionResponse response) {
        try {
            // Récupération du contexte de la requêtes
            ServiceContext sc = ServiceContextFactory.getInstance(request);
            ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

            // Récupération des paramètres
            this.loadParameters(request);

            // Validation
            if (!this.validate(request)) {
                // Si pas valide, on reste sur la page d'édition
                PortalUtil.copyRequestParameters(request, response);

                String portletName = (String) request.getAttribute(WebKeys.PORTLET_ID);
                PortletURL returnURL = PortletURLFactoryUtil.create(request, portletName, themeDisplay.getPlid(),
                        PortletRequest.RENDER_PHASE);

                response.setRenderParameter("returnURL", returnURL.toString());
                response.setRenderParameter("cmd", "editOfficial");
                response.setRenderParameter("mvcPath", "/council-bo-edit-official.jsp");
                return false;
            }

            // Si édition ou création d'une nouvelle entrée
            Official official;
            if (this.officialId == 0) {
                official = this.officialLocalService.createOfficial(sc);
            } else {
                official = this.officialLocalService.getOfficial(this.officialId);
            }

            // Edition de l'entité
            official.setLastname(this.lastname);
            official.setFirstname(this.firstname);
            official.setEmail(this.email);

            // gestion des types de conseil
            // Suppression des anciens type de conseil liés
            List<Type> oldCouncilsTypes = official.getCouncilTypes();
            for (Type type : oldCouncilsTypes) {
                this.officialTypeCouncilLocalService.removeOfficialTypeCouncil(official.getOfficialId(), type.getTypeId());
            }

            List<Type> councilsTypes = typeLocalService.getTypes(-1,-1);
            Boolean isActive = false;
            OfficialTypeCouncil newOfficialTypeCouncil;
            for (Type type : councilsTypes) {
                long typeId = ParamUtil.getLong(request, type.getTitle());
                if(typeId == type.getTypeId()) {
                    newOfficialTypeCouncil = this.officialTypeCouncilLocalService.createOfficialTypeCouncil(official.getOfficialId(), typeId, sc);
                    this.officialTypeCouncilLocalService.updateOfficialTypeCouncil(newOfficialTypeCouncil);

                    isActive = true;
                }
            }

            official.setIsActive(isActive);

            // Mise à jour de l'entrée
            this.officialLocalService.updateOfficial(official, sc);

        } catch (PortalException e) {
            log.error(e);
        }
        return true;
    }

    /**
     * Récupération des paramètres de la requête
     */
    private void loadParameters(ActionRequest request) {
        this.officialId = ParamUtil.getLong(request, PARAM_OFFICIAL_ID);
        this.lastname = ParamUtil.getString(request, PARAM_LASTNAME);
        this.firstname = ParamUtil.getString(request, PARAM_FIRSTNAME);
        this.email = ParamUtil.getString(request, PARAM_EMAIL);
    }

    /**
     * Validation des champs obligatoires
     */
    private boolean validate(ActionRequest request) {
        boolean isValid = true;

        // Nom existe ?
        if (Validator.isNull(this.lastname)) {
            SessionErrors.add(request, ERROR_MISSING_LASTNAME);
            isValid = false;
        }

        // Prénom existe ?
        if (Validator.isNull(this.firstname)) {
            SessionErrors.add(request, ERROR_MISSING_FIRSTNAME);
            isValid = false;
        }

        // Email existe ?
        if (Validator.isNull(this.email)) {
            SessionErrors.add(request, ERROR_MISSING_EMAIL);
            isValid = false;
        }

        return isValid;
    }

    @Reference(unbind = "-")
    protected void setOfficialLocalService(OfficialLocalService officialLocalService) {
        this.officialLocalService = officialLocalService;
    }

    @Reference(unbind = "-")
    protected void setOfficialTypeCouncilLocalService(OfficialTypeCouncilLocalService officialTypeCouncilLocalService) {
        this.officialTypeCouncilLocalService = officialTypeCouncilLocalService;
    }

    @Reference(unbind = "-")
    protected void setTypeLocalService(TypeLocalService typeLocalService) {
        this.typeLocalService = typeLocalService;
    }

}
