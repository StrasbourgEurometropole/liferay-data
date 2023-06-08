package eu.strasbourg.utils.api;

import aQute.bnd.annotation.ProviderType;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;

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
