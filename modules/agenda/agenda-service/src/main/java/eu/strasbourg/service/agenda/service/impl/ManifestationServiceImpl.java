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

package eu.strasbourg.service.agenda.service.impl;

import java.util.List;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.agenda.model.Manifestation;
import eu.strasbourg.service.agenda.service.base.ManifestationServiceBaseImpl;

/**
 * The implementation of the manifestation remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.agenda.service.ManifestationService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author BenjaminBini
 * @see ManifestationServiceBaseImpl
 * @see eu.strasbourg.service.agenda.service.ManifestationServiceUtil
 */
@ProviderType
public class ManifestationServiceImpl extends ManifestationServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.agenda.service.ManifestationServiceUtil} to access the manifestation remote service.
	 */

	@Override
	public JSONObject getManifestation(long id) throws PortalException {
		Manifestation manifestation = this.manifestationLocalService.fetchManifestation(id);
		if (!manifestation.isApproved()) {
			return JSONFactoryUtil.createJSONObject();
		}
		return manifestation.toJSON();
	}

	@Override
	public JSONArray getManifestations() throws PortalException {
		List<Manifestation> manifestations = this.manifestationLocalService.getManifestations(-1, -1);
		return this.getApprovedJSONManifestations(manifestations);
	}

	private JSONArray getApprovedJSONManifestations(List<Manifestation> manifestations) {
		JSONArray jsonManifestations = JSONFactoryUtil.createJSONArray();
		for (Manifestation manifestation : manifestations) {
			if (manifestation.isApproved()) {
				jsonManifestations.put(manifestation.toJSON());
			}
		}
		return jsonManifestations;
	}
}