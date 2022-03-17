/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package eu.strasbourg.service.agenda.service.base;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import eu.strasbourg.service.agenda.model.AgendaExportPeriod;
import eu.strasbourg.service.agenda.service.AgendaExportPeriodService;
import eu.strasbourg.service.agenda.service.persistence.AgendaExportPeriodPersistence;
import eu.strasbourg.service.agenda.service.persistence.AgendaExportPersistence;
import eu.strasbourg.service.agenda.service.persistence.CacheJsonPersistence;
import eu.strasbourg.service.agenda.service.persistence.CampaignEventFinder;
import eu.strasbourg.service.agenda.service.persistence.CampaignEventPersistence;
import eu.strasbourg.service.agenda.service.persistence.CampaignEventStatusPersistence;
import eu.strasbourg.service.agenda.service.persistence.CampaignPersistence;
import eu.strasbourg.service.agenda.service.persistence.CsmapCacheJsonPersistence;
import eu.strasbourg.service.agenda.service.persistence.EventFinder;
import eu.strasbourg.service.agenda.service.persistence.EventParticipationPersistence;
import eu.strasbourg.service.agenda.service.persistence.EventPeriodPersistence;
import eu.strasbourg.service.agenda.service.persistence.EventPersistence;
import eu.strasbourg.service.agenda.service.persistence.HistoricPersistence;
import eu.strasbourg.service.agenda.service.persistence.ImportReportLinePersistence;
import eu.strasbourg.service.agenda.service.persistence.ImportReportPersistence;
import eu.strasbourg.service.agenda.service.persistence.ManifestationPersistence;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the agenda export period remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link eu.strasbourg.service.agenda.service.impl.AgendaExportPeriodServiceImpl}.
 * </p>
 *
 * @author BenjaminBini
 * @see eu.strasbourg.service.agenda.service.impl.AgendaExportPeriodServiceImpl
 * @generated
 */
