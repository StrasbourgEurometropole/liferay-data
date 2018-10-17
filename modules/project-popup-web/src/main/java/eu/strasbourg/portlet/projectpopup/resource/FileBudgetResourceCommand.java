package eu.strasbourg.portlet.projectpopup.resource;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetCategoryModel;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.service.project.model.BudgetParticipatif;
import eu.strasbourg.service.project.service.BudgetParticipatifLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.PublikApiClient;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static eu.strasbourg.portlet.projectpopup.ProjectPopupPortlet.CITY_NAME;
import static eu.strasbourg.portlet.projectpopup.utils.ProjectPopupUtils.getPublikID;
import static org.apache.commons.text.StringEscapeUtils.escapeHtml4;

/**
 * @author alexandre.quere
 */
@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_POPUP_WEB,
                "mvc.command.name=fileBudget"
        },
        service = MVCResourceCommand.class
)
public class FileBudgetResourceCommand implements MVCResourceCommand {


    private static final String BIRTHDAY = "birthday";
    private static final String ADDRESS = "address";
    private static final String CITY = "city";
    private static final String POSTALCODE = "postalcode";
    private static final String PHONE = "phone";
    private static final String MOBILE = "mobile";
    private static final String CONSULTATIONPLACETEXT = "consultationPlacesText";
    private static final String BUDGETTITLE = "budgettitle";
    private static final String BUDGETDESCRIPTION = "budgetdescription";
    private static final String LIEU = "budgetLieux";
    private static final String PROJECT = "project";
    private static final String QUARTIER = "quartier";
    private static final String THEME = "theme";
    private static final String PHOTO = "photo";
    private static final String VIDEO = "video";
    private static final String SAVEINFO = "saveinfo";
    private static final String LASTNAME = "lastname";
    private static final String FIRSTNAME = "firstname";
    private static final String EMAIL = "email";
    private static final String PATTERN = "dd/MM/yyyy";

    private String publikID;
    private PublikUser user;
    private DateFormat dateFormat;
    private Date birthday;
    private String address;
    private String city;
    private long postalcode;
    private String phone;
    private String mobile;
    private String lastname;
    private String firstname;
    private String email;
    private String photo;
    private String video;
    private String title;
    private String placeText;
    private String description;
    private String lieu;
    private long projectId;
    private long quartierId;
    private long themeId;

    /**
     * le log
     */
    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

    @Override
    public boolean serveResource(ResourceRequest request, ResourceResponse response) throws PortletException {
        LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(request);
        HttpServletRequest originalRequest = liferayPortletRequest.getHttpServletRequest();
        boolean result = false;
        String message = "";
        publikID = getPublikID(request);
        if (publikID == null || publikID.isEmpty())
            message = "utilisateur non enregistr&eacute;/identifi&eacute;";
        else
            user = PublikUserLocalServiceUtil.getByPublikUserId(publikID);
        dateFormat = new SimpleDateFormat(PATTERN);
        address = escapeHtml4(ParamUtil.getString(request, ADDRESS));
        city = escapeHtml4(ParamUtil.getString(request, CITY));
        postalcode = ParamUtil.getLong(request, POSTALCODE);
        phone = escapeHtml4(ParamUtil.getString(request, PHONE));
        mobile = escapeHtml4(ParamUtil.getString(request, MOBILE));
        lastname = escapeHtml4(ParamUtil.getString(request, LASTNAME));
        firstname = escapeHtml4(ParamUtil.getString(request, FIRSTNAME));
        email = escapeHtml4(ParamUtil.getString(request, EMAIL));
        lieu = escapeHtml4(ParamUtil.getString(request,LIEU));
        photo = escapeHtml4(ParamUtil.getString(request,PHOTO));
        video = escapeHtml4(ParamUtil.getString(request,VIDEO));
        placeText = escapeHtml4(ParamUtil.getString(request,CONSULTATIONPLACETEXT));
        title = escapeHtml4(ParamUtil.getString(request, BUDGETTITLE));
        description = escapeHtml4(ParamUtil.getString(request, BUDGETDESCRIPTION));
        projectId = ParamUtil.getLong(request, PROJECT);
        quartierId = ParamUtil.getLong(request, QUARTIER);
        themeId = ParamUtil.getLong(request, THEME);

        boolean isValid = validate();
        if (!isValid)
            message = LanguageUtil.get(originalRequest, "general-error");

        boolean savedInfo = false;
        if (message.isEmpty()) {
            boolean saveInfo = ParamUtil.getBoolean(request, SAVEINFO);
            if (saveInfo) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
                String dateNaiss = sdf.format(ParamUtil.getDate(request, "birthday", dateFormat));
                PublikApiClient.setAllUserDetails(publikID, user.getLastName(), address, "" + postalcode, city, dateNaiss, phone, mobile);
            }
            result = sendBudget(request);
        }

