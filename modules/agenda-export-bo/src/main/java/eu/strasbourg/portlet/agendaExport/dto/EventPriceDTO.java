package eu.strasbourg.portlet.agendaExport.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "price")
public class EventPriceDTO {

    @XmlElement(name = "isFree")
    private String isFree;

    @XmlElement(name = "priceDetail")
    private String priceDetail;

    public EventPriceDTO(String isFree, String priceDetail) {
        this.isFree = isFree;
        this.priceDetail = priceDetail;
    }

    public String getIsFree() {
        return isFree;
    }

    public void setIsFree(String isFree) {
        this.isFree = isFree;
    }

    public String getPriceDetail() {
        return priceDetail;
    }

    public void setPriceDetail(String priceDetail) {
        this.priceDetail = priceDetail;
    }
}
