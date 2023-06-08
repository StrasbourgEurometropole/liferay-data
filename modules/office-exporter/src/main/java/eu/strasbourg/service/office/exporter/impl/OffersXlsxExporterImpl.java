package eu.strasbourg.service.office.exporter.impl;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.service.ejob.model.Offer;
import eu.strasbourg.service.ejob.service.OfferLocalService;
import eu.strasbourg.service.office.exporter.api.OffersXlsxExporter;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.StringHelper;
import eu.strasbourg.utils.constants.VocabularyNames;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


@Component(
	immediate = true,
	property = {},
	service = OffersXlsxExporter.class)
public class OffersXlsxExporterImpl implements OffersXlsxExporter {

	private ResourceBundle bundle = ResourceBundleUtil.getBundle("content.Language",
			this.getClass().getClassLoader());
	
	private OfferLocalService offerLocalService;

	@Reference(unbind = "-")
	protected void setOfferLocalService(OfferLocalService offerLocalService) {
		this.offerLocalService = offerLocalService;
	}

	public void exportPublishedOffers(OutputStream stream) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Offres");

		Object[][] offerData = {
				{
					LanguageUtil.get(bundle,"offer-number"),
					LanguageUtil.get(bundle, "ejobTypeRecrutement"),
					LanguageUtil.get(bundle, "ejob-type-publication"),
					LanguageUtil.get(bundle, "post-number"),
					LanguageUtil.get(bundle, "job-creation-description"),
					LanguageUtil.get(bundle, "start-date"),
					LanguageUtil.get(bundle, "motif"),
					LanguageUtil.get(bundle, "permanent-description"),
					LanguageUtil.get(bundle, "duration"),
					LanguageUtil.get(bundle, "offer-title"),
					LanguageUtil.get(bundle, "direction"),
					LanguageUtil.get(bundle, "service"),
					LanguageUtil.get(bundle, "ejob-contract-type"),
					LanguageUtil.get(bundle, "full-time-description"),
					LanguageUtil.get(bundle, "grade-info"),
					LanguageUtil.get(bundle, "grade-info"),
					LanguageUtil.get(bundle, "grade-info"),
					LanguageUtil.get(bundle, "grade-info"),
					LanguageUtil.get(bundle, "grade-info"),
					LanguageUtil.get(bundle, "ejobNiveauEtude"),
					LanguageUtil.get(bundle, "introduction"),
					LanguageUtil.get(bundle, "activities"),
					LanguageUtil.get(bundle, "profil"),
					LanguageUtil.get(bundle, "conditions"),
					LanguageUtil.get(bundle, "avantages"),
					LanguageUtil.get(bundle, "ejobFamille"),
					LanguageUtil.get(bundle, "application-end-date"),
					LanguageUtil.get(bundle, "ejobContact"),
					LanguageUtil.get(bundle, "contact-rrh"),
					LanguageUtil.get(bundle, "emails"),
					LanguageUtil.get(bundle, "share-linkedin"),
					LanguageUtil.get(bundle, "publication-start-date"),
					LanguageUtil.get(bundle, "publication-end-date"),
					LanguageUtil.get(bundle, "status")
				}
		};

