package eu.strasbourg.service.office.exporter.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.Validator;

import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.model.EventPeriod;
import eu.strasbourg.service.agenda.service.EventLocalService;
import eu.strasbourg.service.office.exporter.api.EventsXlsxExporter;


@Component(
	immediate = true,
	property = {},
	service = EventsXlsxExporter.class)
public class EventsXlsxExporterImpl implements EventsXlsxExporter {

	private ResourceBundle bundle = ResourceBundleUtil.getBundle("content.Language",
			this.getClass().getClassLoader());
	
	private EventLocalService eventLocalService;

	@Reference(unbind = "-")
	protected void setEventLocalService(EventLocalService eventLocalService) {
		this.eventLocalService = eventLocalService;
	}

	public void exportEvents(OutputStream stream, String eventIdsStr) {
		List<Event> events = new ArrayList<Event>();
		for (String eventIdStr : eventIdsStr.split(",")) {
			if (Validator.isNotNull(eventIdStr)) {
				Event event = eventLocalService.fetchEvent(Long.valueOf(eventIdStr));
				if (event != null) {
					events.add(event);
				}
			}
		}
		exportEvents(stream, events);
	}

	public void exportEvents(OutputStream stream, List<Event> events) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("events");

		Object[][] eventData = { { LanguageUtil.get(bundle, "title"), LanguageUtil.get(bundle, "subtitle"),
				LanguageUtil.get(bundle, "description"), LanguageUtil.get(bundle, "place-name"),
				LanguageUtil.get(bundle, "address"), LanguageUtil.get(bundle, "organizer"),
				LanguageUtil.get(bundle, "phone"), LanguageUtil.get(bundle, "email"),
				LanguageUtil.get(bundle, "website-name"), LanguageUtil.get(bundle, "website"),
				LanguageUtil.get(bundle, "schedule"), LanguageUtil.get(bundle, "free"),
				LanguageUtil.get(bundle, "price"), LanguageUtil.get(bundle, "types"),
				LanguageUtil.get(bundle, "themes"), LanguageUtil.get(bundle, "publics") } };

		for (Event event : events) {
			String description = StringEscapeUtils.unescapeHtml4(event.getDescription(Locale.FRANCE));
			String price = StringEscapeUtils.unescapeHtml4(event.getPrice(Locale.FRANCE));
			
			String isFreeLabel = "";
			switch (event.getFree()) {
			case 0:
				isFreeLabel = LanguageUtil.get(bundle, "no");
				break;
			case 1:
				isFreeLabel = LanguageUtil.get(bundle, "yes");
				break;
			case 2:
				isFreeLabel = LanguageUtil.get(bundle, "not-communicated");
				break;
			}

			String schedule = "";
			for (EventPeriod period : event.getEventPeriods()) {
				if (schedule.length() > 0) {
					schedule += ", ";
				}
				DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat("dd/MM/yyyy");
				schedule += dateFormat.format(period.getStartDate()) + " - " + dateFormat.format(period.getEndDate());
				schedule += " (" + period.getTimeDetail(Locale.FRANCE) + ")";
			}

			Object[] eventRow = { event.getTitle(Locale.FRANCE), event.getSubtitle(Locale.FRANCE), description,
					event.getPlaceAlias(Locale.FRANCE), event.getPlaceAddress(Locale.FRANCE), event.getPromoter(),
					event.getPhone(), event.getEmail(), event.getWebsiteName(Locale.FRANCE),
					event.getWebsiteURL(Locale.FRANCE), schedule, isFreeLabel, price, event.getTypeLabel(Locale.FRANCE),
					event.getThemeLabel(Locale.FRANCE), event.getPublicLabel(Locale.FRANCE) };
			eventData = ArrayUtil.append(eventData, eventRow);
		}

		int rowIndex = 0;
		int columnIndex = 0;
		for (Object[] eventObject : eventData) {
			Row row = sheet.createRow(rowIndex);
			columnIndex = 0;
			for (Object field : eventObject) {
				Cell cell = row.createCell(columnIndex);
				if (field instanceof String) {
					cell.setCellValue((String) field);
				} else if (field instanceof Integer) {
					cell.setCellValue((Integer) field);
				}
				columnIndex++;
			}
			rowIndex++;
		}

		try {
			workbook.write(stream);
			workbook.close();
			stream.flush();
		} catch (IOException e) {
			_log.error(e.getMessage(), e);
		}
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}
