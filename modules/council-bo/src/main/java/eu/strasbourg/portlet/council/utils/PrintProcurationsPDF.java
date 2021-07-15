package eu.strasbourg.portlet.council.utils;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.council.constants.ProcurationModeEnum;
import eu.strasbourg.service.council.constants.ProcurationPresentialEnum;
import eu.strasbourg.service.council.model.CouncilSession;
import eu.strasbourg.service.council.model.Procuration;
import eu.strasbourg.service.council.model.ProcurationModel;
import eu.strasbourg.service.council.service.CouncilSessionLocalServiceUtil;
import eu.strasbourg.service.council.service.OfficialLocalServiceUtil;
import eu.strasbourg.service.council.service.ProcurationLocalServiceUtil;
import eu.strasbourg.utils.StrasbourgPropsUtil;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class PrintProcurationsPDF {

	private static CouncilSession council;
	private static Timestamp timestamp;

	public static File printPDFs(long councilSessionId) throws SystemException {

		timestamp = new Timestamp(System.currentTimeMillis());

		File folder = new File(System.getProperty("java.io.tmpdir") + "/council_procurations_history_" +
				timestamp.getTime());
		folder.mkdirs();

		council = CouncilSessionLocalServiceUtil.fetchCouncilSession(councilSessionId);

		// on récpère toutes les délibérations du conseil de session qui sont adoptées ou rejetées avec des votes
		List<Procuration> procurations = ProcurationLocalServiceUtil.findByCouncilSessionId(councilSessionId).stream()
				.sorted(Comparator.comparing(ProcurationModel::getStartHour))
				.sorted(Comparator.comparing(p -> OfficialLocalServiceUtil.fetchOfficial(p.getOfficialUnavailableId()).getFullName()))
				.collect(Collectors.toList());
		File pdfFile = null;
		try {
			pdfFile = PrintProcurationsPDF.printPDF(folder, procurations);
			if(Validator.isNull(pdfFile)){
				throw new NullPointerException("Il y a eu un probl\u00e8me lors de la g\u00e9n\u00e9ration du PDF.");
			}
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
		return pdfFile;
	}

	private static void insertCell(Table table, String text, int colspan, PdfFont font, Color color, String border, Float maxWidth){
		//create a new cell with the specified rowspan and colspan
		Cell cell = new Cell(1, colspan);
		if(Validator.isNotNull(color)){
			cell.setBackgroundColor(color);
		}
		if(Validator.isNotNull(border)){
			cell.setBorder(Border.NO_BORDER);
		}
		//set text with font
		cell.add(new Paragraph(text).setTextAlignment(TextAlignment.CENTER))
				.setHorizontalAlignment(HorizontalAlignment.CENTER)
				.setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setMaxHeight(30f)
				.setFont(font);
		if(maxWidth!=0f){
			cell.setMaxWidth(maxWidth);
		}
		//in case there is no text and you wan to create an empty row
		if(text.trim().equalsIgnoreCase("")){
			cell.setMinHeight(10f);
		}
		//add the call to the table
		table.addCell(cell);

	}

	public static File printPDF(File folder, List<Procuration>  procurations)
		throws IOException, SystemException {

	// génération du pdf
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	PdfWriter pdfWriter = new PdfWriter(baos);
	PdfDocument pdf = new PdfDocument(pdfWriter);
	PdfFont fontBold = PdfFontFactory.createRegisteredFont("Helvetica-Bold");
	PdfFont font = PdfFontFactory.createRegisteredFont("Helvetica");

	// mise en page paysage
	//pdf.setDefaultPageSize(PageSize.A4.rotate());
	try (Document document = new Document(pdf)) {
		document.setMargins(40f, 0f, 40f, 0f);
		document.setFont(fontBold).setFontSize(10f);

		// titre du PDF
		String titleCouncil = "";
		if (Validator.isNotNull(council)) {
			titleCouncil +=  council.getTypeCouncil().getTitleLong().toUpperCase();
			SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyy", Locale.FRANCE);
			titleCouncil += " DU " + sdf.format(council.getDate());
		}

		// image d'entête
		String domaine = StrasbourgPropsUtil.getBaseURL();
		ImageData image = ImageDataFactory.create(domaine + "/o/councilbo/images/logo_strasbourg_vert.jpg");
		Image img = new Image(image);
		float newWidth = 140;
		float newHeight = (img.getImageHeight() / img.getImageWidth()) * newWidth;
		img.scaleAbsolute(newWidth, newHeight);
		img.setTextAlignment(TextAlignment.CENTER).setHorizontalAlignment(HorizontalAlignment.CENTER);
		document.add(img);

		// titre d'entête
		Paragraph title = new Paragraph(titleCouncil.toUpperCase())
				.setTextAlignment(TextAlignment.CENTER)
				.setMarginTop(10f)
				.setFontSize(12f);
		document.add(title);
		Paragraph subTitle = new Paragraph("Historique des procurations")
				.setTextAlignment(TextAlignment.CENTER)
				.setFontSize(12f);
		document.add(subTitle);

		//specify column widths
		float[] columnWidths = {2f,2f,2f,2f,1f,1f,1f,1f};
		//create PDF table with the given widths
		Table table = new Table(columnWidths).setHorizontalAlignment(HorizontalAlignment.CENTER);

		//insert column headings
		insertCell(table, "", 8, font, null, "no_border",0f);
		insertCell(table, "", 1, font, null, "no_border",0f);
		insertCell(table, "", 1, font, null, "no_border",0f);
		insertCell(table, "", 1, font, null, "no_border",0f);
		insertCell(table, "", 1, font, null, "no_border",0f);
		insertCell(table, "DEBUT", 2, font, new DeviceRgb(221, 221, 221),null,0f);
		insertCell(table, "FIN", 2, font, new DeviceRgb(221, 221, 221),null,0f);

		insertCell(table, "Votant", 1, font, new DeviceRgb(221, 221, 221),null,120f);
		insertCell(table, "Mode de procuration", 1, font, new DeviceRgb(221, 221, 221),null,80f);
		insertCell(table, "Pr\u00E9sentiel", 1, font, new DeviceRgb(221, 221, 221),null,50f);
		insertCell(table, "Procuration \u00E0", 1, font, new DeviceRgb(221, 221, 221),null,120f);
		insertCell(table, "Heure", 1, font, new DeviceRgb(221, 221, 221),null,30f);
		insertCell(table, "Du point", 1, font, new DeviceRgb(221, 221, 221),null,30f);
		insertCell(table, "Heure", 1, font, new DeviceRgb(221, 221, 221),null,30f);
		insertCell(table, "Au point", 1, font, new DeviceRgb(221, 221, 221),null,30f);

		//just some random data to fill
		for(Procuration procuration : procurations){

			insertCell(table, OfficialLocalServiceUtil.fetchOfficial(procuration.getOfficialUnavailableId()).getFullName(),
					1, font,null,null,120f);
			int procurationMode = procuration.getProcurationMode();
			if(procurationMode==0){
				insertCell(table, "-", 1, font,null,null,80f);
			} else {
				String procurationModeValue = procurationMode==4 ? procuration.getOtherProcurationMode() :ProcurationModeEnum.get(procurationMode).getName();
				insertCell(table, procurationModeValue, 1, font,null,null,80f);
			}
			int presential = procuration.getPresential();
			String presentialValue = ProcurationPresentialEnum.get(presential).getName();
			String value = Validator.isNull(presentialValue) ? "-" : presentialValue;
			insertCell(table, value, 1, font,null,null,50f);

			String OfficialVoter = procuration.getOfficialVotersFullName();
			String OfficialVoterValue = Validator.isNull(OfficialVoter) ? "Aucun" : OfficialVoter;
			insertCell(table, OfficialVoterValue, 1, font,null,null,120f);

			DateFormat hour = new SimpleDateFormat("hh");
			DateFormat minute = new SimpleDateFormat("mm");
			String startMinute = minute.format(procuration.getStartHour()).equals("00") ? "" : minute.format(procuration.getStartHour());
			String startTime = hour.format(procuration.getStartHour())+"h"+startMinute;
			String endTime = null;
			if(Validator.isNotNull(procuration.getEndHour())){
				String endMinute = minute.format(procuration.getEndHour()).equals("00") ? "" : minute.format(procuration.getEndHour());
				endTime = hour.format(procuration.getEndHour())+"h"+endMinute;
			}
			insertCell(table, startTime, 1, font,null,null,30f);
			insertCell(table, String.valueOf(procuration.getStartDelib()), 1, font,null,null,30f);
			insertCell(table, Validator.isNull(endTime) ? "" : endTime, 1, font,null,null,30f);
			insertCell(table, procuration.getEndDelib() == -1 ? "" : String.valueOf(procuration.getEndDelib()),
					1, font,null,null,30f);

		}
		document.add(table);

		if (pdf != null)
			pdf.close();

		if (pdfWriter != null)
			pdfWriter.close();

		String fileName = "";
		if (Validator.isNotNull(council)) {
			fileName +=  council.getTypeCouncil().getTitle();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.FRANCE);
		fileName += " " + sdf.format(council.getDate()) + "_procurations_history_" +
				timestamp.getTime() + ".pdf";

		// enregistrement du fichier
		File deliberationpdf = new File(folder.getAbsolutePath() + "/" + fileName);
		deliberationpdf.getParentFile().mkdirs();
			FileOutputStream fos;
			try {
				fos = new FileOutputStream(deliberationpdf);
				fos.write(baos.toByteArray());
				fos.close();
				return deliberationpdf;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
