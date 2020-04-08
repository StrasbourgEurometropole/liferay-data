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

import eu.strasbourg.service.gtfs.model.Route;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Route in entity cache.
 *
 * @author Cedric Henry
 * @see Route
 * @generated
 */
@ProviderType
public class RouteCacheModel implements CacheModel<Route>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RouteCacheModel)) {
			return false;
		}

		RouteCacheModel routeCacheModel = (RouteCacheModel)obj;

		if (id == routeCacheModel.id) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, id);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", id=");
		sb.append(id);
		sb.append(", route_id=");
		sb.append(route_id);
		sb.append(", route_short_name=");
		sb.append(route_short_name);
		sb.append(", route_long_name=");
		sb.append(route_long_name);
		sb.append(", route_desc=");
		sb.append(route_desc);
		sb.append(", route_type=");
		sb.append(route_type);
		sb.append(", route_color=");
		sb.append(route_color);
		sb.append(", route_text_color=");
		sb.append(route_text_color);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Route toEntityModel() {
		RouteImpl routeImpl = new RouteImpl();

		if (uuid == null) {
			routeImpl.setUuid(StringPool.BLANK);
		}
		else {
			routeImpl.setUuid(uuid);
		}

		routeImpl.setId(id);

		if (route_id == null) {
			routeImpl.setRoute_id(StringPool.BLANK);
		}
		else {
			routeImpl.setRoute_id(route_id);
		}

		if (route_short_name == null) {
			routeImpl.setRoute_short_name(StringPool.BLANK);
		}
		else {
			routeImpl.setRoute_short_name(route_short_name);
		}

		if (route_long_name == null) {
			routeImpl.setRoute_long_name(StringPool.BLANK);
		}
		else {
			routeImpl.setRoute_long_name(route_long_name);
		}

		if (route_desc == null) {
			routeImpl.setRoute_desc(StringPool.BLANK);
		}
		else {
			routeImpl.setRoute_desc(route_desc);
		}

		routeImpl.setRoute_type(route_type);

		if (route_color == null) {
			routeImpl.setRoute_color(StringPool.BLANK);
		}
		else {
			routeImpl.setRoute_color(route_color);
		}

		if (route_text_color == null) {
			routeImpl.setRoute_text_color(StringPool.BLANK);
		}
		else {
			routeImpl.setRoute_text_color(route_text_color);
		}

		routeImpl.resetOriginalValues();

		return routeImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		id = objectInput.readLong();
		route_id = objectInput.readUTF();
		route_short_name = objectInput.readUTF();
		route_long_name = objectInput.readUTF();
		route_desc = objectInput.readUTF();

		route_type = objectInput.readInt();
		route_color = objectInput.readUTF();
		route_text_color = objectInput.readUTF();
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

		objectOutput.writeLong(id);

		if (route_id == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(route_id);
		}

		if (route_short_name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(route_short_name);
		}

		if (route_long_name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(route_long_name);
		}

		if (route_desc == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(route_desc);
		}

		objectOutput.writeInt(route_type);

		if (route_color == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(route_color);
		}

		if (route_text_color == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(route_text_color);
		}
	}

	public String uuid;
	public long id;
	public String route_id;
	public String route_short_name;
	public String route_long_name;
	public String route_desc;
	public int route_type;
	public String route_color;
	public String route_text_color;
}