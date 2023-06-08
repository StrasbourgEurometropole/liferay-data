package eu.strasbourg.service.office.exporter.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
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

import eu.strasbourg.service.office.exporter.api.SignatairesXlsxExporter;
import eu.strasbourg.service.project.model.Petition;
import eu.strasbourg.service.project.model.Signataire;
import eu.strasbourg.service.project.service.PetitionLocalService;

@Component(
	immediate = true,
	property = {},
	service = SignatairesXlsxExporter.class
)
public class SignatairesXlsxExporterImpl implements SignatairesXlsxExporter {
	
	private ResourceBundle bundle = ResourceBundleUtil.getBundle("content.Language",
			this.getClass().getClassLoader());

	@Override
	public void exportSignataires(OutputStream stream, long petitionId) {
		// Recherche de la petition en question
		Petition petition = _petitionLocalService.fetchPetition(petitionId);
		
		// Recuperation des participations de l'evenement
		List <Signataire> signataires = petition.getSignataires();
		
		this.exportSignataires(stream, petition.getTitle(), signataires);
	}

	@Override
	public void exportSignataires(OutputStream stream, String petitionTitle, List<Signataire> signataires) {
		
		// Initialisation du document
		XSSFWorkbook workbook = new XSSFWorkbook();
		// Creation du document
		XSSFSheet sheet = workbook.createSheet("Signatures " + petitionTitle);
		// Initialisation des colonnes
		Object[][] signataireData = { {
			LanguageUtil.get(bundle, "lastname"),
			LanguageUtil.get(bundle, "firstname"),
			LanguageUtil.get(bundle, "birthday"),
			LanguageUtil.get(bundle, "address"),
			LanguageUtil.get(bundle, "postal-code"),
			LanguageUtil.get(bundle, "city"),
			LanguageUtil.get(bundle, "email"),
			LanguageUtil.get(bundle, "phone"),
			LanguageUtil.get(bundle, "mobile"),
			LanguageUtil.get(bundle, "support-date")
		} };
		
		// Parcours des participation et creation de la ligne a ajouter dans l'excel
		for (Signataire signataire : signataires) {
			
			Object[] signataireRow = { 
				getfield(signataire.getSignataireName()),
				getfield(signataire.getSignataireFirstname()),
				getfield(signataire.getBirthday()),
				getfield(signataire.getAddress()),
				getfield(signataire.getPostalCode()),
				getfield(signataire.getCity()),
				getfield(signataire.getMail()),
				getfield(signataire.getPhone()),
				getfield(signataire.getMobilePhone()),
				getfield(signataire.getCreateDate())
			};
			
			signataireData = ArrayUtil.append(signataireData, signataireRow);
			
		}
		
		// Parcours et ajout des donnees dans les cellules
		int rowIndex = 0;
		int columnIndex = 0;
		
		for (Object[] signataireObject : signataireData) {
			Row row = sheet.createRow(rowIndex);
			columnIndex = 0;
			for (Object field : signataireObject) {
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
	
	
	private String getfield(String param) {
        String result = "";
        if (param != null && !param.isEmpty())
            result = param;
        return result;
    }

    private String getfield(Date param) {
        DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat("dd/MM/yyyy");
        String result = "";
        if (param != null)
            result = dateFormat.format(param);
        return result;
    }

    private String getfield(boolean param) {
        return param ? "oui" : "non";
    }

    private String getfield(long param) {
        String result = "";
        if (param != 0L)
            result = String.valueOf(param);
        return result;
    }

    private String getfield(int param) {
        String result = "";
        if (param != 0)
            result = String.valueOf(param);
        return result;
    }
	
	@Reference(unbind = "-")
	protected void setPetitionLocalService(PetitionLocalService petitionLocalService) {
		this._petitionLocalService = petitionLocalService;
	}
	
	private PetitionLocalService _petitionLocalService;

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
	
}
