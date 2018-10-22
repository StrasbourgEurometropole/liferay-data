package eu.strasbourg.portlet.project.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.project.model.BudgetParticipatif;
import eu.strasbourg.service.project.model.PlacitPlace;
import eu.strasbourg.service.project.service.BudgetParticipatifLocalService;
import eu.strasbourg.service.project.service.PlacitPlaceLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author alexandre.quere
 */
@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_BO,
                "mvc.command.name=actionBudgetParticipatif"
        },
        service = MVCActionCommand.class
)
public class updateBudgetParticipatifActionCommand implements MVCActionCommand {

    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());


    @Reference(unbind = "-")
    protected void setbudgetLocalService(BudgetParticipatifLocalService budgetLocalService) {
        _budgetLocalService = budgetLocalService;
    }

    @Reference(unbind = "-")
    protected void setPlacitPlaceLocalService(PlacitPlaceLocalService placitPlaceLocalService) {
        _placitPlaceLocalService = placitPlaceLocalService;
    }

    private BudgetParticipatifLocalService _budgetLocalService;
    private PlacitPlaceLocalService _placitPlaceLocalService;

    @Override
    public boolean processAction(ActionRequest request, ActionResponse response) {
        try {
            String cmd = ParamUtil.getString(request, "cmd");
            if ("updateBudgetParticipatif".equals(cmd)){
                ServiceContext sc = ServiceContextFactory.getInstance(request);
                User user = UserLocalServiceUtil.getUser(sc.getUserId());
                //Récupération des champs
                long budgetParticipatifId = ParamUtil.getLong(request,"budgetParticipatifId");
                String title = ParamUtil.getString(request, "title");
                String description = ParamUtil.getString(request, "description");
                long budget = ParamUtil.getLong(request, "budget");
                String motif = ParamUtil.getString(request, "motif");
                String adresse = ParamUtil.getString(request, "citoyenAdresse");
                long postalCode = ParamUtil.getLong(request, "citoyenPostalCode");
                String city = ParamUtil.getString(request, "citoyenCity");
                String mail = ParamUtil.getString(request, "citoyenEmail");
                String phone= ParamUtil.getString(request, "citoyenPhone");
                String mobile = ParamUtil.getString(request, "citoyenMobile");
                String copyright = ParamUtil.getString(request, "externalImageCopyright");
                String videoUrl = ParamUtil.getString(request, "videoUrl");
                String externalImageURL = ParamUtil.getString(request, "externalImageURL");
                boolean mediaChoice = ParamUtil.getBoolean(request, "mediaChoice");
                String placeTextArea = ParamUtil.getString(request, "consultationPlacesText");
                boolean isCrush = ParamUtil.getBoolean(request, "isCrush");
                String crushComment = ParamUtil.getString(request, "crushComment");
                String placitPlacesIndexesString = ParamUtil.getString(request, "placeIndexes");

                BudgetParticipatif budgetParticipatif;
                if (budgetParticipatifId==0)
                    budgetParticipatif = _budgetLocalService.createBudgetParticipatif(sc);
                else
                    budgetParticipatif = _budgetLocalService.getBudgetParticipatif(budgetParticipatifId);

                budgetParticipatif.setTitle(title);
                budgetParticipatif.setDescription(description);
                budgetParticipatif.setBudget(budget);
                budgetParticipatif.setMotif(motif);
                budgetParticipatif.setUserName(user.getFullName());
                budgetParticipatif.setCitoyenAdresse(adresse);
                budgetParticipatif.setCitoyenPostalCode(postalCode);
                budgetParticipatif.setCitoyenCity(city);
                budgetParticipatif.setCitoyenEmail(mail);
                budgetParticipatif.setCitoyenPhone(phone);
                budgetParticipatif.setCitoyenMobile(mobile);
                budgetParticipatif.setExternalImageCopyright(copyright);
                budgetParticipatif.setVideoUrl(videoUrl);
                budgetParticipatif.setExternalImageURL(externalImageURL);
                budgetParticipatif.setMediaChoice(mediaChoice);
                budgetParticipatif.setPlaceTextArea(placeTextArea);
                budgetParticipatif.setIsCrush(isCrush);
                budgetParticipatif.setCrushComment(crushComment);
                for (PlacitPlace placitPlace: budgetParticipatif.getPlacitPlaces()){
                    _placitPlaceLocalService.removePlacitPlace(placitPlace.getPlacitPlaceId());
                }
                for (String placitPlacesIndexe : placitPlacesIndexesString.split(",")){
                    String placeSIGId = ParamUtil.getString(request, "placeSIGId" + placitPlacesIndexe);
                    String placeName = ParamUtil.getString(request, "placeName" + placitPlacesIndexe);
                    long placeCityId = ParamUtil.getLong(request, "placeCityId" + placitPlacesIndexe);

                    // Si il existe au moins un lieu SIG ou manuel
                    if (Validator.isNotNull(placitPlacesIndexe)
                            &&(Validator.isNotNull(placeSIGId)
                            ||(Validator.isNotNull(placeName)
                            &&Validator.isNotNull(placeCityId)))){
                        // Initialisation de l'entité
                        PlacitPlace placitPlace = _placitPlaceLocalService.createPlacitPlace(sc);

                        if (Validator.isNotNull(placeSIGId)) {
                            // Lieu SIG
                            placitPlace.setPlaceSIGId(placeSIGId);
                        } else {
                            // Nom du lieu
                            placitPlace.setPlaceName(placeName);

                            // Numéro de rue
                            String placeStreetNumber = ParamUtil.getString(request,
                                    "placeStreetNumber" + placitPlacesIndexe);
                            placitPlace.setPlaceStreetNumber(placeStreetNumber);

                            // Nom de la rue
                            String placeStreetName = ParamUtil.getString(request,
                                    "placeStreetName" + placitPlacesIndexe);
                            placitPlace.setPlaceStreetName(placeStreetName);

                            // Code postal
                            String placeZipCode = ParamUtil.getString(request,
                                    "placeZipCode" + placitPlacesIndexe);
                            placitPlace.setPlaceZipCode(placeZipCode);

                            // Ville
                            placitPlace.setPlaceCityId(placeCityId);

                            // Image du lieu
                            long placeImageId = ParamUtil.getLong(request,
                                    "placeImageId" + placitPlacesIndexe);
                            placitPlace.setImageId(placeImageId);
                        }
                        // Rattachement a la participation
                        placitPlace.setPetitionId(budgetParticipatif.getBudgetParticipatifId());

                        // Mise à jour en base
                        _placitPlaceLocalService.updatePlacitPlace(placitPlace, sc);
                    }
                }

            }
        } catch (PortalException e) {
            _log.error("erreur lors de la mise à jour d'un budget : ",e);
        }
        return true;
    }


}
