create index IX_A8629802 on notif_Message (serviceId);

create index IX_41C7100 on notif_NatureNotif (serviceId);

create index IX_B7D722FB on notif_Notif (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_75322C3D on notif_Notif (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_C13664F4 on notif_Notification (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_DB3B9876 on notif_Notification (uuid_[$COLUMN_LENGTH:75$], groupId);