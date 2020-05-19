package eu.strasbourg.utils;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.journal.service.JournalContentSearchLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;

/**
 * Classe helper pour tout ce qui concerne les layouts
 * 
 * @author Benjamin Bini
 *
 */
public class LayoutHelper {
	/**
	 * Retourne l'ensemble du path d'un layout
	 */
	public static String getLayoutPath(Layout layout, Locale locale) {
		try {
			String path = layout.getName(locale);
			for (Layout ancestor : layout.getAncestors()) {
				path = ancestor.getName(locale) + " > " + path;
			}

			path = layout.isPublicLayout() ? "Pages publiques > " + path
				: "Page priv&eacute;es " + path;

			return path;
		} catch (PortalException e) {
			return "";
		}
	}

	/**
	 * Retourne true si la page a des enfants visibles. False sinon.
	 */
	public static boolean hasVisibleChildren(Layout layout) {
		boolean hasVisibleChildren = false;

		for (Layout subLayout : layout.getChildren()) {
			if (!subLayout.isHidden()) {
				hasVisibleChildren = true;
				break;
			}
		}

		return hasVisibleChildren;
	}

	/**
	 * Retourne l'URL d'une page où un contenu web donné est affiché. Par défaut
	 * il s'agit de la page d'affichage. Sinon d'une page où un afficheur de
	 * contenu web est configuré pour afficher le contenu.
	 */
	public static String getJournalArticleLayoutURL(long groupId,
		String articleId, ThemeDisplay themeDisplay) {
		// On tente d'abord de récupérer la page d'affichage du contenu web
		// depuis le champ prévu à cet effet sur le contenu web
		JournalArticle article = JournalArticleLocalServiceUtil
			.fetchArticle(groupId, articleId);
		if (article != null && article.getLayout() != null) {
			return "/web" + themeDisplay.getScopeGroup().getFriendlyURL()
				+ "/-/" + article.getUrlTitle();
		}
		// S'il n'y en a pas, on essaye de trouver une page où le contenu est
		// affiché
		else {
			List<Long> layoutIds = JournalContentSearchLocalServiceUtil
				.getLayoutIds(groupId, false, articleId);
			if (layoutIds.size() > 0) {
				Layout articleLayout = LayoutLocalServiceUtil
					.fetchLayout(groupId, false, layoutIds.get(0));
				try {
					return PortalUtil.getLayoutFriendlyURL(articleLayout,
						themeDisplay, themeDisplay.getLocale());
				} catch (PortalException e) {
					_log.error(e);
				}
			}
		}
		return "";
	}

	public static String getPublikLoginURL(String currentURL) throws MalformedURLException, UnsupportedEncodingException {
		URL url = new URL(currentURL);
		Map<String, List<String>> params = getQueryParams(url);
		String loginURL = url.toString().split("\\?")[0].split("\\#")[0];
		loginURL += "?";
		for (Map.Entry<String, List<String>> param : params.entrySet()) {
			if (!param.getKey().equals("logout")) {
				for (String paramValue : param.getValue()) {
					loginURL += param.getKey() + "=" + paramValue + "&";
				}
			}
		}
		loginURL += "auth=publik";
		return loginURL;
	}

	public static Map<String, List<String>> getQueryParams(URL url)
			throws UnsupportedEncodingException, MalformedURLException {
		final Map<String, List<String>> query_pairs = new LinkedHashMap<String, List<String>>();
		final String[] pairs = url.getQuery() != null ? url.getQuery().split("&") : new String[0];
		for (String pair : pairs) {
			final int idx = pair.indexOf("=");
			final String key = idx > 0 ? URLDecoder.decode(pair.substring(0, idx), "UTF-8") : pair;
			if (!query_pairs.containsKey(key)) {
				query_pairs.put(key, new LinkedList<String>());
			}
			final String value = idx > 0 && pair.length() > idx + 1
					? URLDecoder.decode(pair.substring(idx + 1), "UTF-8") : null;
			query_pairs.get(key).add(value);
		}
		return query_pairs;
	}
	
	private static final Log _log = LogFactoryUtil.getLog(LayoutHelper.class.getName());
}
