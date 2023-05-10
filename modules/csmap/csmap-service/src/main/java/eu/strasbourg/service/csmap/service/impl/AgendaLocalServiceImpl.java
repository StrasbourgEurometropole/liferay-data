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

package eu.strasbourg.service.csmap.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import eu.strasbourg.service.csmap.model.Agenda;
import eu.strasbourg.service.csmap.service.base.AgendaLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.List;

/**
 * The implementation of the agenda local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>eu.strasbourg.service.csmap.service.AgendaLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AgendaLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=eu.strasbourg.service.csmap.model.Agenda",
	service = AopService.class
)
public class AgendaLocalServiceImpl extends AgendaLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>eu.strasbourg.service.csmap.service.AgendaLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>eu.strasbourg.service.csmap.service.AgendaLocalServiceUtil</code>.
	 */
	@Override
	public Agenda createAgenda() {
		long pk = this.counterLocalService.increment();
		return this.agendaLocalService.createAgenda(pk);
	}

	/**
	 * Met à jour un agenda et le cache des agendas
	 */
	@Override
	public Agenda updateAgenda(Agenda agenda){
		this.agendaPersistence.update(agenda);

		return agenda;
	}

	/**
	 * Supprime l'agenda et modifie le cache des agendas
	 *
	 * @param agendaId the primary key of the agenda
	 * @return the agenda that was removed
	 * @throws PortalException if a agenda with the primary key could not be found
	 */
	@Override
	public Agenda deleteAgenda(long agendaId) throws PortalException {
		Agenda agenda = this.agendaPersistence.remove(agendaId);

		return agenda;
	}

	@Override
	public Agenda getAgendaPrincipal() {
		List<Agenda> agendaPrincipal = this.agendaPersistence.findByIsPrincipalAndIsActive(true, true);
		if(agendaPrincipal.isEmpty()){
			return null;
		} else {
			return agendaPrincipal.get(0);
		}
	}

	@Override
	public Agenda getAgendaThematiqueActif() {
		List<Agenda> agendaThematique = this.agendaPersistence.findByIsPrincipalAndIsActive(false, true);
		if(!agendaThematique.isEmpty()){
			return agendaThematique.get(0);
		}
		return null;
	}

	@Override
	public List<Agenda> getAgendasThematiqueInactif() {
		return this.agendaPersistence.findByIsPrincipalAndIsActive(false, false);
	}

	@Override
	public List<Agenda> getAgendasThematique() {
		return this.agendaPersistence.findByIsPrincipal(false);
	}
}