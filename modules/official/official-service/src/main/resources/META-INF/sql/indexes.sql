create index IX_3ACF9065 on official_Official (groupId);
create index IX_4C046899 on official_Official (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_6890415B on official_Official (uuid_[$COLUMN_LENGTH:75$], groupId);