package eu.strasbourg.webservice.csmap.application;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.oidc.exception.NoSuchPublikUserException;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.utils.ProcedureHelper;
import eu.strasbourg.utils.exception.NoUserFormException;
import eu.strasbourg.utils.models.Procedure;
import eu.strasbourg.webservice.csmap.constants.WSConstants;
import eu.strasbourg.webservice.csmap.exception.InvalidJWTException;
import eu.strasbourg.webservice.csmap.exception.NoJWTInHeaderException;
import eu.strasbourg.webservice.csmap.exception.NoSubInJWTException;
import eu.strasbourg.webservice.csmap.service.WSAccountData;
import eu.strasbourg.webservice.csmap.service.WSAuthenticator;
import eu.strasbourg.webservice.csmap.utils.WSResponseUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author angelique.champougny
 */
@Component(
    property = {
        JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=" + WSConstants.APP_GROUP_BASE + WSConstants.APP_ACCOUNT_DATA_BASE,
        JaxrsWhiteboardConstants.JAX_RS_NAME + "=" + WSConstants.APP_ACCOUNT_DATA_NAME,
        "auth.verifier.guest.allowed=true",
        "liferay.access.control.disable=true"
    },
    service = Application.class
)
public class AccountDataApplication extends Application {

    public Set<Object> getSingletons() {
        return Collections.singleton(this);
    }

    private final Log log = LogFactoryUtil.getLog(this.getClass().getName());

    @GET
    @Produces("application/json")
    @Path("/get-procedures")
    public Response getProcedures(
            @Context HttpHeaders httpHeaders) {

        JSONArray jsonProcedures = JSONFactoryUtil.createJSONArray();

        try {
            PublikUser publikUser = authenticator.validateUserInJWTHeader(httpHeaders);

            // On récupère toutes les procédures
            List<Procedure> procedures = ProcedureHelper.getProcedures(publikUser.getPublikId(), WSConstants.TIMEOUT);
            if(procedures.isEmpty())
                return WSResponseUtil.buildOkResponse(jsonProcedures, 201);
            for (Procedure procedure: procedures) {
                JSONObject jsonProcedure = JSONFactoryUtil.createJSONObject();

                if (Validator.isNotNull(procedure.getName()))
                    jsonProcedure.put(WSConstants.JSON_TITLE, procedure.getName());

                if (Validator.isNotNull(procedure.getStatus()))
                    jsonProcedure.put(WSConstants.JSON_STATUS, procedure.getStatus());

                if (Validator.isNotNull(procedure.getUrl()))
                    jsonProcedure.put(WSConstants.JSON_URL, procedure.getUrl());

                jsonProcedures.put(jsonProcedure);
            }
        }catch (NoJWTInHeaderException e) {
            log.error(e);
            return WSResponseUtil.buildErrorResponse(400, e.getMessage());
        } catch (InvalidJWTException | NoSubInJWTException | NoSuchPublikUserException e) {
            log.error(e);
            return WSResponseUtil.buildErrorResponse(401, e.getMessage());
        }   catch (NoUserFormException e){
            log.error(e);
            return WSResponseUtil.buildErrorResponse(500, e.getMessage());
        }

        return WSResponseUtil.buildOkResponse(jsonProcedures);
    }

    @GET
    @Produces("application/json")
    @Path("get-mediatheque")
    public Response getMediatheque(@Context HttpHeaders httpHeaders) {

        JSONObject response = JSONFactoryUtil.createJSONObject();

        try {
            PublikUser publikUser = authenticator.validateUserInJWTHeader(httpHeaders);

            response = WSAccountData.getMediatheque(publikUser.getPublikId(), WSConstants.TIMEOUT_WIDGET);
            int httpResponseCode = (int)response.get("responseCode");
            String httpResponseMessage = (String)response.get("errorDescription");

            if (httpResponseCode == 200) {
                return WSResponseUtil.buildOkResponse(response);
            }
            if (httpResponseCode == 500 || httpResponseCode == 400) {
                return WSResponseUtil.buildErrorResponse(httpResponseCode, httpResponseMessage);
            }

        } catch (NoJWTInHeaderException e) {
            log.error(e);
            return WSResponseUtil.buildErrorResponse(400, e.getMessage());
        } catch (InvalidJWTException | NoSubInJWTException | NoSuchPublikUserException e) {
            log.error(e);
            return WSResponseUtil.buildErrorResponse(401, e.getMessage());
        } catch (Exception e) {
            log.error(e);
            return WSResponseUtil.buildErrorResponse(500, e.getMessage());
        }
        return WSResponseUtil.buildOkResponse(response);
    }

    @GET
    @Produces("application/json")
    @Path("get-resid")
    public Response getResid(@Context HttpHeaders httpHeaders) {

        JSONObject response = JSONFactoryUtil.createJSONObject();

        try {
            PublikUser publikUser = authenticator.validateUserInJWTHeader(httpHeaders);

            response = WSAccountData.getResid(publikUser.getPublikId(), WSConstants.TIMEOUT_WIDGET);
            int httpResponseCode = (int)response.get("responseCode");

            if (httpResponseCode == 200) {
                return WSResponseUtil.buildOkResponse(response);
            }
            if (httpResponseCode == 500 || httpResponseCode == 400) {
                String httpResponseMessage = (String)response.get("errorDescription");
                return WSResponseUtil.buildErrorResponse(httpResponseCode, httpResponseMessage);
            }

        } catch (NoJWTInHeaderException e) {
            log.error(e);
            return WSResponseUtil.buildErrorResponse(400, e.getMessage());
        } catch (InvalidJWTException | NoSubInJWTException | NoSuchPublikUserException e) {
            log.error(e);
            return WSResponseUtil.buildErrorResponse(401, e.getMessage());
        } catch (Exception e) {
            log.error(e);
            return WSResponseUtil.buildErrorResponse(500, e.getMessage());
        }
        return WSResponseUtil.buildOkResponse(response);
    }

    @GET
    @Produces("application/json")
    @Path("get-family-space")
    public Response getFamily(@Context HttpHeaders httpHeaders) {

        JSONObject response = JSONFactoryUtil.createJSONObject();

        try {
            PublikUser publikUser = authenticator.validateUserInJWTHeader(httpHeaders);

            response = WSAccountData.getFamily(publikUser.getPublikId(), WSConstants.TIMEOUT_WIDGET);
            int httpResponseCode = (int)response.get("responseCode");
            String httpResponseMessage = (String)response.get("errorDescription");

            if (httpResponseCode == 200) {
                return WSResponseUtil.buildOkResponse(response);
            }
            if (httpResponseCode == 500 || httpResponseCode == 400) {
                return WSResponseUtil.buildErrorResponse(httpResponseCode, httpResponseMessage);
            }

        } catch (NoJWTInHeaderException e) {
            log.error(e);
            return WSResponseUtil.buildErrorResponse(400, e.getMessage());
        } catch (InvalidJWTException | NoSubInJWTException | NoSuchPublikUserException e) {
            log.error(e);
            return WSResponseUtil.buildErrorResponse(401, e.getMessage());
        } catch (Exception e) {
            log.error(e);
            return WSResponseUtil.buildErrorResponse(500, e.getMessage());
        }
        return WSResponseUtil.buildOkResponse(response);
    }

    @Reference(unbind = "-")
    protected void setWSAuthenticator(WSAuthenticator authenticator) {
        this.authenticator = authenticator;
    }

    @Reference
    protected WSAuthenticator authenticator;
}
