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

package eu.strasbourg.service.council.model;

import aQute.bnd.annotation.ProviderType;

import eu.strasbourg.service.council.service.persistence.OfficialTypeCouncilPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class OfficialTypeCouncilSoap implements Serializable {
	public static OfficialTypeCouncilSoap toSoapModel(OfficialTypeCouncil model) {
		OfficialTypeCouncilSoap soapModel = new OfficialTypeCouncilSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setOfficialId(model.getOfficialId());
		soapModel.setTypeId(model.getTypeId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setResult(model.getResult());

		return soapModel;
	}

	public static OfficialTypeCouncilSoap[] toSoapModels(
		OfficialTypeCouncil[] models) {
		OfficialTypeCouncilSoap[] soapModels = new OfficialTypeCouncilSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static OfficialTypeCouncilSoap[][] toSoapModels(
		OfficialTypeCouncil[][] models) {
		OfficialTypeCouncilSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new OfficialTypeCouncilSoap[models.length][models[0].length];
		}
		else {
			soapModels = new OfficialTypeCouncilSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static OfficialTypeCouncilSoap[] toSoapModels(
		List<OfficialTypeCouncil> models) {
		List<OfficialTypeCouncilSoap> soapModels = new ArrayList<OfficialTypeCouncilSoap>(models.size());

		for (OfficialTypeCouncil model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new OfficialTypeCouncilSoap[soapModels.size()]);
	}

	public OfficialTypeCouncilSoap() {
	}

	public OfficialTypeCouncilPK getPrimaryKey() {
		return new OfficialTypeCouncilPK(_officialId, _typeId);
	}

	public void setPrimaryKey(OfficialTypeCouncilPK pk) {
		setOfficialId(pk.officialId);
		setTypeId(pk.typeId);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getOfficialId() {
		return _officialId;
	}

	public void setOfficialId(long officialId) {
		_officialId = officialId;
	}

	public long getTypeId() {
		return _typeId;
	}

	public void setTypeId(long typeId) {
		_typeId = typeId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public String getResult() {
		return _result;
	}

	public void setResult(String result) {
		_result = result;
	}

	private String _uuid;
	private long _officialId;
	private long _typeId;
	private long _groupId;
	private long _companyId;
	private Date _createDate;
	private String _result;
}