package eu.strasbourg.webservice.csmap.application;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
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
import eu.strasbourg.service.agenda.model.Historic;
import eu.strasbourg.service.agenda.service.CacheJsonLocalService;
import eu.strasbourg.service.agenda.service.CampaignLocalService;
import eu.strasbourg.service.agenda.service.HistoricLocalService;
import eu.strasbourg.service.csmap.model.Agenda;
import eu.strasbourg.service.csmap.service.AgendaLocalService;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.DateHelper;
import eu.strasbourg.utils.FileEntryHelper;
import eu.strasbourg.utils.SearchHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import eu.strasbourg.utils.UriHelper;
import eu.strasbourg.utils.constants.VocabularyNames;
import eu.strasbourg.webservice.csmap.constants.WSConstants;
import eu.strasbourg.webservice.csmap.utils.CSMapJSonHelper;
import eu.strasbourg.webservice.csmap.utils.WSResponseUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Component(
        property = {
                JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=" + WSConstants.APP_GROUP_BASE + WSConstants.APP_EVENT_BASE,
                JaxrsWhiteboardConstants.JAX_RS_NAME + "=" + WSConstants.APP_EVENT_NAME,
                "auth.verifier.guest.allowed=true",
                "liferay.access.control.disable=true"
        },
        service = Application.class
)
public class EventApplication extends Application {

    public Set<Object> getSingletons() {
        return Collections.singleton(this);
    }

    private final Log log = LogFactoryUtil.getLog(this.getClass().getName());

    @GET
    @Produces("application/json")
    @Path("/get-events")
    public Response getEvents() {
        return getEvents("0");
    }

    @GET
    @Produces("application/json")
    @Path("/get-events/{last_update_time}")
    public Response getEvents(
            @PathParam("last_update_time") String lastUpdateTimeString) {

        // On transforme la date string en date
        Date lastUpdateTime;
        try {
            long lastUpdateTimeLong = Long.parseLong(lastUpdateTimeString);
            lastUpdateTime = DateHelper.getDateFromUnixTimestamp(lastUpdateTimeLong);
        } catch (Exception e) {
            return WSResponseUtil.buildErrorResponse(400, "Format de date incorrect");
        }

        JSONObject json = JSONFactoryUtil.createJSONObject();

        try {
            // On récupère tous les events qui ont été ajoutés
            List<CacheJson> ajouts = cacheJsonLocalService.getByCreatedDateAndIsActiveAndWithSchedules(lastUpdateTime);
            JSONArray jsonAjout = JSONFactoryUtil.createJSONArray();
            for (CacheJson cache: ajouts) {
                jsonAjout.put(JSONFactoryUtil.createJSONObject(cache.getJsonEvent()));
            }
            json.put(WSConstants.JSON_ADD, jsonAjout);

            // On récupère tous les events qui ont été modifiés
            List<CacheJson> modifications = cacheJsonLocalService.getByCreatedDateAndModifiedDateAndIsActiveAndWithSchedules(lastUpdateTime);
            JSONArray jsonModif = JSONFactoryUtil.createJSONArray();
            for (CacheJson cache: modifications) {
                jsonModif.put(JSONFactoryUtil.createJSONObject(cache.getJsonEvent()));
            }
            json.put(WSConstants.JSON_UPDATE, jsonModif);

            JSONArray jsonSuppr = JSONFactoryUtil.createJSONArray();

            if(!lastUpdateTimeString.equals("0")) {
                // On récupère tous les events qui ont été dépubliés
                List<CacheJson> depublications = cacheJsonLocalService.getByModifiedDateAndIsNotActive(lastUpdateTime);
                for (CacheJson cache: depublications) {
                    jsonSuppr.put(cache.getEventId());
                }

                // On récupère tous les events qui ont été supprimés
                List<Historic> suppressions = historicLocalService.getBySuppressionDate(lastUpdateTime);
                for (Historic histo : suppressions) {
                    jsonSuppr.put(histo.getEventId());
                }
            }
            json.put(WSConstants.JSON_DELETE, jsonSuppr);

            if(jsonAjout.length() == 0 && jsonModif.length() == 0 && jsonSuppr.length() == 0)
                return WSResponseUtil.buildOkResponse(json, 201);
        } catch (JSONException e) {
            log.error(e);
            return WSResponseUtil.buildErrorResponse(500, e.getMessage());
        }

        return WSResponseUtil.buildOkResponse(json);
    }

    @POST
    @Produces("application/json")
    @Path("/get-themes")
    public Response getThemes(
            @FormParam("ids_themes") String ids_themes) {
        return getThemes("0", ids_themes);
    }

