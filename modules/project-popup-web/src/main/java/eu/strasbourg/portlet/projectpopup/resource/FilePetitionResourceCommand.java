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
import com.liferay.portal.kernel.util.HtmlUtil;
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
import java.util.ArrayList;
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
        this.publikID = getPublikID(request);
        if (this.publikID == null || this.publikID.isEmpty())
            message = "utilisateur non enregistr&eacute;/identifi&eacute;";
        else
        	this.user = PublikUserLocalServiceUtil.getByPublikUserId(this.publikID);

        this.dateFormat = new SimpleDateFormat(PATTERN);
        this.birthday = ParamUtil.getDate(request, BIRTHDAY, dateFormat);
        this.address = HtmlUtil.stripHtml(ParamUtil.getString(request, ADDRESS));
        this.city = HtmlUtil.stripHtml(ParamUtil.getString(request, CITY));
        this.postalcode = ParamUtil.getLong(request, POSTALCODE);
        this.phone = HtmlUtil.stripHtml(ParamUtil.getString(request, PHONE));
        this.mobile = HtmlUtil.stripHtml(ParamUtil.getString(request, MOBILE));
        this.lastname = HtmlUtil.stripHtml(ParamUtil.getString(request, LASTNAME));
        this.firstname = HtmlUtil.stripHtml(ParamUtil.getString(request, FIRSTNAME));
        this.email = HtmlUtil.stripHtml(ParamUtil.getString(request, EMAIL));
        this.lieu = HtmlUtil.stripHtml(ParamUtil.getString(request,LIEU));
        this.title = HtmlUtil.stripHtml(ParamUtil.getString(request, PETITIONTITLE));
        this.description = HtmlUtil.stripHtml(ParamUtil.getString(request, PETITIONDESCRIPTION).replace("\n", "<br>"));
        this.projectId = ParamUtil.getLong(request, PROJECT);
        this.quartierId = ParamUtil.getLong(request, QUARTIER);
        this.themeId = ParamUtil.getLong(request, THEME);

        boolean isValid = validate(request);
        if (!isValid)
            message = LanguageUtil.get(originalRequest, "general-error");
        
        boolean savedInfo = false;
        if (message.isEmpty()) {
            boolean saveInfo = ParamUtil.getBoolean(request, SAVEINFO);
            if (saveInfo) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
                String dateNaiss = sdf.format(ParamUtil.getDate(request, "birthday", dateFormat));
                PublikApiClient.setAllUserDetails(
                		this.publikID, 
                		this.user.getLastName(), 
                		this.address, 
                		"" + this.postalcode,
                		this.city,
                		dateNaiss, 
                		this.phone, 
                		this.mobile
                );
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
            List<Long> identifiants = new ArrayList<>();
            if (this.quartierId == 0) {
                List<AssetCategory> districts = AssetVocabularyHelper.getAllDistrictsFromCity(CITY_NAME);
                assert districts != null;
                identifiants = districts.stream()
                        .map(AssetCategoryModel::getCategoryId)
                        .collect(Collectors.toList());
            }else {
                identifiants.add(this.quartierId);
            }
            if (this.projectId != 0) {
                identifiants.add(this.projectId);
            }
            if (this.themeId != 0) {
                identifiants.add(this.themeId);
            }
            long[] ids = new long[identifiants.size()];
            for (int i = 0; i < identifiants.size(); i++) {
                ids[i]=identifiants.get(i);
            }
            sc.setAssetCategoryIds(ids);

            petition = PetitionLocalServiceUtil.createPetition(sc);
            petition.setTitle(this.title);
            petition.setDescription(this.description);
            petition.setQuotaSignature(signatureNumber);
            petition.setPetitionnaireAdresse(this.address);
            petition.setPetitionnaireBirthday(this.birthday);
            petition.setPetitionnaireCity(this.city);
            petition.setPlaceTextArea(this.lieu);
            petition.setPetitionnaireFirstname(this.firstname);
            petition.setPetitionnaireLastname(this.lastname);
            petition.setPetitionnairePostalCode(this.postalcode);
            petition.setPetitionnairePhone(this.phone);
            if (!this.mobile.isEmpty())
                petition.setPetitionnairePhone(this.mobile);
            petition.setPetitionnaireEmail(this.email);
            petition.setPublikId(this.publikID);
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

    private boolean validate(ResourceRequest request) {
        boolean isValid = true;

        // title
        if (Validator.isNull(this.title)) {
            isValid = false;
        }

        // description
        if (Validator.isNull(this.description)) {
            isValid = false;
        }

        // birthday
        if (Validator.isNull(this.birthday)) {
            isValid = false;
        }

        // city
        if (Validator.isNull(this.city)) {
            isValid = false;
        }

        // address
        if (Validator.isNull(this.address)) {
            isValid = false;
        }

        // postalcode
        if (Validator.isNull(this.postalcode)) {
            isValid = false;
        }

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
