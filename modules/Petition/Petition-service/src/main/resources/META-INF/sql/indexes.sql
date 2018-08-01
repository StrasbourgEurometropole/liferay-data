create index IX_C1510705 on petition_Petition (groupId);
create index IX_EAE65F9 on petition_Petition (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_765A16BB on petition_Petition (uuid_[$COLUMN_LENGTH:75$], groupId);