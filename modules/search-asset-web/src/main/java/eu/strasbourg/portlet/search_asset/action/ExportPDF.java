package eu.strasbourg.portlet.search_asset.action;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.portlet.search_asset.constants.OfficialsConstants;
import eu.strasbourg.service.official.model.Official;
import eu.strasbourg.service.official.service.OfficialLocalServiceUtil;

public class ExportPDF extends MVCPortlet {

	public static String domaine;
	public static Font font = new Font(FontFamily.TIMES_ROMAN, 12);
	public static Font fontBold = new Font(FontFamily.TIMES_ROMAN, 12,
			Font.BOLD);
	public static Font fontTitle = new Font(FontFamily.TIMES_ROMAN, 20,
			Font.BOLD);
	public static Font fontName = new Font(FontFamily.TIMES_ROMAN, 16,
			Font.BOLD);

	public static void export(String[] officialsIds) {
	}

	public static void printPDFWithXMLWorker(ResourceRequest req,
			ResourceResponse res) throws PortletException, IOException,
			DocumentException, SystemException, PortalException {

		// récupération du domaine
		domaine = "http://localhost:8080";

		// génération du pdf
		Document doc = new Document();
		doc.setMargins(35f, 35f, 35f, 35f);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PdfWriter docWriter = null;
		docWriter = PdfWriter.getInstance(doc, baos);
		doc.open();

		printPDFPeople(doc, req);

		res.setContentType("application/pdf");
		res.setContentLength(baos.size());
		OutputStream out = res.getPortletOutputStream();

		// fermeture du PDF
		if (docWriter != null) {
			docWriter.close();
		}
		if (doc != null) {
			doc.close();
		}
		if (out != null) {
			baos.writeTo(out);
			out.flush();
			out.close();
		}
	}

	public static void printPDFPeople(Document document, ResourceRequest req)
			throws SystemException, PortalException, DocumentException,
			MalformedURLException, IOException {

		ThemeDisplay themeDisplay = (ThemeDisplay) req
				.getAttribute(WebKeys.THEME_DISPLAY);

		Paragraph paragraph = new Paragraph();
		PdfPTable table = new PdfPTable(new float[] { 25f, 75f });
		table.setWidthPercentage(100f);
		table.setPaddingTop(1f);

		// image d'entête
		insertCell(table, "header", null,
				domaine + "/o/searchassetweb/images/bandeau.jpg", 2);

		// titre du PDF
		String titrePortlet = null;
		String officialType = ParamUtil.getString(req, "officialType");
		if (Validator.isNotNull(officialType)) {
			switch (officialType) {
			case OfficialsConstants.MUNICIPAL:
				titrePortlet = LanguageUtil.get(themeDisplay.getLocale(),
						"entete-annuaire-elus-communautaires-print");
				break;
			case OfficialsConstants.EUROMETROPOLE:
				titrePortlet = LanguageUtil.get(themeDisplay.getLocale(),
						"entete-annuaire-elus-municipaux-print");
				break;
			}
		}
		insertCell(table, "title",
				new Phrase(new Phrase(titrePortlet.toUpperCase(), fontTitle)),
				null, 3);

		// contenu du PDF
		List<Official> elus = getPeopleList(req);
		for (Official elu : elus) {
			insertCell(table, "image", null, domaine + elu.getImageURL(), 1);

			if (Validator.isNotNull(officialType)) {
				Phrase phrase = new Phrase();
				Chunk chunckName = new Chunk(
						elu.getFirstName() + " "
								+ elu.getLastName().toUpperCase() + "\n\n",
						fontName);
				phrase.add(chunckName);
				switch (officialType) {
				case OfficialsConstants.MUNICIPAL:
					phrase.add(
							printPDFMunicipal(elu, themeDisplay.getLocale()));
					phrase.add("\n\n");
					phrase.add(printPDFEurometropole(elu,
							themeDisplay.getLocale()));
					insertCell(table, "data", phrase, null, 1);
					break;
				case OfficialsConstants.EUROMETROPOLE:
					phrase.add(printPDFEurometropole(elu,
							themeDisplay.getLocale()));
					phrase.add("\n\n");
					phrase.add(
							printPDFMunicipal(elu, themeDisplay.getLocale()));
					insertCell(table, "data", phrase, null, 1);
					break;
				}
			}
		}

		// bas de page
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		insertCell(table, "date",
				new Phrase(new Chunk(sdf.format(new Date()), font)), null, 1);
		Phrase phrase = new Phrase();
		phrase.add(new Chunk(
				LanguageUtil.get(themeDisplay.getLocale(), "footer-title"),
				fontBold));
		phrase.add("\n");
		phrase.add(
				new Chunk(
						HtmlUtil.render(LanguageUtil.get(
								themeDisplay.getLocale(), "footer-content")),
						font));
		insertCell(table, "footer", phrase, null, 1);

		paragraph.add(table);
		document.add(paragraph);

	}

	private static List<Official> getPeopleList(ResourceRequest req)
			throws SystemException {

		List<Official> elus = new ArrayList<Official>();
		String[] officialsIds = ParamUtil.getString(req, "ids").split(",");
		for (String id : officialsIds) {
			if (Validator.isNotNull(id)) {
				elus.add(OfficialLocalServiceUtil
						.fetchOfficial(Long.parseLong(id)));
			}
		}

		return elus;
	}

