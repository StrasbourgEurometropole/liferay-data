package eu.strasbourg.service.csmap.listener;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.transaction.TransactionCommitCallbackUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.csmap.constants.CodeCacheEnum;
import eu.strasbourg.service.csmap.service.CsmapCacheLocalService;
import eu.strasbourg.utils.FileEntryHelper;
import eu.strasbourg.utils.constants.VocabularyNames;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Angélique ZC
 *
 */
@Component(immediate = true, service = ModelListener.class)
public class DLFileEntryListener extends BaseModelListener<DLFileEntry>
{

    /**
     *  A la modification d'un event, on met à jour le cache de csmapAgenda
     */
    @Override
    public void onAfterCreate(DLFileEntry file) throws ModelListenerException {
        generateCsmapCache(file);

        super.onAfterCreate(file);

    }

    /**
     *  A la modification d'un event, on met à jour le cache de csmapAgenda
     */
    @Override
    public void onAfterUpdate(DLFileEntry file) throws ModelListenerException {
        generateCsmapCache(file);

        super.onAfterUpdate(file);

    }

    /**
     *  A la suppression d'un event, on met à jour le cache de csmapAgenda
     */
    @Override
    public void onAfterRemove(DLFileEntry file) throws ModelListenerException {
        generateCsmapCache(file);

        super.onAfterRemove(file);

    }

    private void generateCsmapCache(DLFileEntry file) throws ModelListenerException {
        if(file.getFolderId() == getCSMAPFolderId()) {
        // Use TCC to avoid post method business done by Liferay for DLFileEntry which clears categories
        TransactionCommitCallbackUtil.registerCallback(() -> {
            _csmapCacheLocalService.generateCsmapCache(CodeCacheEnum.CATEGORIES.getId());
            return null;
        });
        }
    }

    private long csmapFolderId = -1;
    private long getCSMAPFolderId() {
        if(csmapFolderId == -1) {
            long companyId = PortalUtil.getDefaultCompanyId();
            long companyGroupId = 0;
            try {
                companyGroupId = CompanyLocalServiceUtil.getCompany(companyId).getGroupId();

                if (Validator.isNotNull(companyGroupId)) {
                    DLFolder pictosFolder = DLFolderLocalServiceUtil
                            .getFolder(companyGroupId, 0, "Pictos");
                    if (pictosFolder != null) {
                        DLFolder placeTypeFolder = DLFolderLocalServiceUtil
                                .getFolder(companyGroupId, pictosFolder.getFolderId(), VocabularyNames.PLACE_TYPE);

                        if (placeTypeFolder != null) {
                            DLFolder csmapFolder = DLFolderLocalServiceUtil
                                    .getFolder(companyGroupId, placeTypeFolder.getFolderId(), "CSMap");
                            if (csmapFolder != null)
                                csmapFolderId = csmapFolder.getFolderId();
                        }
                    }
                }
            } catch (PortalException e) {
                e.printStackTrace();
            }
        }
        return csmapFolderId;
    }

    private CsmapCacheLocalService _csmapCacheLocalService;
    @Reference(unbind = "-")
    protected void setCsmapCacheLocalService(CsmapCacheLocalService csmapCacheLocalService) {
        _csmapCacheLocalService = csmapCacheLocalService;
    }

}
