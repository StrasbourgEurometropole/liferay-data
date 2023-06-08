package eu.strasbourg.service.opendata.geo.address;

import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

public class OpenDataGeoAddressServiceTracker extends ServiceTracker<OpenDataGeoAddressService, OpenDataGeoAddressService> {
	public OpenDataGeoAddressServiceTracker(Object host) {
		super(FrameworkUtil.getBundle(host.getClass()).getBundleContext(), OpenDataGeoAddressService.class, null);
	}
}
