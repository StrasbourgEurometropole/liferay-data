package eu.strasbourg.utils.api;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Locale;

import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import aQute.bnd.annotation.ProviderType;

/**
 * Classe de service qui peut être utilisée dans les templates L'implémentation
 * appelle des fonctions statiques présentes dans les classes Helper classiques
 * 
 * @author Benjamin Bini
 *
 */
@ProviderType
public interface LayoutHelperService {
	public String getLayoutPath(Layout layout, Locale locale);
	
	public String getJournalArticleLayoutURL(long groupId, String articleId, ThemeDisplay themeDisplay);

	public String getPublikLoginURL(String currentCompleteURL) throws MalformedURLException, UnsupportedEncodingException;

	public String getPublikLogoutURL(String currentCompleteURL) throws MalformedURLException, UnsupportedEncodingException;
}
