package eu.strasbourg.portlet.familySpace;

import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Element;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Person {

    public String lastName;
    public String firstName;
    public Boolean hasLunchBooked;
    public LocalDate firstBookingDate;
    public LocalDate lastBookingDate;

    public Person(JSONObject json) {
        lastName = json.getString("nomPersonne");
        firstName = json.getString("prenomPersonne");
        hasLunchBooked = json.getBoolean("aRepasReserve");
        if (hasLunchBooked) {
            firstBookingDate = LocalDate.parse(json.getString("datePremiereReservation"),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            lastBookingDate = LocalDate.parse(json.getString("dateDerniereReservation"),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
    }

    public Person() {
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Boolean getHasLunchBooked() {
        return hasLunchBooked;
    }

    public void setHasLunchBooked(Boolean hasLunchBooked) {
        this.hasLunchBooked = hasLunchBooked;
    }

    public LocalDate getFirstBookingDate() {
        return firstBookingDate;
    }

    public void setFirstBookingDate(LocalDate firstBookingDate) {
        this.firstBookingDate = firstBookingDate;
    }

    public LocalDate getLastBookingDate() {
        return lastBookingDate;
    }

    public void setLastBookingDate(LocalDate lastBookingDate) {
        this.lastBookingDate = lastBookingDate;
    }
}
