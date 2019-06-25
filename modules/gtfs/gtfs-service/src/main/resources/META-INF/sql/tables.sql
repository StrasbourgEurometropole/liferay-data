create table gtfs_Agency (
	uuid_ VARCHAR(75) null,
	id_ LONG not null primary key,
	agency_name VARCHAR(75) null,
	agency_url VARCHAR(400) null,
	agency_timezone VARCHAR(75) null,
	agency_phone VARCHAR(75) null,
	agency_fare_url VARCHAR(400) null,
	agency_lang VARCHAR(75) null
);

create table gtfs_Arret (
	uuid_ VARCHAR(75) null,
	arretId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null,
	stopId VARCHAR(75) null,
	title VARCHAR(75) null,
	code_ VARCHAR(75) null,
	latitude VARCHAR(75) null,
	longitude VARCHAR(75) null,
	type_ VARCHAR(75) null
);

create table gtfs_Calendar (
	uuid_ VARCHAR(75) null,
	id_ LONG not null primary key,
	service_id VARCHAR(75) null,
	monday BOOLEAN,
	tuesday BOOLEAN,
	wednesday BOOLEAN,
	thursday BOOLEAN,
	friday BOOLEAN,
	saturday BOOLEAN,
	sunday BOOLEAN,
	start_date DATE null,
	end_date DATE null
);

create table gtfs_CalendarDate (
	uuid_ VARCHAR(75) null,
	id_ LONG not null primary key,
	service_id VARCHAR(75) null,
	date_ DATE null,
	exception_type INTEGER
);

create table gtfs_Direction (
	uuid_ VARCHAR(75) null,
	directionId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	stopId VARCHAR(75) null,
	routeId VARCHAR(75) null,
	destinationName VARCHAR(75) null
);

create table gtfs_ImportHistoric (
	uuid_ VARCHAR(75) null,
	importHistoricId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null,
	result INTEGER,
	operations VARCHAR(75) null,
	errorDescription VARCHAR(75) null,
	errorStackTrace VARCHAR(75) null
);

create table gtfs_Ligne (
	uuid_ VARCHAR(75) null,
	ligneId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null,
	routeId VARCHAR(75) null,
	shortName VARCHAR(75) null,
	title VARCHAR(75) null,
	type_ VARCHAR(75) null,
	textColor VARCHAR(75) null
);

create table gtfs_Route (
	uuid_ VARCHAR(75) null,
	id_ LONG not null primary key,
	route_id VARCHAR(75) null,
	route_short_name VARCHAR(75) null,
	route_long_name VARCHAR(200) null,
	route_desc VARCHAR(400) null,
	route_type INTEGER,
	route_color VARCHAR(75) null,
	route_text_color VARCHAR(75) null
);

create table gtfs_Stop (
	uuid_ VARCHAR(75) null,
	id_ LONG not null primary key,
	stop_id VARCHAR(75) null,
	stop_code VARCHAR(75) null,
	stop_lat VARCHAR(75) null,
	stop_lon VARCHAR(75) null,
	stop_name VARCHAR(75) null,
	stop_url VARCHAR(400) null,
	stop_desc VARCHAR(400) null
);

create table gtfs_StopTime (
	uuid_ VARCHAR(75) null,
	id_ LONG not null primary key,
	trip_id VARCHAR(75) null,
	arrival_time DATE null,
	departure_time DATE null,
	stop_id VARCHAR(75) null,
	stop_sequence INTEGER,
	pickup_type VARCHAR(75) null,
	drop_off_type VARCHAR(75) null
);

create table gtfs_Trip (
	uuid_ VARCHAR(75) null,
	id_ LONG not null primary key,
	route_id VARCHAR(75) null,
	service_id VARCHAR(75) null,
	trip_id VARCHAR(75) null,
	trip_headsign VARCHAR(75) null,
	direction_id BOOLEAN,
	block_id VARCHAR(75) null
);