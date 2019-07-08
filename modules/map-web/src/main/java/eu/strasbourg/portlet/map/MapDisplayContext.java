package eu.strasbourg.portlet.map;

import com.liferay.portal.kernel.theme.ThemeDisplay;
import eu.strasbourg.service.strasbourg.service.StrasbourgServiceUtil;
import eu.strasbourg.utils.PortletHelper;

public class MapDisplayContext {

	private ThemeDisplay themeDisplay;

	public MapDisplayContext(ThemeDisplay themeDisplay) {
		this.themeDisplay = themeDisplay;
	}

	public int getFavoritesPoisCount(long groupId, String typesContenu){
		return StrasbourgServiceUtil.getFavoritesPoisCount(groupId, typesContenu);
	}

	public int getPoisCategoryCount(long idCategory, String prefilters, long groupId, String typesContenu){
		return StrasbourgServiceUtil.getPoisCategoryCount(idCategory, prefilters, groupId, typesContenu);
	}

	public int getPoisInterestCount(long idInterest, long groupId, String typesContenu){
		return StrasbourgServiceUtil.getPoisInterestCount(idInterest, groupId, typesContenu);
	}

	public boolean showDeleteButton() {
		return PortletHelper.showDeleteButtonOnDashboard(themeDisplay, themeDisplay.getPortletDisplay().getId());
	}

}
