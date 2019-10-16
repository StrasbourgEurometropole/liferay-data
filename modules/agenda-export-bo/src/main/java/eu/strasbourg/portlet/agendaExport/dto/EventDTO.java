package eu.strasbourg.portlet.agendaExport.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "event")
public class EventDTO {

    @XmlElement(name = "title")
    private String title;

    @XmlElement(name = "subtitle")
    private String subtitle;

    @XmlElement(name = "description")
    private String description;

    @XmlElement(name = "manifestations")
    private String manifestations;

    @XmlElement(name = "periods")
    private String periods;

    @XmlElement(name = "firstStartDate")
    private String firstStartDate;

    @XmlElement(name = "lastEndDate")
    private String lastEndDate;

    @XmlElement(name = "image")
    private String image;

    @XmlElement(name = "imageCopyright")
    private String imageCopyright;

    @XmlElement(name = "place")
    private EventPlaceDTO place;

    @XmlElement(name = "contact")
    private EventContactDTO contact;

    @XmlElement(name = "concert")
    private EventConcertDTO concert;

    @XmlElement(name = "price")
    private EventPriceDTO price;

    @XmlElement(name = "booking")
    private EventBookingDTO booking;

    @XmlElementWrapper(name = "categories")
    @XmlElement(name = "category")
    private List<EventVocabularyDTO> categories;

    @XmlElementWrapper(name = "tags")
    @XmlElement(name = "tag")
    private List<String> tags;

    public EventDTO(String title, String subtitle, String description, String manifestations, String periods, String firstStartDate, String lastEndDate, String image, String imageCopyright, EventPlaceDTO place, EventContactDTO contact, EventConcertDTO concert, EventPriceDTO price, EventBookingDTO booking, List<EventVocabularyDTO> categories, List<String> tags) {
        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
        this.manifestations = manifestations;
        this.periods = periods;
        this.firstStartDate = firstStartDate;
        this.lastEndDate = lastEndDate;
        this.image = image;
        this.imageCopyright = imageCopyright;
        this.place = place;
        this.contact = contact;
        this.concert = concert;
        this.price = price;
        this.booking = booking;
        this.categories = categories;
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManifestations() {
        return manifestations;
    }

    public void setManifestations(String manifestations) {
        this.manifestations = manifestations;
    }

    public String getPeriods() {
        return periods;
    }

    public void setPeriods(String periods) {
        this.periods = periods;
    }

    public String getFirstStartDate() {
        return firstStartDate;
    }

    public void setFirstStartDate(String firstStartDate) {
        this.firstStartDate = firstStartDate;
    }

    public String getLastEndDate() {
        return lastEndDate;
    }

    public void setLastEndDate(String lastEndDate) {
        this.lastEndDate = lastEndDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageCopyright() {
        return imageCopyright;
    }

    public void setImageCopyright(String imageCopyright) {
        this.imageCopyright = imageCopyright;
    }

    public EventPlaceDTO getPlace() {
        return place;
    }

    public void setPlace(EventPlaceDTO place) {
        this.place = place;
    }

    public EventContactDTO getContact() {
        return contact;
    }

    public void setContact(EventContactDTO contact) {
        this.contact = contact;
    }

    public EventConcertDTO getConcert() {
        return concert;
    }

    public void setConcert(EventConcertDTO concert) {
        this.concert = concert;
    }

    public EventPriceDTO getPrice() {
        return price;
    }

    public void setPrice(EventPriceDTO price) {
        this.price = price;
    }

    public EventBookingDTO getBooking() {
        return booking;
    }

    public void setBooking(EventBookingDTO booking) {
        this.booking = booking;
    }

    public List<EventVocabularyDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<EventVocabularyDTO> categories) {
        this.categories = categories;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
