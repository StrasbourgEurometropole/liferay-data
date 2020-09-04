/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package eu.strasbourg.service.ejob.service.impl;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.liferay.portal.kernel.language.LanguageUtil;
import eu.strasbourg.service.ejob.model.Offer;
import eu.strasbourg.service.ejob.service.base.OfferServiceBaseImpl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * The implementation of the offer remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>eu.strasbourg.service.ejob.service.OfferService</code> interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OfferServiceBaseImpl
 */
public class OfferServiceImpl extends OfferServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use <code>eu.strasbourg.service.ejob.service.OfferServiceUtil</code> to access the offer remote service.
	 */

	@Override
	public String exportOffer(Long offerId, Locale locale, ByteArrayOutputStream baos) throws IOException {

		// récupération de l'offre
		Offer offer = this.offerLocalService.fetchOffer(offerId);

		PdfFont font = PdfFontFactory.createRegisteredFont("Helvetica");
		PdfFont fontBold = PdfFontFactory.createRegisteredFont("Helvetica-Bold");

		// génération du pdf
		PdfWriter pdfWriter = new PdfWriter(baos);
		PdfDocument pdf = new PdfDocument(pdfWriter);

		try (Document document = new Document(pdf)) {

			document.setMargins(35f, 35f, 35f, 35f);
			document.setFont(font).setFontSize(12f).setFontColor(new DeviceRgb(49, 69, 93));

			// titre du PDF
			// vagues
			String domaine = "http://localhost:8080";
			ImageData image = ImageDataFactory.create(domaine + "/o/strasbourg-theme/images/vagues.jpg");
			Image img = new Image(image);
			document.add(img.setHorizontalAlignment(HorizontalAlignment.CENTER));
			Paragraph paragraph = new Paragraph().setFont(fontBold).setFontSize(20f)
					.setMarginTop(-10f).setMarginBottom(30f)
					.setTextAlignment(TextAlignment.CENTER);
			paragraph.add(offer.getPost(locale));
			document.add(paragraph);

			// info de l'offre
			//  encadré gris
			paragraph = new Paragraph().setBackgroundColor(new DeviceRgb(221, 221, 221))
					.setPaddings(30f, 30f, 30f, 30f);
			paragraph.add(new Text(LanguageUtil.get(locale, "eu.offer-publication-id") + " : ").setFont(fontBold).setFontSize(16f));
			paragraph.add("\n");
			paragraph.add(offer.getPublicationId());
			paragraph.add("\n\n");

			if(offer.getPostNumber() != null && offer.getOfferTypeRecrutement().getTitle(locale)=="Stage") {
				paragraph.add(new Text(LanguageUtil.get(locale, "eu.offer-post-number") + " : ").setFont(fontBold).setFontSize(16f));
				paragraph.add("\n");
				paragraph.add(offer.getPostNumber());
				paragraph.add("\n\n");
			}

			paragraph.add(new Text(LanguageUtil.get(locale, "eu.offer-type-recrutement") + " : ").setFont(fontBold).setFontSize(16f));
			paragraph.add("\n");
			paragraph.add(offer.getOfferTypeRecrutement().getTitle(locale));
			paragraph.add("\n\n");

			if(offer.getPermanentDescription(locale) != null && !offer.getPermanentDescription(locale).equals("") && offer.getOfferTypeRecrutement().getTitle(locale)!="Stage") {
				paragraph.add(new Text(LanguageUtil.get(locale, "eu.offer-permanent-description") + " : ").setFont(fontBold).setFontSize(16f));
				paragraph.add("\n");
				paragraph.add(offer.getPermanentDescription(locale));
				paragraph.add("\n\n");
			}

			if(offer.getDuration(locale) != null && !offer.getDuration(locale).equals("")) {
				paragraph.add(new Text(LanguageUtil.get(locale, "eu.offer-duree-contrat") + " : ").setFont(fontBold).setFontSize(16f));
				paragraph.add("\n");
				paragraph.add(offer.getDuration(locale));
				paragraph.add("\n\n");
			}

			paragraph.add(new Text(LanguageUtil.get(locale, "eu.offer-direction") + " : ").setFont(fontBold).setFontSize(16f));
			paragraph.add("\n");
			paragraph.add(offer.getOfferDirection().getTitle(locale));
			paragraph.add("\n\n");

			if(offer.getOfferService() != null) {
				paragraph.add(new Text(LanguageUtil.get(locale, "eu.offer-service") + " : ").setFont(fontBold).setFontSize(16f));
				paragraph.add("\n");
				paragraph.add(offer.getOfferService().getTitle(locale));
				paragraph.add("\n\n");
			}

			if(offer.getOfferTypeRecrutement().getTitle(locale)!="Stage") {
				paragraph.add(new Text(LanguageUtil.get(locale, "eu.offer-is-full-time") + " : ").setFont(fontBold).setFontSize(16f));
				paragraph.add("\n");
				if(offer.getIsFullTime())
					paragraph.add(LanguageUtil.get(locale, "eu.offer-full-time-true"));
				else
					paragraph.add(LanguageUtil.get(locale, "eu.offer-full-time-false"));
				paragraph.add("\n\n");
			}

			if(offer.getFullTimeDescription(locale) != null && !offer.getFullTimeDescription(locale).equals("") && offer.getIsFullTime() && offer.getOfferTypeRecrutement().getTitle(locale)!="Stage") {
				paragraph.add(new Text(LanguageUtil.get(locale, "eu.offer-full-time-description") + " : ").setFont(fontBold).setFontSize(16f));
				paragraph.add("\n");
				paragraph.add(offer.getFullTimeDescription(locale));
				paragraph.add("\n\n");
			}

			if(offer.getOfferFiliere() != null && offer.getOfferTypeRecrutement().getTitle(locale)!="Stage") {
				paragraph.add(new Text(LanguageUtil.get(locale, "eu.offer-filiere") + " : ").setFont(fontBold).setFontSize(16f));
				paragraph.add("\n");
				paragraph.add(offer.getOfferFiliere().getTitle(locale));
				paragraph.add("\n\n");
			}

			if(offer.getOfferFiliereCategorie() != null && offer.getOfferTypeRecrutement().getTitle(locale)!="Stage") {
				paragraph.add(new Text(LanguageUtil.get(locale, "eu.offer-filiere-categorie") + " : ").setFont(fontBold).setFontSize(16f));
				paragraph.add("\n");
				paragraph.add(offer.getOfferFiliereCategorie().getTitle(locale));
				paragraph.add("\n\n");
			}

			if(offer.getOfferGrade() != null && offer.getOfferTypeRecrutement().getTitle(locale)!="Stage") {
				paragraph.add(new Text(LanguageUtil.get(locale, "eu.offer-grade") + " : ").setFont(fontBold).setFontSize(16f));
				paragraph.add("\n");
				paragraph.add(offer.getOfferGrade().getTitle(locale));
				paragraph.add("\n\n");
			}

			if(offer.getOfferNiveauEtude() != null && offer.getOfferTypeRecrutement().getTitle(locale)=="Stage") {
				paragraph.add(new Text(LanguageUtil.get(locale, "eu.offer-niveau-etude") + " : ").setFont(fontBold).setFontSize(16f));
				paragraph.add("\n");
				paragraph.add(offer.getOfferNiveauEtude().getTitle(locale));
				paragraph.add("\n\n");
			}

			paragraph.add(new Text(LanguageUtil.get(locale, "eu.offer-limit-date") + " : ").setFont(fontBold).setFontSize(16f));
			paragraph.add("\n");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			paragraph.add(sdf.format(offer.getLimitDate()));

			if(offer.getOfferFamille() != null) {
				paragraph.add("\n\n");
				paragraph.add(new Text(LanguageUtil.get(locale, "eu.offer-famille-metier") + " : ").setFont(fontBold).setFontSize(16f));
				paragraph.add("\n");
				paragraph.add(offer.getOfferFamille().getTitle(locale));
			}

			if(offer.getOfferContact() != null && offer.getTypePublication().getTitle(locale)=="Interne uniquement") {
				paragraph.add("\n\n");
				paragraph.add(new Text(LanguageUtil.get(locale, "eu.offer-nom-RE") + " : ").setFont(fontBold).setFontSize(16f));
				paragraph.add("\n");
				paragraph.add(offer.getOfferContact().getTitle(locale));
			}

			if(offer.getContact() != null  && (offer.getOfferTypeRecrutement().getTitle(locale)=="Stage" || offer.getTypePublication().getTitle(locale)=="Interne uniquement")) {
				paragraph.add("\n\n");
				paragraph.add(new Text(LanguageUtil.get(locale, "eu.offer-nom-RRH") + " : ").setFont(fontBold).setFontSize(16f));
				paragraph.add("\n");
				paragraph.add(offer.getContact());
			}
			document.add(paragraph);

			// reste du document
			List<IElement> elements = HtmlConverter.convertToElements(offer.getIntroduction(locale));
			for (IElement element : elements) {
				document.add((IBlockElement)element);
			}

			paragraph = new Paragraph().setMarginBottom(-10f);
			paragraph.add(new Text(LanguageUtil.get(locale, "eu.offer-activities") + " : ").setFont(fontBold).setFontSize(16f));
			document.add(paragraph);
			elements = HtmlConverter.convertToElements(offer.getActivities(locale));
			for (IElement element : elements) {
				document.add((IBlockElement)element);
			}

			paragraph = new Paragraph().setMarginBottom(-10f);
			paragraph.add(new Text(LanguageUtil.get(locale, "eu.offer-profil") + " : ").setFont(fontBold).setFontSize(16f));
			document.add(paragraph);
			elements = HtmlConverter.convertToElements(offer.getProfil(locale));
			for (IElement element : elements) {
				document.add((IBlockElement)element);
			}

			paragraph = new Paragraph().setMarginBottom(-10f);
			paragraph.add(new Text(LanguageUtil.get(locale, "eu.offer-conditions") + " : ").setFont(fontBold).setFontSize(16f));
			document.add(paragraph);
			elements = HtmlConverter.convertToElements(offer.getConditions(locale));
			for (IElement element : elements) {
				document.add((IBlockElement)element);
			}

			if(offer.getAvantages(locale) != null && offer.getOfferTypeRecrutement().getTitle(locale)!="Stage") {
				paragraph = new Paragraph().setMarginBottom(-10f);
				paragraph.add(new Text(LanguageUtil.get(locale, "eu.offer-avantages") + " : ").setFont(fontBold).setFontSize(16f));
				document.add(paragraph);
				elements = HtmlConverter.convertToElements(offer.getAvantages(locale));
				for (IElement element : elements) {
					document.add((IBlockElement)element);
				}
			}

			if (pdf != null)
				pdf.close();

			if (pdfWriter != null)
				pdfWriter.close();
		}

		return offer.getPost(locale);
	}
}