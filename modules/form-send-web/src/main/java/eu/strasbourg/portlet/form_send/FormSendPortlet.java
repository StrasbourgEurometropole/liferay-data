package eu.strasbourg.portlet.form_send;

import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.*;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.portlet.form_send.configuration.FormSendConfiguration;
import eu.strasbourg.portlet.form_send.context.FormSendDisplayContext;
import eu.strasbourg.service.formSendRecordField.model.FormSendRecordFieldSignalement;
import eu.strasbourg.service.formSendRecordField.service.FormSendRecordFieldSignalementLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author angelique.champougny
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=Strasbourg",
		"com.liferay.portlet.instanceable=true", "com.liferay.portlet.required-namespaced-parameters=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/general.jsp",
		"javax.portlet.init-param.config-template=/configuration/form-send-configuration.jsp",
		"javax.portlet.name=" + StrasbourgPortletKeys.FORM_SEND_WEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class FormSendPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest request, RenderResponse response) throws IOException, PortletException {

		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

			FormSendConfiguration configuration = themeDisplay.getPortletDisplay()
					.getPortletInstanceConfiguration(FormSendConfiguration.class);


			String template = configuration.template();
			if (Validator.isNull(template)) {
				template = "general";
			}

			FormSendDisplayContext dc = new FormSendDisplayContext(request, response);
			request.setAttribute("dc", dc);

			// URL de redirection pour le POST evitant les soumissions multiples
			String redirectURL =  themeDisplay.getURLPortal() + themeDisplay.getURLCurrent();

			request.setAttribute("redirectURL", redirectURL);

			include("/templates/" + template + ".jsp", request, response);

		} catch (Exception e) {
			_log.error(e);
		}

	}

	/**
	 * Méthode permettant de signaler une entitié.
	 * @param request request
	 * @param response response
	 * @throws PortalException PortalException
	 */
	public void signalement(ActionRequest request, ActionResponse response) throws PortalException, IOException {

		// Recuperation de l'URL de redirection
		String redirectURL = ParamUtil.getString(request, "redirectURL");
		// ... du contexte de la requete
		ServiceContext sc = ServiceContextFactory.getInstance(request);

		// ... des informations du signalement
		LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(request);
		HttpServletRequest originalRequest = liferayPortletRequest.getHttpServletRequest();
		String publikUserId = SessionParamUtil.getString(originalRequest, "publik_internal_id");
		long entityId = ParamUtil.getLong(request, "entityId");
		long categoryId = ParamUtil.getLong(request, "categorie");

		// Initialisation du signalement
		FormSendRecordFieldSignalement signalement = FormSendRecordFieldSignalementLocalServiceUtil.createFormSendRecordFieldSignalement(sc);

		// Ajout des informations du signalement
		signalement.setPublikId(publikUserId);
		signalement.setFormSendRecordFieldId(entityId);
		signalement.setStatus(WorkflowConstants.STATUS_DENIED);

		AssetCategoryLocalServiceUtil.addAssetEntryAssetCategory(signalement.getSignalementId(), categoryId);
		FormSendRecordFieldSignalementLocalServiceUtil.updateFormSendRecordFieldSignalement(signalement,sc);

		response.sendRedirect(redirectURL  + "#rep_" + entityId);
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}