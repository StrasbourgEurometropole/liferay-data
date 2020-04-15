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
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.service.council.model.Deliberation;
import eu.strasbourg.service.council.service.base.DeliberationLocalServiceBaseImpl;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * The implementation of the deliberation local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.council.service.DeliberationLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DeliberationLocalServiceBaseImpl
 * @see eu.strasbourg.service.council.service.DeliberationLocalServiceUtil
 */
public class DeliberationLocalServiceImpl extends DeliberationLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.council.service.DeliberationLocalServiceUtil} to access the deliberation local service.
	 */

	public final static Log log = LogFactoryUtil.getLog(DeliberationLocalServiceImpl.class);

	/**
	 * Crée une entité vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public Deliberation createDeliberation(ServiceContext sc) throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		long pk = this.counterLocalService.increment();
		Deliberation deliberation = this.deliberationLocalService.createDeliberation(pk);

		deliberation.setUserName(user.getFullName());
		deliberation.setUserId(sc.getUserId());
		deliberation.setGroupId(sc.getScopeGroupId());
		deliberation.setStatus(WorkflowConstants.STATUS_DRAFT);

		return deliberation;
	}

}