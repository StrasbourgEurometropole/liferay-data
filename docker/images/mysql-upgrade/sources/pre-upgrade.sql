# Placer ici les scripts à effectuer avant la migration

# Changement de collation sur les tables des services
ALTER TABLE activity_Activity CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE activity_ActivityCourse CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE activity_ActivityCoursePlace CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE activity_ActivityCourseSchedule CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE activity_ActivityOrganizer CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE activity_Association CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE activity_Practice CHARACTER SET utf8 COLLATE utf8_unicode_ci;

ALTER TABLE agenda_AgendaExport CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE agenda_AgendaExportPeriod CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE agenda_Campaign CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE agenda_CampaignEvent CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE agenda_CampaignEventStatus CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE agenda_Event CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE agenda_EventParticipation CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE agenda_EventPeriod CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE agenda_EventToManifestation CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE agenda_ImportReport CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE agenda_ImportReportLine CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE agenda_Manifestation CHARACTER SET utf8 COLLATE utf8_unicode_ci;

ALTER TABLE artwork_Artwork CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE artwork_ArtworkCollection CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE artwork_ArtworkToArtworkCollection CHARACTER SET utf8 COLLATE utf8_unicode_ci;

ALTER TABLE comment_Comment CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE comment_Signalement CHARACTER SET utf8 COLLATE utf8_unicode_ci;

ALTER TABLE council_CouncilSession CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE council_Deliberation CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE council_Official CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE council_OfficialTypeCouncil CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE council_Procuration CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE council_Type CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE council_Vote CHARACTER SET utf8 COLLATE utf8_unicode_ci;

ALTER TABLE edition_Edition CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE edition_EditionGallery CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE edition_EditionToEditionGallery CHARACTER SET utf8 COLLATE utf8_unicode_ci;

ALTER TABLE event_Event CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE event_EventToManifestation CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE event_Manifestation CHARACTER SET utf8 COLLATE utf8_unicode_ci;

ALTER TABLE favorite_Favorite CHARACTER SET utf8 COLLATE utf8_unicode_ci;

ALTER TABLE formSendRecordField_FormSendRecordField CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE formSendRecordField_FormSendRecordFieldSignalement CHARACTER SET utf8 COLLATE utf8_unicode_ci;

ALTER TABLE gtfs_Agency CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE gtfs_Alert CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE gtfs_Arret CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE gtfs_Calendar CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE gtfs_CalendarDate CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE gtfs_Direction CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE gtfs_ImportHistoric CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE gtfs_Ligne CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE gtfs_Route CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE gtfs_Stop CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE gtfs_StopTime CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE gtfs_Trip CHARACTER SET utf8 COLLATE utf8_unicode_ci;

ALTER TABLE interest_Interest CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE interest_UserInterest CHARACTER SET utf8 COLLATE utf8_unicode_ci;

ALTER TABLE like_Like CHARACTER SET utf8 COLLATE utf8_unicode_ci;

ALTER TABLE link_Link CHARACTER SET utf8 COLLATE utf8_unicode_ci;

ALTER TABLE notification_Notification CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE notification_UserNotificationChannel CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE notification_UserNotificationStatus CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE notification_UserNotificationType CHARACTER SET utf8 COLLATE utf8_unicode_ci;

ALTER TABLE objtp_FoundObject CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE objtp_ObjectCategory CHARACTER SET utf8 COLLATE utf8_unicode_ci;

ALTER TABLE official_Official CHARACTER SET utf8 COLLATE utf8_unicode_ci;

ALTER TABLE place_GoogleMyBusinessHistoric CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE place_Period CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE place_Place CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE place_Price CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE place_PublicHoliday CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE place_ScheduleException CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE place_Slot CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE place_SubPlace CHARACTER SET utf8 COLLATE utf8_unicode_ci;

ALTER TABLE project_BudgetParticipatif CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE project_BudgetPhase CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE project_BudgetSupport CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE project_Initiative CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE project_InitiativeHelp CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE project_Participation CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE project_Petition CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE project_PlacitPlace CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE project_Project CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE project_ProjectFollowed CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE project_ProjectTimeline CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE project_Signataire CHARACTER SET utf8 COLLATE utf8_unicode_ci;

ALTER TABLE publik_AnonymisationHistoric CHARACTER SET utf8 COLLATE utf8_unicode_ci;

ALTER TABLE publik_PublikUser CHARACTER SET utf8 COLLATE utf8_unicode_ci;

ALTER TABLE search_SearchLog CHARACTER SET utf8 COLLATE utf8_unicode_ci;

ALTER TABLE strasbourg_Strasbourg CHARACTER SET utf8 COLLATE utf8_unicode_ci;

ALTER TABLE tipi_TipiEntry CHARACTER SET utf8 COLLATE utf8_unicode_ci;

ALTER TABLE video_Video CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE video_VideoGallery CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE video_VideoToVideoGallery CHARACTER SET utf8 COLLATE utf8_unicode_ci;

#Renommage du champ comment dans la table Comment
ALTER TABLE comment_Comment CHANGE comment_ text_ LONGTEXT;

#Renommage du champ price dans la table Price
ALTER TABLE place_Price CHANGE price priceDescription LONGTEXT;
