package eu.strasbourg.webservice.csmap.application;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetTagLocalServiceUtil;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.utils.DateHelper;
import eu.strasbourg.webservice.csmap.constants.WSConstants;
import eu.strasbourg.webservice.csmap.utils.CSMapJSonHelper;
import eu.strasbourg.webservice.csmap.utils.WSResponseUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * @author angelique.champougny
 */
@Component(
        property = {
                JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=" + WSConstants.APP_GROUP_BASE + WSConstants.APP_VARIOUS_DATA_BASE,
                JaxrsWhiteboardConstants.JAX_RS_NAME + "=" + WSConstants.APP_VARIOUS_DATA_NAME,
                "auth.verifier.guest.allowed=true",
                "liferay.access.control.disable=true"
        },
        service = Application.class
)
public class VariousDataApplication extends Application {

    public Set<Object> getSingletons() {
        return Collections.singleton(this);
    }

    private final Log log = LogFactoryUtil.getLog(this.getClass().getName());

    @PUT
    @Produces("application/json")
    @Path("/get-news/{last_update_time}")
    public Response getNews(
            @PathParam("last_update_time") String lastUpdateTimeString,
            String params) {

        // On vérifie que lastUpdateTimeString est renseigné
        if (Validator.isNull(lastUpdateTimeString))
            return WSResponseUtil.buildErrorResponse(400,
                    "Il manque le paramètre last_update_time");

        // On transforme la date string en date
        Date lastUpdateTime;
        try {
            long lastUpdateTimeLong = Long.parseLong(lastUpdateTimeString);
            lastUpdateTime = DateHelper.getDateFromUnixTimestamp(lastUpdateTimeLong);
        }catch (Exception e) {
            return WSResponseUtil.buildErrorResponse(400, "Format de date incorrect");
        }

        // On vérifie que les ids sont renseignés
        if (Validator.isNull(params) || !params.contains("ids_news="))
            return WSResponseUtil.buildErrorResponse(400, "Il manque le paramètre ids_news");

        JSONObject json = JSONFactoryUtil.createJSONObject();

        try {
            // On récupère les contenu web de structure Brève ayant le tag csmap
            List<JournalArticle> breves = new ArrayList<>();
            // récupération du tag
            Group group = GroupLocalServiceUtil.getGroups(-1, -1).stream().filter(g -> g.getGroupKey().equals("Strasbourg.eu")).findFirst().get();

            // On récupère toutes les brèves qui ont été ajoutées ou modifiées
            JSONArray jsonAjout = JSONFactoryUtil.createJSONArray();
            JSONArray jsonModif = JSONFactoryUtil.createJSONArray();
            AssetTag tag = AssetTagLocalServiceUtil.getGroupTags(group.getGroupId()).stream().filter(t -> t.getName().equals("csmap22")).findFirst().orElse(null);

            if(tag != null) {
                // récupération de l'assetEntry
                List<AssetEntry> entries = AssetEntryLocalServiceUtil.getAssetTagAssetEntries(tag.getTagId());

                // récupération de la structure
                DDMStructure structure = DDMStructureLocalServiceUtil.getStructures(group.getGroupId()).stream().filter(s -> s.getName(Locale.FRANCE).equals("Breve")).findFirst().orElse(null);
                for (AssetEntry entry : entries) {
                    JournalArticle journalArticle = JournalArticleLocalServiceUtil.getLatestArticle(entry.getClassPK());
                    if (structure.getStructureKey().equals(journalArticle.getDDMStructureKey()))
                        breves.add(journalArticle);
                }

                for (JournalArticle breve : breves) {
                    JSONObject jsonWC = CSMapJSonHelper.getBreveCSMapJSON(breve);

                    if (lastUpdateTime.before(breve.getCreateDate()))
                        jsonAjout.put(jsonWC);
                    else if (lastUpdateTime.before(breve.getModifiedDate()))
                        jsonModif.put(jsonWC);

                }
            }

            json.put(WSConstants.JSON_ADD, jsonAjout);
            json.put(WSConstants.JSON_UPDATE, jsonModif);


            // On récupère toutes les news qui ont été supprimées
            JSONArray jsonSuppr = JSONFactoryUtil.createJSONArray();
            String[] paramsArray = params.split("ids_news=");
            if(paramsArray.length > 1 ) {
                for (String idNews : paramsArray[1].split(",")) {
                    if (JournalArticleLocalServiceUtil.fetchArticle(Long.parseLong(idNews)) == null)
                        jsonSuppr.put(idNews);
                }
            }
            json.put(WSConstants.JSON_DELETE, jsonSuppr);
        } catch (PortalException e) {
            log.error(e);
            return WSResponseUtil.buildErrorResponse(500, e.getMessage());
        }

        return WSResponseUtil.buildOkResponse(json);
    }

}