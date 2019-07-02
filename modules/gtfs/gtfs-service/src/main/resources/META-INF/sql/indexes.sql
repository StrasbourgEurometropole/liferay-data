create index IX_899580E4 on gtfs_Agency (uuid_[$COLUMN_LENGTH:75$]);

create index IX_19AE1219 on gtfs_Arret (groupId);
create index IX_DD7BF9A2 on gtfs_Arret (stopId[$COLUMN_LENGTH:75$]);
create index IX_C8A9165 on gtfs_Arret (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_35E22D27 on gtfs_Arret (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_5FE52E4F on gtfs_Calendar (end_date);
create index IX_308C9122 on gtfs_Calendar (service_id[$COLUMN_LENGTH:75$]);
create index IX_1812628 on gtfs_Calendar (start_date);
create index IX_7EB2170B on gtfs_Calendar (uuid_[$COLUMN_LENGTH:75$]);

create index IX_4CFF7FAA on gtfs_CalendarDate (date_);
create index IX_E2F05AF0 on gtfs_CalendarDate (service_id[$COLUMN_LENGTH:75$]);
create index IX_F1CEE47D on gtfs_CalendarDate (uuid_[$COLUMN_LENGTH:75$]);

create index IX_82EDF8A on gtfs_Direction (groupId);
create index IX_6A91A654 on gtfs_Direction (routeId[$COLUMN_LENGTH:75$]);
create index IX_BBE33A11 on gtfs_Direction (stopId[$COLUMN_LENGTH:75$]);
create index IX_AFBADCB4 on gtfs_Direction (tripId[$COLUMN_LENGTH:75$]);
create index IX_120B5314 on gtfs_Direction (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_22B58E96 on gtfs_Direction (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_6BBD685B on gtfs_ImportHistoric (groupId);
create index IX_424B2563 on gtfs_ImportHistoric (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_B213A8A5 on gtfs_ImportHistoric (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_DCE6BE88 on gtfs_Ligne (groupId);
create index IX_3F498552 on gtfs_Ligne (routeId[$COLUMN_LENGTH:75$]);
create index IX_AC2B71D6 on gtfs_Ligne (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_387275D8 on gtfs_Ligne (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_DB93BEF on gtfs_Route (route_id[$COLUMN_LENGTH:75$]);
create index IX_81826F8A on gtfs_Route (uuid_[$COLUMN_LENGTH:75$]);

create index IX_3E9D3E4D on gtfs_Stop (stop_code[$COLUMN_LENGTH:75$]);
create index IX_13414D7B on gtfs_Stop (stop_id[$COLUMN_LENGTH:75$]);
create index IX_B1800C47 on gtfs_Stop (uuid_[$COLUMN_LENGTH:75$]);

create index IX_8402CBEE on gtfs_StopTime (stop_id[$COLUMN_LENGTH:75$]);
create index IX_B1F7DAB on gtfs_StopTime (trip_id[$COLUMN_LENGTH:75$]);
create index IX_89648B7A on gtfs_StopTime (uuid_[$COLUMN_LENGTH:75$]);

create index IX_AA8459B5 on gtfs_Trip (route_id[$COLUMN_LENGTH:75$]);
create index IX_9657449 on gtfs_Trip (service_id[$COLUMN_LENGTH:75$]);
create index IX_996BAF35 on gtfs_Trip (trip_id[$COLUMN_LENGTH:75$]);
create index IX_13439784 on gtfs_Trip (uuid_[$COLUMN_LENGTH:75$]);