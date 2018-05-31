create index IX_B21BE7AD on video_Video (groupId);
create index IX_B2D34C93 on video_Video (publicationDate, status);
create index IX_F451C251 on video_Video (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_2158A913 on video_Video (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_E5DE2909 on video_VideoGallery (groupId);
create index IX_40FF55EF on video_VideoGallery (publicationDate, status);
create index IX_4AED4875 on video_VideoGallery (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_10A76837 on video_VideoGallery (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_19F21359 on video_VideoToVideoGallery (companyId);
create index IX_B823DBAE on video_VideoToVideoGallery (galleryId);
create index IX_2F415C17 on video_VideoToVideoGallery (videoId);