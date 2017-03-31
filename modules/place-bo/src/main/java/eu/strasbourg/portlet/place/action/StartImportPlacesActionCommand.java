/**
 * Copyright 2000-present Liferay, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.strasbourg.portlet.place.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import eu.strasbourg.service.place.model.Place;
import eu.strasbourg.service.place.service.PlaceLocalService;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.MailHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = { "javax.portlet.name=" + StrasbourgPortletKeys.PLACE_BO,
		"mvc.command.name=startImportPlaces" },
	service = MVCActionCommand.class)
public class StartImportPlacesActionCommand implements MVCActionCommand {

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

	private File placesFile = null;

	private String resultat;
	private String messagesErreurs;
	private List<String> listLieuxCrees = new ArrayList<String>();
	private List<String> listLieuxModifies = new ArrayList<String>();
	private List<String> listLieuxErreurs = new ArrayList<String>();
	private ServiceContext sc = null;

	@Override
	public boolean processAction(ActionRequest request, ActionResponse response)
		throws PortletException {

		try {
			resultat = "Succès";
			messagesErreurs = "";
			sc = ServiceContextFactory.getInstance(request);

			ThemeDisplay td = (ThemeDisplay) request
				.getAttribute(WebKeys.THEME_DISPLAY);
			sc.setScopeGroupId(td.getCompanyGroupId());
			sc.setUserId(td.getUserId());
		} catch (PortalException e) {
			_log.error(e);
		}

		UploadPortletRequest uploadRequest = PortalUtil
			.getUploadPortletRequest(request);
		placesFile = uploadRequest.getFile("places-file");
		_log.info("Start import");

		String idSIG = "";
		String alias = "";
		String[] idCategSIG;
		String complement = "";
		String voie = "";
		String distribution = "";
		String codePostal = "";
		String pays = "";
		String mercatorX = "";
		String mercatorY = "";
		String rgf93X = "";
		String rgf93Y = "";
		String[] idTerritoireSIG;

		if (placesFile != null) {

			FileReader fr = null;
			try {
				fr = new FileReader(placesFile);
				BufferedReader br = new BufferedReader(fr);

				try {
					String line = br.readLine();
					String[] chaine = line.split(";");

					if (chaine.length == 14
						&& chaine[0].equals("Identifiant_Lieu_SIG")
						&& chaine[1].equals("Alias_Lieu_SIG")
						&& chaine[2].equals("Identifiant_Categorie_SIG")
						&& chaine[3].equals("Complement_Adresse")
						&& chaine[4].equals("Voie")
						&& chaine[5].equals("Mentions_Distribution")
						&& chaine[6].equals("Code_Postal")
						&& chaine[7].equals("Ville") && chaine[8].equals("Pays")
						&& chaine[9].equals("Coordonnees_SIG_Mercator_X")
						&& chaine[10].equals("Coordonnees_SIG_Mercator_Y")
						&& chaine[11].equals("Coordonnees_SIG_RGF93_X")
						&& chaine[12].equals("Coordonnees_SIG_RGF93_Y")
						&& chaine[13].equals("Identifiant_Territoire")) {

						try {

							int ligne = 1;

							// Récupération du vocabulaire Type Lieu
							AssetVocabulary vocabularyTypeLieu = AssetVocabularyHelper
								.getGlobalVocabulary("Type de lieu");

							// Récupération des catégories du
							// vocabulaire
							// Type de Lieu
							List<AssetCategory> categoriesTypeLieu = vocabularyTypeLieu
								.getCategories();
							Map<String, Long> listTypeLieu = new HashMap<String, Long>();
							for (AssetCategory category : categoriesTypeLieu) {
								listTypeLieu.put(
									AssetVocabularyHelper.getCategoryProperty(
										category.getCategoryId(), "SIG"),
									category.getCategoryId());
							}

							// Récupération du vocabulaire Territoire
							AssetVocabulary vocabularyTerritoire = AssetVocabularyHelper
								.getGlobalVocabulary("Territoire");

							// Récupération des catégories du
							// vocabulaire Territoire
							List<AssetCategory> categoriesTerritoire = vocabularyTerritoire
								.getCategories();
							Map<String, Long> SIGId_categoryIdMap = new HashMap<String, Long>();
							for (AssetCategory category : categoriesTerritoire) {
								SIGId_categoryIdMap.put(
									AssetVocabularyHelper.getCategoryProperty(
										category.getCategoryId(), "SIG"),
									category.getCategoryId());
							}

							for (line = br.readLine(); line != null; line = br
								.readLine()) {
								chaine = line.split(";");

								ligne++;

								idSIG = chaine[0];
								alias = chaine[1];
								idCategSIG = chaine[2].split(",");
								complement = chaine[3];
								voie = chaine[4];
								distribution = chaine[5];
								codePostal = chaine[6];
								pays = chaine[8];
								mercatorX = chaine[9];
								mercatorY = chaine[10];
								rgf93X = chaine[11];
								rgf93Y = chaine[12];
								idTerritoireSIG = chaine[13].split(",");

								if (!idSIG.equals("") && !alias.equals("")
									&& idCategSIG.length > 0 && !voie.equals("")
									&& !codePostal.equals("")
									&& !pays.equals("") && !mercatorX.equals("")
									&& !mercatorY.equals("")
									&& !rgf93X.equals("") && !rgf93Y.equals("")
									&& idTerritoireSIG.length > 0) {

									boolean categoryExiste = true;
									for (String category : idCategSIG) {
										if (listTypeLieu
											.get(category.trim()) == null)
											categoryExiste = false;
									}
									if (categoryExiste) {
										boolean territoireExiste = true;
										for (String territoire : idTerritoireSIG) {
											if (SIGId_categoryIdMap
												.get(territoire.trim()) == null)
												territoireExiste = false;
										}
										if (territoireExiste) {
											Place place = this._placeLocalService
												.getPlaceBySIGId(idSIG);

											if (place == null) {
												sc.setWorkflowAction(
													WorkflowConstants.ACTION_SAVE_DRAFT);
												place = this._placeLocalService
													.createPlace(sc);
												place.setAliasMap(
													LocalizationUtil
														.getLocalizationMap(
															alias));
											} else {
												if (place.isApproved()) {
													sc.setWorkflowAction(
														WorkflowConstants.ACTION_PUBLISH);
												} else {
													sc.setWorkflowAction(
														WorkflowConstants.ACTION_SAVE_DRAFT);
												}
											}
											place.setSIGid(idSIG);
											place.setName(alias);
											place.setAddressComplement(
												complement);
											place.setAddressStreet(voie);
											place.setAddressDistribution(
												distribution);
											place.setAddressZipCode(codePostal);
											place.setAddressCountry(pays);
											place.setMercatorX(mercatorX);
											place.setMercatorY(mercatorY);
											place.setRGF93X(rgf93X);
											place.setRGF93Y(rgf93Y);

											// renseigner l'assetEntry
											List<Long> listAssetCategoryId = new ArrayList<>();
											String[] assetTagNames = {};
											boolean nouveau = true;
											if (!place.isNew()) {
												nouveau = false;
												assetTagNames = place
													.getAssetEntry()
													.getTagNames();

												for (long assetCategoryId : place
													.getAssetEntry()
													.getCategoryIds()) {
													AssetCategory assetCategory = AssetCategoryLocalServiceUtil
														.getAssetCategory(
															assetCategoryId);
													if (assetCategory != null
														&& assetCategory
															.getVocabularyId() != vocabularyTypeLieu
																.getVocabularyId()
														&& assetCategory
															.getVocabularyId() != vocabularyTerritoire
																.getVocabularyId())
														listAssetCategoryId.add(
															assetCategoryId);
												}
											}
											sc.setAssetTagNames(assetTagNames);

											for (String category : idCategSIG) {
												if (listTypeLieu.get(
													category.trim()) != null)
													listAssetCategoryId
														.add(listTypeLieu
															.get(
																category.trim())
															.longValue());
											}
											for (String territorySIGId : idTerritoireSIG) {
												if (Validator.isNotNull(
													territorySIGId)) {
													Long categoryId = SIGId_categoryIdMap
														.get(territorySIGId
															.trim())
														.longValue();
													listAssetCategoryId
														.add(categoryId);
												}
											}
											long[] categoryIds = ArrayUtil
												.toLongArray(
													listAssetCategoryId);
											sc.setAssetCategoryIds(categoryIds);

											try {
												this._placeLocalService
													.updatePlace(place, sc);
												if (nouveau) {
													listLieuxCrees.add(
														"N° ligne : " + ligne
															+ ", identifiant SIG : "
															+ idSIG
															+ ", nom du lieu : "
															+ alias + "\n");
													_log.info(
														"Lieu crée => N° ligne : "
															+ ligne
															+ ", identifiant SIG : "
															+ idSIG
															+ ", nom du lieu : "
															+ alias);
												} else {
													listLieuxModifies.add(
														"N° ligne : " + ligne
															+ ", identifiant SIG : "
															+ idSIG
															+ ", nom du lieu : "
															+ alias + "\n");
													_log.info(
														"Lieu modifié => N° ligne : "
															+ ligne
															+ ", identifiant SIG : "
															+ idSIG
															+ ", nom du lieu : "
															+ alias);
												}
											} catch (Exception e) {
												e.printStackTrace();
												resultat = "Réussi avec des erreurs";
												listLieuxErreurs
													.add("N° ligne : " + ligne
														+ ", identifiant SIG : "
														+ idSIG
														+ ", nom du lieu : "
														+ alias + " => "
														+ e.getMessage()
														+ ".\n");
												_log.info(
													"Erreur à la création/modification du lieu => N° ligne : "
														+ ligne
														+ ", identifiant SIG : "
														+ idSIG
														+ ", nom du lieu : "
														+ alias + " => "
														+ e.getMessage());
											}

										} else {
											resultat = "Réussi avec des erreurs";
											listLieuxErreurs.add("N° ligne : "
												+ ligne + ", identifiant SIG : "
												+ idSIG + ", nom du lieu : "
												+ alias
												+ " : Le(s) territoire(s) associé(s) à la ligne "
												+ ligne
												+ " n’existe(nt) pas.\n");
											_log.info(
												"Erreur à la création/modification du lieu => N° ligne : "
													+ ligne
													+ ", identifiant SIG : "
													+ idSIG + ", nom du lieu : "
													+ alias
													+ " => Le(s) territoire(s) associé(s) à la ligne n'existe(nt) pas.");
										}
									} else {
										resultat = "Réussi avec des erreurs";
										listLieuxErreurs.add("N° ligne : "
											+ ligne + ", identifiant SIG : "
											+ idSIG + ", nom du lieu : " + alias
											+ " : Le type de lieu de la ligne "
											+ ligne + " n’existe pas.\n");
										_log.info(
											"Erreur à la création/modification du lieu => N° ligne : "
												+ ligne + ", identifiant SIG : "
												+ idSIG + ", nom du lieu : "
												+ alias
												+ " => Le type de lieu de la ligne n'existe pas.");
									}
								} else {
									resultat = "Réussi avec des erreurs";
									String erreur = "N° ligne : " + ligne
										+ ", identifiant SIG : " + idSIG
										+ ", nom du lieu : " + alias;
									if (idSIG.equals("")) {
										erreur += "\nLe champ Identifiant_Lieu_SIG est manquant à la ligne "
											+ ligne;
									}
									if (alias.equals("")) {
										erreur += "\nLe champ Alias_Lieu_SIG est manquant à la ligne "
											+ ligne;
									}
									if (idCategSIG.length == 0) {
										erreur += "\nLe champ Identifiant_Categorie_SIG est manquant à la ligne "
											+ ligne;
									}
									if (voie.equals("")) {
										erreur += "\nLe champ Voie est manquant à la ligne "
											+ ligne;
									}
									if (codePostal.equals("")) {
										erreur += "\nLe champ Code_Postal est manquant à la ligne "
											+ ligne;
									}
									if (pays.equals("")) {
										erreur += "\nLe champ Pays est manquant à la ligne "
											+ ligne;
									}
									if (mercatorX.equals("")) {
										erreur += "\nLe champ Coordonnees_SIG_Mercator_X est manquant à la ligne "
											+ ligne;
									}
									if (mercatorY.equals("")) {
										erreur += "\nLe champ Coordonnees_SIG_Mercator_Y est manquant à la ligne "
											+ ligne;
									}
									if (rgf93X.equals("")) {
										erreur += "\nLe champ Coordonnees_SIG_RGF93_X est manquant à la ligne "
											+ ligne;
									}
									if (rgf93Y.equals("")) {
										erreur += "\nLe champ Coordonnees_SIG_RGF93_Y est manquant à la ligne "
											+ ligne;
									}
									if (idTerritoireSIG.length == 0) {
										erreur += "\nLe champ Identifiant_Territoire est manquant à la ligne "
											+ ligne;
									}
									erreur += "\n";
									listLieuxErreurs.add(erreur);
									_log.info(
										"Erreur à la création/modification du lieu => "
											+ erreur);
								}
							}
						} catch (PortalException e) {
							messagesErreurs += e.getMessage();
							resultat = "Erreur";
						}
					} else {
						messagesErreurs = "Le fichier ne respecte pas le formalisme attendu.";
						resultat = "Erreur";
					}
					br.close();
					fr.close();
				} catch (IOException e1) {
					messagesErreurs += "Lecture du fichier impossible.";
					resultat = "Erreur";
				}
			} catch (FileNotFoundException e) {
				messagesErreurs = "Fichier introuvable.";
				resultat = "Erreur";
			}
		} else

		{
			messagesErreurs = "Aucun fichier choisi.";
			resultat = "Erreur";
		}

		sendMail();
		_log.info("End import");

		return true;

	}

	public void sendMail() {

		String environment = StrasbourgPropsUtil.getEnvironment();
		String titre = environment + " Journal d’import des lieux - "
			+ resultat;
		String corps;
		if (resultat.equals("Erreur")) {
			corps = "L'import du fichier ";
			if (placesFile != null) {
				corps += placesFile.getName();
			}
			corps += " n’a pas pu être fait pour les raisons suivantes : \n"
				+ messagesErreurs;
		} else {
			String dateImport = new SimpleDateFormat("yyyy-MM-dd à HH:mm")
				.format(new Date());
			corps = "L'import du fichier " + placesFile.getName()
				+ " a été réalisé avec succès le " + dateImport + ".\n"
				+ "Lieux créés (" + listLieuxCrees.size() + ") :\n";
			for (String lieuxCrees : listLieuxCrees) {
				corps += lieuxCrees;
			}
			corps += "Lieux modifiés (" + listLieuxModifies.size() + ") :\n";
			for (String lieuxModifies : listLieuxModifies) {
				corps += lieuxModifies;
			}
			corps += "Lieux en erreur (" + listLieuxErreurs.size() + ") :\n";
			for (String lieuxErreurs : listLieuxErreurs) {
				corps += lieuxErreurs;
			}
		}

		String mailAddresses = StrasbourgPropsUtil.getPlaceImportMails();

		try {
			MailHelper.sendMailWithPlainText("no-reply@no-reply-strasbourg.eu",
				mailAddresses, titre, corps);
		} catch (Exception e) {
			_log.error(e);
		}
	}

	private PlaceLocalService _placeLocalService;

	@Reference(unbind = "-")
	protected void setPlaceLocalService(PlaceLocalService placeLocalService) {

		_placeLocalService = placeLocalService;
	}
}
