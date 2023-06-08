package eu.strasbourg.utils.group;

import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;

public class GroupHelper {

	/**
	 * Si le paramètre est l'id d'un groupe "live", retourne l'id du group
	 * staging, sinon retourne le paramètre
	 */
	public static long getScopeOrStagingGroupId(long scopeGroupId) {
		Group group = GroupLocalServiceUtil.fetchGroup(scopeGroupId);
		if (group != null && group.hasStagingGroup()) {
			return group.getStagingGroup().getGroupId();
		}
		return scopeGroupId;
	}
}
