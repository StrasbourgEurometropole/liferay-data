package eu.strasbourg.portlet.project.asset;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;
import eu.strasbourg.service.project.model.BudgetParticipatif;
import eu.strasbourg.service.project.service.BudgetParticipatifLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author alexandre.quere
 */
@Component(
        immediate = true,
        property = {"javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_WEB},
        service = AssetRendererFactory.class
)
public class BudgetParticipatifAssetRendererFactory extends BaseAssetRendererFactory<BudgetParticipatif> {

    public static final String TYPE = "budgetParticipatif";

    private BudgetParticipatifLocalService _budgetParticipatifLocalService;

    @Reference(unbind = "-")
    protected void setInterestLocalService(BudgetParticipatifLocalService BudgetParticipatifLocalService) {
        _budgetParticipatifLocalService = BudgetParticipatifLocalService;
    }
    public BudgetParticipatifAssetRendererFactory() {
        setClassName(BudgetParticipatif.class.getName());
        setLinkable(true);
        setPortletId(StrasbourgPortletKeys.PROJECT_WEB);
        setSearchable(true);
    }


    @Override
    public AssetRenderer<BudgetParticipatif> getAssetRenderer(long classPK, int type) throws PortalException {
        BudgetParticipatif entry = _budgetParticipatifLocalService.getBudgetParticipatif(classPK);

        BudgetParticipatifAssetRenderer budgetParticipatifAssetRenderer =
                new BudgetParticipatifAssetRenderer(entry);

        budgetParticipatifAssetRenderer.setAssetRendererType(type);

        return budgetParticipatifAssetRenderer;

    }

    @Override
    public String getType() {
        return TYPE;
    }
}
