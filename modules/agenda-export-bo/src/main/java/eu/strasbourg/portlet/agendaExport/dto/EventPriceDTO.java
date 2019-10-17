package eu.strasbourg.portlet.agendaExport.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "price")
@XmlAccessorType(XmlAccessType.FIELD)
public class EventPriceDTO {

    @XmlElement(name = "isFree")
    private int isFree;

    @XmlElement(name = "priceDetail")
    private String priceDetail;

    public EventPriceDTO() {
    }

    public EventPriceDTO(int isFree, String priceDetail) {
        this.isFree = isFree;
        this.priceDetail = priceDetail;
    }

    public int getIsFree() {
        return isFree;
    }

    public void setIsFree(int isFree) {
        this.isFree = isFree;
    }

    public String getPriceDetail() {
        return priceDetail;
    }

    public void setPriceDetail(String priceDetail) {
        this.priceDetail = priceDetail;
    }
}
