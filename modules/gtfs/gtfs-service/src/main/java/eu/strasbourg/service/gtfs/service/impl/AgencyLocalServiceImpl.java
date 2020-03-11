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

package eu.strasbourg.service.gtfs.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import java.io.IOException;
import java.util.Map;

import eu.strasbourg.service.gtfs.model.Agency;
import eu.strasbourg.service.gtfs.service.base.AgencyLocalServiceBaseImpl;
import eu.strasbourg.utils.models.AgencyGTFS;

/**
 * The implementation of the agency local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.gtfs.service.AgencyLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Cedric Henry
 * @see AgencyLocalServiceBaseImpl
 * @see eu.strasbourg.service.gtfs.service.AgencyLocalServiceUtil
 */
public class AgencyLocalServiceImpl extends AgencyLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.gtfs.service.AgencyLocalServiceUtil} to access the agency local service.
	 */
	
	/**
	 * Crée une agence vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public Agency createAgency(ServiceContext sc) throws PortalException {
		long pk = counterLocalService.increment();
		Agency agency = this.agencyLocalService.createAgency(pk);

		return agency;
	}
	
	/**
	 * Crée une agence à partir d'une entrée GTFS
	 */
	@Override
	public Agency createAgencyFromGTFS(AgencyGTFS entry) throws PortalException {
		long pk = counterLocalService.increment();
		Agency agency = this.agencyLocalService.createAgency(pk);
		
		agency.setAgency_name(entry.getAgency_name());
		agency.setAgency_url(entry.getAgency_url());
		agency.setAgency_timezone(entry.getAgency_timezone());
		agency.setAgency_phone(entry.getAgency_phone());
		agency.setAgency_fare_url(entry.getAgency_fare_url());
		agency.setAgency_lang(entry.getAgency_lang());
		
		agency = this.agencyLocalService.updateAgency(agency);

		return agency;
	}
	
	/**
	 * Met à jour une agence et l'enregistre en base de données
	 * @throws IOException
	 */
	@Override
	public Agency updateAgency(Agency agency, ServiceContext sc) throws PortalException {
		agency = this.agencyLocalService.updateAgency(agency);

		return agency;
	}
	
	/**
	 * Supprime une agence
	 */
	@Override
	public Agency removeAgency(long agencyId) throws PortalException {
		Agency agency = this.agencyPersistence.remove(agencyId);

		return agency;
	}
	
	/**
	 * Supprime toutes les agences
	 */
	@Override
	public void removeAllAgencies() throws PortalException {
		this.agencyPersistence.removeAll();
	}
	
	/**
	 * Import des agences sous le format de données GTFS
	 */
	@Override
	public void importFromGTFS(Map<String, AgencyGTFS> data) throws PortalException {
		// Flush de la table avant incorporation des nouvelles données
		this.removeAllAgencies();
		
		for (Map.Entry<String, AgencyGTFS> mapEntry : data.entrySet()) {
			this.createAgencyFromGTFS(mapEntry.getValue());
		}
	}
	
	
}