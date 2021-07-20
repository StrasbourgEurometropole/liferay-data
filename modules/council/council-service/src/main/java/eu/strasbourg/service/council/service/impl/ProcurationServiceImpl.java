/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package eu.strasbourg.service.council.service.impl;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import eu.strasbourg.service.council.constants.ProcurationModeEnum;
import eu.strasbourg.service.council.model.CouncilSession;
import eu.strasbourg.service.council.model.Official;
import eu.strasbourg.service.council.model.Procuration;
import eu.strasbourg.service.council.service.CouncilSessionLocalServiceUtil;
import eu.strasbourg.service.council.service.OfficialLocalServiceUtil;
import eu.strasbourg.service.council.service.base.ProcurationServiceBaseImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * The implementation of the procuration remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>eu.strasbourg.service.council.service.ProcurationService</code> interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProcurationServiceBaseImpl
 */
public class ProcurationServiceImpl extends ProcurationServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use <code>eu.strasbourg.service.council.service.ProcurationServiceUtil</code> to access the procuration remote service.
	 */


	@Override
	public JSONObject findAssociatedProcurationJSON (long councilSessionId) {
		JSONObject associatedProcuration = JSONFactoryUtil.createJSONObject();

		try {
			Group group = GroupLocalServiceUtil.getGroups(-1, -1).stream().filter(g -> g.getGroupKey().equals("Evote conseils")).findFirst().orElse(null);
			if(group != null) {
				List<Official> officials = OfficialLocalServiceUtil.findByGroupIdAndIsActive(group.getGroupId(), true);

				List<Procuration> procurations = new ArrayList<>();
				CouncilSession councilSession = CouncilSessionLocalServiceUtil.getCouncilSession(councilSessionId);
				if(councilSession != null) {
					procurations = councilSession.getProcurations();
				}
				JSONArray officialsJSON = JSONFactoryUtil.createJSONArray();
				for(Official official : officials) {
					Procuration procuration = procurations.stream()
							.filter(p -> p.getOfficialUnavailableId() == official.getOfficialId())
							.findFirst()
							.orElse(null);
					if(procuration != null){
						JSONObject officialJSON = JSONFactoryUtil.createJSONObject();
						officialJSON.put("officialId", official.getOfficialId());
						officialJSON.put("hasProcuration", true);
						officialJSON.put("officialFullName", official.getFullName());
						officialJSON.put("procurationMode", procuration.getProcurationMode());
						if(procuration.getProcurationMode()== ProcurationModeEnum.AUTRE.getId()){
							officialJSON.put("otherProcurationMode", procuration.getOtherProcurationMode());
						}
						officialJSON.put("presential", procuration.getPresential());
						officialJSON.put("officialVoter", procuration.getOfficialVotersFullName());
						officialsJSON.put(officialJSON);

					} else {
						JSONObject officialJSON = JSONFactoryUtil.createJSONObject();
						officialJSON.put("officialId", official.getOfficialId());
						officialJSON.put("hasProcuration", false);
						officialsJSON.put(officialJSON);
					}
				}
				associatedProcuration.put("official", officialsJSON);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return associatedProcuration;
	}
}