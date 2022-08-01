package eu.strasbourg.service.csmap.listener;


import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;

import java.util.Locale;

@Component(immediate = true, service = ModelListener.class)
public class JournalArticleListener extends BaseModelListener<JournalArticle>
{
    public static final String STRUCTURE_EMERGENCY_HELP = "Aide d\u0027urgence";
    public static final String GROUP_CSMAP = "CSMAP";

    private final Log _log = LogFactoryUtil.getLog(JournalArticleListener.class);

    /**
     * Regarde si le journalArticle est de structure "Aide d'urgence"
     * Si oui, on retrouve la catégorie d'aide d'urgence et on la met à jour (simple update)
     * Ca permet de gérer la suppression d'aide d'urgence dans l'API CSMAP get-emergencies
     * Car on gère uniquement la déletion de la catégorie actuellement
     * @param model
     * @throws ModelListenerException
     */
    @Override
    public void onAfterUpdate(JournalArticle model) throws ModelListenerException {
        try {
            long csmapId = GroupLocalServiceUtil.getGroup(PortalUtil.getDefaultCompanyId(), GROUP_CSMAP).getGroupId();
            DDMStructure structure = DDMStructureLocalServiceUtil.getStructures(csmapId).stream().filter(s -> s.getName(Locale.FRANCE).equals(STRUCTURE_EMERGENCY_HELP)).findFirst().orElse(null);

            // Si le journal article récupéré est de structure "Aide d'urgences"
            if(structure != null && model.getDDMStructureKey().equals(structure.getStructureKey())) {
                //Récupère l'assetEntry lié
                AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry(JournalArticle.class.getName(), model.getResourcePrimKey());
                // On met à jour ses catégories
                // Etant donné que c'est un journal de structure "Aide d'urgence", il ne devrait avoir qu'une seule categ de vocab "catégories d'aides d'urgence"
                for (AssetCategory category: assetEntry.getCategories()) {
                    AssetCategoryLocalServiceUtil.updateAssetCategory(category);
                }
            }

        } catch (PortalException e) {
            _log.error(e);
        }

        super.onAfterUpdate(model);
    }

}
