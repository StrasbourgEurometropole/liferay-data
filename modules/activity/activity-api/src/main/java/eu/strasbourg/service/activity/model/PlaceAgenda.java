package eu.strasbourg.service.activity.model;

import java.util.List;
import java.util.stream.Collectors;

import eu.strasbourg.service.activity.model.ActivityCourseSchedule;

/**
 * Classe facilitant l'affichage des cours d'un lieu
 * Lieu > Périodes > Cours > Horaires
 */
public class PlaceAgenda {
	private List<Period> periods;
	
	public List<Period> getPeriods() {
		return periods;
	}
	public void setPeriods(List<Period> periods) {
		this.periods = periods;
	}

	// Périodes
	public static class Period {
		private long periodId;
		private String periodName;
		private List<Course> courses;
		
		public Period(long periodId, String periodName) {
			this.periodId = periodId;
			this.periodName = periodName;
		}

		public long getPeriodId() {
			return periodId;
		}

		public void setPeriodId(long periodId) {
			this.periodId = periodId;
		}

		public String getPeriodName() {
			return periodName;
		}

		public void setPeriodName(String periodName) {
			this.periodName = periodName;
		}

		public List<Course> getCourses() {
			return courses;
		}

		public void setCourses(List<Course> courses) {
			this.courses = courses;
		}
	}
	
	// Cours
	public static class Course {
		private long courseId;
		private String courseName;
		private List<ActivityCourseSchedule> schedules;

		public Course(long courseId, String courseName) {
			this.courseId = courseId;
			this.courseName = courseName;
		}
		
		public String getDayName(int day) {
			return new String[] { "monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"}[day];
		}
		
		public List<ActivityCourseSchedule> getSchedulesForDay(int day) {
			return schedules.stream().filter(s -> s.hasScheduleOnDay(day)).collect(Collectors.toList());
		}

		public long getCourseId() {
			return courseId;
		}

		public void setCoursePlaceId(long coursePlaceId) {
			this.courseId = coursePlaceId;
		}

		
		public String getCourseName() {
			return courseName;
		}

		public void setCourseName(String courseName) {
			this.courseName = courseName;
		}

		public List<ActivityCourseSchedule> getSchedules() {
			return schedules;
		}

		public void setSchedules(List<ActivityCourseSchedule> schedules) {
			this.schedules = schedules;
		}

	}

}
