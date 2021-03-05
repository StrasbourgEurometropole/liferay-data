package eu.strasbourg.portlet.helppopup;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.portlet.helppopup.configuration.HelpPopupConfiguration;
import eu.strasbourg.service.help.model.HelpProposal;
import eu.strasbourg.service.help.service.HelpProposalLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyAccessor;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.PublikApiClient;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.constants.VocabularyNames;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.io.IOException;
import java.util.List;

import static eu.strasbourg.portlet.helppopup.utils.HelpPopupUtils.getPublikID;

/**
 * @author angelique.champougny
 */
@Component(
		immediate = true,
		property = {
				"javax.portlet.version=3.0",
				"com.liferay.portlet.display-category=Strasbourg",
				"com.liferay.portlet.instanceable=true",
				"com.liferay.portlet.css-class-wrapper=help-popup-portlet",
				"javax.portlet.display-name=Popups Aide",
				"javax.portlet.init-param.add-process-action-success-action=false",
				"javax.portlet.init-param.template-path=/",
				"javax.portlet.init-param.view-template=/help-popup-view.jsp",
				"javax.portlet.name=" + StrasbourgPortletKeys.HELP_POPUP_WEB,
				"javax.portlet.init-param.config-template=/help-popup-configuration.jsp",
				"javax.portlet.resource-bundle=content.Language",
				"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
)
public class HelpPopupPortlet extends MVCPortlet {

	/**
	 * le log
	 */
	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
	private static final String SHARED_ASSET_ID = "LIFERAY_SHARED_assetEntryID";
	public static final String REDIRECT_URL_PARAM = "redirectURL";

	@Override
	public void render(RenderRequest request, RenderResponse response) throws IOException, PortletException {
		AssetVocabularyAccessor assetVocabularyAccessor = new AssetVocabularyAccessor();
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		String publikID = getPublikID(request);
		try {
			// Récupération de la configuration du portlet
			HelpPopupConfiguration configuration = themeDisplay.getPortletDisplay()
					.getPortletInstanceConfiguration(HelpPopupConfiguration.class);

			// Récupération du paramètre de tri des commentaires
			String popupTemplateId = configuration.popupTemplateId();

			// Récupération de l'asset entry Id qui est partagé par le portlet détail
			// entité sur la même page.
			// les popups n'ont pas forcement besion de l'entryId (déposer une petition par exemple)
			// //donc il faut etre en mesure de pouvoir gerer ca.
			long entryID = this.getPortletAssetEntryId(request);

			JSONObject user = null;
			if (publikID != null && !publikID.isEmpty()) {
				user = PublikApiClient.getUserDetails(publikID);

				//For all keys, if null replace with ""
				for (String key : user.keySet()) {
					String value = user.getString(key);
					if (value.equals("")) {
						user.put(key, "");
					}
				}
			}

			long groupId = themeDisplay.getLayout().getGroupId();

			// Récupération des localisations
			AssetCategory france = AssetVocabularyHelper.getCategory("France", themeDisplay.getCompanyGroupId());
			List<AssetCategory> localisations = AssetVocabularyHelper.getChild(france.getCategoryId());

			// Récupération des aidants
			AssetVocabulary helpersVocabulary = AssetVocabularyHelper.getVocabulary(VocabularyNames.HELP_HELPER_TYPE, groupId);
			List<AssetCategory> helpers = helpersVocabulary.getCategories();

			// Récupération des types d'aide
			List<AssetCategory> types = assetVocabularyAccessor.getHelpProposalType(groupId).getCategories();

			request.setAttribute("localisations", localisations);
			request.setAttribute("helpers", helpers);
			request.setAttribute("types", types);

			if (entryID != -1) {
				request.setAttribute("entryId", entryID);
				if (publikID != null && !publikID.isEmpty()) {
					List<HelpProposal> proposals = HelpProposalLocalServiceUtil.getByPublikID(publikID);
					for (HelpProposal proposal : proposals) {
						if (proposal.getAssetEntry().getEntryId() == entryID) {
							JSONObject proposalJSON = JSONFactoryUtil.getJSONFactory().createJSONObject();
							proposalJSON.put("address", proposal.getAddress());
							proposalJSON.put("city", proposal.getCity());
							proposalJSON.put("zipcode", proposal.getPostalCode());
							proposalJSON.put("phoneNumber", proposal.getPhoneNumber());
							request.setAttribute("helpProposalData", proposalJSON);
							break;
						}
					}
				}
			}


			request.setAttribute("userConnected", user);

			// URL de redirection pour le POST evitant les soumissions multiples
			String redirectURL = themeDisplay.getURLPortal() + themeDisplay.getURLCurrent();

			request.setAttribute(REDIRECT_URL_PARAM, redirectURL);
			request.setAttribute("popupTemplateId", popupTemplateId);

			// Vérifie si l'utilisateur est connecté liferay
			boolean isSignedIn = themeDisplay.isSignedIn();
			request.setAttribute("isSignedIn", isSignedIn);

			 // Retourne l'URL de la page d'accueil
			String homeURL = "/";
			if (themeDisplay.getScopeGroup().getPublicLayoutSet().getVirtualHostname() != null
					|| themeDisplay.getScopeGroup().isStagingGroup()) {
				homeURL =  "/web" + themeDisplay.getScopeGroup().getFriendlyURL() + "/";
			}
			request.setAttribute("homeURL", homeURL);


		} catch (Exception e) {
			_log.error("erreur : ", e);
		}
		super.render(request, response);
	}

	/**
	 * Recupere l'ID de l'assetEntry du detail de la page
	 *
	 * @throws PortalException
	 */
	private long getPortletAssetEntryId(PortletRequest request) throws PortalException {
		PortletSession portletSession = request.getPortletSession();
		if (portletSession.getAttribute(SHARED_ASSET_ID, PortletSession.APPLICATION_SCOPE) != null) {
			return (long) portletSession.getAttribute(SHARED_ASSET_ID,
					PortletSession.APPLICATION_SCOPE);
		}

		return -1;
	}
}