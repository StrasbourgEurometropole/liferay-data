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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalServiceUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.service.council.constants.ProcurationModeEnum;
import eu.strasbourg.service.council.model.CouncilSession;
import eu.strasbourg.service.council.model.Deliberation;
import eu.strasbourg.service.council.model.Official;
import eu.strasbourg.service.council.model.Procuration;
import eu.strasbourg.service.council.model.Vote;
import eu.strasbourg.service.council.service.CouncilSessionLocalServiceUtil;
import eu.strasbourg.service.council.service.DeliberationLocalServiceUtil;
import eu.strasbourg.service.council.service.OfficialLocalServiceUtil;
import eu.strasbourg.service.council.service.ProcurationLocalServiceUtil;
import eu.strasbourg.service.council.service.VoteLocalServiceUtil;
import eu.strasbourg.service.council.service.base.ProcurationLocalServiceBaseImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The implementation of the procuration local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.council.service.ProcurationLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProcurationLocalServiceBaseImpl
 * @see eu.strasbourg.service.council.service.ProcurationLocalServiceUtil
 */
public class ProcurationLocalServiceImpl extends ProcurationLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.council.service.ProcurationLocalServiceUtil} to access the procuration local service.
	 */

	public final static Log log = LogFactoryUtil.getLog(ProcurationLocalServiceImpl.class);

	/**
	 * Crée une entité vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public Procuration createProcuration(ServiceContext sc) throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		long pk = this.counterLocalService.increment();
		Procuration procuration = this.procurationLocalService.createProcuration(pk);

		procuration.setUserName(user.getFullName());
		procuration.setUserId(sc.getUserId());
		procuration.setGroupId(sc.getScopeGroupId());
		procuration.setStatus(WorkflowConstants.STATUS_DRAFT);

		return procuration;
	}

	/**
	 * Met à jour une entité et l'enregistre en base de données
	 */
	@Override
	public Procuration updateProcuration(Procuration procuration, ServiceContext sc) throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		procuration.setStatusByUserId(sc.getUserId());
		procuration.setStatusByUserName(user.getFullName());
		procuration.setStatusDate(sc.getModifiedDate());

		if (sc.getWorkflowAction() == WorkflowConstants.ACTION_PUBLISH) {
			procuration.setStatus(WorkflowConstants.STATUS_APPROVED);
		} else {
			procuration.setStatus(WorkflowConstants.STATUS_DRAFT);
		}
		procuration = this.procurationLocalService.updateProcuration(procuration);

		return procuration;
	}

	/**
	 * Met à jour le statut de l'entité par le framework workflow
	 */
	@Override
	public Procuration updateStatus(long userId, long entryId, int status, ServiceContext sc,
								 Map<String, Serializable> workflowContext) throws PortalException {
		// Statut de l'entité
		Procuration procuration = this.getProcuration(entryId);
		procuration.setStatus(status);
		User user = UserLocalServiceUtil.fetchUser(userId);
		if (user != null) {
			procuration.setStatusByUserId(user.getUserId());
			procuration.setStatusByUserName(user.getFullName());
		}
		procuration.setStatusDate(new Date());
		procuration = this.procurationLocalService.updateProcuration(procuration);

		return procuration;
	}

	/**
	 * Met à jour le statut de l'entité "manuellement" (pas via le workflow)
	 */
	@Override
	public void updateStatus(Procuration procuration, int status) throws PortalException {
		this.updateStatus(procuration.getUserId(), procuration.getProcurationId(), status, null,
				null);
	}

	/**
	 * Supprime une entité
	 */
	@Override
	public Procuration removeProcuration(long procurationId) throws PortalException {
		// Supprime l'entité
		Procuration procuration = this.procurationPersistence.remove(procurationId);

		// Supprime ce qui a rapport au workflow
		WorkflowInstanceLinkLocalServiceUtil.deleteWorkflowInstanceLinks(
				procuration.getCompanyId(), procuration.getGroupId(), Procuration.class.getName(),
				procuration.getProcurationId());

		return procuration;
	}

	/**
	 * Recherche par ID de CouncilSession
	 */
	@Override
	public List<Procuration> findByCouncilSessionId(long councilSessionId){
		return this.procurationPersistence.findByCouncilSessionId(councilSessionId);
	}

	/**
	 * Recherche par ID de CouncilSession et Mandataire
	 */
	@Override
	public List<Procuration> findByCouncilSessionIdAndOfficialVotersId(long councilSessionId, long officialVoter){
		return this.procurationPersistence.findByCouncilSessionIdAndOfficialVotersId(councilSessionId, officialVoter);
	}

	/**
	 * Recherche par ID de CouncilSession, élu et mandataire,
	 */
	@Override
	public Procuration findByCouncilSessionIdAndOfficialVotersAndUnavailableIds(
			long councilSessionId, long officialVoter, long officialUnavailableId){
		return this.procurationPersistence.fetchByCouncilSessionIdAndOfficialVotersAndUnavailableIds(
				councilSessionId, officialVoter, officialUnavailableId);
	}

	/**
	 * Recherche d'une absence à une session
	 */
	@Override
	public Procuration findAbsenceForCouncilSession(long councilSessionId, long officialId){
		return this.procurationPersistence.fetchByAbsenceForCouncilSession(councilSessionId, officialId, true);
	}

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
				for(Official official : officials) {
					Procuration procuration = procurations.stream()
						.filter(p -> p.getOfficialUnavailableId() == official.getOfficialId())
						.findFirst()
						.orElse(null);
					JSONArray officialsJSON = JSONFactoryUtil.createJSONArray();
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
						officialJSON.put("officialFullName", official.getFullName());
						officialJSON.put("procurationMode", 0);
						officialJSON.put("presential", 0);
						officialJSON.put("officialVoter", "");
						officialsJSON.put(officialJSON);
					}
					associatedProcuration.put("official", officialsJSON);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return associatedProcuration;
	}
	/**
	 * Recherche d'une procuration pour un officiel
	 */
	@Override
	public List<Procuration> findByCouncilSessionIdAndOfficialUnavailableId(long councilSessionId, long officialId) {
		return this.procurationPersistence.findByCouncilSessionIdAndOfficialUnavailableId(councilSessionId, officialId);
	}

}