	private static Phrase printPDFMunicipal(Official elu, Locale locale)
			throws PortalException {

		Phrase phrase = new Phrase();

		if (elu.isEluMunicipal()) {
			phrase.add(new Chunk(elu.getName(elu.getFonctionCity(), locale),
					fontBold));

			if (Validator.isNotNull(elu.getThematicDelegation())) {
				String cityMission = StringUtil.replaceFirst(
						elu.getThematicDelegation(locale), "<p>", "");
				cityMission = StringUtil.replaceFirst(cityMission, "</p>", "");
				phrase.add("\n");
				phrase.add(new Chunk(
						HtmlUtil.render(LanguageUtil.get(locale, "en-charge-de")
								+ " : " + cityMission),
						font));
			}

			List<AssetCategory> quartiers = elu.getDistricts();
			if (Validator.isNotNull(quartiers) && !quartiers.isEmpty()) {
				phrase.add("\n");
				phrase.add(new Chunk(
						LanguageUtil.get(locale, "adjoint-de-quartier") + " : ",
						fontBold));
				StringBuilder strQuartiers = new StringBuilder();
				for (AssetCategory quartierElu : quartiers) {
					if (strQuartiers.length() > 0) {
						strQuartiers.append(", ");
					}
					strQuartiers.append(quartierElu.getName());
				}
				phrase.add(new Chunk(HtmlUtil.render(strQuartiers.toString()),
						font));
			}
		}

		return phrase;
	}

	private static Phrase printPDFEurometropole(Official elu, Locale locale)
			throws PortalException {

		Phrase phrase = new Phrase();

		if (elu.isEluEurometropole()) {
			phrase.add(new Chunk(
					elu.getName(elu.getFonctionEurometropole(), locale),
					fontBold));

			if (Validator.isNotNull(elu.getFonctionTown())) {
				phrase.add(new Chunk(
						", " + elu.getName(elu.getFonctionTown(), locale),
						font));
			}

			if (Validator.isNotNull(elu.getTown())) {
				phrase.add(
						new Chunk(
								" " + LanguageUtil.get(locale, "de-la-commune")
										+ " " + elu.getTown().getTitle(locale),
								font));
			}

			if (Validator.isNotNull(elu.getMissions())) {
				String cusMission = StringUtil
						.replaceFirst(elu.getMissions(locale), "<p>", "");
				cusMission = StringUtil.replaceFirst(cusMission, "</p>", "");
				phrase.add("\n");
				phrase.add(new Chunk(
						HtmlUtil.render(LanguageUtil.get(locale, "en-charge-de")
								+ " : " + cusMission),
						font));
			}
		}

		return phrase;
	}

	private static void insertCell(PdfPTable table, String cellData,
			Phrase phrase, String url, int colspan)
			throws BadElementException, MalformedURLException, IOException {

		PdfPCell cell;
		switch (cellData) {
		case "header":
			// créer une cellule d'image
			Image img = Image.getInstance(url);
			float newWidth = 520;
			float newHeight = (img.getHeight() / img.getWidth()) * newWidth;
			img.scaleAbsolute(newWidth, newHeight);
			cell = new PdfPCell(img);
			break;
		case "title":
			cell = new PdfPCell(phrase);
			break;
		case "image":
			// créer une cellule d'image
			img = Image.getInstance(url);
			newWidth = 90;
			newHeight = (img.getHeight() / img.getWidth()) * newWidth;
			img.scaleAbsolute(newWidth, newHeight);
			cell = new PdfPCell(img);
			break;
		default:
			cell = new PdfPCell(phrase);
			break;
		}

		// gestion de l'espacement de ligne
		cell.setLeading(0, 1.5f);

		// gestion des colspans
		if (cellData.equals("title") || cellData.equals("header")) {
			cell.setColspan(2);
		}

		// gestion espacements de cellule
		cell.setPadding(0f);
		if (cellData.equals("title")) {
			cell.setPaddingBottom(10f);
		}
		if (cellData.equals("image")) {
			cell.setPaddingTop(10f);
		}
		if (!cellData.equals("header")) {
			cell.setPaddingBottom(15f);
		}

		// gestion des bordures
		cell.disableBorderSide(PdfPCell.LEFT);
		cell.disableBorderSide(PdfPCell.RIGHT);
		cell.disableBorderSide(PdfPCell.TOP);
		cell.disableBorderSide(PdfPCell.BOTTOM);
		if (cellData.equals("footer") || cellData.equals("date")) {
			cell.enableBorderSide(PdfPCell.TOP);
			cell.setBorderColor(new BaseColor(151, 191, 12));
			cell.setBorderWidth(1f);
		} else if (cellData.equals("data")) {
			cell.enableBorderSide(PdfPCell.BOTTOM);
		}

		// gestion des alignements
		cell.setVerticalAlignment(Element.ALIGN_TOP);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		if (cellData.equals("header") || cellData.equals("image")) {
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		} else if (cellData.equals("footer")) {
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		}

		// ajoute la cellule à la table
		table.addCell(cell);
	}

}
