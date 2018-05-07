package eu.strasbourg.portlet.map;

import eu.strasbourg.service.strasbourg.service.StrasbourgServiceUtil;

public class MapDisplayContext {

	public MapDisplayContext() {
	}

	public int getFavoritesPoisCount(long groupId, String typesContenu){
		return StrasbourgServiceUtil.getFavoritesPoisCount(groupId, typesContenu);
	}

	public int getPoisCategoryCount(long idCategory, long groupId, String typesContenu){
		return StrasbourgServiceUtil.getPoisCategoryCount(idCategory, groupId, typesContenu);
	}

	public int getPoisInterestCount(long idInterest, long groupId, String typesContenu){
		return StrasbourgServiceUtil.getPoisInterestCount(idInterest, groupId, typesContenu);
	}

}
