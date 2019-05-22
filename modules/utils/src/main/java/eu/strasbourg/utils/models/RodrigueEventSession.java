package eu.strasbourg.utils.models;

import java.util.Date;

/**
 * Modele de resultat representant les session d'un evenement de l'API Rodrigue
 * @author cedric.henry
 */
public class RodrigueEventSession {
	
	private int eventID;
	private String eventName;
	private String eventCode;
	private String eventDescription1;
	private String eventDescription2;
	private String eventDescription3;
	private long sessionID;
	private Date sessionDate;
	private int placeID;
	private String placeName;
	private String placeCode;
	private int nbSeat;
	private int nbSeatMin;
	private int nbSeatMax;
	
	public int getEventID() {
		return eventID;
	}
	
	public void setEventID(int eventID) {
		this.eventID = eventID;
	}
	
	public String getEventName() {
		return eventName;
	}
	
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
	public String getEventCode() {
		return eventCode;
	}
	
	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}
	
	public String getEventDescription1() {
		return eventDescription1;
	}
	
	public void setEventDescription1(String eventDescription1) {
		this.eventDescription1 = eventDescription1;
	}
	
	public String getEventDescription2() {
		return eventDescription2;
	}
	
	public void setEventDescription2(String eventDescription2) {
		this.eventDescription2 = eventDescription2;
	}
	
	public String getEventDescription3() {
		return eventDescription3;
	}
	
	public void setEventDescription3(String eventDescription3) {
		this.eventDescription3 = eventDescription3;
	}
	
	public long getSessionID() {
		return sessionID;
	}
	
	public void setSessionID(long sessionID) {
		this.sessionID = sessionID;
	}
	
	public Date getSessionDate() {
		return sessionDate;
	}
	
	public void setSessionDate(Date sessionDate) {
		this.sessionDate = sessionDate;
	}
	
	public int getPlaceID() {
		return placeID;
	}
	
	public void setPlaceID(int placeID) {
		this.placeID = placeID;
	}
	
	public String getPlaceName() {
		return placeName;
	}
	
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	
	public String getPlaceCode() {
		return placeCode;
	}
	
	public void setPlaceCode(String placeCode) {
		this.placeCode = placeCode;
	}
	
	public int getNbSeat() {
		return nbSeat;
	}
	
	public void setNbSeat(int nbSeat) {
		this.nbSeat = nbSeat;
	}
	
	public int getNbSeatMin() {
		return nbSeatMin;
	}
	
	public void setNbSeatMin(int nbSeatMin) {
		this.nbSeatMin = nbSeatMin;
	}
	
	public int getNbSeatMax() {
		return nbSeatMax;
	}
	
	public void setNbSeatMax(int nbSeatMax) {
		this.nbSeatMax = nbSeatMax;
	}

	@Override
	public String toString() {
		return "RodrigueEventSession [eventID=" + eventID + ", eventName=" + eventName + ", eventCode=" + eventCode
				+ ", eventDescription1=" + eventDescription1 + ", eventDescription2=" + eventDescription2
				+ ", eventDescription3=" + eventDescription3 + ", sessionID=" + sessionID + ", sessionDate="
				+ sessionDate + ", placeID=" + placeID + ", placeName=" + placeName + ", placeCode=" + placeCode
				+ ", nbSeat=" + nbSeat + ", nbSeatMin=" + nbSeatMin + ", nbSeatMax=" + nbSeatMax + "]";
	}
	
}
