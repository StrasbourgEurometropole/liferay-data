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

package eu.strasbourg.service.project.service.base;

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
import eu.strasbourg.service.project.model.ProjectTimeline;
import eu.strasbourg.service.project.service.ProjectTimelineService;
import eu.strasbourg.service.project.service.persistence.ParticipationPersistence;
import eu.strasbourg.service.project.service.persistence.PetitionPersistence;
import eu.strasbourg.service.project.service.persistence.PlacitPlacePersistence;
import eu.strasbourg.service.project.service.persistence.ProjectFollowedPersistence;
import eu.strasbourg.service.project.service.persistence.ProjectPersistence;
import eu.strasbourg.service.project.service.persistence.ProjectTimelinePersistence;
import eu.strasbourg.service.project.service.persistence.SignatairePersistence;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the project timeline remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link eu.strasbourg.service.project.service.impl.ProjectTimelineServiceImpl}.
 * </p>
 *
 * @author Cedric Henry
 * @see eu.strasbourg.service.project.service.impl.ProjectTimelineServiceImpl
 * @see eu.strasbourg.service.project.service.ProjectTimelineServiceUtil
 * @generated
 */
public abstract class ProjectTimelineServiceBaseImpl extends BaseServiceImpl
	implements ProjectTimelineService, IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link eu.strasbourg.service.project.service.ProjectTimelineServiceUtil} to access the project timeline remote service.
	 */

	/**
	 * Returns the participation local service.
	 *
	 * @return the participation local service
	 */
	public eu.strasbourg.service.project.service.ParticipationLocalService getParticipationLocalService() {
		return participationLocalService;
	}

	/**
	 * Sets the participation local service.
	 *
	 * @param participationLocalService the participation local service
	 */
	public void setParticipationLocalService(
		eu.strasbourg.service.project.service.ParticipationLocalService participationLocalService) {
		this.participationLocalService = participationLocalService;
	}

	/**
	 * Returns the participation remote service.
	 *
	 * @return the participation remote service
	 */
	public eu.strasbourg.service.project.service.ParticipationService getParticipationService() {
		return participationService;
	}

	/**
	 * Sets the participation remote service.
	 *
	 * @param participationService the participation remote service
	 */
	public void setParticipationService(
		eu.strasbourg.service.project.service.ParticipationService participationService) {
		this.participationService = participationService;
	}

	/**
	 * Returns the participation persistence.
	 *
	 * @return the participation persistence
	 */
	public ParticipationPersistence getParticipationPersistence() {
		return participationPersistence;
	}

	/**
	 * Sets the participation persistence.
	 *
	 * @param participationPersistence the participation persistence
	 */
	public void setParticipationPersistence(
		ParticipationPersistence participationPersistence) {
		this.participationPersistence = participationPersistence;
	}

	/**
	 * Returns the petition local service.
	 *
	 * @return the petition local service
	 */
	public eu.strasbourg.service.project.service.PetitionLocalService getPetitionLocalService() {
		return petitionLocalService;
	}

	/**
	 * Sets the petition local service.
	 *
	 * @param petitionLocalService the petition local service
	 */
	public void setPetitionLocalService(
		eu.strasbourg.service.project.service.PetitionLocalService petitionLocalService) {
		this.petitionLocalService = petitionLocalService;
	}

	/**
	 * Returns the petition remote service.
	 *
	 * @return the petition remote service
	 */
	public eu.strasbourg.service.project.service.PetitionService getPetitionService() {
		return petitionService;
	}

	/**
	 * Sets the petition remote service.
	 *
	 * @param petitionService the petition remote service
	 */
	public void setPetitionService(
		eu.strasbourg.service.project.service.PetitionService petitionService) {
		this.petitionService = petitionService;
	}

	/**
	 * Returns the petition persistence.
	 *
	 * @return the petition persistence
	 */
	public PetitionPersistence getPetitionPersistence() {
		return petitionPersistence;
	}

	/**
	 * Sets the petition persistence.
	 *
	 * @param petitionPersistence the petition persistence
	 */
	public void setPetitionPersistence(PetitionPersistence petitionPersistence) {
		this.petitionPersistence = petitionPersistence;
	}

	/**
	 * Returns the placit place local service.
	 *
	 * @return the placit place local service
	 */
	public eu.strasbourg.service.project.service.PlacitPlaceLocalService getPlacitPlaceLocalService() {
		return placitPlaceLocalService;
	}

	/**
	 * Sets the placit place local service.
	 *
	 * @param placitPlaceLocalService the placit place local service
	 */
	public void setPlacitPlaceLocalService(
		eu.strasbourg.service.project.service.PlacitPlaceLocalService placitPlaceLocalService) {
		this.placitPlaceLocalService = placitPlaceLocalService;
	}

	/**
	 * Returns the placit place remote service.
	 *
	 * @return the placit place remote service
	 */
	public eu.strasbourg.service.project.service.PlacitPlaceService getPlacitPlaceService() {
		return placitPlaceService;
	}

	/**
	 * Sets the placit place remote service.
	 *
	 * @param placitPlaceService the placit place remote service
	 */
	public void setPlacitPlaceService(
		eu.strasbourg.service.project.service.PlacitPlaceService placitPlaceService) {
		this.placitPlaceService = placitPlaceService;
	}

	/**
	 * Returns the placit place persistence.
	 *
	 * @return the placit place persistence
	 */
	public PlacitPlacePersistence getPlacitPlacePersistence() {
		return placitPlacePersistence;
	}

	/**
	 * Sets the placit place persistence.
	 *
	 * @param placitPlacePersistence the placit place persistence
	 */
	public void setPlacitPlacePersistence(
		PlacitPlacePersistence placitPlacePersistence) {
		this.placitPlacePersistence = placitPlacePersistence;
	}

	/**
	 * Returns the project local service.
	 *
	 * @return the project local service
	 */
	public eu.strasbourg.service.project.service.ProjectLocalService getProjectLocalService() {
		return projectLocalService;
	}

	/**
	 * Sets the project local service.
	 *
	 * @param projectLocalService the project local service
	 */
	public void setProjectLocalService(
		eu.strasbourg.service.project.service.ProjectLocalService projectLocalService) {
		this.projectLocalService = projectLocalService;
	}

	/**
	 * Returns the project remote service.
	 *
	 * @return the project remote service
	 */
	public eu.strasbourg.service.project.service.ProjectService getProjectService() {
		return projectService;
	}

	/**
	 * Sets the project remote service.
	 *
	 * @param projectService the project remote service
	 */
	public void setProjectService(
		eu.strasbourg.service.project.service.ProjectService projectService) {
		this.projectService = projectService;
	}

	/**
	 * Returns the project persistence.
	 *
	 * @return the project persistence
	 */
	public ProjectPersistence getProjectPersistence() {
		return projectPersistence;
	}

	/**
	 * Sets the project persistence.
	 *
	 * @param projectPersistence the project persistence
	 */
	public void setProjectPersistence(ProjectPersistence projectPersistence) {
		this.projectPersistence = projectPersistence;
	}

	/**
	 * Returns the project followed local service.
	 *
	 * @return the project followed local service
	 */
	public eu.strasbourg.service.project.service.ProjectFollowedLocalService getProjectFollowedLocalService() {
		return projectFollowedLocalService;
	}

	/**
	 * Sets the project followed local service.
	 *
	 * @param projectFollowedLocalService the project followed local service
	 */
	public void setProjectFollowedLocalService(
		eu.strasbourg.service.project.service.ProjectFollowedLocalService projectFollowedLocalService) {
		this.projectFollowedLocalService = projectFollowedLocalService;
	}

	/**
	 * Returns the project followed remote service.
	 *
	 * @return the project followed remote service
	 */
	public eu.strasbourg.service.project.service.ProjectFollowedService getProjectFollowedService() {
		return projectFollowedService;
	}

	/**
	 * Sets the project followed remote service.
	 *
	 * @param projectFollowedService the project followed remote service
	 */
	public void setProjectFollowedService(
		eu.strasbourg.service.project.service.ProjectFollowedService projectFollowedService) {
		this.projectFollowedService = projectFollowedService;
	}

	/**
	 * Returns the project followed persistence.
	 *
	 * @return the project followed persistence
	 */
	public ProjectFollowedPersistence getProjectFollowedPersistence() {
		return projectFollowedPersistence;
	}

	/**
	 * Sets the project followed persistence.
	 *
	 * @param projectFollowedPersistence the project followed persistence
	 */
	public void setProjectFollowedPersistence(
		ProjectFollowedPersistence projectFollowedPersistence) {
		this.projectFollowedPersistence = projectFollowedPersistence;
	}

	/**
	 * Returns the project timeline local service.
	 *
	 * @return the project timeline local service
	 */
	public eu.strasbourg.service.project.service.ProjectTimelineLocalService getProjectTimelineLocalService() {
		return projectTimelineLocalService;
	}

	/**
	 * Sets the project timeline local service.
	 *
	 * @param projectTimelineLocalService the project timeline local service
	 */
	public void setProjectTimelineLocalService(
		eu.strasbourg.service.project.service.ProjectTimelineLocalService projectTimelineLocalService) {
		this.projectTimelineLocalService = projectTimelineLocalService;
	}

	/**
	 * Returns the project timeline remote service.
	 *
	 * @return the project timeline remote service
	 */
	public ProjectTimelineService getProjectTimelineService() {
		return projectTimelineService;
	}

	/**
	 * Sets the project timeline remote service.
	 *
	 * @param projectTimelineService the project timeline remote service
	 */
	public void setProjectTimelineService(
		ProjectTimelineService projectTimelineService) {
		this.projectTimelineService = projectTimelineService;
	}

	/**
	 * Returns the project timeline persistence.
	 *
	 * @return the project timeline persistence
	 */
	public ProjectTimelinePersistence getProjectTimelinePersistence() {
		return projectTimelinePersistence;
	}

	/**
	 * Sets the project timeline persistence.
	 *
	 * @param projectTimelinePersistence the project timeline persistence
	 */
	public void setProjectTimelinePersistence(
		ProjectTimelinePersistence projectTimelinePersistence) {
		this.projectTimelinePersistence = projectTimelinePersistence;
	}

	/**
	 * Returns the signataire local service.
	 *
	 * @return the signataire local service
	 */
	public eu.strasbourg.service.project.service.SignataireLocalService getSignataireLocalService() {
		return signataireLocalService;
	}

	/**
	 * Sets the signataire local service.
	 *
	 * @param signataireLocalService the signataire local service
	 */
	public void setSignataireLocalService(
		eu.strasbourg.service.project.service.SignataireLocalService signataireLocalService) {
		this.signataireLocalService = signataireLocalService;
	}

	/**
	 * Returns the signataire remote service.
	 *
	 * @return the signataire remote service
	 */
	public eu.strasbourg.service.project.service.SignataireService getSignataireService() {
		return signataireService;
	}

	/**
	 * Sets the signataire remote service.
	 *
	 * @param signataireService the signataire remote service
	 */
	public void setSignataireService(
		eu.strasbourg.service.project.service.SignataireService signataireService) {
		this.signataireService = signataireService;
	}

	/**
	 * Returns the signataire persistence.
	 *
	 * @return the signataire persistence
	 */
	public SignatairePersistence getSignatairePersistence() {
		return signatairePersistence;
	}

	/**
	 * Sets the signataire persistence.
	 *
	 * @param signatairePersistence the signataire persistence
	 */
	public void setSignatairePersistence(
		SignatairePersistence signatairePersistence) {
		this.signatairePersistence = signatairePersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.kernel.service.ClassNameLocalService getClassNameLocalService() {
		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService) {
		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name remote service.
	 *
	 * @return the class name remote service
	 */
	public com.liferay.portal.kernel.service.ClassNameService getClassNameService() {
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
	public com.liferay.portal.kernel.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService getUserLocalService() {
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
		return ProjectTimelineService.class.getName();
	}

	protected Class<?> getModelClass() {
		return ProjectTimeline.class;
	}

	protected String getModelClassName() {
		return ProjectTimeline.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = projectTimelinePersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = eu.strasbourg.service.project.service.ParticipationLocalService.class)
	protected eu.strasbourg.service.project.service.ParticipationLocalService participationLocalService;
	@BeanReference(type = eu.strasbourg.service.project.service.ParticipationService.class)
	protected eu.strasbourg.service.project.service.ParticipationService participationService;
	@BeanReference(type = ParticipationPersistence.class)
	protected ParticipationPersistence participationPersistence;
	@BeanReference(type = eu.strasbourg.service.project.service.PetitionLocalService.class)
	protected eu.strasbourg.service.project.service.PetitionLocalService petitionLocalService;
	@BeanReference(type = eu.strasbourg.service.project.service.PetitionService.class)
	protected eu.strasbourg.service.project.service.PetitionService petitionService;
	@BeanReference(type = PetitionPersistence.class)
	protected PetitionPersistence petitionPersistence;
	@BeanReference(type = eu.strasbourg.service.project.service.PlacitPlaceLocalService.class)
	protected eu.strasbourg.service.project.service.PlacitPlaceLocalService placitPlaceLocalService;
	@BeanReference(type = eu.strasbourg.service.project.service.PlacitPlaceService.class)
	protected eu.strasbourg.service.project.service.PlacitPlaceService placitPlaceService;
	@BeanReference(type = PlacitPlacePersistence.class)
	protected PlacitPlacePersistence placitPlacePersistence;
	@BeanReference(type = eu.strasbourg.service.project.service.ProjectLocalService.class)
	protected eu.strasbourg.service.project.service.ProjectLocalService projectLocalService;
	@BeanReference(type = eu.strasbourg.service.project.service.ProjectService.class)
	protected eu.strasbourg.service.project.service.ProjectService projectService;
	@BeanReference(type = ProjectPersistence.class)
	protected ProjectPersistence projectPersistence;
	@BeanReference(type = eu.strasbourg.service.project.service.ProjectFollowedLocalService.class)
	protected eu.strasbourg.service.project.service.ProjectFollowedLocalService projectFollowedLocalService;
	@BeanReference(type = eu.strasbourg.service.project.service.ProjectFollowedService.class)
	protected eu.strasbourg.service.project.service.ProjectFollowedService projectFollowedService;
	@BeanReference(type = ProjectFollowedPersistence.class)
	protected ProjectFollowedPersistence projectFollowedPersistence;
	@BeanReference(type = eu.strasbourg.service.project.service.ProjectTimelineLocalService.class)
	protected eu.strasbourg.service.project.service.ProjectTimelineLocalService projectTimelineLocalService;
	@BeanReference(type = ProjectTimelineService.class)
	protected ProjectTimelineService projectTimelineService;
	@BeanReference(type = ProjectTimelinePersistence.class)
	protected ProjectTimelinePersistence projectTimelinePersistence;
	@BeanReference(type = eu.strasbourg.service.project.service.SignataireLocalService.class)
	protected eu.strasbourg.service.project.service.SignataireLocalService signataireLocalService;
	@BeanReference(type = eu.strasbourg.service.project.service.SignataireService.class)
	protected eu.strasbourg.service.project.service.SignataireService signataireService;
	@BeanReference(type = SignatairePersistence.class)
	protected SignatairePersistence signatairePersistence;
	@ServiceReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.ClassNameLocalService.class)
	protected com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.ClassNameService.class)
	protected com.liferay.portal.kernel.service.ClassNameService classNameService;
	@ServiceReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@ServiceReference(type = com.liferay.portal.kernel.service.ResourceLocalService.class)
	protected com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.UserLocalService.class)
	protected com.liferay.portal.kernel.service.UserLocalService userLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.UserService.class)
	protected com.liferay.portal.kernel.service.UserService userService;
	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
}