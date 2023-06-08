create index IX_3C55C3C0 on favorite_Favorite (entityId, typeId);
create index IX_6F796F33 on favorite_Favorite (publikUserId[$COLUMN_LENGTH:75$], title[$COLUMN_LENGTH:255$], typeId, entityId);
create index IX_96B3F2FE on favorite_Favorite (typeId, entityId, publikUserId[$COLUMN_LENGTH:75$], content[$COLUMN_LENGTH:75$]);