package eu.strasbourg.utils;

import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.portal.kernel.exception.PortalException;

import eu.strasbourg.utils.constants.VocabularyNames;

public class AssetVocabularyAccessor {

	public AssetVocabulary getEventThemes() {
		try {
			return AssetVocabularyHelper.getGlobalVocabulary(VocabularyNames.EVENT_THEME);
		} catch (PortalException e) {
			return null;
		}
	}

	public AssetVocabulary getEventTypes() {
		try {
			return AssetVocabularyHelper.getGlobalVocabulary(VocabularyNames.EVENT_TYPE);
		} catch (PortalException e) {
			return null;
		}
	}

	public AssetVocabulary getEventPublics() {
		try {
			return AssetVocabularyHelper.getGlobalVocabulary(VocabularyNames.EVENT_PUBLIC);
		} catch (PortalException e) {
			return null;
		}
	}

	public AssetVocabulary getTerritories() {
		try {
			return AssetVocabularyHelper.getGlobalVocabulary(VocabularyNames.TERRITORY);
		} catch (PortalException e) {
			return null;
		}
	}

}