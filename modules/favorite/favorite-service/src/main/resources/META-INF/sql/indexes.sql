create index IX_3C55C3C0 on favorite_Favorite (entityId, typeId);
create index IX_3FF4D762 on favorite_Favorite (publikUserId[$COLUMN_LENGTH:75$], title[$COLUMN_LENGTH:255$], url[$COLUMN_LENGTH:255$], typeId, entityId);
create index IX_A8575680 on favorite_Favorite (typeId, entityId);