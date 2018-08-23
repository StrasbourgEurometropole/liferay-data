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

/**
 * @author alexandre.quere
 */
@Component(
        immediate = true,
        service = PetitionsXlsxExporter.class
)
public class PetitionsXlsxExporterImpl implements PetitionsXlsxExporter{

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
        for (String petitionIdStr :petitionIdsStr.split(",")) {
            if (Validator.isNotNull(petitionIdStr)){
                Petition petition = petitionLocalService.fetchPetition(Long.valueOf(petitionIdStr));
                if (Validator.isNotNull(petition))
                    petitions.add(petition);
            }
        }
        exportPetitions(stream,petitions);
    }

    @Override
    public void exportPetitions(OutputStream stream, List<Petition> petitions) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Petitions");
        Object[][] petitionData = {{LanguageUtil.get(bundle,"title"),
                LanguageUtil.get(bundle,"modification-date"),
                LanguageUtil.get(bundle,"user"),
                LanguageUtil.get(bundle,"signataire-count"),
                LanguageUtil.get(bundle,"petition-status"),
        LanguageUtil.get(bundle,"status")}};

        for (Petition petition : petitions) {
            DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat("dd/MM/yyyy");
            String dateModified = dateFormat.format(petition.getModifiedDate());
            String languageId = LocaleUtil.toLanguageId(Locale.FRANCE);
            String title = LocalizationUtil.getLocalization(petition.getTitle(),languageId);
            Object[] petitionRow = {title,dateModified,
                    petition.getUserName(),
                    petition.getNombreSignature(),
                    petition.getPetitionStatus(),
                    petition.getStatus()};
            petitionData = ArrayUtil.append(petitionData,petitionRow);
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
}
