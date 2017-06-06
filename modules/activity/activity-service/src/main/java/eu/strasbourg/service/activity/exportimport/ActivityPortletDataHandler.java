package eu.strasbourg.service.activity.exportimport;

import java.util.List;

import javax.portlet.PortletPreferences;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.exportimport.kernel.lar.BasePortletDataHandler;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataHandler;
import com.liferay.exportimport.kernel.lar.PortletDataHandlerBoolean;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.xstream.XStreamAliasRegistryUtil;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.xml.Element;

import eu.strasbourg.service.activity.model.Activity;
import eu.strasbourg.service.activity.model.ActivityCourse;
import eu.strasbourg.service.activity.model.ActivityCoursePlace;
import eu.strasbourg.service.activity.model.ActivityCourseSchedule;
import eu.strasbourg.service.activity.model.ActivityOrganizer;
import eu.strasbourg.service.activity.model.impl.ActivityImpl;
import eu.strasbourg.service.activity.service.ActivityCourseLocalService;
import eu.strasbourg.service.activity.service.ActivityCoursePlaceLocalService;
import eu.strasbourg.service.activity.service.ActivityCourseScheduleLocalService;
import eu.strasbourg.service.activity.service.ActivityLocalService;
import eu.strasbourg.service.activity.service.ActivityOrganizerLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = { "javax.portlet.name=" + StrasbourgPortletKeys.ACTIVITY_BO },
	service = PortletDataHandler.class)
public class ActivityPortletDataHandler extends BasePortletDataHandler {

	private ActivityLocalService activityLocalService;
	
	private ActivityCourseLocalService activityCourseLocalService;
	
	private ActivityCoursePlaceLocalService activityCoursePlaceLocalService;
	
	private ActivityCourseScheduleLocalService activityCourseScheduleLocalService;
	
	private ActivityOrganizerLocalService activityOrganizerLocalService;
	
	public static final String NAMESPACE = "strasbourg-activity";

	public static final String SCHEMA_VERSION = "1.0.0";

	@Reference(unbind = "-")
	public void setActivityLocalService(ActivityLocalService activityLocalService) {
		this.activityLocalService = activityLocalService;
	}

	@Reference(unbind = "-")
	public void setActivityCourseLocalService(
		ActivityCourseLocalService activityCourseLocalService) {
		this.activityCourseLocalService = activityCourseLocalService;
	}

	@Reference(unbind = "-")
	public void setActivityCoursePlaceLocalService(
		ActivityCoursePlaceLocalService activityCoursePlaceLocalService) {
		this.activityCoursePlaceLocalService = activityCoursePlaceLocalService;
	}

	@Reference(unbind = "-")
	public void setActivityCourseScheduleLocalService(
		ActivityCourseScheduleLocalService activityCourseScheduleLocalService) {
		this.activityCourseScheduleLocalService = activityCourseScheduleLocalService;
	}

	@Reference(unbind = "-")
	public void setActivityOrganizerLocalService(
		ActivityOrganizerLocalService activityOrganizerLocalService) {
		this.activityOrganizerLocalService = activityOrganizerLocalService;
	}

	@Override
	public String getSchemaVersion() {
		return SCHEMA_VERSION;
	}

	@Activate
	protected void activate() {
		setExportControls(
			new PortletDataHandlerBoolean(NAMESPACE, "Activity entity", true,
				false, null, Activity.class.getName()),
			new PortletDataHandlerBoolean(NAMESPACE, "Activity Course entity",
				true, false, null, ActivityCourse.class.getName()),
			new PortletDataHandlerBoolean(NAMESPACE,
				"Activity Course Place entity", true, false, null,
				ActivityCoursePlace.class.getName()),
			new PortletDataHandlerBoolean(NAMESPACE,
				"Activity Course Schedule entity", true, false, null,
				ActivityCourseSchedule.class.getName()),
			new PortletDataHandlerBoolean(NAMESPACE, "Activity Organizer entity",
				true, false, null, ActivityOrganizer.class.getName()));

		XStreamAliasRegistryUtil.register(ActivityImpl.class, "Activity");
		XStreamAliasRegistryUtil.register(ActivityCourse.class,
			"ActivityCourse");
		XStreamAliasRegistryUtil.register(ActivityCoursePlace.class,
			"ActivityCoursePlace");
		XStreamAliasRegistryUtil.register(ActivityCourseSchedule.class,
			"ActivityCourseSchedule");
		XStreamAliasRegistryUtil.register(ActivityOrganizer.class,
			"ActivityOrganizer");
	}

