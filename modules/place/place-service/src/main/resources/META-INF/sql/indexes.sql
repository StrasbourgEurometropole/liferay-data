create index IX_20653473 on place_Period (placeId);
create index IX_FF05E735 on place_Period (uuid_[$COLUMN_LENGTH:75$]);

create index IX_9DFD4BE7 on place_Place (SIGid[$COLUMN_LENGTH:75$]);
create index IX_A56E7895 on place_Place (groupId);
create index IX_240ACBB4 on place_Place (name[$COLUMN_LENGTH:75$]);
create index IX_7F1EBE5F on place_Place (priceId);
create index IX_4F6FB669 on place_Place (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_FB14C32B on place_Place (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_C6227E5D on place_Price (uuid_[$COLUMN_LENGTH:75$]);

create index IX_D4677799 on place_PublicHoliday (recurrent);
create index IX_838EB4B7 on place_PublicHoliday (uuid_[$COLUMN_LENGTH:75$]);

create index IX_FAA0D40C on place_ScheduleException (placeId);
create index IX_F676D8FC on place_ScheduleException (subPlaceId);
create index IX_3EA6990E on place_ScheduleException (uuid_[$COLUMN_LENGTH:75$]);

create index IX_530FEC4C on place_Slot (periodId);
create index IX_CB0546B2 on place_Slot (subPlaceId);
create index IX_8B2BF198 on place_Slot (uuid_[$COLUMN_LENGTH:75$]);

create index IX_F73A1CD on place_SubPlace (placeId);
create index IX_1DA3E30F on place_SubPlace (uuid_[$COLUMN_LENGTH:75$]);