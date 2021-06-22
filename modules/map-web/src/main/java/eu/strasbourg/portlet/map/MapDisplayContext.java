package eu.strasbourg.portlet.map;

import com.liferay.portal.kernel.theme.ThemeDisplay;
import eu.strasbourg.utils.PortletHelper;

public class MapDisplayContext {

	private ThemeDisplay themeDisplay;

	public MapDisplayContext(ThemeDisplay themeDisplay) {
		this.themeDisplay = themeDisplay;
	}

	//AngelTODO à réintégrer un fois que la gestion du territoire et des coordonnées de tous les events physiques sans exception sera faite
	/*public int getPoisCategoryCount(long idCategory, String prefilters, String tags, long groupId, String typesContenu,
									boolean dateField, String fromDate, String toDate, String localeId, long globalGroupId){
		return StrasbourgServiceUtil.getPoisCategoryCount(idCategory, prefilters, tags, groupId, typesContenu,
				dateField, fromDate, toDate, localeId, globalGroupId);
	}

	public int getPoisInterestCount(long idInterest, long groupId, String typesContenu,
									String localeId, long globalGroupId){
		return StrasbourgServiceUtil.getPoisInterestCount(idInterest, groupId, typesContenu, localeId, globalGroupId);
	}

	public int getFavoritesPoisCount(long groupId, String typesContenu){
		return StrasbourgServiceUtil.getFavoritesPoisCount(groupId, typesContenu);
	}*/

	public boolean showDeleteButton() {
		return PortletHelper.showDeleteButtonOnDashboard(themeDisplay, themeDisplay.getPortletDisplay().getId());
	}

	public boolean showRetractableButton() {
		return PortletHelper.showRetractableButtonOnDashboard(themeDisplay, themeDisplay.getPortletDisplay().getId());
	}

	public boolean isFolded() {
		return PortletHelper.isPortletFoldedOnDashboard(themeDisplay, themeDisplay.getPortletDisplay().getId());
	}

}
