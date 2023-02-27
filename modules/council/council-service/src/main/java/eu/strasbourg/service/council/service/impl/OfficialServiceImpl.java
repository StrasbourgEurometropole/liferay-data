/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 * <p>
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * <p>
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package eu.strasbourg.service.council.service.impl;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import eu.strasbourg.service.council.model.CouncilSession;
import eu.strasbourg.service.council.model.Official;
import eu.strasbourg.service.council.model.OfficialModel;
import eu.strasbourg.service.council.model.Type;
import eu.strasbourg.service.council.service.CouncilSessionLocalServiceUtil;
import eu.strasbourg.service.council.service.OfficialLocalServiceUtil;
import eu.strasbourg.service.council.service.ProcurationLocalServiceUtil;
import eu.strasbourg.service.council.service.base.OfficialServiceBaseImpl;
import eu.strasbourg.utils.SearchHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    /**
     * Types de conseil
     */
    public static final String MUNICIPAL = "municipal";
    public static final String EUROMETROPOLITAN = "eurometropolitan";

    /**
     * JSON var names des types de statut de connexion
     */
    public static final String JSON_UNCONNECTED = "unconnected";
    public static final String JSON_ABSENTS = "absents";
    public static final String JSON_CONNECTED = "connected";

    /**
     * Recherche d'élu pour l'autocompletion
     *
     * @param fullName          Nom, prénom ou les deux de l'élu à trouver
     * @param type              Type de l'élu recherché (peut être vide)
     * @param removedOfficialId ID de l'élu à retirer de la liste des résultats (0 si non-utilisé)
     * @param groupId           Site sur lequel cherchés
     * @return Liste des élus au format JSON
     */
    @Override
    public JSONArray getOfficialByFullNameAndType(String fullName, String type, long removedOfficialId, long groupId) {
        JSONArray jsonOfficials = JSONFactoryUtil.createJSONArray();

        // Création du context de recherche
        SearchContext searchContext = new SearchContext();
        searchContext.setCompanyId(PortalUtil.getDefaultCompanyId());
        searchContext.setGroupIds(new long[]{groupId});

        Hits hits = SearchHelper.getCouncilOfficialSearchHits(searchContext, 0, 50, Official.class.getName(),
                groupId, fullName.toLowerCase());

        List<Official> results = new ArrayList<>();

        if (hits != null) {
            for (Document document : hits.getDocs()) {
                Official official = this.officialLocalService.fetchOfficial(GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));

                if (official != null && official.getOfficialId() != removedOfficialId) {
                    if (type.isEmpty()) {
                        results.add(official);
                    } else {
                        List<Type> officialCouncilTypes = official.getCouncilTypes();
                        Optional<Type> optionalType = officialCouncilTypes.stream().filter(t -> t.getTitle().equals(type)).findFirst();
                        if (optionalType.isPresent()) {
                            results.add(official);
                        }
                    }
                }
            }
        }

        // TODO : voir pour indexer les champs sur lesquels on filtre : type et statut d'activité
        List<Official> filteredOfficial;
        filteredOfficial = results.stream()
                .filter(OfficialModel::isIsActive)
                .collect(Collectors.toList());

        for (Official official : filteredOfficial) {
            jsonOfficials.put(official.toJSON());
        }

        return jsonOfficials;
    }

    /**
     * Recherche des électeurs pour une session données groupés par statut de connexion et nom complet
     *
     * @param councilSessionId
     * @param groupId          ID du site
     * @return Tableaux des statuts possibles contenant la liste des électeurs assimilables auxdits statuts
     */
    @Override
    public JSONObject getOfficialByConnexionStatus(long councilSessionId, long groupId) {
        JSONObject jsonData = JSONFactoryUtil.createJSONObject();

        JSONArray jsonUnconnected = JSONFactoryUtil.createJSONArray();
        JSONArray jsonAbsents = JSONFactoryUtil.createJSONArray();
        JSONArray jsonConnected = JSONFactoryUtil.createJSONArray();

        CouncilSession councilSession = CouncilSessionLocalServiceUtil.fetchCouncilSession(councilSessionId);

        if (councilSession != null) {
            List<Official> concernedOfficial = OfficialLocalServiceUtil
                    .findByGroupIdAndTypeId(groupId, councilSession.getTypeId());

            jsonData.put("councilSessionTitle", councilSession.getTitle());
            for (Official official : concernedOfficial) {
                long officialId = official.getOfficialId();
                boolean isAbsent = ProcurationLocalServiceUtil.isOfficialAbsent(councilSessionId, officialId);
                if (isAbsent) {
                    jsonAbsents.put(official.toJSON());
                } else if (official.isConnected()) {
                    jsonConnected.put(official.toJSON());
                } else {
                    jsonUnconnected.put(official.toJSON());
                }
            }
        }

        jsonData.put(JSON_UNCONNECTED, jsonUnconnected);
        jsonData.put(JSON_ABSENTS, jsonAbsents);
        jsonData.put(JSON_CONNECTED, jsonConnected);

        return jsonData;
    }

}