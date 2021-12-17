package eu.strasbourg.webservice.numerique_responsable.application;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.webservice.numerique_responsable.constants.WSConstants;
import eu.strasbourg.webservice.numerique_responsable.service.WSSearch;
import eu.strasbourg.webservice.numerique_responsable.utils.WSResponseUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.Locale;
import java.util.Set;

/**
 * @author angelique.champougny
 */
@Component(
    property = {
        JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=" + WSConstants.APP_GROUP_BASE + WSConstants.APP_SEARCH_BASE,
        JaxrsWhiteboardConstants.JAX_RS_NAME + "=" + WSConstants.APP_SEARCH_NAME,
        "auth.verifier.guest.allowed=true",
        "liferay.access.control.disable=true"
    },
    service = Application.class
)
public class SearchApplication extends Application {

    public Set<Object> getSingletons() {
        return Collections.singleton(this);
    }

    private final Log log = LogFactoryUtil.getLog(this.getClass().getName());

    @GET
    @Produces("application/json")
    @Path("/get-journal-articles/{keywords}/{locale}/{start}/{end}")
    public Response getJournalArticles(
            @PathParam("keywords") String keywords,
            @PathParam("locale") String locale,
            @PathParam("start") String startString,
            @PathParam("end") String endString) {

        // On vérifie que les attributs sont renseignés
        if (Validator.isNull(keywords)) {
            keywords = "";
        }
        if (Validator.isNull(locale)) {
            locale = Locale.FRANCE.getLanguage();
        }
        int start = 0;
        if (Validator.isNotNull(startString)) {
            start = Integer.parseInt(startString);
        }
        int end = 12;
        if (Validator.isNotNull(endString)) {
            end = Integer.parseInt(endString);
        }

        JSONObject response;

        try {
            response = WSSearch.getJournalArticles(keywords, locale, start, end);
            int httpResponseCode = (int)response.get(WSConstants.JSON_RESPONSE_CODE);
            String httpResponseMessage = (String)response.get(WSConstants.JSON_ERROR_DESCRIPTION);

            if (httpResponseCode >= 400) {
                return WSResponseUtil.buildErrorResponse(httpResponseCode, httpResponseMessage);
            }

            if (httpResponseCode > 200) {
                return WSResponseUtil.buildOkResponse(response, httpResponseCode);
            }
            return WSResponseUtil.buildOkResponse(response);

        } catch (Exception e) {
            log.error(e);
            return WSResponseUtil.buildErrorResponse(500, e.getMessage());
        }
    }
}
