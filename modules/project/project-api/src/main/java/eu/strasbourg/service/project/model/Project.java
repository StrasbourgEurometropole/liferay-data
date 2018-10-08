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
	* Retourne la liste des follower au projet
	*/
	public java.util.List<eu.strasbourg.service.project.model.ProjectFollowed> getProjectFollower();

	/**
	* Retourne le nombre de follower au projet
	*/
	public int getNbFollower();

	/**
	* Retourne le label de 5 digits du nombre de follower au projet
	*/
	public java.lang.String getNbFollowerLabel();

	/**
	* Retourne l'AssetEntry rattaché cet item
	*/
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry();

	/**
	* Retourne la liste des lieux placit liés au projet
	*/
	public java.util.List<eu.strasbourg.service.project.model.PlacitPlace> getPlacitPlaces();

	/**
	* Retourne les noms des lieux placit au projet
	*/
	public java.util.List<java.lang.String> getPlaceNames(
		java.util.Locale locale);

	/**
	* Retourne les ids SIG des lieux placit au projet
	*/
	public java.util.List<java.lang.String> getPlaceSIGIds(
		java.util.Locale locale);

	/**
	* Renvoie la liste des AssetCategory rattachées à cet item (via
	* l'assetEntry)
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories();

	/**
	* Retourne les catégories 'Territoire' correspondant aux pays du projet
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getTerritoryCategories();

	/**
	* Retourne les sous-catégories 'Territoire' correspondant aux villes du projet
	*
	* @return : la liste des catégories
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCityCategories();

	/**
	* Retourne les sous-sous-catégories 'Territoire' correspondant aux quartiers du projet
	*
	* @return : la liste des catégories
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getDistrictCategories();

	/**
	* Retourne les quartiers du projet
	*/
	public java.lang.String getDistrictCategories(java.util.Locale locale);

	/**
	* Retourne une chaine des 'Territoires' correspondant aux quartiers du projet
	*
	* @return : Chaine des quartiers ou description "Aucun" ou "Tous"
	*/
	public java.lang.String getDistrictLabel(java.util.Locale locale);

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

	/**
	* Retourne l'asset category du projet (normalement du même non que le projet)
	*/
	public com.liferay.asset.kernel.model.AssetCategory getProjectCategory();

	/**
	* Retourne la liste des participations du projet
	*/
	public java.util.List<eu.strasbourg.service.project.model.Participation> getParticipations();

	public java.util.List<eu.strasbourg.service.project.model.Petition> getPetitions();

	/**
	* Retourne la liste des évènements du projet
	*/
	public java.util.List<eu.strasbourg.service.agenda.model.Event> getEvents();

	/**
	* Retourne les thematiques du projet
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getThematicCategories();

	/**
	* Retourne les commentaires de l'entité
	*/
	public java.util.List<eu.strasbourg.service.comment.model.Comment> getApprovedComments();

	/**
	* Retourne le nombre de commentaires de l'entité
	*/
	public int getNbApprovedComments();

	/**
	* Retourne la version JSON de l'entité
	*/
	public com.liferay.portal.kernel.json.JSONObject toJSON();
}