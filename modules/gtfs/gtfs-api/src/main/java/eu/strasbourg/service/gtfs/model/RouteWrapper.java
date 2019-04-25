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

package eu.strasbourg.service.gtfs.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link Route}.
 * </p>
 *
 * @author Cedric Henry
 * @see Route
 * @generated
 */
@ProviderType
public class RouteWrapper implements Route, ModelWrapper<Route> {
	public RouteWrapper(Route route) {
		_route = route;
	}

	@Override
	public Class<?> getModelClass() {
		return Route.class;
	}

	@Override
	public String getModelClassName() {
		return Route.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("id", getId());
		attributes.put("route_id", getRoute_id());
		attributes.put("route_short_name", getRoute_short_name());
		attributes.put("route_long_name", getRoute_long_name());
		attributes.put("route_desc", getRoute_desc());
		attributes.put("route_type", getRoute_type());
		attributes.put("route_color", getRoute_color());
		attributes.put("route_text_color", getRoute_text_color());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long id = (Long)attributes.get("id");

		if (id != null) {
			setId(id);
		}

		String route_id = (String)attributes.get("route_id");

		if (route_id != null) {
			setRoute_id(route_id);
		}

		String route_short_name = (String)attributes.get("route_short_name");

		if (route_short_name != null) {
			setRoute_short_name(route_short_name);
		}

		String route_long_name = (String)attributes.get("route_long_name");

		if (route_long_name != null) {
			setRoute_long_name(route_long_name);
		}

		String route_desc = (String)attributes.get("route_desc");

		if (route_desc != null) {
			setRoute_desc(route_desc);
		}

		Integer route_type = (Integer)attributes.get("route_type");

		if (route_type != null) {
			setRoute_type(route_type);
		}

		String route_color = (String)attributes.get("route_color");

		if (route_color != null) {
			setRoute_color(route_color);
		}

		String route_text_color = (String)attributes.get("route_text_color");

		if (route_text_color != null) {
			setRoute_text_color(route_text_color);
		}
	}

	@Override
	public boolean isCachedModel() {
		return _route.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _route.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _route.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _route.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.gtfs.model.Route> toCacheModel() {
		return _route.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.gtfs.model.Route toEscapedModel() {
		return new RouteWrapper(_route.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.gtfs.model.Route toUnescapedModel() {
		return new RouteWrapper(_route.toUnescapedModel());
	}

	@Override
	public int compareTo(eu.strasbourg.service.gtfs.model.Route route) {
		return _route.compareTo(route);
	}

	/**
	* Returns the route_type of this route.
	*
	* @return the route_type of this route
	*/
	@Override
	public int getRoute_type() {
		return _route.getRoute_type();
	}

	@Override
	public int hashCode() {
		return _route.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _route.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new RouteWrapper((Route)_route.clone());
	}

	/**
	* Returns the route_color of this route.
	*
	* @return the route_color of this route
	*/
	@Override
	public java.lang.String getRoute_color() {
		return _route.getRoute_color();
	}

	/**
	* Returns the route_desc of this route.
	*
	* @return the route_desc of this route
	*/
	@Override
	public java.lang.String getRoute_desc() {
		return _route.getRoute_desc();
	}

	/**
	* Returns the route_id of this route.
	*
	* @return the route_id of this route
	*/
	@Override
	public java.lang.String getRoute_id() {
		return _route.getRoute_id();
	}

	/**
	* Returns the route_long_name of this route.
	*
	* @return the route_long_name of this route
	*/
	@Override
	public java.lang.String getRoute_long_name() {
		return _route.getRoute_long_name();
	}

	/**
	* Returns the route_short_name of this route.
	*
	* @return the route_short_name of this route
	*/
	@Override
	public java.lang.String getRoute_short_name() {
		return _route.getRoute_short_name();
	}

	/**
	* Returns the route_text_color of this route.
	*
	* @return the route_text_color of this route
	*/
	@Override
	public java.lang.String getRoute_text_color() {
		return _route.getRoute_text_color();
	}

	/**
	* Returns the uuid of this route.
	*
	* @return the uuid of this route
	*/
	@Override
	public java.lang.String getUuid() {
		return _route.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _route.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _route.toXmlString();
	}

	/**
	* Returns the ID of this route.
	*
	* @return the ID of this route
	*/
	@Override
	public long getId() {
		return _route.getId();
	}

	/**
	* Returns the primary key of this route.
	*
	* @return the primary key of this route
	*/
	@Override
	public long getPrimaryKey() {
		return _route.getPrimaryKey();
	}

	@Override
	public void persist() {
		_route.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_route.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_route.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_route.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_route.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the ID of this route.
	*
	* @param id the ID of this route
	*/
	@Override
	public void setId(long id) {
		_route.setId(id);
	}

	@Override
	public void setNew(boolean n) {
		_route.setNew(n);
	}

	/**
	* Sets the primary key of this route.
	*
	* @param primaryKey the primary key of this route
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_route.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_route.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the route_color of this route.
	*
	* @param route_color the route_color of this route
	*/
	@Override
	public void setRoute_color(java.lang.String route_color) {
		_route.setRoute_color(route_color);
	}

	/**
	* Sets the route_desc of this route.
	*
	* @param route_desc the route_desc of this route
	*/
	@Override
	public void setRoute_desc(java.lang.String route_desc) {
		_route.setRoute_desc(route_desc);
	}

	/**
	* Sets the route_id of this route.
	*
	* @param route_id the route_id of this route
	*/
	@Override
	public void setRoute_id(java.lang.String route_id) {
		_route.setRoute_id(route_id);
	}

	/**
	* Sets the route_long_name of this route.
	*
	* @param route_long_name the route_long_name of this route
	*/
	@Override
	public void setRoute_long_name(java.lang.String route_long_name) {
		_route.setRoute_long_name(route_long_name);
	}

	/**
	* Sets the route_short_name of this route.
	*
	* @param route_short_name the route_short_name of this route
	*/
	@Override
	public void setRoute_short_name(java.lang.String route_short_name) {
		_route.setRoute_short_name(route_short_name);
	}

	/**
	* Sets the route_text_color of this route.
	*
	* @param route_text_color the route_text_color of this route
	*/
	@Override
	public void setRoute_text_color(java.lang.String route_text_color) {
		_route.setRoute_text_color(route_text_color);
	}

	/**
	* Sets the route_type of this route.
	*
	* @param route_type the route_type of this route
	*/
	@Override
	public void setRoute_type(int route_type) {
		_route.setRoute_type(route_type);
	}

	/**
	* Sets the uuid of this route.
	*
	* @param uuid the uuid of this route
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_route.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RouteWrapper)) {
			return false;
		}

		RouteWrapper routeWrapper = (RouteWrapper)obj;

		if (Objects.equals(_route, routeWrapper._route)) {
			return true;
		}

		return false;
	}

	@Override
	public Route getWrappedModel() {
		return _route;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _route.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _route.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_route.resetOriginalValues();
	}

	private final Route _route;
}