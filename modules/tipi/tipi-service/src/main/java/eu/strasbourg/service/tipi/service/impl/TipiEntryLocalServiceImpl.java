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

package eu.strasbourg.service.tipi.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.liferay.portal.kernel.exception.SystemException;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.tipi.model.TipiEntry;
import eu.strasbourg.service.tipi.service.TipiEntryLocalServiceUtil;
import eu.strasbourg.service.tipi.service.base.TipiEntryLocalServiceBaseImpl;

/**
 * The implementation of the tipi entry local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link eu.strasbourg.service.tipi.service.TipiEntryLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @see TipiEntryLocalServiceBaseImpl
 * @see eu.strasbourg.service.tipi.service.TipiEntryLocalServiceUtil
 */
@ProviderType
public class TipiEntryLocalServiceImpl extends TipiEntryLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * eu.strasbourg.service.tipi.service.TipiEntryLocalServiceUtil} to access
	 * the tipi entry local service.
	 */
	public List<TipiEntry> getByDate(Date date) {
		List<TipiEntry> entries;
		try {
			entries = this.tipiEntryPersistence.findByDate(date);
		} catch (SystemException e) {
			entries = new ArrayList<TipiEntry>();
		}
		return entries;
	}

	public TipiEntry addPayment(Date date, String type, String status,
			String price) {
		try {
			List<TipiEntry> entries = TipiEntryLocalServiceUtil.getByDate(date);

			boolean alreadyHasEntryTodayForThisType = false;
			TipiEntry todayEntry = null;
			for (TipiEntry entry : entries) {
				if (entry.getType().equals(type)) {
					alreadyHasEntryTodayForThisType = true;
					todayEntry = entry;
					break;
				}
			}
			if (!alreadyHasEntryTodayForThisType) { // Création d'une nouvelle
													// entry
				long id = this.counterLocalService.increment();
				todayEntry = this.createTipiEntry(id);
				todayEntry.setDate(date);
				todayEntry.setType(type);
				todayEntry.setTotal(0);
				todayEntry.setRefusedCount(0);
				todayEntry.setPaidCount(0);
				this.addTipiEntry(todayEntry);
			}

			if (status.equals("P")) {
				todayEntry.setPaidCount(todayEntry.getPaidCount() + 1);
				Integer priceInCents = Integer.parseInt(price);
				todayEntry.setTotal(todayEntry.getTotal() + priceInCents);
			} else if (status.equals("R")) {
				todayEntry.setRefusedCount(todayEntry.getRefusedCount() + 1);
			}
			this.updateTipiEntry(todayEntry);
			return todayEntry;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}