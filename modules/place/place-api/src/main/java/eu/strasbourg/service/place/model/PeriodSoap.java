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

package eu.strasbourg.service.place.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Angelique Zunino Champougny
 * @generated
 */
@ProviderType
public class PeriodSoap implements Serializable {
	public static PeriodSoap toSoapModel(Period model) {
		PeriodSoap soapModel = new PeriodSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setPeriodId(model.getPeriodId());
		soapModel.setName(model.getName());
		soapModel.setDefaultPeriod(model.getDefaultPeriod());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setEndDate(model.getEndDate());
		soapModel.setLinkLabel(model.getLinkLabel());
		soapModel.setLinkURL(model.getLinkURL());
		soapModel.setAlwaysOpen(model.getAlwaysOpen());
		soapModel.setRTGreenThreshold(model.getRTGreenThreshold());
		soapModel.setRTOrangeThreshold(model.getRTOrangeThreshold());
		soapModel.setRTRedThreshold(model.getRTRedThreshold());
		soapModel.setRTMaxThreshold(model.getRTMaxThreshold());
		soapModel.setPlaceId(model.getPlaceId());

		return soapModel;
	}

	public static PeriodSoap[] toSoapModels(Period[] models) {
		PeriodSoap[] soapModels = new PeriodSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PeriodSoap[][] toSoapModels(Period[][] models) {
		PeriodSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new PeriodSoap[models.length][models[0].length];
		}
		else {
			soapModels = new PeriodSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PeriodSoap[] toSoapModels(List<Period> models) {
		List<PeriodSoap> soapModels = new ArrayList<PeriodSoap>(models.size());

		for (Period model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PeriodSoap[soapModels.size()]);
	}

	public PeriodSoap() {
	}

	public long getPrimaryKey() {
		return _periodId;
	}

	public void setPrimaryKey(long pk) {
		setPeriodId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getPeriodId() {
		return _periodId;
	}

	public void setPeriodId(long periodId) {
		_periodId = periodId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public Boolean getDefaultPeriod() {
		return _defaultPeriod;
	}

	public void setDefaultPeriod(Boolean defaultPeriod) {
		_defaultPeriod = defaultPeriod;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	public Date getEndDate() {
		return _endDate;
	}

	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	public String getLinkLabel() {
		return _linkLabel;
	}

	public void setLinkLabel(String linkLabel) {
		_linkLabel = linkLabel;
	}

	public String getLinkURL() {
		return _linkURL;
	}

	public void setLinkURL(String linkURL) {
		_linkURL = linkURL;
	}

	public Boolean getAlwaysOpen() {
		return _alwaysOpen;
	}

	public void setAlwaysOpen(Boolean alwaysOpen) {
		_alwaysOpen = alwaysOpen;
	}

	public long getRTGreenThreshold() {
		return _RTGreenThreshold;
	}

	public void setRTGreenThreshold(long RTGreenThreshold) {
		_RTGreenThreshold = RTGreenThreshold;
	}

	public long getRTOrangeThreshold() {
		return _RTOrangeThreshold;
	}

	public void setRTOrangeThreshold(long RTOrangeThreshold) {
		_RTOrangeThreshold = RTOrangeThreshold;
	}

	public long getRTRedThreshold() {
		return _RTRedThreshold;
	}

	public void setRTRedThreshold(long RTRedThreshold) {
		_RTRedThreshold = RTRedThreshold;
	}

	public long getRTMaxThreshold() {
		return _RTMaxThreshold;
	}

	public void setRTMaxThreshold(long RTMaxThreshold) {
		_RTMaxThreshold = RTMaxThreshold;
	}

	public long getPlaceId() {
		return _placeId;
	}

	public void setPlaceId(long placeId) {
		_placeId = placeId;
	}

	private String _uuid;
	private long _periodId;
	private String _name;
	private Boolean _defaultPeriod;
	private Date _startDate;
	private Date _endDate;
	private String _linkLabel;
	private String _linkURL;
	private Boolean _alwaysOpen;
	private long _RTGreenThreshold;
	private long _RTOrangeThreshold;
	private long _RTRedThreshold;
	private long _RTMaxThreshold;
	private long _placeId;
}