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

package eu.strasbourg.service.help.service.impl;

import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import eu.strasbourg.service.help.exception.NoSuchHelpRequestException;
import eu.strasbourg.service.help.model.HelpRequest;
import eu.strasbourg.service.help.service.base.HelpRequestLocalServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

import java.util.Date;
import java.util.List;

/**
 * The implementation of the help request local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>eu.strasbourg.service.help.service.HelpRequestLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HelpRequestLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=eu.strasbourg.service.help.model.HelpRequest",
	service = AopService.class
)
public class HelpRequestLocalServiceImpl
	extends HelpRequestLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>eu.strasbourg.service.help.service.HelpRequestLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>eu.strasbourg.service.help.service.HelpRequestLocalServiceUtil</code>.
	 */

	/**
	 * Methode de creation d'une demande d'aide
	 * @param sc Le contexte de la requete.
	 * @return L'aide cree.
	 */
	@Override
	public HelpRequest createHelpRequest(ServiceContext sc){
		long pk = this.counterLocalService.increment();

		HelpRequest result = this.helpRequestLocalService.createHelpRequest(pk);

		result.setGroupId(sc.getScopeGroupId());
		result.setCreateDate(new Date());

		return result;
	}

	/**
	 * Supprimer une demande d'aide
	 * @param helpRequestId Id de la demande d'aide
	 */
	@Override
	public HelpRequest removeHelpRequest(long helpRequestId){
		try {
			HelpRequest result = this.helpRequestPersistence.remove(helpRequestId);
			return result;
		} catch (NoSuchHelpRequestException e) {
			_log.error("Pas de demande d'aide : ", e);
		}
		return null;
	}

	/**
	 * Retourne les demandes d'aides pour un utilisateur
	 */
	@Override
	public List<HelpRequest> getByPublikId(String publikId) {
		return this.helpRequestPersistence.findByPublikId(publikId);
	}

	/**
	 * Retourne la liste des demands d'aides pour une proposition
	 */
	@Override
	public List<HelpRequest> getByHelpProposalId(long helpRequestId) {
		return this.helpRequestPersistence.findByHelpProposalId(helpRequestId);
	}

	/**
	 * Retourne la demande d'aide pour un utilisateur et une proposition donnee
	 */
	@Override
	public HelpRequest getByPublikIdAndHelpProposalId(String publikId, long helpProposalId) {
		return this.helpRequestPersistence.fetchByPublikIdAndHelpProposalId(publikId, helpProposalId);
	}



	public final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}