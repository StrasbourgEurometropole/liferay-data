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

package eu.strasbourg.service.csmap.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class ThematicSoap implements Serializable {

	public static ThematicSoap toSoapModel(Thematic model) {
		ThematicSoap soapModel = new ThematicSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setThematicId(model.getThematicId());
		soapModel.setName(model.getName());
		soapModel.setFavorite(model.getFavorite());
		soapModel.setTopics(model.getTopics());

		return soapModel;
	}

	public static ThematicSoap[] toSoapModels(Thematic[] models) {
		ThematicSoap[] soapModels = new ThematicSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ThematicSoap[][] toSoapModels(Thematic[][] models) {
		ThematicSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ThematicSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ThematicSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ThematicSoap[] toSoapModels(List<Thematic> models) {
		List<ThematicSoap> soapModels = new ArrayList<ThematicSoap>(
			models.size());

		for (Thematic model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ThematicSoap[soapModels.size()]);
	}

	public ThematicSoap() {
	}

	public long getPrimaryKey() {
		return _thematicId;
	}

	public void setPrimaryKey(long pk) {
		setThematicId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getThematicId() {
		return _thematicId;
	}

	public void setThematicId(long thematicId) {
		_thematicId = thematicId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getFavorite() {
		return _favorite;
	}

	public void setFavorite(String favorite) {
		_favorite = favorite;
	}

	public String getTopics() {
		return _topics;
	}

	public void setTopics(String topics) {
		_topics = topics;
	}

	private String _uuid;
	private long _thematicId;
	private String _name;
	private String _favorite;
	private String _topics;

}