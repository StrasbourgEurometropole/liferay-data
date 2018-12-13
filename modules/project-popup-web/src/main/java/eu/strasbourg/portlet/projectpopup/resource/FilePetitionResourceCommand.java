package eu.strasbourg.portlet.projectpopup.resource;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetCategoryModel;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
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
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
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
    private static final String IN_THE_NAME_OF = "inTheNameOf";
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
    private String inTheNameOf;
    private String lieu;
    private long projectId;
    private long quartierId;
    private long themeId;
    private String message;

    /**
     * le log
     */
    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

    /**
     * En attendant de faire un fichier properties, on utilise cette variable
     */

    @Override
    public boolean serveResource(ResourceRequest request, ResourceResponse response) throws PortletException {
        
    	// Initialisations respectives de : resultat probant de la requete, sauvegarde ou non des informations Publik, message de retour
        boolean result = false;
        boolean savedInfo = false;
        this.message = "";
        
        // Recuperation de l'utilsiteur Publik ayant lance la demande
        this.publikID = getPublikID(request);
        
        // Recuperation des informations du formulaire
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
        this.inTheNameOf = HtmlUtil.stripHtml(ParamUtil.getString(request, IN_THE_NAME_OF));
        this.projectId = ParamUtil.getLong(request, PROJECT);
        this.quartierId = ParamUtil.getLong(request, QUARTIER);
        this.themeId = ParamUtil.getLong(request, THEME);
        
        // Verification de la validite des informations
        if (validate(request)) {
        	
        	// Mise a jour des informations du compte Publik si requete valide et demande par l'utilisateur
            savedInfo = ParamUtil.getBoolean(request, SAVEINFO);
            if (savedInfo) {
                SimpleDateFormat sdf = new SimpleDateFormat(PATTERN);
                String dateNaiss = sdf.format(birthday);
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
            
            // Envoi de la demande
            result = sendPetition(request);
        }

        // Récupération du json des entités
        JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();
        jsonResponse.put("result", result);
        jsonResponse.put("message", this.message);
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
            petition.setInTheNameOf(this.inTheNameOf);
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
    	
    	// utilisateur 
        if (this.publikID == null || this.publikID.isEmpty()) {
            this.message = "Utilisateur non recconu";
            return false;
        } else {
        	this.user = PublikUserLocalServiceUtil.getByPublikUserId(this.publikID);
        	
        	if (this.user.isBanned()) {
        		this.message = "Vous ne pouvez soutenir ce projet";
        		return false;
        	} else if (this.user.getPactSignature() == null) {
        		this.message = "Vous devez signer le Pacte pour soumettre un projet";
        		return false;
        	}
        }

        // title
        if (Validator.isNull(this.title)) {
        	this.message = "Titre non valide";
            return false;
        }
        
        // description
        if (Validator.isNull(this.description)) {
        	this.message = "Description non valide";
            return false;
        }

        // birthday
        if (Validator.isNull(this.birthday)) {
        	this.message = "Date de naissance non valide";
            return false;
        }

        // city
        if (Validator.isNull(this.city)) {
        	this.message = "Ville non valide";
            return false;
        }

        // address
        if (Validator.isNull(this.address)) {
        	this.message = "Adresse non valide";
        	return false;
        }

        // postalcode
        if (Validator.isNull(this.postalcode)) {
        	this.message = "Code postal non valide";
            return false;
        }

        return true;
    }

}
