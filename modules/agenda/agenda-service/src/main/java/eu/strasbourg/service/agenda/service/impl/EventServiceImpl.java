/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 * <p>
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * <p>
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package eu.strasbourg.service.agenda.service.impl;

import aQute.bnd.annotation.ProviderType;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.service.base.EventServiceBaseImpl;
import eu.strasbourg.service.place.model.Place;
import eu.strasbourg.service.place.service.PlaceLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.JSONHelper;
import eu.strasbourg.utils.SearchHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

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
    public JSONArray getTerritories() throws PortalException {
        long companyId = PortalUtil.getDefaultCompanyId();
        long companyGroupId = CompanyLocalServiceUtil.getCompany(companyId)
                .getGroup().getGroupId();
        return JSONHelper.getEntityVocabularyJSON(Event.class.getName(),
                "territoire", companyGroupId);
    }

    @Override
    public JSONObject getCategory(long id) throws PortalException {
        AssetCategory category = AssetCategoryLocalServiceUtil
                .fetchAssetCategory(id);
        return AssetVocabularyHelper.categoryToJSON(category);
    }

    @Override
    public JSONObject getEvent(long id) throws PortalException {
        if (!isAuthorized()) {
            return error("not authorized");
        }

        Event event = this.eventLocalService.fetchEvent(id);
        if (event == null || !event.isApproved()) {
            return JSONFactoryUtil.createJSONObject();
        }
        JSONObject eventJSON = event.toJSON();
        if (Validator.isNotNull(event.getPlaceSIGId())) {
            Place place = PlaceLocalServiceUtil.getPlaceBySIGId(event.getPlaceSIGId());
            if(place != null)
                eventJSON.put("place", place.toJSON());
        }
        return eventJSON;
    }

    @Override
    public JSONObject getEvents() throws PortalException {
        if (!isAuthorized()) {
            return error("not authorized");
        }

        List<Event> events = this.eventLocalService.getEvents(-1, -1);
        return this.getApprovedJSONEvents(events);
    }

    @Override
    public JSONObject getEventsByDate(String date) throws PortalException {
        if (!isAuthorized()) {
            return error("not authorized");
        }

        LocalDate localDate = LocalDate.parse(date,
                DateTimeFormatter.ofPattern("ddMMyyyy"));
        Hits hits = SearchHelper.getEventWebServiceSearchHits(
                Event.class.getName(), localDate, (long) 0, null);
        List<Event> events = new ArrayList<Event>();
        for (Document document : hits.getDocs()) {
            Event event = this.eventLocalService.fetchEvent(
                    GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
            if (event != null) {
                events.add(event);
            }
        }
        return this.getApprovedJSONEvents(events);
    }

    @Override
    public JSONObject getEventsByCategory(String categoryId)
            throws PortalException {
        if (!isAuthorized()) {
            return error("not authorized");
        }

        // on récupère la catégorie liée à l'externalId de la catégorie (categoryId)
        long categId = 0;
        AssetCategory category = AssetVocabularyHelper.getCategoryByExternalId(categoryId);
        if(Validator.isNotNull(category))
            categId = category.getCategoryId();

        Hits hits = SearchHelper.getEventWebServiceSearchHits(
                Event.class.getName(), null, categId, null);
        List<Event> events = new ArrayList<Event>();
        for (Document document : hits.getDocs()) {
            Event event = this.eventLocalService.fetchEvent(
                    GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
            if (event != null) {
                events.add(event);
            }
        }
        return this.getApprovedJSONEvents(events);
    }

    @Override
    public JSONObject getEventsByPlace(String placeSIGId)
            throws PortalException {
        if (!isAuthorized()) {
            return error("not authorized");
        }

        if (Validator.isNotNull(placeSIGId)) {
            List<Event> events = this.eventLocalService.findByPlaceSIGId(placeSIGId);
            return this.getApprovedJSONEvents(events);
        } else {
            return error("place-sig-id parameter is empty");
        }
    }

    @Override
    public JSONObject getEventsByLanguage(String language)
            throws PortalException {
        if (!isAuthorized()) {
            return error("not authorized");
        }

        Locale locale = LocaleUtil.fromLanguageId(language);
        Hits hits = SearchHelper.getEventWebServiceSearchHits(
                Event.class.getName(), null, (long) 0, locale);
        List<Event> events = new ArrayList<Event>();
        for (Document document : hits.getDocs()) {
            Event event = this.eventLocalService.fetchEvent(
                    GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
            if (event != null) {
                events.add(event);
            }
        }
        return this.getApprovedJSONEvents(events);
    }

    private JSONObject getApprovedJSONEvents(List<Event> events) {
        JSONObject result = JSONFactoryUtil.createJSONObject();
        JSONArray jsonEvents = JSONFactoryUtil.createJSONArray();
        Map<String, Place> places = PlaceLocalServiceUtil.getPlaces(-1, -1).stream()
                .collect(Collectors.toMap(p -> p.getSIGid(), p -> p));
        for (Event event : events) {
            if (event.isApproved()) {
                JSONObject eventJSON = event.toJSON();
                if (Validator.isNotNull(event.getPlaceSIGId())) {
                    JSONObject placeJSON = places.get(event.getPlaceSIGId()).toJSON();
                    eventJSON.put("place", placeJSON);
                }
                jsonEvents.put(eventJSON);
            }
        }
        result.put("events", jsonEvents);
        return result;
    }
    
    @Override
    public JSONArray getSessions(long eventID) {
    	Event event = this.eventLocalService.fetchEvent(eventID);
    	
    	if (event != null) {
    		return event.getSessionsFromRodrigueInJSON();
    	} else {
    		return JSONFactoryUtil.createJSONArray();
    	}
    }

    private JSONObject error(String message) {
        return JSONFactoryUtil.createJSONObject().put("error", message);
    }

    private boolean isAuthorized() {
        try {
            Company defaultCompany = CompanyLocalServiceUtil.getCompanyByWebId("liferay.com");
            long globalGroupId = defaultCompany.getGroup().getGroupId();
            return this.getPermissionChecker().hasPermission(globalGroupId, StrasbourgPortletKeys.AGENDA_BO,
                    StrasbourgPortletKeys.AGENDA_BO, "CONTRIBUTE");
        } catch (PortalException e) {
            return false;
        }
    }
}