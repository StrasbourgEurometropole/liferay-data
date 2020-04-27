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

package eu.strasbourg.service.council.service.impl;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import eu.strasbourg.service.council.constants.StageDeliberation;
import eu.strasbourg.service.council.model.*;
import eu.strasbourg.service.council.service.*;
import eu.strasbourg.service.council.service.base.DeliberationServiceBaseImpl;

import java.util.*;
import java.util.stream.Collectors;

/**
 * The implementation of the deliberation remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.council.service.DeliberationService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DeliberationServiceBaseImpl
 * @see eu.strasbourg.service.council.service.DeliberationServiceUtil
 */
public class DeliberationServiceImpl extends DeliberationServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.council.service.DeliberationServiceUtil} to access the deliberation remote service.
	 */

	private final Log _log = LogFactoryUtil.getLog(this.getClass());

	final static private String  POUR="Pour";
	final static private String  CONTRE="Contre";
	final static private String  ABSTENTION="Abstention";

	@Override
	public JSONObject getUserFront(long officialId) {

		JSONObject userFront = JSONFactoryUtil.createJSONObject();

		//Prépare les objets JSON en avance, pour les remplir au fur et à mesure
		JSONObject session = JSONFactoryUtil.createJSONObject();
		JSONObject deliberation = JSONFactoryUtil.createJSONObject();
		JSONObject votes = JSONFactoryUtil.createJSONObject();
		JSONObject official = JSONFactoryUtil.createJSONObject();
		JSONArray procurations = JSONFactoryUtil.createJSONArray();
		JSONArray pour = JSONFactoryUtil.createJSONArray();
		JSONArray contre = JSONFactoryUtil.createJSONArray();
		JSONArray abstention = JSONFactoryUtil.createJSONArray();

		// On remplit l'info JSON du User
		official.put("officialId", officialId);

		try {

			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(new Date());
			gc.set(Calendar.HOUR_OF_DAY, 0);
			gc.set(Calendar.MINUTE, 0);
			gc.set(Calendar.SECOND, 0);
			gc.set(Calendar.MILLISECOND, 0);
			List<CouncilSession> todayCouncils = CouncilSessionLocalServiceUtil.findByDate(gc.getTime());

			// Il y a un Conseil aujourd'hui
			if (todayCouncils.size() > 0) {
				CouncilSession todayCouncil = todayCouncils.get(0);

				//Remplit les infos de la session pour le JSON
				session.put("councilSessionId", todayCouncil.getCouncilSessionId());
				session.put("title", todayCouncil.getTitle());

				List<Deliberation> deliberations = DeliberationLocalServiceUtil.findByCouncilSessionId(todayCouncil.getCouncilSessionId());
				// Trie par ordre inverse (plus facile pour chercher le dernier, juste à filter et get(0))
				List<Deliberation> sortedDeliberations = deliberations.stream()
						.sorted(Comparator.comparing(Deliberation::getStatusDate).reversed())
						.collect(Collectors.toList());
				// Si Toutes les délibs sont à "Créé" ou "Retiré"
				if (sortedDeliberations.stream().allMatch(x -> x.isCree() || x.isRetire())) {
					// JSON pas de délib
					userFront.put("message", "no-deliberation-yet");
				}
				// Au moins une délib à afficher ("Affichage en cours" / "Vote ouvert" / "Rejete" / "Adopte" / "Communique")
				else {
					// Une délib en "Affichage en cours"
					if (sortedDeliberations.stream().anyMatch(Deliberation::isAffichageEnCours)) {
						Deliberation delibAffichageEnCours = sortedDeliberations.stream().filter(Deliberation::isAffichageEnCours).collect(Collectors.toList()).get(0);

						//Remplit les infos JSON de la delib
						deliberation.put("deliberationId", delibAffichageEnCours.getDeliberationId());
						deliberation.put("order", delibAffichageEnCours.getOrder());
						deliberation.put("title", delibAffichageEnCours.getTitle());
						deliberation.put("stage", delibAffichageEnCours.getStage());

					}
					// Une délib est en "Vote ouvert"
					else if (sortedDeliberations.stream().anyMatch(Deliberation::isVoteOuvert)) {
						Deliberation delibVoteOuvert = sortedDeliberations.stream().filter(Deliberation::isVoteOuvert).collect(Collectors.toList()).get(0);

						//Remplit les infos JSON de la delib
						deliberation.put("deliberationId", delibVoteOuvert.getDeliberationId());
						deliberation.put("order", delibVoteOuvert.getOrder());
						deliberation.put("title", delibVoteOuvert.getTitle());
						deliberation.put("stage", delibVoteOuvert.getStage());

						Vote voteFromUser = VoteLocalServiceUtil.findByDeliberationIdandOfficialId(delibVoteOuvert.getDeliberationId(), officialId);
						List<Procuration> procurationsUserHave = ProcurationLocalServiceUtil.findByCouncilSessionIdAndOfficialVotersId(delibVoteOuvert.getCouncilSessionId(), officialId);

						//Remplit l'info de l'élu
						if (voteFromUser != null) {
							official.put("vote", voteFromUser.getResult());
						}
						for (Procuration procuration : procurationsUserHave) {
							Vote voteAbsent = VoteLocalServiceUtil.findByDeliberationIdandOfficialId(delibVoteOuvert.getDeliberationId(), procuration.getOfficialUnavailableId());
							Official officalUnavailable = OfficialLocalServiceUtil.fetchOfficial(procuration.getOfficialUnavailableId());
							// On ajoute l'info JSON des procurations
							JSONObject procu = JSONFactoryUtil.createJSONObject();
							procu.put("officialUnavailableId", procuration.getOfficialUnavailableId());
							procu.put("fullName", officalUnavailable != null ? officalUnavailable.getFullName() : null);
							procu.put("vote", voteAbsent != null ? voteAbsent.getResult() : null);
							procurations.put(procu);
						}

					}
					// Delib(s) en "Rejete"/"Adopte"/"Communique)
					else if (sortedDeliberations.stream().anyMatch(x -> x.isRejete() || x.isAdopte() || x.isCommunique())) {
						// On va filtrer par ces trois status, et prendre le premier de la liste
						// Comme elle est triè par ordre décroissant, on aura la dernière délib à ce statut, celle à afficher
						Deliberation delibRejeteCommunqiueAdopte = sortedDeliberations.stream().filter(x -> x.isRejete() || x.isAdopte() || x.isCommunique()).collect(Collectors.toList()).get(0);

						//Remplit les infos JSON de la delib
						deliberation.put("deliberationId", delibRejeteCommunqiueAdopte.getDeliberationId());
						deliberation.put("order", delibRejeteCommunqiueAdopte.getOrder());
						deliberation.put("title", delibRejeteCommunqiueAdopte.getTitle());
						deliberation.put("stage", delibRejeteCommunqiueAdopte.getStage());

						List<Vote> votesFromDelib = VoteLocalServiceUtil.findByDeliberationId(delibRejeteCommunqiueAdopte.getDeliberationId());
						List<String> officalsPour = new ArrayList<>();
						List<String> officalsContre = new ArrayList<>();
						List<String> officalsAbstention = new ArrayList<>();
						// On calcule seulement s'il y a des votes (Comme on peut Adopter sans voter et mettre en Communqiue)
						if (votesFromDelib.size() > 0) {
							for (Vote vote : votesFromDelib) {
								switch (vote.getResult()) {
									case POUR:
										Official officialPour = OfficialLocalServiceUtil.fetchOfficial(vote.getOfficialId());
										// On met l'info dans le JSON pour les vote POUR
										pour.put(officialPour.getFullName());
										break;
									case CONTRE:
										Official officialContre = OfficialLocalServiceUtil.fetchOfficial(vote.getOfficialId());
										// On met l'info dans le JSON pour les vote CONTRE
										contre.put(officialContre.getFullName());
										break;
									case ABSTENTION:
										Official officialAbstention = OfficialLocalServiceUtil.fetchOfficial(vote.getOfficialId());
										// On met l'info dans le JSON pour les vote ABSTENTION
										abstention.put(officialAbstention.getFullName());
										break;
									default:
										break;
								}
							}
						}
					}
				}
			}
			// Il n'y a pas de Conseil aujourd'hui
			else {
				// JSON pas de session
				userFront.put("message", "no-council-today");
			}
		} catch (Exception e) {
			//On assemble les pièces du puzzle, les ingrédients du Tacos + ingrédient du crime
			userFront.put("session", session);
			userFront.put("message", "council.technical-error");
			votes.put("approve", pour);
			votes.put("against", contre);
			votes.put("abstention", abstention);
			deliberation.put("votes", votes);
			userFront.put("deliberation", deliberation);
			official.put("procurations", procurations);
			userFront.put("official", official);

			// Et on log
			_log.error(e);
		}

		//On assemble les pièces du puzzle, les ingrédients du Tacos
		userFront.put("session", session);
		votes.put("approve", pour);
		votes.put("against", contre);
		votes.put("abstention", abstention);
		deliberation.put("votes", votes);
		userFront.put("deliberation", deliberation);
		official.put("procurations", procurations);
		userFront.put("official", official);

		return userFront;
	}
}