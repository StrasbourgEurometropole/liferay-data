package eu.strasbourg.service.project.search;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.Summary;
import eu.strasbourg.service.project.model.BudgetParticipatif;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import java.util.Locale;

/**
 * @author alexandre.quere
 */
@Component(immediate = true, service = Indexer.class)
public class BudgetParticipatifIndexer extends BaseIndexer<BudgetParticipatif> {

    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
    public static final String CLASS_NAME = BudgetParticipatif.class.getName();

    public BudgetParticipatifIndexer() {
        setFilterSearch(true);
        setPermissionAware(true);
    }

    @Override
    protected void doDelete(BudgetParticipatif budgetParticipatif) throws Exception {
        deleteDocument(budgetParticipatif.getCompanyId(),budgetParticipatif.getBudgetParticipatifId());
    }

    @Override
    protected Document doGetDocument(BudgetParticipatif budget) throws Exception {
        Document document = getBaseModelDocument(CLASS_NAME,budget);

        //On indexe toute la hierarchie de catégories (parents et enfants des catégories de l'entité)
        //Long[] assetCategoryIds = AssetVocabularyHelper.getFullHierarchyCategoriesIds(budget.)
        return null;
    }

    @Override
    protected Summary doGetSummary(Document document, Locale locale, String snippet, PortletRequest portletRequest, PortletResponse portletResponse) throws Exception {
        return null;
    }

    @Override
    protected void doReindex(String className, long classPK) throws Exception {

    }

    @Override
    protected void doReindex(String[] ids) throws Exception {

    }

    @Override
    protected void doReindex(BudgetParticipatif budget) throws Exception {

    }

    @Override
    public String getClassName() {
        return null;
    }
}
