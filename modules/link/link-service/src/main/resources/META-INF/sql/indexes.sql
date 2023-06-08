create index IX_6A474905 on link_Link (groupId);
create index IX_9A263F9 on link_Link (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_FA3594BB on link_Link (uuid_[$COLUMN_LENGTH:75$], groupId);