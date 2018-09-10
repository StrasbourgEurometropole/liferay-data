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

package eu.strasbourg.service.project.service.impl;

import java.util.List;

import eu.strasbourg.service.project.model.InitiativeHelp;
import eu.strasbourg.service.project.service.base.InitiativeHelpLocalServiceBaseImpl;

/**
 * The implementation of the initiative help local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.project.service.InitiativeHelpLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Cedric Henry
 * @see InitiativeHelpLocalServiceBaseImpl
 * @see eu.strasbourg.service.project.service.InitiativeHelpLocalServiceUtil
 */
public class InitiativeHelpLocalServiceImpl
	extends InitiativeHelpLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.project.service.InitiativeHelpLocalServiceUtil} to access the initiative help local service.
	 */
	
	/**
	 * Retourne la liste des aides d'une initiative
	 */
	@Override
	public List<InitiativeHelp> getByInitiativeId(long initiativeId) {
		return this.initiativeHelpPersistence.findByinitiativeId(initiativeId);
	}
}