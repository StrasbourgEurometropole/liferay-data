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

package eu.strasbourg.service.project.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the Project service. Represents a row in the &quot;project_Project&quot; database table, with each column mapped to a property of this class.
 *
 * @author Cedric Henry
 * @see ProjectModel
 * @see eu.strasbourg.service.project.model.impl.ProjectImpl
 * @see eu.strasbourg.service.project.model.impl.ProjectModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.project.model.impl.ProjectImpl")
@ProviderType
public interface Project extends ProjectModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.project.model.impl.ProjectImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Project, Long> PROJECT_ID_ACCESSOR = new Accessor<Project, Long>() {
			@Override
			public Long get(Project project) {
				return project.getProjectId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Project> getTypeClass() {
				return Project.class;
			}
		};

	/**
	* Retourne l'AssetEntry rattaché cet item
	*/
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry();

	/**
	* Renvoie la liste des AssetCategory rattachées à cet item (via
	* l'assetEntry)
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories();

	/**
	* Retourne l'URL de l'image à partir de l'id du DLFileEntry
	*/
	public java.lang.String getImageURL();

	/**
	* Retourne le copyright de l'image principale
	*/
	public java.lang.String getImageCopyright(java.util.Locale locale);

	/**
	* Retourne la liste des entrées timelines du projet
	*/
	public java.util.List<eu.strasbourg.service.project.model.ProjectTimeline> getProjectTimelines();

	public java.lang.String getProjectStatus(java.util.Locale locale);

	/**
	* Retourne les statuts du projet
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getAllStatus();
}