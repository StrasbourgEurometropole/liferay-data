package eu.strasbourg.portlet.gtfs.itemselector;

import com.liferay.item.selector.BaseItemSelectorCriterionHandler;
import com.liferay.item.selector.ItemSelectorCriterionHandler;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;


@Component(service = ItemSelectorCriterionHandler.class)
public class ArretItemSelectorCriterionHandler extends BaseItemSelectorCriterionHandler {

	@Override
	public Class getItemSelectorCriterionClass() {
		return ArretItemSelectorCriterion.class;
	}

	@Activate
	@Override
	protected void activate(BundleContext bundleContext) {
		super.activate(bundleContext);
	}
	
}
