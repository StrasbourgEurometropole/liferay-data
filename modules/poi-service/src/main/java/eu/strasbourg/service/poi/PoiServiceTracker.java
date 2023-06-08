package eu.strasbourg.service.poi;

import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

public class PoiServiceTracker extends ServiceTracker<PoiService, PoiService> {
	public PoiServiceTracker(Object host) {
		super(FrameworkUtil.getBundle(host.getClass()).getBundleContext(), PoiService.class, null);
	}
}
