create index IX_D0DAEFFC on notification_Notification (expirationDate, status);
create index IX_E7FE9F4B on notification_Notification (publicationDate, status);

create index IX_5870626F on notification_UserNotificationChannel (channelId);
create index IX_43991FEA on notification_UserNotificationChannel (publikUserId[$COLUMN_LENGTH:75$]);

create index IX_C722B9FC on notification_UserNotificationStatus (notificationId);
create index IX_96152B6D on notification_UserNotificationStatus (publikUserId[$COLUMN_LENGTH:75$]);

create index IX_4367CD35 on notification_UserNotificationType (publikUserId[$COLUMN_LENGTH:75$]);
create index IX_7E89BC93 on notification_UserNotificationType (typeId);