package eu.strasbourg.portlet.projectpopup.resource;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetCategoryModel;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadRequest;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
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
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    private static final String BUDGETTITLE = "title";
    private static final String BUDGETDESCRIPTION = "description";
    private static final String LIEU = "budgetLieux";
    private static final String PROJECT = "project";
    private static final String QUARTIER = "quartier";
    private static final String THEME = "theme";
    private static final String PHOTO = "budgetPhoto";
    private static final String VIDEO = "video";
    private static final String SAVEINFO = "saveinfo";
    private static final String LASTNAME = "lastname";
    private static final String FIRSTNAME = "firstname";
    private static final String EMAIL = "email";
    private static final String PATTERN = "dd/MM/yyyy";

    private String publikID;
    private PublikUser user;
    private DateFormat dateFormat;
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
        this.publikID = getPublikID(request);
        if (this.publikID == null || this.publikID.isEmpty())
            message = "utilisateur non enregistr&eacute;/identifi&eacute;";
        else
        	this.user = PublikUserLocalServiceUtil.getByPublikUserId(publikID);
        this.dateFormat = new SimpleDateFormat(PATTERN);
        this.address = HtmlUtil.stripHtml(ParamUtil.getString(request, ADDRESS));
        this.city = HtmlUtil.stripHtml(ParamUtil.getString(request, CITY));
        this.postalcode = ParamUtil.getLong(request, POSTALCODE);
        this.phone = HtmlUtil.stripHtml(ParamUtil.getString(request, PHONE));
        this.mobile = HtmlUtil.stripHtml(ParamUtil.getString(request, MOBILE));
        this.lastname = HtmlUtil.stripHtml(ParamUtil.getString(request, LASTNAME));
        this.firstname = HtmlUtil.stripHtml(ParamUtil.getString(request, FIRSTNAME));
        this.email = HtmlUtil.stripHtml(ParamUtil.getString(request, EMAIL));
        this.lieu = HtmlUtil.stripHtml(ParamUtil.getString(request,LIEU));
        this.video = HtmlUtil.stripHtml(ParamUtil.getString(request,VIDEO));
        this.placeText = HtmlUtil.stripHtml(ParamUtil.getString(request,CONSULTATIONPLACETEXT));
        this.title = HtmlUtil.stripHtml(ParamUtil.getString(request, BUDGETTITLE));
        this.description = HtmlUtil.stripHtml(ParamUtil.getString(request, BUDGETDESCRIPTION));
        this.projectId = ParamUtil.getLong(request, PROJECT);
        this.quartierId = ParamUtil.getLong(request, QUARTIER);
        this.themeId = ParamUtil.getLong(request, THEME);
        Long webImageId = ParamUtil.getLong(request, "webImageId");

        boolean isValid = validate();
        if (!isValid)
            message = LanguageUtil.get(originalRequest, "general-error");

        boolean savedInfo = false;
        if (message.isEmpty()) {
            boolean saveInfo = ParamUtil.getBoolean(request, SAVEINFO);
            if (saveInfo) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
                String dateNaiss = sdf.format(ParamUtil.getDate(request, BIRTHDAY, dateFormat));
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
            List<Long> identifiants = new ArrayList<Long>();
            if (this.quartierId==0) {
                List<AssetCategory> districts = AssetVocabularyHelper.getAllDistrictsFromCity(CITY_NAME);
                assert districts != null;
                identifiants = districts.stream()
                        .map(AssetCategoryModel::getCategoryId)
                        .collect(Collectors.toList());
            }else {
                identifiants.add(quartierId);
            }
            if (this.projectId!=0) {
                identifiants.add(projectId);
            }
            if (this.themeId!=0) {
                identifiants.add(themeId);
            }
            long[] ids = new long[identifiants.size()];
            for (int i = 0; i < identifiants.size(); i++) {
                ids[i]=identifiants.get(i);
            }
            sc.setAssetCategoryIds(ids);

            budgetParticipatif = BudgetParticipatifLocalServiceUtil.createBudgetParticipatif(sc);
            budgetParticipatif.setTitle(this.title);
            budgetParticipatif.setDescription(this.description);
            budgetParticipatif.setUserId(this.user.getUserId());
            budgetParticipatif.setCitoyenFirstname(this.user.getFirstName());
            budgetParticipatif.setCitoyenLastname(this.user.getLastName());
            budgetParticipatif.setCitoyenAdresse(this.address);
            budgetParticipatif.setCitoyenPostalCode(this.postalcode);
            budgetParticipatif.setCitoyenCity(this.city);
            budgetParticipatif.setCitoyenEmail(this.user.getEmail());
            budgetParticipatif.setCitoyenMobile(this.mobile);
            if (!this.video.isEmpty())
                budgetParticipatif.setVideoUrl(this.video);
            budgetParticipatif.setPlaceTextArea(this.placeText);
            budgetParticipatif.setCitoyenPhone(this.phone);
            budgetParticipatif.setPublikId(this.publikID);
            budgetParticipatif = uploadFile(budgetParticipatif,request);
            budgetParticipatif = BudgetParticipatifLocalServiceUtil.updateBudgetParticipatif(budgetParticipatif, sc);
            AssetEntry assetEntry = budgetParticipatif.getAssetEntry();
            if (assetEntry == null)
                throw new PortalException("aucune assetCategory pour le budget"
                        + budgetParticipatif.getBudgetParticipatifId());
        } catch (PortalException | IOException e) {
            _log.error(e);
            throw new PortletException(e);
        }
        _log.info("budget cree : " + budgetParticipatif);
        return true;
    }

    /**
     * méthode permettant de récuperer l'image uploadée par l'utilisateur.
     * @param budgetParticipatif le budget participatif correspondant.
     * @param request la request
     * @return le budgetParticipatif avec l'imageId
     * @throws IOException
     * @throws PortalException
     */
    private BudgetParticipatif uploadFile(BudgetParticipatif budgetParticipatif,
                                          ResourceRequest request)
            throws IOException, PortalException {
        ThemeDisplay themeDisplay = (ThemeDisplay) request
                .getAttribute(WebKeys.THEME_DISPLAY);
        ServiceContext sc = ServiceContextFactory.getInstance(request);
        UploadRequest uploadRequest = PortalUtil.getUploadPortletRequest(request);
        File budgetPhoto = uploadRequest.getFile("budgetPhoto");
        _log.info("budgetPhoto : [" + budgetPhoto + "]");

        if (budgetPhoto != null && budgetPhoto.exists()) {
            _log.info("Going to write the file contents");
            byte[] imageBytes = FileUtil.getBytes(budgetPhoto);
            DLFolder folderparent = DLFolderLocalServiceUtil.getFolder(themeDisplay.getScopeGroupId(),
                    DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
                    "budget participatif");
            DLFolder folder = DLFolderLocalServiceUtil
                    .getFolder(themeDisplay.getScopeGroupId(),
                            folderparent.getFolderId(),
                            "uploads");
            FileEntry fileEntry = DLAppLocalServiceUtil.addFileEntry(
                    sc.getUserId(), folder.getRepositoryId(),
                    folder.getFolderId(), budgetPhoto.getName(),
                    MimeTypesUtil.getContentType(budgetPhoto),
                    budgetPhoto.getName(), title,
                    "", imageBytes, sc);
            budgetParticipatif.setImageId(fileEntry.getFileEntryId());

        }
        return budgetParticipatif;
    }

    private boolean validate() {
        boolean isValid = true;
        // title
        if (Validator.isNull(this.title)) {
            isValid = false;
        }

        // description
        if (Validator.isNull(this.description)) {
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

}
