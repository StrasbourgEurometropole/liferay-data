package eu.strasbourg.utils.api;

import java.util.Locale;

import aQute.bnd.annotation.ProviderType;

/**
 * Classe de service qui peut être utilisée dans les templates OSGI
 * L'implémentation appelle des fonctions statiques présentes dans les classes Helper classiques
 * @author Benjamin Bini
 *
 */
@ProviderType
public interface FileEntryHelperService {
	public String getReadableFileEntrySize(long fileEntryId, Locale locale);
	
	public String getFileEntryURL(long fileEntryId);
}
