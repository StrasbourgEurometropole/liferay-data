create index IX_46F38A49 on project_Participation (groupId);
create index IX_37B14F35 on project_Participation (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_883E1EF7 on project_Participation (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_EE43B560 on project_Petition (groupId);
create index IX_F58DC33A on project_Petition (status, groupId);
create index IX_12FAADFE on project_Petition (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_2EB29C00 on project_Petition (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_EEC80B2C on project_PlacitPlace (groupId);
create index IX_8FD2F0EE on project_PlacitPlace (participationId);
create index IX_1C0D6D6B on project_PlacitPlace (petitionId);
create index IX_AE47DC77 on project_PlacitPlace (placeSIGId[$COLUMN_LENGTH:75$]);
create index IX_9B962C6 on project_PlacitPlace (projectId);
create index IX_740C1DB2 on project_PlacitPlace (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_DD0468B4 on project_PlacitPlace (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_84EC11F1 on project_Project (groupId);
create index IX_F6DCFECB on project_Project (status, groupId);
create index IX_3B92E48D on project_Project (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_DB52A4F on project_Project (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_FC72391B on project_ProjectFollowed (projectId);
create index IX_2A9E13B0 on project_ProjectFollowed (publikUserId[$COLUMN_LENGTH:75$], projectId);

create index IX_6F4E9C8A on project_ProjectTimeline (projectId);