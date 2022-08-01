package eu.strasbourg.webservice.numerique_responsable.application;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.utils.HcaptchaHelper;
import eu.strasbourg.utils.MailHelper;
import eu.strasbourg.webservice.numerique_responsable.constants.WSConstants;
import eu.strasbourg.webservice.numerique_responsable.service.WSSearch;
import eu.strasbourg.webservice.numerique_responsable.utils.WSResponseUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.Set;

/**
 * @author angelique.champougny
 */
@Component(
    property = {
        JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=" + WSConstants.APP_GROUP_BASE + WSConstants.APP_CONTACT_BASE,
        JaxrsWhiteboardConstants.JAX_RS_NAME + "=" + WSConstants.APP_CONTACT_NAME,
        "auth.verifier.guest.allowed=true",
        "liferay.access.control.disable=true"
    },
    service = Application.class
)
public class ContactApplication extends Application {

    public Set<Object> getSingletons() {
        return Collections.singleton(this);
    }

    private final Log log = LogFactoryUtil.getLog(this.getClass().getName());

    @POST
    @Produces("application/json")
    @Path("/send-mail")
    public Response sendMail(
            @FormParam("toAddress") String toAddress,
            @FormParam("firstName") String firstName,
            @FormParam("lastName") String lastName,
            @FormParam("mail") String mail,
            @FormParam("phone") String phone,
            @FormParam("reason") String reason,
            @FormParam("description") String description,
            @FormParam("token") String token) {

        // On vérifie que les attributs sont renseignés
        boolean hasError = false;
        JSONArray jsonErrors = JSONFactoryUtil.createJSONArray();
        if (Validator.isNull(toAddress)) {
            hasError = true;
            jsonErrors.put("error_to_address");
        }else{
            if (!Validator.isEmailAddress(toAddress)) {
                hasError = true;
                jsonErrors.put("error_valid_to_address");
            }
        }

        if (Validator.isNull(firstName)){
            hasError = true;
            jsonErrors.put("error_first_name");
        }

        if (Validator.isNull(lastName)){
            hasError = true;
            jsonErrors.put("error_last_name");
        }

        if (Validator.isNull(mail)){
            hasError = true;
            jsonErrors.put("error_mail");
        }else{
            if (!Validator.isEmailAddress(mail)) {
                hasError = true;
                jsonErrors.put("error_valid_mail");
            }
        }

        if (Validator.isNull(phone))
            phone = "";
        else{
            if (!Validator.isNumber(phone.replaceAll("\\s", "").replace("+", "00"))) {
                hasError = true;
                jsonErrors.put("error_valid_phone");
            }
        }

        if (Validator.isNull(reason)){
            hasError = true;
            jsonErrors.put("error_reason");
        }

        if (Validator.isNull(description)){
            hasError = true;
            jsonErrors.put("error_description");
        }

        if(Validator.isNull(token)){
            hasError = true;
            jsonErrors.put("error_captcha");
        }else{
            if (!HcaptchaHelper.verify(token)) {
                hasError = true;
                jsonErrors.put("error_captcha");
            }
        }

        if(hasError) {
            JSONObject jsonAllErrors = JSONFactoryUtil.createJSONObject();
            jsonAllErrors.put(WSConstants.JSON_ERROR_DESCRIPTION, jsonErrors);
            return WSResponseUtil.buildErrorResponse(400, jsonAllErrors);
        }

        try {
            String mailSubject = "Demande de contact Num\u00e9rique responsable";
            String mailBody =
                "Nom : " + lastName + "\n" +
                "Pr\u00e9nom : " + firstName + "\n" +
                "Mail : " + mail + "\n" +
                (Validator.isNotNull(phone)?"T\u00e9l\u00e9phone : " + phone + "\n":"") +
                "La demande concerne : " + reason + "\n" +
                "Message :\n" + description;
            if (MailHelper.sendMailWithPlainText(mail, toAddress, mailSubject, mailBody)) {
                JSONObject response = JSONFactoryUtil.createJSONObject();
                response.put(WSConstants.JSON_RESPONSE, "mail_send");
                return WSResponseUtil.buildOkResponse(response, 200);
            }else{
                return WSResponseUtil.buildErrorResponse(500, "error_technical");
            }

        } catch (Exception e) {
            log.error(e);
            return WSResponseUtil.buildErrorResponse(500, e.getMessage());
        }
    }

   @Reference(unbind = "-")
    protected void setWSSearch(WSSearch search) {
        this.search = search;
    }

    @Reference
    protected WSSearch search;
}
