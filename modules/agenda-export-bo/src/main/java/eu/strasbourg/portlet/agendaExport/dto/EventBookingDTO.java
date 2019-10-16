package eu.strasbourg.portlet.agendaExport.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "booking")
public class EventBookingDTO {

    @XmlElement(name = "bookingDescription")
    private String bookingDescription;

    @XmlElement(name = "bookingUrl")
    private String bookingUrl;

    @XmlElement(name = "subscriptionUrl")
    private String subscriptionUrl;

    public EventBookingDTO(String bookingDescription, String bookingUrl, String subscriptionUrl) {
        this.bookingDescription = bookingDescription;
        this.bookingUrl = bookingUrl;
        this.subscriptionUrl = subscriptionUrl;
    }

    public String getBookingDescription() {
        return bookingDescription;
    }

    public void setBookingDescription(String bookingDescription) {
        this.bookingDescription = bookingDescription;
    }

    public String getBookingUrl() {
        return bookingUrl;
    }

    public void setBookingUrl(String bookingUrl) {
        this.bookingUrl = bookingUrl;
    }

    public String getSubscriptionUrl() {
        return subscriptionUrl;
    }

    public void setSubscriptionUrl(String subscriptionUrl) {
        this.subscriptionUrl = subscriptionUrl;
    }
}
