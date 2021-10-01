package eu.strasbourg.portlet.notif.action;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.notif.model.Message;
import eu.strasbourg.service.notif.model.NatureNotif;
import eu.strasbourg.service.notif.model.ServiceNotif;
import eu.strasbourg.service.notif.service.MessageLocalService;
import eu.strasbourg.service.notif.service.NatureNotifLocalService;
import eu.strasbourg.service.notif.service.ServiceNotifLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.NOTIF_BO,
                "mvc.command.name=saveService"
        },
        service = MVCActionCommand.class
)
public class SaveServiceActionCommand implements MVCActionCommand {

    private final Log log = LogFactoryUtil.getLog(this.getClass().getName());

    private ThemeDisplay themeDisplay;
    private long serviceId;

    @Override
    public boolean processAction(ActionRequest request, ActionResponse response) {
        try {
            // Récupération du contexte de la requêtes
            themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

            // Validation
            boolean isValid = this.validate(request);
            if (!isValid) {
                // Si pas valide : on reste sur la page d'édition
                PortalUtil.copyRequestParameters(request, response);

                String portletName = (String) request.getAttribute(WebKeys.PORTLET_ID);
                PortletURL returnURL = PortletURLFactoryUtil.create(request, portletName, themeDisplay.getPlid(),
                        PortletRequest.RENDER_PHASE);

                response.setRenderParameter("returnURL", returnURL.toString());
                response.setRenderParameter("cmd", "editService");
                response.setRenderParameter("mvcPath", "/notif-bo-edit-service.jsp");
                return false;
            }

            // Si édition ou création d'une nouvelle entrée
            ServiceNotif service;
            if (this.serviceId == 0) {
                service = _serviceNotifLocalService.createService();
            } else {
                service = _serviceNotifLocalService.fetchServiceNotif(this.serviceId);
            }

            // Champ : nom
            String name = ParamUtil.getString(request, "name");
            service.setName(name);

            // Champ : organisation
            long organizationId = ParamUtil.getLong(request, "organization");
            service.setOrganisationId(organizationId);

            // Champ : picto
            Long pictoId = ParamUtil.getLong(request, "pictoId");
            service.setPictoId(pictoId);

            // Suppression des natures du service
            List<NatureNotif> natures = service.getNatures();
            for (NatureNotif nature : natures) {
                _natureNotifLocalService.deleteNatureNotif(nature);
            }

            // Ajout des natures liées au service
            String serviceNaturesIndexes = ParamUtil.getString(request, "serviceNaturesIndexes");
            for (String serviceNaturesIndex : serviceNaturesIndexes.split(",")) {
                if (Validator.isNotNull(serviceNaturesIndex)
                        && Validator.isNotNull(
                        ParamUtil.getString(request, "natureName" + serviceNaturesIndex))) {
                    String natureName = ParamUtil.getString(request, "natureName" + serviceNaturesIndex);
                    NatureNotif nature = _natureNotifLocalService.createNature();
                    nature.setName(natureName);
                    nature.setServiceId(service.getServiceId());
                    _natureNotifLocalService.updateNatureNotif(nature);
                }
            }

            // Suppression des messages du service
            List<Message> messages = service.getMessages();
            for (Message message : messages) {
                _messageLocalService.deleteMessage(message);
            }

            // Ajout des messages liés au service
            String serviceMessagesIndexes = ParamUtil.getString(request, "serviceMessagesIndexes");
            for (String serviceMessagesIndex : serviceMessagesIndexes.split(",")) {
                if (Validator.isNotNull(serviceMessagesIndex)
                        && Validator.isNotNull(
                        ParamUtil.getString(request, "content" + serviceMessagesIndex))) {
                    Map<Locale, String> content = LocalizationUtil.getLocalizationMap(request, "content" + serviceMessagesIndex);
                    Message message = _messageLocalService.createMessage();
                    message.setContentMap(content);
                    message.setServiceId(service.getServiceId());
                    _messageLocalService.updateMessage(message);
                }
            }

            _serviceNotifLocalService.updateServiceNotif(service);


        } catch (Exception e) {
            log.error(e);
        }
        return true;
    }

    /**
     * Validation de la requête
     */
    private boolean validate(ActionRequest request) {
        boolean isValid = true;

        this.serviceId = ParamUtil.getLong(request, "serviceId");

        //  nom du service
        if (Validator.isNull(ParamUtil.getString(request, "name"))) {
            SessionErrors.add(request, "name-error");
            isValid = false;
        }

        // initulé de l'organisation du service
        if (Validator.isNull(ParamUtil.getLong(request, "organization"))) {
            SessionErrors.add(request, "organization-error");
            isValid = false;
        }

        // natures
        // on vérifie que le nombre de natures récupérées correspond bien au nombre d'indexes reçus
        String naturesIndexes = ParamUtil.getString(request, "serviceNaturesIndexes");
        long nbNature = request.getActionParameters().getNames().stream().filter(p -> p.contains("natureName") && !p.contains("_")).count();
        long nbIndexNature = naturesIndexes.isEmpty()?0:naturesIndexes.split(",").length;
        if(nbIndexNature != nbNature) {
            SessionErrors.add(request, "nb-indexes-error");
            isValid = false;
        }
        for (String index: naturesIndexes.split(",")) {
            if (Validator.isNull(ParamUtil.getString(request, "natureName" + index))) {
                SessionErrors.add(request, "natures-error");
                isValid = false;
                break;
            }
        }

        // messages
        // on vérifie que le nombre de messages récupérés correspond bien au nombre d'indexes reçus
        String messagesIndexes = ParamUtil.getString(request, "serviceMessagesIndexes");
        long nbMessage = request.getActionParameters().getNames().stream().filter(p -> p.contains("natureName") && !p.contains("_")).count();
        long nbIndexMessage = messagesIndexes.isEmpty()?0:messagesIndexes.split(",").length;
        if(nbIndexMessage != nbMessage) {
            SessionErrors.add(request, "nb-indexes-error");
            isValid = false;
        }
        for (String index: messagesIndexes.split(",")) {
            if (Validator.isNull(ParamUtil.getString(request, "content" + index))) {
                SessionErrors.add(request, "messages-error");
                isValid = false;
                break;
            }
        }

        return isValid;
    }

    private ServiceNotifLocalService _serviceNotifLocalService;

    @Reference(unbind = "-")
    protected void setServiceNotifLocalService(ServiceNotifLocalService serviceNotifLocalService) {
        _serviceNotifLocalService = serviceNotifLocalService;
    }

    private NatureNotifLocalService _natureNotifLocalService;

    @Reference(unbind = "-")
    protected void setNatureNotifLocalService(NatureNotifLocalService natureNotifLocalService) {
        _natureNotifLocalService = natureNotifLocalService;
    }

    private MessageLocalService _messageLocalService;

    @Reference(unbind = "-")
    protected void setMessageLocalService(MessageLocalService messageLocalService) {
        _messageLocalService = messageLocalService;
    }
}