        // Récupération du json des entités
        JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();
        jsonResponse.put("result", result);
        jsonResponse.put("message", message);
        jsonResponse.put("savedInfo", savedInfo);

        // Recuperation de l'élément d'écriture de la réponse
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.print(jsonResponse.toString());
        } catch (IOException e) {
            _log.error("erreur dans l'ecriture du budget : ",e);
        }
        return result;
    }

    private boolean sendBudget(ResourceRequest request) throws PortletException {
        ServiceContext sc;
        BudgetParticipatif budgetParticipatif;

        try {
            sc = ServiceContextFactory.getInstance(request);
            sc.setWorkflowAction(WorkflowConstants.ACTION_SAVE_DRAFT);
            List<Long> identifiants = new ArrayList<>();
            if (quartierId==0) {
                List<AssetCategory> districts = AssetVocabularyHelper.getAllDistrictsFromCity(CITY_NAME);
                assert districts != null;
                identifiants = districts.stream()
                        .map(AssetCategoryModel::getCategoryId)
                        .collect(Collectors.toList());
            }else {
                identifiants.add(quartierId);
            }
            if (projectId!=0) {
                identifiants.add(projectId);
            }
            if (themeId!=0) {
                identifiants.add(themeId);
            }
            long[] ids = new long[identifiants.size()];
            for (int i = 0; i < identifiants.size(); i++) {
                ids[i]=identifiants.get(i);
            }
            sc.setAssetCategoryIds(ids);

            budgetParticipatif = BudgetParticipatifLocalServiceUtil.createBudgetParticipatif(sc);
            budgetParticipatif.setTitle(title);
            budgetParticipatif.setDescription(description);
            budgetParticipatif.setUserId(user.getUserId());
            budgetParticipatif.setConsultationPlacesText(lieu);
            budgetParticipatif.setCitoyenFirstname(user.getFirstName());
            budgetParticipatif.setCitoyenLastname(user.getLastName());
            budgetParticipatif.setCitoyenAdresse(address);
            budgetParticipatif.setCitoyenPostalCode(postalcode);
            budgetParticipatif.setCitoyenCity(city);
            budgetParticipatif.setCitoyenEmail(user.getEmail());
            budgetParticipatif.setCitoyenMobile(mobile);
            if (!photo.isEmpty()){
                budgetParticipatif.setExternalImageURL(photo);
                budgetParticipatif.setHasCopyright(true);
            }
            if (!video.isEmpty())
                budgetParticipatif.setVideoUrl(video);
            budgetParticipatif.setPlaceTextArea(placeText);
            budgetParticipatif.setCitoyenPhone(phone);
            budgetParticipatif.setPublikId(publikID);
            budgetParticipatif = BudgetParticipatifLocalServiceUtil.updateBudgetParticipatif(budgetParticipatif, sc);
            AssetEntry assetEntry = budgetParticipatif.getAssetEntry();
            if (assetEntry == null)
                throw new PortalException("aucune assetCategory pour le budget"
                        + budgetParticipatif.getBudgetParticipatifId());
        } catch (PortalException e) {
            _log.error(e);
            throw new PortletException(e);
        }
        _log.info("budget cree : " + budgetParticipatif);
        return true;
    }

    private boolean validate() {
        boolean isValid = true;
        // title
        if (Validator.isNull(title)) {
            isValid = false;
        }

        // description
        if (Validator.isNull(description)) {
            isValid = false;
        }

        // city
        if (Validator.isNull(city)) {
            isValid = false;
        }

        if (!"STRASBOURG".equals(city.toUpperCase()))
            isValid = false;

        // address
        if (Validator.isNull(address)) {
            isValid = false;
        }

        // postalcode
        if (Validator.isNull(postalcode)) {
            isValid = false;
        }

        return isValid;
    }

}
