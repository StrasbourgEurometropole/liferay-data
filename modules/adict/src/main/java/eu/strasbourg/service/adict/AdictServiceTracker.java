package eu.strasbourg.service.adict;

import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

public class AdictServiceTracker extends ServiceTracker<AdictService, AdictService> {
	public AdictServiceTracker(Object host) {
		super(FrameworkUtil.getBundle(host.getClass()).getBundleContext(), AdictService.class, null);
	}
}
