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

package eu.strasbourg.service.objtp.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Transactional;

import eu.strasbourg.service.objtp.model.FoundObject;
import eu.strasbourg.service.objtp.service.base.FoundObjectLocalServiceBaseImpl;
import eu.strasbourg.service.objtp.service.util.ObjtpImporter;

/**
 * The implementation of the found object local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.objtp.service.FoundObjectLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author JeremyZwickert
 * @see FoundObjectLocalServiceBaseImpl
 * @see eu.strasbourg.service.objtp.service.FoundObjectLocalServiceUtil
 */
public class FoundObjectLocalServiceImpl extends FoundObjectLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.objtp.service.FoundObjectLocalServiceUtil} to access the found object local service.
	 */
	
	@Override
	@Transactional(isolation = Isolation.DEFAULT, rollbackFor = {PortalException.class, SystemException.class,IOException.class,JSONException.class, ParseException.class})	
	public void doImport() throws JSONException, IOException, PortalException, ParseException  {
		ObjtpImporter objtpImporter = new ObjtpImporter();
		objtpImporter.doImport();
	}
	
	@Override
	public List<FoundObject> getFoundObjectByCategoryCode(String codeCategory) {
		return this.foundObjectPersistence.findByCategoryCode(codeCategory);
	}
}