package eu.strasbourg.service.activity.model;

import java.util.List;
import java.util.stream.Collectors;


public class CourseAgenda {
	private List<CoursePeriodAgenda> periods;

	public List<CoursePeriodAgenda> getPeriods() {
		return periods;
	}

	public void setPeriods(List<CoursePeriodAgenda> periods) {
		this.periods = periods;
	}
	
	public static class CoursePeriodAgenda {
		private long periodId;
		private String periodName;
		
		private List<CoursePlaceAgenda> places;
		
		public CoursePeriodAgenda(long periodId, String periodName) {
			this.setPeriodId(periodId);
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

		public List<CoursePlaceAgenda> getPlaces() {
			return places;
		}

		public void setPlaces(List<CoursePlaceAgenda> places) {
			this.places = places;
		}

	}

	public static class CoursePlaceAgenda {
		private long coursePlaceId;
		private String placeSigId;
		private String placeName;
		private List<ActivityCourseSchedule> schedules;

		public CoursePlaceAgenda(long coursePlaceId, String placeSigId, String placeName) {
			this.coursePlaceId = coursePlaceId;
			this.placeName = placeName;
			this.placeSigId = placeSigId;
		}
		
		public String getDayName(int day) {
			return new String[] { "monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"}[day];
		}
		
		public List<ActivityCourseSchedule> getSchedulesForDay(int day) {
			return schedules.stream().filter(s -> s.hasScheduleOnDay(day)).collect(Collectors.toList());
		}

		public long getCoursePlaceId() {
			return coursePlaceId;
		}

		public void setCoursePlaceId(long coursePlaceId) {
			this.coursePlaceId = coursePlaceId;
		}

		public String getPlaceSigId() {
			return placeSigId;
		}

		public void setPlaceSigId(String placeSigId) {
			this.placeSigId = placeSigId;
		}
		
		public String getPlaceName() {
			return placeName;
		}

		public void setPlaceName(String placeName) {
			this.placeName = placeName;
		}

		public List<ActivityCourseSchedule> getSchedules() {
			return schedules;
		}

		public void setSchedules(List<ActivityCourseSchedule> schedules) {
			this.schedules = schedules;
		}

	}


}
