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

package eu.strasbourg.service.ejob.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.ejob.model.Alert;
import eu.strasbourg.service.ejob.service.base.AlertServiceBaseImpl;
import eu.strasbourg.utils.StringHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * The implementation of the alert remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>eu.strasbourg.service.ejob.service.AlertService</code> interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AlertServiceBaseImpl
 */
public class AlertServiceImpl extends AlertServiceBaseImpl {

	public static final String ERROR_ALERT_NEEDS_NAME = "alertNeedsName";
	public static final int NAME_SIZE = 75;

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use <code>eu.strasbourg.service.ejob.service.AlertServiceUtil</code> to access the alert remote service.
	 */

	/**
	 * Créer une alerte à un utilisateur
	 */
	@Override
	public JSONObject addAlert(String name, String categoriesId, String keyword, String languageId) {
		HttpServletRequest request = ServiceContextThreadLocal.getServiceContext().getRequest();

		boolean isLoggedIn = SessionParamUtil.getBoolean(request, "publik_logged_in");
		if (isLoggedIn) {
			String id = SessionParamUtil.getString(request, "publik_internal_id");

			try {
				ServiceContext sc = ServiceContextFactory.getInstance(request);

				// on vérifie si une alerte du même nom pour cet utilisateur n'existe pas déjà
				List<Alert> alerts = this.alertLocalService.findByPublikUserId(id);
				if(alerts.size() > 0 && alerts.stream().anyMatch(a -> StringHelper.compareIgnoringAccentuation(a.getName().toLowerCase(), name.toLowerCase())))
					return error("alreadyExist");
				// on vérifie la taille du nom de l'alerte
				if (name.length() < 1)
					return error(ERROR_ALERT_NEEDS_NAME);

				// Création de l'objet
				// Limite la taille du nom à la taille du champ en base
				String resizedName = name.length() > NAME_SIZE ? name.substring(0, NAME_SIZE - 1) : name;
				Alert alert = this.alertLocalService.createAlert(sc);
				alert.setName(resizedName);
				alert.setKeyWord(keyword);
				alert.setPublikUserId(id);
				alert.setLanguage(languageId);

				this.alertLocalService.updateAlert(alert);

				long[] categoryIds = new long[0];
				if(Validator.isNotNull(categoriesId)) {
					String[] categoriesIdString = categoriesId.split(",");
					categoryIds = new long[categoriesIdString.length];
					for (int i = 0; i < categoryIds.length; i++) {
						categoryIds[i] = Long.parseLong(categoriesIdString[i]);
					}
				}

				this.assetEntryLocalService.updateEntry(sc.getUserId(),
						alert.getGroupId(), Alert.class.getName(),
						alert.getAlertId(), categoryIds, null);
			} catch (PortalException e) {
				_log.error(e.getMessage(), e);
			}

			return success("favorite added");
		} else {
			return error("notConnected");
		}
	}

	private JSONObject success(String message) {
		return JSONFactoryUtil.createJSONObject().put("success", message);
	}

	private JSONObject error(String message) {
		return JSONFactoryUtil.createJSONObject().put("error", message);
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}