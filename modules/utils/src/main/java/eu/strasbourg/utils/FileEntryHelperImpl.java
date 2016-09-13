package eu.strasbourg.utils;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

import eu.strasbourg.utils.api.FileEntryHelperService;

/**
 * Impl�mentation du service FileEntryHelperService
 * N'est qu'une couche accessible par les templates FreeMarker qui d�l�gue le travail
 * � une classe helper
 */
@Component(
    immediate = true,
    property = {
    },
    service = FileEntryHelperService.class
)
public class FileEntryHelperImpl implements FileEntryHelperService {

	@Override
	public String getReadableFileEntrySize(long fileEntryId,
		Locale locale) {
		return FileEntryHelper.getReadableFileEntrySize(fileEntryId, locale);
	}

	@Override
	public String getFileEntryURL(long fileEntryId) {
		return FileEntryHelper.getFileEntryURL(fileEntryId);
	}

}
