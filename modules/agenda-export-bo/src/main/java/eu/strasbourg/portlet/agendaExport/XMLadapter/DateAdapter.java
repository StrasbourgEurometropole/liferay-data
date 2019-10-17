package eu.strasbourg.portlet.agendaExport.XMLadapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateAdapter extends XmlAdapter<String, LocalDate> {

    private final DateTimeFormatter outFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final DateTimeFormatter inFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public String marshal(LocalDate date) throws Exception {
        return date.format(outFormatter);
    }

    @Override
    public LocalDate unmarshal(String date) throws Exception {
        //TODO locale
        //formatter = formatter.withLocale( putAppropriateLocaleHere );
        return LocalDate.parse(date, inFormatter);
    }

}
