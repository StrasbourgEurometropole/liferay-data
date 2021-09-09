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

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.service.csmap.model.PlaceCategories;
import eu.strasbourg.service.csmap.service.PlaceCategoriesLocalService;
import eu.strasbourg.service.csmap.service.PlaceCategoriesLocalServiceUtil;
import eu.strasbourg.service.csmap.service.base.PlaceCategoriesLocalServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

import java.util.List;

/**
 * The implementation of the place categories local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>eu.strasbourg.service.csmap.service.PlaceCategoriesLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlaceCategoriesLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=eu.strasbourg.service.csmap.model.PlaceCategories",
	service = AopService.class
)
public class PlaceCategoriesLocalServiceImpl
	extends PlaceCategoriesLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>eu.strasbourg.service.csmap.service.PlaceCategoriesLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>eu.strasbourg.service.csmap.service.PlaceCategoriesLocalServiceUtil</code>.
	 */
	@Override
	public PlaceCategories createPlaceCategories() {
		long pk = this.counterLocalService.increment();
		return this.placeCategoriesLocalService.createPlaceCategories(pk);
	}
	@Override
	public PlaceCategories getPlaceCategories() {
		List<PlaceCategories> allPlaceCategories = this.placeCategoriesLocalService.getPlaceCategorieses(-1,-1);
		if(allPlaceCategories.isEmpty()){
			return null;
		} else {
			return allPlaceCategories.get(0);
		}
	}
}