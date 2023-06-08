package eu.strasbourg.portlet.resid;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.portlet.resid.configuration.ResidConfiguration;
import eu.strasbourg.portlet.resid.dossier.DossiersResponse;
import eu.strasbourg.utils.PortletHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;

import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

public class ResidDisplayContext {

	private ThemeDisplay themeDisplay;
	private ResidConfiguration configuration;
	private String publikId;
	private DossiersResponse dossierResponse;

	public ResidDisplayContext(ThemeDisplay themeDisplay) {
		this.themeDisplay = themeDisplay;
		try {
			this.configuration = themeDisplay.getPortletDisplay()
					.getPortletInstanceConfiguration(ResidConfiguration.class);
		} catch (ConfigurationException e) {
			_log.error(e.getMessage(), e);
		}
	}

	public ResidConfiguration getConfiguration() {
		return configuration;
	}

	public Boolean isUnderMaintenance() {
		return configuration.maintenance();
	}

	public String getResidURL() {
		String residURL = StrasbourgPropsUtil.getResidantURL();
		if (Validator.isNull(residURL)) {
			residURL = "#";
		}
		return residURL;
	}

	// Récupération de l'url de la zone
	public String getZoneURL(String code) {
		String[] zones = configuration.zones();
		for (String zone : zones) {
			String[] zoneValue = zone.split(";");
			if(zoneValue[0].equals(code)) {
				return zoneValue[1];
			}
		}
		return "";
	}

	// Récupération de l'id utilisateur
	public String getPublikID(PortletRequest resourceRequest) {
		if (Validator.isNull(this.publikId)) {
			LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(resourceRequest);
			HttpServletRequest originalRequest = liferayPortletRequest.getHttpServletRequest();

			this.publikId = SessionParamUtil.getString(originalRequest, "publik_internal_id");
		}

		return this.publikId;
	}

	public void setDossierResponse(DossiersResponse dossierResponse) {
		this.dossierResponse = dossierResponse;
	}

	public DossiersResponse getDossierResponse() {
		return this.dossierResponse;
	}

	public LocalDate getToday() {
		return LocalDate.now();
	}

	public boolean showDeleteButton() {
		return PortletHelper.showDeleteButtonOnDashboard(themeDisplay, themeDisplay.getPortletDisplay().getId());
	}

	public boolean showRetractableButton() {
		return PortletHelper.showRetractableButtonOnDashboard(themeDisplay, themeDisplay.getPortletDisplay().getId());
	}

	public boolean isFolded() {
		return PortletHelper.isPortletFoldedOnDashboard(themeDisplay, themeDisplay.getPortletDisplay().getId());
	}

	private Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}
