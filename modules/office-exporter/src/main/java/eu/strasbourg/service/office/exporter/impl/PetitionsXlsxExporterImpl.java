package eu.strasbourg.service.office.exporter.impl;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.office.exporter.api.PetitionsXlsxExporter;
import eu.strasbourg.service.project.model.Petition;
import eu.strasbourg.service.project.service.PetitionLocalService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import static org.apache.commons.text.StringEscapeUtils.unescapeHtml4;

/**
 * @author alexandre.quere
 */
@Component(
        immediate = true,
        service = PetitionsXlsxExporter.class
)
public class PetitionsXlsxExporterImpl implements PetitionsXlsxExporter {

    private ResourceBundle bundle = ResourceBundleUtil.getBundle("content.Language",
            this.getClass().getClassLoader());

    private PetitionLocalService petitionLocalService;

    @Reference(unbind = "-")
    public void setPetitionLocalService(PetitionLocalService petitionLocalService) {
        this.petitionLocalService = petitionLocalService;
    }

    @Override
    public void exportPetitions(OutputStream stream, String petitionIdsStr) {
        List<Petition> petitions = new ArrayList<>();
        for (String petitionIdStr : petitionIdsStr.split(",")) {
            if (Validator.isNotNull(petitionIdStr)) {
                Petition petition = petitionLocalService.fetchPetition(Long.valueOf(petitionIdStr));
                if (Validator.isNotNull(petition))
                    petitions.add(petition);
            }
        }
        exportPetitions(stream, petitions);
    }

    @Override
    public void exportPetitions(OutputStream stream, List<Petition> petitions) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Petitions");
        Object[][] petitionData = {{LanguageUtil.get(bundle, "title"),
                LanguageUtil.get(bundle, "modification-date"),
                LanguageUtil.get(bundle, "user-liferay"),
                LanguageUtil.get(bundle, "signataire-count"),
                LanguageUtil.get(bundle, "description"),
                LanguageUtil.get(bundle, "petition-publication-date"),
                LanguageUtil.get(bundle, "petition-expiration-date"),
                LanguageUtil.get(bundle, "petition-lastname"),
                LanguageUtil.get(bundle, "petition-firstname"),
                LanguageUtil.get(bundle, "address"),
                LanguageUtil.get(bundle, "postal-code"),
                LanguageUtil.get(bundle, "city"),
                LanguageUtil.get(bundle, "phone"),
                LanguageUtil.get(bundle, "email"),
                LanguageUtil.get(bundle, "petition-issupported"),
                LanguageUtil.get(bundle, "petition-supportedby"),
                LanguageUtil.get(bundle, "petition-consultation-place-text"),
                LanguageUtil.get(bundle, "petition-status"),
                LanguageUtil.get(bundle, "thematic"),
                LanguageUtil.get(bundle, "quartiers")}};

        for (Petition petition : petitions) {
            DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat("dd/MM/yyyy");
            String languageId = LocaleUtil.toLanguageId(Locale.FRANCE);
            String title = LocalizationUtil.getLocalization(petition.getTitle(), languageId);
            String dateModified = dateFormat.format(petition.getModifiedDate());
            String publicationDate="";
            if (petition.getPublicationDate()!=null)
                publicationDate = dateFormat.format(petition.getPublicationDate());
            String expirationDate="";
            if (petition.getPublicationDate()!=null)
                expirationDate = dateFormat.format(petition.getExpirationDate());
            String nombreSignataire = String.valueOf(petition.getNombreSignature());
            String codePostal = String.valueOf(petition.getPetitionnairePostalCode());
            String isSupported = (petition.isIsSupported() ? "true" : "false");
            Object[] petitionRow = {getfield(title), getfield(dateModified),
                    getfield(petition.getUserName()),
                    nombreSignataire,
                    getfield(petition.getDescription()),
                    getfield(publicationDate),
                    getfield(expirationDate),
                    getfield(unescapeHtml4(petition.getPetitionnaireLastname())),
                    getfield(unescapeHtml4(petition.getPetitionnaireFirstname())),
                    getfield(unescapeHtml4(petition.getPetitionnaireAdresse())),
                    codePostal,
                    getfield(unescapeHtml4(petition.getPetitionnaireCity())),
                    getfield(unescapeHtml4(petition.getPetitionnairePhone())),
                    getfield(unescapeHtml4(petition.getPetitionnaireEmail())),
                    isSupported,
                    getfield(unescapeHtml4(petition.getSupportedBy())),
                    getfield(petition.getPlaceTextArea()),
                    getfield(petition.getPetitionStatusExcel()),
                    getfield(petition.getProjectTitle(Locale.FRANCE)),
                    getfield(petition.getThematicLabel(Locale.FRANCE)),
                    getfield(petition.getDistrictLabel(Locale.FRANCE))};
            petitionData = ArrayUtil.append(petitionData, petitionRow);
        }
        int rowIndex = 0;
        int columnIndex;
        for (Object[] petitionObject : petitionData) {
            Row row = sheet.createRow(rowIndex);
            columnIndex = 0;
            for (Object field : petitionObject) {
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
            e.printStackTrace();
        }
    }

    private String getfield(String param) {
        String result = LanguageUtil.get(bundle, "undefined");
        if (param != null && !param.isEmpty())
            result = param;
        return result;
    }
}
