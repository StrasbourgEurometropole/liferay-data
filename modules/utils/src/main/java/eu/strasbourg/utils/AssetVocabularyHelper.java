package eu.strasbourg.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;

/**
 * Classe Helper pour tout ce qui concerne les vocabulaires
 *
 */
public class AssetVocabularyHelper {
	public static List<AssetVocabulary> getVocabulariesForAssetType(
		long groupId, long classNameId) {
		List<AssetVocabulary> vocabularies = AssetVocabularyLocalServiceUtil
			.getAssetVocabularies(-1, -1);
		List<AssetVocabulary> attachedVocabularies = new ArrayList<AssetVocabulary>();
		if (classNameId > 0) {
			for (AssetVocabulary vocabulary : vocabularies) {
				if (vocabulary.getGroupId() == groupId
					&& LongStream.of(vocabulary.getSelectedClassNameIds())
						.anyMatch(c -> c == classNameId)) {
					attachedVocabularies.add(vocabulary);
				}
			}
		}
		return attachedVocabularies;
	}
}
