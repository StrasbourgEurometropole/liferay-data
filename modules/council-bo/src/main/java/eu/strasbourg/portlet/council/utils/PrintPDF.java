package eu.strasbourg.portlet.council.utils;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.council.model.CouncilSession;
import eu.strasbourg.service.council.model.Deliberation;
import eu.strasbourg.service.council.model.Official;
import eu.strasbourg.service.council.model.Vote;
import eu.strasbourg.service.council.service.CouncilSessionLocalServiceUtil;
import eu.strasbourg.service.council.service.DeliberationLocalServiceUtil;
import eu.strasbourg.service.council.service.OfficialLocalServiceUtil;
import eu.strasbourg.service.council.service.VoteLocalServiceUtil;
import eu.strasbourg.utils.StrasbourgPropsUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class PrintPDF {

	private static CouncilSession council;
	private static LocalDateTime now;
	private static Timestamp timestamp;

	public static String printPDFs(long councilSessionId)
			throws IOException, SystemException {

		now = LocalDateTime.now();
		timestamp = new Timestamp(System.currentTimeMillis());

		File folder = new File(System.getProperty("java.io.tmpdir") + "/council_results_" +
				timestamp.getTime());
		folder.mkdirs();

		council = CouncilSessionLocalServiceUtil.fetchCouncilSession(councilSessionId);

		// on récpère toutes les délibérations du conseil de session qui sont adoptées ou rejetées avec des votes
		List<Deliberation> deliberations = DeliberationLocalServiceUtil.findByCouncilSessionId(councilSessionId)
				.stream().filter(d -> d.isRejete() || d.isAdopte()).collect(Collectors.toList());
		for (Deliberation deliberation : deliberations) {
			// on vérifi s'il y a des votes
			if(Validator.isNotNull(deliberation)) {
				List<Vote> votes = VoteLocalServiceUtil.findByDeliberationId(deliberation.getDeliberationId());
				if (Validator.isNotNull(votes) && !votes.isEmpty()) {
					PrintPDF.printPDF(folder, deliberation, votes);
				}
			}
		}

		return folder.getAbsolutePath();
	}

		public static void printPDF(File folder, Deliberation deliberation, List<Vote> votes)
			throws IOException, SystemException {

		// génération du pdf
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PdfWriter pdfWriter = new PdfWriter(baos);
		PdfDocument pdf = new PdfDocument(pdfWriter);
		PdfFont fontBold = PdfFontFactory.createRegisteredFont("Helvetica-Bold");
		PdfFont font = PdfFontFactory.createRegisteredFont("Helvetica");

		// mise en page paysage
		pdf.setDefaultPageSize(PageSize.A4.rotate());
		try (Document document = new Document(pdf)) {
			document.setMargins(40f, 0f, 40f, 0f);
			document.setFont(fontBold).setFontSize(12f);

			// titre du PDF
			String titleCouncil = "";
			if (Validator.isNotNull(council)) {
				titleCouncil +=  council.getTypeCouncil().getTitleLong().toUpperCase();
				SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyy", Locale.FRANCE);
				titleCouncil += " DU " + sdf.format(council.getDate());
			}

			Paragraph title = new Paragraph(titleCouncil.toUpperCase() + " - Point n"
					+ LanguageUtil.get(Locale.FRANCE, "eu.numero")
					+ deliberation.getOrder())
				.setPaddings(0f,10f,0f,150f)
				.setTextAlignment(TextAlignment.CENTER);
			document.add(title);
			title = new Paragraph(deliberation.getTitle()).setFont(font)
				.setPaddings(0f,10f,0f,150f)
				.setTextAlignment(TextAlignment.CENTER)
				.setFontSize(13.5f);
			document.add(title);

			// image d'entête
			String domaine = StrasbourgPropsUtil.getBaseURL();
			ImageData image = ImageDataFactory.create(domaine + "/o/councilbo/images/logo_strasbourg_vert.jpg");
			Image img = new Image(image);
			float newWidth = 140;
			float newHeight = (img.getImageHeight() / img.getImageWidth()) * newWidth;
			img.scaleAbsolute(newWidth, newHeight)
					.setFixedPosition(0f,520f);
			document.add(img);

			float hauteur_pour = 245f;
			float hauteur_contre = 70f;
			float hauteur_abstention = 70f;
			float hauteur_totale = hauteur_pour + hauteur_contre + hauteur_abstention;

			Table table = new Table(new float[]{60f, 780f}).useAllAvailableWidth()
					.setFixedPosition(1,1f,40f,840f);

			// votes pour
			List<Vote> votesPour = votes.stream().filter(v -> v.getResult().equals("Pour")).collect(Collectors.toList());
			int nbVotesPour = votesPour.size();
			List<String> offialsFullNamePour = new ArrayList<>();
			for (Vote vote : votesPour) {
				Official elu = OfficialLocalServiceUtil.fetchOfficial(vote.getOfficialId());
				if (elu != null)
					offialsFullNamePour.add(elu.getFullName());
			}
			offialsFullNamePour.sort( Comparator.comparing( String::toString ) );
			// votes contre
			List<Vote> votesContre = votes.stream().filter(v -> v.getResult().equals("Contre")).collect(Collectors.toList());
			int nbVotesContre = votesContre.size();
			List<String> offialsFullNameContre = new ArrayList<>();
			for (Vote vote : votesContre) {
				Official elu = OfficialLocalServiceUtil.fetchOfficial(vote.getOfficialId());
				if (elu != null)
					offialsFullNameContre.add(elu.getFullName());
			}
			offialsFullNameContre.sort( Comparator.comparing( String::toString ) );
			// abstention
			List<Vote> abstentions = votes.stream().filter(v -> v.getResult().equals("Abstention")).collect(Collectors.toList());
			int nbAbstentions = abstentions.size();
			List<String> offialsFullNameAbstentions = new ArrayList<>();
			for (Vote vote : abstentions) {
				Official elu = OfficialLocalServiceUtil.fetchOfficial(vote.getOfficialId());
				if (elu != null)
					offialsFullNameAbstentions.add(elu.getFullName());
			}
			offialsFullNameAbstentions.sort( Comparator.comparing( String::toString ) );

			int nbVotesTotal = votes.size();

			if(nbVotesContre >= 25 || nbAbstentions >= 25){
				float hauteur_min = 70f;
				float hauteur_restante = hauteur_totale - 3 * hauteur_min;
				hauteur_abstention = hauteur_min + nbAbstentions * hauteur_restante / nbVotesTotal;
				hauteur_contre = hauteur_min + nbVotesContre * hauteur_restante / nbVotesTotal;
				hauteur_pour = hauteur_totale - hauteur_contre - hauteur_abstention;
			}

			// colonne verte
			Cell cell = new Cell().add(new Paragraph("Pour").setFontSize(15f))
					.add(new Paragraph(""+nbVotesPour).setFontSize(28f).setMarginTop(hauteur_pour / 2 - 28f - 15f))
					.setTextAlignment(TextAlignment.CENTER)
					.setBackgroundColor(new DeviceRgb(153, 204, 0)).setHeight(hauteur_pour)
					.setBorder(new SolidBorder(ColorConstants.BLACK, 2f));
			table.addCell(cell);
			// liste élus
			String listePour = "";
			for (String officialFullName : offialsFullNamePour) {
				if(!listePour.isEmpty())
					listePour += ", ";
				listePour += officialFullName;
			}
			cell = new Cell().setKeepTogether(true).setVerticalAlignment(VerticalAlignment.MIDDLE)
					.add(new Paragraph(listePour)
							.setFontColor(new DeviceRgb(85, 104, 59)))
					.setBorder(Border.NO_BORDER);
			table.addCell(cell);

			// colonne rouge
			cell = new Cell().add(new Paragraph("Contre").setFontSize(15f))
					.add(new Paragraph(""+nbVotesContre).setFontSize(28f).setMarginTop(hauteur_contre / 2 - 28f - 15f))
					.setTextAlignment(TextAlignment.CENTER)
					.setBackgroundColor(new DeviceRgb(255, 0, 0)).setHeight(hauteur_contre)
					.setBorder(new SolidBorder(ColorConstants.BLACK, 2f));
			table.addCell(cell);
			// liste élus
			String listeContre = "";
			for (String officialFullName : offialsFullNameContre) {
				if(!listeContre.isEmpty())
					listeContre += ", ";
				listeContre += officialFullName;
			}
			cell = new Cell().setKeepTogether(true).setVerticalAlignment(VerticalAlignment.MIDDLE)
					.add(new Paragraph(listeContre)
							.setFontColor(new DeviceRgb(255, 0, 0)))
					.setBorder(Border.NO_BORDER);
			table.addCell(cell);

			// colonne bleue
			cell = new Cell().add(new Paragraph("Abstention").setFontSize(15f))
					.add(new Paragraph(""+nbAbstentions).setFontSize(28f).setMarginTop(hauteur_abstention / 2 - 28f - 15f))
					.setTextAlignment(TextAlignment.CENTER)
					.setBackgroundColor(new DeviceRgb(85, 142, 213)).setHeight(hauteur_abstention)
					.setBorder(new SolidBorder(ColorConstants.BLACK, 2f));
			table.addCell(cell);
			// liste élus
			String listeAbstention = "";
			for (String officialFullName : offialsFullNameAbstentions) {
				if(!listeAbstention.isEmpty())
					listeAbstention += ", ";
				listeAbstention += officialFullName;
			}
			cell = new Cell().setKeepTogether(true).setVerticalAlignment(VerticalAlignment.MIDDLE)
					.add(new Paragraph(listeAbstention)
							.setFontColor(new DeviceRgb(99, 77, 124)))
					.setBorder(Border.NO_BORDER);
			table.addCell(cell);
			document.add(table);

			if (pdf != null)
				pdf.close();

			if (pdfWriter != null)
				pdfWriter.close();

			String fileName = "";
			if (Validator.isNotNull(council)) {
				// Supprime les accents qui posent souci sur la création de fichier
				fileName +=  Normalizer.normalize(council.getTypeCouncil().getTitle(), Normalizer.Form.NFD).replaceAll("\\p{M}", "");
			}
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.FRANCE);
			fileName += " " + sdf.format(council.getDate()) + " - Point " + deliberation.getOrder() + ".pdf";

			// enregistrement du fichier
			File deliberationpdf = new File(folder.getAbsolutePath() + "/" + fileName);
			deliberationpdf.getParentFile().mkdirs();
				FileOutputStream fos;
				try {
					fos = new FileOutputStream(deliberationpdf);
					fos.write(baos.toByteArray());
					fos.close();
				} catch (IOException e) {
					_log.error(e.getMessage(), e);
				}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(PrintPDF.class);

}
