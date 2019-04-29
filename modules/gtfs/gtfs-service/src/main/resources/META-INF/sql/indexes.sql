create index IX_899580E4 on gtfs_Agency (uuid_[$COLUMN_LENGTH:75$]);

create index IX_7EB2170B on gtfs_Calendar (uuid_[$COLUMN_LENGTH:75$]);

create index IX_F1CEE47D on gtfs_CalendarDate (uuid_[$COLUMN_LENGTH:75$]);

create index IX_6BBD685B on gtfs_ImportHistoric (groupId);
create index IX_424B2563 on gtfs_ImportHistoric (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_B213A8A5 on gtfs_ImportHistoric (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_3526F95A on gtfs_ImportHistory (groupId);
create index IX_A3CB4344 on gtfs_ImportHistory (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_5313CAC6 on gtfs_ImportHistory (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_81826F8A on gtfs_Route (uuid_[$COLUMN_LENGTH:75$]);

create index IX_B1800C47 on gtfs_Stop (uuid_[$COLUMN_LENGTH:75$]);

create index IX_89648B7A on gtfs_StopTime (uuid_[$COLUMN_LENGTH:75$]);

create index IX_13439784 on gtfs_Trip (uuid_[$COLUMN_LENGTH:75$]);