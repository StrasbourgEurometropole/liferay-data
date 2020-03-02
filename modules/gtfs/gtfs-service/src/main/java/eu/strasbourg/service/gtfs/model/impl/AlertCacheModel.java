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

package eu.strasbourg.service.gtfs.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import eu.strasbourg.service.gtfs.model.Alert;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Alert in entity cache.
 *
 * @author Cedric Henry
 * @see Alert
 * @generated
 */
@ProviderType
public class AlertCacheModel implements CacheModel<Alert>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AlertCacheModel)) {
			return false;
		}

		AlertCacheModel alertCacheModel = (AlertCacheModel)obj;

		if (alertId == alertCacheModel.alertId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, alertId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", alertId=");
		sb.append(alertId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", arretId=");
		sb.append(arretId);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append(", ligneAndDirection=");
		sb.append(ligneAndDirection);
		sb.append(", perturbation=");
		sb.append(perturbation);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Alert toEntityModel() {
		AlertImpl alertImpl = new AlertImpl();

		if (uuid == null) {
			alertImpl.setUuid(StringPool.BLANK);
		}
		else {
			alertImpl.setUuid(uuid);
		}

		alertImpl.setAlertId(alertId);
		alertImpl.setGroupId(groupId);
		alertImpl.setCompanyId(companyId);
		alertImpl.setArretId(arretId);

		if (startDate == Long.MIN_VALUE) {
			alertImpl.setStartDate(null);
		}
		else {
			alertImpl.setStartDate(new Date(startDate));
		}

		if (endDate == Long.MIN_VALUE) {
			alertImpl.setEndDate(null);
		}
		else {
			alertImpl.setEndDate(new Date(endDate));
		}

		if (ligneAndDirection == null) {
			alertImpl.setLigneAndDirection(StringPool.BLANK);
		}
		else {
			alertImpl.setLigneAndDirection(ligneAndDirection);
		}

		if (perturbation == null) {
			alertImpl.setPerturbation(StringPool.BLANK);
		}
		else {
			alertImpl.setPerturbation(perturbation);
		}

		alertImpl.resetOriginalValues();

		return alertImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		alertId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		arretId = objectInput.readLong();
		startDate = objectInput.readLong();
		endDate = objectInput.readLong();
		ligneAndDirection = objectInput.readUTF();
		perturbation = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(alertId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(arretId);
		objectOutput.writeLong(startDate);
		objectOutput.writeLong(endDate);

		if (ligneAndDirection == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(ligneAndDirection);
		}

		if (perturbation == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(perturbation);
		}
	}

	public String uuid;
	public long alertId;
	public long groupId;
	public long companyId;
	public long arretId;
	public long startDate;
	public long endDate;
	public String ligneAndDirection;
	public String perturbation;
}