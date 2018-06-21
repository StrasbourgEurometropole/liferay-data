package eu.strasbourg.portlet.resid;

import java.time.LocalDate;

import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;

import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.Validator;

import eu.strasbourg.portlet.resid.configuration.ResidConfiguration;
import eu.strasbourg.portlet.resid.dossier.DossiersResponse;

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

	public String getZoneURL() {
		String zoneURL = configuration.zoneURL();
		if (Validator.isNull(zoneURL)) {
			zoneURL = "#";
		}
		return zoneURL;
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
}
