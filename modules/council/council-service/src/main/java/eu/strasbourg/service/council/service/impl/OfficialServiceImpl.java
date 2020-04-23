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
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import eu.strasbourg.service.council.model.Official;
import eu.strasbourg.service.council.model.OfficialModel;
import eu.strasbourg.service.council.service.base.OfficialServiceBaseImpl;
import eu.strasbourg.utils.SearchHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The implementation of the official remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.council.service.OfficialService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OfficialServiceBaseImpl
 * @see eu.strasbourg.service.council.service.OfficialServiceUtil
 */
public class OfficialServiceImpl extends OfficialServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.council.service.OfficialServiceUtil} to access the official remote service.
	 */

	public static final String MUNICIPAL = "municipal";
	public static final String EUROMETROPOLITAN = "eurometropolitan";

	/**
	 * Recherche d'élu pour l'autocompletion
	 * @param fullName Nom, prénom ou les deux de l'élu à trouver
	 * @param type Type de l'élu recherché
	 * @param removedOfficialId ID de l'élu à retirer de la liste des résultats (0 si non-utilisé)
	 * @param groupId Site sur lequel cherchés
	 * @return Liste des élus au format JSON
	 */
	@Override
	public JSONArray getOfficialByFullNameAndType(String fullName, String type, long removedOfficialId, long groupId) {
		JSONArray jsonOfficials = JSONFactoryUtil.createJSONArray();

		// Création du context de recherche
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(PortalUtil.getDefaultCompanyId());
		searchContext.setGroupIds(new long[]{groupId});

		// TODO : utilisation d'une méthode de reccherche plus "light" que BOSearchHit
		Hits hits = SearchHelper.getBOSearchHits(searchContext, 0, 50, Official.class.getName(), groupId,
				"", fullName, "title", true);

		List<Official> results = new ArrayList<>();

		if (hits != null) {
			for (Document document : hits.getDocs()) {
				Official official = this.officialLocalService.fetchOfficial(
						GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
				if (official != null && official.getOfficialId() != removedOfficialId) {
					results.add(official);
				}
			}
		}

		// TODO : voir pour indexer les champs sur lesquels on filtre : type et statut d'activité
		List<Official> filteredOfficial = new ArrayList<>();
		switch (type) {
			case MUNICIPAL:
				filteredOfficial = results.stream()
						.filter(OfficialModel::isIsActive)
						.filter(OfficialModel::isIsMunicipal)
						.collect(Collectors.toList());
				break;
			case EUROMETROPOLITAN:
				filteredOfficial = results.stream()
						.filter(OfficialModel::isIsActive)
						.filter(OfficialModel::isIsEurometropolitan)
						.collect(Collectors.toList());
				break;
		}

		for (Official official : filteredOfficial) {
			jsonOfficials.put(official.toJSON());
		}

		return jsonOfficials;
	}

}