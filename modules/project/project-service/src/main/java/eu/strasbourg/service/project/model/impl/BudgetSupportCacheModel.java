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

package eu.strasbourg.service.project.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import eu.strasbourg.service.project.model.BudgetSupport;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing BudgetSupport in entity cache.
 *
 * @author Cedric Henry
 * @see BudgetSupport
 * @generated
 */
@ProviderType
public class BudgetSupportCacheModel implements CacheModel<BudgetSupport>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BudgetSupportCacheModel)) {
			return false;
		}

		BudgetSupportCacheModel budgetSupportCacheModel = (BudgetSupportCacheModel)obj;

		if (budgetSupportId == budgetSupportCacheModel.budgetSupportId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, budgetSupportId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(43);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", budgetSupportId=");
		sb.append(budgetSupportId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", status=");
		sb.append(status);
		sb.append(", statusByUserId=");
		sb.append(statusByUserId);
		sb.append(", statusByUserName=");
		sb.append(statusByUserName);
		sb.append(", statusDate=");
		sb.append(statusDate);
		sb.append(", citoyenLastName=");
		sb.append(citoyenLastName);
		sb.append(", citoyenFirstname=");
		sb.append(citoyenFirstname);
		sb.append(", citoyenBirthday=");
		sb.append(citoyenBirthday);
		sb.append(", citoyenAddress=");
		sb.append(citoyenAddress);
		sb.append(", citoyenMail=");
		sb.append(citoyenMail);
		sb.append(", citoyenPostalCode=");
		sb.append(citoyenPostalCode);
		sb.append(", citoyenMobilePhone=");
		sb.append(citoyenMobilePhone);
		sb.append(", citoyenPhone=");
		sb.append(citoyenPhone);
		sb.append(", citoyenCity=");
		sb.append(citoyenCity);
		sb.append(", publikUserId=");
		sb.append(publikUserId);
		sb.append(", budgetParticipatifId=");
		sb.append(budgetParticipatifId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public BudgetSupport toEntityModel() {
		BudgetSupportImpl budgetSupportImpl = new BudgetSupportImpl();

		if (uuid == null) {
			budgetSupportImpl.setUuid(StringPool.BLANK);
		}
		else {
			budgetSupportImpl.setUuid(uuid);
		}

		budgetSupportImpl.setBudgetSupportId(budgetSupportId);
		budgetSupportImpl.setGroupId(groupId);
		budgetSupportImpl.setCompanyId(companyId);

		if (createDate == Long.MIN_VALUE) {
			budgetSupportImpl.setCreateDate(null);
		}
		else {
			budgetSupportImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			budgetSupportImpl.setModifiedDate(null);
		}
		else {
			budgetSupportImpl.setModifiedDate(new Date(modifiedDate));
		}

		budgetSupportImpl.setStatus(status);
		budgetSupportImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			budgetSupportImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			budgetSupportImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			budgetSupportImpl.setStatusDate(null);
		}
		else {
			budgetSupportImpl.setStatusDate(new Date(statusDate));
		}

		if (citoyenLastName == null) {
			budgetSupportImpl.setCitoyenLastName(StringPool.BLANK);
		}
		else {
			budgetSupportImpl.setCitoyenLastName(citoyenLastName);
		}

		if (citoyenFirstname == null) {
			budgetSupportImpl.setCitoyenFirstname(StringPool.BLANK);
		}
		else {
			budgetSupportImpl.setCitoyenFirstname(citoyenFirstname);
		}

		if (citoyenBirthday == Long.MIN_VALUE) {
			budgetSupportImpl.setCitoyenBirthday(null);
		}
		else {
			budgetSupportImpl.setCitoyenBirthday(new Date(citoyenBirthday));
		}

		if (citoyenAddress == null) {
			budgetSupportImpl.setCitoyenAddress(StringPool.BLANK);
		}
		else {
			budgetSupportImpl.setCitoyenAddress(citoyenAddress);
		}

		if (citoyenMail == null) {
			budgetSupportImpl.setCitoyenMail(StringPool.BLANK);
		}
		else {
			budgetSupportImpl.setCitoyenMail(citoyenMail);
		}

		budgetSupportImpl.setCitoyenPostalCode(citoyenPostalCode);

		if (citoyenMobilePhone == null) {
			budgetSupportImpl.setCitoyenMobilePhone(StringPool.BLANK);
		}
		else {
			budgetSupportImpl.setCitoyenMobilePhone(citoyenMobilePhone);
		}

		if (citoyenPhone == null) {
			budgetSupportImpl.setCitoyenPhone(StringPool.BLANK);
		}
		else {
			budgetSupportImpl.setCitoyenPhone(citoyenPhone);
		}

		if (citoyenCity == null) {
			budgetSupportImpl.setCitoyenCity(StringPool.BLANK);
		}
		else {
			budgetSupportImpl.setCitoyenCity(citoyenCity);
		}

		if (publikUserId == null) {
			budgetSupportImpl.setPublikUserId(StringPool.BLANK);
		}
		else {
			budgetSupportImpl.setPublikUserId(publikUserId);
		}

		budgetSupportImpl.setBudgetParticipatifId(budgetParticipatifId);

		budgetSupportImpl.resetOriginalValues();

		return budgetSupportImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		budgetSupportId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		status = objectInput.readInt();

		statusByUserId = objectInput.readLong();
		statusByUserName = objectInput.readUTF();
		statusDate = objectInput.readLong();
		citoyenLastName = objectInput.readUTF();
		citoyenFirstname = objectInput.readUTF();
		citoyenBirthday = objectInput.readLong();
		citoyenAddress = objectInput.readUTF();
		citoyenMail = objectInput.readUTF();

		citoyenPostalCode = objectInput.readLong();
		citoyenMobilePhone = objectInput.readUTF();
		citoyenPhone = objectInput.readUTF();
		citoyenCity = objectInput.readUTF();
		publikUserId = objectInput.readUTF();

		budgetParticipatifId = objectInput.readLong();
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

		objectOutput.writeLong(budgetSupportId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeInt(status);

		objectOutput.writeLong(statusByUserId);

		if (statusByUserName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(statusByUserName);
		}

		objectOutput.writeLong(statusDate);

		if (citoyenLastName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(citoyenLastName);
		}

		if (citoyenFirstname == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(citoyenFirstname);
		}

		objectOutput.writeLong(citoyenBirthday);

		if (citoyenAddress == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(citoyenAddress);
		}

		if (citoyenMail == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(citoyenMail);
		}

		objectOutput.writeLong(citoyenPostalCode);

		if (citoyenMobilePhone == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(citoyenMobilePhone);
		}

		if (citoyenPhone == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(citoyenPhone);
		}

		if (citoyenCity == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(citoyenCity);
		}

		if (publikUserId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(publikUserId);
		}

		objectOutput.writeLong(budgetParticipatifId);
	}

	public String uuid;
	public long budgetSupportId;
	public long groupId;
	public long companyId;
	public long createDate;
	public long modifiedDate;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;
	public String citoyenLastName;
	public String citoyenFirstname;
	public long citoyenBirthday;
	public String citoyenAddress;
	public String citoyenMail;
	public long citoyenPostalCode;
	public String citoyenMobilePhone;
	public String citoyenPhone;
	public String citoyenCity;
	public String publikUserId;
	public long budgetParticipatifId;
}