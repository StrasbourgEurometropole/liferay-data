package eu.strasbourg.portlet.agendaExport.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "contact")
@XmlAccessorType(XmlAccessType.FIELD)
public class EventContactDTO {

    @XmlElement(name = "promoter")
    private String promoter;

    @XmlElement(name = "phoneNumber")
    private String phoneNumber;

    @XmlElement(name = "mail")
    private String mail;

    @XmlElement(name = "websiteName")
    private String websiteName;

    @XmlElement(name = "websiteURL")
    private String websiteURL;

    public EventContactDTO() {
    }

    public EventContactDTO(String promoter, String phoneNumber, String mail, String websiteName, String websiteURL) {
        this.promoter = promoter;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
        this.websiteName = websiteName;
        this.websiteURL = websiteURL;
    }

    public String getPromoter() {
        return promoter;
    }

    public void setPromoter(String promoter) {
        this.promoter = promoter;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getWebsiteName() {
        return websiteName;
    }

    public void setWebsiteName(String websiteName) {
        this.websiteName = websiteName;
    }

    public String getWebsiteURL() {
        return websiteURL;
    }

    public void setWebsiteURL(String websiteURL) {
        this.websiteURL = websiteURL;
    }
}
