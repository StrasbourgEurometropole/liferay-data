package eu.strasbourg.portlet.agendaExport.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "place")
public class EventPlaceDTO {

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "streetNumber")
    private String streetNumber;

    @XmlElement(name = "streetName")
    private String streetName;

    @XmlElement(name = "zipCode")
    private String zipCode;

    @XmlElement(name = "city")
    private String city;

    @XmlElement(name = "country")
    private String country;

    @XmlElement(name = "access")
    private String access;

    @XmlElement(name = "handicapAccess")
    private String handicapAccess;

    @XmlElement(name = "blindAccess")
    private boolean blindAccess;

    @XmlElement(name = "wheelchairAccess")
    private boolean wheelchairAccess;

    @XmlElement(name = "deafAccess")
    private boolean deafAccess;

    @XmlElement(name = "elderAccess")
    private boolean elderAccess;

    @XmlElement(name = "deficientAccess")
    private boolean deficientAccess;

    public EventPlaceDTO(String name, String streetNumber, String streetName, String zipCode, String city, String country, String access, String handicapAccess, boolean blindAccess, boolean wheelchairAccess, boolean deafAccess, boolean elderAccess, boolean deficientAccess) {
        this.name = name;
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
        this.access = access;
        this.handicapAccess = handicapAccess;
        this.blindAccess = blindAccess;
        this.wheelchairAccess = wheelchairAccess;
        this.deafAccess = deafAccess;
        this.elderAccess = elderAccess;
        this.deficientAccess = deficientAccess;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getHandicapAccess() {
        return handicapAccess;
    }

    public void setHandicapAccess(String handicapAccess) {
        this.handicapAccess = handicapAccess;
    }

    public boolean isBlindAccess() {
        return blindAccess;
    }

    public void setBlindAccess(boolean blindAccess) {
        this.blindAccess = blindAccess;
    }

    public boolean isWheelchairAccess() {
        return wheelchairAccess;
    }

    public void setWheelchairAccess(boolean wheelchairAccess) {
        this.wheelchairAccess = wheelchairAccess;
    }

    public boolean isDeafAccess() {
        return deafAccess;
    }

    public void setDeafAccess(boolean deafAccess) {
        this.deafAccess = deafAccess;
    }

    public boolean isElderAccess() {
        return elderAccess;
    }

    public void setElderAccess(boolean elderAccess) {
        this.elderAccess = elderAccess;
    }

    public boolean isDeficientAccess() {
        return deficientAccess;
    }

    public void setDeficientAccess(boolean deficientAccess) {
        this.deficientAccess = deficientAccess;
    }
}
