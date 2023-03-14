package eu.strasbourg.portlet.council.utils;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import eu.strasbourg.service.council.model.CouncilSession;
import eu.strasbourg.service.council.model.Type;
import eu.strasbourg.service.council.service.TypeLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.VocabularyNames;
import org.apache.commons.lang3.StringUtils;

public class VocabularyHelper {

    /**
     * Récupère la catégorie du conseil
     * @param themeDisplay
     * @param council : Conseil de type CouncilSession
     */
    public static String getCategorieCouncilId(ThemeDisplay themeDisplay, CouncilSession council) {

        String categoryCouncilId;
        AssetVocabulary conseil = AssetVocabularyHelper.getVocabulary(VocabularyNames.COUNCIL_SESSION, themeDisplay.getScopeGroupId());
        Type type = TypeLocalServiceUtil.fetchType(council.getTypeId());
        AssetCategory typeCategory = conseil.getCategories().stream().filter(c -> c.getName().equals(type.getTitle())).findFirst().get();
        AssetCategory councilCategory = AssetCategoryLocalServiceUtil.getChildCategories(typeCategory.getCategoryId()).stream().filter(c -> c.getName().equals(council.getTitle())).findFirst().get();

        categoryCouncilId = String.valueOf(councilCategory != null ? councilCategory.getCategoryId() : StringUtils.EMPTY);

        return categoryCouncilId;
    }
}
