package eu.strasbourg.utils.group;

import org.osgi.service.component.annotations.Component;

import eu.strasbourg.utils.api.GroupHelperService;

@Component(
	immediate = true,
	property = {},
	service = GroupHelperService.class)
public class GroupHelperImpl implements GroupHelperService {

	/**
	 * Si le paramètre est l'id d'un groupe "live", retourne l'id du group
	 * staging, sinon retourne le paramètre
	 */
	@Override
	public long getScopeOrStagingGroupId(long scopeGroupId) {
		return GroupHelper.getScopeOrStagingGroupId(scopeGroupId);
	}
}