public abstract class AgendaExportPeriodServiceBaseImpl
	extends BaseServiceImpl
	implements AgendaExportPeriodService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>AgendaExportPeriodService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>eu.strasbourg.service.agenda.service.AgendaExportPeriodServiceUtil</code>.
	 */

	/**
	 * Returns the agenda export local service.
	 *
	 * @return the agenda export local service
	 */
	public eu.strasbourg.service.agenda.service.AgendaExportLocalService
		getAgendaExportLocalService() {

		return agendaExportLocalService;
	}

	/**
	 * Sets the agenda export local service.
	 *
	 * @param agendaExportLocalService the agenda export local service
	 */
	public void setAgendaExportLocalService(
		eu.strasbourg.service.agenda.service.AgendaExportLocalService
			agendaExportLocalService) {

		this.agendaExportLocalService = agendaExportLocalService;
	}

	/**
	 * Returns the agenda export remote service.
	 *
	 * @return the agenda export remote service
	 */
	public eu.strasbourg.service.agenda.service.AgendaExportService
		getAgendaExportService() {

		return agendaExportService;
	}

	/**
	 * Sets the agenda export remote service.
	 *
	 * @param agendaExportService the agenda export remote service
	 */
	public void setAgendaExportService(
		eu.strasbourg.service.agenda.service.AgendaExportService
			agendaExportService) {

		this.agendaExportService = agendaExportService;
	}

	/**
	 * Returns the agenda export persistence.
	 *
	 * @return the agenda export persistence
	 */
	public AgendaExportPersistence getAgendaExportPersistence() {
		return agendaExportPersistence;
	}

	/**
	 * Sets the agenda export persistence.
	 *
	 * @param agendaExportPersistence the agenda export persistence
	 */
	public void setAgendaExportPersistence(
		AgendaExportPersistence agendaExportPersistence) {

		this.agendaExportPersistence = agendaExportPersistence;
	}

	/**
	 * Returns the agenda export period local service.
	 *
	 * @return the agenda export period local service
	 */
	public eu.strasbourg.service.agenda.service.AgendaExportPeriodLocalService
		getAgendaExportPeriodLocalService() {

		return agendaExportPeriodLocalService;
	}

	/**
	 * Sets the agenda export period local service.
	 *
	 * @param agendaExportPeriodLocalService the agenda export period local service
	 */
	public void setAgendaExportPeriodLocalService(
		eu.strasbourg.service.agenda.service.AgendaExportPeriodLocalService
			agendaExportPeriodLocalService) {

		this.agendaExportPeriodLocalService = agendaExportPeriodLocalService;
	}

	/**
	 * Returns the agenda export period remote service.
	 *
	 * @return the agenda export period remote service
	 */
	public AgendaExportPeriodService getAgendaExportPeriodService() {
		return agendaExportPeriodService;
	}

	/**
	 * Sets the agenda export period remote service.
	 *
	 * @param agendaExportPeriodService the agenda export period remote service
	 */
	public void setAgendaExportPeriodService(
		AgendaExportPeriodService agendaExportPeriodService) {

		this.agendaExportPeriodService = agendaExportPeriodService;
	}

	/**
	 * Returns the agenda export period persistence.
	 *
	 * @return the agenda export period persistence
	 */
	public AgendaExportPeriodPersistence getAgendaExportPeriodPersistence() {
		return agendaExportPeriodPersistence;
	}

	/**
	 * Sets the agenda export period persistence.
	 *
	 * @param agendaExportPeriodPersistence the agenda export period persistence
	 */
	public void setAgendaExportPeriodPersistence(
		AgendaExportPeriodPersistence agendaExportPeriodPersistence) {

		this.agendaExportPeriodPersistence = agendaExportPeriodPersistence;
	}

	/**
	 * Returns the cache json local service.
	 *
	 * @return the cache json local service
	 */
	public eu.strasbourg.service.agenda.service.CacheJsonLocalService
		getCacheJsonLocalService() {

		return cacheJsonLocalService;
	}

	/**
	 * Sets the cache json local service.
	 *
	 * @param cacheJsonLocalService the cache json local service
	 */
	public void setCacheJsonLocalService(
		eu.strasbourg.service.agenda.service.CacheJsonLocalService
			cacheJsonLocalService) {

		this.cacheJsonLocalService = cacheJsonLocalService;
	}

	/**
	 * Returns the cache json persistence.
	 *
	 * @return the cache json persistence
	 */
	public CacheJsonPersistence getCacheJsonPersistence() {
		return cacheJsonPersistence;
	}

	/**
	 * Sets the cache json persistence.
	 *
	 * @param cacheJsonPersistence the cache json persistence
	 */
	public void setCacheJsonPersistence(
		CacheJsonPersistence cacheJsonPersistence) {

		this.cacheJsonPersistence = cacheJsonPersistence;
	}

	/**
	 * Returns the campaign local service.
	 *
	 * @return the campaign local service
	 */
	public eu.strasbourg.service.agenda.service.CampaignLocalService
		getCampaignLocalService() {

		return campaignLocalService;
	}

	/**
	 * Sets the campaign local service.
	 *
	 * @param campaignLocalService the campaign local service
	 */
	public void setCampaignLocalService(
		eu.strasbourg.service.agenda.service.CampaignLocalService
			campaignLocalService) {

		this.campaignLocalService = campaignLocalService;
	}

	/**
	 * Returns the campaign persistence.
	 *
	 * @return the campaign persistence
	 */
	public CampaignPersistence getCampaignPersistence() {
		return campaignPersistence;
	}

	/**
	 * Sets the campaign persistence.
	 *
	 * @param campaignPersistence the campaign persistence
	 */
	public void setCampaignPersistence(
		CampaignPersistence campaignPersistence) {

		this.campaignPersistence = campaignPersistence;
	}

	/**
	 * Returns the campaign event local service.
	 *
	 * @return the campaign event local service
	 */
	public eu.strasbourg.service.agenda.service.CampaignEventLocalService
		getCampaignEventLocalService() {

		return campaignEventLocalService;
	}

	/**
	 * Sets the campaign event local service.
	 *
	 * @param campaignEventLocalService the campaign event local service
	 */
	public void setCampaignEventLocalService(
		eu.strasbourg.service.agenda.service.CampaignEventLocalService
			campaignEventLocalService) {

		this.campaignEventLocalService = campaignEventLocalService;
	}

	/**
	 * Returns the campaign event persistence.
	 *
	 * @return the campaign event persistence
	 */
	public CampaignEventPersistence getCampaignEventPersistence() {
		return campaignEventPersistence;
	}

	/**
	 * Sets the campaign event persistence.
	 *
	 * @param campaignEventPersistence the campaign event persistence
	 */
	public void setCampaignEventPersistence(
		CampaignEventPersistence campaignEventPersistence) {

		this.campaignEventPersistence = campaignEventPersistence;
	}

	/**
	 * Returns the campaign event finder.
	 *
	 * @return the campaign event finder
	 */
	public CampaignEventFinder getCampaignEventFinder() {
		return campaignEventFinder;
	}

	/**
	 * Sets the campaign event finder.
	 *
	 * @param campaignEventFinder the campaign event finder
	 */
	public void setCampaignEventFinder(
		CampaignEventFinder campaignEventFinder) {

		this.campaignEventFinder = campaignEventFinder;
	}

	/**
	 * Returns the campaign event status local service.
	 *
	 * @return the campaign event status local service
	 */
	public eu.strasbourg.service.agenda.service.CampaignEventStatusLocalService
		getCampaignEventStatusLocalService() {

		return campaignEventStatusLocalService;
	}

	/**
	 * Sets the campaign event status local service.
	 *
	 * @param campaignEventStatusLocalService the campaign event status local service
	 */
	public void setCampaignEventStatusLocalService(
		eu.strasbourg.service.agenda.service.CampaignEventStatusLocalService
			campaignEventStatusLocalService) {

		this.campaignEventStatusLocalService = campaignEventStatusLocalService;
	}

	/**
	 * Returns the campaign event status remote service.
	 *
	 * @return the campaign event status remote service
	 */
	public eu.strasbourg.service.agenda.service.CampaignEventStatusService
		getCampaignEventStatusService() {

		return campaignEventStatusService;
	}

	/**
	 * Sets the campaign event status remote service.
	 *
	 * @param campaignEventStatusService the campaign event status remote service
	 */
	public void setCampaignEventStatusService(
		eu.strasbourg.service.agenda.service.CampaignEventStatusService
			campaignEventStatusService) {

		this.campaignEventStatusService = campaignEventStatusService;
	}

	/**
	 * Returns the campaign event status persistence.
	 *
	 * @return the campaign event status persistence
	 */
	public CampaignEventStatusPersistence getCampaignEventStatusPersistence() {
		return campaignEventStatusPersistence;
	}

	/**
	 * Sets the campaign event status persistence.
	 *
	 * @param campaignEventStatusPersistence the campaign event status persistence
	 */
	public void setCampaignEventStatusPersistence(
		CampaignEventStatusPersistence campaignEventStatusPersistence) {

		this.campaignEventStatusPersistence = campaignEventStatusPersistence;
	}

	/**
	 * Returns the csmap cache json local service.
	 *
	 * @return the csmap cache json local service
	 */
	public eu.strasbourg.service.agenda.service.CsmapCacheJsonLocalService
		getCsmapCacheJsonLocalService() {

		return csmapCacheJsonLocalService;
	}

	/**
	 * Sets the csmap cache json local service.
	 *
	 * @param csmapCacheJsonLocalService the csmap cache json local service
	 */
	public void setCsmapCacheJsonLocalService(
		eu.strasbourg.service.agenda.service.CsmapCacheJsonLocalService
			csmapCacheJsonLocalService) {

		this.csmapCacheJsonLocalService = csmapCacheJsonLocalService;
	}

	/**
	 * Returns the csmap cache json persistence.
	 *
	 * @return the csmap cache json persistence
	 */
	public CsmapCacheJsonPersistence getCsmapCacheJsonPersistence() {
		return csmapCacheJsonPersistence;
	}

	/**
	 * Sets the csmap cache json persistence.
	 *
	 * @param csmapCacheJsonPersistence the csmap cache json persistence
	 */
	public void setCsmapCacheJsonPersistence(
		CsmapCacheJsonPersistence csmapCacheJsonPersistence) {

		this.csmapCacheJsonPersistence = csmapCacheJsonPersistence;
	}

	/**
	 * Returns the event local service.
	 *
	 * @return the event local service
	 */
	public eu.strasbourg.service.agenda.service.EventLocalService
		getEventLocalService() {

		return eventLocalService;
	}

	/**
	 * Sets the event local service.
	 *
	 * @param eventLocalService the event local service
	 */
	public void setEventLocalService(
		eu.strasbourg.service.agenda.service.EventLocalService
			eventLocalService) {

		this.eventLocalService = eventLocalService;
	}

	/**
	 * Returns the event remote service.
	 *
	 * @return the event remote service
	 */
	public eu.strasbourg.service.agenda.service.EventService getEventService() {
		return eventService;
	}

	/**
	 * Sets the event remote service.
	 *
	 * @param eventService the event remote service
	 */
	public void setEventService(
		eu.strasbourg.service.agenda.service.EventService eventService) {

		this.eventService = eventService;
	}

	/**
	 * Returns the event persistence.
	 *
	 * @return the event persistence
	 */
	public EventPersistence getEventPersistence() {
		return eventPersistence;
	}

	/**
	 * Sets the event persistence.
	 *
	 * @param eventPersistence the event persistence
	 */
	public void setEventPersistence(EventPersistence eventPersistence) {
		this.eventPersistence = eventPersistence;
	}

	/**
	 * Returns the event finder.
	 *
	 * @return the event finder
	 */
	public EventFinder getEventFinder() {
		return eventFinder;
	}

	/**
	 * Sets the event finder.
	 *
	 * @param eventFinder the event finder
	 */
	public void setEventFinder(EventFinder eventFinder) {
		this.eventFinder = eventFinder;
	}

	/**
	 * Returns the event participation local service.
	 *
	 * @return the event participation local service
	 */
	public eu.strasbourg.service.agenda.service.EventParticipationLocalService
		getEventParticipationLocalService() {

		return eventParticipationLocalService;
	}

	/**
	 * Sets the event participation local service.
	 *
	 * @param eventParticipationLocalService the event participation local service
	 */
	public void setEventParticipationLocalService(
		eu.strasbourg.service.agenda.service.EventParticipationLocalService
			eventParticipationLocalService) {

		this.eventParticipationLocalService = eventParticipationLocalService;
	}

	/**
	 * Returns the event participation remote service.
	 *
	 * @return the event participation remote service
	 */
	public eu.strasbourg.service.agenda.service.EventParticipationService
		getEventParticipationService() {

		return eventParticipationService;
	}

	/**
	 * Sets the event participation remote service.
	 *
	 * @param eventParticipationService the event participation remote service
	 */
	public void setEventParticipationService(
		eu.strasbourg.service.agenda.service.EventParticipationService
			eventParticipationService) {

		this.eventParticipationService = eventParticipationService;
	}

	/**
	 * Returns the event participation persistence.
	 *
	 * @return the event participation persistence
	 */
	public EventParticipationPersistence getEventParticipationPersistence() {
		return eventParticipationPersistence;
	}

	/**
	 * Sets the event participation persistence.
	 *
	 * @param eventParticipationPersistence the event participation persistence
	 */
	public void setEventParticipationPersistence(
		EventParticipationPersistence eventParticipationPersistence) {

		this.eventParticipationPersistence = eventParticipationPersistence;
	}

	/**
	 * Returns the event period local service.
	 *
	 * @return the event period local service
	 */
	public eu.strasbourg.service.agenda.service.EventPeriodLocalService
		getEventPeriodLocalService() {

		return eventPeriodLocalService;
	}

	/**
	 * Sets the event period local service.
	 *
	 * @param eventPeriodLocalService the event period local service
	 */
	public void setEventPeriodLocalService(
		eu.strasbourg.service.agenda.service.EventPeriodLocalService
			eventPeriodLocalService) {

		this.eventPeriodLocalService = eventPeriodLocalService;
	}

	/**
	 * Returns the event period remote service.
	 *
	 * @return the event period remote service
	 */
	public eu.strasbourg.service.agenda.service.EventPeriodService
		getEventPeriodService() {

		return eventPeriodService;
	}

	/**
	 * Sets the event period remote service.
	 *
	 * @param eventPeriodService the event period remote service
	 */
	public void setEventPeriodService(
		eu.strasbourg.service.agenda.service.EventPeriodService
			eventPeriodService) {

		this.eventPeriodService = eventPeriodService;
	}

	/**
	 * Returns the event period persistence.
	 *
	 * @return the event period persistence
	 */
	public EventPeriodPersistence getEventPeriodPersistence() {
		return eventPeriodPersistence;
	}

	/**
	 * Sets the event period persistence.
	 *
	 * @param eventPeriodPersistence the event period persistence
	 */
	public void setEventPeriodPersistence(
		EventPeriodPersistence eventPeriodPersistence) {

		this.eventPeriodPersistence = eventPeriodPersistence;
	}

	/**
	 * Returns the historic local service.
	 *
	 * @return the historic local service
	 */
	public eu.strasbourg.service.agenda.service.HistoricLocalService
		getHistoricLocalService() {

		return historicLocalService;
	}

	/**
	 * Sets the historic local service.
	 *
	 * @param historicLocalService the historic local service
	 */
	public void setHistoricLocalService(
		eu.strasbourg.service.agenda.service.HistoricLocalService
			historicLocalService) {

		this.historicLocalService = historicLocalService;
	}

	/**
	 * Returns the historic persistence.
	 *
	 * @return the historic persistence
	 */
	public HistoricPersistence getHistoricPersistence() {
		return historicPersistence;
	}

	/**
	 * Sets the historic persistence.
	 *
	 * @param historicPersistence the historic persistence
	 */
	public void setHistoricPersistence(
		HistoricPersistence historicPersistence) {

		this.historicPersistence = historicPersistence;
	}

	/**
	 * Returns the import report local service.
	 *
	 * @return the import report local service
	 */
	public eu.strasbourg.service.agenda.service.ImportReportLocalService
		getImportReportLocalService() {

		return importReportLocalService;
	}

	/**
	 * Sets the import report local service.
	 *
	 * @param importReportLocalService the import report local service
	 */
	public void setImportReportLocalService(
		eu.strasbourg.service.agenda.service.ImportReportLocalService
			importReportLocalService) {

		this.importReportLocalService = importReportLocalService;
	}

	/**
	 * Returns the import report persistence.
	 *
	 * @return the import report persistence
	 */
	public ImportReportPersistence getImportReportPersistence() {
		return importReportPersistence;
	}

	/**
	 * Sets the import report persistence.
	 *
	 * @param importReportPersistence the import report persistence
	 */
	public void setImportReportPersistence(
		ImportReportPersistence importReportPersistence) {

		this.importReportPersistence = importReportPersistence;
	}

	/**
	 * Returns the import report line local service.
	 *
	 * @return the import report line local service
	 */
	public eu.strasbourg.service.agenda.service.ImportReportLineLocalService
		getImportReportLineLocalService() {

		return importReportLineLocalService;
	}

	/**
	 * Sets the import report line local service.
	 *
	 * @param importReportLineLocalService the import report line local service
	 */
	public void setImportReportLineLocalService(
		eu.strasbourg.service.agenda.service.ImportReportLineLocalService
			importReportLineLocalService) {

		this.importReportLineLocalService = importReportLineLocalService;
	}

	/**
	 * Returns the import report line persistence.
	 *
	 * @return the import report line persistence
	 */
	public ImportReportLinePersistence getImportReportLinePersistence() {
		return importReportLinePersistence;
	}

	/**
	 * Sets the import report line persistence.
	 *
	 * @param importReportLinePersistence the import report line persistence
	 */
	public void setImportReportLinePersistence(
		ImportReportLinePersistence importReportLinePersistence) {

		this.importReportLinePersistence = importReportLinePersistence;
	}

	/**
	 * Returns the manifestation local service.
	 *
	 * @return the manifestation local service
	 */
	public eu.strasbourg.service.agenda.service.ManifestationLocalService
		getManifestationLocalService() {

		return manifestationLocalService;
	}

	/**
	 * Sets the manifestation local service.
	 *
	 * @param manifestationLocalService the manifestation local service
	 */
	public void setManifestationLocalService(
		eu.strasbourg.service.agenda.service.ManifestationLocalService
			manifestationLocalService) {

		this.manifestationLocalService = manifestationLocalService;
	}

	/**
	 * Returns the manifestation remote service.
	 *
	 * @return the manifestation remote service
	 */
	public eu.strasbourg.service.agenda.service.ManifestationService
		getManifestationService() {

		return manifestationService;
	}

	/**
	 * Sets the manifestation remote service.
	 *
	 * @param manifestationService the manifestation remote service
	 */
	public void setManifestationService(
		eu.strasbourg.service.agenda.service.ManifestationService
			manifestationService) {

		this.manifestationService = manifestationService;
	}

	/**
	 * Returns the manifestation persistence.
	 *
	 * @return the manifestation persistence
	 */
	public ManifestationPersistence getManifestationPersistence() {
		return manifestationPersistence;
	}

	/**
	 * Sets the manifestation persistence.
	 *
	 * @param manifestationPersistence the manifestation persistence
	 */
	public void setManifestationPersistence(
		ManifestationPersistence manifestationPersistence) {

		this.manifestationPersistence = manifestationPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService
		getCounterLocalService() {

		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService
			counterLocalService) {

		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.kernel.service.ClassNameLocalService
		getClassNameLocalService() {

		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.kernel.service.ClassNameLocalService
			classNameLocalService) {

		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name remote service.
	 *
	 * @return the class name remote service
	 */
	public com.liferay.portal.kernel.service.ClassNameService
		getClassNameService() {

		return classNameService;
	}

	/**
	 * Sets the class name remote service.
	 *
	 * @param classNameService the class name remote service
	 */
	public void setClassNameService(
		com.liferay.portal.kernel.service.ClassNameService classNameService) {

		this.classNameService = classNameService;
	}

	/**
	 * Returns the class name persistence.
	 *
	 * @return the class name persistence
	 */
	public ClassNamePersistence getClassNamePersistence() {
		return classNamePersistence;
	}

	/**
	 * Sets the class name persistence.
	 *
	 * @param classNamePersistence the class name persistence
	 */
	public void setClassNamePersistence(
		ClassNamePersistence classNamePersistence) {

		this.classNamePersistence = classNamePersistence;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.kernel.service.ResourceLocalService
		getResourceLocalService() {

		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService
			resourceLocalService) {

		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService
		getUserLocalService() {

		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {

		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public com.liferay.portal.kernel.service.UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(
		com.liferay.portal.kernel.service.UserService userService) {

		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
	}

	public void destroy() {
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return AgendaExportPeriodService.class.getName();
	}

	protected Class<?> getModelClass() {
		return AgendaExportPeriod.class;
	}

	protected String getModelClassName() {
		return AgendaExportPeriod.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				agendaExportPeriodPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(
		type = eu.strasbourg.service.agenda.service.AgendaExportLocalService.class
	)
	protected eu.strasbourg.service.agenda.service.AgendaExportLocalService
		agendaExportLocalService;

	@BeanReference(
		type = eu.strasbourg.service.agenda.service.AgendaExportService.class
	)
	protected eu.strasbourg.service.agenda.service.AgendaExportService
		agendaExportService;

	@BeanReference(type = AgendaExportPersistence.class)
	protected AgendaExportPersistence agendaExportPersistence;

	@BeanReference(
		type = eu.strasbourg.service.agenda.service.AgendaExportPeriodLocalService.class
	)
	protected
		eu.strasbourg.service.agenda.service.AgendaExportPeriodLocalService
			agendaExportPeriodLocalService;

	@BeanReference(type = AgendaExportPeriodService.class)
	protected AgendaExportPeriodService agendaExportPeriodService;

	@BeanReference(type = AgendaExportPeriodPersistence.class)
	protected AgendaExportPeriodPersistence agendaExportPeriodPersistence;

	@BeanReference(
		type = eu.strasbourg.service.agenda.service.CacheJsonLocalService.class
	)
	protected eu.strasbourg.service.agenda.service.CacheJsonLocalService
		cacheJsonLocalService;

	@BeanReference(type = CacheJsonPersistence.class)
	protected CacheJsonPersistence cacheJsonPersistence;

	@BeanReference(
		type = eu.strasbourg.service.agenda.service.CampaignLocalService.class
	)
	protected eu.strasbourg.service.agenda.service.CampaignLocalService
		campaignLocalService;

	@BeanReference(type = CampaignPersistence.class)
	protected CampaignPersistence campaignPersistence;

	@BeanReference(
		type = eu.strasbourg.service.agenda.service.CampaignEventLocalService.class
	)
	protected eu.strasbourg.service.agenda.service.CampaignEventLocalService
		campaignEventLocalService;

	@BeanReference(type = CampaignEventPersistence.class)
	protected CampaignEventPersistence campaignEventPersistence;

	@BeanReference(type = CampaignEventFinder.class)
	protected CampaignEventFinder campaignEventFinder;

	@BeanReference(
		type = eu.strasbourg.service.agenda.service.CampaignEventStatusLocalService.class
	)
	protected
		eu.strasbourg.service.agenda.service.CampaignEventStatusLocalService
			campaignEventStatusLocalService;

	@BeanReference(
		type = eu.strasbourg.service.agenda.service.CampaignEventStatusService.class
	)
	protected eu.strasbourg.service.agenda.service.CampaignEventStatusService
		campaignEventStatusService;

	@BeanReference(type = CampaignEventStatusPersistence.class)
	protected CampaignEventStatusPersistence campaignEventStatusPersistence;

	@BeanReference(
		type = eu.strasbourg.service.agenda.service.CsmapCacheJsonLocalService.class
	)
	protected eu.strasbourg.service.agenda.service.CsmapCacheJsonLocalService
		csmapCacheJsonLocalService;

	@BeanReference(type = CsmapCacheJsonPersistence.class)
	protected CsmapCacheJsonPersistence csmapCacheJsonPersistence;

	@BeanReference(
		type = eu.strasbourg.service.agenda.service.EventLocalService.class
	)
	protected eu.strasbourg.service.agenda.service.EventLocalService
		eventLocalService;

	@BeanReference(
		type = eu.strasbourg.service.agenda.service.EventService.class
	)
	protected eu.strasbourg.service.agenda.service.EventService eventService;

	@BeanReference(type = EventPersistence.class)
	protected EventPersistence eventPersistence;

	@BeanReference(type = EventFinder.class)
	protected EventFinder eventFinder;

	@BeanReference(
		type = eu.strasbourg.service.agenda.service.EventParticipationLocalService.class
	)
	protected
		eu.strasbourg.service.agenda.service.EventParticipationLocalService
			eventParticipationLocalService;

	@BeanReference(
		type = eu.strasbourg.service.agenda.service.EventParticipationService.class
	)
	protected eu.strasbourg.service.agenda.service.EventParticipationService
		eventParticipationService;

	@BeanReference(type = EventParticipationPersistence.class)
	protected EventParticipationPersistence eventParticipationPersistence;

	@BeanReference(
		type = eu.strasbourg.service.agenda.service.EventPeriodLocalService.class
	)
	protected eu.strasbourg.service.agenda.service.EventPeriodLocalService
		eventPeriodLocalService;

	@BeanReference(
		type = eu.strasbourg.service.agenda.service.EventPeriodService.class
	)
	protected eu.strasbourg.service.agenda.service.EventPeriodService
		eventPeriodService;

	@BeanReference(type = EventPeriodPersistence.class)
	protected EventPeriodPersistence eventPeriodPersistence;

	@BeanReference(
		type = eu.strasbourg.service.agenda.service.HistoricLocalService.class
	)
	protected eu.strasbourg.service.agenda.service.HistoricLocalService
		historicLocalService;

	@BeanReference(type = HistoricPersistence.class)
	protected HistoricPersistence historicPersistence;

	@BeanReference(
		type = eu.strasbourg.service.agenda.service.ImportReportLocalService.class
	)
	protected eu.strasbourg.service.agenda.service.ImportReportLocalService
		importReportLocalService;

	@BeanReference(type = ImportReportPersistence.class)
	protected ImportReportPersistence importReportPersistence;

	@BeanReference(
		type = eu.strasbourg.service.agenda.service.ImportReportLineLocalService.class
	)
	protected eu.strasbourg.service.agenda.service.ImportReportLineLocalService
		importReportLineLocalService;

	@BeanReference(type = ImportReportLinePersistence.class)
	protected ImportReportLinePersistence importReportLinePersistence;

	@BeanReference(
		type = eu.strasbourg.service.agenda.service.ManifestationLocalService.class
	)
	protected eu.strasbourg.service.agenda.service.ManifestationLocalService
		manifestationLocalService;

	@BeanReference(
		type = eu.strasbourg.service.agenda.service.ManifestationService.class
	)
	protected eu.strasbourg.service.agenda.service.ManifestationService
		manifestationService;

	@BeanReference(type = ManifestationPersistence.class)
	protected ManifestationPersistence manifestationPersistence;

	@ServiceReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.ClassNameLocalService.class
	)
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.ClassNameService.class
	)
	protected com.liferay.portal.kernel.service.ClassNameService
		classNameService;

	@ServiceReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.ResourceLocalService.class
	)
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.UserLocalService.class
	)
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.UserService.class
	)
	protected com.liferay.portal.kernel.service.UserService userService;

	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;

}