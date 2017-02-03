package eu.strasbourg.portlet.entity_detail.configuration;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PredicateFilter;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	configurationPid = "eu.strasbourg.portlet.entity_detail.configuration.EntityDetailConfiguration",
	configurationPolicy = ConfigurationPolicy.OPTIONAL,
	immediate = true,
	property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.ENTITY_DETAIL_WEB },
	service = ConfigurationAction.class)
public class EntityDetailConfigurationAction
	extends DefaultConfigurationAction {

	/**
	 * Action : Sauvegarde de la configuration si on a validé le formulaire ou
	 * envoi de la JSP des sélecteurs si on a changé la liste déroulante des
	 * types d'entité
	 */
	@Override
	public void processAction(PortletConfig portletConfig,
		ActionRequest request, ActionResponse response) throws Exception {

		String cmd = ParamUtil.getString(request, "cmd");

		if (cmd.equals("update")) {
			// Type d'entité
			String className = ParamUtil.getString(request, "className");
			setPreference(request, "className", className);

			// Item
			String classPK = ParamUtil.getString(request, "classPK");
			setPreference(request, "classPK", classPK);
		} else {
			classNameChange(request, response);
		}

		super.processAction(portletConfig, request, response);
	}

	/**
	 * Envoie à la JSP de configuration des informations nécessaires
	 */
	@Override
	public void include(PortletConfig portletConfig, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) request
				.getAttribute(WebKeys.THEME_DISPLAY);
			String portletResource = ParamUtil.getString(request,
				"portletResource");
			PortletPreferences preferences = PortletPreferencesFactoryUtil
				.getPortletSetup(request, portletResource);

			String cmd = ParamUtil.getString(request, "cmd");
			if (cmd.equals("update") || Validator.isNull(cmd)) {

				EntityDetailConfiguration configuration = themeDisplay
					.getPortletDisplay().getPortletInstanceConfiguration(
						EntityDetailConfiguration.class);

				// Liste des types d'entités (ainsi que leurs labels)
				List<AssetRendererFactory<?>> availableAssetRendererFactories = ListUtil
					.filter(
						AssetRendererFactoryRegistryUtil
							.getAssetRendererFactories(
								themeDisplay.getCompany().getCompanyId()),
						new PredicateFilter<AssetRendererFactory<?>>() {

							@Override
							public boolean filter(
								AssetRendererFactory<?> assetRendererFactory) {
								return assetRendererFactory.isCategorizable()
									&& assetRendererFactory.getClassName()
										.startsWith("eu.strasbourg");
							}

						});

				List<String> classNames = new ArrayList<String>();
				List<String> classNamesLabels = new ArrayList<String>();
				for (AssetRendererFactory<?> assetRendererFactory : availableAssetRendererFactories) {
					classNames.add(assetRendererFactory.getClassName());
					String classNameLabel = ResourceActionsUtil
						.getModelResource(themeDisplay.getLocale(),
							assetRendererFactory.getClassName());
					classNamesLabels.add(classNameLabel);
				}
				request.setAttribute("classNames", classNames);
				request.setAttribute("classNamesLabels", classNamesLabels);

				// Type d'entité sélectionné (et son label, pour le label du
				// sélecteur d'entité)
				String className = Validator
					.isNotNull(configuration.className())
						? configuration.className() : classNames.get(0);
				String classNameLabel = Validator
					.isNotNull(configuration.className())
						? ResourceActionsUtil.getModelResource(
							themeDisplay.getLocale(), configuration.className())
						: classNamesLabels.get(0);
				request.setAttribute("className", className);
				request.setAttribute("classNameLabel", classNameLabel);

				// Item selectionné
				request.setAttribute("classPK", configuration.classPK());
			}

			// Tout ce qui est Application Display Template
			String displayStyle = GetterUtil.getString(
				preferences.getValue("displayStyle", StringPool.BLANK));
			request.setAttribute("displayStyle", displayStyle);
			long displayStyleGroupId = GetterUtil
				.getLong(preferences.getValue("displayStyleGroupId", null), 0);
			String refreshURL = PortalUtil.getCurrentURL(request);
			request.setAttribute("displayStyleGroupId", displayStyleGroupId);
			request.setAttribute("refreshURL", refreshURL);

			super.include(portletConfig, request, response);
		} catch (ConfigurationException e) {
			_log.error(e);
		}
	}

	/**
	 * Fonction appelée via AJAX lors d'un changement de la liste déroulante des
	 * types d'items Elle se charge de renvoyer la JSP contenant le selecteur
	 * d'item et la liste déroulante des Application Display Templates
	 */
	public void classNameChange(ActionRequest request,
		ActionResponse response) {
		ThemeDisplay themeDisplay = (ThemeDisplay) request
			.getAttribute(WebKeys.THEME_DISPLAY);

		// ClassName
		String className = ParamUtil.getString(request, "className");
		request.setAttribute("className", className);

		// Label correspondant au ClassName
		String classNameLabel = ResourceActionsUtil
			.getModelResource(themeDisplay.getLocale(), className);
		request.setAttribute("classNameLabel", classNameLabel);

		// JSP
		response.setRenderParameter("jspPage",
			"/entity-detail-configuration-selectors.jsp");
	}

	/**
	 * Retourne le chemin vers la JSP à afficher, si aucun paramètre jspPage n'a
	 * été setté, la JSP de configuration normale définie dans les paramètres
	 * initiaux du portlet, sinon la JSP configurée via jspPage. Ceci est
	 * utilisé pour renvoyer la JSP des sélecteurs lors du changement de la
	 * liste déroulante des types d'entité
	 */
	@Override
	public String getJspPath(HttpServletRequest request) {
		String jsp = request.getParameter("jspPage");
		if (Validator.isNull(jsp)) { // Default JSP
			return super.getJspPath(request);
		} else {
			return jsp;
		}
	}
	
	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}
