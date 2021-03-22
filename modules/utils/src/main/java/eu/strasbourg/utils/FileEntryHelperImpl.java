package eu.strasbourg.utils;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import eu.strasbourg.utils.api.FileEntryHelperService;
import org.osgi.service.component.annotations.Component;

import java.io.File;
import java.util.Locale;
import java.util.Map;

/**
 * Implémentation du service FileEntryHelperService N'est qu'une couche
 * accessible par les templates FreeMarker qui délègue le travail à une classe
 * helper
 */
@Component(
	immediate = true,
	property = {},
	service = FileEntryHelperService.class)
public class FileEntryHelperImpl implements FileEntryHelperService {

	@Override
	public String getFileTitle(long fileEntryId, Locale locale) {
		return FileEntryHelper.getFileTitle(fileEntryId, locale);
	}
	
	@Override
	public String getReadableFileEntrySize(long fileEntryId, Locale locale) {
		return FileEntryHelper.getReadableFileEntrySize(fileEntryId, locale);
	}

	@Override
	public String getFileEntryURL(long fileEntryId) {
		return FileEntryHelper.getFileEntryURL(fileEntryId);
	}

	@Override
	public String getImageCopyright(long fileEntryId, Locale locale) {
		return FileEntryHelper.getImageCopyright(fileEntryId, locale);
	}

	@Override
	public String getImageLegend(long fileEntryId, Locale locale) {
		return FileEntryHelper.getImageLegend(fileEntryId, locale);
	}

	@Override
	public String getFileThumbnail(long fileEntryId,
		ThemeDisplay themeDisplay) {
		return FileEntryHelper.getFileThumbnail(fileEntryId, themeDisplay);
	}

	@Override
	public DLFileEntry getFileEntryByRelativeURL(String url) {
		return FileEntryHelper.getFileEntryByRelativeURL(url);
	}

	@Override
	public String getStructureFieldValue(Long fileEntryId, String fieldName,
		Locale locale) {
		return FileEntryHelper.getStructureFieldValue(fileEntryId, fieldName, locale);
	}

	@Override
	public String scanFile(File file) {
		return FileEntryHelper.scanFile(file);
	}

	@Override
	public Map<String, DLFileEntry> getPictoForVocabulary(String nomRepertoireVocabulaire, String nomRepertoire)
			throws PortalException {
		return FileEntryHelper.getPictoForVocabulary(nomRepertoireVocabulaire, nomRepertoire);
	}

}
