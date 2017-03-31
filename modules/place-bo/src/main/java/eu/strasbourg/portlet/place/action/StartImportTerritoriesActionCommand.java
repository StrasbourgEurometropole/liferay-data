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
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.MailHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(immediate = true, property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.PLACE_BO,
		"mvc.command.name=startImportTerritories" }, service = MVCActionCommand.class)
public class StartImportTerritoriesActionCommand implements MVCActionCommand {

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

	private File territoriesFile = null;

	private String resultat;
	private String messagesErreurs;
	private List<String> listCategoryCrees = new ArrayList<String>();
	private List<String> listCategoryModifies = new ArrayList<String>();
	private List<String> listCategoryErreurs = new ArrayList<String>();
	private ServiceContext sc = null;

	@Override
	public boolean processAction(ActionRequest request, ActionResponse response)
			throws PortletException {

		try {
			resultat = "Succès";
			messagesErreurs = "";
			sc = ServiceContextFactory.getInstance(request);
			sc.setWorkflowAction(WorkflowConstants.ACTION_PUBLISH);

			ThemeDisplay td = (ThemeDisplay) request
					.getAttribute(WebKeys.THEME_DISPLAY);
			sc.setScopeGroupId(td.getCompanyGroupId());
			sc.setUserId(td.getUserId());
		} catch (PortalException e) {
			_log.error(e);
		}

		UploadPortletRequest uploadRequest = PortalUtil
				.getUploadPortletRequest(request);
		territoriesFile = uploadRequest.getFile("territories-file");

		_log.info("Start import");

		String idTerritoire = "";
		String idParentTerritoire = "";
		String nom = "";

		if (territoriesFile != null) {

			FileReader fr = null;
			try {
				fr = new FileReader(territoriesFile);
				BufferedReader br = new BufferedReader(fr);

				try {
					String line = br.readLine();
					String[] chaine = line.split(";");

					if (chaine.length == 3 && chaine[1]
							.equals("Identifiant_Parent_Categorie_Territoire")
							&& chaine[2].equals("Nom_Categorie_Territoire")) {

						try {
							int ligne = 1;

							// Récupération du vocabulaire Territoire
							AssetVocabulary vocabulary = AssetVocabularyHelper
									.getGlobalVocabulary("Territoire");

							if (vocabulary != null) {

								for (line = br
										.readLine(); line != null; line = br
												.readLine()) {
									chaine = line.split(";");

									ligne++;

									idTerritoire = chaine[0];
									idParentTerritoire = chaine[1];
									nom = chaine[2];

									if (!idTerritoire.equals("")
											&& !nom.equals("")) {

										// Récupération des catégories du
										// vocabulaire Territoire
										List<AssetCategory> categories = vocabulary
												.getCategories();

										// Récupère la catégorie
										AssetCategory selectCategory = null;
										for (AssetCategory category : categories) {
											if (AssetVocabularyHelper
													.getCategoryProperty(
															category.getCategoryId(),
															"SIG")
													.equals(idTerritoire)) {
												selectCategory = category;
												break;
											}
										}

										// Récupère la catégorie parent
										AssetCategory categoryParent = null;
										long idParent = 0;
										if (!idParentTerritoire.equals("")) {
											for (AssetCategory category : categories) {
												if (AssetVocabularyHelper
														.getCategoryProperty(
																category.getCategoryId(),
																"SIG")
														.equals(idParentTerritoire)) {
													categoryParent = category;
													idParent = categoryParent
															.getCategoryId();
													break;
												}
											}
										}

										if (idParentTerritoire.equals("")
												|| categoryParent != null) {

											String[] categoryProperties = {
													"SIG:" + idTerritoire };

											if (selectCategory == null) {

												try {
													_assetCategoryService
															.addCategory(
																	sc.getScopeGroupId(),
																	idParent,
																	LocalizationUtil
																			.getLocalizationMap(
																					nom),
																	null,
																	vocabulary
																			.getVocabularyId(),
																	categoryProperties,
																	sc);

													listCategoryCrees
															.add("N° ligne : "
																	+ ligne
																	+ ", identifiant territoire : "
																	+ idTerritoire
																	+ ", nom du territoire : "
																	+ nom
																	+ "\n");
													_log.info(
															"territoire crée => N° ligne : "
																	+ ligne
																	+ ", identifiant territoire : "
																	+ idTerritoire
																	+ ", nom du territoire : "
																	+ nom);
												} catch (Exception e) {
													resultat = "Réussi avec des erreurs";
													listCategoryErreurs
															.add("N° ligne : "
																	+ ligne
																	+ ", identifiant territoire : "
																	+ idTerritoire
																	+ ", nom du territoire : "
																	+ nom
																	+ " => "
																	+ e.getMessage()
																	+ ".\n");
													_log.info(
															"Erreur à la création du territoire => N° ligne : "
																	+ ligne
																	+ ", identifiant territoire : "
																	+ idTerritoire
																	+ ", nom du territoire : "
																	+ nom
																	+ " => "
																	+ e.getMessage()
																	+ ".");
												}
											} else {
												try {
													_assetCategoryService
															.updateCategory(
																	selectCategory
																			.getCategoryId(),
																	idParent,
																	LocalizationUtil
																			.getLocalizationMap(
																					nom),
																	null,
																	vocabulary
																			.getVocabularyId(),
																	categoryProperties,
																	sc);

													listCategoryModifies
															.add("N° ligne : "
																	+ ligne
																	+ ", identifiant territoire : "
																	+ idTerritoire
																	+ ", nom du territoire : "
																	+ nom
																	+ "\n");
													_log.info(
															"Territoire modifié => N° ligne : "
																	+ ligne
																	+ ", identifiant territoire : "
																	+ idTerritoire
																	+ ", nom du territoire : "
																	+ nom);
												} catch (Exception e) {
													resultat = "Réussi avec des erreurs";
													listCategoryErreurs
															.add("N° ligne : "
																	+ ligne
																	+ ", identifiant territoire : "
																	+ idTerritoire
																	+ ", nom du territoire : "
																	+ nom
																	+ " => "
																	+ e.getMessage()
																	+ ".\n");
													_log.info(
															"Erreur à la modification du territoire => N° ligne : "
																	+ ligne
																	+ ", identifiant territoire : "
																	+ idTerritoire
																	+ ", nom du territoire : "
																	+ nom
																	+ " => "
																	+ e.getMessage()
																	+ ".");
												}
											}
										} else {
											resultat = "Réussi avec des erreurs";
											listCategoryErreurs
													.add("N° ligne : " + ligne
															+ ", identifiant territoire : "
															+ idTerritoire
															+ ", nom du territoire : "
															+ nom
															+ " => Le parent associé à la ligne "
															+ ligne
															+ " n’existe pas.\n");
											_log.info(
													"Erreur à la création/modification du territoire => N° ligne : "
															+ ligne
															+ ", identifiant territoire : "
															+ idTerritoire
															+ ", nom du territoire : "
															+ nom
															+ " => Le parent associé à la ligne n’existe pas.");
										}
									} else {
										resultat = "Réussi avec des erreurs";
										String erreur = "N° ligne : " + ligne
												+ ", identifiant territoire : "
												+ idTerritoire
												+ ", nom du territoire : " + nom
												+ "";
										if (idTerritoire.equals("")) {
											erreur += "\nLe champ Identifiant_Categories_SIG est manquant à la ligne "
													+ ligne;
										}
										if (nom.equals("")) {
											erreur += "\nLe champ Nom_Catégorie_SIG est manquant à la ligne "
													+ ligne;
										}
										erreur += "\n";
										listCategoryErreurs.add(erreur);
										_log.info(
												"Erreur à la création/modification du territoire => N° ligne : "
														+ ligne
														+ ", identifiant territoire : "
														+ idTerritoire
														+ ", nom du territoire : "
														+ nom
														+ " => champ Identifiant_Categories_SIG et/ou Nom_Catégorie_SIG manquant(s).");
									}
								}
							} else {
								messagesErreurs = "Le vocabulaire Territoire n'existe pas.";
								resultat = "Erreur";
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
		_log.info("End import");
		
		sendMail();

		return true;
	}

	public void sendMail() {

		String environment = StrasbourgPropsUtil.getEnvironment();
		String titre = environment + " Journal d’import des territoires - "
				+ resultat;
		String corps;
		if (resultat.equals("Erreur")) {
			corps = "L'import du fichier ";
			if (territoriesFile != null) {
				corps += territoriesFile.getName();
			}
			corps += " n’a pas pu être fait pour les raisons suivantes : \n"
					+ messagesErreurs;
		} else {
			String dateImport = new SimpleDateFormat("yyyy-MM-dd à HH:mm")
					.format(new Date());
			corps = "L'import du fichier " + territoriesFile.getName()
					+ " a été réalisé avec succès le " + dateImport + ".\n"
					+ "Territoires créés (" + listCategoryCrees.size()
					+ ") :\n";
			for (String lieuxCrees : listCategoryCrees) {
				corps += lieuxCrees;
			}
			corps += "Territoires modifiés (" + listCategoryModifies.size()
					+ ") :\n";
			for (String lieuxModifies : listCategoryModifies) {
				corps += lieuxModifies;
			}
			corps += "Territoires en erreur (" + listCategoryErreurs.size()
					+ ") :\n";
			for (String lieuxErreurs : listCategoryErreurs) {
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

	@Reference(unbind = "-")
	protected void setAssetCategoryService(
			AssetCategoryService assetCategoryService) {

		_assetCategoryService = assetCategoryService;
	}

	private AssetCategoryService _assetCategoryService;
}