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
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.service.project.model.Petition;
import eu.strasbourg.service.project.service.PetitionLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.PublikApiClient;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static eu.strasbourg.portlet.projectpopup.ProjectPopupPortlet.CITY_NAME;

/**
 * @author alexandre.quere
 */
@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_POPUP_WEB,
                "mvc.command.name=filePetition"
        },
        service = MVCResourceCommand.class
)
public class FilePetitionResourceCommand implements MVCResourceCommand {

    private static final String BIRTHDAY = "birthday";
    private static final String ADDRESS = "address";
    private static final String CITY = "city";
    private static final String POSTALCODE = "postalcode";
    private static final String PHONE = "phone";
    private static final String MOBILE = "mobile";
    private static final String PETITIONTITLE = "petitiontitle";
    private static final String PETITIONDESCRIPTION = "petitiondescription";
    private static final String LIEU = "consultationPlacesText";
    private static final String PROJECT = "project";
    private static final String QUARTIER = "quartier";
    private static final String THEME = "theme";
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
    private String title;
    private String description;
    private String lieu;
    private long projectId;
    private long quartierId;
    private long themeId;

    /**
     * le log
     */
    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

    /**
     * En attendant de faire un fichier properties, on utilise cette variable
     */

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
        birthday = ParamUtil.getDate(request, BIRTHDAY, dateFormat);
        address = ParamUtil.getString(request, ADDRESS);
        city = ParamUtil.getString(request, CITY);
        postalcode = ParamUtil.getLong(request, POSTALCODE);
        phone = ParamUtil.getString(request, PHONE);
        mobile = ParamUtil.getString(request, MOBILE);
        lastname = ParamUtil.getString(request, LASTNAME);
        firstname = ParamUtil.getString(request, FIRSTNAME);
        email = ParamUtil.getString(request, EMAIL);
        lieu = ParamUtil.getString(request,LIEU);
        title = ParamUtil.getString(request, PETITIONTITLE);
        description = ParamUtil.getString(request, PETITIONDESCRIPTION).replace("\n", "<br>");
        projectId = ParamUtil.getLong(request, PROJECT);
        quartierId = ParamUtil.getLong(request, QUARTIER);
        themeId = ParamUtil.getLong(request, THEME);

        boolean isValid = validate(request);

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
            result = sendPetition(request);
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.print(jsonResponse.toString());

        return result;
    }

    private boolean sendPetition(ResourceRequest request) throws PortletException {
        ServiceContext sc;
        Petition petition;
        
        try {
            ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
            int signatureNumber = (int) themeDisplay.getSiteGroup().getExpandoBridge().getAttribute("number_of_signatures_required_per_petition");

            sc = ServiceContextFactory.getInstance(request);
            sc.setWorkflowAction(WorkflowConstants.ACTION_SAVE_DRAFT);
            List<Long> identifiants = null;
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

            petition = PetitionLocalServiceUtil.createPetition(sc);
            petition.setTitle(title);
            petition.setDescription(description);
            petition.setQuotaSignature(signatureNumber);
            petition.setPetitionnaireAdresse(address);
            petition.setPetitionnaireBirthday(birthday);
            petition.setPetitionnaireCity(city);
            petition.setConsultationPlacesText(lieu);
            petition.setPetitionnaireFirstname(firstname);
            petition.setPetitionnaireLastname(lastname);
            petition.setPetitionnairePostalCode(postalcode);
            petition.setPetitionnairePhone(phone);
            if (!mobile.isEmpty())
                petition.setPetitionnairePhone(mobile);
            petition.setPetitionnaireEmail(email);
            petition.setPublikId(publikID);
            petition = PetitionLocalServiceUtil.updatePetition(petition, sc);
            AssetEntry assetEntry = petition.getAssetEntry();
            if (assetEntry == null)
                throw new PortalException("aucune assetCategory pour la pétition"
                        + petition.getPetitionId());
        } catch (PortalException e) {
            _log.error(e);
            throw new PortletException(e);
        }
        _log.info("pétition créé : " + petition);
        return true;
    }

    private boolean checkLegalAge(Date birthday) {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Instant instant = birthday.toInstant();
        LocalDate localDate = instant.atZone(defaultZoneId).toLocalDate();
        LocalDate current = LocalDate.now(defaultZoneId);
        int result = Period.between(localDate, current).getYears();
        return result >= 16;
    }

    private boolean checkPostalCode(long postalcode) {
        int param = Math.toIntExact(postalcode);
        return param == 67000 || param == 67100 || param == 67200;
    }

    private boolean checkCity(String city) {
        return "strasbourg".equals(city.toLowerCase());
    }

    private boolean validate(ResourceRequest request) {
        boolean isValid = true;
        DateFormat dateFormat = new SimpleDateFormat(PATTERN);

        // title
        if (Validator.isNull(title)) {
            isValid = false;
        }

        // description
        if (Validator.isNull(description)) {
            isValid = false;
        }

        // birthday
        if (Validator.isNull(birthday)) {
            isValid = false;
        }

        // city
        if (Validator.isNull(city)) {
            isValid = false;
        }

        // address
        if (Validator.isNull(address)) {
            isValid = false;
        }

        // postalcode
        if (Validator.isNull(postalcode)) {
            isValid = false;
        }
        if (isValid)
            isValid = checkCity(city);
        if (isValid)
            isValid = checkPostalCode(postalcode);
        if (isValid)
            isValid = checkLegalAge(birthday);

        return isValid;
    }

    /**
     * Récupération du publik ID avec la session
     */
    private String getPublikID(PortletRequest request) {
        LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(request);
        HttpServletRequest originalRequest = liferayPortletRequest.getHttpServletRequest();
        return SessionParamUtil.getString(originalRequest, "publik_internal_id");
    }
}
