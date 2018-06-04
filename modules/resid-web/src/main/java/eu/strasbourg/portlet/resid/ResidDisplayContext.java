package eu.strasbourg.portlet.resid;

import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;

import eu.strasbourg.portlet.resid.configuration.ResidConfiguration;

public class ResidDisplayContext {

	private ThemeDisplay themeDisplay;
	private ResidConfiguration configuration;

	public ResidDisplayContext(ThemeDisplay themeDisplay) {
		this.themeDisplay = themeDisplay;
		try {
			this.configuration = themeDisplay.getPortletDisplay()
					.getPortletInstanceConfiguration(ResidConfiguration.class);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}

	public ResidConfiguration getConfiguration() {
		return configuration;
	}

	public String getResidURL() {
		String residURL = configuration.residURL();
		if (Validator.isNull(residURL)) {
			residURL = "#";
		}
		return residURL;
	}
}
