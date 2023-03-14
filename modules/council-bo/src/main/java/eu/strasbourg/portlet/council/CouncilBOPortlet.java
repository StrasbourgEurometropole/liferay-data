package eu.strasbourg.portlet.council;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.portlet.council.constants.CouncilConstants;
import eu.strasbourg.portlet.council.display.context.*;
import eu.strasbourg.portlet.council.utils.UserRoleType;
import eu.strasbourg.portlet.council.utils.VocabularyHelper;
import eu.strasbourg.service.council.model.CouncilSession;
import eu.strasbourg.service.council.service.CouncilSessionLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import org.apache.commons.lang3.StringUtils;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jeremy.zwickert
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.instanceable=false",
		"com.liferay.portlet.footer-portlet-javascript=/js/council-bo-main.js",
		"com.liferay.portlet.header-portlet-css=/css/council-bo-main.css",
		"com.liferay.portlet.layout-cacheable=true",
		"com.liferay.portlet.single-page-application=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/council-bo-view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
	},
	service = Portlet.class
)
public class CouncilBOPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		String cmd = ParamUtil.getString(renderRequest, "cmd");
		String tab = ParamUtil.getString(renderRequest, "tab");
		String mvcPath = ParamUtil.getString(renderRequest, "mvcPath");

		renderResponse.setTitle("CouncilSessions");

		// If we are on an "add" page, we set a return URL and show the "back"
		// button
		String returnURL = ParamUtil.getString(renderRequest, "returnURL");
		boolean showBackButton = Validator.isNotNull(returnURL);
		if (showBackButton) {
			portletDisplay.setShowBackIcon(true);
			portletDisplay.setURLBack(returnURL);
		}

		// If we are on the Session, we add the corresponding
		// display context
		if (cmd.equals("editCouncilSession") || mvcPath.equals("/council-bo-edit-council-session.jsp")) {
			EditCouncilSessionDisplayContext dc = new EditCouncilSessionDisplayContext(renderRequest);
			renderRequest.setAttribute("dc", dc);
			long councilSessionId = ParamUtil.getLong(renderRequest, "councilSessionId");
			HttpServletRequest originalRequest = PortalUtil.getHttpServletRequest(renderRequest);
			HttpSession session = originalRequest.getSession();
			session.setAttribute("councilSessionId", councilSessionId);
		} else if (cmd.equals("editDeliberation") || mvcPath.equals("/council-bo-edit-deliberation.jsp")) {
			EditDeliberationDisplayContext dc = new EditDeliberationDisplayContext(renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else if (cmd.equals("editOfficial") || mvcPath.equals("/council-bo-edit-official.jsp")) {
			EditOfficialDisplayContext dc = new EditOfficialDisplayContext(renderRequest);
			renderRequest.setAttribute("dc", dc);
		} else if (cmd.equals("editType") || mvcPath.equals("/council-bo-edit-type.jsp")) {
			EditTypeDisplayContext dc = new EditTypeDisplayContext(renderRequest);
			renderRequest.setAttribute("dc", dc);
		} else if (cmd.equals("importDeliberation") || mvcPath.equals("/council-bo-import-deliberation.jsp")) {
			EditDeliberationDisplayContext dc = new EditDeliberationDisplayContext(renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else if (cmd.equals("manageProcurations") || mvcPath.equals("/council-bo-manage-procurations.jsp")) {
			ManageProcurationsDisplayContext dc = new ManageProcurationsDisplayContext(renderRequest);
			renderRequest.setAttribute("dc", dc);
		} else if (cmd.equals("deliberations") || mvcPath.equals("/council-bo-view-deliberations.jsp")) {

			HttpServletRequest originalRequest = PortalUtil.getHttpServletRequest(renderRequest);
			HttpSession session = originalRequest.getSession();
			String categoryCouncilId = null;
					
			long councilSessionId = (long) session.getAttribute("councilSessionId");
			CouncilSession councilSession = CouncilSessionLocalServiceUtil.fetchCouncilSession(councilSessionId);
			if (Validator.isNotNull(councilSession)) {
				categoryCouncilId = VocabularyHelper.getCategorieCouncilId(themeDisplay, councilSession);
				session.setAttribute("categoryCouncilId", categoryCouncilId);
			}

			ViewDeliberationsDisplayContext dc = new ViewDeliberationsDisplayContext(renderRequest, renderResponse, categoryCouncilId);
			renderRequest.setAttribute("dc", dc);
		} else if (tab.equals("deliberations")) {
			String sessionCategoryId = getCategoryIdSession(renderRequest, themeDisplay);
			ViewDeliberationsDisplayContext dc = new ViewDeliberationsDisplayContext(renderRequest, renderResponse,sessionCategoryId );
			renderRequest.setAttribute("dc", dc);
		} else if (tab.equals("officials")) {
			ViewOfficialsDisplayContext dc = new ViewOfficialsDisplayContext(renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else if (tab.equals("officialsConnection")) {
			// Gestion de la selection du conseil
			ViewOfficialsConnectionDisplayContext dc = new ViewOfficialsConnectionDisplayContext(renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else if (tab.equals("types")) {
			ViewTypesDisplayContext dc = new ViewTypesDisplayContext(renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else { // Else, we are on the event list page
			ViewCouncilSessionsDisplayContext dc = new ViewCouncilSessionsDisplayContext(renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		}

		// Admin ou pas
		renderRequest.setAttribute("isAdmin", themeDisplay.getPermissionChecker().isOmniadmin());

		// Admin EVOTE ou pas
		renderRequest.setAttribute("isAdminEvote", UserRoleType.IsAdminEvote(themeDisplay));

		super.render(renderRequest, renderResponse);
	}

	/**
	 * Récupère la catégorie du CouncilSession de la session, ou de celui du jour ou du dernier
	 */
	private String getCategoryIdSession(RenderRequest renderRequest, ThemeDisplay themeDisplay) {

		HttpServletRequest originalRequest = PortalUtil.getHttpServletRequest(renderRequest);
		HttpSession session = originalRequest.getSession();

		String categoryCouncilId = null;
		String categoryDelibStage= null;

		String categoryToAdd = ParamUtil.getString(renderRequest, "categoryToAdd");
		if (Validator.isNotNull(categoryToAdd)) {
			AssetCategory ac = AssetCategoryLocalServiceUtil.fetchAssetCategory(Long.parseLong(categoryToAdd));
			if (ac != null) {
				AssetVocabulary vocab = AssetVocabularyLocalServiceUtil.fetchAssetVocabulary(ac.getVocabularyId());
				if (vocab != null & vocab.getName().equals("Conseil")) {
					categoryCouncilId = categoryToAdd;
				} else if (vocab != null & vocab.getName().equals("Statut")) {
					categoryDelibStage = categoryToAdd;
				}
			}
		}

		// Récupère la catégorie de conseil en session
		Object sessionObject = session.getAttribute("categoryCouncilId");
		String sessionCategoryCouncilId = null;
		if (Validator.isNotNull(sessionObject)) {
			sessionCategoryCouncilId = sessionObject.toString();
		}

		String categoryId = null;
		// Si aucun conseil sélectionné, on prend celui de la session
		if (Validator.isNull(categoryCouncilId)) {

			if (Validator.isNull(sessionCategoryCouncilId)) {

				// Calcul de la date
				GregorianCalendar gc = CouncilSessionLocalServiceUtil.calculDateForFindCouncil();

				List<CouncilSession> todayCouncils = CouncilSessionLocalServiceUtil.findByDate(gc.getTime());

				// Si on a rien en session, on cherche le conseil du jour ou le dernier conseil
				if (todayCouncils.size() == 1) {
					CouncilSession todayCouncil = todayCouncils.get(0);
					categoryCouncilId = VocabularyHelper.getCategorieCouncilId(themeDisplay, todayCouncil);
					session.setAttribute("categoryCouncilId", categoryCouncilId);

				} else if (todayCouncils.size() > 1) {

					long groupId = themeDisplay.getScopeGroupId();
					AssetCategory category = AssetVocabularyHelper.getCategory(CouncilConstants.NO_COUNCIL_CATEGORY_NAME, groupId);
					if (category != null) {
						categoryCouncilId = String.valueOf(category.getCategoryId());
						session.setAttribute("categoryCouncilId", categoryCouncilId);
					}
				} else {
					List<CouncilSession> futureCouncilSessions = CouncilSessionLocalServiceUtil.getCouncilSessions(-1,-1);

					futureCouncilSessions = futureCouncilSessions.stream()
							.sorted(Comparator.comparing(CouncilSession::getDate).reversed())
							.collect(Collectors.toList());

					if (futureCouncilSessions.size() > 0) {
						CouncilSession lastCouncil = futureCouncilSessions.get(0);
						categoryCouncilId = VocabularyHelper.getCategorieCouncilId(themeDisplay, lastCouncil);
						session.setAttribute("categoryCouncilId", categoryCouncilId);
					}
				}
			} else {
				categoryCouncilId=sessionCategoryCouncilId;
			}
		}
		// Si on a sélectionné une catégorie différente à celle de la session, on prend la nouvelle et on l'enregistre en session
		else if (!categoryCouncilId.equals(sessionCategoryCouncilId)) {
			session.setAttribute("categoryCouncilId", categoryCouncilId);
		}

		final String finalCategoryCouncilId = categoryCouncilId;
		// On récupère les catégories de type conseil autorisé par le rôle du USER
		List<AssetCategory> typeCoucilCats = UserRoleType.typeCategoriesForUser(themeDisplay);
		// On récupère totes les sous catégories de conseil liées
		List<AssetCategory> councilCats = new ArrayList<>();

		councilCats.addAll(typeCoucilCats);
		for (AssetCategory cat : typeCoucilCats) {
			councilCats.addAll(AssetVocabularyHelper.getChild(cat.getCategoryId()));
		}
		// Si la catégorie qui était sélectionnée/choisie n'est pas dans les catégories autorisée, on donne celle du premier type de conseil autorisé
		if (!councilCats.stream().anyMatch(x -> String.valueOf(x.getCategoryId()).equals(finalCategoryCouncilId))) {
			categoryCouncilId = StringUtils.EMPTY;
			if (typeCoucilCats.size() > 0) {
				categoryCouncilId=String.valueOf(typeCoucilCats.get(0));
			}
		}
		categoryId = categoryCouncilId +','+categoryDelibStage;

		return categoryId;
	}
}