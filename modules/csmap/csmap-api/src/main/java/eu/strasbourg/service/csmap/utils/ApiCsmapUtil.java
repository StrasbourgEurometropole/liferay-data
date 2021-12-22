package eu.strasbourg.service.csmap.utils;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
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
import eu.strasbourg.service.agenda.model.Historic;
import eu.strasbourg.service.agenda.service.CacheJsonLocalServiceUtil;
import eu.strasbourg.service.agenda.service.CampaignLocalServiceUtil;
import eu.strasbourg.service.agenda.service.HistoricLocalServiceUtil;
import eu.strasbourg.service.csmap.model.Agenda;
import eu.strasbourg.service.csmap.service.AgendaLocalServiceUtil;
import eu.strasbourg.utils.*;

import java.net.URISyntaxException;
import java.time.format.DateTimeParseException;
import java.util.*;

public class ApiCsmapUtil {

    public static JSONObject getEvents(String lastUpdateTimeString) throws JSONException {

        // On transforme la date string en date
        Date lastUpdateTime;
        try {
            long lastUpdateTimeLong = Long.parseLong(lastUpdateTimeString);
            lastUpdateTime = DateHelper.getDateFromUnixTimestamp(lastUpdateTimeLong);
        } catch (Exception e) {
            throw new DateTimeParseException("Le timestamp n'est pas bon format",lastUpdateTimeString,0);
        }

        JSONObject json = JSONFactoryUtil.createJSONObject();

        // On récupère tous les events qui ont été ajoutés
        List<CacheJson> ajouts = CacheJsonLocalServiceUtil.getByCreatedDateAndIsActiveAndWithSchedules(lastUpdateTime);
        JSONArray jsonAjout = JSONFactoryUtil.createJSONArray();
        for (CacheJson cache: ajouts) {
            jsonAjout.put(JSONFactoryUtil.createJSONObject(cache.getJsonEvent()));
        }
        json.put("ADD", jsonAjout);

        // On récupère tous les events qui ont été modifiés
        List<CacheJson> modifications = CacheJsonLocalServiceUtil.getByCreatedDateAndModifiedDateAndIsActiveAndWithSchedules(lastUpdateTime);
        JSONArray jsonModif = JSONFactoryUtil.createJSONArray();
        for (CacheJson cache: modifications) {
            jsonModif.put(JSONFactoryUtil.createJSONObject(cache.getJsonEvent()));
        }
        json.put("UPDATE", jsonModif);

        JSONArray jsonSuppr = JSONFactoryUtil.createJSONArray();

        if(!lastUpdateTimeString.equals("0")) {
            // On récupère tous les events qui ont été dépubliés
            List<CacheJson> depublications = CacheJsonLocalServiceUtil.getByModifiedDateAndIsNotActive(lastUpdateTime);
            for (CacheJson cache: depublications) {
                jsonSuppr.put(cache.getEventId());
            }

            // On récupère tous les events qui ont été supprimés
            List<Historic> suppressions = HistoricLocalServiceUtil.getBySuppressionDate(lastUpdateTime);
            for (Historic histo : suppressions) {
                jsonSuppr.put(histo.getEventId());
            }
        }
        json.put("DELETE", jsonSuppr);

        return json;
    }

    public static JSONObject getAgenda() throws URISyntaxException {
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
            jsonTitle.put("fr_FR", thematique.getTitle(Locale.FRANCE));
            jsonThematique.put("title", jsonTitle);

            JSONObject jsonSubtitle = JSONFactoryUtil.createJSONObject();
            jsonSubtitle.put("fr_FR", thematique.getSubtitle(Locale.FRANCE));
            jsonThematique.put("subtitle", jsonSubtitle);

            String imageURL = "";
            if (thematique.getImageId() != null && thematique.getImageId() > 0)
                imageURL = StrasbourgPropsUtil.getURL() + UriHelper.appendUriImagePreview(FileEntryHelper.getFileEntryURLWithTimeStamp(thematique.getImageId()));

            jsonThematique.put("imageURL", imageURL);

        }
        json.put("thematique", jsonThematique);

        if(jsonPrincipal.length() == 0)
            throw new NullPointerException("agenda principal inexistant");

        return json;
    }

    private static JSONArray getJsonIds(Agenda agenda) {

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
        List<Long> longIds = new ArrayList<>();
        if (hits != null) {
            List<CacheJson> cacheJsons = CacheJsonLocalServiceUtil.getCacheJsons(-1,-1);
            for (Document document : hits.getDocs()) {
                long id = Long.parseLong(document.get(Field.ENTRY_CLASS_PK));

                if((campaignsTitle.length() == 0) || campaignsTitle.toString().contains(document.get("campaign"))) {
                    // on ne prend que les event présent dans cacheJson avec des schedules
                    CacheJson cacheJson = cacheJsons.stream().filter(c -> c.getEventId() == id).findFirst().orElse(null);
                    if(Validator.isNotNull(cacheJson) && cacheJson.getHasSchedules())
                        longIds.add(id);
                }
            }
        }
        Collections.sort(longIds);
        for(long id : longIds){
            jsonIds.put(id);
        }
        return jsonIds;
    }
}
