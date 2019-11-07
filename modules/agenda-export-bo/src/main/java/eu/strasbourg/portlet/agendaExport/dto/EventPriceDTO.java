package eu.strasbourg.portlet.agendaExport.dto;

import com.liferay.portal.kernel.util.HtmlUtil;

import javax.xml.bind.annotation.*;

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

        if(this.priceDetail == null) {
            this.priceDetail = "";
        }
        this.priceDetail = HtmlUtil.stripHtml(priceDetail).replaceAll("<br/>", "");
    }

    public int getIsFree() {
        return isFree;
    }

    public void setIsFree(int isFree) {
        this.isFree = isFree;
    }

    public String getPriceDetail() {
        if(priceDetail == null) {
            priceDetail = "";
        }
        return HtmlUtil.stripHtml(priceDetail).replaceAll("<br/>", "");
    }

    public void setPriceDetail(String priceDetail) {
        this.priceDetail = priceDetail;
    }
}
