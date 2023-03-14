package eu.strasbourg.portlet.familySpace;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.portlet.familySpace.configuration.FamilySpaceConfiguration;
import eu.strasbourg.utils.PortletHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import eu.strasbourg.utils.constants.VocabularyNames;

import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;


public class FamilySpaceDisplayContext {

	private ThemeDisplay themeDisplay;
	private FamilySpaceConfiguration configuration;
	private String publikId;
	private FamilySpaceResponse familySpace;

	public FamilySpaceDisplayContext(ThemeDisplay themeDisplay) {
		this.themeDisplay = themeDisplay;
		try {
			this.configuration = themeDisplay.getPortletDisplay()
					.getPortletInstanceConfiguration(FamilySpaceConfiguration.class);
		} catch (ConfigurationException e) {
			_log.error(e.getMessage(), e);
		}
	}

	public Boolean isUnderMaintenance() {
		return configuration.maintenance();
	}

	public FamilySpaceConfiguration getConfiguration() {
		return configuration;
	}

	public String getAddLunchURL() {
		String addLunchURL = configuration.addLunchURL();
		if (Validator.isNull(addLunchURL)) {
			addLunchURL = "#";
		}
		return addLunchURL;
	}

	public String getLinkAccountURL() {
		String linkAccountURL = configuration.linkAccountURL();
		if (Validator.isNull(linkAccountURL)) {
			linkAccountURL = "#";
		}
		return linkAccountURL;
	}

	public String getFamilySpaceURL() {
		String familySpaceURL = StrasbourgPropsUtil.getFamilySpaceURL();
		if (Validator.isNull(familySpaceURL)) {
			familySpaceURL = "#";
		}
		return familySpaceURL;
	}

	public void setFamilySpace(FamilySpaceResponse familySpace) {
		this.familySpace = familySpace;
	}

	public FamilySpaceResponse getFamilyspace() {
		return this.familySpace;
	}

	public LocalDate getToday() {
		return LocalDate.now();
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

	public boolean showDeleteButton() {
		return PortletHelper.showDeleteButtonOnDashboard(themeDisplay, themeDisplay.getPortletDisplay().getId());
	}

	public boolean showRetractableButton() {
		return PortletHelper.showRetractableButtonOnDashboard(themeDisplay, themeDisplay.getPortletDisplay().getId());
	}

	public boolean isFolded() {
		return PortletHelper.isPortletFoldedOnDashboard(themeDisplay, themeDisplay.getPortletDisplay().getId());
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}
