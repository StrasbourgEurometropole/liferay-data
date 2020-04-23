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

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
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
	final static private String  POUR="Pour";
	final static private String  CONTRE="Contre";
	final static private String  ABSTENTION="Abstention";

	@Override
	public JSONObject getUserFront(long officialId) {

		JSONObject userFront = JSONFactoryUtil.createJSONObject();

		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		gc.set(Calendar.HOUR_OF_DAY, 0);
		gc.set(Calendar.MINUTE, 0);
		gc.set(Calendar.SECOND, 0);
		gc.set(Calendar.MILLISECOND, 0);
		List<CouncilSession> todayCouncils = CouncilSessionLocalServiceUtil.findByDate(gc.getTime());

		// Il y a un Conseil aujourd'hui
		if(todayCouncils.size() > 0) {
			CouncilSession todayCouncil =todayCouncils.get(0);

			List<Deliberation> deliberations = DeliberationLocalServiceUtil.findByCouncilSessionId(todayCouncil.getCouncilSessionId());
			// Trie par ordre inverse (plus facile pour chercher le dernier, juste à filter et get(0))
			List<Deliberation> sortedDeliberations = deliberations.stream()
					.sorted(Comparator.comparing(Deliberation::getOrder).reversed())
					.collect(Collectors.toList());
			// Si Toutes les délibs sont à "Créé" ou "Retiré"
			if(sortedDeliberations.stream().allMatch(x -> x.isCree() || x.isRetire())) {

			}
			// Au moins une délib à afficher ("Affichage en cours" / "Vote ouvert" / "Rejete" / "Adopte" / "Communique")
			else {
				// Une délib en "Affichage en cours"
				if(sortedDeliberations.stream().anyMatch(Deliberation::isAffichageEnCours)) {
					Deliberation delibAffichageEnCours = sortedDeliberations.stream().filter(Deliberation::isAffichageEnCours).collect(Collectors.toList()).get(0);

				}
				// Une délib est en "Vote ouvert"
				else if (sortedDeliberations.stream().anyMatch(Deliberation::isVoteOuvert)) {
					Deliberation delibVoteOuvert = sortedDeliberations.stream().filter(Deliberation::isVoteOuvert).collect(Collectors.toList()).get(0);

					Vote voteFromUser = VoteLocalServiceUtil.findByDeliberationIdandOfficialId(delibVoteOuvert.getDeliberationId(), officialId);
					List<Procuration> procurationsUserHave = ProcurationLocalServiceUtil.findByCouncilSessionIdAndOfficialVotersId(delibVoteOuvert.getCouncilSessionId(), officialId);
					Map<Long, String> mapVoteProcuration = new HashMap<Long, String>();

					for (Procuration procuration:procurationsUserHave) {
						Vote voteAbsent = VoteLocalServiceUtil.findByDeliberationIdandOfficialId(delibVoteOuvert.getDeliberationId(), procuration.getOfficialUnavailableId());
						if(voteAbsent != null) {
							mapVoteProcuration.put(procuration.getOfficialUnavailableId(), voteAbsent.getResult());
						}
					}


				}
				// Delib(s) en "Rejete"/"Adopte"/"Communique)
				else if (sortedDeliberations.stream().anyMatch(x -> x.isRejete() || x.isAdopte() || x.isCommunique())) {
					// On va filtrer par ces trois status, et prendre le premier de la liste
					// Comme elle est triè par ordre décroissant, on aura la dernière délib à ce statut, celle à afficher
					Deliberation delibRejeteCommunqiueAdopte = sortedDeliberations.stream().filter(x -> x.isRejete() || x.isAdopte() || x.isCommunique()).collect(Collectors.toList()).get(0);

					List<Vote> votesFromDelib = VoteLocalServiceUtil.findByDeliberationId(delibRejeteCommunqiueAdopte.getDeliberationId());
					List<String> officalsPour = new ArrayList<>();
					List<String> officalsContre= new ArrayList<>();
					List<String> officalsAbstention= new ArrayList<>();
					// On calcule seulement s'il y a des votes (Comme on peut Adopter sans voter et mettre en Communqiue)
					if(votesFromDelib.size() >0) {
						for (Vote vote:votesFromDelib) {
							switch (vote.getResult()) {
								case POUR:
									Official officialPour = OfficialLocalServiceUtil.fetchOfficial(vote.getOfficialId());
									officalsPour.add(officialPour.getFullName());
									break;
								case CONTRE:
									Official officialContre = OfficialLocalServiceUtil.fetchOfficial(vote.getOfficialId());
									officalsContre.add(officialContre.getFullName());
									break;
								case ABSTENTION:
									Official officialAbstention = OfficialLocalServiceUtil.fetchOfficial(vote.getOfficialId());
									officalsAbstention.add(officialAbstention.getFullName());
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

		}

		return userFront;
	}

}