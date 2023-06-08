package eu.strasbourg.service.csmap.listener;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.transaction.TransactionCommitCallbackUtil;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.csmap.constants.CodeCacheEnum;
import eu.strasbourg.service.csmap.service.CsmapCacheLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Angélique ZC
 *
 */
@Component(immediate = true, service = ModelListener.class)
public class AssetEntryListener extends BaseModelListener<AssetEntry>
{

    /**
     *  A la modification d'un event, on met à jour le cache de csmapAgenda
     */
    @Override
    public void onAfterCreate(AssetEntry entry) throws ModelListenerException {
        generateCsmapCache(entry);

        super.onAfterCreate(entry);

    }

    /**
     *  A la modification d'un event, on met à jour le cache de csmapAgenda
     */
    @Override
    public void onAfterUpdate(AssetEntry entry) throws ModelListenerException {
        generateCsmapCache(entry);

        super.onAfterUpdate(entry);

    }

    /**
     *  A la suppression d'un event, on met à jour le cache de csmapAgenda
     */
    @Override
    public void onAfterRemove(AssetEntry entry) throws ModelListenerException {
        generateCsmapCache(entry);

        super.onAfterRemove(entry);

    }

    private void generateCsmapCache(AssetEntry entry) throws ModelListenerException {
        if(entry.getClassName().equals(Event.class.getName())) {
            // Use TCC to avoid post method business done by Liferay for DLFileEntry which clears categories
            TransactionCommitCallbackUtil.registerCallback(() -> {
                _csmapCacheLocalService.generateCsmapCache(CodeCacheEnum.EVENT.getId());
                return null;
            });
        }
    }

    private CsmapCacheLocalService _csmapCacheLocalService;
    @Reference(unbind = "-")
    protected void setCsmapCacheLocalService(CsmapCacheLocalService csmapCacheLocalService) {
        _csmapCacheLocalService = csmapCacheLocalService;
    }

}
