package eu.strasbourg.utils;

import java.util.List;
import java.util.Locale;

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
	
	private static final Log _log = LogFactoryUtil.getLog(LayoutHelper.class.getName());
}
