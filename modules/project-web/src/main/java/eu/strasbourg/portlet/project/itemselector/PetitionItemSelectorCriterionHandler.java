package eu.strasbourg.portlet.project.itemselector;

import com.liferay.item.selector.BaseItemSelectorCriterionHandler;
import com.liferay.item.selector.ItemSelectorCriterionHandler;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Component;

/**
 * @author alexandre.quere
 */
@Component(service = ItemSelectorCriterionHandler.class)
public class PetitionItemSelectorCriterionHandler extends BaseItemSelectorCriterionHandler {

    @Override
    public Class getItemSelectorCriterionClass() {
        return ProjectItemSelectorCriterion.class;
    }

    protected void activate(BundleContext bundleContext){
        super.activate(bundleContext);
    }
}
