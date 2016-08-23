package eu.strasbourg.utils;

import javax.portlet.PortletRequest;

public class PortletConfigurationHelper {
	public String getAttributeFromPrefOrRequest(PortletRequest request,
		String attributeName) {
		String valueFromPerfs = request.getPreferences().getValue(attributeName, "");
		String valueFromRequest = request.getParameter(attributeName);
		String value = valueFromPerfs;
		if (valueFromRequest != null) {
		    value = valueFromRequest;
		}
		System.out.println("it works !");
		return value;
	}
}
