package eu.strasbourg.utils;

import java.util.Locale;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Layout;

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
}
