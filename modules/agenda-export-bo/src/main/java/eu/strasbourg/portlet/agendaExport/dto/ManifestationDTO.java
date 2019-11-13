package eu.strasbourg.portlet.agendaExport.dto;

import eu.strasbourg.service.agenda.model.Manifestation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Locale;

@XmlRootElement(name = "manifestation")
@XmlAccessorType(XmlAccessType.FIELD)
public class ManifestationDTO {

    @XmlElement(name = "name")
    private String name;

    public ManifestationDTO() {
    }

    public ManifestationDTO(Manifestation manifestation, Locale locale) {
        this.name = manifestation.getTitle(locale);
    }
}