    @POST
    @Produces("application/json")
    @Path("/get-themes/{last_update_time}")
    public Response getThemes(
            @PathParam("last_update_time") String lastUpdateTimeString,
            @FormParam("ids_themes") String ids_themes) {


        JSONObject json = JSONFactoryUtil.createJSONObject();

        // On transforme la date string en date
        Date lastUpdateTime;
        try {
            long lastUpdateTimeLong = Long.parseLong(lastUpdateTimeString);
            lastUpdateTime = DateHelper.getDateFromUnixTimestamp(lastUpdateTimeLong);
        } catch (Exception e) {
            return WSResponseUtil.lastUpdateTimeFormatError();
        }

        // On vérifie que les ids sont renseignés
        if (Validator.isNull(ids_themes)) {
            ids_themes = "";
        }

        try {
            // On récupère les catégories du vocabulaire des thèmes agenda
            AssetVocabulary eventThemeVocabulary = AssetVocabularyHelper
                    .getGlobalVocabulary(VocabularyNames.EVENT_THEME);
            List<AssetCategory> categories = new ArrayList<>();
            if (Validator.isNotNull(eventThemeVocabulary))
                categories = eventThemeVocabulary.getCategories();

            // On récupère toutes les catégories qui ont été ajoutées ou modifiées
            JSONArray jsonAjout = JSONFactoryUtil.createJSONArray();
            JSONArray jsonModif = JSONFactoryUtil.createJSONArray();

            for (AssetCategory categ : categories) {
                if (lastUpdateTime.before(categ.getCreateDate()))
                    jsonAjout.put(CSMapJSonHelper.eventThemesCSMapJSON(categ));
                else if (lastUpdateTime.before(categ.getModifiedDate()))
                    jsonModif.put(CSMapJSonHelper.eventThemesCSMapJSON(categ));
            }

            json.put(WSConstants.JSON_ADD, jsonAjout);
            json.put(WSConstants.JSON_UPDATE, jsonModif);

            // On récupère toutes les catégories qui ont été supprimées
            JSONArray jsonSuppr = JSONFactoryUtil.createJSONArray();

            if (Validator.isNotNull(ids_themes)) {
                if (Validator.isNotNull(eventThemeVocabulary))
                    for (String idCategory : ids_themes.split(",")) {
                        AssetCategory category = AssetVocabularyHelper.getCategoryByExternalId(eventThemeVocabulary, idCategory);
                        if (Validator.isNull(category)) {
                            jsonSuppr.put(idCategory);
                        }
                    }
            }
            json.put(WSConstants.JSON_DELETE, jsonSuppr);

            if (jsonAjout.length() == 0 && jsonModif.length() == 0 && jsonSuppr.length() == 0)
                return WSResponseUtil.buildOkResponse(json, 201);
        } catch (PortalException e) {
            log.error(e);
            return WSResponseUtil.buildErrorResponse(500, e.getMessage());
        }
        return WSResponseUtil.buildOkResponse(json);
    }

    @POST
    @Produces("application/json")
    @Path("/get-types")
    public Response getTypes(
            @FormParam("ids_types") String ids_types) {
        return getTypes("0", ids_types);
    }

    @POST
    @Produces("application/json")
    @Path("/get-types/{last_update_time}")
    public Response getTypes(
            @PathParam("last_update_time") String lastUpdateTimeString,
            @FormParam("ids_types") String ids_types) {


        JSONObject json = JSONFactoryUtil.createJSONObject();

        // On transforme la date string en date
        Date lastUpdateTime;
        try {
            long lastUpdateTimeLong = Long.parseLong(lastUpdateTimeString);
            lastUpdateTime = DateHelper.getDateFromUnixTimestamp(lastUpdateTimeLong);
        } catch (Exception e) {
            return WSResponseUtil.lastUpdateTimeFormatError();
        }

        // On vérifie que les ids sont renseignés
        if (Validator.isNull(ids_types)) {
            ids_types = "";
        }

        try {
            // On récupère les catégories du vocabulaire des types agenda
            AssetVocabulary eventTypeVocabulary = AssetVocabularyHelper
                    .getGlobalVocabulary(VocabularyNames.EVENT_TYPE);
            List<AssetCategory> categories = new ArrayList<>();
            if (Validator.isNotNull(eventTypeVocabulary))
                categories = eventTypeVocabulary.getCategories();

            // On récupère toutes les catégories qui ont été ajoutées ou modifiées
            JSONArray jsonAjout = JSONFactoryUtil.createJSONArray();
            JSONArray jsonModif = JSONFactoryUtil.createJSONArray();

            for (AssetCategory categ : categories) {
                if (lastUpdateTime.before(categ.getCreateDate()))
                    jsonAjout.put(CSMapJSonHelper.eventTypesCSMapJSON(categ));
                else if (lastUpdateTime.before(categ.getModifiedDate()))
                    jsonModif.put(CSMapJSonHelper.eventTypesCSMapJSON(categ));
            }

            json.put(WSConstants.JSON_ADD, jsonAjout);
            json.put(WSConstants.JSON_UPDATE, jsonModif);

            // On récupère toutes les catégories qui ont été supprimées
            JSONArray jsonSuppr = JSONFactoryUtil.createJSONArray();

            if (Validator.isNotNull(ids_types)) {
                if (Validator.isNotNull(eventTypeVocabulary))
                    for (String idCategory : ids_types.split(",")) {
                        AssetCategory category = AssetVocabularyHelper.getCategoryByExternalId(eventTypeVocabulary, idCategory);
                        if (Validator.isNull(category)) {
                            jsonSuppr.put(idCategory);
                        }
                    }
            }
            json.put(WSConstants.JSON_DELETE, jsonSuppr);

            if (jsonAjout.length() == 0 && jsonModif.length() == 0 && jsonSuppr.length() == 0)
                return WSResponseUtil.buildOkResponse(json, 201);
        } catch (PortalException e) {
            log.error(e);
            return WSResponseUtil.buildErrorResponse(500, e.getMessage());
        }
        return WSResponseUtil.buildOkResponse(json);
    }

