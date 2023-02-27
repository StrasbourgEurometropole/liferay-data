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

import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.model.EventParticipation;
import eu.strasbourg.service.agenda.service.EventLocalService;
import eu.strasbourg.service.office.exporter.api.EventParticipationsXlsxExporter;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalService;

@Component(
	immediate = true,
	property = {},
	service = EventParticipationsXlsxExporter.class
)
public class EventParticipationsXlsxExporterImpl implements EventParticipationsXlsxExporter {
	
	private ResourceBundle bundle = ResourceBundleUtil.getBundle("content.Language",
			this.getClass().getClassLoader());

	@Override
	public void exportEventParticipations(OutputStream stream, long eventId) {
		// Recherche de l'evenement en question
		Event event = _eventLocalService.fetchEvent(eventId);
		
		// Recuperation des participations de l'evenement
		List <EventParticipation> eventParticipations = event.getEventParticipations();
		
		List<PublikUser> publikUsers = new ArrayList<PublikUser>();
		
		// Recuperation des utilisateurs publik de chaque participation
		for (EventParticipation eventParticipation : eventParticipations) {
			PublikUser user = _publikUserLocalService.getByPublikUserId(eventParticipation.getPublikUserId());
			
			// Verifie l'existance de l'utilisateur en cas d'injection de fausses participations 
			if (user != null) {
				publikUsers.add(user);
			}
		}
		
		this.exportEventParticipations(stream, event.getTitle(Locale.FRANCE), eventParticipations);
	}

	@Override
	public void exportEventParticipations(OutputStream stream, String eventTitle, List<EventParticipation> eventParticipations) {
		
		// Initialisation du document
		XSSFWorkbook workbook = new XSSFWorkbook();
		// Creation du document
		XSSFSheet sheet = workbook.createSheet("Participants " + eventTitle);
		// Initialisation des colonnes
		Object[][] participationData = { {
			LanguageUtil.get(bundle, "lastname"),
			LanguageUtil.get(bundle, "firstname"),
			LanguageUtil.get(bundle, "email"),
			LanguageUtil.get(bundle, "participation-date")
		} };
		
		// Parcours des participation et creation de la ligne a ajouter dans l'excel
		for (EventParticipation eventParticipation : eventParticipations) {
			
			// Recuperation de l'utilisateur lie a la participation
			PublikUser user = _publikUserLocalService.getByPublikUserId(eventParticipation.getPublikUserId());
			
			// Verifie l'existance de l'utilisateur en prevention de l'injection de fausses participations 
			if (user == null) {
				continue;
			}
			
			DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat("dd/MM/yyyy");
			
			Object[] participationRow = {
				user.getLastName(),
				user.getFirstName(),
				user.getEmail(),
				dateFormat.format(eventParticipation.getCreateDate()),
			};
			
			participationData = ArrayUtil.append(participationData, participationRow);
			
		}
		
		// Parcours et ajout des donnees dans les cellules
		int rowIndex = 0;
		int columnIndex = 0;
		
		for (Object[] eventObject : participationData) {
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

	@Reference(unbind = "-")
	protected void setEventLocalService(EventLocalService eventLocalService) {
		this._eventLocalService = eventLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setPublikUserLocalService(PublikUserLocalService publikUserLocalService) {
		this._publikUserLocalService = publikUserLocalService;
	}
	
	private EventLocalService _eventLocalService;
	
	private PublikUserLocalService _publikUserLocalService;

	private final Log _log = LogFactoryUtil.getLog(this.getClass());

}
