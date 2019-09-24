create index IX_8AC0B357 on agenda_AgendaExport (groupId, status);
create index IX_FEBA893F on agenda_AgendaExport (status);
create index IX_6B24710D on agenda_AgendaExport (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_F53FD6CF on agenda_AgendaExport (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_33C5F7C1 on agenda_Campaign (groupId);
create index IX_B306DFDF on agenda_Campaign (title[$COLUMN_LENGTH:75$]);
create index IX_5EB688BD on agenda_Campaign (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_7CD41A7F on agenda_Campaign (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_F577BCDC on agenda_CampaignEvent (campaignId);
create index IX_70EF9311 on agenda_CampaignEvent (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_8B3A9D3 on agenda_CampaignEvent (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_CDE8F146 on agenda_CampaignEventStatus (campaignEventId);
create index IX_D3F95625 on agenda_CampaignEventStatus (uuid_[$COLUMN_LENGTH:75$]);

create index IX_D69467AB on agenda_Event (groupId, title[$COLUMN_LENGTH:400$]);
create index IX_3BEFC8D7 on agenda_Event (idSource[$COLUMN_LENGTH:75$]);
create index IX_8EE3FDB6 on agenda_Event (lastEndDate);
create index IX_31077546 on agenda_Event (placeSIGId[$COLUMN_LENGTH:75$]);
create index IX_D2CC01A3 on agenda_Event (publicationDate, status);
create index IX_774725E6 on agenda_Event (source[$COLUMN_LENGTH:75$], idSource[$COLUMN_LENGTH:75$]);
create index IX_11402873 on agenda_Event (status);
create index IX_1777B627 on agenda_Event (statusDate, status);
create index IX_E1A6B3DB on agenda_Event (title[$COLUMN_LENGTH:400$]);
create index IX_24E39F41 on agenda_Event (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_519D0203 on agenda_Event (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_B2EF1E05 on agenda_EventParticipation (eventId);
create index IX_CA18BE1A on agenda_EventParticipation (publikUserId[$COLUMN_LENGTH:75$], eventId);

create index IX_355794E7 on agenda_EventPeriod (campaignEventId);
create index IX_91BE1417 on agenda_EventPeriod (eventId);
create index IX_7830DF06 on agenda_EventPeriod (uuid_[$COLUMN_LENGTH:75$]);

create index IX_3DF3373E on agenda_EventToManifestation (companyId);
create index IX_F006BDB on agenda_EventToManifestation (eventId);
create index IX_E5121167 on agenda_EventToManifestation (manifestationId);

create index IX_303B8202 on agenda_ImportReport (uuid_[$COLUMN_LENGTH:75$]);

create index IX_D532E9A9 on agenda_ImportReportLine (reportId);
create index IX_BC33AA75 on agenda_ImportReportLine (type_[$COLUMN_LENGTH:75$], status);
create index IX_CE752A6E on agenda_ImportReportLine (uuid_[$COLUMN_LENGTH:75$]);

create index IX_E1C2A620 on agenda_Manifestation (endDate);
create index IX_44120AB1 on agenda_Manifestation (groupId);
create index IX_7BC83963 on agenda_Manifestation (idSource[$COLUMN_LENGTH:75$]);
create index IX_14CAE797 on agenda_Manifestation (publicationDate, status);
create index IX_651C4A72 on agenda_Manifestation (source[$COLUMN_LENGTH:75$], idSource[$COLUMN_LENGTH:75$]);
create index IX_E495E7B3 on agenda_Manifestation (statusDate, status);
create index IX_B81AEECF on agenda_Manifestation (title[$COLUMN_LENGTH:400$]);
create index IX_12B8C3CD on agenda_Manifestation (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_F749D98F on agenda_Manifestation (uuid_[$COLUMN_LENGTH:75$], groupId);