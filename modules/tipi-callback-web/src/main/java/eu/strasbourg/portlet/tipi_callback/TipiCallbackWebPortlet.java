package eu.strasbourg.portlet.tipi_callback;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.service.tipi.service.TipiEntryLocalServiceUtil;

/**
 * @author 01i454
 */
@Component(immediate = true, property = {
		"com.liferay.portlet.display-category=Strasbourg",
		"javax.portlet.display-name=Tipi Callback Portlet2",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class TipiCallbackWebPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest renderRequest,
			RenderResponse renderResponse)
			throws IOException, PortletException {		

		// On récupère les paramètres de l'url
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest
				.getAttribute(WebKeys.THEME_DISPLAY);
		Map<String, List<String>> params = splitQuery(themeDisplay.getURLCurrent());
		List<String> objets = params != null ? params.get("objet") : null;
		String objet = objets != null && objets.size() > 0 ? objets.get(0) : "";
		List<String> montants = params != null ? params.get("montant") : null;
		String montant = montants != null && montants.size() > 0 ? montants.get(0) : "";
		List<String> resultranss = params != null ? params.get("resultrans") : null;
		String resultrans = resultranss != null && resultranss.size() > 0 ? resultranss.get(0) : "";
		List<String> dattranss = params != null ? params.get("dattrans") : null;
		String dattrans = dattranss != null && dattranss.size() > 0 ? dattranss.get(0) : "";

		if (Validator.isNotNull(objet) && Validator.isNotNull(montant)
				&& Validator.isNotNull(resultrans)
				&& Validator.isNotNull(dattrans)){

			SimpleDateFormat sf = new SimpleDateFormat("ddMMyyyy");
			Date transactionDateTime = null;
			try {
				transactionDateTime = sf.parse(dattrans);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			TipiEntryLocalServiceUtil.addPayment(transactionDateTime, objet,
					resultrans, montant);
		}
		super.render(renderRequest, renderResponse);
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
}