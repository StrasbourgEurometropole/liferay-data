create index IX_DEDC7EE4 on comment_Comment (assetEntryId, level, status);
create index IX_43271B5C on comment_Comment (assetEntryId, status);
create index IX_EE1FE1E5 on comment_Comment (groupId);
create index IX_3F3765A1 on comment_Comment (parentCommentId, status);
create index IX_B437C719 on comment_Comment (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_8B093FDB on comment_Comment (uuid_[$COLUMN_LENGTH:75$], groupId);