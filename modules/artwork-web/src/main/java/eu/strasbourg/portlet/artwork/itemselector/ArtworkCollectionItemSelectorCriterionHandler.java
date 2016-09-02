package eu.strasbourg.portlet.artwork.itemselector;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import com.liferay.item.selector.BaseItemSelectorCriterionHandler;
import com.liferay.item.selector.ItemSelectorCriterionHandler;

@Component(service = ItemSelectorCriterionHandler.class)
public class ArtworkCollectionItemSelectorCriterionHandler
	extends BaseItemSelectorCriterionHandler {

	@Override
	public Class getItemSelectorCriterionClass() {
		return ArtworkCollectionItemSelectorCriterion.class;
	}

	@Activate
	@Override
	protected void activate(BundleContext bundleContext) {
		super.activate(bundleContext);
	}

}
