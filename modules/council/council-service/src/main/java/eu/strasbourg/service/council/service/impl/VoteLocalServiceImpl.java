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
import com.liferay.portal.kernel.service.ServiceContext;
import eu.strasbourg.service.council.model.Vote;
import eu.strasbourg.service.council.service.base.VoteLocalServiceBaseImpl;
import eu.strasbourg.service.council.service.persistence.VotePK;

import java.util.List;

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
	 *  Crée une entité vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public Vote createVote(long officialId, long deliberationId, ServiceContext sc) {

		VotePK votePK = new VotePK(officialId, deliberationId);
		Vote vote = this.createVote(votePK);

		vote.setGroupId(sc.getScopeGroupId());

		return vote;
	}

	/**
	 * Met à jour une entité et l'enregistre en base de données
	 */
	@Override
	public Vote updateVote(Vote vote, ServiceContext sc) {
		vote = this.voteLocalService.updateVote(vote);

		return vote;
	}

	/**
	 * Supprime une entité
	 */
	@Override
	public Vote removeVote(long  officialId, long  deliberationId) throws PortalException {
		VotePK votePK = new VotePK(officialId, deliberationId);
		return this.votePersistence.remove(votePK);
	}

	/**
	 * Supprime les votes d'une délibération
	 */
	public void removeVotesFromDeliberation(long deliberationId) throws PortalException {
		List<Vote> votesToRemove = this.findByDeliberationId(deliberationId);

		for (Vote vote: votesToRemove) {
			this.removeVote(vote.getOfficialId(), vote.getDeliberationId());
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
	public Vote findByDeliberationIdandOfficialId(long deliberationId, long officialId) {
		return this.votePersistence.fetchByDeliberationIdAndOfficialId(deliberationId, officialId);
	}
}