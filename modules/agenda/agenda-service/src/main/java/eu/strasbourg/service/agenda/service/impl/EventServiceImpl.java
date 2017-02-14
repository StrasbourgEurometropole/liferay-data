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

package eu.strasbourg.service.agenda.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.service.base.EventServiceBaseImpl;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.JSONHelper;
import eu.strasbourg.utils.SearchHelper;

/**
 * The implementation of the event remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link eu.strasbourg.service.agenda.service.EventService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have
 * security checks based on the propagated JAAS credentials because this service
 * can be accessed remotely.
 * </p>
 *
 * @author BenjaminBini
 * @see EventServiceBaseImpl
 * @see eu.strasbourg.service.agenda.service.EventServiceUtil
 */
@ProviderType
@AccessControlled(guestAccessEnabled = true)
public class EventServiceImpl extends EventServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * eu.strasbourg.service.agenda.service.EventServiceUtil} to access the
	 * event remote service.
	 */
	@Override
	public JSONArray getCategories() throws PortalException {
		long companyId = PortalUtil.getDefaultCompanyId();
		long companyGroupId = CompanyLocalServiceUtil.getCompany(companyId)
			.getGroup().getGroupId();
		long classNameId = ClassNameLocalServiceUtil
			.getClassName(Event.class.getName()).getClassNameId();
		List<AssetVocabulary> vocabularies = AssetVocabularyHelper
			.getVocabulariesForAssetType(companyGroupId, classNameId);
		JSONArray vocabulariesArray = JSONFactoryUtil.createJSONArray();
		for (AssetVocabulary vocabulary : vocabularies) {
			JSONObject vocabularyObject = JSONFactoryUtil.createJSONObject();
			vocabularyObject.put("name", vocabulary.getTitle(Locale.FRANCE));
			vocabularyObject.put("categories",
				AssetVocabularyHelper.toJSON(vocabulary));
			vocabulariesArray.put(vocabularyObject);
		}
		return vocabulariesArray;
	}

	@Override
	public JSONArray getPublics() throws PortalException {
		long companyId = PortalUtil.getDefaultCompanyId();
		long companyGroupId = CompanyLocalServiceUtil.getCompany(companyId)
			.getGroup().getGroupId();
		return JSONHelper.getEntityVocabularyJSON(Event.class.getName(),
			"public agenda", companyGroupId);
	}

	@Override
	public JSONArray getThemes() throws PortalException {
		long companyId = PortalUtil.getDefaultCompanyId();
		long companyGroupId = CompanyLocalServiceUtil.getCompany(companyId)
			.getGroup().getGroupId();
		return JSONHelper.getEntityVocabularyJSON(Event.class.getName(),
			"theme agenda", companyGroupId);
	}

	@Override
	public JSONArray getTypes() throws PortalException {
		long companyId = PortalUtil.getDefaultCompanyId();
		long companyGroupId = CompanyLocalServiceUtil.getCompany(companyId)
			.getGroup().getGroupId();
		return JSONHelper.getEntityVocabularyJSON(Event.class.getName(),
			"type agenda", companyGroupId);
	}

	@Override
	public JSONArray getServices() throws PortalException {
		long companyId = PortalUtil.getDefaultCompanyId();
		long companyGroupId = CompanyLocalServiceUtil.getCompany(companyId)
			.getGroup().getGroupId();
		return JSONHelper.getEntityVocabularyJSON(Event.class.getName(),
			"service gestionnaire", companyGroupId);
	}

	@Override
	public JSONObject getCategory(long id) throws PortalException {
		AssetCategory category = AssetCategoryLocalServiceUtil
			.fetchAssetCategory(id);
		return AssetVocabularyHelper.categotyToJSON(category);
	}

	@Override
	public JSONObject getEvent(long id) throws PortalException {
		Event event = this.eventLocalService.fetchEvent(id);
		if (!event.isApproved()) {
			return JSONFactoryUtil.createJSONObject();
		}
		return event.toJSON();
	}

	@Override
	public JSONArray getEvents() throws PortalException {
		List<Event> events = this.eventLocalService.getEvents(-1, -1);
		return this.getApprovedJSONEvents(events);
	}

	@Override
	public JSONArray getEventsByDate(String date) throws PortalException {
		LocalDate localDate = LocalDate.parse(date,
			DateTimeFormatter.ofPattern("ddMMyyyy"));
		Hits hits = SearchHelper.getEventWebServiceSearchHits(
			Event.class.getName(), localDate, (long) 0, null);
		List<Event> events = new ArrayList<Event>();
		for (Document document : hits.getDocs()) {
			Event event = this.eventLocalService.fetchEvent(
				GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
			events.add(event);
		}
		return this.getApprovedJSONEvents(events);
	}

	@Override
	public JSONArray getEventsByCategory(long categoryId)
		throws PortalException {
		Hits hits = SearchHelper.getEventWebServiceSearchHits(
			Event.class.getName(), null, categoryId, null);
		List<Event> events = new ArrayList<Event>();
		for (Document document : hits.getDocs()) {
			Event event = this.eventLocalService.fetchEvent(
				GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
			events.add(event);
		}
		return this.getApprovedJSONEvents(events);
	}

	@Override
	public JSONArray getEventsByPlace(String placeSIGId)
		throws PortalException {
		Hits hits = SearchHelper.getEventWebServiceSearchHits(
			Event.class.getName(), null, (long) 0, null);
		List<Event> events = new ArrayList<Event>();
		for (Document document : hits.getDocs()) {
			Event event = this.eventLocalService.fetchEvent(
				GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
			events.add(event);
		}
		events = events.stream().filter(e -> e.getPlaceSIGId().equals(placeSIGId))
			.collect(Collectors.toList());
		return this.getApprovedJSONEvents(events);
	}

	@Override
	public JSONArray getEventsByLanguage(String language)
		throws PortalException {
		Locale locale = LocaleUtil.fromLanguageId(language);
		Hits hits = SearchHelper.getEventWebServiceSearchHits(
			Event.class.getName(), null, (long) 0, locale);
		List<Event> events = new ArrayList<Event>();
		for (Document document : hits.getDocs()) {
			Event event = this.eventLocalService.fetchEvent(
				GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
			events.add(event);
		}
		return this.getApprovedJSONEvents(events);
	}

	private JSONArray getApprovedJSONEvents(List<Event> events) {
		JSONArray jsonEvents = JSONFactoryUtil.createJSONArray();
		for (Event event : events) {
			if (event.isApproved()) {
				jsonEvents.put(event.toJSON());
			}
		}
		return jsonEvents;
	}
}