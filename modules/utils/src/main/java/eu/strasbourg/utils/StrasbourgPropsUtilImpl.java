package eu.strasbourg.utils;

import eu.strasbourg.utils.api.StrasbourgPropsUtilService;
import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true,
		property = {},
		service = StrasbourgPropsUtilService.class)
public class StrasbourgPropsUtilImpl  implements StrasbourgPropsUtilService {

	@Override
	public String getPublikApiBase() {
		return StrasbourgPropsUtil.getPublikApiBase();
	}

	@Override
	public String getPublikJobOfferApply() {
		return StrasbourgPropsUtil.getPublikJobOfferApply();
	}

	@Override
	public String getPublikJobApply() {
		return StrasbourgPropsUtil.getPublikJobApply();
	}


}