	@Override
	protected String doExportData(PortletDataContext portletDataContext,
		String portletId, PortletPreferences portletPreferences)
		throws Exception {
		Element rootElement = addExportDataRootElement(portletDataContext);

		rootElement.addAttribute("group-id",
			String.valueOf(portletDataContext.getScopeGroupId()));

		// Si la checkbox correspondant au type à exporté est décochée, on ne
		// fait rien
		if (portletDataContext.getBooleanParameter(NAMESPACE,
			"Activity entity")) {
			ExportActionableDynamicQuery entryActionableDynamicQuery = this.activityLocalService
				.getExportActionableDynamicQuery(portletDataContext);
			entryActionableDynamicQuery
				.setGroupId(portletDataContext.getScopeGroupId());
			entryActionableDynamicQuery.performActions();
		}
		if (portletDataContext.getBooleanParameter(NAMESPACE,
			"ActivityCourse entity")) {
			ExportActionableDynamicQuery entryActionableDynamicQuery = this.activityCourseLocalService
				.getExportActionableDynamicQuery(portletDataContext);
			entryActionableDynamicQuery
				.setGroupId(portletDataContext.getScopeGroupId());
			entryActionableDynamicQuery.performActions();
		}
		if (portletDataContext.getBooleanParameter(NAMESPACE,
			"ActivityCoursePlace entity")) {
			ExportActionableDynamicQuery entryActionableDynamicQuery = this.activityCoursePlaceLocalService
				.getExportActionableDynamicQuery(portletDataContext);
			entryActionableDynamicQuery
				.setGroupId(portletDataContext.getScopeGroupId());
			entryActionableDynamicQuery.performActions();
		}
		if (portletDataContext.getBooleanParameter(NAMESPACE,
			"ActivityCourseSchedule entity")) {
			ExportActionableDynamicQuery entryActionableDynamicQuery = this.activityCourseScheduleLocalService
				.getExportActionableDynamicQuery(portletDataContext);
			entryActionableDynamicQuery
				.setGroupId(portletDataContext.getScopeGroupId());
			entryActionableDynamicQuery.performActions();
		}
		if (portletDataContext.getBooleanParameter(NAMESPACE,
			"ActivityOrganizer entity")) {
			ExportActionableDynamicQuery entryActionableDynamicQuery = this.activityOrganizerLocalService
				.getExportActionableDynamicQuery(portletDataContext);
			entryActionableDynamicQuery
				.setGroupId(portletDataContext.getScopeGroupId());
			entryActionableDynamicQuery.performActions();
		}

		return getExportDataRootElementString(rootElement);
	}

	@Override
	protected PortletPreferences doImportData(
		PortletDataContext portletDataContext, String portletId,
		PortletPreferences portletPreferences, String data) throws Exception {

		Element activitiesElement = portletDataContext
			.getImportDataGroupElement(Activity.class);

		List<Element> activitiesElements = activitiesElement.elements();
		for (Element activityElement : activitiesElements) {
			StagedModelDataHandlerUtil.importStagedModel(portletDataContext,
				activityElement);
		}
		
		Element activityCoursesElement = portletDataContext
			.getImportDataGroupElement(Activity.class);

		List<Element> activityCoursesElements = activityCoursesElement.elements();
		for (Element activityCourseElement : activityCoursesElements) {
			StagedModelDataHandlerUtil.importStagedModel(portletDataContext,
				activityCourseElement);
		}

		Element activityCoursePlacesElement = portletDataContext
			.getImportDataGroupElement(Activity.class);

		List<Element> activityCoursePlacesElements = activityCoursePlacesElement.elements();
		for (Element activityElement : activityCoursePlacesElements) {
			StagedModelDataHandlerUtil.importStagedModel(portletDataContext,
				activityElement);
		}

		Element activityCourseSchedulesElement = portletDataContext
			.getImportDataGroupElement(Activity.class);

		List<Element> activityCourseSchedulesElements = activityCourseSchedulesElement.elements();
		for (Element activityElement : activityCourseSchedulesElements) {
			StagedModelDataHandlerUtil.importStagedModel(portletDataContext,
				activityElement);
		}

		Element activityOrganizersElement = portletDataContext
			.getImportDataGroupElement(Activity.class);

		List<Element> activityOrganizersElements = activityOrganizersElement.elements();
		for (Element activityElement : activityOrganizersElements) {
			StagedModelDataHandlerUtil.importStagedModel(portletDataContext,
				activityElement);
		}

		return null;
	}

}
