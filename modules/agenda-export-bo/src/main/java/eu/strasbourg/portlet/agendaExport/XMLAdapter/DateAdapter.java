package eu.strasbourg.portlet.agendaExport.XMLAdapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateAdapter extends XmlAdapter<String, LocalDate> {

    private final DateTimeFormatter outFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    private final DateTimeFormatter inFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public String marshal(LocalDate date) throws Exception {
        LocalDateTime ldt = date.atStartOfDay();
        return ldt.format(outFormatter);
    }

    @Override
    public LocalDate unmarshal(String date) throws Exception {
        return LocalDate.parse(date, inFormatter);
    }

}
