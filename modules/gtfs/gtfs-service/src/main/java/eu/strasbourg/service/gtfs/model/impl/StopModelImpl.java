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

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import eu.strasbourg.service.gtfs.model.Stop;
import eu.strasbourg.service.gtfs.model.StopModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the Stop service. Represents a row in the &quot;gtfs_Stop&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link StopModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link StopImpl}.
 * </p>
 *
 * @author Cedric Henry
 * @see StopImpl
 * @see Stop
 * @see StopModel
 * @generated
 */
@ProviderType
public class StopModelImpl extends BaseModelImpl<Stop> implements StopModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a stop model instance should use the {@link Stop} interface instead.
	 */
	public static final String TABLE_NAME = "gtfs_Stop";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "id_", Types.BIGINT },
			{ "stop_id", Types.VARCHAR },
			{ "stop_code", Types.VARCHAR },
			{ "stop_lat", Types.VARCHAR },
			{ "stop_lon", Types.VARCHAR },
			{ "stop_name", Types.VARCHAR },
			{ "stop_url", Types.VARCHAR },
			{ "stop_desc", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("id_", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("stop_id", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("stop_code", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("stop_lat", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("stop_lon", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("stop_name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("stop_url", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("stop_desc", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table gtfs_Stop (uuid_ VARCHAR(75) null,id_ LONG not null primary key,stop_id VARCHAR(75) null,stop_code VARCHAR(75) null,stop_lat VARCHAR(75) null,stop_lon VARCHAR(75) null,stop_name VARCHAR(75) null,stop_url VARCHAR(400) null,stop_desc VARCHAR(400) null)";
	public static final String TABLE_SQL_DROP = "drop table gtfs_Stop";
	public static final String ORDER_BY_JPQL = " ORDER BY stop.stop_id ASC";
	public static final String ORDER_BY_SQL = " ORDER BY gtfs_Stop.stop_id ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(eu.strasbourg.service.gtfs.service.util.PropsUtil.get(
				"value.object.entity.cache.enabled.eu.strasbourg.service.gtfs.model.Stop"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(eu.strasbourg.service.gtfs.service.util.PropsUtil.get(
				"value.object.finder.cache.enabled.eu.strasbourg.service.gtfs.model.Stop"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(eu.strasbourg.service.gtfs.service.util.PropsUtil.get(
				"value.object.column.bitmask.enabled.eu.strasbourg.service.gtfs.model.Stop"),
			true);
	public static final long STOP_ID_COLUMN_BITMASK = 1L;
	public static final long UUID_COLUMN_BITMASK = 2L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(eu.strasbourg.service.gtfs.service.util.PropsUtil.get(
				"lock.expiration.time.eu.strasbourg.service.gtfs.model.Stop"));

	public StopModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _id;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _id;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Stop.class;
	}

	@Override
	public String getModelClassName() {
		return Stop.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("id", getId());
		attributes.put("stop_id", getStop_id());
		attributes.put("stop_code", getStop_code());
		attributes.put("stop_lat", getStop_lat());
		attributes.put("stop_lon", getStop_lon());
		attributes.put("stop_name", getStop_name());
		attributes.put("stop_url", getStop_url());
		attributes.put("stop_desc", getStop_desc());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

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

		String stop_id = (String)attributes.get("stop_id");

		if (stop_id != null) {
			setStop_id(stop_id);
		}

		String stop_code = (String)attributes.get("stop_code");

		if (stop_code != null) {
			setStop_code(stop_code);
		}

		String stop_lat = (String)attributes.get("stop_lat");

		if (stop_lat != null) {
			setStop_lat(stop_lat);
		}

		String stop_lon = (String)attributes.get("stop_lon");

		if (stop_lon != null) {
			setStop_lon(stop_lon);
		}

		String stop_name = (String)attributes.get("stop_name");

		if (stop_name != null) {
			setStop_name(stop_name);
		}

		String stop_url = (String)attributes.get("stop_url");

		if (stop_url != null) {
			setStop_url(stop_url);
		}

		String stop_desc = (String)attributes.get("stop_desc");

		if (stop_desc != null) {
			setStop_desc(stop_desc);
		}
	}

	@Override
	public String getUuid() {
		if (_uuid == null) {
			return StringPool.BLANK;
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@Override
	public long getId() {
		return _id;
	}

	@Override
	public void setId(long id) {
		_id = id;
	}

	@Override
	public String getStop_id() {
		if (_stop_id == null) {
			return StringPool.BLANK;
		}
		else {
			return _stop_id;
		}
	}

	@Override
	public void setStop_id(String stop_id) {
		_columnBitmask = -1L;

		if (_originalStop_id == null) {
			_originalStop_id = _stop_id;
		}

		_stop_id = stop_id;
	}

	public String getOriginalStop_id() {
		return GetterUtil.getString(_originalStop_id);
	}

	@Override
	public String getStop_code() {
		if (_stop_code == null) {
			return StringPool.BLANK;
		}
		else {
			return _stop_code;
		}
	}

	@Override
	public void setStop_code(String stop_code) {
		_stop_code = stop_code;
	}

	@Override
	public String getStop_lat() {
		if (_stop_lat == null) {
			return StringPool.BLANK;
		}
		else {
			return _stop_lat;
		}
	}

	@Override
	public void setStop_lat(String stop_lat) {
		_stop_lat = stop_lat;
	}

	@Override
	public String getStop_lon() {
		if (_stop_lon == null) {
			return StringPool.BLANK;
		}
		else {
			return _stop_lon;
		}
	}

	@Override
	public void setStop_lon(String stop_lon) {
		_stop_lon = stop_lon;
	}

	@Override
	public String getStop_name() {
		if (_stop_name == null) {
			return StringPool.BLANK;
		}
		else {
			return _stop_name;
		}
	}

	@Override
	public void setStop_name(String stop_name) {
		_stop_name = stop_name;
	}

	@Override
	public String getStop_url() {
		if (_stop_url == null) {
			return StringPool.BLANK;
		}
		else {
			return _stop_url;
		}
	}

	@Override
	public void setStop_url(String stop_url) {
		_stop_url = stop_url;
	}

	@Override
	public String getStop_desc() {
		if (_stop_desc == null) {
			return StringPool.BLANK;
		}
		else {
			return _stop_desc;
		}
	}

	@Override
	public void setStop_desc(String stop_desc) {
		_stop_desc = stop_desc;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			Stop.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Stop toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (Stop)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		StopImpl stopImpl = new StopImpl();

		stopImpl.setUuid(getUuid());
		stopImpl.setId(getId());
		stopImpl.setStop_id(getStop_id());
		stopImpl.setStop_code(getStop_code());
		stopImpl.setStop_lat(getStop_lat());
		stopImpl.setStop_lon(getStop_lon());
		stopImpl.setStop_name(getStop_name());
		stopImpl.setStop_url(getStop_url());
		stopImpl.setStop_desc(getStop_desc());

		stopImpl.resetOriginalValues();

		return stopImpl;
	}

	@Override
	public int compareTo(Stop stop) {
		int value = 0;

		value = getStop_id().compareTo(stop.getStop_id());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Stop)) {
			return false;
		}

		Stop stop = (Stop)obj;

		long primaryKey = stop.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		StopModelImpl stopModelImpl = this;

		stopModelImpl._originalUuid = stopModelImpl._uuid;

		stopModelImpl._originalStop_id = stopModelImpl._stop_id;

		stopModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Stop> toCacheModel() {
		StopCacheModel stopCacheModel = new StopCacheModel();

		stopCacheModel.uuid = getUuid();

		String uuid = stopCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			stopCacheModel.uuid = null;
		}

		stopCacheModel.id = getId();

		stopCacheModel.stop_id = getStop_id();

		String stop_id = stopCacheModel.stop_id;

		if ((stop_id != null) && (stop_id.length() == 0)) {
			stopCacheModel.stop_id = null;
		}

		stopCacheModel.stop_code = getStop_code();

		String stop_code = stopCacheModel.stop_code;

		if ((stop_code != null) && (stop_code.length() == 0)) {
			stopCacheModel.stop_code = null;
		}

		stopCacheModel.stop_lat = getStop_lat();

		String stop_lat = stopCacheModel.stop_lat;

		if ((stop_lat != null) && (stop_lat.length() == 0)) {
			stopCacheModel.stop_lat = null;
		}

		stopCacheModel.stop_lon = getStop_lon();

		String stop_lon = stopCacheModel.stop_lon;

		if ((stop_lon != null) && (stop_lon.length() == 0)) {
			stopCacheModel.stop_lon = null;
		}

		stopCacheModel.stop_name = getStop_name();

		String stop_name = stopCacheModel.stop_name;

		if ((stop_name != null) && (stop_name.length() == 0)) {
			stopCacheModel.stop_name = null;
		}

		stopCacheModel.stop_url = getStop_url();

		String stop_url = stopCacheModel.stop_url;

		if ((stop_url != null) && (stop_url.length() == 0)) {
			stopCacheModel.stop_url = null;
		}

		stopCacheModel.stop_desc = getStop_desc();

		String stop_desc = stopCacheModel.stop_desc;

		if ((stop_desc != null) && (stop_desc.length() == 0)) {
			stopCacheModel.stop_desc = null;
		}

		return stopCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", id=");
		sb.append(getId());
		sb.append(", stop_id=");
		sb.append(getStop_id());
		sb.append(", stop_code=");
		sb.append(getStop_code());
		sb.append(", stop_lat=");
		sb.append(getStop_lat());
		sb.append(", stop_lon=");
		sb.append(getStop_lon());
		sb.append(", stop_name=");
		sb.append(getStop_name());
		sb.append(", stop_url=");
		sb.append(getStop_url());
		sb.append(", stop_desc=");
		sb.append(getStop_desc());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(31);

		sb.append("<model><model-name>");
		sb.append("eu.strasbourg.service.gtfs.model.Stop");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>id</column-name><column-value><![CDATA[");
		sb.append(getId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>stop_id</column-name><column-value><![CDATA[");
		sb.append(getStop_id());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>stop_code</column-name><column-value><![CDATA[");
		sb.append(getStop_code());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>stop_lat</column-name><column-value><![CDATA[");
		sb.append(getStop_lat());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>stop_lon</column-name><column-value><![CDATA[");
		sb.append(getStop_lon());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>stop_name</column-name><column-value><![CDATA[");
		sb.append(getStop_name());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>stop_url</column-name><column-value><![CDATA[");
		sb.append(getStop_url());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>stop_desc</column-name><column-value><![CDATA[");
		sb.append(getStop_desc());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = Stop.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			Stop.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _id;
	private String _stop_id;
	private String _originalStop_id;
	private String _stop_code;
	private String _stop_lat;
	private String _stop_lon;
	private String _stop_name;
	private String _stop_url;
	private String _stop_desc;
	private long _columnBitmask;
	private Stop _escapedModel;
}