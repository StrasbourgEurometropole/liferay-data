create index IX_DEDC7EE4 on comment_Comment (assetEntryId, level, status);
create index IX_43271B5C on comment_Comment (assetEntryId, status);
create index IX_EE1FE1E5 on comment_Comment (groupId);
create index IX_3F3765A1 on comment_Comment (parentCommentId, status);
create index IX_B437C719 on comment_Comment (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_8B093FDB on comment_Comment (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_B31CB9C9 on comment_Signalement (commentId);
create index IX_FFD15D29 on comment_Signalement (groupId);
create index IX_2682B855 on comment_Signalement (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_28975017 on comment_Signalement (uuid_[$COLUMN_LENGTH:75$], groupId);