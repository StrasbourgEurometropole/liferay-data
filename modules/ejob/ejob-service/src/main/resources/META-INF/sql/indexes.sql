create index IX_23E45306 on ejob_Alert (publikUserId[$COLUMN_LENGTH:75$]);
create index IX_3F45CB2F on ejob_Alert (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_58D55171 on ejob_Alert (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_DF0B8AF2 on ejob_Offer (publicationEndDate, status);
create index IX_1B0C0E0A on ejob_Offer (publicationStartDate, publicationEndDate, status);
create index IX_EE15DC2F on ejob_Offer (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_EE75A271 on ejob_Offer (uuid_[$COLUMN_LENGTH:75$], groupId);