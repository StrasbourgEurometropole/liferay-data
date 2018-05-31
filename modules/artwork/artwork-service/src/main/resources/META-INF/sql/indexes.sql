create index IX_EDC5D57B on artwork_Artwork (groupId);
create index IX_579B7C43 on artwork_Artwork (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_334C3785 on artwork_Artwork (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_7F8CA73D on artwork_ArtworkCollection (groupId);
create index IX_799784C1 on artwork_ArtworkCollection (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_C50C4783 on artwork_ArtworkCollection (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_D2B28C2B on artwork_ArtworkToArtworkCollection (artworkId);
create index IX_8C01B481 on artwork_ArtworkToArtworkCollection (collectionId);
create index IX_4FF30434 on artwork_ArtworkToArtworkCollection (companyId);