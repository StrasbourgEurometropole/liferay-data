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

package eu.strasbourg.service.csmap.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.agenda.model.CacheJson;
import eu.strasbourg.service.agenda.model.Campaign;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.service.CacheJsonLocalServiceUtil;
import eu.strasbourg.service.agenda.service.CampaignLocalServiceUtil;
import eu.strasbourg.service.csmap.model.Agenda;
import eu.strasbourg.service.csmap.model.CacheAgendaJson;
import eu.strasbourg.service.csmap.service.AgendaLocalServiceUtil;
import eu.strasbourg.service.csmap.service.base.CacheAgendaJsonLocalServiceBaseImpl;
import eu.strasbourg.utils.FileEntryHelper;
import eu.strasbourg.utils.SearchHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import eu.strasbourg.utils.UriHelper;
import org.osgi.service.component.annotations.Component;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * The implementation of the cache agenda json local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>eu.strasbourg.service.csmap.service.CacheAgendaJsonLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CacheAgendaJsonLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=eu.strasbourg.service.csmap.model.CacheAgendaJson",
	service = AopService.class
)
public class CacheAgendaJsonLocalServiceImpl
	extends CacheAgendaJsonLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>eu.strasbourg.service.csmap.service.CacheAgendaJsonLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>eu.strasbourg.service.csmap.service.CacheAgendaJsonLocalServiceUtil</code>.
	 *//**
	 * Met à jour le cache des agendas
	 */
	@Override
	public void updateCacheAgendaJson() {
		CacheAgendaJson cacheJson = this.cacheAgendaJsonLocalService.fetchCacheAgendaJson(0);
		if(Validator.isNull(cacheJson))
			cacheJson = this.cacheAgendaJsonLocalService.createCacheAgendaJson(0);

		JSONObject json = JSONFactoryUtil.createJSONObject();

		// On récupère tous les ids events de l'agenda principal
		Agenda principal = AgendaLocalServiceUtil.getAgendaPrincipal();
		JSONObject jsonPrincipal = JSONFactoryUtil.createJSONObject();
		JSONArray jsonIds = getJsonIds(principal);
		jsonPrincipal.put("ids", jsonIds);
		json.put("principal", jsonPrincipal);

		// On récupère tous les ids events de l'agenda thématique s'il y en a un
		Agenda thematique = AgendaLocalServiceUtil.getAgendaThematiqueActif();
		JSONObject jsonThematique = JSONFactoryUtil.createJSONObject();
		if(Validator.isNotNull(thematique)) {
			jsonIds = getJsonIds(thematique);
			jsonThematique.put("ids", jsonIds);

			JSONObject jsonTitle = JSONFactoryUtil.createJSONObject();
			jsonTitle.put("fr_FR", thematique.getEditorialTitle(Locale.FRANCE));
			jsonThematique.put("title", jsonTitle);

			JSONObject jsonSubtitle = JSONFactoryUtil.createJSONObject();
			jsonSubtitle.put("fr_FR", thematique.getSubtitle(Locale.FRANCE));
			jsonThematique.put("subtitle", jsonSubtitle);

			String imageURL = "";
			try {
				if (thematique.getImageId() != null && thematique.getImageId() > 0)
					imageURL = StrasbourgPropsUtil.getURL() + UriHelper.
							appendUriImagePreview(FileEntryHelper.getFileEntryURLWithTimeStamp(thematique.getImageId()));
			} catch (URISyntaxException e) {
				_log.error(e);
			}
			jsonThematique.put("imageURL", imageURL);

		}
		json.put("thematique", jsonThematique);

		cacheJson.setJson(json.toString());
		this.cacheAgendaJsonLocalService.updateCacheAgendaJson(cacheJson);
	}

	private JSONArray getJsonIds(Agenda agenda) {

		// ClassNames de la configuration
		String className = Event.class.getName();

		// catégories
		List<Long[]> categoriesIds = new ArrayList<>();
		Long[] categoriesIdsForTheme = ArrayUtil
				.toLongArray(StringUtil.split(agenda.getThemesIds(), ",", 0));
		categoriesIds.add(categoriesIdsForTheme);
		Long[] categoriesIdsForType = ArrayUtil
				.toLongArray(StringUtil.split(agenda.getTypesIds(), ",", 0));
		categoriesIds.add(categoriesIdsForType);

		// tags
		String[] tagsArray = StringUtil.split(agenda.getTags());

		// campaigns
		// on récupère le nom de la campagne et non l'id
		StringBuilder campaignsTitle = new StringBuilder();
		if (!agenda.getCampaignsIds().isEmpty()){
			for (String campaignId : agenda.getCampaignsIds().split(",")) {
				Campaign campaign = CampaignLocalServiceUtil.fetchCampaign(Long.parseLong(campaignId));
				if (Validator.isNotNull(campaign)) {
					if (campaignsTitle.length() > 0)
						campaignsTitle.append(",");
					campaignsTitle.append(FriendlyURLNormalizerUtil
							.normalize(campaign.getTitleCurrentValue()));
				}
			}
		}
		String[] campaignsArray = StringUtil.split(campaignsTitle.toString());

		// Recherche
		Hits hits = SearchHelper.getEventsAgendaWebServiceSearchHits(className, categoriesIds, tagsArray, campaignsArray);

		JSONArray jsonIds = JSONFactoryUtil.createJSONArray();
		if (hits != null) {
			List<CacheJson> cacheJsons = CacheJsonLocalServiceUtil.getCacheJsons(-1,-1);
			for (Document document : hits.getDocs()) {
				long id = Long.parseLong(document.get(Field.ENTRY_CLASS_PK));

				if((campaignsTitle.length() == 0) || campaignsTitle.toString().contains(document.get("campaign"))) {
					// on ne prend que les event présent dans cacheJson avec des schedules
					eu.strasbourg.service.agenda.model.CacheJson cacheJson = cacheJsons.stream().
							filter(c -> c.getEventId() == id).findFirst().orElse(null);
					if(Validator.isNotNull(cacheJson) && cacheJson.getHasSchedules())
						jsonIds.put(id);
				}
			}
		}

		return jsonIds;
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass());
}