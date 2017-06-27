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

package eu.strasbourg.service.tipi.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link eu.strasbourg.service.tipi.service.http.TipiEntryServiceSoap}.
 *
 * @author Angelique Zunino Champougny
 * @see eu.strasbourg.service.tipi.service.http.TipiEntryServiceSoap
 * @generated
 */
@ProviderType
public class TipiEntrySoap implements Serializable {
	public static TipiEntrySoap toSoapModel(TipiEntry model) {
		TipiEntrySoap soapModel = new TipiEntrySoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setId(model.getId());
		soapModel.setDate(model.getDate());
		soapModel.setTotal(model.getTotal());
		soapModel.setPaidCount(model.getPaidCount());
		soapModel.setRefusedCount(model.getRefusedCount());
		soapModel.setCanceledCount(model.getCanceledCount());
		soapModel.setType(model.getType());

		return soapModel;
	}

	public static TipiEntrySoap[] toSoapModels(TipiEntry[] models) {
		TipiEntrySoap[] soapModels = new TipiEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TipiEntrySoap[][] toSoapModels(TipiEntry[][] models) {
		TipiEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new TipiEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new TipiEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TipiEntrySoap[] toSoapModels(List<TipiEntry> models) {
		List<TipiEntrySoap> soapModels = new ArrayList<TipiEntrySoap>(models.size());

		for (TipiEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TipiEntrySoap[soapModels.size()]);
	}

	public TipiEntrySoap() {
	}

	public long getPrimaryKey() {
		return _id;
	}

	public void setPrimaryKey(long pk) {
		setId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getId() {
		return _id;
	}

	public void setId(long id) {
		_id = id;
	}

	public Date getDate() {
		return _date;
	}

	public void setDate(Date date) {
		_date = date;
	}

	public int getTotal() {
		return _total;
	}

	public void setTotal(int total) {
		_total = total;
	}

	public int getPaidCount() {
		return _paidCount;
	}

	public void setPaidCount(int paidCount) {
		_paidCount = paidCount;
	}

	public int getRefusedCount() {
		return _refusedCount;
	}

	public void setRefusedCount(int refusedCount) {
		_refusedCount = refusedCount;
	}

	public int getCanceledCount() {
		return _canceledCount;
	}

	public void setCanceledCount(int canceledCount) {
		_canceledCount = canceledCount;
	}

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;
	}

	private String _uuid;
	private long _id;
	private Date _date;
	private int _total;
	private int _paidCount;
	private int _refusedCount;
	private int _canceledCount;
	private String _type;
}