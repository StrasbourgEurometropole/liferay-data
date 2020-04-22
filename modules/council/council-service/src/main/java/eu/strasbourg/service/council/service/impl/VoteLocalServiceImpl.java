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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalServiceUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.service.council.exception.NoSuchVoteException;
import eu.strasbourg.service.council.model.Procuration;
import eu.strasbourg.service.council.model.Vote;
import eu.strasbourg.service.council.service.base.VoteLocalServiceBaseImpl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * The implementation of the vote local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.council.service.VoteLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see VoteLocalServiceBaseImpl
 * @see eu.strasbourg.service.council.service.VoteLocalServiceUtil
 */
public class VoteLocalServiceImpl extends VoteLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.council.service.VoteLocalServiceUtil} to access the vote local service.
	 */

	public final static Log log = LogFactoryUtil.getLog(VoteLocalServiceImpl.class);

	/**
	 * Crée une entité vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public Vote createVote(ServiceContext sc) throws PortalException {
		long pk = this.counterLocalService.increment();
		Vote vote = this.voteLocalService.createVote(pk);

		vote.setGroupId(sc.getScopeGroupId());

		return vote;
	}

	/**
	 * Met à jour une entité et l'enregistre en base de données
	 */
	@Override
	public Vote updateVote(Vote vote, ServiceContext sc) throws PortalException {
		vote = this.voteLocalService.updateVote(vote);

		return vote;
	}

	/**
	 * Supprime une entité
	 */
	@Override
	public Vote removeVote(long voteId) throws PortalException {
		Vote vote = this.votePersistence.remove(voteId);

		return vote;
	}

	/**
	 * Supprime les votes d'une délibération
	 */
	public void removeVotesFromDeliberation(long deliberationId) throws PortalException {
		List<Vote> votesToRemove = this.findByDeliberationId(deliberationId);

		for (Vote vote: votesToRemove) {
			this.removeVote(vote.getVoteId());
		}
	}

	/**
	 * Retourne la liste des votes d'une délibération
	 */
	@Override
	public List<Vote> findByDeliberationId(long deliberationId) {
		return this.votePersistence.findByDeliberationId(deliberationId);
	}

	/**
	 * Retourne le vote d'un élu pour une délibération
	 */
	@Override
	public Vote findByDeliberationIdandOfficialId(long deliberationId, long officialId) throws NoSuchVoteException {
		return this.votePersistence.findByDeliberationIdAndOfficialId(deliberationId, officialId);
	}
}