		List<Offer> offers = offerLocalService.getOffers(-1, -1);
		DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat("dd/MM/yyyy");
		for (Offer offer : offers) {
			try {
				String direction = "";
				if (Validator.isNotNull(offer.getDirection()))
					direction = offer.getDirection().getTitle(Locale.FRANCE);
				String service = "";
				if (Validator.isNotNull(offer.getService()))
					service = offer.getService().getTitle(Locale.FRANCE);
				String startDate = "";
				if (Validator.isNotNull(offer.getStartDate()))
					startDate = dateFormat.format(offer.getStartDate());
				String motif = "";
				if (Validator.isNotNull(offer.getMotif()))
					motif = offer.getMotif().getTitle(Locale.FRANCE);
				String contractType = LanguageUtil.get(bundle, "ejob-partial-time");
				if (offer.isIsFullTime())
					contractType = LanguageUtil.get(bundle, "ejob-full-time");
				String grade1 = "", grade2 = "", grade3 = "", grade4 = "", grade5 = "";
				List<List> gradeRanges = offer.getGradeRanges();
				switch (gradeRanges.size()) {
					case 5:
						grade5 = getGrade(gradeRanges.get(4));
					case 4:
						grade4 = getGrade(gradeRanges.get(3));
					case 3:
						grade3 = getGrade(gradeRanges.get(2));
					case 2:
						grade2 = getGrade(gradeRanges.get(1));
					case 1:
						grade1 = getGrade(gradeRanges.get(0));
						break;
				}
				String levelStudy = "";
				if (Validator.isNotNull(offer.getNiveauEtude()))
					levelStudy = offer.getNiveauEtude().getTitle(Locale.FRANCE);
				List<String> familyList = new ArrayList<>();
				for (AssetCategory category : offer.getCategories()) {
					AssetVocabulary vocabulary = AssetVocabularyLocalServiceUtil
							.fetchAssetVocabulary(category.getVocabularyId());
					if (vocabulary != null
							&& StringHelper.compareIgnoringAccentuation(
							vocabulary.getName().toLowerCase(), VocabularyNames.EJOB_FAMILLE)) {
						familyList.add(category.getTitle(Locale.FRANCE));
					}
				}
				String families = String.join(", ", familyList);
				String contact = "";
				if (Validator.isNotNull(offer.getContactRE()))
					contact = offer.getContactRE().getTitle(Locale.FRANCE);
				String shareLinkedin = LanguageUtil.get(bundle, "no");
				if (offer.isShareLinkedin())
					shareLinkedin = LanguageUtil.get(bundle, "yes");
				// .replaceAll("<\\/?[a-zA-Z][^>]*>", "")  permet de supprimer toutes les balises HTML
				Object[] offerRow = {
						offer.getPublicationId(),
						offer.getTypeRecrutement().getTitle(Locale.FRANCE),
						offer.getTypePublication().getTitle(Locale.FRANCE),
						offer.getPostNumber(),
						offer.getJobCreationDescription(Locale.FRANCE),
						startDate,
						motif,
						offer.getPermanentDescription(Locale.FRANCE),
						offer.getDuration(Locale.FRANCE),
						offer.getPost(Locale.FRANCE),
						direction,
						service,
						contractType,
						offer.getFullTimeDescription(Locale.FRANCE),
						grade1,
						grade2,
						grade3,
						grade4,
						grade5,
						levelStudy,
						offer.getIntroduction(Locale.FRANCE).replaceAll("<\\/?[a-zA-Z][^>]*>", "").replaceAll("&nbsp;", " "),
						offer.getActivities(Locale.FRANCE).replaceAll("<\\/?[a-zA-Z][^>]*>", "").replaceAll("&nbsp;", " "),
						offer.getProfil(Locale.FRANCE).replaceAll("<\\/?[a-zA-Z][^>]*>", "").replaceAll("&nbsp;", " "),
						offer.getConditions(Locale.FRANCE).replaceAll("<\\/?[a-zA-Z][^>]*>", "").replaceAll("&nbsp;", " "),
						offer.getAvantages(Locale.FRANCE).replaceAll("<\\/?[a-zA-Z][^>]*>", "").replaceAll("&nbsp;", " "),
						families,
						dateFormat.format(offer.getLimitDate()),
						contact,
						offer.getContact(),
						offer.getEmails(),
						shareLinkedin,
						dateFormat.format(offer.getPublicationStartDate()),
						dateFormat.format(offer.getPublicationEndDate()),
						LanguageUtil.get(bundle, WorkflowConstants.getStatusLabel(offer.getStatus()))
				};
				offerData = ArrayUtil.append(offerData, offerRow);
			}catch (Exception e){
				_log.error("erreur offre " + offer.getPublicationId(), e);
			}
		}

		int rowIndex = 0;
		int columnIndex = 0;
		for (Object[] offerObject : offerData) {
			Row row = sheet.createRow(rowIndex);
			columnIndex = 0;
			for (Object field : offerObject) {
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

	private String getGrade(List gradeRange){
		AssetCategory categorie = (AssetCategory) gradeRange.get(0);
		AssetCategory filiere = (AssetCategory) gradeRange.get(1);
		AssetCategory gradeMin = (AssetCategory) gradeRange.get(2);
		String gradeMaxString = "";
		AssetCategory gradeMax = (AssetCategory) gradeRange.get(3);
		if (Validator.isNotNull(gradeMax)) {
			gradeMaxString = gradeMax.getTitle(Locale.FRANCE) +
					"(" + gradeMax.getParentCategory().getName().replaceFirst("^[A-Z]_", "") + ")";
		}
		return categorie.getTitle(Locale.FRANCE) + "/" +
						filiere.getTitle(Locale.FRANCE) + "/" +
						gradeMin.getTitle(Locale.FRANCE) +
						"(" + gradeMin.getParentCategory().getName().replaceFirst("^[A-Z]_", "") + ")/" +
						gradeMaxString;

	}
}
