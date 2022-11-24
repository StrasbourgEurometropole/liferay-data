package eu.strasbourg.utils.api;

import aQute.bnd.annotation.ProviderType;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalServiceUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.theme.NavItem;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Locale;

/**
 * Classe de service qui peut être utilisée dans les templates L'implémentation
 * appelle des fonctions statiques présentes dans les classes Helper classiques
 * 
 * @author Angélique ZUNINO CHAMPOUGNY
 *
 */
@ProviderType
public interface TemplateHelperService {
	public DDMTemplate getDDMTemplateByGroupeIdAndName(long groupId, String name);
}
