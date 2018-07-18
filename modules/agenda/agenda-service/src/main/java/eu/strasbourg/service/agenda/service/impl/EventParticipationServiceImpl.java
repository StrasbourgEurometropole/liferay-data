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

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.util.SessionParamUtil;

import eu.strasbourg.service.agenda.exception.NoSuchEventParticipationException;
import eu.strasbourg.service.agenda.model.EventParticipation;
import eu.strasbourg.service.agenda.service.base.EventParticipationServiceBaseImpl;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

/**
 * The implementation of the event participation remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.agenda.service.EventParticipationService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Cedric Henry
 * @see EventParticipationServiceBaseImpl
 * @see eu.strasbourg.service.agenda.service.EventParticipationServiceUtil
 */
public class EventParticipationServiceImpl
	extends EventParticipationServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.agenda.service.EventParticipationServiceUtil} to access the event participation remote service.
	 */
	
	/**
	 * Retourne les participation d'evenement d'un utilisateur
	 */
	@Override
	public JSONObject getUserEventParticipations(String userId) {
		if (!isAuthorized()) {
			return error("not authorized");
		}
		
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray jsonEventParticipations = JSONFactoryUtil.createJSONArray();
		
		List<EventParticipation> eventParticipations = this.eventParticipationLocalService.getByPublikUser(userId);
		for (EventParticipation eventParticipation : eventParticipations) {
			jsonEventParticipations.put(eventParticipation.toJSON());
		}
		
		result.put("eventParticipations", jsonEventParticipations);
		return result;
	}
	
	/**
	 * Ajoute une participation à un utilisateur
	 */
	@Override
	public JSONObject addEventParticipationLink(long eventId, long groupId) {		
		HttpServletRequest  request = ServiceContextThreadLocal.getServiceContext().getRequest();
		boolean isLoggedIn = SessionParamUtil.getBoolean(request, "publik_logged_in");
		if (isLoggedIn) {
		    String id = SessionParamUtil.getString(request, "publik_internal_id");
		    
		    EventParticipation eventParticipationExist = null;
		    
		    try {
		    	eventParticipationExist = this.eventParticipationPersistence.findByPublikUserIdAndEventId(
		    			id, eventId);
			} catch (NoSuchEventParticipationException e) {
				// C'est ce qu'on espere
			}
		    // Si il n'est pas nul, on le supprime
		    if(eventParticipationExist != null) {
		    	this.eventParticipationLocalService.deleteEventParticipation(eventParticipationExist);
		    	
			    return success("participation deleted");
		    }
		    
			// Création de l'objet
			EventParticipation eventParticipation = this.eventParticipationLocalService.createEventParticipation();
			eventParticipation.setCreateDate(new Date());
			eventParticipation.setPublikUserId(id);
			eventParticipation.setEventId(eventId);
			eventParticipation.setGroupId(groupId);
			
			this.eventParticipationLocalService.updateEventParticipation(eventParticipation);
			
			return success("participation added");
			
		} else {
			return error("notConnected");
		}
	}
	
	/**
	 * Supprime une participation d'evenement d'un utilisateur
	 */
	@Override
	public JSONObject deleteEventParticipationLink(long eventId) {		
		HttpServletRequest  request = ServiceContextThreadLocal.getServiceContext().getRequest();
		boolean isLoggedIn = SessionParamUtil.getBoolean(request, "publik_logged_in");
		if (isLoggedIn) {
		    String id = SessionParamUtil.getString(request, "publik_internal_id");
		    
		    EventParticipation eventParticipation = null;
		    
			try {
				eventParticipation = this.eventParticipationPersistence.findByPublikUserIdAndEventId(id, eventId);
			} catch (NoSuchEventParticipationException e) {
				// Possiblement plusieurs onglets d'ouvert et déjà supprimé sur l'un d'eux
				return success("participation deleted");
			}
			
			try {
				this.eventParticipationLocalService.deleteEventParticipation(eventParticipation.getEventParticipationId());
			} catch (PortalException e) {
				return error("unknown error");
			}
			
			return success("participation deleted");
		} else {
			return error("notConnected");
		}
	}
	
	/**
	 * Verifie si l'utilisateur courant participe a l'evenement
	 */
	@Override
	public JSONObject isUserParticipates(long eventId) {		
		HttpServletRequest  request = ServiceContextThreadLocal.getServiceContext().getRequest();
		boolean isLoggedIn = SessionParamUtil.getBoolean(request, "publik_logged_in");
		if (isLoggedIn) {
		    String id = SessionParamUtil.getString(request, "publik_internal_id");
		    
			try {
				this.eventParticipationPersistence.findByPublikUserIdAndEventId(id, eventId);
			} catch (NoSuchEventParticipationException e) {
				// Non trouve, l'utilisateur ne participe pas
				return success("false");
			}
			// Element trouve, l'utilisateur participe
		    return success("true");
			
		} else {
			return error("notConnected");
		}
	}
	
	private boolean isAuthorized() {
		try {
			Company defaultCompany = CompanyLocalServiceUtil.getCompanyByWebId("liferay.com");
			long globalGroupId = defaultCompany.getGroup().getGroupId();
			return this.getPermissionChecker().hasPermission(globalGroupId, StrasbourgPortletKeys.NOTIFICATION_BO,
					StrasbourgPortletKeys.NOTIFICATION_BO, "CONTRIBUTE");
		} catch (PortalException e) {
			return false;
		}
	}
	
	private JSONObject success(String message) {
		return JSONFactoryUtil.createJSONObject().put("success", message);
	}

	private JSONObject error(String message) {
		return JSONFactoryUtil.createJSONObject().put("error", message);
	}
	
}