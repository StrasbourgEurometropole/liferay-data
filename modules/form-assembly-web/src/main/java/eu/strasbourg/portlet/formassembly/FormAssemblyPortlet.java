package eu.strasbourg.portlet.formassembly;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.portlet.formassembly.configuration.FormAssemblyConfiguration;

@Component(
	immediate = true,
	configurationPid = "eu.strasbourg.portlet.formassembly.configuration.FormAssemblyConfiguration",
	property = { "com.liferay.portlet.display-category=Strasbourg",
		"com.liferay.portlet.instanceable=false",
		"com.liferay.portlet.requires-namespaced-parameters=false",
		"com.liferay.portlet.single-page-application=false",
		"com.liferay.portlet.css-class-wrapper=formassembly-portlet form-assembly-portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/formassembly-view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" },
	service = Portlet.class)
public class FormAssemblyPortlet extends MVCPortlet {
	@Override
	public void render(RenderRequest request, RenderResponse response)
		throws PortletException, IOException {
		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) request
				.getAttribute(WebKeys.THEME_DISPLAY);
			FormAssemblyConfiguration configuration = themeDisplay
				.getPortletDisplay().getPortletInstanceConfiguration(
					FormAssemblyConfiguration.class);

			// Formulaire à afficher

			// On récupère l'ID du formulaire selon la locale
			String formIdStr = LocalizationUtil.getLocalization(
				configuration.formId(), themeDisplay.getLanguageId(), true);
			int formId = GetterUtil.getInteger(formIdStr);

			// Erreur si pas de formulaire sélectionné
			if (formId <= 0) {
				SessionErrors.add(request, "no-form");
			} else {
				// On récupère l'html du formulaire (ou les résultats si les
				// données viennent d'être saisies)
				Map<String, List<String>> params = splitQuery(themeDisplay.getURLCurrent());
				List<String> tfaNexts = params != null ? params.get("tfa_next") : null;
				String tfaNext = tfaNexts != null && tfaNexts.size() > 0 ? tfaNexts.get(0) : "";
				FormAssemblyUtil formAssemblyUtil = new FormAssemblyUtil(
					configuration.path(), configuration.token());
				String formHtml = formAssemblyUtil.getHTML(formId,
					tfaNext);
				request.setAttribute("formHtml", formHtml);
			}

			super.render(request, response);

		} catch (Exception ex) {
			_log.error(ex);
		}
	}

	private static Map<String, List<String>> splitQuery(String url)
		throws UnsupportedEncodingException {
		final Map<String, List<String>> query_pairs = new LinkedHashMap<String, List<String>>();
		if (!url.contains("?")) {
			return null;
		}
		final String[] pairs = url.split("\\?")[1].split("&");
		for (String pair : pairs) {
			final int idx = pair.indexOf("=");
			final String key = idx > 0
				? URLDecoder.decode(pair.substring(0, idx), "UTF-8") : pair;
			if (!query_pairs.containsKey(key)) {
				query_pairs.put(key, new LinkedList<String>());
			}
			final String value = idx > 0 && pair.length() > idx + 1
				? URLDecoder.decode(pair.substring(idx + 1), "UTF-8") : null;
			query_pairs.get(key).add(value);
		}
		return query_pairs;
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}