package eu.strasbourg.service.csmap.listener;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.transaction.TransactionCommitCallbackUtil;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.csmap.constants.CodeCacheEnum;
import eu.strasbourg.service.csmap.service.CsmapCacheLocalService;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.VocabularyNames;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Angélique ZC
 *
 */
@Component(immediate = true, service = ModelListener.class)
public class AssetCategoryListener extends BaseModelListener<AssetCategory>
{

    /**
     *  A la modification d'un event, on met à jour le cache de csmapAgenda
     */
    @Override
    public void onAfterCreate(AssetCategory category) throws ModelListenerException {
        generateCsmapCache(category);

        super.onAfterCreate(category);

    }

    /**
     *  A la modification d'un event, on met à jour le cache de csmapAgenda
     */
    @Override
    public void onAfterUpdate(AssetCategory category) throws ModelListenerException {
        generateCsmapCache(category);

        super.onAfterUpdate(category);

    }

    /**
     *  A la suppression d'un event, on met à jour le cache de csmapAgenda
     */
    @Override
    public void onAfterRemove(AssetCategory category) throws ModelListenerException {
        generateCsmapCache(category);

        super.onAfterRemove(category);

    }

    private void generateCsmapCache(AssetCategory category) throws ModelListenerException {
        // Use TCC to avoid post method business done by Liferay for DLFileEntry which clears categories
        TransactionCommitCallbackUtil.registerCallback(() -> {
            AssetVocabulary vocabuary = AssetVocabularyLocalServiceUtil.fetchAssetVocabulary(category.getVocabularyId());
            switch (vocabuary.getName().toLowerCase()){
                case VocabularyNames.EVENT_THEME:
                    _csmapCacheLocalService.generateCsmapCache(CodeCacheEnum.THEME.getId());
                    break;
                case VocabularyNames.TERRITORY:
                    _csmapCacheLocalService.generateCsmapCache(CodeCacheEnum.TERRITOIRE.getId());
                    break;
                case VocabularyNames.EVENT_TYPE:
                    _csmapCacheLocalService.generateCsmapCache(CodeCacheEnum.TYPE.getId());
                    break;
                case VocabularyNames.PLACE_TYPE:
                    _csmapCacheLocalService.generateCsmapCache(CodeCacheEnum.CATEGORIES.getId());
                    break;
                default:
                    break;
            }
            return null;
        });
    }

    private CsmapCacheLocalService _csmapCacheLocalService;
    @Reference(unbind = "-")
    protected void setCsmapCacheLocalService(CsmapCacheLocalService csmapCacheLocalService) {
        _csmapCacheLocalService = csmapCacheLocalService;
    }

}
