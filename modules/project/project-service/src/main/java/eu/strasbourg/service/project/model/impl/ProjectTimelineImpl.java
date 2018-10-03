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

package eu.strasbourg.service.project.model.impl;

import java.text.DateFormat;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.HtmlUtil;

import aQute.bnd.annotation.ProviderType;

/**
 * The extended model implementation for the ProjectTimeline service. Represents a row in the &quot;project_ProjectTimeline&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.project.model.ProjectTimeline} interface.
 * </p>
 *
 * @author Cedric Henry
 */
@ProviderType
public class ProjectTimelineImpl extends ProjectTimelineBaseImpl {

	private static final long serialVersionUID = -7959920725497646087L;

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a project timeline model instance should use the {@link eu.strasbourg.service.project.model.ProjectTimeline} interface instead.
	 */
	public ProjectTimelineImpl() {
	}
	
	/**
	 * Retourne la version JSON de l'entité
	 */
	@Override
	public JSONObject toJSON() {
		// Initialisation des variables tempons et résultantes
		JSONObject jsonProjectTimeline = JSONFactoryUtil.createJSONObject();
		DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat("yyyy-MM-dd");
		
		// Champs de gestion
		jsonProjectTimeline.put("id", this.getProjectTimelineId());
		
		// Champs : Autres
		jsonProjectTimeline.put("startDay", this.getStartDay());
		jsonProjectTimeline.put("date", dateFormat.format(this.getDate()));
		jsonProjectTimeline.put("title", HtmlUtil.escapeJS(this.getTitle()));
		jsonProjectTimeline.put("link", this.getLink());
		
		return jsonProjectTimeline;
	}
	
}