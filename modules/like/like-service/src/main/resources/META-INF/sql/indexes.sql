create index IX_41985B6B on like_Like (entityId, typeId, isDislike);
create index IX_4B68609B on like_Like (publikUserId[$COLUMN_LENGTH:75$], isDislike);
create index IX_14606A6E on like_Like (publikUserId[$COLUMN_LENGTH:75$], title[$COLUMN_LENGTH:255$], isDislike, typeId, entityId);
create index IX_A78E6CD3 on like_Like (publikUserId[$COLUMN_LENGTH:75$], title[$COLUMN_LENGTH:255$], typeId, entityId);