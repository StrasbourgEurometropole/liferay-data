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

create table gtfs_Route (
	uuid_ VARCHAR(75) null,
	id_ LONG not null primary key,
	route_id VARCHAR(75) null,
	route_short_name VARCHAR(75) null,
	route_long_name VARCHAR(75) null,
	route_desc VARCHAR(75) null,
	route_type INTEGER,
	route_color VARCHAR(75) null,
	route_text_color VARCHAR(75) null
);

create table gtfs_Stop (
	uuid_ VARCHAR(75) null,
	id_ LONG not null,
	stop_id VARCHAR(75) not null,
	stop_code VARCHAR(75) null,
	stop_lat LONG,
	stop_lon LONG,
	stop_name VARCHAR(75) null,
	stop_url VARCHAR(75) null,
	stop_desc VARCHAR(75) null,
	primary key (id_, stop_id)
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
	block_id INTEGER
);