    @GET
    @Produces("application/json")
    @Path("/get-agendas")
    public Response getAgendas() {

        JSONObject json = JSONFactoryUtil.createJSONObject();

        // On récupère tous les ids events de l'agenda principal
        Agenda principal = agendaLocalService.getAgendaPrincipal();
        JSONObject jsonPrincipal = JSONFactoryUtil.createJSONObject();
        JSONArray jsonIds = getJsonIds(principal);
        jsonPrincipal.put(WSConstants.JSON_IDS, jsonIds);
        json.put(WSConstants.JSON_AGENDA_PRINCIPAL, jsonPrincipal);

        // On récupère tous les ids events de l'agenda thématique s'il y en a un
        Agenda thematique = agendaLocalService.getAgendaThematiqueActif();
        JSONObject jsonThematique = JSONFactoryUtil.createJSONObject();
        if(Validator.isNotNull(thematique)) {
            jsonIds = getJsonIds(thematique);
            jsonThematique.put(WSConstants.JSON_IDS, jsonIds);

            JSONObject jsonTitle = JSONFactoryUtil.createJSONObject();
            jsonTitle.put(WSConstants.JSON_LANGUAGE_FRANCE, thematique.getTitle(Locale.FRANCE));
            jsonThematique.put(WSConstants.JSON_TITLE, jsonTitle);

            JSONObject jsonSubtitle = JSONFactoryUtil.createJSONObject();
            jsonSubtitle.put(WSConstants.JSON_LANGUAGE_FRANCE, thematique.getSubtitle(Locale.FRANCE));
            jsonThematique.put(WSConstants.JSON_SUBTITLE, jsonSubtitle);

            String imageURL = "";
            try {
                if (thematique.getImageId() != null && thematique.getImageId() > 0)
                    imageURL = StrasbourgPropsUtil.getURL() + UriHelper.appendUriImagePreview(FileEntryHelper.getFileEntryURLWithTimeStamp(thematique.getImageId()));
            } catch (URISyntaxException e) {
                log.error(e);
            }
            jsonThematique.put(WSConstants.JSON_IMAGE_URL, imageURL);

        }
        json.put(WSConstants.JSON_AGENDA_THEMATIQUE, jsonThematique);

        if(jsonPrincipal.length() == 0)
            return WSResponseUtil.buildErrorResponse(500, "agenda principal inexistant");

        return WSResponseUtil.buildOkResponse(json);
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
                Campaign campaign = campaignLocalService.fetchCampaign(Long.parseLong(campaignId));
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
            List<CacheJson> cacheJsons = cacheJsonLocalService.getCacheJsons(-1,-1);
            for (Document document : hits.getDocs()) {
                long id = Long.parseLong(document.get(Field.ENTRY_CLASS_PK));

                if((campaignsTitle.length() == 0) || campaignsTitle.toString().contains(document.get("campaign"))) {
                    // on ne prend que les event présent dans cacheJson avec des schedules
                    CacheJson cacheJson = cacheJsons.stream().filter(c -> c.getEventId() == id).findFirst().orElse(null);
                    if(Validator.isNotNull(cacheJson) && cacheJson.getHasSchedules())
                        jsonIds.put(id);
                }
            }
        }

        return jsonIds;
    }

    @Reference
    protected CacheJsonLocalService cacheJsonLocalService;

    @Reference(unbind = "-")
    protected void setCacheJsonLocalService(CacheJsonLocalService cacheJsonLocalService) {
        this.cacheJsonLocalService = cacheJsonLocalService;
    }

    @Reference
    protected HistoricLocalService historicLocalService;

    @Reference(unbind = "-")
    protected void setHistoricLocalService(HistoricLocalService historicLocalService) {
        this.historicLocalService = historicLocalService;
    }

    @Reference
    protected AgendaLocalService agendaLocalService;

    @Reference(unbind = "-")
    protected void setAgendaLocalService(AgendaLocalService agendaLocalService) {
        this.agendaLocalService = agendaLocalService;
    }

    @Reference
    protected CampaignLocalService campaignLocalService;

    @Reference(unbind = "-")
    protected void setCampaignLocalService(CampaignLocalService campaignLocalService) {
        this.campaignLocalService = campaignLocalService;
    }
}
