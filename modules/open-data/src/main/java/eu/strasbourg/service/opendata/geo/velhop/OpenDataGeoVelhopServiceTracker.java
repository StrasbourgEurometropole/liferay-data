package eu.strasbourg.service.opendata.geo.velhop;

import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

public class OpenDataGeoVelhopServiceTracker extends ServiceTracker<OpenDataGeoVelhopService, OpenDataGeoVelhopService> {
	public OpenDataGeoVelhopServiceTracker(Class<?> _class) {
		super(FrameworkUtil.getBundle(_class).getBundleContext(), OpenDataGeoVelhopService.class, null);
	}
}
