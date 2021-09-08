create index IX_24001F16 on csmap_PlaceCategories (categoriesIds[$COLUMN_LENGTH:4000$]);
create index IX_C04F161E on csmap_PlaceCategories (uuid_[$COLUMN_LENGTH:75$]);

create index IX_E80807DD on csmap_RefreshToken (uuid_[$COLUMN_LENGTH:75$]);
create index IX_72E2BCEA on csmap_RefreshToken (value[$COLUMN_LENGTH:300$], publikId[$COLUMN_LENGTH:200$]);