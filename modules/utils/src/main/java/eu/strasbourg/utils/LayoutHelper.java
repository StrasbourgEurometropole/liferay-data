package eu.strasbourg.utils;

import java.util.List;
import java.util.Locale;

import com.liferay.journal.service.JournalContentSearchLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
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
	
	public static String getJournalArticleLayoutURL(long groupId, String articleId, ThemeDisplay themeDisplay) {
		List<Long> layoutIds = JournalContentSearchLocalServiceUtil.getLayoutIds(groupId, false, articleId);
		if (layoutIds.size() > 0) {
			try {
				Layout layout = LayoutLocalServiceUtil.getLayout(groupId, false, layoutIds.get(0));
				return PortalUtil.getLayoutFriendlyURL(layout, themeDisplay, themeDisplay.getLocale());
			} catch (PortalException e) {
				e.printStackTrace();
			}
		}
		return "";
	}
}
