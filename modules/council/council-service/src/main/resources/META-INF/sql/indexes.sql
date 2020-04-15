create index IX_2C142A9D on council_CouncilSession (councilSessionId);
create index IX_A71BB2ED on council_CouncilSession (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_5FDE10AF on council_CouncilSession (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_B017553B on council_Deliberation (deliberationId);
create index IX_302FDFBC on council_Deliberation (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_6E65253E on council_Deliberation (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_263C73FD on council_Official (email[$COLUMN_LENGTH:75$]);
create index IX_8C722F39 on council_Official (officailId);
create index IX_C307D0A3 on council_Official (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_148223E5 on council_Official (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_4433A78E on council_Procuration (councilSessionId);
create index IX_BF3B2FDE on council_Procuration (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_AEA15E0 on council_Procuration (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_6F109921 on council_Vote (deliberationId);
create index IX_15E7C222 on council_Vote (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_2D5E6924 on council_Vote (uuid_[$COLUMN_LENGTH:75$], groupId);