package eu.strasbourg.portlet.project.asset;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.PortletBag;
import com.liferay.portal.kernel.portlet.PortletBagPool;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import eu.strasbourg.service.project.model.BudgetParticipatif;
import eu.strasbourg.service.project.service.BudgetParticipatifLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author alexandre.quere
 */
@Component(
    immediate = true,
    property = {
    	"javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_WEB
    },
    service = AssetRendererFactory.class
)
public class BudgetParticipatifAssetRendererFactory extends BaseAssetRendererFactory<BudgetParticipatif> {

    public static final String TYPE = "budgetParticipatif";

    private BudgetParticipatifLocalService _budgetParticipatifLocalService;
    
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

    /**
     * Notes : surcharge de la méthode pour enlever le préfix du className "model.resource" non présent sur les modules
     * 			custom en 7.0
     */
    @Override
    public String getTypeName(Locale locale) {
        String key = getClassName();

        String value = LanguageUtil.get(locale, key, null);

        String portletId = getPortletId();

        if ((value == null) && (portletId != null)) {
            PortletBag portletBag = PortletBagPool.get(portletId);

            ResourceBundle resourceBundle = portletBag.getResourceBundle(
                    locale);

            if (resourceBundle != null) {
                value = ResourceBundleUtil.getString(resourceBundle, key);
            }
        }

        if (value == null) {
            value = getClassName();
        }

        return value;
    }
    
    @Reference(unbind = "-")
    protected void setBudgetParticipatifocalService(BudgetParticipatifLocalService BudgetParticipatifLocalService) {
        _budgetParticipatifLocalService = BudgetParticipatifLocalService;
    }
}
